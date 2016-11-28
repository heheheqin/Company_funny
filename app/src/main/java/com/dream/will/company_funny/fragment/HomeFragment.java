package com.dream.will.company_funny.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dream.will.company_funny.R;
import com.dream.will.company_funny.adapter.AbsBaseAdapter2;
import com.dream.will.company_funny.bean.HomePageBean;
import com.dream.will.company_funny.inter.IHomePageList;
import com.dream.will.company_funny.ui.CityChoiceActivity;
import com.dream.will.company_funny.ui.DiscoverActivity;
import com.dream.will.company_funny.ui.DiscoverActivity2;
import com.dream.will.company_funny.ui.HomeListViewDetailActivity;
import com.dream.will.company_funny.utils.APIManager;
import com.dream.will.company_funny.utils.IntentUtils;
import com.dream.will.company_funny.utils.L;
import com.dream.will.company_funny.widget.BannerView;
import com.dream.will.company_funny.widget.GradView;
import com.dream.will.company_funny.widget.PullRoRefreshHeadView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Author：Will on 2016/11/21 14:33
 * Mail：heheheqin.will@gmail.com
 */

public class HomeFragment extends Fragment implements View.OnClickListener, AbsListView.OnScrollListener,AdapterView.OnItemClickListener {
    private static final int INTENT_REQUEST_CITY = 914;
    private static final int INTENT_REQUEST_SAO = 915;
    BannerView bannerView;
    PtrFrameLayout refresh;
    boolean isMore;
    int pageFlag = 0;  //刷新
    int buttonMore = 0;  //刷新
    String lastid = null;  //刷新
    private AbsBaseAdapter2<HomePageBean.DataBean> adapter;
    private ListView listView;
    private String cityName;
    private String cityId = "1";
    private View view;
    private TextView localChoice;
    private List<HomePageBean.DataBean> dataBeen;
//    自定义的 listView视图头部
    GradView gradView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d("HomeFragment --- onCreate");
        //获取数据  两个地方   开始进来到时候  城市改变时候
        bannerView = new BannerView(getActivity());
        dataBeen = new ArrayList<>();
        initData();
        getData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView saoma = (ImageView) view.findViewById(R.id.sao);
        listView = (ListView) view.findViewById(R.id.home_listView);
        saoma.setOnClickListener(this);
        initRefresh(view);
    }

    private void initRefresh(View view) {
        refresh = (PtrFrameLayout) view.findViewById(R.id.refresh);
        //创建刷新头
        PullRoRefreshHeadView pullRoRefreshHeadView = new PullRoRefreshHeadView(getActivity());
        //添加头
        refresh.setHeaderView(pullRoRefreshHeadView);
        //添加属性头控件
        refresh.addPtrUIHandler(pullRoRefreshHeadView);
        //刷新事件
        refresh.setPtrHandler(new PtrDefaultHandler() {
            //属性方法
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //刷新代码
                getData();
            }

            //
            //解决Listview 和下拉刷新冲突冲突
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, content, header);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        localChoice = (TextView) view.findViewById(R.id.local);

        gradView = new GradView(getActivity());
        localChoice.setOnClickListener(this);

        listView.addHeaderView(bannerView);
        listView.addHeaderView(gradView);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(this);
        listView.setOnItemClickListener(this);
        bannerView.setCityId(cityId);
        restorView();
    }

    /**
     * 恢复 定位信息
     */
    private void restorView() {
        if (cityName != null) {
            localChoice.setText(cityName);
        }
    }
    ///////////////////////////////////////////////////////////////////////////
    // lv滚动监听
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.local: {
                Intent intent = new Intent(getActivity(), CityChoiceActivity.class);
                startActivityForResult(intent, INTENT_REQUEST_CITY);
            }
            break;
            case R.id.sao: {
                Toast.makeText(getActivity(), "----", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(i, INTENT_REQUEST_SAO);
            }
            break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //根据requestCode判断是那个界面返回
        switch (requestCode) {
            case INTENT_REQUEST_CITY: {
                //城市选择界面返回
                if (data != null) {
                    //取出数据 1.城市名称
                    cityName = data.getStringExtra(IntentUtils.KEY_CITYNAME);
                    cityId = data.getStringExtra(IntentUtils.KEY_CITYID);
                    L.d("onActivityResult+cityId:::" + cityId);
                    localChoice.setText(cityName);
                    //获取数据  两个地方   开始进来到时候  城市改变时候
                    getData();
                    bannerView.setCityId(cityId);
                }
            }
            break;
            case INTENT_REQUEST_SAO: {
                //扫码返回结果
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        String string = bundle.getString(CodeUtils.RESULT_STRING);
                        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }
    }

    //切换城市调用方法
    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        IHomePageList iHomePageList = retrofit.create(IHomePageList.class);
        Call<String> call = iHomePageList.getListContent("0", "0", cityId,null);
//        L.d("请求城市："+cityId);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                L.d("首页请求成功"+response.body());
                String body = response.body();
                //把字符串解析层HomePageBean
                Gson gson = new Gson();
                HomePageBean bean = gson.fromJson(body, new TypeToken<HomePageBean>() {
                }.getType());
                dataBeen.clear();
                dataBeen.addAll(bean.getData());
                adapter.notifyDataSetChanged();
                //结束刷新状态
                refresh.refreshComplete();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                L.d("首页请求失败");
                refresh.refreshComplete();
            }
        });
    }

    private void initData() {
        adapter = new AbsBaseAdapter2<HomePageBean.DataBean>(getActivity()
                , dataBeen, R.layout.home_list_item_layout1, R.layout.home_list_item_layout2) {
            @Override
            public void bindData(int position, ViewHolder viewHolder) {
                //绑定数据
                //根据不同类型 知道布局类型加载不同布局
                HomePageBean.DataBean dataBean = dataBeen.get(position);

//                if (dataBean.getType()==0) {
//
//                }
                ImageView im = (ImageView) viewHolder.findViewBid(R.id.item_image);
                Glide.with(getActivity()).load(dataBean.getGroupthumbnail()).into(im);
                TextView textView = (TextView) viewHolder.findViewBid(R.id.item_text_msg);
                TextView textView1 = (TextView) viewHolder.findViewBid(R.id.item_text);
                textView.setText(dataBean.getTitle());
                textView1.setText(dataBean.getSummary().trim());

            }

            @Override
            public int getItemViewType(int position) {
                HomePageBean.DataBean dataBean = dataBeen.get(position);
                String type = dataBean.getType();
                return Integer.valueOf(type);
            }
        };
    }

    //下拉加载时调用方法
    public void getMore() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        IHomePageList iHomePageList = retrofit.create(IHomePageList.class);
        Call<String> call = iHomePageList.getMore(cityId,lastid);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                L.d("onScrollStateChanged::" + response.body());
                String body = response.body();
                //把字符串解析层HomePageBean
                Gson gson = new Gson();
                HomePageBean bean = gson.fromJson(body, new TypeToken<HomePageBean>() {
                }.getType());
//                L.d("onScrollStateChanged"+);
                dataBeen.addAll(bean.getData());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                L.d("onScrollStateChanged请求失败");
            }
        });
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == 0 && isMore) {
            lastid = dataBeen.get(dataBeen.size()-1).getId();
            L.d("onScrollStateChanged:::lastid:::" + lastid);
            getMore();
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //滑动到底部时候  设置标志位为true
        isMore = firstVisibleItem + visibleItemCount == totalItemCount;
    }
    //点击时跳转浏览新闻
    //thumbnail
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        if (position < listView.getHeaderViewsCount()){
            return;
        }

        if(dataBeen.get(position-listView.getHeaderViewsCount()).getType().equals("0")){
            intent = new Intent(getActivity(), HomeListViewDetailActivity.class);
        }else {
            intent = new Intent(getActivity(), DiscoverActivity2.class);
        }
//        L.d("Home- onItemClick::"+position);
        String url = dataBeen.get(position-listView.getHeaderViewsCount()).getId();
        intent.putExtra(IntentUtils.KEY_NEWSID,url);
        String url1 = APIManager.BASE_URL_ZIXUN+dataBeen.get(position-listView.getHeaderViewsCount()).getId()+"/";
        intent.putExtra(IntentUtils.KEY_NEWD,url1);
        L.d("url1:"+url1);
        L.d("fragment0::"+url);
        startActivity(intent);
//        L.d("Home- onItemClick--"+dataBeen.get(position-2).getThumbnail());
    }
}

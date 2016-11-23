package com.dream.will.company_funny.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.will.company_funny.R;
import com.dream.will.company_funny.adapter.AbsBaseAdapter;
import com.dream.will.company_funny.adapter.AbsBaseAdapter2;
import com.dream.will.company_funny.bean.HomePageBean;
import com.dream.will.company_funny.inter.IHomePageList;
import com.dream.will.company_funny.ui.CityChoiceActivity;
import com.dream.will.company_funny.utils.APIManager;
import com.dream.will.company_funny.utils.IntentUtils;
import com.dream.will.company_funny.utils.L;
import com.dream.will.company_funny.widget.BannerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Author：Will on 2016/11/21 14:33
 * Mail：heheheqin.will@gmail.com
 */

public class HomeFragment extends Fragment implements View.OnClickListener {
    private  static final int INTENT_REQUEST_CITY = 914;
    private AbsBaseAdapter2<HomePageBean.DataBean> adapter;
    private ListView lv;
    private String cityName;
    private String cityId = "1";
    private View view;
    private TextView localChoice;
    private List<HomePageBean.DataBean> dataBeen;
    BannerView bannerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d("HomeFragment --- onCreate");
        //获取数据  两个地方   开始进来到时候  城市改变时候
        getData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.home_fragment_layout, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        localChoice = (TextView) view.findViewById(R.id.local);
        lv = (ListView) view.findViewById(R.id.home_listView);
        View grad = LayoutInflater.from(getActivity()).inflate(R.layout.home_list_grad, null);
        localChoice.setOnClickListener(this);
        lv.addHeaderView(bannerView);
        lv.addHeaderView(grad);
        lv.setAdapter(adapter);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.local: {
                Intent intent = new Intent(getActivity(), CityChoiceActivity.class);
                startActivityForResult(intent, INTENT_REQUEST_CITY);
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
                    localChoice.setText(cityName);
                    //获取数据  两个地方   开始进来到时候  城市改变时候
                    getData();
                    bannerView.setCityId(cityId);
                }
            }
            break;
        }
    }

    private void getData() {
        bannerView  = new BannerView(getActivity());
        dataBeen = new ArrayList<>();
        adapter = new AbsBaseAdapter2<HomePageBean.DataBean>(getActivity(), dataBeen, R.layout.home_list_item_layout1,R.layout.home_list_item_layout2) {
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


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        IHomePageList iHomePageList = retrofit.create(IHomePageList.class);
        Call<String> call = iHomePageList.getListContent("0", "0", cityId);
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
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                L.d("首页请求失败");
            }
        });
    }
}

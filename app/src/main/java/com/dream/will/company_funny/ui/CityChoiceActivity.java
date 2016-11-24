package com.dream.will.company_funny.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.adapter.CityChoiceAdapter;
import com.dream.will.company_funny.bean.CityBean;
import com.dream.will.company_funny.inter.ICityChoice;
import com.dream.will.company_funny.utils.APIManager;
import com.dream.will.company_funny.utils.CityJsonUtils;
import com.dream.will.company_funny.utils.IntentUtils;
import com.dream.will.company_funny.utils.L;
import com.dream.will.company_funny.utils.SharedUtils;
import com.dream.will.company_funny.widget.SlideLetterView;
import com.dream.will.company_funny.widget.SlideView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class CityChoiceActivity extends BaseNoActionBarActivity implements SlideView.SlideClickCallback,AdapterView.OnItemClickListener {

    private static final int YES = 419;
    List<CityBean> data;
    CityChoiceAdapter adapter;
    String str_srarch;
    int currentPosition = -1;
    private StickyListHeadersListView stickyListView;
    private SlideView slideView;
    private SlideLetterView leterView;
    //清除文本消息  延迟搜索
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case YES: {
                    leterView.setVisibility(View.GONE);
                }
                break;
            }
        }
    };
    private EditText editText;
    private ImageView editText_cancel;

    Handler han = new Handler();
    Runnable searchRunable = new Runnable() {
        @Override
        public void run() {
            adapter.search(str_srarch);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_citychoice);
        initView();
        initData();
        adapter = new CityChoiceAdapter(this, data);
        stickyListView.setAdapter(adapter);
        L.d("CityChoiceActivity");
        getCityData();


    }

    private void getCityData() {
        //联网下载数据
        //1。定义Retrofit对象，构建者模式
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIManager.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        //2。创建接口ICityChoice实例
        ICityChoice iCityChoice = retrofit.create(ICityChoice.class);
        //3。调用接口实例
        Call<String> call = iCityChoice.getCity();
        //4。发请求
        call.enqueue(new Callback<String>() {
            /**请求成功
             * @param call
             * @param response
             */
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                L.d(response.body());
                String body = response.body();
                try {
                    List<CityBean> citys = CityJsonUtils.getCityByJson(body);
                    data.clear();
                    data.addAll(citys);
                    adapter.setAllData(citys);
                    //解析成功打印长度
                    adapter.notifyDataSetChanged();
                    L.d(citys.size() + "");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            /**请求失败
             * 网络不通畅  服务器挂了
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                L.d("  请求失败");
            }
        });
    }

    private void initData() {
        data = new ArrayList<>();
    }

    private void initView() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.city_list_item_gps, null);
        stickyListView = (StickyListHeadersListView) findViewById(R.id.stickyListView);
        stickyListView.addHeaderView(inflate);
        //设施监听
        stickyListView.setOnItemClickListener(this);
        slideView = (SlideView) findViewById(R.id.slideView);
        leterView = (SlideLetterView) findViewById(R.id.leterView);
        //设置  互动字母监听
        slideView.setOnSlideClike(this);
        editText = (EditText) findViewById(R.id.editText);
        editText_cancel = (ImageView) findViewById(R.id.editText_cancel);
        //开始时候隐藏  叉叉图标
        editText_cancel.setVisibility(View.GONE);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText_cancel.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                //触发handler 实现搜索功能
                str_srarch = s.toString();
                handler.removeCallbacks(searchRunable);
                han.postDelayed(searchRunable,500);
            }
        });
    }

    /**
     * 侧滑是回调
     *
     * @param position
     * @param str
     */
    @Override
    public void slideOnClick(int position, String str) {
        //显示leterView控件
        //设置文本
        leterView.setVisibility(View.VISIBLE);
        leterView.setText(str);
        //从data集合中遍历到第一条数据以str开图数据位置
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getLetter().equals(str)) {
                //设置lsitView位置
                stickyListView.setSelection(i);
                //找到符合条件数据返回
                return;
            }
        }
        //设置   文本在一秒钟之后消失  首先清除消息后面发送
        handler.removeMessages(YES);
        Message m = Message.obtain();
        m.what = YES;
        handler.sendMessageDelayed(m, 1000);
    }

    // 字母 控件回调方法
    @Override
    public void slideUp() {
        leterView.setVisibility(View.GONE);
    }

    public void cancel(View c) {
        editText.setText("");
        editText_cancel.setVisibility(View.GONE);
    }
    //触摸 城市选择标题  退出 软键盘
    public void closeInputMethed(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
    // 功能栏上  取消按钮
    public void cancelCity(View vw) {
        //输入的时候 退出输入    没有输入的时候退出城市选择
        if (editText.isFocused()) {
            editText.setText("");
            editText.clearFocus();
        }else {
            finish();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       if (SharedUtils.isFirstRun(this)){
           L.d("citychoies --- sharePre");
           Intent intent =new Intent(this,MainActivity.class);
           startActivity(intent);
           finish();
       }else {
           //获取intent  返回到home界面
           Intent intent= getIntent();
           String cityN = null;
           String cityI = null;
           if (position == 0){
               cityN = ((TextView)view.findViewById(R.id.item_city)).getText().toString().trim();
               for (int i = 0; i < data.size(); i++) {
                   if (data.get(i).getCityname().equals(cityN)) {
                       cityI = data.get(i).getCityid();
                   }
               }
           }else {
               cityN = data.get(position-1).getCityname();
               cityI = data.get(position-1).getCityid();
           }
           L.d("onItemClick+cityId:::"+cityI);
           L.d("onItemClick+cityName:::"+cityN);
           intent.putExtra(IntentUtils.KEY_CITYNAME,cityN);
           intent.putExtra(IntentUtils.KEY_CITYID,cityI);
           setResult(1,intent);
           finish();
       }
    }
}

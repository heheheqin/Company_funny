package com.dream.will.company_funny.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.will.company_funny.R;
import com.dream.will.company_funny.bean.HomeNewsDetail;

import java.util.List;

/**
 * Author：Will on 2016/11/25 10:39
 * Mail：heheheqin.will@gmail.com
 * 显示详情界面
 * 标题
 * 来源：时间
 * -------------------
 * 内容（文字和图片交叉）
 */

public class HomeDetailView extends LinearLayout {


    public HomeDetailView(Context context) {
        super(context);
        initView();
    }

    public HomeDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        //设置方向
        setOrientation(VERTICAL);
        setPadding(20,20,20,20);
    }

    public void setData(HomeNewsDetail data) {
        if (data == null) {

        }
        //标题
        LayoutParams param = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        param.setMargins(0,20,0,20);
        TextView title = new TextView(getContext());
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.home_detail_titil));
        title.setText(data.getTitle());
        title.setTextColor(Color.parseColor("#000000"));
        addView(title, param);
        //来源&时间

        TextView laiyuan = new TextView(getContext());
        laiyuan.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.home_detail_time));
        laiyuan.setText(data.getSource() + "  " + data.getTime());
        addView(laiyuan, param);
        //分割线
        View v = new View(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        v.setBackgroundColor(Color.parseColor("#cccccc"));
        addView(v, params);
        //内容  ====== 两种数据
        List<HomeNewsDetail.ContentBean> content = data.getContent();
        for (HomeNewsDetail.ContentBean contentBean : content) {
            //根据数据类型创建控件
            int type = contentBean.getType();
            String value = contentBean.getValue();
            switch (type) {
                //1 文本  2 图片
                case 1: {
                    TextView neirong = new TextView(getContext());
                    neirong.setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimensionPixelSize(R.dimen.home_detail_content));
                    //段落之间换行
                    value = value.replaceAll("\n","\n\n");
                    neirong.setText(value);
                    //行间距
                    neirong.setLineSpacing(1.5f,1.2f);

                    addView(neirong, param);
                }
                break;
                case 2: {
                    ImageView imageView = new ImageView(getContext());
                    Glide.with(getContext()).load(value).into(imageView);
                    addView(imageView,param);
                }
                break;
            }
        }

        //
        //


    }


}

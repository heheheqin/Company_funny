package com.dream.will.company_funny.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.bean.MineListBean;

import java.util.List;

/**
 * Author：Will on 2016/11/21 17:38
 * Mail：heheheqin.will@gmail.com
 */

public class MineListAdapterC extends AbsBaseAdapter {

    List<MineListBean> data;

    public MineListAdapterC(Context context, List data, int layoutId) {
        super(context, data, layoutId);
        this.data = data;
    }

    @Override
    public void bindData(int position, ViewHolder viewHolder) {
        //找到控件
        ImageView itemImage = (ImageView) viewHolder.findViewBid(R.id.item_image);
        TextView itemText = (TextView) viewHolder.findViewBid(R.id.item_text);
        MineListBean mineListBean = data.get(position);
        itemImage.setImageResource(mineListBean.img);
        itemText.setText(mineListBean.title);
    }
}

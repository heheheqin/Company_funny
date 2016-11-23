package com.dream.will.company_funny.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Author：Will on 2016/11/23 14:38
 * Mail：heheheqin.will@gmail.com
 * <p>
 * 自定义抽象万能 BaseAdapter
 */

public abstract class AbsBaseAdapter<T> extends BaseAdapter {
    //数据源
    List<T> data;
    LayoutInflater inflater;
    //布局
    int layoutId;
    //构造方法


    public AbsBaseAdapter(Context context, List<T> data, int layoutId) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //抽吸那个绑定数据方法
    public abstract  void  bindData(int position,ViewHolder viewHolder);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolde;
        if (convertView == null) {
            convertView = inflater.inflate(layoutId, parent, false);
            viewHolde = new ViewHolder(convertView);
            convertView.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolder) convertView.getTag();
        }
        bindData(position,viewHolde);
        return convertView;
    }


    public static class ViewHolder{
        //保存的控件 是需要设置值的控件
        private  View view;
        public ViewHolder(View c){
            this.view = c;
        }

        //向子类提供一个方法，返回需要设置值的控件
        public  View findViewBid(int viewId){
            //根据viewID找到对应控件
            return view.findViewById(viewId);
        }

    }

}

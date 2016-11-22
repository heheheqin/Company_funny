package com.dream.will.company_funny.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.bean.CityBean;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Author：Will on 2016/11/22 10:59
 * Mail：heheheqin.will@gmail.com
 *
 * StickyListAdapter
 */

public class CityChoiceAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    List<CityBean> data;
    LayoutInflater inflater;

    public CityChoiceAdapter(Context context, List<CityBean> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }
    ///////////////////////////////////////////////////////////////////////////
    // StickyListHeadersAdapter
    ///////////////////////////////////////////////////////////////////////////

    /** 头部view
     * @param position  位置
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeadViewHolder viewHolde = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.city_item_layout2, parent, false);
            viewHolde = new HeadViewHolder(convertView);
            convertView.setTag(viewHolde);
        } else {
            viewHolde = (HeadViewHolder) convertView.getTag();
        }
        //关键：：： 绑定数据   首字母
        CityBean cityBean = data.get(position);
        viewHolde.tv_cityHead.setText(cityBean.getLetter());
        return convertView;
    }

    /**
     * @param position  返回头部 头部
     * @return  返回头部 类型  便于分组
     */
    @Override
    public long getHeaderId(int position) {
        return data.get(position).getTypeId();
    }

    class  HeadViewHolder{
        TextView tv_cityHead;
        public HeadViewHolder(View view) {
            tv_cityHead = (TextView) view.findViewById(R.id.cityLetter);
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // BaseAdapter
    ///////////////////////////////////////////////////////////////////////////
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BeanViewHolder viewHolde = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.city_item_layout, parent, false);
            viewHolde = new BeanViewHolder(convertView);
            convertView.setTag(viewHolde);
        } else {
            viewHolde = (BeanViewHolder) convertView.getTag();
        }
        CityBean cityBean = data.get(position);
        viewHolde.tv_cityName.setText(cityBean.getCityname());
        return convertView;
    }
    class  BeanViewHolder{
        TextView tv_cityName;
        public BeanViewHolder(View view) {
            tv_cityName = (TextView) view.findViewById(R.id.cityName);
        }
    }


}

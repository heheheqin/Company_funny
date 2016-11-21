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

public class MineListAdapter extends BaseAdapter {

    List<MineListBean> data;
    LayoutInflater inflater;

    public MineListAdapter(Context context, List<MineListBean> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolde;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.mine_list_item, parent, false);
            viewHolde = new ViewHolder(convertView);
            convertView.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolder) convertView.getTag();
        }
        MineListBean mineListBean = data.get(position);
        viewHolde.itemImage.setImageResource(mineListBean.img);
        viewHolde.itemText.setText(mineListBean.title);
        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView itemImage;
        public TextView itemText;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.itemImage = (ImageView) rootView.findViewById(R.id.item_image);
            this.itemText = (TextView) rootView.findViewById(R.id.item_text);
        }

    }
}

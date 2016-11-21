package com.dream.will.company_funny.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.bean.MessageListBean;

import java.util.List;

/**
 * Author：Will on 2016/11/21 17:38
 * Mail：heheheqin.will@gmail.com
 */

public class MessageListAdapter extends BaseAdapter {

    List<MessageListBean> data;
    LayoutInflater inflater;

    public MessageListAdapter(Context context, List<MessageListBean> data) {
        this.inflater = LayoutInflater.from(context);
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
            convertView = inflater.inflate(R.layout.messge_list_item, parent, false);
            viewHolde = new ViewHolder(convertView);
            convertView.setTag(viewHolde);
        } else {
            viewHolde = (ViewHolder) convertView.getTag();
        }
        MessageListBean messageListBean = data.get(position);
        viewHolde.itemImage.setImageResource(messageListBean.img);
        viewHolde.itemText.setText(messageListBean.title);
        viewHolde.itemTextTiny.setText(messageListBean.tinytitle);
        return convertView;
    }

    public static class ViewHolder {
        public View rootView;
        public ImageView itemImage;
        public TextView itemText;
        public TextView itemTextTiny;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.itemImage = (ImageView) rootView.findViewById(R.id.item_image);
            this.itemText = (TextView) rootView.findViewById(R.id.item_text_msg);
            this.itemTextTiny = (TextView) rootView.findViewById(R.id.item_text);
        }

    }
}

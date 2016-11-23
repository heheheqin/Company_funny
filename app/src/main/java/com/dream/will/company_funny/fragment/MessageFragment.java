package com.dream.will.company_funny.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.adapter.MessageListAdapter;
import com.dream.will.company_funny.adapter.MessageListAdapterC;
import com.dream.will.company_funny.bean.MessageListBean;
import com.dream.will.company_funny.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Will on 2016/11/21 14:33
 * Mail：heheheqin.will@gmail.com
 */

public class MessageFragment extends Fragment {
    View view;
    ListView listView;

    int[] imageId = {
            R.drawable.ic_zixun_pressed,
            R.drawable.ic_kaipan_pressed,
            R.drawable.ic_zufang_pressed
    };

    String[] title = {
            "热点资讯","楼盘动态消息","路线报名消息"
    };
    String[] tinyTitle = {
            "每天最新热点资讯推荐",
            "报名团购，实时获取最新动态",
            "报名看房团，免费接送，礼品丰厚"
    };

    List<MessageListBean> data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d("MessageFragment --- onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        data = new ArrayList<>();
        view = inflater.inflate(R.layout.messge_fragment_layout, container, false);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = (ListView) view.findViewById(R.id.msg_listView);
        for (int i = 0; i < imageId.length; i++) {
            data.add(new MessageListBean(imageId[i], title[i],tinyTitle[i]));
        }
//        listView.setAdapter(new MessageListAdapter(getActivity(),data));
        listView.setAdapter(new MessageListAdapterC(getActivity(),data,R.layout.messge_list_item));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "跳转登录", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

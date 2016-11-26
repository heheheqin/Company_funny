package com.dream.will.company_funny.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.adapter.MineListAdapter;
import com.dream.will.company_funny.adapter.MineListAdapterC;
import com.dream.will.company_funny.bean.MineListBean;
import com.dream.will.company_funny.ui.Main2Activity;
import com.dream.will.company_funny.utils.L;

import java.util.ArrayList;
import java.util.List;

/**
 * Author：Will on 2016/11/21 14:33
 * Mail：heheheqin.will@gmail.com
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    private ImageView setting;
    private ImageView avatar;
    private TextView login;
    private ListView listView;
    private View view;
    int[] imageId = {
            R.drawable.ic_mine_activity_myqb,
            R.drawable.ic_mine_activity_collection,
            R.drawable.ic_mine_activity_group,
            R.drawable.ic_mine_activity_baoming,
            R.drawable.ic_mine_activity_app_commend
    };

    String[] title = {
            "我的Q币","我的收藏","我的团购","我的看房团","应用推荐"
    };

    List<MineListBean> data;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d("MineFragment --- onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        data = new ArrayList<>();
        view = inflater.inflate(R.layout.mine_fragment_layout, container, false);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        avatar =(ImageView) view.findViewById(R.id.avatar);
        avatar.setOnClickListener(this);
        login = (TextView) view.findViewById(R.id.login);
        login.setOnClickListener(this);
        setting =(ImageView) view.findViewById(R.id.setting);
        setting.setOnClickListener(this);
        listView = (ListView) view.findViewById(R.id.listView);
        for (int i = 0; i < imageId.length; i++) {
            data.add(new MineListBean(imageId[i], title[i]));
        }
//        listView.setAdapter(new MineListAdapter(getActivity(),data));
        listView.setAdapter(new MineListAdapterC(getActivity(),data,R.layout.mine_list_item));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loginTO();
                Intent intent = new Intent(getActivity(), Main2Activity.class);
                startActivity(intent);
            }
        });
    }



    private void loginTO() {
        Toast.makeText(getActivity(), "跳转登录", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.avatar:
            case R.id.setting:
            case  R.id.login:{
                loginTO();
            }break;
        }
    }
}

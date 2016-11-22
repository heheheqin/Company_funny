package com.dream.will.company_funny.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.ui.CityChoiceActivity;
import com.dream.will.company_funny.utils.L;

/**
 * Author：Will on 2016/11/21 14:33
 * Mail：heheheqin.will@gmail.com
 */

public class HomeFragment extends Fragment implements View.OnClickListener {
    View view;
    TextView localChoice;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d("HomeFragment --- onCreate");
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
        localChoice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.local: {
                Intent intent = new Intent(getActivity(), CityChoiceActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}

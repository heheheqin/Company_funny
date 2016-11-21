package com.dream.will.company_funny.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dream.will.company_funny.R;

/**
 * Author：Will on 2016/11/21 14:33
 * Mail：heheheqin.will@gmail.com
 */

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.home_fragment_layout,container,false);
    }
}

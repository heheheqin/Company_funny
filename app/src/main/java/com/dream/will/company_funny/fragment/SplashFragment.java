package com.dream.will.company_funny.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dream.will.company_funny.R;

/**
 * Author：Will on 2016/11/21 09:54
 * Mail：heheheqin.will@gmail.com
 */

public class SplashFragment extends Fragment {
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    public static final String INIT_KRY = "INIT_KRY";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.splash_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupView(view);
    }

    public void setupView(View upView) {
        iv1 = (ImageView) upView.findViewById(R.id.iv1);
        iv2 = (ImageView) upView.findViewById(R.id.iv2);
        iv3 = (ImageView) upView.findViewById(R.id.iv3);
        //设置图片
        Bundle arguments = getArguments();
        int index = arguments.getInt(INIT_KRY);
        switch (index) {
            case 0: {
                iv1.setImageResource(R.drawable.guide_content_new_0);
                iv2.setImageResource(R.drawable.guide_content_0);
                iv3.setImageResource(R.drawable.ic_guide_picture_new_0);
            }
            break;
            case 1: {
                iv1.setImageResource(R.drawable.guide_content_new_1);
                iv2.setImageResource(R.drawable.guide_content_1);
                iv3.setImageResource(R.drawable.ic_guide_picture_new_1);
            }
            break;
            case 2: {
                iv1.setImageResource(R.drawable.guide_content_new_2);
                iv2.setImageResource(R.drawable.guide_content_2);
                iv3.setImageResource(R.drawable.ic_guide_picture_new_2);
            }
            break;
            case 3: {
                iv1.setImageResource(R.drawable.guide_content_new_3);
                iv2.setImageResource(R.drawable.guide_content_3);
                iv3.setImageResource(R.drawable.ic_guide_picture_new_3);
            }
            break;
        }
    }
}

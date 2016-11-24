package com.dream.will.company_funny.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dream.will.company_funny.R;
import com.dream.will.company_funny.utils.L;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * Author：Will on 2016/11/24 09:42
 * Mail：heheheqin.will@gmail.com
 * 自定义刷新头
 *
 */

public class PullRoRefreshHeadView  extends FrameLayout implements PtrUIHandler {
    ImageView image;
    int startId = R.drawable.refresh_001;
    int endId = R.drawable.refresh_048;
    int imgCount = endId-startId;

    public PullRoRefreshHeadView(Context context) {
        super(context);
        init();
    }

    public PullRoRefreshHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
//初始化imageview  添加到布局
    private void init() {
        image = new ImageView(getContext());
        //创建布局属性
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        params.setMargins(20,40,20,20);

        addView(image,params);
        image.setImageResource(R.drawable.refresh_001);
    }

    ///////////////////////////////////////////////////////////////////////////
    // PtrUIHandler   实现方法
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void onUIReset(PtrFrameLayout frame) {
        L.d("onUIReset---  ");
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        L.d("onUIRefreshPrepare---  ");
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        L.d("onUIRefreshBegin---  ");
        image.setImageResource(R.drawable.icon_black_progressbar);
        RotateAnimation rotateAnimation = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        image.startAnimation(rotateAnimation);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        image.clearAnimation();
        image.setImageResource(R.drawable.refresh_001);
        L.d("onUIRefreshComplete---  ");
    }

    /**
     * @param frame
     * @param isUnderTouch  手指是否按下
     * @param status  状态
     * @param ptrIndicator  UI指示器变化
     *
     */
    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        L.d("onUIPositionChange---  ");
        L.d("PtrIndicator:::"+ptrIndicator.getCurrentPercent());
        //如果当前状态是刷新状态就不执行后面代码
        if(status == PtrFrameLayout.PTR_STATUS_LOADING){
            return;
        }
        //根据百分比计算加载图片索引
        //如果达到100%图片就固定

        int pre = (int) (ptrIndicator.getCurrentPercent()*imgCount);
        if (pre <48){
            image.setImageResource(startId+pre);
        }else {
            image.setImageResource(endId);
        }


    }
    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////
}

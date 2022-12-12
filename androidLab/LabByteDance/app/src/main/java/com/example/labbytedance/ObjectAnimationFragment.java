package com.example.labbytedance;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

//import com.example.hellofragment.R;

public class ObjectAnimationFragment extends BaseFragment {

    private static final String TAG = "StaticFragment";

    private ImageView mRobot;

    public ObjectAnimationFragment() {

    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_object_animation, container, false);
        mRobot = view.findViewById(R.id.iv_robot);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != mRobot) {
            //透明度动画
            ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mRobot,"alpha",1,0,1);
            alphaAnimator.setDuration(1000);
            alphaAnimator.setInterpolator(new LinearInterpolator());

            //旋转动画：围绕y轴旋转
            ObjectAnimator rotationYAnimator = ObjectAnimator.ofFloat(mRobot,"rotationY",0,180,0);
            rotationYAnimator.setDuration(1000);
            rotationYAnimator.setInterpolator(new LinearInterpolator());

            //平移动画:在x轴上平移
            ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(mRobot, "translationX", 0, 200, -200,0);
            translationXAnimator.setDuration(1000);
            translationXAnimator.setInterpolator(new LinearInterpolator());

            //缩放动画：在x轴缩放
            ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(mRobot, "scaleX", 1.1f, 0.9f);
            scaleXAnimator.setDuration(1000);
            scaleXAnimator.setRepeatCount(1);
            scaleXAnimator.setRepeatMode(ValueAnimator.REVERSE);
            scaleXAnimator.setInterpolator(new LinearInterpolator());

            //缩放动画：在y轴上缩放
            ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(mRobot, "scaleY", 1.1f, 0.9f);
            scaleYAnimator.setDuration(1000);
            scaleYAnimator.setRepeatCount(1);
            scaleYAnimator.setRepeatMode(ValueAnimator.REVERSE);
            scaleYAnimator.setInterpolator(new LinearInterpolator());

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playSequentially(translationXAnimator, alphaAnimator, rotationYAnimator,scaleXAnimator, scaleYAnimator);

            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    animatorSet.start();
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            animatorSet.start();


        }
    }


}

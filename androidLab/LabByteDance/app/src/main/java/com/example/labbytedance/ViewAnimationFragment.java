package com.example.labbytedance;

import static android.view.animation.Animation.INFINITE;
import static android.view.animation.Animation.RESTART;
import static android.view.animation.Animation.REVERSE;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ViewAnimationFragment extends BaseFragment {

    private static final String TAG = "ViewAnimationFragment";

    private static final long ROTATE_DURATION = 1000;
    private static final float ROTATE_START_DEGREE = 0f;
    private static final float ROTATE_END_DEGREE = 360f;
    private static final float ROTATE_PIVOT = 0.5f;

    private static final long SCALE_DURATION = 1000;


    private ImageView mRobot;
    private AnimationSet setAnimation;
    private RotateAnimation mRotateAnimation;
    private  ScaleAnimation sScaleAnimation;
    private AlphaAnimation alphaAnimation;
    private TranslateAnimation translateAnimation;
    private TranslateAnimation translateAnimation2;

    public ViewAnimationFragment() {
        // Required empty public constructor
    }



    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_animation, container, false);

        mRobot = view.findViewById(R.id.iv_robot);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initAnimation();
        mRobot.startAnimation(setAnimation);

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    private void initAnimation() {
        setAnimation = new AnimationSet(false);
        setAnimation.setRepeatCount(INFINITE);
        setAnimation.setRepeatMode(setAnimation.RESTART);

        mRotateAnimation = new RotateAnimation(
                ROTATE_START_DEGREE, ROTATE_END_DEGREE,
                Animation.RELATIVE_TO_SELF, ROTATE_PIVOT,
                Animation.RELATIVE_TO_SELF, ROTATE_PIVOT
        );

        mRotateAnimation.setStartOffset(2000);
        mRotateAnimation.setDuration(ROTATE_DURATION);
        //mRotateAnimation.setRepeatCount(1);
        //mRotateAnimation.setRepeatMode(REVERSE);
        //mRotateAnimation.setFillAfter(true);

        sScaleAnimation = new ScaleAnimation(0, 1, 0, 1,
                Animation.ABSOLUTE, 0.5f,
                Animation.ABSOLUTE, 0.5f);
        sScaleAnimation.setDuration(SCALE_DURATION);
        sScaleAnimation.setStartOffset(0);
        //sScaleAnimation.setRepeatCount(2);
        //sScaleAnimation.setRepeatMode(REVERSE);
        sScaleAnimation.setFillAfter(true);


        translateAnimation = new TranslateAnimation(
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 400,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0);
        translateAnimation.setDuration(1000);
        translateAnimation.setStartOffset(3000);
        //alphaAnimation.setFillAfter(true);
        translateAnimation2 = new TranslateAnimation(
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, -400,
                Animation.ABSOLUTE, 0,
                Animation.ABSOLUTE, 0);
        translateAnimation2.setDuration(1000);
        translateAnimation2.setStartOffset(4000);
        //alphaAnimation.setFillAfter(true);


        setAnimation.addAnimation(mRotateAnimation);
        setAnimation.addAnimation(sScaleAnimation);
        setAnimation.addAnimation(translateAnimation);
        setAnimation.addAnimation(translateAnimation2);

        setAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //mRobot.setAnimation(setAnimation);
                mRobot.startAnimation(setAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mRotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.i(getLogTag(), "onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i(getLogTag(), "onAnimationEnd");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i(getLogTag(), "onAnimationRepeat");
            }
        });

    }

}
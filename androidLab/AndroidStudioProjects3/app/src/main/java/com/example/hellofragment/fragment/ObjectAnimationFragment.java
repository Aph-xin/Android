package com.example.hellofragment.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.example.hellofragment.R;

public class ObjectAnimationFragment extends BaseFragment {

    private static final String TAG = "StaticFragment";

    // Parameter for rotations
    private static final long ROTATE_DURATION = 5000;
    private static final float ROTATE_START_DEGREE = 0f;
    private static final float ROTATE_END_DEGREE = 360f;
    private static final float ROTATE_PIVOT = 0.5f;

    // Parameter for alpha
    private static final long ANIMATION_DURATION = 3000;
    private static final float ALPHA_START = 0f;
    private static final float ALPHA_END = 1f;

    private ImageView mRobot;

    private AnimatorSet mAnimatorSet;
    private AnimatorSet mAnimatorSetScale;
    private ObjectAnimator mRotateAnimator;
    private ObjectAnimator mTranslateAnimator;
    private ObjectAnimator mAlphaAnimator;
    private ObjectAnimator mScaleAnimator;

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
        // ObjectAnimation
        super.onResume();
        if (null != mRobot) {
            mAnimatorSet = new AnimatorSet();
            //mAnimatorSet.setDuration(500);

            mRotateAnimator = ObjectAnimator.ofFloat(mRobot, "rotation",
                    ROTATE_START_DEGREE, ROTATE_END_DEGREE, // 0，360
                    Animation.RELATIVE_TO_SELF, ROTATE_PIVOT,
                    Animation.RELATIVE_TO_SELF, ROTATE_PIVOT);
            mRotateAnimator.setDuration(ROTATE_DURATION);
            //mRotateAnimator.setRepeatCount(ValueAnimator.INFINITE);

            mAlphaAnimator = ObjectAnimator.ofFloat(mRobot, "alpha",
                    ALPHA_START, ALPHA_END, ALPHA_START);
            mAlphaAnimator.setDuration(ANIMATION_DURATION);
            //mAlphaAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mAlphaAnimator.setRepeatMode(ValueAnimator.RESTART);

            float [] f = new float[]{100,-100,0};
            mTranslateAnimator = ObjectAnimator.ofFloat(mRobot, "translationX", f);
            mTranslateAnimator.setDuration(2000);
            //mTranslateAnimator.setRepeatCount(ValueAnimator.INFINITE);

            mAnimatorSetScale = new AnimatorSet();
            float [] f2  = new float[]{1,0.5f};
            ObjectAnimator mScaleAnimatorX = ObjectAnimator.ofFloat(mRobot, "scaleX", f2);
            mScaleAnimatorX.setDuration(1000);
            ObjectAnimator mScaleAnimatorY = ObjectAnimator.ofFloat(mRobot, "scaleY", f2);
            mScaleAnimatorY.setDuration(1000);
            mAnimatorSetScale.playTogether(mScaleAnimatorX, mScaleAnimatorY);
            //mScaleAnimatorX.setRepeatMode(ValueAnimator.REVERSE);
            //mScaleAnimatorX.setRepeatCount(ValueAnimator.INFINITE);
            //mScaleAnimatorY.setRepeatMode(ValueAnimator.REVERSE);
            //mScaleAnimatorY.setRepeatCount(ValueAnimator.INFINITE);

            mAnimatorSet.playSequentially(mRotateAnimator, mTranslateAnimator,  mAnimatorSetScale, mAlphaAnimator);
            // Property, StartValue, RepectCount, RepectMode, TypeEvaluator, Interpolator
            mAnimatorSet.start();
        }
    }

    @Override
    // 防止内存外漏
    public void onPause() {
        super.onPause();
        if (null != mAnimatorSet && mAnimatorSet.isRunning()) {
            mAnimatorSet.cancel();
        }
    }

}

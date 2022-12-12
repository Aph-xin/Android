package com.example.hellofragment;

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
import com.example.hellofragment.R;
import com.example.hellofragment.fragment.BaseFragment;

import static android.view.animation.Animation.INFINITE;
import static android.view.animation.Animation.REVERSE;

public class ViewAnimationFragment extends BaseFragment {

    private static final String TAG = "ViewAnimationFragment";
    private static final String PARAM_COLOR = "param_color";

    // Parameter for rotations
    private static final long ROTATE_DURATION = 2000;
    private static final float ROTATE_START_DEGREE = 0f;
    private static final float ROTATE_END_DEGREE = 360f;
    private static final float ROTATE_PIVOT = 0.5f;

    private int mColor = Color.WHITE;
    private ImageView mRobot;

    // Animation set
    private AnimationSet mAnimationSet;
    private RotateAnimation mRotateAnimation;
    private TranslateAnimation mTranslateAnimation;
    private AlphaAnimation mAlphaAnimation;
    private ScaleAnimation mScaleAnimation;

    public ViewAnimationFragment() {
        // Required empty public constructor
    }

    public static ViewAnimationFragment newInstance(int color) {
        ViewAnimationFragment fragment = new ViewAnimationFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM_COLOR, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (null != args) {
            int givenColor = args.getInt(PARAM_COLOR);
            mColor = (0 != givenColor) ? givenColor : mColor;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_animation, container, false);
        view.setBackgroundColor(mColor);// 传递颜色参数
        mRobot = view.findViewById(R.id.iv_robot);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initAnimation();
        if (null != mRobot) {
            //.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            mRobot.startAnimation(mAnimationSet);
        }
    }

    @Override
    public void onPause() {
        //super.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        super.onPause();
        if(null != mAnimationSet && mAnimationSet.hasStarted()) {
            mAnimationSet.cancel();
        }
    }

    private void initAnimation() {
        //Animationset: Rotate, Translate, Alpha, Scale
        mAnimationSet = new AnimationSet(true);
        mAnimationSet.setRepeatMode(Animation.RESTART);

        mRotateAnimation = new RotateAnimation(
                ROTATE_START_DEGREE, ROTATE_END_DEGREE, // 0，360
                Animation.RELATIVE_TO_SELF, ROTATE_PIVOT,
                Animation.RELATIVE_TO_SELF, ROTATE_PIVOT
        );
        mRotateAnimation.setDuration(ROTATE_DURATION);
        //mRotateAnimation.setRepeatCount(INFINITE);
        mRotateAnimation.setStartOffset(0);
        // mRotateAnimation.setRepeatMode(REVERSE);

        mTranslateAnimation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_PARENT, 0,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.5f,
                TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 0);
        mTranslateAnimation.setDuration(5000);
        //mTranslateAnimation.setRepeatCount(INFINITE);
        mTranslateAnimation.setStartOffset(2000);

        mScaleAnimation = new ScaleAnimation(
                1,0.5f,1,0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        mScaleAnimation.setDuration(1000);
        //mScaleAnimation.setRepeatCount(INFINITE);
        mScaleAnimation.setStartOffset(7000);

        mAlphaAnimation = new AlphaAnimation(1,0);
        mAlphaAnimation.setDuration(3000);
        //mAlphaAnimation.setRepeatCount(INFINITE);
        mAlphaAnimation.setStartOffset(8000);

        mAnimationSet.addAnimation(mRotateAnimation);
        mAnimationSet.addAnimation(mTranslateAnimation);
        mAnimationSet.addAnimation(mAlphaAnimation);
        mAnimationSet.addAnimation(mScaleAnimation);

        // AnimationSet.playSequentially(mRotateAnimation, mTranslateAnimation, mAlphaAnimation, mScaleAnimation);

        // 监听，配合xml方式设置
        mAnimationSet.setAnimationListener(new Animation.AnimationListener() {
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
package com.example.labbytedance;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.airbnb.lottie.LottieAnimationView;

//import com.example.hellofragment.R;

public class LottieAnimationFragment extends BaseFragment {

    public static final String TAG = "LottieAnimationFragment";
    private View lottie;

    public LottieAnimationFragment() {

    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_lottie_animation, container, false);
        //lottie = view.findViewById(R.id.lottie_layer_name);
        return view;
    }

}

package com.example.hellofragment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hellofragment.R;

public class LottieAnimationFragment extends BaseFragment {

    public static final String TAG = "LottieAnimationFragment";

    public LottieAnimationFragment() {

    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    // LottieAnimation调用
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lottie_animation, container, false);
    }
}

package com.example.hellofragment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hellofragment.R;

public class HelloFragment extends BaseFragment {

    private static final String TAG = "HelloFragment";

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hello, container, false);
    }
    // LayoutInflater寻找xml布局下具体控件，viewGroup container表示容器，view放在里面
    // Bundle保存当前状态，在活动的生命周期中，只要离开可见阶段，活动很快被进程终止，这种机制能保存当前状态
}

package com.example.hellofragment.fragment;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.hellofragment.ViewAnimationFragment;

import org.jetbrains.annotations.NotNull;

public class HelloFragmentViewPagerAdapter extends FragmentStateAdapter {

    public static final int FRAGMENTS_COUNT = 3;
    public static final int FRAGMENT_VIEW_ANIMATION = 0;
    public static final int FRAGMENT_OBJECT_ANIMATION = 1;
    public static final int FRAGMENT_LOTTIE_ANIMATION = 2;

    public HelloFragmentViewPagerAdapter(@NonNull @NotNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case FRAGMENT_VIEW_ANIMATION:
                return ViewAnimationFragment.newInstance(Color.BLUE);
            case FRAGMENT_OBJECT_ANIMATION:
                return new ObjectAnimationFragment();
            case FRAGMENT_LOTTIE_ANIMATION:
                return new LottieAnimationFragment();
            default:
                return new Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return FRAGMENTS_COUNT;
    }
}

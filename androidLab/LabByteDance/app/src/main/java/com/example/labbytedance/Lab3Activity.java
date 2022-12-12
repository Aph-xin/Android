package com.example.labbytedance;

import static androidx.viewpager2.widget.ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;


public class Lab3Activity extends AppCompatActivity {
    //@Override
    //public void onMultiTabsViewCreated(int tabsCount) {
    //    TextView tv = findViewById(R.id.tv_tabs_count);
    //    tv.setText(tabsCount + " tabs created");
    //    tv.setVisibility(View.VISIBLE);
    //}

    //@Override
    //public void onMultiTabsViewDetach() {
    //    mReplaceButton.setVisibility(View.VISIBLE);
    //    TextView tv = findViewById(R.id.tv_tabs_count);
    //    tv.setVisibility(View.GONE);
    //}
    private static final String TAG = "MainFragment";
    private static final String TITLE_VIEW_ANIMATION = "视图动画";
    private static final String TITLE_OBJECT_ANIMATION = "属性动画";
    private static final String TITLE_LOTTIE_ANIMATION = "Lottie动画";

    private final String[] tabTitles = new String[FragmentViewAdapter.FRAGMENTS_COUNT];

    private ViewPager2 mViewPager;
    private TabLayout mTabLayout;
    private FragmentAdapter mAdapter;
    private MainFragment.MainFragmentListener mListener = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_lab3);

        mViewPager = findViewById(R.id.view_pager_main);
        mTabLayout = findViewById(R.id.tab_layout);
        mAdapter = new FragmentAdapter(this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT_DEFAULT);
        mViewPager.setPageTransformer(new ZoomOutPageTransformer());
        initTabLayout();

    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }

    private void initTabLayout() {
        initTabTitles();
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                mTabLayout,
                mViewPager,
                true,
                false,
                (tab, position) -> tab.setText(tabTitles[position]));
        tabLayoutMediator.attach();
    }
    private void initTabTitles() {
        tabTitles[FragmentViewAdapter.FRAGMENT_VIEW_ANIMATION] = TITLE_VIEW_ANIMATION;
        tabTitles[FragmentViewAdapter.FRAGMENT_OBJECT_ANIMATION] = TITLE_OBJECT_ANIMATION;
        tabTitles[FragmentViewAdapter.FRAGMENT_LOTTIE_ANIMATION] = TITLE_LOTTIE_ANIMATION;
    }

    private class FragmentAdapter extends FragmentStateAdapter {
        public static final int FRAGMENTS_COUNT = 3;
        public static final int FRAGMENT_VIEW_ANIMATION = 0;
        public static final int FRAGMENT_OBJECT_ANIMATION = 1;
        public static final int FRAGMENT_LOTTIE_ANIMATION = 2;

        public FragmentAdapter(@NonNull FragmentActivity fragment) {
            super(fragment);
        }

        @NonNull
        @NotNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case FRAGMENT_VIEW_ANIMATION:
                    return new ViewAnimationFragment();
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


}
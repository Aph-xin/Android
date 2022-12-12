package com.example.hellofragment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.example.hellofragment.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import static androidx.viewpager2.widget.ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT;

public class MainFragment extends BaseFragment {
    // title define
    private static final String TAG = "MainFragment";
    private static final String TITLE_VIEW_ANIMATION = "视图动画";
    private static final String TITLE_OBJECT_ANIMATION = "属性动画";
    private static final String TITLE_LOTTIE_ANIMATION = "Lottie动画";

    private final String[] tabTitles = new String[HelloFragmentViewPagerAdapter.FRAGMENTS_COUNT];

    private ViewPager2 mViewPager;
    private TabLayout mTabLayout;

    private HelloFragmentViewPagerAdapter mAdapter;
    private MainFragmentListener mListener = null;

    public interface MainFragmentListener {
        // fragment中定义接口以及接口类型tabcount
        void onMultiTabsViewCreated(int tabsCount);
        void onMultiTabsViewDetach();
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    @Override
    // onAttach创建实例，mListener获取接口
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (MainFragmentListener) context;
    }

    @Override
    // 加载视图
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initViews(view);
        initViewPager();
        initTabLayout();
        if (null != mListener) { //如果null不等于监听，按需调用改接口方法进行通信
            mListener.onMultiTabsViewCreated(mAdapter.getItemCount());
        }
        return view;
    }

    @Override
    // 销毁
    public void onDetach() {
        super.onDetach();
        if (null != mListener) {
            mListener.onMultiTabsViewDetach();
        }
    }

    private void initViews(View view) {
        mViewPager = view.findViewById(R.id.view_pager_main);
        mTabLayout = view.findViewById(R.id.tab_layout);
    }

    private void initViewPager() {
        mAdapter = new HelloFragmentViewPagerAdapter(this);
        mViewPager.setAdapter(new HelloFragmentViewPagerAdapter(this));
        mViewPager.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT_DEFAULT);
    }

    // viewpager and tablayout 连接
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
        tabTitles[HelloFragmentViewPagerAdapter.FRAGMENT_VIEW_ANIMATION] = TITLE_VIEW_ANIMATION;
        tabTitles[HelloFragmentViewPagerAdapter.FRAGMENT_OBJECT_ANIMATION] = TITLE_OBJECT_ANIMATION;
        tabTitles[HelloFragmentViewPagerAdapter.FRAGMENT_LOTTIE_ANIMATION] = TITLE_LOTTIE_ANIMATION;
    }
}

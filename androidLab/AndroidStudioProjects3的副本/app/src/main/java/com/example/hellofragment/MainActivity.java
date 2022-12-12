package com.example.hellofragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.hellofragment.fragment.MainFragment;
import com.example.hellofragment.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity
        implements MainFragment.MainFragmentListener {

    private static final String TAG = "MainActivity";

    private Button mReplaceButton;

    @Override
    // Activity实现接口方法，执行相关处理，显示tabs建立数量
    public void onMultiTabsViewCreated(int tabsCount) {
        TextView tv = findViewById(R.id.tv_tabs_count);
        tv.setText(tabsCount + " tabs created");
        tv.setVisibility(View.VISIBLE);
    }

    @Override
    public void onMultiTabsViewDetach() {
        mReplaceButton.setVisibility(View.VISIBLE);
        TextView tv = findViewById(R.id.tv_tabs_count);
        tv.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate --- Start ---");
        super.onCreate(savedInstanceState);
        // 调用其父类Activity的onCreate方法来实现对界面的图画绘制工作
        Log.i(TAG, "onCreate --- End   ---");
        setContentView(R.layout.activity_main);
        // 显示layout.activity_main画面

        // 在fragmentManager中加入fragment，Add replace button
        mReplaceButton = findViewById(R.id.btn_replace);
        mReplaceButton.setOnClickListener(v -> {
            FragmentManager fm = getSupportFragmentManager();
            // addToBackStack添加返回栈
            fm.beginTransaction()
                    .add(R.id.fragment_container, new MainFragment())
                    .addToBackStack(null)
                    .commit();

            mReplaceButton.setVisibility(View.GONE);
        });
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, "onRestart --- Start ---");
        super.onRestart();
        Log.i(TAG, "onRestart --- End   ---");
    }

    @Override
    protected void onStart() { // 可见
        Log.i(TAG, "onStart --- Start ---");
        super.onStart();
        Log.i(TAG, "onStart --- End   ---");
    }

    @Override
    protected void onResume() { // 可交互
        Log.i(TAG, "onResume --- Start ---");
        super.onResume();
        Log.i(TAG, "onResume --- End   ---");
    }

    @Override
    protected void onPause() { // 不可交互
        Log.i(TAG, "onPause --- Start ---");
        super.onPause();
        Log.i(TAG, "onPause --- End   ---");
    }

    @Override
    protected void onStop() { // 不可见
        Log.i(TAG, "onStop --- Start ---");
        super.onStop();
        Log.i(TAG, "onStop --- End   ---");
    }

    @Override
    protected void onDestroy() { // 销毁状态处理
        Log.i(TAG, "onDestroy --- Start ---");
        super.onDestroy();
        Log.i(TAG, "onDestroy --- End   ---");
    }
}
package com.example.timerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    /*
    完成控件注入
     */
    @BindView(R.id.title_txt)
    TextView mTitle;
    @BindView(R.id.time_txt)
    TextView mTimer;
    @BindView(R.id.btn)
    Button mBtn;
    @BindView(R.id.end_txt)
    TextView mEndTxt;

    private int times = 0;//控制时间自增变量
    private boolean start = false;//控制点击开始和点击结束
    private String time;

    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mTitle.setText("Timing...");
            mBtn.setText("STOP");
            int min = times / 60;//分钟计算
            int sec = times % 60;//秒钟计算
            time = (min < 10 ? "0" + min : "" + min) + ":" + (sec < 10 ? "0" + sec : "" + sec);
            mTimer.setText(time);
            times++;
            handler.postDelayed(runnable, 1000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (start == false) {
                    start = true;
                    new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            handler.postDelayed(runnable, 1000);
                        }
                    }.start();
                } else {

                    start = false;
                    times = 0;
                    mBtn.setText("START");
                    mTitle.setText("MyTimer");
                    mEndTxt.setVisibility(View.VISIBLE);
                    mEndTxt.setText("Current time：" + time);
                    handler.removeCallbacks(runnable);


                }
            }
        });
    }
}
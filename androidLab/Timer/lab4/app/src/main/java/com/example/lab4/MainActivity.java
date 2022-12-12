package com.example.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static TextView textView1;
    Button bt_clear,bt_stop,bt_start;
    MyService mService;
    TimerCircle mTimerCircle;
    boolean mBound;
    static final int STOP = 0, START = 1;
    int STATE = 1;
    //static Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimerCircle=findViewById(R.id.timer);
        textView1=findViewById(R.id.textview);
        bt_clear=findViewById(R.id.bt_clear);
        bt_stop=findViewById(R.id.bt_stop);
        bt_start=findViewById(R.id.bt_start);

        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { finish();}
        });

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimerCircle.setDuration(60000, 60000);
                mService = new MyService() {
                    public void run() {
                        int i = 60;
                        super.run();
                        try {
                            while (true) {
                                while (i >= 0){
                                    while (c_stop) {
                                        onPause();
                                    }
                                    i--;
                                    String s=""+i;
                                    Message message=handler.obtainMessage();
                                    message.what = i;
                                    message.obj = s;
                                    handler.sendMessage(message);
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    }
                };
                mService.start();
            }
        });

        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(STATE == 1)
                {
                    mService.countstop();
                    mTimerCircle.timePause();
                    STATE = 0;
                }
                else {
                    mService.resumecount();
                    mTimerCircle.timeResume();
                    STATE = 1;

                }
            }
        });
    }

    @SuppressLint("Leak")
    final Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String s = "One Minute Time Over";
            if (msg.what == 0) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

        }
    };


}


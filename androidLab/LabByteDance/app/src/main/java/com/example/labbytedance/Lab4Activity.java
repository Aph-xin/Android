package com.example.labbytedance;

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


public class Lab4Activity extends AppCompatActivity {
    private Button btn, btn_stop, btn_back;
    private TextView tv;
    private myThread thread;
    private TimeCircle tc;
    public static final int STATE_STOP = 0, STATE_WORK = 1;
    public int STATE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab4);

        btn = findViewById(R.id.btn);
        btn_stop = findViewById(R.id.btn_time_stopper);
        btn_back = findViewById(R.id.lab4_back);
        //tv = findViewById(R.id.tv);
        tc = findViewById(R.id.timer);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tc.setDuration(60000,60000);
                thread = new myThread(){
                    @Override
                    public void run() {
                        int i = 60;
                        super.run();
                        try {
                            while (true) {
                                while (i >= 0){
                                    while (pause) {
                                        onPause();
                                    }
                                    i--;
                                    String s=""+i;
                                    //发送给处理器去处理,用处理器handler,创建一个空消息对象
                                    Message message=handler.obtainMessage();
                                    //把值赋给message
                                    message.what = i;
                                    message.obj = s;
                                    //把消息发送给处理器F
                                    handler.sendMessage(message);
                                    try {
                                        //延时是一秒
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
                thread.start();
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (STATE == 1) {
                    thread.pauseThread();
                    tc.timePause();
                    STATE = 0;
                }
                else{
                    thread.resumeThread();
                    tc.timeResume();
                    STATE = 1;
                }


            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {finish();}
        });
    }


    //创建一个处理器对象
    @SuppressLint("HandlerLeak")
    final Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String s = "1 minute countdown finished";
            if (msg.what == 0) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

        }
    };
}

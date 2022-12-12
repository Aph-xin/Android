package com.example.labbytedance;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import java.sql.Time;

public class TimeCircle extends View {
    private Paint mPaint;     //圆环画笔
    private Paint nPaint;     //圆画笔
    private int circle_color; //圆的颜色
    private int ring_color;   //圆环颜色
    private int ring_width;   //圆环大小(画笔的宽度)
    //指定控件宽度和长度
    private int width;
    private int height;
    private int text_color;     //字体颜色
    private int text_size;      //字体大小

    private int path_color;     //路径颜色
    private int in_radius;      //内圆的半径
    private int center;         //圆心

    private int current_value;  //动画的当前值
    private float  angle_value;//圆弧角度 进行到x ms时圆弧弧度为 x * 360.f /maxTime x = currentValue
    private ValueAnimator animator;//通过valueAnimator获得currentValue
    private float duration; //valueAnimator的持续时间，60000ms
    private int maxTime=60000; //最大倒计时时间60000ms

    private int currentTime;//倒计时剩余时间，显示在圆中


    public TimeCircle(Context context){
        this(context, null);       //TimerCircle circle = new TimerCircle()时调用该构造方法
    }

    public TimeCircle(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeCircle(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TimerCircle);
        circle_color = typedArray.getColor(R.styleable.TimerCircle_circleColor, Color.BLUE);
        ring_color = typedArray.getInteger(R.styleable.TimerCircle_ringColor, 0);
        ring_width = (int) typedArray.getDimension(R.styleable.TimerCircle_width, 40);
        text_color = typedArray.getColor(R.styleable.TimerCircle_path, Color.WHITE);
        text_size = (int) typedArray.getDimension(R.styleable.TimerCircle_textSize,20);
        path_color = typedArray.getColor(R.styleable.TimerCircle_path, 0);
        typedArray.recycle();
        InitPaint();
    }

    private void InitPaint() {
        mPaint = new Paint();
        mPaint.setColor(ring_color);
        mPaint.setAntiAlias(true);
        nPaint = new Paint();
        nPaint.setAntiAlias(true);
        nPaint.setColor(circle_color);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthPixels = this.getResources().getDisplayMetrics().widthPixels;//获取屏幕宽度
        int heightPixels = this.getResources().getDisplayMetrics().heightPixels;//获取屏幕高度
        //测量，根据指定的宽高和屏幕的宽高确定圆的半径，四个中最小的即为半径
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int minWidth = Math.min(width, widthPixels);
        int minHeight = Math.min(height, heightPixels);
        setMeasuredDimension(Math.min(minHeight, minWidth), Math.min(minHeight, minWidth));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        in_radius = (this.getWidth()-2*ring_width)/2; //内圆的半径
        center = this.getWidth()-in_radius-ring_width;//圆的圆心
        drawInner(canvas, in_radius,center);  //内圆和圆环;
        drawText(canvas,center); //绘制倒计时
    }


    private void drawInner(Canvas canvas, int radius, int center) {
        canvas.drawCircle(center, center, radius, nPaint); //画出内圆
        //画圆环，画笔的宽度就是圆环的宽度
        mPaint.setStyle(Paint.Style.STROKE);//设置空心圆
        mPaint.setStrokeWidth(ring_width);  //画笔宽度即圆环宽度
        mPaint.setColor(ring_color);        //圆环的颜色
        canvas.drawCircle(center, center, radius, mPaint);

        RectF rectF = new RectF(center-radius,center-radius,center+radius,center+radius);   //内圆的外接矩形

        angle_value = (maxTime- current_value) * 360.0f / maxTime * 1.0f; //计算弧度
        mPaint.setShadowLayer(1, 1, 1, Color.GRAY); //阴影大小和颜色
        mPaint.setStrokeCap(Paint.Cap.ROUND); //线帽样式
        mPaint.setColor(path_color);//圆弧的颜色
        canvas.drawArc(rectF, -90, angle_value , false, mPaint);//绘制圆弧
    }

    public void setDuration(int duration, final int maxTime) {
        this.maxTime=maxTime;
        this.duration=duration;
        if (animator != null) {
            animator.cancel();
        } else {
            animator = ValueAnimator.ofInt(0, maxTime);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    //38.获取此时的进度
                    current_value = (int) animation.getAnimatedValue();
                    if(current_value == maxTime){
                        animator.pause();
                    }
                    invalidate();   //invalidate 系统自动调用View的onDraw()方法
                }
            });
            animator.setInterpolator(new LinearInterpolator());//线性插值器，匀速变化
        }
        animator.setDuration(duration);
        animator.start();

    }

    public void timePause() {
        animator.pause();
    }

    public void timeResume() {
        animator.resume();
    }

    private void drawText(Canvas canvas, int center) {

        currentTime = (maxTime - current_value) / 1000;  //计算当前的剩余的时间(s)
        String Text=null; //倒计时字符串

        if(currentTime < 10){
            Text = "00:0" + currentTime;
        }else if(currentTime >= 10 && currentTime < 60){
            Text = "00:" + currentTime;
        }else if(currentTime >= 60 && currentTime < 600){
            int min = currentTime / 60;
            int sen = currentTime % 60;
            if (sen < 10) {
                Text = "0" + min + ":0" + sen;
            }else{
                Text="0" + min + ":" + sen;
            }

        }else{
            int min = currentTime / 60;
            int sen = currentTime % 60;
            if (sen < 10) {
                Text = min + ":0" + sen;
            }else{
                Text = min+ ":" + sen;
            }
        }
        mPaint.setTextAlign(Paint.Align.CENTER);//设置文字居中
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(text_size);
        mPaint.setStyle(Paint.Style.FILL);
        //mPaint.setStrokeWidth(0);//清除画笔宽度
        mPaint.clearShadowLayer();//清除阴影
        // 文字边框
        //Rect bounds = new Rect();
        // 获得绘制文字的边界矩形
       // mPaint.getTextBounds(Text, 0, Text.length(), bounds);
        // 获取绘制Text时的四条线
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        // 计算文字的基线
        int baseline = center - (fontMetrics.bottom + fontMetrics.top) / 2;
        // 绘制表示进度的文字
        canvas.drawText(Text, center, baseline, mPaint);

    }
}

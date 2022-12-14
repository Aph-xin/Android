package com.example.demoapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by huangxin.2020 on 2022/11/3
 * @author huangxin.2020@bytedance.com
 */
class ProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): View(context, attrs, defStyleAttr) {

    var progress = 0f   // 0..1f
        set(value) {
            field = when {
                value < 0 -> 0f
                value > 1f -> 1f
                else -> value
            }
            invalidate()
        }

    private var lineColor = Color.BLACK
    private var lineWidth = 2.dpFloat

    private val paint = Paint()

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ProgressView, defStyleAttr, 0)
        lineColor = ta.getColor(R.styleable.ProgressView_line_color, 0)
        lineWidth = ta.getDimension(R.styleable.ProgressView_line_width, 0f)
        ta.recycle()
        paint.color = lineColor
        paint.strokeWidth = lineWidth
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas) // 自己绘制线条
        canvas.drawLine(0f, 0f, progress * width, 0f, paint)
    }

}
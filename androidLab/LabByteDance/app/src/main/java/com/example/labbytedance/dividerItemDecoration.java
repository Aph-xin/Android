package com.example.labbytedance;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class dividerItemDecoration extends RecyclerView.ItemDecoration {
    //分割线
    private int dividerHeight;

    //定制分割线颜色
    private Paint dividerPaint;

    public dividerItemDecoration(Context context) {

        dividerPaint = new Paint();
        dividerHeight = context.getResources().getDimensionPixelSize(androidx.cardview.R.dimen.cardview_compat_inset_shadow);
        dividerPaint.setColor(context.getResources().getColor(R.color.black));
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        //画item的布局
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float startx = parent.getPaddingLeft();
            float starty = view.getBottom();
            float stopx = startx + view.getWidth();
            float stopy = view.getBottom();
            c.drawLine(startx, starty, stopx, stopy, dividerPaint);
        }
    }
}

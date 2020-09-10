package com.example.jetpackdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class SinWaveView extends View {
    private PaintFlagsDrawFilter mDrawFilter;
    private Paint mWavePaint;
    private float mOffset1 = 0.0f;
    private float mOffset2 = 0.0f;
    private float mSpeed1 = 0.05f;
    private float mSpeed2 = 0.07f;

    public SinWaveView(Context context) {
        super(context);
        init();
    }

    public SinWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SinWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 初始绘制波纹的画笔
        mWavePaint = new Paint();
        // 去除画笔锯齿
        mWavePaint.setAntiAlias(true);
        // 设置风格为实线
        mWavePaint.setStyle(Paint.Style.FILL);
        // 设置画笔颜色
        mWavePaint.setColor(0x880000aa);
        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 从canvas层面去除绘制时锯齿
        canvas.setDrawFilter(mDrawFilter);
        for (int i = 0; i < getMeasuredWidth(); i++) {

            // y = A * sin( wx + b) + h ; A： 浪高； w：周期；b：初相；
            float endY = (float) (20 * Math.sin(2 * Math.PI / getMeasuredWidth() * i + mOffset1) + 300);
            //画第一条波浪
            canvas.drawLine(i, (float) getMeasuredHeight()/2, i, endY, mWavePaint);
            //画第二条波浪
            float endY2 = (float) (20 * Math.sin(2 * Math.PI / getMeasuredWidth() * i + mOffset2) + 300);
            canvas.drawLine(i, (float) getMeasuredHeight()/2, i, endY2, mWavePaint);
        }

        if (mOffset1 > Float.MAX_VALUE - 1) {//防止数值超过浮点型的最大值
            mOffset1 = 0;
        }
        mOffset1 += mSpeed1;

        if (mOffset2 > Float.MAX_VALUE - 1) {//防止数值超过浮点型的最大值
            mOffset2 = 0;
        }
        mOffset2 += mSpeed2;
        //刷新
        postInvalidate();
    }
}
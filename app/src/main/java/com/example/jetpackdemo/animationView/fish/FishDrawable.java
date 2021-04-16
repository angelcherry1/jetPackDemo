package com.example.jetpackdemo.animationView.fish;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FishDrawable extends Drawable {


    public FishDrawable() {
        init();
    }

    private Path mPath;
    private Paint mPaint;
    private final static int OTHER_ALPHA = 110;
    private final static int BODY_ALPHA = 160;
    private final static int HEADER_RADIUS = 50;
    private final static float body_length = 3.2f * HEADER_RADIUS; //鱼身体长
    //寻找鱼鳍起点的线长
    private final static float find_fish_length = 0.9f * HEADER_RADIUS;

    //鱼鳍长度
    private final static float fins_length = 1.3f * HEADER_RADIUS;


    private PointF middlePoint;

    private float fishMainAngel = 0;

    private void init() {

        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);//方抖动
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setARGB(OTHER_ALPHA, 244, 92, 71);


        middlePoint = new PointF(4.18f * HEADER_RADIUS, 4.18f * HEADER_RADIUS);
    }


    @Override
    public void draw(@NonNull Canvas canvas) {
        float fishAngel = fishMainAngel;
        //头
        PointF headPoint = calulatPoint(middlePoint, body_length / 2, fishAngel);
        canvas.drawCircle(headPoint.x, headPoint.y, HEADER_RADIUS, mPaint);

        //右鱼鳍
        PointF rightFinsPoint = calulatPoint(headPoint, find_fish_length, fishAngel - 110);
        makeFins(canvas, rightFinsPoint, fishAngel);


    }

    private void makeFins(Canvas canvas, PointF startFinsPoint, float fishAngel) {
        float controlAngel = 115;
        //二阶贝塞尔曲线控制点坐标
        PointF controlPoint = calulatPoint(startFinsPoint, 1.8f * fins_length, fishAngel - controlAngel);

        PointF endPoint = calulatPoint(startFinsPoint, fins_length, fishAngel - 180);

        mPath.reset();
        mPath.moveTo(startFinsPoint.x, startFinsPoint.y);
        mPath.quadTo(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y);
        canvas.drawPath(mPath, mPaint);
    }

    /**
     * @param startPoint 起始点坐标
     * @param length     两点之间的长度
     * @param angle      两点连线的 的角度和x坐标夹角
     * @return 需要点的坐标
     */
    public static PointF calulatPoint(PointF startPoint, float length, float angle) {
        float deltax = (float) (Math.cos(Math.toRadians(angle)) * length);
        float deltaY = (float) (Math.cos(Math.toRadians(angle - 180)) * length);
        return new PointF(startPoint.x + deltax, startPoint.y + deltaY);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) (8.38f * HEADER_RADIUS);
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) (8.38f * HEADER_RADIUS);
    }
}

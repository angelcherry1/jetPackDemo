package com.example.jetpackdemo.animationView.snow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnowView extends View {

    private Paint myPaint;
    private int snowNum=100;
    private List<SnowBean> snowBeans;
    private boolean isPlaying;
    public SnowView(Context context) {
        super(context);
        init();
    }

    public SnowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SnowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        myPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        snowBeans=new ArrayList<>();
        isPlaying=true;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        snowBeans.clear();
        for (int i=0;i<snowNum;i++){
            snowBeans.add(SnowBean.getBuildSnowBean(w,h));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i=0;i<snowNum;i++){
            snowBeans.get(i).onDraw(canvas,myPaint);
            snowBeans.get(i).step();
        }
        if(isPlaying){
            postInvalidateDelayed(10);
        }

    }


    public void start(){
        invalidate();
        isPlaying=true;
    }

    public void stop(){
        isPlaying=false;
    }

    /**
     * 雪花对象
     */
  static   class SnowBean {
        private int x;
        private int y;
        private int color;
        private int radius;
        private int rotation;
        private int speed;
        private int viewWdith;
        private int viewheight;
        private int stokeWidth;
        private Point startPoint;
        private Point middlePoint;
        private Point endPoint;

        public int getStokeWidth() {
            return stokeWidth;
        }

        public void setStokeWidth(int stokeWidth) {
            this.stokeWidth = stokeWidth;
        }

        public Point getStartPoint() {
            return startPoint;
        }

        public void setStartPoint(Point startPoint) {
            this.startPoint = startPoint;
        }

        public Point getMiddlePoint() {
            return middlePoint;
        }

        public void setMiddlePoint(Point middlePoint) {
            this.middlePoint = middlePoint;
        }

        public Point getEndPoint() {
            return endPoint;
        }

        public void setEndPoint(Point endPoint) {
            this.endPoint = endPoint;
        }

        public int getViewWdith() {
            return viewWdith;
        }

        public void setViewWdith(int viewWdith) {
            this.viewWdith = viewWdith;
        }

        public int getViewheight() {
            return viewheight;
        }

        public void setViewheight(int viewheight) {
            this.viewheight = viewheight;
        }

        public int getX() {
            float t = getY() * 1.0f / (getEndPoint().y - getStartPoint().y);
            return BezierUtil.calutePoint(t, getStartPoint(), getMiddlePoint(), getEndPoint()).x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public int getRadius() {
            return radius;
        }

        public void setRadius(int radius) {
            this.radius = radius;
        }

        public int getRotation() {
            return rotation;
        }

        public void setRotation(int rotation) {
            this.rotation = rotation;
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public static SnowBean getBuildSnowBean(int width, int height) {
            SnowBean snowBean = new SnowBean();
            Random random = new Random();

            snowBean.setY(-random.nextInt(height));
            snowBean.setX(random.nextInt(width));
            snowBean.setColor(Color.WHITE);
            snowBean.setRadius(2+random.nextInt(20));
            snowBean.setRotation(random.nextInt(60));
            snowBean.setSpeed(2 +Math.abs(snowBean.getRadius()-20));
            snowBean.setViewheight(height);
            snowBean.setViewWdith(width);
            snowBean.setStartPoint(new Point(random.nextInt(width), -random.nextInt(height)));
            snowBean.setMiddlePoint(new Point(random.nextInt(width), random.nextInt(height)));
            snowBean.setEndPoint(new Point(random.nextInt(width), height + random.nextInt(height)));
            snowBean.setStokeWidth((int)(snowBean.getRadius()*0.2));
            snowBean.setRotation(random.nextInt(60));
            return snowBean;
        }

        public void step() {
            y += speed;
            if (y > viewheight) {
                y = -50;
            }
            setRotation(getRotation()+1);
        }

        public void onDraw(Canvas canvas, Paint paint) {
            canvas.save();
            paint.setColor(getColor());
//        canvas.drawRect(getX() - getRadius(), getY() - getRadius(), getX() + getRadius(), getY() + getRadius(), paint);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(getStokeWidth());
            canvas.rotate(getRotation(),getX(),getY());
            for(int i=0;i<6;i++){
                int lineStartX=getX();
                int lineEndX=getX()+radius;
                canvas.drawLine(lineStartX,getY(),lineEndX,getY(),paint);


                int line1Startx=(int)(getX()+getRadius()*0.5);
                int lint1Starty=getY();
                double degree60=Math.toRadians(60);
                int lint1Endx=(int)(line1Startx+radius*0.5*Math.cos(degree60));
                int lint1Endy=(int)(lint1Starty-radius*0.5*Math.cos(degree60));
                canvas.drawLine(line1Startx,lint1Starty,lint1Endx,lint1Endy,paint);

                int line2Startx = (int) (getX() + getRadius() * 0.5);
                int lint2Starty = getY();
                int lint2Endx = (int) (line1Startx + radius * 0.5 * Math.cos(degree60));
                int lint2Endy = (int) (lint1Starty + radius * 0.5 * Math.cos(degree60));
                canvas.drawLine(line2Startx, lint2Starty, lint2Endx, lint2Endy, paint);


                canvas.rotate(60,getX(),getY());
            }
            canvas.restore();
        }
    }

}

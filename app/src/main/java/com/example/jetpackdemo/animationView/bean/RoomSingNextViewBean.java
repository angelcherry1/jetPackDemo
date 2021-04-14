package com.example.jetpackdemo.animationView.bean;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.jetpackdemo.R;

public class RoomSingNextViewBean {
    private float x;
    private float y;
    private float speed;
    private int viewHeight;
    private int viewWeight;
    private int position;
    private int title;
    private Bitmap nextBimanp;
    private int  alpha;

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }



    public int getViewHeight() {
        return viewHeight;
    }

    public void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
    }

    public int getViewWeight() {
        return viewWeight;
    }

    public void setViewWeight(int viewWeight) {
        this.viewWeight = viewWeight;
    }

    public Bitmap getNextBimanp() {
        return nextBimanp;
    }

    public void setNextBimanp(Bitmap nextBimanp) {
        this.nextBimanp = nextBimanp;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public static RoomSingNextViewBean getRoomSingNextViewBean(Context context,int weight, int height,int title,int position){
        RoomSingNextViewBean roomSingNextViewBean=new RoomSingNextViewBean();
        roomSingNextViewBean.setNextBimanp(BitmapFactory.decodeResource(context.getResources(), R.mipmap.app_room_sing_next_tab2));
        int w=weight/(title-1);
        roomSingNextViewBean.setX(w*(position-1));
        roomSingNextViewBean.setTitle(title);
        roomSingNextViewBean.setPosition(position);
        roomSingNextViewBean.setY(height/2);
        roomSingNextViewBean.setSpeed(10);
        roomSingNextViewBean.setAlpha(225);
        roomSingNextViewBean.setViewHeight(height);
        roomSingNextViewBean.setViewWeight(weight);
        return roomSingNextViewBean;
    }

    public void step(){
        x+=speed;
        int w=viewWeight/(title-1);
        int speedAlpha = (viewWeight + w) / 225;
        alpha-=speedAlpha;
        if(x>viewWeight){
            x=-w;
            alpha=225;
        }
    }

    public void onDraw(Canvas canvas,Paint paint){
        paint.setAlpha(alpha);
        canvas.drawBitmap(getNextBimanp(),getX(),0,paint);
    }
}

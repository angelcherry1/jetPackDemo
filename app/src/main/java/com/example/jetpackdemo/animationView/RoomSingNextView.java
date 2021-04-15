package com.example.jetpackdemo.animationView;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.jetpackdemo.animationView.bean.RoomSingNextViewBean;

import java.util.ArrayList;
import java.util.List;


public class RoomSingNextView extends View {
    private int nextNum=4;
    private List<RoomSingNextViewBean> roomSingNextViewBeans=new ArrayList<>();

    private Paint paint;

    public RoomSingNextView(Context context) {
        super(context);
        init();
    }

    public RoomSingNextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoomSingNextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint=new Paint();
        setBackgroundColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        for(int i=0;i<nextNum;i++){
            roomSingNextViewBeans.add(RoomSingNextViewBean.getRoomSingNextViewBean(getContext(),w,h,nextNum,i));
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i=0;i<nextNum;i++){
            roomSingNextViewBeans.get(i).onDraw(canvas,paint);
            roomSingNextViewBeans.get(i).step();
        }
        postInvalidateDelayed(10);
    }
}

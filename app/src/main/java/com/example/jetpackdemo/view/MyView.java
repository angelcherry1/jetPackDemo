package com.example.jetpackdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.jetpackdemo.R;

/**
 * @author:lixiaobiao
 * @date:On 2020/8/7
 * @Desriptiong: 23231
 */
public class MyView extends RelativeLayout {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(context);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        initUI(context);

    }

    private void initUI(Context context) {
        View view = View.inflate(context, R.layout.view_my, this);
    }


    int left;
    int top;
    int right;
    int bottom;
    int startX;
    int startY;
    int endX;
    int endY;
    int startleft;
    int startright;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                left = getLeft();
                top = getTop();
                right = getRight();

                startleft = getLeft();
                startright = getRight();

                bottom = getBottom();
                startX = Math.round(event.getRawX());
                startY = Math.round(event.getRawY());
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = Math.round(event.getRawX());
                int moveY = Math.round(event.getRawY());
                int dx = moveX - startX;
                int dy = moveY - startY;
                left = left + dx;
//                top = top +dy;
                right = right + dx;
//                bottom = bottom+dy;
                layout(left, top, right, bottom);
                Log.i("测试", "left:" + left + "top:" + top + "right:" + right + "bottom:" + bottom);
                startX = moveX;
                startY = moveY;
                break;
            case MotionEvent.ACTION_UP:

                endX = Math.round(event.getX());
                endY = Math.round(event.getRawY());
                Log.i("测试", "离开是的数值startX:" +startX +"离开是的数值endX:" +endX  );
                if((startX-endX)>500){
                    myViewListener.isDissmiss(true);
                }else {
                    layout(startleft, top, startright, bottom);
                }
                break;

        }
        return true;
    }

    /**
     * 设置监听
     *
     * @param myViewListener 监听
     */

    MyViewListener myViewListener;

    public void setListener(MyViewListener myViewListener) {
        this.myViewListener = myViewListener;
    }
}

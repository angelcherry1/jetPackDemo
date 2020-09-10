package com.example.jetpackdemo.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.jetpackdemo.R;

public class FloatingWindows extends LinearLayout {
    public FloatingWindows(Context context) {
        super(context);
        initUi();
    }


    public FloatingWindows(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUi();
    }

    public FloatingWindows(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUi();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FloatingWindows(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private ImageView userHeaderIV, editIV;
    private TextView userNameTV, userIdTV;

    private void initUi() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.app_activity_main_floating_windows, this);
        userHeaderIV = inflate.findViewById(R.id.iv_user_header);
        userNameTV = inflate.findViewById(R.id.tv_user_name);
        userIdTV = inflate.findViewById(R.id.tv_user_id);
        editIV = inflate.findViewById(R.id.iv_edit);

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
                top = top + dy;
                right = right + dx;
                bottom = bottom + dy;
                layout(left, top, right, bottom);
                Log.i("测试", "left:" + left + "top:" + top + "right:" + right + "bottom:" + bottom);
                startX = moveX;
                startY = moveY;
                break;
            case MotionEvent.ACTION_UP:

//                endX = Math.round(event.getX());
//                endY = Math.round(event.getRawY());
//                Log.i("测试", "离开是的数值startX:" + startX + "离开是的数值endX:" + endX);
                // 这里宽度必须确定宽高

                break;

        }
        return true;
    }
}

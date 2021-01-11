package com.example.jetpackdemo.pagerMore.viewPager2AndViewpager2.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

public class MyViewPager2 extends FrameLayout {
    public MyViewPager2(@NonNull Context context) {
        super(context);
        initUI(context);
    }

    public MyViewPager2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    public MyViewPager2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(context);
    }

    public MyViewPager2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initUI(context);
    }


    private ViewPager2 mViewPager2;
    private boolean isIntercept = true, mIsViewPager2Drag;
    // 滑动距离范围
    private int mTouchSlop;
    // 记录触摸的位置（主要用于解决事件冲突问题）
    private float mStartX, mStartY;

    private void initUI(Context context) {
        mViewPager2 = new ViewPager2(context);
        mViewPager2.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mViewPager2.setOffscreenPageLimit(1);
        addView(mViewPager2);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (!mViewPager2.isUserInputEnabled() || !isIntercept) {
            return super.onInterceptTouchEvent(event);
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                mStartY = event.getY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float endX = event.getX();
                float endY = event.getY();
                float distanceX = Math.abs(endX - mStartX);
                float distanceY = Math.abs(endY - mStartY);
//                if (mViewPager2.getOrientation() == HORIZONTAL) {
                mIsViewPager2Drag = distanceX > mTouchSlop && distanceX > distanceY;
//                } else {
//                    mIsViewPager2Drag = distanceY > mTouchSlop && distanceY > distanceX;
//                }
                mIsViewPager2Drag = !(mViewPager2.getScrollState() == ViewPager2.SCROLL_STATE_DRAGGING);

                getParent().requestDisallowInterceptTouchEvent(mIsViewPager2Drag);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onInterceptTouchEvent(event);
    }

    public ViewPager2 getmViewPager2() {
        return mViewPager2;
    }
}

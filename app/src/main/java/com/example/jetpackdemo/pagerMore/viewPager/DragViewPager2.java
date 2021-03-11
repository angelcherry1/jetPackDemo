package com.example.jetpackdemo.pagerMore.viewPager;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.jetpackdemo.pagerMore.pageturn.utils.ScreenUtils;
import com.nineoldandroids.view.ViewHelper;

import static androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_DRAGGING;

public class DragViewPager2 extends FrameLayout implements View.OnClickListener {
    public DragViewPager2(@NonNull Context context) {
        super(context);
        initUI(context);
    }

    public DragViewPager2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initUI(context);
    }

    public DragViewPager2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI(context);
    }

    public DragViewPager2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initUI(context);
    }

    private ViewPager2 mViewPager2;
    private boolean isIntercept = true;


    public static final int STATUS_NORMAL = 0;//正常浏览状态
    public static final int STATUS_MOVING = 1;//滑动状态
    public static final int STATUS_RESETTING = 2;//返回中状态
    public static final String TAG = "DragViewPager2";


    public static final float MIN_SCALE_SIZE = 0.3f;//最小缩放比例
    public static final int BACK_DURATION = 300;//ms
    public static final int DRAG_GAP_PX = 50;
    public static final int MIN_TRANS_DEST = 5;//最小滑动距离

    private int currentStatus = STATUS_NORMAL;
    private int currentPageStatus;

    private float mDownX;
    private float mDownY;
    private float screenHeight;

    /**
     * 要缩放的View
     */
    private View currentShowView;
    /**
     * 滑动速度检测类
     */
    private VelocityTracker mVelocityTracker;
    private IAnimClose iAnimClose;

    private void initUI(Context context) {
        mViewPager2 = new ViewPager2(context);
        mViewPager2.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mViewPager2);

        screenHeight = ScreenUtils.getScreenHeight(context);

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                currentPageStatus = state;
            }
        });

    }

    public void setCurrentShowView(View currentShowView) {
        this.currentShowView = currentShowView;
        if (this.currentShowView != null) {
            this.currentShowView.setOnClickListener(this);
        }
    }

    public void setIAnimClose(IAnimClose iAnimClose) {
        this.iAnimClose = iAnimClose;
    }
    //配合SubsamplingScaleImageView使用，根据需要拦截ACTION_MOVE

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mViewPager2.getAdapter() instanceof RecyclerView.Adapter) {

            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    Log.e("jc", "onInterceptTouchEvent:ACTION_DOWN");
                    mDownX = ev.getRawX();
                    mDownY = ev.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    Log.e("jc", "onInterceptTouchEvent:ACTION_MOVE");
//                    if (mImage.getCenter() != null && mImage.getCenter().y <= mImage.getHeight() / mImage.getScale() / 2) {
//                        Log.e("jc", "onInterceptTouchEvent:ACTION_MOVE");
//                        int deltaX = Math.abs((int) (ev.getRawX() - mDownX));
//                        int deltaY = (int) (ev.getRawY() - mDownY);
//                        if (deltaY > DRAG_GAP_PX && deltaX <= DRAG_GAP_PX) {//往下移动超过临界，左右移动不超过临界时，拦截滑动事件
//                            return true;
//                        }
//                    }
                    int mx = (int) ev.getX();
                    int my = (int) ev.getY();

                    int offsetX = (int) (mx - mDownX);
                    int offsetY = (int) (my - mDownY);

                    // 往下移动超过临界，左右移动不超过临界时，拦截滑动事件
                    if ((Math.abs(offsetX) < Math.abs(offsetY) || Math.abs(offsetY) > MIN_TRANS_DEST)) {
                        return true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    Log.e("jc", "onInterceptTouchEvent:ACTION_UP");
                    break;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (currentStatus == STATUS_RESETTING)
            return false;
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getRawX();
                mDownY = ev.getRawY();
                addIntoVelocity(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                addIntoVelocity(ev);
                int deltaY = (int) (ev.getRawY() - mDownY);
                //手指往上滑动
                if (deltaY <= DRAG_GAP_PX && currentStatus != STATUS_MOVING)
                    return super.onTouchEvent(ev);
                //viewpager不在切换中，并且手指往下滑动，开始缩放
                if (currentPageStatus != SCROLL_STATE_DRAGGING && (deltaY > DRAG_GAP_PX || currentStatus == STATUS_MOVING)) {
                    moveView(ev.getRawX(), ev.getRawY());
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (currentStatus != STATUS_MOVING)
                    return super.onTouchEvent(ev);
                final float mUpX = ev.getRawX();
                final float mUpY = ev.getRawY();

                float vY = computeYVelocity();//松开时必须释放VelocityTracker资源
                if (vY >= 1200 || Math.abs(mUpY - mDownY) > screenHeight / 4) {
                    //下滑速度快，或者下滑距离超过屏幕高度的一半，就关闭
                    if (iAnimClose != null) {
                        iAnimClose.onPictureRelease(currentShowView);
                    }
                } else {
                    resetReviewState(mUpX, mUpY);
                }
                break;
        }

        return super.onTouchEvent(ev);
    }

    //返回浏览状态
    private void resetReviewState(final float mUpX, final float mUpY) {
        currentStatus = STATUS_RESETTING;
        if (mUpY != mDownY) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(mUpY, mDownY);
            valueAnimator.setDuration(BACK_DURATION);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float mY = (float) animation.getAnimatedValue();
                    float percent = (mY - mDownY) / (mUpY - mDownY);
                    float mX = percent * (mUpX - mDownX) + mDownX;
                    moveView(mX, mY);
                    if (mY == mDownY) {
                        mDownY = 0;
                        mDownX = 0;
                        currentStatus = STATUS_NORMAL;
                    }
                }
            });
            valueAnimator.start();
        } else if (mUpX != mDownX) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(mUpX, mDownX);
            valueAnimator.setDuration(BACK_DURATION);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float mX = (float) animation.getAnimatedValue();
                    float percent = (mX - mDownX) / (mUpX - mDownX);
                    float mY = percent * (mUpY - mDownY) + mDownY;
                    moveView(mX, mY);
                    if (mX == mDownX) {
                        mDownY = 0;
                        mDownX = 0;
                        currentStatus = STATUS_NORMAL;
                    }
                }
            });
            valueAnimator.start();
        } else if (iAnimClose != null)
            iAnimClose.onPictureClick();
    }


    public ViewPager2 getmViewPager2() {
        return mViewPager2;
    }

    @Override
    public void onClick(View v) {
        if (iAnimClose != null) {
            iAnimClose.onPictureClick();
        }
    }


    //移动View
    private void moveView(float movingX, float movingY) {
        if (currentShowView == null)
            return;
        currentStatus = STATUS_MOVING;
        float deltaX = movingX - mDownX;
        float deltaY = movingY - mDownY;
        float scale = 1f;
        float alphaPercent = 1f;
        if (deltaY > 0) {
            scale = 1 - Math.abs(deltaY) / screenHeight;
            alphaPercent = 1 - Math.abs(deltaY) / (screenHeight / 2);
        }

        ViewHelper.setTranslationX(currentShowView, deltaX);
        ViewHelper.setTranslationY(currentShowView, deltaY);
        scaleView(scale);
        setBackgroundColor(getBlackAlpha(alphaPercent));
    }

    //缩放View
    private void scaleView(float scale) {
        scale = Math.min(Math.max(scale, MIN_SCALE_SIZE), 1);
        ViewHelper.setScaleX(currentShowView, scale);
        ViewHelper.setScaleY(currentShowView, scale);
    }

    //背景透明度变化
    private int getBlackAlpha(float percent) {
        percent = Math.min(1, Math.max(0, percent));
        int intAlpha = (int) (percent * 255);
        return Color.argb(intAlpha, 0, 0, 0);
    }


    //初始位置记录
    private void addIntoVelocity(MotionEvent event) {
        if (mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();
        mVelocityTracker.addMovement(event);
    }

    //松开时必须释放VelocityTracker资源
    private float computeYVelocity() {
        float result = 0;
        if (mVelocityTracker != null) {
            mVelocityTracker.computeCurrentVelocity(1000);
            result = mVelocityTracker.getYVelocity();
            releaseVelocity();
        }
        return result;
    }

    //重置VelocityTracker资源
    private void releaseVelocity() {
        if (mVelocityTracker != null) {
            mVelocityTracker.clear();
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    public interface IAnimClose {
        void onPictureClick();

        void onPictureRelease(View view);
    }

}

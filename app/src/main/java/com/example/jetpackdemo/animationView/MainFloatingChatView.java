package com.example.jetpackdemo.animationView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.Utils;
import com.example.jetpackdemo.R;
import com.example.jetpackdemo.util.BitmapUtil;
import com.example.jetpackdemo.util.SizeUtils;

import java.util.ArrayList;
import java.util.List;

public class MainFloatingChatView extends View {


    private String lastMessage = "缓存消息";//缓存消息
    private List<Bitmap> avatarBitmap;//头像
    private boolean isPlay = false;
    private boolean isFrist = true;

    private boolean startPlay = false;//是否开始播放
    private List<MessageItem> messageItems;


    public MainFloatingChatView(Context context) {
        super(context);
        init();
    }

    public MainFloatingChatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MainFloatingChatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MainFloatingChatView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {

        setBackgroundResource(R.drawable.app_solid_80000000_corners_19dp);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.moren_avatar);
        avatarBitmap = new ArrayList<>();
        avatarBitmap.add(BitmapUtil.small(BitmapUtil.toRoundBitmap(bitmap), SizeUtils.dip2px(getContext(), 30), SizeUtils.dip2px(getContext(), 30)));

        messageItems = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            MessageItem messageItem = new MessageItem();
            messageItem.setTextColor(Color.WHITE);
            messageItem.setTextSize(SizeUtils.dip2px(getContext(), 12));
            messageItem.setX(SizeUtils.dip2px(getContext(), 5));
            messageItem.setContext(lastMessage);
            messageItem.setAvatarBitMap(avatarBitmap.get(0));
            messageItem.setPlay(i == 0);
            messageItems.add(messageItem);
        }


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (messageItems != null && messageItems.size() > 0) {
            for (MessageItem messageItem : messageItems) {
                if (messageItem.isPlay) {
                    messageItem.onMeasure();
                    setMeasuredDimension(messageItem.getViewWith(), messageItem.getViewHith());
                }
            }

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (messageItems != null && messageItems.size() > 0) {
            for (MessageItem messageItem : messageItems) {
                if (isFrist) {
                    if (messageItem.isPlay) {
                        messageItem.onDraw(canvas);
                    }
                } else {
                    messageItem.onDraw(canvas);
                }
            }
        }

    }


    public void setData(String context) {
        lastMessage = context;
        isFrist = false;
        for (MessageItem messageItem : messageItems) {
            if (messageItem.isPlay) {
                messageItem.setPlay(false);
            } else {
                messageItem.setPlay(true);
                messageItem.setContext(context);
            }
        }

        startAnimation();
    }

    public void startAnimation() {
        if (startPlay) return;
        starAnimation(0, SizeUtils.dip2px(getContext(), 40));
        startPlay = true;
    }

    private void starAnimation(float stary, float endy) {

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(stary, endy);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                for (MessageItem messageItem : messageItems) {
                    if (value < SizeUtils.dip2px(getContext(), 40)) {
                        messageItem.setStep((int) value);
                    } else {
                        startPlay = false;
                        requestLayout();
                    }
                }
                postInvalidate();

            }
        });
        // 设置插值器
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.setDuration(500);
        valueAnimator.start();
        valueAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {

            }
        });


    }


    static class MessageItem {
        private int x;
        private int y;
        private int viewWith;
        private int viewHith;
        private boolean isPlay;

        private int textColor;
        private int textSize;
        private Paint textPaint;
        private Paint avatarPaint;
        private String context;
        private Bitmap avatarBitMap;
        private int step;


        public int getX() {
            return x;
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

        public int getTextColor() {
            return textColor;
        }

        public void setTextColor(int textColor) {
            this.textColor = textColor;
        }

        public int getTextSize() {
            return textSize;
        }

        public void setTextSize(int textSize) {
            this.textSize = textSize;
        }

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }

        public int getViewWith() {
            return viewWith;
        }

        public void setViewWith(int viewWith) {
            this.viewWith = viewWith;
        }

        public int getViewHith() {
            return viewHith;
        }

        public void setViewHith(int viewHith) {
            this.viewHith = viewHith;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public Bitmap getAvatarBitMap() {
            return avatarBitMap;
        }

        public void setAvatarBitMap(Bitmap avatarBitMap) {
            this.avatarBitMap = avatarBitMap;
        }

        public boolean isPlay() {
            return isPlay;
        }

        public void setPlay(boolean play) {
            isPlay = play;
        }

        public Paint getTextPaint() {
            if (textPaint == null) {
                textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
                textPaint.setTextSize(textSize);
                textPaint.setColor(textColor);
            }
            return textPaint;
        }

        public Paint getAvatarPaint() {
            if (avatarPaint == null) {
                avatarPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            }
            return textPaint;
        }

        public void onMeasure() {
            if (TextUtils.isEmpty(getContext())) {
                viewWith = SizeUtils.dip2px(Utils.getApp(), 40);
            } else {
                float v = getTextPaint().measureText(getContext()) + SizeUtils.dip2px(Utils.getApp(), 5);
                float v2 = Math.min(v, SizeUtils.dip2px(Utils.getApp(), 150));
                if (v > SizeUtils.dip2px(Utils.getApp(), 150)) {
                    String ellipsizeStr = (String) TextUtils.ellipsize(getContext(), (TextPaint) getTextPaint(), SizeUtils.dip2px(Utils.getApp(), 150), TextUtils.TruncateAt.END);
                    setContext(ellipsizeStr);
                }
                viewWith = SizeUtils.dip2px(Utils.getApp(), 40) + (int) v2;
            }
            viewHith = SizeUtils.dip2px(Utils.getApp(), 40);
        }


        public void onDraw(Canvas canvas) {
            canvas.save();
            int ay = (int) ((getViewHith() - avatarBitMap.getHeight()) / 2) - step;
            canvas.drawBitmap(getAvatarBitMap(), x, isPlay ? getViewHith() + ay : ay, getAvatarPaint());
            //文字的y轴坐标
            Paint.FontMetrics fontMetrics = getTextPaint().getFontMetrics();
            float y = getViewHith() / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2 - getStep();
            canvas.drawText(getContext(), getViewHith(), isPlay ? getViewHith() + y : y, getTextPaint());
            canvas.restore();
        }
    }

}

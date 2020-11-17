package com.example.jetpackdemo.animationView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.jetpackdemo.R;

public class AnnouncementView extends LinearLayout {
    private View view;
    private RelativeLayout txtRL;
    private Context mContext;

    public AnnouncementView(Context context) {
        super(context);
        initUI();
    }


    public AnnouncementView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initUI();
    }

    public AnnouncementView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initUI();
    }

    public AnnouncementView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
    }

    //初始化布局
    private void initUI() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.app_announcement_view, this);
        txtRL = view.findViewById(R.id.rl_txt);

    }


    /**
     * 播放文字动画
     *
     * @ time 播放时间毫秒
     */
    private ObjectAnimator animation;


    private void playTextAnim(CharSequence text) {
        TextView announcementTv1 = new TextView(txtRL.getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        announcementTv1.setLayoutParams(params);
        announcementTv1.setTextColor(Color.parseColor("#ffffff"));
        announcementTv1.setTextSize(11);
        announcementTv1.setMaxLines(1);
        announcementTv1.setText(text);
        txtRL.addView(announcementTv1);
        animation = ObjectAnimator.ofFloat(announcementTv1, "translationX", txtRL.getMeasuredWidth(), 0.0f);
        animation.setDuration(2000);
        animation.start();
    }

    private ObjectAnimator animation2;
    private String userName, roomName, giftText;


    private void playTextExitAnim() {
        animation2 = ObjectAnimator.ofFloat(txtRL.getChildAt(0), "translationX", 0.0f, -txtRL.getMeasuredWidth());
        animation2.setDuration(2000);
        playTextAnim(setEggInformation(userName, roomName, giftText));
        animation2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                txtRL.removeView(txtRL.getChildAt(0));
            }
        });
        animation2.start();
    }


    private int count = 0;

    public void setMessage(String userName, String roomName, String giftText) {
        if (count == 0) {
            playEnterAnima(setEggInformation(userName, roomName, giftText));
        } else {
            this.userName = userName;
            this.roomName = roomName;
            this.giftText = giftText;
            playTextExitAnim();
        }
        count++;
        time = 14;
    }


    public SpannableStringBuilder setEggInformation(String userName, String roomName, String giftText) {
        String txt = "恭喜" + userName + "在" + roomName + "的房间中砸出了" + giftText;
        SpannableStringBuilder ss = new SpannableStringBuilder(txt);
        ss.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), txt.indexOf(userName), txt.indexOf(userName) + userName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.parseColor("#FFFC00")), txt.indexOf(userName), txt.indexOf(userName) + userName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), txt.indexOf(roomName), txt.indexOf(roomName) + userName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.parseColor("#07FFEC")), txt.indexOf(roomName), txt.indexOf(roomName) + roomName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), txt.indexOf(giftText), txt.indexOf(giftText) + giftText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.parseColor("#3FFF6C")), txt.indexOf(giftText), txt.indexOf(giftText) + giftText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    private void playEnterAnima(CharSequence text) {
        if (playEdixtAnima != null) {
            playEdixtAnima.cancel();
        }
        TextView announcementTv = new TextView(txtRL.getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        announcementTv.setLayoutParams(params);
        announcementTv.setTextColor(Color.parseColor("#ffffff"));
        announcementTv.setTextSize(11);
        announcementTv.setText(text);
        txtRL.addView(announcementTv);
        ObjectAnimator animation = ObjectAnimator.ofFloat(this, "translationX", this.getMeasuredWidth(), 0.0f);
        animation.setDuration(2000);
        animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                postDelayed(EdixtAnima, 1000);
            }
        });
        animation.start();
    }

    private int time;
    Runnable EdixtAnima = new Runnable() {
        @Override
        public void run() {
            if (time > 0) {
                time--;
                postDelayed(EdixtAnima, 1000);
            } else {
                playEdixtAnima();
                count = 0;
            }
        }
    };

    ObjectAnimator playEdixtAnima;

    private void playEdixtAnima() {
        if (animation != null) {
            animation.cancel();
        }
        if (animation2 != null) {
            animation2.cancel();
        }
        playEdixtAnima = ObjectAnimator.ofFloat(this, "translationX", 0.0f, -this.getMeasuredWidth());
        playEdixtAnima.setDuration(2000);
        playEdixtAnima.addListener(new AnimatorListenerAdapter() {


            @Override
            public void onAnimationEnd(Animator animation) {
                txtRL.removeAllViews();
            }
        });
        playEdixtAnima.start();
    }

}

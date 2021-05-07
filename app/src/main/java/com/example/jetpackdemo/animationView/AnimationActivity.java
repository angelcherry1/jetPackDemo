package com.example.jetpackdemo.animationView;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;

import com.example.jetpackdemo.R;

public class AnimationActivity extends AppCompatActivity {

    private SpreadView spreadView;
    private CustomScoreBar csb;
    private ImageView iv_red, imageView;
    private FlexBoxLayout2 ll_add_view;
    private EditText left_et, right_et;
    //    private TextView tv_9;
    private AnnouncementView avm;
    private HorizontalScrollView hsv;
    private int conte = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        spreadView = findViewById(R.id.spreadView);
        csb = findViewById(R.id.csb);
        iv_red = findViewById(R.id.iv_red);
        left_et = findViewById(R.id.left_et);
        right_et = findViewById(R.id.right_et);
        ll_add_view = findViewById(R.id.ll_add_view);
        avm = findViewById(R.id.avm);
        hsv = findViewById(R.id.hsv);
//        tv_9 = findViewById(R.id.tv_9);
//        input = findViewById(R.id.input);
//        ImageView iv_image = findViewById(R.id.iv_image);
        final ImageView iv_red2 = findViewById(R.id.iv_red2);
        Button start = findViewById(R.id.start);
        Button stop = findViewById(R.id.stop);
        TextView font = findViewById(R.id.tv_font);

        Typeface fromAsset = Typeface.createFromAsset(getAssets(), "app_mao_bi.ttf");
        font.setTypeface(fromAsset);
//        tv_9.setBackground(createNinePatch(this));
//        iv_image.setImageDrawable(createNinePatch(this));
//        input.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                tv_9.setText(s);
//            }
//        });
        final Button tanli = findViewById(R.id.tanli);
        ObjectAnimator textEnterAnim = ObjectAnimator.ofFloat(tanli, "translationY", 0f, 200.0f);
        textEnterAnim.setDuration(2000);
        final SpringAnimation springAnim = new SpringAnimation(tanli, DynamicAnimation.TRANSLATION_Y, 10.0f);
        springAnim.setMaxValue(300f);
        tanli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                springAnim.start();
            }
        });


        csb.setScores(20, 20);
        left_et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    csb.setScores(Integer.parseInt(left_et.getText().toString()), Integer.parseInt(right_et.getText().toString()));
                }
                return false;
            }
        });
        right_et.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    csb.setScores(Integer.parseInt(left_et.getText().toString()), Integer.parseInt(right_et.getText().toString()));
                }
                return false;
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spreadView.setStart();
                scaleAnimation(iv_red);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(10, 5, 10, 5);
                TextView textView = new TextView(getBaseContext());
                textView.setText("我是你爹");
                textView.setLayoutParams(layoutParams);
                textView.setTextSize(10);
                ll_add_view.addView(textView);
                avm.setVisibility(View.VISIBLE);
                conte++;
                avm.setMessage("兮兮", "女神来了", "火箭x" + conte);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spreadView.stopAnima();
                ll_add_view.removeAllViews();
            }
        });


        imageView = findViewById(R.id.iv_search);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageView.getBackground() instanceof Animatable) {
                    Animatable background = (Animatable) imageView.getBackground();
                    if (background.isRunning()) {
                        background.stop();
                    } else {
                        background.start();
                    }

                }
            }
        });
    }

    private int a = 0;
    @SuppressLint("HandlerLeak")
    final Handler handler = new Handler() {

        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 100:
                    if (a < 100) {
                        a++;
                        csb.setScores(20, a);
                        handler.sendEmptyMessageDelayed(100, 100);

                    }

                    break;

            }
        }
    };

    public Drawable createNinePatch(Context context) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.qipao);
        NinePatchBuilder ninePatchBuilder = new NinePatchBuilder(context.getResources(), bitmap);
//        ninePatchBuilder.addXCenteredRegion(SizeUtils.dip2px(context, 10));
        ninePatchBuilder.addXRegionPoints(100, 160);
//        ninePatchBuilder.addYCenteredRegion(SizeUtils.dip2px(context, 10));
        ninePatchBuilder.addYRegionPoints(100, 160);

//        return NinePatchBitmapFactory.createNinePathWithCapInsets(context.getResources(), bitmap, 38, 50, 50, 38, "qipao.9.png");
        return ninePatchBuilder.build();
    }

    private void scaleAnimation(final View view) {
        AnimationSet mAnimatorSet = new AnimationSet(true);
//        TranslateAnimation moveUpAnimation = new TranslateAnimation(0F, dpToPx(300), 0, 0);
//        moveUpAnimation.setDuration(2000);//设置动画变化的持续时间
//        moveUpAnimation.setFillEnabled(true);//使其可以填充效果从而不回到原地
//        moveUpAnimation.setFillAfter(true);//不回到起始位置
//        mAnimatorSet.addAnimation(moveUpAnimation);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1000f, 0f);
        scaleAnimation.setDuration(2000);

        mAnimatorSet.addAnimation(scaleAnimation);

        view.startAnimation(mAnimatorSet);
        mAnimatorSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * dp转换为px
     */
    public static int dpToPx(float dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density + 0.5f);
    }


    private void hscorller() {
        hsv.smoothScrollTo(0, 0);
    }

}
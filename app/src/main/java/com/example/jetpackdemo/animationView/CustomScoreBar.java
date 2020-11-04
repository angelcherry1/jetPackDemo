package com.example.jetpackdemo.animationView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

import com.example.jetpackdemo.R;


public class CustomScoreBar extends View {
    private long mScoreLeft, mScoreRight;
    //各种画笔
    private Paint paintText = new Paint();

    private Bitmap left; //左边背景图
    private Bitmap right; //右边背景图
    private Bitmap center; //中间图标
    private float leftLoft; //左边进度条长度
    private float rightLoft; //右边进度条长度
    private float changeW;
    private int spaceWidth = 20;
    private Context mContext;

    public CustomScoreBar(Context context) {
        this(context, null);
        this.mContext = context;
        init();
    }

    public CustomScoreBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.mContext = context;
        init();
    }

    public CustomScoreBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomScoreBar, defStyleAttr, 0);
        int leftBackground = a.getResourceId(R.styleable.CustomScoreBar_leftBackground, ContextCompat.getColor(context, R.color.colorAccent));
        int rightBackground = a.getResourceId(R.styleable.CustomScoreBar_rightBackground, ContextCompat.getColor(context, R.color.colorAccent));
        int centerIcon = a.getResourceId(R.styleable.CustomScoreBar_centerIcon, ContextCompat.getColor(context, R.color.colorAccent));
        left = BitmapFactory.decodeResource(context.getResources(), leftBackground);
        right = BitmapFactory.decodeResource(context.getResources(), rightBackground);
        center = BitmapFactory.decodeResource(context.getResources(), centerIcon);
        a.recycle();
        init();
    }

    private void init() {
        //初始化数据，默认属性
        mScoreLeft = 0;
        mScoreRight = 0;

        //文字画笔设置
        paintText.reset();
        paintText.setAntiAlias(true);
        paintText.setTextSize(dip2px(15));
        paintText.setFakeBoldText(true);
        paintText.setColor(Color.parseColor("#FFFFFF"));
        /*Paint.Align.CENTER：The text is drawn centered horizontally on the x,y origin*/
        paintText.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //组件宽度
        changeW = (float) w;
    }


    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getWidth();
        float height = getHeight();
        if (mScoreLeft == 0 && mScoreRight == 0) {
            leftLoft = rightLoft = changeW / 2;
        } else {
            leftLoft = changeW * ((float) mScoreLeft / (mScoreLeft + mScoreRight));
            rightLoft = changeW * ((float) mScoreRight / (mScoreLeft + mScoreRight));
        }
        if (leftLoft < right.getHeight()) {
            rightLoft = rightLoft - (right.getHeight() - leftLoft);
            leftLoft = right.getHeight();
        }
        if (rightLoft < right.getHeight()) {
            leftLoft = leftLoft - (right.getHeight() - rightLoft);
            rightLoft = right.getHeight();
        }
        //绘制右边进度条
        Bitmap scaledBitmap2 = Bitmap.createScaledBitmap(right, (int) width, right.getHeight(), false);
        for (int i = 1; i < scaledBitmap2.getHeight(); i++) {
            Bitmap cutBitmap2 = Bitmap.createBitmap(scaledBitmap2, (int) width - ((int) rightLoft + right.getHeight() / 2), i, (int) rightLoft + right.getHeight() / 2, 1);
            canvas.drawBitmap(cutBitmap2, leftLoft - right.getHeight() / 2, (i - (left.getHeight() / 2)) + dip2px(5), new Paint());
        }


        //绘制左边进度条
        Bitmap scaledBitmap1 = Bitmap.createScaledBitmap(left, (int) width, left.getHeight(), false);
        // 定义矩阵对象
        for (int i = 1; i < scaledBitmap1.getHeight(); i++) {
            Bitmap cutBitmap = Bitmap.createBitmap(scaledBitmap1, 0, i, (int) leftLoft + (scaledBitmap1.getHeight() / 2) - i + dip2px(5), 1);
            canvas.drawBitmap(cutBitmap, 0, (i - (left.getHeight() / 2)) + dip2px(5), new Paint());
        }


        //绘制中间图标
        Bitmap scaledBitmap3 = Bitmap.createScaledBitmap(center, (int) height, (int) height, false);
        canvas.drawBitmap(scaledBitmap3, leftLoft - height / 2, 0, new Paint());

        //测量左边的比分文字
        Paint.FontMetrics fontMetrics = paintText.getFontMetrics();
        float textBaseLineOffset = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        Rect rect = new Rect();
        paintText.getTextBounds(String.valueOf(mScoreLeft), 0, String.valueOf(mScoreLeft).length(), rect);
        //绘制左边的比分文字
        canvas.drawText("" + mScoreLeft, dip2px(18) + rect.width(), height / 2 + textBaseLineOffset, paintText);

        //测量右边的比分文字
        fontMetrics = paintText.getFontMetrics();
        textBaseLineOffset = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        Rect rect2 = new Rect();
        paintText.getTextBounds(String.valueOf(mScoreRight), 0, String.valueOf(mScoreRight).length(), rect2);
        //在控件宽度的最后十分之一绘制右边的比分数字
        canvas.drawText("" + mScoreRight, width - (dip2px(18) + rect2.width()), height / 2 + textBaseLineOffset, paintText);
    }

    /**
     * 动态设置比分
     *
     * @param score1 //左边分数
     * @param score2 //右边分数
     */
    public void setScores(long score1, long score2) {
        this.mScoreLeft = score1;
        this.mScoreRight = score2;
        if (score1 >= 0 && score2 >= 0) {
            postInvalidate();
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
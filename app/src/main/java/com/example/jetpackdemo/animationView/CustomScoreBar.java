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
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.example.jetpackdemo.R;


public class CustomScoreBar extends View {
    private int mScoreLeft, mScoreRight;
    //各种画笔

    private Paint paintText=new Paint();


    private Bitmap left;
    private Bitmap right;
    private Bitmap center;
    private   float leftLoft;
    private   float rightLoft;
    private float my;
    public CustomScoreBar(Context context) {
        this(context,null);
        init();
    }

    public CustomScoreBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        init();
    }

    public CustomScoreBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        mScoreLeft =1;
        mScoreRight =1;

        //文字画笔设置
        paintText.reset();
        paintText.setAntiAlias(true);
        //文字的大小取控件宽度的十分之一和高度的二分之一的最小值
        paintText.setTextSize(30);
        paintText.setFakeBoldText(true);
        paintText.setColor(Color.parseColor("#FFFFFF"));
        /*Paint.Align.CENTER：The text is drawn centered horizontally on the x,y origin*/
        paintText.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //圆心位置
        my = (float)w;

    }

    private boolean isfirst=true;
    private int y=0;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width=getWidth();
        float height=getHeight();
        if(mScoreLeft==0&&mScoreRight==0){
            leftLoft= rightLoft=my/2;
        }else {
            leftLoft= my*((float)mScoreLeft/(mScoreLeft+mScoreRight));
            rightLoft=my*((float)mScoreRight/(mScoreLeft+mScoreRight));
        }
    if(leftLoft==0){
        leftLoft=1;
    }
        if(rightLoft==0){
            rightLoft=1;
        }


        //绘制左边进度条
        if(y<(int)leftLoft){
            Bitmap scaledBitmap1 = Bitmap.createScaledBitmap(left, (int)width, left.getHeight(), false);
            Bitmap cutBitmap = Bitmap.createBitmap(scaledBitmap1,0,0, y, left.getHeight());
            canvas.drawBitmap(cutBitmap, 0, (height - left.getHeight()) / 2, new Paint());
        }
        y++;
        //绘制右边进度条
        Bitmap scaledBitmap2 = Bitmap.createScaledBitmap(right, (int)width, left.getHeight(), false);
        Bitmap cutBitmap2 = Bitmap.createBitmap(scaledBitmap2,(int)width-(int) rightLoft,0, (int) rightLoft, right.getHeight());
        canvas.drawBitmap(cutBitmap2, leftLoft, (height - left.getHeight()) / 2, new Paint());

        //绘制中间图标
        Bitmap scaledBitmap3 = Bitmap.createScaledBitmap(center, (int) height, (int) height, false);
        canvas.drawBitmap(scaledBitmap3, leftLoft - height / 2, 0, new Paint());


        //测量字体
        Paint.FontMetrics fontMetrics = paintText.getFontMetrics();
        float textBaseLineOffset = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        Rect rect = new Rect();
        paintText.getTextBounds(String.valueOf(mScoreLeft), 0,String.valueOf(mScoreLeft).length(), rect);
        //绘制左边的比分文字
        canvas.drawText("" + mScoreLeft, 30+rect.width(), height / 2 + textBaseLineOffset, paintText);
        //测量右边的比分文字
        fontMetrics = paintText.getFontMetrics();
        textBaseLineOffset = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;

        Rect rect2 = new Rect();
        paintText.getTextBounds(String.valueOf(mScoreRight), 0, String.valueOf(mScoreRight).length(), rect2);
        //在控件宽度的最后十分之一绘制右边的比分数字
        canvas.drawText("" + mScoreRight, width - (30+rect2.width()), height / 2 + textBaseLineOffset, paintText);

        if(isfirst){
            postInvalidateDelayed(100);
        }

    }

    public void setScores(int score1, int score2) {
        this.mScoreLeft =score1;
        this.mScoreRight =score2;
        if(score1>=0&&score2>=0){
            postInvalidate();
        }
    }

}
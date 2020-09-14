package com.example.jetpackdemo.animationView;

import android.annotation.SuppressLint;
import android.content.Context;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.jetpackdemo.R;

import java.util.ArrayList;
import java.util.List;


public class SpreadView extends View {

    private Paint centerPaint; //中心圆paint
    private int radius = 100; //中心圆半径
    private Paint spreadPaint; //扩散圆paint
    private float centerX;//圆心x
    private float centerY;//圆心y
    private int distance = 2; //每次圆递增间距
    private int maxRadius = 50; //最大圆半径
    private int delayMilliseconds = 3;//扩散延迟间隔，越大扩散越慢
    private List<Integer> spreadRadius = new ArrayList<>();//扩散圆层级数，元素为扩散的距离
    private List<Integer> alphas = new ArrayList<>();//对应每层圆的透明度
    private int centerColor;
    private int spreadColor;
    private Paint TextPaint; //文字
    private boolean isStart=true;
    private Context mContext;
    public SpreadView(Context context) {
        this(context,null);
    }

    public SpreadView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);

    }



    public SpreadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SpreadView, defStyleAttr, 0);
        radius = a.getInt(R.styleable.SpreadView_spread_radius, radius);
        maxRadius = a.getInt(R.styleable.SpreadView_spread_max_radius, maxRadius);
       centerColor = a.getColor(R.styleable.SpreadView_spread_center_color, ContextCompat.getColor(context, R.color.colorAccent));
      spreadColor = a.getColor(R.styleable.SpreadView_spread_spread_color, ContextCompat.getColor(context, R.color.colorAccent));
        distance = a.getInt(R.styleable.SpreadView_spread_distance, distance);
        a.recycle();
        initUI();
    }

    private void initUI() {

        centerPaint = new Paint();
        centerPaint.setColor(centerColor);
        centerPaint.setAntiAlias(true);
        //最开始不透明且扩散距离为0
        alphas.add(255);
        spreadRadius.add(0);
        spreadPaint = new Paint();
        spreadPaint.setStyle(Paint.Style.FILL);
        spreadPaint.setAntiAlias(true);
        spreadPaint.setAlpha(255);
        spreadPaint.setColor(spreadColor);
        TextPaint=new Paint();
        TextPaint.setAntiAlias(true);
        TextPaint.setColor(Color.parseColor("#FED201"));
        TextPaint.setTextSize(50f);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //圆心位置
        centerX = w / 2;
        centerY = h / 2;
        y=centerY;
    }
    private float y;
    private boolean isFirst=true;
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < spreadRadius.size(); i++) {
            int alpha = alphas.get(i);
            spreadPaint.setAlpha(alpha);
            int width = spreadRadius.get(i);
            //绘制扩散的圆
            canvas.drawCircle(centerX, centerY, radius + width, spreadPaint);

            //每次扩散圆半径递增，圆透明度递减
            if (alpha > 0 && width < 200) {
                alpha = alpha - distance-1 > 0 ? alpha - distance-1 : 1;
                alphas.set(i, alpha);
                spreadRadius.set(i, width + distance-1);
            }
        }
        //当最外层扩散圆半径达到最大半径时添加新扩散圆
        if (spreadRadius.get(spreadRadius.size() - 1) > maxRadius) {
            spreadRadius.add(0);
            alphas.add(255);
        }
        //超过8个扩散圆，删除最先绘制的圆，即最外层的圆
        if (spreadRadius.size() >= 4) {
            alphas.remove(0);
            spreadRadius.remove(0);
        }
        //中间的圆
        canvas.drawCircle(centerX, centerY, radius, centerPaint);
        //TODO 可以在中间圆绘制文字或者图片
        y++;
        if(y<centerY*2){
            canvas.drawText("我是你爹",centerX,y,TextPaint);
        }else {
            y=centerY;
            canvas.drawText("我是你爹",centerX,centerY,TextPaint);
        }


        //延迟更新，达到扩散视觉差效果
        if(isStart){
            postInvalidateDelayed(delayMilliseconds);
        }

    }

    public void stopAnima(){
        isStart=false;
    }
    public void setStart(){
        isStart=true;
        postInvalidateDelayed(delayMilliseconds);
    }
}

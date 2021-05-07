package com.example.jetpackdemo.animationView.myViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MyFlowlayout extends ViewGroup {


    private int mHorizontalSpacing = 0;
    private int mVerticalSpacing = 0;

    private List<List<View>> allLineView;//所有行的view
    private List<Integer> allLineHeight; //所有的行高

    public MyFlowlayout(Context context) {
        super(context);
    }

    public MyFlowlayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public MyFlowlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        allLineView = new ArrayList<>();//所有行的view
        allLineHeight = new ArrayList<>(); //所有的行高

        List<View> lineView = new ArrayList<>();//保存一行中所有的view

        int row = 0;
        View childAt1 = getChildAt(0);
        measureChild(childAt1,widthMeasureSpec,heightMeasureSpec);
        int lineUserWidth = 0;//这一行使用的宽度
        int lineUserHeight = childAt1.getMeasuredHeight();//这一行使用的高度

        allLineView.add(lineView);
        allLineHeight.add(lineUserHeight);
        int parentNeedWidth = 0;
        int parentNeedHeight = 0;

        int myWidth = MeasureSpec.getSize(widthMeasureSpec);
        int myHeight = MeasureSpec.getSize(heightMeasureSpec);

        int paddingBottom = getPaddingBottom();
        int paddingTop = getPaddingTop();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();


        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
//            LayoutParams layoutParams = childAt.getLayoutParams();
//            int childMeasureSpec_w = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, layoutParams.width);
//            int childMeasureSpec_h = getChildMeasureSpec(heightMeasureSpec, paddingBottom + paddingTop, layoutParams.height);
//
//            childAt.measure(childMeasureSpec_w, childMeasureSpec_h);
            measureChild(childAt,widthMeasureSpec,heightMeasureSpec);
            int width = childAt.getMeasuredWidth();
            int height = childAt.getMeasuredHeight();

            if (lineUserWidth + width + mHorizontalSpacing > myWidth) {
                //换行
                row++;
                allLineView.add(lineView);
                allLineHeight.add(lineUserHeight);

                parentNeedWidth = Math.max(lineUserWidth, parentNeedWidth);
                parentNeedHeight = parentNeedHeight + lineUserHeight + mVerticalSpacing;

                lineView = new ArrayList<>();//保存一行中所有的view
                lineUserWidth = 0;//这一行使用的宽度
                lineUserHeight = 0;//这一行使用的高度
            } else {
                lineView.add(childAt);
                lineUserWidth = lineUserWidth + width + mHorizontalSpacing;
                lineUserHeight = Math.max(lineUserHeight, height);

            }

        }
        int myWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int myHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int realWidth = myWidthMode == MeasureSpec.EXACTLY ? myWidth : parentNeedWidth;
        int realHeight = myHeightMode == MeasureSpec.EXACTLY ? myHeight : parentNeedHeight;

        setMeasuredDimension(realWidth, realHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int heightSize = allLineHeight.size(); //获得行数

        int culL = getPaddingLeft();
        int culT = getPaddingTop();
        for (int i = 0; i < heightSize; i++) {
            List<View> views = allLineView.get(i);
            for (int j = 0; j < views.size(); j++) {
                View childView = views.get(j);
                int left = culL;
                int right = culL + childView.getMeasuredWidth();
                int top = culT;
                int bottom = culT + childView.getMeasuredHeight();
                childView.layout(left, top, right, bottom);
                culL = right + mHorizontalSpacing;
            }
            culL = getPaddingLeft();
            culT = culT + allLineHeight.get(i) + mVerticalSpacing;
        }

    }
}

package com.example.jetpackdemo.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CharAvatarDrawable extends Drawable {
    // 颜色画板集
    private static final int[] colors = {
            0xff1abc9c, 0xff16a085, 0xfff1c40f, 0xfff39c12, 0xff2ecc71,
            0xff27ae60, 0xffe67e22, 0xffd35400, 0xff3498db, 0xff2980b9,
            0xffe74c3c, 0xffc0392b, 0xff9b59b6, 0xff8e44ad, 0xffbdc3c7,
            0xff34495e, 0xff2c3e50, 0xff95a5a6, 0xff7f8c8d, 0xffec87bf,
            0xffd870ad, 0xfff69785, 0xff9ba37e, 0xffb49255, 0xffb49255, 0xffa94136
    };


    private Paint mPaintBackground;
    private Paint mPaintText;
    private Rect mRect;

    private String text;

    private int charHash;
    private int mW;
    private int mH;

    public CharAvatarDrawable() {
        init();
    }

    private void init() {
        mPaintBackground = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRect = new Rect();


    }


    @Override
    public void draw(@NonNull Canvas canvas) {

        if (null != text) {
            int color = colors[charHash % colors.length];
            // 画圆
            mPaintBackground.setColor(color);
            canvas.drawCircle((float) mW / 2, (float) mW / 2, (float) mW / 2, mPaintBackground);
            // 写字
            mPaintText.setColor(Color.WHITE);
            mPaintText.setTextSize((float) mW / 2);
            mPaintText.setStrokeWidth(3);
            mPaintText.getTextBounds(text, 0, 1, mRect);
            // 垂直居中
            Paint.FontMetricsInt fontMetrics = mPaintText.getFontMetricsInt();
            int baseline = (mW - fontMetrics.bottom - fontMetrics.top) / 2;
            // 左右居中
            mPaintText.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(text, (float) mW / 2, baseline, mPaintText);
        }
    }


    @Override
    public void setAlpha(int alpha) {
        mPaintBackground.setAlpha(alpha);
        mPaintText.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaintText.setColorFilter(colorFilter);

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    /**
     * @param content 传入字符内容
     *                只会取内容的第一个字符,如果是字母转换成大写
     */
    public void setText(String content, int mW) {
        if (content == null) {
            content = " ";
        }
        this.text = String.valueOf(content.toCharArray()[0]);
        this.text = text.toUpperCase();
        charHash = this.text.hashCode();
        this.mW = mW;
        // 重绘
        invalidateSelf();
    }

}

package com.example.jetpackdemo.view;

import android.content.Context;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

public class RoundProgressBar extends View {
    private boolean isPausing;
    private Context ctx;
    protected Paint paint;
    protected int roundColor;
    private float backColorWidth;
    protected int roundProgressColor;
    private int secondProgressColor;
    private boolean roundShader;
    private int roundShaderStartColor;
    private int roundShaderEndColor;
    private int textColor;
    protected int startAngle;
    private int endAngle;
    private float textSize;
    private float roundWidth;
    private int max;
    private int progress;
    private boolean textIsDisplayable;
    private RoundProgressCallback roundProgressCallback;
    private int style;
    private int radius;
    public static final int STROKE = 0;
    public static final int FILL = 1;
    public BitmapShader mBitmapShader;
    private SweepGradient mRoundShader;
    private Matrix mMatrix;
    private int mWidth;
    private int backgroundColor;
    private Handler mMainHandle;
    private int maxMiliSeconds;
    private Runnable processRunnable;
    int mProgressAngle;

    public int getRadius() {
        return this.radius;
    }

    public RoundProgressBar(Context context) {
        this(context, (AttributeSet) null);
        this.ctx = context;
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.ctx = context;
    }

    public void startProcess(int maxMiliSeconds, RoundProgressCallback roundProgressCallback) {
        this.roundProgressCallback = roundProgressCallback;
        this.setProgress(0);
        this.maxMiliSeconds = maxMiliSeconds;
        this.mMainHandle.post(this.processRunnable);
    }

    public void setGradientColor(int color) {
        this.roundShaderEndColor = color;
    }

    public void pauseProcess(boolean pause) {
        this.isPausing = pause;
    }

    public void stopProcess() {
        this.mMainHandle.removeCallbacks(this.processRunnable);
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.isPausing = false;
        this.style = 0;
        this.radius = 0;
        this.maxMiliSeconds = -1;
        this.processRunnable = new Runnable() {
            public void run() {
                if (RoundProgressBar.this.isPausing) {
                    RoundProgressBar.this.mMainHandle.postDelayed(this, (long) (RoundProgressBar.this.maxMiliSeconds / RoundProgressBar.this.getMax()));
                } else {
                    int process = RoundProgressBar.this.getProgress();
                    ++process;
                    if (null != RoundProgressBar.this.roundProgressCallback) {
                        RoundProgressBar.this.roundProgressCallback.onProgress(RoundProgressBar.this.progress);
                    }

                    if (process >= RoundProgressBar.this.getMax()) {
                        process = RoundProgressBar.this.getMax();
                    }

                    RoundProgressBar.this.setProgress(process);
                    if (process >= RoundProgressBar.this.getMax()) {
                        if (null != RoundProgressBar.this.roundProgressCallback) {
                            RoundProgressBar.this.roundProgressCallback.onFinish();
                            RoundProgressBar.this.stopProcess();
                        }
                    } else {
                        RoundProgressBar.this.mMainHandle.postDelayed(this, (long) (RoundProgressBar.this.maxMiliSeconds / RoundProgressBar.this.getMax()));
                    }

                }
            }
        };
        this.mProgressAngle = 0;
        this.paint = new Paint();
        this.mMainHandle = new Handler(Looper.getMainLooper());
//        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.zface_round_progressBar);
//        this.roundColor = mTypedArray.getColor(R.styleable.zface_round_progressBar_zface_round_color, -65536);
//        this.roundProgressColor = mTypedArray.getColor(R.styleable.zface_round_progressBar_zface_round_progress_color, -16711936);
//        this.secondProgressColor = mTypedArray.getColor(R.styleable.zface_round_progressBar_zface_round_progress_color, -16711936);
//        this.textColor = mTypedArray.getColor(R.styleable.zface_round_progressBar_zface_text_color, -16711936);
//        this.textSize = mTypedArray.getDimension(R.styleable.zface_round_progressBar_zface_text_size, 15.0F);
//        this.roundWidth = mTypedArray.getDimension(R.styleable.zface_round_progressBar_zface_round_width, 5.0F);
//        this.max = mTypedArray.getInteger(R.styleable.zface_round_progressBar_zface_max, 100);
//        this.textIsDisplayable = mTypedArray.getBoolean(R.styleable.zface_round_progressBar_zface_text_is_displayable, true);
//        this.style = mTypedArray.getInt(R.styleable.zface_round_progressBar_zface_style, 0);
//        this.roundShader = mTypedArray.getBoolean(R.styleable.zface_round_progressBar_zface_progress_shader, false);
//        this.backColorWidth = mTypedArray.getDimension(R.styleable.zface_round_progressBar_zface_color_bg_width, 0.0F);
//        this.roundShaderStartColor = mTypedArray.getColor(R.styleable.zface_round_progressBar_zface_gradient_color_start, 0);
//        this.roundShaderEndColor = mTypedArray.getColor(R.styleable.zface_round_progressBar_zface_gradient_color_end, 0);
//        this.startAngle = mTypedArray.getInt(R.styleable.zface_round_progressBar_zface_start_angle, 0);
//        this.endAngle = mTypedArray.getInt(R.styleable.zface_round_progressBar_zface_end_angle, 360);
//        this.backgroundColor = mTypedArray.getColor(R.styleable.zface_round_progressBar_zface_background_color, -1);
//        if (this.backColorWidth > 0.0F && this.roundShader) {
//            this.mMatrix = new Matrix();
//            Bitmap bm = BitmapFactory.decodeResource(this.getResources(), R.mipmap.zface_circle_bg);
//            this.mBitmapShader = new BitmapShader(bm, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//            float scale = 1.0F;
//            this.mWidth = (int) this.backColorWidth;
//            int bSize = Math.min(bm.getWidth(), bm.getHeight());
//            scale = (float) this.mWidth * 1.0F / (float) bSize;
//            this.mMatrix.setScale(scale, scale);
//            this.mBitmapShader.setLocalMatrix(this.mMatrix);
//        }

//        mTypedArray.recycle();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centre = this.getWidth() / 2;
        this.radius = (int) ((float) centre - this.roundWidth / 2.0F);
        this.paint.setColor(this.roundColor);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(this.roundWidth);
        this.paint.setAntiAlias(true);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setColor(this.backgroundColor);
        this.paint.setStrokeWidth(0.0F);
        this.paint.setColor(this.textColor);
        this.paint.setTextSize(this.textSize);
        this.paint.setTypeface(Typeface.DEFAULT_BOLD);
        int percent = (int) ((float) this.progress / (float) this.max * 100.0F);
        float textWidth = this.paint.measureText(percent + "%");
        this.paint.setShader((Shader) null);
        if (this.textIsDisplayable && percent != 0 && this.style == 0) {
            canvas.drawText(percent + "%", (float) centre - textWidth / 2.0F, (float) centre + this.textSize / 2.0F, this.paint);
        }

        this.paint.setStrokeWidth(this.roundWidth);
        RectF oval = new RectF((float) (centre - this.radius), (float) (centre - this.radius), (float) (centre + this.radius), (float) (centre + this.radius));
        this.paint.setColor(this.roundColor);
        switch (this.style) {
            case 0:
                this.paintStroke(canvas, oval);
                break;
            case 1:
                this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
                if (this.progress != 0) {
                    canvas.drawArc(oval, (float) (90 + this.startAngle), (float) ((this.endAngle - this.startAngle) * this.progress / this.max), true, this.paint);
                }
        }

    }

    private void paintStroke(Canvas canvas, RectF oval) {
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(this.roundColor);
        canvas.drawArc(oval, (float) this.startAngle, (float) (this.endAngle - this.startAngle), false, this.paint);
        if (this.mBitmapShader != null) {
            this.paint.setShader(this.mBitmapShader);
        }

        if (this.roundShader && this.roundShaderStartColor != 0 && this.roundShaderEndColor != 0 && null == this.mRoundShader) {
            float xCenter = oval.centerX();
            float yCenter = oval.centerY();
            this.mRoundShader = new SweepGradient(xCenter, yCenter, new int[]{this.roundShaderStartColor, this.roundShaderEndColor}, (float[]) null);
            Matrix matrix = new Matrix();
            matrix.setRotate(90.0F, xCenter, yCenter);
            this.mRoundShader.setLocalMatrix(matrix);
        }

        if (this.mRoundShader != null) {
            this.paint.setShader(this.mRoundShader);
        }

        this.paint.setColor(this.roundProgressColor);
        int progressAngle = this.progress * (this.endAngle - this.startAngle) / this.getMax();
        canvas.drawArc(oval, (float) this.startAngle, (float) progressAngle, false, this.paint);
        this.paint.setShader((Shader) null);
    }

    public void setBackgroundColor(int color) {
        this.backgroundColor = color;
        this.postInvalidate();
    }

    public void setRoundColor(int color) {
        this.roundColor = color;
        this.postInvalidate();
    }

    public synchronized int getMax() {
        return this.max;
    }

    public synchronized void setMax(int max) {
        if (max < 0) {
            throw new IllegalArgumentException("max not less than 0");
        } else {
            this.max = max;
        }
    }

    public synchronized int getProgress() {
        return this.progress;
    }

    public synchronized void setProgress(int progress) {
        if (progress < 0) {
            throw new IllegalArgumentException("progress not less than 0");
        } else {
            if (progress > this.max) {
                progress = this.max;
            }

            if (progress <= this.max) {
                this.progress = progress;
                this.postInvalidate();
            }

        }
    }

    public synchronized void setProgressAngle(int angle) {
        this.mProgressAngle = angle;
        this.postInvalidate();
    }

    public int getCricleColor() {
        return this.roundColor;
    }

    public void setCricleColor(int cricleColor) {
        this.roundColor = cricleColor;
    }

    public int getCricleProgressColor() {
        return this.roundProgressColor;
    }

    public void setCricleProgressColor(int cricleProgressColor) {
        this.roundProgressColor = cricleProgressColor;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return this.textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public float getRoundWidth() {
        return this.roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

    public void setRoundProgressColor(int roundProgressColor) {
        this.roundProgressColor = roundProgressColor;
    }


    public static interface RoundProgressCallback {
        void onProgress(int var1);

        void onFinish();
    }
}

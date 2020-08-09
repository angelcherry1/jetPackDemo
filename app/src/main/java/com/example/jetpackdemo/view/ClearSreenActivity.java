package com.example.jetpackdemo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.jetpackdemo.R;

public class ClearSreenActivity extends AppCompatActivity {
private MyView myView;
private RelativeLayout my_rl;
private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_sreen);
        myView=  findViewById(R.id.my_view);
        my_rl=  findViewById(R.id.my_rl);
        btn=  findViewById(R.id.btn);
        myView.setTag(100); //打上标记


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"我是点击事件",Toast.LENGTH_LONG).show();
            }
        });

        myView.setListener(new MyViewListener() {
            @Override
            public void isDissmiss(Boolean isDissmiss) {
                Log.i("测试","是否隐藏"+isDissmiss);
                disMissMyView();
            }
        });
    }
private int downX;//手指按下的x坐标
private int downY;//手指按下的Y坐标
private int moveX;//手指移动的x坐标
private int moveY;//手指移动的Y坐标
private int upX;//手指离开的x坐标
private int upY;//手指离开的Y坐标
    //屏幕触摸事件
    private Boolean isShow=true;
    private Boolean isDissmiss=false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int sreenX = (int) event.getRawX();
        int sreenY = (int) event.getRawY();
        int lastX  ;
        int lastY;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX  = (int)event.getX();
                downY  = (int)event.getY();
                lastX  = downX;
                lastY  =downY;

                Log.i("测试","按下的X坐标"+(downX)+"按下的Y坐标"+(downY));
                break;
            case MotionEvent.ACTION_MOVE:
                moveX=(int)event.getX();
                moveY=(int)event.getY();


//                Log.d("xinwa", "事件为" + event.getAction() + ",View距离左边的位置为" + getLeft() + ",X轴的偏移量为" + offsetX + ",移动的过程中相对父View的X坐标为"+x);

                Log.i("测试","拖动的X距离"+(moveX-downX)+"拖动的Y距离"+(downY-moveY));
                break;
            case MotionEvent.ACTION_UP:
                upX=(int)event.getX();
                upY=(int)event.getY();
                Log.i("测试","离开的X坐标"+(upX)+"离开的Y坐标"+(upY));
                if(upX-downX>200&&isShow){
                 disMissMyView();
                }
                if(upX-downX<200&&(!isShow)){
                    showMyView();

                }
            break;
        }
        return super.onTouchEvent(event);
    }

    private void showMyView() {
        Animation animation = AnimationUtils.makeInAnimation(getBaseContext(), false);
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                myView.setVisibility(View.VISIBLE);
//                            my_rl.addView(myView);
                isShow=true;
                Log.i("测试","我添加了东西");
            }

            @Override
            public void onAnimationEnd(Animation animation) {


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        my_rl.startAnimation(animation);
    }

    private void disMissMyView() {
        Animation animation = AnimationUtils.makeOutAnimation(getBaseContext(), true);
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                myView.setVisibility(View.GONE);
                isShow=false;
                Log.i("测试","我移除了东西");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        my_rl.startAnimation(animation);
    }


}

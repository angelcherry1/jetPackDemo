package com.example.jetpackdemo.animationView;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.jetpackdemo.R;

public class AnimationActivity extends AppCompatActivity {

    private SpreadView spreadView;
    private CustomScoreBar csb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_animation);
         spreadView=findViewById(R.id.spreadView);
        csb=findViewById(R.id.csb);
        Button start = findViewById(R.id.start);
        Button stop = findViewById(R.id.stop);
        csb.setScores(20,0);
        handler.sendEmptyMessageDelayed(100,100);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spreadView.setStart();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spreadView.stopAnima();
            }
        });
    }
    private int a=0;
    @SuppressLint("HandlerLeak")
    final Handler handler = new Handler() {

        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 100:
                    if(a<100){
                        a++;
                        csb.setScores(20,a);
                        handler.sendEmptyMessageDelayed(100,100);

                    }

                    break;

            }
        }
    };
}
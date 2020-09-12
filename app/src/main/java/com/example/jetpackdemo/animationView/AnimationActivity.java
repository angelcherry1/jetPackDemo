package com.example.jetpackdemo.animationView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jetpackdemo.R;

public class AnimationActivity extends AppCompatActivity {

    private SpreadView spreadView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_animation);
         spreadView=findViewById(R.id.spreadView);
        Button start = findViewById(R.id.start);
        Button stop = findViewById(R.id.stop);

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
}
package com.example.jetpackdemo.animationView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.jetpackdemo.R;

public class AnimationActivity extends AppCompatActivity {

    private SpreadView spreadView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
         spreadView=findViewById(R.id.spreadView);
    }
}
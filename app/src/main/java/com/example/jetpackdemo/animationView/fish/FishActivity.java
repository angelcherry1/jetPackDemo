package com.example.jetpackdemo.animationView.fish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.jetpackdemo.R;

public class FishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish);
//        ImageView viewById = findViewById(R.id.iv_fish);
//        viewById.setImageDrawable(new FishDrawable());
    }
}
package com.example.jetpackdemo.gifshow;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.gifshow.gif.VrGifView;


public class GifActivity extends AppCompatActivity {
    private VrGifView mGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        mGif = (VrGifView) findViewById(R.id.gif);
        mGif.setTouch(true);
        mGif.setDrag(true);
        mGif.setScale(true);
        mGif.setMoveMode(VrGifView.MODE_FAST);
    }
}

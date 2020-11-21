package com.example.jetpackdemo.pageturn;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.pageturn.factory.PageFactory;
import com.example.jetpackdemo.pageturn.view.CoverPageView;

public class PageTurnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_turn);
        CoverPageView viewById = findViewById(R.id.cpv);

        viewById.setPageFactory(new PageFactory() {
            @Override
            public void drawPreviousBitmap(Bitmap bitmap, int pageNum) {

            }

            @Override
            public void drawCurrentBitmap(Bitmap bitmap, int pageNum) {

            }

            @Override
            public void drawNextBitmap(Bitmap bitmap, int pageNum) {

            }

            @Override
            public Bitmap getBitmapByIndex(int index) {
                return null;
            }
        });
    }
}
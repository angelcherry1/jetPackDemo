package com.example.jetpackdemo.pagerMore.pageturn;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.pagerMore.pageturn.view.BookPageView;

public class PageTurnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_turn);
        BookPageView viewById = findViewById(R.id.cpv);


    }
}
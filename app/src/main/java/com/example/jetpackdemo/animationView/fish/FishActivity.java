package com.example.jetpackdemo.animationView.fish;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.animationView.MainFloatingChatView;

public class FishActivity extends AppCompatActivity {

    private MainFloatingChatView mainFloatingChatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish);
//        ImageView viewById = findViewById(R.id.iv_fish);
//        viewById.setImageDrawable(new FishDrawable());
        mainFloatingChatView = findViewById(R.id.main_chat);
        EditText editText = findViewById(R.id.et_input_text);
        Button btn_play = findViewById(R.id.btn_play);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mainFloatingChatView.setData(s.toString());
            }
        });
        mainFloatingChatView.setData("我是你爸");
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainFloatingChatView.startAnimation();
            }
        });
    }
}
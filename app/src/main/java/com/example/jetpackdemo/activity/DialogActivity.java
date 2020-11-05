package com.example.jetpackdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.base.BaseDialog;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Button btn_dialog = findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBaseDialog();
            }
        });
    }

    private BaseDialog baseDialog;

    private void showBaseDialog() {
        if (baseDialog == null) {
            baseDialog = BaseDialog.newInstance();
        }
        baseDialog.show(getSupportFragmentManager(), BaseDialog.TAG);
    }
}
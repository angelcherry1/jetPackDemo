package com.example.jetpackdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jetpackdemo.liveData.LiveDataActivity;
import com.example.jetpackdemo.navigation.NavigationActivity;
import com.example.jetpackdemo.viewModel.ConstomViewModel;
import com.example.jetpackdemo.viewModel.ConstomViewModelActivity;

public class MainActivity extends AppCompatActivity {
private Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1  =findViewById(R.id.btn1_viewModel);
        btn2  =findViewById(R.id.btn2_liveData);
        btn3   =findViewById(R.id.btn3_navigation);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ConstomViewModelActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), LiveDataActivity.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), NavigationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

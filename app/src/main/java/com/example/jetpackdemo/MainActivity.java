package com.example.jetpackdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jetpackdemo.dataBinding.DataBindActivity;
import com.example.jetpackdemo.liveData.LiveDataActivity;
import com.example.jetpackdemo.navigation.NavigationActivity;
import com.example.jetpackdemo.room.RoomActivity;
import com.example.jetpackdemo.viewModel.ConstomViewModel;
import com.example.jetpackdemo.viewModel.ConstomViewModelActivity;

public class MainActivity extends AppCompatActivity {
private Button btn1,btn2,btn3,btn4,btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1  =findViewById(R.id.btn1_viewModel);
        btn2  =findViewById(R.id.btn2_liveData);
        btn3   =findViewById(R.id.btn3_navigation);
        btn4   =findViewById(R.id.btn4_room);
        btn5  =findViewById(R.id.btn5_dataBinding);
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
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), RoomActivity.class);
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), DataBindActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

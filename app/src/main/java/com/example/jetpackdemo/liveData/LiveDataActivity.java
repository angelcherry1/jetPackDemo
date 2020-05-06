package com.example.jetpackdemo.liveData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.viewModel.ConstomViewModel;

public class LiveDataActivity extends AppCompatActivity {
    private LiveDataViewModel liveDataViewModel;
    private TextView tv1,tv2;
    private Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);

        liveDataViewModel=new ViewModelProvider(this).get(LiveDataViewModel.class);
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);//gfg

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);

        //观察者模式，检测数据的改变观察
        liveDataViewModel.getTickadd1().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tv1.setText(String.valueOf(liveDataViewModel.getTickadd1().getValue()));
            }
        });

        liveDataViewModel.getTickadd2().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tv2.setText(String.valueOf(liveDataViewModel.getTickadd2().getValue()));
            }
        });

        //设置按钮的监听
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接调用viewModel中的方法
                liveDataViewModel.addTickadd1();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liveDataViewModel.addTickadd2();
            }
        });
    }
}

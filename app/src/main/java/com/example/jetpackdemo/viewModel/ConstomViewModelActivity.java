package com.example.jetpackdemo.viewModel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jetpackdemo.R;

import java.util.Timer;
import java.util.TimerTask;

public class ConstomViewModelActivity extends AppCompatActivity {

    private ConstomViewModel constomViewModel;
    private TextView tv1, tv2;
    private Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constom_view_model);
        constomViewModel = new ViewModelProvider(this).get(ConstomViewModel.class);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);//gfg

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        tv1.setText(String.valueOf(constomViewModel.getTickadd1()));
        tv2.setText(String.valueOf(constomViewModel.getTickadd2()));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constomViewModel.setTickadd1(constomViewModel.getTickadd1() + 1);
                tv1.setText(String.valueOf(constomViewModel.getTickadd1()));

            }
        });
        handler.sendEmptyMessage(MSG);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constomViewModel.setTickadd2(constomViewModel.getTickadd2() + 1);
                tv2.setText(String.valueOf(constomViewModel.getTickadd2()));
                System.out.println("我是连击");

                titol=10;
            }
        });
    }
private final static int MSG=0x001;
  private static   int titol=10;
 @SuppressLint("HandlerLeak")
 final    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG:
                    if(titol>0){
                        titol--;
                        System.out.println("每一秒的输出"+titol);
                        handler.sendMessageDelayed(handler.obtainMessage(MSG),1000);
                    }
                    else {
                        System.out.println("我要回收了");
                    }
                    break;
            }


        }
    };

}

package com.example.jetpackdemo.viewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jetpackdemo.R;

public class ConstomViewModelActivity extends AppCompatActivity {

    private  ConstomViewModel constomViewModel;
    private TextView  tv1,tv2;
    private Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constom_view_model);
        constomViewModel=new ViewModelProvider(this).get(ConstomViewModel.class);

        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);//gfg

        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);

        tv1.setText(String.valueOf(constomViewModel.getTickadd1()));
        tv2.setText(String.valueOf(constomViewModel.getTickadd2()));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constomViewModel.setTickadd1(constomViewModel.getTickadd1()+1);
                tv1.setText(String.valueOf(constomViewModel.getTickadd1()));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constomViewModel.setTickadd2(constomViewModel.getTickadd2()+1);
                tv2.setText(String.valueOf(constomViewModel.getTickadd2()));
            }
        });
    }
}

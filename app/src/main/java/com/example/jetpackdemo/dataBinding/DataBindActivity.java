package com.example.jetpackdemo.dataBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import com.example.jetpackdemo.R;
import com.example.jetpackdemo.databinding.ActivityDataBindBinding;

public class DataBindActivity extends AppCompatActivity {
    private DataBindingViewModel dataBindingViewModel;
    private ActivityDataBindBinding dataBindBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBindBinding= DataBindingUtil.setContentView(this,R.layout.activity_data_bind);
        dataBindingViewModel=new ViewModelProvider(this).get(DataBindingViewModel.class);
        dataBindBinding.setData(dataBindingViewModel);
        dataBindBinding.setLifecycleOwner(this);

    }
}

package com.example.jetpackdemo.recycleView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.jetpackdemo.R;
import com.example.jetpackdemo.util.PhoneUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity {

    private RecyclerView rv_recycler_view;
    private BaseQuickAdapter<PhoneDto, BaseViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        rv_recycler_view = findViewById(R.id.rv_recycler_view);

        List<String> lists = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            lists.add(i + "");
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false);
        rv_recycler_view.setLayoutManager(linearLayoutManager);
        mAdapter = new BaseQuickAdapter<PhoneDto, BaseViewHolder>(R.layout.item_recycle_view) {
            @Override
            protected void convert(@NotNull BaseViewHolder viewHolder, PhoneDto s) {
                viewHolder.setText(R.id.tv_num, s.getName() + ":" + s.getTelPhone());
            }
        };
        rv_recycler_view.setAdapter(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new CustomItemochCallBack(mAdapter));
        itemTouchHelper.attachToRecyclerView(rv_recycler_view);
        check();
    }


    /**
     * 检查权限
     */
    private void check() {
        //判断是否有权限
        if (ContextCompat.checkSelfPermission(RecycleViewActivity.this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(RecycleViewActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 201);
        } else {
//            initViews();
            PhoneUtil phoneUtil = new PhoneUtil(this);
            List<PhoneDto> phone = phoneUtil.getPhone();
            mAdapter.setList(phone);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 201) {
//            initViews();
            PhoneUtil phoneUtil = new PhoneUtil(this);
            List<PhoneDto> phone = phoneUtil.getPhone();
            mAdapter.setList(phone);
        } else {
            return;
        }

    }
}
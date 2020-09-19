package com.example.jetpackdemo.recycleView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.jetpackdemo.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity {

    private RecyclerView rv_recycler_view;
    private BaseQuickAdapter<String, BaseViewHolder> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
         rv_recycler_view = findViewById(R.id.rv_recycler_view);

        List<String> lists=new ArrayList<>();
        for(int i=0;i<20;i++){
            lists.add(i+"");
        }

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getBaseContext(),RecyclerView.VERTICAL,false);
        rv_recycler_view.setLayoutManager(linearLayoutManager);
        mAdapter=new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_recycle_view,lists) {
            @Override
            protected void convert(@NotNull BaseViewHolder viewHolder, String s) {
                viewHolder.setText(R.id.tv_num,s);
            }
        };
        rv_recycler_view.setAdapter(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new CustomItemochCallBack(mAdapter));
        itemTouchHelper.attachToRecyclerView(rv_recycler_view);
    }
}
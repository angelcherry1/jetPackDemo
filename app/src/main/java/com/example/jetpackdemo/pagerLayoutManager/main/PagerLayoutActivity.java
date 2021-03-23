package com.example.jetpackdemo.pagerLayoutManager.main;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.pagerLayoutManager.wiget.PagerGridLayoutManager;

public class PagerLayoutActivity extends AppCompatActivity implements PagerGridLayoutManager
        .PageListener, RadioGroup.OnCheckedChangeListener {
    private int mRows = 2;
    private int mColumns = 3;
    private RecyclerView mRecyclerView;
    private PagerGridLayoutManager mLayoutManager;
    private RadioGroup mRadioGroup;
    private TextView mPageTotal;        // 总页数
    private TextView mPageCurrent;      // 当前页数

    private int mTotal = 0;
    private int mCurrent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_layout);


    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }

    @Override
    public void onPageSizeChanged(int pageSize) {

    }

    @Override
    public void onPageSelect(int pageIndex) {

    }
}
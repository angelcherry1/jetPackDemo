package com.example.jetpackdemo.pagerMore.viewPager2AndViewpager2;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.jetpackdemo.R;

public class ViewPager2Activity extends AppCompatActivity {

    private TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager2);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return ViewPager2Fragment.newInstance("1", position);
            }

            @Override
            public int getItemCount() {
                return 3;
            }
        });
//        viewPager2.setUserInputEnabled(false);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        tv1.setTextColor(Color.parseColor("#000000"));
                        tv2.setTextColor(Color.parseColor("#cccccc"));
                        tv3.setTextColor(Color.parseColor("#cccccc"));
                        break;
                    case 1:
                        tv1.setTextColor(Color.parseColor("#cccccc"));
                        tv2.setTextColor(Color.parseColor("#000000"));
                        tv3.setTextColor(Color.parseColor("#cccccc"));
                        break;
                    case 2:
                        tv1.setTextColor(Color.parseColor("#cccccc"));
                        tv2.setTextColor(Color.parseColor("#cccccc"));
                        tv3.setTextColor(Color.parseColor("#000000"));
                        break;
                }
            }
        });
    }
}
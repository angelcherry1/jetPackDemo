package com.example.jetpackdemo.pagerMore.viewPager;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.HorizontalScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.pagerMore.viewPager.indector.CommonNavigator;
import com.example.jetpackdemo.pagerMore.viewPager.indector.CommonNavigatorAdapter;
import com.example.jetpackdemo.pagerMore.viewPager.indector.LinePagerIndicator;
import com.example.jetpackdemo.pagerMore.viewPager.indector.MagicIndicator;
import com.example.jetpackdemo.pagerMore.viewPager.indector.ViewPagerHelper;
import com.example.jetpackdemo.pagerMore.viewPager.indector.interfaceIndector.IPagerIndicator;
import com.example.jetpackdemo.pagerMore.viewPager.indector.interfaceIndector.IPagerTitleView;
import com.example.jetpackdemo.pagerMore.viewPager.indector.title.ColorFlipPagerTitleView;
import com.example.jetpackdemo.pagerMore.viewPager.indector.title.SimplePagerTitleView;
import com.example.jetpackdemo.pagerMore.viewPager.indector.uitils.UIUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private HorizontalScrollView hsv;
    private ViewPager vp;

    private List<Fragment> mFragments = new ArrayList<>();
    private static final String[] CHANNELS = new String[]{"标题1", "标题2", "标题1", "标题2", "标题1", "标题2", "标题1", "标题2", "标题1", "标题2"};
    private List<String> mDataList = Arrays.asList(CHANNELS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        hsv = findViewById(R.id.hsv);
        vp = findViewById(R.id.vp);
        for (int i = 0; i < 10; i++) {
            mFragments.add(ViewItemFragment.newInstance(i));
        }
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
        initMagicIndicator7();
    }

    private void initMagicIndicator7() {
        MagicIndicator magicIndicator = findViewById(R.id.magic_indicator7);
        magicIndicator.setBackgroundColor(Color.parseColor("#fafafa"));
        CommonNavigator commonNavigator7 = new CommonNavigator(this);
        commonNavigator7.setScrollPivotX(0.65f);
        commonNavigator7.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(Color.parseColor("#9e9e9e"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#00c853"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vp.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setLineHeight(UIUtil.dip2px(context, 6));
                indicator.setLineWidth(UIUtil.dip2px(context, 10));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
                indicator.setColors(Color.parseColor("#00c853"));


                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator7);
        ViewPagerHelper.bind(magicIndicator, vp);
    }
}
package com.example.jetpackdemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jetpackdemo.activity.AppBarLayoutActivity;
import com.example.jetpackdemo.activity.DialogActivity;
import com.example.jetpackdemo.animationView.AnimationActivity;
import com.example.jetpackdemo.animationView.SnowActivity;
import com.example.jetpackdemo.conslayout.LayoutActivity;
import com.example.jetpackdemo.dataBinding.DataBindActivity;
import com.example.jetpackdemo.gifshow.My3dShowActivity;
import com.example.jetpackdemo.liveData.LiveDataActivity;
import com.example.jetpackdemo.model.City;
import com.example.jetpackdemo.myGlide.BlureActivity;
import com.example.jetpackdemo.navigation.NavigationActivity;
import com.example.jetpackdemo.pagerMore.pageturn.PageTurnActivity;
import com.example.jetpackdemo.pagerMore.viewPager.ViewPagerActivity;
import com.example.jetpackdemo.pagerMore.viewPager2AndViewpager2.ViewPager2Activity;
import com.example.jetpackdemo.pagerMore.viewPagerCurl.CurlActivity;
import com.example.jetpackdemo.recycleView.RecycleViewActivity;
import com.example.jetpackdemo.room.RoomActivity;
import com.example.jetpackdemo.view.ClearSreenActivity;
import com.example.jetpackdemo.viewModel.ConstomViewModelActivity;
import com.example.jetpackdemo.webAndJs.WebActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn_paixu, btn6_anima, btn6_cos, btn6_shou, btn6_rec,
            btn6_dialog, btn6_view_pager, btn6_book_pager, btn7_3d_vr,
            btn6_book_pager_curl, btn6_web, btn7_my_view_pager2, btn7_glide, btn7_snow;
    private Button btn7_snow1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1_viewModel);
        btn2 = findViewById(R.id.btn2_liveData);
        btn3 = findViewById(R.id.btn3_navigation);
        btn4 = findViewById(R.id.btn4_room);
        btn5 = findViewById(R.id.btn5_dataBinding);
        btn6 = findViewById(R.id.btn6_dataBinding);
        btn6_shou = findViewById(R.id.btn6_shou);
        btn_paixu = findViewById(R.id.btn_paixu);
        btn6_anima = findViewById(R.id.btn6_anima);
        btn6_cos = findViewById(R.id.btn6_cos);
        btn6_rec = findViewById(R.id.btn6_rec);
        btn6_dialog = findViewById(R.id.btn6_dialog);
        btn6_view_pager = findViewById(R.id.btn6_view_pager);
        btn6_book_pager = findViewById(R.id.btn6_book_pager);
        btn6_book_pager_curl = findViewById(R.id.btn6_book_pager_curl);
        btn6_web = findViewById(R.id.btn6_web);
        btn7_my_view_pager2 = findViewById(R.id.btn7_my_view_pager2);
        btn7_glide = findViewById(R.id.btn7_glide);
        btn7_3d_vr = findViewById(R.id.btn7_3d_vr);
        btn7_snow = findViewById(R.id.btn7_snow);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConstomViewModelActivity.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LiveDataActivity.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DataBindActivity.class);
                startActivity(intent);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ClearSreenActivity.class);
                startActivity(intent);
            }
        });
        btn6_anima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AnimationActivity.class);
                startActivity(intent);
            }
        });
        btn6_cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LayoutActivity.class);
                startActivity(intent);
            }
        });

        btn6_shou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AppBarLayoutActivity.class);
                startActivity(intent);
            }
        });

        btn6_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecycleViewActivity.class);
                startActivity(intent);
            }
        });

        btn6_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DialogActivity.class);
                startActivity(intent);
            }
        });
        btn_paixu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                SpannableStringBuilder spannableStringBuilder = setEggInformation("兮兮", "女神来了", "火箭x1");
                btn_paixu.setText(spannableStringBuilder);
            }
        });
        btn6_view_pager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewPagerActivity.class);
                startActivity(intent);
            }
        });
        btn6_book_pager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PageTurnActivity.class);
                startActivity(intent);
            }
        });

        btn6_book_pager_curl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CurlActivity.class);
                startActivity(intent);
            }
        });
        btn6_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebActivity.class);
                startActivity(intent);
            }
        });
        btn7_my_view_pager2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewPager2Activity.class);
                startActivity(intent);
            }
        });
        btn7_glide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BlureActivity.class);
                startActivity(intent);
            }
        });
        btn7_3d_vr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), My3dShowActivity.class);
                startActivity(intent);
            }
        });

        btn7_snow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SnowActivity.class);
                startActivity(intent);
            }
        });
    }


    private List<Integer> getStringNumAndIndex(String string) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < string.length() - "年求".length() + 1; i++) {
            String substring = string.substring(i, i + "年求".length());
            if (substring.equals("年求")) {
                list.add(i);
            }
        }
        Log.i("我是测试字符串个数和位置的", list.toString());
        return list;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void getJsonData(Context context) {
        City city = new Gson().fromJson(
                loadAddressInfoFromAssets(context).trim(),
                new TypeToken<City>() {
                }.getType());

        List<String> mycity = city.getCities();
        Collections.sort(mycity, Collator.getInstance(java.util.Locale.CHINA));
        String string = getString(getBaseContext());
        Log.i("测试", "获取的数据" + string);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String loadAddressInfoFromAssets(Context context) {

        StringBuffer addressInfoBuffer = new StringBuffer();

        try {
            InputStream inputStream = context.getResources().getAssets().open("cityConfig.json");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String info = "";
            while ((info = bufferedReader.readLine()) != null) {
//                Log.i("fff", info);
                addressInfoBuffer.append(info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return addressInfoBuffer.toString();
    }

    /**
     * 通过一个InputStream获取内容
     *
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String getString(Context context) {
        StringBuffer addressInfoBuffer = new StringBuffer();

        try {
            InputStream inputStream = context.getResources().getAssets().open("BaiduMap_cityCode_1102.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String info = "";
            while ((info = bufferedReader.readLine()) != null) {
//                Log.i("fff", info);
                addressInfoBuffer.append(info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return addressInfoBuffer.toString();
    }

    public SpannableStringBuilder setEggInformation(String userName, String roomName, String giftText) {
        String txt = "恭喜" + userName + "在" + roomName + "的房间中砸出了" + giftText;
        SpannableStringBuilder ss = new SpannableStringBuilder(txt);
        ss.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), txt.indexOf(userName), txt.indexOf(userName) + userName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.parseColor("#FFFC00")), txt.indexOf(userName), txt.indexOf(userName) + userName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), txt.indexOf(roomName), txt.indexOf(roomName) + userName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.parseColor("#07FFEC")), txt.indexOf(roomName), txt.indexOf(roomName) + roomName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), txt.indexOf(giftText), txt.indexOf(giftText) + giftText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.parseColor("#3FFF6C")), txt.indexOf(giftText), txt.indexOf(giftText) + giftText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    private void Spannable() {
        List<Integer> stringNumAndIndex = getStringNumAndIndex("我们一起新年快乐啊,新年的年求");
        SpannableStringBuilder ss = new SpannableStringBuilder("我们一起新年快乐啊,新年的年求");
        for (int i = 0; i < stringNumAndIndex.size(); i++) {
            SpannableStringBuilder nian = new SpannableStringBuilder("年求");
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#FF0000"));
            nian.setSpan(foregroundColorSpan, 0, "年求".length(), SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
            ss.replace(stringNumAndIndex.get(i), stringNumAndIndex.get(i) + "年求".length(), nian);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

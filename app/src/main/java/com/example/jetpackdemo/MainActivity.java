package com.example.jetpackdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jetpackdemo.animationView.AnimationActivity;
import com.example.jetpackdemo.conslayout.LayoutActivity;
import com.example.jetpackdemo.dataBinding.DataBindActivity;
import com.example.jetpackdemo.liveData.LiveDataActivity;
import com.example.jetpackdemo.model.City;
import com.example.jetpackdemo.navigation.NavigationActivity;
import com.example.jetpackdemo.room.RoomActivity;
import com.example.jetpackdemo.view.ClearSreenActivity;
import com.example.jetpackdemo.viewModel.ConstomViewModelActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.Collator;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn_paixu,btn6_anima,btn6_cos;

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
        btn_paixu = findViewById(R.id.btn_paixu);
        btn6_anima = findViewById(R.id.btn6_anima);
        btn6_cos = findViewById(R.id.btn6_cos);
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
        btn_paixu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                City city = new Gson().fromJson(
                        loadAddressInfoFromAssets(getBaseContext()).trim(),
                        new TypeToken<City>() {
                        }.getType());

                List<String> mycity = city.getCities();
                Collections.sort(mycity, Collator.getInstance(java.util.Locale.CHINA));
                String string = getString(getBaseContext());
                Log.i("测试", "获取的数据" + string);
            }
        });
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
    public  String getString(Context context ) {
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

//    public void writeJson(List<String> citys) {
//        File baiducCity = new File("baiducCity");
//        try {
//            FileWriter fileWriter = new FileWriter(baiducCity);
//            for()
//            fileWriter.write();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

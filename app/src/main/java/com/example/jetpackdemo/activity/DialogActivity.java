package com.example.jetpackdemo.activity;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.base.BaseDialog;

import java.util.Objects;

public class DialogActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Button btn_dialog = findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void checkDrawOverlayPermission() {
        if (!Settings.canDrawOverlays(this)) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (Settings.canDrawOverlays(this)) {
                // continue here - permission was granted
                if (baseDialog == null) {
                    baseDialog = BaseDialog.newInstance();
                    Window window = Objects.requireNonNull(baseDialog.getDialog()).getWindow();
                    window.setType(WindowManager.LayoutParams.TYPE_STATUS_BAR);
                }
                baseDialog.show(getSupportFragmentManager(), BaseDialog.TAG);
            }
        }
    }

    private BaseDialog baseDialog;

    private void showBaseDialog() {

        checkDrawOverlayPermission();

        if (baseDialog == null) {
            baseDialog = BaseDialog.newInstance();
            Window window = Objects.requireNonNull(baseDialog.getDialog()).getWindow();
            window.setType(WindowManager.LayoutParams.TYPE_STATUS_BAR);
        }
        baseDialog.show(getSupportFragmentManager(), BaseDialog.TAG);
    }

    private void showAlertDialog() {
        Toast.makeText(this, "我是toast提示弹窗", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle("我是AlertDialog").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showBaseDialog();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        showBaseDialog();
    }
}
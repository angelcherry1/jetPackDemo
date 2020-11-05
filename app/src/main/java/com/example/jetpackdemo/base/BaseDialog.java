package com.example.jetpackdemo.base;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.jetpackdemo.R;

import java.util.Objects;


public class BaseDialog extends DialogFragment {
    public final static String TAG = "BaseDialog";

    public static BaseDialog newInstance() {
        return new BaseDialog();
    }

    private BaseDialog() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = View.inflate(requireContext(), R.layout.activity_dialog_dialog, null);
        return new AlertDialog.Builder(requireActivity())
                .setView(view)
                .create();
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onStart() {
        super.onStart();
        Window window = Objects.requireNonNull(getDialog()).getWindow();
        assert window != null;
        //设置窗口动画
//        window.setWindowAnimations(R.style.dialog_txt_animation);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams attr = window.getAttributes();
        if (attr != null) {
            attr.height = 500;
            attr.width = 500;
            attr.dimAmount = 0.4f;
            attr.gravity = Gravity.CENTER;//设置dialog 在布局底部的位置
            window.setAttributes(attr);
        }
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        if (manager.findFragmentByTag(TAG) == null) {
            super.show(manager, tag);
        }
    }
}

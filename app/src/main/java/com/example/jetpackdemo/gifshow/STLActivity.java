package com.example.jetpackdemo.gifshow;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jetpackdemo.R;
import com.example.jetpackdemo.gifshow.widget.stlview.callback.OnReadCallBack;
import com.example.jetpackdemo.gifshow.widget.stlview.widget.STLView;
import com.example.jetpackdemo.gifshow.widget.stlview.widget.STLViewBuilder;


public class STLActivity extends AppCompatActivity {
    private STLView mStl;
    private Context mContext;
    private ProgressDialog mBar;
    private Bundle bundle = new Bundle();
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            float cur = bundle.getFloat("cur");
            float total = bundle.getFloat("total");
            float progress = cur / total;
            Log.i("Progress", progress + "");
            mBar.setProgress((int) (progress * 100.0f));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stl);
        mContext = this;
        mStl = (STLView) findViewById(R.id.stl);
        mBar = prepareProgressDialog(mContext);
        mStl.setOnReadCallBack(new OnReadCallBack() {
            @Override
            public void onStart() {
                Toast.makeText(mContext, "开始解析!", Toast.LENGTH_LONG).show();
                mBar.show();
            }

            @Override
            public void onReading(int cur, int total) {
                bundle.putFloat("cur", cur);
                bundle.putFloat("total", total);
                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);
            }

            @Override
            public void onFinish() {
                mBar.dismiss();
                mStl.requestRedraw();
            }
        });
        STLViewBuilder.init(mStl).Assets(this, "BelleBook_Big.stl").build();
        mStl.setTouch(true);
        mStl.setScale(true);
        mStl.setRotate(true);
        mStl.setSensor(true);

    }

    private ProgressDialog prepareProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(R.string.stl_load_progress_title);
        progressDialog.setMax(100);
        progressDialog.setMessage(context.getString(R.string.stl_load_progress_message));
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        return progressDialog;
    }
}

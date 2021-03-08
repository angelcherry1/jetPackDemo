package com.example.jetpackdemo.gifshow.widget.stlview.callback;


import com.example.jetpackdemo.gifshow.widget.stlview.model.STLModel;

public interface OnReadListener {
    void onstart();

    void onLoading(int cur, int total);

    void onFinished(STLModel model);

    void onFailure(Exception e);
}
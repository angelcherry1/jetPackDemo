package com.example.jetpackdemo.gifshow.widget.stlview.operate;


import com.example.jetpackdemo.gifshow.widget.stlview.callback.OnReadListener;
import com.example.jetpackdemo.gifshow.widget.stlview.model.STLModel;

/**
 * Author : xuan.
 * Date : 2017/12/10.
 * Description : interface of stlreader
 */

public interface ISTLReader {
    public STLModel parserBinStl(byte[] bytes);

    public STLModel parserAsciiStl(byte[] bytes);

    public void setCallBack(OnReadListener listener);
}

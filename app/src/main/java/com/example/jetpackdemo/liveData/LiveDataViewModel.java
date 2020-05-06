package com.example.jetpackdemo.liveData;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author:lixiaobiao
 * @date:On 2020/5/6
 * @Desriptiong: 23231
 */
public class LiveDataViewModel extends ViewModel {
    private MutableLiveData<Integer> tickadd1,tickadd2;
    //创建get方法
    public MutableLiveData<Integer> getTickadd1() {
        if (tickadd1 == null) {
            tickadd1 = new MutableLiveData<Integer>();
            tickadd1.setValue(0);//设置初始值
        }
        return tickadd1;
    }

    //创建get方法
    public MutableLiveData<Integer> getTickadd2() {
        if (tickadd2 == null) {
            tickadd2 = new MutableLiveData<Integer>();
            tickadd2.setValue(0);//设置初始值
        }
        return tickadd2;
    }
    //创建票数加1的方法

    public void addTickadd1(){
        if(tickadd1.getValue()!=null){tickadd1.setValue(tickadd1.getValue()+1);}
    }
    public void addTickadd2(){
        if(tickadd2.getValue()!=null){tickadd2.setValue(tickadd2.getValue()+1);}
    }

}

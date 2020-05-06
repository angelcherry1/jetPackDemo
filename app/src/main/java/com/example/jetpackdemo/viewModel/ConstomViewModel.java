package com.example.jetpackdemo.viewModel;

import androidx.lifecycle.ViewModel;

/**
 * @author:lixiaobiao
 * @date:On 2020/5/5
 * @Desriptiong: 23231
 */
public class ConstomViewModel extends ViewModel {
    private int tickadd1=0;
    private int tickadd2=0;

    public int getTickadd1() {
        return tickadd1;
    }

    public void setTickadd1(int tickadd1) {
        this.tickadd1 = tickadd1;
    }

    public int getTickadd2() {
        return tickadd2;
    }

    public void setTickadd2(int tickadd2) {
        this.tickadd2 = tickadd2;
    }
}

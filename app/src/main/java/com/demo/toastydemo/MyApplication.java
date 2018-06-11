package com.demo.toastydemo;

import android.app.Application;

import com.liudengjian.toasty.ToastyConfig;

/**
 * Created by 刘登建 on 2018/5/10.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ToastyConfig.getInstance().initApplication(this);
    }
}

package com.demo.toastydemo;

import android.content.Context;
import android.widget.Toast;

import com.liudengjian.toasty.IToastyView;

/**
 * 自定义的吐司显示控件
 * Created by 刘登建 on 2018/5/11.
 */

public class ItemToastyView implements IToastyView{
    private Toast toast;

    @Override
    public Toast createToast(Context context, String text) {
        toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
        return toast;
    }

    @Override
    public void setText(String title) {
        toast.setText(title);
    }

    @Override
    public Toast getToast() {
        return toast;
    }

    @Override
    public void setType(Context context, int type) {

    }
}

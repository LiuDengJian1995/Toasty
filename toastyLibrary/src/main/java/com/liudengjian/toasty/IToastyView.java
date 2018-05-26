package com.liudengjian.toasty;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.Toast;

/**
 * Created by hp on 2018/5/11.
 */

public interface IToastyView {
    int DEFAULT = 0;    //默认
    int SUCCESS = 1;    //成功
    int ERROR = 2;      //错误
    int INFO = 3;       //提示

    /**
     * 创建吐司
     */
    Toast createToast(Context context, String text);

    /**
     * 设置吐司内容
     */
    void setText(String title);

    /**
     * 获取吐司
     */
    Toast getToast();

    /**设置吐司信息类型（DEFAULT：默认、SUCCESS：成功、ERROR：错误、INFO：提示）*/
    void setType(Context context, int type);
}
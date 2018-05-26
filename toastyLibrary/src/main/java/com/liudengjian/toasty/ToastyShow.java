package com.liudengjian.toasty;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by 刘登建 on 2018/5/10.
 */

public class ToastyShow {

    //无图片的吐司(长时间显示)
    public static void makeTextLong(String text) {
        makeText(ToastyConfig.mApplication, text, Toasty.LENGTH_LONG);
    }

    //无图片的吐司(短时间显示)
    public static void makeTextShort(String text) {
        makeText(ToastyConfig.mApplication, text, Toasty.LENGTH_SHORT);
    }

    //无图片的吐司(长时间显示)
    public static void makeTextLong(Context context, String text) {
        makeText(context, text, Toasty.LENGTH_LONG);
    }

    //无图片的吐司(短时间显示)
    public static void makeTextShort(Context context, String text) {
        makeText(context, text, Toasty.LENGTH_SHORT);
    }

    /**
     * 无图片的吐司
     *
     * @param context 上下文
     * @param text    吐司内容
     * @param delay   吐司时间
     */
    public static void makeText(Context context, String text, int delay) {
        showToaty(context, text, delay, ToastyView.DEFAULT);
    }

    //有错误图片的吐司(长时间显示)
    public static void errorLong(String text) {
        error(ToastyConfig.mApplication, text, Toasty.LENGTH_LONG);
    }

    //有错误图片的吐司(短时间显示)
    public static void errorShort(String text) {
        error(ToastyConfig.mApplication, text, Toasty.LENGTH_SHORT);
    }

    //有错误图片的吐司(长时间显示)
    public static void errorLong(Context context, String text) {
        error(context, text, Toasty.LENGTH_LONG);
    }

    //有错误图片的吐司(短时间显示)
    public static void errorShort(Context context, String text) {
        error(context, text, Toasty.LENGTH_SHORT);
    }

    /**
     * 有错误图片的吐司
     */
    public static void error(Context context, String text, int delay) {
        showToaty(context, text, delay, ToastyView.ERROR);
    }

    //有成功图片的吐司(长时间显示)
    public static void successLong(String text) {
        success(ToastyConfig.mApplication, text, Toasty.LENGTH_LONG);
    }

    //有成功图片的吐司(短时间显示)
    public static void successShort(String text) {
        success(ToastyConfig.mApplication, text, Toasty.LENGTH_SHORT);
    }

    //有成功图片的吐司(长时间显示)
    public static void successLong(Context context, String text) {
        success(context, text, Toasty.LENGTH_LONG);
    }

    //有成功图片的吐司(短时间显示)
    public static void successShort(Context context, String text) {
        success(context, text, Toasty.LENGTH_SHORT);
    }

    /**
     * 有成功图片的吐司
     */
    public static void success(Context context, String text, int delay) {
        showToaty(context, text, delay, ToastyView.SUCCESS);
    }

    //有提示信息图片的吐司(长时间显示)
    public static void infoLong(String text) {
        info(ToastyConfig.mApplication, text, Toasty.LENGTH_LONG);
    }

    //有提示信息图片的吐司(短时间显示)
    public static void infoShort(String text) {
        info(ToastyConfig.mApplication, text, Toasty.LENGTH_SHORT);
    }

    //有提示信息图片的吐司(长时间显示)
    public static void infoLong(Context context, String text) {
        info(context, text, Toasty.LENGTH_LONG);
    }

    //有提示信息图片的吐司(短时间显示)
    public static void infoShort(Context context, String text) {
        info(context, text, Toasty.LENGTH_SHORT);
    }


    /**
     * 有提示信息图片的吐司
     */
    public static void info(Context context, String text, int delay) {
        showToaty(context, text, delay, ToastyView.INFO);
    }

    //无图片的吐司(长时间显示)
    public static void normalLong(String text) {
        normal(ToastyConfig.mApplication, text, Toasty.LENGTH_LONG);
    }

    //无图片的吐司(短时间显示)
    public static void normalShort(String text) {
        normal(ToastyConfig.mApplication, text, Toasty.LENGTH_SHORT);
    }

    //无图片的吐司(长时间显示)
    public static void normalLong(Context context, String text) {
        normal(context, text, Toasty.LENGTH_LONG);
    }

    //无图片的吐司(短时间显示)
    public static void normalShort(Context context, String text) {
        normal(context, text, Toasty.LENGTH_SHORT);
    }

    /**
     * 无图片的吐司
     */
    public static void normal(Context context, String text, int delay) {
        showToaty(context, text, delay, ToastyView.DEFAULT);
    }


    /**
     * 显示吐司
     *
     * @param context 上下文
     * @param text    吐司内容
     * @param delay   吐司时间
     * @param type    吐司的图片类型
     */
    private static void showToaty(Context context, String text, int delay, int type) {
        if (context == null) {
            if (ToastyConfig.mApplication == null) {
                throw new NullPointerException(ToastyConfig.CONTEXT_ERROR_MSG);
            }
            context = ToastyConfig.mApplication.getApplicationContext();
        }
        if (TextUtils.isEmpty(text)) {
            return;
        }
        Toasty.makeText(context, text, delay, type).show();
    }


}

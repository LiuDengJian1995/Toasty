package com.liudengjian.toasty;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;

/**
 * Created by 刘登建 on 2018/5/11.
 */

public class ToastyConfig {

    //影子实例（https://www.cnblogs.com/woshimrf/p/5223315.html）
    private volatile static ToastyConfig instance = null;

    //使用影子实例获取全局回调管理
    public static ToastyConfig getInstance() {
        if (instance == null) {
            synchronized (ToastyConfig.class) {
                if (instance == null)
                    instance = new ToastyConfig();
            }
        }
        return instance;
    }

    //全局上下文
    static Application mApplication = null;

    //上下文为空的时候的NullPointerException信息
    public static String CONTEXT_ERROR_MSG = "请传入正确的上下文，" +
            "或者在Application中初始化initApplication方法";

    /**
     * 吐司的显示控件
     */
    private static IToastyView mToastyView = null;

    //mToastyView的值是否变化
    static boolean isToastyViewChange = false;

    //吐司的位置
    public static int GRAVITY = Gravity.BOTTOM;
    public static int xOffset = 0;
    public static int yOffset = 0;

    //当通知权限没有打开时是否强制跳转应用管理页面打开
    static boolean isForceOpen = false;

    //当通知权限没有打开时上下文为Activity或Fragment时
    // 弹出对话框提示用户是否强制跳转应用管理页面打开
    static boolean hintForceOpen = true;

    //弹出对话框
    private Dialog mDialog = null;

    //在Application中初始化，得到Application的上下文
    public ToastyConfig initApplication(Application application) {
        mApplication = application;
        return this;
    }

    //修改吐司的显示控件
    public ToastyConfig setToastyView(IToastyView mToastyView) {
        if (ToastyConfig.mToastyView == null && mToastyView == null) {
            isToastyViewChange = false;
        } else {
            isToastyViewChange = true;
        }
        ToastyConfig.mToastyView = mToastyView;
        return this;
    }

    //获取吐司的显示控件
    public static IToastyView getToastyView() {
        return mToastyView;
    }

    //设置吐司的位置
    public ToastyConfig setGravity(Context context, int gravity) {
        GRAVITY = gravity;
        switch (GRAVITY) {
            case Gravity.CENTER:
                xOffset = 0;
                yOffset = 0;
                break;
            case Gravity.TOP:
                xOffset = 0;
                yOffset = ToastyUtils.dip2px(context, 75);
                break;
            default:
                xOffset = 0;
                yOffset = ToastyUtils.dip2px(context, 55);
                break;
        }
        return this;
    }

    //当通知权限没有打开时是否强制跳转应用管理页面打开
    public ToastyConfig setForceOpen(boolean isForceOpen) {
        ToastyConfig.isForceOpen = isForceOpen;
        return this;
    }

    //当通知权限没有打开时上下文为Activity或Fragment时
    // 弹出对话框提示用户是否强制跳转应用管理页面打开
    //isForceOpen和hintForceOpen都为true时，hintForceOpen优化于isForceOpen
    //hintForceOpen为true时，上下文不为Activity或Fragment时按isForceOpen的逻辑进行
    public ToastyConfig setHintForceOpen(boolean hintForceOpen) {
        ToastyConfig.hintForceOpen = hintForceOpen;
        return this;
    }

    public Dialog getDialog(final Context context) {
        if (mDialog == null){
            mDialog =  new AlertDialog.Builder(context)
                    .setTitle("温馨提示")
                    .setMessage("您没有开启通知权限，需要开启通知权限才可以弹出通知信息")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ToastyUtils.openNotificationsEnabled(context);
                        }
                    })
                    .create();
        }
        return mDialog;
    }

    public void setDialog(Dialog dialog) {
        this.mDialog = mDialog;
    }
}

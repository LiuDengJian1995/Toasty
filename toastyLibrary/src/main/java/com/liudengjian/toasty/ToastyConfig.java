package com.liudengjian.toasty;

import android.app.Application;
import android.content.Context;
import android.view.Gravity;

/**
 * Created by 刘登建 on 2018/5/11.
 */

public class ToastyConfig {

    static Application mApplication = null;

    public static String CONTEXT_ERROR_MSG = "请传入正确的上下文，" +
            "或者在Application中初始化initApplication方法";

    //在Application中初始化，得到Application的上下文
    public static void initApplication(Application application) {
        mApplication = application;
    }

    /**
     * 吐司的显示控件
     */
    private static IToastyView mToastyView = null;

    //mToastyView的值是否变化
    public static boolean isToastyViewChange = false;

    public static void setToastyView(IToastyView mToastyView) {
        if (ToastyConfig.mToastyView == null && mToastyView == null) {
            isToastyViewChange = false;
        } else {
            isToastyViewChange = true;
        }
        ToastyConfig.mToastyView = mToastyView;
    }

    public static IToastyView getToastyView() {
        return mToastyView;
    }

    //吐司的位置
    public static int GRAVITY = Gravity.BOTTOM;
    public static int xOffset = 0;
    public static int yOffset = 0;

    //设置吐司的位置
    public static void setGravity(Context context, int gravity) {
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
    }


}

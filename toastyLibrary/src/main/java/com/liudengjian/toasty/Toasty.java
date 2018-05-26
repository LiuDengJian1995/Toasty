package com.liudengjian.toasty;

/**
 * Created by 刘登建 on 2018/5/9.
 */


import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Toasty {

    private Handler handler;

    public static int LENGTH_SHORT = android.widget.Toast.LENGTH_SHORT;
    public static int LENGTH_LONG = android.widget.Toast.LENGTH_LONG;

    private Context mContext;

    private Object mObj;
    private Method showMethod, hideMethod;
    private IToastyView mToastyView;

    private String text = "";
    private int time = 2500;

    private boolean hasReflectException = false;

    private static Toasty instance;

    public static Toasty makeText(Context context, String text, int delay) {
        return makeText(context, text, delay, ToastyView.DEFAULT);
    }

    public static Toasty error(Context context, String text, int delay) {
        return makeText(context, text, delay, ToastyView.ERROR);
    }

    public static Toasty success(Context context, String text, int delay) {
        return makeText(context, text, delay, ToastyView.SUCCESS);
    }

    public static Toasty info(Context context, String text, int delay) {
        return makeText(context, text, delay, ToastyView.INFO);
    }

    public static Toasty normal(Context context, String text, int delay) {
        return makeText(context, text, delay, ToastyView.DEFAULT);
    }

    public static Toasty makeText(Context context, String text, int delay, int type) {
        if (ToastyConfig.isToastyViewChange) {
            instance = null;
            ToastyConfig.isToastyViewChange = false;
        }
        if (instance == null) {
            instance = new Toasty(context);
        }
        instance.setDuration(delay);
        instance.setText(text);

        if (instance.mToastyView != null) {
            instance.mToastyView.setType(context, type);
        }
        instance.setShowGravity(context);
        return instance;
    }

    private Toasty(Context context) {
        if (context == null || context.getApplicationContext() == null) {
            throw new NullPointerException("context can't be null");
        }
        this.mContext = context;
        initTN();
        if (handler == null) {
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    Toasty.this.hideToast();
                }
            };
        }
    }

    public void setText(String text) {
        this.text = text;
    }

    void setShowGravity(Context context){
        if (ToastyConfig.GRAVITY == Gravity.BOTTOM && ToastyConfig.yOffset == 0) {
            ToastyConfig.setGravity(context, Gravity.BOTTOM);
        }
        if (instance.getToastyView() != null &&
                instance.getToastyView().getToast() != null) {
            instance.getToastyView().getToast().setGravity(
                    ToastyConfig.GRAVITY, ToastyConfig.xOffset, ToastyConfig.yOffset);
        }
    }

    public void setText(int resId) {
        setText(mContext.getText(resId).toString());
    }

    public void setDuration(int t) {
        if (t == android.widget.Toast.LENGTH_SHORT) {
            this.time = 2000;
        } else if (t == android.widget.Toast.LENGTH_LONG) {
            this.time = 3000;
        } else if (t > 1000) {
            this.time = t;
        }
    }

    public final void show() {
        if (!hasReflectException) {
            mToastyView.setText(text);
            if (TextUtils.isEmpty(text)) {
                return;
            }
            showToast();
            handler.removeMessages(1);
            handler.sendEmptyMessageDelayed(1, time);

        }
    }

    private void initTN() {
        Field mTN;
        if (mToastyView == null) {
            if (ToastyConfig.getToastyView() != null) {
                mToastyView = ToastyConfig.getToastyView();
            } else {
                mToastyView = new ToastyView();
            }
            mToastyView.createToast(mContext, text);
        } else if (mToastyView != ToastyConfig.getToastyView()) {
            if (ToastyConfig.getToastyView() != null) {
                mToastyView = ToastyConfig.getToastyView();
            } else {
                mToastyView = new ToastyView();
            }
            mToastyView.createToast(mContext, text);
        }
        Class<android.widget.Toast> clazz = android.widget.Toast.class;
        try {
            mTN = clazz.getDeclaredField("mTN");
            mTN.setAccessible(true);
            mObj = mTN.get(mToastyView.getToast());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                showMethod = mObj.getClass().getDeclaredMethod("show", IBinder.class);
            } else {
                showMethod = mObj.getClass().getDeclaredMethod("show", new Class<?>[0]);
            }
            hideMethod = mObj.getClass().getDeclaredMethod("hide", new Class<?>[0]);
            Field mY = mObj.getClass().getDeclaredField("mY");
            mY.setAccessible(true);
            mY.set(mObj, ToastyUtils.dip2px(mContext, 68F));
            hasReflectException = false;
        } catch (Exception e) {
            hasReflectException = true;
        }
    }

    private void showToast() {
        try {
            Field mNextView = mObj.getClass().getDeclaredField("mNextView");
            mNextView.setAccessible(true);
            mNextView.set(mObj, mToastyView.getToast().getView());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                showMethod.invoke(mObj, new Object[1]);
            } else {
                showMethod.invoke(mObj, new Object[0]);
            }
            hasReflectException = false;
        } catch (Exception e) {
            hasReflectException = true;
        }
    }

    private void hideToast() {
        try {
            hideMethod.invoke(mObj, new Object[0]);
            hasReflectException = false;
        } catch (Exception e) {
            hasReflectException = true;
        }
    }

    public IToastyView getToastyView() {
        return mToastyView;
    }
}
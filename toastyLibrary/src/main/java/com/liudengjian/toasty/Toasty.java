package com.liudengjian.toasty;

/**
 * Created by 刘登建 on 2018/5/9.
 */


import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.Gravity;

public class Toasty {

    public static int LENGTH_SHORT = android.widget.Toast.LENGTH_SHORT;
    public static int LENGTH_LONG = android.widget.Toast.LENGTH_LONG;

    private Context mContext;

    private IToastyView mToastyView;

    private String text = "";

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
        ToastyUtils.checkNotificationsEnabled(context);
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
            //开始执行AsyncTask，并传入某些数据
            instance.new TypeTask().execute(type);
//            instance.mToastyView.setType(context, type);
        }
        instance.setShowGravity(context);
        return instance;
    }

    private class TextTask extends AsyncTask<String,Void,String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            return params[0];
        }
        @Override
        protected void onPostExecute(String result)
        {
            //更新UI的操作，这里面的内容是在UI线程里面执行的
            mToastyView.setText(result);
            showToast();
        }
    }

    private class TypeTask extends AsyncTask<Integer,Void,Integer>
    {
        @Override
        protected Integer doInBackground(Integer... params)
        {
            return params[0];
        }
        @Override
        protected void onPostExecute(Integer result)
        {
            //更新UI的操作，这里面的内容是在UI线程里面执行的
            mToastyView.setType(mContext, result);
        }
    }


    private Toasty(Context context) {
        if (context == null || context.getApplicationContext() == null) {
            throw new NullPointerException("context can't be null");
        }
        this.mContext = context;
        initTN();
    }

    public void setText(String text) {
        this.text = text;
    }

    void setShowGravity(Context context) {
        if (ToastyConfig.GRAVITY == Gravity.BOTTOM && ToastyConfig.yOffset == 0) {
            ToastyConfig.getInstance().setGravity(context, Gravity.BOTTOM);
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
            mToastyView.getToast().setDuration(t);
        } else if (t == android.widget.Toast.LENGTH_LONG) {
            mToastyView.getToast().setDuration(t);
        } else if (t > 1000) {
            mToastyView.getToast().setDuration(android.widget.Toast.LENGTH_LONG);
        }
    }

    public final void show() {
        if (!hasReflectException) {
            if (TextUtils.isEmpty(text)) {
                return;
            }
            new TextTask().execute(text);

        }
    }

    private void initTN() {
        try {
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
            hasReflectException = false;
        } catch (Exception e) {
            hasReflectException = true;
        }
    }

    private void showToast() {
        try {
            mToastyView.getToast().show();
            hasReflectException = false;
        } catch (Exception e) {
            hasReflectException = true;
        }
    }

    public IToastyView getToastyView() {
        return mToastyView;
    }
}
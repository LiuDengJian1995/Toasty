package com.liudengjian.toasty;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 刘登建 on 2018/5/9.
 */

public class ToastyView implements IToastyView{

    private static final Typeface LOADED_TOAST_TYPEFACE = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
    private static Typeface currentTypeface = LOADED_TOAST_TYPEFACE;
    private TextView toastTextView;
    private ImageView toastIcon;
    private Toast mToast;

    @Override
    public Toast createToast(Context context, String text) {
        LinearLayout layout = new LinearLayout(context);//创建一个线性布局对象
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setGravity(Gravity.CENTER);

        Drawable drawableFrame = ToastyUtils.getDrawable(context, R.drawable.toast_frames);
        ToastyUtils.setBackground(layout, drawableFrame);

        toastTextView = new TextView(context);
        toastTextView.setText(text);
        toastTextView.setTextColor(Color.parseColor("#FFFFFF"));
        toastTextView.setTypeface(currentTypeface);
        toastTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

        toastIcon = new ImageView(context);

        LinearLayout.LayoutParams imgviewParams = new LinearLayout.LayoutParams(
                        ToastyUtils.dip2px(context, 24), ToastyUtils.dip2px(context, 24));
        imgviewParams.setMargins(0, 0, ToastyUtils.dip2px(context, 5), 0);

        LinearLayout.LayoutParams textviewParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

        layout.addView(toastIcon, imgviewParams);
        layout.addView(toastTextView, textviewParams);

        mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        mToast.setView(layout);
        return mToast;
    }

    @Override
    public void setText(String title) {
        if (toastTextView != null && !TextUtils.isEmpty(title)) {
            toastTextView.setText(title);
        }
    }

    @Override
    public void setType(Context context, int type) {
        if (toastIcon == null) return;
        Drawable icon = null;
        switch (type) {
            case DEFAULT:
                toastIcon.setVisibility(View.GONE);
                break;
            case SUCCESS:
                icon = ToastyUtils.getDrawable(context, R.drawable.ic_success_outline_white_48dp);
                break;
            case ERROR:
                icon = ToastyUtils.getDrawable(context, R.drawable.ic_error_outline_white_48dp);
                break;
            case INFO:
                icon = ToastyUtils.getDrawable(context, R.drawable.ic_info_outline_white_48dp);
                break;
            default:
                toastIcon.setVisibility(View.GONE);
                break;
        }
        if (icon!=null){
            icon = ToastyUtils.tintIcon(icon, Color.parseColor("#FFFFFF"));
            ToastyUtils.setBackground(toastIcon, icon);
            toastIcon.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Toast getToast() {
        return mToast;
    }

}

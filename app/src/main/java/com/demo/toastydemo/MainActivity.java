package com.demo.toastydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.liudengjian.toasty.Toasty;
import com.liudengjian.toasty.ToastyConfig;
import com.liudengjian.toasty.ToastyShow;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_error_toast).setOnClickListener(this);
        findViewById(R.id.btn_info_toast).setOnClickListener(this);
        findViewById(R.id.btn_normal_toast).setOnClickListener(this);
        findViewById(R.id.btn_success_toast).setOnClickListener(this);
        findViewById(R.id.btn_item_toast).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        //不传上下文的必须在Application中初始化（ ToastyShow.initApplication(Application);）
        if (v.getId() == R.id.btn_item_toast) {
            //在ToastyConfig中设置自定义的吐司显示控件
            ToastyConfig.setToastyView(new ItemToastyView());
        } else {
            //在ToastyConfig中设置吐司显示控件为空，则会显示默认的吐司显示控件（ToastyView）
            ToastyConfig.setToastyView(null);
        }
        switch (v.getId()) {
            case R.id.btn_normal_toast:
                ToastyConfig.setGravity(this, Gravity.CENTER);
                ToastyShow.normal(this, "This is Default Toast", Toasty.LENGTH_SHORT);
              /*  ToastyShow.normalLong(this,"This is Default Toast");
                ToastyShow.normalShort(this,"This is Default Toast");
                ToastyShow.normalLong("This is Default Toast");
                ToastyShow.normalShort("This is Default Toast");*/
                break;
            case R.id.btn_success_toast:
                ToastyConfig.setGravity(this, Gravity.TOP);
//                ToastyShow.success(this,"This is success Toast", Toasty.LENGTH_SHORT);
                ToastyShow.successLong(this, "This is success Toast");
                /*ToastyShow.successShort(this,"This is success Toast");
                ToastyShow.successLong("This is success Toast");
                ToastyShow.successShort("This is success Toast");*/
                break;
            case R.id.btn_info_toast:
                ToastyConfig.setGravity(this, Gravity.TOP);
               /* ToastyShow.info(this,"This is info Toast", Toasty.LENGTH_SHORT);
                ToastyShow.infoLong(this,"This is info Toast");
                ToastyShow.infoShort(this,"This is info Toast");*/
                ToastyShow.infoLong("This is info Toast");
                /*  ToastyShow.infoShort("This is info Toast");*/
                break;
            case R.id.btn_error_toast:
                ToastyConfig.setGravity(this, Gravity.BOTTOM);
               /* ToastyShow.error(this,"This is error Toast", Toasty.LENGTH_SHORT);
                ToastyShow.errorLong(this,"This is error Toast");
                ToastyShow.errorShort(this,"This is error Toast");
                ToastyShow.errorLong("This is error Toast");*/
                ToastyShow.errorShort("This is error Toast");
                break;
            case R.id.btn_item_toast:
                ToastyConfig.setGravity(this, Gravity.BOTTOM);
                ToastyShow.normal(this, "This is item Toast", Toasty.LENGTH_SHORT);
              /*  ToastyShow.normalLong(this,"This is Default Toast");
                ToastyShow.normalShort(this,"This is Default Toast");
                ToastyShow.normalLong("This is Default Toast");
                ToastyShow.normalShort("This is Default Toast");*/
                break;
        }
    }
}

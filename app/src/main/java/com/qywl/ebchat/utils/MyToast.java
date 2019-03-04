package com.qywl.ebchat.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.qywl.ebchat.base.BaseApplication;


/**
 * @author: created by ${关云秀}
 * 时间：2018/12/20 18:00
 * 描述：
 */
public class MyToast {
    private static BaseApplication app;

    private MyToast() {
    }

    public static void init(BaseApplication app) {
        MyToast.app = app;
    }

    public static void s(String msg) {
        if (app == null) return;
        s(app, msg);
    }

    public static void l(String msg) {
        if (app == null) return;
        l(app, msg);
    }

    public static void s(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    public static void l(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}

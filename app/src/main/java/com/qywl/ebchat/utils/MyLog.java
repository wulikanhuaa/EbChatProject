package com.qywl.ebchat.utils;

import android.util.Log;

import com.qywl.ebchat.BuildConfig;


/**
 * 作者：张艳珍 on 2019/1/19 17:36
 * 描述：
 */
public class MyLog {
    public static int v(String tag, String msg) {
        if (BuildConfig.IS_SHOW_LOG) {
            return Log.v(tag, msg);
        } else {
            return -1;
        }
    }

    public static int v(String tag, String msg, Throwable tr) {
        if (BuildConfig.IS_SHOW_LOG) {
            return Log.v(tag, msg, tr);
        } else {
            return -1;
        }
    }

    public static int d(String tag, String msg) {
        if (BuildConfig.IS_SHOW_LOG) {
            return Log.d(tag, msg);
        } else {
            return -1;
        }
    }

    public static int d(String tag, String msg, Throwable tr) {
        if (BuildConfig.IS_SHOW_LOG) {
            return Log.d(tag, msg, tr);
        } else {
            return -1;
        }
    }

    public static int i(String tag, String msg) {
        if (BuildConfig.IS_SHOW_LOG) {
            return Log.i(tag, msg);
        } else {
            return -1;
        }
    }

    public static int i(String tag, String msg, Throwable tr) {
        if (BuildConfig.IS_SHOW_LOG) {
            return Log.i(tag, msg, tr);
        } else {
            return -1;
        }
    }

    public static int w(String tag, String msg) {
        if (BuildConfig.IS_SHOW_LOG) {
            return Log.w(tag, msg);
        } else {
            return -1;
        }
    }

    public static int w(String tag, String msg, Throwable tr) {
        if (BuildConfig.IS_SHOW_LOG) {
            return Log.w(tag, msg, tr);
        } else {
            return -1;
        }
    }

    public static int w(String tag, Throwable tr) {
        if (BuildConfig.IS_SHOW_LOG) {
            return Log.w(tag, tr);
        } else {
            return -1;
        }
    }

    public static int e(String tag, String msg) {
        if (BuildConfig.IS_SHOW_LOG) {
            return Log.e(tag, msg);
        } else {
            return -1;
        }
    }

    public static int e(String tag, String msg, Throwable tr) {
        if (BuildConfig.IS_SHOW_LOG) {
            return Log.e(tag, msg, tr);
        } else {
            return -1;
        }
    }
}

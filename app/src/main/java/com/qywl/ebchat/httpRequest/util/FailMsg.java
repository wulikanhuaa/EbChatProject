package com.qywl.ebchat.httpRequest.util;

import android.content.Context;
import android.content.Intent;

import com.qywl.ebchat.R;
import com.qywl.ebchat.base.BaseApplication;
import com.qywl.ebchat.utils.CheckUtils;
import com.qywl.ebchat.utils.MyLog;
import com.qywl.ebchat.utils.MyToast;

/**
 * 作者：张艳珍 on 2018/12/25 18:15
 * 描述：失败消息处理
 */
public class FailMsg {
    public static final int CODE_0 = 0; //失败
    public static final int CODE_1 = 1; //成功
    public static final int CODE_100 = -100; //必须传递token
    public static final int CODE_1007 = -1007; //签名验证失败
    public static final int CODE_101 = -101; //token错误	登录失效，跳转至登录页面
    public static final int CODE_102 = -102; //登录超时,请重新登录!!!	登录失效，跳转至登录页面
    public static boolean showMsg(Context context, int code, String msg) {
        /*if (code == CODE_1){
            return true;
        }else if (code == CODE_0){
            if(CheckUtils.checkStringNoNull(msg)) {
                MyToast.s(msg);
            }
            return false;
        }else if (code == CODE_100){
            if(CheckUtils.checkStringNoNull(msg)) {
                MyToast.s(msg);
            }
            return false;
        }else if (code == CODE_101){
            MyToast.s(context.getString(R.string.logintoken));
            BaseApplication.mSharedPrefUtil.clear();
            BaseApplication.removeActivity();
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            return false;
        }else if (code == CODE_102){
            MyToast.s(context.getString(R.string.The_login_timeout));
            BaseApplication.mSharedPrefUtil.clear();
            BaseApplication.removeActivity();
            Intent loginIntent = new Intent(context, LoginActivity.class);
            context.startActivity(loginIntent);
            return false;
        }else {
            if(CheckUtils.checkStringNoNull(msg)) {
                MyToast.s(msg);
                MyLog.e("msg","=================================="+msg);
            }
            return false;
        }*/
        return false;
    }
}

/*
package com.qywl.ebchat.httpRequest;


import com.google.gson.Gson;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;


public abstract class JsonCallback<T> extends AbsCallback<T> {


    @Override
    public T convertResponse(Response response) throws Throwable {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];
        String string = response.body().string();
//        LogUtils.d("string:" +string);
        Gson gson = new Gson();
        return  gson.fromJson(string,type);
    }

}*/

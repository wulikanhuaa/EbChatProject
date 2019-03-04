package com.qywl.ebchat.httpRequest.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;


import com.qywl.ebchat.R;
import com.qywl.ebchat.utils.MyToast;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * caheInterceptor
 * Created by Tamic on 2016-08-09.
 */
public class CaheInterceptor implements Interceptor {

    private Context context;

    public CaheInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (NetworkUtil.isNetworkAvailable(context)) {
            Response response = chain.proceed(request);
            // read from cache for 60 s
            String headers = response.headers().toString();

            Log.i("resoponse",headers+"******"+response.header("Session"));
            int maxAge = 1;
           return response.newBuilder()
                   // .removeHeader("Pragma")
                  //  .removeHeader("Cache-Control")
                   // .header("Cache-Control", "public, max-age=" + maxAge)
                   // .header("Authorization", BaseApplication.mSharedPrefUtil.getString(SharedConstants.TOKEN,""))
                    .build();
        } else {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MyToast.s(context, context.getString(R.string.net));
//                    Toast.makeText(context, "当前无网络! 为你智能加载缓存", Toast.LENGTH_SHORT).show();
                }
            });
            Log.e("Tamic", " no network load cahe");
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
            Response response = chain.proceed(request);
            //set cahe times is 3 days
            int maxStale = 60 * 60 * 24 * 5;
            return response.newBuilder()
                  //  .removeHeader("Pragma")
                  //  .removeHeader("Cache-Control")
                   // .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                   // .header("Authorization", BaseApplication.mSharedPrefUtil.getString(SharedConstants.TOKEN,""))
                    .build();
        }
    }



}

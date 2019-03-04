package com.qywl.ebchat.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

import com.qywl.ebchat.R;
import com.qywl.ebchat.config.SharedConstants;
import com.qywl.ebchat.utils.LanguageNUtils;
import com.qywl.ebchat.utils.MyToast;
import com.qywl.ebchat.utils.SharedPrefConfigUtils;
import com.qywl.ebchat.utils.SharedPrefUserUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.util.LinkedList;
import java.util.List;

import io.rong.imkit.RongIM;

/**
 * 作者：张艳珍 on 2019/1/22 11:30
 * 描述：
 */
public class BaseApplication extends Application {
    private static BaseApplication mApplication;
    public static List<Activity> activityList = new LinkedList<Activity>();
    public static int screenHeight;//屏幕高度
    public static float screenDensity;//屏幕密度
    public static int screenWidth;//屏幕宽度
    public static SharedPrefUserUtil mSharedPrefUtil;
    public static SharedPrefConfigUtils mSharedPrefConfigUtil;
    public static BaseApplication getInstance() {
        return mApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        mSharedPrefUtil = new SharedPrefUserUtil(this, SharedConstants.sharersinfor);
        mSharedPrefConfigUtil = new SharedPrefConfigUtils(this,SharedConstants.sharersinfor_config);
        MyToast.init(this);
        initScreenSize();
        //  设置本地化语言
        LanguageNUtils.setLocale(this);
//        setBuggly();
    /*    JPushInterface.setDebugMode(true);
        JPushInterface.init(this);


        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        Bugly.init(this, "7eff410071", false);*/



        RongIM.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    /**
     * 初始化当前设备屏幕宽高
     */
    private void initScreenSize() {
        DisplayMetrics curMetrics = getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = curMetrics.widthPixels;
        screenHeight = curMetrics.heightPixels;
        screenDensity = curMetrics.density;
    }

    public void removeActivity(Activity act, int index) {
        if (activityList != null && !activityList.isEmpty()) {
            activityList.remove(act);
        }
    }

    public void addActivity(Activity act) {
        if (activityList != null) {
            activityList.add(act);
        }
    }

    public static void removeActivity() {
        Log.i("activityList", activityList.size() + "***");
        //
        for (Activity activity : activityList) {
            activity.finish();
        }
        activityList.clear();
    }

    static {//static 代码段可以防止内存泄露
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.app_color, android.R.color.white);//全局设置主题颜色
//                layout.setRefreshHeader(new MaterialHeader(context).setShowBezierWave(true));
                 return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//指定为经典Header，默认是 贝塞尔雷达Header
//                return new MaterialHeader(context).setShowBezierWave(true);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                layout.setPrimaryColorsId(R.color.app_color);
                return  new BallPulseFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
            }
        });
    }





}

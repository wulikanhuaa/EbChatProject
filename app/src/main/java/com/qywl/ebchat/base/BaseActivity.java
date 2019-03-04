package com.qywl.ebchat.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qywl.ebchat.R;
import com.qywl.ebchat.customView.MultiStateView;
import com.qywl.ebchat.customView.SimpleMultiStateView;
import com.qywl.ebchat.utils.AlexStatusBarUtils;
import com.qywl.ebchat.utils.CheckUtils;
import com.qywl.ebchat.utils.LanguageNUtils;
import com.qywl.ebchat.utils.MyToast;
import com.qywl.ebchat.utils.Store;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseContract.BaseView{
    @Nullable
    @BindView(R.id.SimpleMultiStateView)
    public SimpleMultiStateView mSimpleMultiStateView;
    protected T mPresenter;
    Unbinder unbinder;
    public Context mContext;
    public Toolbar toolbar;
    public TextView titleName, rightText;
    public ImageView rightIcon;//右侧图标按钮
    public boolean ONLINE_SHOPING = false;//标识是否使用沉浸式状态栏，默认false 使用沉浸式状态栏； true，设置状态栏为白色 状态栏文字为黑色
    protected BaseApplication getAppInstance() {
        return (BaseApplication) getApplication();
    }
    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale();
        setContentView(attachLayoutRes());
        getAppInstance().addActivity(this);
        mContext = this;
        unbinder = ButterKnife.bind(this);
        if (null != onCreatePresenter()) {
            mPresenter = onCreatePresenter();
        }
        initStateView();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        initToolBar();
        attachView();
        initViews();

        loadData();
        if (ONLINE_SHOPING){
            setStatus();
        }else {
            AlexStatusBarUtils.setTransparentStatusBar(this, null);
        }

        EventBus.getDefault().register(this);

    }

    protected abstract int attachLayoutRes();

    protected abstract void initViews();

    protected abstract void loadData();

    protected abstract T onCreatePresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        EventBus.getDefault().unregister(this);
    }
    protected void initToolBar(){
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        titleName = (TextView)findViewById(R.id.title_name);
        rightIcon = (ImageView)findViewById(R.id.right_icon);
        rightText = (TextView)findViewById(R.id.right_text);
        setSupportActionBar(toolbar);
    }
    /**
     * 初始化 Toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    protected void initTitle( boolean homeAsUpEnabled, String title) {
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_back_write);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if(!TextUtils.isEmpty(title)) {
            titleName.setText(title);
        }
    }


    /**
     *
     * 设置acitivity标题
     * @param homeAsUpEnabled 是否显示返回键 true显示
     * @param backImg 返回键图标
     * @param title title名称
     * @param titleColor title颜色
     * @param isShowQuit 右侧是否显示按钮 true显示
     * @param quitImg 右侧按钮图标
     * @param isShowRightText 是否显示
     * @param rightTextStr 右侧按钮文字
     * @param rightTextColor 右侧按钮字体颜色
     */
    protected void initTitle(boolean homeAsUpEnabled, int backImg, String title, int titleColor, boolean isShowQuit, int quitImg, boolean isShowRightText, String rightTextStr, int rightTextColor) {
        if (backImg != 0 ){
            getSupportActionBar().setHomeAsUpIndicator(backImg);
        }else {
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_back_write);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        if(!TextUtils.isEmpty(title)) {
            titleName.setText(title);
            titleName.setTextColor(getResources().getColor(titleColor));
        }
        if (isShowQuit){
            rightIcon.setVisibility(View.VISIBLE);
            rightIcon.setBackgroundResource(quitImg);
        }else {
            rightIcon.setVisibility(View.GONE);
        }
        if (isShowRightText){
            if (rightTextColor != 0){
                rightText.setTextColor(rightTextColor);
            }
            if (CheckUtils.checkStringNoNull(rightTextStr)){
                rightText.setText(rightTextStr);
            }
            rightText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyToast.l("点击右侧按钮");
                }
            });
        }else {
            rightText.setVisibility(View.GONE);
        }
    }
    protected  void initTitle(Toolbar toolbar2, boolean homeAsUpEnabled){
        setSupportActionBar(toolbar2);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icon_back_write);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    protected void initTitle(boolean homeAsUpEnabled, int resTitle) {
        initTitle( homeAsUpEnabled, getString(resTitle));
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }
    //设置状态栏为白色
    public void setStatus(){
        AlexStatusBarUtils.setStatusColor(this,getResources().getColor(R.color.white));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        AlexStatusBarUtils.MIUISetStatusBarLightMode(getWindow(),true);
        AlexStatusBarUtils.FlymeSetStatusBarLightMode(getWindow(),true);
    }
    private void initStateView() {
        if (mSimpleMultiStateView == null){ return;}
        mSimpleMultiStateView.setEmptyResource(R.layout.view_empty)
                .setRetryResource(R.layout.view_retry)
                .setLoadingResource(R.layout.view_loading)
                .setNoNetResource(R.layout.view_nonet)
                .build()
                .setonReLoadlistener(new MultiStateView.onReLoadlistener() {
                    @Override
                    public void onReload() {
                        loadData();
                    }
                });
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(String str) {
        switch (str) {
            case "EVENT_REFRESH_LANGUAGE":
                changeAppLanguage();
                recreate();//刷新界面
                break;
        }
    }
    public void changeAppLanguage() {
        String sta = Store.getLanguageLocal(this);
        if(sta != null && !"".equals(sta)){
            // 本地语言设置
            Locale myLocale = new Locale(sta);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
        }

    }

    /**
     * 设置Locale
     */
    private void setLocale() {

        if (!LanguageNUtils.isSameLanguage(this)) {
            LanguageNUtils.setLocale(this);
         //   LanguageNUtils.toRestartMainActvity(this);
        }
    }

}

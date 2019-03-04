package com.qywl.ebchat.bussinessModule.mainFragment;

import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qywl.ebchat.R;
import com.qywl.ebchat.base.BaseFragment;
import com.qywl.ebchat.base.BasePresenter;
import com.qywl.ebchat.utils.AlexStatusBarUtils;
import com.qywl.ebchat.utils.BannerImageLoader;
import com.qywl.ebchat.utils.MyLog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 作者：张艳珍 on 2019/1/22 17:32
 * 描述：
 */
public class HomeNewsFragment extends BaseFragment {


    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_main_homenews;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews() {

    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }


}

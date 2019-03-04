package com.qywl.ebchat.bussinessModule.mainFragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.qywl.ebchat.R;
import com.qywl.ebchat.base.BaseFragment;
import com.qywl.ebchat.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：张艳珍 on 2019/1/22 17:34
 * 描述：
 */
public class NoticeFragment extends BaseFragment {

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_main_notice;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

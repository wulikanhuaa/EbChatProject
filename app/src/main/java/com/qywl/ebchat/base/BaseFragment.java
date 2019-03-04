package com.qywl.ebchat.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qywl.ebchat.R;
import com.qywl.ebchat.customView.MultiStateView;
import com.qywl.ebchat.customView.SimpleMultiStateView;
import com.qywl.ebchat.utils.MyLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseContract.BaseView{
    @Nullable
    @BindView(R.id.SimpleMultiStateView)
    public SimpleMultiStateView mSimpleMultiStateView;
    protected Context mContext;
    //缓存Fragment view
    private View mRootView;
    public Toolbar toolbar;
    public TextView titleName;
    private boolean mIsMulti = false;
    Unbinder unbinder;
    protected T  mPresenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), null);
            unbinder = ButterKnife.bind(this, mRootView);
            if (null != onCreatePresenter()) {
                mPresenter = onCreatePresenter();
            }
            initViews();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        initStateView();
        attachView();
        return mRootView;
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
                        updateViews();
                    }
                });
    }
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews();
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView != null  && !mIsMulti) {
            mIsMulti = true;
            updateViews();
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    /**
     * 绑定布局文件
     * @return  布局文件ID
     */
    protected abstract int attachLayoutRes();
    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 更新视图控件
     */
    protected abstract void updateViews();

    protected abstract T onCreatePresenter();
    @Override
    public void onDestroy() {
        super.onDestroy();

        if (unbinder != null) {
            unbinder.unbind();
            MyLog.e("111", "Actiyity_unbinder --->执行了");
        }
    }



}

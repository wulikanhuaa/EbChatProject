package com.qywl.ebchat.bussinessModule;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qywl.ebchat.R;
import com.qywl.ebchat.base.BaseActivity;
import com.qywl.ebchat.base.BasePresenter;
import com.qywl.ebchat.bussinessModule.mainFragment.ContractsFragment;
import com.qywl.ebchat.bussinessModule.mainFragment.HomeNewsFragment;
import com.qywl.ebchat.bussinessModule.mainFragment.MineFragment;
import com.qywl.ebchat.bussinessModule.mainFragment.NoticeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.id_content)
    FrameLayout idContent;
    @BindView(R.id.tab01_lv)
    RadioButton tab01Lv;
    @BindView(R.id.tab02_lv)
    RadioButton tab02Lv;
    @BindView(R.id.tab03_lv)
    RadioButton tab03Lv;
    @BindView(R.id.tab04_lv)
    RadioButton tab04Lv;
    @BindView(R.id.main_radiogroup)
    RadioGroup mainRadiogroup;

    HomeNewsFragment homeNewsFragment;//首页消息
    ContractsFragment contractsFragment;//联系人
    NoticeFragment noticeFragment;//消息
    MineFragment mineFragment;//我的
    public static MainActivity mainActivity;
    public static int currentFragment = -1;
    public static int updateFlag = -1;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mainActivity = this;
        setSelect(0);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected BasePresenter onCreatePresenter() {
        return null;
    }


    public void setSelect(int i) {
        resetImgs();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        // 把图片设置为亮的
        // 设置内容区域
        switch (i) {
            case 0:
                currentFragment = 0;
                tab01Lv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon_eaby_home_true, 0, 0);
                tab01Lv.setTextColor(getResources().getColor(R.color.white));
                if (homeNewsFragment == null) {
                    homeNewsFragment = new HomeNewsFragment();
                    transaction.add(R.id.id_content, homeNewsFragment);
                } else {
                    transaction.show(homeNewsFragment);
                }
                break;
            case 1:
                currentFragment = 1;
                tab02Lv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon_eaby_trans_true, 0, 0);
                tab02Lv.setTextColor(getResources().getColor(R.color.white));
                if (contractsFragment == null) {
                    contractsFragment = new ContractsFragment();
                    transaction.add(R.id.id_content, contractsFragment);
                } else {
                    transaction.show(contractsFragment);
                }
                break;
            case 2:
                currentFragment = 2;
                tab03Lv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon_eaby_c2c_true, 0, 0);
                tab03Lv.setTextColor(getResources().getColor(R.color.white));
                if (noticeFragment == null) {
                    noticeFragment = new NoticeFragment();
                    transaction.add(R.id.id_content, noticeFragment);
                } else {   //
                    transaction.show(noticeFragment);
                }

                break;
            case 3:
                currentFragment = 3;
                tab04Lv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon_eaby_consultation_true, 0, 0);
                tab04Lv.setTextColor(getResources().getColor(R.color.white));

                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.id_content, mineFragment);
                } else {   //
                    transaction.show(mineFragment);
                }

                break;

            default:
                break;
        }

        transaction.commit();
    }

    public void hideFragment(FragmentTransaction transaction) {
        if (homeNewsFragment != null) {
            transaction.hide(homeNewsFragment);
        }
        if (contractsFragment != null) {
            transaction.hide(contractsFragment);
        }
        if (noticeFragment != null) {
            transaction.hide(noticeFragment);
        }

        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }

    /**
     * 切换图片至暗色
     */
    public void resetImgs() {
        tab01Lv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon_eaby_home_false, 0, 0);
        tab02Lv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon_eaby_trans_false, 0, 0);
        tab03Lv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon_eaby_c2c_false, 0, 0);
        tab04Lv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon_eaby_consultation_false, 0, 0);
        tab01Lv.setTextColor(getResources().getColor(R.color.eaby_home_false));
        tab02Lv.setTextColor(getResources().getColor(R.color.eaby_home_false));
        tab03Lv.setTextColor(getResources().getColor(R.color.eaby_home_false));
        tab04Lv.setTextColor(getResources().getColor(R.color.eaby_home_false));
    }

    @OnClick({R.id.tab01_lv, R.id.tab02_lv, R.id.tab03_lv, R.id.tab04_lv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab01_lv:
                setSelect(0);
                break;
            case R.id.tab02_lv:
                setSelect(1);
                break;
            case R.id.tab03_lv:
                setSelect(2);
                break;
            case R.id.tab04_lv:
                setSelect(3);
                break;

        }
    }
}

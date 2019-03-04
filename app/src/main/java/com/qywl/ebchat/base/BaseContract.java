package com.qywl.ebchat.base;


/**
 * desc:
 * author: Will .
 * date: 2017/9/2 .
 */
public interface BaseContract {

    interface BasePresenter<T extends BaseContract.BaseView> {

        void attachView(T view);

        void detachView();
    }


    interface BaseView {






    }
}

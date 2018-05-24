package com.beta.MoneyballMaster.contract.base;

import com.beta.MoneyballMaster.http.callback.ApiCallBack;

import rx.Observable;

/**
 * Created by yas on 2018/5/24.
 */

public interface IBaseModel {

    void addSubscription(Observable observable, ApiCallBack callBack);
    void onUnsubscribe();
}

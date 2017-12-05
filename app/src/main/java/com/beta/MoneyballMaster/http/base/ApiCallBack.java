package com.beta.MoneyballMaster.http.base;

/**
 * Created by yas on 2017/11/30.
 */

public interface ApiCallBack<T> {
    void onSuccessful(T t);
    void onFailed(int errorCode,String errorMsg);
    void onCompleted();

}

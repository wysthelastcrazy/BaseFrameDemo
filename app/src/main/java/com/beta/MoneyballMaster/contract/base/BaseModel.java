package com.beta.MoneyballMaster.contract.base;


import com.beta.MoneyballMaster.http.APIService;
import com.beta.MoneyballMaster.http.ProtocolManager;
import com.beta.MoneyballMaster.http.callback.ApiCallBack;
import com.beta.MoneyballMaster.http.callback.SubscriberCallBack;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yas on 2017/12/14.
 */

public class BaseModel implements IBaseModel{
    public APIService apiStores = ProtocolManager.getInstance().apiStores;
    private CompositeSubscription mCompositeSubscription;
    //RXjava取消注册，以避免内存泄露
    @Override
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void addSubscription(Observable observable, ApiCallBack callBack) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SubscriberCallBack(callBack)));
    }
}

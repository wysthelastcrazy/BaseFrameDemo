package com.beta.MoneyballMaster.contract.base;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by yas on 2018/5/24.
 */

public interface IBaseModel {

    void addSubscription(Observable observable, Subscriber subscriber);
    void onUnsubscribe();
}

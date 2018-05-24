package com.beta.MoneyballMaster.contract.base;


import com.beta.MoneyballMaster.http.APIService;
import com.beta.MoneyballMaster.http.base.RetrofitUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yas on 2017/12/14.
 */

public class BaseModel implements IBaseModel{
    public APIService apiStores = RetrofitUtils.newInstence().create(APIService.class);
    private CompositeSubscription mCompositeSubscription;
    //RXjava取消注册，以避免内存泄露
    @Override
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}

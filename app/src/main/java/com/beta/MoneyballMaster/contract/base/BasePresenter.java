package com.beta.MoneyballMaster.contract.base;


/**
 * ============================================================
 * 作 者 : yas
 * 版 本 ： 1.0
 * 创建日期 ： 2017/11/30 22:20
 * 描 述 ：Presenter通用配置类，所有Presenter继承该类,MVP的P层，负责数据交互
 * ============================================================
 **/
public abstract class BasePresenter<V> implements IBasePresenter<V> {
    public V mView;
//    public APIService apiStores = RetrofitUtils.newInstence().create(APIService.class);
//    private CompositeSubscription mCompositeSubscription;
    @Override
    public void attach(V v) {
        this.mView = v;
    }

    @Override
    public void deAttach() {
        this.mView = null;
        onUnsubscribe();
    }
    public abstract void onUnsubscribe();
}

package com.beta.MoneyballMaster.contract.base;

/**
 * Created by yas on 2017/11/30.
 */

public interface IBasePresenter<T> {
    /**
     * 绑定view与model
     * @param t
     */
     void attach(T t);

    /**
     * 取消view的绑定
     */
     void deAttach();
}

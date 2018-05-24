package com.beta.MoneyballMaster.contract.base;
import android.text.TextUtils;

import com.beta.MoneyballMaster.common.MConfiger;
import com.beta.MoneyballMaster.utils.DeviceUtil;
import com.beta.MoneyballMaster.utils.MyLog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.beta.MoneyballMaster.http.APIService;
import com.beta.MoneyballMaster.http.base.RetrofitUtils;
import com.beta.MoneyballMaster.utils.MD5;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static android.content.ContentValues.TAG;

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

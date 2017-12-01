package com.wys.baseframedemo.contract.base;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wys.baseframedemo.http.APIService;
import com.wys.baseframedemo.http.base.RetrofitUtils;
import com.wys.common.MD5;

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

/**
 * ============================================================
 * <p/>
 * 版 权 ：
 * <p/>
 * 作 者 : yas
 * <p/>
 * 版 本 ： 1.0
 * <p/>
 * 创建日期 ： 2017/11/30 22:20
 * <p/>
 * 描 述 ：Presenter通用配置类，所有Presenter继承该类,MVP的P层，负责数据交互
 * <p/>
 * ============================================================
 **/
public class BasePresenter<V> implements IBasePresenter<V> {
    public V mView;
    public APIService apiStores = RetrofitUtils.newInstence().create(APIService.class);
    private CompositeSubscription mCompositeSubscription;
    @Override
    public void attach(V v) {
        this.mView = v;
    }

    @Override
    public void deAttach() {
        this.mView = null;
        onUnsubscribe();
    }

    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }


    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    protected final RequestBody creatRequestBody(Map<String, Object> map){
        String strJson=getPostJavaParams(map);
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),strJson);
        return body;
    }
    /**
     * post请求时json参数处理
     * @param map
     * @return
     */
    protected final String getPostJavaParams(Map<String, Object> map) {

        String sign = getSignParam(map);
        if(!TextUtils.isEmpty(sign)) {
            map.put("sign", sign);
        }
        if (map == null) {
            map = new HashMap<>();
        }
        if (map != null) {
            //添加时间戳
            map.put("timestamp", System.currentTimeMillis());
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            String str = gson.toJson(map, type);
            return str;
        } else {
            return "";
        }
    }
    /**
     * 获取sign签名验证
     * @param map
     * @return
     */
    protected final String getSignParam(Map<String, Object> map) {
        StringBuilder builder = new StringBuilder();
        String sign = "";

        if (map != null && map.size() > 0) {
            TreeMap<String, Object> treeMap = new TreeMap<>(new Comparator<String>() {
                @Override
                public int compare(String s, String t1) {
                    int compare = 0;
                    if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(t1)) {
                        compare = s.compareTo(t1);
                    }

                    if (compare > 0) {
                        return 1;
                    } else if (compare < 0) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });

            treeMap.putAll(map);
            if (treeMap.size() > 0) {
                for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
                    String key = entry.getKey();
                    Object val = entry.getValue();
                    if(val == null){
                        builder.append(key + "=0&");
                    } else {
                        if (val instanceof String){
                            String str= (String) val;
                            if (TextUtils.isEmpty(str)){
                                builder.append(key + "=0&");
                            }else{
                                builder.append(key + "=" + val + "&");
                            }
                        }else if(val instanceof ArrayList){
                            //list暂时不参与签名
                        }else {
                            builder.append(key + "=" + val + "&");
                        }
                    }
                }
//                if(builder.length()>0) {
//                    builder.append(NetCommon.JAVA_KEY_CODE);
//                }
            }
            sign = MD5.getMd5(builder.toString(), "utf-8");
            if (!TextUtils.isEmpty(sign)) {
                sign=sign.toLowerCase();
            }
        }
        return sign;
    }

}

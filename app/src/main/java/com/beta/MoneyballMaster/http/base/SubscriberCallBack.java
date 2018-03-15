package com.beta.MoneyballMaster.http.base;

import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by yas on 2017/11/30.
 */

public class SubscriberCallBack<T> extends Subscriber<HttpBaseResult<T>> {
    private ApiCallBack<T> apiCallBack;
    public SubscriberCallBack(ApiCallBack<T> apiCallBack){
        this.apiCallBack=apiCallBack;
    }
    @Override
    public void onCompleted() {
        apiCallBack.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException){
            HttpException httpException= (HttpException) e;
            int code=httpException.code();
            String msg="数据异常!";
            if (code == 500 || code == 404){
                msg="网路不给力!";
            }
            apiCallBack.onFailed(code,msg);
        }else if (e instanceof ConnectException){
            apiCallBack.onFailed(0,"网络断开,请打开网络!");
        }else if(e instanceof SocketTimeoutException){
            apiCallBack.onFailed(0,"网络连接超时!");
        }else if (e instanceof JSONException||e instanceof JsonSyntaxException){
            apiCallBack.onFailed(0,"json解析错误!");
        }else{
            apiCallBack.onFailed(0,"无法连接到服务器，请检查网络或联系客服!");
        }
        apiCallBack.onCompleted();
    }

    @Override
    public void onNext(HttpBaseResult<T> t) {
        if (t.result) {
            apiCallBack.onSuccessful(t.getData());
        }else{
            apiCallBack.onFailed(t.errorCode,t.msg);
        }
        apiCallBack.onCompleted();
    }
}

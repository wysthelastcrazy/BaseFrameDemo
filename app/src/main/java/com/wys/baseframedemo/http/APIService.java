package com.wys.baseframedemo.http;


import com.wys.baseframedemo.entity.base.HttpBaseResult;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * API--接口  服务[这里处理的是同一的返回格式 resultCode  resultInfo Data<T> --->这里的data才是返回的结果,T就是泛型 具体返回的been对象或集合]
 * Created by HDL on 2016/8/3.
 */
public interface APIService {
    /**
     * 用户登录的接口
     *
     * @return RxJava 对象
     */
    @POST("api/staff/passport/loginWithPwd.action")
    Observable<HttpBaseResult> userLogin(@Body RequestBody body);
}


package com.beta.MoneyballMaster.http;


import com.beta.MoneyballMaster.http.base.HttpBaseResult;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * API--接口  服务[这里处理的是同一的返回格式 resultCode  resultInfo Data<T> --->这里的data才是返回的结果,T就是泛型 具体返回的been对象或集合]
 * Created by yas on 2017/12/14.
 */
public interface APIService {
    /**
     * 用户登录的接口
     *
     * @return RxJava 对象
     */
    @POST("api/staff/passport/loginWithPwd.action")
    Observable<HttpBaseResult> userLogin(@Body RequestBody body);
    /**
     * 上传单个文件
     */
    @Multipart
    @POST
    Observable<HttpBaseResult> uploadSingleFile(@Url String url, @Part MultipartBody.Part part);

    /**
     * 多文件上传
     * @param url
     * @param parts
     * @return
     */
    @Multipart
    @POST
    Observable<HttpBaseResult> upload(@Url String url, @Part List<MultipartBody.Part> parts);

    /**
     * 普通get请求
     * @param params
     * @return
     */
    @GET("")
    Observable<HttpBaseResult> getOrderList(@QueryMap Map<String,Object> params);
}


package com.beta.MoneyballMaster.http;



import com.beta.MoneyballMaster.entity.UserInfo;
import com.beta.MoneyballMaster.http.callback.HttpBaseResult;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
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
    @POST("/api/passport/login.action")
    @FormUrlEncoded
    Observable<HttpBaseResult<UserInfo>> userLogin(@Field("phone") String phone, @Field("smsCode") String smsCode);

    /**
     * 上传外访图片（批量）
     * 通过 MultipartBody和@body作为参数来上传
     * @param multipartBody MultipartBody包含多个Part
     */
    @POST("/api/loan/batchUploadPicture.action")
    Observable<HttpBaseResult> uploadFile(@Body MultipartBody multipartBody);
}


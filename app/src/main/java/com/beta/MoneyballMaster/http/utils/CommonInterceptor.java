package com.beta.MoneyballMaster.http.utils;

import android.text.TextUtils;

import com.beta.MoneyballMaster.BuildConfig;
import com.beta.MoneyballMaster.utils.MyLog;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by yas on 2017/11/30.
 * 封装公共参数
 */

public class CommonInterceptor implements Interceptor {

    @Override public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        // 新的请求
        Request.Builder newBuilder = oldRequest.newBuilder();
        if ("GET".equals(oldRequest.method())){
            newBuilder.url(addGetCommonParams(oldRequest).build());
            newBuilder.method(oldRequest.method(),oldRequest.body());
        }else if ("POST".equals(oldRequest.method())){
            RequestBody body=oldRequest.body();
            if (body instanceof FormBody){
                //不普通的post请求
                FormBody formBody= (FormBody) body;
                newBuilder.method(oldRequest.method(),addParamsToFormBody(formBody))
                .url(oldRequest.url());
            }else if (body instanceof MultipartBody){
                //有文件上传的post请求
                //公共参数在组织生成MultipartBody时已添加，此处无需再添加
//                return chain.proceed(oldRequest);
            }
        }
        // 新的请求
        newBuilder.addHeader("Cookie", getCookie())
                .build();

        Response response = chain.proceed(newBuilder.build());
//        if (BuildConfig.DEBUG) {
//            MyLog.debug("---> " + response.body().string());
//        }
        return response;
    }
    /***
     * cookie
     * @return
     */
    private final String getCookie(){
        return "";
    }

    /**
     * 给普通post请求添加公共参数，并且转换为json形式
     * @param formBody
     * @return
     */
    private final RequestBody addParamsToFormBody(FormBody formBody){
        int size=formBody.size();
        Map<String,Object> mMap=new HashMap<>();
        for (int i=0;i<size;i++){
            mMap.put(formBody.name(i),formBody.value(i));
        }
        String strJson= HttpUtils.mapToJsonStr(mMap);
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),strJson);
        return body;
    }

    /**
     * 添加get方式公共参数
     * @param oldRequest
     * @return
     */
    private HttpUrl.Builder addGetCommonParams(Request oldRequest){
        HttpUrl.Builder authorizedUrlBuilder=oldRequest.url().newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());
        //遍历参数，用于sign计算
        int size=oldRequest.url().querySize();
        Map<String,Object> mMap=new HashMap<>();
        for (int i=0;i<size;i++){
            mMap.put(oldRequest.url().queryParameterName(i),oldRequest.url().queryParameterValue(i));
        }
        //计算sign并添加
        String sign=HttpUtils.getSignParam(mMap);
        if (!TextUtils.isEmpty(sign)) {
            authorizedUrlBuilder.addQueryParameter("sign", sign);
        }
        //添加公共参数
        Map<String,Object> commonParams=HttpUtils.getCommonParams();
        for (Map.Entry<String,Object> entry:commonParams.entrySet()){
            authorizedUrlBuilder.addQueryParameter(entry.getKey(), entry.getValue()+"");
        }
        return authorizedUrlBuilder;
    }

    private void debugLog(Chain chain) {
        if (BuildConfig.DEBUG) {

        }
    }

}

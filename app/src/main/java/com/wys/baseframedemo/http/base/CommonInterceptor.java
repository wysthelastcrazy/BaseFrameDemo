package com.wys.baseframedemo.http.base;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yas on 2017/11/30.
 * 封装公共参数
 */

public class CommonInterceptor implements Interceptor {

    @Override public Response intercept(Interceptor.Chain chain) throws IOException {
        Request oldRequest = chain.request();

        // 添加新的参数
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());

        // 新的请求
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(authorizedUrlBuilder.build())
                .addHeader("Cookie", getLoginStatus())
                .build();

        return chain.proceed(newRequest);
    }
    /***
     * 登录身份信息
     * @return
     */
    public static String getLoginStatus(){
//        String cookie = LoginController.getInstance().getCookie();
//        if(MyLog.isDebugable()){
//            MyLog.debug(TAG,"[getLoginStatus]" + " cookie:" + cookie);
//        }
//        //现进行整体编码处理
//        if (!TextUtils.isEmpty(cookie)){
//            try {
//                cookie= URLEncoder.encode(cookie, "UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
        return "uid=3;token=212;";
    }
}

package com.beta.MoneyballMaster.http.utils;



import com.beta.MoneyballMaster.common.Common;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Retrofit网络请求工具类
 * Created by yas on 2018/4/25.
 */
public class RetrofitUtils {
    private static final int READ_TIMEOUT = 60;//读取超时时间,单位  秒
    private static final int CONN_TIMEOUT = 12;//连接超时时间,单位  秒

    private static Retrofit mRetrofit;

    private RetrofitUtils() {

    }

    public static Retrofit newInstance() {
        if (mRetrofit==null) {
            OkHttpClient client = getHttpClient();
            //初始化一个client,不然retrofit会自己默认添加一个
            mRetrofit = new Retrofit.Builder()
                    .client(client)//添加一个client,不然retrofit会自己默认添加一个
                    .baseUrl(Common.BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    /**
     * 获取OkHttpClient
     * @return
     */
    private static OkHttpClient getHttpClient(){
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new CommonInterceptor())
                .connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT,TimeUnit.SECONDS)
                .sslSocketFactory(createSSLSocketFactory())
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                })
                .build();

        return okHttpClient;
    }
    private static javax.net.ssl.SSLSocketFactory createSSLSocketFactory() {
        javax.net.ssl.SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }
    private static class TrustAllCerts implements X509TrustManager {

        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[0];
        }
    }

}

package com.beta.MoneyballMaster.http.callback;

/**
 * Created by yas on 2017/11/30.
 */

public class HttpBaseResult<T> {
    /**返回错误消息提示**/
    public String msg;
    /**返回的数据域信息**/
    public T data;
    /**是否返回正确**/
    public boolean result;
    /**错误码**/
    public int errorCode;

    public T getData(){
        return data;
    }
}

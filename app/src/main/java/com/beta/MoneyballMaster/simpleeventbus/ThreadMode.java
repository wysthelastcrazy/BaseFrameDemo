package com.beta.MoneyballMaster.simpleeventbus;

/**
 * Created by yas on 2018/3/8.
 */

public enum ThreadMode {
    POSITING,   //订阅方法运行在发送事件的线程
    MAIN,       //主线程
    BACKGROUND, //后台线程
    ASYNC       //订阅方法与发送事件始终不在同一个线程，即订阅方法始终会使用新的线程来运行
}

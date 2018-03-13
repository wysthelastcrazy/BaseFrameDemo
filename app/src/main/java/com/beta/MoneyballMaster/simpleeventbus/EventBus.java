package com.beta.MoneyballMaster.simpleeventbus;

/**
 * Created by yas on 2018/3/8.
 */

public class EventBus {
    private static final EventBus ourInstance = new EventBus();

    public static EventBus getInstance() {
        return ourInstance;
    }

    private EventBus() {
    }
    public void register(Object subscriber){

    }
}

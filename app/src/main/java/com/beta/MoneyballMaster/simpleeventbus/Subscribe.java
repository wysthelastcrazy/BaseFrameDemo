package com.beta.MoneyballMaster.simpleeventbus;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yas on 2018/3/8.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscribe {
    /**
     * 阅读方法所运行的线程
     * @return
     */
    ThreadMode threadMode() default ThreadMode.POSITING;

    /**
     * 是否是粘性事件
     * @return
     */
    boolean sticky() default false;

    /**
     * 优先级
     * @return
     */
    int priority() default 0;
}

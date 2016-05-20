package com.haihui.top.one.eventbus.meta;

import com.haihui.top.one.eventbus.SubscriberMethod;

/**
 * Base class for generated index classes created by annotation(注解) processing(处理)
 */
public interface SubscriberInfo {
    // 得到订阅者的Class
    Class<?> getSubscriberClass();
    // 得到订阅者Method
    SubscriberMethod[] getSubscriberMethods();
    // 得到父类的Info
    SubscriberInfo getSuperSubscriperInfo();
    // 是否应该Check父类
    boolean shouldCheckSuperclass();
}

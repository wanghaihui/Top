package com.haihui.top.one.eventbus.meta;

/**
 * 订阅者信息的索引
 * Interface for generated(生成的) indexes(索引).
 */
public interface SubscriberInfoIndex {
    // 得到订阅者信息
    SubscriberInfo getSubscriberInfo(Class<?> subscriberClass);
}

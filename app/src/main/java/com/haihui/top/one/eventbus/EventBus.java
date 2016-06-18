package com.haihui.top.one.eventbus;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * EventBus is a central publish/subscribe event system for Android
 * Events are posted post(Object) to the bus, which delivers it to subscribers that have a matching handler method for the event type
 * To receive events, subscribers must register themselves to the bus using register(Object)
 * Once registered, subscribers receive events until unregister(Object) is called
 * Event handling methods must be annotated by Subscribe, must be public, return nothing (void), and have exactly one parameter
 * (the event)
 */
public class EventBus {

    public static String TAG = "EventBus";

    // 包中都可引用
    // 为了解决线程并发的问题, 在语言内部引入了同步块和volatile关键字机制
    // 用volatile修饰的变量, 线程在每次使用变量的时候, 都会读取变量修改后的最的值
    static volatile EventBus defaultInstance;

    // 默认的Builder
    private static final EventBusBuilder DEFAULT_BUILDER = new EventBusBuilder();

    // CopyOnWriteArrayList--一个线程安全的随机访问List--并发容器--优化策略
    // 基本思路是: 从一开始大家都在共享同一个内容, 当某个人想要修改这个内容的时候, 才会真正把内容Copy出去形成一个新的内容然后再改, 这是一种延时懒惰策略

    // CopyOnWrite容器即写时复制的容器--通俗的理解是当我们往一个容器添加元素的时候, 不直接往当前容器添加, 而是先将当前容器进行Copy, 复制出一个新的容器
    // 然后新的容器里添加元素, 添加完元素之后, 再将原容器的引用指向新的容器
    // 这样做的好处是我们可以对CopyOnWrite容器进行并发的读, 而不需要加锁, 因为当前容器不会添加任何元素
    // 所以CopyOnWrite容器也是一种读写分离的思想, 读和写不同的容器

    // CopyOnWrite并发容器用于读多写少的并发场景
    // CopyOnWrite容器只能保证数据的最终一致性, 不能保证数据的实时一致性, 所以如果你希望写入的的数据, 马上能读到, 请不要使用CopyOnWrite容器
    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionByEventType;

    /**
     * Convenience(方便的) singleton for apps using a process-wide(进程范围的) EventBus instance
     */
    public static EventBus getDefault() {
        if (defaultInstance == null) {
            synchronized (EventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new EventBus();
                }
            }
        }

        return defaultInstance;
    }

    /**
     * Creates a new EventBus instance
     * each instance is a separate scope(隔离的作用域) in which events are delivered
     * To use a central bus, consider getDefault()
     */
    public EventBus() {
        this(DEFAULT_BUILDER);
    }

    EventBus(EventBusBuilder builder) {

    }

}

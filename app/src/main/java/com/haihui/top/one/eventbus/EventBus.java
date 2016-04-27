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
    static volatile EventBus defaultInstance;

    private static final EventBusBuilder DEFAULT_BUILDER = new EventBusBuilder();

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

package com.haihui.top.one.eventbus;

import com.haihui.top.one.eventbus.meta.SubscriberInfoIndex;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Creates EventBus instances with custom parameters and also allows to install a custom default EventBus instance.
 * Create a new builder using {@link EventBus#builder()}.
 *
 * Builder模式--用于构建复杂对象的一种模式, 所构建的对象往往需要多步初始化和赋值才能完成
 * Builder模式--来替代多参数的构造函数是一个比较好的实践法则
 * Builder模式的要点就是通过一个代理来完成对象的构建过程, 这个代理的职责就是完成构建的各个步骤, 同时它也是易扩展的
 */
public class EventBusBuilder {
    // 缺省的执行服务--newCachedThreadPool在解决一些短期的任务上非常高效--私有化
    private final static ExecutorService DEFAULT_EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    // 是否打印订阅者异常log--默认打印
    boolean logSubscriberExceptions = true;

    // 是否打印没有订阅者信息log--默认打印
    boolean logNoSubscriberMessages = true;

    // 是否发送订阅者异常Event--默认发送
    boolean sendSubscriberExceptionEvent = true;

    // 是否发送没有订阅者Event--默认发送
    boolean sendNoSubscriberEvent = true;

    // 是否抛出Subscriber异常--默认不抛出
    boolean throwSubscriberException;

    // Event是否继承--默认继承
    boolean eventInheritance = true;

    // 是否忽视生成的索引--默认忽视
    boolean ignoreGeneratedIndex;

    // 是否有严格的方法确认--默认没有
    boolean strictMethodVerification;

    // 具体执行者--多线程执行任务
    ExecutorService executorService = DEFAULT_EXECUTOR_SERVICE;

    // 泛型的参数类型可以是通配符类型
    // Class类一般用于反射, <>表示泛型, ?表示通配符
    // 跳过方法确认的类
    List<Class<?>> skipMethodVerificationForClasses;

    // 订阅者信息的索引列表
    List<SubscriberInfoIndex> subscriberInfoIndexes;

    EventBusBuilder() {

    }

    /**
     * 缺省为true
     */
    public EventBusBuilder logSubscriberExceptions(boolean logSubscriberExceptions) {
        this.logSubscriberExceptions = logSubscriberExceptions;
        return this;
    }

    /**
     * 缺省为true
     */
    public EventBusBuilder logNoSubscriberMessages(boolean logNoSubscriberMessages) {
        this.logNoSubscriberMessages = logNoSubscriberMessages;
        return this;
    }

    /**
     * 缺省为true
     */
    public EventBusBuilder sendSubscriberExceptionEvent(boolean sendSubscriberExceptionEvent) {
        this.sendSubscriberExceptionEvent = sendSubscriberExceptionEvent;
        return this;
    }

    /**
     * 缺省为true
     */
    public EventBusBuilder sendNoSubscriberEvent(boolean sendNoSubscriberEvent) {
        this.sendNoSubscriberEvent = sendNoSubscriberEvent;
        return this;
    }

    /**
     * Fails if an subscriber throws an Exception(默认为false)
     * Tip: Use this with BuildConfig.DEBUG to let the app crash in DEBUG mode (only). This way,
     * you won't miss exceptions during development.
     */
    public EventBusBuilder throwSubscriberException(boolean throwSubscriberException) {
        this.throwSubscriberException = throwSubscriberException;
        return this;
    }

    /**
     * By default, EventBus considers the event class hierarchy(继承体系) (subscribers to super classes will be notified(被通知)).
     * Switching this feature off (关掉这个特征) will improve posting of events.
     * For simple event classes extending Object directly, we measured a speed up of 20% for event posting.
     * For more complex event hierarchies, the speed up should be > 20%
     *
     * However, keep in mind that event posting usually consumes just a small proportion(比例) of CPU time inside an app,
     * unless it is posting at high rates, e.g. hundreds/thousands of events per second.
     */
    public EventBusBuilder eventInheritance(boolean eventInheritance) {
        this.eventInheritance = eventInheritance;
        return this;
    }

    /**
     * Forces the use of reflection even if there's a generated index
     * 缺省是false
     */
    public EventBusBuilder ignoreGeneratedIndex(boolean ignoreGeneratedIndex) {
        this.ignoreGeneratedIndex = ignoreGeneratedIndex;
        return this;
    }

    /**
     * Enables strict method verification
     * 缺省值false
     */
    public EventBusBuilder strictMethodVerification(boolean strictMethodVerification) {
        this.strictMethodVerification = strictMethodVerification;
        return this;
    }

    /**
     * Provide a custom thread pool to EventBus used for async and background event delivery(分发或传递). This is an advanced
     * setting to that can break things(打破事情): ensure the given ExecutorService won't get stuck(被卡住) to avoid undefined(未定义的) behavior.
     */
    public EventBusBuilder executorService(ExecutorService executorService) {
        this.executorService =executorService;
        return this;
    }

    /**
     * Method name verification is done for methods starting with onEvent to avoid typos(拼写错误); Using this method you can
     * exclude(排除) subscriber classes from this check.
     * Also disables checks for method modifiers(方法修饰符) (public, not static nor abstract)
     */
    public EventBusBuilder skipMethodVerificationFor(Class<?> clazz) {
        if (skipMethodVerificationForClasses == null) {
            skipMethodVerificationForClasses = new ArrayList<>();
        }
        skipMethodVerificationForClasses.add(clazz);
        return this;
    }

    /**
     * Adds an index generated by EventBus's annotation preprocessor.
     */
    public EventBusBuilder addIndex(SubscriberInfoIndex index) {
        if (subscriberInfoIndexes == null) {
            subscriberInfoIndexes = new ArrayList<>();
        }
        subscriberInfoIndexes.add(index);
        return this;
    }

    /**
     * Installs the default EventBus returned by EventBus.getDefault() using this builders' values.
     * Must be done only once before the first usage of the default EventBus.
     *
     * @throws EventBusException if there's already a default EventBus instance in place.
     */
    public EventBus installDefaultEventBus() {
        // 同步EventBus
        // 当它用来修饰一个方法或者一个代码块的时候, 能够保证在同一时刻最多只有一个线程执行该段代码
        // 获得EventBus的对象锁--对对象加锁
        synchronized (EventBus.class) {
            if (EventBus.defaultInstance != null) {
                throw new EventBusException("Default instance already exists." +
                            " It may be only set once before it's used the first time to ensure consistent(一致的) behavior.");
            }

            EventBus.defaultInstance = build();
            return EventBus.defaultInstance;
        }
    }

    /**
     * Builds an EventBus based on the current configuration.
     */
    public EventBus build() {
        return new EventBus(this);
    }

}

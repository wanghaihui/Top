package com.haihui.top.one.mvp.base;

/**
 * View里面有一个Presenter依赖
 */
public interface BaseView<T> {
    void setPresenter(T presenter);
}

package com.haihui.top.one.bitmap.practice;

/**
 * Android自己绘制动画的实现与优化策略
 *
 * 基础知识
 * Android的图形绘制, 主要是基于View这个类实现
 * 每个View的绘制都需要经过onMeasure, onLayout, onDraw三部曲--分别对应到测量大小, 布局, 绘制
 *
 * Android系统为了简化线程开发, 降低应用开发的难度, 将这三个过程都放在应用的主线程(UI线程)中执行, 以保证绘制系统的线程安全
 *
 * 这三个过程通过一个叫Choreographer的定时器来驱动调用更新
 * Choreographer每16ms被vsync这个信号唤醒调用一次, 这有点类似早期的电视机刷新的机制
 * 在Choreographer的doFrame方法中, 通过树状结构存储的ViewGroup, 依次递归的调用到每个View的onMeasure, onLayout, onDraw方法
 * 从而最后将每个View都绘制出来(当然最后还会经过SurfaceFlinger的类来将View合成起来显示, 实际过程很复杂)
 *
 *
 */
public class ViewAndAnimation {

}

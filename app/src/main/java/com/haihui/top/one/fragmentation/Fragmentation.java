package com.haihui.top.one.fragmentation;

/**
 * Created by wanba on 2016/4/26.
 * Fragment Manager
 */
public class Fragmentation {
    // 请求码
    static final String ARG_REQUEST_CODE = "arg_request_code";
    // 结果码
    static final String ARG_RESULT_CODE = "arg_result_code";
    // 结果Bundle
    static final String ARG_RESULT_BUNDLE = "arg_result_bundle";
    // 是否是Root
    static final String ARG_IS_ROOT = "arg_is_root";

    // 添加
    public static final int TYPE_ADD = 0;
    // 添加完成
    public static final int TYPE_ADD_FINISH = 1;

    // 点击空白时间
    private static final int CLICK_SPACE_TIME = 400;

    private long mCurrentTime;



}

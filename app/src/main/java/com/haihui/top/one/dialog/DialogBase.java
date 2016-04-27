package com.haihui.top.one.dialog;

import android.app.Dialog;
import android.content.DialogInterface;

import com.haihui.top.one.dialog.internal.MDRootLayout;

/**
 * Created by wanba on 2016/4/23.
 */
class DialogBase extends Dialog implements DialogInterface.OnShowListener {

    protected MDRootLayout view;
    private OnShowListener mShowListener;


}

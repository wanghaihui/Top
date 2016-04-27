package com.haihui.top.one.dialog.internal;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wanba on 2016/4/23.
 * This is the top level view for all MaterialDialogs
 * It handles the layout of:
 * titleFrame (md_stub_titleframe)
 * content (text, custom view, listview, etc)
 * buttonDefault... (either stacked or horizontal)
 */
public class MDRootLayout extends ViewGroup {

    private View mTitleBar;
    private View mContent;

    private static final int INDEX_NEUTRAL = 0;
    private static final int INDEX_NEGATIVE = 1;
    private static final int INDEX_POSITIVE = 2;

    private boolean mDrawTopDivider = false;
    private boolean mDrawBottomDivider = false;

    private final MDButton[]
}

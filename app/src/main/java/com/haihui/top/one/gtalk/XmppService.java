package com.haihui.top.one.gtalk;

import android.app.Service;
import android.content.BroadcastReceiver;

/**
 * 基础服务
 */
public class XmppService extends Service {

    private static XmppService instance = null;

    /**
     * A bit of(一点) a hack to allow global receivers to know whether or not the service is running,
     * and therefore whether to tell the service about some events.
     */
    public static boolean isRunning = false;

    // 监听器是否活着
    private static boolean listenersActive = false;

    private static XmppManager xmppManager;

    // Xmpp连接改变接收器
    private static BroadcastReceiver xmppConChangeReceiver;


}

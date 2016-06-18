package com.haihui.top.one.gtalk;

/**
 * 基础管理器
 */
public class XmppManager {
    // 以下为连接状态

    // 未连接
    public static final int DISCONNECTED = 1;
    // 连接中--A "transient(短暂的, 转瞬即逝的)" state -- will only be CONNECTING during a call to start()
    public static final int CONNECTING = 2;
    // 已经连接
    public static final int CONNECTED = 3;
    // A "transient" state -- will only be DISCONNECTING during a call to stop()
    public static final int DISCONNECTING = 4;
    // This state means we are waiting for a retry(重试) attempt etc, mostly because a connection went down(停止)
    public static final int WAITING_TO_CONNECT = 5;
    // We are waiting for a valid data connection
    public static final int WAITING_FOR_NETWORK = 6;

    private static XmppManager xmppManager = null;
    // 重用连接数
    private static int reusedConnectionCount = 0;
    // 新的连接数
    private static int newConnectionCount = 0;
    // 连接配置
    private static ConnectionConfiguration

}

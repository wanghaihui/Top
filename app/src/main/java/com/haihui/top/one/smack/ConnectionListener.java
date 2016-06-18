package com.haihui.top.one.smack;

/**
 * Interface that allows for(考虑到) implementing classes(子类或者实现类) to listen for(监听) connection closing(连接关闭)
 * and reconnection(重连) events.
 * Listeners are registered with XMPPConnection objects.
 *
 * @see XMPPConnection#addConnectionListener
 * @see XMPPConnection#removeConnectionListener
 */
public interface ConnectionListener {

    /**
     * Notification that the connection has been successfully connected to the remote endpoint (e.g. the XMPP server)
     *
     * Note that the connection is likely not yet authenticated(可能没身份认证) and therefore(因此) only limited operations
     * like registering an account may be possible.
     *
     * @param connection the XMPPConnection which successfully connected to its endpoint.
     */
    void connected(XMPPConnection connection);

    /**
     * Notification that the connection has been authenticated(被认证)
     *
     * @param connection the XMPPConnection which successfully authenticated
     * @param resumed (恢复或重新开始) true if a previous XMPP session's stream(XMPP 会话流) was resumed(被恢复)
     */
    void authenticated(XMPPConnection connection, boolean resumed);

    /**
     * Notification that the connection was closed normally(连接被正常关闭).
     */
    void connectionClosed();

    /**
     * Notification that the connection was closed due to an exception.
     * When abruptly(突然的) disconnected it is possible for the connection to try reconnecting(重连) to the server
     * @param e the exception
     */
    void connectionClosedOnError(Exception e);

    /**
     * The connection has reconnected successfully to the server.
     * Connections will reconnect to the server when the previous socket connection was abruptly closed.
     */
    void reconnectionSuccessful();

    ////////////////////////////////////////////////////////////////////////////////////////////////

    // The next two methods must only be invoked by ReconnectionManager

    /**
     * The connection will retry to reconnect in the specified number of seconds.
     *
     * Note: This method is only called if ReconnectionManager#isAutomaticReconnectEnabled() returns true, i.e.
     * only when the reconnection manager is enabled for the connection.
     *
     * @param seconds remaining seconds before attempting a reconnection.
     */
    void reconnectingIn(int seconds);

    /**
     * An attempt to connect to the server has failed.
     * The connection will keep trying reconnecting to the server in a moment.
     *
     * Note: This method is only called if ReconnectionManager#isAutomaticReconnectEnabled() returns true, i.e.
     * only when the reconnection manager is enabled for the connection.
     *
     * @param e the exception that caused the reconnection to fail
     */
    void reconnectionFailed(Exception e);
}

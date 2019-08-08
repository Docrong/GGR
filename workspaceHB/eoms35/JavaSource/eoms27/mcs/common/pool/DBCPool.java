/**
 * @(#)DBCPool Copyright (c) 2001 MCS. All Rights Reserved.
 * @author Yuanlei
 * @version 1.0
 * @date 15Apr2002
 * # 此模块为数据库连接池，当一个实例初始化时，其绑定一个连接池清理线程
 * # 是从 DBCDriver 取得 DBConnection 的后台实现
 */
package mcs.common.pool;

import java.util.*;
import java.sql.*;
import java.io.*;

/**
 * 此模块为数据库连接池，当一个实例初始化时，其绑定一个连接池清理线程,是从 DBCDriver 取得 DBConnection 的后台实现<br>
 */
public class DBCPool {
    private Vector conns = null;
    private String url, user, pwd;
    private long timeout = 0;
    private long timestamp = 0;
    private ConnReaper reaper = null;
    private int poolsize = 0;
    Env env = null;
    int i = 0;

    public DBCPool(String url, String user, String pwd) {
        this.url = url;
        this.user = user;
        this.pwd = pwd;
    }

    /**
     * 设置环境变量
     */
    public void setEnv(Env uniqueEnv) {
        this.env = uniqueEnv;
        init();
    }

    /**
     * 初始化连接池
     */
    public void init() {
        this.timeout = env.connTimeOut;
        this.poolsize = env.maxConns;
        conns = new Vector(poolsize);
        reaper = new ConnReaper(this, env);
        reaper.start(); //启动连接监控线程
    }

    /**
     * 清理连接池中的死连接
     */
    public synchronized void reapConns() {
        long stale = System.currentTimeMillis() - timeout;
        Enumeration connlist = conns.elements();
        while ((connlist != null) && (connlist.hasMoreElements())) {
            DBConnection conn = (DBConnection) connlist.nextElement();
            if ((conn.inUse()) && (stale > conn.getLastUse()) && (!conn.validate())) removeConn(conn);
        }
    }

    /**
     * 清除连接池中的每一条连接
     */
    public synchronized void closeConns() {
        Enumeration connlist = conns.elements();
        while ((connlist != null) && (connlist.hasMoreElements())) {
            DBConnection conn = (DBConnection) connlist.nextElement();
            removeConn(conn);
        }
    }

    /**
     * 清除一条连接
     */
    private synchronized void removeConn(DBConnection conn) {
        conns.removeElement(conn);
    }

    /**
     * 提出得到一条连接
     */
    public synchronized Connection getConn() throws SQLException {
        timestamp = System.currentTimeMillis();
        DBConnection conn = null;
        for (int i = 0; i < conns.size(); i++) {
            conn = (DBConnection) conns.elementAt(i);
            if (conn.lease()) {
                return conn;
            }
        }
        if (conns.size() < poolsize) {
            Connection connNew = DriverManager.getConnection(url, user, pwd);
            conn = new DBConnection(connNew, this);
            conn.lease();
            conns.addElement(conn);
        }
        /**/
        System.err.println("第" + i + "次连接池<" + env.alias + " url" + url + ">中的连接数" + conns.size());
        i++;
        return conn;
    }

    /**
     * 把不用的连接返还给连接池
     */
    public synchronized void returnConn(DBConnection conn) {
        timestamp = System.currentTimeMillis();
        conn.expireLease();
    }

    /**
     * 得到上次使用的时间
     */
    public long getLastUse() {
        return timestamp;
    }


}

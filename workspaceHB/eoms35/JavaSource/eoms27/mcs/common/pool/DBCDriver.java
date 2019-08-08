/**
 * @(#)DBCDriver Copyright (c) 2001 MCS. All Rights Reserved.
 * @author Yuanlei
 * @version 1.0
 * @date 15Apr2002
 * # 此模块为 Driver 的包装类，其作用是向DriverManager注册一个连接池DBCPool
 */
package mcs.common.pool;

import java.util.*;
import java.sql.*;

/**
 * 此模块为 Driver 的包装类，其作用是向DriverManager注册一个连接池DBCPool<br>
 */
public class DBCDriver implements Driver {
    public String URL_PREFIX = "jdbc:";
    public final int MAJOR_VERSION = 1;
    public final int MINOR_VERSION = 0;
    public DBCPool pool;
    private long timestamp = 0;
    private long timeout = 0;
    private Env env = null;

    public DBCDriver(String driver, String url, String user, String pwd) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        /**/
        System.err.println("the driver:" + driver);
        DriverManager.registerDriver(this);
        //确认env.driver所对应的 JDBC Driver 已存在于CLASSPATH中

        Class.forName(driver).newInstance();
        pool = new DBCPool(url, user, pwd); //启动标示为url的数据库连接池
    }

    public void setEnv(Env env) {
        this.env = env;
        URL_PREFIX += env.alias + ":";
        pool.setEnv(env);
        timeout = env.poolTimeOut;
    }

    public Connection connect(String url, Properties props) throws SQLException {
        if (!url.startsWith(URL_PREFIX)) return null;
        timestamp = System.currentTimeMillis();
        //modify by yuanlei 2002-06-19
        //original: return pool.getConn();
        //modified: below
        Connection conn = pool.getConn();
        if (conn != null) {
            return conn;
        } else {
            //建立一个连接请求代理，让代理去排队等待可用连接。
            try {
                ConnAgent qca = new ConnAgent(pool);
                conn = qca.getConn();
                return conn;
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error:" + e);
            }
        }
        return conn;
    }

    /**
     * 检查连接池是否超时来判断是否删除连接池，返回值为是否需要拆毁该 Driver
     */
    public boolean reapPool() {
        //参数为-1表示连接池永远保持
        if (env.poolTimeOut == -1) return false;
        //确认连接池超时
        long stale = System.currentTimeMillis() - timeout;
        if (stale > pool.getLastUse()) {
            pool.closeConns();
            pool = null;
            //连接池已经拆毁
            return true;
        } else return false;
    }

    public void destroy() throws SQLException {
        DriverManager.deregisterDriver(this);
    }

    public boolean acceptsURL(String url) {
        return url.startsWith(URL_PREFIX);
    }

    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    public int getMinorVersion() {
        return MINOR_VERSION;
    }

    public DriverPropertyInfo[] getPropertyInfo(String str, Properties props) {
        return new DriverPropertyInfo[0];
    }

    public boolean jdbcCompliant() {
        return false;
    }


}

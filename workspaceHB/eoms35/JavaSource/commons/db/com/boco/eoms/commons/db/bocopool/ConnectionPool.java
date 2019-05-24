/**
 * Title: com.boco.eoms.commons.db.ConnectionPool Description:
 * 数据库连接池管理类，支持对一个或多个由属性文件定义的特定 数据库连接池的访问．客户端可以调用getInstance()方法访问本类的唯一实例．
 * Copyright: Copyright (c) 2007 Company: boco modify records: CONTENT DATE BY
 * WHO
 */
package com.boco.eoms.commons.db.bocopool;

// standard library
import java.sql.*;
import java.util.*;

// eoms private library
import com.boco.eoms.commons.db.util.PropertyFile;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class ConnectionPool {

    static private ConnectionPool instance;

    static private int clients;

    static private String defaultpoolname;

    private Vector drivers = new Vector();

    private Hashtable pools = new Hashtable();

    private PropertyFile prop = PropertyFile.getInstance();

    /**
     * 取得数据库连接池管理类的唯一实例
     * 
     * @return ConnectionPool 特定数据库连接池
     */
    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }

        clients++;
        return instance;
    }

    /**
     * Default constructor, outside of this class cannot be able to call.
     */
    private ConnectionPool() {
        init();
    }

    /**
     * Get one database connection pool from pools(connection Hashtable) using
     * defaultpoolname property, if not exist, return null;
     * 
     * @return DBConnectionPool, one valid connection pool
     */
    public DBConnectionPool getPool() {
        return getPool(defaultpoolname);
    }

    /**
     * Get one database connection pool from pools(connection Hashtable) using
     * input parameter(_strPoolName), if not exist, return null.
     * 
     * @return DBConnectionPool, one valid connection pool
     */
    public DBConnectionPool getPool(String _strPoolName) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(_strPoolName);
        if (pool != null) {
            return pool;
        }

        return null;
    }

    /**
     * Get one BocoConnection type database connection from default connection
     * pool. if default pool not exist, return null, else can call public
     * interface from this connection pool return one BocoConnection type
     * object.
     * 
     * @return BocoConnection, return a valid database connection or null
     */
    public BocoConnection getConnection() {
        return getConnection(defaultpoolname);
    }

    /**
     * Get one BocoConnection type database connection from default connection
     * pool. if default pool not exist, return null, else can call public
     * interface from this connection pool return one BocoConnection object. if
     * _strPoolName pool not exist, return null, else can call public interface
     * from this connection pool return one BocoConnection type object.
     * 
     * @param _strPoolName,
     *            identify one specific connection pool
     * @return BocoConnection, return a valid database connection or null
     */
    public BocoConnection getConnection(String _strPoolName) {
        DBConnectionPool pool = getPool(_strPoolName);
        if (pool != null) {
            return pool.getConnection();
        }

        return null;
    }

    /**
     * This function is like to getConnection(String, int), only difference is
     * to get database connection from default connection pool.
     * 
     * @param int
     *            time,
     * @return BocoConnection,
     */
    public BocoConnection getConnection(int time) {
        return getConnection(defaultpoolname, time);
    }

    /**
     * Get one BocoConnection object from special connection pool, which name is
     * first input parameter, the second input parameter identity how long time
     * client will wait, if at that time dont has connection usable.
     * 
     * @param String
     *            name, the name of database connection pool
     * @prama int time, max wait time for one connection
     * @return BocoConnection, return one database connection from pool, if dont
     *         get one, then return null.
     */
    public BocoConnection getConnection(String name, int time) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(name);
        if (pool != null) {
            return pool.getConnection(time);
        }

        return null;
    }

    /**
     * Release a BocoConnection type object to default database connection pool,
     * then other client can use this database connection from this default
     * database connection pool. In fact, set BocoConnection type object's
     * status not be using.
     * 
     * @param _objConn,
     *            one BocoConnection object in default pool
     */
    public void returnConnection(BocoConnection _objConn) {
        returnConnection(defaultpoolname, _objConn);
    }

    /**
     * Release a BocoConnection type object to sepcific database connection
     * pool, then other client can use this database connection from this
     * specific database connection pool. In fact, set BocoConnection type
     * object's status not be using.
     * 
     * @param _strPoolName,
     *            one connection pool
     * @param _objConn,
     *            one BocoConnection object in _strPoolName connection pool
     */
    public void returnConnection(String _strPoolName, BocoConnection _objConn) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(_strPoolName);
        if (pool != null) {
            pool.freeConnection(_objConn);
        }
    }

    /**
     * Release all connection pools, and unregister registed JDBC divers.
     */
    public synchronized void closeAllConnection() {
        /*
         * if there is only one client using current pool, who can execute this
         * function
         */
        if (--clients != 0) return;

        // release all connection pools
        Enumeration allPools = pools.elements();
        while (allPools.hasMoreElements()) {
            DBConnectionPool pool = (DBConnectionPool) allPools.nextElement();
            pool.release();
        }

        unregisterDrivers(); // unregister all drivers
    }

    // private function block

    /**
     * 读取属性文件完成JDBC驱动加载特定数据库连接池的初始化
     */
    private void init() {
        defaultpoolname = prop.getProperty("DefaultPoolName");
        registerDrivers();
        createPools();
    }

    /**
     * 注册JDBC驱动
     */
    private void registerDrivers() {
        String driverClasses = prop.getProperty("DriverClasses");
        StringTokenizer st = new StringTokenizer(driverClasses);

        while (st.hasMoreElements()) {
            String driverClassName = st.nextToken().trim();
            BocoLog.debug(this, "注册JDBC驱动：[" + driverClassName + "]--开始");

            try {
                Driver _objDriver = (Driver) Class.forName(driverClassName)
                        .newInstance();
                DriverManager.registerDriver(_objDriver);
                drivers.addElement(_objDriver);
                BocoLog.debug(this, "注册JDBC驱动：[" + driverClassName + "]--完成");
            }
            catch (Exception e) {
                BocoLog.error(this, "注册JDBC驱动：[" + driverClassName + "]--失败");
            }
        }
    }

    /**
     * 注销全部的JDBC驱动
     */
    private void unregisterDrivers() {
        Iterator _objDrivers = drivers.iterator();

        while (_objDrivers.hasNext()) {
            Driver driver = (Driver) _objDrivers.next();
            try {
                DriverManager.deregisterDriver(driver);
            }
            catch (SQLException e) {
                BocoLog.error(this, "无法注销下列已注册的JDBC驱动："
                        + driver.getClass().getName());
            }
        }
    }

    /**
     * 根据制定属性创建数据库连接池实例
     */
    private void createPools() {
        String poolnames = prop.getProperty("PoolNames", "Boco");
        StringTokenizer st = new StringTokenizer(poolnames);

        // 建立不同数据库连接池
        while (st.hasMoreElements()) {
            String poolName = st.nextToken().trim();
            BocoLog.debug(this, "创建特定数据库连接池：[" + poolName + "]--准备");

            String url = prop.getProperty(poolName + "Url");
            String user = prop.getProperty(poolName + "User");
            String password = prop.getProperty(poolName + "Password");
            String charset = prop.getProperty(poolName + "Charset");

            int maxconn = StaticMethod.getIntValue(prop.getProperty(poolName
                    + "Maxconn"), 10);
            int minconn = StaticMethod.getIntValue(prop.getProperty(poolName
                    + "Minconn"), 5);
            int maxusecount = StaticMethod.getIntValue(prop
                    .getProperty(poolName + "Maxusecount"), 20);
            int maxidletime = StaticMethod.getIntValue(prop
                    .getProperty(poolName + "Maxidletime"), 30);
            int maxalivetime = StaticMethod.getIntValue(prop
                    .getProperty(poolName + "Maxalivetime"), 3);

            DBConnectionPool pool = new DBConnectionPool(poolName, url, user,
                    password, charset, maxconn, minconn, maxusecount,
                    maxidletime, maxalivetime);
            pools.put(poolName, pool);
            BocoLog.debug(this, "创建特定数据库连接池：[" + poolName + "]--完成");

            BocoLog.debug(this, "建立数据库连接池：" + url);
        }
    }

}

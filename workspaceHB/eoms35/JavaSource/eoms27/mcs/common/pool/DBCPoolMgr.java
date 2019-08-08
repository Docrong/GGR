/**
 * @(#)DBCPoolMgr Copyright (c) 2001 MCS. All Rights Reserved.
 * @author Yuanlei
 * @version 1.0
 * @date 15Apr2002
 * # 此模块为连接池管理器类，管理一个连接池的散列PoolHash，返回外罩类请求的相应连接池的连接
 */
package mcs.common.pool;

import java.util.*;
import java.sql.*;

/**
 * 此模块为连接池管理器类，管理一个连接池的散列PoolHash，返回外罩类请求的相应连接池的连接<br>
 */
public class DBCPoolMgr {
    static Hashtable poolTbl = new Hashtable();
    public static mcs.common.pool.DBCPoolMgr instance;
    static PoolHash pools = null;
    static String defaultPool = "";
    PoolReaper reaper = null;

    /**
     * 得到一个DBCPoolMgr的实例
     */
    public static synchronized mcs.common.pool.DBCPoolMgr getInstance() {
        if (instance == null) instance = new DBCPoolMgr();
        return instance;
    }

    private DBCPoolMgr() {
        init();
    }

    /**
     * 初始化连接池管理器
     */
    private void init() {
        ConfReader.init();
        pools = ConfReader.getPoolHash();
        for (int i = 0; i < pools.size(); i++) {
            PoolHash ph = pools.get(i);
            /**/
            System.err.println("content key" + ph.getKey());
            /**/
            System.err.println(ph.getEnv().alias + " " + ph.getEnv().defaultPool);
            if (ph.getEnv().defaultPool.equals("1")) {
                /**/
                System.err.println("进入了if");
                defaultPool = ph.getEnv().alias;
                break;
            }
        }
        reaper = new PoolReaper(this);
        reaper.start(); //启动连接监控线程
    }

    /**
     * 从默认的连接池得到一条连接
     */
    public synchronized Connection getConnection() throws ClassNotFoundException, InstantiationException, SQLException, IllegalAccessException {
        /**/
        System.err.println("default=" + defaultPool);
        return getConnection(defaultPool);
    }

    /**
     * 根据连接池的别名从特定的的连接池得到一条连接
     */
    public synchronized Connection getConnection(String poolAlias) throws InstantiationException, ClassNotFoundException, SQLException, IllegalAccessException {
        Driver drvr = pools.getPool(poolAlias);
        Env env = pools.getEnv(poolAlias);
        Connection conn = null;
        if (drvr == null && env != null) // 连接池还未初始化
        {
            /**/
            System.err.println("getConnection enter if 1");
            //初始化并注册连接池
            DBCDriver drv = new DBCDriver(env.driver, env.url, env.user, env.password);
            drv.setEnv(env);
            pools.setPool(poolAlias, drv);

            return DriverManager.getConnection("jdbc:" + poolAlias + ":DBCPool");

        } else if (drvr != null && env != null) // 连接池已经初始化
        {
            /**/
            System.err.println("getConnection enter if 2");
            return DriverManager.getConnection("jdbc:" + poolAlias + ":JDCPool");
        } else if (env == null)//无理请求
        {
            /**/
            System.err.println("getConnection enter if 3");
            return null;
        }
        return null;
    }

    /**
     * 清理超时连接池
     */
    public void reapPools() throws SQLException {
        for (int i = 0; i < pools.size(); i++) {
            DBCDriver drv = (pools.get(i)).getPool();
            if (drv.reapPool()) {
                drv.destroy();
                drv = null;
            }
        }
    }

}

package com.db;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import java.sql.Connection;


public class MySQLConnection {

    private static final int FREE_AND_USED_RATIO = 1;

    private static String m_url = "";
    private static String m_user = "";
    private static String m_password = "";

    private static LinkedList freeConn = new LinkedList();
    private static LinkedList usedConn = new LinkedList();

    static {
        //当类加载时，自动加载并初始化JDBC驱动
        initDriver();
    }

    /**
     * 加载JDBC驱动
     */
    private static void initDriver() {
        Driver driver = null;
        try {
            driver = (Driver) Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            installDriver(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册安装JDBC驱动
     */
    private static void installDriver(Driver driver) {
        try {
            DriverManager.registerDriver(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized Connection get() throws SQLException {
        Connection conn = null;
        if (freeConn.isEmpty()) {
            conn = (Connection) DriverManager.getConnection(m_url, m_user, m_password);
        } else {
            //取出一个连接并且检查是否关闭，防止用户误操作调用了conn.close();
            while (true) {
                conn = (Connection) freeConn.removeFirst();
                if (!conn.isClosed()) {
                    break;
                } else {
                    if (!freeConn.isEmpty()) {
                        continue;
                    } else {
                        conn = (Connection) DriverManager.getConnection(m_url, m_user, m_password);
                        break;
                    }
                }
            }
        }
        usedConn.add(conn);
        return conn;
    }

    public synchronized void close(Connection conn) throws SQLException {
        usedConn.remove(conn);
        if (freeConn.size() > usedConn.size() * FREE_AND_USED_RATIO) {
            conn.close();
        } else {
            freeConn.add(conn);
        }
    }

    /**
     * 创建一个新的连接
     *
     * @return 创建的连接
     * @throws SQLException
     */
    protected Connection getNewConnection() throws SQLException {
        Connection conn = (Connection) DriverManager.getConnection(m_url, m_user, m_password);
        return conn;
    }

    public String getUrl() {
        return m_url;
    }

    public void setUrl(String mUrl) {
        m_url = mUrl;
    }

    public String getUserName() {
        return m_user;
    }

    public void setUserName(String mUser) {
        m_user = mUser;
    }

    public String getPassword() {
        return m_password;
    }

    public void setPassword(String mPassword) {
        m_password = mPassword;
    }

    public void setConnect(String url, String username, String password) {
        this.setUrl(url);
        this.setUserName(username);
        this.setPassword(password);
    }

    public void printDebug() {
        System.out.println("Free conn num : " + freeConn.size() + "\tUsed conn num : " + usedConn.size());
    }
}

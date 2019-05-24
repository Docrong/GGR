package com.boco.eoms.commons.db.bocopool;

/**
 * Title: Description: Copyright: Copyright (c) 2002 Company:
 * 
 * @author
 * @version 1.0
 */

// java standard library
import java.sql.*;
import java.util.*;
import java.util.Date;

// eoms class
import com.boco.eoms.commons.loging.BocoLog;

public class DBConnectionPool {

    private Vector connections = new Vector();

    private Vector closeConnections = new Vector();

    private boolean defaultAutoCommit = false;

    private int maxconn;

    private int minconn;

    private int maxusecount;

    private int maxidletime;

    private int maxalivetime;

    private String poolname;

    private String password;

    private String URL;

    private String user;

    private String charset;

    private ConnCheckerTimer connChk;

    private boolean checkdbable = true;

    private long mtime = 30000;

    /**
     * 构造函数
     * 
     * @param poolname,
     *            连接池名称
     * @param URL,
     *            数据库的JDBC URL
     * @param user,
     *            数据库帐户
     * @param password,
     *            数据库帐户密码
     * @param charset,
     *            连接的字符集
     * @param maxconn,
     *            此连接池允许建立的最大连接数
     * @param minconn,
     *            次连接池允许建立的最小连接数
     * @param maxusecount,
     *            数据库连接允许使用的最大次数
     * @param maxidletime,
     *            连接池允许的最大空闲时间（分钟）
     * @param maxalivetime,
     *            连接在一个请求中允许的最大持续时间
     */
    public DBConnectionPool(String poolname, String URL, String user,
            String password, String charset, int maxconn, int minconn,
            int maxusecount, int maxidletime, int maxalivetime) {
        this.poolname = poolname;
        this.URL = URL;
        this.user = user;
        this.password = password;
        this.charset = charset;
        this.maxconn = maxconn;
        this.minconn = minconn;
        this.maxusecount = maxusecount;
        this.maxidletime = maxidletime;
        this.maxalivetime = maxalivetime;

        for (int i = 0; i < minconn; i++) {
            newConnection();
        }
        connChk = new ConnCheckerTimer(this, mtime);
        connChk.start();
    }

    /**
     * @param defaultAutoCommit
     * @param maxconn
     * @param minconn
     * @param maxusecount
     * @param maxidletime
     * @param maxalivetime
     * @param poolname
     * @param password
     * @param url
     * @param user
     * @param charset
     */
    public DBConnectionPool(boolean defaultAutoCommit, int maxconn,
            int minconn, int maxusecount, int maxidletime, int maxalivetime,
            String poolname, String password, String url, String user,
            String charset) {
        super();
        this.defaultAutoCommit = defaultAutoCommit;
        this.maxconn = maxconn;
        this.minconn = minconn;
        this.maxusecount = maxusecount;
        this.maxidletime = maxidletime;
        this.maxalivetime = maxalivetime;
        this.poolname = poolname;
        this.password = password;
        URL = url;
        this.user = user;
        this.charset = charset;
    }

    /**
     * 将不再使用的连接返还给当前连接池
     * 
     * @param BocoConnectio,
     *            客户端释放的数据库连接
     */
    public synchronized void freeConnection(BocoConnection conn) {
        if (conn == null) return;
        conn.close();
    }

    /**
     * 从连接池中获得一个可用的连接，如果没有空闲的连接且当前连接数小于最大连接数两限制，
     * 则创建新的连接。如果原来登记为可用的连接不再有效，则从池中删除该连接，然后循环尝试 新的可用连接。
     */
    public synchronized BocoConnection getConnection() {
        BocoConnection conn = null;
        Enumeration connList = connections.elements();
        int i = 1;
        while ((connList != null) && (connList.hasMoreElements())) {
            conn = (BocoConnection) connList.nextElement();
            if (conn.isRelease() && isUsable(conn)) {
                connections.removeElement(conn);
                connections.addElement(conn);
                conn.useConnection();
                return conn;
            }
            i++;
        }

        if (maxconn == 0 || connections.size() < maxconn || conn.isRelease()) {
            conn = newConnection();
            conn.useConnection();
        }
        return conn;
    }

    /**
     * 从连接池中获取可用连接，可以指定客户程序能够等待的最长时间，参见前方法。
     * 
     * @param int
     *            timeout, 以分钟计算等待时间
     * @return BocoConnection
     */
    public synchronized BocoConnection getConnection(int timeout) {
        long startTime = new Date().getTime();
        BocoConnection conn;
        while ((conn = getConnection()) == null) {
            try {
                wait(500);
            }
            catch (InterruptedException e) {
            }
            if ((new Date().getTime() - startTime) >= timeout * 60 * 1000)
                return null;
        }
        return conn;
    }

    /**
     * 检查当前连接池中的连接
     */
    public synchronized void checkDBPool() {
        // BocoLog.debug(this, "检查前"+ poolname + "池中的是否检查" + checkdbable);
        if (!checkdbable) return;
        long idletime = System.currentTimeMillis() - maxidletime * 60 * 1000;
        long activetime = System.currentTimeMillis() - maxalivetime * 60 * 1000;

        for (int i = connections.size() - 1; i >= 0; i--) {
            BocoConnection conn = (BocoConnection) connections.get(i);
            boolean flagUseLongActinveTime = conn.isFlagUseLongActiveTime();
            if (flagUseLongActinveTime)
                activetime = System.currentTimeMillis() - 30 * 60 * 1000;

            if (conn.isUse() && (activetime > conn.getTimeStamp())) {
                closeConnection(conn);
                // 如果有省市出现连接池的性能问题，就将注释去掉，进行分析
                // BocoLog.debug(this, "正在关闭连接。。。"+conn.getTimeStamp());
            }
            else if ((idletime > conn.getTimeStamp() || !conn.isValidate())
                    && conn.isRelease())
                closeConnection(conn);
            else if (conn.useCount() > maxusecount && conn.isRelease())
                closeConnection(conn);
        }
        // BocoLog.info(this, "检查后" + poolname + "池中的连接数为："+
        // connections.size());
        for (int i = connections.size(); i < minconn; i++) {
            if (newConnection() == null) {
                break;
            }
        }
        // BocoLog.info(this, "检查更新后"+ poolname + "池中的连接数为：" +
        // connections.size());
    }

    /**
     * 关闭当前连接池中所有的连接
     */
    public synchronized void release() {
        Enumeration connList = connections.elements();
        while (connList.hasMoreElements()) {
            BocoConnection conn = (BocoConnection) connList.nextElement();
            conn.release();
        }
        connections.removeAllElements();
    }

    /**
     * 返回当前池中所有连接
     */
    public Iterator getConnectionIterator() {
        return connections.iterator();
    }

    /**
     * 返回当前池中的连接数
     */
    public int getConnectionSize() {
        return connections.size();
    }

    /**
     * 返回可用连接数
     */
    public int getUsableConnSize() {
        int _iReturn = 0;

        BocoConnection _objConn = null;
        Iterator _objIterator = getConnectionIterator();
        while (_objIterator.hasNext()) {
            _objConn = (BocoConnection) _objIterator.next();
            if (_objConn.isValidate() && !_objConn.isUse()) {
                _iReturn++;
            }
        }
        return _iReturn;
    }

    /**
     * 返回关闭池中所有连接
     */
    public Iterator getCloseConnectionIterator() {
        return closeConnections.iterator();
    }

    /**
     * 返回关闭池中的连接数
     */
    public int getCloseConnectionSize() {
        return closeConnections.size();
    }

    /**
     * 重新启动当前数据库连接池
     */
    public boolean restartPool() {
        try {
            release();
            for (int i = 0; i < minconn; i++) {
                newConnection();
            }
            checkdbable = true;
            connChk.exit();
            connChk = null;
            BocoLog.debug(this, "重新启动连接池成功！" + checkdbable);

            connChk = new ConnCheckerTimer(this, mtime);
            connChk.start();
        }
        catch (Exception e) {
            BocoLog.error(this, "重新启动连接池失败: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 重置当前数据库连接池的检测器
     */
    public boolean resetCheckTimer() {
        try {
            connChk.exit();
            connChk = null;
            connChk = new ConnCheckerTimer(this, mtime);
            connChk.start();
        }
        catch (Exception e) {
            BocoLog.error(this, "重置检查器失败: " + checkdbable);
            return false;
        }

        BocoLog.debug(this, "重置检查器成功！" + checkdbable);
        return true;
    }

    /**
     * 创建新的数据库连接实例，放入当前数据库连接池中
     */
    private BocoConnection newConnection() {
        Connection conn = null;
        BocoConnection Bococonn = null;
        try {
            if (user == null)
                conn = DriverManager.getConnection(URL);
            else {
                Properties props = new Properties();
                props.put("user", user);
                props.put("password", password);
                props.put("CHARSET", charset);
                conn = DriverManager.getConnection(URL, props);
            }
            if (URL.indexOf("oracle") > 0) {
                Statement stmt = conn.createStatement();
                stmt
                        .execute("alter session set NLS_DATE_FORMAT='YYYY-MM-DD HH24:MI:SS'");
                BocoLog.debug(this, "oracle NLS_DATE_FORMAT changed!");
                conn.commit();
            }
            if (URL.indexOf("informix") > 0) {
                conn.setTransactionIsolation(1);
            }
        }
        catch (SQLException e) {
            BocoLog.error(this, "无法创建下列URL的连接：" + URL);
            return null;
        }

        Bococonn = new BocoConnection(defaultAutoCommit, conn);
        connections.addElement(Bococonn);
        return Bococonn;
    }

    /**
     * 检查连接是否可以使用，如果不可使用，则去掉
     */
    private boolean isUsable(BocoConnection conn) {
        long idletime = System.currentTimeMillis() - maxidletime * 60 * 1000;
        if (!conn.isValidate() || idletime > conn.getTimeStamp()
                || conn.useCount() > maxusecount) {
            closeConnection(conn);
            return false;
        }
        return true;
    }

    /**
     * Remove special BocoConnection from current connection pool.
     */
    private void closeConnection(BocoConnection conn) {
        try {
            ConnCloseThread cct = new ConnCloseThread(conn, closeConnections);
            cct.start();
            connections.removeElement(conn);
            // 如果有省市出现连接池的性能问题，就将注释去掉，进行分析
            // BocoLog.trace(this,275,"conn"+conn.getTimeStamp()+"从池中去掉");
            conn = null;
        }
        catch (Exception e) {
            BocoLog.error(this, "关闭连接失败！");
        }
    }

    public String getPoolName() {
        return poolname;
    }

    public boolean getTimerExitFlag() {
        return connChk.getFlag();
    }

    public String getTimerAlive() {
        String active = "isAlive :" + connChk.isAlive() + "|| isInterrupted"
                + connChk.isInterrupted() + " ||" + connChk + "|| ExitFlag"
                + connChk.getFlag();
        return active;
    }

    /**
     * @return the charset
     */
    public String getCharset() {
        return charset;
    }

    /**
     * @param charset
     *            the charset to set
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }

    /**
     * @return the maxalivetime
     */
    public int getMaxalivetime() {
        return maxalivetime;
    }

    /**
     * @param maxalivetime
     *            the maxalivetime to set
     */
    public void setMaxalivetime(int maxalivetime) {
        this.maxalivetime = maxalivetime;
    }

    /**
     * @return the maxconn
     */
    public int getMaxconn() {
        return maxconn;
    }

    /**
     * @param maxconn
     *            the maxconn to set
     */
    public void setMaxconn(int maxconn) {
        this.maxconn = maxconn;
    }

    /**
     * @return the maxidletime
     */
    public int getMaxidletime() {
        return maxidletime;
    }

    /**
     * @param maxidletime
     *            the maxidletime to set
     */
    public void setMaxidletime(int maxidletime) {
        this.maxidletime = maxidletime;
    }

    /**
     * @return the maxusecount
     */
    public int getMaxusecount() {
        return maxusecount;
    }

    /**
     * @param maxusecount
     *            the maxusecount to set
     */
    public void setMaxusecount(int maxusecount) {
        this.maxusecount = maxusecount;
    }

    /**
     * @return the minconn
     */
    public int getMinconn() {
        return minconn;
    }

    /**
     * @param minconn
     *            the minconn to set
     */
    public void setMinconn(int minconn) {
        this.minconn = minconn;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the poolname
     */
    public String getPoolname() {
        return poolname;
    }

    /**
     * @param poolname
     *            the poolname to set
     */
    public void setPoolname(String poolname) {
        this.poolname = poolname;
    }

    /**
     * @return the uRL
     */
    public String getURL() {
        return URL;
    }

    /**
     * @param url
     *            the uRL to set
     */
    public void setURL(String url) {
        URL = url;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the checkdbable
     */
    public boolean isCheckdbable() {
        return checkdbable;
    }

    /**
     * @param checkdbable
     *            the checkdbable to set
     */
    public void setCheckdbable(boolean checkdbable) {
        this.checkdbable = checkdbable;
    }

    /**
     * @return the defaultAutoCommit
     */
    public boolean isDefaultAutoCommit() {
        return defaultAutoCommit;
    }

    /**
     * @param defaultAutoCommit
     *            the defaultAutoCommit to set
     */
    public void setDefaultAutoCommit(boolean defaultAutoCommit) {
        this.defaultAutoCommit = defaultAutoCommit;
    }
}

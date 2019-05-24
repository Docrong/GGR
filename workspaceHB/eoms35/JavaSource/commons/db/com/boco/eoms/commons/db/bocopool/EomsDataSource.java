/**
 * com.boco.eoms.commons.db.bocopool.EomsDataSource.java
 */
package com.boco.eoms.commons.db.bocopool;

// java standard library
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

// java extend library
import javax.sql.DataSource;

import com.boco.eoms.commons.loging.BocoLog;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class EomsDataSource implements DataSource {

	// properties
	private boolean defaultAutoCommit;

	private String poolName;

	private String url;

	private String user;

	private String password;

	private String charset;

	private int maxConn;

	private int minConn;

	private int maxUseCounts;

	private int maxIdleTime;

	private int maxAliveTime;

	private int maxWaitTime = -1;

	private String driverClassName;

	// JDBC椹卞姩
	private Driver _objDriver = null;

	// 鏁版嵁搴撹繛鎺ユ睜
	private static DBConnectionPool dbConnPool = null;

	/**
	 * @see Default Constructor
	 */
	public EomsDataSource() {
	}

	/**
	 * @param defaultAutoCommit
	 * @param poolName
	 * @param url
	 * @param user
	 * @param password
	 * @param charset
	 * @param maxConn
	 * @param minConn
	 * @param maxUseCounts
	 * @param maxIdleTime
	 * @param maxAliveTime
	 * @param maxWaitTime
	 * @param driverClassName
	 */
	public EomsDataSource(boolean defaultAutoCommit, String poolName,
			String url, String user, String password, String charset,
			int maxConn, int minConn, int maxUseCounts, int maxIdleTime,
			int maxAliveTime, int maxWaitTime, String driverClassName) {
		super();
		this.defaultAutoCommit = defaultAutoCommit;
		this.poolName = poolName;
		this.url = url;
		this.user = user;
		this.password = password;
		this.charset = charset;
		this.maxConn = maxConn;
		this.minConn = minConn;
		this.maxUseCounts = maxUseCounts;
		this.maxIdleTime = maxIdleTime;
		this.maxAliveTime = maxAliveTime;
		this.maxWaitTime = maxWaitTime;
		this.driverClassName = driverClassName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getConnection()
	 */
	public synchronized Connection getConnection() throws SQLException {
		Connection _objConn = null;

		if (dbConnPool == null) {
			createDBPool();
		}

		if (maxWaitTime <= 0) {
			_objConn = dbConnPool.getConnection();
		} else {
			_objConn = dbConnPool.getConnection(maxWaitTime);
		}

		return _objConn;
	}

	/**
	 * 鏈€澶氶噸澶嶆寚瀹氭鏁版潵鑾峰彇鏁版嵁搴撹繛鎺?
	 * 
	 * @param int _iCounts 鎸囧畾娆℃暟
	 * @return Connection 鏁版嵁搴撹繛鎺?
	 */
	public synchronized Connection getConnection(int _iCounts) {
		Connection _objConn = null;

		if (dbConnPool == null) {
			createDBPool();
		}

		for (int i=0; i< _iCounts; i++) {
			if (maxWaitTime <= 0) {
				_objConn = dbConnPool.getConnection();
			} else {
				_objConn = dbConnPool.getConnection(maxWaitTime);
			}

			if (_objConn != null) {
				return _objConn;
			}
		}

		return _objConn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getConnection(java.lang.String,
	 *      java.lang.String)
	 */
	public synchronized Connection getConnection(String arg0, String arg1)
			throws SQLException {
		BocoLog.info(this, "Sorry! Dont support this interface.");
		return null;
	}

	/**
	 * @see Destory Database Connection Pool
	 */
	public void close() {
		if (dbConnPool != null) {
			dbConnPool.release();
		}

		unregisterDriver();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getLogWriter()
	 */
	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getLoginTimeout()
	 */
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#setLogWriter(java.io.PrintWriter)
	 */
	public void setLogWriter(PrintWriter arg0) throws SQLException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#setLoginTimeout(int)
	 */
	public void setLoginTimeout(int arg0) throws SQLException {
	}

	/**
	 * @return the poolName
	 */
	public String getPoolName() {
		return poolName;
	}

	/**
	 * @param poolName
	 *            the poolName to set
	 */
	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
	 * @return the maxConn
	 */
	public int getMaxConn() {
		return maxConn;
	}

	/**
	 * @param maxConn
	 *            the maxConn to set
	 */
	public void setMaxConn(int maxConn) {
		this.maxConn = maxConn;
	}

	/**
	 * @return the minConn
	 */
	public int getMinConn() {
		return minConn;
	}

	/**
	 * @param minConn
	 *            the minConn to set
	 */
	public void setMinConn(int minConn) {
		this.minConn = minConn;
	}

	/**
	 * @return the maxUseCounts
	 */
	public int getMaxUseCounts() {
		return maxUseCounts;
	}

	/**
	 * @param maxUseCounts
	 *            the maxUseCounts to set
	 */
	public void setMaxUseCounts(int maxUseCounts) {
		this.maxUseCounts = maxUseCounts;
	}

	/**
	 * @return the maxIdleTime
	 */
	public int getMaxIdleTime() {
		return maxIdleTime;
	}

	/**
	 * @param maxIdleTime
	 *            the maxIdleTime to set
	 */
	public void setMaxIdleTime(int maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
	}

	/**
	 * @return the maxAliveTime
	 */
	public int getMaxAliveTime() {
		return maxAliveTime;
	}

	/**
	 * @param maxAliveTime
	 *            the maxAliveTime to set
	 */
	public void setMaxAliveTime(int maxAliveTime) {
		this.maxAliveTime = maxAliveTime;
	}

	/**
	 * @return the maxWaitTime
	 */
	public int getMaxWaitTime() {
		return maxWaitTime;
	}

	/**
	 * @param maxWaitTime
	 *            the maxWaitTime to set
	 */
	public void setMaxWaitTime(int maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}

	/**
	 * @return the driverClassName
	 */
	public String getDriverClassName() {
		return driverClassName;
	}

	/**
	 * @param driverClassName
	 *            the driverClassName to set
	 */
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
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

	/**
	 * 娉ㄥ唽JDBC椹卞姩
	 */
	private void registerDriver() {
		String _strDriverClasse = this.getDriverClassName();

		BocoLog.debug(this, "娉ㄥ唽JDBC椹卞姩锛歔" + _strDriverClasse + "]--寮€濮?");

		try {
			_objDriver = (Driver) Class.forName(_strDriverClasse).newInstance();
			DriverManager.registerDriver(_objDriver);
			BocoLog.debug(this, "娉ㄥ唽JDBC椹卞姩锛歔" + _strDriverClasse + "]--瀹屾垚");
		} catch (Exception e) {
			BocoLog.error(this, "娉ㄥ唽JDBC椹卞姩锛歔" + _strDriverClasse + "]--澶辫触");
		}
	}

	/**
	 * 娉ㄩ攢鍏ㄩ儴鐨凧DBC椹卞姩
	 */
	private void unregisterDriver() {
		if (_objDriver != null) {
			try {
				DriverManager.deregisterDriver(_objDriver);
			} catch (SQLException e) {
				BocoLog.error(this, "鏃犳硶娉ㄩ攢涓嬪垪宸叉敞鍐岀殑JDBC椹卞姩锛?"
						+ _objDriver.getClass().getName());
			}
		}
	}

	/**
	 * 鍒涘缓鏁版嵁搴撹繛鎺ユ睜
	 */
	private void createDBPool() {
		registerDriver();

		String _strPoolName = this.getPoolName();
		BocoLog.debug(this, "鍒涘缓鐗瑰畾鏁版嵁搴撹繛鎺ユ睜锛歔" + _strPoolName + "]--鍑嗗");

		String _strUrl = this.getUrl();
		String _strUser = this.getUser();
		String _strPassword = this.getPassword();
		String _strCharset = this.getCharset();
		int _iMaxConn = this.getMaxConn();
		int _iMinConn = this.getMinConn();
		int _iMaxUseCounts = this.getMaxUseCounts();
		int _iMaxIdleTime = this.getMaxIdleTime();
		int _iMaxAliveTime = this.getMaxAliveTime();
		boolean _bDefaultAutoCommit = this.isDefaultAutoCommit();

		dbConnPool = new DBConnectionPool(_bDefaultAutoCommit, _iMaxConn,
				_iMinConn, _iMaxUseCounts, _iMaxIdleTime, _iMaxAliveTime,
				_strPoolName, _strPassword, _strUrl, _strUser, _strCharset);

		BocoLog.debug(this, "鍒涘缓鐗瑰畾鏁版嵁搴撹繛鎺ユ睜锛歔" + _strPoolName + "]--瀹屾垚");
		BocoLog.debug(this, "寤虹珛鏁版嵁搴撹繛鎺ユ睜锛? + _strUrl");
	}

	public boolean isWrapperFor(Class arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	public Object unwrap(Class arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

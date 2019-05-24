package com.boco.eoms.db.util;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:
 * @author
 * @version 1.0
 */

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Map;

import com.boco.eoms.common.log.BocoLog;

public class BocoConnection implements Connection {

	private Connection conn;

	private boolean inuse;

	private long timestamp; // 锟斤拷锟揭伙拷锟絣锟斤拷时锟斤拷锟?

	private int ConnectionUseCount; // l锟斤拷锟斤拷使锟矫达拷锟斤拷

	private String tempsql = "";

	private boolean flagUseLongActiveTime = false;

	public BocoConnection(Connection conn) {
		this.conn = conn;
		this.timestamp = System.currentTimeMillis();
		this.ConnectionUseCount = 0;
		this.inuse = false;
		try {
			this.setAutoCommit(false);
		} catch (Exception e) {
			BocoLog.error(this, 35, "connection error", e);
		}
	}

	synchronized void useConnection() {
		this.inuse = true;
		timestamp = System.currentTimeMillis();
		ConnectionUseCount++;
	}

	public synchronized boolean isRelease() {
		if (inuse)
			return false;
		return true;
	}

	public boolean isUse() {
		return inuse;
	}

	public int useCount() {
		return ConnectionUseCount;
	}

	public boolean isValidate() {
		try {
			conn.getMetaData();
		} catch (SQLException e) {
			BocoLog.error(this, 63, "validate error", e);
			return false;
		}
		return true;
	}

	public long getTimeStamp() {
		return timestamp;
	}

	void release() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			BocoLog.error(this, 78, "l锟斤拷锟酵放达拷锟斤拷!", e);
		}
	}

	public void close() {
		timestamp = System.currentTimeMillis();
		// this.tempsql = "";
		this.flagUseLongActiveTime = false;
		this.inuse = false;
		// 关闭连接
		this.release();
	}

	protected Connection getConnection() {
		return conn;
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
		this.tempsql = sql;
		// System.out.println("************************************");
		// System.out.println(sql);
		return conn.prepareStatement(sql);
	}

	public java.util.Map getTypeMap() throws SQLException {
		return conn.getTypeMap();
	}

	public void setTypeMap(Map map) throws SQLException {
		conn.setTypeMap(map);
	}

	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return conn.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	public CallableStatement prepareCall(String sql) throws SQLException {
		this.tempsql = sql;
		return conn.prepareCall(sql);
	}

	public CallableStatement prepareCall(java.lang.String sql,
			int resultSetType, int resultSetConcurrency) throws SQLException {
		return conn.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	public Statement createStatement() throws SQLException {
		return conn.createStatement();
	}

	public Statement createStatement(int resultSetType, int resultSetConcurrency)
			throws SQLException {
		return conn.createStatement(resultSetType, resultSetConcurrency);
	}

	public String nativeSQL(String sql) throws SQLException {
		return conn.nativeSQL(sql);
	}

	public void setAutoCommit(boolean autoCommit) throws SQLException {
		conn.setAutoCommit(autoCommit);
	}

	public boolean getAutoCommit() throws SQLException {
		return conn.getAutoCommit();
	}

	public void commit() throws SQLException {
		conn.commit();
	}

	public void rollback() throws SQLException {
		conn.rollback();
	}

	public boolean isClosed() throws SQLException {
		return conn.isClosed();
	}

	public DatabaseMetaData getMetaData() throws SQLException {
		return conn.getMetaData();
	}

	public void setReadOnly(boolean readOnly) throws SQLException {
		conn.setReadOnly(readOnly);
	}

	public boolean isReadOnly() throws SQLException {
		return conn.isReadOnly();
	}

	public void setCatalog(String catalog) throws SQLException {
		conn.setCatalog(catalog);
	}

	public String getCatalog() throws SQLException {
		return conn.getCatalog();
	}

	public void setTransactionIsolation(int level) throws SQLException {
		conn.setTransactionIsolation(level);
	}

	public int getTransactionIsolation() throws SQLException {
		return conn.getTransactionIsolation();
	}

	public SQLWarning getWarnings() throws SQLException {
		return conn.getWarnings();
	}

	public void clearWarnings() throws SQLException {
		conn.clearWarnings();
	}

	public String getSQL() {
		return this.tempsql;
	}

	public void setHoldability(int holdability) throws SQLException {
		/** @todo: Implement this java.sql.Connection method */
		throw new java.lang.UnsupportedOperationException(
				"Method setHoldability() not yet implemented.");
	}

	public int getHoldability() throws SQLException {
		/** @todo: Implement this java.sql.Connection method */
		throw new java.lang.UnsupportedOperationException(
				"Method getHoldability() not yet implemented.");
	}

	public Statement createStatement(int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		/** @todo: Implement this java.sql.Connection method */
		throw new java.lang.UnsupportedOperationException(
				"Method createStatement() not yet implemented.");
	}

	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		/** @todo: Implement this java.sql.Connection method */
		throw new java.lang.UnsupportedOperationException(
				"Method prepareStatement() not yet implemented.");
	}

	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		/** @todo: Implement this java.sql.Connection method */
		throw new java.lang.UnsupportedOperationException(
				"Method prepareCall() not yet implemented.");
	}

	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
			throws SQLException {
		/** @todo: Implement this java.sql.Connection method */
		throw new java.lang.UnsupportedOperationException(
				"Method prepareStatement() not yet implemented.");
	}

	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
			throws SQLException {
		/** @todo: Implement this java.sql.Connection method */
		throw new java.lang.UnsupportedOperationException(
				"Method prepareStatement() not yet implemented.");
	}

	public PreparedStatement prepareStatement(String sql, String[] columnNames)
			throws SQLException {
		/** @todo: Implement this java.sql.Connection method */
		throw new java.lang.UnsupportedOperationException(
				"Method prepareStatement() not yet implemented.");
	}

	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		/** @todo Implement this java.sql.Connection method */
		throw new java.lang.UnsupportedOperationException(
				"Method releaseSavepoint() not yet implemented.");
	}

	public void rollback(Savepoint savepoint) throws SQLException {
		/** @todo Implement this java.sql.Connection method */
		throw new java.lang.UnsupportedOperationException(
				"Method rollback() not yet implemented.");
	}

	public Savepoint setSavepoint(String name) throws SQLException {
		/** @todo Implement this java.sql.Connection method */
		throw new java.lang.UnsupportedOperationException(
				"Method setSavepoint() not yet implemented.");
	}

	public Savepoint setSavepoint() throws SQLException {
		/** @todo Implement this java.sql.Connection method */
		throw new java.lang.UnsupportedOperationException(
				"Method setSavepoint() not yet implemented.");
	}

	public boolean isFlagUseLongActiveTime() {
		return flagUseLongActiveTime;
	}

	public void setFlagUseLongActiveTime(boolean flagUseLongActiveTime) {
		this.flagUseLongActiveTime = flagUseLongActiveTime;
	}

}

/**
 * Title: com.boco.eoms.commons.db.bocopool.BocoConnection Description: self
 * defined database connection, which implements java.sql.Connection interface.
 * Copyright: Copyright (c) 2007 Company: boco
 */
package com.boco.eoms.commons.db.bocopool;

// java standard library

import java.util.Map;
import java.sql.Savepoint;
import java.sql.SQLWarning;
import java.sql.DatabaseMetaData;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// eoms class
import com.boco.eoms.commons.loging.BocoLog;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class BocoConnection implements Connection {

    // private properties block

    /* Identify one physical database connection */
    private Connection conn;

    /* Identify current database connection wether or not be using */
    private boolean inuse;

    /* Identify the time of last connection on conn(private property) */
    private long timestamp;

    /* Identify how many times user have connected to conn(private property) */
    private int ConnectionUseCount;

    /* Identify SQL clause will or have be executed */
    private String tempsql = "";

    /* Identify current database connection wether or not need time check */
    private boolean flagUseLongActiveTime = false;

    /**
     * minimal constructor
     *
     * @param Connection, one physical database connection
     */
    public BocoConnection(Connection conn) {
        this.conn = conn;
        this.timestamp = System.currentTimeMillis();
        this.ConnectionUseCount = 0;
        this.inuse = false;
        try {
            this.setAutoCommit(false); // 锟斤拷璁剧疆鏄剧ず浜嬪姟鎺у埗
        } catch (Exception e) {
            BocoLog.error(this, e.getMessage());
        }
    }

    /**
     * Constructor
     *
     * @param boolean     _bAutoCommit
     * @param Connection, one physical database connection
     */
    public BocoConnection(boolean _bAutoCommit, Connection conn) {
        this.conn = conn;
        this.timestamp = System.currentTimeMillis();
        this.ConnectionUseCount = 0;
        this.inuse = false;
        try {
            this.setAutoCommit(_bAutoCommit);
        } catch (Exception e) {
            BocoLog.error(this, e.getMessage());
        }
    }

    /**
     * Set current database connection's status, including wether or not be
     * using, last connecting time and incremnt times be used. This function is
     * synchronized one, to add one object type lock.
     */
    synchronized void useConnection() {
        this.inuse = true;
        timestamp = System.currentTimeMillis();
        ConnectionUseCount++;
    }

    public synchronized boolean isRelease() {
        boolean _bReturn = true;
        if (inuse) {
            _bReturn = false;
        }

        return _bReturn;
    }

    public boolean isUse() {
        return inuse;
    }

    public int useCount() {
        return ConnectionUseCount;
    }

    public boolean isValidate() {
        boolean _bReturn = false;
        try {
            conn.getMetaData();
            _bReturn = true;
        } catch (SQLException e) {
            BocoLog.error(this, e.getMessage());
        }

        return _bReturn;
    }

    public long getTimeStamp() {
        return timestamp;
    }

    /**
     * com.boco.eoms.commons.db.bocopool.BocoConnection.release() Description:
     * Close current database connection.
     */
    public void release() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            BocoLog.error(this, "杩炴帴閲婃斁閿欒: " + e.getMessage());
        }
    }

    /**
     * com.boco.eoms.commons.db.bocopool.BocoConnection.close() Description: Set
     * current connection not be using, that is mean other client can get this
     * connection from connection pool and use.
     *
     * @return boolean, if be using, return false, else return true.
     */
    public void close() {
        timestamp = System.currentTimeMillis();
        this.flagUseLongActiveTime = false;
        this.inuse = false;
    }

    protected Connection getConnection() {
        return conn;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        this.tempsql = sql;
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

    /**
     * @return the connectionUseCount
     */
    public int getConnectionUseCount() {
        return ConnectionUseCount;
    }

    /**
     * @param connectionUseCount the connectionUseCount to set
     */
    public void setConnectionUseCount(int connectionUseCount) {
        ConnectionUseCount = connectionUseCount;
    }

}

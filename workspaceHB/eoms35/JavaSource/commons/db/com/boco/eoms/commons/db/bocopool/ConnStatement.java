package com.boco.eoms.commons.db.bocopool;

import java.sql.*;
import java.math.*;
import java.io.*;

import javax.sql.*;
import javax.naming.*;

import com.boco.eoms.commons.db.util.PropertyFile;
import com.boco.eoms.base.util.StaticMethod;

public class ConnStatement {

    private Connection conn = null;

    private PreparedStatement ps = null;

    private ResultSet rt = null;

    private ResultSetMetaData rs = null;

    private PropertyFile prop = PropertyFile.getInstance();

    private int connectionType = StaticMethod.getIntValue(prop
            .getProperty("ConnectionType"), 1);

    private String jdbcDriverName = prop.getProperty("DriverClassName");

    private String jdbcUrl = prop.getProperty("url");

    private ConnectionPool pool;

    public ConnStatement() {
    }

    public void setStatementSql(String sqlstring) throws SQLException {
        // if(conn == null) conn = getConnection() ;
        if (conn != null) conn.close();
        conn = getConnection();
        if (ps != null) ps.close();
        ps = conn.prepareStatement(sqlstring);
    }

    public void setStatementSql(String sqlstring, boolean flag)
            throws SQLException {
        // if(conn == null) conn = getConnection() ;
        if (conn != null) conn.close();
        conn = getConnection();
        if (ps != null) ps.close();
        ps = conn.prepareStatement(sqlstring);
    }

    public void setAutoCommit(boolean bool) throws SQLException {
        conn.setAutoCommit(bool);
    }

    public void commit() throws SQLException {
        conn.commit();
    }

    public void rollback() throws SQLException {
        conn.rollback();
    }

    private Connection getConnection() {
        try {
            if (connectionType == 1) {
                Class.forName(jdbcDriverName);
                conn = DriverManager.getConnection(this.jdbcUrl);
            }
            else if (connectionType == 2) {
                Context env = (Context) new InitialContext()
                        .lookup("java:comp/env");
                DataSource pool = (DataSource) env.lookup("jdbc/boco");
                conn = pool.getConnection();
            }
            else {
                pool = ConnectionPool.getInstance();
                conn = pool.getConnection();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }

            return conn;


    }

    public void setString(int sequenceid, String value) throws SQLException {
        ps.setString(sequenceid, value);
    }

    public void setInt(int sequenceid, int value) throws SQLException {
        ps.setInt(sequenceid, value);
    }

    public void setFloat(int sequenceid, float value) throws SQLException {
        ps.setFloat(sequenceid, value);
    }

    public void setDate(int sequenceid, Date value) throws SQLException {
        ps.setDate(sequenceid, value);
    }

    public void setBigDecimal(int sequenceid, BigDecimal value)
            throws SQLException {
        ps.setBigDecimal(sequenceid, value);
    }

    public void setBinaryStream(int sequenceid, InputStream value, int length)
            throws SQLException {
        ps.setBinaryStream(sequenceid, value, length);
    }

    public void setBytes(int sequenceid, byte[] value) throws SQLException {
        ps.setBytes(sequenceid, value);
    }

    public void setCharacterStream(int parameterIndex, Reader value, int length)
            throws SQLException {
        ps.setCharacterStream(parameterIndex, value, length);
    }

    public void setObject(int sequenceid, Object value) throws SQLException {
        ps.setObject(sequenceid, value);
    }

    public void setNull(int sequenceid) throws SQLException {
        ps.setNull(sequenceid, Types.NULL);
    }

    public void executeQuery() throws SQLException {
        rt = ps.executeQuery();
    }

    public int executeUpdate() throws SQLException {
        return ps.executeUpdate();
    }

    public boolean next() throws SQLException {
        return rt.next();
    }

    public void absolute(int row) throws SQLException {
        rt.absolute(row);
    }

    public void afterLast() throws SQLException {
        rt.afterLast();
    }

    public void beforeFirst() throws SQLException {
        rt.beforeFirst();
    }

    public String getString(String key) throws SQLException {
        return rt.getString(key);
    }

    public String getString(int key) throws SQLException {
        return rt.getString(key);
    }

    public int getInt(String key) throws SQLException {
        return rt.getInt(key);
    }

    public int getInt(int key) throws SQLException {
        return rt.getInt(key);
    }

    public float getFloat(String key) throws SQLException {
        return rt.getFloat(key);
    }

    public float getFloat(int key) throws SQLException {
        return rt.getFloat(key);
    }

    public BigDecimal getBigDecimal(int key) throws SQLException {
        return rt.getBigDecimal(key);
    }

    public BigDecimal getBigDecimal(String key) throws SQLException {
        return rt.getBigDecimal(key);
    }

    public InputStream getBinaryStream(int key) throws SQLException {
        return rt.getBinaryStream(key);
    }

    public InputStream getBinaryStream(String key) throws SQLException {
        return rt.getBinaryStream(key);
    }

    public byte[] getBytes(int key) throws SQLException {
        return rt.getBytes(key);
    }

    public byte[] getBytes(String key) throws SQLException {
        return rt.getBytes(key);
    }

    public Reader getCharacterStream(int key) throws SQLException {
        return rt.getCharacterStream(key);
    }

    public Reader getCharacterStream(String key) throws SQLException {
        return rt.getCharacterStream(key);
    }

    public Date getDate(String key) throws SQLException {
        return rt.getDate(key);
    }

    public Object getObject(int key) throws SQLException {
        return rt.getObject(key);
    }

    public Object getObject(String key) throws SQLException {
        return rt.getObject(key);
    }

    public int getColumnCount() throws SQLException {
        if (rs == null) rs = rt.getMetaData();
        return rs.getColumnCount();
    }

    public String getColumnName(int column) throws SQLException {
        if (rs == null) rs = rt.getMetaData();
        return rs.getColumnName(column);
    }

    public int getColumnType(int column) throws SQLException {
        if (rs == null) rs = rt.getMetaData();
        return rs.getColumnType(column);
    }

    public String getColumnTypeName(int column) throws SQLException {
        if (rs == null) rs = rt.getMetaData();
        return rs.getColumnTypeName(column);
    }

    public void close() {
        try {
            if (ps != null) ps.close();
            if (conn != null) {

                conn.close();
            }
        }
        catch (Exception e) {
        }
    }
}
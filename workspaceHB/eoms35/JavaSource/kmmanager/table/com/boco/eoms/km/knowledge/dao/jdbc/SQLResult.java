package com.boco.eoms.km.knowledge.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import oracle.sql.TIMESTAMP;
import oracle.sql.TIMESTAMPLTZ;
import oracle.sql.TIMESTAMPTZ;
import com.boco.eoms.km.core.dataservice.util.BaseUtil;

public class SQLResult {

    private Connection conn = null;
    private ResultSet result = null;
    private ResultSetMetaData resmd = null;

    public SQLResult(Connection aConn, ResultSet aResult, ResultSetMetaData aResmd) {
        this.conn = aConn;
        this.result = aResult;
        this.resmd = aResmd;
    }

    public String getObjectFormResult(int index) throws SQLException {
        Object value = this.result.getObject(index);
        int type = this.resmd.getColumnType(index);
        if (value == null) {
            return "";
        } else if (value instanceof Timestamp) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
        } else if (value instanceof TIMESTAMPLTZ) {
            TIMESTAMPLTZ times = (TIMESTAMPLTZ) value;
            long length = times.getLength();
            if (length > 0) {
                Timestamp timestamp = TIMESTAMPLTZ.toTimestamp(conn, times.getBytes(), Calendar.getInstance());
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
            }
            return "";
        } else if (value instanceof TIMESTAMPTZ) {
            TIMESTAMPTZ times = (TIMESTAMPTZ) value;
            long length = times.getLength();
            if (length > 0) {
                Timestamp timestamp = TIMESTAMPTZ.toTimestamp(conn, times.getBytes());
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
            }
            return "";
        } else if (value instanceof TIMESTAMP) {
            TIMESTAMP times = (TIMESTAMP) value;
            long length = times.getLength();
            if (length > 0) {
                Timestamp timestamp = TIMESTAMP.toTimestamp(times.getBytes());
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
            }
            return "";
        }
        return BaseUtil.convertObjectToString(value, type);
    }
}

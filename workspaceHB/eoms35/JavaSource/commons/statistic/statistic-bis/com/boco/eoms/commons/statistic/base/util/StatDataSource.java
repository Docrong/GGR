package com.boco.eoms.commons.statistic.base.util;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.PrintWriter;

public class StatDataSource implements DataSource {
    private PrintWriter p = new PrintWriter(System.out);

    public StatDataSource() {
//		System.out.println("lxy");
    }

    public Connection getConnection() throws SQLException {
        // TODO Auto-generated method stub
        return com.boco.eoms.db.util.ConnectionPool.getInstance()
                .getConnection();

    }

    public Connection getConnection(String username, String password)
            throws SQLException {
        // TODO Auto-generated method stub
        return com.boco.eoms.db.util.ConnectionPool.getInstance()
                .getConnection();
    }

    public int getLoginTimeout() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    public PrintWriter getLogWriter() throws SQLException {
        // TODO Auto-generated method stub
        return p;
    }

    public void setLoginTimeout(int seconds) throws SQLException {
        // TODO Auto-generated method stub

    }

    public void setLogWriter(PrintWriter out) throws SQLException {
        // TODO Auto-generated method stub
        p = out;
    }

}

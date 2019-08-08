package com.boco.eoms.extra.supplierkpi.report.statistic.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * created at 2007-09-29
 * provide the method to connect to the database
 *
 * @author lzp
 */
public class Conn {
    private Connection connection = null;

    public synchronized Connection getConnection(String driver, String url, String username, String password) {
        try {
            if ((connection == null) || (!connection.isClosed())) {
                try {
                    Class.forName(driver);

                    DriverManager.setLoginTimeout(3);
                    connection = DriverManager.getConnection(url, username, password);


                } catch (SQLException e) {
                    System.out.println("Can't use the provided message create a connection");
                    System.err.println(e);
                } catch (ClassNotFoundException e) {
                    System.out.println("Can't find the Driver Class");
                    System.err.println(e);

                }
            }
        } catch (SQLException e) {
            System.out.println("Connection can't get!");
            System.err.println(e);
            return null;
        }
        return connection;
    }

    /*****  关闭数据库连接   */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection has been closed!");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.out.println(" close con:" + e.getMessage());
            }
        }
        connection = null;
    }

    /***** 关闭结果集 */
    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                System.out.println("ResultSet has been closed!");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.out.println("close rs:" + e.getMessage());
            }
        }
        rs = null;
    }

    /***** 关闭PreparedStatement */
    public static void close(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
                System.out.println("PreparedStataement has been closed");
            } catch (SQLException e) {
                System.out.println("close ps:" + e.getMessage());
            }
        }
        ps = null;
    }

    /***** 关闭Statement *****/
    public static void close(Statement st) {
        if (st != null) {
            try {
                st.close();
                System.out.println("Statement has been closed!");
            } catch (SQLException e) {
                //TODO Auto-generated catch block
                System.out.println("close st:" + e.getMessage());
            }
        }
        st = null;
    }

}

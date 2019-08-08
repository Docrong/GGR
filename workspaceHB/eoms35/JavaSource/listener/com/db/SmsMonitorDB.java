package com.db;

import java.sql.Connection;


public class SmsMonitorDB {

    private static String className = "";
    private static String conURL = "";
    private static String userName = "";
    private static String userPassword = "";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            className = "oracle.jdbc.driver.OracleDriver";
            conURL = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=10.25.0.198)(PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=10.25.0.199)(PORT=1521))(LOAD_BALANCE=yes)(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=rac)(FAILOVER_MODE=(TYPE=SELECT)(METHOD=BASIC)(RETRIES=180)(DELAY=5))))";
            userName = "eomsv35";
            userPassword = "WdL4P#3s34sl34#ls2";
            Class.forName(className).newInstance();
            conn = java.sql.DriverManager.getConnection(conURL, userName, userPassword);
            System.out.println("已连接数据库");
            return conn;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}

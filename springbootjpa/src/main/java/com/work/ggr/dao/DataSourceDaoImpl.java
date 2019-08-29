package com.work.ggr.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author : gr
 * @date : 2019/8/29 11:05
 */
@Repository("dsDao")
public class DataSourceDaoImpl {
    @Autowired
    @Qualifier(value = "mysqlDataSource")
    DataSource dataSource;

    @Autowired
    @Qualifier(value = "mysqlDataSource2")
    DataSource dataSource2;


    private Connection conn;
    private Statement state;
    private ResultSet rs;

    public void testMysql1() {
        try {
            conn = dataSource.getConnection();
            state = conn.createStatement();
            rs = state.executeQuery("select * from person");
            System.out.println("Query from Mysql1");
            if (!rs.next())
                System.out.println("no data");
            while (rs.next()) {
                String col1 = String.valueOf(rs.getObject(1));
                String col2 = String.valueOf(rs.getObject(2));
                String col3 = String.valueOf(rs.getObject(3));
                String col4 = String.valueOf(rs.getObject(4));
                String col5 = String.valueOf(rs.getObject(5));
                String col6 = String.valueOf(rs.getObject(6));
                System.out.println(col1 + "|" + col2 + "|" + col3 + "|" + col4 + "|" + col5 + "|" + col6);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                state.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testMysql2() {
        try {
            conn = dataSource2.getConnection();
            state = conn.createStatement();
            rs = state.executeQuery("select * from person");
            System.out.println("Query from Mysql2");
            if (!rs.next())
                System.out.println("no data");
            while (rs.next()) {
                String col1 = String.valueOf(rs.getObject(1));
                String col2 = String.valueOf(rs.getObject(2));
                String col3 = String.valueOf(rs.getObject(3));
                String col4 = String.valueOf(rs.getObject(4));
                String col5 = String.valueOf(rs.getObject(5));
                String col6 = String.valueOf(rs.getObject(6));
                System.out.println(col1 + "|" + col2 + "|" + col3 + "|" + col4 + "|" + col5 + "|" + col6);
            }
            rs.close();
            state.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                state.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}

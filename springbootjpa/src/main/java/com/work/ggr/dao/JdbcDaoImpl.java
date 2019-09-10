package com.work.ggr.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author : gr
 * @date : 2019/8/29 11:05
 * 通过jdbc的方式直接连接,delete语句不写了,数据库一般没有delete操作
 */
@Repository("dsDao")
public class JdbcDaoImpl {

    DataSource dataSource;


    DataSource dataSource2;

    @Autowired
    public void setDataSource(@Qualifier(value = "mysqlDataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setDataSource2(@Qualifier(value = "mysqlDataSource2") DataSource dataSource2) {
        this.dataSource2 = dataSource2;
    }

    private Connection conn;
    private Statement state;
    private ResultSet rs;

    public void selectMysql1(Map maptj) {
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

    public void insertMysql1(Map maptj) {
        try {
            conn = dataSource.getConnection();
            state = conn.createStatement();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String currentTime = new Date().toString();
            String insertSql = " insert into person (id,username,created,remark)values('" + uuid + "','ggr','" + currentTime + "','insertByJdbc')";
            int back = state.executeUpdate(insertSql);
            System.out.println("insert result back:" + back);
            state.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                state.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateMysql1(Map maptj) {
        try {
            conn = dataSource.getConnection();
            state = conn.createStatement();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String currentTime = new Date().toString();
            String insertSql = " update person set username='updateaction' where id='7be77afd0c1d4a51bdbe2ba28ab320f4'";
            int back = state.executeUpdate(insertSql);
            System.out.println("insert result back:" + back);
            state.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                state.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void selectMysql2(Map maptj) {
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

    public void insertMysql2(Map maptj) {
        try {
            conn = dataSource2.getConnection();
            state = conn.createStatement();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String currentTime = new Date().toString();
            String insertSql = " insert into person (id,username,created,remark)values('" + uuid + "','ggr','" + currentTime + "','inserByJdbc')";
            int back = state.executeUpdate(insertSql);
            System.out.println("insert result back:" + back);
            state.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                state.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

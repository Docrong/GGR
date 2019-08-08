/**
 * Used in mcm project
 * 这是公共文件,多个模块均要使用,请勿在未通知其他人的情况下进行任何改动
 */
package mcs.common.db;


import java.sql.*;
//import mcs.common.pool.*;
import com.boco.eoms.db.util.*;


public class DBSQL extends java.lang.Object {
    Connection connection;
    Statement statement = null;
    ConnectionPool jdbc;
    //DBCPoolMgr jdbc ;
    //JdbcMgr    jdbc;
    //JdbcMgr2 jdbc;


    public DBSQL()
            throws Exception {
        //jdbc=new JdbcMgr();
        jdbc = ConnectionPool.getInstance();
        connection = jdbc.getConnection();
    }

    public DBSQL(Connection conn)
            throws Exception {
        //jdbc=new JdbcMgr();
        this.connection = conn;
    }

    public DBSQL(String alias) throws Exception {
        jdbc = ConnectionPool.getInstance();
        connection = jdbc.getConnection(alias);
    }


    public ResultSet executeSelect(String SelectSQL) {

        ResultSet resultset = null;
        if ((SelectSQL != null) && (!SelectSQL.equals(""))) {
            try {
                statement = connection.createStatement();
                resultset = statement.executeQuery(SelectSQL);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }


        }
        return resultset;
    }

    public int executeUpdate(String UpdateSQL) {
        String SQL = UpdateSQL;
        Statement statement = null;
        int updateRows = -1;
        try {
            statement = connection.createStatement();
            updateRows = statement.executeUpdate(SQL);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        //释放内存
        finally {
            try {

                if (statement != null) {
                    statement.close();
                    statement = null;
                }
            } catch (Exception e2) {
            }
        }
        return updateRows;
    }


    public int executeInsert(String InsertSQL) {
        return executeUpdate(InsertSQL);
    }

    public int executeDelete(String DeleteSQL) {
        return executeUpdate(DeleteSQL);
    }

    public boolean executeNonQuery(String[] NonQuerySql) {
        Statement statement = null;
        boolean updateRows = false;
        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            for (int i = 0; i < NonQuerySql.length; i++)
                statement.executeUpdate(NonQuerySql[i]);

            System.out.println("********************** begin commit *****************");
            connection.commit();
            updateRows = true;
        } catch (SQLException ex) {
            System.err.println(ex);
            try {
                System.out.println("****************** Begin RollBack ! ***************");
                connection.rollback();
            } catch (SQLException ee) {
                System.out.println("SQLException :" + ee.getMessage());
            }
        }
        //释放内存
        finally {
            try {
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
            } catch (Exception e2) {
            }
        }
        return updateRows;
    }

    public void setAutoCommit(boolean autocommit) {
        try {
            connection.setAutoCommit(autocommit);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected void finalize() throws Throwable {
        closeConnection();
    }

    /*****************************************************************************
     * Close the result set and release resources
     */
    public void closeConnection() {

        try {

            if (statement != null) {
                statement.close();
                statement = null;
            }
            connection.close();
            //jdbc.release(connection);
        } catch (SQLException e) {
            System.err.println("closeConnection is failure!" + e.getMessage());
        }
    }

    public static void main(String args[]) {
        try {
            DBSQL db = new DBSQL();
            String[] Sql = {"Insert into temp_ly values(4,'aaadasf')", "insert into temp_class(pi_id,cc_name) values(2,'fdsaf',fdas);"};
            db.executeNonQuery(Sql);
        } catch (Exception e) {
            System.out.println("Error !" + e.getMessage());
        }
    }
	  /*
    try{
    DBSQL db = new DBSQL();
    String sql = "insert into temp_class values('1','test',?)";
    System.out.println("sql is :" + sql);
    PreparedStatement pstmt = null;
    pstmt = db.connection.prepareStatement(sql);
    pstmt.setString(1,"test text insert");
    if (pstmt.executeUpdate()==0)
      System.out.println("insert fail!!!!!!!");
    else
      System.out.println("insert successs!");
    }
    catch(Exception e)
    {
      System.out.println("the exception is :" + e.getMessage());

    }
	*/
}















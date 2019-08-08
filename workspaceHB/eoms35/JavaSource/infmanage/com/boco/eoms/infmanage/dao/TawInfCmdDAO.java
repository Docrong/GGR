package com.boco.eoms.infmanage.dao;

import java.sql.*;
import java.util.*;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.infmanage.model.*;

public class TawInfCmdDAO extends DAO {
    public Connection con = null;

    public TawInfCmdDAO(com.boco.eoms.db.util.ConnectionPool ds) {
        super(ds);
    }

    /**
     * 在命令库表中插入新的记录
     *
     * @param _tawInfMaintainer 命令库信息对象
     * @throws SQLException
     */
    public void insert(TawInfCmd tawInfCmd) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(true);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "";
        sql = "INSERT INTO taw_inf_cmd (dept_id,cmd_swich,cmd_id,cmd_name,cmd_param,param_scope,cmd_des)"
                + " VALUES (?,?,?,?,?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, tawInfCmd.getDeptId());
            pstmt.setString(2, tawInfCmd.getCmdSwich());
            pstmt.setString(3, tawInfCmd.getCmdId());
            pstmt.setString(4, tawInfCmd.getCmdName());
            pstmt.setString(5, tawInfCmd.getCmdParam());
            pstmt.setString(6, tawInfCmd.getParamScope());
            pstmt.setString(7, tawInfCmd.getCmdDes());
            pstmt.executeUpdate();
            close(pstmt);
            conn.setAutoCommit(false);
        } catch (Exception sqle) {
            //conn.rollback();
            sqle.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt = null;
            }
            if (conn != null) {
                conn = null;
            }
        }
    }

    /**
     * 根据记录ID删除命令库资料信息
     *
     * @param id 　记录ID
     * @throws SQLException
     */
    public void delete(int id) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;

        try {
            String sql = "";
            sql = "DELETE FROM taw_inf_cmd WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn = null;
            }
            if (pstmt != null) {
                pstmt = null;
            }
        }
    }

    /**
     * 获得命令库资料信息列表
     *
     * @param condition 　查询条件
     * @param offset
     * @param length
     * @throws SQLException
     * @return　信息列表
     */
    public List getList(String condition, int offset, int length) throws
            SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            sql = "select * from taw_inf_cmd";
            sql = sql + condition;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (offset > 0) {
                int i = 0;
                while (i++ < offset) {
                    rs.next();
                }
            }

            int recCount = 0;
            while ((recCount++ < length) && rs.next()) {
                TawInfCmd tawInfCmd = new TawInfCmd();
                tawInfCmd.setDeptId(rs.getInt("dept_id"));
                populate(tawInfCmd, rs);

                list.add(tawInfCmd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
            close(conn);
        }
        return list;
    }

    /**
     * 获取查询到的记录的长度
     *
     * @param tableName
     * @param condition
     * @throws SQLException
     * @return　size
     */
    public int getSize(String tableName, String condition) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int size = 0;

        try {
            String sql = "";
            sql = "select count(*) from " + tableName + " " + condition;
            System.out.println("sql===" + sql);
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            rs.next();
            size = rs.getInt(1);
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (rs != null) {
                rs = null;
            }
            if (pstmt != null) {
                pstmt = null;
            }
            if (conn != null) {
                conn = null;
            }
        }
        return size;
    }

    /**
     * 根据记录id获取命令库资料信息对象
     *
     * @param id 　记录ID
     * @return
     * @throws SQLException
     */
    public TawInfCmd getById(int id) throws
            SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        TawInfCmd tawInfCmd = new TawInfCmd();
        try {
            String sql = "";
            sql = "select * from taw_inf_cmd where id=?";

            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                populate(tawInfCmd, rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn = null;
            }
            if (pstmt != null) {
                pstmt = null;
            }
            if (rs != null) {
                rs = null;
            }
        }
        return tawInfCmd;
    }

    /**
     * 根据记录ID修改命令库资料信息
     *
     * @param tawInfCmd
     * @throws SQLException
     */
    public void update(TawInfCmd tawInfCmd) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;

        try {
            String sql = "";
            sql =
                    "UPDATE taw_inf_cmd SET cmd_swich=?,cmd_id=?,cmd_name=?,cmd_param=?,param_scope=?,cmd_des=?" +
                            " WHERE id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tawInfCmd.getCmdSwich());
            pstmt.setString(2, tawInfCmd.getCmdId());
            pstmt.setString(3, tawInfCmd.getCmdName());
            pstmt.setString(4, tawInfCmd.getCmdParam());
            pstmt.setString(5, tawInfCmd.getParamScope());
            pstmt.setString(6, tawInfCmd.getCmdDes());
            pstmt.setInt(7, tawInfCmd.getId());

            pstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn = null;
            }
            if (pstmt != null) {
                pstmt = null;
            }
        }
    }
}

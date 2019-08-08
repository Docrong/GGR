package com.boco.eoms.sparepart.dao;

import java.util.List;
import java.util.ArrayList;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import com.boco.eoms.db.util.ConnectionPool;
import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.sparepart.model.TawClassMsg;
import com.boco.eoms.sparepart.controller.TawClassMsgForm;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 *
 * @author HAO
 * @version 2.0
 */

public class TawClassMsgDAO
        extends DAO {
    public TawClassMsgDAO(ConnectionPool ds) {
        super(ds);
    }

    /**
     * @param SQL
     * @return
     * @throws SQLException
     */
    public List getClassMsg(String SQL) {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql =
                    "SELECT  id ,note,cname,parent_id,deleted FROM taw_sp_classmsg " +
                            SQL;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                TawClassMsg tawClassMsg = new TawClassMsg();
                populate(tawClassMsg, rs);
                list.add(tawClassMsg);
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return list;
    }

    public void insertClassMsg(String cname, String note, String parentId, String i) {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;

        String sql =
                "INSERT INTO taw_sp_classmsg (cname,note,parent_id,deleted) VALUES (?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, cname);
            pstmt.setString(2, note);
            pstmt.setInt(3, Integer.parseInt(parentId));
            pstmt.setString(4, i);
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            rollback(conn);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public void deleteClassMsg(String id) {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;

        String sql =
                "delete from  taw_sp_classmsg where id=?";
        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, Integer.parseInt(id));
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            rollback(conn);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    public String getClassMsgStatid(String cname) throws SQLException {
        String id = "";
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "select id from  taw_sp_classmsg where parent_id=10 and cname='" + cname + "'";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                id = rs.getString(1);
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return id;
    }


    public void updateClassMsg(TawClassMsgForm form) {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;

        String sql =
                "update taw_sp_classmsg set cname=?,note=? where id=?";
        try {
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, form.getCname());
            pstmt.setString(2, form.getNote());
            pstmt.setInt(3, Integer.parseInt(form.getId()));
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            rollback(conn);
        } finally {
            close(pstmt);
            close(conn);
        }
    }

    /**
     * @param department
     * @param necode
     * @param objectname
     * @return 三级的id字符之和
     * @see 根据中文名称返回id之和，名称可以是中文名称或编码
     */
    public String getClassMsgId(String department, String necode,
                                String objectname) {
        String id = "";
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql =
                    "select a.id,b.id,c.id from taw_sp_tree a,taw_sp_tree b," +
                            "taw_sp_tree c where c.cname=? and c.id=b.parent_id and " +
                            " b.cname=? and b.id=a.parent_id and a.cname=? ";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, department);
            pstmt.setString(2, necode);
            pstmt.setString(3, objectname);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                String a = rs.getString(1);
                String b = rs.getString(2);
                String c = rs.getString(3);
                id = a + "_" + b + "_" + c;
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return id;
    }

    public String getTwoClassMsgId(String department, String necode) {
        String id = "";
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql =
                    "select a.id,b.id from taw_sp_classmsg a,taw_sp_classmsg b" +
                            " where a.cname=? and a.id=b.parent_id and " +
                            " b.cname=?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, department);
            pstmt.setString(2, necode);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                String a = rs.getString(1);
                String b = rs.getString(2);
                id = a + "_" + b;
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return id;
    }

    public List getClassMsgList(String condition, int offset, int length) {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "select id,parent_id,cname,note,deleted from taw_sp_classmsg " +
                    condition;
            pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = pstmt.executeQuery();
            while (offset > 0) {
                rs.next();
                offset--;
            }

            int recCount = 0;
            while ((recCount++ < length) && rs.next()) {
                TawClassMsg tawClassMsg = new TawClassMsg();
                this.populate(tawClassMsg, rs);
                list.add(tawClassMsg);
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return list;
    }

    /*得到state的值*/
    public List getStateList() throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int i = 0;
        try {
            conn = ds.getConnection();
            String sql =
                    "SELECT  id from taw_sp_classmsg where parent_id=10 ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getObject(++i));
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return list;
    }

}

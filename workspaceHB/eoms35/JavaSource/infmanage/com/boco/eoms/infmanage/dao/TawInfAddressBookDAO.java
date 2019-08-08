package com.boco.eoms.infmanage.dao;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;

import com.boco.eoms.infmanage.model.*;
import com.boco.eoms.infmanage.dao.*;
import org.apache.struts.util.LabelValueBean;
import com.boco.eoms.infmanage.controller.*;
import com.boco.eoms.common.util.CacheManager;
import com.boco.eoms.common.dao.*;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticVariable;

import java.util.Vector;

public class TawInfAddressBookDAO
        extends DAO {
    /**
     * @param ds DataSource 数据源（由Struts框架所提供）
     * @see 构造方法
     * @see 获得数据库的连接
     */
    public TawInfAddressBookDAO(com.boco.eoms.db.util.ConnectionPool ds) {
        super(ds);
    }

    public void insert(TawInfAddressBook tawInfAddressBook) throws SQLException {
        String sql;
        sql = "INSERT INTO taw_inf_address (company, dept_name, specialty, duty, name, group_id, user_id, mobile, office_tel, smart, email, remark, rec_type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tawInfAddressBook.getCompany());
            pstmt.setString(2, tawInfAddressBook.getDeptName());
            pstmt.setString(3, tawInfAddressBook.getSpecialty());
            pstmt.setString(4, tawInfAddressBook.getDuty());
            pstmt.setString(5, tawInfAddressBook.getName());
            pstmt.setInt(6, tawInfAddressBook.getGroupId());
            pstmt.setString(7, tawInfAddressBook.getUserId());
            pstmt.setString(8, tawInfAddressBook.getMobile());
            pstmt.setString(9, tawInfAddressBook.getOfficeTel());
            pstmt.setString(10, tawInfAddressBook.getSmart());
            pstmt.setString(11, tawInfAddressBook.getEmail());
            pstmt.setString(12, tawInfAddressBook.getRemark());
            pstmt.setString(13, tawInfAddressBook.getRecType());
            pstmt.executeUpdate();
            pstmt.close();
            conn.commit();
        } catch (SQLException sqle) {
            //   close(pstmt);
            //   rollback(conn);
            //   sqle.printStackTrace();
        } finally {
            close(conn);
        }
    }

    public List selectByCondition(int offset, int limit, String condition) throws
            SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql =
                    "SELECT a.*,b.group_name FROM taw_inf_address a, taw_inf_group b " +
                            condition;
            System.out.println(sql);

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (offset > 0) {
                int i = 0;
                while (i++ < offset) {
                    rs.next();
                }
            }

            int recCount = 0;
            while ((recCount++ < limit) && rs.next()) {
                TawInfAddressBook tawInfAddressBook = new TawInfAddressBook();
                //populate(tawInfAddressBook, rs);
                tawInfAddressBook.setId(rs.getInt("id"));

                tawInfAddressBook.setDeptName(StaticMethod.dbNull2String(rs.getString(
                        "dept_name")).trim());

                tawInfAddressBook.setCompany(StaticMethod.dbNull2String(rs.getString(
                        "company")).
                        trim());
                tawInfAddressBook.setSpecialty(StaticMethod.dbNull2String(rs.getString(
                        "specialty")).trim());
                tawInfAddressBook.setDuty(StaticMethod.dbNull2String(rs.getString(
                        "duty")).trim());
                tawInfAddressBook.setName(StaticMethod.dbNull2String(rs.getString(
                        "name")).
                        trim());
                tawInfAddressBook.setGroupId(rs.getInt("group_id"));
                tawInfAddressBook.setGroupName(StaticMethod.dbNull2String(rs.getString(
                        "group_name")).
                        trim());
                tawInfAddressBook.setUserId(
                        StaticMethod.dbNull2String(rs.getString(
                                "user_id")).trim());
                tawInfAddressBook.setMobile(StaticMethod.dbNull2String(rs.getString(
                        "mobile")).trim());

                tawInfAddressBook.setOfficeTel(StaticMethod.dbNull2String(rs.getString(
                        "office_tel")).
                        trim());
                tawInfAddressBook.setSmart(StaticMethod.dbNull2String(rs.getString(
                        "smart")).
                        trim());
                tawInfAddressBook.setEmail(StaticMethod.dbNull2String(rs.getString(
                        "email")).
                        trim());
                tawInfAddressBook.setRemark(StaticMethod.dbNull2String(rs.getString(
                        "remark")).
                        trim());
                tawInfAddressBook.setRecType(StaticMethod.dbNull2String(rs.getString(
                        "rec_type")).
                        trim());
                list.add(tawInfAddressBook);
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            //   close(rs);
            //   close(pstmt);
            //  rollback(conn);
            //   e.printStackTrace();
        } finally {
            close(conn);
        }
        return list;
    }

    public Vector getAllGroup(String userId) throws SQLException {
        Vector allGroup = new Vector();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        TawInfAddressBookForm tawInfAddressBookForm = new TawInfAddressBookForm();
        try {
            String sql = "";
            sql = "SELECT * from taw_inf_group WHERE user_id='" + userId + "'";
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            allGroup.add(new LabelValueBean("-请选择-", ""));

            while (rs.next()) {
                allGroup.add(new LabelValueBean(StaticMethod.dbNull2String(rs.getString("group_name")).trim(),
                        String.valueOf(rs.getInt(
                                "group_id"))));
            }
        } catch (Exception e) {
            //  e.printStackTrace();
        } finally {
            close(conn);
        }
        return allGroup;
    }

    public Vector getAllGroupName(String userId) throws SQLException {
        Vector allGroupName = new Vector();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        TawInfAddressBookForm tawInfAddressBookForm = new TawInfAddressBookForm();
        try {
            String sql = "";
            sql = "SELECT * from taw_inf_group WHERE user_id='" + userId + "'";
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                allGroupName.add(StaticMethod.dbNull2String(rs.getString("group_name")).trim());
            }
        } catch (Exception e) {
            //  e.printStackTrace();
        } finally {
            close(conn);
        }
        return allGroupName;
    }

    public int getSize(String tableName, String condition) {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int size = 0;
        try {
            String sql = "";
            //-------------------------
            //当条件语句中有order by的时候，在informix中无法计算count，所以要去掉排序的部分
            //------------------------
            if (condition.lastIndexOf("order by") != -1) {
                condition = condition.substring(0,
                        condition.lastIndexOf(
                                "order by"));
            }
            sql = "SELECT count(*) FROM " + tableName + " " + condition;

            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                size = rs.getInt(1);
            }

        } catch (Exception sqle) {
            // sqle.printStackTrace();
            //  throw sqle;
        } finally {
            close(conn);
        }

        return size;
    }

    public TawInfAddressBook getById(int id) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        TawInfAddressBook tawInfAddressBook = new TawInfAddressBook();
        try {
            String sql = "";
            sql = "SELECT a.*,b.group_name FROM taw_inf_address a,taw_inf_group b WHERE a.id=? and a.group_id=b.group_id";
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {

                //populate(tawInfAddressBook,rs);
                tawInfAddressBook.setId(rs.getInt("id"));

                tawInfAddressBook.setDeptName(StaticMethod.dbNull2String(rs.getString(
                        "dept_name")).trim());

                tawInfAddressBook.setCompany(StaticMethod.dbNull2String(rs.getString(
                        "company")).
                        trim());
                tawInfAddressBook.setSpecialty(StaticMethod.dbNull2String(rs.getString(
                        "specialty")).trim());
                tawInfAddressBook.setDuty(StaticMethod.dbNull2String(rs.getString(
                        "duty")).trim());
                tawInfAddressBook.setName(StaticMethod.dbNull2String(rs.getString(
                        "name")).
                        trim());
                tawInfAddressBook.setGroupId(rs.getInt("group_id"));
                tawInfAddressBook.setGroupName(StaticMethod.dbNull2String(rs.getString(
                        "group_name")).
                        trim());
                tawInfAddressBook.setUserId(
                        StaticMethod.dbNull2String(rs.getString(
                                "user_id")).trim());
                tawInfAddressBook.setMobile(StaticMethod.dbNull2String(rs.getString(
                        "mobile")).trim());

                tawInfAddressBook.setOfficeTel(StaticMethod.dbNull2String(rs.getString(
                        "office_tel")).
                        trim());
                tawInfAddressBook.setSmart(StaticMethod.dbNull2String(rs.getString(
                        "smart")).
                        trim());
                tawInfAddressBook.setEmail(StaticMethod.dbNull2String(rs.getString(
                        "email")).
                        trim());
                tawInfAddressBook.setRemark(StaticMethod.dbNull2String(rs.getString(
                        "remark")).
                        trim());
                tawInfAddressBook.setRecType(StaticMethod.dbNull2String(rs.getString(
                        "rec_type")).
                        trim());

            }
        } catch (Exception e) {
            //  e.printStackTrace();
        } finally {
            close(conn);
        }
        return tawInfAddressBook;
    }

    public void delete(int id) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;

        try {
            String sql = "";
            sql = "DELETE FROM taw_inf_address WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            //  conn.rollback();
            //  e.printStackTrace();
        } finally {
            close(conn);
        }
    }

    public void update(TawInfAddressBook tawInfAddressBook) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "";
            sql =
                    "UPDATE taw_inf_address SET company=?, dept_name=?, specialty=?, duty=?, name=?, group_id=?, mobile=?, office_tel=?, smart=?, email=?, remark=?, rec_type=? " +
                            "WHERE id=?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, StaticMethod.null2String(tawInfAddressBook.getCompany().
                    trim()));
            pstmt.setString(2, StaticMethod.null2String(tawInfAddressBook.getDeptName().
                    trim()));
            pstmt.setString(3,
                    StaticMethod.null2String(tawInfAddressBook.getSpecialty().
                            trim()));
            pstmt.setString(4,
                    StaticMethod.null2String(tawInfAddressBook.getDuty().trim()));
            pstmt.setString(5,
                    StaticMethod.null2String(tawInfAddressBook.getName().trim()));
            pstmt.setInt(6, tawInfAddressBook.getGroupId());
            pstmt.setString(7,
                    StaticMethod.null2String(tawInfAddressBook.getMobile().
                            trim()));
            pstmt.setString(8,
                    StaticMethod.null2String(tawInfAddressBook.getOfficeTel().
                            trim()));
            pstmt.setString(9,
                    StaticMethod.null2String(tawInfAddressBook.getSmart().
                            trim()));
            pstmt.setString(10,
                    StaticMethod.null2String(tawInfAddressBook.getEmail().
                            trim()));

            pstmt.setString(11,
                    StaticMethod.null2String(tawInfAddressBook.getRemark().
                            trim()));
            pstmt.setString(12,
                    StaticMethod.null2String(tawInfAddressBook.getRecType().
                            trim()));

            pstmt.setInt(13, tawInfAddressBook.getId());
            System.out.println(sql);
            pstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            //  conn.rollback();
            //  e.printStackTrace();
        } finally {
            close(conn);
        }
    }

    public void insertGroup(String groupName, String userId) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;

        try {
            String sql = "";
            sql = "insert into taw_inf_group (group_name,user_id) values ('" +
                    groupName + "','" + userId +
                    "')";
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate(sql);
            conn.commit();

        } catch (Exception e) {
            //  e.printStackTrace();
            //   conn.rollback();
        } finally {
            close(conn);
        }

    }

    public void updateCount(int id) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        conn = ds.getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "";
            sql =
                    "UPDATE taw_inf_address SET count=count+1 WHERE id = '" + id +
                            "'";

            pstmt = conn.prepareStatement(sql);
            System.out.println(sql);
            pstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            // conn.rollback();
            //  e.printStackTrace();
        } finally {
            close(conn);
        }
    }

    public ArrayList getNameMail(String userId) throws SQLException {
        ArrayList ret = new ArrayList();

        ArrayList list2 = new ArrayList();
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT a.group_id,a.name,a.email,b.group_name FROM taw_inf_address a,taw_inf_group b " +
                    "WHERE a.user_id =? and a.group_id=b.group_id group by a.group_id,a.name,a.email,b.group_name";
            pstmt = conn.prepareStatement(sql);
            System.out.println(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(StaticMethod.dbNull2String(rs.getString("name")) + " (" +
                        StaticMethod.dbNull2String(rs.getString("email")) + " : " + StaticMethod.dbNull2String(rs.getString("group_name")) + ")");
                list2.add(StaticMethod.dbNull2String(rs.getString("email")));
            }

            close(rs);
            close(pstmt);

            ret.add(list);
            ret.add(list2);
        } catch (SQLException e) {
            //  close(rs);
            ////    close(pstmt);
            //   e.printStackTrace();
        } finally {
            close(conn);
            list = null;
            list2 = null;
        }
        return ret;
    }

}

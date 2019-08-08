//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : TawRmRecordSubDAO.java
//
// Copyright 2003 Company
// Generated at Thu Mar 27 10:17:32 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.duty.dao;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;

import com.boco.eoms.common.util.*;
import com.boco.eoms.duty.model.*;
import com.boco.eoms.common.util.CacheManager;
import com.boco.eoms.common.dao.*;

public class TawRmRecordPerDAO extends DAO {


    public TawRmRecordPerDAO() {
        super();
    }

    public TawRmRecordPerDAO(com.boco.eoms.db.util.ConnectionPool ds) {
        super(ds);
    }

    /**
     * @param workserial
     * @return
     * @throws SQLException
     * @see：根据班次得到值班日志信息
     */
    public Vector getVecPerRecord(int workserial) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        Vector vecSubRecords = null;
        Vector vecSubRecord = null;

        try {
            vecSubRecords = new Vector();
            conn = ds.getConnection();
            sql = "select * from Taw_rm_record_per where  Workserial= ? order by id ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, workserial);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                vecSubRecord = new Vector();
                vecSubRecord.add(String.valueOf(rs.getInt("id")));
                vecSubRecord.add(String.valueOf(rs.getInt("Workserial")));
                vecSubRecord.add(StaticMethod.null2String(String.valueOf(rs.getString("Recordtime")).replaceAll("\\.0", "")));
                vecSubRecord.add(StaticMethod.null2String(String.valueOf(rs.getString("DutyMan"))));
                vecSubRecord.add(StaticMethod.null2String(String.valueOf(rs.getString("DutyManName"))));
                vecSubRecord.add(StaticMethod.null2String(String.valueOf(rs.getString("Dutyrecord")).trim()));
                vecSubRecord.add(String.valueOf(rs.getInt("complete_flag")));
                vecSubRecord.add(String.valueOf(rs.getString("url")));
                vecSubRecord.add(StaticMethod.null2String(String.valueOf(rs.getString("typename"))));

                vecSubRecords.add(vecSubRecord);
                vecSubRecord = null;
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            //rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
            sql = null;
            rs = null;
        }
        return vecSubRecords;
    }

    /**
     * @param workserial
     * @param complete_flag
     * @return
     * @throws SQLException
     * @see 根据班次和完成标识得到值班日志信息
     */
    public Vector getVecPerRecord(int workserial, int complete_flag) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        Vector vecSubRecords = null;
        Vector vecSubRecord = null;

        try {
            vecSubRecords = new Vector();
            conn = ds.getConnection();
            sql = "select * from Taw_rm_record_per where  Workserial= ? and complete_flag = ? order by id ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, workserial);
            pstmt.setInt(2, complete_flag);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                vecSubRecord = new Vector();
                vecSubRecord.add(String.valueOf(rs.getInt("id")));
                vecSubRecord.add(String.valueOf(rs.getInt("Workserial"))); //null2String
                vecSubRecord.add(StaticMethod.null2String(String.valueOf(rs.getString("Recordtime")).replaceAll("\\.0", "")));
                vecSubRecord.add(StaticMethod.null2String(String.valueOf(rs.getString("DutyMan"))));
                vecSubRecord.add(StaticMethod.null2String(String.valueOf(rs.getString("DutyManName"))));
                vecSubRecord.add(StaticMethod.null2String(String.valueOf(rs.getString("Dutyrecord"))));
                vecSubRecord.add(String.valueOf(rs.getInt("complete_flag")));
                vecSubRecord.add(String.valueOf(rs.getString("url")));
                vecSubRecord.add(StaticMethod.null2String(String.valueOf(rs.getString("typename"))));
                vecSubRecords.add(vecSubRecord);
                vecSubRecord = null;
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            //rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
            sql = null;
            rs = null;
        }
        return vecSubRecords;
    }

    /**
     * @param id
     * @param Dutyrecord
     * @param complete_flag
     * @param dutyman
     * @param dutymanname
     * @param recordtime
     * @throws SQLException
     * @see 根据id更新所有信息
     */
    public void updatePerRecord(int id, String Dutyrecord, int complete_flag, String dutyman, String dutymanname, String recordtime) throws SQLException {
        String sql;

        sql = "update Taw_rm_record_per set Dutyrecord = ? ,complete_flag = ?,dutyman = ? , dutymanname = ? ,recordtime = ?  where id =?";

        com.boco.eoms.db.util.BocoConnection conn = null;

        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            //conn.setAutoCommit(true);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Dutyrecord);
            pstmt.setInt(2, complete_flag);
            pstmt.setString(3, dutyman);
            pstmt.setString(4, dutymanname);
            pstmt.setString(5, recordtime);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
            pstmt.close();
            //conn.setAutoCommit(false);
            conn.commit();

        } catch (SQLException sqle) {
            close(pstmt);
            //rollback(conn);
            sqle.printStackTrace();
            throw sqle;
        } finally {
            close(conn);
            sql = null;
        }
    }

    /**
     * @param workserial1
     * @param workserial2
     * @throws SQLException
     * @see 把班次为了workserial1的日志信息复制到班次号为workserial2的日志信息
     */
    public void hangover(int workserial1, int workserial2) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            String sql = "INSERT INTO taw_rm_record_per ( workserial,  recordtime, dutyman, dutymanname, dutyrecord, complete_flag ,url,typename,seqid) select  +'" + String.valueOf(workserial2) + "',  recordtime, dutyman, dutymanname, dutyrecord, complete_flag,url,typename,seqid  from taw_rm_record_per where workserial = ? and complete_flag = 0";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, workserial1);

            pstmt.executeUpdate();
            close(pstmt);
            conn.commit();
        } catch (SQLException e) {
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
        }
    }

    /**
     * @param id
     * @throws SQLException
     * @see 根据id删除日志信息
     */
    public void deletePerRecord(int id) throws SQLException {
        String sql;

        sql = "delete from Taw_rm_record_per where id =?";

        com.boco.eoms.db.util.BocoConnection conn = null;

        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            //conn.setAutoCommit(true);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
            //conn.setAutoCommit(false);
            conn.commit();

        } catch (SQLException sqle) {
            close(pstmt);
            //rollback(conn);
            sqle.printStackTrace();
            throw sqle;
        } finally {
            close(conn);
            sql = null;
        }
    }

    /**
     * @param Workserial
     * @param Recordtime
     * @param DutyMan
     * @param DutyManName
     * @param Dutyrecord
     * @param complete_flag
     * @throws SQLException
     * @see 插入值班信息(注意字符集)
     */
    public void insertPerRecord(int Workserial, String Recordtime, String DutyMan, String DutyManName, String Dutyrecord, int complete_flag, String url, String typename, String SeqId) throws SQLException,
            UnsupportedEncodingException {
        String sql;

        sql = "insert into Taw_rm_record_per(Workserial,Recordtime,DutyMan,DutyManName,Dutyrecord,complete_flag,url,typename,seqid) values(?,?,?,?,?,?,?,?,?)";

        com.boco.eoms.db.util.BocoConnection conn = null;

        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            //DutyManName=new String(DutyManName.getBytes("gb2312"),"819");
            //conn.setAutoCommit(true);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Workserial);
            pstmt.setString(2, Recordtime);
            pstmt.setString(3, DutyMan);
            pstmt.setString(4, DutyManName);
            pstmt.setString(5, Dutyrecord);
            pstmt.setInt(6, complete_flag);
            pstmt.setString(7, url);
            pstmt.setString(8, typename);
            pstmt.setString(9, SeqId);
            pstmt.executeUpdate();
            pstmt.close();
            //conn.setAutoCommit(false);
            conn.commit();

        } catch (SQLException sqle) {
            close(pstmt);
            //rollback(conn);
            sqle.printStackTrace();
            throw sqle;
        } finally {
            close(conn);
            sql = null;
        }
    }


    public void delete(int id) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            //conn.setAutoCommit(true);
            String sql = "DELETE FROM taw_rm_record_per WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            close(pstmt);
            //conn.setAutoCommit(false);
            conn.commit();
        } catch (SQLException e) {
            close(pstmt);
            //rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
        }
    }

    /**
     * @param workserial
     * @throws SQLException
     * @see 根据班次号删除日志信息
     */
    public void delete_workserial(int workserial) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            //conn.setAutoCommit(true);
            String sql = "DELETE FROM taw_rm_record_per WHERE workserial=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, workserial);
            pstmt.executeUpdate();
            close(pstmt);
            //conn.setAutoCommit(false);
            conn.commit();
        } catch (SQLException e) {
            close(pstmt);
            //rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
        }
    }

    /**
     * @param seqId
     * @throws SQLException
     */
    public void UptFinishFlag(String seqId, int flag) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            //conn.setAutoCommit(true);
            String sql = "UPDATE taw_rm_record_per SET complete_flag=? WHERE seqid=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, flag);
            pstmt.setString(2, seqId);

            pstmt.executeUpdate();
            close(pstmt);
            //conn.setAutoCommit(false);
            conn.commit();
        } catch (SQLException e) {
            close(pstmt);
            //rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
        }
    }

    /**
     * @param workserial
     * @param complete_flag
     * @return
     * @throws SQLException
     * @see 查询统计
     */
    public Vector getSearchResult(String config) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        Vector vecSubRecords = null;
        Vector vecSubRecord = null;

        try {
            vecSubRecords = new Vector();
            conn = ds.getConnection();
            sql = "select * from Taw_rm_record_per where " + config;
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                vecSubRecord = new Vector();
                vecSubRecord.add(String.valueOf(rs.getInt("id")));
                vecSubRecord.add(String.valueOf(rs.getInt("Workserial")));
                vecSubRecord.add(StaticMethod.null2String(String.valueOf(rs.getString("Recordtime")).replaceAll("\\.0", "")));
                vecSubRecord.add(StaticMethod.null2String(String.valueOf(rs.getString("DutyMan"))));
                vecSubRecord.add(StaticMethod.null2String(String.valueOf(rs.getString("DutyManName"))));
                vecSubRecord.add(StaticMethod.null2String(String.valueOf(rs.getString("Dutyrecord"))));
                vecSubRecord.add(String.valueOf(rs.getInt("complete_flag")));
                vecSubRecord.add(String.valueOf(rs.getString("url")));
                vecSubRecord.add(StaticMethod.null2String(String.valueOf(rs.getString("typename"))));
                vecSubRecords.add(vecSubRecord);
                vecSubRecord = null;
            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            //rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
            sql = null;
            rs = null;
        }
        return vecSubRecords;
    }


}

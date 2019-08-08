/**
 * @author 赵川
 * @version 2.0
 * @see <p>功能描述：值班子表的DAO。</p>
 */


package com.boco.eoms.duty.dao;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.sql.*;

import com.boco.eoms.common.util.*;
import com.boco.eoms.duty.model.*;
import com.boco.eoms.common.util.CacheManager;
import com.boco.eoms.common.dao.*;

public class TawRmRecordSubDAO extends DAO {

    public TawRmRecordSubDAO(com.boco.eoms.db.util.ConnectionPool ds) {
        super(ds);
    }

    /**
     * @param tawRmRecordSub
     * @throws SQLException
     * @see 插入值班子表
     */
    public void insert(TawRmRecordSub tawRmRecordSub) throws SQLException {
        String sql;
        sql = "INSERT INTO taw_rm_record_sub (id, room_id, workserial, dutyman, starttime, endtime, starttime_defined, endtime_defined, statement, workflag, status, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tawRmRecordSub.getId());
            pstmt.setInt(2, tawRmRecordSub.getRoomId());
            pstmt.setInt(3, tawRmRecordSub.getWorkserial());
            pstmt.setString(4, tawRmRecordSub.getDutyman());
      /*
      pstmt.setTimestamp(5, tawRmRecordSub.getStarttime());
      pstmt.setTimestamp(6, tawRmRecordSub.getEndtime());
      pstmt.setTimestamp(7, tawRmRecordSub.getStarttimeDefined());
      pstmt.setTimestamp(8, tawRmRecordSub.getEndtimeDefined());
          */
            pstmt.setString(5, tawRmRecordSub.getStarttime());
            pstmt.setString(6, tawRmRecordSub.getEndtime());
            pstmt.setString(7, tawRmRecordSub.getStarttimeDefined());
            pstmt.setString(8, tawRmRecordSub.getEndtimeDefined());

            pstmt.setString(9, tawRmRecordSub.getStatement());
            pstmt.setInt(10, tawRmRecordSub.getWorkflag());
            pstmt.setInt(11, tawRmRecordSub.getStatus());
            pstmt.setString(12, tawRmRecordSub.getNotes());
            pstmt.executeUpdate();
            pstmt.close();
            conn.commit();
        } catch (SQLException sqle) {
            close(rs);
            close(pstmt);
            rollback(conn);
            sqle.printStackTrace();
            throw sqle;
        } finally {
            close(conn);
        }
    }

    /**
     * @param tawRmRecordSub
     * @throws SQLException
     * @see 修改值班子表
     */
    public void update(TawRmRecordSub tawRmRecordSub) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            String sql = "UPDATE taw_rm_record_sub SET room_id=?, workserial=?, dutyman=?, starttime=?, endtime=?, starttime_defined=?, endtime_defined=?, statement=?, workflag=?, status=?, notes=? WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tawRmRecordSub.getRoomId());
            pstmt.setInt(2, tawRmRecordSub.getWorkserial());
            pstmt.setString(3, tawRmRecordSub.getDutyman());
      /*
      pstmt.setTimestamp(4, tawRmRecordSub.getStarttime());
      pstmt.setTimestamp(5, tawRmRecordSub.getEndtime());
      pstmt.setTimestamp(6, tawRmRecordSub.getStarttimeDefined());
      pstmt.setTimestamp(7, tawRmRecordSub.getEndtimeDefined());
          */
            pstmt.setString(4, tawRmRecordSub.getStarttime());
            pstmt.setString(5, tawRmRecordSub.getEndtime());
            pstmt.setString(6, tawRmRecordSub.getStarttimeDefined());
            pstmt.setString(7, tawRmRecordSub.getEndtimeDefined());
            pstmt.setString(8, tawRmRecordSub.getStatement());
            pstmt.setInt(9, tawRmRecordSub.getWorkflag());
            pstmt.setInt(10, tawRmRecordSub.getStatus());
            pstmt.setString(11, tawRmRecordSub.getNotes());
            pstmt.setInt(12, tawRmRecordSub.getId());
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
     * @see 根据ID删除值班子表记录
     */
    public void delete(int id) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            String sql = "DELETE FROM taw_rm_record_sub WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
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
     * @param workserial
     * @throws SQLException
     * @see 删除值班流水号ID对应的所有值班子记录
     */
    public void delete_workserial(int workserial) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            String sql = "DELETE FROM taw_rm_record_sub WHERE workserial=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, workserial);
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
     * @return
     * @throws SQLException
     * @see 根据子表ID，得到值班子表对象
     */
    public TawRmRecordSub retrieve(int id) throws SQLException {
        TawRmRecordSub tawRmRecordSub = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT * FROM taw_rm_record_sub WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                tawRmRecordSub = new TawRmRecordSub();
                populate(tawRmRecordSub, rs);
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
        return tawRmRecordSub;
    }

    /**
     * @param workserial
     * @param dutyman
     * @return
     * @throws SQLException
     * @see 根据值班流水号和值班人员得到一个值班子记录对象
     */
    public TawRmRecordSub retrieve_sub(int workserial, String dutyman) throws SQLException {
        TawRmRecordSub tawRmRecordSub = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT * FROM taw_rm_record_sub WHERE workserial=? and dutyman = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, workserial);
            pstmt.setString(2, dutyman);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                tawRmRecordSub = new TawRmRecordSub();
                populate(tawRmRecordSub, rs);
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
        return tawRmRecordSub;
    }

    /**
     * @param workserial
     * @param dutyman
     * @return
     * @throws SQLException
     * @see 根据值班流水号和值班人员得到一个值班子记录对象，并修改时间类型字段，去掉".0"
     */
    public TawRmRecordSub retrieve_sub1(int workserial, String dutyman) throws SQLException {
        TawRmRecordSub tawRmRecordSub = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT * FROM taw_rm_record_sub WHERE workserial=? and dutyman = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, workserial);
            pstmt.setString(2, dutyman);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                tawRmRecordSub = new TawRmRecordSub();
                tawRmRecordSub.setDutyman(rs.getString("dutyman"));
                tawRmRecordSub.setEndtime(StaticMethod.null2String(rs.getString("endtime")).replaceAll("\\.0", ""));
                tawRmRecordSub.setEndtimeDefined(StaticMethod.null2String(rs.getString("endtime_defined")).replaceAll("\\.0", ""));
                tawRmRecordSub.setId(rs.getInt("id"));
                tawRmRecordSub.setNotes(rs.getString("notes"));
                tawRmRecordSub.setRoomId(rs.getInt("room_id"));
                tawRmRecordSub.setStarttime(StaticMethod.null2String(rs.getString("starttime")).replaceAll("\\.0", ""));
                tawRmRecordSub.setStarttimeDefined(StaticMethod.null2String(rs.getString("starttime_defined")).replaceAll("\\.0", ""));
                tawRmRecordSub.setStatement(rs.getString("statement"));
                tawRmRecordSub.setStatus(rs.getInt("status"));
                tawRmRecordSub.setWorkflag(rs.getInt("workflag"));
                tawRmRecordSub.setWorkserial(rs.getInt("workserial"));
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
        return tawRmRecordSub;
    }

    /**
     * @return
     * @throws SQLException
     * @see 返回值班子表对象列表
     */
    public List list() throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT room_id, workserial, dutyman, starttime, endtime, starttime_defined, endtime_defined, statement, workflag, status, notes, id FROM taw_rm_record_sub";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                TawRmRecordSub tawRmRecordSub = new TawRmRecordSub();
                populate(tawRmRecordSub, rs);
                list.add(tawRmRecordSub);
                tawRmRecordSub = null;
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

    /**
     * @return
     * @throws SQLException
     * @see 返回值班子表对象列表
     */
    public List list(int offset, int limit) throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT room_id, workserial, dutyman, starttime, endtime, starttime_defined, endtime_defined, statement, workflag, status, notes, id FROM taw_rm_record_sub";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (offset > 0) rs.absolute(offset);
            int recCount = 0;
            while ((recCount++ < limit) && rs.next()) {
                TawRmRecordSub tawRmRecordSub = new TawRmRecordSub();
                populate(tawRmRecordSub, rs);
                list.add(tawRmRecordSub);
                tawRmRecordSub = null;
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

    /**
     * @param workserial
     * @param dutyman
     * @return
     * @throws SQLException
     * @see add by denden （贵州本地）根据值班流水号和值班人员得到一个值班子记录对象
     */
    public int retrieve_sub0(int workserial, String dutyman) throws SQLException {
        TawRmRecordSub tawRmRecordSub = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int type = 1;
        try {
            conn = ds.getConnection();
            String sql = "SELECT typemode FROM taw_rm_record_sub WHERE workserial=? and dutyman = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, workserial);
            pstmt.setString(2, dutyman);
            rs = pstmt.executeQuery();
            if (rs.next()) {
//          tawRmRecordSub = new TawRmRecordSub();
//          populate(tawRmRecordSub, rs);
                type = rs.getInt(1);
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
        return type;
    }

    public List retrieve_sub2(int workserial, String dutyman) throws SQLException {
        TawRmRecordSub tawRmRecordSub = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        // String dutySummary="",importRecord="", importFault="", netCut="", netKPI="";
//      int type=1;

        List listrs = new ArrayList();
        try {
            conn = ds.getConnection();
            String sql = "SELECT trans,switch,data,system,other, dutySummary,importRecord,importFault,netCut,netKPI,needcorrespond,handoverproceeding  FROM taw_rm_record_sub WHERE workserial=? and dutyman = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, workserial);
            pstmt.setString(2, dutyman);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                listrs.add(StaticMethod.null2String(rs.getString("trans")).trim());
                listrs.add(StaticMethod.null2String(rs.getString("switch")).trim());
                listrs.add(StaticMethod.null2String(rs.getString("data")).trim());
                listrs.add(StaticMethod.null2String(rs.getString("system")).trim());
                listrs.add(StaticMethod.null2String(rs.getString("other")).trim());
                listrs.add(StaticMethod.null2String(rs.getString("dutySummary")).trim());
                listrs.add(StaticMethod.null2String(rs.getString("importRecord")).trim());
                listrs.add(StaticMethod.null2String(rs.getString("importFault")).trim());
                listrs.add(StaticMethod.null2String(rs.getString("netCut")).trim());
                listrs.add(StaticMethod.null2String(rs.getString("netKPI")).trim());
                listrs.add(StaticMethod.null2String(rs.getString("needcorrespond")).trim());
                listrs.add(StaticMethod.null2String(rs.getString("handoverproceeding")).trim());
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
        return listrs;
    }


}

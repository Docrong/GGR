package com.boco.eoms.duty.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Properties;
import java.util.Vector;

import org.apache.struts.util.LabelValueBean;

import com.boco.eoms.common.dao.DAO;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.duty.model.*;

import java.util.List;
import java.util.ArrayList;

/**
 * <p>Title: TawAutoSheetDAO</p>
 * <p>Description:自动表单 </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Boco</p>
 *
 * @version 1.0
 * @author: Lihong Qi
 */

public class TawSheetStatDAO
        extends DAO {
    public TawSheetStatDAO(com.boco.eoms.db.util.ConnectionPool ds) {
        super(ds);
    }

    /**
     * @param String sheetType 工单类型 ,int roomId 机房ID,int dutyId 值班ID,int deptID 派单部门,String beginTime,String endTime
     * @return LIST
     * @see 取出该部门当前为归档的工单列表
     */
    public Vector getAutoSheetSelect(String sheetType, int deptId, int roomId,
                                     int dutyId, String beginTime,
                                     String endTime, String dutyDate,
                                     String dutyMaster) {
        Vector retVector = new Vector();

        try {
            com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String sql = "";
            String startTime = "2007-01-01 00:00:00";
            conn = ds.getConnection();
            sql = "select distinct faultsheetMain.id, faultsheetMain.title,faultsheetMain.send_time,faultsheetMain.sheet_id ";
            sql = sql + "from " + sheetType + "Node faultsheetNode," + sheetType +
                    "Main faultsheetMain, " + sheetType + "Link faultsheetLink";
            sql = sql + " where faultsheetNode.worksheet_Id = faultsheetMain.id  and faultsheetLink.worksheet_id=faultsheetmain.id and faultsheetLink.begin_node_id = faultsheetnode.id ";
            sql = sql + " and faultsheetlink.end_send_time>= '" + startTime + "'";
            sql = sql + " and faultsheetlink.end_send_time<= '" + endTime + "'";
            sql = sql +
                    " and faultsheetMain.status=2 and faultsheetNode.flag_Node =1";
            sql = sql +
                    " and ((faultsheetNode.flag_User = 1 and faultsheetNode.dept_Id = " +
                    deptId + ") ";
            sql = sql + ") and faultsheetMain.send_time>= '" + startTime + "'";
            sql = sql + " and faultsheetMain.sheet_id not in ( select distinct main.sheet_id ";
            sql = sql + " from " + sheetType + "Node node," + sheetType + "Main main ";
            sql = sql + " where main.send_dept_id='" + deptId + "'";
            sql = sql + " and node.node_id!=101 and node.flag_end<2 and main.sheet_id=node.sheet_id and main.id=node.worksheet_id";
            sql = sql + " and main.send_time >= '" + startTime + "'and main.send_time <= '" + endTime + "'";
            sql = sql + ") order by faultsheetMain.send_Time desc";
            System.out.println(sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                TawSheetStat tawSheetStat = new TawSheetStat();
                tawSheetStat.setWorksheetId(rs.getString(1));
                tawSheetStat.setSheetId(rs.getString(4));
                tawSheetStat.setSendTime(rs.getString(3));
                tawSheetStat.setTitle(rs.getString(2));
                tawSheetStat.setDeptId(deptId);
                tawSheetStat.setRoomId(roomId);
                tawSheetStat.setDutyId(dutyId);
                tawSheetStat.setDutyDate(dutyDate);
                tawSheetStat.setDutyMaster(dutyMaster);
                if (sheetType.equals("faultsheet")) {
                    tawSheetStat.setUrl("/Faultsheet/detail.do?id=");
                } else if (sheetType.equals("datasheet")) {
                    tawSheetStat.setUrl("/Datasheet/detail.do?id=");
                } else if (sheetType.equals("tasksheet")) {
                    tawSheetStat.setUrl("/Tasksheet/detail.do?id=");
                } else if (sheetType.equals("requsheet")) {
                    tawSheetStat.setUrl("/Requsheet/detail.do?id=");
                }

                retVector.add(tawSheetStat);
                tawSheetStat = null;
            }

            //释放对象
            close(conn);
            pstmt = null;
            rs = null;
            sql = "";
            //tawSheetStat = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVector;
    }

    /**
     * @param String sheetId 工单流水号 ,int dutyID 班次，String tableName 表名称
     * @return LIST
     * @see 根据工单流水号和班次ID判断该工单是否已保存
     */
    public boolean getIfStat(String sheetId, int dutyID, String tableName) {
        boolean ifStat = false;
        try {
            com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String sql = "";
            conn = ds.getConnection();
            sql = "select * from " + tableName + " where sheet_id='" + sheetId + "' ";
            sql = sql + " and duty_id =" + dutyID;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ifStat = true;
            }
            //释放对象
            close(conn);
            pstmt = null;
            rs = null;
            sql = "";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ifStat;
    }

    /**
     * @param TawSheetStat,String tableName
     * @return LIST
     * @see 保存
     */
    public void insert(TawSheetStat tawSheetStat, String tableName,
                       String starttime, String endtime, String url) throws
            SQLException {
        String sql;
        sql = "INSERT INTO " + tableName + " (worksheet_id,sheet_id,title,dept_id,room_id,duty_id,dutyDate,dutymaster,starttime,endtime,url) VALUES (? ,? ,?, ?, ?, ?, ?, ?, ?, ?, ?)";
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tawSheetStat.getWorksheetId());
            pstmt.setString(2, tawSheetStat.getSheetId());
            pstmt.setString(3, tawSheetStat.getTitle());
            pstmt.setInt(4, tawSheetStat.getDeptId());
            pstmt.setInt(5, tawSheetStat.getRoomId());
            pstmt.setInt(6, tawSheetStat.getDutyId());
            pstmt.setString(7, tawSheetStat.getDutyDate());
            pstmt.setString(8, tawSheetStat.getDutyMaster());
            pstmt.setString(9, starttime);
            pstmt.setString(10, endtime);
            pstmt.setString(11, tawSheetStat.getUrl());
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
     * @param sheetId   String
     * @param dutyID    int
     * @param tableName String
     * @return boolean
     */
    public List getTime(int dutyId, int roomId) {
        List list = new ArrayList();

        try {
            com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String sql = "";
            conn = ds.getConnection();
            sql = "select StartTime_defined,Endtime_defined,dutydate,dutymaster from  Taw_rm_assignwork" +
                    " where id='" + dutyId + "' ";
            sql = sql + " and room_id ='" + roomId + "'";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                list.add(rs.getString("StartTime_defined"));
                list.add(rs.getString("Endtime_defined"));
                list.add(rs.getString("dutydate"));
                list.add(rs.getString("dutymaster"));

            }
            //释放对象
            close(conn);
            pstmt = null;
            rs = null;
            sql = "";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List getCheck(String startTime, String endTime, String roomId) throws SQLException {
        List list = new ArrayList();
        try {

            com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String sql = "";
            conn = ds.getConnection();
            sql =
                    "select a.dutydate,a.duty_id,b.user_name ,a.starttime,a.endtime,count(*) from taw_fault_stat_duty a" +
                            ",taw_rm_user b";
            sql += " where a.dutydate>='" + startTime.trim() + "' and a.dutydate<='" +
                    endTime + "'" +
                    " and a.dutymaster=b.user_id";
            if (!roomId.equals("")) {
                sql += " and room_id='" + roomId.trim() + "'";
            }

            sql += " group by a.dutydate,a.duty_id,b.user_name,a.starttime,a.endtime";
            sql += " order by a.dutydate,a.duty_id";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                List listrs = new ArrayList();
                listrs.add(rs.getString(1).substring(0, 10));
                listrs.add(rs.getString(2));
                listrs.add(rs.getString(3));
                listrs.add(rs.getString(4));
                listrs.add(rs.getString(5));
                listrs.add(rs.getString(6));
                list.add(listrs);
                listrs = null;
            }
            //释放对象
            close(conn);
            pstmt = null;
            rs = null;
            sql = "";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public String getDatemax() {
        String dateMax = "";
        try {
            com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String sql = "";
            conn = ds.getConnection();
            sql = "select max(dutydate) from taw_fault_stat_duty";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                dateMax = rs.getString(1);
            }
            //释放对象
            close(conn);
            pstmt = null;
            rs = null;
            sql = "";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateMax;

    }

    public String getDatemix() {
        String dateMix = "";
        try {
            com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String sql = "";
            conn = ds.getConnection();
            sql = "select min(dutydate) from taw_fault_stat_duty";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                dateMix = rs.getString(1);
            }
            //释放对象
            close(conn);
            pstmt = null;
            rs = null;
            sql = "";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateMix;
    }

    public List getCheckList(String dutyId) throws SQLException {
        List list = new ArrayList();
        try {

            com.boco.eoms.db.util.BocoConnection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            String sql = "";
            conn = ds.getConnection();
            sql =
                    "select a.worksheet_id,a.sheet_id,a.title,b.user_name,a.dutydate,a.url from taw_fault_stat_duty a,taw_rm_user b where duty_id='" +
                            dutyId.trim() + "'";
            sql += " and a.dutymaster=b.user_id";
            sql += " order by sheet_id";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                List listrs = new ArrayList();
                listrs.add(rs.getString("worksheet_id"));
                listrs.add(rs.getString("sheet_id"));
                listrs.add(rs.getString("title"));
                listrs.add(rs.getString("user_name"));
                listrs.add(rs.getString("dutydate").substring(0, 10));
                listrs.add(rs.getString("url"));
                list.add(listrs);
                listrs = null;
            }
            //释放对象
            close(conn);
            pstmt = null;
            rs = null;
            sql = "";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}

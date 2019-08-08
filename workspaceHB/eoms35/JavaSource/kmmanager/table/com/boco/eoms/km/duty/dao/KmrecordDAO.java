/**
 * @author 赵川
 * @version 2.0
 * @see <p>功能描述：值班记录的DAO。</p>
 */

package com.boco.eoms.km.duty.dao;

import java.sql.*;
import java.util.*;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.EOMSAttributes;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.common.dao.*;
import com.boco.eoms.common.util.*;
import com.boco.eoms.commons.file.exception.FMException;
import com.boco.eoms.commons.file.service.IFMExportFileManager;
import com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.duty.model.*;
import com.boco.eoms.duty.util.DutyMgrLocator;
import com.boco.eoms.km.configInfo.dao.KmsysteminfoDAO;
import com.boco.eoms.km.configInfo.model.Kmsysteminfo;
import com.boco.eoms.km.duty.model.Kmrecord;
import com.boco.eoms.km.duty.model.KmrecordSub;
import com.boco.eoms.workplan.vo.TawwpMonthPlanVO;

public class KmrecordDAO extends DAO {

    public KmrecordDAO(com.boco.eoms.db.util.ConnectionPool ds) {
        super(ds);
    }

    public KmrecordDAO() {
        super();
    }

    /**
     * @param workserial
     * @throws SQLException
     * @see 先删除该值班流水号对应的值班记录
     * @see 再插入新的值班记录（主要是上下和本班次人员、机房基本信息等）
     */
    public void getNewManiRecord(int workserial, String roomId) throws SQLException {

        com.boco.eoms.db.util.BocoConnection conn = null;

        com.boco.eoms.db.util.BocoConnection conndel = null;
        com.boco.eoms.db.util.BocoConnection conncreate = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtdel = null;
        PreparedStatement pstmtcreate = null;
        ResultSet rs = null;
        SaveSessionBeanDAO saveSessionBeanDAO = null;
        String sqldel = null;
        Vector vHander = null;
        String strHander = null;
        Vector vDutyman = null;
        String strDutyman = null;
        Vector vReceiver = null;
        String strReceiver = null;
        String Sql_sel = null;
        String temp_sql = null;

        try {
            saveSessionBeanDAO = new SaveSessionBeanDAO(ds);
            saveSessionBeanDAO.alterDateFormat();
            KmsysteminfoDAO tawRmSysteminfoDAO = new KmsysteminfoDAO(ds);
            Kmsysteminfo tawRmSysteminfo = tawRmSysteminfoDAO.retrieve(roomId
                    + "");

            int int_stagger = 0;
            if (tawRmSysteminfo != null) {
                int_stagger = tawRmSysteminfo.getStaggertime();
            }

            // 交班人，

            int iPreWorkserial = getExSerial(workserial, 1, int_stagger);
            vHander = getOneAssignMemberName(iPreWorkserial);
            strHander = "";
            for (int i = 0; i < vHander.size(); i++) {
                strHander = strHander + " " + vHander.get(i);
            }
            // 值班人
            vDutyman = getOneAssignMemberName(workserial);
            strDutyman = "";
            for (int i = 0; i < vDutyman.size(); i++) {
                strDutyman = strDutyman + " " + vDutyman.get(i);
            }
            // 接班人
            int iFollowWorkserial = getExSerial(workserial, 2, int_stagger);
            vReceiver = getOneAssignMemberName(iFollowWorkserial);
            strReceiver = "";
            for (int i = 0; i < vReceiver.size(); i++) {
                strReceiver = strReceiver + " " + vReceiver.get(i);
            }

            java.util.Date date = new java.util.Date();
            java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            String strDutyDate = StaticMethod.getAddZero(dtFormat.format(date));
            conncreate = ds.getConnection();

            // 取基本信息
            conn = ds.getConnection();


            Sql_sel = "Select id, room_id, region_id, StartTime_defined,EndTime_defined,Dutydate,Dutymaster from km_assignwork where id = "
                    + workserial + "";
            pstmt = conn.prepareStatement(Sql_sel);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // 在创建
                temp_sql = "INSERT INTO km_record (Id,Region_id,Room_id,Flag,Starttime,"
                        + "Hander, DutyMan, Receiver, DutyDate,Temperature,Wet,Weather,Clean,Clean1,Conditioner) "
                        + "values ("
                        + workserial
                        + ",0,"
                        + rs.getInt(2)
                        + ",0,'"
                        + StaticMethod.getAddZero(strDutyDate)
                        + "','"
                        + strHander
                        + "','"
                        + strDutyman + "','" + strReceiver + "','";

                // for informi
                temp_sql = temp_sql
                        + rs.getString(6).replaceAll(" 00:00:00.0", "")
                        + "',20,50,'" + StaticMethod.null2String("晴") + "','"
                        + StaticMethod.null2String("正常") + "','"
                        + StaticMethod.null2String("正常") + "','"
                        + StaticMethod.null2String("正常") + "')";

                pstmtcreate = conncreate.prepareStatement(temp_sql);
                pstmtcreate.executeUpdate();
                pstmtcreate.close();
                conncreate.commit();
            }
            // System.out.println("inster=" + pstmtcreate.toString());
            close(rs);
            close(pstmt);
            // close(pstmtcreate);
        } catch (SQLException sqle) {
            close(rs);
            close(pstmt);
            close(pstmtcreate);
            rollback(conn);
            rollback(conncreate);
            sqle.printStackTrace();
        } finally {
            close(conn);
            close(conndel);
            close(conncreate);
            // null
            saveSessionBeanDAO = null;
            sqldel = null;
            vHander = null;
            strHander = null;
            vDutyman = null;
            strDutyman = null;
            vReceiver = null;
            strReceiver = null;
            Sql_sel = null;
            temp_sql = null;
        }
    }

    /**
     * @param workserial
     * @throws SQLException
     * @see 全部更新子记录，设置结束
     */
    public void updateSub_setEnd(int workserial) throws SQLException {
        String sql = null;
        sql = "Update Km_RECORD_SUB SET STATUS = 1 where workserial =?";
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, workserial);
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
            // null
            sql = null;
            rs = null;
        }
    }

    /**
     * @param tawRmRecord
     * @param tawRmRecordSub
     * @param isDutymaster
     * @param workserial
     * @param userId
     * @throws SQLException
     * @see 保存个人值班纪录，如果是值班班长保存值班记录
     */
    public void saveRecord(Kmrecord tawRmRecord,
                           KmrecordSub tawRmRecordSub, boolean isDutymaster,
                           int workserial, String userId) throws SQLException {
        String sql;
        //ֵ��ס��¼
        sql = "Update km_RECORD SET Weather = ?,Temperature = ?,WET = ?,Clean = ?,Clean1 = ?,Conditioner = ?,dutyrecord = ?,notes = ?, netfault=?, importantaffair=?, harmony=?, otheraffair=? WHERE ID = " +
                workserial + "";
        //ֵ���Ӽ�¼
        String sqlsub =
                "Update Km_RECORD_SUB SET Statement = ? WHERE WorkSerial = " +
                        workserial + "  AND Dutyman = '" + userId + "'";

        com.boco.eoms.db.util.BocoConnection conn = null;
        com.boco.eoms.db.util.BocoConnection connsub = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtsub = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        ResultSet rssub = null;
        try {
            if (isDutymaster == true) {
                conn = ds.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, tawRmRecord.getWeather());
                pstmt.setInt(2, tawRmRecord.getTemperature());
                pstmt.setInt(3, tawRmRecord.getWet());
                pstmt.setString(4, tawRmRecord.getClean());
                pstmt.setString(5, tawRmRecord.getClean1());
                pstmt.setString(6, tawRmRecord.getConditioner());
                System.out.println("dutyrecord=" + tawRmRecord.getDutyrecord());
                System.out.println("notes=" + tawRmRecord.getNotes());
                pstmt.setString(7, tawRmRecord.getDutyrecord());
                pstmt.setString(8, tawRmRecord.getNotes());
                pstmt.executeUpdate();
                pstmt.close();
                conn.commit();
            }

            connsub = ds.getConnection();
            pstmtsub = connsub.prepareStatement(sqlsub);
            pstmtsub.setString(1, tawRmRecord.getStatement());
            pstmtsub.setString(2, tawRmRecord.getDutycheck());
            pstmtsub.executeUpdate();
            pstmtsub.close();
            connsub.commit();

            String dutycheck = "";
            pstmt1 = connsub
                    .prepareStatement("select dutycheck from km_record_sub where workserial="
                            + workserial);
            rssub = pstmt1.executeQuery();
            while (rssub.next()) {
                dutycheck = ("".equalsIgnoreCase(dutycheck) ? dutycheck
                        : dutycheck + ";")
                        + StaticMethod.null2String(rssub.getString(1));
            }

            pstmt2 = connsub
                    .prepareStatement("update km_record set dutycheck = '"
                            + dutycheck + "' where id=" + workserial);
            pstmt2.executeUpdate();
            pstmt2.close();
            connsub.commit();

            pstmt1.close();
        } catch (SQLException sqle) {
            if (isDutymaster == true) {
                close(rs);
                close(pstmt);
                rollback(conn);
            }
            close(rssub);
            close(pstmtsub);
            close(pstmt1);
            close(pstmt2);
            rollback(connsub);
            sqle.printStackTrace();
            throw sqle;
        } finally {
            if (isDutymaster == true) {
                close(conn);
            }
            close(connsub);
            // null
            sql = null;
            sqlsub = null;
            rs = null;
            rssub = null;
        }
    }

    /**
     * @param tawRmRecord
     * @param tawRmRecordSub
     * @throws SQLException
     * @see 插入值班主表和值班子表
     */
    public void insert(Kmrecord tawRmRecord, KmrecordSub tawRmRecordSub)
            throws SQLException {
        String sql;

        sql = "INSERT INTO km_record "
                + "(id, region_id, room_id, flag, starttime, endtime, weather, temperature, wet, dutydate, "
                + "clean, clean1, conditioner, hander, dutyman, receiver, dutyrecord, notes, importantnetfault, importantsocietydisaster, needcorrespond, handoverproceeding) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlsub = "insert into km_record_sub "
                + "(id,room_id,workserial,dutyman,starttime,starttime_defined,endtime_defined,statement,workflag,status,notes, importantnetfault, importantsocietydisaster, needcorrespond, handoverproceeding) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        com.boco.eoms.db.util.BocoConnection conn = null;
        com.boco.eoms.db.util.BocoConnection connsub = null;

        PreparedStatement pstmt = null;
        PreparedStatement pstmtsub = null;
        ResultSet rs = null;
        ResultSet rssub = null;
        try {
            conn = ds.getConnection();


            pstmt = conn.prepareStatement(sql);
            // System.out.println(sql);
            pstmt.setInt(1, tawRmRecord.getId());
            pstmt.setInt(2, tawRmRecord.getRegionId());
            pstmt.setInt(3, tawRmRecord.getRoomId());
            pstmt.setInt(4, tawRmRecord.getFlag());
            // pstmt.setTimestamp(5, tawRmRecord.getStarttime());
            // pstmt.setTimestamp(6, tawRmRecord.getEndtime());
            // pstmt.setTimestamp(10, tawRmRecord.getDutydate());
            pstmt.setString(5, StaticMethod.getAddZero(tawRmRecord.getStarttime()));
            pstmt.setString(6, StaticMethod.getAddZero(tawRmRecord.getEndtime()));
            pstmt.setString(7, tawRmRecord.getWeather());
            pstmt.setInt(8, tawRmRecord.getTemperature());
            pstmt.setInt(9, tawRmRecord.getWet());
            pstmt.setString(10, StaticMethod.getAddZero(tawRmRecord.getDutydate()));
            // System.out.println("dutydate:"+tawRmRecord.getDutydate());
            pstmt.setString(11, tawRmRecord.getClean());
            pstmt.setString(12, tawRmRecord.getClean1());
            pstmt.setString(13, tawRmRecord.getConditioner());
            pstmt.setString(14, tawRmRecord.getHander());
            pstmt.setString(15, tawRmRecord.getDutyman());
            pstmt.setString(16, tawRmRecord.getReceiver());
            pstmt.setString(17, tawRmRecord.getDutyrecord());
            pstmt.setString(18, tawRmRecord.getNotes());
            pstmt.setString(19, tawRmRecord.getImportantnetfault());
            pstmt.setString(20, tawRmRecord.getImportantsocietydisaster());
            pstmt.setString(21, tawRmRecord.getNeedcorrespond());
            pstmt.setString(22, tawRmRecord.getHandoverproceeding());
            pstmt.executeUpdate();
            pstmt.close();
            conn.commit();

            connsub = ds.getConnection();
            pstmtsub = connsub.prepareStatement(sqlsub);
            pstmtsub.setInt(1, tawRmRecord.getIdsub());
            pstmtsub.setInt(2, tawRmRecord.getRoomId());
            // System.out.println("workserial="+tawRmRecord.getWorkserial());
            pstmtsub.setInt(3, tawRmRecord.getWorkserial());
            pstmtsub.setString(4, tawRmRecord.getDutymansub());
            pstmtsub.setString(5, tawRmRecord.getStarttimesub());
            pstmtsub.setString(6, StaticMethod.getAddZero(getRightTime(tawRmRecord
                    .getStarttimeDefined())));
            pstmtsub
                    .setString(7, StaticMethod.getAddZero(getRightTime(tawRmRecord.getEndtimeDefined())));
            pstmtsub.setString(8, tawRmRecord.getStatement());
            pstmtsub.setInt(9, tawRmRecord.getWorkflag());
            pstmtsub.setInt(10, tawRmRecord.getStatus());
            pstmtsub.setString(11, tawRmRecord.getNotes());
            pstmtsub.setString(12, tawRmRecord.getImportantnetfault());
            pstmtsub.setString(13, tawRmRecord.getImportantsocietydisaster());
            pstmtsub.setString(14, tawRmRecord.getNeedcorrespond());
            pstmtsub.setString(15, tawRmRecord.getHandoverproceeding());

            pstmtsub.executeUpdate();
            pstmtsub.close();
            connsub.commit();

        } catch (SQLException sqle) {
            close(rs);
            close(pstmt);
            rollback(conn);
            close(rssub);
            close(pstmtsub);
            rollback(connsub);

            sqle.printStackTrace();
            throw sqle;
        } finally {
            close(conn);
            close(connsub);
            // null
            sql = null;
            sqlsub = null;
            rs = null;
            rssub = null;
        }
    }

    /**
     * @param tawRmRecord
     * @throws SQLException
     * @see 更新值班主表
     */
    public void update(Kmrecord tawRmRecord) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;

        try {
            conn = ds.getConnection();


            sql = "UPDATE km_record SET region_id=?, room_id=?, flag=?, "
                    + "starttime=?, endtime=?, weather=?, temperature=?, wet=?,"
                    + " dutydate=?, clean=?, clean1=?, conditioner=?, hander=?,"
                    + " dutyman=?, receiver=?, dutyrecord=?, notes=?,au_content =?,"
                    + "auditor =?,au_time =?, importantnetfault=?, importantsocietydisaster=?,"
                    + " needcorrespond=?, handoverproceeding=? WHERE id=?"; // changed by sxs,add by sunshengtai
            pstmt = conn.prepareStatement(sql);
            // System.out.println(" tawrmrecord id is " +tawRmRecord.getId());
            pstmt.setInt(1, tawRmRecord.getRegionId());
            pstmt.setInt(2, tawRmRecord.getRoomId());
            pstmt.setInt(3, tawRmRecord.getFlag());
            // pstmt.setTimestamp(4, tawRmRecord.getStarttime());
            // pstmt.setTimestamp(5, tawRmRecord.getEndtime());
            pstmt.setString(4, tawRmRecord.getStarttime().trim());
            pstmt.setString(5, tawRmRecord.getEndtime().trim());
            pstmt.setString(6, tawRmRecord.getWeather());
            pstmt.setInt(7, tawRmRecord.getTemperature());
            pstmt.setInt(8, tawRmRecord.getWet());
            // pstmt.setTimestamp(9, tawRmRecord.getDutydate());
            pstmt.setString(9, tawRmRecord.getDutydate());
            pstmt.setString(10, tawRmRecord.getClean());
            pstmt.setString(11, tawRmRecord.getClean1());
            pstmt.setString(12, tawRmRecord.getConditioner());
            pstmt.setString(13, tawRmRecord.getHander());
            pstmt.setString(14, tawRmRecord.getDutyman());
            pstmt.setString(15, tawRmRecord.getReceiver());
            pstmt.setString(16, tawRmRecord.getDutyrecord());
            pstmt.setString(17, tawRmRecord.getNotes());
            pstmt.setString(18, tawRmRecord.getAuContent());
            pstmt.setString(19, tawRmRecord.getAuditor());
            pstmt.setString(20, tawRmRecord.getAuTime());

            pstmt.setString(21, tawRmRecord.getImportantnetfault());
            pstmt.setString(22, tawRmRecord.getImportantsocietydisaster());
            pstmt.setString(23, tawRmRecord.getNeedcorrespond());
            pstmt.setString(24, tawRmRecord.getHandoverproceeding());

            pstmt.setInt(25, tawRmRecord.getId());
            pstmt.executeUpdate();
            close(pstmt);
            conn.commit();
        } catch (SQLException e) {
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
            sql = null;
        }
    }

    /**
     * @param id
     * @throws SQLException
     * @see 删除值班子记录
     */
    public void delete(int id) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "DELETE FROM km_record WHERE id=?";
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
            sql = null;
        }
    }

    /**
     * @param id
     * @return
     * @throws SQLException
     * @see 得到值班主表对应对象
     */
    public Kmrecord retrieve(int id) throws SQLException {
        Kmrecord tawRmRecord = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "SELECT * FROM km_record WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                tawRmRecord = new Kmrecord();
                populate(tawRmRecord, rs);
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
            sql = null;
            rs = null;
        }
        return tawRmRecord;
    }

    /**
     * @param id
     * @return
     * @throws SQLException
     * @see 得到值班主表对应对象，并把时间格式字段修改，去掉小时或".0"
     */
    public Kmrecord retrieve1(int id) throws SQLException {
        Kmrecord tawRmRecord = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "SELECT * FROM km_record WHERE id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                tawRmRecord = new Kmrecord();
                tawRmRecord.setId(id);
                tawRmRecord.setClean(rs.getString("clean"));
                tawRmRecord.setClean1(rs.getString("clean1"));
                tawRmRecord.setConditioner(rs.getString("conditioner"));
                tawRmRecord.setDutydate(StaticMethod.null2String(
                        rs.getString("dutydate")).replaceAll("00:00:00.0", "")
                        .trim());
                tawRmRecord.setDutyman(rs.getString("dutyman"));
                tawRmRecord.setDutyrecord(rs.getString("dutyrecord"));
                tawRmRecord.setEndtime(StaticMethod.null2String(
                        rs.getString("endtime")).replaceAll("\\.0", ""));
                tawRmRecord.setFlag(rs.getInt("flag"));
                tawRmRecord.setNotes(rs.getString("notes"));
                tawRmRecord.setReceiver(rs.getString("receiver"));
                tawRmRecord.setRegionId(rs.getInt("region_id"));
                tawRmRecord.setRoomId(rs.getInt("room_id"));
                tawRmRecord.setStarttime(StaticMethod.null2String(
                        rs.getString("starttime")).replaceAll("\\.0", ""));
                tawRmRecord.setWet(rs.getInt("wet"));
                tawRmRecord.setWeather(rs.getString("weather"));
                tawRmRecord.setTemperature(rs.getInt("temperature"));
                tawRmRecord.setHander(rs.getString("hander"));
                tawRmRecord.setImportantnetfault(rs.getString("importantnetfault"));
                tawRmRecord.setImportantsocietydisaster(rs.getString("importantsocietydisaster"));
                tawRmRecord.setNeedcorrespond(rs.getString("needcorrespond"));
                tawRmRecord.setHandoverproceeding(rs.getString("handoverproceeding"));
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
            sql = null;
            rs = null;
        }
        return tawRmRecord;
    }

    /**
     * @return
     * @throws SQLException
     * @see 得到值班记录列表
     */
    public List list() throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "SELECT region_id, room_id, flag, starttime, endtime, weather, temperature, wet, dutydate, clean, clean1, conditioner, hander, dutyman, receiver, dutyrecord, notes, id FROM km_record";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Kmrecord tawRmRecord = new Kmrecord();
                // tawRmRecord.setRegionId(rs.getInt(1));
                populate(tawRmRecord, rs);
                list.add(tawRmRecord);
                tawRmRecord = null;
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
            sql = null;
            rs = null;
        }
        return list;
    }

    /**
     * @param offset
     * @param limit
     * @return
     * @throws SQLException
     * @see 得到值班记录列表
     */
    public List list(int offset, int limit) throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "SELECT region_id, room_id, flag, starttime, endtime, weather, temperature, wet, dutydate, clean, clean1, conditioner, hander, dutyman, receiver, dutyrecord, notes, id FROM km_record";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (offset > 0) {
                rs.absolute(offset);
            }
            int recCount = 0;
            while ((recCount++ < limit) && rs.next()) {
                Kmrecord tawRmRecord = new Kmrecord();
                populate(tawRmRecord, rs);
                list.add(tawRmRecord);
                tawRmRecord = null;
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
            sql = null;
            rs = null;
        }
        return list;
    }

    /**
     * @param workserial
     * @return
     * @throws SQLException
     * @see 得到值班人员列表
     */
    public List listDutyman(int workserial) throws SQLException {
        ArrayList listDutyman = new ArrayList();
        KmrecordSubDAO tawRmRecordSubDAO = new KmrecordSubDAO(ds);
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "select t.dutyman,u.username from km_record_sub t,taw_System_user u where t.workserial = "
                    + workserial + " and t.dutyman = u.userid ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                KmrecordSub tawRmRecordSub = new KmrecordSub();
                String strDutyman = rs.getString(1);
                String strUsername = rs.getString(2);
                tawRmRecordSub = tawRmRecordSubDAO.retrieve_sub(workserial,
                        strDutyman);
                tawRmRecordSub.setDutyman(strUsername);
                listDutyman.add(tawRmRecordSub);
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
            tawRmRecordSubDAO = null;
            sql = null;
            rs = null;
        }
        return listDutyman;
    }


    /**
     * @param workserial
     * @return
     * @throws SQLException
     * @see 得到值班人员列表返回用户对象
     */
    public List listDutymanInfo(int workserial) throws SQLException {
        ArrayList listDutyman = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "select a.dutyman,u.username,u.deptid from km_assign_sub a,taw_System_user u where a.workserial = "
                    + workserial + " and a.dutyman = u.userid ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                TawSystemUser tawSystemUser = new TawSystemUser();
                String userid = rs.getString(1);
                String username = rs.getString(2);
                String deptid = rs.getString(3);
                tawSystemUser.setUserid(userid);
                tawSystemUser.setUsername(username);
                tawSystemUser.setDeptid(deptid);
                listDutyman.add(tawSystemUser);
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
            sql = null;
            rs = null;
        }
        return listDutyman;
    }

    /**
     * @param timeStr
     * @return
     * @see 处理时间字符串，去掉后面两个字符串：2003-04-03 11:54:18.0 -》2003-04-03 11:54:18
     */
    public String getRightTime(String timeStr) {
        timeStr = timeStr.trim();
        int strLength = timeStr.length();
        if (strLength > 2) {
            String errStr = timeStr.substring(strLength - 2);
            if (errStr.equals(".0")) {
                timeStr = timeStr.substring(0, strLength - 2);
            }
        }
        return timeStr;
    }

    /**
     * @param offset
     * @param limit
     * @param condition
     * @return
     * @throws SQLException
     * @see 得到值班记录列表
     */
    public List search(int offset, int limit, String condition)
            throws SQLException {
        ArrayList search = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "SELECT * FROM km_record " + condition;
            System.out.println("sql====" + sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (offset > 0) {
                rs.next();
                offset--;
            }
            // rs.absolute(offset);
            int recCount = 0;
            while ((recCount++ < limit) && rs.next()) {
                Kmrecord tawRmRecord = new Kmrecord();
                populate(tawRmRecord, rs);
                tawRmRecord.setAuditor(getUserName(tawRmRecord.getAuditor()));
                search.add(tawRmRecord);
                tawRmRecord = null;
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
            sql = null;
            rs = null;
        }
        return search;
    }

    /**
     * @param offset
     * @param limit
     * @param condition
     * @return
     * @throws SQLException
     * @see 得到值班记录列表
     */
    public List search7Days(int deptid) throws SQLException {
        ArrayList search = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        String sql = null;

        java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        java.util.Calendar cl = java.util.Calendar.getInstance();
        String endtime = sf.format(cl.getTime());
        cl.add(java.util.Calendar.DAY_OF_YEAR, -7);
        cl.set(java.util.Calendar.HOUR_OF_DAY, 0);
        cl.set(java.util.Calendar.MINUTE, 0);
        cl.set(java.util.Calendar.SECOND, 0);
        String starttime = sf.format(cl.getTime());

        try {
            conn = ds.getConnection();
            sql = "SELECT a.* FROM km_record a,km_systeminfo b where a.room_id = b.room_id and b.dept_id="
                    + deptid
                    + " and a.starttime >= '"
                    + starttime
                    + "' and a.starttime <= '"
                    + endtime
                    + "' order by starttime ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Kmrecord tawRmRecord = new Kmrecord();
                populate(tawRmRecord, rs);
                String record = "", sql1 = "", record1 = "", record2 = "", record3 = "", record4 = "", record5 = "", record6 = "", record7 = "", record8 = "", record9 = "", record0 = "";

                sql1 = " select dutyman, statement,typemode,trans,switch,data,system,other,dutySummary,importRecord, importFault,"
                        + " netCut, netKPI from km_record_sub where workserial="
                        + tawRmRecord.getId();
                sql1 += " order by typemode ";
                pstmt1 = conn.prepareStatement(sql1);
                rs1 = pstmt1.executeQuery();
                while (rs1.next()) {

                    record1 = record1 + getUserName(rs1.getString(1)) + ":"
                            + StaticMethod.null2String(rs1.getString(4)).trim();
                    record2 = record2 + "" + getUserName(rs1.getString(1))
                            + ":"
                            + StaticMethod.null2String(rs1.getString(5)).trim();
                    record3 = record3 + "" + getUserName(rs1.getString(1))
                            + ":"
                            + StaticMethod.null2String(rs1.getString(6)).trim();
                    record4 = record4 + "" + getUserName(rs1.getString(1))
                            + ":"
                            + StaticMethod.null2String(rs1.getString(7)).trim();
                    record5 = record5 + "" + getUserName(rs1.getString(1))
                            + ":"
                            + StaticMethod.null2String(rs1.getString(8)).trim();
                    record6 = record6 + getUserName(rs1.getString(1)) + ":"
                            + StaticMethod.null2String(rs1.getString(9)).trim();
                    record7 = record2
                            + ""
                            + getUserName(rs1.getString(1))
                            + ":"
                            + StaticMethod.null2String(rs1.getString(10))
                            .trim();
                    record8 = record3
                            + ""
                            + getUserName(rs1.getString(1))
                            + ":"
                            + StaticMethod.null2String(rs1.getString(11))
                            .trim();
                    record9 = record4
                            + ""
                            + getUserName(rs1.getString(1))
                            + ":"
                            + StaticMethod.null2String(rs1.getString(12))
                            .trim();
                    record0 = record5
                            + ""
                            + getUserName(rs1.getString(1))
                            + ":"
                            + StaticMethod.null2String(rs1.getString(13))
                            .trim();
                }
                record = "传输专业  " + record1 + "交换专业  " + record2 + "数据专业  "
                        + record3 + "系统专业  " + record4 + "其它   " + record5
                        + "值班综述  " + record6 + "重要纪事  " + record7 + "重大故障  "
                        + record8 + "网络割接  " + record9 + "网络KPI  " + record0;

                tawRmRecord.setDutyrecord(record);
                rs1 = null;
                search.add(tawRmRecord);
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
            sql = null;
            rs = null;
        }
        return search;
    }

    /**
     * @param offset
     * @param limit
     * @param name
     * @param date
     * @param roomid
     * @return
     * @throws SQLException
     * @see 根据日期机房ID和值班人员ID得到值班班长列表
     */
    public List listReceiverMaster(int offset, int limit, String name,
                                   String date, int roomid) throws SQLException {
        ArrayList listReceiverMaster = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "select km_assignwork.dutymaster "
                    + "from km_assignwork "
                    + "where km_assignwork.starttime_defined in "
                    + "(select distinct km_assignwork.endtime_defined "
                    + "from km_assign_sub, km_assignwork "
                    + "where km_assignwork.id in "
                    + "(select km_assign_sub.workserial "
                    + "from km_assign_sub where km_assign_sub.dutyman = "
                    + "'" + name + "'" + ")"
                    + " and km_assignwork.endtime_defined >=" + "'" + date
                    + "'" + " and km_assignwork.starttime_defined <= "
                    + "'" + date + "'" + " and km_assignwork.room_id = "
                    + roomid + ")";
            System.out.println("sql=" + sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (offset > 0) {
                rs.absolute(offset);
            }
            int recCount = 0;

            while ((recCount++ < limit) && rs.next()) {
                Kmrecord tawRmRecord = new Kmrecord();
                tawRmRecord.setDutymaster(rs.getString(1));
                listReceiverMaster.add(tawRmRecord);
                tawRmRecord = null;
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
            sql = null;
            rs = null;
        }
        return listReceiverMaster;
    }

    /**
     * @param workserial
     * @return
     * @throws SQLException
     * @see 得到值班流水号对应值班人员ID列表
     */
    public Vector getOneAssignMember(int workserial) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Vector dutyman = new Vector();
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "Select a.id,a.Dutyman,u.username from km_assign_sub a,taw_System_user u where a.workserial = "
                    + workserial + " and a.dutyman = u.userid order by a.id";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                dutyman.add(rs.getString(2));
            }
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
            sql = null;
            rs = null;
        }
        return dutyman;
    }

    /**
     * @param workserial
     * @return
     * @throws SQLException
     * @see 根据值班流水号ID，得到值班人员名称列表
     */

    public Vector getOneAssignMemberName(int workserial) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Vector dutyman = new Vector();
        try {
            conn = ds.getConnection();
            String sql = "Select a.id,a.Dutyman,u.username from km_assign_sub a,taw_System_user u where a.workserial = "
                    + workserial + " and a.dutyman = u.userid order by a.id";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                dutyman.add(String.valueOf(rs.getString(3)));
            }
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return dutyman;
    }

    /**
     * @param workserial
     * @param serialtype
     * @return
     * @throws SQLException
     * @see 根据值班班次号，得到上下班次班次号，前一班次 serialtype＝ － 1，后一班次 serialtype＝－ 2
     */
    public int getExSerial(int workserial, int serialtype, int stagger) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int getExSerial = -1;
        String Sql = null;
        String sqlw = null;
        try {
            conn = ds.getConnection();
            Sql = "select * from km_assignwork where id = " + workserial
                    + "";
            sqlw = "";
            pstmt = conn.prepareStatement(Sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String startTime = StaticMethod.getLocalString(StaticMethod.getDateForMinute(getRightTime(rs.getString(8)), stagger * 60));
                String endTime = StaticMethod.getLocalString(StaticMethod.getDateForMinute(getRightTime(rs.getString(9)), -stagger * 60));
                if (serialtype == 1) { // 前一班次
                    sqlw = "Select id from km_assignwork where room_id = "
                            + rs.getInt(3) + " and endtime_defined = '"
                            + startTime + "'";
                } else if (serialtype == 2) { // 后一班次
                    sqlw = "Select id from km_assignwork where room_id = "
                            + rs.getInt(3) + " and starttime_defined = '"
                            + endTime + "'";
                }
                // System.out.println(sqlw);
                pstmt = conn.prepareStatement(sqlw);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    getExSerial = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
            Sql = null;
            sqlw = null;
            rs = null;
        }
        return getExSerial;
    }

    /**
     * @param roomId
     * @param time
     * @return
     * @throws SQLException
     * @see 得到输入时间、该机房的班次号
     */
    public int receiverWorkSerial(String roomId, String time, String userid)
            throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int workserial = 0;
        String sql = null;
        try {
            conn = ds.getConnection();

            try {

                sql = "select a.id,a.workid,a.starttime_defined from km_assignwork a ,km_assign_sub b "
                        + "where a.room_id = " + roomId + " "
                        + " and  a.starttime_defined <= '" + StaticMethod.getLocalString(time)
                        + "' and a.endtime_defined >='" + StaticMethod.getLocalString(time) + "'"
                        + " and a.flag = 0  and a.id = b.workserial and b.dutyman = '" + userid + "' order by a.starttime_defined, a.workid";
            } catch (Exception ex) {
            }

            // System.out.println("sql=" + sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Kmrecord tawRmRecord = new Kmrecord();
                tawRmRecord.setWorkserial(rs.getInt(1));
                workserial = rs.getInt(1);
                tawRmRecord = null;
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
            sql = null;
            rs = null;
        }
        return workserial;
    }

    /**
     * @param roomId
     * @param time
     * @return
     * @throws SQLException
     * @see 得到当前时间、该机房的班次号
     */
    public int receivedrWorkSerial(int roomId) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int workserial = 0;
        String strDutyTime = null;
        String sql = null;
        try {
            java.util.Date date = new java.util.Date();
            java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            strDutyTime = dtFormat.format(date);
            conn = ds.getConnection();
            sql = "select id,workid,starttime_defined from km_assignwork "
                    + "where room_id = " + roomId + " "
                    + " and  starttime_defined <= '" + strDutyTime
                    + "' and endtime_defined >='" + strDutyTime + "'"
                    + " and flag = 0 order by starttime_defined, workid";

            // System.out.println("sql=" + sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Kmrecord tawRmRecord = new Kmrecord();
                tawRmRecord.setWorkserial(rs.getInt(1));
                workserial = rs.getInt(1);
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
            strDutyTime = null;
            sql = null;
            rs = null;
        }
        return workserial;
    }

    /**
     * @param roomId
     * @return
     * @throws SQLException
     * @see 根据机房ID，得到当前时间德班次号
     */
    public int receiverWorkSerial(String roomId) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int workserial = 0;
        String strDutyTime = null;
        String sql = null;
        try {
            java.util.Date date = new java.util.Date();
            java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            strDutyTime = dtFormat.format(date);
            conn = ds.getConnection();
            sql = "select id,workid,starttime_defined from km_assignwork "
                    + "where room_id = '" + roomId + "' "
                    + " and  starttime_defined <= '" + strDutyTime
                    + "' and endtime_defined >='" + strDutyTime + "'"
                    + " and flag = 0 order by starttime_defined, workid";

            // System.out.println("sql=" + sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Kmrecord tawRmRecord = new Kmrecord();
                tawRmRecord.setWorkserial(rs.getInt(1));
                workserial = rs.getInt(1);
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
            strDutyTime = null;
            sql = null;
            rs = null;
        }
        return workserial;
    }

    /**
     * @param workserial
     * @return
     * @throws SQLException
     * @see 得到班次号对应值班班长
     */
    public String receiverDutyMaster(int workserial) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String DutyMaster = "";
        String sql = null;
        try {
            java.util.Date date = new java.util.Date();
            java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            String strDutyTime = dtFormat.format(date);
            conn = ds.getConnection();
            sql = "Select DutyMaster from km_assignwork where ID =  "
                    + workserial + "";

            // System.out.println("sql=" + sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Kmrecord tawRmRecord = new Kmrecord();
                tawRmRecord.setDutymaster(rs.getString(1));
                DutyMaster = StaticMethod.null2String(rs.getString(1));
                tawRmRecord = null;
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
            sql = null;
            rs = null;
        }
        return DutyMaster;
    }

    /**
     * @param workserial
     * @param strDutyMan
     * @return true，是;false，否
     * @throws SQLException
     * @see 查看人员对于班次号是否为值班人员
     */
    public boolean receiverIsDutyMan(int workserial, String strDutyMan)
            throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean IsDutyMan = false;
        String sql = null;
        String DutyMan = null;
        try {
            conn = ds.getConnection();
            sql = "Select DutyMan from km_assign_sub where WorkSerial =  "
                    + workserial + "";
            System.out.println("sqll=" + sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                DutyMan = StaticMethod.null2String(rs.getString(1));
                if (DutyMan.equals(strDutyMan)) {
                    IsDutyMan = true;
                    break;
                }
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
            rs = null;
            sql = null;
            DutyMan = null;
        }
        return IsDutyMan;
    }

    /**
     * @param workserial
     * @return true，存在；false，不存在
     * @throws SQLException
     * @see 查看班次号为workserial的值班记录是否存在
     */
    public boolean receiverCheckMainRecordExist(int workserial)
            throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean blExist = false;
        Vector MainRecord = new Vector();
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "select id from km_record where id =  " + workserial + "";
            // System.out.println("sql=" + sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                MainRecord.add(new Integer(rs.getInt(1)));
            }
            if (MainRecord.size() > 0) {
                blExist = true;
            } else {
                blExist = false;
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
            MainRecord = null;
            rs = null;
            sql = null;
        }
        return blExist;
    }

    /**
     * @param workserial
     * @param userid
     * @return true，存在；false，不存在
     * @throws SQLException
     * @see 查看该班次号，该人员的值班子记录是否存在
     */
    public boolean receiverCheckSubRecordExist(int workserial, String userid)
            throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean blExist = false;
        Vector SubRecord = new Vector();
        String sqls = null;

        try {
            conn = ds.getConnection();
            sqls = "Select id from km_record_sub where WorkSerial = "
                    + workserial + " and DutyMan = '" + userid + "'";
            // System.out.println("sql=" + sql);
            pstmt = conn.prepareStatement(sqls);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SubRecord.add(new Integer(rs.getInt(1)));
            }
            if (SubRecord.size() > 0) {
                blExist = true;
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
            SubRecord = null;
            sqls = null;
            rs = null;
        }
        return blExist;
    }

    /**
     * @param workserial
     * @param userId
     * @throws SQLException
     * @see 根据班次号和人员ID，生成初始化的值班子记录
     */
    public void getSaveSubRecord(int workserial, String userId)
            throws SQLException {
        String sql = "INSERT INTO km_record_sub(room_id,workserial,"
                + "dutyman,Starttime,Starttime_defined,"
                + "Endtime_defined,WorkFlag,status) "
                + "values (?,?,?,?,?,?,?,?)";

        com.boco.eoms.db.util.BocoConnection conn = null;
        com.boco.eoms.db.util.BocoConnection conncreate = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtcreate = null;
        ResultSet rs = null;
        String strDutyDate = null;
        String Sql = null;
        KmsysteminfoDAO tawRmSysteminfoDAO = null;
        Kmsysteminfo tawRmSysteminfo = null;
        GregorianCalendar cal_end = null;
        KmrecordDAO tawRmRecordDAO = null;
        String time_end = null;

        try {
            SaveSessionBeanDAO saveSessionBeanDAO = new SaveSessionBeanDAO(ds);
            saveSessionBeanDAO.alterDateFormat();
            // 判断是否存在此值班人
            // 判断值班子记录是否存在
            // 如果不存在创建
            saveSessionBeanDAO = null;
            java.util.Date date = new java.util.Date();
            java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            strDutyDate = dtFormat.format(date);

            // 取基本信息
            conn = ds.getConnection();
            Sql = "Select id, room_id, region_id, StartTime_defined,EndTime_defined,Dutydate,Dutymaster from km_assignwork where id = "
                    + workserial + "";
            pstmt = conn.prepareStatement(Sql);
            rs = pstmt.executeQuery();
            conncreate = ds.getConnection();
            if (rs.next()) {
                // 在创建
                // 默认状态为未刷卡
                int int_WorkFlag = 0;
                // 当前时间-最大误差时间，是否属于本班次
                // 如果属于，说明该人迟到

                // 取当前机房误差时间
                tawRmSysteminfoDAO = new KmsysteminfoDAO(ds);
                tawRmSysteminfo = tawRmSysteminfoDAO.retrieve(rs.getString(2));
                int maxerrortime = 0;
                if (tawRmSysteminfo != null) {
                    maxerrortime = tawRmSysteminfo.getMaxerrortime();
                }
                // 得到当前时间-最大误差时间
                cal_end = new GregorianCalendar();
                maxerrortime = -maxerrortime;
                cal_end.add(cal_end.MINUTE, maxerrortime);
                time_end = String.valueOf(cal_end.get(cal_end.YEAR));
                time_end = time_end + "-"
                        + String.valueOf(cal_end.get(cal_end.MONTH) + 1);
                time_end = time_end + "-"
                        + String.valueOf(cal_end.get(cal_end.DATE));
                time_end = time_end + " "
                        + String.valueOf(cal_end.get(cal_end.HOUR_OF_DAY));
                time_end = time_end + ":"
                        + String.valueOf(cal_end.get(cal_end.MINUTE));
                time_end = time_end + ":"
                        + String.valueOf(cal_end.get(cal_end.SECOND));
                // 该时间属于哪个值班班次，如果属于本班次，迟到
                tawRmRecordDAO = new KmrecordDAO(ds);
                int workserial1 = tawRmRecordDAO.receiverWorkSerial(rs
                        .getString(2), time_end, userId);
                if (workserial1 == workserial && workserial != 0) {
                    int_WorkFlag = 1;
                    if (getRightTime(rs.getString(4)).indexOf(" 00:00:00") > 0)
                        int_WorkFlag = 4;
                }

                // 新建值班子记录
                pstmtcreate = conncreate.prepareStatement(sql);
                pstmtcreate.setInt(1, rs.getInt(2));
                pstmtcreate.setInt(2, workserial);
                pstmtcreate.setString(3, userId);
                pstmtcreate.setString(4, strDutyDate);
                pstmtcreate.setString(5, getRightTime(rs.getString(4)));
                pstmtcreate.setString(6, getRightTime(rs.getString(5)));
                pstmtcreate.setInt(7, int_WorkFlag);
                pstmtcreate.setInt(8, 0);
                pstmtcreate.executeUpdate();
                pstmtcreate.close();
                System.out.println("sql=" + sql);
                conncreate.commit();
            }
            // System.out.println("inster=" + pstmtcreate.toString());
            close(rs);
            close(pstmt);
            close(pstmtcreate);
        } catch (SQLException sqle) {
            close(rs);
            close(pstmt);
            close(pstmtcreate);
            rollback(conn);
            rollback(conncreate);
            sqle.printStackTrace();
        } finally {
            close(conn);
            close(conncreate);
            sql = null;
            rs = null;
            strDutyDate = null;
            Sql = null;
            tawRmSysteminfoDAO = null;
            tawRmSysteminfo = null;
            cal_end = null;
            tawRmRecordDAO = null;
            time_end = null;

        }
    }

    /**
     * @param workserial
     * @return
     * @throws SQLException
     * @see 合并值班日值
     */
    public String coalitionSub(int workserial) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        com.boco.eoms.db.util.BocoConnection conns = null;
        PreparedStatement pstmts = null;
        ResultSet rss = null;

        boolean blExist = false;
        Vector SubRecord = new Vector();
        String[] arrName;
        String strStatement = "";
        String dutymanName = "";
        String strDutyRecord = "";
        String strDutyRecordAll = "";
        String sql = null;
        String sqls = null;
        try {
            conn = ds.getConnection();
            sql = "Select Dutyman from km_assign_sub where workserial = "
                    + workserial + "";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            String dutyman = "";
            while (rs.next()) { // 判断主记录是否存在
                dutyman = StaticMethod.null2String(rs.getString(1));
                // 提取值班子记录
                if (dutyman.equals("")) {

                } else { // 如果值班祝记录存在
                    conns = ds.getConnection();
                    sqls = "Select id, DutyMan, Statement from km_record_sub where WorkSerial = "
                            + workserial + " And Dutyman = '" + dutyman + "'";
                    pstmts = conns.prepareStatement(sqls);
                    rss = pstmts.executeQuery();
                    if (rss.next()) {
                        if (rss.getString(3) == null) {
                            strStatement = "日志为空";
                        } else {
                            strStatement = StaticMethod.null2String(rss
                                    .getString(3));
                        }
                    } else {
                        strStatement = " 没有日志记录";
                    }
                    // 用户id转换为用户名称
                    dutymanName = getUserName(dutyman);
                    strDutyRecordAll += dutymanName + "：" + strStatement
                            + "<p>";
                }
                close(rss);
                close(pstmts);
            }

            close(rs);
            close(pstmt);

        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            rollback(conn);
            close(rss);
            close(pstmts);
            rollback(conns);
            e.printStackTrace();
        } finally {
            close(conn);
            close(conns);
            SubRecord = null;
            arrName = null;
            strStatement = null;
            dutymanName = null;
            strDutyRecord = null;
            // strDutyRecordAll= null;
            sql = null;
            sqls = null;
            rs = null;

        }
        return strDutyRecordAll;
    }

    /**
     * @param userid
     * @return
     * @throws SQLException
     * @see 根据值班人员ID，得到人员用户名
     */
    public String getUserName(String userid) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String strUserName = "";
        String sqls = null;

        try {
            conn = ds.getConnection();
            sqls = " Select UserNAME From TAW_system_USER WHERE Userid = '"
                    + userid + "'";
            pstmt = conn.prepareStatement(sqls);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                strUserName = StaticMethod.null2String(rs.getString(1));
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
            sqls = null;
            rs = null;
        }
        return strUserName;
    }

    /**
     * @param userid
     * @param password
     * @return true，密码正确；false，密码错误
     * @throws SQLException
     * @see 判断值班人员ID和密码是否匹配
     */
    public boolean getVerifyUser(String userid, String password)
            throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean blnReturn = false;
        String message = "";
        String Sql = null;

        try {
            conn = ds.getConnection();
            Sql = "Select password from taw_system_user where userid = '"
                    + userid + "' and Deleted = 0";
            pstmt = conn.prepareStatement(Sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (password.equals(rs.getString(1))) {
                    blnReturn = true;
                } else {
                    message = "用户名和密码不相符！";
                }
            } else {
                message = "用户不存在或已被删除！";
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
            Sql = null;
            message = null;
            rs = null;

        }
        return blnReturn;
    }

    // 2003-12-10,ZC,通过机房和时间得到统计结果
    // 2004-03-08,ZC,修改功能，增加对排班了但未上班人的统计

    /**
     * @param roomId
     * @param starttime
     * @param endtime
     * @return
     * @throws SQLException
     * @see 得到该机房、该时间段内所有班次号的串，调用getStatisticsVector方法，得到值班人员的考勤情况
     */
    public Vector getStatisticsVector(int roomId, String starttime,
                                      String endtime) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String WorkserialString = "-1";
        String sql = null;
        Vector getStatistics = new Vector();

        try {
            conn = ds.getConnection();
            sql = " Select id From Km_ASSIGNWORK WHERE room_id = ? and starttime_defined >= ? and starttime_defined <= ? ";
            //and extend(starttime_defined,hour to second)<>'00:00:00'
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomId);
            pstmt.setString(2, starttime);
            pstmt.setString(3, endtime);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                WorkserialString += "," + String.valueOf(rs.getInt(1));
            }
            close(rs);
            close(pstmt);
            getStatistics = getStatisticsVector(WorkserialString);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
            sql = null;
            rs = null;
        }
        return getStatistics;
    }

    // 2003-12-10,ZC,通过值班班次号得到统计结果
    // 2004-03-08,ZC,修改功能，增加对排班了但未上班人的统计

    /**
     * @param WorkserialString
     * @return
     * @throws SQLException
     * @see 根据班次号的字符串得到人员，从而得到对应人员考勤情况统计
     */
    public Vector getStatisticsVector(String WorkserialString)
            throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Vector getStatistics = new Vector();
        String sql = null;

        try {
            conn = ds.getConnection();
            sql = " Select distinct s.dutyman ,u.username From Km_ASSIGN_SUB s , TAW_SYSTEM_USER u WHERE s.dutyman=u.userid and s.workserial in ("
                    + WorkserialString + ")";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Vector getStatistic = new Vector();
                String dutyman = String.valueOf(rs.getString(1));
                getStatistic.add(rs.getString(2));
                getStatistic = getStatisticVector(dutyman, getStatistic,
                        WorkserialString);
                getStatistics.add(getStatistic);
                getStatistic = null;
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
            sql = null;
            rs = null;
        }
        return getStatistics;
    }

    // 2003-12-10,ZC,得到每个人的迟到和早退次数
    // 2004-03-08,ZC,修改功能，增加对排班了但未上班人的统计

    /**
     * @param dutyman
     * @param getStatistic
     * @param WorkserialString
     * @return
     * @throws SQLException
     * @see 根据该人员和值班班次号，得到考勤统计情况
     */
    public Vector getStatisticVector(String dutyman, Vector getStatistic,
                                     String WorkserialString) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        int int1 = 0;
        int int2 = 0;
        int int3 = 0;
        try {
            conn = ds.getConnection();
            sql = " select workflag,count(workflag) From Km_RECORD_SUB where dutyman=? and workserial in ("
                    + WorkserialString
                    + ") and workflag in (1,2,3) group by workflag";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dutyman);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    int1 = rs.getInt(2);
                }
                if (rs.getInt(1) == 2) {
                    int2 = rs.getInt(2);
                }
                if (rs.getInt(1) == 3) {
                    int3 = rs.getInt(2);
                }
            }
            close(rs);
            close(pstmt);
            sql = "select count(*) from Km_ASSIGN_SUB where dutyman=? and workserial in ("
                    + WorkserialString
                    + ") and workserial not in (select workserial From Km_RECORD_SUB where dutyman=? )";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dutyman);
            pstmt.setString(2, dutyman);
            rs = pstmt.executeQuery();
            int int4 = 0;
            if (rs.next()) {
                int4 = rs.getInt(1);
            }
            close(rs);
            close(pstmt);
            int3 += int4;
            int1 += int3;
            int2 += int3;
            getStatistic.add(String.valueOf(int1));
            getStatistic.add(String.valueOf(int2));
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
            sql = null;
            rs = null;
        }
        return getStatistic;
    }

    /**
     * add by 邓林华 2005-10-08
     *
     * @param roomId
     * @param starttime
     * @param endtime
     * @return
     * @throws SQLException
     * @see 返回个人值班班次的统计结果
     */
    public List getDutyStatistics(int roomId, String starttime, String endtime)
            throws SQLException {
        // this.ds = com.boco.eoms.db.util.ConnectionPool.getInstance();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String WorkserialString = "";
        String sql = null;
        ArrayList dutyList = new ArrayList();

        try {
            conn = ds.getConnection();
            // 将数据插入临时表
            sql = " select extend(starttime_defined,hour to second)||'-'||extend(endtime_defined,hour to second) defined_time , dutyman , username from km_record_sub , taw_System_user where room_id=? and starttime_defined >= ? and starttime_defined <= ? and dutyman=userid order by dutyman,defined_time "
                    + " into temp dutystatistic ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomId);
            pstmt.setString(2, starttime);
            pstmt.setString(3, endtime);
            // System.out.println("rooId=" + roomId + ";StartT=" + starttime +
            // ";endT=" + endtime);
            pstmt.execute();

            // 从临时表中提取值班的所有时间段
            sql = "select distinct defined_time from dutystatistic order by 1";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            String definedTime = "";
            while (rs.next()) {
                if ("".equals(definedTime)) {
                    definedTime = rs.getString(1).substring(0, 17);
                } else {
                    definedTime = definedTime + ","
                            + rs.getString(1).substring(0, 17);
                }
            }

            dutyList.add(definedTime); // 列表中的第一个对象存放所有时间段

            // 从临时表中提取数据到QO对象中
            sql = "select user_name , defined_time , count(*) times from dutystatistic "
                    + "group by dutyman , defined_time , user_name order by user_name , defined_time";
            rs = null;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                DutyStatisticQO dutyStatisticQO = new DutyStatisticQO();
                populate(dutyStatisticQO, rs);

                dutyList.add(dutyStatisticQO);
            }

            // 通过重新分配连接,删除临时表
            conn = ds.getConnection();

        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            close(conn);
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
            close(conn);
            ds.closeAllConnection();
            sql = null;
            rs = null;
        }
        return dutyList;
    }

    public void auditBacth(Kmrecord tawRmRecord, String chkSel)
            throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;

        try {
            conn = ds.getConnection();

            sql = "UPDATE km_record SET au_content =?,"
                    + "auditor =?,au_time =? WHERE id in(" + chkSel + ")";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tawRmRecord.getAuContent());
            pstmt.setString(2, tawRmRecord.getAuditor());
            pstmt.setString(3, tawRmRecord.getAuTime());
            pstmt.executeUpdate();
            close(pstmt);
            conn.commit();
        } catch (SQLException e) {
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
            sql = null;
        }
    }

    public int getAuditPriv(String userId, int roomId) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String sqls = null;
        int flag = 0;

        try {
            // com.boco.eoms.jbzl.bo.TawValidatePrivBO tawValidatePrivBO
            // = new com.boco.eoms.jbzl.bo.TawValidatePrivBO(ds);
            conn = ds.getConnection();
            sqls = "Select manager,temp_manager From taw_apparatusroom WHERE id = '"
                    + roomId + "'";

            pstmt = conn.prepareStatement(sqls);
            rs = pstmt.executeQuery();

            if (rs.next()
                    && (userId.equalsIgnoreCase(rs.getString(1)) || userId
                    .equalsIgnoreCase(rs.getString(2))))
                flag = 1;

            close(pstmt);
            conn.commit();
        } catch (SQLException e) {
            close(pstmt);
            rollback(conn);
            e.printStackTrace();
            rs.close();
        } finally {
            close(conn);
            sqls = null;
            rs = null;
        }
        return flag;
    }

    /**
     * @return
     * @throws SQLException
     * @see 得到值班记录列表
     */
    public List listAutoMerge() throws SQLException {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        String starttime = StaticMethod.getTimeBeginString(-1);
        String endtime = StaticMethod.getTimeBeginString(0);
        try {
            conn = ds.getConnection();
            sql = "SELECT distinct id FROM km_assignwork where starttime_defined='"
                    + starttime + "' and endtime_defined='" + endtime + "'";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Integer(rs.getInt(1)));
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
            sql = null;
            rs = null;
        }
        return list;
    }

    public String getCheckman(String workserial, String dutycheckId)
            throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        String name = com.boco.eoms.duty.util.StaticDutycheck
                .getDutycheckName(dutycheckId);
        String men = name + ":";

        try {
            conn = ds.getConnection();
            sql = "SELECT dutycheck,dutyman FROM km_record_sub where workserial='"
                    + workserial + "'";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (StaticMethod.null2String(rs.getString(1)).lastIndexOf(
                        dutycheckId) != -1) {
                    men = men + " " + getUserName(rs.getString(2));
                }
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
            sql = null;
            rs = null;
        }
        return men;
    }

    /**
     * @param tawRmRecord
     * @param tawRmRecordSub
     * @param isDutymaster
     * @param workserial
     * @param userId
     * @throws SQLException
     * @see 增加by denden(贵州本地)保存个人值班纪录，如果是值班班长保存值班记录
     */
    public void saveRecord1(Kmrecord tawRmRecord,
                            KmrecordSub tawRmRecordSub, boolean isDutymaster,
                            int workserial, String userId, int type) throws SQLException {

        String sql, trans = "", switch1 = "", data = "", system = "", other = "";
        String dutySummary = "", importRecord = "", importFault = "", netCut = "", netKPI = "";

        // 值班住纪录
        sql = "Update Km_RECORD SET Weather = ?,Temperature = ?,WET = ?,Clean = ?,Clean1 = ?,Conditioner = ?,dutyrecord = ?,notes = ?, importantnetfault=?, importantsocietydisaster=?, needcorrespond=?, handoverproceeding=? WHERE ID = "
                + workserial + "";
        // 值班子记录

        String sqlsub = "Update Km_RECORD_SUB SET Statement = ?,dutycheck = ?,typemode= ?,trans= ?, importantnetfault=?, importantsocietydisaster=?, needcorrespond=?, handoverproceeding=? WHERE WorkSerial = "
                + workserial + "  AND Dutyman = '" + userId + "'";

        com.boco.eoms.db.util.BocoConnection conn = null;
        com.boco.eoms.db.util.BocoConnection connsub = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtsub = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        ResultSet rssub = null;
        try {
            if (isDutymaster == true) {
                conn = ds.getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, tawRmRecord.getWeather());
                pstmt.setInt(2, tawRmRecord.getTemperature());
                pstmt.setInt(3, tawRmRecord.getWet());
                pstmt.setString(4, tawRmRecord.getClean());
                pstmt.setString(5, tawRmRecord.getClean1());
                pstmt.setString(6, tawRmRecord.getConditioner());
                pstmt.setString(7, tawRmRecord.getDutyrecord());
                pstmt.setString(8, tawRmRecord.getNotes());
                pstmt.setString(9, tawRmRecord.getImportantnetfault());
                pstmt.setString(10, tawRmRecord.getImportantsocietydisaster());
                pstmt.setString(11, tawRmRecord.getNeedcorrespond());
                pstmt.setString(12, tawRmRecord.getHandoverproceeding());
                pstmt.executeUpdate();
                pstmt.close();
                conn.commit();
            }
            // 记录各个专业的值班日志
            if (type == 1) {
                trans = tawRmRecord.getStatement();
                sqlsub = "Update Km_RECORD_SUB SET Statement = ?,dutycheck = ?,typemode= ?,trans= ?, importantnetfault=?, importantsocietydisaster=?, needcorrespond=?, handoverproceeding=? WHERE WorkSerial = "
                        + workserial + "  AND Dutyman = '" + userId + "'";
            } else if (type == 2) {
                switch1 = tawRmRecord.getStatement();
                sqlsub = "Update Km_RECORD_SUB SET Statement = ?,dutycheck = ?,typemode= ?,switch= ?, importantnetfault=?, importantsocietydisaster=?, needcorrespond=?, handoverproceeding=? WHERE WorkSerial = "
                        + workserial + "  AND Dutyman = '" + userId + "'";
            } else if (type == 3) {
                data = tawRmRecord.getStatement();
                sqlsub = "Update Km_RECORD_SUB SET Statement = ?,dutycheck = ?,typemode= ?,data= ?, importantnetfault=?, importantsocietydisaster=?, needcorrespond=?, handoverproceeding=? WHERE WorkSerial = "
                        + workserial + "  AND Dutyman = '" + userId + "'";
            } else if (type == 4) {
                system = tawRmRecord.getStatement();
                sqlsub = "Update Km_RECORD_SUB SET Statement = ?,dutycheck = ?,typemode= ?,system= ?, importantnetfault=?, importantsocietydisaster=?, needcorrespond=?, handoverproceeding=? WHERE WorkSerial = "
                        + workserial + "  AND Dutyman = '" + userId + "'";
            } else if (type == 5) {
                other = tawRmRecord.getStatement();
                sqlsub = "Update Km_RECORD_SUB SET Statement = ?,dutycheck = ?,typemode= ?,other= ?, importantnetfault=?, importantsocietydisaster=?, needcorrespond=?, handoverproceeding=? WHERE WorkSerial = "
                        + workserial + "  AND Dutyman = '" + userId + "'";
            } else if (type == 6) {
                dutySummary = tawRmRecord.getStatement();
                sqlsub = "Update Km_RECORD_SUB SET Statement = ?,dutycheck = ?,typemode= ?,dutySummary= ?, importantnetfault=?, importantsocietydisaster=?, needcorrespond=?, handoverproceeding=? WHERE WorkSerial = "
                        + workserial + "  AND Dutyman = '" + userId + "'";
            } else if (type == 7) {
                importRecord = tawRmRecord.getStatement();
                sqlsub = "Update Km_RECORD_SUB SET Statement = ?,dutycheck = ?,typemode= ?,importRecord= ?, importantnetfault=?, importantsocietydisaster=?, needcorrespond=?, handoverproceeding=? WHERE WorkSerial = "
                        + workserial + "  AND Dutyman = '" + userId + "'";
            } else if (type == 8) {
                importFault = tawRmRecord.getStatement();
                sqlsub = "Update Km_RECORD_SUB SET Statement = ?,dutycheck = ?,typemode= ?,importFault= ?, importantnetfault=?, importantsocietydisaster=?, needcorrespond=?, handoverproceeding=? WHERE WorkSerial = "
                        + workserial + "  AND Dutyman = '" + userId + "'";
            } else if (type == 9) {
                netCut = tawRmRecord.getStatement();
                sqlsub = "Update Km_RECORD_SUB SET Statement = ?,dutycheck = ?,typemode= ?,netCut= ?, importantnetfault=?, importantsocietydisaster=?, needcorrespond=?, handoverproceeding=? WHERE WorkSerial = "
                        + workserial + "  AND Dutyman = '" + userId + "'";
            } else if (type == 0) {
                netKPI = tawRmRecord.getStatement();
                sqlsub = "Update Km_RECORD_SUB SET Statement = ?,dutycheck = ?,typemode= ?,netKPI= ?, importantnetfault=?, importantsocietydisaster=?, needcorrespond=?, handoverproceeding=? WHERE WorkSerial = "
                        + workserial + "  AND Dutyman = '" + userId + "'";
            } else {
                data = tawRmRecord.getStatement();
                sqlsub = "Update Km_RECORD_SUB SET Statement = ?,dutycheck = ?,typemode= ?,data= ?, importantnetfault=?, importantsocietydisaster=?, needcorrespond=?, handoverproceeding=? WHERE WorkSerial = "
                        + workserial + "  AND Dutyman = '" + userId + "'";
            }

            connsub = ds.getConnection();
            pstmtsub = connsub.prepareStatement(sqlsub);
            pstmtsub.setString(1, tawRmRecord.getStatement());
            pstmtsub.setString(2, tawRmRecord.getDutycheck());
            pstmtsub.setInt(3, type);
            pstmtsub.setString(4, tawRmRecord.getStatement());
            pstmtsub.setString(5, tawRmRecord.getImportantnetfault());
            pstmtsub.setString(6, tawRmRecord.getImportantsocietydisaster());
            pstmtsub.setString(7, tawRmRecord.getNeedcorrespond());
            pstmtsub.setString(8, tawRmRecord.getHandoverproceeding());

            pstmtsub.executeUpdate();
            pstmtsub.close();
            connsub.commit();

            String dutycheck = "";
            pstmt1 = connsub
                    .prepareStatement("select dutycheck from km_record_sub where workserial="
                            + workserial);
            rssub = pstmt1.executeQuery();
            while (rssub.next()) {
                dutycheck = ("".equalsIgnoreCase(dutycheck) ? dutycheck
                        : dutycheck + ";")
                        + StaticMethod.null2String(rssub.getString(1));
            }

            pstmt2 = connsub
                    .prepareStatement("update km_record set dutycheck = '"
                            + dutycheck + "' where id=" + workserial);
            pstmt2.executeUpdate();
            pstmt2.close();
            connsub.commit();

            pstmt1.close();
        } catch (SQLException sqle) {
            if (isDutymaster == true) {
                close(rs);
                close(pstmt);
                rollback(conn);
            }
            close(rssub);
            close(pstmtsub);
            close(pstmt1);
            close(pstmt2);
            rollback(connsub);
            sqle.printStackTrace();
            throw sqle;
        } finally {
            if (isDutymaster == true) {
                close(conn);
            }
            close(connsub);
            // null
            sql = null;
            sqlsub = null;
            rs = null;
            rssub = null;
        }
    }

    /**
     * @param workserial
     * @return
     * @throws SQLException
     * @see add by denden（贵州本地）合并值班日值
     */
    public String coalitionSub1(int workserial) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        com.boco.eoms.db.util.BocoConnection conns = null;
        PreparedStatement pstmts = null;
        ResultSet rss = null;

        boolean blExist = false;
        Vector SubRecord = new Vector();
        String[] arrName;
        String strStatement = "";
        String dutymanName = "";
        String strDutyRecord = "";
        String strDutyRecordAll = "";
        String sql = null;
        String sqls = null;

        try {
            for (int i = 1; i < 6; i++) {
                conn = ds.getConnection();
                sql = " select  a.dutyman,b.typemode from km_assign_sub a , km_record_sub b WHERE  a.workserial=b.workserial";
                sql += "  and  a.workserial = "
                        + workserial
                        + "  and starttime_defined>current - 25 units hour  and a.dutyman=b.dutyman";

                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                String dutyman = "";
                if (i == 1) {
                    strDutyRecordAll += "传输专业 :";
                }
                if (i == 2) {
                    strDutyRecordAll += "交换专业 : ";
                }
                if (i == 3) {
                    strDutyRecordAll += "数据专业 :";
                }
                if (i == 4) {
                    strDutyRecordAll += "系统专业 :";
                }
                if (i == 5) {
                    strDutyRecordAll += "其它 :";
                }

                while (rs.next()) { // 判断主记录是否存在
                    dutyman = StaticMethod.null2String(rs.getString(1));

                    if (dutyman.equals("")) {

                    } else { // 如果值班祝记录存在
                        conns = ds.getConnection();
                        if (i == 1) {
                            sqls = "Select id, DutyMan, trans from km_record_sub where WorkSerial = "
                                    + workserial
                                    + " And Dutyman = '"
                                    + dutyman
                                    + "'";
                        }
                        if (i == 2) {
                            sqls = "Select id, DutyMan, switch from km_record_sub where WorkSerial = "
                                    + workserial
                                    + " And Dutyman = '"
                                    + dutyman
                                    + "'";
                        }
                        if (i == 3) {
                            sqls = "Select id, DutyMan, data from km_record_sub where WorkSerial = "
                                    + workserial
                                    + " And Dutyman = '"
                                    + dutyman
                                    + "'";
                        }
                        if (i == 4) {
                            sqls = "Select id, DutyMan, system from km_record_sub where WorkSerial = "
                                    + workserial
                                    + " And Dutyman = '"
                                    + dutyman
                                    + "'";
                        }
                        if (i == 5) {
                            sqls = "Select id, DutyMan, other from km_record_sub where WorkSerial = "
                                    + workserial
                                    + " And Dutyman = '"
                                    + dutyman
                                    + "'";
                        }

                        pstmts = conns.prepareStatement(sqls);
                        rss = pstmts.executeQuery();
                        if (rss.next()) {
                            if (rss.getString(3) == null) {
                                strStatement = "日志为空";
                            } else {
                                strStatement = StaticMethod.null2String(
                                        rss.getString(3)).trim();
                            }
                        } else {
                            strStatement = " 没有日志记录";
                        }

                        dutymanName = getUserName(dutyman);
                        strDutyRecordAll += " " + dutymanName + "："
                                + strStatement;

                    }

                    close(rss);
                    close(pstmts);
                }
            }

            close(rs);
            close(pstmt);

        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            rollback(conn);
            close(rss);
            close(pstmts);
            rollback(conns);
            e.printStackTrace();
        } finally {
            close(conn);
            close(conns);
            SubRecord = null;
            arrName = null;
            strStatement = null;
            dutymanName = null;
            strDutyRecord = null;
            // strDutyRecordAll= null;
            sql = null;
            sqls = null;
            rs = null;

        }
        return strDutyRecordAll;
    }

    public String coalitionSub2(int workserial) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        com.boco.eoms.db.util.BocoConnection conns = null;
        PreparedStatement pstmts = null;
        ResultSet rss = null;

        boolean blExist = false;
        Vector SubRecord = new Vector();
        String[] arrName;
        String strStatement = "";
        String dutymanName = "";
        String strDutyRecord = "";
        String strDutyRecordAll = "";
        String sql = null;
        String sqls = null;
        StringBuffer buffer = new StringBuffer();
        String dutyrecord = "";

        try {

            conn = ds.getConnection();
            sql = " select  a.dutyman,b.typemode from km_assign_sub a , km_record_sub b WHERE  a.workserial=b.workserial";
            sql += "  and  a.workserial = "
                    + workserial
                    + "    and a.dutyman=b.dutyman";
            //and starttime_defined>current - 25 units hour bumingbai
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            String dutyman = "";

            while (rs.next()) { // 判断主记录是否存在
                dutyman = StaticMethod.null2String(rs.getString(1));

                if (dutyman.equals("")) {

                } else { // 如果值班祝记录存在
                    conns = ds.getConnection();

                    sqls = "Select id, DutyMan,trans,switch,data,system,other,dutySummary,importRecord, importFault, netCut, netKPI,importantnetfault, importantsocietydisaster, needcorrespond, handoverproceeding from km_record_sub where WorkSerial = "
                            + workserial + " And Dutyman = '" + dutyman + "'";

                    pstmts = conns.prepareStatement(sqls);
                    rss = pstmts.executeQuery();
                    if (rss.next()) {
                        if (rss.getString(3) == null) {
                            strStatement = "传输 ： 日志为空 ";
                        } else {
                            strStatement = "传输 ： "
                                    + StaticMethod
                                    .null2String(rss.getString(3))
                                    .trim() + "。";
                        }
                        dutyrecord += strStatement;
                        if (rss.getString(4) == null) {
                            strStatement = "交换 ： 日志为空 ";
                        } else {
                            strStatement = "交换 ： "
                                    + StaticMethod
                                    .null2String(rss.getString(4))
                                    .trim() + "。";
                        }
                        dutyrecord += strStatement;

                        if (rss.getString(5) == null) {
                            strStatement = "数据 ： 日志为空 ";
                        } else {
                            strStatement = "数据 ： "
                                    + StaticMethod
                                    .null2String(rss.getString(5))
                                    .trim() + "。";
                        }
                        dutyrecord += strStatement;
                        if (rss.getString(6) == null) {
                            strStatement = "系统 ： 日志为空 ";
                        } else {
                            strStatement = "系统 ： "
                                    + StaticMethod
                                    .null2String(rss.getString(6))
                                    .trim() + "。";
                        }
                        dutyrecord += strStatement;
                        if (rss.getString(7) == null) {
                            strStatement = "其他 ： 日志为空 ";
                        } else {
                            strStatement = "其他 ： "
                                    + StaticMethod
                                    .null2String(rss.getString(7))
                                    .trim() + "。";
                        }
                        dutyrecord += strStatement;
                        if (rss.getString(8) == null) {
                            strStatement = "值班综述 ： 日志为空 ";
                        } else {
                            strStatement = "值班综述 ： "
                                    + StaticMethod
                                    .null2String(rss.getString(8))
                                    .trim() + "。";
                        }
                        dutyrecord += strStatement;
                        if (rss.getString(9) == null) {
                            strStatement = "重要纪事 ： 日志为空 ";
                        } else {
                            strStatement = "重要纪事 ： "
                                    + StaticMethod
                                    .null2String(rss.getString(9))
                                    .trim() + "。";
                        }
                        dutyrecord += strStatement;
                        if (rss.getString(10) == null) {
                            strStatement = "重大故障 ： 日志为空 ";
                        } else {
                            strStatement = "重大故障 ： "
                                    + StaticMethod.null2String(
                                    rss.getString(10)).trim() + "。";
                        }
                        dutyrecord += strStatement;
                        if (rss.getString(11) == null) {
                            strStatement = "网络割接 ： 日志为空 ";
                        } else {
                            strStatement = "网络割接 ： "
                                    + StaticMethod.null2String(
                                    rss.getString(11)).trim() + "。";
                        }
                        dutyrecord += strStatement;
                        if (rss.getString(12) == null) {
                            strStatement = "网络KPI ： 日志为空 ";
                        } else {
                            strStatement = "网络KPI ： "
                                    + StaticMethod.null2String(
                                    rss.getString(12)).trim() + "。";
                        }
                        dutyrecord += strStatement;
                        //start
                        if (rss.getString(13) == null) {
                            strStatement = "网络重要故障 ： 日志为空 ";
                        } else {
                            strStatement = "网络重要故障 ： "
                                    + StaticMethod.null2String(
                                    rss.getString(13)).trim() + "。";
                        }
                        dutyrecord += strStatement;
                        if (rss.getString(14) == null) {
                            strStatement = "重要社会或灾害事件 ： 日志为空 ";
                        } else {
                            strStatement = "重要社会或灾害事件 ： "
                                    + StaticMethod.null2String(
                                    rss.getString(14)).trim() + "。";
                        }
                        dutyrecord += strStatement;
                        if (rss.getString(15) == null) {
                            strStatement = "需要省公司协调事项 ： 日志为空 ";
                        } else {
                            strStatement = "需要省公司协调事项 ： "
                                    + StaticMethod.null2String(
                                    rss.getString(15)).trim() + "。";
                        }
                        dutyrecord += strStatement;
                        if (rss.getString(16) == null) {
                            strStatement = "交接事项 ： 日志为空 ";
                        } else {
                            strStatement = "交接事项 ： "
                                    + StaticMethod.null2String(
                                    rss.getString(16)).trim() + "。";
                        }
                        dutyrecord += strStatement;
                        //end
                    } else {
                        dutyrecord = " 没有日志记录";
                    }

                    dutymanName = getUserName(dutyman);
                    strDutyRecordAll += " " + dutymanName + "：" + dutyrecord;

                }
                dutyrecord = "";
                close(rss);
                close(pstmts);
            }

            close(rs);
            close(pstmt);

        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            rollback(conn);
            close(rss);
            close(pstmts);
            rollback(conns);
            e.printStackTrace();
        } finally {
            close(conn);
            close(conns);
            SubRecord = null;
            arrName = null;
            strStatement = null;
            dutymanName = null;
            strDutyRecord = null;
            // strDutyRecordAll= null;
            sql = null;
            sqls = null;
            rs = null;

        }
        return strDutyRecordAll;
    }

    /**
     * @param condition
     * @see 查询所有的日志信息
     */
    public List searchAll(String condition) throws SQLException {
        ArrayList search = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "SELECT * FROM km_record " + condition;
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Kmrecord tawRmRecord = new Kmrecord();
                populate(tawRmRecord, rs);
                search.add(tawRmRecord);
                tawRmRecord = null;
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
            sql = null;
            rs = null;
        }
        return search;
    }

    // add by gongyufeng
    // 附加表

    public void insetAddons(TawRmAddonsTable tawRmAddonsTable)
            throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;

        String sql = null;

        try {

            conn = ds.getConnection();
            sql = "INSERT INTO km_addons "
                    + "(name, remark, model, url, roomId, creatUser, creatTime, deleted)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, tawRmAddonsTable.getName());
            pstmt.setString(2, tawRmAddonsTable.getRemark());
            pstmt.setString(3, tawRmAddonsTable.getModel());
            pstmt.setString(4, tawRmAddonsTable.getUrl());
            pstmt.setString(5, tawRmAddonsTable.getRoomId());
            pstmt.setString(6, tawRmAddonsTable.getCreatUser());
            pstmt.setString(7, tawRmAddonsTable.getCreatTime());
            pstmt.setString(8, "0");
            pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException sqle) {

            close(pstmt);
            rollback(conn);

            sqle.printStackTrace();
            throw sqle;
        } finally {
            close(conn);

            // null
            sql = null;

        }
    }

    // 根据roomid 得到这么机房下所有的附加表
    public List getAddons(String roomid) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        ITawSystemCptroomManager cptroommanager = (ITawSystemCptroomManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemCptroomManager");
        PreparedStatement pstmt = null;
        List list = new ArrayList();
        String sql = null;
        ResultSet rs = null;
        TawRmAddonsTable tawRmaddonsTable = null;
        try {
            conn = ds.getConnection();
            sql = "select id ,name, remark, model, url, roomId, creatUser, creatTime, deleted from km_addons where roomId = '"
                    + roomid + "' and deleted = 0 order by creatTime desc ";

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tawRmaddonsTable = new TawRmAddonsTable();
                tawRmaddonsTable.setCreatTime(StaticMethod.null2String(rs
                        .getString("creatTime")));
                tawRmaddonsTable.setId(rs.getString("id"));
                tawRmaddonsTable.setCreatUser(rs.getString("creatUser"));
                tawRmaddonsTable.setName(rs.getString("name"));
                tawRmaddonsTable.setRemark(rs.getString("remark"));
                tawRmaddonsTable.setUrl(rs.getString("url"));
                tawRmaddonsTable.setRoomId(rs.getString("roomId"));
                tawRmaddonsTable.setId(rs.getString("id"));
                tawRmaddonsTable.setRoomName(cptroommanager
                        .getTawSystemCptroomName(new Integer(rs
                                .getInt("roomId"))));
                list.add(tawRmaddonsTable);
            }
        } catch (Exception e) {
            close(pstmt);
            rollback(conn);
            e.printStackTrace();

        } finally {
            close(conn);
            sql = null;
        }
        return list;
    }

    // 删除
    public void deleteAddons(String id) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;

        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "update km_addons set deleted = 1 where id = '" + id
                    + "'";
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            close(pstmt);
            rollback(conn);
            e.printStackTrace();

        } finally {
            close(conn);
            sql = null;
        }

    }

    public String getAddonsUrl(String id) throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        String url = null;
        String sql = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            sql = "select url from km_addons where id=" + id
                    + " and deleted = 0";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                url = rs.getString("url");
            }

        } catch (Exception e) {
            close(pstmt);
            rollback(conn);

            e.printStackTrace();

        } finally {
            rs.close();
            close(conn);
            sql = null;
        }
        return url;
    }

    public List searchFaultreportsheetlist(int offset, int limit,
                                           String startDuty, String endDuty, String userId)
            throws SQLException {
        ArrayList search = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "select m.id,m.title from  wf_faultreportsheetlink t,"
                    + " wf_faultreportsheetmain m"
                    + " where t.sheet_key=m.id and t.operate_time>'"
                    + startDuty + "'" + " and t.operate_time<'" + endDuty
                    + "'+ " + "and t.operate_user='" + userId
                    + "' group by m.id,m.title";
            System.out.println("sql====" + sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (offset > 0) {
                rs.next();
                offset--;
            }
            // rs.absolute(offset);
            int recCount = 0;
            while ((recCount++ < limit) && rs.next()) {
                Kmrecord tawRmRecord = new Kmrecord();
                tawRmRecord.setSheetUrl("<a href='"
                        + UtilMgrLocator.getEOMSAttributes().getEomsUrl()
                        + "/wfworksheet/Faultreportsheet/detail.do?sheetKey="
                        + rs.getString(1) + "'" + ">" + rs.getString(2) + "</a>");
                search.add(tawRmRecord);
                tawRmRecord = null;
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
            sql = null;
            rs = null;
        }
        return search;
    }

    public List searchFaultsheetlist(int offset, int limit, String startDuty,
                                     String endDuty, String userId) throws SQLException {
        ArrayList search = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "select m.id,m.title from  wf_faultsheetlink t,"
                    + " wf_faultsheetmain m"
                    + " where t.sheet_key=m.id and t.operate_time>'"
                    + startDuty + "'" + " and t.operate_time<'" + endDuty + "'"
                    + " and t.operate_user='" + userId
                    + "' group by m.id,m.title";
            System.out.println("sql====" + sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (offset > 0) {
                rs.next();
                offset--;
            }
            // rs.absolute(offset);
            int recCount = 0;
            while ((recCount++ < limit) && rs.next()) {
                Kmrecord tawRmRecord = new Kmrecord();
                tawRmRecord.setSheetUrl("<a href='"
                        + UtilMgrLocator.getEOMSAttributes().getEomsUrl()
                        + "/wfworksheet/Faultreportsheet/detail.do?sheetKey="
                        + rs.getString(1) + "'" + ">" + rs.getString(2) + "</a>");
                search.add(tawRmRecord);
                tawRmRecord = null;
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
            sql = null;
            rs = null;
        }
        return search;
    }

    public List searchCutoversheetlist(int offset, int limit, String startDuty,
                                       String endDuty, String userId) throws SQLException {
        ArrayList search = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "select m.id,m.title from  wf_cutoversheetlink t,"
                    + " wf_cutoversheetmain m"
                    + " where t.sheet_key=m.id and t.operate_time>'"
                    + startDuty + " and t.operate_time<'" + endDuty + "'+"
                    + " and t.operate_user='" + userId
                    + "' group by m.id,m.title";
            System.out.println("sql====" + sql);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (offset > 0) {
                rs.next();
                offset--;
            }
            // rs.absolute(offset);
            int recCount = 0;
            while ((recCount++ < limit) && rs.next()) {
                Kmrecord tawRmRecord = new Kmrecord();
                tawRmRecord.setSheetUrl("<a href='"
                        + UtilMgrLocator.getEOMSAttributes().getEomsUrl()
                        + "/wfworksheet/Faultreportsheet/detail.do?sheetKey="
                        + rs.getString(1) + "'" + ">" + rs.getString(2) + "</a>");
                search.add(tawRmRecord);
                tawRmRecord = null;
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
            sql = null;
            rs = null;
        }
        return search;
    }

    public int retrieveTmp(int workserial, int roomId) throws SQLException {
        Kmrecord tawRmRecord = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        int count = 0;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "SELECT count(*) FROM Km_RECORD_Additional WHERE workserial=? and ROOM_ID=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, workserial);
            pstmt.setInt(2, roomId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
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
            sql = null;
            rs = null;
        }
        return count;
    }

    public void deleteAdditionallist(int roomId, int workserial)
            throws SQLException {
        Kmrecord tawRmRecord = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;

        String sql = null;

        try {

            conn = ds.getConnection();
            sql = "delete Km_RECORD_Additional where ROOM_ID=? and WORKSERIAL=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomId);
            pstmt.setInt(2, workserial);
            pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException sqle) {

            close(pstmt);
            rollback(conn);

            sqle.printStackTrace();
            throw sqle;
        } finally {
            close(conn);

            // null
            sql = null;
        }
    }

    public boolean if_tempManager(int roomId, String userId)
            throws SQLException {
        boolean result = false;
        String sql = null;

        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            sql = "select * from  km_user  where duty_flag=1 and user_id='"
                    + userId + "'";
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = true;
            }
            close(rs);
            close(pstmt);
        } catch (SQLException sqle) {
            close(rs);
            close(pstmt);
            // rollback(conn); liquan modify
            sqle.printStackTrace();
            throw sqle;
        } finally {
            close(conn);
            sql = null;
        }
        return result;
    }

    public List retrieveRECORDExcelFile(String time) throws SQLException {
        Kmrecord tawRmRecord = null;
        com.boco.eoms.db.util.BocoConnection conn = null;

        List list = new ArrayList();
        PreparedStatement pstmt = null;
        String url = "";
        ITawSystemCptroomManager cptroommanager = (ITawSystemCptroomManager) ApplicationContextHolder
                .getInstance().getBean("ItawSystemCptroomManager");
        int count = 0;
        ResultSet rs = null;
        String sql = null;
        try {
            conn = ds.getConnection();
            sql = "select t.room_id,t.starttime,t.endtime,t.weather ,t.temperature,t.wet,t.dutydate,t.dutyrecord,t.dutyman from km_record t where t.dutydate=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, time);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                tawRmRecord = new Kmrecord();
                // tawRmRecord.set
                tawRmRecord.setRoomId(rs.getInt(1));
                tawRmRecord.setStarttime(rs.getString(2));
                tawRmRecord.setEndtime(rs.getString(3));
                tawRmRecord.setWeather(rs.getString(4));
                tawRmRecord.setTemperature(rs.getInt(5));
                tawRmRecord.setWet(rs.getInt(6));
                tawRmRecord.setDutydate(rs.getString(7));

                tawRmRecord.setDutyrecord(rs.getString(8));
                tawRmRecord.setDutyman(rs.getString(9));
                tawRmRecord.setRoomname((cptroommanager
                        .getTawSystemCptroomName(new Integer(rs.getInt(1)))));
                list.add(tawRmRecord);
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
            sql = null;
            rs = null;
        }
        return list;
    }

    public String getExcelFile(List list, String time) throws SQLException {
        String filePath = "";
        if (list.size() > 0) {


            try {
                IFMExportFileManager efm = (IFMExportFileManager) this
                        .getBean("FMExportExcelFileManagerImpl");
                Map map = new HashMap();
                map.put(new Integer(0), list);

                efm.export(map, StaticMethod.CLASSPATH_FLAG
                                + "com/boco/eoms/duty/sample/FMExportSample.xml",
                        "d:/" + time + "值班日报" + ".xls");

            } catch (FMException e) {
                e.printStackTrace();

            }
            // TODO add by zhouchunxing
            filePath = UtilMgrLocator.getEOMSAttributes().getEomsUrl();

            filePath += "duty/model/new/" + time + "值班日报" + ".xls";
        }
        return filePath;

    }

    public void getAdditionallist(int roomId, int workserial, String user_id,
                                  String STARTTIME, String ENDTIME, String url, List list,
                                  String sheetName) throws SQLException {
        Kmrecord tawRmRecord = null;
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (url.equals("")) {
                    tawRmRecord = new Kmrecord();
                    tawRmRecord = (Kmrecord) list.get(i);
                    insetAdditional(roomId, workserial, user_id, STARTTIME,
                            ENDTIME, tawRmRecord.getSheetUrl(), sheetName);
                } else {
                    TawwpMonthPlanVO tawwpMonthPlanVO = new TawwpMonthPlanVO();
                    tawwpMonthPlanVO = (TawwpMonthPlanVO) list.get(i);
                    insetAdditional(roomId, workserial, user_id, STARTTIME,
                            ENDTIME, url + tawwpMonthPlanVO.getId() + "'" + "/>" + tawwpMonthPlanVO.getName() + "</a>", sheetName);
                }
            }
        }
    }

    public void insetAdditional(int roomId, int workserial, String user_id,
                                String STARTTIME, String ENDTIME, String url, String sheetName)
            throws SQLException {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;

        String sql = null;

        try {

            conn = ds.getConnection();
            sql = "INSERT INTO Km_RECORD_Additional "
                    + "(ROOM_ID, WORKSERIAL, DUTYMAN, STARTTIME, ENDTIME,url,sheet_name)"
                    + "VALUES (?, ?, ?, ?, ?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomId);
            pstmt.setInt(2, workserial);
            pstmt.setString(3, user_id);
            pstmt.setString(4, STARTTIME);

            pstmt.setString(5, ENDTIME);
            pstmt.setString(6, url);
            pstmt.setString(7, sheetName);
            pstmt.executeUpdate();

            conn.commit();

        } catch (SQLException sqle) {

            close(pstmt);
            rollback(conn);

            sqle.printStackTrace();
            throw sqle;
        } finally {
            close(conn);

            // null
            sql = null;

        }
    }

    public List retrieveAdditionalList(int workserial, int roomId,
                                       String SHEET_NAME) throws SQLException {
        Kmrecord tawRmRecord = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        List list = new ArrayList();
        PreparedStatement pstmt = null;
        int count = 0;
        ResultSet rs = null;
        String sql = null;

        try {
            conn = ds.getConnection();
            sql = "SELECT url FROM Km_RECORD_Additional WHERE workserial=? and ROOM_ID=? and SHEET_NAME=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, workserial);
            pstmt.setInt(2, roomId);
            pstmt.setString(3, SHEET_NAME);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                tawRmRecord = new Kmrecord();
                tawRmRecord.setSheetUrl(rs.getString(1));
                list.add(tawRmRecord);
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
            sql = null;
            rs = null;
        }
        return list;
    }

    protected Object getBean(String beanId) {
        return ApplicationContextHolder.getInstance().getBean(beanId);
    }

    public List getTawRmExchangeTime(String roomid, String startTime, String endTime, String flag) {
        ArrayList list = new ArrayList();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String colname = "";
        int count = 0;
        try {
            conn = ds.getConnection();
            String sql = "select workserial,starttime_defined,endtime_defined,receiver from km_assignwork t,taw_Rm_exchange_per ps WHERE t.id=ps.workserial and t.room_id=? and t.starttime_defined<=? and t.endtime_defined>=? and ps.flag=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, roomid);
            pstmt.setString(2, startTime);
            pstmt.setString(3, endTime);
            pstmt.setString(4, flag);
            rs = pstmt.executeQuery();

            ResultSetMetaData md = rs.getMetaData();


            //检索此ResultSet对象的列的编号、类型和属性返回此ResultSet对象的列的描述
            while (rs.next())//遍历所有行
            {
                Map data = new HashMap();
                count++;
                for (int i = 0; i < md.getColumnCount(); i++)//遍历此行的所有列
                {
                    colname = md.getColumnName(i + 1);//取出列名
                    Object val = rs.getObject(colname);
                    val = (val == null) ? "" : val;//格式化空值
                    data.put(colname, "" + val);

                }
                list.add(data);

                close(rs);
                close(pstmt);
            }
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

}

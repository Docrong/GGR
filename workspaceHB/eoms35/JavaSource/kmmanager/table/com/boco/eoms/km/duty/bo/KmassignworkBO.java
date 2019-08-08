/**
 * @author 赵川
 * @version 1.0
 * @see <p>功能描述：封装排班管理的业务逻辑类。</p>
 * <p>使用举例：首先实例化该类，然后通过实例化该类的对象，调用相应方法。</p>
 */

package com.boco.eoms.km.duty.bo;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.boco.eoms.common.bo.BO;
import com.boco.eoms.common.dao.SaveSessionBeanDAO;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.cptroom.bo.TawSystemCptroomBo;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.bo.impl.TawSystemUserRoleBo;
import com.boco.eoms.duty.dao.TawRmAssignExpertDAO;
import com.boco.eoms.duty.dao.TawRmAssignSubDAO;
import com.boco.eoms.duty.dao.TawRmAssignworkDAO;
import com.boco.eoms.duty.dao.TawRmRecordDAO;
import com.boco.eoms.duty.dao.TawRmSysteminfoDAO;
import com.boco.eoms.duty.model.TawApparatusroom;
import com.boco.eoms.duty.model.TawRmRecord;
import com.boco.eoms.duty.model.TawRmSysteminfo;
import com.boco.eoms.duty.util.DutyMgrLocator;
import com.boco.eoms.km.configInfo.dao.KmsysteminfoDAO;
import com.boco.eoms.km.configInfo.model.Kmsysteminfo;
import com.boco.eoms.km.cptroom.bo.KmsystemCptroomBo;
import com.boco.eoms.km.cptroom.model.KmsystemCptroom;
import com.boco.eoms.km.duty.dao.KmassignSubDAO;
import com.boco.eoms.km.duty.dao.KmassignworkDAO;
import com.boco.eoms.km.duty.dao.KmrecordDAO;
import com.boco.eoms.km.duty.model.Kmassignwork;
import com.boco.eoms.message.service.impl.MsgServiceImpl;
import com.boco.eoms.message.util.MsgMgrLocator;

public class KmassignworkBO extends BO {
    public KmassignworkBO(com.boco.eoms.db.util.ConnectionPool ds) {
        super(ds);
    }

    public KmassignworkBO() {
        super();
    }

    String serverId = DutyMgrLocator.getAttributes().getServerId();

    // 取当前时间
    String creattime = StaticMethod.getLocalString();

    /**
     * @param old_vector
     * @param date_add
     * @return
     * @see 调用getDateAddString方法，将源列表中每个日期加上date_add天，加入目标列表中
     */
    public Vector getDateAddVector(Vector old_vector, int date_add) {
        Vector new_vector = new Vector();
        for (int i = 0; i < old_vector.size(); i++) {
            new_vector.add(getDateAddString(String.valueOf(
                    old_vector.elementAt(i)).trim(), date_add));
        }
        return new_vector;
    }

    // 日期加1 如果為周六則加2。周日加1
    public Vector getDate(Vector old_vector, int date_add, String workSelect) {
        Vector new_vector = new Vector();
        String date = "";
        date = (String) old_vector.elementAt(old_vector.size() - 1);
        for (int i = 0; i < date_add; i++) {

            date = getDateAddString(date, 1);
            if (week(date) == 0 && workSelect.equals("0")) {
                date = getDateAddString(date, 1);
            }
            if (week(date) == 6 && workSelect.equals("0")) {
                date = getDateAddString(date, 2);
            }
            new_vector.add(date);
        }
        return new_vector;
    }

    public Vector getTime(String date, Vector vector_team, int team_num,
                          String workSelect) {
        Vector new_vector = new Vector();
        try {
            for (int i = 0; i < team_num; i++) {
                Vector time_vector = StaticMethod.getVector(
                        (String) vector_team.elementAt(i), " ");
                new_vector.add(date + " " + time_vector.elementAt(1));

            }
            if (date != null) {
                Vector head_vector = StaticMethod.getVector(date, "");

                Vector last_vector = StaticMethod.getVector(
                        (String) vector_team.elementAt(vector_team.size() - 1),
                        " ");
                String time = (String) getDate(head_vector, 1, workSelect)
                        .elementAt(0);
                new_vector
                        .add(time + " " + last_vector.elementAt(1).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new_vector;
    }

    public Vector getDateTime(Vector old_vector, int date_unm, int cycle_num) {
        Vector new_vector = new Vector();
        String date = "";
        // for (int i = 1; i < cycle_num; i++) {

        for (int k = 0; k < old_vector.size(); k++) {
            date = (String) old_vector.elementAt(k);

            date = getDateAddString(date, date_unm);
            if (week(date) == 0) {
                date = getDateAddString(date, 1);
            }
            if (week(date) == 6) {
                date = getDateAddString(date, 2);
            }
            new_vector.add(date);
            // }
        }
        // 2
        // new_vector .add(getDateTime(new_vector, date_unm, cycle_num));
        return new_vector;
    }

    // 判斷是否為周末
    public int week(String old_date) {
        int i = 0;
        Vector time_vector = StaticMethod.getVector(old_date, " ");
        Vector date_vector = StaticMethod.getVector(String.valueOf(
                time_vector.elementAt(0)).trim(), "-");
        int year = Integer.parseInt(String.valueOf(date_vector.elementAt(0))
                .trim());
        int month = Integer.parseInt(String.valueOf(date_vector.elementAt(1))
                .trim());
        int day = Integer.parseInt(String.valueOf(date_vector.elementAt(2))
                .trim());
        java.util.Date date = new java.util.Date(year - 1900, month - 1,
                day - 0);
        i = date.getDay();
        return i;
    }

    /**
     * @param old_date
     * @param date_add
     * @return
     * @see 将源时间加上date_add天，变成目标时间串；例如输入2003－6－1，3，返回2003－6－4
     */
    public String getDateAddString(String old_date, int date_add) {
        String new_date = "";
        GregorianCalendar cal = new GregorianCalendar();
        Vector time_vector = StaticMethod.getVector(old_date, " ");
        Vector date_vector = StaticMethod.getVector(String.valueOf(
                time_vector.elementAt(0)).trim(), "-");
        int year = Integer.parseInt(String.valueOf(date_vector.elementAt(0))
                .trim());
        int month = Integer.parseInt(String.valueOf(date_vector.elementAt(1))
                .trim());
        int day = Integer.parseInt(String.valueOf(date_vector.elementAt(2))
                .trim());
        java.util.Date date = new java.util.Date(year - 1900, month - 1,
                day - 0);
        cal.setTime(date);
        cal.add(5, date_add);
        year = cal.get(cal.YEAR);
        month = cal.get(cal.MONTH) + 1;
        day = cal.get(cal.DATE);
        new_date = String.valueOf(year).trim() + "-"
                + String.valueOf(month).trim() + "-"
                + String.valueOf(day).trim();
        if (time_vector.size() > 1) {
            new_date = new_date + " " + time_vector.elementAt(1);
        }
        // null
        cal = null;
        time_vector = null;
        date_vector = null;
        date = null;
        return new_date;
    }

    /**
     * @param roomId
     * @param time_from
     * @param time_to
     * @return
     * @throws SQLException
     * @see 输入机房编号，开始和结束时间，返回查询排班结果：一个包含多个vector的vector
     */
    public Vector getQueryResultVector(int roomId, String time_from,
                                       String time_to) throws SQLException {
        Vector getVector = new Vector();
        Vector getDutydate = new Vector();
        Vector getStarttime = new Vector();
        Vector getEndtime = new Vector();
        Vector getDutymaster = new Vector();
        Vector getDutyman = new Vector();
        String strDutydate = "";
        Vector vectDutydate = new Vector();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            HashMap map = this.getSubDutyMan(roomId, time_from, time_to);
            SaveSessionBeanDAO saveSessionBeanDAO = new SaveSessionBeanDAO(ds);
            saveSessionBeanDAO.alterDateFormat();
            conn = ds.getConnection();
            String sql = "";
            sql = "select a.id,a.dutydate,a.starttime_defined,a.endtime_defined,b.username from km_assignwork a,taw_system_user b WHERE a.room_id = ? and a.dutydate >= ? and a.dutydate <= ? and b.userid = a.dutymaster order by a.starttime_defined";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomId);
            pstmt.setString(2, time_from);
            pstmt.setString(3, time_to);
            rs = pstmt.executeQuery();
            // null
            sql = "";
            while (rs.next()) {
                strDutydate = rs.getString(2).trim();
                vectDutydate = StaticMethod.getVector(strDutydate, " ");
                strDutydate = String.valueOf(vectDutydate.elementAt(0)).trim();
                getDutydate.add(strDutydate);
                getStarttime.add(StaticMethod.StringReplace(rs.getString(3)
                        .trim(), ".0", ""));
                getEndtime.add(StaticMethod.StringReplace(rs.getString(4)
                        .trim(), ".0", ""));
                // edit by wangheqi 2.7to3.5
                // TawSystemUserRoleBo userbo =
                // TawSystemUserRoleBo.getInstance();
                // TawSystemUser tawRmUser = userbo.getUserByuserid(StaticMethod
                // .dbNull2String(rs.getString(5)).trim());
                // edit end
                // TawRmUserDAO tawRmUserDAO = new TawRmUserDAO(ds);
                getDutymaster.add(StaticMethod.null2String(rs.getString(5))
                        .trim());
                getDutyman.add(map.get(rs.getString(1)));
            }
            map = null;
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            rs.close();
            pstmt.close();
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.close();
        }
        getVector.add(getDutydate);
        getVector.add(getStarttime);
        getVector.add(getEndtime);
        getVector.add(getDutymaster);
        getVector.add(getDutyman);

        // null
        // tawRmAssignSubDAO = null;
        // tawRmUserDAO=null;
        rs = null;
        getDutydate = null;
        getStarttime = null;
        getEndtime = null;
        getDutymaster = null;
        getDutyman = null;
        strDutydate = null;
        vectDutydate = null;
        return getVector;
    }

    /**
     * @param roomId
     * @param time_from
     * @param time_to
     * @return
     * @throws SQLException
     * @see 输入机房编号，开始和结束时间，返回查询排班结果：一个包含多个vector的vector
     */
    public Vector getQueryResultVector(int roomId, String time)
            throws SQLException {
        Vector getVector = new Vector();
        Vector getDutydate = new Vector();
        Vector getStarttime = new Vector();
        Vector getEndtime = new Vector();
        Vector getDutymaster = new Vector();
        Vector getDutyman = new Vector();
        String strDutydate = "";
        Vector vectDutydate = new Vector();
        // TawRmAssignSubDAO tawRmAssignSubDAO = new TawRmAssignSubDAO(ds);
        ITawSystemUserManager mgr = (ITawSystemUserManager) ApplicationContextHolder
                .getInstance().getBean("itawSystemUserManager");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // List list = this.getSubDutyMan(roomId, time);
            SaveSessionBeanDAO saveSessionBeanDAO = new SaveSessionBeanDAO(ds);
            saveSessionBeanDAO.alterDateFormat();
            conn = ds.getConnection();
            String sql = "";
            sql = "select a.id,a.dutydate,a.starttime_defined,a.endtime_defined,b.username from km_assignwork a,taw_system_user b WHERE a.room_id = ? and a.dutydate = ?   and b.userid = a.dutymaster order by a.starttime_defined";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomId);
            pstmt.setString(2, time);

            rs = pstmt.executeQuery();
            TawSystemUser tawSystemUser = null;
            // null
            sql = "";
            while (rs.next()) {
                strDutydate = rs.getString(2).trim();
                vectDutydate = StaticMethod.getVector(strDutydate, " ");
                strDutydate = String.valueOf(vectDutydate.elementAt(0)).trim();
                getDutydate.add(strDutydate);
                getStarttime.add(StaticMethod.StringReplace(rs.getString(3)
                        .trim(), ".0", ""));
                getEndtime.add(StaticMethod.StringReplace(rs.getString(4)
                        .trim(), ".0", ""));
                /*
                 * getDutymaster.add(StaticMethod.null2String(rs.getString(5))
                 * .trim());
                 */
                getDutyman.add(this.getSubDutyMan(StaticMethod.null2String(rs
                        .getString(1))));

            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            rs.close();
            pstmt.close();
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.close();
        }
        getVector.add(getDutydate);
        getVector.add(getStarttime);
        getVector.add(getEndtime);
        getVector.add(getDutymaster);
        getVector.add(getDutyman);

        // null
        // tawRmAssignSubDAO = null;
        // tawRmUserDAO=null;
        rs = null;
        getDutydate = null;
        getStarttime = null;
        getEndtime = null;
        getDutymaster = null;
        getDutyman = null;
        strDutydate = null;
        vectDutydate = null;
        return getVector;
    }

    /**
     * @param roomId
     * @param date_num
     * @param team_num
     * @param user_num
     * @param vector_date
     * @param vector_team
     * @param vector_user
     * @throws SQLException
     * @see 刷新排班，先删除选择时间段的排班记录，插入新的排班记录，并加入短信提示
     * @see 用于自动、自动覆盖、手动排班
     */
    public void updateAssignwork(int roomId, int date_num, int team_num,
                                 int user_num, Vector vector_date, Vector vector_team,
                                 Vector vector_user) throws SQLException {
        KmassignworkDAO tawRmAssignworkDAO = new KmassignworkDAO(ds);
        KmassignSubDAO tawRmAssignSubDAO = new KmassignSubDAO(ds);
        int workserial = 0;
        int date_num_i = 0;
        int team_num_i = 0;
        int user_num_i = 0;
        int workid = 0;
        String dutydate = "";
        String dutymaster = "";
        String dutyman = "";
        String starttime_defined = "";
        String endtime_defined = "";

        // use for send SMS
        // edit by wangheqi
        KmsystemCptroomBo cptroomBO = KmsystemCptroomBo.getInstance();
        KmsystemCptroom tawApparatusroom = cptroomBO.getKmsystemCptroomById(
                new Integer(roomId), 0);
        // TawApparatusroomDAO tawApparatusroomDAO = new
        // TawApparatusroomDAO(ds);
        // TawApparatusroom tawApparatusroom =
        // tawApparatusroomDAO.retrieve(roomId);
        String str_Roomname = "";
        if (tawApparatusroom != null) {
            // str_Roomname =
            // StaticMethod.null2String(tawApparatusroom.getRoomName());
            str_Roomname = StaticMethod.null2String(tawApparatusroom
                    .getRoomname());
        }
        KmsysteminfoDAO tawRmSysteminfoDAO = new KmsysteminfoDAO(ds);
        Kmsysteminfo tawRmSysteminfo = tawRmSysteminfoDAO.retrieve(roomId + "");
        int int_Maxerrortime = 0;
        int int_stagger = 0;
        if (tawRmSysteminfo != null) {
            int_Maxerrortime = tawRmSysteminfo.getMaxerrortime();
            int_stagger = tawRmSysteminfo.getStaggertime();
        }
        for (date_num_i = 0; date_num_i < date_num; date_num_i++) {
            dutydate = StaticMethod.getAddZero(String.valueOf(vector_date
                    .elementAt(date_num_i)));
            tawRmAssignSubDAO.delete_room_date(roomId, dutydate);
            tawRmAssignworkDAO.delete_room_date(roomId, dutydate);
            // edit by wangheqi for sms

            // deleteSMS(roomId,dutydate);

            for (team_num_i = 0; team_num_i < team_num; team_num_i++) {
                workid = team_num_i;
                dutymaster = StaticMethod.dbNull2String(String
                        .valueOf(vector_user
                                .elementAt((date_num_i * team_num + team_num_i)
                                        * user_num)));
                starttime_defined = String.valueOf(vector_team
                        .elementAt(date_num_i * team_num + team_num_i));
                if (team_num_i != 0) {
                    starttime_defined = StaticMethod
                            .getLocalString(StaticMethod.getDateForMinute(
                                    starttime_defined, -int_stagger * 60));
                }
                endtime_defined = String.valueOf(vector_team
                        .elementAt(date_num_i * team_num + team_num_i + 1));
                tawRmAssignworkDAO.insert(roomId, dutydate, workid, dutymaster,
                        starttime_defined, endtime_defined);
                workserial = tawRmAssignworkDAO
                        .get_id(roomId, dutydate, workid);

                java.util.Date date = new java.util.Date();
                java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                String strDutyDate = dtFormat.format(date);

                for (user_num_i = 0; user_num_i < user_num; user_num_i++) {
                    dutyman = StaticMethod.dbNull2String(String
                            .valueOf(vector_user.elementAt((date_num_i
                                    * team_num + team_num_i)
                                    * user_num + user_num_i)));
                    tawRmAssignSubDAO.insert(workserial, dutyman);
                    // edit by wangheqi for sms
                    // insertSMS(roomId,dutydate,dutyman,str_Roomname,starttime_defined,int_Maxerrortime);
                }
                // edit by wangheqi for sms
                // 排班提醒
                // RemindAssignwork(roomId,"");
            }
        }
        // null
        tawRmAssignworkDAO = null;
        tawRmAssignSubDAO = null;
        dutydate = null;
        dutymaster = null;
        dutyman = null;
        starttime_defined = null;
        endtime_defined = null;
        cptroomBO = null;
        // tawApparatusroomDAO =null;
        tawApparatusroom = null;
        str_Roomname = null;
        tawRmSysteminfoDAO = null;
        tawRmSysteminfo = null;
    }

    /*
     * public void updateAssignworkAddExpert(int roomId, int date_num, int
     * team_num, int user_num, Vector vector_date, Vector vector_team, Vector
     * vector_user, Vector vector_expert) throws SQLException { KmassignworkDAO
     * tawRmAssignworkDAO = new KmassignworkDAO(ds); KmassignSubDAO
     * tawRmAssignSubDAO = new KmassignSubDAO(ds); TawRmAssignExpertDAO
     * tawRmAssignExpertDAO = new TawRmAssignExpertDAO(ds); int workserial = 0;
     * int date_num_i = 0; int team_num_i = 0; int user_num_i = 0; int workid =
     * 0; String dutydate = ""; String dutymaster = ""; String dutyman = "";
     * String expert = ""; String starttime_defined = ""; String endtime_defined =
     * "";
     *  // use for send SMS TawSystemCptroomBo cptroomBO =
     * TawSystemCptroomBo.getInstance(); TawSystemCptroom tawApparatusroom =
     * cptroomBO.getTawSystemCptroomById( new Integer(roomId), 0); String
     * str_Roomname = ""; if (tawApparatusroom != null) { str_Roomname =
     * StaticMethod.null2String(tawApparatusroom .getRoomname()); }
     * TawRmSysteminfoDAO tawRmSysteminfoDAO = new TawRmSysteminfoDAO(ds);
     * TawRmSysteminfo tawRmSysteminfo = tawRmSysteminfoDAO.retrieve(roomId +
     * ""); int int_Maxerrortime = 0; int int_stagger = 0; if (tawRmSysteminfo !=
     * null) { int_Maxerrortime = tawRmSysteminfo.getMaxerrortime(); int_stagger =
     * tawRmSysteminfo.getStaggertime(); } for (date_num_i = 0; date_num_i <
     * date_num; date_num_i++) { dutydate =
     * StaticMethod.getAddZero(String.valueOf(vector_date
     * .elementAt(date_num_i))); tawRmAssignSubDAO.delete_room_date(roomId,
     * dutydate); tawRmAssignExpertDAO.delete_room_date(roomId, dutydate);
     * tawRmAssignworkDAO.delete_room_date(roomId, dutydate);
     *
     * for (team_num_i = 0; team_num_i < team_num; team_num_i++) { workid =
     * team_num_i; dutymaster = StaticMethod.dbNull2String(String
     * .valueOf(vector_user .elementAt((date_num_i * team_num + team_num_i)
     * user_num))); starttime_defined = StaticMethod.getLocalString(String
     * .valueOf(vector_team.elementAt(date_num_i * team_num + team_num_i))); if
     * (team_num_i != 0) { starttime_defined = StaticMethod
     * .getLocalString(StaticMethod.getDateForMinute( starttime_defined,
     * -int_stagger * 60)); } endtime_defined =
     * StaticMethod.getLocalString(String
     * .valueOf(vector_team.elementAt(date_num_i * team_num + team_num_i + 1)));
     * tawRmAssignworkDAO.insert(roomId, dutydate, workid, dutymaster,
     * starttime_defined, endtime_defined); workserial = tawRmAssignworkDAO
     * .get_id(roomId, dutydate, workid);
     *
     * java.util.Date date = new java.util.Date(); java.text.SimpleDateFormat
     * dtFormat = new java.text.SimpleDateFormat( "yyyy-MM-dd HH:mm:ss"); String
     * strDutyDate = dtFormat.format(date);
     *
     * for (user_num_i = 0; user_num_i < user_num; user_num_i++) { dutyman =
     * StaticMethod.dbNull2String(String
     * .valueOf(vector_user.elementAt((date_num_i team_num + team_num_i)
     * user_num + user_num_i))); tawRmAssignSubDAO.insert(workserial, dutyman); }
     * expert = (String) vector_expert.get(team_num_i); if (!"".equals(expert))
     * tawRmAssignExpertDAO.insert(workserial, expert); } } tawRmAssignworkDAO =
     * null; tawRmAssignSubDAO = null; dutydate = null; dutymaster = null;
     * dutyman = null; starttime_defined = null; endtime_defined = null;
     * cptroomBO = null; tawApparatusroom = null; str_Roomname = null;
     * tawRmSysteminfoDAO = null; tawRmSysteminfo = null; }
     */

    public void updateAssignwork2(int roomId, int date_num, int team_num,
                                  int user_num, String dutydate, Vector vector_team,
                                  Vector vector_user, int cycle) throws SQLException {
        KmassignworkDAO tawRmAssignworkDAO = new KmassignworkDAO(ds);
        KmassignSubDAO tawRmAssignSubDAO = new KmassignSubDAO(ds);
        int workserial = 0;
        int date_num_i = 0;
        int team_num_i = 0;
        int user_num_i = 0;
        int workid = 0;
        // String dutydate = "";
        String dutymaster = "";
        String dutyman = "";
        String starttime_defined = "";
        String endtime_defined = "";

        // use for send SMS
        // edit by wangheqi
        KmsystemCptroomBo cptroomBO = KmsystemCptroomBo.getInstance();
        KmsystemCptroom tawApparatusroom = cptroomBO.getKmsystemCptroomById(
                new Integer(roomId), 0);
        // TawApparatusroomDAO tawApparatusroomDAO = new
        // TawApparatusroomDAO(ds);
        // TawApparatusroom tawApparatusroom =
        // tawApparatusroomDAO.retrieve(roomId);
        String str_Roomname = "";
        if (tawApparatusroom != null) {
            // str_Roomname =
            // StaticMethod.null2String(tawApparatusroom.getRoomName());
            str_Roomname = StaticMethod.null2String(tawApparatusroom
                    .getRoomname());
        }
        KmsysteminfoDAO tawRmSysteminfoDAO = new KmsysteminfoDAO(ds);
        Kmsysteminfo tawRmSysteminfo = tawRmSysteminfoDAO.retrieve(roomId + "");
        int int_Maxerrortime = 0;
        int int_stagger = 0;
        if (tawRmSysteminfo != null) {
            int_Maxerrortime = tawRmSysteminfo.getMaxerrortime();
            int_stagger = tawRmSysteminfo.getStaggertime();
        }
        dutydate = StaticMethod.getAddZero(dutydate);
        tawRmAssignSubDAO.delete_room_date(roomId, dutydate);
        tawRmAssignworkDAO.delete_room_date(roomId, dutydate);

        for (team_num_i = 0; team_num_i < vector_team.size() - 1; team_num_i++) {
            workid = team_num_i;
            if (cycle == 0) {
                dutymaster = StaticMethod.dbNull2String(String
                        .valueOf(vector_user.elementAt(team_num_i * user_num
                                + cycle * team_num * user_num)));
            } else {
                dutymaster = StaticMethod.dbNull2String(String
                        .valueOf(vector_user.elementAt(team_num_i * user_num
                                + cycle * team_num * user_num)));
            }
            int k = cycle * date_num;
            starttime_defined = String.valueOf(vector_team
                    .elementAt(team_num_i));
            if (team_num_i != 0) {
                starttime_defined = StaticMethod
                        .getLocalString(StaticMethod.getDateForMinute(
                                starttime_defined, -int_stagger * 60));
            }
            endtime_defined = String.valueOf(vector_team
                    .elementAt(team_num_i + 1));
            tawRmAssignworkDAO.insert(roomId, dutydate, workid, dutymaster,
                    starttime_defined, endtime_defined);
            workserial = tawRmAssignworkDAO.get_id(roomId, dutydate, workid);

            java.util.Date date = new java.util.Date();
            java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            String strDutyDate = dtFormat.format(date);

            for (user_num_i = 0; user_num_i < user_num; user_num_i++) {
                if (cycle == 0) {
                    dutyman = StaticMethod.dbNull2String(String
                            .valueOf(vector_user.elementAt(team_num_i
                                    * user_num + cycle * team_num * user_num
                                    + user_num_i)));
                } else {
                    dutyman = StaticMethod.dbNull2String(String
                            .valueOf(vector_user.elementAt(team_num_i
                                    * user_num + cycle * team_num * user_num
                                    + user_num_i)));
                }
                // System.out.println(dutyman+cycle+team_num);
                tawRmAssignSubDAO.insert(workserial, dutyman);
                // edit by wangheqi for sms
                // insertSMS(roomId,dutydate,dutyman,str_Roomname,starttime_defined,int_Maxerrortime);
            }
            // edit by wangheqi for sms
            // 排班提醒
            // RemindAssignwork(roomId,"");
        }

        // null
        tawRmAssignworkDAO = null;
        tawRmAssignSubDAO = null;
        dutydate = null;
        dutymaster = null;
        dutyman = null;
        starttime_defined = null;
        endtime_defined = null;
        cptroomBO = null;
        // tawApparatusroomDAO =null;
        tawApparatusroom = null;
        str_Roomname = null;
        tawRmSysteminfoDAO = null;
        tawRmSysteminfo = null;
    }

    /*
     * public void updateAssignwork2AddExpert(int roomId, int date_num, int
     * team_num, int user_num, String dutydate, Vector vector_team, Vector
     * vector_user, int cycle, Vector vector_expert) throws SQLException {
     * TawRmAssignworkDAO tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
     * TawRmAssignSubDAO tawRmAssignSubDAO = new TawRmAssignSubDAO(ds);
     * TawRmAssignExpertDAO tawRmAssignExpertDAO = new TawRmAssignExpertDAO(ds);
     * int workserial = 0; int date_num_i = 0; int team_num_i = 0; int
     * user_num_i = 0; int workid = 0; // String dutydate = ""; String
     * dutymaster = ""; String dutyman = ""; String expert = ""; String
     * starttime_defined = ""; String endtime_defined = "";
     *  // use for send SMS TawSystemCptroomBo cptroomBO =
     * TawSystemCptroomBo.getInstance(); TawSystemCptroom tawApparatusroom =
     * cptroomBO.getTawSystemCptroomById( new Integer(roomId), 0);
     *
     * String str_Roomname = ""; if (tawApparatusroom != null) { str_Roomname =
     * StaticMethod.null2String(tawApparatusroom .getRoomname()); }
     * TawRmSysteminfoDAO tawRmSysteminfoDAO = new TawRmSysteminfoDAO(ds);
     * TawRmSysteminfo tawRmSysteminfo = tawRmSysteminfoDAO.retrieve(roomId +
     * ""); int int_Maxerrortime = 0; int int_stagger = 0; if (tawRmSysteminfo !=
     * null) { int_Maxerrortime = tawRmSysteminfo.getMaxerrortime(); int_stagger =
     * tawRmSysteminfo.getStaggertime(); } dutydate =
     * StaticMethod.getAddZero(dutydate);
     * tawRmAssignSubDAO.delete_room_date(roomId, dutydate);
     * tawRmAssignExpertDAO.delete_room_date(roomId, dutydate);
     * tawRmAssignworkDAO.delete_room_date(roomId, dutydate);
     *
     * for (team_num_i = 0; team_num_i < vector_team.size() - 1; team_num_i++) {
     * workid = team_num_i; if (cycle == 0) { dutymaster =
     * StaticMethod.dbNull2String(String
     * .valueOf(vector_user.elementAt(team_num_i * user_num + cycle * team_num *
     * user_num))); expert = (String) vector_expert.get(cycle); } else {
     * dutymaster = StaticMethod.dbNull2String(String
     * .valueOf(vector_user.elementAt(team_num_i * user_num + cycle * team_num *
     * user_num))); expert = (String) vector_expert.get(cycle); } int k = cycle *
     * date_num; starttime_defined = String.valueOf(vector_team
     * .elementAt(team_num_i)); if (team_num_i != 0) { starttime_defined =
     * StaticMethod .getLocalString(StaticMethod.getDateForMinute(
     * starttime_defined, -int_stagger * 60)); } endtime_defined =
     * String.valueOf(vector_team .elementAt(team_num_i + 1));
     * tawRmAssignworkDAO.insert(roomId, dutydate, workid, dutymaster,
     * starttime_defined, endtime_defined); workserial =
     * tawRmAssignworkDAO.get_id(roomId, dutydate, workid);
     *
     * java.util.Date date = new java.util.Date(); java.text.SimpleDateFormat
     * dtFormat = new java.text.SimpleDateFormat( "yyyy-MM-dd HH:mm:ss"); String
     * strDutyDate = dtFormat.format(date);
     *
     * for (user_num_i = 0; user_num_i < user_num; user_num_i++) { if (cycle ==
     * 0) { dutyman = StaticMethod.dbNull2String(String
     * .valueOf(vector_user.elementAt(team_num_i user_num + cycle * team_num *
     * user_num + user_num_i))); } else { dutyman =
     * StaticMethod.dbNull2String(String
     * .valueOf(vector_user.elementAt(team_num_i user_num + cycle * team_num *
     * user_num + user_num_i))); } tawRmAssignSubDAO.insert(workserial,
     * dutyman);
     *  } tawRmAssignExpertDAO.insert(workserial, expert); }
     *  // null tawRmAssignworkDAO = null; tawRmAssignSubDAO = null; dutydate =
     * null; dutymaster = null; dutyman = null; starttime_defined = null;
     * endtime_defined = null; cptroomBO = null; // tawApparatusroomDAO =null;
     * tawApparatusroom = null; str_Roomname = null; tawRmSysteminfoDAO = null;
     * tawRmSysteminfo = null; }
     */

    /**
     * @param roomId
     * @param date_num
     * @param team_num
     * @param user_num
     * @param vector_date
     * @param vector_team
     * @param vector_user
     * @throws SQLException
     * @see 刷新排班，对于已上班班次不修改，对于未上班班次增加新的排班表
     * @see 用于补排当日班
     */
    public void updateAssignwork1(int roomId, int date_num, int team_num,
                                  int user_num, Vector vector_date, Vector vector_team,
                                  Vector vector_user) throws SQLException {
        KmassignworkDAO tawRmAssignworkDAO = new KmassignworkDAO(ds);
        KmassignSubDAO tawRmAssignSubDAO = new KmassignSubDAO(ds);
        int workserial = 0;
        int date_num_i = 0;
        int team_num_i = 0;
        int user_num_i = 0;
        int workid = 0;
        String dutydate = "";
        String dutymaster = "";
        String dutyman = "";
        String starttime_defined = "";
        String endtime_defined = "";

        // use for send SMS
        // edit by wangheqi
        KmsystemCptroomBo cptroomBO = KmsystemCptroomBo.getInstance();
        KmsystemCptroom tawApparatusroom = cptroomBO.getKmsystemCptroomById(
                new Integer(roomId), 0);

        // TawApparatusroomDAO tawApparatusroomDAO = new
        // TawApparatusroomDAO(ds);
        // TawApparatusroom tawApparatusroom =
        // tawApparatusroomDAO.retrieve(roomId);
        String str_Roomname = "";
        if (tawApparatusroom != null) {
            str_Roomname = StaticMethod.null2String(tawApparatusroom
                    .getRoomname());
            // str_Roomname =
            // StaticMethod.null2String(tawApparatusroom.getRoomName());
        }
        KmsysteminfoDAO tawRmSysteminfoDAO = new KmsysteminfoDAO(ds);
        Kmsysteminfo tawRmSysteminfo = tawRmSysteminfoDAO.retrieve(roomId + "");
        int int_Maxerrortime = 0;
        int int_stagger = 0;
        if (tawRmSysteminfo != null) {
            int_Maxerrortime = tawRmSysteminfo.getMaxerrortime();
            int_stagger = tawRmSysteminfo.getStaggertime();
        }
        //

        for (date_num_i = 0; date_num_i < date_num; date_num_i++) {
            dutydate = StaticMethod.getAddZero(String.valueOf(vector_date
                    .elementAt(date_num_i)));

            for (team_num_i = 0; team_num_i < team_num; team_num_i++) {

                workid = team_num_i;
                dutymaster = StaticMethod.dbNull2String(String
                        .valueOf(vector_user
                                .elementAt((date_num_i * team_num + team_num_i)
                                        * user_num)));
                starttime_defined = String.valueOf(vector_team
                        .elementAt(date_num_i * team_num + team_num_i));
                endtime_defined = String.valueOf(vector_team
                        .elementAt(date_num_i * team_num + team_num_i + 1));
                if (endtime_defined == "null") {
                    endtime_defined = "";
                } else {
                    endtime_defined = StaticMethod
                            .getLocalString(endtime_defined);
                }
                if (starttime_defined == "null") {
                    starttime_defined = "";
                } else {
                    if (team_num_i != 0) {
                        starttime_defined = StaticMethod
                                .getLocalString(StaticMethod.getDateForMinute(
                                        starttime_defined, -int_stagger * 60));
                    } else {
                        starttime_defined = StaticMethod
                                .getLocalString(starttime_defined);
                    }
                }
                if (!checkDutyRecordExist(roomId, starttime_defined)) {
                    delete_room_starttime_sub(roomId, starttime_defined);
                    delete_room_starttime_main(roomId, starttime_defined);
                    tawRmAssignworkDAO.insert(roomId, dutydate, workid,
                            dutymaster, starttime_defined, endtime_defined);
                    workserial = tawRmAssignworkDAO.get_id(roomId, dutydate,
                            workid);

                    java.util.Date date = new java.util.Date();
                    java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    String strDutyDate = dtFormat.format(date);

                    for (user_num_i = 0; user_num_i < user_num; user_num_i++) {
                        dutyman = StaticMethod.dbNull2String(String
                                .valueOf(vector_user.elementAt((date_num_i
                                        * team_num + team_num_i)
                                        * user_num + user_num_i)));
                        tawRmAssignSubDAO.insert(workserial, dutyman);
                        /*
                         * insertSMS(roomId, dutydate, dutyman, str_Roomname,
                         * starttime_defined, int_Maxerrortime);
                         */
                    }
                }
            }
        }
        // null
        tawRmAssignworkDAO = null;
        tawRmAssignSubDAO = null;
        dutydate = null;
        dutymaster = null;
        dutyman = null;
        starttime_defined = null;
        endtime_defined = null;
        cptroomBO = null;
        tawApparatusroom = null;
        str_Roomname = null;
        tawRmSysteminfoDAO = null;
        tawRmSysteminfo = null;
    }

    /*
     * public void updateAssignwork1AddExpert(int roomId, int date_num, int
     * team_num, int user_num, Vector vector_date, Vector vector_team, Vector
     * vector_user, Vector vector_expert) throws SQLException {
     * TawRmAssignworkDAO tawRmAssignworkDAO = new TawRmAssignworkDAO(ds);
     * TawRmAssignSubDAO tawRmAssignSubDAO = new TawRmAssignSubDAO(ds);
     * TawRmAssignExpertDAO tawRmAssignExpertDAO = new TawRmAssignExpertDAO(ds);
     * int workserial = 0; int date_num_i = 0; int team_num_i = 0; int
     * user_num_i = 0; int workid = 0; String dutydate = ""; String dutymaster =
     * ""; String dutyman = ""; String starttime_defined = ""; String
     * endtime_defined = ""; String expert = ""; TawSystemCptroomBo cptroomBO =
     * TawSystemCptroomBo.getInstance(); TawSystemCptroom tawApparatusroom =
     * cptroomBO.getTawSystemCptroomById( new Integer(roomId), 0);
     *
     * String str_Roomname = ""; if (tawApparatusroom != null) { str_Roomname =
     * StaticMethod.null2String(tawApparatusroom .getRoomname()); }
     * TawRmSysteminfoDAO tawRmSysteminfoDAO = new TawRmSysteminfoDAO(ds);
     * TawRmSysteminfo tawRmSysteminfo = tawRmSysteminfoDAO.retrieve(roomId +
     * ""); int int_Maxerrortime = 0; int int_stagger = 0; if (tawRmSysteminfo !=
     * null) { int_Maxerrortime = tawRmSysteminfo.getMaxerrortime(); int_stagger =
     * tawRmSysteminfo.getStaggertime(); } //
     *
     * for (date_num_i = 0; date_num_i < date_num; date_num_i++) { dutydate =
     * StaticMethod.getAddZero(String.valueOf(vector_date
     * .elementAt(date_num_i)));
     *
     * for (team_num_i = 0; team_num_i < team_num; team_num_i++) {
     *
     * workid = team_num_i; dutymaster = StaticMethod.dbNull2String(String
     * .valueOf(vector_user .elementAt((date_num_i * team_num + team_num_i)
     * user_num))); starttime_defined = String.valueOf(vector_team
     * .elementAt(date_num_i * team_num + team_num_i)); endtime_defined =
     * String.valueOf(vector_team .elementAt(date_num_i * team_num + team_num_i +
     * 1)); if (endtime_defined == "null") { endtime_defined = ""; } else {
     * endtime_defined = StaticMethod .getLocalString(endtime_defined); } if
     * (starttime_defined == "null") { starttime_defined = ""; } else { if
     * (team_num_i != 0) { starttime_defined = StaticMethod
     * .getLocalString(StaticMethod.getDateForMinute( starttime_defined,
     * -int_stagger * 60)); } else { starttime_defined = StaticMethod
     * .getLocalString(starttime_defined); } } if (!checkDutyRecordExist(roomId,
     * starttime_defined)) { delete_room_starttime_sub(roomId,
     * starttime_defined); delete_room_starttime_main(roomId,
     * starttime_defined); tawRmAssignworkDAO.insert(roomId, dutydate, workid,
     * dutymaster, starttime_defined, endtime_defined); workserial =
     * tawRmAssignworkDAO.get_id(roomId, dutydate, workid);
     *
     * java.util.Date date = new java.util.Date(); java.text.SimpleDateFormat
     * dtFormat = new java.text.SimpleDateFormat( "yyyy-MM-dd HH:mm:ss"); String
     * strDutyDate = dtFormat.format(date);
     *
     * for (user_num_i = 0; user_num_i < user_num; user_num_i++) { dutyman =
     * StaticMethod.dbNull2String(String
     * .valueOf(vector_user.elementAt((date_num_i team_num + team_num_i)
     * user_num + user_num_i))); tawRmAssignSubDAO.insert(workserial, dutyman);
     *
     *
     *  } expert = (String) vector_expert.get(team_num_i); if
     * (!"".equals(expert)) tawRmAssignExpertDAO.insert(workserial, expert); } } }
     * tawRmAssignworkDAO = null; tawRmAssignSubDAO = null; dutydate = null;
     * dutymaster = null; dutyman = null; starttime_defined = null;
     * endtime_defined = null; cptroomBO = null; tawApparatusroom = null;
     * str_Roomname = null; tawRmSysteminfoDAO = null; tawRmSysteminfo = null; }
     */

    /**
     * @param room_id
     * @param starttime_defined
     * @return
     * @throws SQLException
     * @see 根据机房ID和排班开始时间，判断是否有排班
     */
    public boolean checkDutyRecordExist(int room_id, String starttime_defined)
            throws SQLException {
        boolean checkDutyRecordExist = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "select count(*) from km_record where id in (select id from km_assignwork where starttime_defined = ? and room_id = ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, starttime_defined);
            pstmt.setInt(2, room_id);
            rs = pstmt.executeQuery();
            // null
            sql = null;
            if (rs.next()) {
                if (rs.getInt(1) > 0) {
                    checkDutyRecordExist = true;
                }
            }
            rs.close();
            pstmt.close();
            // null
            rs = null;
        } catch (SQLException e) {
            rs.close();
            pstmt.close();
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return checkDutyRecordExist;
    }

    /**
     * @param roomId
     * @param starttime_defined
     * @throws SQLException
     * @see 删除对应机房ID和值班开始时间的排班表
     */
    public void delete_room_starttime_main(int roomId, String starttime_defined)
            throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            String sql = "DELETE FROM km_assignwork WHERE room_id=? and starttime_defined=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomId);
            pstmt.setString(2, starttime_defined);
            pstmt.executeUpdate();
            pstmt.close();
            conn.commit();
        } catch (SQLException e) {
            pstmt.close();
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

    /**
     * @param roomId
     * @param starttime_defined
     * @throws SQLException
     * @see 删除对应机房ID和值班开始时间的排班表对应的排班子表
     */
    public void delete_room_starttime_sub(int roomId, String starttime_defined)
            throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = ds.getConnection();
            String sql = "DELETE FROM km_assign_sub WHERE workserial in (select id from km_assignwork where room_id=? and starttime_defined=?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomId);
            pstmt.setString(2, starttime_defined);
            pstmt.executeUpdate();
            pstmt.close();
            conn.commit();
        } catch (SQLException e) {
            pstmt.close();
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

    /**
     * @param strDutyDate
     * @param strStarttime
     * @param int_Maxerrortime
     * @return
     * @see 目前不用该功能
     */
    public int getTimelimit(String strDutyDate, String strStarttime,
                            int int_Maxerrortime) {
        int intTimelimit = 0;

        strStarttime = StaticMethod.null2String(strStarttime).replaceAll(
                "\\.0", "");
        strDutyDate = StaticMethod.null2String(strDutyDate).replaceAll("\\.0",
                "");
        GregorianCalendar calStarttime = StaticMethod.String2Cal(strStarttime);
        GregorianCalendar calDutyDate = StaticMethod.String2Cal(strDutyDate);

        intTimelimit = 100;
        return intTimelimit;

    }

    /**
     * @param roomId
     * @param dutydate
     * @param dutyman
     * @param str_Roomname
     * @param strStarttime
     * @param int_Maxerrortime
     * @throws SQLException
     * @see 插入短信，提醒值班人员上班
     */
    public void insertSMS(int roomId, String dutydate, String dutyman,
                          String str_Roomname, String strStarttime, int int_Maxerrortime)
            throws SQLException {

        TawSystemUserRoleBo userbo = TawSystemUserRoleBo.getInstance();
        TawSystemUser tawRmUser = null;
        // edit end
        if (dutyman != null && !dutyman.equals("")) {
            // edit by wangheqi 2.7to3.5
            // TawSystemUserRoleBo userbo = TawSystemUserRoleBo.getInstance();
            tawRmUser = userbo.getUserByuserid(dutyman);
            // edit end
        }
        // tawRmUser = tawRmUserDAO.retrieve(dutyman);

        // TODO 发送短信
        // TawHieMonitorBO tawHieMonitorBO = new TawHieMonitorBO();
        // String serviceType=MesStaticVariable.DUTY;

        // end 发送短信
        String strDutyDate = "";
        int dealStatus = 0;
        int hieUrgeGrade = 0;
        String taskId = "duty_" + String.valueOf(roomId) + "_" + dutydate;
        String receiver = dutyman;
        int receiverType = 3;
        String attachNote = "";
        String strUserName = "";
        if (tawRmUser != null)
            // edit by wangheqi 2.7to3.5
            strUserName = tawRmUser.getUsername();
        // strUserName = tawRmUser.getUserName();
        GregorianCalendar cal_start = StaticMethod.String2Cal(strStarttime);
        int_Maxerrortime = -int_Maxerrortime;
        cal_start.add(cal_start.MINUTE, int_Maxerrortime);
        strDutyDate = StaticMethod.Cal2String(cal_start);

        attachNote = strUserName + "您好，" + str_Roomname + "有您的值班，时间为"
                + strStarttime + "，请按时到达!";

        // TODO 发送消息
        // tawHieMonitorBO.sendMessage(serviceType,dealStatus,hieUrgeGrade,taskId,
        // strDutyDate,receiver,receiverType,attachNote,0);
        // END 发送消息
        // tawRmUserDAO=null;
        userbo = null;
        tawRmUser = null;
    }

    /**
     * @param roomId
     * @param dutydate
     * @throws SQLException
     * @see 删除机房ID和日期对应的短信
     */
    public void deleteSMS(int roomId, String dutydate) throws SQLException {
        // TODO 发送消息
        // TawHieMonitorBO tawHieMonitorBO = new TawHieMonitorBO();
        // String serviceType = MesStaticVariable.DUTY;
        // end 发送消息
        int dealStatus = 0;
        int hieUrgeGrade = 0;
        String taskId = "duty_" + String.valueOf(roomId) + "_" + dutydate;

        // TODO 发送消息
        // tawHieMonitorBO.closeMessage(serviceType, dealStatus, hieUrgeGrade,
        // taskId);
    }

    /**
     * @return
     * @throws SQLException
     * @see 获得所有非删除机房id的vector；主要用于：对于admin型的用户，有操作所有机房的权限，故机房的下拉选单要显示所有机房
     */
    public Vector getRoomSelect() throws SQLException {
        Vector vector = new Vector();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT id,roomname FROM km_system_cptroom WHERE deleted=0 order by roomname";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            // null
            sql = null;
            while (rs.next()) {
                vector.add(rs.getString(1));
            }
            rs.close();
            pstmt.close();
            // null
            rs = null;
        } catch (SQLException e) {
            rs.close();
            pstmt.close();
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return vector;
    }

    public void RemindAssignwork(int roomId, String userid, String result)
            throws SQLException {
        // 取出机房管理员以及临时管理员
        // TODO 发送消息
        // TawHieMonitorBO tawHieMonitorBO = new TawHieMonitorBO();
        // TawHieMonitorDAO tawHieMonitorDAO = new TawHieMonitorDAO();
        // END
        KmsystemCptroomBo cptroomBO = KmsystemCptroomBo.getInstance();
        KmsystemCptroom tawApparatusroom = cptroomBO.getKmsystemCptroomById(
                new Integer(roomId), 0);

        // TawApparatusroomDAO tawApparatusroomDAO = new
        // TawApparatusroomDAO(ds);
        // TawApparatusroom tawApparatusroom =
        // tawApparatusroomDAO.retrieve(roomId);
        String managerId = tawApparatusroom.getManager();
        String tempId = tawApparatusroom.getTempmanager();
        // edit by wangheqi 2.7to3.5
        TawSystemUserRoleBo userbo = TawSystemUserRoleBo.getInstance();
        TawSystemUser tawRmUser = null;
        // TawRmUserDAO tawRmUserDAO = new TawRmUserDAO(ds);
        String roomName = tawApparatusroom.getRoomname();
        KmassignworkDAO tawRmAssignworkDAO = new KmassignworkDAO(ds);
        KmsysteminfoDAO tawRmSysteminfoDAO = new KmsysteminfoDAO(ds);
        Kmsysteminfo tawRmSysteminfo = tawRmSysteminfoDAO.retrieve(String
                .valueOf(roomId));
        if (tawRmSysteminfo.getExAnswer() != 0
                || tawRmSysteminfo.getExRequest() != 0
                || tawRmSysteminfo.getDutyInform() != 0) {
            // TODO 发送消息
            // String serviceType = MesStaticVariable.DUTY;
            // end
            int dealStatus = 0;
            int hieUrgeGrade = 0;
            String taskId = "assignwork_" + String.valueOf(roomId);
            String dispatcher = "";
            String receiver = managerId;
            int receiverType = 3;
            String attachNote = "";
            String strUserName = "";

            String maxduty1 = tawRmAssignworkDAO.getMaxDutydate(roomId); // 排班表里最大的日期
            if (maxduty1 != null)
                maxduty1 = StaticMethod.StringReplace(maxduty1, ".0", "");
            else
                maxduty1 = "";
            // TODO 发送消息
            // String maxduty2 = tawHieMonitorDAO.getMaxDate(roomId,
            // "assignwork");
            // // 短信表里最大的日期
            String maxduty2 = "";
            // END
            GregorianCalendar cal_assign = StaticMethod.String2Cal(maxduty1);
            GregorianCalendar cal_monitor = StaticMethod.String2Cal(maxduty2);
            // TODO 发送消息

            if (maxduty2 == null || "".equals(maxduty2)) {
                if (managerId != null && !"".equals(managerId)) {
                    tawRmUser = userbo.getUserByuserid(managerId);
                    // edit end
                    if (tawRmUser != null)
                        // edit by wangheqi 2.7to 3.5
                        strUserName = tawRmUser.getUsername();

                    attachNote = strUserName + "您好，" + roomName + "的班次的最后日期是"
                            + maxduty1 + "，请即时排班";

                }
                if (tempId != null && !"".equals(tempId)) {
                    if (tawRmUser != null)
                        strUserName = userbo.getUsernameByUserid(tempId);

                    taskId = "assignwork_" + String.valueOf(roomId);
                    receiver = strUserName;
                    attachNote = strUserName + "您好，" + roomName + "的班次的最后日期是"
                            + maxduty1 + "，请即时排班";

                }
                if (userid != null && !"".equals(userid)) {

                    // edit by wangheqi
                    String userName = userbo.getUsernameByUserid(userid);
                    // strUserName = tawRmUser.getUserName();

                    taskId = "assignwork_" + String.valueOf(roomId);
                    receiver = strUserName;
                    if ("success".equals(result)) {
                        attachNote = "管理员" + userName + "您好，" + roomName
                                + "的班次的最后日期是" + maxduty1 + "，请即时排班";
                    } else if ("fail".equals(result)) {
                        attachNote = "管理员" + userName + "您好，" + roomName
                                + "的排班操作失败，请重新排班";
                    }

                    MsgServiceImpl msgService = new MsgServiceImpl();
                    if (msgService.hasService(serverId).equals("true")) {
                        msgService.sendMsg(serverId, attachNote, roomId + "",
                                "1," + userid, creattime);
                        String dutytime = getDateAddString(maxduty1, -1);
                        msgService.sendMsg(serverId, attachNote, roomId + "",
                                "1," + userid, dutytime);
                    }
                }
            } else if (cal_assign.after(cal_monitor)) { // 如果短信表里日期小于排班的日期
                // add by wangheqi for sms
                // tawHieMonitorDAO.UptMaxDate(roomId, roomName, "assignwork",
                // maxduty1);
            }
            userbo = null;
            tawRmUser = null;
        }
        // TODO 发送消息
        // tawHieMonitorDAO = null;
        // end
    }

    public String exportUrl(String roomId, String timeForm, String timeTo) {
        Vector vectorQueryResult = new Vector();
        String url = "";
        TawSystemCptroomBo cptroomBO = TawSystemCptroomBo.getInstance();
        TawSystemCptroom tawApparatusroom = null;
        try {
            String timeTag = StaticMethod.getCurrentDateTime("yyyyMMddHH");
            String uploadPath = DutyMgrLocator.getAttributes()
                    .getDutyRootPath()
                    + "/";

            String sysTemPaht = StaticMethod.getWebPath();

            url = "/" + sysTemPaht + "/" + uploadPath + timeTag + ".xls";
            tawApparatusroom = cptroomBO.getTawSystemCptroomById(new Integer(
                    Integer.parseInt(roomId)), 0);
            String roomName = tawApparatusroom.getRoomname();
            // 建立输出流
            FileOutputStream fos = new FileOutputStream(url);
            // 建立一个新的工作表
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet();

            HSSFRow row = null; // 建立新行
            HSSFCell cell = null; // 建立新cell
            HSSFCellStyle cellStyle = wb.createCellStyle();
            ; // 样式
            cellStyle.setWrapText(true);
            cellStyle.setVerticalAlignment((short) 1); // 设置垂直居中
            cellStyle.setAlignment((short) 2); // 设置水平居中

            // 写信息－start
            // TawRmRecordDAO tawRmRecordDAO = new TawRmRecordDAO(ds);
            int y = 0;
            // TawRmRecord tawRmRecord = null;

            // List tawRmRecords = null;

            /** ********************配置常量单元格信息 start************************ */
            // 常量值
            String[] valueOfLine = {"值班日期", "周", "开始时间", "结束时间", "值班长", "值班人员"};
            // 对应的行
            int[] rowOfLine = {1, 1, 1, 1, 1, 1};
            // 对应的列
            int[] colOfLine = {0, 1, 2, 3, 4, 5};
            // xy
            int[][] xyOfLine = {{1, 1}, {1, 1}, {1, 1}, {1, 1},
                    {1, 1}, {1, 1}};

            vectorQueryResult = this.getQueryResultVector(Integer
                    .parseInt(roomId), timeForm, timeTo);

            wb.setSheetName(0, "排班情况", HSSFWorkbook.ENCODING_UTF_16);
            row = sheet.createRow((short) (0));
            sheet.addMergedRegion(new Region((0), (short) 0, (short) (0),
                    (short) 5));
            //
            cell = row.createCell((short) 0);
            cell.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell.setCellValue(roomName + "  ：" + timeForm + "----->" + timeTo);

            // 定义常量单元格
            for (int i = 0; i < valueOfLine.length; i++) {
                row = sheet.createRow((short) rowOfLine[i]); // 建立新行
                cell = row.createCell((short) colOfLine[i]); // 建立新cell
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                cell.setCellValue(valueOfLine[i]);

                sheet.addMergedRegion(new Region((short) rowOfLine[i],
                        (short) (colOfLine[i]), (short) (rowOfLine[i]
                        + xyOfLine[i][0] - 1), (short) (colOfLine[i]
                        + xyOfLine[i][1] - 1)));

            }
            Vector getDutydate = (Vector) vectorQueryResult.elementAt(0);
            Vector getStarttime = (Vector) vectorQueryResult.elementAt(1);
            Vector getEndtime = (Vector) vectorQueryResult.elementAt(2);
            Vector getDutymaster = (Vector) vectorQueryResult.elementAt(3);
            Vector getDutyman = (Vector) vectorQueryResult.elementAt(4);

            y = 2;
            // 循环处理执行内容
            for (int i = 0; i < getDutydate.size(); i++) {// tawRmRecords.size();

                // tawRmRecord = (TawRmRecord) tawRmRecords.get(i);

                // 新的行
                row = sheet.createRow((short) (i + 2));
                sheet.addMergedRegion(new Region((short) ((i) + y), (short) 0,
                        (short) ((i) + y), (short) 0));
                // HSSFCellStyle style = wb.createCellStyle();
                // style.setAlignment(HSSFCellStyle.ALIGN_CENTER );
                cell = row.createCell((short) 0);
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                cell.setCellValue(getDutydate.elementAt(i) + "");

                Vector date_vector = StaticMethod.getVector(String.valueOf(
                        getDutydate.elementAt(i)).trim(), "-");
                int year = Integer.parseInt(String.valueOf(
                        date_vector.elementAt(0)).trim());
                int month = Integer.parseInt(String.valueOf(
                        date_vector.elementAt(1)).trim());
                int day = Integer.parseInt(String.valueOf(
                        date_vector.elementAt(2)).trim());
                java.util.Date date = new java.util.Date(year - 1900,
                        month - 1, day - 0);

                int numOfWeek = date.getDay();
                String weekName[] = {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五",
                        "星期六"};
                sheet.addMergedRegion(new Region((short) ((i) + y), (short) 1,
                        (short) ((i) + y), (short) 1));
                cell = row.createCell((short) 1);
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                cell.setCellValue(weekName[numOfWeek]);

                sheet.addMergedRegion(new Region((short) ((i) + y), (short) 2,
                        (short) ((i) + y), (short) 2));
                cell = row.createCell((short) 2);
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                cell.setCellValue(getStarttime.elementAt(i) + "");

                sheet.addMergedRegion(new Region((short) ((i) + y), (short) 3,
                        (short) ((i) + y), (short) 3));
                cell = row.createCell((short) 3);
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                cell.setCellValue(getEndtime.elementAt(i) + "");

                sheet.addMergedRegion(new Region((short) ((i) + y), (short) 4,
                        (short) ((i) + y), (short) 4));
                cell = row.createCell((short) 4);
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                cell.setCellValue(getDutymaster.elementAt(i) + "");

                sheet.addMergedRegion(new Region((short) ((i) + y), (short) 5,
                        (short) ((i) + y), (short) 5));
                cell = row.createCell((short) 5);

                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                cell.setCellValue(getDutyman.elementAt(i) + "");

                y++;

            }

            wb.write(fos);
            fos.close(); // 关闭输出流

        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i % 4);

        }
    }

    public List getRoomSelectName() throws SQLException {
        Vector vector = new Vector();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List list = new java.util.ArrayList();
        try {
            conn = ds.getConnection();
            String sql = "SELECT id,roomname FROM km_system_cptroom WHERE deleted=0 order by roomname";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            // null
            sql = null;
            while (rs.next()) {
                TawApparatusroom tawApparatusroom = new TawApparatusroom();
                tawApparatusroom.setId(rs.getInt(1));
                tawApparatusroom.setRoomName(rs.getString(2));
                list.add(tawApparatusroom);
            }
            rs.close();
            pstmt.close();
            // null
            rs = null;
        } catch (SQLException e) {
            rs.close();
            pstmt.close();
            conn.rollback();
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return list;
    }

    /**
     * @param id int 机房ID
     * @return Model(TawApparatusroom)类型值。
     * @throws SQLException
     * @see 根据机房ID，检索机房信息！
     * @see 该方法是单表检索，只对taw_apparatusroom表检索。
     */
    public String retrieve(int id) throws SQLException {
        String roomName = null;
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ds.getConnection();
            String sql = "SELECT roomname FROM km_system_cptroom WHERE deleted=0 and id=? order by roomname";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                roomName = rs.getString(1);

            }
            close(rs);
            close(pstmt);
        } catch (SQLException e) {
            close(rs);
            close(pstmt);
            // rollback(conn);
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return roomName;
    }

    protected void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            }
            rs = null;
        }
    }

    protected void close(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
            }
            pstmt = null;
        }
    }

    protected void close(com.boco.eoms.db.util.BocoConnection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void rollback(com.boco.eoms.db.util.BocoConnection conn) {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    // 将值班人员的数据一次性取出，根据班次的ID拼写值班人员名称的字符串，放到MAP中，减少获取值班人员信息时对taw_rm_assign_sub表的查询次数
    public HashMap getSubDutyMan(int roomId, String time_from, String time_to) {
        HashMap subDutyMain = new HashMap();
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String temp = "";
        String DutymanStr = "";
        try {
            conn = ds.getConnection();
            String sql = "select a.workserial,a.dutyman,b.username from km_assign_sub a,taw_system_user b where b.userid=a.dutyman and a.workserial in (select id from km_assignwork WHERE room_id = ? and dutydate >= ? and dutydate <= ?) order by a.workserial";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, roomId);
            pstmt.setString(2, time_from);
            pstmt.setString(3, time_to);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String id = StaticMethod.null2String(rs.getString(1));
                if (temp.equals("") || id.equals(temp)) {
                    DutymanStr = DutymanStr + " " + rs.getString(3).trim();
                } else {
                    subDutyMain.put(temp, DutymanStr);
                    DutymanStr = rs.getString(3).trim();
                }
                temp = StaticMethod.null2String(rs.getString(1));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            close(rs);
            close(pstmt);
        } finally {
            close(conn);
        }
        subDutyMain.put(temp, DutymanStr);
        return subDutyMain;
    }

    public List getSubDutyMan(String id) {
        com.boco.eoms.db.util.BocoConnection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TawSystemUser tawSystemUser = null;
        List list = new ArrayList();
        try {
            conn = ds.getConnection();
            String sql = "select a.dutyman from km_assign_sub a where  a.workserial = ? order by a.id";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            ITawSystemUserManager mgr = (ITawSystemUserManager) ApplicationContextHolder
                    .getInstance().getBean("itawSystemUserManager");
            while (rs.next()) {
                String userid = StaticMethod.null2String(rs.getString(1));
                tawSystemUser = mgr.getUserByuserid(userid);
                list.add(tawSystemUser);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            close(rs);
            close(pstmt);
        } finally {
            close(conn);
        }

        return list;
    }

    public static String getWorkSerial(String userId,
                                       com.boco.eoms.db.util.ConnectionPool ds) {
        List roomList = new ArrayList();
        KmrecordDAO kmrecordDAO = null;
        KmsystemCptroomBo cptroomBO = KmsystemCptroomBo.getInstance();
        kmrecordDAO = new KmrecordDAO(ds);
        roomList = cptroomBO.getKmsystemCptroomList();
        String expertRoomId = "0";
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat dtFormat = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String strDutyTime = dtFormat.format(date);
        for (int i = 0; i < roomList.size(); i++) {
            expertRoomId = ((org.apache.struts.util.LabelValueBean) roomList
                    .get(i)).getValue();
            try {
                int tempWorkSerial = kmrecordDAO.receiverWorkSerial(
                        expertRoomId, strDutyTime, userId);
                if (!"0".equals(tempWorkSerial + "")) {
                    return tempWorkSerial + "";
                }
            } catch (SQLException e) {

                e.printStackTrace();
                return "0";
            }
        }
        return "0";
    }

    public static String getRoomId(String userId, com.boco.eoms.db.util.ConnectionPool ds) {
        /*
         * List roomList = new ArrayList(); KmsystemCptroomBo cptroomBO =
         * KmsystemCptroomBo.getInstance(); roomList =
         * cptroomBO.getKmsystemCptroomList(); String expertRoomId ="-1";
         * if(roomList.size()>0){ expertRoomId =
         * ((org.apache.struts.util.LabelValueBean)roomList.get(0)).getValue();
         *  } return expertRoomId;
         */
        KmassignworkDAO kmassignworkDAO = null;
        kmassignworkDAO = new KmassignworkDAO(ds);
        String workserial2 = getWorkSerial(userId, ds);
        try {

            if (workserial2.equals("0")) {
                return "-1";
            } else {
                Kmassignwork kmassignwork = kmassignworkDAO.retrieve(Integer.parseInt(workserial2));
                if (kmassignwork != null) {

                    return kmassignwork.getRoomId() + "";
                } else {
                    return "-1";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }

    public static String getRoomName(String userId, com.boco.eoms.db.util.ConnectionPool ds) {
        KmsystemCptroomBo cptroomBO = KmsystemCptroomBo.getInstance();

        String roomId = getRoomId(userId, ds);
        Integer ss = Integer.valueOf(Integer.parseInt(roomId));
        String roomName = cptroomBO.getKmsystemCptroomName(ss);
        return roomName;
    }

    public static boolean isdutyMaster(String userId,
                                       com.boco.eoms.db.util.ConnectionPool ds) {
        // com.boco.eoms.db.util.ConnectionPool ds
        // =com.boco.eoms.db.util.ConnectionPool.getInstance();
        KmassignworkDAO kmassignworkDAO = null;
        kmassignworkDAO = new KmassignworkDAO(ds);
        String workserial2 = getWorkSerial(userId, ds);
        try {

            if (workserial2.equals("0")) {
                return false;
            } else {
                Kmassignwork kmassignwork = kmassignworkDAO.retrieve(Integer
                        .parseInt(workserial2));
                if (kmassignwork != null) {
                    if (userId.equals(kmassignwork.getDutymaster())) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

package com.boco.eoms.sheet.commonfaultAlarm.dao.hibernate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;
import com.boco.eoms.sheet.commonfaultAlarm.dao.IAlarmsolveDateDao;
import com.boco.eoms.sheet.commonfaultAlarm.db.AlarmsolveDateDB;

public class AlarmsolveDateDao extends BaseSheetDaoHibernate implements IAlarmsolveDateDao {

    /***
     *
     */
    public Date findbyid(String alarmsolveid) {
        String[] alarmsolveids = alarmsolveid.split("_");
//		String[] alarmsolveidnum = alarmsolveids[1].split("-");
        String sql = "";
        System.out.println("yangxd in alarmsolveDateDao= " + alarmsolveid + "");
        PreparedStatement ps = null;
        Connection conn = null;
        ResultSet rs = null;
        Date dt = null;

        if (alarmsolveids.length > 0) {
            sql = "select cancel_time from nmosdb.tfa_alarm where fp0 = ? and fp1 = ? and fp2 = ? and fp3 = ?";
            //sql = "select cancel_time from TFA_ALARM_ACT where fp0 = ? and fp1 = ? and fp2 = ? and fp3 = ?";
        }
        try {
            conn = AlarmsolveDateDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, alarmsolveids[2]);
            ps.setString(2, alarmsolveids[3]);
            ps.setString(3, alarmsolveids[4]);
            ps.setString(4, alarmsolveids[5]);
            System.out.println("yangxd fp0=" + alarmsolveids[2] + "  fp1=" + alarmsolveids[3] + "  fp2=" + alarmsolveids[4] + "  fp3=" + alarmsolveids[5]);
            rs = ps.executeQuery();
            if (rs.next()) {
                //dt = StaticMethod.nullObject2Timestamp(rs.getObject("cancel_time"));
                dt = rs.getTimestamp("cancel_time");
                System.out.println("yangxd dt = " + dt);
                //return dt;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return dt;

    }

    public void update(String alarmsolveid, String canceltime) {
        String[] alarmsolveids = alarmsolveid.split("_");
//		String[] alarmsolveidnum = alarmsolveids[1].split("-");
        String sql = "";

        PreparedStatement ps = null;
        Connection conn = null;

        if (alarmsolveids.length > 0) {
            sql = "update fmdb.tfa_alarm_act set cancel_time = to_date(?,'yyyy-mm-dd hh24:mi:ss'),active_status = 0,cancel_terminal = 'EOMS' where fp0 = ? and fp1 = ? and fp2 = ? and fp3 = ?";
//			sql = "update tfa_alarm_act set cancel_time = to_date(?,'yyyy-mm-dd hh24:mi:ss'),active_status = 0,cancel_terminal = 'EOMS' where fp0 = ? and fp1 = ? and fp2 = ? and fp3 = ?";
        }
        try {
            conn = AlarmsolveDateDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, canceltime);
            ps.setString(2, alarmsolveids[2]);
            ps.setString(3, alarmsolveids[3]);
            ps.setString(4, alarmsolveids[4]);
            ps.setString(5, alarmsolveids[5]);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public void updateCheckTime(String sheetid, String checkTime) {
        //	String[] alarmsolveids = alarmsolveid.split("_");
//		String[] alarmsolveidnum = alarmsolveids[1].split("-");
        String sql = "";

        PreparedStatement ps = null;
        Connection conn = null;

        if (!"".equals(sheetid) && sheetid != null) {
            sql = "update tfa_alarm_act a set a.cancel_time = to_date(?,'yyyy-mm-dd hh24:mi:ss') ,active_status = 0 where  a.cancel_time is null and (a.fp0, a.fp1, a.fp2,a.fp3) in (select b.fp0, b.fp1, b.fp2,b.fp3 from tfa_alarmsheetinfo b where b.sheet_no =?) ";
//			sql = "update tfa_alarm_act set cancel_time = to_date(?,'yyyy-mm-dd hh24:mi:ss'),active_status = 0,cancel_terminal = 'EOMS' where fp0 = ? and fp1 = ? and fp2 = ? and fp3 = ?";
            System.out.println("--updateCheckTime---sql--" + sql);
        }
        try {
            conn = AlarmsolveDateDB.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, checkTime);
            ps.setString(2, sheetid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}

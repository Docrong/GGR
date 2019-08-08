package com.boco.eoms.common.resource;

import org.apache.log4j.Category;

import java.sql.PreparedStatement;
import java.sql.Connection;

import com.boco.eoms.db.util.ConnectionPool;

/**
 * Created by IntelliJ IDEA.
 * User: wangxh
 * Date: Jan 9, 2003
 * Time: 3:18:40 PM
 * To change this template use Options | File Templates.
 */

public class SaveLog {

    public SaveLog() {

    }

    protected Category cat = Category.getInstance(this.getClass());

    public void LogSave(String optr_id, String optr_name, String comp_name, String comp_ip, String logModule, String logAction, String logSql, int watch, String memo) {
        Connection dataobj = ConnectionPool.getInstance().getConnection();
        PreparedStatement pst = null;

        String log_time = DateUtil.getCurrentTime();
        String frSql = "insert into a_event_log (optr_id ,optr_name,comp_name,comp_ip,log_module,log_time,log_action,log_sql,watch,memo) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = dataobj.prepareStatement(frSql);
            pst.setString(1, optr_id);
            pst.setString(2, optr_name);
            pst.setString(3, comp_name);
            pst.setString(4, comp_ip);
            pst.setString(5, logModule);
            pst.setString(6, log_time);
            pst.setString(7, logAction);
            pst.setString(8, logSql);
            pst.setInt(9, watch);
            pst.setString(10, memo);
            pst.execute();

        } catch (Exception e) {
            cat.error(":SaveLog:" + e.getMessage());
            cat.error(":SaveLog:optr_id" + optr_id + "#" + optr_name);
            cat.error(":SaveLog:comp_name" + comp_name + "#" + comp_ip);
            cat.error(":SaveLog:logModule" + logModule + "#" + log_time + "#" + logAction);
            cat.error(":SaveLog:Sql" + logSql);
            cat.error(":SaveLog:watch" + watch + "#" + memo);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    cat.error(":SaveLog:close Statement:" + Util.UNI2GBK(e.getMessage()));
                }
            }
            if (dataobj != null) {
                try {
                    dataobj.close();
                } catch (Exception e) {
                    cat.error(":SaveLog:close Connection:" + Util.UNI2GBK(e.getMessage()));
                }
            }
        }
    }

    public void LogSave(Connection dataobj, String optr_id, String optr_name, String comp_name, String comp_ip, String logModule, String logAction, String logSql, int watch, String memo) {
        PreparedStatement pst = null;
        String log_time = DateUtil.getCurrentTime();
        String frSql = "insert into a_event_log (optr_id ,optr_name,comp_name,comp_ip,log_module,log_time,log_action,log_sql,watch,memo) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = dataobj.prepareStatement(frSql);
            pst.setString(1, optr_id);
            pst.setString(2, optr_name);
            pst.setString(3, comp_name);
            pst.setString(4, comp_ip);
            pst.setString(5, logModule);
            pst.setString(6, log_time);
            pst.setString(7, logAction);
            pst.setString(8, logSql);
            pst.setInt(9, watch);
            pst.setString(10, memo);
            pst.execute();

        } catch (Exception e) {
            cat.error(":SaveLog:" + e.getMessage());
            cat.error(":SaveLog:optr_id" + optr_id + "#" + optr_name);
            cat.error(":SaveLog:comp_name" + comp_name + "#" + comp_ip);
            cat.error(":SaveLog:logModule" + logModule + "#" + log_time + "#" + logAction);
            cat.error(":SaveLog:Sql" + logSql);
            cat.error(":SaveLog:watch" + watch + "#" + memo);
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                    cat.error(":SaveLog:close Statement:" + Util.UNI2GBK(e.getMessage()));
                }
            }
        }
    }
}

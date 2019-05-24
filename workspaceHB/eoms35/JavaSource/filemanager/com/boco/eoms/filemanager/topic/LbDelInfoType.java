package com.boco.eoms.filemanager.topic;

/**
 * Created by IntelliJ IDEA.
 * User: lxl
 * Date: 2003-9-1
 * Time: 16:29:30
 * To change this template use Options | File Templates.
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LbDelInfoType {
    //成功
    public static final int MACRO_SUCCESS = 1;
    //失败
    public static final int MACRO_ERROR = -1;

    private String strMaxParentId = "";
    Connection m_con = null;

    public LbDelInfoType() {
        super();
    }

    public LbDelInfoType(Connection con) {
        m_con = con;
    }


    public int executeProcess(String[] strInputArray) throws SQLException, Exception {
        int iReturn = MACRO_ERROR;
        try {
            String strDleId = strInputArray[0];

            PreparedStatement pst = null;
            String strSql = "";

            m_con.setAutoCommit(false);
            if (strDleId != null && !strDleId.equals("")) {
                //删除主记录
                strSql = "DELETE FROM taw_file_mgr_topic WHERE topic_id= ? ";
                pst = m_con.prepareStatement(strSql);
                //System.out.println("SQL = "+strSql);
                pst.setString(1, strDleId);
                //System.out.println("strDleId = "+strDleId);
                pst.executeUpdate();
                pst.close();
                //删除权限
//                strSql = "delete from a_topic_typeprvlg where topic_id = ? ";
//                pst = m_con.prepareStatement(strSql);
//                //System.out.println("SQL = "+strSql);
//                pst.setString(1, strDleId);
//                //System.out.println("strDleId = "+strDleId);
//                pst.executeUpdate();
//                pst.close();
            }

            m_con.commit();

            iReturn = MACRO_SUCCESS;
        } catch (SQLException sqle) {
            m_con.rollback();
            sqle.printStackTrace();
            //System.out.println("SQLException"+sqle);
            throw sqle;
        } catch (Exception e) {
            m_con.rollback();
            e.printStackTrace();
            //System.out.println("Exception"+e);
            throw e;
        }
        return iReturn;
    }

    public String getMaxNo() {
        return strMaxParentId;
    }

}


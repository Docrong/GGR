package com.boco.eoms.filemanager.topic;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: lxl
 * Date: 2003-9-3
 * Time: 11:21:11
 * To change this template use Options | File Templates.
 */
public class LbUpdateInfoType {

    public LbUpdateInfoType() {
        super();
    }

    public LbUpdateInfoType(Connection con) {
        m_con = con;
    }

    //成功
    public static final int MACRO_SUCCESS = 1;
    //失败
    public static final int MACRO_ERROR = -1;
    //权限
    private String[] strPrivilegeArray = null;

    Connection m_con = null;

    public int executeProcess(String[] strParamArray) throws SQLException, Exception {
        int iReturn = MACRO_ERROR;
        try {
            String strDleId = strParamArray[4];

            PreparedStatement pst = null;
            String strSql = "";

            m_con.setAutoCommit(false);
            if (strParamArray != null && strParamArray.length > 0) {
                //更改信息
                strSql = "UPDATE taw_file_mgr_topic " +
                        "SET topic_name=? " +
                        ", topic_path=? " +
                        ", topic_memo=? " +
                        ", topic_order=? " +
                        ", topic_type=? " +
                        ", topic_type_name=? " +
                        "WHERE topic_id = ? ";
                pst = m_con.prepareStatement(strSql);
                //System.out.println("SQL = "+strSql);
                if (strParamArray != null && strParamArray.length > 0) {
                    for (int i = 0; i < strParamArray.length; i++) {

                        //System.out.println("param["+i+"] = "+strParamArray[i]);
                        pst.setString(i + 1, strParamArray[i]);
                    }
                }
                pst.executeUpdate();
                pst.close();
                //删除权限
//                strSql = "delete from  a_topic_typeprvlg where topic_id = ? ";
//                pst = m_con.prepareStatement(strSql);
//                //System.out.println("SQL = "+strSql);
//                pst.setString(1, strDleId);
//                //System.out.println("strDleId = "+strDleId);
//                pst.executeUpdate();
//                pst.close();
                //增加权限
//System.out.println("SQL =增加权限 " );
//                if (strPrivilegeArray != null && strPrivilegeArray.length > 0) {
//                    for (int i = 0; i < strPrivilegeArray.length; i++) {
//                        int nIndex = strPrivilegeArray[i].indexOf(",");
//                        String strGroupId = strPrivilegeArray[i].substring(0, nIndex);
//                        String strPri = strPrivilegeArray[i].substring(nIndex + 1, strPrivilegeArray[i].length());
//                        strSql = "insert into a_topic_typeprvlg(topic_id,user_grp_id,user_id,privilege)values(?,?,'0',?)";
//                        pst = m_con.prepareStatement(strSql);
////System.out.println("SQL = "+strSql);
//                        pst.setString(1, strDleId);
//                        pst.setString(2, strGroupId);
//                        pst.setString(3, strPri);
////System.out.println("param 1 ="+strDleId);
////System.out.println("param 2 ="+strGroupId);
////System.out.println("param 3 ="+strPri);
//                        pst.executeUpdate();
//                        pst.close();
//                    }
//                }
/*
			strSql="insert into  a_topic_typeprvlg(?,?,?,?) values(?,?,?,?)";
			pst = this.getPreparedStmt(strSql);
			//System.out.println("SQL = "+strSql);

			pst.executeUpdate();
			pst.close();
            */
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

    /**
     * 得到权限数组
     */
    public void setPrivilege(String[] strArray) {
        this.strPrivilegeArray = strArray;
    }

}

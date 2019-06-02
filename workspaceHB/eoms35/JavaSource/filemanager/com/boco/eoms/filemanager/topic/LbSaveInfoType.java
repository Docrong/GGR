package com.boco.eoms.filemanager.topic;


import com.boco.eoms.common.resource.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: lxl
 * Date: 2003-9-3
 * Time: 11:21:11
 * To change this template use Options | File Templates.
 */
public class LbSaveInfoType {
    //成功
    public static final int MACRO_SUCCESS = 1;
    //失败
    public static final int MACRO_ERROR = -1;
    //InfoTypeId
    private String strInfoTypeId = "";
    //权限
    private String[] strPrivilegeArray = null;
    Connection m_con = null;

    public LbSaveInfoType() {
        super();
    }

    public LbSaveInfoType(Connection con) {
        m_con = con;
    }

    public int executeProcess(String[] strParamArray) throws SQLException, Exception {
        int iReturn = MACRO_ERROR;
        try {
            PreparedStatement pst = null;
            String strSql = "";

            m_con.setAutoCommit(false);
            if (strParamArray != null && strParamArray.length > 0) {
                //更改信息
                strSql = "INSERT INTO taw_file_mgr_topic (topic_type,topic_type_name,par_topic_id,topic_name,topic_memo,topic_path,topic_order,class_id,topic_id) VALUES ( ?,?,?, ? , ? , ? , ? , ?,? )";
                pst = m_con.prepareStatement(strSql);
                //System.out.println("SQL = "+strSql);
                if (strParamArray != null && strParamArray.length > 0) {
                    for (int i = 0; i < strParamArray.length; i++) {

                        //System.out.println("param["+i+"] = "+strParamArray[i]);
                        pst.setString(i + 1, strParamArray[i]);
                    }
                }
                strInfoTypeId=Util.getSequenceId(m_con,"taw_file_mgr_topic","topic_id");
                pst.setString(9,strInfoTypeId);
                pst.executeUpdate();
                pst.close();
//查询得到Id
//                LbSelectInfoTypeId objSelId = new LbSelectInfoTypeId(m_con);
//                objSelId.executeProcess(strParamArray);
//                HashMap hmId = objSelId.getResult();
//                Vector vTemp1 = (Vector) hmId.get("topic_id");
//                String[] strTopicId = LBCommonFunc.vector2StringArray(vTemp1);
//objForm.setHdId(strTopicId[0]);
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
//                        pst.setString(1, strInfoTypeId);
//                        pst.setString(2, strGroupId);
//                        pst.setString(3, strPri);
////System.out.println("param 1 ="+strInfoTypeId);
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
     * 返回插入的信息Id
     */
    public String getInfoTypeId() {
        return strInfoTypeId;
    }

    /**
     * 得到权限数组
     */
    public void setPrivilege(String[] strArray) {
        this.strPrivilegeArray = strArray;
    }

}

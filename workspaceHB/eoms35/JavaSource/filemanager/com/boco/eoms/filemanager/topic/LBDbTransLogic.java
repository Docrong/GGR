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

public abstract class LBDbTransLogic {
    //成功
    public static final int MACRO_SUCCESS = 1;
    //失败
    public static final int MACRO_ERROR = -1;
    //判断是否为删除,如果为删除则不需要setSql()
    public boolean bDelFalg = false;
    //要要执行的SQL文
    String m_strSql = "";
    Connection m_con = null;

    public LBDbTransLogic() {
        super();
    }

    public LBDbTransLogic(Connection con) {
        m_con = con;
    }

    //得到SQL文
    public abstract void setSql();

    public int executeProcess(String[] strParamArray) throws SQLException, Exception {
        int iReturn = MACRO_ERROR;
        try {
            m_con.setAutoCommit(false);
            PreparedStatement pst = null;
            if (!bDelFalg) {
                setSql();
            }
            //更新数据
            pst = m_con.prepareStatement(m_strSql);

            //System.out.println("SQL = "+m_strSql);
            if (strParamArray != null && strParamArray.length > 0) {
                for (int i = 0; i < strParamArray.length; i++) {

                    //System.out.println("param["+i+"] = "+strParamArray[i]);
                    pst.setString(i + 1, strParamArray[i]);
                }
            }
            pst.executeUpdate();
            pst.close();
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

}


/**
 * com.boco.eoms.commons.db.test.ContainerPoolTest
 */
package com.boco.eoms.commons.db.test;

// java standard library
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;

// java extend library
import javax.naming.Context;

// eoms class
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.db.containerpool.WebLogicDataSource;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class ContainerPoolTest {

    private WebLogicDataSource m_objWDS = null;

    public void initParams() {
        m_objWDS = new WebLogicDataSource();

        String _strInitContext = "weblogic.jndi.WLInitialContextFactory";
        String _strProviderURL = "t3://10.0.6.4:7001";
        //String _strUserPassword = "weblogic";

        m_objWDS.setAParam(Context.INITIAL_CONTEXT_FACTORY, _strInitContext);
        m_objWDS.setAParam(Context.PROVIDER_URL, _strProviderURL);
        //m_objWDS.setAParam(Context.SECURITY_PRINCIPAL, _strUserPassword);
        //m_objWDS.setAParam(Context.SECURITY_CREDENTIALS, _strUserPassword);

        //m_objWDS.setDatasourceKey("jdbc/myOracleTrans");
        m_objWDS.setDatasourceKey("tstOracle");

        try {
            m_objWDS.init();
        }
        catch (SQLException e) {
            BocoLog.error(this, "Fail to execute m_objWDS.init(), error message is: ["
                    + e.getMessage() + "]");
        }
    }

    public void printContent(Connection _Conn) {
        Statement _objStmt = null;
        ResultSet _objRest = null;

        try {
            _objStmt = _Conn.createStatement();
        }
        catch (SQLException e) {
            BocoLog.error(this, "Fail to execute _Conn.createStatement(), error message is: ["
                    + e.getMessage() + "]");
        }

        String _strSQL = "select * from boco_trans_1";
        try {
            _objRest = _objStmt.executeQuery(_strSQL);
        }
        catch (SQLException e) {
            BocoLog.error(this, "Fail to execute _objStmt.executeQuery(_strSQL), error message is: ["
                    + e.getMessage() + "]");
        }

        try {
            while (_objRest.next()) {
                BocoLog.info(this, "au_id: [" +_objRest.getString("au_id")+ "]");
                BocoLog.info(this, "au_lname: [" +_objRest.getString("au_lname")+ "]");
                BocoLog.info(this, "au_fname: [" +_objRest.getString("au_fname")+ "]");
                BocoLog.info(this, "------------");
            }
        }
        catch (SQLException e) {
            BocoLog.error(this, "Fail to get result of quering, error message is: ["
                    + e.getMessage() + "]");
        }
        finally {
            if (_objRest != null) {
                try {
                    _objRest.close();
                }
                catch (SQLException e) {
                    BocoLog.error(this, "Fail to execute _objRest.close(), error message is: ["
                            + e.getMessage() + "]");
                }
            }

            if (_objStmt != null) {
                try {
                    _objStmt.close();
                }
                catch (SQLException e) {
                    BocoLog.error(this, "Fail to execute _objStmt.close(), error message is: ["
                            + e.getMessage() + "]");
                }
            }
        }

    }

    /**
     *  
     * @param args
     */
    public static void main(String[] args) {
        Connection _objConn = null;
        ContainerPoolTest _objTest = new ContainerPoolTest();
        _objTest.initParams();

        try {
            _objConn = _objTest.m_objWDS.getConnection();
        }
        catch (SQLException e) {
            BocoLog
                    .error(null, "Fail to execute _ojbTest.m_objWDS.getConnection(), error message is: ["
                            + e.getMessage() + "]");
        }
        finally {
            if (_objConn != null) {
                try {
                    _objConn.close();
                }
                catch (SQLException e) {
                    BocoLog
                    .error(null, "Fail to execute _objConn.close(), error message is: ["
                            + e.getMessage() + "]");
                }
            }
        }
    }

}

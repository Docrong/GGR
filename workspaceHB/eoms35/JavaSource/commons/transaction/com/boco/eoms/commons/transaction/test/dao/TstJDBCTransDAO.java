/**
 * 
 */
package com.boco.eoms.commons.transaction.test.dao;

// java standard library
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

// apache library
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// eoms library
import com.boco.eoms.commons.transaction.dao.JDBCTransDAO;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class TstJDBCTransDAO extends JDBCTransDAO {

    private static Log log;

    /**
     * @param conn,
     *            java.sql.Connection
     */
    public TstJDBCTransDAO(Connection conn) {
        super(conn);
    }

    /**
     * Execute one SQL Clause which is identified by input String parameter.
     * 
     * @param strSQL, 
     * @return int, 
     */
    public int executeSQL(String strSQL) {
        int _iReturn = -10001;

        Statement _objStmt = null;
        try {
            _objStmt = m_objConn.createStatement();
        }
        catch (SQLException e) {
            if (log.isErrorEnabled()) {
                log
                        .error("Fail to execute m_objConn.createStatement(), error message is: ["
                                + e.getMessage() + "]");
            }
        }

        String _strSQL = strSQL;
        try {
            _objStmt.execute(_strSQL);
            _iReturn = 10001;
        }
        catch (SQLException e) {
            if (log.isErrorEnabled()) {
                log
                        .error("Fail to execute _objStmt.execute(...), error message is: ["
                                + e.getMessage() + "]");
            }
        }

        return _iReturn;
    }

    static {
        log = LogFactory
                .getLog(com.boco.eoms.commons.transaction.test.dao.TstJDBCTransDAO.class);
    }
}

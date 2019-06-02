/**
 * 
 */
package com.boco.eoms.commons.transaction.dao;

// java standard library
import java.sql.Connection;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class JDBCTransDAO {

    // properties block
    protected Connection m_objConn = null;

    /**
     * minimal constructor
     * 
     * @param conn
     */
    public JDBCTransDAO(Connection conn) {
        m_objConn = conn;
    }

}

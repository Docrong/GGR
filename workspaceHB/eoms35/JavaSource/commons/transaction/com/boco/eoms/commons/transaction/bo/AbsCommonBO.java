/**
 * com.boco.eoms.commons.transaction.bo.AbsCommonBO
 */
package com.boco.eoms.commons.transaction.bo;

// java standard library
import java.sql.Connection;

// hibernate library
import org.hibernate.Session;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class AbsCommonBO {

    // Properties block

    private Connection m_objConn;

    private Session m_objSession;

    // Functions block

    /**
     * default constructor
     */
    public AbsCommonBO() {
        this(null, null);
    }

    /**
     * @param conn
     * @param session
     */
    public AbsCommonBO(Connection conn, Session session) {
        m_objConn = conn;
        m_objSession = session;
    }

    /**
     * @return the m_objConn
     */
    public Connection getM_objConn() {
        return m_objConn;
    }

    /**
     * @param conn the m_objConn to set
     */
    public void setM_objConn(Connection conn) {
        m_objConn = conn;
    }

    /**
     * @return the m_objSession
     */
    public Session getM_objSession() {
        return m_objSession;
    }

    /**
     * @param session the m_objSession to set
     */
    public void setM_objSession(Session session) {
        m_objSession = session;
    }
    
}

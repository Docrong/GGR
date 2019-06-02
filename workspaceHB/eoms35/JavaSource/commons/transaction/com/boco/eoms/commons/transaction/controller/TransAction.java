/**
 * com.boco.eoms.commons.transaction.controller.TransAction
 */
package com.boco.eoms.commons.transaction.controller;

// java standard library
import java.sql.Connection;
import java.sql.SQLException;

// hibernate library
import org.hibernate.HibernateException;
import org.hibernate.Session;

// java extend library
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// apache struts library
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// eoms library
import com.boco.eoms.commons.db.bocopool.BocoConnection;
import com.boco.eoms.commons.db.util.HibernateUtil;
import com.boco.eoms.commons.transaction.util.ITransConstants;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class TransAction extends Action {

    // properties block

    private static Log log;

    protected boolean m_bNeedTransaction;

    protected boolean m_bNeedConnection;

    protected boolean m_bNeedSession;

    protected Connection m_objConn;

    protected Session m_objSession;

    // functions block

    /**
     * default constructor
     */
    public TransAction() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // initialize parameters for connection and hibernate session
        initEnvParams();
        // initialize autocommit
        initAutoCommit();

        // initialize connection and session accroding to m_bNeedConnection and
        // m_bNeedSession
        int _iLogicResult = ITransConstants.LOGIC_FAILED; // failure
        if (initDBConnection() && initHibernateSession()) {
            if (m_bNeedTransaction) { // need transaction control
                if (beginTransaction()) {
                    _iLogicResult = executeProcess(mapping, form, request,
                            response, m_objConn, m_objSession);

                    if (_iLogicResult > 0) { // executeProcess(...) success
                        if (!commitTransaction()) {
                            _iLogicResult = ITransConstants.COMMITTRANS_ERROR; // failed
                            if (log.isDebugEnabled()) {
                                log
                                        .debug("Fail to execute commitTransaction()!");
                            }
                        }
                    }
                    else { // executeProcess(...) failed
                        if (!rollbackTransaction()) {
                            _iLogicResult = ITransConstants.ROLLBACKTRANS_ERROR; // failure
                            if (log.isDebugEnabled()) {
                                log
                                        .debug("Fail to execute rollbackTransaction()!");
                            }
                        }
                    }
                }
                else {
                    _iLogicResult = ITransConstants.BEGINTRANS_ERROR; // failure
                    if (log.isDebugEnabled()) {
                        log.debug("Fail to execute beginTransaction()!");
                    }
                }
            }
            else { // don't need transaction control
                this.m_objConn.setAutoCommit(true);
                _iLogicResult = executeProcess(mapping, form, request,
                        response, m_objConn, m_objSession);
            }
        }

        // close session and connection
        closeSessionAndConnection();

        return null;
        // return forwardProcess(mapping, form, request, response,
        // _iLogicResult);
    }

    /**
     * Do business logic.
     */
    protected int executeProcess(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response,
            Connection _objConn, Session _objSession) throws Exception {
        return 1;
    }

    /**
     * Forward which client page as response, according to _iLogicResult input
     * parameter.
     */
    protected ActionForward forwardProcess(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response, int _iLogicResult) throws Exception {

        return mapping
                .findForward(_iLogicResult < 0 ? "ERROR_PAGE" : "Default");
    }

    /**
     * Set current connection's autoCommit property is false, in order to
     * support explicit transaction management.
     * 
     * @return boolean, if return true, that mean begin transaction
     *         successfully, else begin transaction failure.
     */
    protected boolean beginTransaction() {
        boolean _bReturn = false;
        boolean _bAutoCommit = true;

        if (m_bNeedSession) {
            try {
                HibernateUtil.currentTransaction(m_objConn);
                _bReturn = true;
            }
            catch (HibernateException e) {
                if (log.isErrorEnabled()) {
                    log
                            .error("Fail to execute HibernateUtil.currentTransaction(m_objConn), error message is: ["
                                    + e.getMessage() + "]");
                }
            }

            if (log.isInfoEnabled()) {
                log
                        .info("Execute HibernateUtil.currentTransaction(m_objConn) Successfully!");
            }
            return _bReturn;
        }

        try {
            _bAutoCommit = m_objConn.getAutoCommit();
        }
        catch (SQLException e) {
            if (log.isErrorEnabled()) {
                log
                        .error("Fail to execute Connection.getAutoCommit(), error message is: ["
                                + e.getMessage() + "]");
            }
            return _bReturn;
        }

        if (_bAutoCommit) {
            try {
                m_objConn.setAutoCommit(false);
                _bReturn = true;

                if (log.isDebugEnabled()) {
                    log
                            .debug("Execute Connection.setAutoCommit(false) successfully!");
                }
            }
            catch (SQLException e) {
                if (log.isErrorEnabled()) {
                    log
                            .error("Fail to execute Connection.setAutoCommit(false), error message is: ["
                                    + e.getMessage() + "]");
                }
                return _bReturn;
            }
        }
        else {
            _bReturn = true;
        }

        return _bReturn;
    }

    /**
     * Commit transaction on current database connection.
     * 
     * @return boolean, if succussfully execute database connection commit, will
     *         return true, else false.
     */
    protected boolean commitTransaction() {
        boolean _bReturn = false;

        if (m_bNeedSession) {
            try {
                HibernateUtil.commitTransaction();
                _bReturn = true;
            }
            catch (HibernateException e) {
                if (log.isErrorEnabled()) {
                    log
                            .error("Fail to execute HibernateUtil.commitTransaction(), error message is: ["
                                    + e.getMessage() + "]");
                }
            }

            if (log.isInfoEnabled()) {
                log
                        .info("Execute HibernateUtil.commitTransaction() Successfully!");
            }
            return _bReturn;
        }

        try {
            m_objConn.commit();
            _bReturn = true;
        }
        catch (SQLException e) {
            if (log.isErrorEnabled()) {
                log
                        .error("Fail to execute m_objConn.commit(), error message is: ["
                                + e.getMessage() + "]");
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("Execute m_objConn.commit() successfully!");
        }
        return _bReturn;
    }

    /**
     * Rollback transaction on current database connection.
     * 
     * @return boolean, if successfully execute connection's rollback function,
     *         will return true, else false
     */
    protected boolean rollbackTransaction() {
        boolean _bReturn = false;

        if (m_bNeedSession) {
            try {
                HibernateUtil.rollbackTransaction();
                _bReturn = true;
            }
            catch (HibernateException e) {
                if (log.isErrorEnabled()) {
                    log
                            .error("Fail to execute HibernateUtil.rollbackTransaction(), error message is: ["
                                    + e.getMessage() + "]");
                }
            }

            if (log.isInfoEnabled()) {
                log
                        .info("Execute HibernateUtil.rollbackTransaction() Successfully!");
            }
            return _bReturn;
        }

        try {
            m_objConn.rollback();
            _bReturn = true;
        }
        catch (SQLException e) {
            if (log.isErrorEnabled()) {
                log
                        .error("Fail to execute m_objConn.rollback(), error message is: ["
                                + e.getMessage() + "]");
            }
        }

        if (log.isDebugEnabled()) {
            log.debug("Execute m_objConn.rollback() successfully!");
        }
        return _bReturn;
    }

    /**
     * Config m_bNeedTransaction property to control wether autocommit on
     * current database connection.
     */
    protected void initAutoCommit() {
        m_bNeedTransaction = true;
    }

    /**
     * According to input parameters, this function decide wether initialize
     * database connection and hibernate session instance or not. If initialize
     * successfully, then return true, else false.
     */
    protected void initEnvParams() {
        this.m_bNeedConnection = true;
        this.m_bNeedSession = false;
    }

    /**
     * Initialize database connection accroding to m_bNeedConnection property
     * 
     * @return boolean,
     */
    protected boolean initDBConnection() {
        boolean _bReturn = false;

        if (m_bNeedConnection) {
            m_objConn = HibernateUtil.getConnection();
            if (m_objConn != null) {
                _bReturn = true;
                if (log.isDebugEnabled()) {
                    log
                            .debug("Execute HibernateUtil.getConnection() successfully!");
                }
            }
            else {
                if (log.isDebugEnabled()) {
                    log.debug("HibernateUtil.getConnection() return null!");
                }
            }
        }
        else {
            _bReturn = true;
            if (log.isDebugEnabled()) {
                log.debug("Don't need database connection!");
            }
        }

        return _bReturn;
    }

    /**
     * Initialize hibernate session accroding to m_bNeedSession property
     * 
     * @return boolean,
     */
    protected boolean initHibernateSession() {
        boolean _bReturn = false;

        if (m_bNeedSession) {
            try {
                m_objSession = HibernateUtil.currentSession(m_objConn);
                if (m_objSession != null) {
                    _bReturn = true;
                    if (log.isDebugEnabled()) {
                        log
                                .debug("Execute HibernateUtil.currentSession(...) Successfully!");
                        log.debug("Session: [" + m_objSession.toString() + "]");
                    }
                }
                else {
                    if (log.isDebugEnabled()) {
                        log
                                .debug("HibernateUtil.currentSession(...) return null!");
                    }
                }
            }
            catch (HibernateException e) {
                if (log.isErrorEnabled()) {
                    log
                            .error("Fail to execute HibernateUtil.currentSession(...), error message is: ["
                                    + e.getMessage() + "]");
                }
            }
        }
        else {
            _bReturn = true;
            if (log.isDebugEnabled()) {
                log.debug("Don't need Hibernate Session!");
            }
        }

        return _bReturn;
    }

    protected void closeSessionAndConnection() {
        try {
            HibernateUtil.closeSession();
        }
        catch (HibernateException e) {
            if (log.isErrorEnabled()) {
                log
                        .error("Fail to execute HibernateUtil.closeSession(), error message is: ["
                                + e.getMessage() + "]");
            }
        }

        if (m_objConn != null) {
            ((BocoConnection) m_objConn).close();
        }

        m_objConn = null;
        m_objSession = null;
    }

    static {
        log = LogFactory
                .getLog(com.boco.eoms.commons.transaction.controller.TransAction.class);
    }

}

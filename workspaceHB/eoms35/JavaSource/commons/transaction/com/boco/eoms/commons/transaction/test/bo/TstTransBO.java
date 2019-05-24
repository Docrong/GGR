/**
 * 
 */
package com.boco.eoms.commons.transaction.test.bo;

// java standard library
import java.sql.Connection;

// hibernate library
import org.hibernate.HibernateException;
import org.hibernate.Session;

// apache library
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// eoms library
import com.boco.eoms.commons.transaction.bo.AbsCommonBO;
import com.boco.eoms.commons.transaction.test.model.ApplyDetail;
import com.boco.eoms.commons.transaction.test.dao.TstHibernateDAO;
import com.boco.eoms.commons.transaction.test.dao.TstJDBCTransDAO;

/**
 * @author Sandy.wei
 * @version 3.5
 */
public class TstTransBO extends AbsCommonBO {

    private static Log log;

    /**
     * @param conn,
     *            java.sql.Connection
     * @param session,
     *            net.sf.hibernate.Session
     */
    public TstTransBO(Connection conn, Session session) {
        super(conn, session);
    }

    /**
     * @see 使用JDBC对trans_test_01表进行先删除后插入操作。
     */
    public int execOnlyJDBCDAO() {
        int _iReturn = -10001;

        String _strSQL1 = "delete from trans_test_01 where id > 10";
        String _strSQL2 = "insert into trans_test_01(name, sex) values('aaa', 'nan')";
        TstJDBCTransDAO _objJDBCTransDAO = new TstJDBCTransDAO(this
                .getM_objConn());
        int _iResult1 = _objJDBCTransDAO.executeSQL(_strSQL1);
        int _iResult2 = _objJDBCTransDAO.executeSQL(_strSQL2);

        if ((_iResult1 > 0) && (_iResult2 > 0)) {
            _iReturn = _iResult1;
        }

        return _iReturn;
    }

    /**
     * @see 使用Hibernate对trans_test_02表进行连续插入操作。
     */
    public int execOnlyHibernateDAO() {
        int _iReturn = -10001;

        ApplyDetail _obj1 = new ApplyDetail();
        ApplyDetail _obj2 = new ApplyDetail();
        _obj1.setName("bbb");
        _obj1.setRemark("is bbb");
        _obj2.setName("ccc");
        _obj2.setRemark("is ccc");
        TstHibernateDAO _objHibernateDAO = new TstHibernateDAO(this
                .getM_objSession());
        try {
            _objHibernateDAO.save(_obj1);
        }
        catch (HibernateException e) {
            if (log.isErrorEnabled()) {
                log
                        .error("Fail to execute _objHibernateDAO.save(_obj1), error message is: ["
                                + e.getMessage() + "]");
            }
            return _iReturn;
        }

        try {
            _objHibernateDAO.save(_obj2);
            _iReturn = 10001;
        }
        catch (HibernateException e) {
            if (log.isErrorEnabled()) {
                log
                        .error("Fail to execute _objHibernateDAO.save(_obj2), error message is: ["
                                + e.getMessage() + "]");
            }
        }

        return _iReturn;
    }

    /**
     * @see 首先使用Hibernate对trans_test_02表进行插入操作，然后使用JDBC对trans_test_01
     * @see 进行插入操作。 
     */
    public int execHibernateAndJDBC() {
        int _iReturn = -10001;

        ApplyDetail _obj1 = new ApplyDetail();
        _obj1.setName("bbb");
        _obj1.setRemark("is bbb");
        TstHibernateDAO _objHibernateDAO = new TstHibernateDAO(this
                .getM_objSession());
        try {
            _objHibernateDAO.save(_obj1);
        }
        catch (HibernateException e) {
            if (log.isErrorEnabled()) {
                log
                        .error("Fail to execute _objHibernateDAO.save(_obj1), error message is: ["
                                + e.getMessage() + "]");
            }
            return _iReturn;
        }

        String _strSQL2 = "insert into trans_test_01(name, sex) values('aaa', 'nan')";
        TstJDBCTransDAO _objJDBCTransDAO = new TstJDBCTransDAO(this
                .getM_objConn());
        int _iResult2 = _objJDBCTransDAO.executeSQL(_strSQL2);

        if (_iResult2 > 0) {
            _iReturn = _iResult2;
        }

        return _iReturn;
    }

    /**
     * @see 首先使用JDBC对trans_test_01表进行插入操作，然后使用Hibernate对trans_test_02
     * @see 进行插入操作。 
     */
    public int execJDBCAndHibernate() {
        int _iReturn = -10001;

        String _strSQL1 = "insert into trans_test_01(name, sex) values('aaa', 'nan')";
        TstJDBCTransDAO _objJDBCTransDAO = new TstJDBCTransDAO(this
                .getM_objConn());
        int _iResult2 = _objJDBCTransDAO.executeSQL(_strSQL1);

        if (_iResult2 > 0) {
            ApplyDetail _obj2 = new ApplyDetail();
            _obj2.setName("bbb");
            _obj2.setRemark("is bbb");
            TstHibernateDAO _objHibernateDAO = new TstHibernateDAO(this
                    .getM_objSession());
            try {
                _objHibernateDAO.save(_obj2);
                _iReturn = 10001;
            }
            catch (HibernateException e) {
                if (log.isErrorEnabled()) {
                    log
                            .error("Fail to execute _objHibernateDAO.save(_obj2), error message is: ["
                                    + e.getMessage() + "]");
                }
            }
        }

        return _iReturn;
    }

    /**
     * @see 首先使用Hibernate对trans_test_02表进行插入，然后再进行查询，最后进行修改操作。
     */
    public int execSimulation_01(String _strName, String _strRemark) {
        int _iReturn = -10001;
        ApplyDetail _obj1 = new ApplyDetail();
        _obj1.setName(_strName);
        _obj1.setRemark(_strRemark);

        TstHibernateDAO _objHibernateDAO = new TstHibernateDAO(this
                .getM_objSession());
        try {
            _objHibernateDAO.save(_obj1);
        }
        catch (HibernateException e) {
            if (log.isErrorEnabled()) {
                log
                        .error("Fail to execute _objHibernateDAO.save(_obj1), error message is: ["
                                + e.getMessage() + "]");
            }
            return _iReturn;
        }

        String _strQuery = "from ApplyDetail where name='";
        _strQuery += _strName;
        _strQuery += "'";
        ApplyDetail _obj1_1 = null;
        try {
            _obj1_1 = (ApplyDetail) _objHibernateDAO.query(_strQuery).get(0);
        }
        catch (HibernateException e) {
            log.error("Query operation: [" + e.getMessage() + "]");
            return _iReturn;
        }

        _obj1_1.setRemark(_strRemark + "_forupdate");
        try {
            _objHibernateDAO.saveOrUpdate(_obj1_1);
            _iReturn = 10001;
        }
        catch (HibernateException e) {
            log.error("saveOrUpdate operation: [" + e.getMessage() + "]");
        }

        return _iReturn;
    }

    static {
        log = LogFactory
                .getLog(com.boco.eoms.commons.transaction.test.bo.TstTransBO.class);
    }

}

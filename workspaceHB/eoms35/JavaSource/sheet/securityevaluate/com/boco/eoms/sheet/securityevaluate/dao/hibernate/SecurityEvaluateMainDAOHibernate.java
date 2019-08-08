
package com.boco.eoms.sheet.securityevaluate.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.model.BaseMain;

import com.boco.eoms.sheet.securityevaluate.dao.ISecurityEvaluateMainDAO;
import com.boco.eoms.sheet.securityevaluate.model.SecurityEvaluateMain;

public class SecurityEvaluateMainDAOHibernate extends MainDAO implements ISecurityEvaluateMainDAO {

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePO(java.lang.String)
     */
    public BaseMain loadSinglePO(String id) throws HibernateException {
        SecurityEvaluateMain main = (SecurityEvaluateMain) getHibernateTemplate().get(SecurityEvaluateMain.class, id);
        return main;
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePOByProcessId(java.lang.String)
     */
    public BaseMain loadSinglePOByProcessId(String processId)
            throws HibernateException {
        SecurityEvaluateMain main = new SecurityEvaluateMain();
        String sql = " from SecurityEvaluateMain as main where main.piid ='"
                + processId + "'";
        List list = getHibernateTemplate().find(sql);
        if (list.size() == 0) {
            main = null;
        } else {
            main = (SecurityEvaluateMain) list.get(0);
        }
        return main;
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#deleteMain(java.lang.String)
     */
    public void deleteMain(String id) throws HibernateException {
        SecurityEvaluateMain main = (SecurityEvaluateMain) getHibernateTemplate().get(SecurityEvaluateMain.class, id);
        main.setDeleted(new Integer(1));
        getHibernateTemplate().save(main);
    }

    /**
     * @see com.boco.eoms.base.dao.Dao#saveObject(java.lang.Object)
     */
    public void save(Object o) {
        getHibernateTemplate().save(o);
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
     */
    public String getMainName() {
        // TODO Auto-generated method stub
        return "SecurityEvaluateMain";
    }

}


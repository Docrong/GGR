
package com.boco.eoms.sheet.securitydeal.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.model.BaseMain;

import com.boco.eoms.sheet.securitydeal.dao.ISecurityDealMainDAO;
import com.boco.eoms.sheet.securitydeal.model.SecurityDealMain;

public class SecurityDealMainDAOHibernate extends MainDAO implements ISecurityDealMainDAO {

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePO(java.lang.String)
     */
    public BaseMain loadSinglePO(String id) throws HibernateException {
        SecurityDealMain main = (SecurityDealMain) getHibernateTemplate().get(SecurityDealMain.class, id);
        return main;
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePOByProcessId(java.lang.String)
     */
    public BaseMain loadSinglePOByProcessId(String processId)
            throws HibernateException {
        SecurityDealMain main = new SecurityDealMain();
        String sql = " from SecurityDealMain as main where main.piid ='"
                + processId + "'";
        List list = getHibernateTemplate().find(sql);
        if (list.size() == 0) {
            main = null;
        } else {
            main = (SecurityDealMain) list.get(0);
        }
        return main;
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#deleteMain(java.lang.String)
     */
    public void deleteMain(String id) throws HibernateException {
        SecurityDealMain main = (SecurityDealMain) getHibernateTemplate().get(SecurityDealMain.class, id);
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
        return "SecurityDealMain";
    }

}


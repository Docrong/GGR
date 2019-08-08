
package com.boco.eoms.sheet.emergencydrill.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.model.BaseMain;

import com.boco.eoms.sheet.emergencydrill.dao.IEmergencyDrillMainDAO;
import com.boco.eoms.sheet.emergencydrill.model.EmergencyDrillMain;

public class EmergencyDrillMainDAOHibernate extends MainDAO implements IEmergencyDrillMainDAO {

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePO(java.lang.String)
     */
    public BaseMain loadSinglePO(String id) throws HibernateException {
        EmergencyDrillMain main = (EmergencyDrillMain) getHibernateTemplate().get(EmergencyDrillMain.class, id);
        return main;
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePOByProcessId(java.lang.String)
     */
    public BaseMain loadSinglePOByProcessId(String processId)
            throws HibernateException {
        EmergencyDrillMain main = new EmergencyDrillMain();
        String sql = " from EmergencyDrillMain as main where main.piid ='"
                + processId + "'";
        List list = getHibernateTemplate().find(sql);
        if (list.size() == 0) {
            main = null;
        } else {
            main = (EmergencyDrillMain) list.get(0);
        }
        return main;
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#deleteMain(java.lang.String)
     */
    public void deleteMain(String id) throws HibernateException {
        EmergencyDrillMain main = (EmergencyDrillMain) getHibernateTemplate().get(EmergencyDrillMain.class, id);
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
        return "EmergencyDrillMain";
    }

}


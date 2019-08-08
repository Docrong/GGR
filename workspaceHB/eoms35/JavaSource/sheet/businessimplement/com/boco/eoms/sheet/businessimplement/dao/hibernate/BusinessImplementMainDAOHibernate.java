
package com.boco.eoms.sheet.businessimplement.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.businessimplement.dao.IBusinessImplementMainDAO;
import com.boco.eoms.sheet.businessimplement.model.BusinessImplementMain;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;


public class BusinessImplementMainDAOHibernate extends MainDAO implements IBusinessImplementMainDAO {

    /**
     * @see com.boco.eoms.base.dao.Dao#saveObject(java.lang.Object)
     */
    public void save(Object o) {
        getHibernateTemplate().save(o);
    }

    /**
     * @see com.boco.eoms.base.dao.Dao#saveObject(java.lang.Object)
     */
    public void saveOrUpdate(Object o) {
        getHibernateTemplate().update(o);
    }

    /**
     * 通过定单号获取工单
     *
     * @param orderSheetId 定单号
     * @return
     * @throws HibernateException
     */
    public BaseMain getMainByOrderSheetId(String orderSheetId)
            throws HibernateException {
        BusinessImplementMain main = new BusinessImplementMain();
        String sql = "from BusinessImplementMain as main where main.orderSheetId ='"
                + orderSheetId + "'";
        List list = getHibernateTemplate().find(sql);
        if (list.size() == 0) {
            main = null;
        } else {
            main = (BusinessImplementMain) list.get(0);
        }
        return main;
    }
}


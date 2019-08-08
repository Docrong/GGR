/*
 * Created on 2008-4-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.commonfault.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.BaseSheetDaoHibernate;
import com.boco.eoms.sheet.commonfault.dao.ICommonFaultViSheetInfoDAO;
import com.boco.eoms.sheet.commonfault.model.CommonFaultViSheetInfo;

/**
 * @author IBM_USER
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CommonFaultViSheetInfoDAOHibernate extends BaseSheetDaoHibernate implements ICommonFaultViSheetInfoDAO {

    public CommonFaultViSheetInfo getCommonFaultViSheetInfoBymainId(String id) throws HibernateException {
        String hql = "from CommonFaultViSheetInfo where deleted=0 and mainId='" + id + "'";
        List list = this.getHibernateTemplate().find(hql);
        if (null != list && list.size() > 0) {
            return (CommonFaultViSheetInfo) list.get(0);
        }
        return null;
    }

    public void saveOrUpdate(CommonFaultViSheetInfo main) throws HibernateException {
        CommonFaultViSheetInfo baseMain = (CommonFaultViSheetInfo) main;
        if (baseMain.getId() == null) {
            getHibernateTemplate().save(baseMain);
            getHibernateTemplate().flush();
            getHibernateTemplate().clear();
        } else {
            getHibernateTemplate().flush();
            getHibernateTemplate().clear();
            getHibernateTemplate().saveOrUpdate(baseMain);
        }

    }

    public Object getObject(Class clazz, Serializable id) {
        // TODO Auto-generated method stub
        return null;
    }

    public List getObjects(Class clazz) {
        // TODO Auto-generated method stub
        return null;
    }

    public void removeObject(Class clazz, Serializable id) {
        // TODO Auto-generated method stub

    }

    public void saveObject(Object o) {
        // TODO Auto-generated method stub

    }

    public CommonFaultViSheetInfo getCommonFaultViSheetInfoByVisId(String mid, String vid) throws HibernateException {
        String hql = "from CommonFaultViSheetInfo where deleted=0 and mainId='" + mid + "' and visId='" + vid + "'";
        List list = this.getHibernateTemplate().find(hql);
        if (null != list && list.size() > 0) {
            return (CommonFaultViSheetInfo) list.get(0);
        }
        return null;
    }

}

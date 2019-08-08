/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.userdefinegroup.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.userdefinegroup.dao.IUserdefineGroupDAO;
import com.boco.eoms.sheet.userdefinegroup.service.IUserdefineGroupManager;


/**
 * @author panlong
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserdefineGroupManagerImpl implements IUserdefineGroupManager {

    private IUserdefineGroupDAO groupDao;


    public IUserdefineGroupDAO getGroupDao() {
        return groupDao;
    }

    public void setGroupDao(IUserdefineGroupDAO groupDao) {
        this.groupDao = groupDao;
    }

    public Object getObject(Class clazz, Serializable id) throws HibernateException {
        return groupDao.getObject(clazz, id);
    }

    public List getObjectsByCondtion(Integer curPage, Integer pageSize, int[] aTotal, Map condition, String queryNumber) throws HibernateException {

        String hql = " from UserdefineGroup ";
        String countHql = "select count(*) from UserdefineGroup ";
        String where = (String) condition.get("where");
        hql = hql + where;
        countHql = countHql + where;
        return groupDao.getObjectsByCondtion(curPage, pageSize, aTotal, hql, countHql, queryNumber);
    }

    public void removeObject(Class clazz, Serializable id) throws HibernateException {
        groupDao.removeObject(clazz, id);

    }

    public void saveObject(Object o) throws HibernateException {
        groupDao.saveObject(o);

    }

    public List getRolesByCondition(String where) throws HibernateException {
        String hql = " from TawSystemSubRole ";
        if (where != null && !where.equals("")) {
            hql = hql + where;
        }

        return groupDao.getObjectsByHql(hql);
    }

    public List getUserBySubRoleCondtion(String where) throws HibernateException {
        String hql = " from TawSystemUserRefRole ";
        if (where != null && !where.equals("")) {
            hql = hql + where;
        }

        return groupDao.getObjectsByHql(hql);
    }

    public List getDeptsByCondition(String where) throws HibernateException {
        String hql = " from TawSystemDept ";
        if (where != null && !where.equals("")) {
            hql = hql + where;
        }

        return groupDao.getObjectsByHql(hql);
    }

    public List getUsersByCondition(String where) throws HibernateException {
        String hql = " from TawSystemUser ";
        if (where != null && !where.equals("")) {
            hql = hql + where;
        }

        return groupDao.getObjectsByHql(hql);
    }

}

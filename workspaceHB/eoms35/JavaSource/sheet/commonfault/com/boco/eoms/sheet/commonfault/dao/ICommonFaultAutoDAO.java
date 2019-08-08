/*
 * Created on 2008-4-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.commonfault.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;

/**
 * @author TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ICommonFaultAutoDAO {

    public void saveObject(Object o) throws HibernateException;

    public Object getObject(Class clazz, Serializable id) throws HibernateException;

    public void removeObject(Class clazz, Serializable id) throws HibernateException;

    public List getObjectsByCondtion(Integer curPage, Integer pageSize, int[] aTotal, String hql, String countHql, String queryNubmer) throws HibernateException;

    public Object getMaxFilter(final String hql) throws HibernateException;

    //	增加查询操作措施的列表
    public List getSteps(final String hql) throws HibernateException;

}

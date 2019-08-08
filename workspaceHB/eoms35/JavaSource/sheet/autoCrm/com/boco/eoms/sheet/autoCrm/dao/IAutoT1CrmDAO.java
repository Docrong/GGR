package com.boco.eoms.sheet.autoCrm.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;

public interface IAutoT1CrmDAO {

    public void saveObject(Object o) throws HibernateException;

    public Object getObject(Class clazz, Serializable id) throws HibernateException;

    public void removeObject(Class clazz, Serializable id) throws HibernateException;

    public List getObjectsByCondtion(Integer curPage, Integer pageSize, int[] aTotal, String hql, String countHql, String queryNubmer) throws HibernateException;
}

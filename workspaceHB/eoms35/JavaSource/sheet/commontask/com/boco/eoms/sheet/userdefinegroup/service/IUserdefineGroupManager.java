/*
 * Created on 2007-8-29
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.userdefinegroup.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;


/**
 * @author panlong
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IUserdefineGroupManager {

    public void saveObject(Object o) throws HibernateException;

    public Object getObject(Class clazz, Serializable id) throws HibernateException;

    public void removeObject(Class clazz, Serializable id) throws HibernateException;

    public List getObjectsByCondtion(Integer curPage, Integer pageSize, int[] aTotal, Map condtion, String queryNumber) throws HibernateException;

    public List getRolesByCondition(String where) throws HibernateException;

    public List getUserBySubRoleCondtion(String where) throws HibernateException;

    public List getDeptsByCondition(String where) throws HibernateException;

    public List getUsersByCondition(String where) throws HibernateException;
}

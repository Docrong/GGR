/*
 * Created on 2007-8-29
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.datum.service;

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
public interface ITawBureaudataCityzoneManager {
    public String getTawSystemApplyID(String userid, String type) throws HibernateException;

    public Map getAllCityIntoMapKeyZoneNum() throws HibernateException;

    public Map getAllCityIntoMapKeyCityName() throws HibernateException;

    public void saveorUpdate(Object obj) throws HibernateException;

    public void removeObject(Class clazz, Serializable id) throws HibernateException;

    public Object findById(String id) throws HibernateException;

    public List findByAll() throws HibernateException;

    public Object findByUser(String userid) throws HibernateException;

    public Object findByDept(String deptid) throws HibernateException;
}

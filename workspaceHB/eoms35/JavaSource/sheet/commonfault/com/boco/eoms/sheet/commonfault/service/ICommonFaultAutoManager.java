/*
 * Created on 2007-8-29
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.commonfault.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.commonfault.model.CommonFaultAuto;


/**
 * @author panlong
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ICommonFaultAutoManager {

    public void saveObject(Object o) throws HibernateException;

    public Object getObject(Class clazz, Serializable id) throws HibernateException;

    public void removeObject(Class clazz, Serializable id) throws HibernateException;

    public List getObjectsByCondtion(Integer curPage, Integer pageSize, int[] aTotal, Map condtion, String queryNumber) throws HibernateException;

    public CommonFaultAuto getMaxFilter(Map map, String type, String autoType) throws HibernateException;

    //增加查询操作措施的列表
    public List getSteps() throws HibernateException;

    //增加查询符合归档条件的列表
    public List getStepsbycondition(String remark1, String commonFaultDesc) throws HibernateException;

}

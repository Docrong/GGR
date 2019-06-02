package com.boco.eoms.sheet.mofficedata.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataBuisType;

/**
 * <p>
 * Title:局数据工单流程
 * </p>
 * <p>
 * Description:局数据工单流程
 * </p>
 * <p>
 * Tue Mar 22 09:31:29 CST 2016
 * </p>
 * 
 * @author weichao
 * @version 3.5
 * 
 */

public interface IMofficeDataBuisTypeDAO extends Dao {
	public void saveOrUpdate(MofficeDataBuisType obj) throws HibernateException;

	public List getBuisTypeObjects()throws HibernateException;

	public List getBuisTypeObjectsByHql(String hql)throws HibernateException;

	public void clearData()throws HibernateException;
}
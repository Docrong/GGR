package com.boco.eoms.sheet.mofficedata.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataProMatch;

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

public interface IMofficeDataProMatchDAO extends Dao {
	public void saveOrUpdate(MofficeDataProMatch obj) throws HibernateException;

	public List getProMatchObjects(String mainId)throws HibernateException;

	public HashMap getProMatchsByCondition(String hql, Integer pageIndex, Integer pageSize)throws HibernateException;

	public List getProMatchObjectByCorreKey(String tkid)throws HibernateException;

	public List getProMatchObjectByPreLinkId(String prelinkId)throws HibernateException;

	public List getProMatchObjectById(String id)throws HibernateException;

	public boolean delObj(MofficeDataProMatch mo)throws HibernateException;

}
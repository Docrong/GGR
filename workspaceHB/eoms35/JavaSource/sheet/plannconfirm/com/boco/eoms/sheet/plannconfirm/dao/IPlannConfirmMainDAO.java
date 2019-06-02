package com.boco.eoms.sheet.plannconfirm.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.IMainDAO;

/**
 * <p>
 * Title:规划站址确认申请流程
 * </p>
 * <p>
 * Description:规划站址确认申请流程
 * </p>
 * <p>
 * Thu Jun 06 17:13:18 CST 2013
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public interface IPlannConfirmMainDAO extends IMainDAO  {
	 public abstract List getNumber(final String sendTimeStartDate,final String sendTimeEndDate,final String queryType) throws HibernateException;
	 
	 public abstract Map getDetail(final Integer curPage, final Integer pageSize, final String sendTimeStartDate,final String sendTimeEndDate) throws HibernateException;
 }
 




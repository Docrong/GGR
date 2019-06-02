package com.boco.eoms.sheet.plannadjust.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ILinkDAO;

/**
 * <p>
 * Title:规划站址调整申请流程
 * </p>
 * <p>
 * Description:规划站址调整申请流程
 * </p>
 * <p>
 * Sat Jun 08 11:16:09 CST 2013
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public interface IPlannAdjustLinkDAO extends ILinkDAO {
 	 public List getLinksBycondition(String condition, String linkName) throws HibernateException;
 }
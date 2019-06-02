package com.boco.eoms.sheet.maintenanceservice.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ILinkDAO;

/**
 * <p>
 * Title:维保服务审批
 * </p>
 * <p>
 * Description:维保服务审批
 * </p>
 * <p>
 * Thu Mar 16 15:48:02 CST 2017
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public interface IMaintenanceServiceLinkDAO extends ILinkDAO {
 	 public List getLinksBycondition(String condition, String linkName) throws HibernateException;
 }
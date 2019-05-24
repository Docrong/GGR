package com.boco.eoms.sheet.transprovincial.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ILinkDAO;

/**
 * <p>
 * Title:跨省集客业务工单
 * </p>
 * <p>
 * Description:跨省集客业务工单
 * </p>
 * <p>
 * Thu Sep 27 14:32:21 CST 2012
 * </p>
 * 
 * @author ph
 * @version 3.5
 * 
 */
 
 public interface ITransprovincialLinkDAO extends ILinkDAO {
 	 public List getLinksBycondition(String condition, String linkName) throws HibernateException;
 }
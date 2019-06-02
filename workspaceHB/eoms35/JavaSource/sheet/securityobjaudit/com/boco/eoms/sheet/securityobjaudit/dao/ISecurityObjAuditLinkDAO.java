package com.boco.eoms.sheet.securityobjaudit.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ILinkDAO;

/**
 * <p>
 * Title:安全对象问题审批工单
 * </p>
 * <p>
 * Description:安全对象问题审批工单
 * </p>
 * <p>
 * Tue Apr 25 11:41:14 CST 2017
 * </p>
 * 
 * @author liuyonggnag
 * @version 3.6
 * 
 */
 
 public interface ISecurityObjAuditLinkDAO extends ILinkDAO {
 	 public List getLinksBycondition(String condition, String linkName) throws HibernateException;
 }
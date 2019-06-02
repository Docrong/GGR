package com.boco.eoms.sheet.securityobjattribute.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ILinkDAO;

/**
 * <p>
 * Title:安全对象属性信息变更审批工单
 * </p>
 * <p>
 * Description:安全对象属性信息变更审批工单
 * </p>
 * <p>
 * Tue Apr 25 11:43:03 CST 2017
 * </p>
 * 
 * @author liuyonggnag
 * @version 3.6
 * 
 */
 
 public interface ISecurityObjAttributeLinkDAO extends ILinkDAO {
 	 public List getLinksBycondition(String condition, String linkName) throws HibernateException;
 }
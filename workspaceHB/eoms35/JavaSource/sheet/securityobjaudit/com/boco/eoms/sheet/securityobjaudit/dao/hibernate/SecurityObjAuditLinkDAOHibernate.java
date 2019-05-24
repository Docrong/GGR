package com.boco.eoms.sheet.securityobjaudit.dao.hibernate;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.securityobjaudit.dao.ISecurityObjAuditLinkDAO;
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
 
 public class SecurityObjAuditLinkDAOHibernate extends LinkDAO implements ISecurityObjAuditLinkDAO {
  		/**
	     * 根据条件查出所有的link对象
	     */
	    public List getLinksBycondition(String condition, String linkName) throws HibernateException {
	    	String hql = "from " + linkName + " where " + condition;
	    	return getHibernateTemplate().find(hql);	
	    }
 }
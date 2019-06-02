package com.boco.eoms.sheet.securityobjaudit.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.service.impl.LinkServiceImpl;
import com.boco.eoms.sheet.securityobjaudit.dao.ISecurityObjAuditLinkDAO;
import com.boco.eoms.sheet.securityobjaudit.service.ISecurityObjAuditLinkManager;

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
 
 public class SecurityObjAuditLinkManagerImpl extends LinkServiceImpl implements ISecurityObjAuditLinkManager {
  		/**
	     * 根据条件查出所有的link对象
	     */
	    public List getLinksBycondition(String condition) throws Exception {    	
	    	ISecurityObjAuditLinkDAO dao = (ISecurityObjAuditLinkDAO)this.getLinkDAO();
	        List list = dao.getLinksBycondition(condition, this.getLinkObject().getClass().getName());
	        return list;
	    }
 
 }
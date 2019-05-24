package com.boco.eoms.sheet.securityobjattribute.dao.hibernate;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.securityobjattribute.dao.ISecurityObjAttributeMainDAO;
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
 
 public class SecurityObjAttributeMainDAOHibernate extends MainDAO implements ISecurityObjAttributeMainDAO {
 	  
 	
	  
	   /* (non-Javadoc)
	    * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
	    */
	  public String getMainName() {
		return "SecurityObjAttributeMain";
	  }
 
 }
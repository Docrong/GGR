package com.boco.eoms.sheet.equipmentsecurityresponse.dao.hibernate;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.equipmentsecurityresponse.dao.IEquipmentSecurityResponseMainDAO;
/**
 * <p>
 * Title:设备互联核查安全告警响应工单
 * </p>
 * <p>
 * Description:设备互联核查安全告警响应工单
 * </p>
 * <p>
 * Tue Apr 25 11:45:21 CST 2017
 * </p>
 * 
 * @author liuyonggnag
 * @version 3.6
 * 
 */
 
 public class EquipmentSecurityResponseMainDAOHibernate extends MainDAO implements IEquipmentSecurityResponseMainDAO {
 	  
 	
	  
	   /* (non-Javadoc)
	    * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
	    */
	  public String getMainName() {
		return "EquipmentSecurityResponseMain";
	  }
 
 }
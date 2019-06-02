package com.boco.eoms.sheet.maintenanceservice.dao.hibernate;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.maintenanceservice.dao.IMaintenanceServiceMainDAO;
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
 
 public class MaintenanceServiceMainDAOHibernate extends MainDAO implements IMaintenanceServiceMainDAO {
 	  
 	
	  
	   /* (non-Javadoc)
	    * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
	    */
	  public String getMainName() {
		return "MaintenanceServiceMain";
	  }
 
 }
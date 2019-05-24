package com.boco.eoms.sheet.equipmentinstallation.dao.hibernate;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.equipmentinstallation.dao.IEquipmentInstallationMainDAO;
/**
 * <p>
 * Title:设备安装流程
 * </p>
 * <p>
 * Description:设备安装流程
 * </p>
 * <p>
 * Tue Oct 09 14:09:25 GMT+08:00 2018
 * </p>
 * 
 * @author lyg
 * @version 3.6
 * 
 */
 
 public class EquipmentInstallationMainDAOHibernate extends MainDAO implements IEquipmentInstallationMainDAO {
 	  
 	
	  
	   /* (non-Javadoc)
	    * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
	    */
	  public String getMainName() {
		return "EquipmentInstallationMain";
	  }
 
 }
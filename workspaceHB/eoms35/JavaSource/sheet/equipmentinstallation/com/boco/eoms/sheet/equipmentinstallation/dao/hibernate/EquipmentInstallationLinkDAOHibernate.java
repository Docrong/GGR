package com.boco.eoms.sheet.equipmentinstallation.dao.hibernate;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.equipmentinstallation.dao.IEquipmentInstallationLinkDAO;
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
 
 public class EquipmentInstallationLinkDAOHibernate extends LinkDAO implements IEquipmentInstallationLinkDAO {
  		/**
	     * 根据条件查出所有的link对象
	     */
	    public List getLinksBycondition(String condition, String linkName) throws HibernateException {
	    	String hql = "from " + linkName + " where " + condition;
	    	return getHibernateTemplate().find(hql);	
	    }
 }
package com.boco.eoms.sheet.maintenanceservice.dao.hibernate;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.maintenanceservice.dao.IMaintenanceServiceLinkDAO;
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
 
 public class MaintenanceServiceLinkDAOHibernate extends LinkDAO implements IMaintenanceServiceLinkDAO {
  		/**
	     * 根据条件查出所有的link对象
	     */
	    public List getLinksBycondition(String condition, String linkName) throws HibernateException {
	    	String hql = "from " + linkName + " where " + condition;
	    	return getHibernateTemplate().find(hql);	
	    }
 }
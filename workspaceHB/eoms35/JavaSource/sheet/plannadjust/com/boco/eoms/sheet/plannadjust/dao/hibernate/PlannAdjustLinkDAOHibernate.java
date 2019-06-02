package com.boco.eoms.sheet.plannadjust.dao.hibernate;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.plannadjust.dao.IPlannAdjustLinkDAO;
/**
 * <p>
 * Title:规划站址调整申请流程
 * </p>
 * <p>
 * Description:规划站址调整申请流程
 * </p>
 * <p>
 * Sat Jun 08 11:16:10 CST 2013
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public class PlannAdjustLinkDAOHibernate extends LinkDAO implements IPlannAdjustLinkDAO {
  		/**
	     * 根据条件查出所有的link对象
	     */
	    public List getLinksBycondition(String condition, String linkName) throws HibernateException {
	    	String hql = "from " + linkName + " where " + condition;
	    	return getHibernateTemplate().find(hql);	
	    }
 }
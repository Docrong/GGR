package com.boco.eoms.sheet.widencomplaint.dao.hibernate;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.widencomplaint.dao.IWidenComplaintLinkDAO;
/**
 * <p>
 * Title:家宽投诉处理工单
 * </p>
 * <p>
 * Description:家宽投诉处理工单
 * </p>
 * <p>
 * Mon Feb 01 17:09:53 CST 2016
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public class WidenComplaintLinkDAOHibernate extends LinkDAO implements IWidenComplaintLinkDAO {
  		/**
	     * 根据条件查出所有的link对象
	     */
	    public List getLinksBycondition(String condition, String linkName) throws HibernateException {
	    	String hql = "from " + linkName + " where " + condition;
	    	return getHibernateTemplate().find(hql);	
	    }
 }
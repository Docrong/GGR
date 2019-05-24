package com.boco.eoms.sheet.industrysms.dao.hibernate;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.industrysms.dao.IIndustrySmsLinkDAO;
/**
 * <p>
 * Title:行业短信开通删除工单
 * </p>
 * <p>
 * Description:行业短信开通删除工单
 * </p>
 * <p>
 * Mon Mar 04 17:27:01 CST 2013
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public class IndustrySmsLinkDAOHibernate extends LinkDAO implements IIndustrySmsLinkDAO {
  		/**
	     * 根据条件查出所有的link对象
	     */
	    public List getLinksBycondition(String condition, String linkName) throws HibernateException {
	    	String hql = "from " + linkName + " where " + condition;
	    	return getHibernateTemplate().find(hql);	
	    }
 }
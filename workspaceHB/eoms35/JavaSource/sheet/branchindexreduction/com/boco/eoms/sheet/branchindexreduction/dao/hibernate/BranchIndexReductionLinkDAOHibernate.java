package com.boco.eoms.sheet.branchindexreduction.dao.hibernate;
import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.branchindexreduction.dao.IBranchIndexReductionLinkDAO;
import java.util.List;
import org.hibernate.HibernateException;
/**
 * <p>
 * Title:分公司指标核减流程
 * </p>
 * <p>
 * Description:分公司指标核减流程
 * </p>
 * <p>
 * Fri Jul 28 15:47:20 CST 2017
 * </p>
 * 
 * @author wangmingming
 * @version 1.0
 * 
 */
 
 public class BranchIndexReductionLinkDAOHibernate extends LinkDAO implements IBranchIndexReductionLinkDAO {
  		/**
	     * 根据条件查出所有的link对象
	     */
	    public List getLinksBycondition(String condition, String linkName) throws HibernateException {
	    	String hql = "from " + linkName + " where " + condition;
	    	return getHibernateTemplate().find(hql);	
	    }
 }
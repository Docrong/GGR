package com.boco.eoms.sheet.daiweiindexreduction.dao.hibernate;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.daiweiindexreduction.dao.IDaiWeiIndexReductionMainDAO;

/**
 * <p>
 * Title:代维公司指标核减流程
 * </p>
 * <p>
 * Description:代维公司指标核减流程
 * </p>
 * <p>
 * Tue Aug 01 17:34:54 CST 2017
 * </p>
 * 
 * @author wangmingming
 * @version 1.0
 * 
 */
 
 public class DaiWeiIndexReductionMainDAOHibernate extends MainDAO implements IDaiWeiIndexReductionMainDAO {
 	  
 	
	  
	   /* (non-Javadoc)
	    * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
	    */
	  public String getMainName() {
		return "DaiWeiIndexReductionMain";
	  }
 
 }
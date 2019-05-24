package com.boco.eoms.sheet.transprovincial.dao.hibernate;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.transprovincial.dao.ITransprovincialMainDAO;
/**
 * <p>
 * Title:跨省集客业务工单
 * </p>
 * <p>
 * Description:跨省集客业务工单
 * </p>
 * <p>
 * Thu Sep 27 14:32:21 CST 2012
 * </p>
 * 
 * @author ph
 * @version 3.5
 * 
 */
 
 public class TransprovincialMainDAOHibernate extends MainDAO implements ITransprovincialMainDAO {
 	  
 	
	  
	   /* (non-Javadoc)
	    * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
	    */
	  public String getMainName() {
		return "TransprovincialMain";
	  }
 
 }
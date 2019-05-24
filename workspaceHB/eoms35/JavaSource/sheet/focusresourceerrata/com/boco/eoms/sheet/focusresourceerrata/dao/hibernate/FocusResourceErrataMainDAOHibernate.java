package com.boco.eoms.sheet.focusresourceerrata.dao.hibernate;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.focusresourceerrata.dao.IFocusResourceErrataMainDAO;
/**
 * <p>
 * Title:集客资源勘误
 * </p>
 * <p>
 * Description:集客资源勘误
 * </p>
 * <p>
 * Thu May 10 09:23:09 CST 2018
 * </p>
 * 
 * @author lyg
 * @version 3.6
 * 
 */
 
 public class FocusResourceErrataMainDAOHibernate extends MainDAO implements IFocusResourceErrataMainDAO {
 	  
 	
	  
	   /* (non-Javadoc)
	    * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
	    */
	  public String getMainName() {
		return "FocusResourceErrataMain";
	  }
 
 }
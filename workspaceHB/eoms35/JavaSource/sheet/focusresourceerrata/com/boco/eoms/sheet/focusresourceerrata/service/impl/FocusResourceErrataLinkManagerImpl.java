package com.boco.eoms.sheet.focusresourceerrata.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.service.impl.LinkServiceImpl;
import com.boco.eoms.sheet.focusresourceerrata.dao.IFocusResourceErrataLinkDAO;
import com.boco.eoms.sheet.focusresourceerrata.service.IFocusResourceErrataLinkManager;

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
 
 public class FocusResourceErrataLinkManagerImpl extends LinkServiceImpl implements IFocusResourceErrataLinkManager {
  		/**
	     * 根据条件查出所有的link对象
	     */
	    public List getLinksBycondition(String condition) throws Exception {    	
	    	IFocusResourceErrataLinkDAO dao = (IFocusResourceErrataLinkDAO)this.getLinkDAO();
	        List list = dao.getLinksBycondition(condition, this.getLinkObject().getClass().getName());
	        return list;
	    }
 
 }
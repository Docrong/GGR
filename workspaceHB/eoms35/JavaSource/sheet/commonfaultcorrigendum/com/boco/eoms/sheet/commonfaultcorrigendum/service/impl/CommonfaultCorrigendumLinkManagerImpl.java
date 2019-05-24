package com.boco.eoms.sheet.commonfaultcorrigendum.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.service.impl.LinkServiceImpl;
import com.boco.eoms.sheet.commonfaultcorrigendum.dao.ICommonfaultCorrigendumLinkDAO;
import com.boco.eoms.sheet.commonfaultcorrigendum.service.ICommonfaultCorrigendumLinkManager;

/**
 * <p>
 * Title:故障工单勘误流程
 * </p>
 * <p>
 * Description:故障工单勘误流程
 * </p>
 * <p>
 * Mon Sep 29 11:24:17 CST 2014
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public class CommonfaultCorrigendumLinkManagerImpl extends LinkServiceImpl implements ICommonfaultCorrigendumLinkManager {
  		/**
	     * 根据条件查出所有的link对象
	     */
	    public List getLinksBycondition(String condition) throws Exception {    	
	    	ICommonfaultCorrigendumLinkDAO dao = (ICommonfaultCorrigendumLinkDAO)this.getLinkDAO();
	        List list = dao.getLinksBycondition(condition, this.getLinkObject().getClass().getName());
	        return list;
	    }
 
 }
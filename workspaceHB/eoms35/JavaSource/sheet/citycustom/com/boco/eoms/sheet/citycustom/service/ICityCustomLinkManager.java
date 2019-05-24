package com.boco.eoms.sheet.citycustom.service;

import java.util.List;

import com.boco.eoms.sheet.base.service.ILinkService;

/**
 * <p>
 * Title:地市集客业务工单
 * </p>
 * <p>
 * Description:地市集客业务工单
 * </p>
 * <p>
 * Fri Sep 28 14:06:48 CST 2012
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public interface ICityCustomLinkManager extends ILinkService {
 		 /**
	     * 根据条件查出所有的link对象
	     */
		 public List getLinksBycondition(String condition) throws Exception;
 
 }
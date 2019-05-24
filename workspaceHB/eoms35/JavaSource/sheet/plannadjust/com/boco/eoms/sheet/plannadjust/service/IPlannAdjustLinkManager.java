package com.boco.eoms.sheet.plannadjust.service;

import java.util.List;

import com.boco.eoms.sheet.base.service.ILinkService;

/**
 * <p>
 * Title:规划站址调整申请流程
 * </p>
 * <p>
 * Description:规划站址调整申请流程
 * </p>
 * <p>
 * Sat Jun 08 11:16:09 CST 2013
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public interface IPlannAdjustLinkManager extends ILinkService {
 		 /**
	     * 根据条件查出所有的link对象
	     */
		 public List getLinksBycondition(String condition) throws Exception;
 
 }
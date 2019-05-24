package com.boco.eoms.sheet.equipmentsecurityresponse.service;

import java.util.List;

import com.boco.eoms.sheet.base.service.ILinkService;

/**
 * <p>
 * Title:设备互联核查安全告警响应工单
 * </p>
 * <p>
 * Description:设备互联核查安全告警响应工单
 * </p>
 * <p>
 * Tue Apr 25 11:45:21 CST 2017
 * </p>
 * 
 * @author liuyonggnag
 * @version 3.6
 * 
 */
 
 public interface IEquipmentSecurityResponseLinkManager extends ILinkService {
 		 /**
	     * 根据条件查出所有的link对象
	     */
		 public List getLinksBycondition(String condition) throws Exception;
 
 }
package com.boco.eoms.sheet.equipmentsecurityresponse.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.service.impl.LinkServiceImpl;
import com.boco.eoms.sheet.equipmentsecurityresponse.dao.IEquipmentSecurityResponseLinkDAO;
import com.boco.eoms.sheet.equipmentsecurityresponse.service.IEquipmentSecurityResponseLinkManager;

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
 
 public class EquipmentSecurityResponseLinkManagerImpl extends LinkServiceImpl implements IEquipmentSecurityResponseLinkManager {
  		/**
	     * 根据条件查出所有的link对象
	     */
	    public List getLinksBycondition(String condition) throws Exception {    	
	    	IEquipmentSecurityResponseLinkDAO dao = (IEquipmentSecurityResponseLinkDAO)this.getLinkDAO();
	        List list = dao.getLinksBycondition(condition, this.getLinkObject().getClass().getName());
	        return list;
	    }
 
 }
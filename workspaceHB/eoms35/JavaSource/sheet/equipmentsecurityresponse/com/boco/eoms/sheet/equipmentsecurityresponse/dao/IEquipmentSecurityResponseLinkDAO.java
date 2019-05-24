package com.boco.eoms.sheet.equipmentsecurityresponse.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ILinkDAO;

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
 
 public interface IEquipmentSecurityResponseLinkDAO extends ILinkDAO {
 	 public List getLinksBycondition(String condition, String linkName) throws HibernateException;
 }
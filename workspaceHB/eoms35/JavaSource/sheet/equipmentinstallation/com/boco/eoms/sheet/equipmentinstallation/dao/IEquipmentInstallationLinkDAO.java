package com.boco.eoms.sheet.equipmentinstallation.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ILinkDAO;

/**
 * <p>
 * Title:设备安装流程
 * </p>
 * <p>
 * Description:设备安装流程
 * </p>
 * <p>
 * Tue Oct 09 14:09:25 GMT+08:00 2018
 * </p>
 * 
 * @author lyg
 * @version 3.6
 * 
 */
 
 public interface IEquipmentInstallationLinkDAO extends ILinkDAO {
 	 public List getLinksBycondition(String condition, String linkName) throws HibernateException;
 }
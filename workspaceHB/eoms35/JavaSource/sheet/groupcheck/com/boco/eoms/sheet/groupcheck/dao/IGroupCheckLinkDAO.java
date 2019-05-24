package com.boco.eoms.sheet.groupcheck.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ILinkDAO;

/**
 * <p>
 * Title:集客投诉核查工单
 * </p>
 * <p>
 * Description:集客投诉核查工单
 * </p>
 * <p>
 * Wed Nov 08 15:11:38 GMT+08:00 2017
 * </p>
 * 
 * @author lyg
 * @version 3.6
 * 
 */
 
 public interface IGroupCheckLinkDAO extends ILinkDAO {
 	 public List getLinksBycondition(String condition, String linkName) throws HibernateException;
 }
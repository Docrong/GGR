package com.boco.eoms.sheet.widencomplaint.dao;

import com.boco.eoms.sheet.base.dao.IMainDAO;

/**
 * <p>
 * Title:家宽投诉处理工单
 * </p>
 * <p>
 * Description:家宽投诉处理工单
 * </p>
 * <p>
 * Mon Feb 01 17:09:53 CST 2016
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public interface IWidenComplaintMainDAO extends IMainDAO  {
	 public int getCustomPhoneBySendTime(String beforedate, String afterdate,String customPhone);
 }
 



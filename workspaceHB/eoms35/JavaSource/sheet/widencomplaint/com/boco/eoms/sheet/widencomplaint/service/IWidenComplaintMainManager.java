package com.boco.eoms.sheet.widencomplaint.service;

import java.util.Map;

import com.boco.eoms.sheet.base.service.IMainService;

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
 
 public interface IWidenComplaintMainManager extends IMainService {
	 public int getCustomPhoneBySendTime(String beforedate, String afterdate,String customPhone);
	 
	 public abstract Map getMainsByConditionSQL(String s, Integer integer, Integer integer1);
 }
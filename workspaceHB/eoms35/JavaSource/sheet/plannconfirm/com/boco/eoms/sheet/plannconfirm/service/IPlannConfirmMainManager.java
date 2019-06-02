package com.boco.eoms.sheet.plannconfirm.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.service.IMainService;

/**
 * <p>
 * Title:规划站址确认申请流程
 * </p>
 * <p>
 * Description:规划站址确认申请流程
 * </p>
 * <p>
 * Thu Jun 06 17:13:18 CST 2013
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public interface IPlannConfirmMainManager extends IMainService {
	 public abstract List getNumber(String sendTimeStartDate,String sendTimeEndDate,String queryType) throws SheetException;
	 
	 public abstract Map getDetail(Integer curPage,Integer pageSize,String sendTimeStartDate,String sendTimeEndDate) throws SheetException;
 }
/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.service.impl;

import com.boco.eoms.sheet.base.exception.SheetException;

;
/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 14:32:20
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 *  
 */
public class MainServiceImpl extends MainService {

	public String getFlowCodeId() throws SheetException{
		return "00-00";
	}
	
	/**
	 * 获取流程及其子流程编码，用于拼接工单流水号
	 * @return
	 * @throws SheetException
	 */
	public  String getPreSheetId() throws SheetException {
         return "";
	 }

	/**
	 * 获取工单最大数，用于拼接工单流水号
	 * @return
	 * @throws SheetException
	 */
	public  String getSheetMaxNum() throws SheetException {
		return "";	
	 }
	
	
}

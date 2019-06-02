
package com.boco.eoms.sheet.arithmeticmodify.service;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.arithmeticmodify.model.ArithmeticModifyMain;

public interface IArithmeticModifyMainManager extends IMainService {
	
	/**
	 * 通过工单的父流水号，获取工单mian对象
	 * @param mainYuLiuA 工单父流程sheetKey
	 * @return
	 * @throws SheetException
	 */
	public abstract ArithmeticModifyMain getMainObjByMainYuLiuA(String mainYuLiuA) throws SheetException;
	
}


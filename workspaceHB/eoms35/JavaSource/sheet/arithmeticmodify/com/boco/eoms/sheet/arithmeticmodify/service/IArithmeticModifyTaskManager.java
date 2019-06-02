
package com.boco.eoms.sheet.arithmeticmodify.service;


import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.service.ITaskService;

public interface IArithmeticModifyTaskManager extends ITaskService {
	public Integer getCountOfBrother(String sheetKey, String parentLevelId) throws SheetException;
	
}


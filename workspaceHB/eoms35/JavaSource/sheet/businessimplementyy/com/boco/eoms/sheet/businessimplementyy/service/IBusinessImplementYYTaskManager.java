
package com.boco.eoms.sheet.businessimplementyy.service;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.service.ITaskService;

public interface IBusinessImplementYYTaskManager extends ITaskService {
	public Integer getCountOfBrother(String sheetKey, String parentLevelId) throws SheetException;
}


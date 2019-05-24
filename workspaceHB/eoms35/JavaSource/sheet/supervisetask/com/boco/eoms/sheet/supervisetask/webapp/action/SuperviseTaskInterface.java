package com.boco.eoms.sheet.supervisetask.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.sheet.supervisetask.service.SuperviseTaskManager;

public class SuperviseTaskInterface {

	public String querySheetStatus(String sheetid)throws Exception{
		SuperviseTaskManager superviseTaskManager=(SuperviseTaskManager)ApplicationContextHolder.getInstance().getBean("iSuperviseTaskManager"); 
		String json=superviseTaskManager.querySheetStatus(sheetid);
		sheetid=json;
		return sheetid;
	}
}

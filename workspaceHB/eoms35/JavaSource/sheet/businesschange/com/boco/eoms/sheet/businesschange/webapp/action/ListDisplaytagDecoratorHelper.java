package com.boco.eoms.sheet.businesschange.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.sheet.base.webapp.action.MainListDisplaytagDecoratorHelper;

import com.boco.eoms.sheet.businesschange.task.IBusinessChangeTask;

public class ListDisplaytagDecoratorHelper extends MainListDisplaytagDecoratorHelper {
	public String getUrgentDegree() {
		System.out.println("****************Test*******");
		IBusinessChangeTask task = (IBusinessChangeTask) getCurrentRowObject();
        ID2NameService service = (ID2NameService) ApplicationContextHolder
           .getInstance().getBean("ID2NameGetServiceCatch");
        String name = service.id2Name(task.getUrgentDegree(), "ItawSystemDictTypeDao");
        return name;
    }
	
	public String getBusinessType() {
		IBusinessChangeTask task = (IBusinessChangeTask) getCurrentRowObject();
        ID2NameService service = (ID2NameService) ApplicationContextHolder
           .getInstance().getBean("ID2NameGetServiceCatch");
        String name = service.id2Name(task.getBusinessType(), "ItawSystemDictTypeDao");
        return name;
    }
}

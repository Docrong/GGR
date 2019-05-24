package com.boco.eoms.sheet.businessdredge.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.util.Constants;
import com.boco.eoms.sheet.businessdredge.task.IBusinessDredgeTask;
import com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper;

public class ListDisplaytagDecoratorHelper extends
		ProcessListDisplaytagDecoratorHelper {
	public String getUrgentDegree() {
		IBusinessDredgeTask task = (IBusinessDredgeTask) getCurrentRowObject();
        ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String name = service.id2Name(task.getUrgentDegree(), "ItawSystemDictTypeDao");
        
        return name;
    }
	
	public String getBusinessType() {
		IBusinessDredgeTask task = (IBusinessDredgeTask) getCurrentRowObject();
        ID2NameService service = (ID2NameService) ApplicationContextHolder
        .getInstance().getBean("ID2NameGetServiceCatch");
        String name = service.id2Name(task.getBusinessType(), "ItawSystemDictTypeDao");
        
        return name;
    }
}

package com.boco.eoms.sheet.businessbackout.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
//import com.boco.eoms.sheet.base.webapp.action.MainListDisplaytagDecoratorHelper;
import com.boco.eoms.sheet.base.webapp.action.ProcessListDisplaytagDecoratorHelper;
import com.boco.eoms.sheet.businessbackout.task.IBusinessBackoutTask;

public class ListDisplaytagDecoratorHelper extends ProcessListDisplaytagDecoratorHelper {
    public String getUrgentDegree() {
        System.out.println("****************Test*******");
        IBusinessBackoutTask task = (IBusinessBackoutTask) getCurrentRowObject();
        ID2NameService service = (ID2NameService) ApplicationContextHolder
                .getInstance().getBean("ID2NameGetServiceCatch");
        String name = service.id2Name(task.getUrgentDegree(), "ItawSystemDictTypeDao");
        return name;
    }

    public String getBusinessType() {
        IBusinessBackoutTask task = (IBusinessBackoutTask) getCurrentRowObject();
        ID2NameService service = (ID2NameService) ApplicationContextHolder
                .getInstance().getBean("ID2NameGetServiceCatch");
        String name = service.id2Name(task.getBusinessType(), "ItawSystemDictTypeDao");
        return name;
    }
}

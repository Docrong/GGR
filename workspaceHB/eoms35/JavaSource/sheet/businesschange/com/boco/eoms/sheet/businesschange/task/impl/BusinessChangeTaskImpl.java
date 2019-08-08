
package com.boco.eoms.sheet.businesschange.task.impl;

import com.boco.eoms.sheet.base.task.impl.TaskImpl;
import com.boco.eoms.sheet.businesschange.task.IBusinessChangeTask;

public class BusinessChangeTaskImpl extends TaskImpl implements IBusinessChangeTask {

    private String urgentDegree;
    private String businessType;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getUrgentDegree() {
        return urgentDegree;
    }

    public void setUrgentDegree(String urgentDegree) {
        this.urgentDegree = urgentDegree;
    }
}


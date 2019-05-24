
package com.boco.eoms.sheet.businessdredgebroad.task.impl;

import com.boco.eoms.sheet.base.task.impl.TaskImpl;
import com.boco.eoms.sheet.businessdredgebroad.task.IBusinessDredgebroadTask;

public class BusinessDredgebroadTaskImpl extends TaskImpl implements IBusinessDredgebroadTask {

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


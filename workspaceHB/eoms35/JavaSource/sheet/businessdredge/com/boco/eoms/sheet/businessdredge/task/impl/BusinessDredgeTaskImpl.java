
package com.boco.eoms.sheet.businessdredge.task.impl;

import com.boco.eoms.sheet.base.task.impl.TaskImpl;
import com.boco.eoms.sheet.businessdredge.task.IBusinessDredgeTask;

public class BusinessDredgeTaskImpl extends TaskImpl implements IBusinessDredgeTask {

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


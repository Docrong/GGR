
package com.boco.eoms.sheet.businessdredge.task;

import com.boco.eoms.sheet.base.task.ITask;

public interface IBusinessDredgeTask extends ITask {
	public String getBusinessType();
	public String getUrgentDegree();
}


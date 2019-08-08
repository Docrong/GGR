
package com.boco.eoms.sheet.businessbackout.task;

import com.boco.eoms.sheet.base.task.ITask;

public interface IBusinessBackoutTask extends ITask {
    public String getBusinessType();

    public String getUrgentDegree();
}



package com.boco.eoms.sheet.businesschange.task;

import com.boco.eoms.sheet.base.task.ITask;

public interface IBusinessChangeTask extends ITask {
    public String getBusinessType();

    public String getUrgentDegree();
}


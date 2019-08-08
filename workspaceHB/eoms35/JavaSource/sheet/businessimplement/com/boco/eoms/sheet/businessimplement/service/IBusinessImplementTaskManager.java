
package com.boco.eoms.sheet.businessimplement.service;

import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.sheet.base.exception.SheetException;
import com.boco.eoms.sheet.base.service.ITaskService;

public interface IBusinessImplementTaskManager extends ITaskService {
    public Integer getCountOfBrother(String sheetKey, String parentLevelId) throws SheetException;

    /**
     * 获取当前角色待处理未挂起工单,带超时提醒
     *
     * @param userId     用户ID
     * @param deptId     部门ID
     * @param flowName   流程ID
     * @param startIndex
     * @param length
     * @return
     * @throws Exception
     */
    public HashMap getUndoTaskByOverTime(Map condition, String userId, String deptId, String flowName, Integer startIndex, Integer length) throws Exception;

}


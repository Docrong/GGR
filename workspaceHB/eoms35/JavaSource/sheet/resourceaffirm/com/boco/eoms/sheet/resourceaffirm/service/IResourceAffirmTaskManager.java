
package com.boco.eoms.sheet.resourceaffirm.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.base.service.ITaskService;

public interface IResourceAffirmTaskManager extends ITaskService {
	/**
	 * 获取当前时间内所有未处理的工单
	 * @return
	 * @throws Exception
	 */
	public List getAllUndoTaskList(Map condition) throws Exception ;
}


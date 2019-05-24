
package com.boco.eoms.sheet.commontask.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.base.service.ITaskService;

public interface ICommonTaskTaskManager extends ITaskService {
	
	public abstract HashMap getSplitAndReplyTask(Map map, String s, String s1, String s2, Integer integer, Integer integer1)
    throws Exception;

    public abstract HashMap getSplitNotReplyTask(Map map, String s, String s1, String s2, Integer integer, Integer integer1)
    throws Exception;

	
	/**
	 * 获取当前时间内所有未处理的工单
	 * @return
	 * @throws Exception
	 */
	public List getAllUndoTaskList(Map condition) throws Exception ;
}


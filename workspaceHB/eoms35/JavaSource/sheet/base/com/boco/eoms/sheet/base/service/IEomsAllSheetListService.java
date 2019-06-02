package com.boco.eoms.sheet.base.service;

import java.util.HashMap;
import java.util.Map;

public interface IEomsAllSheetListService {
	/**
	 * 获取当前角色待处理工单
	 * 
	 * @param userId
	 *            用户ID
	 * @param deptId
	 *            部门ID
	 * @param flowName
	 *            流程ID
	 * @param startIndex
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public HashMap getUndoAllSheetTask(Map condition, String userId,
			String deptId, String flowName, Integer startIndex, Integer length)
			throws Exception;
}

/*
 * Created on 2008-4-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.complaint.service;

import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.sheet.base.service.ITaskService;

/**
 * @author IBM_USER
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IComplaintTaskManager extends ITaskService{

	HashMap getUndoTaskByOverTimeAll(Map condition, String userId, String deptId, String flowName, Integer pageIndex, Integer pageSize);

}

/*
 * Created on 2008-4-1
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.base.flowchar.Node;
import com.boco.eoms.sheet.base.task.ITask;

/**
 * @author IBM_USER
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ITaskService {
	/**
	 * 获取当前角色待处理工单
	 * @param userId 用户ID
	 * @param deptId 部门ID
	 * @param flowName 流程ID
	 * @param startIndex 
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public abstract HashMap getUndoTask(Map condition,String userId,String deptId,String flowName,Integer startIndex,Integer length) throws Exception;
	/**
	 * 获取当前角色已处理工单
	 * @param userId 用户ID
	 * @param deptId 部门ID
	 * @param flowName
	 * @param startIndex
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public abstract HashMap getDoneTask(Map condition,String userId,String deptId,String flowName,Integer startIndex,Integer length) throws Exception;
    /**
     * 获取TaskModel对象，此对象是个空对象
     * @return
     */
	public ITask getTaskModelObject();
	
	/**
	 * 保存任务信息
	 * @param obj
	 * @throws Exception
	 */
	public void addTask(Object obj) throws Exception;
	/**
	 * 根据taskId获取task model对象
	 * @param id taskId
	 * @return
	 * @throws Exception
	 */
	public ITask getSinglePO(String id) throws Exception;
	/**
	 * 
	 * @param sheetKey
	 * @return
	 */
	public ITask getOperateRoleId(String sheetKey);
	
	public List getOperateRoleIdAll(String sheetKey);
	
	/**
	 * 将各种任务信息（流程、业务）放置到task model对象中
	 * 此方法为新流程专用，流程中取消对task对象业务信息的赋值。
	 * @param task
	 * @param methods
	 * @param processTemplateMap
	 * @param taskBOMap
	 * @param taskObjectMap
	 * @param mainObjectMap
	 * @param
	 * @return
	 * @throws Exception
	 */
	public  HashMap setTaskModel(Object task, HashMap processTemplateMap,
			HashMap taskBOMap, HashMap taskObjectMap,HashMap mainObjectMap,HashMap linkObjectMap) throws Exception;
	
	/**
	 * 将各种任务信息（流程、业务）放置到task model对象中
	 * 此方法为老流程用，即是流程中存在task赋值语句
	 * @param task
	 * @param methods
	 * @param processTemplateMap
	 * @param taskBOMap
	 * @param taskObjectMap
	 * @return
	 * @throws Exception
	 */
	public HashMap setTaskModel(Object task, HashMap processTemplateMap,
			HashMap taskBOMap, HashMap taskObjectMap) throws Exception;
	

    public abstract List getCurrentUndoTask(String sheetKey)throws Exception;
    
    /**
	 * 获取除当前角色外的其他步骤的任务工单
	 * @param taskOwner 角色ID
	 * @param flowName 流程ID
	 * @param startIndex 
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public  List getExceptMeTask(String mainId,String linkName,String taskOwner) throws Exception;
	
	 /**
	  * 查询上一个任务执行情况
	  * @param preLinkId
	  * @return
	  */
	 public ITask getTaskByPreLinkid(String preLinkId) throws Exception;

	 
	 
	 /**
	  * 查询所有子任务
	  * @param preLinkId
	  * @return
	  */
	 public List getUndealTaskListByParentTaskId(String parentTaskId) throws Exception;
	 
	 
	 /**
	  * 查询所有子任务
	  * @param preLinkId
	  * @return
	  */
	 public List getTasksByTaskNameAndSheetId(String taskName, String mainId) throws Exception;
	 
	 
	 /**
	  * 查询没有回复的子任务
	  * @param preLinkId
	  * @return
	  */
	 public List getNotReversionListByParentTaskId(String parentTaskId) throws Exception;

	 
	 /**
	  * 根据sheetID找出所以的任务表
	  * @param preLinkId
	  * @return
	  */
	 public List getTasksBySheetKey(String sheetKey, String condition) throws Exception;
	 
	 /**
	  * 根据用户自拟的条件查询任务表
	  * @param preLinkId
	  * @return
	  */
	 public List getTasksByCondition(String condition) throws Exception;
	 
	 /**
	  * 根所sheetID找出所以的任务表
	  * @param preLinkId
	  * @return
	  */
	 public List getAllSubTask(ArrayList nodeList, ArrayList floorList,
				Node parNode, int floorNumber) throws Exception;
	 
	 /**
	  * 根所sheetID找出所以的任务表
	  * @param preLinkId
	  * @return
	  */
	 public HashMap getDraftList(Map condition,String flowTemplateName, Integer pageIndex, Integer pageSize) throws Exception;
	 public ITask getTask(String sheetKey,String taskName) throws Exception;
	 
	 /**
	  * 查询所有子任务
	  * @param preLinkId
	  * @return
	  */
	 public List getAiTasksByTaskNameAndSheetId(String taskName, String mainId) throws Exception;	 

	 /**
	  * 查询所有子任务
	  * @param preLinkId
	  * @return
	  */
	 public List getTkiTasksByTaskNameAndSheetId(String taskName, String mainId) throws Exception;
	 
	 /**
	  * 查询在值班时间内需要处理的工单和已经处理工单
	  * @param preLinkId
	  * @return
	  */
	 public List getTasksByCreateTimeAndUserId(String startTime, String endTime, String userId, String moduleName) throws Exception;
	/**
	 * 获取派发给本角色的未被抢单的工单列表
	 * 
	 * @param userId  用户ID
	 * @param startIndex
	 * @param length
	 * @return HashMap
	 * @throws Exception
	 */
	public HashMap getUndoTaskByRole(Map condition,String userId, Integer startIndex, Integer length) throws Exception;

	/**
	 * 获取本角色的已被组内其他人员抢单后的工单
	 * 
	 * @param userId  用户ID
	 * @param startIndex
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public HashMap getDoneTaskByRole(Map condition, String userId, Integer startIndex, Integer length) throws Exception;
	
	/**
	 * 获取当前角色所有待处理工单
	 * @param userId 用户ID
	 * @param deptId 部门ID
	 * @param flowName 流程ID
	 * @param startIndex 
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public abstract HashMap getAllUndoTask(Map condition,String userId,String deptId,String flowName) throws Exception;
	/**
	 * 获取当前角色待处理工单,带超时提醒
	 * @param userId 用户ID
	 * @param deptId 部门ID 
	 * @param flowName 流程ID
	 * @param startIndex 
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public  HashMap getUndoTaskByOverTime(Map condition,String userId,String deptId,String flowName,Integer startIndex,Integer length) throws Exception;
	/**
	 * 获取本角色的已被组内其他人员抢单但未处理的工单
	 * 
	 * @param userId  用户ID
	 * @param startIndex
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public HashMap getAcceptTaskByRole(Map condition, String userId, Integer startIndex, Integer length) throws Exception;
	 /**
     * 获取当前角色当前工单待处理任务
     * @param subRoleId
     * @param sheetKey
     * @return
     * @throws Exception
     */
    public abstract ITask getUndoTaskByRoleAndSheetKey(String subRoleId,String sheetKey) throws Exception;
    
    /**
	 * 获取超时待办工单的数目
	 * @param userId 用户ID
	 * @param deptId 部门ID 
	 * @param flowName 流程ID
	 * @param startIndex 
	 * @param length
	 * @return
	 * @throws Exception
	 */
    public  Integer getOverTimeTaskCount(Map condition,String userId,String deptId) throws Exception;
    
    /**
	 * 获取当前角色待处理工单
	 * @param userId 用户ID
	 * @param deptId 部门ID
	 * @param flowName 流程ID
	 * @param startIndex 
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public abstract HashMap getUndoAllSheetTask(Map condition,String userId,String deptId,String flowName,Integer startIndex,Integer length) throws Exception;
	/**
	 * 获取当前角色所有待处理工单
	 * @param userId 用户ID
	 * @param deptId 部门ID
	 * @param flowName 流程ID
	 * @param startIndex 
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public abstract HashMap getAllUndoAllSheetTask(Map condition,String userId,String deptId,String flowName) throws Exception;
	/**
	 * 获取当前角色待归档工单,带超时提醒

	 * @param userId 用户ID
	 * @param deptId 部门ID 
	 * @param flowName 流程ID
	 * @param startIndex 
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public  HashMap getUnHoldTask(Map condition,String userId,String deptId,String flowName,Integer startIndex,Integer length) throws Exception;	
	/**
	 * 获取待处理工单任务列表（不含过滤步骤的任务）
	 * @param condition
	 * @param userId
	 * @param deptId
	 * @param flowName
	 * @param startIndex
	 * @param length
	 * @return
	 * @throws Exception
	 */
		public  HashMap getUndoListByFilter(Map condition,String userId,String deptId,String flowName,Integer startIndex,Integer length) throws Exception;
		
		/**
		 * 获取当前延期申请的工单
		 * @param userId 用户ID
		 * @param deptId 部门ID
		 * @param startIndex
		 * @param length
		 * @return
		 * @throws Exception
		 */
		public abstract HashMap deferAppList(Map condition,String userId,String deptId,Integer startIndex,Integer length) throws Exception;
	
}

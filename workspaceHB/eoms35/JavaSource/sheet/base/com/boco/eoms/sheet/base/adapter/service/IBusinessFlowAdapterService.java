/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.adapter.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.base.task.ITask;
import com.ibm.task.api.Task;

/**
 * <p>
 * Title:流程相关adapter API
 *
 * </p>
 * <p>
 * Description:流程相关adapter API,由本地service(WPSBusinessFlowAdapterServiceImpl)调用
 * </p>
 * <p>
 * Date:2007-8-3 14:10:05
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public interface IBusinessFlowAdapterService {
    /**
     * 获取已处理任务列表
     *
     * @param startIndex
     * @param length
     * @param filter
     * @param taskBoName
     * @param taskField
     * @return
     * @throws Exception
     */
    public List getDoneTasksList(int startIndex, int length, HashMap filter,
                                 String taskBoName, HashMap taskField, HashMap sessionMap)
            throws Exception;

    public List getDoneTasksList(int startIndex, int length, HashMap filter,
                                 String taskBoName, HashMap taskField) throws Exception;

    public int getDoneTasksCount(HashMap filter, HashMap sessionMap)
            throws Exception;

    /**
     * 获取未处理任务个数
     *
     * @param filter
     * @return
     * @throws Exception
     */
    public int getUndoTasksCount(HashMap filter) throws Exception;

    public int getUndoTasksCount(HashMap filter, HashMap sessionMap)
            throws Exception;

    /**
     * 获取未处理任务列表
     *
     * @param startIndex
     * @param length
     * @param filter
     * @param taskBoName
     * @param taskField
     * @return
     * @throws Exception
     */
    public List getUndoTasksList(int startIndex, int length, HashMap filter,
                                 String taskBoName, HashMap taskField, HashMap sessionMap)
            throws Exception;

    public List getUndoTasksList(int startIndex, int length, HashMap filter,
                                 String taskBoName, HashMap taskField) throws Exception;

    /**
     * 获取未处理任务列表
     *
     * @param filter
     * @return
     * @throws Exception
     */
    public List getUndoTasksList(HashMap filter) throws Exception;

    /**
     * 完成人工任务
     *
     * @param activityId
     * @param parameters
     * @throws Exception
     */
    public void finishTask(String activityId, HashMap parameters,
                           HashMap sessionMap) throws Exception;

    public void finishTask(String activityId, HashMap parameters)
            throws Exception;

    /**
     * 启动流程
     *
     * @param processTemplateName 流程模板名称
     * @param operationName       初始操作名称
     * @param parameters          流程入口参数
     * @return
     * @throws Exception
     */
    public String triggerProcess(String processTemplateName,
                                 String operationName, HashMap parameters, HashMap sessionMap)
            throws Exception;

    public String triggerProcess(String processTemplateName,
                                 String operationName, HashMap parameters) throws Exception;

    /**
     * @param identifier
     * @param variableName
     * @param sessionMap
     * @return
     * @throws Exception
     */
    public Map getVariable(String identifier, String variableName, HashMap sessionMap) throws Exception;


    /**
     * 将流程引擎传递过来的dataObject转换为model对象
     *
     * @param object
     * @param modelClassName
     * @return
     * @throws Exception
     */
    public Object transferMainObjectToMode(Object object, String modelClassName)
            throws Exception;

    /**
     * 将流程引擎传递过来的dataObject转换为model对象
     *
     * @param object
     * @param modelClassName
     * @return
     * @throws Exception
     */
    public Object transferLinkObjectToMode(Object object, String modelClassName)
            throws Exception;

    /**
     * 撤销任务
     *
     * @param piid
     * @return
     */
    public boolean cancel(String piid, HashMap sessionMap) throws Exception;

    /**
     * 各种流程ID的转换方法（piid、tkid、aiid等） 将流程数据库中的存储格式转换成API中可以获取的存储格式
     *
     * @param ids
     * @return
     */
    public String transferIdFromDBToAction(String id) throws Exception;

    /**
     * 各种流程ID的转换方法（piid、tkid、aiid等） 将API中可以获取的存储格式转换成流程数据库中的存储格式
     *
     * @param ids
     * @return
     */
    public String transferIdFormActionToDB(String id) throws Exception;

    /**
     * 声明一个任务
     *
     * @param tkid
     * @param sessionMap
     * @throws Exception
     */
    public void claimTask(String tkid, HashMap parameters, HashMap sessionMap) throws Exception;

    /**
     * 创建一个子任务
     *
     * @param tkid       任务模板的任务ID
     * @param parameters 子任务的参数
     * @param sessionMap 用户的登陆信息
     * @throws Exception
     */
    public void createSubTask(String tkid, HashMap parameters,
                              HashMap sessionMap) throws Exception;

    /**
     * 获取节点事件接口列表
     *
     * @param tkid
     * @param sessionMap
     * @throws Exception
     */
    public List getActiveEventHandlers(String piid, HashMap sessionMap)
            throws Exception;


    /**
     * 获取指定的task对象
     *
     * @param taskId     ÈÎÎñID
     * @param task       taskModel¶ÔÏó
     * @param sessionMap
     * @throws Exception
     */
    public ITask getTaskModeByTaskId(String taskId, ITask task, HashMap sessionMap) throws Exception;


    /**
     * 触发事件
     *
     * @param taskId     任务ID
     * @param task       taskModel对象
     * @param sessionMap
     * @throws Exception
     */
    public String triggerEvent(String piid, String processTemplateName,
                               String operationName, HashMap parameters, HashMap sessionMap)
            throws Exception;

    /**
     * 工单移交
     *
     * @param tkid
     * @param sessionMap TODO
     * @param sessionMap
     * @throws Exception
     */
    public void transferWorkItem(String tkid, String fromOwnerUserId, String fromOwnerRoleId,
                                 String toOwnerUserId, String toOwnerRoleId, String operOrgType, HashMap map, HashMap sessionMap) throws Exception;

    /**
     * 流程回调
     *
     * @param piid
     * @param operationName
     * @param parameters
     * @param sessionMap
     * @throws Exception
     */
    public void reInvokeProcess(String piid, String operationName,
                                HashMap parameters, HashMap sessionMap) throws Exception;

    /**
     * 取消申请一个任务
     *
     * @param tkid
     * @param sessionMap
     * @throws Exception
     */
    public void cancelClaimTask(String tkid, HashMap parameters, HashMap sessionMap) throws Exception;


}

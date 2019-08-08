/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.base.task.ITask;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: wps
 * </p>
 * <p>
 * Date:2007-8-3 14:10:05
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public interface IBusinessFlowService {
    /**
     * @param processTemplateName
     * @param OperateName
     * @param map
     * @return
     * @throws Exception
     */
    public abstract String initProcess(String processTemplateName,
                                       String OperateName, HashMap map, HashMap sessionMap)
            throws Exception;

    /**
     * @param identifier
     * @param variableName
     * @param sessionMap
     * @return
     * @throws Exception
     */
    public Map getVariable(String identifier, String variableName, HashMap sessionMap) throws Exception;

    /**
     * 完成人工任务的api，主要调用引擎中的具体方法，来完成人工任务
     *
     * @param activityId 人工任务对应的实例号，类似与3.0里的taskId
     * @param parameters
     * @return
     * @throws Exception
     */
    public abstract String completeHumanTask(String activityId,
                                             HashMap parameters, HashMap sessionMap) throws Exception;

    public int getUndoTasksCount(HashMap filter) throws Exception;

    public int getUndoTasksCount(HashMap filter, HashMap sessionMap)
            throws Exception;

    public List getUndoTasksList(HashMap filter) throws Exception;

    public List getUndoTasksList(int startIndex, int length, HashMap taskMap,
                                 HashMap filter, HashMap sessionMap) throws Exception;

    public int getDoneTasksCount(HashMap filter, HashMap sessionMap)
            throws Exception;

    public List getDoneTasksList(HashMap filter) throws Exception;

    public List getDoneTasksList(int startIndex, int length, HashMap taskMap,
                                 HashMap filter, HashMap sessionMap) throws Exception;

    /**
     * @param processId
     */
    public boolean cancel(String processId, HashMap sessionMap)
            throws Exception;

    /**
     * 申请一个任务
     *
     * @param tkid
     * @param sessionMap
     * @throws Exception
     */
    public void claimTask(String tkid, HashMap parameters, HashMap sessionMap) throws Exception;

    /**
     * 创建一个子任务
     *
     * @param tkid
     * @param sessionMap
     * @throws Exception
     */
    public void createSubTask(String tkid, HashMap parameters, HashMap sessionMap) throws Exception;

    /**
     * 获取节点事件接口列表
     *
     * @param oiid
     * @param sessionMap
     * @throws Exception
     */
    public List getActiveEventHandlers(String piid, HashMap sessionMap)
            throws Exception;

    /**
     * 触发EventHandle
     *
     * @param oiid
     * @param sessionMap
     * @throws Exception
     */
    public String triggerEvent(String piid, String processTemplateName,
                               String OperateName, HashMap map, HashMap sessionMap)
            throws Exception;

    /**
     * 根据任务ID号，获取task model对象值
     *
     * @param tkiid
     * @param task
     * @param sessionMap
     * @return
     * @throws Exception
     */
    public ITask getTaskModelByTaskId(String tkiid, ITask task, HashMap sessionMap) throws Exception;

    /**
     * Transfers the owner for the specified activity instance to the specified
     * user using the activity instance ID.
     * <p>
     * The activity instance must be in the ready,claimed, waiting, ready, or
     * stopped execution state. The associated process instance must be in the
     * running, failing, or terminating execution state.
     * <p>
     * The caller must own the work item with an assignment reason "owner" or
     * "potential owner" or be an administrator of the associated process
     * instance.
     * <p>
     * If the caller is not an administrator, then the user that work item is
     * transferred to must be an administrator or own the same work item with an
     * assignment reason "potential owner".
     *
     * @param fromOwner   the original owner
     * @param toOwner     the delegated owner
     * @param tkid        specified task id
     * @param operOrgType the operate org type
     * @return the result of the operation
     * @throws Exception thrown by the method
     */
    public void transferWorkItem(String tkid, String fromOwnerUserId, String fromOwnerRoleId,
                                 String toOwnerUserId, String toOwnerRoleId, String operOrgType, HashMap map, HashMap sessionMap) throws Exception;

    /**
     * 流程互调
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

    public void setVariable(String processId, String variableName, Map control, HashMap sessionMap) throws Exception;


}

/*
 * Created on 2007-9-16
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
 * @author IBM_USER
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IWorkflowEngineService {
    /**
     * query and return the number of process templates defined in the database
     *
     * @return the number of process templates
     * @throws GeneralException thrown by the method if any exception happened
     */
    public int getProcessTemplatesCount() throws Exception;

    /**
     * query and return the process templates with the specified size
     *
     * @param size the number of process templates returned
     * @return list of process templates name
     * @throws Exception thrown by the method if any exception happened
     */
    public List getProcessTemplates(int size) throws Exception;

    /**
     * query and return the information of the specified process template. If
     * the process template doesn't exist, then an empty HashMap will be
     * returned
     *
     * @param processTemplateName the process template name
     * @return the HashMap representation of the process template information
     * @throws Exception thrown by the method if any exception happened
     */
    public HashMap getProcessTemplateInfo(String processTemplateName)
            throws Exception;

    /**
     * get the process instance number with the specified process template name
     *
     * @param processTemplateName the specified process template name
     * @return the number of process instances
     * @throws Exception thrown by the method if any exception happened
     */
    public int getProcessInstancesCountByProcessTemplateName(
            String processTemplateName) throws Exception;

    /**
     * query and return the process instances with the specified process
     * template name and the specified size
     *
     * @param processTemplateName the process template name
     * @param size                the size of process instances returned
     * @return the list of process instances
     * @throws Exception thrown by the method if any exception happened
     */
    public List getProcessInstancesByProcessTemplateName(
            String processTemplateName, int size) throws Exception;

    /**
     * query and return the number of running process instances started by the
     * current user
     *
     * @return the number of process instances in running states
     * @throws Exception thrown by the method if any exception happened
     */
    public int getStartedProcessInstancesCount() throws Exception;

    /**
     * query and return the process instances started by the current user, and
     * limit the number of process instances with the specified size.
     *
     * @param size the number of process instances returned
     * @return the process instances started by current user
     * @throws Exception thrown by the method if any exception happened
     */
    public List getStartedProcessInstances(int size) throws Exception;

    /**
     * @param identifier
     * @param variableName
     * @return
     * @throws Exception
     */
    public Map getVariable(String identifier, String variableName) throws Exception;

    /**
     * query and return the number of running process instances administrated by
     * the current user
     *
     * @return the number of running processes administrated by current user
     * @throws Exception
     */
    public int getAdministratedProcessInstancesCount() throws Exception;

    /**
     * query and return the process instances administrated by current user, and
     * limit the number of process instances with the specified size.
     *
     * @param size the number of process instances returned
     * @return the process instances administrated by current user
     * @throws Exception thrown by the method if any exception happened
     */
    public List getAdministratedProcessInstances(int size) throws Exception;

    /**
     * query and return the number of subProcess of the specified process
     * instance
     *
     * @param parentProcessName the name of parent process instance
     * @return the number of subProcess
     * @throws Exception thrown by the method if any exception happened
     */
    public int getSubProcessInstancesCount(String parentProcessName)
            throws Exception;

    /**
     * query and return the subProcesses of the specified process instances, and
     * limit the number to the specified size
     *
     * @param parentProcessName the name of parent process instance
     * @param size              number of process instances returned
     * @return the subProcess instances
     * @throws Exception thrown by the method is any exception happened
     */
    public List getSubProcessInstances(String parentProcessName, int size)
            throws Exception;

    /**
     * query and return the number of process instances which satisfy the filter
     * conditions
     *
     * @param filter the filter condition
     * @return the number of process instances
     * @throws Exception thrown by the method if any exception happened
     */
    public int getProcessInstancesCount(HashMap filter) throws Exception;

    /**
     * query and return the process instances which satisfy the filter
     * conditions
     *
     * @param filter the filter conditions
     * @return the process instances
     * @throws Exception thrown by the method if any exception happened
     */
    public List getProcessInstances(HashMap filter) throws Exception;

    /**
     * get the specified process instance information
     *
     * @param processId the processId which can uniquely identify the process instance
     * @return the HashMap representation of the process instance information
     * @throws Exception thrown by the method if any exception happened
     */
    public HashMap getProcessInstanceInfo(String processId) throws Exception;

    /**
     * trigger the process using the specified operation and parameters. The
     * end-user should specify the correct processName, operationName and
     * parameters. Since DataObject may not be available in the client
     * environment, HashMap is used to represent the input BO of the process
     *
     * @param processTemplateName the specified processName
     * @param operationName       the specified operationName
     * @param parameters          the HashMap representation of the input BO of the process
     * @return the processId, if the process is started successfully
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public String triggerProcess(String processTemplateName,
                                 String operationName, HashMap parameters) throws Exception;

    /**
     * trigger the process using the specified operation and parameters, and set
     * the process instance with the specified custom properties. The end-user
     * should specify the correct processName, operationName and parameters.
     *
     * @param processTemplateName the specified process template name
     * @param operationName       the operation name corresponding to start activity of the
     *                            process
     * @param parameters          the HashMap representation of the input BO of the process
     * @param properties          the HashMap representation of specified custom properties.
     *                            key:custom property name; value: custom property value
     * @return the process instance id
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public String triggerProcess(String processTemplateName,
                                 String operationName, HashMap parameters, HashMap properties)
            throws Exception;

    /**
     * Retrieves the list of custom property names for the specified process
     * instance
     * <p>
     * The caller must have at least reader authority for the process instance.
     *
     * @param processId the process ID which can uniquely identify the process
     *                  instance
     * @return the list of custom property names
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public List getProcessInstanceCustomPropertyNameList(String processId)
            throws Exception;

    /**
     * get the custom properties list of the specified process instance, which
     * include the list of name/value pair
     * <p>
     * The caller must have at least reader authority for the process instance.
     *
     * @param processId the process ID which uniquely represent the process instance
     * @return custom properties
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public HashMap getProcessInstanceCustomProperties(String processId)
            throws Exception;

    /**
     * get the specfied custom property value of the specified process instance
     * <p>
     * The caller must have at least reader authority for the process instance.
     *
     * @param processId    the process ID which can uniquely identify the process
     *                     instance
     * @param propertyName the custom property name
     * @return the custom property value
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public String getProcessInstanceCustomPropertyValue(String processId,
                                                        String propertyName) throws Exception;

    /**
     * set custom property of specified process instance with the specified
     * name/value
     * <p>
     * The caller must be the starter or an administrator of the process
     * instance
     *
     * @param processId     the process ID which can uniquely identify the process
     *                      instance
     * @param propertyName  the custom property name
     * @param propertyValue the custom property value
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public void setProcessInstanceCustomProperty(String processId,
                                                 String propertyName, String propertyValue) throws Exception;

    /**
     * Set the custom properties of specified process instance with the
     * specified name/value pairs
     * <p>
     * The caller must be the starter or an administrator of the process
     * instance
     *
     * @param processId  the process ID which can uniquely identify the process
     *                   instance
     * @param properties the HashMap representation of the custom properties
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public void setProcessInstanceCustomProperties(String processId,
                                                   HashMap properties) throws Exception;

    /**
     * Create a work item for the specified activitiy instance. The work item is
     * created with the specified assignment reason and the specified user.
     * <p>
     * Possible reasons are: REASON_POTENTIAL_OWNER =1, REASON_EDITOR =2,
     * REASON_READER = 3, REASON_OWNER = 4, REASON_STARTER =6,
     * REASON_ADMINISTRATOR =7, REASON_ORIGINATOR =9, REASON_ESCALATION_RECEIVER
     * =10, REASON_POTENTIAL_INSTANCE_CREATOR = 11
     * <p>
     * A work item with an assignment reason "owner" cannot be explicitly
     * created. Work items with assignment reasons "administrator" can only be
     * created on the process instance
     * <p>
     * When a work item for an activity instance is to be created, the activity
     * instance must be in the claimed, waiting, ready, or stopped execution
     * state. The activity must be an invoke, script, or staff activity.
     * <p>
     * <p>
     * The caller must be an administrator of the process instance.
     *
     * @param activityId   the activity ID which can uniquely identify the activity
     *                     instance
     * @param assignReason the assignment reason
     * @param userName     the user name
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public void createActivityWorkitem(String activityId, int assignReason,
                                       String userName) throws Exception;

    /**
     * Creates a work item for processes with the specified assignment reason
     * for the specified process instance and user.
     * <p>
     * A work item with an assignment reason "starter" cannot be explicitly
     * created. Work items with assignment reasons "administrator" or "reader"
     * are automatically propagated to all enclosed activity instances.
     * <p>
     * Possible reasons are: REASON_POTENTIAL_OWNER =1, REASON_EDITOR =2,
     * REASON_READER = 3, REASON_OWNER = 4, REASON_STARTER =6,
     * REASON_ADMINISTRATOR =7, REASON_ORIGINATOR =9, REASON_ESCALATION_RECEIVER
     * =10, REASON_POTENTIAL_INSTANCE_CREATOR = 11
     * <p>
     * The process instance must be in the running, failing, or terminating
     * execution state. The caller must be an administrator of the process
     * instance.
     *
     * @param processId    the process ID which can uniquely represent the process
     *                     instance
     * @param assignReason the assignment reason
     * @param userName     the user name
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public void createProcessWorkitem(String processId, int assignReason,
                                      String userName) throws Exception;

    /**
     * Deletes the specified work item for the specified activity instance.
     * Following restrictions apply: 1.A work item with an assignment reason
     * "owner" cannot be deleted. 2.A "reader" or "administrator" work item that
     * has been propagated from the process instance to the activity instance
     * can only be deleted on the process instance. 3.A work item for a specific
     * user cannot be deleted when the work item is assigned to everybody.
     * <p>
     * The activity instance must be in the claimed, waiting, ready, or stopped
     * execution state. The associated process instance must be in the running,
     * failing, or terminating execution state. The activity must be an invoke,
     * script, or staff activity.
     * <p>
     * The caller must be an administrator of the process instance.
     *
     * @param activityId   the activity ID which can uniquely represent the activity
     * @param assignReason the assignment reason
     * @param userName     the user name
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public void deleteActivityWorkitem(String activityId, int assignReason,
                                       String userName) throws Exception;

    /**
     * Deletes the specified work item for the specified process instance.
     * Following restrictions apply: 1. A work item with an assignment reason
     * "starter" cannot be deleted. 2. A work item with an assignment reason
     * "administrator" can only be deleted as long as it is not the last work
     * item with this assignment reason for the process instance. 3. A work
     * itemfor a specific user cannot be deleted when the work item is assigned
     * to everybody.
     * <p>
     * The deletion of a process reader or administrator work item is
     * automatically propagated to all enclosed activities.
     * <p>
     * The process instance must be in the running, failing, or terminating
     * execution state. The caller must be an administrator of the process
     * instance.
     *
     * @param processId    the process ID which can uniquely identify the process
     *                     instance
     * @param assignReason the assignment reason
     * @param userName     the user name
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public void deleteProcessWorkitem(String activityId, int assignReason,
                                      String userName) throws Exception;

    /**
     * Retrieves the specified variable of the specified process instance using
     * the process instance ID.
     * <p>
     * The caller must have at least reader authority for the process instance.
     *
     * @param processId    the process ID which can uniquely identify the process
     *                     instance
     * @param variableName the variable name
     * @return the object representation of the value of the variable. If the
     * value is DataObject, it will be represented as HashMap
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public Object getProcessVariableValue(String processId, String variableName)
            throws Exception;

    /**
     * Set the specified variable with the specified value for the specified
     * process instance. The original variable value can not be null, otherwise
     * we don't know the variable type.
     * <p>
     * The caller must be an administrator of the process instance.
     *
     * @param processId    the process ID which can uniquely identify the process
     *                     instance
     * @param variableName the variable name
     * @param parameters   representation of the variable value;If variable is
     *                     DataObject, HashMap is required
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public void setProcessVariable(String processId, String variableName,
                                   Object parameters) throws Exception;

    /**
     * get the list of users which are in the specified role for the process
     * instance
     * <p>
     * Possible reasons are: REASON_POTENTIAL_OWNER =1, REASON_EDITOR =2,
     * REASON_READER = 3, REASON_OWNER = 4, REASON_STARTER =6,
     * REASON_ADMINISTRATOR =7, REASON_ORIGINATOR =9, REASON_ESCALATION_RECEIVER
     * =10, REASON_POTENTIAL_INSTANCE_CREATOR = 11
     * <p>
     * The caller must have reader authority for the process instance, be the
     * process administrator, or be the business process administrator.
     *
     * @param processId the process ID which can uniquely identify the process
     *                  instance
     * @param role      the role of the user
     * @return the List of users
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public List getUsersInProcessRole(String processId, int role)
            throws Exception;

    /**
     * get the list of users which are in the specified role for the activity
     * instance
     * <p>
     * Possible reasons are: REASON_POTENTIAL_OWNER =1, REASON_EDITOR =2,
     * REASON_READER = 3, REASON_OWNER = 4, REASON_STARTER =6,
     * REASON_ADMINISTRATOR =7, REASON_ORIGINATOR =9, REASON_ESCALATION_RECEIVER
     * =10, REASON_POTENTIAL_INSTANCE_CREATOR = 11
     * <p>
     * The caller must have reader authority for the process instance, be the
     * process administrator, or be the business process administrator.
     *
     * @param activityId the activity ID which can uniquely identify the activity
     *                   instance
     * @param role       the role of the user
     * @return the list of users
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public List getUsersInActivityRole(String activityId, int role)
            throws Exception;

    /**
     * Indicates whether the logged-on user is a business process administrator
     * (system administrator).
     *
     * @return true if the user is in the specified role; otherwise return false
     * @throws Exception
     */
    public boolean isSystemAdministrator() throws Exception;

    /**
     * Indicates whether the logged-on user is a business process monitor
     * (system monitor).
     *
     * @return true if the user is in the specified role; otherwise return false
     * @throws Exception
     */
    public boolean isSystemMonitor() throws Exception;

    /**
     * pause the process instance with the specified processId
     * <p>
     * The process instance must be in the running or failing execution state.
     * The caller must be an administrator of the process instance.
     *
     * @param processId the processId which identify the process instance
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public void pauseProcess(String processId) throws Exception;

    /**
     * Resumes the specified top-level process instance and its subprocesses
     * using a string representation of the process instance ID.
     * <p>
     * The process instance must be in the suspended execution state. The caller
     * must be an administrator of the process instance.
     *
     * @param processId the processId which identify the process instance
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public void resumeProcess(String processId) throws Exception;

    /**
     * Terminates the specified top-level process instance, its subprocesses,
     * and its running, claimed, or waiting activities using the process
     * instance ID.
     * <p>
     * The process instance is immediately terminated without waiting for
     * outstanding activities to complete. Process instances that are terminated
     * are not compensated.
     * <p>
     * The process instance must be in the running, suspended, or failing
     * execution state.
     * <p>
     * Only an administrator of the process instance can terminate the process
     * instance.
     *
     * @param processId the process ID which can uniquely identify the process
     *                  instance
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public void terminateProcess(String processId) throws Exception;

    /**
     * Deletes the specified top-level process instance and its subprocesses
     * using a string representation of the process instance ID.
     * <p>
     * The process instance must be in the finished, terminated, compensated, or
     * failed execution state.
     * <p>
     * The caller must be an administrator of the process instance.
     *
     * @param processId
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public void deleteProcess(String processId) throws Exception;

    /**
     * get the number of tasks owned by the log-on user which satisfy the filter
     * condition
     *
     * @param filter the filter condition
     * @return the number of tasks
     * @throws Exception
     */
    public int getUndoTasksCount(HashMap filter) throws Exception;

    /**
     * get the tasks owned by the log-on user which satisfy the filter condition
     *
     * @param filter the where filter condition,
     * @return the List of tasks, for each task, it is represented as a HashMap
     * @throws Exception
     */
    public List getUndoTasksList(HashMap filter) throws Exception;

    /**
     * get the tasks owned by the log-on user which satisfy the filter
     * condition. And contrain the returned tasks under specific size(Currently
     * all tasks are sorted using task activiation time)
     *
     * @param startIndex from which index that a task is returned
     * @param length     the number of tasks to be returned
     * @param filter     the filter condition
     * @return the tasks which satisfy the filter condition
     * @throws Exception
     */
    public List getUndoTasksList(int startIndex, int length, HashMap filter,
                                 String taskBoName, HashMap taskField)
            throws Exception;

    /**
     * get the tasks owned by the log-on user which satisfy the filter
     * condition. And contrain the returned tasks under specific size(Currently
     * all tasks are sorted using task activiation time)
     *
     * @param startIndex from which index that a task is returned
     * @param length     the number of tasks to be returned
     * @param filter     the filter condition
     * @return the tasks which satisfy the filter condition
     * @throws Exception
     */
    public List getDoneTasksList(int startIndex, int length, HashMap filter,
                                 String taskBoName, HashMap taskField)
            throws Exception;

    /**
     * get the number of tasks administrated by the log-on user which satisfy
     * the filter condition
     *
     * @param filter the filter condition
     * @return the number of tasks
     * @throws Exception
     */
    public int getAdministratedTasksCount(HashMap filter) throws Exception;

    /**
     * get the tasks administrated by the log-on user which satisfy the filter
     * condition
     *
     * @param filter the filter condition
     * @return the List of tasks, for each task, it is represented as a HashMap
     * @throws Exception
     */
    public List getAdministratedTasks(HashMap filter) throws Exception;

    /**
     * get the tasks administrated by the log-on user which satisfy the filter
     * condition. And contrain the returned tasks under specific size(Currently
     * all tasks are sorted using task activiation time)
     *
     * @param startIndex from which index that a task is returned
     * @param length     the number of tasks to be returned
     * @param filter     the filter condition
     * @return the tasks which satisfy the filter condition
     * @throws Exception
     */
    public List getAdministratedTasks(int startIndex, int length, HashMap filter)
            throws Exception;

    /**
     * Claims a ready activity instance for user processing using a string
     * representation of the activity instance ID. And return the HashMap
     * representation of the input BO.
     * <p>
     * The activity instance must be a staff activity instance and be in the
     * ready execution state. The associated process instance must be in the
     * running execution state.
     * <p>
     * The caller must be a potential owner of the activity instance or an
     * administrator of the associated process instance.
     *
     * @param activityId the activity ID which can uniquely identify the activity
     * @return HashMap representation of the input BO for the activity instance
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public boolean claimTask(String tkiid, HashMap parameters) throws Exception;

    /**
     * Cancels the claim of an activity instance using a string representation
     * of the activity instance ID.
     * <p>
     * The activity instance must have been claimed. It is returned to the ready
     * execution state. Any previously stored output or fault message is
     * deleted.
     * <p>
     * The associated process instance must be in the running, failing, or
     * terminating execution state.
     * <p>
     * The caller must be the owner of the activity instance or an administrator
     * of the associated process instance.
     *
     * @param activityId the activity ID which can uniquely identify the activity
     *                   instance
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public void unclaimTask(String activityId) throws Exception;

    /**
     * Retrieves the input message of the specified activity instance using the
     * activity instance ID.
     * <p>
     * The input message can be retrieved in any execution state of the
     * associated process instance. The input message can be retrieved in the
     * claimed, expired, failed, finished, terminated, ready, or stopped
     * execution state of the activity instance. The activity can be an invoke
     * or staff activity.
     * <p>
     * The caller must have at least reader authority for the activity instance
     * or for the associated process instance.
     *
     * @param activityId the activity ID which can uniquely identify the activity
     *                   instance
     * @return the HashMap representation of the input message
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public HashMap getActivityInputData(String activityId) throws Exception;

    /**
     * Stores the output message of the specified activity instance into the
     * database using a string representation of the activity instance ID. The
     * output message is saved only, that is, navigation does not continue.
     * <p>
     * Any previously stored output or fault message is deleted. The activity
     * instance must be in the ready, claimed or stopped execution state and the
     * associated process instance must be in the running, failing, or
     * terminating execution state.
     * <p>
     * The caller must have editor authority for the activity instance, be the
     * potential owner or owner of the activity instance, or be the
     * administrator of the associated process instance.
     *
     * @param activityId the activity ID which can uniquely identify the activity
     *                   instance
     * @param parameters the HashMap representation of the output BO
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public void setTaskOutputData(String activityId, HashMap parameters)
            throws Exception;

    /**
     * Completes a claimed or ready activity instance using a string
     * representation of the activity instance ID
     * <p>
     * The activity instance must be in the claimed or ready execution state and
     * the associated process instance must be in the running, failing, or
     * terminating execution state.
     * <p>
     * The caller must be the owner or potential owner(depends on the activity
     * state) of the activity instance or an administrator of the associated
     * process instance.
     * <p>
     * Completion of an activity instance means that user processing finished
     * and that navigation of the process instance can continue.
     * <p>
     * When finished, output message values are used to continue navigation.
     *
     * @param activityId the activity ID which can uniquely identify the activity
     *                   instance
     * @param parameters the HashMap representation of the output BO
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public void finishTask(String activityId) throws Exception;

    /**
     * Completes a claimed or ready activity instance using a string
     * representation of the activity instance ID and passes the result of user
     * processing.
     * <p>
     * The activity instance must be in the claimed or ready execution state and
     * the associated process instance must be in the running, failing, or
     * terminating execution state.
     * <p>
     * The caller must be the owner or potential owner(depends on the activity
     * state) of the activity instance or an administrator of the associated
     * process instance.
     * <p>
     * Completion of an activity instance means that user processing finished
     * and that navigation of the process instance can continue. An output
     * message is passed to denote the successful execution of user processing.
     * The activity instance is put into the finished execution state.
     * <p>
     * When finished, output message values are used to continue navigation.
     *
     * @param activityId the activity ID which can uniquely identify the activity
     *                   instance
     * @param parameters the HashMap representation of the output BO
     * @throws InvalidParameterException
     * @throws UnauthorizedException
     * @throws Exception
     */
    public String finishTask(String activityId, HashMap parameters)
            throws Exception;


    /**
     * @return the activity instance id
     */
    public String getActivityInstanceId(String piid, String activityTemplateName)
            throws Exception;

    /**
     * get the number of tasks owned by the log-on user which satisfy the filter
     * condition
     *
     * @param filter the filter condition
     * @return the number of tasks
     * @throws Exception
     */
    public int getDoneTasksCount(HashMap filter) throws Exception;

    /**
     * 创建子任务
     *
     * @param TaskId     任务模板的任务ID
     * @param parameters 任务的启动参数
     * @return
     * @throws Exception
     */
    public boolean createSubTask(String taskId, HashMap parameters) throws Exception;

    /**
     * 获取节点事件接口列表
     *
     * @param tkid
     * @param sessionMap
     * @throws Exception
     */
    public List getActiveEventHandlers(String piid) throws Exception;

    /**
     * 根据任务ID号，获取task model对象值
     *
     * @param taskId 任务ID
     * @param task   taskModel对象
     * @throws Exception
     */
    public ITask getTaskModelByTaskId(String taskId, ITask task) throws Exception;

    public String triggerEvent(String piid, String processTemplateName,
                               String operationName, HashMap parameters) throws Exception;

    /**
     * 工单移交
     *
     * @param tkid
     * @param sessionMap
     * @throws Exception
     */
    public void transferWorkItem(String taskId, String fromOwnerUserId, String fromOwnerRoleId,
                                 String toOwnerUserId, String toOwnerRoleId, String operOrgType, HashMap map)
            throws Exception;

    /**
     * 流程回调
     *
     * @param processInstanceId
     * @param operationName
     * @param parameters
     * @throws Exception
     */
    public void reInvokeProcess(String processInstanceId,
                                String operationName, HashMap parameters) throws Exception;

    /**
     * 取消申请一个任务
     *
     * @param tkid
     * @param sessionMap
     * @throws Exception
     */
    public boolean cancelClaimTask(String tkid) throws Exception;

    /**
     * 根据条件查询流程数据
     *
     * @param selectClause
     * @param whereClause
     * @return
     * @throws Exception
     */
    public HashMap queryAll(String selectClause, String whereClause) throws Exception;

}

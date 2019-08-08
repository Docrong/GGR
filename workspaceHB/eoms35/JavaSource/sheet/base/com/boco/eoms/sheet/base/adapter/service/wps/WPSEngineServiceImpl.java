/*
 * Created on 2007-9-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.adapter.service.wps;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ofbiz.core.util.GeneralException;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.sheet.base.adapter.service.IWorkflowEngineService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.ibm.bpe.api.AIID;
import com.ibm.bpe.api.ActivityInstanceData;
import com.ibm.bpe.api.ActivityServiceTemplateData;
import com.ibm.bpe.api.BusinessFlowManagerService;
import com.ibm.bpe.api.ClientObjectWrapper;
import com.ibm.bpe.api.CustomProperty;
import com.ibm.bpe.api.EventHandlerTemplateData;
import com.ibm.bpe.api.InvalidParameterException;
import com.ibm.bpe.api.PIID;
import com.ibm.bpe.api.ProcessInstanceData;
import com.ibm.bpe.api.ProcessTemplateData;
import com.ibm.bpe.api.QueryResultSet;
import com.ibm.bpe.api.WorkItemData;
import com.ibm.task.api.HumanTaskManagerService;
import com.ibm.task.api.TKIID;
import com.ibm.task.api.Task;
import com.ibm.task.api.WorkItem;
import commonj.sdo.DataObject;
import commonj.sdo.Property;

/**
 * @author IBM_USER
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class WPSEngineServiceImpl implements IWorkflowEngineService {
    private static final String SOURCE_CLASS = WPSBusinessFlowAdapterServiceImpl.class
            .getName();

    private static final Logger LOGGER = Logger.getLogger(SOURCE_CLASS);

    private BusinessFlowManagerService workflowManager;

    private HumanTaskManagerService humanTaskManager;

    private HashMap processCacheMap = new HashMap();

    /**
     * @return Returns the processCacheMap.
     */
    public HashMap getProcessCacheMap() {
        return processCacheMap;
    }

    /**
     * @param processCacheMap The processCacheMap to set.
     */
    public void setProcessCacheMap(HashMap processCacheMap) {
        this.processCacheMap = processCacheMap;
    }

    /**
     * @return the workflowManager
     */
    public BusinessFlowManagerService getWorkflowManager() {
        return workflowManager;
    }

    /**
     * @param workflowManager the workflowManager to set
     */
    public void setWorkflowManager(BusinessFlowManagerService workflowManager) {
        this.workflowManager = workflowManager;
    }

    /**
     * @return Returns the humanTaskManager.
     */
    public HumanTaskManagerService getHumanTaskManager() {
        return humanTaskManager;
    }

    /**
     * @param humanTaskManager The humanTaskManager to set.
     */
    public void setHumanTaskManager(HumanTaskManagerService humanTaskManager) {
        this.humanTaskManager = humanTaskManager;
    }

    /**
     * query and return the number of process templates defined in the database
     *
     * @return the number of process templates
     * @throws GeneralException thrown by the method if any exception happened
     */
    public int getProcessTemplatesCount() throws Exception {
        /** 1. prepare for the method call */
        int result = -1;
        /** 2. query the process templates and return the count */
        try {
            ProcessTemplateData[] processTemplates = workflowManager
                    .queryProcessTemplates(null, null, null, null);
            result = processTemplates.length;
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
        return result;
    }

    /**
     * query and return the process templates with the specified size
     *
     * @param size the number of process templates returned
     * @return list of process templates name
     * @throws Exception thrown by the method if any exception happened
     */
    public List getProcessTemplates(int size) throws Exception {
        String SOURCE_METHOD = "getProcessTemplates";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_METHOD, SOURCE_METHOD,
                new Object[]{new Integer(size)});

        /** 1. Prepare for the method call */
        List processTemplateList = new ArrayList();
        Integer threshold = null;
        if (size > 0) {
            threshold = new Integer(size);
        }

        /** 2. Query and return the process templates */
        try {
            ProcessTemplateData[] processTemplates = workflowManager
                    .queryProcessTemplates(null, null, threshold, null);
            for (int i = 0; i < processTemplates.length; i++) {
                ProcessTemplateData processTemplateData = processTemplates[i];
                processTemplateList.add(processTemplateData.getName());
            }
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_METHOD, SOURCE_METHOD, processTemplateList);
        return processTemplateList;
    }

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
            throws Exception {
        String SOURCE_METHOD = "getProcessTemplateInfo";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD,
                new Object[]{processTemplateName});

        /** 1. Prepare for the method call */
        HashMap map = new HashMap();
        try {
            /** 2. Query and return the ProcessTemplateData */
            String whereClause = "PROCESS_TEMPLATE.NAME = '"
                    + processTemplateName + "'";
            ProcessTemplateData[] processTemplates = workflowManager
                    .queryProcessTemplates(whereClause, null, null, null);

            /** 3. retrieve the information from the Object if available */
            if (processTemplates.length > 0) {
                ProcessTemplateData processTemplateData = processTemplates[0];
                // TODO retrieve the corresponding data here
                map.put("processTemplateData", processTemplateData);
            }
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, map);
        return map;
    }

    /**
     * get the process instance number with the specified process template name
     *
     * @param processTemplateName the specified process template name
     * @return the number of process instances
     * @throws Exception thrown by the method if any exception happened
     */
    public int getProcessInstancesCountByProcessTemplateName(
            String processTemplateName) throws Exception {
        String SOURCE_METHOD = "getProcessInstancesCountByProcessTemplateName";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD,
                new Object[]{processTemplateName});

        /** 1. Prepare for the method call */
        int result = -1;

        /** 2. query and return the process instances count */
        try {
            String selectClause = WPSQueryHelper
                    .getProcessInstanceCountSelectClause();
            String whereClause = " (PROCESS_INSTANCE.STATE = PROCESS_INSTANCE.STATE.STATE_RUNNING) "
                    + " AND (PROCESS_INSTANCE.TEMPLATE_NAME= '"
                    + processTemplateName + "')";
            QueryResultSet resultSet = workflowManager.query(selectClause,
                    whereClause, "", null, null);
            if (resultSet.next()) {
                result = resultSet.getInteger(1).intValue();
            }

        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, new Integer(result));
        return result;

    }

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
            String processTemplateName, int size) throws Exception {
        String SOURCE_METHOD = "getProcessInstancesByProcessTemplateName";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{
                processTemplateName, new Integer(size)});

        /** 1. Prepare for the method call */
        List processInstanceList = new ArrayList();
        try {

            /** 2. query and return the process instances */
            String selectClause = WPSQueryHelper
                    .getProcessInstanceSelectClause();
            String whereClause = " (PROCESS_INSTANCE.STATE = PROCESS_INSTANCE.STATE.STATE_RUNNING) "
                    + " AND (PROCESS_INSTANCE.TEMPLATE_NAME= '"
                    + processTemplateName + "')";
            Integer threshold = null;
            if (size > 0) {
                threshold = new Integer(size);
            }
            QueryResultSet resultSet = workflowManager.query(selectClause,
                    whereClause, "", threshold, null);
            processInstanceList = WPSQueryHelper
                    .parseProcessInstanceQueryResult(resultSet);

        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, processInstanceList);
        return processInstanceList;
    }

    /**
     * query and return the number of running process instances started by the
     * current user
     *
     * @return the number of process instances in running states
     * @throws Exception thrown by the method if any exception happened
     */
    public int getStartedProcessInstancesCount() throws Exception {
        String SOURCE_METHOD = "getStartedProcessInstances";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD);

        /** 1. Prepare for the method call */
        int result = -1;

        /** 2. query and return the process instances count */
        try {
            String selectClause = WPSQueryHelper
                    .getProcessInstanceCountSelectClause();
            String whereClause = "PROCESS_INSTANCE.STATE = PROCESS_INSTANCE.STATE.STATE_RUNNING AND "
                    + " (WORK_ITEM.REASON = WORK_ITEM.REASON.REASON_STARTER)";
            QueryResultSet queryResultSet = workflowManager.query(selectClause,
                    whereClause, "", null, null);

            if (queryResultSet.next()) {
                result = queryResultSet.getInteger(1).intValue();
            }
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, new Integer(result));
        return result;
    }

    /**
     * query and return the process instances started by the current user, and
     * limit the number of process instances with the specified size.
     *
     * @param size the number of process instances returned
     * @return the process instances started by current user
     * @throws Exception thrown by the method if any exception happened
     */
    public List getStartedProcessInstances(int size) throws Exception {
        String SOURCE_METHOD = "getStartedProcessInstances";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD);

        /** 1. Prepare for the method call */
        List result = new ArrayList();
        try {
            /** 2. query and return the process instances */
            String selectClause = WPSQueryHelper
                    .getProcessInstanceSelectClause();
            String whereClause = "PROCESS_INSTANCE.STATE = PROCESS_INSTANCE.STATE.STATE_RUNNING AND "
                    + " (WORK_ITEM.REASON = WORK_ITEM.REASON.REASON_STARTER)";
            Integer threshold = null;
            if (size > 0) {
                threshold = new Integer(size);
            }

            QueryResultSet queryResultSet = workflowManager.query(selectClause,
                    whereClause, "", threshold, null);
            /**
             * 3. retrieve the process instances info and put them into return
             * list
             */
            result = WPSQueryHelper
                    .parseProcessInstanceQueryResult(queryResultSet);
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, result);
        return result;

    }

    /**
     * query and return the number of running process instances administrated by
     * the current user
     *
     * @return the number of running processes administrated by current user
     * @throws Exception
     */
    public int getAdministratedProcessInstancesCount() throws Exception {
        String SOURCE_METHOD = "getAdministratedProcessInstancesCount";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD);

        /** 1. Prepare for the method call */
        int result = -1;
        /** 2. Query and return the number of running process instances */
        try {
            String selectClause = WPSQueryHelper
                    .getProcessInstanceCountSelectClause();
            String whereClause = "PROCESS_INSTANCE.STATE = PROCESS_INSTANCE.STATE.STATE_RUNNING AND "
                    + " (WORK_ITEM.REASON = WORK_ITEM.REASON.REASON_ADMINISTRATOR)";
            QueryResultSet queryResultSet = workflowManager.query(selectClause,
                    whereClause, "", null, null);

            if (queryResultSet.next()) {
                result = queryResultSet.getInteger(1).intValue();
            }
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }
        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, new Integer(result));
        return result;
    }

    /**
     * query and return the process instances administrated by current user, and
     * limit the number of process instances with the specified size.
     *
     * @param size the number of process instances returned
     * @return the process instances administrated by current user
     * @throws Exception thrown by the method if any exception happened
     */
    public List getAdministratedProcessInstances(int size) throws Exception {
        String SOURCE_METHOD = "getAdministratedProcessInstances";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Integer(size));

        /** 1. Prepare for the method call */
        List result = new ArrayList();
        try {

            /** 2. Query and return the process instances */
            String selectClause = WPSQueryHelper
                    .getProcessInstanceSelectClause();
            String whereClause = "PROCESS_INSTANCE.STATE = PROCESS_INSTANCE.STATE.STATE_RUNNING AND "
                    + " (WORK_ITEM.REASON = WORK_ITEM.REASON.REASON_ADMINISTRATOR)";
            Integer threshold = null;
            if (size > 0) {
                threshold = new Integer(size);
            }
            QueryResultSet queryResultSet = workflowManager.query(selectClause,
                    whereClause, "", threshold, null);

            /**
             * 3. retrieve the process instances info and put them into the
             * return list
             */
            result = WPSQueryHelper
                    .parseProcessInstanceQueryResult(queryResultSet);
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, result);
        return result;
    }

    /**
     * query and return the number of subProcess of the specified process
     * instance
     *
     * @param parentProcessName the name of parent process instance
     * @return the number of subProcess
     * @throws Exception thrown by the method if any exception happened
     */
    public int getSubProcessInstancesCount(String parentProcessName)
            throws Exception {
        String SOURCE_METHOD = "getSubProcessInstancesCount";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD,
                new Object[]{parentProcessName});

        /** 1. Prepare for the method call */
        int result = -1;
        /** 2. Query and return the number of subProcess */
        try {
            String selectClause = WPSQueryHelper
                    .getProcessInstanceCountSelectClause();
            String whereClause = "PROCESS_INSTANCE.PARENT_NAME = '"
                    + parentProcessName + "'";
            QueryResultSet queryResultSet = workflowManager.query(selectClause,
                    whereClause, "", null, null);

            if (queryResultSet.next()) {
                result = queryResultSet.getInteger(1).intValue();
            }
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, new Integer(result));
        return result;
    }

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
            throws Exception {
        String SOURCE_METHOD = "getSubProcessInstances";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{
                parentProcessName, new Integer(size)});

        /** 1. Prepare for the method call */
        List result = new ArrayList();
        try {

            /** 2. Query and return the process instances */
            String selectClause = WPSQueryHelper
                    .getProcessInstanceSelectClause();
            String whereClause = "PROCESS_INSTANCE.PARENT_NAME = '"
                    + parentProcessName + "'";
            Integer threshold = null;
            if (size > 0) {
                threshold = new Integer(size);
            }
            QueryResultSet queryResultSet = workflowManager.query(selectClause,
                    whereClause, "", threshold, null);

            /** 3. Retrieve the process instances info and put them into the list */
            result = WPSQueryHelper
                    .parseProcessInstanceQueryResult(queryResultSet);
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, result);
        return result;

    }

    /**
     * query and return the number of process instances which satisfy the filter
     * conditions
     *
     * @param filter the filter condition
     * @return the number of process instances
     * @throws Exception thrown by the method if any exception happened
     */
    public int getProcessInstancesCount(HashMap filter) throws Exception {
        String SOURCE_METHOD = "getProcessInstancesCount";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{filter});

        int result = -1;
        try {
            String selectClause = WPSQueryHelper
                    .getProcessInstanceCountSelectClause();
            String whereClause = WPSQueryHelper
                    .getProcessInstanceWhereClause(filter);
            QueryResultSet queryResultSet = workflowManager.query(selectClause,
                    whereClause, "", null, null);

            if (queryResultSet.next()) {
                result = queryResultSet.getInteger(1).intValue();
            }
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, new Integer(result));
        return result;
    }

    /**
     * query and return the process instances which satisfy the filter
     * conditions
     *
     * @param filter the filter conditions
     * @return the process instances
     * @throws Exception thrown by the method if any exception happened
     */
    public List getProcessInstances(HashMap filter) throws Exception {
        String SOURCE_METHOD = "getProcessInstances";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{filter});

        List result = new ArrayList();
        try {

            /** 2. Query and return the process instances */
            String selectClause = WPSQueryHelper
                    .getProcessInstanceSelectClause();
            String whereClause = WPSQueryHelper
                    .getProcessInstanceWhereClause(filter);
            QueryResultSet queryResultSet = workflowManager.query(selectClause,
                    whereClause, "", null, null);

            /** 3. Retrieve the process instances info and put them into the list */
            result = WPSQueryHelper
                    .parseProcessInstanceQueryResult(queryResultSet);
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }
        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, result);
        return result;
    }

    /**
     * get the specified process instance information
     *
     * @param processId the processId which can uniquely identify the process instance
     * @return the HashMap representation of the process instance information
     * @throws Exception thrown by the method if any exception happened
     */
    public HashMap getProcessInstanceInfo(String processId) throws Exception {
        String SOURCE_METHOD = "getProcessInstanceInfo";
        String EXCEPTION_MESSAGE = "";
        LOGGER
                .entering(SOURCE_CLASS, SOURCE_METHOD,
                        new Object[]{processId});

        /** 1. Prepare for the method call */
        HashMap instanceInfo = new HashMap();

        /** 2. retrieve the process instance info and put it in the HashMap */
        try {
            ProcessInstanceData processInstanceData = workflowManager
                    .getProcessInstance(processId);
            // TODO retrieve information here
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, instanceInfo);
        return instanceInfo;
    }

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
                                 String operationName, HashMap parameters) throws Exception {
        String SOURCE_METHOD = "triggerProcess";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{
                processTemplateName, operationName, parameters});
        String pid = "";
        /** 1. Get the process template. */
        ProcessTemplateData processTemplate = null;

        ActivityServiceTemplateData startActivity = null;
        HashMap startActivityMap = new HashMap();
        String startActivityName = processTemplateName + "_" + operationName;

//		if (this.getProcessCacheMap() != null
//				&& this.getProcessCacheMap().get(startActivityName) != null) {
//			startActivity = (ActivityServiceTemplateData) this
//					.getProcessCacheMap().get(startActivityName);
//		} else {
//			System.out.println("Startactivity is null");
//		}

        if (startActivity == null) {
            try {
                String whereClause = "PROCESS_TEMPLATE.NAME = '"
                        + processTemplateName + "'";
                String orderByClause = "PROCESS_TEMPLATE.VALID_FROM DESC";
                ProcessTemplateData[] processTemplates = workflowManager
                        .queryProcessTemplates(whereClause, orderByClause,
                                null, null);
                if (processTemplates.length > 0) {
                    processTemplate = processTemplates[0];
                }
            } catch (Exception e) {
                LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                        EXCEPTION_MESSAGE, e.getCause());
                throw new Exception(e.getLocalizedMessage());
            }
            if (processTemplate == null) {
                throw new Exception("Invalid Process Name is specified");
            }
            /** 2. Get the startActivity */

            try {
                ActivityServiceTemplateData[] startActivities = workflowManager
                        .getStartActivities(processTemplate.getID());
                for (int i = 0; i < startActivities.length; i++) {
                    ActivityServiceTemplateData activity = startActivities[i];
                    if (activity.getOperationName().equals(operationName)) {
                        startActivity = activity;
                        this.processCacheMap.put(startActivityName,
                                startActivity);
                        break;
                    }
                }
            } catch (Exception e) {
                LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                        EXCEPTION_MESSAGE, e.getCause());
                throw new Exception(e.getLocalizedMessage());
            }
            if (startActivity == null) {
                throw new Exception("Invalid Operation Name is specified");
            }
        }

        /** 3. set the input message and start the process */
        com.ibm.bpe.api.ClientObjectWrapper clientObjectWrapper = null;
        try {
            clientObjectWrapper = workflowManager.createMessage(startActivity
                    .getServiceTemplateID(), startActivity
                    .getActivityTemplateID(), startActivity
                    .getInputMessageTypeName());

            // set the initial values in the input message
            if (clientObjectWrapper.getObject() != null
                    && clientObjectWrapper.getObject() instanceof DataObject) {
                DataObject inputMessage = (DataObject) clientObjectWrapper
                        .getObject();
                WPSStaticMethod.createDataObject(inputMessage, parameters);
            }

            // create a process instance
            PIID piid = workflowManager.sendMessage(startActivity
                    .getServiceTemplateID(), startActivity
                    .getActivityTemplateID(), clientObjectWrapper);
            pid = piid.toString();

            // System.out.println("receiveMessage begin");
            // String
            // receiveMessage=StaticMethod.nullObject2String(WPSStaticMethod.receiveMessage(pid));
            // System.out.println("receiveMessage
            // end,receiveMessage="+receiveMessage);
            // String sendMessage="create first human task ok";
            // if(!receiveMessage.equals(sendMessage)){
            // System.out.println("receiveMessage error");
            // throw new Exception("trigger process error!");
            // }

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("++++++triggerProcess wpsed++++++++");
            throw new Exception(e.getMessage());

        }
        return pid;
    }

    public Map getVariable(String identifier, String variableName)
            throws Exception {
        ClientObjectWrapper clientObjectWrapper = null;
        Map objectMap = new HashMap();
        try {
            clientObjectWrapper = workflowManager.getVariable(identifier,
                    variableName);

            if (clientObjectWrapper.getObject() != null
                    && clientObjectWrapper.getObject() instanceof DataObject) {
                DataObject variable = (DataObject) clientObjectWrapper
                        .getObject();
                objectMap = WPSStaticMethod.createHashMap(variable);
            } else {
                objectMap.put(variableName, clientObjectWrapper.getObject());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return objectMap;
    }

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
            throws Exception {
        String SOURCE_METHOD = "triggerProcess";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{
                processTemplateName, operationName, parameters, properties});
        /** 1. start the process instance */
        String pid = triggerProcess(processTemplateName, operationName,
                parameters);

        /** 2. set the custom properties */
        setProcessInstanceCustomProperties(pid, properties);

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, pid);
        return pid;
    }

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
            throws Exception {
        String SOURCE_METHOD = "getProcessInstanceCustomPropertyNameList";
        String EXCEPTION_MESSAGE = "";
        LOGGER
                .entering(SOURCE_CLASS, SOURCE_METHOD,
                        new Object[]{processId});

        /** 1. Prepare for the method call */
        List result = null;

        /** 2. return the custom property name list */
        try {
            result = workflowManager.getCustomPropertyNames(processId);
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, result);
        return result;
    }

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
            throws Exception {
        String SOURCE_METHOD = "getProcessInstanceCustomProperties";
        String EXCEPTION_MESSAGE = "";
        LOGGER
                .entering(SOURCE_CLASS, SOURCE_METHOD,
                        new Object[]{processId});

        /** 1. Prepare for the method call */
        HashMap result = new HashMap();

        /** 2. Return the list of custom properties */
        try {
            List tempResult = workflowManager.getCustomProperties(processId);
            for (int i = 0; i < tempResult.size(); i++) {
                CustomProperty property = (CustomProperty) tempResult.get(i);
                result.put(property.getName(), property.getValue());
            }
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }
        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, result);
        return result;

    }

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
                                                        String propertyName) throws Exception {
        String SOURCE_METHOD = "getProcessInstanceCustomPropertyValue";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{processId,
                propertyName});

        /** 1. Prepare for the method call */
        String result = null;

        /** 2. Retrieve the custom property value */
        try {
            result = workflowManager.getCustomProperty(processId, propertyName);
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, result);
        return result;
    }

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
                                                 String propertyName, String propertyValue) throws Exception {
        String SOURCE_METHOD = "setProcessInstanceCustomProperty";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{processId,
                propertyName, propertyValue});

        /** 1. Prepare for the method call */

        /** 2. Set the custom property for the process instance */
        try {
            workflowManager.setCustomProperty(processId, propertyName,
                    propertyValue);
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);
    }

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
                                                   HashMap properties) throws Exception {
        String SOURCE_METHOD = "setProcessInstanceCustomProperties";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{processId,
                properties});

        Iterator keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String value = (String) properties.get(key);
            setProcessInstanceCustomProperty(processId, key, value);
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);

    }

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
                                       String userName) throws Exception {
        String SOURCE_METHOD = "createActivityWorkitem";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{activityId,
                new Integer(assignReason), userName});

        try {
            workflowManager.createWorkItem(activityId, assignReason, userName);
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);
    }

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
                                      String userName) throws Exception {
        String SOURCE_METHOD = "createProcessWorkitem";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{processId,
                new Integer(assignReason), userName});

        try {
            workflowManager.createWorkItem(processId, assignReason, userName);
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);
    }

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
                                       String userName) throws Exception {
        String SOURCE_METHOD = "deleteActivityWorkitem";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{activityId,
                new Integer(assignReason), userName});

        try {
            workflowManager.deleteWorkItem(activityId, assignReason, userName);
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);
    }

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
                                      String userName) throws Exception {
        String SOURCE_METHOD = "deleteActivityWorkitem";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{activityId,
                new Integer(assignReason), userName});

        try {
            workflowManager.deleteWorkItem(activityId, assignReason, userName);
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }
        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);
    }

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
            throws Exception {
        String SOURCE_METHOD = "getProcessVariableValue";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{processId,
                variableName});

        /** 1. Prepare for the method call */
        HashMap map = new HashMap();
        try {

            /** 2. Get the Object wrapper of the variable */
            ClientObjectWrapper wrapper = workflowManager.getVariable(
                    processId, variableName);

            /** 3. Transform the Object wrapper to HashMap and return */
            if (wrapper.getObject() != null
                    && wrapper.getObject() instanceof DataObject) {
                DataObject dataObject = (DataObject) wrapper.getObject();
                map = WPSStaticMethod.createHashMap(dataObject);
            } else if (wrapper.getObject() != null) {
                LOGGER
                        .exiting(SOURCE_CLASS, SOURCE_METHOD, wrapper
                                .getObject());
                return wrapper.getObject();
            }
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, map);
        return map;
    }

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
                                   Object parameters) throws Exception {
        String SOURCE_METHOD = "setProcessVariable";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{processId,
                variableName, parameters});

        /** 1. Prepare for the method call */
        try {

            /**
             * 2. Set the DataObject representation of the variable value in the
             * case of DataObject
             */
            ClientObjectWrapper wrapper = workflowManager.getVariable(
                    processId, variableName);
            if (wrapper.getObject() != null
                    && wrapper.getObject() instanceof DataObject) {
                /** 2.1 get the original DataObject */
                DataObject dataObject = (DataObject) wrapper.getObject();
                /** 2.2 Unset the orginal DataObject */
                List properties = dataObject.getType().getProperties();
                for (int i = 0; i < properties.size(); i++) {
                    Property property = (Property) properties.get(i);
                    dataObject.unset(property);
                }
                /** 2.3 create the new DataObject and set the value */
                WPSStaticMethod.createDataObject(dataObject,
                        (HashMap) parameters);
                workflowManager.setVariable(processId, variableName, wrapper);
            }
            /** 3. Set the variable directly in the case of Simple type */
            else if (wrapper.getObject() != null) {
                workflowManager.setVariable(processId, variableName, wrapper);
            }
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);
    }

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
            throws Exception {
        String SOURCE_METHOD = "getUsersInProcessRole";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{processId,
                new Integer(role)});

        /** 1. Prepare for the method call */
        List userList = new ArrayList();
        String EVERYBODY = "everybody";
        try {
            /**
             * 2. Retrieve all work item data associated with the process
             * instance
             */
            WorkItemData[] workitemData = workflowManager
                    .getAllWorkItems(processId);
            /** 3. Retrieve the user list which is the specified role */
            for (int i = 0; i < workitemData.length; i++) {
                /** if it is assigned to everybody, return everybody */
                if (workitemData[i].getIsAssignedToEverybody()) {
                    userList.clear();
                    userList.add(EVERYBODY);
                    break;
                } else if (workitemData[i].getAssignmentReason() == role) {
                    userList.add(workitemData[i].getOwnerID());
                }
            }
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);
        return userList;
    }

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
            throws Exception {
        String SOURCE_METHOD = "getUsersInActivityRole";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{activityId,
                new Integer(role)});

        /** 1. Prepare for the method call */
        List userList = new ArrayList();
        String EVERYBODY = "everybody";
        try {

            /**
             * 2. Retrieve the work item datas assoicated with the activity
             * instance
             */
            WorkItemData[] workitemData = workflowManager
                    .getAllWorkItems(activityId);
            /** 3. Retrieve the users which are in the specified role */
            for (int i = 0; i < workitemData.length; i++) {
                if (workitemData[i].getIsAssignedToEverybody()) {
                    userList.clear();
                    userList.add(EVERYBODY);
                    break;
                } else if (workitemData[i].getAssignmentReason() == role) {
                    userList.add(workitemData[i].getOwnerID());
                }
            }
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);
        return userList;
    }

    /**
     * Indicates whether the logged-on user is a business process administrator
     * (system administrator).
     *
     * @return true if the user is in the specified role; otherwise return false
     * @throws Exception
     */
    public boolean isSystemAdministrator() throws Exception {
        String SOURCE_METHOD = "isSystemAdministrator";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD);

        boolean result = false;
        try {
            result = workflowManager.isBusinessProcessAdministrator();
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, new Boolean(result));
        return result;
    }

    /**
     * Indicates whether the logged-on user is a business process monitor
     * (system monitor).
     *
     * @return true if the user is in the specified role; otherwise return false
     * @throws Exception
     */
    public boolean isSystemMonitor() throws Exception {
        String SOURCE_METHOD = "isSystemMonitor";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD);

        boolean result = false;
        try {
            result = workflowManager.isBusinessProcessMonitor();
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, new Boolean(result));
        return result;
    }

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
    public void pauseProcess(String processId) throws Exception {

        String SOURCE_METHOD = "pauseProcess";
        String EXCEPTION_MESSAGE = "";
        LOGGER
                .entering(SOURCE_CLASS, SOURCE_METHOD,
                        new Object[]{processId});

        try {
            workflowManager.suspend(processId);
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }
        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);
    }

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
    public void resumeProcess(String processId) throws Exception {
        String SOURCE_METHOD = "resumeProcess";
        String EXCEPTION_MESSAGE = "";
        LOGGER
                .entering(SOURCE_CLASS, SOURCE_METHOD,
                        new Object[]{processId});

        try {
            workflowManager.resume(processId);
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);
    }

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
    public void terminateProcess(String processId) throws Exception {
        String SOURCE_METHOD = "terminateProcess";
        String EXCEPTION_MESSAGE = "";
        LOGGER
                .entering(SOURCE_CLASS, SOURCE_METHOD,
                        new Object[]{processId});

        try {
            workflowManager.forceTerminate(processId);
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);
    }

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
    public void deleteProcess(String processId) throws Exception {
        String SOURCE_METHOD = "deleteProcess";
        String EXCEPTION_MESSAGE = "";
        LOGGER
                .entering(SOURCE_CLASS, SOURCE_METHOD,
                        new Object[]{processId});

        try {
            workflowManager.delete(processId);
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);
    }

    /**
     * get the number of tasks owned by the log-on user which satisfy the filter
     * condition
     *
     * @param filter the filter condition
     * @return the number of tasks
     * @throws Exception
     */
    public int getUndoTasksCount(HashMap filter) throws Exception {
        String SOURCE_METHOD = "getOwnTasksCount";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{filter});

        int count = -1;
        try {

            /** 1. Construct the additional condition */
            String filterClause = WPSQueryHelper
                    .getActivityWorkitemWhereClause(
                            "PROCESS_INSTANCE.TEMPLATE_NAME", filter)
                    + " and TASK.IS_WAIT_FOR_SUB_TK=0";

            /** 2. query for the count */
            QueryResultSet result = null;

            System.out.println("before getUndoTasksCount:"
                    + StaticMethod
                    .getCurrentDateTime("yyyy-MM-dd HH:mm:ss.SSS"));
            String selectClause = "COUNT(DISTINCT TASK.TKIID)";// (COUNT(DISTINCT
            // ACTIVITY.AIID))
            String whereClause = " (PROCESS_INSTANCE.STATE = PROCESS_INSTANCE.STATE.STATE_RUNNING) AND ((WORK_ITEM.REASON = WORK_ITEM.REASON.REASON_POTENTIAL_OWNER AND TASK.STATE = TASK.STATE.STATE_READY) OR TASK.STATE = TASK.STATE.STATE_CLAIMED) "
                    + " AND (TASK.KIND = TASK.KIND.KIND_PARTICIPATING OR TASK.KIND = TASK.KIND.KIND_HUMAN)";
            // filterClause = " and (PROCESS_INSTANCE.TEMPLATE_NAME in
            // ('BusinessDesign')) ";
            System.out.println("filterClause==" + filterClause);
            result = workflowManager.query(selectClause, whereClause
                    + filterClause, "", null, null);
            System.out.println("after getUndoTasksCount:"
                    + StaticMethod
                    .getCurrentDateTime("yyyy-MM-dd HH:mm:ss.SSS"));

            /** 3. get the count */
            if (result.next()) {
                count = result.getInteger(1).intValue();
            }
            System.out.println("count==" + count);
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_METHOD, SOURCE_METHOD, new Integer(count));
        return count;
    }

    /**
     * get the tasks owned by the log-on user which satisfy the filter condition
     *
     * @param filter the where filter condition,
     * @return the List of tasks, for each task, it is represented as a HashMap
     * @throws Exception
     */
    public List getUndoTasksList(HashMap filter) throws Exception {
        String SOURCE_METHOD = "getOwnTasks";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{filter});
        List retList = new ArrayList();
        try {
            /** 1. Construct the addition condition */
            String filterClause = WPSQueryHelper
                    .getActivityWorkitemWhereClause("PROCESS_TEMPLATE.NAME",
                            filter);
            /** 2. Query the task list */
            QueryResultSet result = null;
            String selectClause = WPSQueryHelper
                    .getActivityWorkitemSelectClause();
            String whereClause = " ((WORK_ITEM.REASON = WORK_ITEM.REASON.REASON_POTENTIAL_OWNER AND TASK.STATE = TASK.STATE.STATE_READY) OR TASK.STATE = TASK.STATE.STATE_CLAIMED) "
                    + " AND (TASK.KIND = TASK.KIND.KIND_PARTICIPATING OR TASK.KIND = TASK.KIND.KIND_HUMAN)";

            String orderClause = "TASK.ACTIVATED DESC";
            result = workflowManager.query(selectClause, whereClause
                    + filterClause, orderClause, null, null);

            /** 3. Extract the result from the list and put them into return List */
            retList = WPSQueryHelper.parseActivityWorkitemQueryResult(result);

        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, retList);
        return retList;
    }

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
                                 String taskBoName, HashMap taskField) throws Exception {
        String SOURCE_METHOD = "getOwnTasks";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{
                new Integer(startIndex), new Integer(length), filter});

        List retList = new ArrayList();
        try {

            /** 1. Construct the addition condition */
            String filterClause = WPSQueryHelper
                    .getActivityWorkitemWhereClause("PROCESS_TEMPLATE.NAME",
                            filter)
                    + " and TASK.IS_WAIT_FOR_SUB_TK=0";
            /** 2. Query the task list */
            QueryResultSet result = null;
            String selectClause = WPSQueryHelper.getTaskSelectClause();
            // selectClause = " DISTINCT
            // TASK.TKIID,TASK.NAME,PROCESS_TEMPLATE.NAME,TASK.STATE,TASK.TKIID,TASK_TEMPL_DESC.DISPLAY_NAME,PROCESS_INSTANCE.PIID,TASK.TKIID,TASK.ACTIVATED";
            System.out.println("selectCaluse =" + selectClause);
            System.out.println("before getUndoTasksList:"
                    + StaticMethod
                    .getCurrentDateTime("yyyy-MM-dd HH:mm:ss.SSS"));

            // String whereClause = " ((WORK_ITEM.REASON =
            // WORK_ITEM.REASON.REASON_POTENTIAL_OWNER AND "
            // + "TASK.STATE = TASK.STATE.STATE_READY) OR "
            // + "(WORK_ITEM.REASON=WORK_ITEM.REASON.REASON_OWNER AND TASK.STATE
            // = "
            // + "TASK.STATE.STATE_CLAIMED)) AND (TASK.KIND =
            // TASK.KIND.KIND_PARTICIPATING "
            // + "OR TASK.KIND = TASK.KIND.KIND_HUMAN)";
            String whereClause = " ((WORK_ITEM.REASON = WORK_ITEM.REASON.REASON_POTENTIAL_OWNER AND TASK.STATE = TASK.STATE.STATE_READY) OR TASK.STATE = TASK.STATE.STATE_CLAIMED) "
                    + " AND (TASK.KIND = TASK.KIND.KIND_PARTICIPATING OR TASK.KIND = TASK.KIND.KIND_HUMAN)";

            String orderClause = "TASK.ACTIVATED DESC";

            Integer skipTuples = new Integer(startIndex);
            Integer threshold = new Integer(startIndex + length);
            // filterClause = " and (PROCESS_TEMPLATE.NAME in
            // ('BusinessDesign')) ";

            result = workflowManager.query(selectClause, whereClause
                    + filterClause, orderClause, skipTuples, threshold, null);

            System.out.println("after getUndoTasksList:"
                    + StaticMethod
                    .getCurrentDateTime("yyyy-MM-dd HH:mm:ss.SSS"));

            /** 3. Extract the result from the list */
            retList = WPSQueryHelper.parseTaskQueryResult(result, taskBoName,
                    taskField);

        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, retList);
        return retList;
    }

    /**
     * get the number of tasks owned by the log-on user which satisfy the filter
     * condition
     *
     * @param filter the filter condition
     * @return the number of tasks
     * @throws Exception
     */
    public int getDoneTasksCount(HashMap filter) throws Exception {
        String SOURCE_METHOD = "getOwnTasksCount";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{filter});

        int count = -1;
        try {

            /** 1. Construct the additional condition */
            String filterClause = WPSQueryHelper
                    .getActivityWorkitemWhereClause(
                            "PROCESS_INSTANCE.TEMPLATE_NAME", filter);

            /** 2. query for the count */
            QueryResultSet result = null;
            System.out.println("before getDoneTasksCount:"
                    + StaticMethod
                    .getCurrentDateTime("yyyy-MM-dd HH:mm:ss.SSS"));
            String selectClause = "COUNT(DISTINCT TASK.TKIID)";// (COUNT(DISTINCT
            // ACTIVITY.AIID))
            String whereClause = " (PROCESS_INSTANCE.STATE = PROCESS_INSTANCE.STATE.STATE_RUNNING ) "
                    + "AND (WORK_ITEM.REASON = WORK_ITEM.REASON.REASON_POTENTIAL_OWNER AND TASK.STATE = TASK.STATE.STATE_FINISHED) "
                    + " AND (TASK.KIND = TASK.KIND.KIND_PARTICIPATING OR TASK.KIND = TASK.KIND.KIND_HUMAN)";
            // filterClause = " and (PROCESS_INSTANCE.TEMPLATE_NAME in
            // ('BusinessDesign')) ";
            System.out.println("filterClause==" + filterClause);
            result = workflowManager.query(selectClause, whereClause
                    + filterClause, "", null, null);
            System.out.println("after getDoneTasksCount:"
                    + StaticMethod
                    .getCurrentDateTime("yyyy-MM-dd HH:mm:ss.SSS"));

            /** 3. get the count */
            if (result.next()) {
                count = result.getInteger(1).intValue();
            }
            System.out.println("count==" + count);
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_METHOD, SOURCE_METHOD, new Integer(count));
        return count;
    }

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
                                 String taskBoName, HashMap taskField) throws Exception {
        String SOURCE_METHOD = "getOwnTasks";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{
                new Integer(startIndex), new Integer(length), filter});

        List retList = new ArrayList();
        try {

            /** 1. Construct the addition condition */
            String filterClause = WPSQueryHelper
                    .getActivityWorkitemWhereClause("PROCESS_TEMPLATE.NAME",
                            filter);
            /** 2. Query the task list */
            QueryResultSet result = null;
            String selectClause = WPSQueryHelper.getTaskSelectClause();
            String whereClause = " (PROCESS_INSTANCE.STATE = PROCESS_INSTANCE.STATE.STATE_RUNNING ) and (WORK_ITEM.REASON=WORK_ITEM.REASON.REASON_OWNER AND TASK.STATE = TASK.STATE.STATE_FINISHED) "
                    + " AND (TASK.KIND = TASK.KIND.KIND_PARTICIPATING OR TASK.KIND = TASK.KIND.KIND_HUMAN)";
            String orderClause = "TASK.ACTIVATED DESC";
            Integer skipTuples = new Integer(startIndex);
            Integer threshold = new Integer(startIndex + length);
            result = workflowManager.query(selectClause, whereClause
                    + filterClause, orderClause, skipTuples, threshold, null);

            /** 3. Extract the result from the list */
            retList = WPSQueryHelper.parseTaskQueryResult(result, taskBoName,
                    taskField);

        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, retList);
        return retList;
    }

    /**
     * get the number of tasks administrated by the log-on user which satisfy
     * the filter condition
     *
     * @param filter the filter condition
     * @return the number of tasks
     * @throws Exception
     */
    public int getAdministratedTasksCount(HashMap filter) throws Exception {
        String SOURCE_METHOD = "getAdministratedTasksCount";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{filter});

        int count = -1;
        try {

            /** 1. Construct the additional condition */
            String filterClause = WPSQueryHelper
                    .getActivityWorkitemWhereClause("PROCESS_TEMPLATE.NAME",
                            filter);
            /** 2. query for the count */
            QueryResultSet result = null;
            String selectClause = "COUNT(DISTINCT TASK.TKIID)";// (COUNT(DISTINCT
            // ACTIVITY.AIID))
            String whereClause = " ((WORK_ITEM.REASON = WORK_ITEM.REASON.REASON_ADMINISTRATOR AND TASK.STATE = TASK.STATE.STATE_READY) OR (WORK_ITEM.REASON=WORK_ITEM.REASON.REASON_ADMINISTRATOR AND TASK.STATE = TASK.STATE.STATE_CLAIMED)) "
                    + " AND (TASK.KIND = TASK.KIND.KIND_PARTICIPATING OR TASK.KIND = TASK.KIND.KIND_HUMAN)";

            result = workflowManager.query(selectClause, whereClause
                    + filterClause, "", null, null);

            /** 3. get the count */
            if (result.next()) {
                count = result.getInteger(1).intValue();
            }

        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_METHOD, SOURCE_METHOD, new Integer(count));
        return count;
    }

    /**
     * get the tasks administrated by the log-on user which satisfy the filter
     * condition
     *
     * @param filter the filter condition
     * @return the List of tasks, for each task, it is represented as a HashMap
     * @throws Exception
     */
    public List getAdministratedTasks(HashMap filter) throws Exception {
        String SOURCE_METHOD = "getAdministratedTasks";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{filter});

        List retList = new ArrayList();
        try {

            /** 1. Construct the addition condition */
            String filterClause = WPSQueryHelper
                    .getActivityWorkitemWhereClause("PROCESS_TEMPLATE.NAME",
                            filter);
            /** 2. Query the task list */
            QueryResultSet result = null;
            String selectClause = WPSQueryHelper
                    .getActivityWorkitemSelectClause();
            String whereClause = "((WORK_ITEM.REASON = WORK_ITEM.REASON.REASON_ADMINISTRATOR AND ACTIVITY.STATE = ACTIVITY.STATE.STATE_READY) OR (WORK_ITEM.REASON=WORK_ITEM.REASON.REASON_ADMINISTRATOR AND ACTIVITY.STATE = ACTIVITY.STATE.STATE_CLAIMED)) "
                    + " AND (TASK.KIND = TASK.KIND.KIND_PARTICIPATING OR TASK.KIND = TASK.KIND.KIND_HUMAN)";
            String orderClause = "TASK.ACTIVATED DESC";
            result = workflowManager.query(selectClause, whereClause
                    + filterClause, orderClause, null, null);

            /** 3. Extract the result from the list and put them into return List */
            retList = WPSQueryHelper.parseActivityWorkitemQueryResult(result);

        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, retList);
        return retList;
    }

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
            throws Exception {
        String SOURCE_METHOD = "getAdministratedTasks";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{
                new Integer(startIndex), new Integer(length), filter});

        List retList = new ArrayList();
        try {

            /** 1. Construct the addition condition */
            String filterClause = WPSQueryHelper
                    .getActivityWorkitemWhereClause("PROCESS_TEMPLATE.NAME",
                            filter);
            /** 2. Query the task list */
            QueryResultSet result = null;
            String selectClause = WPSQueryHelper
                    .getActivityWorkitemSelectClause();
            String whereClause = "((WORK_ITEM.REASON = WORK_ITEM.REASON.REASON_ADMINISTRATOR AND TASK.STATE = TASK.STATE.STATE_READY) OR (WORK_ITEM.REASON=WORK_ITEM.REASON.REASON_ADMINISTRATOR AND TASK.STATE = TASK.STATE.STATE_CLAIMED)) "
                    + " AND (TASK.KIND = TASK.KIND.KIND_PARTICIPATING OR TASK.KIND = TASK.KIND.KIND_HUMAN)";
            String orderClause = "TASK.ACTIVATED DESC";
            Integer skipTuples = new Integer(startIndex);
            Integer threshold = new Integer(startIndex + length);
            result = workflowManager.query(selectClause, whereClause
                    + filterClause, orderClause, skipTuples, threshold, null);

            /** 3. Extract the result from the list and put them into return List */
            retList = WPSQueryHelper.parseActivityWorkitemQueryResult(result);

        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, retList);
        return retList;
    }

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
    public boolean claimTask(String tkiid, HashMap parameters) throws Exception {
        String SOURCE_METHOD = "claimTask";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{tkiid});
        boolean flag = false;
        HashMap map = new HashMap();
        try {
            // 申明任务
            this.humanTaskManager.claim(tkiid);
            //
            com.ibm.task.api.ClientObjectWrapper clientObjectWrapper = this.humanTaskManager
                    .createOutputMessage(tkiid);
            if (clientObjectWrapper.getObject() != null
                    && clientObjectWrapper.getObject() instanceof DataObject) {
                DataObject outPutMessage = (DataObject) clientObjectWrapper
                        .getObject();
                WPSStaticMethod.createDataObject(outPutMessage, parameters);
                humanTaskManager.setOutputMessage(tkiid, clientObjectWrapper);
                flag = true;
            }
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, map);
        return flag;
    }

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
    public void unclaimTask(String activityId) throws Exception {
        String SOURCE_METHOD = "unclaimTask";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD,
                new Object[]{activityId});

        try {
            workflowManager.cancelClaim(activityId);
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);
    }

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
    public HashMap getActivityInputData(String activityId) throws Exception {
        String SOURCE_METHOD = "getActivityInputData";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, activityId);

        HashMap result = new HashMap();
        try {
            ClientObjectWrapper clientObjectWrapper = workflowManager
                    .getInputMessage(activityId);
            if (clientObjectWrapper.getObject() != null
                    && clientObjectWrapper.getObject() instanceof DataObject) {
                DataObject dataObject = (DataObject) clientObjectWrapper
                        .getObject();
                result = WPSStaticMethod.createHashMap(dataObject);
            }
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, result);
        return result;
    }

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
            throws Exception {
        String SOURCE_METHOD = "setTaskOutputData";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{activityId,
                parameters});

        try {
            /** 1. If the task is not claimed yet, claim it first */
            ActivityInstanceData task = workflowManager
                    .getActivityInstance(activityId);
            if (task.getExecutionState() == ActivityInstanceData.STATE_READY) {
                workflowManager.claim(activityId);
            }

            /** 2. Save the task by setting the output BO */
            ClientObjectWrapper output = workflowManager.createMessage(
                    activityId, task.getOutputMessageTypeName());
            DataObject myMessage = null;
            if (output.getObject() != null
                    && output.getObject() instanceof DataObject) {
                myMessage = (DataObject) output.getObject();
                WPSStaticMethod.createDataObject(myMessage, parameters);
                workflowManager.setOutputMessage(activityId, output);
            }

        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);
    }

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
    public void finishTask(String activityId) throws Exception {
        String SOURCE_METHOD = "finishTask";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD,
                new Object[]{activityId});

        try {
            /** 1. If the task is not claimed yet, claim it first */
            ActivityInstanceData task = workflowManager
                    .getActivityInstance(activityId);
            if (task.getExecutionState() == ActivityInstanceData.STATE_READY) {
                workflowManager.claim(activityId);
            }

            /** 2. Complete the task */

            workflowManager.complete(activityId);

        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);

    }

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
            throws Exception {
        String szReturn = "";
        String SOURCE_METHOD = "finishTask";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{activityId,
                parameters});

        try {
            /** 1. If the task is not claimed yet, claim it first */
            // Task task = humanTaskManager.getTask(activityId);
            // if (task.getState() == Task.STATE_READY) {
            // humanTaskManager.claim(activityId);
            // }
            int taskState = 0;
            String selectClause = "TASK.STATE";
            String whereClause = "TASK.TKIID=ID('" + activityId + "')";
            QueryResultSet result = this.workflowManager.queryAll(selectClause,
                    whereClause, null, null, null, null);
            if (result.next()) {
                taskState = result.getInteger(1).intValue();
            }
            if (taskState == Task.STATE_READY) {
                humanTaskManager.claim(activityId);
            }
            /** 2. Complete the task by setting the output BO */
            // ClientObjectWrapper output = workflowManager.createMessage(
            // activityId, task.getOutputMessageTypeName());
            com.ibm.task.api.ClientObjectWrapper output = humanTaskManager
                    .createOutputMessage(activityId);
            System.out.println("createOutputMessage end!");
            DataObject myMessage = null;
            if (output.getObject() != null
                    && output.getObject() instanceof DataObject) {
                myMessage = (DataObject) output.getObject();
                WPSStaticMethod.createDataObject(myMessage, parameters);
                // humanTaskManager.setOutputMessage(activityId, output);
                System.out.println("complete task begin!");
                humanTaskManager.complete(activityId, output);

            }
            szReturn = "success";
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof com.ibm.task.api.TaskIsWaitingForSubTaskException) {
                szReturn = "subtaskfail";
            }
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD);
        return szReturn;
    }

    /**
     * @return the username passed in the security context
     */
    private String getCurrentUser() {

        String SOURCE_METHOD = "getCurrentUser";
        String EXCEPTION_MESSAGE = "";

        String userName = null;
        try {
            javax.security.auth.Subject caller_subject;
            com.ibm.websphere.security.cred.WSCredential caller_cred;
            caller_subject = com.ibm.websphere.security.auth.WSSubject
                    .getCallerSubject();
            String callerUserName = null;
            if (caller_subject != null) {
                caller_cred = (com.ibm.websphere.security.cred.WSCredential) caller_subject
                        .getPublicCredentials(
                                com.ibm.websphere.security.cred.WSCredential.class)
                        .iterator().next();
                userName = (String) caller_cred.getSecurityName();
            }
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
        }
        return userName;
    }

    /**
     * @return the activity instance id
     */

    public String getActivityInstanceId(String piid, String activityTemplateName)
            throws Exception {
        String aiid = "";
        ActivityInstanceData activityData = workflowManager
                .getActivityInstance(piid, activityTemplateName);
        AIID acitvity = activityData.getID();
        aiid = activityData.getID().toString();
        return aiid;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.adapter.service.IWorkflowEngineService#createSubTask(java.lang.String)
     */
    public boolean createSubTask(String parentTaskId, HashMap parameters)
            throws Exception {
        boolean flag = false;
        try {
            /* 1.声明parentTask */
            int taskState = 0;

            String selectClause = "TASK.STATE";
            String whereClause = "TASK.TKIID=ID('" + parentTaskId + "')";
            QueryResultSet result = this.workflowManager.queryAll(selectClause,
                    whereClause, null, null, null, null);
            if (result.next()) {
                taskState = result.getInteger(1).intValue();
            }
            if (taskState == Task.STATE_READY) {
                humanTaskManager.claim(parentTaskId);
            }
            /* 2.创建subtask */
            // Task parentTask = this.humanTaskManager.getTask(taskId);
            /*
             * 若采用非全局humantask作为subtask的模板，采用下述代码：
             *
             * String selectClause="TASK.NAME,TASK.NAME_SPACE"; String
             * whereClause="TASK.TKIID=ID('"+parentTaskId+"')"; QueryResultSet
             * result=this.workflowManager.query(selectClause,
             * whereClause,null,null,null,null); if(result.next()){ String
             * taskName=result.getString(1); String
             * taskNamespace=result.getString(2); }
             */
            // 采用全局（standlone）humantask作为subtask的模板，采用下述代码：
            String taskName = (String) parameters.get("taskName");// "subTaskTemplate";
            String taskNamespace = (String) parameters.get("taskNamespace");// "http://NewBusiness";
            String parentTaskName = (String) parameters.get("parentTaskName");
            ;
            String operateType = (String) parameters.get("operateType");
            System.out.println("taskName=" + taskName + "taskNamespace="
                    + taskNamespace + "parentTaskName=" + parentTaskName);
            System.out.println("tkid=" + parentTaskId);
            TKIID subTaskId = humanTaskManager.createTask(taskName,
                    taskNamespace);

            System.out.println("subtkid=---------------------<><><><><><><>"
                    + subTaskId);

            /* 3.获取父任务的输入值 */
            Task task = humanTaskManager.getTask(subTaskId);
            task.setName("aaaaaaaaa");
            task.setDisplayName("bbbbb", Locale.CHINA);
            task.setDescription("aaaaaaaaa", Locale.CHINA);
            HashMap dataObjectMap = new HashMap();
            com.ibm.task.api.ClientObjectWrapper clientObjectWrapper = humanTaskManager
                    .getInputMessage(parentTaskId);
            if (clientObjectWrapper.getObject() != null
                    && clientObjectWrapper.getObject() instanceof DataObject) {
                DataObject inputMessage = (DataObject) clientObjectWrapper
                        .getObject();
                dataObjectMap = WPSStaticMethod.createHashMap(inputMessage);
            }
            /* 4.将父任务的输入值作为子任务的输入值 */
            com.ibm.task.api.ClientObjectWrapper clientObjectWrapper2 = humanTaskManager
                    .createInputMessage(subTaskId);
            if (clientObjectWrapper2.getObject() != null
                    && clientObjectWrapper2.getObject() instanceof DataObject) {
                DataObject inputMessage2 = (DataObject) clientObjectWrapper2
                        .getObject();
                // 构建subtask的执行者消息。add by qinmin
                dataObjectMap.put("performer", parameters.get("dealPerformer"));
                HashMap taskMap = (HashMap) dataObjectMap.get("task");
                taskMap.put("operateRoleId", parameters.get("dealPerformer"));
                taskMap.put("taskOwner", parameters.get("dealPerformerLeader"));
                taskMap.put("operateType", parameters.get("dealPerformerType"));

                HashMap linkMap = (HashMap) dataObjectMap.get("link");
                SheetBeanUtils.populateMap2Map(linkMap, parameters);
                linkMap.put("id", UUIDHexGenerator.getInstance().getID());
                linkMap.put("activeTemplateId", parentTaskName);
                linkMap.put("aiid", parentTaskId);
                linkMap.put("operateType", operateType);
                linkMap.put("toOrgRoleId", parameters.get("dealPerformerLeader"));
                dataObjectMap.put("task", taskMap);
                dataObjectMap.put("link", linkMap);
                WPSStaticMethod.createDataObject(inputMessage2, dataObjectMap);
            }
            /* 5.真正生成子任务 */
            humanTaskManager.startTaskAsSubTask(subTaskId.toString(),
                    parentTaskId, clientObjectWrapper2);
            flag = true;
        } catch (Exception e) {
            throw new Exception("创建子任务失败，报错原因：" + e.getLocalizedMessage());
        }
        return flag;
    }

    /**
     * 获取节点事件接口列表
     *
     * @param tkid
     * @param sessionMap
     * @throws Exception
     */
    public List getActiveEventHandlers(String piid) throws Exception {
        EventHandlerTemplateData events[] = workflowManager
                .getActiveEventHandlers(piid);
        List list = new ArrayList();
        for (int i = 0; events.length > 0 && i < events.length; i++) {
            EventHandlerTemplateData event = events[i];
            HashMap map = new HashMap();
            map.put("processTemplateName", event.getProcessTemplateName());
            map.put("operationName", events[0].getOperationName());
            list.add(map);
        }
        return list;
    }

    /**
     * 根据任务ID号，获取task model对象值
     *
     * @param taskId 任务ID
     * @param task   taskModel对象
     * @throws Exception
     */
    public ITask getTaskModelByTaskId(String taskId, ITask task)
            throws Exception {
        Task taskBO = this.humanTaskManager.getTask(taskId);
        ProcessTemplateData processTemplate = workflowManager
                .getProcessTemplate(workflowManager.getProcessInstance(
                        taskBO.getContainmentContextID().toString())
                        .getProcessTemplateID());

        HashMap processTemplateMap = new HashMap(); // 流程模板对象
        HashMap taskBOMap = new HashMap(); // 任务实例对象
        HashMap taskObjectMap = new HashMap(); // 业务端任务信息对象

        // 获取流程模板信息
        if (processTemplate != null)
            processTemplateMap = WPSStaticMethod
                    .getNamesWithValueByMethods(processTemplate);

        // 获取任务信息中有关流程部分的信息
        if (task != null)
            taskBOMap = WPSStaticMethod.getNamesWithValueByMethods(taskBO);

        // 获取任务信息中有关业务部分的信息
        com.ibm.task.api.ClientObjectWrapper clientObjectWrapper = this.humanTaskManager
                .getInputMessage(taskId);
        if (clientObjectWrapper.getObject() != null
                && clientObjectWrapper.getObject() instanceof DataObject) {
            DataObject dataObject = (DataObject) clientObjectWrapper
                    .getObject();
            DataObject taskObject = (DataObject) dataObject.get("task");
            if (taskObject != null)
                taskObjectMap = WPSStaticMethod
                        .getNamesWithValueByDataObject(taskObject);
        }

        // 将任务信息放置到任务model对象中
        Method[] superMethods = task.getClass().getSuperclass()
                .getDeclaredMethods();
        Method[] methods = task.getClass().getDeclaredMethods();
        task = (ITask) WPSStaticMethod.setTaskModel(task, superMethods,
                processTemplateMap, taskBOMap, taskObjectMap);
        task = (ITask) WPSStaticMethod.setTaskModel(task, methods,
                processTemplateMap, taskBOMap, taskObjectMap);
        return task;

    }

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
    public String triggerEvent(String piid, String processTemplateName,
                               String operationName, HashMap parameters) throws Exception {
        String SOURCE_METHOD = "triggerEvent";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{
                processTemplateName, operationName, parameters});
        String szReturn = "";
        /** 1. Get the process template. */
        ProcessTemplateData processTemplate = null;
        try {
            String whereClause = "PROCESS_TEMPLATE.NAME = '"
                    + processTemplateName + "'";
            String orderByClause = "PROCESS_TEMPLATE.VALID_FROM DESC";
            ProcessTemplateData[] processTemplates = workflowManager
                    .queryProcessTemplates(whereClause, orderByClause, null,
                            null);
            if (processTemplates.length > 0) {
                processTemplate = processTemplates[0];
            }
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }
        if (processTemplate == null) {
            throw new Exception("Invalid Process Name is specified");
        }

        /**
         * 临时处理，解决非流程动作时link中的activyTemplateId错误的问题
         * add by chenyuanshu 2008-10-28 begin
         */
        HashMap link = (HashMap) parameters.get("link");
        String aiid = StaticMethod.nullObject2String(link.get("aiid"));
        String taskBeanId = "";
        List properties = workflowManager.getCustomProperties(piid);
        for (int i = 0; properties != null && i < properties.size(); i++) {
            CustomProperty c = (CustomProperty) properties.get(i);
            if (c.getName().equals("taskBeanId")) {
                taskBeanId = c.getValue();
                break;
            }
        }
        if (!aiid.equals("") && !taskBeanId.equals("")) {
            ITaskService taskService = (ITaskService) ApplicationContextHolder()
                    .getInstance().getBean(taskBeanId);
            ITask task = (ITask) taskService.getSinglePO(aiid);
            link.put("activeTemplateId", task.getTaskName());
            parameters.put("link", link);
        }
        /**
         * 临时处理结束，留待以后优化
         * add by chenyuanshu 2008-10-28 end
         */


        /** 3. set the input message and start the process */
        com.ibm.bpe.api.ClientObjectWrapper clientObjectWrapper = null;
        try {
            EventHandlerTemplateData events[] = workflowManager
                    .getActiveEventHandlers(piid);
            EventHandlerTemplateData event = null;
            for (int i = 0; events != null && i < events.length; i++) {
                event = events[i];
                if (event.getOperationName().equals(operationName)) {
                    break;
                }
            }
            clientObjectWrapper = workflowManager.createMessage(event.getID(),
                    event.getInputMessageTypeName());

            // set the initial values in the input message
            if (clientObjectWrapper.getObject() != null
                    && clientObjectWrapper.getObject() instanceof DataObject) {
                DataObject inputMessage = (DataObject) clientObjectWrapper
                        .getObject();
                WPSStaticMethod.createDataObject(inputMessage, parameters);
            }
            processTemplateName = workflowManager.getProcessInstance(piid)
                    .getProcessTemplateName();
            workflowManager.sendMessage(processTemplateName, event
                    .getPortTypeNamespace(), event.getPortTypeName(), event
                    .getOperationName(), clientObjectWrapper, null);

            szReturn = "success";
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());

        }
        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, szReturn);
        return szReturn;
    }

    private ApplicationContextHolder ApplicationContextHolder() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 工单移交,包括同一角色内部移交，以及不同角色之间移交。
     *
     * @param taskId          任务ID
     * @param fromOwnerUserId 任务移交者用户ID
     * @param fromOwnerRoleId 任务移交者角色ID
     * @param toOwnerRoleId   任务被移交者角色ID
     * @param toOwnerUserId   任务被移交者人员ID
     * @param operOrgType     任务移交者类型：人员、角色
     * @return
     * @throws Exception
     */
    public void transferWorkItem(String taskId, String fromOwnerUserId,
                                 String fromOwnerRoleId, String toOwnerUserId, String toOwnerRoleId,
                                 String operOrgType, HashMap parametersMap) throws Exception {
        try {
            /** 1. If the activity state is ready, claim it first */
            int taskState = 0;
            String piid = null;
            String selectClause = "TASK.STATE,TASK.CONTAINMENT_CTX_ID";
            String whereClause = "TASK.TKIID=ID('" + taskId + "')";
            QueryResultSet result = this.workflowManager.queryAll(selectClause,
                    whereClause, null, null, null, null);
            if (result.next()) {
                taskState = result.getInteger(1).intValue();
                piid = StaticMethod.nullObject2String(result.getOID(2)
                        .toString());
                System.out.println("==transferWorkItem piid===" + piid);
            }
            if (taskState == Task.STATE_READY) {
                humanTaskManager.claim(taskId);
            }
            /** 2. save the link info */
            com.ibm.task.api.ClientObjectWrapper output = humanTaskManager
                    .createOutputMessage(taskId);
            DataObject myMessage = null;
            if (output.getObject() != null
                    && output.getObject() instanceof DataObject) {
                myMessage = (DataObject) output.getObject();
                WPSStaticMethod.createDataObject(myMessage, parametersMap);
                humanTaskManager.setOutputMessage(taskId, output);
            }
            /** 3. Transfer the task owner */
            if (operOrgType.equals(UIConstants.NODETYPE_USER)
                    && !toOwnerUserId.equals("")) {
                // 同一角色内部移交，只需要修改当前任务的拥有者即可。
                humanTaskManager.transferWorkItem(taskId,
                        WorkItem.REASON_OWNER, fromOwnerUserId, toOwnerUserId);
            } else if (operOrgType.equals(UIConstants.NODETYPE_SUBROLE)
                    && !toOwnerRoleId.equals("")) {
                // 首先对传递过来的移交者进行解析，获取移交者的roleId与leader user id

                // 不同角色之间任务的移交，需要修改当前任务的潜在拥有者，并且将此任务释放

                if (!toOwnerUserId.equals(toOwnerRoleId)) {
                    // 制定了组长
                    humanTaskManager.transferWorkItem(taskId,
                            WorkItem.REASON_OWNER, fromOwnerUserId,
                            toOwnerUserId);
                    humanTaskManager.transferWorkItem(taskId,
                            WorkItem.REASON_POTENTIAL_OWNER, fromOwnerRoleId,
                            toOwnerRoleId);
                } else {
                    // 抢单
                    humanTaskManager.transferWorkItem(taskId,
                            WorkItem.REASON_OWNER, fromOwnerUserId,
                            toOwnerRoleId);
                    humanTaskManager.transferWorkItem(taskId,
                            WorkItem.REASON_POTENTIAL_OWNER, fromOwnerRoleId,
                            toOwnerRoleId);
                    humanTaskManager.cancelClaim(taskId);
                }
            }

        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }

    }

    /**
     * 流程回调
     *
     * @param processInstanceId
     * @param operationName
     * @param parameters
     * @throws Exception
     */
    public void reInvokeProcess(String processInstanceId, String operationName,
                                HashMap parameters) throws Exception {
        /** 1. Get the process template. */
        ActivityServiceTemplateData invokeActivity = null;
        try {
            ActivityServiceTemplateData[] activities = workflowManager
                    .getWaitingActivities(processInstanceId);
            System.out.println("processInstanceId=" + processInstanceId);
            for (int i = 0; i < activities.length; i++) {
                ActivityServiceTemplateData activity = activities[i];
                System.out.println("activity operationname="
                        + activity.getOperationName());
                if (activity.getOperationName().equals(operationName)) {
                    invokeActivity = activity;
                    break;
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getLocalizedMessage());
        }
        if (invokeActivity == null) {
            throw new Exception("Invalid Operation Name is specified");
        }

        /** 3. set the input message and start the process */
        com.ibm.bpe.api.ClientObjectWrapper clientObjectWrapper = null;
        try {
            clientObjectWrapper = workflowManager.createMessage(invokeActivity
                    .getServiceTemplateID(), invokeActivity
                    .getActivityTemplateID(), invokeActivity
                    .getInputMessageTypeName());
            // set the initial values in the input message
            if (clientObjectWrapper.getObject() != null
                    && clientObjectWrapper.getObject() instanceof DataObject) {
                DataObject inputMessage = (DataObject) clientObjectWrapper
                        .getObject();
                WPSStaticMethod.createDataObject(inputMessage, parameters);
            }
            workflowManager
                    .sendMessage(invokeActivity.getServiceTemplateID(),
                            invokeActivity.getActivityTemplateID(),
                            clientObjectWrapper);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("++++++reInvokeProcess error++++++++");
            throw new Exception(e.getMessage());

        }
    }

    public boolean cancelClaimTask(String taskId) throws Exception {
        String SOURCE_METHOD = "cancelClaimTask";
        String EXCEPTION_MESSAGE = "";
        LOGGER.entering(SOURCE_CLASS, SOURCE_METHOD, new Object[]{taskId});
        boolean flag = false;
        HashMap map = new HashMap();
        try {
            int taskState = 0;
            String selectClause = "TASK.STATE";
            String whereClause = "TASK.TKIID=ID('" + taskId + "')";
            QueryResultSet result = this.workflowManager.queryAll(selectClause,
                    whereClause, null, null, null, null);
            if (result.next()) {
                taskState = result.getInteger(1).intValue();
            }
            if (taskState == Task.STATE_CLAIMED) {
                humanTaskManager.cancelClaim(taskId);
            }

        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }

        LOGGER.exiting(SOURCE_CLASS, SOURCE_METHOD, map);
        return flag;

    }

    public HashMap queryAll(String selectClause, String whereClause) throws Exception {
        String SOURCE_METHOD = "queryAll";
        String EXCEPTION_MESSAGE = "";
        HashMap resultMap = new HashMap();
        try {
            QueryResultSet result = this.workflowManager.queryAll(selectClause,
                    whereClause, null, null, null, null);
            if (result != null) {
                int num = 0;
                while (result.next()) {
                    HashMap columnMap = new HashMap();
                    for (int i = 0; i < result.numberColumns(); i++) {
                        String clumnName = result.getColumnDisplayName(i + 1);
                        Object obj = result.getObject(i + 1);
                        columnMap.put(clumnName, obj);
                    }
                    resultMap.put("" + num, columnMap);
                    num++;
                }
            }
        } catch (Exception e) {
            LOGGER.logp(Level.SEVERE, SOURCE_CLASS, SOURCE_METHOD,
                    EXCEPTION_MESSAGE, e.getCause());
            throw new Exception(e.getLocalizedMessage());
        }
        return resultMap;
    }


}

/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.adapter.service.bpm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService;
import com.boco.eoms.sheet.base.task.ITask;
import com.ibm.bpe.api.ProcessInstanceData;
import com.ibm.task.api.Task;

/**
 * <p>
 * Title:业务流程api
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 14:19:49
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 * @see IBusinessFlowAdapterService
 */
public class BPMBusinessFlowAdapterServiceImpl implements
        IBusinessFlowAdapterService {

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#getDoneTasksList(int,
     *      int, java.util.HashMap, java.lang.String, java.util.HashMap)
     */
    public List getDoneTasksList(int startIndex, int length, HashMap filter,
                                 String taskBoName, HashMap taskField, HashMap sessionMap)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#getUndoTasksCount(java.util.HashMap)
     */
    public int getUndoTasksCount(HashMap filter) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#getUndoTasksList(int,
     *      int, java.util.HashMap, java.lang.String, java.util.HashMap)
     */
    public List getUndoTasksList(int startIndex, int length, HashMap filter,
                                 String taskBoName, HashMap taskField, HashMap sessionMap)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#getUndoTasksList(java.util.HashMap)
     */
    public List getUndoTasksList(HashMap filter) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#finishTask(java.lang.String,
     *      java.util.HashMap)
     */
    public void finishTask(String activityId, HashMap parameters,
                           HashMap sessionMap) throws Exception {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#triggerProcess(java.lang.String,
     *      java.lang.String, java.util.HashMap)
     */
    public String triggerProcess(String processTemplateName,
                                 String operationName, HashMap parameters, HashMap sessionMap)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#transferFlowObjectToMode(java.lang.Object,
     *      java.lang.String)
     */
    public Object transferFlowObjectToMode(Object object, String modelClassName)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#getDoneTasksList(int,
     *      int, java.util.HashMap, java.lang.String, java.util.HashMap)
     */
    public List getDoneTasksList(int startIndex, int length, HashMap filter,
                                 String taskBoName, HashMap taskField) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#getUndoTasksList(int,
     *      int, java.util.HashMap, java.lang.String, java.util.HashMap)
     */
    public List getUndoTasksList(int startIndex, int length, HashMap filter,
                                 String taskBoName, HashMap taskField) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#finishTask(java.lang.String,
     *      java.util.HashMap)
     */
    public void finishTask(String activityId, HashMap parameters)
            throws Exception {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#triggerProcess(java.lang.String,
     *      java.lang.String, java.util.HashMap)
     */
    public String triggerProcess(String processTemplateName,
                                 String operationName, HashMap parameters) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#getUndoTasksCount(java.util.HashMap,
     *      java.util.HashMap)
     */
    public int getUndoTasksCount(HashMap filter, HashMap sessionMap)
            throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#transferTask(java.lang.String,
     *      java.lang.String, java.lang.String, java.util.HashMap)
     */
    public boolean cancel(String piid, HashMap sessionMap) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#transferMainObjectToMode(java.lang.Object, java.lang.String)
     */
    public Object transferMainObjectToMode(Object object, String modelClassName) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#transferLinkObjectToMode(java.lang.Object, java.lang.String)
     */
    public Object transferLinkObjectToMode(Object object, String modelClassName) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }


    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#transferIdFromDBToAction(java.lang.String)
     */
    public String transferIdFromDBToAction(String id) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#transferIdFormActionToDB(java.lang.String)
     */
    public String transferIdFormActionToDB(String id) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#getDoneTasksCount(java.util.HashMap, java.util.HashMap)
     */
    public int getDoneTasksCount(HashMap filter, HashMap sessionMap) throws Exception {
        // TODO Auto-generated method stub
        return 0;
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#claimTask(java.lang.String, java.util.HashMap)
     */
    public void claimTask(String tkid, HashMap parameters, HashMap sessionMap) throws Exception {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#creatSubTask(java.lang.String, java.util.HashMap)
     */
    public void createSubTask(String tkid, HashMap parameters, HashMap sessionMap) throws Exception {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#getActiveEventHandlers(java.lang.String, java.util.HashMap)
     */
    public List getActiveEventHandlers(String piid, HashMap sessionMap) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#getTaskModeByTaskId(java.lang.String, java.util.HashMap)
     */
    public ITask getTaskModeByTaskId(String taskId, ITask task, HashMap sessionMap) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService#triggerEvent(java.lang.String, java.lang.String, java.lang.String, java.util.HashMap, java.util.HashMap)
     */
    public String triggerEvent(String piid, String processTemplateName, String operationName, HashMap parameters, HashMap sessionMap) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 工单移交
     *
     * @param tkid
     * @param sessionMap TODO
     * @param sessionMap
     * @throws Exception
     */
    public void transferWorkItem(String tkid, String fromOwnerUserId, String fromOwnerRoleId,
                                 String toOwnerUserId, String toOwnerRoleId, String operOrgType, HashMap map, HashMap sessionMap) throws Exception {

    }

    public void updateTaskStatus(String taskId, HashMap sessionMap) throws Exception {
        // TODO Auto-generated method stub

    }

    public Map getVariable(String identifier, String variableName, HashMap sessionMap) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public void reInvokeProcess(String piid, String operationName, HashMap parameters, HashMap sessionMap) throws Exception {
        // TODO Auto-generated method stub

    }

    public void cancelClaimTask(String tkid, HashMap parameters, HashMap sessionMap) throws Exception {
        // TODO Auto-generated method stub

    }


}

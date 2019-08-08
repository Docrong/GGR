/*
 * Created on 2007-8-3
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService;
import com.boco.eoms.sheet.base.service.IBusinessFlowService;
import com.boco.eoms.sheet.base.task.ITask;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2007-8-3 14:46:42
 * </p>
 *
 * @author 曲静波
 * @version 1.0
 */
public class BusinessFlowServiceImpl implements IBusinessFlowService {

    private IBusinessFlowAdapterService businessFlowAdapterService;

    /**
     * @return the businessFlowAdapterService
     */
    public IBusinessFlowAdapterService getBusinessFlowAdapterService() {
        return businessFlowAdapterService;
    }

    /**
     * @param businessFlowAdapterService the businessFlowAdapterService to set
     */
    public void setBusinessFlowAdapterService(
            IBusinessFlowAdapterService businessFlowAdapterService) {
        this.businessFlowAdapterService = businessFlowAdapterService;
    }

    /*
     * (non-Javadoc)
     * @see com.boco.eoms.sheet.base.service.IBusinessFlowService#getVariable(java.lang.String, java.lang.String, java.util.HashMap)
     */
    public Map getVariable(String identifier, String variableName, HashMap sessionMap) throws Exception {
        Map map = new HashMap();
        try {
            map = businessFlowAdapterService.getVariable(identifier, variableName, sessionMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.service.IBusinessFlowService#initProcess(java.lang.String,
     *      java.lang.String, java.util.Map)
     */
    public String initProcess(String processTemplateName, String OperateName,
                              HashMap map, HashMap sessionMap) throws Exception {
        String sRutrn = "success";
        try {
            System.out.println("processTemplateName=" + processTemplateName);
            System.out.println("OperateName=" + OperateName);

            businessFlowAdapterService.triggerProcess(processTemplateName,
                    OperateName, map, sessionMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("新增工单报错，请联系管理员！");
        }
        return sRutrn;
    }

    /**
     * 完成人工任务的api，主要调用引擎中的具体方法，来完成人工任务
     *
     * @param activityId 人工任务对应的实例号，类似与3.0里的taskId
     * @param parameters
     * @return
     * @throws Exception
     */
    public String completeHumanTask(String activityId, HashMap parameters,
                                    HashMap sessionMap) throws Exception {

        String sRutrn = "success";
        try {
            businessFlowAdapterService.finishTask(activityId, parameters,
                    sessionMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("处理工单报错，请联系管理员！");
        }
        return sRutrn;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.service.IBusinessFlowService#getOwnTasks(java.util.HashMap)
     */
    public List getUndoTasksList(HashMap filter) throws Exception {
        List list = new ArrayList();
        try {
            list = businessFlowAdapterService.getUndoTasksList(filter);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("获取待处理工单列表报错，，请联系管理员！");
        }
        return list;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.service.IBusinessFlowService#getOwnTasks(int,
     *      int, java.util.HashMap)
     */
    public List getUndoTasksList(int startIndex, int length, HashMap taskMap,
                                 HashMap filter, HashMap sessionMap) throws Exception {
        List list = new ArrayList();
        try {
            Set set = taskMap.keySet();
            String taskName = "";
            HashMap map = new HashMap();
            Iterator it = set.iterator();
            while (it.hasNext()) {
                taskName = StaticMethod.nullObject2String(it.next());
                map = (HashMap) taskMap.get(taskName);
                break;
            }
            list = businessFlowAdapterService.getUndoTasksList(startIndex,
                    length, filter, taskName, map, sessionMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("获取待处理工单列表报错，请联系管理员！");
        }
        return list;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.service.IBusinessFlowService#getOwnTasksCount(java.util.HashMap)
     */
    public int getUndoTasksCount(HashMap filter) throws Exception {
        int iReturn = 0;
        try {
            iReturn = businessFlowAdapterService.getUndoTasksCount(filter);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("获取待处理工单总数报错，请联系管理员！");
        }
        return iReturn;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.service.IBusinessFlowService#getOwnTasksCount(java.util.HashMap)
     */
    public int getUndoTasksCount(HashMap filter, HashMap sessionMap)
            throws Exception {
        int iReturn = 0;
        try {
            iReturn = businessFlowAdapterService.getUndoTasksCount(filter,
                    sessionMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("获取待处理工单总数报错，请联系管理员！");
        }
        return iReturn;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.service.IBusinessFlowService#getOwnTasksCount(java.util.HashMap)
     */
    public int getDoneTasksCount(HashMap filter) throws Exception {
        int iReturn = 0;
        try {
            //iReturn = businessFlowAdapterService.getUndoTasksCount(filter);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("获取已处理工单总数报错，请联系管理员！");
        }
        return iReturn;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.service.IBusinessFlowService#getOwnTasks(java.util.HashMap)
     */
    public List getDoneTasksList(HashMap filter) throws Exception {
        List list = new ArrayList();
        try {
            //list = businessFlowAdapterService.getUndoTasksList(filter);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("获取已处理工单列表报错，请联系管理员！");
        }
        return list;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.service.IBusinessFlowService#getOwnTasks(int,
     *      int, java.util.HashMap)
     */
    public List getDoneTasksList(int startIndex, int length, HashMap taskMap,
                                 HashMap filter, HashMap sessionMap) throws Exception {
        List list = new ArrayList();
        try {
            Set set = taskMap.keySet();
            String taskName = "";
            HashMap map = new HashMap();
            Iterator it = set.iterator();
            while (it.hasNext()) {
                taskName = StaticMethod.nullObject2String(it.next());
                map = (HashMap) taskMap.get(taskName);
                break;
            }
            list = businessFlowAdapterService.getDoneTasksList(startIndex,
                    length, filter, taskName, map, sessionMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("获取已处理工单列表报错，请联系管理员！");
        }
        return list;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.service.IBusinessFlowService#transferTask(java.lang.String,
     *      java.lang.String, java.lang.String, java.util.HashMap)
     */
    public boolean cancel(String piid, HashMap sessionMap) throws Exception {
        boolean result = false;
        try {
            result = businessFlowAdapterService.cancel(piid, sessionMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("撤销工单报错，请联系管理员！");
        }
        return result;
    }

    /*
     * 获取已处理任务的总数量
     *
     * @see com.boco.eoms.sheet.base.service.IBusinessFlowService#getDoneTasksCount(java.util.HashMap,
     *      java.util.HashMap)
     */
    public int getDoneTasksCount(HashMap filter, HashMap sessionMap)
            throws Exception {
        int iReturn = 0;
        try {
            iReturn = businessFlowAdapterService.getDoneTasksCount(filter,
                    sessionMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("获取已处理工单总数报错，请联系管理员！");
        }
        return iReturn;
    }

    /**
     * 申明一个任务
     */
    public void claimTask(String tkid, HashMap parameters, HashMap sessionMap) throws Exception {
        try {
            businessFlowAdapterService.claimTask(tkid, parameters, sessionMap);
        } catch (Exception ex) {
            throw new Exception("受理工单报错，请联系管理员！");
        }
    }

    /**
     * 创建一个子任务
     */
    public void createSubTask(String tkid, HashMap parameters, HashMap sessionMap) throws Exception {
        try {
            businessFlowAdapterService.createSubTask(tkid, parameters, sessionMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("工单分派报错，请联系管理员！");
        }

    }

    /**
     * 获取节点事件接口列表
     *
     * @param piid
     * @param sessionMap
     * @throws Exception
     */
    public List getActiveEventHandlers(String piid, HashMap sessionMap)
            throws Exception {
        List list = new ArrayList();
        try {
            list = businessFlowAdapterService.getActiveEventHandlers(piid, sessionMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("getActiveEventHandlers exception");
        }
        return list;
    }

    /*
     *  (non-Javadoc)
     * @see com.boco.eoms.sheet.base.service.IBusinessFlowService#initProcess(java.lang.String, java.lang.String, java.util.HashMap, java.util.HashMap)
     */
    public String triggerEvent(String piid, String processTemplateName, String OperateName,
                               HashMap map, HashMap sessionMap) throws Exception {
        String sRutrn = "success";
        try {
            System.out.println("processTemplateName=" + processTemplateName);
            System.out.println("OperateName=" + OperateName);

            businessFlowAdapterService.triggerEvent(piid, processTemplateName,
                    OperateName, map, sessionMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("非流程操作报错，请联系管理员！");
        }
        return sRutrn;
    }

    /**
     * 根据任务ID号，获取task model对象值
     *
     * @param tkiid
     * @param task
     * @param sessionMap
     * @return
     * @throws Exception
     */
    public ITask getTaskModelByTaskId(String tkiid, ITask task, HashMap sessionMap) throws Exception {
        // TODO Auto-generated method stub

        try {
            businessFlowAdapterService.getTaskModeByTaskId(tkiid, task, sessionMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("获取任务信息报错，请联系管理员！");
        }
        return task;
    }

    /**
     * 工单移交
     *
     * @param sessionMap
     * @param tkiid
     * @param task
     * @return
     * @throws Exception
     */
    public void transferWorkItem(String taskId, String fromOwnerUserId, String fromOwnerRoleId,
                                 String toOwnerUserId, String toOwnerRoleId, String operOrgType, HashMap map, HashMap sessionMap) throws Exception {
        try {
            businessFlowAdapterService.transferWorkItem(taskId, fromOwnerUserId, fromOwnerRoleId, toOwnerUserId, toOwnerRoleId, operOrgType, map, sessionMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("工单移交报错，请联系管理员！");
        }

    }

    /**
     * 流程回调
     */
    public void reInvokeProcess(String piid, String operationName,
                                HashMap parameters, HashMap sessionMap) throws Exception {
        try {
            businessFlowAdapterService.reInvokeProcess(piid, operationName, parameters, sessionMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("工单回调报错，请联系管理员！");
        }

    }

    /**
     * 取消申请一个任务
     *
     * @param tkid
     * @param sessionMap
     * @throws Exception
     */
    public void cancelClaimTask(String tkid, HashMap parameters, HashMap sessionMap) throws Exception {
        try {
            businessFlowAdapterService.cancelClaimTask(tkid, parameters, sessionMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("工单同组受理报错，请联系管理员！");
        }
    }

    public void setVariable(String processId, String variableName, Map control, HashMap sessionMap) throws Exception {
        // TODO 自动生成方法存根

    }
}

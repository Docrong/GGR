/*
 * Created on 2008-4-1
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.sheet.base.adapter.service.IBusinessFlowAdapterService;
import com.boco.eoms.sheet.base.dao.ITaskDAO;
import com.boco.eoms.sheet.base.flowchar.Node;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.EomsBshEval;
import com.boco.eoms.sheet.base.util.HibernateConfigurationHelper;
import com.boco.eoms.sheet.base.util.PropertyFile;
import com.boco.eoms.sheet.base.util.QuerySqlInit;
import com.boco.eoms.sheet.base.util.SheetStaticMethod;
import com.ibm.bpe.api.ProcessInstanceData;
import com.ibm.task.api.Task;

/**
 * @author IBM_USER
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TaskServiceImpl implements ITaskService {

    private ITaskDAO taskDAO;

    private ITask taskModelObject;

    private String taskConfigPath;


    /**
     * @return Returns the taskModelObject.
     */
    public ITask getTaskModelObject() {
        return taskModelObject;
    }

    /**
     * @param taskModelObject The taskModelObject to set.
     */
    public void setTaskModelObject(ITask taskModelObject) {
        this.taskModelObject = taskModelObject;
    }

    /**
     * @return Returns the taskDAO.
     */
    public ITaskDAO getTaskDAO() {
        return taskDAO;
    }

    /**
     * @param taskDAO The taskDAO to set.
     */
    public void setTaskDAO(ITaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    public String getTaskConfigPath() {
        return taskConfigPath;
    }

    public void setTaskConfigPath(String taskConfigPath) {
        this.taskConfigPath = taskConfigPath;
    }

    /**
     * 获取当前角色待处理工单
     *
     * @param userId     用户ID
     * @param deptId     部门ID
     * @param flowName   流程ID
     * @param startIndex
     * @param length
     * @return
     * @throws Exception
     */
    public HashMap getUndoTask(Map condition, String userId, String deptId, String flowName, Integer startIndex, Integer length) throws Exception {
        HashMap taskMap = new HashMap();
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        String hql = "select distinct task from "
                + this.getTaskModelObject().getClass().getName() + " as task,"
                + mainObject.getClass().getName() + " as main "
                //+ ",TawSystemUserRefRole as userrefrole "
                + "where ((task.taskOwner='" + userId + "' or task.taskOwner='" + deptId + "')"
                //由于建立了索引，将查询角色改为子查询方式 modify by jialei 2009-02-25
                //+ "(task.taskOwner=userrefrole.subRoleid and userrefrole.userid='"+ userId + "'))"
                + " or task.taskOwner in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))"
                + " and (task.taskStatus=" + Constants.TASK_STATUS_READY
                + " or task.taskStatus=" + Constants.TASK_STATUS_CLAIMED
                + " or task.taskStatus=" + Constants.TASK_STATUS_INACTIVE + ")"
                + " and task.taskDisplayName<>'草稿'"
                // 增加main.deleted<>1的条件，过滤已经隐藏或删除的工单 add by jialei
                + " and main.id=task.sheetKey and main.deleted<>'1'"
                //分派处理 add by jialei
                + " and (task.ifWaitForSubTask='false' or (task.ifWaitForSubTask='true' and task.id in (select task1.parentTaskId from " + this.getTaskModelObject().getClass().getName() + " as task1 where task1.subTaskFlag='true' and (task1.subTaskDealFalg='false' or task1.subTaskDealFalg is null ) and task1.taskStatus='" + Constants.TASK_STATUS_FINISHED + "')))"
                + " order by task.createTime desc";

        taskMap = taskDAO.getTaskListByCondition(hql, startIndex, length);
        return taskMap;
    }

    /**
     * 获取当前角色待处理工单
     *
     * @param userId     用户ID
     * @param deptId     部门ID
     * @param flowName   流程ID
     * @param startIndex
     * @param length
     * @return
     * @throws Exception
     */
    public HashMap getUndoAllSheetTask(Map condition, String userId,
                                       String deptId, String flowName, Integer startIndex, Integer length)
            throws Exception {
        HashMap taskMap = new HashMap();
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        String hql = " select task from EomsUndoSheetView as task where task.taskOwner='"
                + userId
                + "' or task.taskOwner='"
                + deptId
                + "' or task.taskOwner in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='"
                + userId + "') order by task.createTime desc";
        taskMap = taskDAO.getTaskListByCondition(hql, startIndex, length);
        return taskMap;
    }

    /**
     * 获取当前角色已处理工单
     *
     * @param userId     用户ID
     * @param deptId     部门ID
     * @param flowName   流程ID
     * @param startIndex
     * @param length
     * @return
     * @throws Exception
     */
    public HashMap getDoneAllSheetTask(Map condition, String userId,
                                       String deptId, String flowName, Integer startIndex, Integer length)
            throws Exception {
        HashMap taskMap = new HashMap();
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        String hql = " select task from EomsDoneSheetView as task where task.taskOwner='"
                + userId
                + "' or task.taskOwner='"
                + deptId
                + "' or task.taskOwner in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='"
                + userId + "') order by task.createTime desc";
        taskMap = taskDAO.getTaskListByCondition(hql, startIndex, length);
        return taskMap;
    }

    /**
     * 获取当前角色所有待处理工单
     *
     * @param userId     用户ID
     * @param deptId     部门ID
     * @param flowName   流程ID
     * @param startIndex
     * @param length
     * @return
     * @throws Exception
     */
    public HashMap getAllUndoAllSheetTask(Map condition, String userId,
                                          String deptId, String flowName) throws Exception {
        HashMap taskMap = new HashMap();
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        String hql = " select task from EomsUndoSheetView as task where task.taskOwner='"
                + userId
                + "' or task.taskOwner='"
                + deptId
                + "' or task.taskOwner in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='"
                + userId + "') order by task.createTime desc";
        List list = taskDAO.getTasksByHql(hql);
        taskMap.put("taskList", list);
        taskMap.put("taskTotal", new Integer(list.size()));
        return taskMap;
    }

    /**
     * 获取当前角色待处理工单,带超时提醒
     *
     * @param userId     用户ID
     * @param deptId     部门ID
     * @param flowName   流程ID
     * @param startIndex
     * @param length
     * @return
     * @throws Exception
     */
    public HashMap getUndoTaskByOverTime(Map condition, String userId, String deptId, String flowName, Integer startIndex, Integer length) throws Exception {
        HashMap taskMap = new HashMap();
        String orderCondition = (String) condition.get("orderCondition");
        condition.put("userId", userId);
        condition.put("deptId", deptId);
        String sql = QuerySqlInit.getUndoTaskSql(condition, flowName);
        StringBuffer hql = new StringBuffer(sql);
        if (length.intValue() != -1) {
            if (!orderCondition.equals("") && orderCondition != null) {
                hql.append("order by " + orderCondition);
            } else {
                hql.append("order by task.createTime desc");
            }
        }
        taskMap = taskDAO.getTaskListByCondition(hql.toString(), startIndex,
                length);
        return taskMap;
    }

    /**
     * 获取当前对象待归档任务列表
     *
     * @param userId     用户ID
     * @param deptId     部门ID
     * @param flowName   流程ID
     * @param startIndex
     * @param length
     * @return
     * @throws Exception
     */
    public HashMap getUnHoldTask(Map condition, String userId, String deptId,
                                 String flowName, Integer startIndex, Integer length)
            throws Exception {
        HashMap taskMap = new HashMap();
        String orderCondition = (String) condition.get("orderCondition");
        condition.put("userId", userId);
        condition.put("deptId", deptId);
        String sql = QuerySqlInit.getUnHoldTaskSql(condition, flowName);
        StringBuffer hql = new StringBuffer(sql);
        if (length.intValue() != -1) {
            if (!orderCondition.equals("") && orderCondition != null) {
                hql.append("order by " + orderCondition);
            } else {
                hql.append("order by task.createTime desc");
            }
        }
        taskMap = taskDAO.getTaskListByCondition(hql.toString(), startIndex,
                length);
        return taskMap;
    }

    /**
     * 获取待处理工单任务列表（不含过滤步骤的任务）
     *
     * @param condition
     * @param userId
     * @param deptId
     * @param flowName
     * @param startIndex
     * @param length
     * @return
     * @throws Exception
     */
    public HashMap getUndoListByFilter(Map condition, String userId, String deptId,
                                       String flowName, Integer startIndex, Integer length)
            throws Exception {
        HashMap taskMap = new HashMap();
        String orderCondition = (String) condition.get("orderCondition");
        condition.put("userId", userId);
        condition.put("deptId", deptId);
        String sql = QuerySqlInit.getUndoListByFilterSql(condition, flowName);
        StringBuffer hql = new StringBuffer(sql);
        if (length.intValue() != -1) {
            if (!orderCondition.equals("") && orderCondition != null) {
                hql.append("order by " + orderCondition);
            } else {
                hql.append("order by task.createTime desc");
            }
        }
        taskMap = taskDAO.getTaskListByCondition(hql.toString(), startIndex,
                length);
        return taskMap;
    }

    /**
     * 获取当前角色草稿工单
     *
     * @param flowName   流程ID
     * @param startIndex
     * @param length
     * @return
     * @throws Exception
     */
    public HashMap getDraftList(Map condition, String flowName, Integer startIndex, Integer length) throws Exception {
        HashMap taskMap = new HashMap();
        String orderCondition = (String) condition.get("orderCondition");
        String userId = (condition.get("userId") == null ? ""
                : (String) condition.get("userId"));
        condition.put("userId", userId);
        String sql = QuerySqlInit.getDraftTaskSql(condition, flowName);
        StringBuffer hql = new StringBuffer(sql);
        if (length.intValue() != -1) {
            if (!orderCondition.equals("") && orderCondition != null) {
                hql.append("order by " + orderCondition);
            } else {
                hql.append("order by task.createTime desc");
            }
        }
        taskMap = taskDAO.getTaskListByCondition(hql.toString(), startIndex,
                length);
        return taskMap;
    }

    /**
     * 获取当前角色已处理工单
     *
     * @param userId     用户ID
     * @param deptId     部门ID
     * @param flowName
     * @param startIndex
     * @param length
     * @return
     * @throws Exception
     */
    public HashMap getDoneTask(Map condition, String userId, String deptId, String flowName, Integer startIndex, Integer length) throws Exception {
        HashMap taskMap = new HashMap();
        String orderCondition = (String) condition.get("orderCondition");
        condition.put("userId", userId);
        condition.put("deptId", deptId);
        String sql = QuerySqlInit.getDoneTaskSql(condition);
        StringBuffer hql = new StringBuffer(sql);
        if (length.intValue() != -1) {
            if (!orderCondition.equals("") && orderCondition != null) {
                hql.append("order by " + orderCondition);
            } else {
                hql.append("order by main.sendTime desc");
            }
        }
        taskMap = taskDAO.getTaskListByCondition(hql.toString(), startIndex,
                length);
        return taskMap;
    }

    /**
     * 保存任务信息
     *
     * @param obj
     * @throws Exception
     */
    public void addTask(Object obj) throws Exception {
        taskDAO.saveObject(obj);
    }

    /**
     * 根据taskId获取task model对象
     *
     * @param id taskId
     * @return
     * @throws Exception
     */
    public ITask getSinglePO(String id) throws Exception {
        ITask task = taskDAO.loadSinglePO(id, this.getTaskModelObject());
        return task;
    }

    public ITask getOperateRoleId(String sheetKey) {
        String hql = " from " + this.getTaskModelObject().getClass().getName() + " as task where task.sheetKey ='" + sheetKey + "' and  ( task.taskStatus=" + Constants.TASK_STATUS_READY + " or task.taskStatus="
                + Constants.TASK_STATUS_CLAIMED + " or task.taskStatus=" + Constants.TASK_STATUS_INACTIVE + " )";
        ITask task = taskDAO.loadTaskPO(hql);
        return task;
    }

    ;

    /**
     * 查询上一个任务执行情况
     *
     * @param preLinkId
     * @return
     */
    public ITask getTaskByPreLinkid(String preLinkId) throws Exception {
        String hql = " from " + this.getTaskModelObject().getClass().getName() + " as task where task.preLinkId='" + preLinkId + "'";

        ITask task = taskDAO.loadTaskPO(hql);
        return task;
    }

    public List getOperateRoleIdAll(String sheetKey) {
        String hql = " from " + this.getTaskModelObject().getClass().getName() + " as task where task.sheetKey ='" + sheetKey + "'";
        List task = taskDAO.loadTaskList(hql);
        List operateRoleIds = new ArrayList();
        for (Iterator iterator = task.iterator(); iterator.hasNext(); ) {
            ITask iTask = (ITask) iterator.next();
            String operateRoleId = iTask.getOperateRoleId();
            operateRoleIds.add(operateRoleId);
        }
        return operateRoleIds;
    }

    ;

    /**
     * 将各种任务信息（流程、业务）放置到task model对象中
     * 此方法为老流程用，即是流程中存在task赋值语句
     *
     * @param task
     * @param methods
     * @param processTemplateMap
     * @param taskBOMap
     * @param taskObjectMap
     * @return
     * @throws Exception
     */
    public HashMap setTaskModel(Object task, HashMap processTemplateMap,
                                HashMap taskBOMap, HashMap taskObjectMap) throws Exception {
        HashMap valueMap = new HashMap();
        String taskPropNames = HibernateConfigurationHelper.getPkColumnName(task
                .getClass()) + ","
                + HibernateConfigurationHelper.getPropNamesWithoutPK(task
                .getClass());
        String[] taskPropName = taskPropNames.split(",");
        for (int i = 0; taskPropName.length > 0 && i < taskPropName.length; i++) {
            String name = taskPropName[i];

//				String key = StaticMethod.nullObject2String(WPSQueryHelper
//						.getPropertyValue(WPSQueryHelper.getTaskModel(), name));
//				
            PropertyFile propertyFile = PropertyFile.getInstance(this.taskConfigPath);
            String key = propertyFile.getPropertyByName(name);

            if (key.equals("")) {
                Object taskObjectTemp = taskObjectMap.get(name);
                valueMap.put(name, taskObjectTemp);
            } else if (key.indexOf("task.") >= 0) {
                key = key.substring(key.lastIndexOf(".") + 1, key.length());
                Object taskBOTemp = taskBOMap.get(key);
                valueMap.put(name, taskBOTemp);
            } else if (key.indexOf("process.") >= 0) {
                key = key.substring(key.lastIndexOf(".") + 1, key.length());
                Object processTemp = processTemplateMap.get(key);
                valueMap.put(name, processTemp);
            }

        }
//			Iterator it=valueMap.keySet().iterator();
//			while(it.hasNext()){
//				String key=it.next().toString();
//				System.out.println("task model name="+key);
//				Object obj=valueMap.get(key);
//				if(obj!=null) {
//					System.out.println("task mode value="+obj.toString());
//				}
//				else {
//					System.out.println("task mode value is null");
//				}
//			}

        return valueMap;
    }

    /**
     * 将各种任务信息（流程、业务）放置到task model对象中
     * 此方法为新流程专用，流程中取消对task对象业务信息的赋值。
     *
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
    public HashMap setTaskModel(Object task, HashMap processTemplateMap,
                                HashMap taskBOMap, HashMap taskObjectMap, HashMap mainObjectMap, HashMap linkObjectMap) throws Exception {
        HashMap valueMap = new HashMap();
        String taskPropNames = HibernateConfigurationHelper.getPkColumnName(task
                .getClass()) + ","
                + HibernateConfigurationHelper.getPropNamesWithoutPK(task
                .getClass());
        String[] taskPropName = taskPropNames.split(",");
        for (int i = 0; taskPropName.length > 0 && i < taskPropName.length; i++) {
            String name = taskPropName[i];
            PropertyFile propertyFile = PropertyFile.getInstance(this.taskConfigPath);
            String key = SheetStaticMethod.nullObject2String(propertyFile.getPropertyByName(name));
            System.out.println("key=" + key);

            if (key.equals("")) {
                Object taskObjectTemp = taskObjectMap.get(name);
                valueMap.put(name, taskObjectTemp);
            } else if (key.indexOf("task.") >= 0) {
                key = key.substring(key.lastIndexOf(".") + 1, key.length());
                Object taskBOTemp = taskBOMap.get(key);
                valueMap.put(name, taskBOTemp);
            } else if (key.indexOf("process.") >= 0) {
                key = key.substring(key.lastIndexOf(".") + 1, key.length());
                Object processTemp = processTemplateMap.get(key);
                valueMap.put(name, processTemp);

                //如果是要取parentProcessInstanceName的值，且值为空的
//					if (key.equals("parentProcessInstanceName") && processTemp == null) {
//						BocoLog.info(this, "process==正常流程流转时，parentProcessName没有取到值，在这里重新取值！");
//						//从当前linkmap里取到aiid即taskId
//						final String piid = StaticMethod.nullObject2String(taskBOMap.get("containmentContextID"));
//						BocoLog.info(this, "process==piid===" + piid);
//						if (!piid.equals("")) {
//							IBusinessFlowAdapterService businessFlowManager = (IBusinessFlowAdapterService) ApplicationContextHolder.getInstance().getBean("BusinessFlowAdapterService");
//							ProcessInstanceData processData = businessFlowManager.getProcessInstanceDataByPiid(piid);
//							if (processData != null) {
//								String parenProcessName = processData.getParentProcessInstanceName();
//								BocoLog.info(this, "process==parenProcessName===" + parenProcessName);
//								valueMap.put(name, parenProcessName);
//							} else {
//								BocoLog.info(this, "根据piid查找的processData为空！");
//							}
//						} else {
//							BocoLog.info(this, "taskBOMap里containmentContextID的值为空！");
//						}
//					}
            } else if (key.indexOf("main.") >= 0) {
                key = key.substring(key.lastIndexOf(".") + 1, key.length());
                Object processTemp = mainObjectMap.get(key);
                valueMap.put(name, processTemp);
            } else if (key.indexOf("link.") >= 0) {
                key = key.substring(key.lastIndexOf(".") + 1, key.length());
                Object processTemp = linkObjectMap.get(key);
                valueMap.put(name, processTemp);
            }
            /**
             * @author wangjianhua
             * @date 2008-10-07
             */
            else if (key.indexOf("#") >= 0) {
                //将@taskBO.owner == "" ? taskOwner : taskBO.owner 从？切割成@taskBO.owner == "" 和 taskOwner : taskBO.owner
                String expressions[] = key.split("\\?");
                if (expressions.length > 1) {
                    //leftExpression表达式为：@taskBO.owner == ""
                    String leftExpression = expressions[0];
                    //rightExpression表达式为： taskOwner : taskBO.owner
                    String rightExpression = expressions[1];
                    //将右边的表达式切割成taskOwner 和taskBO.owner两个部份
                    String[] rightExprs = rightExpression.split(":");
                    if (leftExpression.indexOf("taskBO.") >= 0) {
                        //得到左边@task.owner == ""里的owner
                        int endNumber = leftExpression.indexOf("==");
                        String objectName = leftExpression.substring(leftExpression.lastIndexOf(".") + 1, endNumber).trim();
                        //从流程引擎中找到taskBO里的owner值,它是一个字符串
                        Object taskBOTemp = taskBOMap.get(objectName);
                        String taskBOTempValue = SheetStaticMethod.nullObject2String(taskBOTemp);

                        //得到流程引擎中值在和左边表达式后面的一部 == ""拼成字符串如："" == ""
                        String real = leftExpression.substring(endNumber, leftExpression.length());
                        String realExpression = "\"" + taskBOTempValue + "\"" + real;

                        //判断此逻辑表达式是否为空，如果是真的，则进入下面的语句块
                        if (EomsBshEval.getbooleanFromStringExpression(realExpression)) {
                            if (rightExprs.length > 1) {
                                //one代表右边里的：前面的字符串
                                String rightOne = rightExprs[0].trim();
                                //如果没有.字符，则表示要从TaskObject里取值
                                if (rightOne.indexOf(".") == -1) {
                                    Object taskObjectTemp = taskObjectMap.get(name);
                                    valueMap.put(name, taskObjectTemp);
                                }
                            }
                        } else {//不为空
                            if (rightExprs.length > 1) {
                                String rightTwo = rightExprs[1];
                                if (rightTwo.indexOf(".") >= 0) {
                                    String rightTwoName = rightTwo.substring(rightTwo.indexOf(".") + 1, rightTwo.length()).trim();
                                    Object taskObjectTemp = taskBOMap.get(rightTwoName);
                                    valueMap.put(name, taskObjectTemp);
                                }
                            }
                        }
                    }
                }
            }
        }


        Iterator it = valueMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next().toString();
            System.out.println("task model name=" + key);
            Object obj = valueMap.get(key);
            if (obj != null) {
                System.out.println("task mode value=" + obj.toString());
            } else {
                System.out.println("task mode value is null");
            }
        }

        return valueMap;
    }

    /**
     * 获取当前待处理工单
     *
     * @param operateRoleId 角色ID
     * @param flowName      流程ID
     * @param startIndex
     * @param length
     * @return
     * @throws Exception
     */
    public List getCurrentUndoTask(String mainId) throws Exception {
        List taskList = new ArrayList();
        String hql = " from " + this.getTaskModelObject().getClass().getName() + " as task where task.sheetKey ='"
                + mainId + "' and ( task.taskStatus=" + Constants.TASK_STATUS_READY + " or task.taskStatus="
                + Constants.TASK_STATUS_CLAIMED + " or task.taskStatus=" + Constants.TASK_STATUS_INACTIVE + " ) and task.ifWaitForSubTask='" + Constants.SUB_TASK_FLAG_F + "'"
                + " and task.taskName not in ('" + Constants.TASK_NAME_COPY + "','" + Constants.TASK_NAME_ADVICE + "','" + Constants.TASK_NAME_REPLY + "')";

        taskList = taskDAO.loadTaskList(hql);
        return taskList;
    }

    /**
     * 获取除当前角色外的其他步骤的任务工单
     *
     * @param taskOwner  角色ID
     * @param flowName   流程ID
     * @param startIndex
     * @param length
     * @return
     * @throws Exception
     */
    public List getExceptMeTask(String mainId, String linkName, String taskOwner) throws Exception {
        List taskList = new ArrayList();
        String hql = " select distinct task.operateRoleId,task.operateType,task.taskOwner,task.taskDisplayName "
                + "from " + this.getTaskModelObject().getClass().getName() + " as task," + linkName + " as link where task.sheetKey ='"
                + mainId + "' and  task.taskStatus=" + Constants.TASK_STATUS_FINISHED + " and task.taskOwner!='" + taskOwner + "'"
                + " and task.sheetKey=link.mainId"
                + " and task.subTaskFlag is null"
                + " and task.taskName not in ('" + Constants.TASK_NAME_COPY + "','" + Constants.TASK_NAME_ADVICE + "','" + Constants.TASK_NAME_REPLY + "')";

        taskList = taskDAO.loadTaskList(hql);
        return taskList;
    }


    /**
     * @param parentTaskId 父任务的实例号
     */
    public List getUndealTaskListByParentTaskId(String parentTaskId) throws Exception {
        List taskList = new ArrayList();
        String hql = "from " + this.getTaskModelObject().getClass().getName() + " as task where task.parentTaskId ='" + parentTaskId + "'"
                + " and task.taskStatus=5"
                + " and (task.subTaskDealFalg is null or  task.subTaskDealFalg<>'true') ";

        taskList = taskDAO.loadTaskList(hql);
        return taskList;
    }

    /*
     * (non-Javadoc)
     * @see com.boco.eoms.sheet.base.service.ITaskService#getNotReversionListByParentTaskId(java.lang.String)
     */
    public List getNotReversionListByParentTaskId(String parentTaskId) throws Exception {
        List taskList = new ArrayList();
        String hql = "from " + this.getTaskModelObject().getClass().getName() + " as task where task.parentTaskId ='" + parentTaskId + "'"
                + " and (task.subTaskDealFalg is null or  task.subTaskDealFalg<>'true' or task.taskStatus !=5 ) ";

        taskList = taskDAO.loadTaskList(hql);
        return taskList;
    }

    /**
     * 根据main主键
     */
    public List getTasksBySheetKey(String sheetKey, String condition) throws Exception {
        String hql = " from " + this.getTaskModelObject().getClass().getName() + " as task "
                + " where  task.sheetKey = '" + sheetKey + "'"
                + " and task.taskName in (" + condition + ") "
                + " and (task.subTaskFlag is null or task.subTaskFlag = 'false' )"
                + " order by task.createTime";
        return taskDAO.getTasksBySheetKey(hql);
    }

    /**
     * 根据main主键
     */
    public List getTasksByCondition(String condition) throws Exception {
        String hql = " from " + this.getTaskModelObject().getClass().getName()
                + " where " + condition
                + " order by createTime";
        return taskDAO.getTasksByHql(hql);
    }

    /**
     * 查找子任务
     */
    public List getAllSubTask(ArrayList nodeList, ArrayList floorList,
                              Node parNode, int floorNumber) throws Exception {
        String hql = " from " + this.getTaskModelObject().getClass().getName() + " as task "
                + " where  task.parentTaskId = '" + parNode.getId() + "'"
                + " order by task.createTime";
        return taskDAO.getAllSubTask(hql);
    }

    /**
     * 获取某工单某阶段的任务
     *
     * @param sheetKey
     * @param taskName
     * @return
     * @throws Exception
     */
    public ITask getTask(String sheetKey, String taskName) throws Exception {

        String hql = " from "
                + this.getTaskModelObject().getClass().getName() + " as task where task.sheetKey='" + sheetKey + "' "
                + " and task.taskName = '" + taskName + "'  and  task.taskStatus='" + Constants.TASK_STATUS_READY + "'";
        return taskDAO.loadTaskPO(hql);
    }

    /*
     * (non-Javadoc)
     * @see com.boco.eoms.sheet.base.service.ITaskService#getTasksByTaskNameAndSheetId(java.lang.String, java.lang.String)
     */
    public List getTasksByTaskNameAndSheetId(String taskName, String mainId) throws Exception {
        String hql = " from " + this.getTaskModelObject().getClass().getName() + " as task "
                + " where  task.taskName = '" + taskName + "'"
                + " and task.sheetKey = '" + mainId + "'"
                + " order by task.createTime";
        return taskDAO.getAllSubTask(hql);
    }

    public List getAiTasksByTaskNameAndSheetId(String taskName, String mainId) throws Exception {
        String hql = " from " + this.getTaskModelObject().getClass().getName() + " as task "
                + " where  task.taskName = '" + taskName + "'"
                + " and task.sheetKey = '" + mainId + "'"
                + " and task.parentTaskId like '_AI:%' order by task.createTime";
        return taskDAO.getAllSubTask(hql);
    }

    public List getTkiTasksByTaskNameAndSheetId(String taskName, String mainId) throws Exception {
        String hql = " from " + this.getTaskModelObject().getClass().getName() + " as task "
                + " where  task.taskName = '" + taskName + "'"
                + " and task.sheetKey = '" + mainId + "'"
                + " and task.parentTaskId like '_TKI:%' order by task.createTime";
        return taskDAO.getAllSubTask(hql);
    }

    /*
     * (non-Javadoc)
     * @see com.boco.eoms.sheet.base.service.ITaskService#getTasksByCreateTimeAndUserId(java.util.Date, java.util.Date, java.lang.String)
     */
    public List getTasksByCreateTimeAndUserId(String startTime, String endTime, String userId, String moduleName) throws Exception {
        String module = StaticMethod.firstToUpperCase(moduleName) + "Task";
        String hql = " from " + module + " as task "
                + " where task.createTime between '" + startTime + "' and '" + endTime + "'"
                + " task.taskOwner = '" + userId + "'"
                + " order by task.createTime desc";
        return taskDAO.getTasksByHql(hql);
    }

    /**
     * 获取派发给本角色的未被抢单的工单列表
     *
     * @param userId     用户ID
     * @param startIndex
     * @param length
     * @return HashMap
     * @throws Exception
     */
    public HashMap getUndoTaskByRole(Map condition, String userId, Integer startIndex, Integer length) throws Exception {
        HashMap taskMap = new HashMap();
        String orderCondition = (String) condition.get("orderCondition");
        String flowName = (String) condition.get("flowName");
        condition.put("userId", userId);
        String sql = QuerySqlInit.getUndoTaskByRoleSql(condition, flowName);
        StringBuffer hql = new StringBuffer(sql);
        if (length.intValue() != -1) {
            if (!orderCondition.equals("") && orderCondition != null) {
                hql.append("order by " + orderCondition);
            } else {
                hql.append("order by task.createTime desc");
            }
        }
        taskMap = taskDAO.getTaskListByCondition(hql.toString(), startIndex,
                length);
        return taskMap;
    }

    /**
     * 获取本角色的已被组内其他人员抢单后的工单
     *
     * @param userId     用户ID
     * @param startIndex
     * @param length
     * @return
     * @throws Exception
     */
    public HashMap getDoneTaskByRole(Map condition, String userId, Integer startIndex, Integer length) throws Exception {
        HashMap taskMap = new HashMap();
        String orderCondition = (String) condition.get("orderCondition");
        condition.put("userId", userId);
        String sql = QuerySqlInit.getDoneTaskByRoleSql(condition);
        StringBuffer hql = new StringBuffer(sql);
        if (length.intValue() != -1) {
            if (!orderCondition.equals("") && orderCondition != null) {
                hql.append("order by main." + orderCondition + "desc");
            } else {
                hql.append("order by main.sendTime desc");
            }
        }
        System.out.println("sql=======" + hql);
        taskMap = taskDAO.getTaskListByCondition(hql.toString(), startIndex,
                length);
        return taskMap;
    }

    /**
     * 获取当前角色所有待处理工单
     *
     * @param userId     用户ID
     * @param deptId     部门ID
     * @param flowName   流程ID
     * @param startIndex
     * @param length
     * @return
     * @throws Exception
     */
    public HashMap getAllUndoTask(Map condition, String userId, String deptId, String flowName) throws Exception {
        HashMap taskMap = new HashMap();
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        String hql = "select distinct task from " + this.getTaskModelObject().getClass().getName() + " as task," + mainObject.getClass().getName() + " main,TawSystemUserRefRole as userrefrole " +
                "where ((task.taskOwner='" + userId + "' or task.taskOwner='" + deptId + "') or (task.taskOwner=userrefrole.subRoleid and userrefrole.userid='" + userId + "'))"
                + " and ( task.taskStatus=" + Constants.TASK_STATUS_READY + " or task.taskStatus="
                + Constants.TASK_STATUS_CLAIMED + " or task.taskStatus=" + Constants.TASK_STATUS_INACTIVE + " ) "
                + " and task.taskDisplayName<>'草稿'"
                //增加main.deleted<>1的条件，过滤已经隐藏或删除的工单 add by jialei
                + " and main.id=task.sheetKey and main.deleted<>'1'"
                + " order by task.createTime desc";
        List list = taskDAO.getTasksByHql(hql);
        taskMap.put("taskList", list);
        taskMap.put("taskTotal", new Integer(list.size()));
        return taskMap;
    }

    /**
     * 获取当前角色当前工单待处理任务
     *
     * @param subRoleId
     * @param sheetKey
     * @return
     * @throws Exception
     */
    public ITask getUndoTaskByRoleAndSheetKey(String subRoleId, String sheetKey) throws Exception {
        ITask task = null;
        String hql = " from " + this.getTaskModelObject().getClass().getName() + " as task  where task.sheetKey='" + sheetKey
                + "' and task.taskOwner='" + subRoleId
                + "' and ( task.taskStatus=" + Constants.TASK_STATUS_READY + " or task.taskStatus="
                + Constants.TASK_STATUS_CLAIMED + " or task.taskStatus=" + Constants.TASK_STATUS_INACTIVE + " ) ";
        System.out.println("getUndoTaskByRoleAndSheetKey hql=" + hql);
        List list = taskDAO.getTasksByHql(hql);
        if (list.size() > 0) {
            task = (ITask) list.get(0);
        }
        System.out.println("getUndoTaskByRoleAndSheetKey end =====");
        return task;
    }

    /**
     * 获取本角色的已被组内其他人员抢单但未处理的工单
     *
     * @param userId     用户ID
     * @param startIndex
     * @param length
     * @return
     * @throws Exception
     */
    public HashMap getAcceptTaskByRole(Map condition, String userId, Integer startIndex, Integer length) throws Exception {
        return null;
    }

    /**
     * 获取超时待办工单的数目
     *
     * @param userId     用户ID
     * @param deptId     部门ID
     * @param flowName   流程ID
     * @param startIndex
     * @param length
     * @return
     * @throws Exception
     */
    public Integer getOverTimeTaskCount(Map condition, String userId, String deptId) throws Exception {
        BaseMain mainObject = (BaseMain) condition.get("mainObject");
        String hqlDialect = ApplicationContextHolder.getInstance().getHQLDialect().trim();
        StringBuffer addCondition = new StringBuffer(" and ");
        String date = StaticMethod.date2String(new Date());
        if (hqlDialect.equals("org.hibernate.dialect.OracleDialect")) {
            addCondition.append("task.completeTimeLimit< to_date('" + date + "','yyyy-MM-dd HH24:mi:ss')");
        } else {
            addCondition.append("task.completeTimeLimit<'" + date + "'");
        }
        String hql = "select count(distinct task.id) from "
                + this.getTaskModelObject().getClass().getName() + " as task,"
                + mainObject.getClass().getName() + " as main "
                + "where ((task.taskOwner='" + userId + "' or task.taskOwner='" + deptId + "')"
                + " or task.taskOwner in (select userrefrole.subRoleid from TawSystemUserRefRole as userrefrole where userrefrole.userid='" + userId + "'))"
                + " and (task.taskStatus=" + Constants.TASK_STATUS_READY
                + " or task.taskStatus=" + Constants.TASK_STATUS_CLAIMED
                + " or task.taskStatus=" + Constants.TASK_STATUS_INACTIVE + ")"
                + " and task.taskDisplayName<>'草稿'"
                + " and main.id=task.sheetKey and main.deleted<>'1'"
                + " and (task.ifWaitForSubTask='false' or (task.ifWaitForSubTask='true' and task.id in (select task1.parentTaskId from " + this.getTaskModelObject().getClass().getName() + " as task1 where task1.subTaskFlag='true' and (task1.subTaskDealFalg='false' or task1.subTaskDealFalg is null ) and task1.taskStatus='" + Constants.TASK_STATUS_FINISHED + "')))"
                + addCondition.toString();
        List countList = taskDAO.getTasksByHql(hql);
        Integer count;
        if (!countList.isEmpty()) {
            count = (Integer) countList.get(0);
        } else
            count = new Integer(0);

        return count;
    }

    /**
     * 获取当前延期申请的工单
     *
     * @param userId     用户ID
     * @param deptId     部门ID
     * @param startIndex
     * @param length
     * @return
     * @throws Exception
     */
    public HashMap deferAppList(Map condition, String userId, String deptId, Integer startIndex, Integer length)
            throws Exception {
        HashMap taskMap = new HashMap();
        String orderCondition = (String) condition.get("orderCondition");
        condition.put("userId", userId);
        condition.put("deptId", deptId);
        String sql = QuerySqlInit.deferAppListSql(condition);
        StringBuffer hql = new StringBuffer(sql);
        if (length.intValue() != -1) {
            if (!orderCondition.equals("") && orderCondition != null) {
                hql.append("order by " + orderCondition);
            } else {
                hql.append("order by main.sendTime desc");
            }
        }
        taskMap = taskDAO.getTaskListByCondition(hql.toString(), startIndex, length);
        return taskMap;
    }
}

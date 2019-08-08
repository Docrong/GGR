
package com.boco.eoms.sheet.resourceaffirm.service.impl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.service.impl.TaskServiceImpl;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.HibernateConfigurationHelper;
import com.boco.eoms.sheet.resourceaffirm.service.IResourceAffirmTaskManager;

public class ResourceAffirmTaskManagerImpl extends TaskServiceImpl implements IResourceAffirmTaskManager {
    /**
     * 获取当前时间内所有未处理的工单
     *
     * @return
     * @throws Exception
     */
    public List getAllUndoTaskList(Map condition) throws Exception {
        BaseMain mainObject = (BaseMain) condition.get("mainObject");

        String taskColumnName = HibernateConfigurationHelper.getColumnNames(this.getTaskModelObject().getClass());
        String mainTableName = HibernateConfigurationHelper.getTableName(mainObject.getClass());
        String taskTableName = HibernateConfigurationHelper.getTableName(this.getTaskModelObject().getClass());

        taskColumnName = "task." + taskColumnName.replace(",", ", task.");
        String hql = "select distinct " + taskColumnName + ", main.mainAdviceNum from " + taskTableName + " task, " + mainTableName + "  main "
                + " where (task.taskStatus='" + Constants.TASK_STATUS_READY + "'"
                + " or task.taskStatus='" + Constants.TASK_STATUS_CLAIMED + "'"
                + " or task.taskStatus='" + Constants.TASK_STATUS_INACTIVE + "')"
                + " and task.taskDisplayName<>'草稿'"
                + " and main.id=task.sheetKey and main.deleted=0"
                + " and (task.ifWaitForSubTask='false' or (task.ifWaitForSubTask='true' and task.id in (select task1.parentTaskId from " + taskTableName + "  task1 where task1.subTaskFlag='true' and (task1.subTaskDealFalg='false' or task1.subTaskDealFalg is null ) and task1.taskStatus='" + Constants.TASK_STATUS_FINISHED + "')))";
        IDownLoadSheetAccessoriesService mgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder
                .getInstance().getBean("IDownLoadSheetAccessoriesService");
        List results = mgr.getSheetAccessoriesList(hql);
//		结果list构造
        List resultList = new ArrayList();
        if (results != null && results.size() > 0) {
            Method[] method = this.getTaskModelObject().getClass().getMethods();
            for (int i = 0; i < results.size(); i++) {
                Map resultMap = (Map) results.get(i);
                if (resultMap != null && resultMap.size() > 0) {
                    //通过反射为task对象赋值
                    Object[] object = new Object[2];
                    ITask tmpTask = (ITask) this.getTaskModelObject().getClass().newInstance();
                    tmpTask = (ITask) this.makeModelObject(tmpTask, resultMap, method);
                    String mainAdviceNum = StaticMethod.nullObject2String(resultMap.get("mainAdviceNum"));
                    object[0] = tmpTask;
                    object[1] = mainAdviceNum;
                    resultList.add(object);
                }
            }
        }
        return resultList;
    }

    /**
     * 通过反射机制，将查询结果中的值放到model中
     *
     * @param model
     * @param resultMap
     * @param method
     * @return
     * @throws Exception
     * @author jialei
     */
    public Object makeModelObject(Object model, Map resultMap, Method[] method) throws Exception {
        for (int j = 0; j < method.length; j++) {
            Method meth = method[j];
            String methName = meth.getName();
            if (methName.startsWith("set")) {
                Class[] clazz = meth.getParameterTypes();
                String paraClassName = clazz[0].getName();
                String tmpName = (methName.substring(3)).toUpperCase();
                Object obj = resultMap.get(tmpName);
                if (obj != null) {
                    if (paraClassName.equals("java.lang.String")) {
                        meth.invoke(model, new Object[]{(String) obj});
                    } else if (paraClassName.equals("java.lang.Integer")) {
                        BigDecimal tmp = (BigDecimal) obj;
                        meth.invoke(model, new Object[]{new Integer(tmp.intValue())});
                    } else if (paraClassName.equals("java.util.Date")) {
                        Timestamp tmp = (Timestamp) obj;
                        Date tmpDate = new Date(tmp.getTime());
                        meth.invoke(model, new Object[]{tmpDate});
                    } else if (paraClassName.equals("java.lang.Long")) {
                        BigDecimal tmp = (BigDecimal) obj;
                        meth.invoke(model, new Object[]{new Long(tmp.longValue())});
                    }
                }
            }
        }
        return model;
    }
}

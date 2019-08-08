package com.boco.eoms.sheet.base.service.ejb;

import java.util.HashMap;

/**
 * Remote interface for Enterprise Bean: SaveDataService
 */
public interface SaveDataService extends javax.ejb.EJBObject {
    //public HashMap saveMain(Object obj,String modelName,int startFlag, String mainServiceBeanId) throws Exception;
    public void saveMain(Object obj, String modelName) throws Exception;

    public String saveLink(Object obj, String modelName) throws Exception;

    public void saveTask(HashMap taskMap, String taskBeanId) throws Exception;

    //public String saveMainAndLink(Object mainObj,String mainModelName,
    //	  Object linkObj,String linkModelName)throws Exception;
    //public void init(String mainServiceBeanId) throws Exception;
    public void saveOrUpdateMain(Object obj, String modelName) throws Exception;

    public String saveOrUpdateLink(Object obj, String modelName) throws Exception;

    public void saveOrUpdateTask(HashMap taskMap, String taskBeanId) throws Exception;

    public void updateMain(HashMap mainMap, String mainBeanId) throws Exception;

    public void updateLink(HashMap linkMap, String linkBeanId) throws Exception;

    public void updateTask(HashMap taskMap, String taskBeanId) throws Exception;

    public void updateTaskByStatus(String sheetKey, String taskBeanId) throws Exception;

    public void updateTaskBySubTaskFlag(String taskId, String subTaskFlag, String taskBeanId) throws Exception;

    public void updateSheetRelationState(String sheetKey) throws Exception;

    public void updateTaskState(String taskId, String taskState, String taskBeanId) throws Exception;

    public String getUUID() throws Exception;

    public void invokeWfInterface(String mainBeanId, String sheetKey, String linkId, String linkBeanId, String interfaceType, String methodType, String sendType) throws Exception;
}

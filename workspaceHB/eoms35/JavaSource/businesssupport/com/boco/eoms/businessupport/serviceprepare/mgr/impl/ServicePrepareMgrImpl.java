package com.boco.eoms.businessupport.serviceprepare.mgr.impl;


import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.serviceprepare.dao.ServicePrepareDao;
import com.boco.eoms.businessupport.serviceprepare.mgr.ServicePrepareMgr;
import com.boco.eoms.businessupport.serviceprepare.model.ProcessTasks;
import com.boco.eoms.businessupport.serviceprepare.model.ProcessType;
import com.boco.eoms.businessupport.serviceprepare.model.ProductSpecification;
import com.boco.eoms.businessupport.serviceprepare.model.ProductsServiceDirectory;
import com.boco.eoms.businessupport.serviceprepare.model.ProfessionalServiceDirectory;
import com.boco.eoms.businessupport.serviceprepare.model.ServiceConfiguration;
import com.boco.eoms.businessupport.serviceprepare.model.TaskLinks;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;

public class ServicePrepareMgrImpl implements ServicePrepareMgr {

    private ProcessTasks processTasksObject;
    private ProcessType processTypeObject;
    private ProductSpecification productSpecificationObject;
    private ProductsServiceDirectory productsServiceDirectoryObject;
    private ProfessionalServiceDirectory professionalServiceDirectoryObject;
    private ServiceConfiguration serviceConfigurationObject;
    private TaskLinks taskLinksObject;
    private ServicePrepareDao servicePrepareDAO;

    public ProcessTasks getProcessTasksObject() {
        return processTasksObject;
    }

    public void setProcessTasksObject(ProcessTasks processTasksObject) {
        this.processTasksObject = processTasksObject;
    }

    public ProcessType getProcessTypeObject() {
        return processTypeObject;
    }

    public void setProcessTypeObject(ProcessType processTypeObject) {
        this.processTypeObject = processTypeObject;
    }

    public ProductSpecification getProductSpecificationObject() {
        return productSpecificationObject;
    }

    public void setProductSpecificationObject(
            ProductSpecification productSpecificationObject) {
        this.productSpecificationObject = productSpecificationObject;
    }

    public ProductsServiceDirectory getProductsServiceDirectoryObject() {
        return productsServiceDirectoryObject;
    }

    public void setProductsServiceDirectoryObject(
            ProductsServiceDirectory productsServiceDirectoryObject) {
        this.productsServiceDirectoryObject = productsServiceDirectoryObject;
    }

    public ProfessionalServiceDirectory getProfessionalServiceDirectoryObject() {
        return professionalServiceDirectoryObject;
    }

    public void setProfessionalServiceDirectoryObject(
            ProfessionalServiceDirectory professionalServiceDirectoryObject) {
        this.professionalServiceDirectoryObject = professionalServiceDirectoryObject;
    }

    public ServiceConfiguration getServiceConfigurationObject() {
        return serviceConfigurationObject;
    }

    public void setServiceConfigurationObject(
            ServiceConfiguration serviceConfigurationObject) {
        this.serviceConfigurationObject = serviceConfigurationObject;
    }

    public ServicePrepareDao getServicePrepareDAO() {
        return servicePrepareDAO;
    }

    public void setServicePrepareDAO(ServicePrepareDao servicePrepareDAO) {
        this.servicePrepareDAO = servicePrepareDAO;
    }

    public TaskLinks getTaskLinksObject() {
        return taskLinksObject;
    }

    public void setTaskLinksObject(TaskLinks taskLinksObject) {
        this.taskLinksObject = taskLinksObject;
    }

    public List getProcessTasksByParentTaskId(String id) throws Exception {
        String hql = " from " + this.getProcessTasksObject().getClass().getName() + " as processtasks  "
                + "where processtasks.parentTaskId='" + id + "'";
        return this.getServicePrepareDAO().getTableListBySql(hql);

    }

    public ProcessTasks getProcessTasksSinglePO(String id) throws Exception {
        // TODO Auto-generated method stub
        return servicePrepareDAO.getProcessTasksSinglePO(id, this.getProcessTasksObject());
    }

    public ProcessType getProcessTypeSinglePO(String id) throws Exception {
        // TODO Auto-generated method stub
        return servicePrepareDAO.getProcessTypeSinglePO(id, this.getProcessTypeObject());
    }

    public ProductSpecification getProductSpecificationSinglePO(String id) throws Exception {
        // TODO Auto-generated method stub
        return servicePrepareDAO.getProductSpecificationSinglePO(id, this.getProductSpecificationObject());
    }

    public ProductsServiceDirectory getProductsServiceDirectorySinglePO(String id) throws Exception {
        // TODO Auto-generated method stub
        return servicePrepareDAO.getProductsServiceDirectorySinglePO(id, this.getProductsServiceDirectoryObject());
    }

    public ProfessionalServiceDirectory getProfessionalServiceDirectorySinglePO(String id) throws Exception {
        // TODO Auto-generated method stub
        return servicePrepareDAO.getProfessionalServiceDirectorySinglePO(id, this.getProfessionalServiceDirectoryObject());
    }

    public ServiceConfiguration getServiceConfigurationSinglePO(String id) throws Exception {
        // TODO Auto-generated method stub
        return servicePrepareDAO.getServiceConfigurationSinglePO(id, this.getServiceConfigurationObject());
    }

    public TaskLinks getTaskLinksSinglePO(String id) throws Exception {
        // TODO Auto-generated method stub
        return servicePrepareDAO.getTaskLinksSinglePO(id, this.getTaskLinksObject());
    }

    public String addObject(Object obj) throws Exception {
        // TODO Auto-generated method stub
        Method getterMethod = obj.getClass().getMethod("getId", new Class[]{});
        Object idObject = getterMethod.invoke(obj, new Object[]{});
        String id = "";
        if (idObject != null) {
            id = idObject.toString();
        }
        if (id == null || id.equals("")) {
            id = UUIDHexGenerator.getInstance()
                    .getID();
            Method setterMethod = obj.getClass().getMethod("setId", new Class[]{String.class});
            setterMethod.invoke(obj, new Object[]{id});
        }
        servicePrepareDAO.saveOrUpdate(obj);
        return id;
    }

    public ProcessType getProcessTypeByFlowId(String flowId) throws Exception {
        // TODO Auto-generated method stub
        return servicePrepareDAO.getProcessTypeByFlowId(flowId, this.getProcessTypeObject());
    }

    public ProcessTasks getProcessTasksByParentLinkId(String parentLinkId) throws Exception {
        // TODO Auto-generated method stub
        return servicePrepareDAO.getProcessTasksByParentLinkId(parentLinkId, this.getProcessTasksObject());
    }

    public List getAllListByCondition(String flowId, String taskName) throws Exception {
        // TODO Auto-generated method stub
        String sql = " select serviceConfiguration,professionalServiceDirectory"
                + " from " + this.getServiceConfigurationObject().getClass().getName() + "  serviceConfiguration,"
                + this.getProfessionalServiceDirectoryObject().getClass().getName() + "  professionalServiceDirectory,"
                + this.getTaskLinksObject().getClass().getName() + "  taskLinks where serviceConfiguration.processId ='" + flowId + "' and serviceConfiguration.taskId=taskLinks.id"
                + " and taskLinks.sheetCode='" + flowId + "' and taskLinks.phaseId='" + taskName + "' and serviceConfiguration.specialtyId=professionalServiceDirectory.id"
                + " and serviceConfiguration.deleted<>'1' and taskLinks.deleted<>'1' and professionalServiceDirectory.deleted<>'1'";
        return servicePrepareDAO.getAllListBySql(sql);
    }

    /**
     * @param flowId      流程编号
     * @param taskName    环节名称
     * @param specialType 业务类别
     * @return
     * @throws Exception
     */
    public List getAllListByCondition(String flowId, String taskName, String specialType) throws Exception {
        // TODO Auto-generated method stub
        String sql = " select serviceConfiguration,professionalServiceDirectory"
                + " from " + this.getServiceConfigurationObject().getClass().getName() + "  serviceConfiguration,"
                + this.getProductSpecificationObject().getClass().getName() + " productSpecification , "
                + this.getProfessionalServiceDirectoryObject().getClass().getName() + "  professionalServiceDirectory,"
                + this.getTaskLinksObject().getClass().getName() + "  taskLinks where serviceConfiguration.processId ='" + flowId + "' and serviceConfiguration.taskId=taskLinks.id"
                + " and taskLinks.sheetCode='" + flowId + "' and taskLinks.phaseId='" + taskName + "' and serviceConfiguration.specialtyId=professionalServiceDirectory.id and productSpecification.id=serviceConfiguration.businessId and productSpecification.code='" + specialType + "'"
                + " and serviceConfiguration.deleted<>'1' and taskLinks.deleted<>'1' and professionalServiceDirectory.deleted<>'1' order by professionalServiceDirectory.sortId";
        return servicePrepareDAO.getAllListBySql(sql);
    }

    public HashMap getAllServiceConfigurationListByCondition(Map condition, Integer curPage, Integer pageSize) throws Exception {
        String flowId = StaticMethod.nullObject2String(condition.get("flowId"));
        String taskId = StaticMethod.nullObject2String(condition.get("taskId"));
        String id = StaticMethod.nullObject2String(condition.get("id"));
        String orderCondition = StaticMethod.nullObject2String(condition.get("orderCondition"));
        String whereSql = "";
        if (!id.equals("")) {
            whereSql = whereSql + " and serviceConfiguration.id='" + id + "'";
        }
        if (!flowId.equals("")) {
            whereSql = " and serviceConfiguration.flowId='" + flowId + "'";
        }
        if (!taskId.equals("")) {
            whereSql = whereSql + " and serviceConfiguration.taskId='" + taskId + "'";
        }

        String sql = " select distinct serviceConfiguration,professionalServiceDirectory,taskLinks,processType,productSpecification"
                + " from " + this.getServiceConfigurationObject().getClass().getName() + "  serviceConfiguration,"
                + this.getProfessionalServiceDirectoryObject().getClass().getName() + "  professionalServiceDirectory,"
                + this.getTaskLinksObject().getClass().getName() + "  taskLinks,"
                + this.getProcessTypeObject().getClass().getName() + "  processType,"
                + this.getProductSpecificationObject().getClass().getName() + " productSpecification  "
                + "where serviceConfiguration.taskId=taskLinks.id and serviceConfiguration.specialtyId=professionalServiceDirectory.id"
                + " and serviceConfiguration.processId=processType.flowId and serviceConfiguration.businessId=productSpecification.id"
                + " and serviceConfiguration.deleted<>'1'";

        StringBuffer hql = new StringBuffer(sql);
        if (pageSize.intValue() != -1) {
            if (!orderCondition.equals("") && orderCondition != null) {
                hql.append(" order by " + orderCondition);
            } else {
                hql.append(" order by createTime desc");
            }
        }
        // TODO Auto-generated method stub
        return servicePrepareDAO.getQueryListBySql(hql.toString(), curPage, pageSize);
    }

    public List getProcessTypListByCondition(Map condition) throws Exception {
        // TODO Auto-generated method stub
        String sql = " from " + this.getProcessTypeObject().getClass().getName() + " where deleted<>'1'";
        return servicePrepareDAO.getTableListBySql(sql);
    }

    public List getTaskLinksByFlowId(String flowId) throws Exception {
        // TODO Auto-generated method stub
        String sql = " from " + this.getTaskLinksObject().getClass().getName() + " taskLinks where taskLinks.sheetCode=" + flowId + "";
        return servicePrepareDAO.getTableListBySql(sql);
    }

    public List getAllProfessionalServiceDirectoryList() throws Exception {
        // TODO Auto-generated method stub
        String sql = " from " + this.getProfessionalServiceDirectoryObject().getClass().getName() + " professionalServiceDirectory "
                + " where professionalServiceDirectory.deleted<>'1'";
        return servicePrepareDAO.getTableListBySql(sql);
    }

    public List getAllProductSpecificationList() throws Exception {
        // TODO Auto-generated method stub
        String sql = " from " + this.getProductSpecificationObject().getClass().getName() + " productSpecification "
                + " where productSpecification.deleted<>'1'";
        return servicePrepareDAO.getTableListBySql(sql);
    }

    public HashMap getAllProductSpecificationListByCondition(Map condition, Integer curPage, Integer pageSize) throws Exception {
        // TODO Auto-generated method stub
        String orderCondition = StaticMethod.nullObject2String(condition.get("orderCondition"));
        String sql = " from " + this.getProductSpecificationObject().getClass().getName() + " productSpecification where "
                + " deleted<>'1'";
        StringBuffer hql = new StringBuffer(sql);
        if (pageSize.intValue() != -1) {
            if (!orderCondition.equals("") && orderCondition != null) {
                hql.append(" order by " + orderCondition);
            } else {
                hql.append(" order by name desc");
            }
        }
        return servicePrepareDAO.getQueryListBySql(hql.toString(), curPage, pageSize);
    }

}
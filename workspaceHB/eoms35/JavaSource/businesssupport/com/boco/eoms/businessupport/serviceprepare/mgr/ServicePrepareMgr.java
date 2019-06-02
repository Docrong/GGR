package com.boco.eoms.businessupport.serviceprepare.mgr;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.businessupport.serviceprepare.mgr.ServicePrepareMgr;
import com.boco.eoms.businessupport.serviceprepare.model.ProcessTasks;
import com.boco.eoms.businessupport.serviceprepare.model.ProcessType;
import com.boco.eoms.businessupport.serviceprepare.model.ProductSpecification;
import com.boco.eoms.businessupport.serviceprepare.model.ProductsServiceDirectory;
import com.boco.eoms.businessupport.serviceprepare.model.ProfessionalServiceDirectory;
import com.boco.eoms.businessupport.serviceprepare.model.ServiceConfiguration;
import com.boco.eoms.businessupport.serviceprepare.model.TaskLinks;

 public interface ServicePrepareMgr {
		public abstract List  getProcessTasksByParentTaskId(String parentTaskId)throws Exception;
		public ProcessTasks getProcessTasksSinglePO(String id) throws Exception;	
		public ProcessType getProcessTypeSinglePO(String id) throws Exception;	
		public ProductSpecification getProductSpecificationSinglePO(String id) throws Exception;	
		public ProductsServiceDirectory getProductsServiceDirectorySinglePO(String id) throws Exception;	
		public ProfessionalServiceDirectory getProfessionalServiceDirectorySinglePO(String id) throws Exception;	
		public ServiceConfiguration getServiceConfigurationSinglePO(String id) throws Exception;	
		public TaskLinks getTaskLinksSinglePO(String id) throws Exception;	
	    public String addObject(Object obj) throws Exception;
		public ProcessType getProcessTypeByFlowId(String flowId) throws Exception;	
		public ProcessTasks getProcessTasksByParentLinkId(String parentLinkId) throws Exception;
		public abstract List  getAllListByCondition(String flowId,String taskName)throws Exception;		
		public abstract List  getAllListByCondition(String flowId,String taskName,String specialType)throws Exception;		
		public abstract HashMap  getAllServiceConfigurationListByCondition(Map condition,Integer curPage,Integer pageSize)throws Exception;			
		public abstract List  getProcessTypListByCondition(Map condition)throws Exception;
		public abstract List  getTaskLinksByFlowId(String flowId)throws Exception;		
		public abstract List  getAllProfessionalServiceDirectoryList()throws Exception;			
		public abstract List  getAllProductSpecificationList()throws Exception;		
		public abstract HashMap  getAllProductSpecificationListByCondition(Map condition,Integer curPage,Integer pageSize)throws Exception;		
 }
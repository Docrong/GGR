package com.boco.eoms.businessupport.serviceprepare.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.businessupport.serviceprepare.model.ProcessTasks;
import com.boco.eoms.businessupport.serviceprepare.model.ProcessType;
import com.boco.eoms.businessupport.serviceprepare.model.ProductSpecification;
import com.boco.eoms.businessupport.serviceprepare.model.ProductsServiceDirectory;
import com.boco.eoms.businessupport.serviceprepare.model.ProfessionalServiceDirectory;
import com.boco.eoms.businessupport.serviceprepare.model.ServiceConfiguration;
import com.boco.eoms.businessupport.serviceprepare.model.TaskLinks;

public interface ServicePrepareDao extends Dao {
    public abstract List getTableListBySql(String sql)throws HibernateException;	
	public ProcessTasks getProcessTasksSinglePO(String id,Object obj) throws Exception;	
	public ProcessType getProcessTypeSinglePO(String id,Object obj) throws Exception;	
	public ProductSpecification getProductSpecificationSinglePO(String id,Object obj) throws Exception;	
	public ProductsServiceDirectory getProductsServiceDirectorySinglePO(String id,Object obj) throws Exception;	
	public ProfessionalServiceDirectory getProfessionalServiceDirectorySinglePO(String id,Object obj) throws Exception;	
	public ServiceConfiguration getServiceConfigurationSinglePO(String id,Object obj) throws Exception;	
	public TaskLinks getTaskLinksSinglePO(String id,Object obj) throws Exception;	 
	public void saveOrUpdate(Object obj);	
	public ProcessType getProcessTypeByFlowId(String flowId,Object obj) throws Exception;		
	public ProcessTasks getProcessTasksByParentLinkId(String parentLinkId,Object obj) throws Exception;	
    public abstract List getAllListBySql(String sql)throws HibernateException;		
    public abstract HashMap getQueryListBySql(String hsql,final Integer curPage,final Integer pageSize)throws HibernateException;    
}
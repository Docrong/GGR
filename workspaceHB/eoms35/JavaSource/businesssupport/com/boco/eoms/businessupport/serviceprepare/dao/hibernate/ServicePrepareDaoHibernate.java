package com.boco.eoms.businessupport.serviceprepare.dao.hibernate;


import java.util.HashMap;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.serviceprepare.dao.ServicePrepareDao;
import com.boco.eoms.businessupport.serviceprepare.model.ProcessTasks;
import com.boco.eoms.businessupport.serviceprepare.model.ProcessType;
import com.boco.eoms.businessupport.serviceprepare.model.ProductSpecification;
import com.boco.eoms.businessupport.serviceprepare.model.ProductsServiceDirectory;
import com.boco.eoms.businessupport.serviceprepare.model.ProfessionalServiceDirectory;
import com.boco.eoms.businessupport.serviceprepare.model.ServiceConfiguration;
import com.boco.eoms.businessupport.serviceprepare.model.TaskLinks;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.QuerySqlInit;



public class ServicePrepareDaoHibernate extends BaseDaoHibernate implements ServicePrepareDao{

	public List getTableListBySql(final String sql) throws HibernateException {
		 return getHibernateTemplate().find(sql);
	}

	public ProcessTasks getProcessTasksSinglePO(String id, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return (ProcessTasks) getHibernateTemplate().get(obj.getClass(),id);
	}

	public ProcessType getProcessTypeSinglePO(String id, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return (ProcessType) getHibernateTemplate().get(obj.getClass(),id);
	}

	public ProductSpecification getProductSpecificationSinglePO(String id, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return (ProductSpecification) getHibernateTemplate().get(obj.getClass(),id);
	}

	public ProductsServiceDirectory getProductsServiceDirectorySinglePO(String id, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return (ProductsServiceDirectory) getHibernateTemplate().get(obj.getClass(),id);
	}

	public ProfessionalServiceDirectory getProfessionalServiceDirectorySinglePO(String id, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return (ProfessionalServiceDirectory) getHibernateTemplate().get(obj.getClass(),id);
	}

	public ServiceConfiguration getServiceConfigurationSinglePO(String id, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return (ServiceConfiguration) getHibernateTemplate().get(obj.getClass(),id);
	}

	public TaskLinks getTaskLinksSinglePO(String id, Object obj) throws Exception {
		// TODO Auto-generated method stub
		return (TaskLinks) getHibernateTemplate().get(obj.getClass(),id);
	}
    public void saveOrUpdate(Object obj) {
    	this.getHibernateTemplate().saveOrUpdate(obj);
    }

	public ProcessType getProcessTypeByFlowId(String flowId, Object obj) throws Exception {
		// TODO Auto-generated method stub
		ProcessType processType = new ProcessType();
		String sql = "from " + obj.getClass().getName() + " processType where processType.flowId ='"
				+ flowId + "' and processType.deleted<>'1'";
		List list = getHibernateTemplate().find(sql);
		if (list.size() > 0) {
			processType = (ProcessType) list.get(0);
		}
		return processType;
	}

	public ProcessTasks getProcessTasksByParentLinkId(String parentLinkId, Object obj) throws Exception {
		// TODO Auto-generated method stub
		ProcessTasks processTasks = new ProcessTasks();
		String sql =" from "+ obj.getClass().getName() +" processTasks where processTasks.parentLinkId='"+parentLinkId+"' and processTasks.deleted<>'1'";
		List list = getHibernateTemplate().find(sql);
		if (list.size() > 0) {
			processTasks = (ProcessTasks) list.get(0);
		}
		return processTasks;		
	}

	public List getAllListBySql(final String sql) throws HibernateException {
		// TODO Auto-generated method stub
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(sql);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	public HashMap getQueryListBySql(final String hsql, final Integer curPage, final Integer pageSize) throws HibernateException {
		// TODO Auto-generated method stub
	   	   
		   HibernateCallback callback = new HibernateCallback() {
	        public Object doInHibernate(Session session) throws HibernateException {        
	            //获取任务总数的查询sql
	        	HashMap map = new HashMap();
	        try{
	        	int sql_distinct = hsql.indexOf("distinct");
	        
	    		int sql_index = hsql.indexOf("from");
	    		int sql_orderby = hsql.indexOf("order by");

	    		String queryCountStr;
	    		if (sql_distinct > 0){
	    			int sql_comma = (hsql.substring(sql_distinct, sql_index)).indexOf(",");
	    			if(sql_comma>0){
	     			queryCountStr = "select count("
	    					+ hsql.substring(sql_distinct, sql_distinct+sql_comma) + ") ";
	    			}else{
	    				queryCountStr = "select count("
	    					+ hsql.substring(sql_distinct, sql_index) + ") ";
	    			}
	    		}
	    		else{
	    			queryCountStr = "select count(*) ";
	    		}

	    		if (sql_orderby > 0)
	    			queryCountStr += hsql.substring(sql_index, sql_orderby);
	    		else
	    			queryCountStr += hsql.substring(sql_index);
	    		  		
		        Integer totalCount;
		        
		        Query totalQuery = session.createQuery(queryCountStr);
		        List result = totalQuery.list();
				if (result!=null&&!result.isEmpty()) {
					totalCount = (Integer) result.get(0);
				} else{
					totalCount = new Integer(0);
		        
				}
			    Query query = session.createQuery(hsql);
			    if(pageSize.intValue()!=-1){
			    query.setFirstResult(pageSize.intValue()
						              * (curPage.intValue()));
			    query.setMaxResults(pageSize.intValue());
			    }
			    List resultList = query.list();
			    
			    map.put("queryTotal", totalCount);
			    map.put("queryList", resultList);
	        }
	        catch(Exception e){
	        	System.out.println("-------query list error!---------");
	        	e.printStackTrace();
	        }
			    return map;
	        }
	     };
	     return (HashMap) getHibernateTemplate().execute(callback);
		
	}	
}
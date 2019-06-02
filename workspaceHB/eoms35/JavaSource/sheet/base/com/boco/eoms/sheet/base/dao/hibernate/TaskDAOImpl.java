/*
 * Created on 2008-4-1
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.dao.hibernate;

import java.util.HashMap;
import java.util.List;

import org.hibernate.cfg.Configuration;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.base.dao.ITaskDAO;
import com.boco.eoms.sheet.base.task.ITask;


/**
 * @author IBM_USER
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TaskDAOImpl extends BaseSheetDaoHibernate implements ITaskDAO {


	/**
     * 根据查询条件查询任务信息, 并进行分页处理
     * @param hsql 查询语句
     * @param curPage 分页起始
     * @param pageSize 单页显示数量
     * @return HashMap, 包括任务总量和任务列表
     * @throws HibernateException
     */
	public HashMap getTaskListByCondition(final String queryStr, 
	          final Integer curPage,final Integer pageSize)
	          throws HibernateException {   	   
	   HibernateCallback callback = new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException {        
            //获取任务总数的查询sql
        	HashMap map = new HashMap();
        try{
        	int sql_distinct = queryStr.indexOf("distinct");
        	
    		int sql_index = queryStr.indexOf("from");
    		int sql_orderby = queryStr.indexOf("order by");

    		String queryCountStr;
    		if (sql_distinct > 0){
    			int sql_comma = (queryStr.substring(sql_distinct, sql_index)).indexOf(",");
    			if(sql_comma>0){
     			queryCountStr = "select count("
    					+ queryStr.substring(sql_distinct, sql_distinct+sql_comma) + ") ";
    			}else{
    				queryCountStr = "select count("
    					+ queryStr.substring(sql_distinct, sql_index) + ") ";
    			}
    		}
    		else{
    			queryCountStr = "select count(*) ";
    		}
    		if (sql_orderby > 0)  
    			queryCountStr += queryStr.substring(sql_index, sql_orderby);
    		else
    			queryCountStr += queryStr.substring(sql_index);
    		  		
	        Integer totalCount; 
	        
	        Query totalQuery = session.createQuery(queryCountStr);
	        List result = totalQuery.list();
			if (result!=null&&!result.isEmpty()) {
				totalCount = (Integer) result.get(0);
			} else
				totalCount = new Integer(0);
	        
	        System.out.println("===============wanghao1====queryStr="+queryStr);
		    Query query = session.createQuery(queryStr);
		    if(pageSize.intValue()!=-1)   { 
		    query.setFirstResult(pageSize.intValue()
					              * (curPage.intValue()));
		    query.setMaxResults(pageSize.intValue());
		    }
		    List resultList = query.list();
		    
		    map.put("taskTotal", totalCount);
		    map.put("taskList", resultList);
        }
        catch(Exception e){
        	System.out.println("-------task list error!---------");
        	e.printStackTrace();
        	throw new HibernateException("task list error");
        }
		    return map;
        }
     };
     return (HashMap) getHibernateTemplate().execute(callback);
	}
	
	 /**
	 * 根据taskId获取task model对象
	 * @param id taskId
	 * @return
	 * @throws Exception
	 */
    public ITask loadSinglePO(final String id,final Object obj) throws HibernateException{
//    	ITask main = (ITask)getHibernateTemplate().get(obj.getClass(),id);
//    	HibernateCallback callback = new HibernateCallback() {
//         public Object doInHibernate(Session session) throws HibernateException { 
//        	ITask task=null;
//            try{               
//            	String hsql="from "+obj+" as task where task.id='"+id+"'";	          	        
//    		    Query query = session.createQuery(hsql);
//
//    		    List resultList = query.list();
//    		    if(resultList.size()>0) task=(ITask)resultList.get(0);   		    
//            }
//            catch(HibernateException e){
//            	System.out.println("-------task loadSinglePO error!---------");
//            	throw e;
//            }
//    		    return task;
//            }
//         };
//         return (ITask) getHibernateTemplate().execute(callback);
    	ITask task = (ITask)getHibernateTemplate().get(obj.getClass(),id);
        return task;
    }
    public ITask loadTaskPO(final String hql) throws HibernateException{
    	
    	HibernateCallback callback = new HibernateCallback() {
         public Object doInHibernate(Session session) throws HibernateException { 
        	ITask task=null;
            try{               	          	        
    		    Query query = session.createQuery(hql);

    		    List resultList = query.list();
    		    if(resultList.size()>0) task=(ITask)resultList.get(0);   		    
            }
            catch(HibernateException e){
            	System.out.println("-------task loadTaskPO error!---------");
            	throw e;
            }
    		    return task;
            }
         };
         return (ITask) getHibernateTemplate().execute(callback);
    }
    
 public List loadTaskList(final String hql) throws HibernateException{ 	
	 
	 return getHibernateTemplate().find(hql);
   
    }

 /*
  * (non-Javadoc)
  * @see com.boco.eoms.sheet.base.dao.ITaskDAO#getTasksBySheetKey(java.lang.String)
  */
public List getTasksBySheetKey(String hql) throws HibernateException {
	 return getHibernateTemplate().find(hql);
} 
/*
 * (non-Javadoc)
 * @see com.boco.eoms.sheet.base.dao.ITaskDAO#getAllSubTask(java.lang.String)
 */
public List getAllSubTask(String hql) throws HibernateException {
	 return getHibernateTemplate().find(hql);
}

/*
 * (non-Javadoc)
 * @see com.boco.eoms.sheet.base.dao.ITaskDAO#getTasksByHql(java.lang.String)
 */
public List getTasksByHql(String hql) throws HibernateException {
	 return getHibernateTemplate().find(hql);
}  
   
public HashMap getTaskListByConditions(final String countSql, final String queryStr, final Integer curPage, final Integer pageSize)
throws HibernateException
{
HibernateCallback callback = new HibernateCallback() {

    public Object doInHibernate(Session session)
        throws HibernateException
    {
        HashMap map = new HashMap();
        try
        {
            int sql_distinct = countSql.indexOf("distinct");
            int sql_index = countSql.indexOf("from");
            int sql_orderby = countSql.indexOf("order by");
            String queryCountStr;
            if(sql_distinct > 0)
            {
                int sql_comma = countSql.substring(sql_distinct, sql_index).indexOf(",");
                if(sql_comma > 0)
                    queryCountStr = "select count(" + countSql.substring(sql_distinct, sql_distinct + sql_comma) + ") ";
                else
                    queryCountStr = "select count(" + countSql.substring(sql_distinct, sql_index) + ") ";
            } else
            {
                queryCountStr = "select count(*) ";
            }
            if(sql_orderby > 0)
                queryCountStr = queryCountStr + countSql.substring(sql_index, sql_orderby);
            else
                queryCountStr = queryCountStr + countSql.substring(sql_index);
            Query totalQuery = session.createQuery(queryCountStr);
            List result = totalQuery.list();
            Integer totalCount;
            if(result != null && !result.isEmpty())
                totalCount = (Integer)result.get(0);
            else
                totalCount = new Integer(0);
            Query query = session.createQuery(queryStr);
            if(pageSize.intValue() != -1)
            {
                query.setFirstResult(pageSize.intValue() * curPage.intValue());
                query.setMaxResults(pageSize.intValue());
            }
            List resultList = query.list();
            map.put("taskTotal", totalCount);
            map.put("taskList", resultList);
        }
        catch(Exception e)
        {
            System.out.println("-------task list error!---------");
            e.printStackTrace();
            throw new HibernateException("task list error");
        }
        return map;
    }

};
return (HashMap)getHibernateTemplate().execute(callback);
}  
    
}

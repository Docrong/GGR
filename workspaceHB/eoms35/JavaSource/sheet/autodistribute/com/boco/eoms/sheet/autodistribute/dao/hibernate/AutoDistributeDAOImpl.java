package com.boco.eoms.sheet.autodistribute.dao.hibernate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.autodistribute.dao.IAutoDistributeDAO;
import com.boco.eoms.sheet.autodistribute.model.AutoDistribute;

public class AutoDistributeDAOImpl extends BaseDaoHibernate implements IAutoDistributeDAO {

	/**
	 * 保存
	 * @param Proxy the object to be saved
	 */
	public void saveAutoDistribute(AutoDistribute autoDistribute)  throws HibernateException{
		getHibernateTemplate().saveOrUpdate(autoDistribute);
	}

	/**
	 * 删除
	 * @param Proxy the object to be removed
	 */
	public void removeAutoDistribute(String Id)   throws HibernateException{
		getHibernateTemplate().delete(getAutoDistribute(Id));
	}

	/**
	 * 获取
	 * 
	 */
	public AutoDistribute getAutoDistribute(String Id)   throws HibernateException{
		AutoDistribute autoDistribute = (AutoDistribute) getHibernateTemplate().get(AutoDistribute.class, Id);
		if (autoDistribute == null) {
			throw new ObjectRetrievalFailureException(AutoDistribute.class, Id);
		}
		return autoDistribute;
	}
	/**
	 * 得到所有的记录
	 */
	public HashMap getAllAutoDistribute(Integer pageIndex, Integer pageSize) throws HibernateException{
		String hql = " from AutoDistribute au ";
		return getListByCondition(hql,pageIndex,pageSize);
	}
	/**
     * 根据查询条件查询, 并进行分页处理
     * @param hsql 查询语句
     * @param curPage 分页起始
     * @param pageSize 单页显示数量
     * @return HashMap, 包括任务总量和任务列表
     * @throws HibernateException
     */
	public HashMap getListByCondition(final String queryStr, 
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
    		if (sql_distinct > 0)
    			queryCountStr = "select count("
    					+ queryStr.substring(sql_distinct, sql_index) + ") ";
    		else
    			queryCountStr = "select count(*) ";

    		if (sql_orderby > 0)
    			queryCountStr += queryStr.substring(sql_index, sql_orderby);
    		else
    			queryCountStr += queryStr.substring(sql_index);
    		  		
	        Integer totalCount;
	        
	        Query totalQuery = session.createQuery(queryCountStr);
			if (!totalQuery.list().isEmpty()) {
				totalCount = (Integer) totalQuery.list().get(0);
			} else
				totalCount = new Integer(0);
	        
	        
		    Query query = session.createQuery(queryStr);
		    query.setFirstResult(pageSize.intValue()
					              * (curPage.intValue()));
		    query.setMaxResults(pageSize.intValue());
		    List resultList = query.list();
		    
		    map.put("autoTotal", totalCount);
		    map.put("autoList", resultList);
        }
        catch(Exception e){
        	System.out.println("-------list error!---------");
        	e.printStackTrace();
        	throw new HibernateException("list error");
        }
		    return map;
        }
     };
     return (HashMap) getHibernateTemplate().execute(callback);
	}
}

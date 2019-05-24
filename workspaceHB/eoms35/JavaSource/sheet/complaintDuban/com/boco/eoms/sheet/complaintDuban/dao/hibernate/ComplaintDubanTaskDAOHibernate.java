
package com.boco.eoms.sheet.complaintDuban.dao.hibernate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.sheet.base.dao.hibernate.TaskDAOImpl;
import com.boco.eoms.sheet.complaintDuban.dao.IComplaintDubanTaskDAO;
import com.boco.eoms.sheet.complaintDuban.model.ComplaintDubanTask;

public class ComplaintDubanTaskDAOHibernate extends TaskDAOImpl implements IComplaintDubanTaskDAO {
	/**
	 * 根据count SQL语句查询
	 * 
	 * @param sql
	 *            查询语句
	 * @param curPage
	 *            分页起始
	 * @param pageSize
	 *            单页显示数量
	 * @return HashMap, 包括任务总量和任务列表
	 * @throws HibernateException
	 */
	public Integer getCountTaskBySQL(final String queryCountStr)
			throws HibernateException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				// 获取任务总数的查询sql
				Integer totalCount;
				try {
					Query totalQuery = session.createSQLQuery(queryCountStr);
					List totalList=totalQuery.list();
					if (totalList!=null&&!totalList.isEmpty()) {
						System.out.println("is not empty!");
						totalCount = new Integer(((BigDecimal) totalList.get(0)).intValue());
						System.out.println(totalCount);
					} else {
						totalCount = new Integer(0);
						System.out.println("is not empty!");
					}
				} catch (Exception e) {
					System.out.println("-------task list error!---------");
					e.printStackTrace();
					throw new HibernateException("task list error");
				}
				return totalCount;
			}
		};
		return (Integer) getHibernateTemplate().execute(callback);
	}
	/**
     * 根据SQL语句查询任务信息, 并进行分页处理
     * @param hsql 查询语句
     * @param curPage 分页起始
     * @param pageSize 单页显示数量
     * @return HashMap, 包括任务总量和任务列表
     * @throws HibernateException
     */
	public HashMap getTaskListBySQL(final String queryStr, 
	          final Integer curPage,final Integer pageSize)
	          throws HibernateException {   	   
	   HibernateCallback callback = new HibernateCallback() {
        public Object doInHibernate(Session session) throws HibernateException {        
            //获取任务总数的查询sql
        	HashMap map = new HashMap();
        try{
		    Query query = session.createSQLQuery(queryStr).addScalar("title").addEntity(ComplaintDubanTask.class);
		    if(pageSize.intValue()!=-1)   { 
			    query.setFirstResult(pageSize.intValue()* (curPage.intValue())); 
			    query.setMaxResults(pageSize.intValue());
		    }
		    List resultList = query.list();
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
}

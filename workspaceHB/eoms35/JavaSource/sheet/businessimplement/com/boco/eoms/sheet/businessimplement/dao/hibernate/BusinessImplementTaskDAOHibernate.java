
package com.boco.eoms.sheet.businessimplement.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.sheet.base.dao.hibernate.TaskDAOImpl;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.businessimplement.dao.IBusinessImplementTaskDAO;

public class BusinessImplementTaskDAOHibernate extends TaskDAOImpl implements IBusinessImplementTaskDAO {

	public Integer getCountOfBrother(final Object taskObject, final String sheetKey, final String parentLevelId) throws HibernateException {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				//取列表数量
				String queryCountStr = "select count(distinct task.levelId) from " + taskObject.getClass().getName()
						+ " task where task.sheetKey = '" + sheetKey + "' and task.parentLevelId = '" + parentLevelId + "' ";
				Query query = session.createQuery(queryCountStr);
				
				Integer total = (Integer) query.iterate().next();
				return total;
			}
		};
		return (Integer) getHibernateTemplate().execute(callback);
	}
	/**
     * 根据查询条件查询任务信息, 并进行分页处理
     * @param hsql 查询语句
     * @param curPage 分页起始
     * @param pageSize 单页显示数量
     * @return HashMap, 包括任务总量和任务列表
     * @throws HibernateException
     */
//	 public HashMap getTaskListByCondition(final String queryStr, final Integer curPage, final Integer pageSize)
//     throws HibernateException
// {
//     HibernateCallback callback = new HibernateCallback() {
//
//         public Object doInHibernate(Session session)
//             throws HibernateException
//         {
//             HashMap map = new HashMap();
//             try
//             {
//                 int sql_distinct = queryStr.indexOf("distinct");
//                 int sql_index = queryStr.indexOf("from");
//                 int sql_orderby = queryStr.indexOf("order by");
//                 String queryCountStr;
//                 if(sql_distinct > 0)
//                 {
//                     int sql_comma = queryStr.substring(sql_distinct, sql_index).indexOf(",");
//                     if(sql_comma > 0)
//                         queryCountStr = "select count(" + queryStr.substring(sql_distinct, sql_distinct + sql_comma) + ") ";
//                     else
//                         queryCountStr = "select count(" + queryStr.substring(sql_distinct, sql_index) + ") ";
//                 } else
//                 {
//                     queryCountStr = "select count(*) ";
//                 }
//                 if(sql_orderby > 0)
//                     queryCountStr = queryCountStr + queryStr.substring(sql_index, sql_orderby);
//                 else
//                     queryCountStr = queryCountStr + queryStr.substring(sql_index);
//                 Query totalQuery = session.createQuery(queryCountStr);
//                 List result = totalQuery.list();
//                 Integer totalCount;
//                 if(result != null && !result.isEmpty())
//                     totalCount = (Integer)result.get(0);
//                 else
//                     totalCount = new Integer(0);
//                 Query query = session.createQuery(queryStr);
//                 query.setFirstResult(pageSize.intValue() * curPage.intValue());
//                 query.setMaxResults(pageSize.intValue());
//                 List resultList = query.list();
//                 map.put("taskTotal", totalCount);
//                 map.put("taskList", resultList);
//             }
//             catch(Exception e)
//             {
//                 System.out.println("-------task list error!---------");
//                 e.printStackTrace();
//                 throw new HibernateException("task list error");
//             }
//             return map;
//         }
//
//     };
//     return (HashMap)getHibernateTemplate().execute(callback);
// }

	public void insertsql(HashMap map) throws HibernateException {
		// TODO Auto-generated method stub
		
	}
}

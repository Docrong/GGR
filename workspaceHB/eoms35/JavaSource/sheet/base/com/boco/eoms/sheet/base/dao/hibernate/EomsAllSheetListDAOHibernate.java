package com.boco.eoms.sheet.base.dao.hibernate;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.sheet.base.dao.IEomsAllSheetListDAO;
import com.boco.eoms.sheet.base.model.EomsUndoSheetView;

public class EomsAllSheetListDAOHibernate extends BaseSheetDaoHibernate
		implements IEomsAllSheetListDAO {
	public HashMap getAllTasksByHql(final String hql, final Integer curPage,
			final Integer pageSize) throws HibernateException {

		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// 获取任务总数的查询sql
				HashMap map = new HashMap();
				try {
					int sql_distinct = hql.indexOf("distinct");

					int sql_index = hql.indexOf("from");
					int sql_orderby = hql.indexOf("order by");

					String queryCountStr;
					if (sql_distinct > 0) {
						int sql_comma = (hql.substring(sql_distinct, sql_index))
								.indexOf(",");
						if (sql_comma > 0) {
							queryCountStr = "select count("
									+ hql.substring(sql_distinct, sql_distinct
											+ sql_comma) + ") ";
						} else {
							queryCountStr = "select count("
									+ hql.substring(sql_distinct, sql_index)
									+ ") ";
						}
					} else {
						queryCountStr = "select count(*) as c ";
					}
					if (sql_orderby > 0)
						queryCountStr += hql.substring(sql_index, sql_orderby);
					else
						queryCountStr += hql.substring(sql_index);

					Integer totalCount;

					SQLQuery totalQuery = session.createSQLQuery(queryCountStr);
					totalQuery.addScalar("c", Hibernate.INTEGER);
					List result = totalQuery.list();
					if (result != null && !result.isEmpty()) {
						totalCount = (Integer) result.get(0);
					} else
						totalCount = new Integer(0);
					String queryGruopCount = " select t.sheetType as sheetType,t.sheetTypeName as sheetTypeName,count(distinct t.sheetId) as c ";
					if (sql_orderby > 0)
						queryGruopCount += hql.substring(sql_index, sql_orderby);
					else
						queryGruopCount += hql.substring(sql_index);
					queryGruopCount = queryGruopCount + " group by t.sheetType,t.sheetTypeName ";
					
					SQLQuery totalGroupCountQuery = session.createSQLQuery(queryGruopCount);
					totalGroupCountQuery.addScalar("sheetType", Hibernate.STRING);
					totalGroupCountQuery.addScalar("sheetTypeName", Hibernate.STRING);
					totalGroupCountQuery.addScalar("c", Hibernate.INTEGER);
					List countList = totalGroupCountQuery.list();
					
					
					SQLQuery query = session.createSQLQuery(hql);
					query.addEntity(EomsUndoSheetView.class);

					if (pageSize.intValue() != -1) {
						query.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
						query.setMaxResults(pageSize.intValue());
					}
					List resultList = query.list();

					map.put("taskTotal", totalCount);
					map.put("taskList", resultList);
					map.put("taskCountList", countList);
				} catch (Exception e) {
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
	 * 根据查询条件查询任务信息, 并进行分页处理
	 * 
	 * @param hsql
	 *            查询语句
	 * @param curPage
	 *            分页起始
	 * @param pageSize
	 *            单页显示数量
	 * @return HashMap, 包括任务总量和任务列表
	 * @throws HibernateException
	 */
	public HashMap getTaskListByCondition(final String queryStr,
			final Integer curPage, final Integer pageSize)
			throws HibernateException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// 获取任务总数的查询sql
				HashMap map = new HashMap();
				try {
					int sql_distinct = queryStr.indexOf("distinct");

					int sql_index = queryStr.indexOf("from");
					int sql_orderby = queryStr.indexOf("order by");

					String queryCountStr;
					if (sql_distinct > 0) {
						int sql_comma = (queryStr.substring(sql_distinct,
								sql_index)).indexOf(",");
						if (sql_comma > 0) {
							queryCountStr = "select count("
									+ queryStr.substring(sql_distinct,
											sql_distinct + sql_comma) + ") ";
						} else {
							queryCountStr = "select count("
									+ queryStr.substring(sql_distinct,
											sql_index) + ") ";
						}
					} else {
						queryCountStr = "select count(*) ";
					}
					if (sql_orderby > 0)
						queryCountStr += queryStr.substring(sql_index,
								sql_orderby);
					else
						queryCountStr += queryStr.substring(sql_index);

					Integer totalCount;

					Query totalQuery = session.createQuery(queryCountStr);
					List result = totalQuery.list();
					if (result != null && !result.isEmpty()) {
						totalCount = (Integer) result.get(0);
					} else
						totalCount = new Integer(0);

					Query query = session.createQuery(queryStr);
					if (pageSize.intValue() != -1) {
						query.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
						query.setMaxResults(pageSize.intValue());
					}
					List resultList = query.list();

					map.put("taskTotal", totalCount);
					map.put("taskList", resultList);
				} catch (Exception e) {
					System.out.println("-------task list error!---------");
					e.printStackTrace();
					throw new HibernateException("task list error");
				}
				return map;
			}
		};
		return (HashMap) getHibernateTemplate().execute(callback);
	}

	public HashMap getAllTasksCountByHql(final String queryStr) throws HibernateException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// 获取任务总数的查询sql
				HashMap map = new HashMap();
				try {
					Integer totalCount;

					SQLQuery totalQuery = session.createSQLQuery(queryStr);
					totalQuery.addScalar("sheetType", Hibernate.STRING);
					totalQuery.addScalar("sheetTypeName", Hibernate.STRING);
					totalQuery.addScalar("c", Hibernate.INTEGER);
					List result = totalQuery.list();
					

					map.put("taskCountList", result);
				} catch (Exception e) {
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

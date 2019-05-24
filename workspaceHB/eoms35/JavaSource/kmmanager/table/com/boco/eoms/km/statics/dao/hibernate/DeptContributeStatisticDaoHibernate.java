package com.boco.eoms.km.statics.dao.hibernate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.km.statics.dao.DeptContributeStatisticDao;
import com.boco.eoms.km.statics.model.DeptContributeStatistic;

/**
 * <p>
 * Title:部门知识贡献情况 dao的hibernate实现
 * </p>
 * <p>
 * Description:部门知识贡献情况
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 * 
 * @author ljt
 * @version 0.1
 * 
 */
public class DeptContributeStatisticDaoHibernate extends BaseDaoHibernate implements DeptContributeStatisticDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.DeptContributeStatisticDao#getDeptContributeStatistics()
	 *      
	 */
	public List getDeptContributeStatistics() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from DeptContributeStatistic deptContributeStatistic";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.DeptContributeStatisticDao#getDeptContributeStatistic(java.lang.String)
	 *
	 */
	public DeptContributeStatistic getDeptContributeStatistic(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from DeptContributeStatistic deptContributeStatistic where deptContributeStatistic.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (DeptContributeStatistic) result.iterator().next();
				} else {
					return new DeptContributeStatistic();
				}
			}
		};
		return (DeptContributeStatistic) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.DeptContributeStatisticDao#saveDeptContributeStatistics(com.boco.eoms.km.DeptContributeStatistic)
	 *      
	 */
	public void saveDeptContributeStatistic(final DeptContributeStatistic deptContributeStatistic) {
		if ((deptContributeStatistic.getId() == null) || (deptContributeStatistic.getId().equals("")))
			getHibernateTemplate().save(deptContributeStatistic);
		else
			getHibernateTemplate().saveOrUpdate(deptContributeStatistic);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.DeptContributeStatisticDao#removeDeptContributeStatistics(java.lang.String)
	 *      
	 */
    public void removeDeptContributeStatistic(final String id) {
		getHibernateTemplate().delete(getDeptContributeStatistic(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		DeptContributeStatistic deptContributeStatistic = this.getDeptContributeStatistic(id);
		if(deptContributeStatistic==null){
			return "";
		}
		//TODO 请修改代码
		return null;//deptContributeStatistic.yourCode();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.DeptContributeStatisticDao#getDeptContributeStatistics(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getDeptContributeStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
		    	StringBuffer countStr = new StringBuffer("select count(distinct t.operate_dept) as count ");
		    	countStr.append("from km_operate_date_log t ");
		    	countStr.append("where t.operate_date BETWEEN ? AND ?");
		    	System.out.println("sql = " + countStr.toString());
		    	
		    	SQLQuery countQuery = session.createSQLQuery(countStr.toString());
		    	countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
		    	countQuery.setDate(0, startDate);
		    	countQuery.setDate(1, endDate);
		    	Integer total = (Integer)countQuery.uniqueResult(); 
	    	
		    	StringBuffer queryStr = new StringBuffer("select t.operate_dept as operateDept, ");
		    	queryStr.append("sum(t.add_count) as addCount, ");
		    	queryStr.append("sum(t.audit_ok_count) as auditOkCount, ");
		    	queryStr.append("sum(t.audit_back_count) as auditBackCount, ");
		    	queryStr.append("sum(t.use_count) as useCount, ");
		    	queryStr.append("sum(t.up_count) as upCount ");
		    	queryStr.append("from km_operate_date_log t ");
		    	queryStr.append("where t.operate_date BETWEEN ? AND ? ");
		    	queryStr.append("group by t.operate_dept");
		    	System.out.println("sql = "+queryStr.toString());
		    	
				SQLQuery query = session.createSQLQuery(queryStr.toString());
				query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				query.addScalar("operateDept", org.hibernate.Hibernate.STRING);
				query.addScalar("addCount", org.hibernate.Hibernate.INTEGER);
				query.addScalar("auditOkCount", org.hibernate.Hibernate.INTEGER);
				query.addScalar("auditBackCount", org.hibernate.Hibernate.INTEGER);
				query.addScalar("useCount", org.hibernate.Hibernate.INTEGER);
				query.addScalar("upCount", org.hibernate.Hibernate.INTEGER);
				query.setDate(0, startDate);
				query.setDate(1, endDate);				
				List result = query.list();					
				
				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	public Map getDeptContributeStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
		    	StringBuffer countStr = new StringBuffer("select count(distinct t.operate_dept) as count ");
		    	countStr.append("from km_operate_date_log t ");
		    	countStr.append("where t.operate_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss')");
		    	System.out.println("sql = " + countStr.toString());
		    	
		    	SQLQuery countQuery = session.createSQLQuery(countStr.toString());
		    	countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
		    	countQuery.setString(0, startDate);
		    	countQuery.setString(1, endDate);
		    	Integer total = (Integer)countQuery.uniqueResult(); 
	    	
		    	StringBuffer queryStr = new StringBuffer("select t.operate_dept as operateDept, ");
		    	queryStr.append("sum(t.add_count) as addCount, ");
		    	queryStr.append("sum(t.audit_ok_count) as auditOkCount, ");
		    	queryStr.append("sum(t.audit_back_count) as auditBackCount, ");
		    	queryStr.append("sum(t.use_count) as useCount, ");
		    	queryStr.append("sum(t.up_count) as upCount ");
		    	queryStr.append("from km_operate_date_log t ");
		    	queryStr.append("where t.operate_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss') ");
		    	queryStr.append("group by t.operate_dept");
		    	System.out.println("sql = "+queryStr.toString());
		    	
				SQLQuery query = session.createSQLQuery(queryStr.toString());
				query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				query.addScalar("operateDept", org.hibernate.Hibernate.STRING);
				query.addScalar("addCount", org.hibernate.Hibernate.INTEGER);
				query.addScalar("auditOkCount", org.hibernate.Hibernate.INTEGER);
				query.addScalar("auditBackCount", org.hibernate.Hibernate.INTEGER);
				query.addScalar("useCount", org.hibernate.Hibernate.INTEGER);
				query.addScalar("upCount", org.hibernate.Hibernate.INTEGER);
				query.setString(0, startDate);
				query.setString(1, endDate);				
				List result = query.list();					
				
				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
}
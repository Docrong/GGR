package com.boco.eoms.km.statics.dao.hibernate;

import java.util.ArrayList;
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
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.km.statics.dao.BaseStatisticDao;
import com.boco.eoms.km.statics.model.BaseStatistic;
import com.boco.eoms.km.table.model.KmTableGeneral;

/**
 * <p> Title:知识库统计 </p>
 * <p> Description:知识库统计 </p>
 * <p> Mon Mar 30 14:39:15 CST 2009 </p>
 * @author zhangxiaobo
 * @version 0.1
 * 
 */

public class BaseStatisticDaoHibernate extends BaseDaoHibernate implements BaseStatisticDao{
	
	/**
	 * 根据表信息主键查询表的名称
	 * @param tableId
	 * @return
	 */
	public String getKmTableName(final String tableId) {
		KmTableGeneral kmTableGeneral = (KmTableGeneral) getHibernateTemplate().get(KmTableGeneral.class, tableId);
		if (kmTableGeneral == null) {
			return "";
		}
		return kmTableGeneral.getTableName();
	}

	public Map getBaseStatisticsByThemeId(final String tableId, final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String tableName = getKmTableName(tableId);

		    	StringBuffer countStr = new StringBuffer("select count(distinct t.THEME_ID) as count from ");
		    	countStr.append(tableName);
		    	countStr.append(" where t.operate_date BETWEEN ? AND ?");
		    	//System.out.println("sql = " + countStr.toString());

		    	SQLQuery countQuery = session.createSQLQuery(countStr.toString());
		    	countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
		    	countQuery.setDate(0, startDate);
		    	countQuery.setDate(1, endDate);		    	
		    	Integer total = (Integer)countQuery.uniqueResult(); 

		    	List result = new ArrayList();
		    	if(total.intValue() >0){
			    	StringBuffer queryStr = new StringBuffer("select t.theme_id as themdId,"); //知识分类			    	
			    	queryStr.append(" count(theme_id) as countThemeId," ); //该分类的知识个数
			    	
			    	queryStr.append(" sum(case when read_count>0 then 1 else 0 end) as countReadCount," ); //被阅读的知识个数
			    	queryStr.append(" sum(read_count) as sumReadCount," ); //知识被阅读的总次数
			    	
			    	queryStr.append(" from ");
			    	countStr.append(tableName);
			    	queryStr.append(" as t where t.operate_date BETWEEN ? AND ? ");
			    	queryStr.append(" group by t.theme_id");
			    	queryStr.append(" order by t.theme_id");

			    	//System.out.println("sql = " + queryStr.toString());
			    	
					SQLQuery query = session.createSQLQuery(queryStr.toString());
					query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
					query.setMaxResults(pageSize.intValue());
					query.addScalar("operateUser", org.hibernate.Hibernate.STRING);
					query.addScalar("operateDept", org.hibernate.Hibernate.STRING);
					query.addScalar("addCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("modifyCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("overCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("deleteCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("useCount", org.hibernate.Hibernate.INTEGER);
					query.addScalar("upCount", org.hibernate.Hibernate.INTEGER);

					query.setDate(0, startDate);
					query.setDate(1, endDate);				
					result = query.list();		    		
		    	}

				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
    /**
	 * 
	 * @see com.boco.eoms.km.BaseStatisticDao#getBaseStatistics()
	 *      
	 */
	public List getBaseStatistics() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from BaseStatistic baseStatistic";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.BaseStatisticDao#getBaseStatistic(java.lang.String)
	 *
	 */
	public BaseStatistic getBaseStatistic(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from BaseStatistic baseStatistic where baseStatistic.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (BaseStatistic) result.iterator().next();
				} else {
					return new BaseStatistic();
				}
			}
		};
		return (BaseStatistic) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.BaseStatisticDao#saveBaseStatistics(com.boco.eoms.km.BaseStatistic)
	 *      
	 */
	public void saveBaseStatistic(final BaseStatistic baseStatistic) {
		if ((baseStatistic.getId() == null) || (baseStatistic.getId().equals("")))
			getHibernateTemplate().save(baseStatistic);
		else
			getHibernateTemplate().saveOrUpdate(baseStatistic);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.BaseStatisticDao#removeBaseStatistics(java.lang.String)
	 *      
	 */
    public void removeBaseStatistic(final String id) {
		getHibernateTemplate().delete(getBaseStatistic(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		BaseStatistic baseStatistic = this.getBaseStatistic(id);
		if(baseStatistic==null){
			return "";
		}
		//TODO 请修改代码
		return null;//baseStatistic.yourCode();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.BaseStatisticDao#getBaseStatistics(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getBaseStatistics(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from BaseStatistic baseStatistic";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();
				Query query = session.createQuery(queryStr);
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
	
}
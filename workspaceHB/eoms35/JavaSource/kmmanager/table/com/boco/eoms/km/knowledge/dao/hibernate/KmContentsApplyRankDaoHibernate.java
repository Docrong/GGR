package com.boco.eoms.km.knowledge.dao.hibernate;

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
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.km.knowledge.dao.KmContentsApplyRankDao;
import com.boco.eoms.km.statics.util.StatisticMethod;

/**
 * <p>
 * Title:知识申请排行 dao的hibernate实现
 * </p>
 * <p>
 * Description:知识申请排行
 * </p>
 * <p>
 * Wed Aug 19 15:53:50 CST 2009
 * </p>
 * 
 * @author wangzhiyong
 * @version 1.0
 * 
 */
public class KmContentsApplyRankDaoHibernate extends BaseDaoHibernate implements KmContentsApplyRankDao,
		ID2NameDAO {
	
    
	
	/**
	 *
	 * @see com.boco.eoms.km.knowledge.KmContentsApplyRankDao#getKmContentsApplyRank(java.lang.String)
	 *
	 */
	
	
	
	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		
		return "";
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.knowledge.KmContentsApplyRankDao#getKmContentsApplyRanks(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmContentsApplyRanks(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmContentsApplyRank kmContentsApplyRank";
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

	public Map getKmContentsApplyRankDetail(final String id, final String startDate, final String endDate) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
		
		HashMap map = new HashMap();
		SQLQuery query = null;
		StringBuffer queryStr = new StringBuffer(); //申请主题
    	

		
		if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
			Date newStartDate = StatisticMethod.stringToDate( startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
			Date newEndDate = StatisticMethod.stringToDate(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
//			queryStr.append("select t2.id as id,t2.apply_title as applyTitle,t3.applyCount as applyCount from km_contents_apply t2,(select count(*) as applyCount,t1.apply_id as applyId from km_contents_apply_vote t1 where t1.vote_date BETWEEN ? AND ? group by t1.apply_id order by applyCount desc) t3	where t2.id = t3.applyId");
//			
//			query = session.createSQLQuery(queryStr.toString());
//			query.addScalar("id", org.hibernate.Hibernate.STRING);
//			query.addScalar("applyTitle", org.hibernate.Hibernate.STRING);
//			query.addScalar("applyCount", org.hibernate.Hibernate.STRING);
//			
//	    	query.setDate(0, newStartDate);
//	    	query.setDate(1, newEndDate);
			queryStr.append("select count(*) as applyCount,TO_CHAR(t.apply_date, '%Y-%m-%d') as applyDate from km_contents_apply t where t.apply_theme = ? and t.apply_date BETWEEN ? and ? group by 2 order by 2 asc");
	    	query = session.createSQLQuery(queryStr.toString());
			query.addScalar("applyCount", org.hibernate.Hibernate.INTEGER);
			query.addScalar("applyDate", org.hibernate.Hibernate.STRING);
	    	query.setString(0, id);
	    	query.setDate(1, newStartDate);
			query.setDate(2, newEndDate);
		}
		else if("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())){
			String newStartDate = startDate + " 00:00:00";
			String newEndDate = endDate + " 23:59:59";
			queryStr.append("select count(*) as applyCount,to_char(t.apply_date,'yyyy-mm-dd') as applyDate from km_contents_apply t where t.apply_theme = ? and t.apply_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss') group by to_char(t.apply_date,'yyyy-mm-dd') order by to_char(t.apply_date,'yyyy-mm-dd') asc");
	    	query = session.createSQLQuery(queryStr.toString());
			query.addScalar("applyCount", org.hibernate.Hibernate.INTEGER);
			query.addScalar("applyDate", org.hibernate.Hibernate.STRING);
	    	query.setString(0, id);
	    	query.setString(1, newStartDate);
			query.setString(2, newEndDate);
		} 
		List result = query.list();
		map.put("result", result);
		 //返回结果
		return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
	
}
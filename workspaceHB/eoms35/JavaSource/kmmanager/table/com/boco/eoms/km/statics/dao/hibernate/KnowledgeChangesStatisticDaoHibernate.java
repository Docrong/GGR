package com.boco.eoms.km.statics.dao.hibernate;

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
import com.boco.eoms.km.statics.dao.KnowledgeChangesStatisticDao;
import com.boco.eoms.km.statics.model.KnowledgeChangesStatistic;

/**
 * <p>
 * Title:知识库知识变更情况统计表 dao的hibernate实现
 * </p>
 * <p>
 * Description:知识库知识变更情况统计表
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 * 
 * @author ljt
 * @version 0.1
 * 
 */
public class KnowledgeChangesStatisticDaoHibernate extends BaseDaoHibernate implements KnowledgeChangesStatisticDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.KnowledgeChangesStatisticDao#getKnowledgeChangesStatistics()
	 *      
	 */
	public List getKnowledgeChangesStatistics() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KnowledgeChangesStatistic knowledgeChangesStatistic";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.KnowledgeChangesStatisticDao#getKnowledgeChangesStatistic(java.lang.String)
	 *
	 */
	public KnowledgeChangesStatistic getKnowledgeChangesStatistic(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KnowledgeChangesStatistic knowledgeChangesStatistic where knowledgeChangesStatistic.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (KnowledgeChangesStatistic) result.iterator().next();
				} else {
					return new KnowledgeChangesStatistic();
				}
			}
		};
		return (KnowledgeChangesStatistic) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.KnowledgeChangesStatisticDao#saveKnowledgeChangesStatistics(com.boco.eoms.km.KnowledgeChangesStatistic)
	 *      
	 */
	public void saveKnowledgeChangesStatistic(final KnowledgeChangesStatistic knowledgeChangesStatistic) {
		if ((knowledgeChangesStatistic.getId() == null) || (knowledgeChangesStatistic.getId().equals("")))
			getHibernateTemplate().save(knowledgeChangesStatistic);
		else
			getHibernateTemplate().saveOrUpdate(knowledgeChangesStatistic);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.KnowledgeChangesStatisticDao#removeKnowledgeChangesStatistics(java.lang.String)
	 *      
	 */
    public void removeKnowledgeChangesStatistic(final String id) {
		getHibernateTemplate().delete(getKnowledgeChangesStatistic(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		KnowledgeChangesStatistic knowledgeChangesStatistic = this.getKnowledgeChangesStatistic(id);
		if(knowledgeChangesStatistic==null){
			return "";
		}
		//TODO 请修改代码
		return null;//knowledgeChangesStatistic.yourCode();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.KnowledgeChangesStatisticDao#getKnowledgeChangesStatistics(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKnowledgeChangesStatistics(final Integer curPage, final Integer pageSize) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {		    	
				String countStr = "select count(distinct t.table_id) as count from km_operate_log t";
		    	SQLQuery countQuery = session.createSQLQuery(countStr);
		    	countQuery.addScalar("count", org.hibernate.Hibernate.INTEGER);
		    	Integer total = (Integer)countQuery.uniqueResult(); 

		    	StringBuffer queryStr = new StringBuffer("select distinct t.table_id as tableId, ");
		    	queryStr.append("p.operate_id as operateId, ");
		    	queryStr.append("count(t.operate_id) as operateCount ");
		    	queryStr.append("from km_operate_log t, km_operate_score p ");
		    	queryStr.append("where t.operate_id=p.id ");
		    	queryStr.append("group by t.table_id, p.operate_id");
		    	System.out.println("sql = "+queryStr.toString());

				SQLQuery query = session.createSQLQuery(queryStr.toString());
				query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				query.addScalar("tableId", org.hibernate.Hibernate.STRING);
				query.addScalar("operateId", org.hibernate.Hibernate.STRING);
				query.addScalar("operateCount", org.hibernate.Hibernate.INTEGER);
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
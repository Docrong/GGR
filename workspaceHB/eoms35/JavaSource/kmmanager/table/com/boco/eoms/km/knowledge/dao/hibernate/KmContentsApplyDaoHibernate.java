package com.boco.eoms.km.knowledge.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.km.knowledge.dao.KmContentsApplyDao;
import com.boco.eoms.km.knowledge.model.KmContentsApply;
import com.boco.eoms.km.statics.util.StatisticMethod;
import com.boco.eoms.km.table.model.KmTableTheme;

/**
 * <p>
 * Title:知识申请 dao的hibernate实现
 * </p>
 * <p>
 * Description:知识申请
 * </p>
 * <p>
 * Tue Jul 14 10:27:17 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class KmContentsApplyDaoHibernate extends BaseDaoHibernate implements KmContentsApplyDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.knowledge.KmContentsApplyDao#getKmContentsApplys()
	 *      
	 */
	public List getKmContentsApplys() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmContentsApply kmContentsApply";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.knowledge.KmContentsApplyDao#getKmContentsApply(java.lang.String)
	 *
	 */
	public KmContentsApply getKmContentsApply(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmContentsApply kmContentsApply where kmContentsApply.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (KmContentsApply) result.iterator().next();
				} else {
					return new KmContentsApply();
				}
			}
		};
		return (KmContentsApply) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.knowledge.KmContentsApplyDao#saveKmContentsApplys(com.boco.eoms.km.knowledge.KmContentsApply)
	 *      
	 */
	public void saveKmContentsApply(final KmContentsApply kmContentsApply) {
		if ((kmContentsApply.getId() == null) || (kmContentsApply.getId().equals("")))
			getHibernateTemplate().save(kmContentsApply);
		else
			getHibernateTemplate().saveOrUpdate(kmContentsApply);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.knowledge.KmContentsApplyDao#removeKmContentsApplys(java.lang.String)
	 *      
	 */
    public void removeKmContentsApply(final String id) {
		getHibernateTemplate().delete(getKmContentsApply(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(final String nodeId) throws DictDAOException {
		if(nodeId == null || nodeId.equals("")){
			return "";
		}
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String hql = " from KmTableTheme kmTableTheme where kmTableTheme.nodeId=?";
				Query query = session.createQuery(hql);
				query.setString(0, nodeId);
				List list = query.list();
				if (list.iterator().hasNext()) {
					String name = ((KmTableTheme) list.iterator().next()).getThemeName();
					return name;
				}
				return "无对应的分类";
			}
		};
		return (String) getHibernateTemplate().execute(callback);
	}

 	/**
	 * 
	 * @see com.boco.eoms.km.knowledge.KmContentsApplyDao#getKmContentsApplys(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmContentsApplys(final Integer curPage, final Integer pageSize,
			final String whereStr, final String orderStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("from KmContentsApply kmContentsApply");
				if (whereStr != null && whereStr.length() > 0)
					queryStr.append(whereStr);
				
				StringBuffer queryCountStr = new StringBuffer("select count(*) ");
				queryCountStr.append(queryStr);

				int total = ((Integer) session.createQuery(queryCountStr.toString())
						.iterate().next()).intValue();
				
				List result = new ArrayList();
				if(total > 0){
					queryStr.append(orderStr);
					Query query = session.createQuery(queryStr.toString());
					query.setFirstResult(pageSize.intValue()
									* (curPage.intValue()));
					query.setMaxResults(pageSize.intValue());
					result = query.list();					
				}

				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	public Map getKmContentsApplyRanks(final String startDate,final  String endDate,
			final String userId, final String deptId, final String themeId , final String maxSize) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
//		select * from (select count(*) as applyCount, t.apply_title as applyTitle from km_contents_apply t group by t.apply_title) order by applyCount desc
//		select t2.id,t2.apply_title,t3.applyCount from km_contents_apply t2,(select count(*) as applyCount,t1.apply_id as applyId from km_contents_apply_vote t1  group by t1.apply_id order by applyCount desc) t3	where t2.id = t3.applyId		
		
				
		List result = new ArrayList();
		HashMap map = new HashMap();
		SQLQuery query = null;
		StringBuffer queryStr = new StringBuffer(); //申请主题
    	

		
		if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
			Date newStartDate = StatisticMethod.stringToDate( startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
			Date newEndDate = StatisticMethod.stringToDate(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
			
			queryStr.append("select t2.node_id as id,t2.theme_name as themeName from km_table_theme t2 where t2.node_id in (select t1.apply_theme as themeId from km_contents_apply t1 where 1=1 ");
			if(!"".equals(userId))
	    		queryStr.append(" and t1.apply_user like '"+userId+"' ");
	    	if(!"".equals(deptId))
	    		queryStr.append(" and t1.apply_dept like '"+deptId+"' ");
	    	queryStr.append(" group by t1.apply_theme )");
	    	query = session.createSQLQuery(queryStr.toString());
			query.addScalar("id", org.hibernate.Hibernate.STRING);
			query.addScalar("themeName", org.hibernate.Hibernate.STRING);
			List list1 = query.list();
			StringBuffer queryStr2 = new StringBuffer();
			queryStr2.append("select count(*) as applyCount,t1.apply_theme as themeId from km_contents_apply t1 where t1.apply_date BETWEEN ? and ? ");
			if(!"".equals(userId))
				queryStr2.append(" and t1.apply_user like '"+userId+"' ");
	    	if(!"".equals(deptId))
	    		queryStr2.append(" and t1.apply_dept like '"+deptId+"' ");
	    	queryStr2.append(" group by t1.apply_theme order by applyCount desc");
	    	query = session.createSQLQuery(queryStr2.toString());
	    	query.setDate(0, newStartDate);
	    	query.setDate(1, newEndDate);
			query.addScalar("applyCount", org.hibernate.Hibernate.STRING);
			query.addScalar("themeId", org.hibernate.Hibernate.STRING);
			if(!"".equals(maxSize)&&!"0".equals(maxSize)){
				query.setMaxResults(Integer.parseInt(maxSize));
			}
			List list2 = query.list();
			
			Map newMap = new HashMap();
			int list1Size = list1.size();
			
			for(int i=0;i<list1Size;i++){
				Object[] objs = (Object[])list1.get(i);
				newMap.put((String)objs[0], objs[1]);
			}
			
			int list2Size = list2.size();
			for(int i=0;i<list2Size;i++){
				Object[] objs = new Object[3];
				Object[] objs1 = (Object[])list2.get(i);
				Object obj = newMap.get((String)objs1[1]);
				objs[0] = objs1[1];
				objs[1] = obj;
				objs[2] = objs1[0];
				result.add(objs);
			}
		}
		else if("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())){
			String newStartDate = startDate + " 00:00:00";
			String newEndDate = endDate + " 23:59:59";
			//queryStr.append("select t2.id as id,t2.apply_title as applyTitle,t3.applyCount as applyCount from km_contents_apply t2,(select count(*) as applyCount,t1.apply_id as applyId from km_contents_apply_vote t1 where t1.vote_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss') group by t1.apply_id order by applyCount desc) t3	where t2.id = t3.applyId");
//			queryStr.append("select t4.id as id,t4.themeName as themeName,t4.applyCount as applyCount from (select t2.node_id as id,t2.theme_name as themeName,t3.applyCount as applyCount from km_table_theme t2,(select count(*) as applyCount,t1.apply_theme as themeId from km_contents_apply t1 where t1.apply_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss') ");
			queryStr.append("select t2.node_id as id,t2.theme_name as themeName,t3.applyCount as applyCount from km_table_theme t2,(select count(*) as applyCount,t1.apply_theme as themeId from km_contents_apply t1 where t1.apply_date BETWEEN to_date(?, 'yyyy-MM-dd HH24:mi:ss') AND to_date(?, 'yyyy-MM-dd HH24:mi:ss') ");
//			select t4.id as id,t4.themeName as themeName,t4.applyCount as applyCount from (select t2.node_id as id,t2.theme_name as themeName,t3.applyCount as applyCount from km_table_theme t2,(select count(*) as applyCount,t1.apply_theme as themeId from km_contents_apply t1  group by t1.apply_theme  ) t3  where t2.node_id = t3.themeId  union  select t2.node_id as id,t2.theme_name,0 from km_table_theme t2 where t2.leaf ='1' and  t2.node_id not in (select t1.apply_theme from km_contents_apply t1) ) t4 order by applyCount desc
			if(!"".equals(userId))
	    		queryStr.append(" and t1.apply_user like '"+userId+"' ");
	    	if(!"".equals(deptId))
	    		queryStr.append(" and t1.apply_dept like '"+deptId+"' ");
	    	if(!"".equals(themeId))
	    		queryStr.append(" and t1.apply_theme like '"+themeId+"%' ");
	    	queryStr.append(" group by t1.apply_theme ) t3  where t2.node_id = t3.themeId order by applyCount desc ");
//			queryStr.append(" union all select t2.node_id as id,t2.theme_name,0 from km_table_theme t2 where t2.leaf ='1' and  t2.node_id not in (select t1.apply_theme from km_contents_apply t1)) t4 order by applyCount desc ");
			
	    	query = session.createSQLQuery(queryStr.toString());
			query.addScalar("id", org.hibernate.Hibernate.STRING);
			query.addScalar("themeName", org.hibernate.Hibernate.STRING);
			query.addScalar("applyCount", org.hibernate.Hibernate.STRING);
	    	query.setString(0, newStartDate);
	    	query.setString(1, newEndDate);
	    	if(!"".equals(maxSize)&&!"0".equals(maxSize)){
				query.setMaxResults(Integer.parseInt(maxSize));
			}
			result = query.list();
		} 
		map.put("result", result);
		
		 //返回结果
		return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	public Map getKmContentsApplys(final Integer curPage, final Integer pageSize,
			final String applyTheme, final String startDate, final String endDate, final String orderStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				
				Date newStartDate = null;
				Date newEndDate = null;
				StringBuffer queryStr = new StringBuffer("from KmContentsApply kmContentsApply where 1=1");
				if(applyTheme!=null && !"".equals(applyTheme.trim()))
					queryStr.append(" and kmContentsApply.applyTheme like '"+applyTheme+"%'");
				if(startDate!=null && !"".equals(startDate.trim()))
					newStartDate = StatisticMethod.stringToDate( startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
				if(endDate!=null && !"".equals(endDate.trim()))
					newEndDate = StatisticMethod.stringToDate(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
					
				
				Criteria crit = session.createCriteria(KmContentsApply.class);
				crit.add( Restrictions.like("applyTheme", applyTheme+"%") );
				crit.add( Restrictions.ge("applyDate", newStartDate));
				crit.add( Restrictions.le("applyDate", newEndDate));
				crit.addOrder(Order.desc("applyDate") );
				crit.setFirstResult(pageSize.intValue()*curPage.intValue());
				crit.setMaxResults(pageSize.intValue());
				List result = crit.list();
				int total = result.size();
				

				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
	
}
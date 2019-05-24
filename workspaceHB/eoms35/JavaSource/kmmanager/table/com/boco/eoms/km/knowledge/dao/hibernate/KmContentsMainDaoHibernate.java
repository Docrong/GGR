package com.boco.eoms.km.knowledge.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.km.knowledge.dao.KmContentsMainDao;

/**
 * <p>
 * Title:首页知识排行 dao的hibernate实现
 * </p>
 * <p>
 * Description:首页知识排行
 * </p>
 * <p>
 * Wed Aug 19 15:53:50 CST 2009
 * </p>
 * 
 * @author wangzhiyong
 * @version 1.0
 * 
 */
public class KmContentsMainDaoHibernate extends BaseDaoHibernate implements KmContentsMainDao,
		ID2NameDAO {
	
	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		
		return "";
	}
 
 	

	public List getKmContentsMain(final int count,final String type) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
		
		SQLQuery query = null;
		String queryStr = null; 
		
		if("1".equals(type)){
			if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect()))
				queryStr = "select t.table_id as tableId,t.content_title as contentTitle,to_char(t.create_time,'%Y-%m-%d %H:%M:%S') as createTime,t.create_user as createUser,t.create_dept as createDept,t.content_id as id,t.theme_id as themeId from km_contents t order by 3 desc";
			else if("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect()))
				queryStr = "select t.table_id as tableId,t.content_title as contentTitle,to_char(t.create_time,'yyyy-MM-dd HH24:mi:ss') as createTime,t.create_user as createUser,t.create_dept as createDept,t.content_id as id,t.theme_id as themeId from km_contents t order by 3 desc";
		}
		else if("2".equals(type))
			queryStr = "select t1.mapping_node_id as tableId, t1.abstract as contentTitle,t1.uploadtime as createTime,t1.userid as createUser,t1.deptId as createDept,t1.id as id,'' as themeId from km_files t1 order by 3 desc";
		query = session.createSQLQuery(queryStr.toString());
		query.addScalar("tableId",Hibernate.STRING);
		query.addScalar("contentTitle",Hibernate.STRING);
		query.addScalar("createTime",Hibernate.STRING);
		query.addScalar("createUser",Hibernate.STRING);
		query.addScalar("createDept",Hibernate.STRING);
		query.addScalar("id",Hibernate.STRING);
		query.addScalar("themeId",Hibernate.STRING);
		query.setMaxResults(count);
		List result = query.list();
		 //返回结果
		return result;
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}



	public Map getKmContentsMain(final Integer curPage, final Integer pageSize,
  			final String whereStr,final String type) {
			HibernateCallback callback = new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException {
					String queryCountStr = null;
					String queryStr = null;
					int total = 0;
					
					if("1".equals(type)){
						queryCountStr = "select  count(*) as count from km_contents t";
						if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect()))
							queryStr = "select t.table_id as tableId,t.content_title as contentTitle,to_char(t.create_time,'%Y-%m-%d %H:%M:%S') as createTime,t.create_user as createUser,t.create_dept as createDept,t.content_id as id,t.theme_id as themeId from km_contents t order by 3 desc";
						else if("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect()))
							queryStr = "select t.table_id as tableId,t.content_title as contentTitle,to_char(t.create_time,'yyyy-MM-dd HH24:mi:ss') as createTime,t.create_user as createUser,t.create_dept as createDept,t.content_id as id,t.theme_id as themeId from km_contents t order by 3 desc";
					}
					else if("2".equals(type)){
						queryCountStr = "select count(*) as count from km_files t1 ";
						queryStr = "select t1.mapping_node_id as tableId, t1.abstract as contentTitle,t1.uploadtime as createTime,t1.userid as createUser,t1.deptId as createDept,t1.id as id,'' as themeId from km_files t1 order by 3 desc";
					}
					total = Integer.parseInt(session.createSQLQuery(queryCountStr).addScalar("count", Hibernate.STRING).uniqueResult().toString());
					
					SQLQuery query = session.createSQLQuery(queryStr);
					
					query.addScalar("tableId",Hibernate.STRING);
					query.addScalar("contentTitle",Hibernate.STRING);
					query.addScalar("createTime",Hibernate.STRING);
					query.addScalar("createUser",Hibernate.STRING);
					query.addScalar("createDept",Hibernate.STRING);
					query.addScalar("id",Hibernate.STRING);
					query.addScalar("themeId",Hibernate.STRING);
					
					query.setFirstResult(pageSize.intValue()* (curPage.intValue()));
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


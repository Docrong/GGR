package com.boco.eoms.km.lesson.dao.hibernate;

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
import com.boco.eoms.km.lesson.dao.LessonAnaysisDao;
import com.boco.eoms.km.lesson.model.LessonAnaysis;

/**
 * <p>
 * Title:考试课程分析 dao的hibernate实现
 * </p>
 * <p>
 * Description:考试课程分析
 * </p>
 * <p>
 * Tue Jul 07 09:44:42 CST 2009
 * </p>
 * 
 * @author mosquito
 * @version 1.0
 * 
 */
public class LessonAnaysisDaoHibernate extends BaseDaoHibernate implements LessonAnaysisDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.lesson.LessonAnaysisDao#getLessonAnaysiss()
	 *      
	 */
	public List getLessonAnaysiss() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from LessonAnaysis lessonAnaysis";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.lesson.LessonAnaysisDao#getLessonAnaysis(java.lang.String)
	 *
	 */
	public LessonAnaysis getLessonAnaysis(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from LessonAnaysis lessonAnaysis where lessonAnaysis.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (LessonAnaysis) result.iterator().next();
				} else {
					return new LessonAnaysis();
				}
			}
		};
		return (LessonAnaysis) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.lesson.LessonAnaysisDao#saveLessonAnaysiss(com.boco.eoms.lesson.LessonAnaysis)
	 *      
	 */
	public void saveLessonAnaysis(final LessonAnaysis lessonAnaysis) {
		if ((lessonAnaysis.getId() == null) || (lessonAnaysis.getId().equals("")))
			getHibernateTemplate().save(lessonAnaysis);
		else
			getHibernateTemplate().saveOrUpdate(lessonAnaysis);
	}

	/**
	 * 
	 * @see com.boco.eoms.lesson.LessonAnaysisDao#removeLessonAnaysiss(java.lang.String)
	 *      
	 */
    public void removeLessonAnaysis(final String id) {
		getHibernateTemplate().delete(getLessonAnaysis(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		LessonAnaysis lessonAnaysis = this.getLessonAnaysis(id);
		if(lessonAnaysis==null){
			return "";
		}
		//TODO 请修改代码
		return null;
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.lesson.LessonAnaysisDao#getLessonAnaysiss(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getLessonAnaysiss(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from LessonAnaysis lessonAnaysis";
				String sumStr = "select t.test_id as testid, count(t.attend_user) as totaluser, round(sum(t.score)/count(t.attend_user),2) as avgscore" +
						" from km_exam_attend t group by t.test_id";
				
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();
				
				Query query = session.createQuery(queryStr);
				SQLQuery sumquery = session.createSQLQuery(sumStr);
				
				query.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				
//				sumquery.addScalar("totaluser", org.hibernate.Hibernate.STRING);
//				sumquery.addScalar("avgscore", org.hibernate.Hibernate.STRING);
				
				List result = query.list();
				List resultlist = sumquery.list();
				
				HashMap map = new HashMap();
				map.put("total", new Integer(total));
				map.put("result", result);
				map.put("resultlist",resultlist);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
//	public Map getLessonResult(final Integer curPage, final Integer pageSize,
//			final String whereStr) {
//		HibernateCallback callback = new HibernateCallback() {
//			public Object doInHibernate(Session session)
//					throws HibernateException {
//				String queryStr = "from LessonAnaysis lessonAnaysis";
//				String sumStr = "select count(t.attend_user) as totaluser, round(sum(t.score)/count(t.attend_user),2) as avgscore" +
//						" from km_exam_attend t group by t.test_id";
//				
//				if (whereStr != null && whereStr.length() > 0)
//					queryStr += whereStr;
//				String queryCountStr = "select count(*) " + queryStr;
//
//				int total = ((Integer) session.createQuery(queryCountStr)
//						.iterate().next()).intValue();
//				
//				Query query = session.createQuery(queryStr);
//				SQLQuery sumquery = session.createSQLQuery(sumStr);
//				
//				query.setFirstResult(pageSize.intValue()
//								* (curPage.intValue()));
//				query.setMaxResults(pageSize.intValue());
//				
//				sumquery.setFirstResult(pageSize.intValue());
////				sumquery.addScalar("totaluser", org.hibernate.Hibernate.STRING);
////				sumquery.addScalar("avgscore", org.hibernate.Hibernate.STRING);
//				
//				List result = query.list();
//				List resultlist = sumquery.list();
//				
//				HashMap map = new HashMap();
//				map.put("total", new Integer(total));
//				map.put("result", result);
//				map.put("resultlist",resultlist);
//				return map;
//			}
//		};
//		return (Map) getHibernateTemplate().execute(callback);
//	}
}
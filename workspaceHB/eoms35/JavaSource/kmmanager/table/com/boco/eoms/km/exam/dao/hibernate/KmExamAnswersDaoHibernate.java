package com.boco.eoms.km.exam.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.km.exam.dao.KmExamAnswersDao;
import com.boco.eoms.km.exam.model.KmExamAnswers;

/**
 * <p>
 * Title:答题信息表 dao的hibernate实现
 * </p>
 * <p>
 * Description:答题信息表
 * </p>
 * <p>
 * Mon May 11 11:00:31 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class KmExamAnswersDaoHibernate extends BaseDaoHibernate implements KmExamAnswersDao,
		ID2NameDAO {
	
	/**
	 * 根据试卷id 题目id 参加考试人 查询答题信息
	 * @param testID
	 * @param questionID
	 * @param attendUser
	 * @return
	 */
	public KmExamAnswers getKmExamAnswers(final String testID,final String questionID,final String attendUser) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("from KmExamAnswers kmExamAnswers ");
				queryStr.append("where kmExamAnswers.testID=? ");	
				queryStr.append("and kmExamAnswers.questionId=? ");	
				queryStr.append("and kmExamAnswers.attendUser=? ");	

				Query query = session.createQuery(queryStr.toString());
				query.setString(0, testID);
				query.setString(1, questionID);
				query.setString(2, attendUser);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (KmExamAnswers) result.iterator().next();
				} else {
					return new KmExamAnswers();
				}
			}
		};
		return (KmExamAnswers) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 对某个人的某套试卷进行求和
	 * @param testID
	 * @param attendUser
	 * @return该人该套试卷的总成绩
	 */
	public String getSumScore(final String testID, final String attendUser){		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("select sum(kmExamAnswers.score) from KmExamAnswers kmExamAnswers ");
				queryStr.append("where kmExamAnswers.testID=? ");	
				queryStr.append("and kmExamAnswers.attendUser=? ");	
				
				Query query = session.createQuery(queryStr.toString());
				query.setString(0, testID);
				query.setString(1, attendUser);
				List list = query.list();
				list.iterator().next();
				return (String)list.get(0);
			}
		};
		return (String) getHibernateTemplate().execute(callback);
	}
	
    /**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamAnswersDao#getKmExamAnswerss()
	 *      
	 */
	public List getKmExamAnswerss() {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExamAnswers kmExamAnswers";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.exam.KmExamAnswersDao#getKmExamAnswers(java.lang.String)
	 *
	 */
	public KmExamAnswers getKmExamAnswers(final String id) {
		KmExamAnswers kmExamAnswers = (KmExamAnswers) getHibernateTemplate().get(KmExamAnswers.class, id);
		if (kmExamAnswers == null) {
			kmExamAnswers = new KmExamAnswers();
		}
		return kmExamAnswers;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamAnswersDao#saveKmExamAnswerss(com.boco.eoms.km.exam.KmExamAnswers)
	 *      
	 */
	public void saveKmExamAnswers(final KmExamAnswers kmExamAnswers) {
		if ((kmExamAnswers.getId() == null) || (kmExamAnswers.getId().equals("")))
			getHibernateTemplate().save(kmExamAnswers);
		else
			getHibernateTemplate().saveOrUpdate(kmExamAnswers);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamAnswersDao#removeKmExamAnswerss(java.lang.String)
	 *      
	 */
    public void removeKmExamAnswers(final String id) {
		getHibernateTemplate().delete(getKmExamAnswers(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		KmExamAnswers kmExamAnswers = this.getKmExamAnswers(id);
		if(kmExamAnswers==null){
			return "";
		}
		//TODO 请修改代码
		return null;
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamAnswersDao#getKmExamAnswerss(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmExamAnswerss(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("from KmExamAnswers kmExamAnswers ");
				if (whereStr != null && whereStr.length() > 0)
					queryStr.append(whereStr);

				StringBuffer queryCountStr = new StringBuffer("select count(kmExamAnswers.id) ");
				queryCountStr.append(queryStr);

				int total = ((Integer) session.createQuery(queryCountStr.toString())
						.iterate().next()).intValue();

				List result = new ArrayList();
				if(total >0){
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
	
}
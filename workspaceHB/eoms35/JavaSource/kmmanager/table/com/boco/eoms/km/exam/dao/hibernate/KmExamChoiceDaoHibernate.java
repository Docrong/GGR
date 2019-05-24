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
import com.boco.eoms.km.exam.dao.KmExamChoiceDao;
import com.boco.eoms.km.exam.model.KmExamChoice;

/**
 * <p>
 * Title:选项表 dao的hibernate实现
 * </p>
 * <p>
 * Description:选项表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 * 
 * @author hsl
 * @version 1.0
 * 
 */
public class KmExamChoiceDaoHibernate extends BaseDaoHibernate implements KmExamChoiceDao,
		ID2NameDAO {
	
	/**
	 * 查询某试题下的所用选项
	 * @param questionID
	 * @return
	 */
	public List getKmExamChoices(final String questionsID) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExamChoice kmExamChoice where kmExamChoice.questionsID=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, questionsID);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	
    /**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamChoiceDao#getKmExamChoices()
	 *      
	 */
	public List getKmExamChoices() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExamChoice kmExamChoice";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.exam.KmExamChoiceDao#getKmExamChoice(java.lang.String)
	 *
	 */
	public KmExamChoice getKmExamChoice(final String id) {
		KmExamChoice kmExamChoice = (KmExamChoice) getHibernateTemplate().get(KmExamChoice.class, id);
		if (kmExamChoice == null) {
			kmExamChoice = new KmExamChoice();
		}
		return kmExamChoice;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamChoiceDao#saveKmExamChoices(com.boco.eoms.km.exam.KmExamChoice)
	 *      
	 */
	public void saveKmExamChoice(final KmExamChoice kmExamChoice) {
		if ((kmExamChoice.getChoiceID() == null) || (kmExamChoice.getChoiceID().equals("")))
			getHibernateTemplate().save(kmExamChoice);
		else
			getHibernateTemplate().saveOrUpdate(kmExamChoice);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamChoiceDao#removeKmExamChoices(java.lang.String)
	 *      
	 */
    public void removeKmExamChoice(final String id) {
		getHibernateTemplate().delete(getKmExamChoice(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		KmExamChoice kmExamChoice = this.getKmExamChoice(id);
		if(kmExamChoice==null){
			return "";
		}
		//TODO 请修改代码
		return kmExamChoice.getContent();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamChoiceDao#getKmExamChoices(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmExamChoices(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("from KmExamChoice kmExamChoice ");
				if (whereStr != null && whereStr.length() > 0)
					queryStr.append(whereStr);

				StringBuffer queryCountStr = new StringBuffer("select count(kmExamChoice.choiceID) ");
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

	public List getKmExamChoicesByQuestionID(final String questionID){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExamChoice kmExamChoice where kmExamChoice.questionsID=? order by orderBy";
				Query query = session.createQuery(queryStr);
				query.setString(0, questionID);
				query.setFirstResult(0);
				//query.setMaxResults(1);
				List result = query.list();
				return result;
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
}
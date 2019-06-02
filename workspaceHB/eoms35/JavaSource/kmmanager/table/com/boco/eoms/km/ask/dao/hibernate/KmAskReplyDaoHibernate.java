package com.boco.eoms.km.ask.dao.hibernate;

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
import com.boco.eoms.km.ask.dao.KmAskReplyDao;
import com.boco.eoms.km.ask.model.KmAskReply;

/**
 * <p>
 * Title:答题 dao的hibernate实现
 * </p>
 * <p>
 * Description:回答答案
 * </p>
 * <p>
 * Tue Aug 04 15:52:08 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class KmAskReplyDaoHibernate extends BaseDaoHibernate implements KmAskReplyDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.ask.KmAskReplyDao#getKmAskReplys()
	 *      
	 */
	public List getKmAskReplys() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmAskReply kmAskReply";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 * 根据问题查询所有回答
	 * @param questionId
	 * @return
	 */
	public List getKmAskReplysByQuestionId(final String questionId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmAskReply kmAskReply where kmAskReply.questionId=?";
				Query query  = session.createQuery(queryStr);
				query.setString(0, questionId);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *  查询某人回答的所有问题
	 * @param answerUser
	 * @return
	 */
	public List getKmAskReplysByAnswerUser(final String answerUser){
		HibernateCallback callback = new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException{
				String queryStr = "from KmAskReply kmAskReply where kmAskReply.answerUser = ?";
				Query query = session.createQuery(queryStr);
				query.setString(0, answerUser);
				return query.list();
			}
		};
		return (List)getHibernateTemplate().executeFind(callback);
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.ask.KmAskReplyDao#getKmAskReply(java.lang.String)
	 *
	 */
	public KmAskReply getKmAskReply(final String id) {
		KmAskReply kmAskReply = (KmAskReply) getHibernateTemplate().get(KmAskReply.class, id);
		if (kmAskReply == null) {
			kmAskReply = new KmAskReply();
		}
		return kmAskReply;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.ask.KmAskReplyDao#saveKmAskReplys(com.boco.eoms.km.ask.KmAskReply)
	 *      
	 */
	public void saveKmAskReply(final KmAskReply kmAskReply) {
		if ((kmAskReply.getId() == null) || (kmAskReply.getId().equals("")))
			getHibernateTemplate().save(kmAskReply);
		else
			getHibernateTemplate().saveOrUpdate(kmAskReply);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.ask.KmAskReplyDao#removeKmAskReplys(java.lang.String)
	 *      
	 */
    public void removeKmAskReply(final String id) {
		HibernateCallback callback = new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException {
				String queryStr = "delete from KmAskReply kmAskReply where kmAskReply.id=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, id);
				int result = query.executeUpdate();
				return new Integer(result);
			}
		};
		getHibernateTemplate().execute(callback);
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		KmAskReply kmAskReply = this.getKmAskReply(id);
		if(kmAskReply==null){
			return "";
		}
		//TODO 请修改代码
		return kmAskReply.getAnswer();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.ask.KmAskReplyDao#getKmAskReplys(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmAskReplys(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmAskReply kmAskReply";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();

				List result = null;
				if(total >0){
					Query query = session.createQuery(queryStr);
					query.setFirstResult(pageSize.intValue()
									* (curPage.intValue()));
					query.setMaxResults(pageSize.intValue());
					result = query.list();					
				}
				else{
					result = new ArrayList();
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
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
import com.boco.eoms.km.ask.dao.KmAskCommentDao;
import com.boco.eoms.km.ask.model.KmAskComment;

/**
 * <p>
 * Title:问答评论 dao的hibernate实现
 * </p>
 * <p>
 * Description:问答评论
 * </p>
 * <p>
 * Fri Aug 14 15:49:40 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class KmAskCommentDaoHibernate extends BaseDaoHibernate implements KmAskCommentDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.ask.KmAskCommentDao#getKmAskComments()
	 *      
	 */
	public List getKmAskComments() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmAskComment kmAskComment";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 * 查询某问题下的所有评论信息
	 * @param questionId 问答id
	 * @return 所有评论集合
	 */
	public List getKmAskComments(final String questionId){
		HibernateCallback callback = new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException {
				String queryStr = "from KmAskComment kmAskComment where kmAskComment.questionId=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, questionId);
				return query.list();
			}
		};
		return (List)getHibernateTemplate().execute(callback);
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.ask.KmAskCommentDao#getKmAskComment(java.lang.String)
	 *
	 */
	public KmAskComment getKmAskComment(final String id) {
		KmAskComment kmAskComment = (KmAskComment) getHibernateTemplate().get(KmAskComment.class, id);
		if (kmAskComment == null) {
			kmAskComment = new KmAskComment();
		}
		return kmAskComment;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.ask.KmAskCommentDao#saveKmAskComments(com.boco.eoms.km.ask.KmAskComment)
	 *      
	 */
	public void saveKmAskComment(final KmAskComment kmAskComment) {
		if ((kmAskComment.getId() == null) || (kmAskComment.getId().equals("")))
			getHibernateTemplate().save(kmAskComment);
		else
			getHibernateTemplate().saveOrUpdate(kmAskComment);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.ask.KmAskCommentDao#removeKmAskComments(java.lang.String)
	 *      
	 */
    public void removeKmAskComment(final String id) {
		HibernateCallback callback = new HibernateCallback(){
			public Object doInHibernate(Session session)
			throws HibernateException {
				String queryStr = "delete from KmAskComment kmAskComment where kmAskComment.id=?";
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
		KmAskComment kmAskComment = this.getKmAskComment(id);
		if(kmAskComment==null){
			return "";
		}
		//TODO 请修改代码
		return null;
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.ask.KmAskCommentDao#getKmAskComments(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmAskComments(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmAskComment kmAskComment";
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
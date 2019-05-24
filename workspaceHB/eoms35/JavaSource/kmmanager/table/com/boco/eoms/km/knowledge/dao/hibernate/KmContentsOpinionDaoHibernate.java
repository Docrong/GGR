package com.boco.eoms.km.knowledge.dao.hibernate;

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
import com.boco.eoms.km.knowledge.dao.KmContentsOpinionDao;
import com.boco.eoms.km.knowledge.model.KmContentsOpinion;

/**
 * <p>
 * Title:知识管理 dao的hibernate实现
 * </p>
 * <p>
 * Description:知识管理
 * </p>
 * <p>
 * Tue Mar 24 10:33:01 CST 2009
 * </p>
 * 
 * @author eoms
 * @version 1.0
 * 
 */
public class KmContentsOpinionDaoHibernate extends BaseDaoHibernate implements KmContentsOpinionDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.knowledge.KmContentsOpinionDao#getKmContentsOpinions()
	 *      
	 */
	public List getKmContentsOpinions() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmContentsOpinion kmContentsOpinion";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.knowledge.KmContentsOpinionDao#getKmContentsOpinion(java.lang.String)
	 *
	 */
	public KmContentsOpinion getKmContentsOpinion(final String id) {
		KmContentsOpinion kmContentsOpinion = (KmContentsOpinion) getHibernateTemplate().get(KmContentsOpinion.class, id);
		if (kmContentsOpinion == null) {
			kmContentsOpinion = new KmContentsOpinion();
		}
		return kmContentsOpinion;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.knowledge.KmContentsOpinionDao#saveKmContentsOpinions(com.boco.eoms.km.knowledge.KmContentsOpinion)
	 *      
	 */
	public void saveKmContentsOpinion(final KmContentsOpinion kmContentsOpinion) {
		if ((kmContentsOpinion.getId() == null) || (kmContentsOpinion.getId().equals("")))
			getHibernateTemplate().save(kmContentsOpinion);
		else
			getHibernateTemplate().saveOrUpdate(kmContentsOpinion);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.knowledge.KmContentsOpinionDao#removeKmContentsOpinions(java.lang.String)
	 *      
	 */
    public void removeKmContentsOpinion(final String id) {
		getHibernateTemplate().delete(getKmContentsOpinion(id));
	}
    
    /**
     * 根据知识id查询该知识所用的评论
     * @param contentId
     * @return
     */
    public List getKmContentsOpinionsByContentId(final String contentId){
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("from KmContentsOpinions kmContentsOpinions ");
				queryStr.append("where kmContentsOpinions.contentId=? ");

				Query query = session.createQuery(queryStr.toString());
				query.setString(0, contentId);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);
    }
    
    /**
     * 根据知识id删除所用对应的知识评论记录
     * @param contentId
     */
    public void removeKmContentsOpinionList(final String contentId){
    	List list = getKmContentsOpinionsByContentId(contentId);
    	for(int i=0;i<list.size();i++){
    		KmContentsOpinion kmContentsOpinion = (KmContentsOpinion)list.get(i);
    		getHibernateTemplate().delete(kmContentsOpinion);
    	}
    }
    
	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		KmContentsOpinion kmContentsOpinion = this.getKmContentsOpinion(id);
		if(kmContentsOpinion==null){
			return "";
		}
		//TODO 请修改代码
		return null;//kmContentsOpinion.yourCode();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.knowledge.KmContentsOpinionDao#getKmContentsOpinions(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmContentsOpinions(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("from KmContentsOpinion kmContentsOpinion ");
				if (whereStr != null && whereStr.length() > 0)
					queryStr.append(whereStr);

				StringBuffer queryCountStr = new StringBuffer("select count(kmContentsOpinion.id) ");
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
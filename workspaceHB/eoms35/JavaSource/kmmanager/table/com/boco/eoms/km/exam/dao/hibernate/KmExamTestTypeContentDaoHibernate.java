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
import com.boco.eoms.km.exam.dao.KmExamTestTypeContentDao;
import com.boco.eoms.km.exam.model.KmExamTestTypeContent;

/**
 * <p>
 * Title:题型内容表 dao的hibernate实现
 * </p>
 * <p>
 * Description:题型内容表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 * 
 * @author hsl
 * @version 1.0
 * 
 */
public class KmExamTestTypeContentDaoHibernate extends BaseDaoHibernate implements KmExamTestTypeContentDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamTestTypeContentDao#getKmExamTestTypeContents()
	 *      
	 */
	public List getKmExamTestTypeContents() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExamTestTypeContent kmExamTestTypeContent";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 * 查询某类型下所有题目信息
	 * @param testTypeID
	 * @return
	 */
	public List getKmExamTestTypeContentByTestTypeID(final String testTypeID) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExamTestTypeContent kmExamTestTypeContent where kmExamTestTypeContent.testTypeID=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, testTypeID);
				List result = query.list();
				return result;
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.exam.KmExamTestTypeContentDao#getKmExamTestTypeContent(java.lang.String)
	 *
	 */
	public KmExamTestTypeContent getKmExamTestTypeContent(final String id) {
		KmExamTestTypeContent kmExamTestTypeContent = (KmExamTestTypeContent) getHibernateTemplate().get(KmExamTestTypeContent.class, id);
		if (kmExamTestTypeContent == null) {
			kmExamTestTypeContent = new KmExamTestTypeContent();
		}
		return kmExamTestTypeContent;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamTestTypeContentDao#saveKmExamTestTypeContents(com.boco.eoms.km.exam.KmExamTestTypeContent)
	 *      
	 */
	public void saveKmExamTestTypeContent(final KmExamTestTypeContent kmExamTestTypeContent) {
		if ((kmExamTestTypeContent.getTypeContentID() == null) || (kmExamTestTypeContent.getTypeContentID().equals("")))
			getHibernateTemplate().save(kmExamTestTypeContent);
		else
			getHibernateTemplate().saveOrUpdate(kmExamTestTypeContent);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamTestTypeContentDao#removeKmExamTestTypeContents(java.lang.String)
	 *      
	 */
    public void removeKmExamTestTypeContent(final String id) {
		getHibernateTemplate().delete(getKmExamTestTypeContent(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		KmExamTestTypeContent kmExamTestTypeContent = this.getKmExamTestTypeContent(id);
		if(kmExamTestTypeContent==null){
			return "";
		}
		//TODO 请修改代码
		//return kmExamTestTypeContent.yourCode();
		return "";
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamTestTypeContentDao#getKmExamTestTypeContents(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmExamTestTypeContents(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("from KmExamTestTypeContent kmExamTestTypeContent ");
				if (whereStr != null && whereStr.length() > 0)
					queryStr.append(whereStr);

				StringBuffer queryCountStr = new StringBuffer("select count(kmExamTestTypeContent.typeContentID) ");
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
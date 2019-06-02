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
import com.boco.eoms.km.exam.dao.KmExamAttendDao;
import com.boco.eoms.km.exam.model.KmExamAttend;

/**
 * <p>
 * Title:考试信息 dao的hibernate实现
 * </p>
 * <p>
 * Description:考试信息
 * </p>
 * <p>
 * Mon May 11 10:55:38 CST 2009
 * </p>
 * 
 * @author lvweihua
 * @version 1.0
 * 
 */
public class KmExamAttendDaoHibernate extends BaseDaoHibernate implements KmExamAttendDao,
		ID2NameDAO {
	
	/**
	 * 根据试卷id和参加考试人 得到唯一的一条考试信息
	 * @see com.boco.eoms.km.exam.KmExamAttendDao#getKmExamAttend(java.lang.String,java.lang.String)
	 * @param testID
	 * @param attendUser
	 * @return
	 */
	public KmExamAttend getKmExamAttend(final String testID,final String attendUser) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExamAttend kmExamAttend where kmExamAttend.testId=? and kmExamAttend.attendUser=?";
				Query query = session.createQuery(queryStr);
				query.setParameter(0, testID);
				query.setParameter(1, attendUser);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (KmExamAttend) result.iterator().next();
				} else {
					return new KmExamAttend();
				}
			}
		};
		return (KmExamAttend) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 查询当前人参加考试的发布结果信息
	 * @param attendUser
	 * @return
	 */
	public List getKmExamAttends(final String attendUser,final String isPublic) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExamAttend kmExamAttend where kmExamAttend.attendUser=? and kmExamAttend.isPublic=?";
				Query query = session.createQuery(queryStr);
				query.setParameter(0, attendUser);
				query.setParameter(1, isPublic);
				List result = query.list();
				return result;
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}
	
    /**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamAttendDao#getKmExamAttends()
	 *      
	 */
	public List getKmExamAttends() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExamAttend kmExamAttend";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.km.exam.KmExamAttendDao#getKmExamAttend(java.lang.String)
	 *
	 */
	public KmExamAttend getKmExamAttend(final String id) {
		KmExamAttend kmExamAttend = (KmExamAttend) getHibernateTemplate().get(KmExamAttend.class, id);
		if (kmExamAttend == null) {
			kmExamAttend = new KmExamAttend();
		}
		return kmExamAttend;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamAttendDao#saveKmExamAttends(com.boco.eoms.km.exam.KmExamAttend)
	 *      
	 */
	public void saveKmExamAttend(final KmExamAttend kmExamAttend) {
		if ((kmExamAttend.getId() == null) || (kmExamAttend.getId().equals("")))
			getHibernateTemplate().save(kmExamAttend);
		else
			getHibernateTemplate().saveOrUpdate(kmExamAttend);
	}

	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamAttendDao#removeKmExamAttends(java.lang.String)
	 *      
	 */
    public void removeKmExamAttend(final String id) {
		getHibernateTemplate().delete(getKmExamAttend(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		KmExamAttend kmExamAttend = this.getKmExamAttend(id);
		if(kmExamAttend==null){
			return "";
		}
		//TODO 请修改代码
		return null;
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.km.exam.KmExamAttendDao#getKmExamAttends(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmExamAttends(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				StringBuffer queryStr = new StringBuffer("from KmExamAttend kmExamAttend ");
				if (whereStr != null && whereStr.length() > 0)
					queryStr.append(whereStr);

				StringBuffer queryCountStr = new StringBuffer("select count(kmExamAttend.id) ");
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
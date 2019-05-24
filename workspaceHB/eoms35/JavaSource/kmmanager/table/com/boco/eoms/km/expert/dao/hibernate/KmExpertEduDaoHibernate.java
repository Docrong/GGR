package com.boco.eoms.km.expert.dao.hibernate;

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
import com.boco.eoms.km.expert.dao.KmExpertEduDao;
import com.boco.eoms.km.expert.model.KmExpertEdu;

/**
 * <p>
 * Title:教育背景 dao的hibernate实现
 * </p>
 * <p>
 * Description:专家教育背景
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
public class KmExpertEduDaoHibernate extends BaseDaoHibernate implements KmExpertEduDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.expert.edu.KmExpertEduDao#getKmExpertEdus()
	 *      
	 */
	public List getKmExpertEdus() {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExpertEdu kmExpertEdu";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.expert.edu.KmExpertEduDao#getKmExpertEdu(java.lang.String)
	 *
	 */
	public KmExpertEdu getKmExpertEdu(final String id) {
		KmExpertEdu kmExpertEdu = (KmExpertEdu) getHibernateTemplate().get(KmExpertEdu.class, id);
		if (kmExpertEdu == null) {
			kmExpertEdu = new KmExpertEdu();
		}
		return kmExpertEdu;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.expert.edu.KmExpertEduDao#saveKmExpertEdus(com.boco.eoms.expert.edu.KmExpertEdu)
	 *      
	 */
	public void saveKmExpertEdu(final KmExpertEdu kmExpertEdu) {
		if ((kmExpertEdu.getId() == null) || (kmExpertEdu.getId().equals("")))
			getHibernateTemplate().save(kmExpertEdu);
		else
			getHibernateTemplate().saveOrUpdate(kmExpertEdu);
	}

	/**
	 * 
	 * @see com.boco.eoms.expert.edu.KmExpertEduDao#removeKmExpertEdus(java.lang.String)
	 *      
	 */
    public void removeKmExpertEdu(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "delete from KmExpertEdu kmExpertEdu where kmExpertEdu.id=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, id);
				int ret = query.executeUpdate();
				return new Integer(ret);
			}
		};
		getHibernateTemplate().execute(callback);
	}
    
    /**
     * 根据id批量删除教育背景
     * @param id 主键
     * 
     */
    public void removeKmExpertEdus(final String[] ids) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "delete from KmExpertEdu kmExpertEdu where kmExpertEdu.id in (:ids)";
				Query query = session.createQuery(queryStr);
				query.setParameterList("ids", ids);
				int ret = query.executeUpdate();
				return new Integer(ret);
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
		KmExpertEdu kmExpertEdu = this.getKmExpertEdu(id);
		if(kmExpertEdu==null){
			return "";
		}
		//TODO 请修改代码
		return kmExpertEdu.getExpertEduProName();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.expert.edu.KmExpertEduDao#getKmExpertEdus(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmExpertEdus(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExpertEdu kmExpertEdu";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();
				Query query = session.createQuery(queryStr);
				query.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
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

	public Map getKmExpertEdusByUserId(final Integer curPage, final Integer pageSize, final String userId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {								
				String queryCountStr = "select count(kmExpertEdu.id) from KmExpertEdu kmExpertEdu where kmExpertEdu.userId=?";
				Query queryCount = session.createQuery(queryCountStr);
				queryCount.setString(0, userId);
				int total = ((Integer)queryCount.iterate().next()).intValue();
				
				List result = new ArrayList();
				if(total >0){
					String queryStr = "from KmExpertEdu kmExpertEdu where kmExpertEdu.userId=?";
					Query query = session.createQuery(queryStr);
					query.setString(0, userId);
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
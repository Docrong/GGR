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
import com.boco.eoms.km.expert.dao.KmExpertComDao;
import com.boco.eoms.km.expert.model.KmExpertCom;

/**
 * <p>
 * Title:技术交流竞赛表彰 dao的hibernate实现
 * </p>
 * <p>
 * Description:技术交流竞赛表彰
 * </p>
 * <p>
 * Mon Jun 15 18:07:24 CST 2009
 * </p>
 * 
 * @author zhangxb
 * @version 1.0
 * 
 */
public class KmExpertComDaoHibernate extends BaseDaoHibernate implements KmExpertComDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.expert.com.KmExpertComDao#getKmExpertComs()
	 *      
	 */
	public List getKmExpertComs() {		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExpertCom kmExpertCom";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.expert.com.KmExpertComDao#getKmExpertCom(java.lang.String)
	 *
	 */
	public KmExpertCom getKmExpertCom(final String id) {
		KmExpertCom kmExpertCom = (KmExpertCom) getHibernateTemplate().get(KmExpertCom.class, id);
		if (kmExpertCom == null) {
			kmExpertCom = new KmExpertCom();
		}
		return kmExpertCom;
	}
	
	/**
	 * 
	 * @see com.boco.eoms.expert.com.KmExpertComDao#saveKmExpertComs(com.boco.eoms.expert.com.KmExpertCom)
	 *      
	 */
	public void saveKmExpertCom(final KmExpertCom kmExpertCom) {
		if ((kmExpertCom.getId() == null) || (kmExpertCom.getId().equals("")))
			getHibernateTemplate().save(kmExpertCom);
		else
			getHibernateTemplate().saveOrUpdate(kmExpertCom);
	}

	/**
	 * 
	 * @see com.boco.eoms.expert.com.KmExpertComDao#removeKmExpertComs(java.lang.String)
	 *      
	 */
    public void removeKmExpertCom(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "delete from KmExpertCom kmExpertCom where kmExpertCom.id=?";
				Query query = session.createQuery(queryStr);
				query.setString(0, id);
				int ret = query.executeUpdate();
				return new Integer(ret);
			}
		};
		getHibernateTemplate().execute(callback);	
	}

    /**
     * 根据id批量删除技术交流竞赛表彰
     * @param id 主键
     * 
     */
    public void removeKmExpertComs(final String[] ids) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "delete from KmExpertCom kmExpertCom where kmExpertCom.id in (:ids)";
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
		KmExpertCom kmExpertCom = this.getKmExpertCom(id);
		if(kmExpertCom==null){
			return "";
		}
		//TODO 请修改代码
		return kmExpertCom.getExpertComDetail();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.expert.com.KmExpertComDao#getKmExpertComs(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getKmExpertComs(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmExpertCom kmExpertCom";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();
				Query query = session.createQuery(queryStr);
				query
						.setFirstResult(pageSize.intValue()
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

	public Map getKmExpertComsByUserId(final Integer curPage, final Integer pageSize, final String userId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {								
				String queryCountStr = "select count(kmExpertCom.id) from KmExpertCom kmExpertCom where kmExpertCom.userId=?";
				Query queryCount = session.createQuery(queryCountStr);
				queryCount.setString(0, userId);
				int total = ((Integer)queryCount.iterate().next()).intValue();
				
				List result = new ArrayList();
				if(total >0){
					String queryStr = "from KmExpertCom kmExpertCom where kmExpertCom.userId=?";
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
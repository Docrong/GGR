package com.boco.eoms.repository.dao.hibernate;

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
import com.boco.eoms.repository.dao.TawLocalRepositoryUpDao;
import com.boco.eoms.repository.model.TawLocalRepositoryUp;

/**
 * <p>
 * Title:tawLocalRepositoryUp dao的hibernate实现
 * </p>
 * <p>
 * Description:tawLocalRepositoryUp
 * </p>
 * <p>
 * Fri Oct 30 16:52:13 CST 2009
 * </p>
 * 
 * @author 李锋
 * @version 1.0
 * 
 */
public class TawLocalRepositoryUpDaoHibernate extends BaseDaoHibernate implements TawLocalRepositoryUpDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.repositoryup.TawLocalRepositoryUpDao#getTawLocalRepositoryUps()
	 *      
	 */
	public List getTawLocalRepositoryUps() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawLocalRepositoryUp tawLocalRepositoryUp";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.repositoryup.TawLocalRepositoryUpDao#getTawLocalRepositoryUp(java.lang.String)
	 *
	 */
	public TawLocalRepositoryUp getTawLocalRepositoryUp(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawLocalRepositoryUp tawLocalRepositoryUp where tawLocalRepositoryUp.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (TawLocalRepositoryUp) result.iterator().next();
				} else {
					return new TawLocalRepositoryUp();
				}
			}
		};
		return (TawLocalRepositoryUp) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.system.repositoryup.TawLocalRepositoryUpDao#saveTawLocalRepositoryUps(com.boco.eoms.system.repositoryup.TawLocalRepositoryUp)
	 *      
	 */
	public void saveTawLocalRepositoryUp(final TawLocalRepositoryUp tawLocalRepositoryUp) {
		if ((tawLocalRepositoryUp.getId() == null) || (tawLocalRepositoryUp.getId().equals("")))
			getHibernateTemplate().save(tawLocalRepositoryUp);
		else
			getHibernateTemplate().saveOrUpdate(tawLocalRepositoryUp);
	}

	/**
	 * 
	 * @see com.boco.eoms.system.repositoryup.TawLocalRepositoryUpDao#removeTawLocalRepositoryUps(java.lang.String)
	 *      
	 */
    public void removeTawLocalRepositoryUp(final String id) {
		getHibernateTemplate().delete(getTawLocalRepositoryUp(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		TawLocalRepositoryUp tawLocalRepositoryUp = this.getTawLocalRepositoryUp(id);
		if(tawLocalRepositoryUp==null){
			return "";
		}
		//TODO 请修改代码
		return "";
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.system.repositoryup.TawLocalRepositoryUpDao#getTawLocalRepositoryUps(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getTawLocalRepositoryUps(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "select * from taw_repository_upgrade u where u.repository_id in (select r.id  from taw_repository r where 1=1 ";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				queryStr += " ) order by u.repository_id asc,u.uptime desc ";
				String queryCountStr = "select count(*) from (" + queryStr+")";

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
	
}
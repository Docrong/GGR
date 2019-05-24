package com.boco.eoms.duty.dao.hibernate;

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
import com.boco.eoms.duty.dao.AttemperContrastDao;
import com.boco.eoms.duty.model.AttemperContrast;

/**
 * <p>
 * Title:网调对比表 dao的hibernate实现
 * </p>
 * <p>
 * Description:网调对比表
 * </p>
 * <p>
 * Thu Apr 02 14:11:04 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public class AttemperContrastDaoHibernate extends BaseDaoHibernate implements AttemperContrastDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.duty.AttemperContrastDao#getAttemperContrasts()
	 *      
	 */
	public List getAttemperContrasts() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from AttemperContrast attemperContrast";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.duty.AttemperContrastDao#getAttemperContrast(java.lang.String)
	 *
	 */
	public AttemperContrast getAttemperContrast(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from AttemperContrast attemperContrast where attemperContrast.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (AttemperContrast) result.iterator().next();
				} else {
					return new AttemperContrast();
				}
			}
		};
		return (AttemperContrast) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.duty.AttemperContrastDao#saveAttemperContrasts(com.boco.eoms.duty.AttemperContrast)
	 *      
	 */
	public void saveAttemperContrast(final AttemperContrast attemperContrast) {
		if ((attemperContrast.getId() == null) || (attemperContrast.getId().equals("")))
			getHibernateTemplate().save(attemperContrast);
		else
			getHibernateTemplate().saveOrUpdate(attemperContrast);
	}

	/**
	 * 
	 * @see com.boco.eoms.duty.AttemperContrastDao#removeAttemperContrasts(java.lang.String)
	 *      
	 */
    public void removeAttemperContrast(final String id) {
		getHibernateTemplate().delete(getAttemperContrast(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		AttemperContrast attemperContrast = this.getAttemperContrast(id);
		if(attemperContrast==null){
			return "";
		}
		//TODO 请修改代码
		return attemperContrast.getAttemperId();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.duty.AttemperContrastDao#getAttemperContrasts(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getAttemperContrasts(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from AttemperContrast attemperContrast,Attemper attemper,AttemperSub attemperSub "
					+ " where attemper.id=attemperContrast.attemperId "
					+ " and attemperSub.id= attemperContrast.subAttemperId ";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				
				String queryCountStr = "select count(*) " + queryStr;
				queryStr +=  " order by attemperContrast.beginTime desc ";

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
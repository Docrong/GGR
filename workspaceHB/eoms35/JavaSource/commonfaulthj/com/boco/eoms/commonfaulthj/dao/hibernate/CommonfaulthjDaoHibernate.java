package com.boco.eoms.commonfaulthj.dao.hibernate;

import java.math.BigDecimal;
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
import com.boco.eoms.commonfaulthj.dao.CommonfaulthjDao;
import com.boco.eoms.commonfaulthj.model.Commonfaulthj;

/**
 * <p>
 * Title:commonfaulthj dao的hibernate实现
 * </p>
 * <p>
 * Description:commonfaulthj
 * </p>
 * <p>
 * Thu Dec 18 15:28:05 CST 2014
 * </p>
 * 
 * @author zhoupan
 * @version 3.5
 * 
 */
public class CommonfaulthjDaoHibernate extends BaseDaoHibernate implements CommonfaulthjDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.commonfaulthj.CommonfaulthjDao#getCommonfaulthjs()
	 *      
	 */
	public List getCommonfaulthjs() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Commonfaulthj commonfaulthj";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.commonfaulthj.CommonfaulthjDao#getCommonfaulthj(java.lang.String)
	 *
	 */
	public Commonfaulthj getCommonfaulthj(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Commonfaulthj commonfaulthj where commonfaulthj.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (Commonfaulthj) result.iterator().next();
				} else {
					return new Commonfaulthj();
				}
			}
		};
		return (Commonfaulthj) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.commonfaulthj.CommonfaulthjDao#saveCommonfaulthjs(com.boco.eoms.commonfaulthj.Commonfaulthj)
	 *      
	 */
	public void saveCommonfaulthj(final Commonfaulthj commonfaulthj) {
		if ((commonfaulthj.getId() == null) || (commonfaulthj.getId().equals("")))
			getHibernateTemplate().save(commonfaulthj);
		else
			getHibernateTemplate().saveOrUpdate(commonfaulthj);
	}

	/**
	 * 
	 * @see com.boco.eoms.commonfaulthj.CommonfaulthjDao#removeCommonfaulthjs(java.lang.String)
	 *      
	 */
    public void removeCommonfaulthj(final String id) {
		getHibernateTemplate().delete(getCommonfaulthj(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		Commonfaulthj commonfaulthj = this.getCommonfaulthj(id);
		if(commonfaulthj==null){
			return "";
		}
		return "";
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.commonfaulthj.CommonfaulthjDao#getCommonfaulthjs(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getCommonfaulthjs(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Commonfaulthj commonfaulthj";
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
	
	
	
	public Map getMapList(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "SELECT netname,alarmid,netid,city,subroleid,createtime,createuserid FROM COMMONFAULT_new_ROLE ORDER BY createtime DESC";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) from (" + queryStr+ ")";

				Query totalQuery = session.createSQLQuery(queryCountStr);
				Integer totalCount;
				if (!totalQuery.list().isEmpty()) {
					totalCount = new Integer(((BigDecimal) totalQuery.list().get(0)).intValue());
				} else {
					totalCount = new Integer(0);
				}
				
				Query query = session.createSQLQuery(queryStr);
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				
				ArrayList queryList = new ArrayList();
				String[] elementKey = new String[]{"netname","alarmid","netid","city","subroleid","createtime","createuserid"};
				ArrayList taskList = new ArrayList();
				queryList = (ArrayList)query.list();
				for (int i=0;i<queryList.size();i++) {
					Object[] tmpObjArr = (Object[])queryList.get(i);
					Map tmptaskMap = new HashMap();
					for (int j=0;j<elementKey.length;j++) {
						tmptaskMap.put(elementKey[j], tmpObjArr[j]);
					}
					taskList.add(tmptaskMap);
				}
				
				
				HashMap map = new HashMap();
				map.put("total", totalCount);
				map.put("result", taskList);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
	
}
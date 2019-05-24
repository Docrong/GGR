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
import com.boco.eoms.duty.dao.AttemperSubDao;
import com.boco.eoms.duty.model.AttemperSub;
import com.boco.eoms.workplan.model.TawwpMonthPlan;

/**
 * <p>
 * Title:网调子过程 dao的hibernate实现
 * </p>
 * <p>
 * Description:网调子过程
 * </p>
 * <p>
 * Thu Apr 02 14:11:03 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version EOMS3.5
 * 
 */
public class AttemperSubDaoHibernate extends BaseDaoHibernate implements AttemperSubDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.duty.AttemperSubDao#getAttemperSubs()
	 *      
	 */
	public List getAttemperSubs() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from AttemperSub attemperSub";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 * 
	 * @see com.boco.eoms.duty.AttemperSubDao#getAttemperSubs()
	 *      
	 */
	public List getAttemperSubs(final String attemperId) {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from AttemperSub attemperSub where attemperSub.status not in (3) and attemperId='" + attemperId + "'";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.duty.AttemperSubDao#getAttemperSub(java.lang.String)
	 *
	 */
	public AttemperSub getAttemperSub(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from AttemperSub attemperSub where attemperSub.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (AttemperSub) result.iterator().next();
				} else {
					return new AttemperSub();
				}
			}
		};
		return (AttemperSub) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.duty.AttemperSubDao#saveAttemperSubs(com.boco.eoms.duty.AttemperSub)
	 *      
	 */
	public void saveAttemperSub(final AttemperSub attemperSub) {
		if ((attemperSub.getId() == null) || (attemperSub.getId().equals("")))
			getHibernateTemplate().save(attemperSub);
		else
			getHibernateTemplate().saveOrUpdate(attemperSub);
	}

	/**
	 * 
	 * @see com.boco.eoms.duty.AttemperSubDao#removeAttemperSubs(java.lang.String)
	 *      
	 */
    public void removeAttemperSub(final String id) {
		getHibernateTemplate().delete(getAttemperSub(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		AttemperSub attemperSub = this.getAttemperSub(id);
		if(attemperSub==null){
			return "";
		}
		//TODO 请修改代码
		return attemperSub.getTitle();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.duty.AttemperSubDao#getAttemperSubs(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getAttemperSubs(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from AttemperSub attemperSub";
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
	
    /**
     * 获取网调子过程数量
     * @return String 数量
     */
     public String getNum(String condition){
    	 String hSql ="select count(id) from AttemperSub attemperSub "
				+ "where attemperSub.status not in (3) " + condition + " ";
    	 return getHibernateTemplate().find(hSql).get(0).toString();
     }
     
     /**
      * 批量修改子过程数据
      * @return void
      */
 	public void updateState(final String status,final String attemperId) {
 		getHibernateTemplate().execute(new HibernateCallback() {
 			public Object doInHibernate(Session session) {
 				Query query = session.createQuery(
 						"update AttemperSub set status = :status where attemperId = :attemperId");
 				query.setParameter("status", status);
 				query.setParameter("attemperId", attemperId);
 				query.executeUpdate();
 				return "";
 			}

 		});

 	}
	
}
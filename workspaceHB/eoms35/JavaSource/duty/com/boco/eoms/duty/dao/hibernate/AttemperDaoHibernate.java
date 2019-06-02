package com.boco.eoms.duty.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.duty.dao.AttemperDao;
import com.boco.eoms.duty.model.Attemper;
import com.boco.eoms.workplan.model.TawwpModelPlan;

/**
 * <p>
 * Title:网调信息 dao的hibernate实现
 * </p>
 * <p>
 * Description:网调信息管理
 * </p>
 * <p>
 * Thu Apr 02 14:11:04 CST 2009
 * </p>
 * 
 * @author 李江红
 * @version 3.5
 * 
 */
public class AttemperDaoHibernate extends BaseDaoHibernate implements AttemperDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.duty.AttemperDao#getAttempers()
	 *      
	 */
	public List getAttempers() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Attemper attemper";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.duty.AttemperDao#getAttemper(java.lang.String)
	 *
	 */
	public Attemper getAttemper(final String id) {
		return (Attemper) this.getHibernateTemplate().get(
				Attemper.class, id);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.duty.AttemperDao#saveAttempers(com.boco.eoms.duty.Attemper)
	 *      
	 */
	public void saveAttemper(final Attemper attemper) {
		if ((attemper.getId() == null) || (attemper.getId().equals("")))
			getHibernateTemplate().save(attemper);
		else
			getHibernateTemplate().saveOrUpdate(attemper);
	}

	/**
	 * 
	 * @see com.boco.eoms.duty.AttemperDao#removeAttempers(java.lang.String)
	 *      
	 */
    public void removeAttemper(final String id) {
		getHibernateTemplate().delete(getAttemper(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		Attemper attemper = this.getAttemper(id);
		if(attemper==null){
			return "";
		}
		//TODO 请修改代码
		return attemper.getTitle();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.duty.AttemperDao#getAttempers(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getAttempers(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Attemper attemper";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += " where 1=1 " + whereStr;
				
				String queryCountStr = "select count(*) " + queryStr;	
				queryStr +=  " order by sheetId desc ";
				
				int total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next()).intValue();
				Query query = session.createQuery(queryStr);
				query.setFirstResult(pageSize.intValue()
						* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				query.setFirstResult(pageSize.intValue() * (curPage.intValue()));
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
	 * 
	 * @see com.boco.eoms.duty.AttemperDao#getAttemperAndSubs(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getAttemperAndSubs(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Attemper attemper,AttemperSub attemperSub";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += " where attemperSub.attemperId=attemper.id " + whereStr;
				
				String queryCountStr = "select count(attemperSub.id) " + queryStr;	
				queryStr +=  " order by attemperSub.intendBeginTime desc ";
				
				int total = ((Integer) session.createQuery(queryCountStr).iterate().next()).intValue();
				Query query = session.createQuery(queryStr);
				query.setFirstResult(pageSize.intValue() * (curPage.intValue()));
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
     * 获取网调记录中当天最后一条记录数据
     * @return String 最后一条记录数
     */
     public String getSheetId(String sheetId){
    	 String hSql ="select count(id) from Attemper attemper "
				+ "where sheet_id like('%" + sheetId + "%') ";
    	 return getHibernateTemplate().find(hSql).get(0).toString();
     }
}
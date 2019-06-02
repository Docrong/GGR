package com.boco.eoms.commons.mms.mmsreport.dao.hibernate;

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
import com.boco.eoms.commons.mms.mmsreport.dao.MmsreportDao;
import com.boco.eoms.commons.mms.mmsreport.model.Mmsreport;

/**
 * <p>
 * Title:彩信报实例 dao的hibernate实现
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Wed Feb 18 18:16:20 CST 2009
 * </p>
 * 
 * @author 李振友
 * @version 3.5
 * 
 */
public class MmsreportDaoHibernate extends BaseDaoHibernate implements MmsreportDao,
		ID2NameDAO {
	
    /**
	 * 
	 * @see com.boco.eoms.commons.mms.mmsreport.MmsreportDao#getMmsreports()
	 *      
	 */
	public List getMmsreports() {
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Mmsreport mmsreport";
				Query query = session.createQuery(queryStr);
				return query.list();
			}
		};
		return (List) getHibernateTemplate().execute(callback);		
	}
	
	/**
	 *
	 * @see com.boco.eoms.commons.mms.mmsreport.MmsreportDao#getMmsreport(java.lang.String)
	 *
	 */
	public Mmsreport getMmsreport(final String id) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Mmsreport mmsreport where mmsreport.id=:id";
				Query query = session.createQuery(queryStr);
				query.setString("id", id);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (Mmsreport) result.iterator().next();
				} else {
					return new Mmsreport();
				}
			}
		};
		return (Mmsreport) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 
	 * @see com.boco.eoms.commons.mms.mmsreport.MmsreportDao#saveMmsreports(com.boco.eoms.commons.mms.mmsreport.Mmsreport)
	 *      
	 */
	public void saveMmsreport(final Mmsreport mmsreport) {
		if ((mmsreport.getId() == null) || (mmsreport.getId().equals("")))
			getHibernateTemplate().save(mmsreport);
		else
			getHibernateTemplate().saveOrUpdate(mmsreport);
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.mms.mmsreport.MmsreportDao#removeMmsreports(java.lang.String)
	 *      
	 */
    public void removeMmsreport(final String id) {
		getHibernateTemplate().delete(getMmsreport(id));
	}

	/**
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 *      
	 */
	public String id2Name(String id) throws DictDAOException {
		Mmsreport mmsreport = this.getMmsreport(id);
		if(mmsreport==null){
			return "";
		}
		//TODO 请修改代码
		return "";//mmsreport.yourCode();
	}
 
 	/**
	 * 
	 * @see com.boco.eoms.commons.mms.mmsreport.MmsreportDao#getMmsreports(java.lang.Integer,java.lang.Integer,java.lang.String)
	 *      
	 */ 
	public Map getMmsreports(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from Mmsreport mmsreport ";
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
	
}
package com.boco.eoms.sheet.numberapply.dao.hibernate;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.numberapply.dao.INumberApplyMscDAO;
import com.boco.eoms.sheet.numberapply.model.NumberApplyMscid;

public class NumberApplyMscDAOHibernate extends BaseDaoHibernate implements INumberApplyMscDAO {
	/**
	 * 删除
	 */
	public void delNumberApplyMscid(NumberApplyMscid numberApplyMscid)
			throws HibernateException {
			getHibernateTemplate().delete(numberApplyMscid);
	}
	/**
	 * 根据工单号获得全部MSC的信息
	 */
	public HashMap getAllNumberApplyMscidByMainid(final String mainid,
			final Integer pageSize, final Integer curPage) throws HibernateException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				HashMap map = new HashMap();
				String hql = "from NumberApplyMscid as  agent where (agent.mainid =  '" + mainid + "' )";
			    Integer totalCount;
			    String queryCountStr = "select count(*) c from (select * from  numberapply_mscid    agent where agent.mainid =  '" + mainid + "' )";
			    SQLQuery queryCount = session.createSQLQuery(queryCountStr);
			    queryCount.addScalar("c", Hibernate.INTEGER);
				List list = queryCount.list();
				if (!list.isEmpty()) {
					totalCount = (Integer) list.get(0);
				} else
					totalCount = new Integer(0);
			    
			    Query query = session.createQuery(hql);
//			  分页查询条
				if (pageSize.intValue() != -1) {
					query.setFirstResult(pageSize.intValue()
							* (curPage.intValue()));
					query.setMaxResults(pageSize.intValue());
				}
				 map.put("total", totalCount);
				 map.put("taskList", query.list());
				 return map;
			}
			};
			return (HashMap)getHibernateTemplate().execute(callback);
	}
	/**
	 * 根据id获得信息
	 */
	public NumberApplyMscid getNumberApplyMscid(final String id)
			throws HibernateException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				NumberApplyMscid numberApplyMscid = new NumberApplyMscid();
			    String hql = "from NumberApplyMscid as  agent where (agent.id =  '" + id + "' )";
				Query queryCount = session.createQuery(hql);
				List list=queryCount.list();
				numberApplyMscid = (NumberApplyMscid)list.get(0);
				return numberApplyMscid;
			}
			};
			return (NumberApplyMscid)getHibernateTemplate().execute(callback);
	}
	/**
	 * 保存信息
	 */
	public void saveNumberApplyMscid(NumberApplyMscid numberApplyMscid)
			throws HibernateException {
		this.getHibernateTemplate().saveOrUpdate(numberApplyMscid);

	}

}

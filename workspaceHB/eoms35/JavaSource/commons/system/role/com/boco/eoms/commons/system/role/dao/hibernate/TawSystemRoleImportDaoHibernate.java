package com.boco.eoms.commons.system.role.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.role.model.TawSystemRoleImport;
import com.boco.eoms.commons.system.role.dao.ITawSystemRoleImportDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSystemRoleImportDaoHibernate extends BaseDaoHibernate implements
		ITawSystemRoleImportDao {

	

	/**
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleImportDao#getTawSystemRoleImports(com.boco.eoms.commons.system.role.model.TawSystemRoleImport)
	 */
	public List getTawSystemRoleImports(
			final TawSystemRoleImport tawSystemRoleImport) {
		return getHibernateTemplate().find("from TawSystemRoleImport");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawSystemRoleImport ==
		 * null) { return getHibernateTemplate().find("from
		 * TawSystemRoleImport"); } else { // filter on properties set in the
		 * tawSystemRoleImport HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(tawSystemRoleImport).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(TawSystemRoleImport.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleImportDao#getTawSystemRoleImport(String
	 *      id)
	 */
	public TawSystemRoleImport getTawSystemRoleImport(final String id) {
		TawSystemRoleImport tawSystemRoleImport = (TawSystemRoleImport) getHibernateTemplate()
				.get(TawSystemRoleImport.class, id);
		if (tawSystemRoleImport == null) {
			throw new ObjectRetrievalFailureException(
					TawSystemRoleImport.class, id);
		}

		return tawSystemRoleImport;
	}

	/**
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleImportDao#saveTawSystemRoleImport(TawSystemRoleImport
	 *      tawSystemRoleImport)
	 */
	public void saveTawSystemRoleImport(
			final TawSystemRoleImport tawSystemRoleImport) {
		if ((tawSystemRoleImport.getId() == null)
				|| (tawSystemRoleImport.getId().equals("")))
			getHibernateTemplate().save(tawSystemRoleImport);
		else
			getHibernateTemplate().saveOrUpdate(tawSystemRoleImport);
	}

	/**
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleImportDao#removeTawSystemRoleImport(String
	 *      id)
	 */
	public void removeTawSystemRoleImport(final String id) {
		getHibernateTemplate().delete(getTawSystemRoleImport(id));
	}

	/**
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleImportDao#getTawSystemRoleImports(final
	 *      Integer curPage, final Integer pageSize)
	 */
	public Map getTawSystemRoleImports(final Integer curPage,
			final Integer pageSize) {
		return this.getTawSystemRoleImports(curPage, pageSize, null);
	}

	/**
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleImportDao#getChildList(String
	 *      parentId)
	 */
	public ArrayList getChildList(String parentId) {
		String hql = " from TawSystemRoleImport obj where obj.parentId='"
				+ parentId + "' order by obj.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	/**
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemRoleImportDao#getTawSystemRoleImports(final
	 *      Integer curPage, final Integer pageSize,final String whereStr)
	 */
	public Map getTawSystemRoleImports(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the tawSystemRoleImport
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawSystemRoleImport";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				Integer total = (Integer) session.createQuery(queryCountStr)
						.iterate().next();
				Query query = session.createQuery(queryStr
						+ " order by versionAt desc");
				query
						.setFirstResult(pageSize.intValue()
								* (curPage.intValue()));
				query.setMaxResults(pageSize.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
}

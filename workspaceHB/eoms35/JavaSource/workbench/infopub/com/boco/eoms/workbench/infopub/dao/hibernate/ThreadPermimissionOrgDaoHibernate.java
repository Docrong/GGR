package com.boco.eoms.workbench.infopub.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workbench.infopub.model.ThreadPermimissionOrg;
import com.boco.eoms.workbench.infopub.dao.ThreadPermimissionOrgDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * 
 * <p>
 * Title:记录信息可否操作，组织结构（谁）权限dao的hibernate实现
 * </p>
 * <p>
 * Description:谁可看贴，谁可发贴
 * </p>
 * <p>
 * Date:May 24, 2008 6:02:03 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ThreadPermimissionOrgDaoHibernate extends BaseDaoHibernate
		implements ThreadPermimissionOrgDao {

	/**
	 * @see com.boco.eoms.workbench.infopub.dao.ThreadPermimissionOrgDao#getThreadPermimissionOrgs(com.boco.eoms.workbench.infopub.model.ThreadPermimissionOrg)
	 */
	public List getThreadPermimissionOrgs(
			final ThreadPermimissionOrg threadPermimissionOrg) {
		return getHibernateTemplate().find("from ThreadPermimissionOrg");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (threadPermimissionOrg ==
		 * null) { return getHibernateTemplate().find("from
		 * ThreadPermimissionOrg"); } else { // filter on properties set in the
		 * threadPermimissionOrg HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(threadPermimissionOrg).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(ThreadPermimissionOrg.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.dao.ThreadPermimissionOrgDao#getThreadPermimissionOrg(String
	 *      id)
	 */
	public ThreadPermimissionOrg getThreadPermimissionOrg(final String id) {
		ThreadPermimissionOrg threadPermimissionOrg = (ThreadPermimissionOrg) getHibernateTemplate()
				.get(ThreadPermimissionOrg.class, id);
		if (threadPermimissionOrg == null) {
			throw new ObjectRetrievalFailureException(
					ThreadPermimissionOrg.class, id);
		}

		return threadPermimissionOrg;
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.dao.ThreadPermimissionOrgDao#saveThreadPermimissionOrg(ThreadPermimissionOrg
	 *      threadPermimissionOrg)
	 */
	public void saveThreadPermimissionOrg(
			final ThreadPermimissionOrg threadPermimissionOrg) {
		if ((threadPermimissionOrg.getId() == null)
				|| (threadPermimissionOrg.getId().equals("")))
			getHibernateTemplate().save(threadPermimissionOrg);
		else
			getHibernateTemplate().saveOrUpdate(threadPermimissionOrg);
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.dao.ThreadPermimissionOrgDao#removeThreadPermimissionOrg(String
	 *      id)
	 */
	public void removeThreadPermimissionOrg(final String id) {
		getHibernateTemplate().delete(getThreadPermimissionOrg(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.dao.ThreadPermimissionOrgDao#removeThreadPermimissionOrg(com.boco.eoms.workbench.infopub.model.ThreadPermimissionOrg)
	 */
	public void removeThreadPermimissionOrg(
			ThreadPermimissionOrg permimissionOrg) {
		getHibernateTemplate().delete(permimissionOrg);

	}

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ�� whereStr
	 * where�������䣬������"where"��ͷ,����Ϊ��
	 */
	public Map getThreadPermimissionOrgs(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the threadPermimissionOrg
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from ThreadPermimissionOrg";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				Integer total = ((Integer) session.createQuery(queryCountStr)
						.iterate().next());
				Query query = session.createQuery(queryStr);
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

	public Map getThreadPermimissionOrgs(final Integer curPage,
			final Integer pageSize) {
		return this.getThreadPermimissionOrgs(curPage, pageSize, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.dao.ThreadPermimissionOrgDao#getThreadPermissionOrgsByThreadId(java.lang.String)
	 */
	public List getThreadPermissionOrgsByThreadId(String threadId) {
		return (List) getHibernateTemplate().find(
				"from ThreadPermimissionOrg t where t.threadId='" + threadId
						+ "'");
	}

}

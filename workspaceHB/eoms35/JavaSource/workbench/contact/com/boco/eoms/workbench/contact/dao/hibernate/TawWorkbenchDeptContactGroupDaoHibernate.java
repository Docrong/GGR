package com.boco.eoms.workbench.contact.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.workbench.contact.dao.TawWorkbenchDeptContactGroupDao;
import com.boco.eoms.workbench.contact.model.TawWorkbenchDeptContactGroup;

/**
 * <p>
 * Title:部门通讯录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 30, 2008 20:30:30 PM
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 * 
 */
public class TawWorkbenchDeptContactGroupDaoHibernate extends BaseDaoHibernate
		implements TawWorkbenchDeptContactGroupDao {

	/**
	 * @see com.boco.eoms.workbench.contact.dao.TawWorkbenchContactGroupDao#getTawWorkbenchContactGroups(com.boco.eoms.workbench.contact.model.TawWorkbenchContactGroup)
	 */
	public List getTawWorkbenchDeptContactGroups(
			final TawWorkbenchDeptContactGroup tawWorkbenchDeptContactGroup) {
		return getHibernateTemplate().find("from TawWorkbenchDeptContactGroup");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if
		 * (tawWorkbenchDeptContactGroup == null) { return
		 * getHibernateTemplate().find("from TawWorkbenchDeptContactGroup"); }
		 * else { // filter on properties set in the
		 * tawWorkbenchDeptContactGroup HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(tawWorkbenchDeptContactGroup).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(TawWorkbenchDeptContactGroup.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.workbench.contact.dao.TawWorkbenchContactGroupDao#getTawWorkbenchContactGroup(String
	 *      id)
	 */
	public TawWorkbenchDeptContactGroup getTawWorkbenchDeptContactGroup(
			final String id) {
		TawWorkbenchDeptContactGroup tawWorkbenchDeptContactGroup = (TawWorkbenchDeptContactGroup) getHibernateTemplate()
				.get(TawWorkbenchDeptContactGroup.class, id);
		if (tawWorkbenchDeptContactGroup == null) {
			throw new ObjectRetrievalFailureException(
					TawWorkbenchDeptContactGroup.class, id);
		}

		return tawWorkbenchDeptContactGroup;
	}

	/**
	 * @see com.boco.eoms.workbench.contact.dao.TawWorkbenchContactGroupDao#saveTawWorkbenchContactGroup(TawWorkbenchDeptContactGroup
	 *      tawWorkbenchDeptContactGroup)
	 */
	public void saveTawWorkbenchDeptContactGroup(
			final TawWorkbenchDeptContactGroup tawWorkbenchDeptContactGroup) {
		if ((tawWorkbenchDeptContactGroup.getId() == null)
				|| (tawWorkbenchDeptContactGroup.getId().equals("")))
			getHibernateTemplate().save(tawWorkbenchDeptContactGroup);
		else
			getHibernateTemplate().saveOrUpdate(tawWorkbenchDeptContactGroup);
	}

	/**
	 * @see com.boco.eoms.workbench.contact.dao.TawWorkbenchContactGroupDao#removeTawWorkbenchContactGroup(String
	 *      id)
	 */
	public void removeTawWorkbenchDeptContactGroup(final String id) {
		getHibernateTemplate().delete(getTawWorkbenchDeptContactGroup(id));
	}

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ�� whereStr
	 * where�������䣬������"where"��ͷ,����Ϊ��
	 */
	public Map getTawWorkbenchDeptContactGroups(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the tawWorkbenchDeptContactGroup
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawWorkbenchDeptContactGroup";
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
				map.put("total", total + "");
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}

	public Map getTawWorkbenchDeptContactGroups(final Integer curPage,
			final Integer pageSize) {
		return this.getTawWorkbenchDeptContactGroups(curPage, pageSize, null);
	}

	/**
	 * 查询下一级信�?
	 * 
	 * @param parentdictid
	 * @return
	 */
	public List getSonsById(String parentid) {
		String hql = " from TawWorkbenchDeptContactGroup work where work.parentId='"
				+ parentid + "' order by work.name";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	public List getNextLevecGroups(String nodid, String deptId, String deleted) {
		String hql = "";

		if (nodid.equals("-1")) {
			// hql = " from TawWorkbenchDeptContactGroup work where
			// work.userId='"
			// + user_id + "' and work.deleted = '"+deleted+"' order by
			// work.id";
			hql = "select g from TawWorkbenchDeptContactGroup g, TawSystemDept d where g.deptId=d.deptId and g.deleted='"
					+ deleted
					+ "' and ( d.deptId='"
					+ deptId
					+ "' or d.parentDeptid='"
					+ deptId
					+ "'  or  (d.deptId=(select parentDeptid from TawSystemDept where deptId='"
					+ deptId + "') and g.allow='true' ))";
			// hql = "from TawWorkbenchDeptContactGroup work where
			// work.deleted='"+deleted+"' and work.deptId in (select deptId from
			// TawSystemDept where deptId='"+deptId+"' or
			// parentDeptid='"+deptId+"')";
		} else {
			hql = "from TawWorkbenchDeptContact contact where contact.deleted='"
					+ deleted + "' and contact.groupId='" + nodid + "'";
		}
		return (ArrayList) getHibernateTemplate().find(hql);

	}

	// 得到最大数
	public int getMaxGroupId() {
		Session session = getSession();
		String hql = " select count(*) from TawWorkbenchDeptContactGroup work ";
		int groupid = ((Integer) session.createQuery(hql).iterate().next())
				.intValue();
		return groupid + 1;

	}

	public TawWorkbenchDeptContactGroup getTawWorkbenchDeptContactGroupById(
			final String id) {

		String hql = " from TawWorkbenchDeptContactGroup work where work.groupId='"
				+ id + "'";
		TawWorkbenchDeptContactGroup tawWorkbenchDeptContactGroup = (TawWorkbenchDeptContactGroup) getHibernateTemplate()
				.find(hql).iterator().next();
		return tawWorkbenchDeptContactGroup;

	}

	public List getOwnerGroup(String nodid, String userId, String deleted) {
		String hql = "";

		if (nodid.equals("-1")) {
			hql = " from TawWorkbenchDeptContactGroup work where work.userId='"
					+ userId + "' and work.deleted = '" + deleted
					+ "' order by work.id";

		}
		return (ArrayList) getHibernateTemplate().find(hql);
	}


}

package com.boco.eoms.commons.system.priv.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuDao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivMenu;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;

public class TawSystemPrivMenuDaoHibernate extends BaseDaoHibernate implements
		TawSystemPrivMenuDao {

	/**
	 * 部门mgr
	 */
	private ITawSystemDeptManager tawSystemDeptManager;

	/**
	 * @param tawSystemDeptManager
	 *            the tawSystemDeptManager to set
	 */
	public void setTawSystemDeptManager(
			ITawSystemDeptManager tawSystemDeptManager) {
		this.tawSystemDeptManager = tawSystemDeptManager;
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuDao#getTawSystemPrivMenus(com.boco.eoms.commons.system.priv.model.TawSystemPrivMenu)
	 */
	public List getTawSystemPrivMenus(final TawSystemPrivMenu tawSystemPrivMenu) {
		return getHibernateTemplate().find("from TawSystemPrivMenu");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawSystemPrivMenu == null) {
		 * return getHibernateTemplate().find("from TawSystemPrivMenu"); } else { //
		 * filter on properties set in the tawSystemPrivMenu HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex =
		 * Example.create(tawSystemPrivMenu).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(TawSystemPrivMenu.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuDao#getTawSystemPrivMenu(String
	 *      id)
	 */
	public TawSystemPrivMenu getTawSystemPrivMenu(final String privid) {
		TawSystemPrivMenu tawSystemPrivMenu = (TawSystemPrivMenu) getHibernateTemplate()
				.get(TawSystemPrivMenu.class, privid);
		if (tawSystemPrivMenu == null) {
			throw new ObjectRetrievalFailureException(TawSystemPrivMenu.class,
					privid);
		}

		return tawSystemPrivMenu;
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuDao#saveTawSystemPrivMenu(TawSystemPrivMenu
	 *      tawSystemPrivMenu)
	 */
	public void saveTawSystemPrivMenu(final TawSystemPrivMenu tawSystemPrivMenu) {
		if ((tawSystemPrivMenu.getPrivid() == null)
				|| (tawSystemPrivMenu.getPrivid().equals("")))
			getHibernateTemplate().save(tawSystemPrivMenu);
		else
			getHibernateTemplate().saveOrUpdate(tawSystemPrivMenu);
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuDao#removeTawSystemPrivMenu(String
	 *      id)
	 */
	public void removeTawSystemPrivMenu(final String id) {
		getHibernateTemplate().delete(getTawSystemPrivMenu(id));
	}

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ�� whereStr
	 * where�������䣬������"where"��ͷ,����Ϊ��
	 */
	public Map getTawSystemPrivMenus(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the tawSystemPrivMenu
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawSystemPrivMenu";
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

	public Map getTawSystemPrivMenus(final Integer curPage,
			final Integer pageSize) {
		return this.getTawSystemPrivMenus(curPage, pageSize, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivAssignDao#listModuleMenu(java.lang.String,
	 *      java.lang.String, java.util.List)
	 */
	public List listMenu(String userId, String deptId, List roleIds) {
		String hql = "select distinct menu from TawSystemPrivAssign assign,TawSystemPrivMenu menu where assign.privid=menu.privid and ((assign.objectid='"
				+ userId
				+ "' and assign.assigntype='"
				+ StaticVariable.PRIV_ASSIGNTYPE_USER + "')";
		// 该用户有所属部门
		if (deptId != null && !"".equals(deptId.toString())) {
			deptId = tawSystemDeptManager.deptId2deptIds(deptId);
			hql += " or assign.objectid in (" + deptId
					+ ") and assign.assigntype='"
					+ StaticVariable.PRIV_ASSIGNTYPE_DEPT + "'";
		}
		// 该用户有角色
		if (roleIds != null && !roleIds.isEmpty()) {
			String roleHql = "";
			// 遍历角色列表
			for (Iterator it = roleIds.iterator(); it.hasNext();) {
				TawSystemSubRole role = (TawSystemSubRole) it.next();
				roleHql += " assign.objectid='" + role.getId() + "' or";
			}
			// 去掉末尾的"or"
			roleHql = StaticMethod.removeLastStr(roleHql, "or");
   
			// if (roleHql.endsWith("or")) {
			// roleHql = roleHql
			// .substring(0, roleHql.length() - "or".length());
			// }
			hql += " or (" + roleHql + " and assign.assigntype='"
					+ StaticVariable.PRIV_ASSIGNTYPE_ROLE + "')";
		}
		hql+=")";
		return getHibernateTemplate().find(hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivAssignDao#listModuleMenu(java.lang.String,
	 *      java.lang.String, java.util.List)
	 */
	public List listMenu(String userId, String deptId, List roleIds,
			String privid) {
		String hql = "select distinct menu from TawSystemPrivAssign assign,TawSystemPrivMenu menu where assign.privid=menu.privid and ((assign.objectid='"
				+ userId
				+ "'  and assign.assigntype='"
				+ StaticVariable.PRIV_ASSIGNTYPE_USER + "')";
		// 该用户有所属部门
		if (deptId != null && !"".equals(deptId.toString())) {
			deptId = tawSystemDeptManager.deptId2deptIds(deptId);
			hql += " or assign.objectid in (" + deptId
					+ ") and assign.assigntype='"
					+ StaticVariable.PRIV_ASSIGNTYPE_DEPT + "'";
		}
		// 该用户有角色
		if (roleIds != null && !roleIds.isEmpty()) {
			String roleHql = "";
			// 遍历角色列表
			for (Iterator it = roleIds.iterator(); it.hasNext();) {
				TawSystemSubRole role = (TawSystemSubRole) it.next();
				roleHql += " assign.objectid='" + role.getId() + "' or";
			}
			// 去掉末尾的"or"
			roleHql = StaticMethod.removeLastStr(roleHql, "or");

			// if (roleHql.endsWith("or")) {
			// roleHql = roleHql
			// .substring(0, roleHql.length() - "or".length());
			// }
			hql += " or (" + roleHql + " and assign.assigntype='"
					+ StaticVariable.PRIV_ASSIGNTYPE_ROLE + "')";
		}
		hql += ") and assign.privid = '" + privid+"'";

		final String resultHql = hql;
		// filter on properties set in the tawSystemPrivMenu
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {

				Query query = session.createQuery(resultHql);
				query.setFirstResult(0);
				query.setMaxResults(1);
				return query.list();

			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivMenuDao#listMenu(java.lang.String,
	 *      java.lang.String)
	 */
	public List listMenu(String orgId, String orgType) {
		String hql = "select distinct menu from TawSystemPrivAssign assign,TawSystemPrivMenu menu where assign.privid=menu.privid ";
		if (StaticVariable.PRIV_ASSIGNTYPE_ROLE.equals(orgType)) {
			if (orgId == null || "".equals(orgId.trim())) {
				return new ArrayList();
			}
			String roleIds[] = orgId.split(",");
			String roleHql = "";
			// 拼接多个角色ID
			for (int i = 0; i < roleIds.length; i++) {
				roleHql += " assign.objectid='" + roleIds[i] + "' or";
			}
			if (roleHql.endsWith("or")) {
				roleHql = roleHql
						.substring(0, roleHql.length() - "or".length());
			}
			hql += " and (" + roleHql + ") and assign.assigntype='" + orgType
					+ "'";
			return this.getHibernateTemplate().find(hql);
		} else if (StaticVariable.PRIV_ASSIGNTYPE_DEPT.equals(orgType)) {
			// 该用户有所属部门
			if (orgId != null && !"".equals(orgId.toString())) {
				String deptId = tawSystemDeptManager.deptId2deptIds(orgId);
				hql += " and assign.objectid in (" + deptId
						+ ") and assign.assigntype='"
						+ StaticVariable.PRIV_ASSIGNTYPE_DEPT + "'";
			}
			return this.getHibernateTemplate().find(hql);
		}
		hql += " and assign.objectid='" + orgId + "' and assign.assigntype='"
				+ orgType + "'";
		return this.getHibernateTemplate().find(hql);
	}
}

package com.boco.eoms.commons.system.priv.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign;
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivAssignDao;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSystemPrivAssignDaoHibernate extends BaseDaoHibernate implements
		TawSystemPrivAssignDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivAssignDao#listPirv(java.lang.String)
	 */
	public List listAssign(String url) {

		String hql = "select distinct assign from TawSystemPrivAssign assign,TawSystemPrivOperation operation,TawSystemPrivMenuItem menuitem,TawSystemPrivMenu menu where operation.deleted='"
				+ Constants.NOT_DELETED_FLAG
				+ "' and operation.url='"
				+ url
				+ "' and menuitem.code=operation.code and menuitem.menuid=menu.privid and assign.privid=menu.privid";
		return getHibernateTemplate().find(hql);
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivAssignDao#getTawSystemPrivAssigns(com.boco.eoms.commons.system.priv.model.TawSystemPrivAssign)
	 */
	public List getTawSystemPrivAssigns(
			final TawSystemPrivAssign tawSystemPrivAssign) {
		return getHibernateTemplate().find("from TawSystemPrivAssign");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawSystemPrivAssign ==
		 * null) { return getHibernateTemplate().find("from
		 * TawSystemPrivAssign"); } else { // filter on properties set in the
		 * tawSystemPrivAssign HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(tawSystemPrivAssign).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(TawSystemPrivAssign.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivAssignDao#getTawSystemPrivAssign(String
	 *      id)
	 */
	public TawSystemPrivAssign getTawSystemPrivAssign(final String id) {
		TawSystemPrivAssign tawSystemPrivAssign = (TawSystemPrivAssign) getHibernateTemplate()
				.get(TawSystemPrivAssign.class, id);
		if (tawSystemPrivAssign == null) {
			throw new ObjectRetrievalFailureException(
					TawSystemPrivAssign.class, id);
		}

		return tawSystemPrivAssign;
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivAssignDao#saveTawSystemPrivAssign(TawSystemPrivAssign
	 *      tawSystemPrivAssign)
	 */
	public void saveTawSystemPrivAssign(
			final TawSystemPrivAssign tawSystemPrivAssign) {
		if ((tawSystemPrivAssign.getId() == null)
				|| (tawSystemPrivAssign.getId().equals("")))
			getHibernateTemplate().save(tawSystemPrivAssign);
		else
			getHibernateTemplate().saveOrUpdate(tawSystemPrivAssign);
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivAssignDao#removeTawSystemPrivAssign(String
	 *      id)
	 */
	public void removeTawSystemPrivAssign(final String id) {
		getHibernateTemplate().delete(getTawSystemPrivAssign(id));
	}

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ�� whereStr
	 * where�������䣬������"where"��ͷ,����Ϊ��
	 */
	public Map getTawSystemPrivAssigns(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the tawSystemPrivAssign
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawSystemPrivAssign";
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

	public Map getTawSystemPrivAssigns(final Integer curPage,
			final Integer pageSize) {
		return this.getTawSystemPrivAssigns(curPage, pageSize, null);
	}

	/**
	 * 查询某用户分配的权限信息
	 * 
	 * @param operuserid
	 * @return
	 */
	public List getUserCreatePrivs(final String operuserid) {
		String hql = " from TawSystemPrivAssign privassign where privassign.operuserid='"
				+ operuserid + "'";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 查询OBJECT 对应的权限
	 * 
	 * @param objectid
	 * @return
	 */
	public List getObjectPriv(final String objectid) {
		String hql = " from TawSystemPrivAssign privassign where privassign.objectid='"
				+ objectid + "'";
		return (List) getHibernateTemplate().find(hql);

	}

	/**
	 * 查询某权限方案被分配的情况
	 * 
	 * @return
	 */
	public List getPrivassigninfos(final String privid) {
		String hql = " from TawSystemPrivAssign privassign where privassign.privid='"
				+ privid + "'";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 判断某GROUP是否已经被分配权限
	 * 
	 * @param userid
	 * @return
	 */
	public boolean hasAssign(String objectid) {
		boolean hasAsg = false;
		List list = null;
		String hql = "  from TawSystemPrivAssign assign where assign.objectid='"
				+ objectid + "'";
		list = (ArrayList) getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			hasAsg = true;
		}

		return hasAsg;
	}

	/**
	 * 判断某菜单方案是否被分配过
	 * 
	 * @param userid
	 * @return
	 */
	public boolean isMenuHasAssign(String menuid) {
		boolean hasAsg = false;
		List list = null;
		String hql = "  from TawSystemPrivAssign assign where assign.privid='"
				+ menuid + "'";
		list = (ArrayList) getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			hasAsg = true;
		}

		return hasAsg;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivAssignDao#getAssignsByUrl(java.lang.String)
	 */
	public List getAssignsByUrl(String url) {
		return getHibernateTemplate()
				.find(
						"select distinct(a) from TawSystemPrivUserAssign u,TawSystemPrivAssign a where u.url='"
								+ url + "' and u.menuid=a.privid");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivAssignDao#getTawSystemPrivAssign(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public TawSystemPrivAssign getTawSystemPrivAssign(final String orgId,
			final String type, final String menuId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawSystemPrivAssign t where t.objectid='"
						+ orgId + "' and t.assigntype=:type and privid=:menuId";
				if (StaticVariable.PRIV_ASSIGNTYPE_ROLE.equals(type)) {
					String roleCondition = "";
					if (orgId == null || "".equals(orgId.trim())) {
						return new ArrayList();
					}
					String roleIds[] = orgId.split(",");
					for (int i = 0; i < roleIds.length; i++) {
						roleCondition += "'" + roleIds[i] + "',";
					}
					// 去最后逗号
					if (roleCondition.endsWith(",")) {
						roleCondition = roleCondition.substring(0,
								roleCondition.length() - ",".length());
					}
					queryStr = "from TawSystemPrivAssign t where t.objectid in("
							+ roleCondition
							+ ") and t.assigntype=:type and privid=:menuId";
				}

				Query query = session.createQuery(queryStr);
				query.setString("type", type);
				query.setString("menuId", menuId);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (TawSystemPrivAssign) result.iterator().next();
				}
				return new TawSystemPrivAssign();
			}
		};
		return (TawSystemPrivAssign) getHibernateTemplate().execute(callback);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivAssignDao#listModuleMenu(java.lang.String,
	 *      java.lang.String, java.util.List)
	 */
	public List listMenu(String userId, String deptId, List roleIds) {
		String hql = "select distinct t from TawSystemPrivAssign t where (t.objectid='"
				+ userId
				+ "' and t.assigntype='"
				+ StaticVariable.PRIV_ASSIGNTYPE_USER + "')";
		// 该用户有所属部门
		if (deptId != null && !"".equals(deptId.toString())) {
			hql += " or (t.objectid='" + deptId + "' and t.assigntype='"
					+ StaticVariable.PRIV_ASSIGNTYPE_DEPT + "')";
		}
		// 该用户有角色
		if (roleIds != null && !roleIds.isEmpty()) {
			String roleHql = "";
			// 遍历角色列表
			for (Iterator it = roleIds.iterator(); it.hasNext();) {
				TawSystemSubRole role = (TawSystemSubRole) it.next();
				roleHql += " t.objectid='" + role.getId() + "' or";
			}
			// 去掉末尾的"or"
			if (roleHql.endsWith("or")) {
				roleHql = roleHql
						.substring(0, roleHql.length() - "or".length());
			}
			hql += " or (" + roleHql + " and t.assigntype='"
					+ StaticVariable.PRIV_ASSIGNTYPE_ROLE + "')";
		}
		return getHibernateTemplate().find(hql);
	}
}

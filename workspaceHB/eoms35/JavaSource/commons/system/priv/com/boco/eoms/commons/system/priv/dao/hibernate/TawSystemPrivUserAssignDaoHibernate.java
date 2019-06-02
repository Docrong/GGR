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
import com.boco.eoms.commons.system.priv.dao.TawSystemPrivUserAssignDao;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation;
import com.boco.eoms.commons.system.priv.model.TawSystemPrivUserAssign;

public class TawSystemPrivUserAssignDaoHibernate extends BaseDaoHibernate
		implements TawSystemPrivUserAssignDao {

	/**
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivUserAssignDao#getTawSystemPrivUserAssigns(com.boco.eoms.commons.system.priv.model.TawSystemPrivUserAssign)
	 */
	public List getTawSystemPrivUserAssigns(
			final TawSystemPrivUserAssign tawSystemPrivUserAssign) {
		return getHibernateTemplate().find("from TawSystemPrivUserAssign");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawSystemPrivUserAssign ==
		 * null) { return getHibernateTemplate().find("from
		 * TawSystemPrivUserAssign"); } else { // filter on properties set in
		 * the tawSystemPrivUserAssign HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(tawSystemPrivUserAssign).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(TawSystemPrivUserAssign.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivUserAssignDao#getTawSystemPrivUserAssign(String
	 *      id)
	 */
	public TawSystemPrivUserAssign getTawSystemPrivUserAssign(final String id) {
		TawSystemPrivUserAssign tawSystemPrivUserAssign = (TawSystemPrivUserAssign) getHibernateTemplate()
				.get(TawSystemPrivUserAssign.class, id);
		if (tawSystemPrivUserAssign == null) {
			throw new ObjectRetrievalFailureException(
					TawSystemPrivUserAssign.class, id);
		}

		return tawSystemPrivUserAssign;
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivUserAssignDao#saveTawSystemPrivUserAssign(TawSystemPrivUserAssign
	 *      tawSystemPrivUserAssign)
	 */
	public void saveTawSystemPrivUserAssign(
			final TawSystemPrivUserAssign tawSystemPrivUserAssign) {
		if ((tawSystemPrivUserAssign.getId() == null)
				|| (tawSystemPrivUserAssign.getId().equals("")))
			getHibernateTemplate().save(tawSystemPrivUserAssign);
		else
			getHibernateTemplate().saveOrUpdate(tawSystemPrivUserAssign);
	}

	/**
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivUserAssignDao#removeTawSystemPrivUserAssign(String
	 *      id)
	 */
	public void removeTawSystemPrivUserAssign(final String id) {
		getHibernateTemplate().delete(getTawSystemPrivUserAssign(id));
	}

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ�� whereStr
	 * where�������䣬������"where"��ͷ,����Ϊ��
	 */
	public Map getTawSystemPrivUserAssigns(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the tawSystemPrivUserAssign
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawSystemPrivUserAssign";
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

	public Map getTawSystemPrivUserAssigns(final Integer curPage,
			final Integer pageSize) {
		return this.getTawSystemPrivUserAssigns(curPage, pageSize, null);
	}

	/**
	 * 根据用户ID获取用户当前的权限
	 * 
	 * @param userid
	 * @return
	 */
	public List getPrivUserMenu(final String userid) {
		List list = new ArrayList();
		String hsql = " from TawSystemPrivUserAssign userassign where userassign.userid='"
				+ userid + "'";
		list = (ArrayList) getHibernateTemplate().find(hsql);
		return list;
	}

	/**
	 * 根据用户ID 一级菜单标志获取一级菜单
	 * 
	 * @param userid
	 * @param isone
	 * @return
	 */
	public List getOnelevMents(final String userid, final String isone) {
		List list = new ArrayList();
		String hsql = "  from TawSystemPrivUserAssign userassign where userassign.userid='"
				+ userid
				+ "'"
				+ " and userassign.isonepriv="
				+ Integer.valueOf(isone)
				+ " and userassign.hide='"
				+ new Integer(0) + "' order by userassign.orderby ";
		list = (ArrayList) getHibernateTemplate().find(hsql);
		return list;
	}

	/**
	 * 查询某用户对应的权限信息
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public TawSystemPrivUserAssign getUserToPirvInfo(final String userid,
			final String menuid) {
		List list = new ArrayList();
		String hsql = " from TawSystemPrivUserAssign userassign where userassign.userid='"
				+ userid
				+ "'"
				+ " and userassign.currentprivid='"
				+ menuid
				+ "'";
		TawSystemPrivUserAssign userassign = null;
		list = (ArrayList) getHibernateTemplate().find(hsql);
		if (list != null) {
			if (list.size() > 0) {
				userassign = new TawSystemPrivUserAssign();
				userassign = (TawSystemPrivUserAssign) list.get(0);
			}
		}
		return userassign;
	}

	/**
	 * 根据用户ID 父菜单ID 获取上一级的菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List getSuperiorMenus(final String userid, final String fmenuid) {
		List list = new ArrayList();
		String hsql = " from TawSystemPrivUserAssign userassign where userassign.userid='"
				+ userid
				+ "'"
				+ " and userassign.currentprivid='"
				+ fmenuid
				+ "'";
		list = (ArrayList) getHibernateTemplate().find(hsql);
		return list;
	}

	/**
	 * 根据用户ID 菜单ID 获取当前菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public TawSystemPrivUserAssign getTawSystemUserAssign(final String userid,
			final String privid) {

		String hsql = " from TawSystemPrivUserAssign userassign where userassign.userid='"
				+ userid
				+ "'"
				+ " and userassign.currentprivid='"
				+ privid
				+ "'";

		return (TawSystemPrivUserAssign) getHibernateTemplate().find(hsql).get(
				0);
	}

	/**
	 * 根据用户ID 菜单ID 获取下一级的菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List getLowerleveMenus(final String userid, final String menuid) {
		List list = new ArrayList();
		String hsql = " from TawSystemPrivUserAssign userassign where userassign.userid='"
				+ userid
				+ "'"
				+ " and userassign.parentprivid='"
				+ menuid
				+ "' and userassign.hide=" + new Integer(0);
		list = (ArrayList) getHibernateTemplate().find(hsql);
		return list;
	}

	/**
	 * 根据用户ID 菜单ID 父权限ID 获取下一级的菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List getNextUserPrivMenus(final String menuid, final String userid,
			String parentprivid) {
		List list = new ArrayList();
		String hsql = " from TawSystemPrivUserAssign userassign where userassign.userid='"
				+ userid
				+ "'"
				+ " and userassign.parentprivid='"
				+ parentprivid
				+ "' and userassign.menuid='"
				+ menuid
				+ "' and userassign.hide=" + new Integer(0);
		list = (ArrayList) getHibernateTemplate().find(hsql);
		return list;
	}

	/**
	 * 根据用户ID 菜单ID 获取菜单的所有子菜单
	 * 
	 * @param userid
	 * @param menutid
	 * @return
	 */
	public List getSonMenus(final String userid, final String menutid) {
		List list = new ArrayList();
		String hsql = " from TawSystemPrivUserAssign userassign where userassign.userid='"
				+ userid
				+ "'"
				+ " and userassign.parentprivid like '"
				+ menutid + "%'";
		list = (ArrayList) getHibernateTemplate().find(hsql);
		return list;
	}

	/**
	 * 根据用户ID 菜单ID 获取某菜单的所有父菜单
	 * 
	 * @param userid
	 * @param menuid
	 * @return
	 */
	public List getFatherMenus(final String userid, final String menuid) {
		String privid = menuid.substring(0, 2);
		List list = new ArrayList();
		String hsql = " from TawSystemPrivUserAssign userassign where userassign.userid='"
				+ userid
				+ "'"
				+ " and userassign.currentprivid like '"
				+ privid + "%'";
		list = (ArrayList) getHibernateTemplate().find(hsql);
		return list;
	}

	/**
	 * IN查询某用户的一些权限
	 * 
	 * @param userid
	 * @param menuids
	 * @return
	 */
	public List getUserMenuInserachs(final String userid, final String menuids) {
		List list = new ArrayList();
		String hql = "  from TawSystemPrivUserAssign userassign where userssign.currentprivid in("
				+ menuids + ")" + " and userassign.userid='" + userid + "'";
		list = (ArrayList) getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 判断某用户是否已经被分配权限
	 * 
	 * @param userid
	 * @return
	 */
	public boolean isExitsUserassign(String userid) {
		boolean flag = false;

		List list = new ArrayList();
		String hql = "  from TawSystemPrivUserAssign userassign where userassign.userid='"
				+ userid + "'";
		list = (ArrayList) getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 查询某菜单方案被分配给的用户
	 * 
	 * @param menuid
	 */
	public List getUserAssignByMenuid(String menuid) {

		String hql = " from TawSystemPrivUserAssign userassign where userassign.menuid='"
				+ menuid + "'";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	/**
	 * 查询某菜单方案的某菜单项的父节点
	 * 
	 * @return
	 */
	public List getFUserAssignByMenuidAndPrivid(String menuid, String code) {

		String hql = "";
		if (code.equals("-1")) {
			hql = " from TawSystemPrivUserAssign userassign where userassign.menuid='"
					+ menuid + "' and userassign.parentprivid='" + code + "'";
		} else {
			hql = " from TawSystemPrivUserAssign userassign where userassign.menuid='"
					+ menuid + "' and userassign.currentprivid='" + code + "'";
		}
		return (ArrayList) getHibernateTemplate().find(hql);

	}

	/**
	 * 查询是否有此url 目的用来判断按钮的url
	 * 
	 * @param url
	 * @return
	 */
	public boolean isHaveUrl(String url, String userid) {
		boolean flag = false;
		String hql = " from TawSystemPrivUserAssign userassign where userassign.url='"
				+ url + "' and userassign.userid='" + userid + "'";

		List list = getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 查询是否有此url 目的用来判断按钮的url
	 * 
	 * @param url
	 * @return
	 */
	public boolean isHaveUrl(String url, String userid, String urltype) {
		boolean flag = false;
		String hql = " from TawSystemPrivUserAssign userassign where userassign.url='"
				+ url
				+ "' and userassign.userid='"
				+ userid
				+ "' and userassign.isapp='" + urltype + "'";

		List list = getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 判断某菜单项是否已经被分配
	 * 
	 * @param privid
	 * @return
	 */
	public boolean isExitsPrivid(String privid) {
		boolean flag = false;
		String hql = " from TawSystemPrivUserAssign userassign where userassign.currentprivid='"
				+ privid + "'";
		List list = getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 更加菜单ID 和USERID 判断某用户是否拥有某功能项的权限
	 * 
	 * @param userid
	 * @param code
	 * @return
	 */
	public boolean hasCode(String userid, String code) {
		boolean flag = false;
		String hql = " from TawSystemPrivUserAssign userassign where userassign.currentprivid='"
				+ code + "' and userassign.userid='" + userid + "'";
		List list = getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 根据具体的菜单项获取人员的集合
	 * 
	 * @return
	 */
	public List getUserAssignByPrivid(String privId) {

		String hql = "";
		hql = " from TawSystemPrivUserAssign userassign where   userassign.currentprivid='"
				+ privId + "'";

		return (ArrayList) getHibernateTemplate().find(hql);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivUserAssignDao#getAssignsByUrl(java.lang.String)
	 */
	public List getAssignsByUrl(String url) {
		String hql = "select u from TawSystemPrivUserAssign p,TawSystemUser u where p.userid=u.userid and p.url='"
				+ url + "'";
		return getHibernateTemplate().find(hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivUserAssignDao#getAssignsByConditions(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public List getAssignsByConditions(String parentprivid, String userId,
			String hide) {
		String sql = "from TawSystemPrivUserAssign " + "where parentprivid='"
				+ parentprivid + "' and userid='" + userId + "' and hide="
				+ hide + " and currentprivname!='' order by orderby";
		//菜单可能会出现重复的情况 edit by liqiuye 20080901
		List list = getHibernateTemplate().find(sql);
		List noRepeatList = new ArrayList();
		HashMap hm = new HashMap();
		for(Iterator it = list.iterator(); it.hasNext();){
			TawSystemPrivUserAssign privUserAssign = (TawSystemPrivUserAssign)it.next();
			if (null == hm.get(privUserAssign.getCurrentprivid())) {
				hm.put(privUserAssign.getCurrentprivid(), privUserAssign);
				noRepeatList.add(privUserAssign);
			}
		}
		return noRepeatList;
	}

	/**
	 * 修改用户已经拥有的菜单项 add by jiangcheng
	 * 
	 * @param privid
	 */
	public void updateUserAssignByPrivid(final TawSystemPrivOperation _objOneOpt) {

		final String hide = _objOneOpt.getDeleted();

		final String getCode = _objOneOpt.getCode();

		final String isApp = _objOneOpt.getIsApp();

		final String name = _objOneOpt.getName();

		final String url = _objOneOpt.getUrl();

		final String remark = _objOneOpt.getRemark();

		getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session) {

				Query query = session
						.createQuery(

						"update TawSystemPrivUserAssign set hide = :hide,isapp = :isapp,url = :url,currentprivid = :currentprivid,remark = :remark  where currentprivid = :currentprivid");
				query.setParameter("hide", hide);
				query.setParameter("isapp", isApp);
				query.setParameter("url", url);
				query.setParameter("currentprivid", name);
				query.setParameter("remark", remark);

				query.setParameter("currentprivid", getCode);

				int i = query.executeUpdate();

				return "";
			}
		});

		// return (Integer) getHibernateTemplate().execute(callback);
		// String sql = " update taw_system_priv_userassign set hide=" +
		// _objOneOpt.getDeleted()
		// + " where currentprivid='" + _objOneOpt.getCode() + "'";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.priv.dao.TawSystemPrivUserAssignDao#getTawSystemPrivUserAssign(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public TawSystemPrivUserAssign getTawSystemPrivUserAssign(
			final String menuId, final String menuItemCode, final String userId) {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawSystemPrivUserAssign t where t.currentprivid=:menuItemCode and t.menuid=:menuId and t.userid=:userId";

				Query query = session.createQuery(queryStr);
				query.setString("menuItemCode", menuItemCode);
				query.setString("menuId", menuId);
				query.setString("userId", userId);
				query.setFirstResult(0);
				query.setMaxResults(1);
				List result = query.list();
				if (result != null && !result.isEmpty()) {
					return (TawSystemPrivUserAssign) result.iterator().next();
				}
				return new TawSystemPrivUserAssign();
			}
		};
		return (TawSystemPrivUserAssign) getHibernateTemplate().execute(
				callback);
	}

	
}

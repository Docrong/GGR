package com.boco.eoms.commons.system.user.dao.hibernate;

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
import com.boco.eoms.commons.loging.BocoLog;

import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;

public class TawSystemUserRefRoleDaoHibernate extends BaseDaoHibernate
		implements TawSystemUserRefRoleDao {

	/**
	 * @see com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao#getTawSystemUserRefRoles(com.boco.eoms.commons.system.user.model.TawSystemUserRefRole)
	 */
	public List getTawSystemUserRefRoles(
			final TawSystemUserRefRole tawSystemUserRefRole) {
		return getHibernateTemplate().find("from TawSystemUserRefRole");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawSystemUserRefRole ==
		 * null) { return getHibernateTemplate().find("from
		 * TawSystemUserRefRole"); } else { // filter on properties set in the
		 * tawSystemUserRefRole HibernateCallback callback = new
		 * HibernateCallback() { public Object doInHibernate(Session session)
		 * throws HibernateException { Example ex =
		 * Example.create(tawSystemUserRefRole).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return
		 * session.createCriteria(TawSystemUserRefRole.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao#getTawSystemUserRefRole(String
	 *      id)
	 */
	public TawSystemUserRefRole getTawSystemUserRefRole(final String id) {
		TawSystemUserRefRole tawSystemUserRefRole = (TawSystemUserRefRole) getHibernateTemplate()
				.get(TawSystemUserRefRole.class, id);
		if (tawSystemUserRefRole == null) {
			BocoLog.warn(this, "uh oh, tawSystemUserRefRole with id '" + id
					+ "' not found...");
			throw new ObjectRetrievalFailureException(
					TawSystemUserRefRole.class, id);
		}

		return tawSystemUserRefRole;
	}

	/**
	 * @see com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao#saveTawSystemUserRefRole(TawSystemUserRefRole
	 *      tawSystemUserRefRole)
	 */
	public void saveTawSystemUserRefRole(
			final TawSystemUserRefRole tawSystemUserRefRole) {
		if ((tawSystemUserRefRole.getId() == null)
				|| (tawSystemUserRefRole.getId().equals("")))
			getHibernateTemplate().save(tawSystemUserRefRole);
		else
			getHibernateTemplate().saveOrUpdate(tawSystemUserRefRole);
	}

	/**
	 * @see com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao#removeTawSystemUserRefRole(String
	 *      id)
	 */
	public void removeTawSystemUserRefRole(final String id) {
		getHibernateTemplate().delete(getTawSystemUserRefRole(id));
	}

	// /**
	// * 得到角色的某用户信息
	// *
	// * @param userid
	// * @return
	// */
	// public TawSystemUserRefRole getRoleidByuserids(String roleid, String
	// userid) {
	// String hql = " from TawSystemUserRefRole refrole where refrole.userid='"
	// + userid + "'" + " and refrole.roleid='" + roleid + "'";
	//
	// List list = new ArrayList();
	// list = getHibernateTemplate().find(hql);
	// TawSystemUserRefRole role = null;
	// if (list != null) {
	// role = new TawSystemUserRefRole();
	// role = (TawSystemUserRefRole) list.get(0);
	// }
	// return role;
	// }

	// /**
	// * 得到某角色的所有用户
	// *
	// * @param roleid
	// * @return
	// */
	// public List getUseridbyroleid(String roleid) {
	//
	// List useridlist = new ArrayList();
	//
	// String hql = " select distinct refrole.userid from TawSystemUserRefRole
	// refrole where refrole.roleid='"
	// + roleid + "'";
	// useridlist = getHibernateTemplate().find(hql);
	// return useridlist;
	// }
	//
	/**
	 * 得到某用户的所有角色
	 * 
	 * @param userid
	 * @return
	 */
	public List getRoleidByuserid(String userid) {

		List roleidlist = new ArrayList();

		String hql = " select distinct refrole.subRoleid from TawSystemUserRefRole refrole where refrole.userid='"
				+ userid + "'";
		roleidlist = getHibernateTemplate().find(hql);
		return roleidlist;
	}

	/**
	 * 得到某用户的所有角色 by leo
	 * 
	 * @param userid
	 * @return
	 */
	public List getRoleByUserId(String userId) {

		List roleidlist = new ArrayList();

		String hql = " select distinct role from TawSystemUserRefRole refrole,TawSystemSubRole subrole,TawSystemRole role where refrole.userid='"
				+ userId
				+ "' and refrole.subRoleid=subrole.id and role.roleId=subrole.roleId and "
				+ StaticMethod.noDeletedCon("role");
		roleidlist = getHibernateTemplate().find(hql);
		return roleidlist;
	}

	/**
	 * 得到某用户的所有角色
	 * 
	 * @param userid
	 * @return
	 */
	public List getUserRefRoleByuserid(String userid) {

		String hql = "  from TawSystemUserRefRole refrole where refrole.userid='"
				+ userid + "'";
		return getHibernateTemplate().find(hql);
	}

	// /**
	// * 判断某角色的某用户是否存在
	// *
	// * @param userid
	// * @return
	// */
	// public boolean getRoleidByuserid(String roleid, String userid) {
	//
	// String hql = " from TawSystemUserRefRole refrole where refrole.userid='"
	// + userid + "'" + " and refrole.roleid='" + roleid + "'";
	//
	// List list = new ArrayList();
	// list = getHibernateTemplate().find(hql);
	// boolean flag = false;
	// if (list != null) {
	// flag = true;
	// }
	// return flag;
	//
	// }

	/**
	 * 删除某角色的所有用户
	 * 
	 * @param roleid
	 */
	public void removeUseridByroleid(String roleid) {

		String hql = " from TawSystemUserRefRole role where role.subRoleid='"
				+ roleid + "'";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		if (list != null) {
			if (list.size() > 0) {
				TawSystemUserRefRole role = new TawSystemUserRefRole();
				for (int i = 0; i < list.size(); i++) {
					role = (TawSystemUserRefRole) list.get(i);

					getHibernateTemplate().delete(role);
				}
			}
		}
	}

	/**
	 * 删除某用户的所有角色
	 * 
	 * @param userid
	 */
	public void removeRoleidByUserid(String userid, String roleType) {

		String hql = "  from TawSystemUserRefRole role where role.userid='"
				+ userid + "' and role.roleType='" + roleType + "'";

		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		if (list != null) {
			if (list.size() > 0) {
				TawSystemUserRefRole role = new TawSystemUserRefRole();
				for (int i = 0; i < list.size(); i++) {
					role = (TawSystemUserRefRole) list.get(i);

					getHibernateTemplate().delete(role);
				}
			}
		}

	}

	/**
	 * 得到某角色的所有用户
	 * 
	 * @param roleid
	 * @return Map(TawSystemUser,groupType)
	 */
	public Map getGroupUserbySubRoleid(String subRoleid) {

		List useridlist = new ArrayList();
		Map userMap = new HashMap();

		String hql = " from TawSystemUser user,TawSystemUserRefRole refrole where refrole.subRoleid='"
				+ subRoleid + "' and user.userid=refrole.userid";
		useridlist = getHibernateTemplate().find(hql);
		for (int i = 0; i < useridlist.size(); i++) {
			TawSystemUserRefRole ur = (TawSystemUserRefRole) (((Object[]) useridlist
					.get(i))[1]);
			TawSystemUser user = (TawSystemUser) ((Object[]) useridlist.get(i))[0];
			String userId = user.getUserid();
			String groupType = StaticMethod
					.nullObject2String(ur.getGroupType());
			userMap.put(userId, groupType);
			// RoleConstants.groupType_common)
			// userMap.put(((Object[]) useridlist.get(i))[0], StaticMethod
			// .nullObject2String(ur.getGroupType(),
			// RoleConstants.groupType_common));
		}
		return userMap;
	}

	/**
	 * 得到某角色的所有用户
	 * 
	 * @param roleid
	 * @return Map(TawSystemUser,groupType)
	 */
	public List getUserbySubRoleid(String subRoleid) {

		List useridlist = new ArrayList();
		List userList = new ArrayList();

		String hql = "select distinct user  from TawSystemUser user,TawSystemUserRefRole refrole where refrole.subRoleid='"
				+ subRoleid + "' and user.userid=refrole.userid";
//		String hql = " from TawSystemUser user,TawSystemUserRefRole refrole where refrole.subRoleid='"
//			+ subRoleid + "' and user.userid=refrole.userid";
		useridlist = getHibernateTemplate().find(hql);
		for (int i = 0; i < useridlist.size(); i++) {
			//userList.add(((Object[]) useridlist.get(i))[0]);
				userList.add(useridlist.get(i));
		}
		return userList;
	}

	/**
	 * 得到某角色的所有用户
	 * 
	 * @param roleid
	 * @return Map(TawSystemUser,groupType)
	 */
	public List getUserbyRoleids(String subRoleid) {
		String hql = " from TawSystemUserRefRole refrole where refrole.subRoleid='"
				+ subRoleid + "'";
		return getHibernateTemplate().find(hql);
	}

	/**
	 * 得到某角色的所有用户
	 * 
	 * @param roleid
	 * @return TawSystemUserRefRole
	 */
	public List getUserRefRoleBySubRoleid(String subRoleid) {

		List useridlist = new ArrayList();
		List userlist = new ArrayList();

		String hql = " from TawSystemUserRefRole refrole where refrole.subRoleid='"
				+ subRoleid + "'";
		return getHibernateTemplate().find(hql);
	}

	/**
	 * 得到某子角色,某在线状态的所有用户
	 * 
	 * @param roleid
	 * @return
	 */
	public List getUserbySubRoleidUserstatus(String roleid, String userstatus) {
		List useridlist = new ArrayList();
		List userlist = new ArrayList();

		String hql = " from TawSystemUser user,TawSystemUserRefRole refrole where refrole.subRoleid='"
				+ roleid
				+ "' and user.userid=refrole.userid and user.userStatus='"
				+ userstatus + "'";
		useridlist = getHibernateTemplate().find(hql);
		for (int i = 0; i < useridlist.size(); i++) {
			userlist.add(((Object[]) useridlist.get(i))[0]);
		}
		return userlist;
	}

	/**
	 * 得到某用户的所有角色
	 * 
	 * @param userid
	 * @return
	 */
	public List getSubRoleByuserid(String userid, String roleType) {

		List roleidlist = new ArrayList();
		List rolelist = new ArrayList();
		String hql = "";

		if (roleType.equals(RoleConstants.ROLETYPE_SUBROLE)) {
			hql = " from TawSystemSubRole subrole,TawSystemUserRefRole refrole where refrole.subRoleid=subrole.id and refrole.userid='"
					+ userid
					+ "' and refrole.roleType='"
					+ roleType
					+ "' order by subrole.subRoleName";
		} else if (roleType.equals(RoleConstants.ROLETYPE_ROLE)) {
			hql = " from TawSystemRole role,TawSystemUserRefRole refrole where refrole.subRoleid=role.id and refrole.userid='"
					+ userid + "' and refrole.roleType='" + roleType + "'";
		}

		roleidlist = getHibernateTemplate().find(hql);
		for (int i = 0; i < roleidlist.size(); i++) {
			rolelist.add(((Object[]) roleidlist.get(i))[0]);
		}

		return rolelist;
	}

	public List getUserIdBySubRoleids(String subRoleid) {
		String hql = "select userid from TawSystemUserRefRole refrole where refrole.subRoleid='"
				+ subRoleid + "'";
		return getHibernateTemplate().find(hql);
	}

	public boolean isExist(String subRoleid, String userId) {
		boolean bool = false;
		List roleidlist = new ArrayList();
		String hql = " from TawSystemUserRefRole role where role.subRoleid='"
				+ subRoleid + "' and role.userid='" + userId + "'";
		roleidlist = getHibernateTemplate().find(hql);
		if (roleidlist.size() > 0) {
			bool = true;
		}
		return bool;
	}

	public void removeUserid(String userid, String subRolei) {

		String hql = "  from TawSystemUserRefRole role where role.userid='"
				+ userid + "' and role.subRoleid='" + subRolei + "'";

		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		if (list != null) {
			if (list.size() > 0) {
				TawSystemUserRefRole role = new TawSystemUserRefRole();
				for (int i = 0; i < list.size(); i++) {
					role = (TawSystemUserRefRole) list.get(i);

					getHibernateTemplate().delete(role);
				}
			}
		}

	}

	/**
	 * 获取当前子角色的组长
	 * 
	 * @param subRoleid
	 * @return
	 * @throws HibernateException
	 */

	public TawSystemUserRefRole getRoleLeaderBySubRoleid(final String subRoleid)
			throws HibernateException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				TawSystemUserRefRole userRefRole = null;
				try {
					String hql = " from TawSystemUserRefRole refrole where refrole.subRoleid='"
							+ subRoleid
							+ "' and refrole.groupType='"
							+ RoleConstants.groupType_leader + "'";
					Query query = session.createQuery(hql);
					List resultList = query.list();
					if (resultList.size() > 0) {
						userRefRole = (TawSystemUserRefRole) resultList.get(0);
					}

				} catch (Exception e) {
					System.out
							.println("-------getRoleLeaderBySubRoleid error!---------");
					e.printStackTrace();
					throw new HibernateException(
							"getRoleLeaderBySubRoleid error");
				}
				return userRefRole;
			}
		};
		return (TawSystemUserRefRole) getHibernateTemplate().execute(callback);
	}

	public List getRoleLeaderlistBySubRoleid(final String subRoleid)
	throws HibernateException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				List resultList = new ArrayList();
				try {
					String hql = " from TawSystemUserRefRole refrole where refrole.subRoleid='"
							+ subRoleid
							+ "' and refrole.groupType='"
							+ RoleConstants.groupType_leader + "'";
					Query query = session.createQuery(hql);
					resultList = query.list();
		
				} catch (Exception e) {
					System.out
							.println("-------getRoleLeaderBySubRoleid error!---------");
					e.printStackTrace();
					throw new HibernateException(
							"getRoleLeaderBySubRoleid error");
				}
				return resultList;
			}
		};
		return (List) getHibernateTemplate().execute(callback);
	}
	
	/**
	 * 得到某角色的当前用户信息
	 * 
	 * @param subRoleid
	 * @param userId
	 * @return
	 * @throws HibernateException
	 */

	public TawSystemUserRefRole getUserRefRoleByUserid(final String subRoleid,
			final String userId) throws HibernateException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				TawSystemUserRefRole userRefRole = null;
				try {
					String hql = " from TawSystemUserRefRole refrole where refrole.subRoleid='"
							+ subRoleid
							+ "' and refrole.userid='"
							+ userId
							+ "'";
					Query query = session.createQuery(hql);
					List resultList = query.list();
					if (resultList.size() > 0) {
						userRefRole = (TawSystemUserRefRole) resultList.get(0);
					}

				} catch (Exception e) {
					e.printStackTrace();
					throw new HibernateException("getUserRefRoleByUserid error");
				}
				return userRefRole;
			}
		};
		return (TawSystemUserRefRole) getHibernateTemplate().execute(callback);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.role.dao.ITawSystemRoleImportDao#removeTawSystemRoleImportByVersion(java.lang.String)
	 */
	public void removeTawSystemUserRefRoleByVersion(String version) {
		List list = this.getHibernateTemplate()
				.find(
						"from TawSystemUserRefRole t where t.version='"
								+ version + "'");
		if (list != null) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				TawSystemUserRefRole tawSystemUserRefRole = (TawSystemUserRefRole) it
						.next();
				this.removeTawSystemUserRefRole(tawSystemUserRefRole.getId());
			}
		}

	}

	/**
	 * 将用户数组写为sql条件后in的字符串
	 * 
	 * @param userIds
	 *            用户id数组
	 * 
	 * @return sql条件
	 */
	private String userIds2userIdCondition(String userIds[]) {
		String condition = "";
		if (userIds != null) {
			for (int i = 0; i < userIds.length; i++) {
				condition += "'" + userIds[i] + "',";
			}
		} else {
			return "()";
		}
		return "(" + StaticMethod.removeLastStr(condition, ",") + ")";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.user.dao.TawSystemUserRefRoleDao#getSubrolesByUserId(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public List getSubrolesByUserId(String userIds[], String roleType,
			String delFlag) {

		// select subrole.* from taw_system_sub_role as subrole
		// ,taw_system_userrefrole as userrefrole,taw_system_role as role
		// where userrefrole.userid='infopub'
		// and subrole.deleted='0'
		// and userrefrole.roletype='1'
		// and userrefrole.subroleid=subrole.id
		// and subrole.roleid=role.role_id
		// and role.roletype_id='2'

		String roleTypes[] = roleType.split(",");
		String roleTypeCond = "";
		for (int i = 0; i < roleTypes.length; i++) {
			roleTypeCond += "'" + roleTypes[i] + "',";
		}
		roleTypeCond = StaticMethod.removeLastStr(roleTypeCond, ",");

		String hql = "select distinct tawSystemSubRole from TawSystemSubRole tawSystemSubRole,"
				+ "TawSystemUserRefRole tawSystemUserRefRole ,TawSystemRole tawSystemRole where tawSystemSubRole.deleted='"
				+ delFlag
				+ "' and  tawSystemUserRefRole.userid in"
				+ userIds2userIdCondition(userIds)
				+ " and tawSystemUserRefRole.roleType in("
				+ roleTypeCond
				+ ") and tawSystemUserRefRole.subRoleid=tawSystemSubRole.id "
				+ " and tawSystemSubRole.roleId=tawSystemRole.roleId ";
		// // 非查询全部角色类型（系统角色、流程角色）
		// if (!RoleConstants.ALL_ROLE.equals(roleType)) {
		// hql += " and tawSystemRole.roleTypeId='" + roleType + "'";
		// }

		return this.getHibernateTemplate().find(hql);
	}
	public String getRoleTypeBySubRoleId(String subRoleId){
		long roletypeId=0;
		long roleId=0;
		System.out.println("0114-----------------------------SubroleId="+subRoleId);
		String hql="from TawSystemSubRole where id= '"+subRoleId+"'";
		List subList=getHibernateTemplate().find(hql);
		TawSystemSubRole tawSystemSubRole=null;
		if(subList!=null&&subList.size()!=0){
			tawSystemSubRole=(TawSystemSubRole) subList.get(0);
			System.out.println("0114-----------------------------roleId="+tawSystemSubRole.getRoleId());
			 roleId=tawSystemSubRole.getRoleId();
		}
	
		String hql2="from TawSystemRole where role_id= '"+roleId+"'";
		List roleList=getHibernateTemplate().find(hql2);
		TawSystemRole tawSystemRole=null;
		if(roleList!=null&&roleList.size()!=0){
			tawSystemRole=(TawSystemRole) roleList.get(0);
			roletypeId=tawSystemRole.getRoleTypeId();
		}
		
		return Long.toString(roletypeId);
	}
	
	public List getSubRoleByCondition(String condition) {

		List rolelist = new ArrayList();

		String hql = " from TawSystemUserRefRole refrole,TawSystemSubRole subrole where refrole.subRoleid=subrole.id and "
				+ StaticMethod.noDeletedCon("subrole") + condition;
		System.out.println("lizhi:hql="+hql);
		rolelist = getHibernateTemplate().find(hql);
		return rolelist;
	}

}

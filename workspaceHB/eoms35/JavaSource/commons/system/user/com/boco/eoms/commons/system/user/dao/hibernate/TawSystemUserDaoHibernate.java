package com.boco.eoms.commons.system.user.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.user.dao.TawSystemUserDao;
import com.boco.eoms.commons.system.user.model.TawPartnersUser;
import com.boco.eoms.commons.system.user.model.TawSystemUser;

public class TawSystemUserDaoHibernate extends BaseDaoHibernate implements
		TawSystemUserDao, ID2NameDAO {

	/**
	 * @see com.boco.eoms.commons.system.user.dao.TawSystemUserDao#getTawSystemUsers(com.boco.eoms.commons.system.user.model.TawSystemUser)
	 */
	public List getTawSystemUsers(final TawSystemUser tawSystemUser) {
		return getHibernateTemplate().find("from TawSystemUser");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawSystemUser == null) {
		 * return getHibernateTemplate().find("from TawSystemUser"); } else { //
		 * filter on properties set in the tawSystemUser HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex =
		 * Example.create(tawSystemUser).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return session.createCriteria(TawSystemUser.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.system.user.dao.TawSystemUserDao#getTawSystemUser(String
	 *      id)
	 */
	public TawSystemUser getTawSystemUser(final String id) {
		TawSystemUser tawSystemUser = (TawSystemUser) getHibernateTemplate()
				.get(TawSystemUser.class, id);
		if (tawSystemUser == null) {
			BocoLog.warn(this, "uh oh, tawSystemUser with id '" + id
					+ "' not found...");
			throw new ObjectRetrievalFailureException(TawSystemUser.class, id);
		}

		return tawSystemUser;
	}

	/**
	 * @see com.boco.eoms.commons.system.user.dao.TawSystemUserDao#saveTawSystemUser(TawSystemUser
	 *      tawSystemUser)
	 */
	public void saveTawSystemUser(final TawSystemUser tawSystemUser) {
		if ((tawSystemUser.getId() == null)
				|| (tawSystemUser.getId().equals("")))
			getHibernateTemplate().save(tawSystemUser);
		else
			getHibernateTemplate().saveOrUpdate(tawSystemUser);
	}

	/**
	 * @see com.boco.eoms.commons.system.user.dao.TawSystemUserDao#removeTawSystemUser(String
	 *      id)
	 */
	public void removeTawSystemUser(final String id) {
		//getHibernateTemplate().delete(getTawSystemUser(id));
		TawSystemUser systemuser= getTawSystemUser(id);
		 systemuser.setDeleted("1");
		 systemuser.setMobile("");
		getHibernateTemplate().saveOrUpdate(getTawSystemUser(id));
	}

	/**
	 * 删除用户恢复
	 * @param id
	 */
	public void resumeTawSystemUser(final String id) {
		TawSystemUser systemuser= getTawSystemUser(id);
		 systemuser.setDeleted("0");
		 getHibernateTemplate().saveOrUpdate(getTawSystemUser(id));
	}
	/**
	 * 得到部门的所有USER
	 * 
	 * @param deptid
	 * @return
	 */
	public List getUserBydeptids(String deptid) {
		String hql = " from TawSystemUser systemuser where systemuser.deptid='"
				+ deptid
				+ "' and systemuser.userid !='admin' and deleted='0' order by username";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;
	}

	/**
	 * 2008-12-2 wangsixuan 得到部门的所有USER,只取是代维的USER
	 * 
	 * @param deptid
	 * @return
	 */
	public List getUserBydeptidsPartner(String deptid) {
		String hqlDept = " from TawSystemDept sysdept where sysdept.isPartners='1' and sysdept.deleted='0' and sysdept.deptid ='"
				+ deptid + "'";
		List listDept = new ArrayList();
		listDept = (ArrayList) getHibernateTemplate().find(hqlDept);
		List list = new ArrayList();
		if (!listDept.isEmpty()) {
			String hql = " from TawSystemUser systemuser where systemuser.deptid='"
					+ deptid
					+ "' and systemuser.userid !='admin' order by username";

			list = (ArrayList) getHibernateTemplate().find(hql);
		}
		return list;
	}

	/**
	 * 得到部门的所有USER
	 * 
	 * @param deptid
	 * @return
	 */
	public List getUserBydeptidsNoSelf(String deptid, String userid) {
		String hql = " from TawSystemUser systemuser where systemuser.deptid='"
				+ deptid
				+ "' and systemuser.userid !='admin' and systemuser.userid !='"
				+ userid + "' order by username";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;
	}

	/**
	 * 得到部门的所有USERID
	 * 
	 * @param deptid
	 * @return
	 */
	public List getUserIdsBydeptid(String deptid) {
		String hql = "select userid from TawSystemUser systemuser where systemuser.deptid='"
				+ deptid
				+ "' and systemuser.userid !='admin' order by username";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;
	}

	/**
	 * 根据userid得到用户的信息
	 * 
	 * @param userid
	 * @return
	 */
	public TawSystemUser getUserByuserid(String userid) {
		TawSystemUser systemuser = new TawSystemUser();
		String hql = " from TawSystemUser systemuser where systemuser.userid='"
				+ userid + "' and " + StaticMethod.noDeletedCon("systemuser");
		List list = new ArrayList();
		list = (List) getHibernateTemplate().find(hql);
		if (list != null) {

			if (list.size() > 0) {

				systemuser = (TawSystemUser) list.get(0);
			}
		}
		return systemuser;
	}

	/**
	 * 根据userid得到所有用户的信息（包括已删除，查询不到返回为空）
	 * 
	 * @param userid
	 * @return
	 */
	public TawSystemUser getTawSystemUserByuserid(String userid) {
		TawSystemUser systemuser = new TawSystemUser();
		String hql = " from TawSystemUser systemuser where systemuser.userid='"
				+ userid + "'";
		List list = new ArrayList();
		list = (List) getHibernateTemplate().find(hql);
		if (list != null) {

			if (list.size() > 0) {

				systemuser = (TawSystemUser) list.get(0);
			}
		}
		return systemuser;
	}
	
	/**
	 * 根据名称得到用户列表
	 * 
	 * @param userName
	 * @return List
	 */
	public List getUsersByName(String userName) {
		String hql = "from TawSystemUser u where u.username like '%" + userName
				+ "%' and deleted='0'  order by username";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		return list;
	}
	/**
	 * 根据名称，部门得到用户列表
	 * 
	 * @param userName
	 * @return List
	 */
	public List getUsersByName(String userName,String userDeptname) {
		List list = new ArrayList();
		if(userName.equals("")&&!userDeptname.equals("")){
			String hql = "from TawSystemUser u where u.deptname like '%" + userDeptname
			+ "%' and deleted='0' order by username";
			list = (ArrayList) getHibernateTemplate().find(hql);
		}
		if(!userName.equals("")&&userDeptname.equals("")){
			String hql = "from TawSystemUser u where u.username like '%" + userName
			+ "%' and deleted='0' order by username";
			list = (ArrayList) getHibernateTemplate().find(hql);
		}
		if(!userName.equals("")&&!userDeptname.equals("")){
			String hql = "from TawSystemUser u where u.username like '%" + userName
			+ "%'and u.deptname like '%" + userDeptname + "%' and deleted='0' order by username";
			list = (ArrayList) getHibernateTemplate().find(hql);
		}
		return list;
	}

	public List getUsersDelByName(String userName) {
		 String hql="";
		if("".equals(userName)){
		     hql = "from TawSystemUser u where  u.deleted='1'  order by u.username";
		}else{
		     hql = "from TawSystemUser u where u.username like '%" + userName
			+ "%' and deleted='1'  order by username";
		}
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		return list;
	}
	
	/**
	 * 用户删除回收站
	 * @return List
	 */
	public List getUsersByDeleted() {
		 String hql = "from TawSystemUser u where  u.deleted='1'  order by u.username";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		return list;
	}
	
	/**
	 * 根据名称得到用户列表 add：wangsixuan 09-1-5
	 * 
	 * @param 用户名，地市、部门、电话、邮箱
	 * @return List
	 */
	public List getUsersByInfo(String userName, String cptroomname,
			String deptname, String mobile, String email) {
		String hql = "from TawSystemUser u where 1=1 and u.isPartners='1'";
		if (userName != null && !"".equals(userName))
			hql += " and u.username like '%" + userName + "%'";
		if (cptroomname != null && !"".equals(cptroomname))
			hql += " and u.cptroomname like '%" + cptroomname + "%'";
		if (deptname != null && !"".equals(deptname))
			hql += " and u.deptname like '%" + deptname + "%'";
		if (mobile != null && !"".equals(mobile))
			hql += " and u.mobile like '%" + mobile + "%'";
		if (email != null && !"".equals(email))
			hql += " and u.email like '%" + email + "%'";
		hql += " order by u.username";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 获取系统管理员的用户列表
	 * 
	 * @param userdegree
	 * @return
	 */
	public List getUserbyUserdegrees(String userdegree) {

		String hql = "from TawSystemUser systemuser where systemuser.userdegree='"
				+ userdegree + "'";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;

	}

	/**
	 * 得到某机房的所有用户列表
	 * 
	 * @param cptid
	 * @return
	 */
	public List getUserbyCptids(String cptid) {

		String hql = "from TawSystemUser systemuser where systemuser.cptroomid='"
				+ cptid + "'";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;

	}

	/**
	 * 查询某用户添加的所有用户
	 * 
	 * @param operuserid
	 * @return
	 */
	public List getUserbyOperuserids(String operuserid) {
		String hql = "from TawSystemUser systemuser where systemuser.operuserid='"
				+ operuserid + "'";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;
	}

	/**
	 * 根据性别查询用户
	 * 
	 * @param sexid
	 * @return
	 */
	public List getUserbysexs(String sexid) {
		String hql = "from TawSystemUser systemuser where systemuser.sex='"
				+ sexid + "'";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;
	}

	/**
	 * 取得某部门下的所有管理员的userid
	 * 
	 * @param deptid
	 * @param degreeid
	 * @return
	 */
	public List getUserbyDeptidandDegids(String deptid, String degreeid) {
		String hql = "from TawSystemUser systemuser where systemuser.deptid='"
				+ deptid + "'" + " and systemuser.userdegree='" + degreeid
				+ "'";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;

	}

	/**
	 * 取得某机房的所有管理员
	 * 
	 * @param cptid
	 * @param degid
	 * @return
	 */
	public List getUserBycptidAndDegid(String cptid, String degid) {

		String hql = "from TawSystemUser systemuser where systemuser.cptroomid='"
				+ cptid + "'" + " and systemuser.userdegree='" + degid + "'";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;

	}

	/**
	 * 删除某部门的所有用户
	 * 
	 * @param deptid
	 */
	public void removeUserbydeptid(String deptid) {
		String hql = " from TawSystemUser user where user.deptid='" + deptid
				+ "'";

		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		if (list != null) {
			if (list.size() > 0) {
				TawSystemUser user = new TawSystemUser();
				for (int i = 0; i < list.size(); i++) {
					user = (TawSystemUser) list.get(i);
					user.setDeptid(StaticVariable.USER_NO_DEPTID);
					// getHibernateTemplate().delete(user);
					saveTawSystemUser(user);
				}
			}
		}
	}

	/**
	 * 删除某机房的所有用户
	 * 
	 * @param cptid
	 */
	public void removeUserbycptid(String cptid) {

		String hql = " from TawSystemUser user where user.cptroomid='" + cptid
				+ "'";

		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		if (list != null) {
			if (list.size() > 0) {
				TawSystemUser user = new TawSystemUser();
				for (int i = 0; i < list.size(); i++) {
					user = (TawSystemUser) list.get(i);

					getHibernateTemplate().delete(user);
				}
			}
		}
	}

	/**
	 * 根据部门id 和子部门id 查询用户
	 * 
	 * @param deptid
	 * @param sondeptid
	 * @return
	 */
	public List getUserbydeptidanson(String deptids) {

		String hql = " from TawSystemUser systemuser  where systemuser.deptid in("
				+ deptids + ")";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;
	}

	/**
	 * 查询某部门所有的EMAIL
	 * 
	 * @return
	 */
	public List getAllEmalibyDeptid(String deptid) {

		String hql = " select systemuser.email from TawSystemUser systemuser where systemuser.deptid='"
				+ deptid + "'";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;

	}

	/**
	 * 查询某部门所有的手机号
	 * 
	 * @return
	 */
	public List getAllMobileBydeptid(String deptid) {

		String hql = " select systemuser.mobile from TawSystemUser systemuser where systemuser.deptid='"
				+ deptid + "'";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;

	}

	/**
	 * 查询某机房的所有EMAIL
	 * 
	 * @return
	 */
	public List getAllEmailbyCptid(String cptid) {

		String hql = "select systemuser.email from TawSystemUser systemuser where systemuser.cptroomid='"
				+ cptid + "'";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;
	}

	/**
	 * 查询某机房的所有手机号
	 * 
	 * @return
	 */
	public List getAllMobilebyCptid(String cptid) {

		String hql = "select systemuser.mobile from TawSystemUser systemuser where systemuser.cptroomid='"
				+ cptid + "'";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;

	}

	/**
	 * 得到某些用户的EMAIL in查询
	 * 
	 * @param userids
	 * @return
	 */
	public List getAllEmailbyuserids(String userids) {

		String hql = "select systemuser.email from TawSystemUser systemuser  where systemuser.userid in('"
				+ userids + "')";// modify by sunsht at 08-12-18,add '
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;

	}

	/**
	 * 得到某些用户的手机号 in查询
	 * 
	 * @param userids
	 * @return
	 */
	public List getAllMobileByuerids(String userids) {

		String hql = "select  systemuser.mobile from TawSystemUser systemuser  where systemuser.userid in("
				+ userids + ")";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;
	}

	/**
	 * 查询未被删除的用户
	 * 
	 * @return
	 */
	public List getNoDelUser() {

		String hql = " from TawSystemUser user where user.deleted=0";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;
	}

	/**
	 * 得到正在休假的用户
	 * 
	 * @param userid
	 * @return
	 */
	public List getRestByUserid(String userid) {
		String hql = " from TawSystemUser user where user.userid='" + userid
				+ "' and user.isrest='" + StaticVariable.AREA_DEFAULT_STRONE
				+ "'";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	/**
	 * 得到未休假的用户
	 * 
	 * @param userid
	 * @return
	 */
	public List getNoRestByUserid(String userid) {
		String hql = " from TawSystemUser user where user.userid='" + userid
				+ "' and user.isrest='" + StaticVariable.AREA_DEFAULT_STRZERO
				+ "'";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	/**
	 * 判断某用户是否正在休假
	 * 
	 * @param userid
	 * @return
	 */
	public boolean isRestbyUserid(String userid) {
		String hql = " from TawSystemUser user where user.userid='" + userid
				+ "' and user.isrest='" + StaticVariable.AREA_DEFAULT_STRONE
				+ "'";
		List list = (ArrayList) getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断某用户是否是全职
	 * 
	 * @param userid
	 * @return
	 */
	public boolean isFullemploybyUserid(String userid) {
		String hql = " from TawSystemUser user where user.userid='" + userid
				+ "' and user.isfullemploy='"
				+ StaticVariable.AREA_DEFAULT_STRONE + "'";
		List list = (ArrayList) getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查询所有全职用户
	 * 
	 * @param userid
	 * @return
	 */
	public List getFullemploybyUserid(String userid) {
		String hql = " from TawSystemUser user where user.userid='" + userid
				+ "' and user.isfullemploy='"
				+ StaticVariable.AREA_DEFAULT_STRONE + "'";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	/**
	 * 查询所有兼职用户
	 * 
	 * @param userid
	 * @return
	 */
	public List getPartemployBuUserid(String userid) {
		String hql = " from TawSystemUser user where user.userid='" + userid
				+ "' and user.isfullemploy='"
				+ StaticVariable.AREA_DEFAULT_STRZERO + "'";
		return (ArrayList) getHibernateTemplate().find(hql);
	}

	/*
	 * id2name，即用户id转为用户名称 added by leo
	 * 
	 * @see com.boco.eoms.base.dao.ID2NameDAO#id2Name(java.lang.String)
	 */
	public String id2Name(final String id) throws DictDAOException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
				String queryStr = " from TawSystemUser user where user.userid=:userId";

				Query query = session.createQuery(queryStr);
				// 发起人id
				query.setString("userId", id);
				// 仅查一条
				query.setFirstResult(0);
				query.setMaxResults(1);
				List list = query.list();
				TawSystemUser user = null;

				if (list != null && !list.isEmpty()) {
					// 不为空则取user
					user = (TawSystemUser) list.iterator().next();
				} else {
					// 为空，写入将username设为未知联系人
					user = new TawSystemUser();
					user.setUsername(Util.idNoName());
				}
				return user;
			}
		};

		TawSystemUser user = null;
		try {
			user = (TawSystemUser) getHibernateTemplate().execute(callback);
		} catch (Exception e) {
			// 若有异常则抛出daoexception,加入DAoException是为了解藕，若抛出hibernateException，这样在换orm时，如ibatis，service就要换异常捕捉
			throw new DictDAOException(e);
		}
		return user.getUsername();
	}

	/**
	 * 查询某状态的所有用户
	 * 
	 * @param userstatus
	 * @return
	 */
	public List getUserByUserstatus(String userstatus) {
		String hql = " from TawSystemUser user where user.userstatus='"
				+ userstatus + "'";
		return getHibernateTemplate().find(hql);
	}

	public String getMobilesByUserId(String userId) {
		List userList = new ArrayList();
		String hql = "select user.mobile from TawSystemUser user where user.userid='"
				+ userId + "'";
		String mobiles = "";
		userList = getHibernateTemplate().find(hql);
		int listSize = userList.size();
		if (listSize > 0) {
			mobiles = (String) userList.get(0);
		}
		return mobiles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.user.dao.TawSystemUserDao#getUser(java.lang.String,
	 *      java.lang.String)
	 */
	public TawSystemUser getUser(final String userId, final String passwd) {

		final String md5Passwd = new Md5PasswordEncoder().encodePassword(
				passwd, new String());
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
				String queryStr = " from TawSystemUser user where user.userid=:userId and user.password=:passwd and "
						+ StaticMethod.noDeletedCon("user");

				Query query = session.createQuery(queryStr);
				// 发起人id
				query.setString("userId", userId);
				query.setString("passwd", md5Passwd);
				// 仅查一条
				query.setFirstResult(0);
				query.setMaxResults(1);
				List users = query.list();
				if (users != null && !users.isEmpty()) {
					return (TawSystemUser) users.iterator().next();
				}
				return new TawSystemUser();
			}
		};

		return (TawSystemUser) getHibernateTemplate().execute(callback);

	}

	public List getUsersBySubRoleid(String subRoleId) {
		return this
				.getHibernateTemplate()
				.find(
						"select distinct user from TawSystemUser user ,TawSystemUserRefRole ref where ref.subRoleid='"
								+ subRoleId + "' and ref.userid=user.userid");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.user.dao.TawSystemUserDao#getLeaderOfGroup(java.lang.String,
	 *      java.lang.String)
	 */
	public List getLeaderOfGroup(String groupId, String groupType) {
		// select tuser.* from taw_system_userrefrole ref ,taw_system_user tuser
		// where ref.subroleid='8aa081e71d6c1180011d6c14dc250005' and
		// ref.userid=tuser.userid
		return this
				.getHibernateTemplate()
				.find(
						"select distinct user from TawSystemUser user ,TawSystemUserRefRole ref where ref.subRoleid='"
								+ groupId
								+ "' and user.userid=ref.userid and ref.groupType='"
								+ groupType
								+ "' and "
								+ StaticMethod.noDeletedCon("user"));
	}

	/**
	 * @see com.boco.eoms.commons.system.user.dao.TawSystemUserDao#getTawSystemUsers(Integer,
	 *      Integer, String)
	 */
	public Map getTawSystemUsers(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		// filter on properties set in the tawSystemRole
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawSystemUser where "
						+ StaticMethod.noDeletedCon(null);
				if (whereStr != null && whereStr.length() > 0)
					queryStr += " and " + whereStr;
				String queryCountStr = "select count(*) " + queryStr;

				Integer total = (Integer) session.createQuery(queryCountStr)
						.iterate().next();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.user.dao.TawSystemUserDao#getTawSystemUsers(java.lang.String)
	 */
	public List getTawSystemUsers(String whereStr) {
		String hql = "from TawSystemUser where "
				+ StaticMethod.noDeletedCon(null);
		if (whereStr != null || "".equals(whereStr.trim())) {
			hql += " and " + whereStr;
		}
		return this.getHibernateTemplate().find(hql);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.user.dao.TawSystemUserDao#getTawSystemUsersOfArea(java.lang.String)
	 */
	public List getTawSystemUsersOfArea(String areaId) {
		// select * from taw_system_user tuser,taw_system_dept dept where
		// dept.areaid='101' and dept.deptid=tuser.deptid
		return this
				.getHibernateTemplate()
				.find(
						"select distinct user from TawSystemUser user,TawSystemDept dept where dept.areaid='"
								+ areaId
								+ "' and dept.deptId=user.deptid and "
								+ StaticMethod.noDeletedCon("user"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.user.dao.TawSystemUserDao#getTawSystemUsersOfDept(java.lang.String[])
	 */
	public List getTawSystemUsersOfDept(String[] deptIds) {
		String deptIdStr = this.array2inStr(deptIds);
		if (deptIdStr == null || "".equals(deptIdStr)) {
			return new ArrayList();
		}

		return this.getHibernateTemplate().find(
				"from TawSystemUser user where user.deptid in (" + deptIdStr
						+ ") and " + StaticMethod.noDeletedCon("user"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.user.dao.TawSystemUserDao#getTawSystemUsersOfRoom(java.lang.String[])
	 */
	public List getTawSystemUsersOfRoom(String[] roomIds) {
		String roomIdStr = this.array2inStr(roomIds);
		if (roomIdStr == null || "".equals(roomIdStr)) {
			return new ArrayList();
		}
		return this.getHibernateTemplate().find(
				"from TawSystemUser user where user.cptroomid in (" + roomIdStr
						+ ") and " + StaticMethod.noDeletedCon("user"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.user.dao.TawSystemUserDao#getTawSystemUsersOfSubrole(java.lang.String[])
	 */
	public List getTawSystemUsersOfSubrole(String[] subroleIds) {
		String subroleIdStr = this.array2inStr(subroleIds);
		if (subroleIdStr == null || "".equals(subroleIdStr)) {
			return new ArrayList();
		}
		return this
				.getHibernateTemplate()
				.find(
						"select distinct user from TawSystemUser user,TawSystemUserRefRole ref where ref.subRoleid in ("
								+ subroleIdStr
								+ ") and user.userid=ref.userid and "
								+ StaticMethod.noDeletedCon("user"));
	}

	/**
	 * 将数组转为in（用于查询）的字符串
	 * 
	 * @param array
	 *            数组
	 * @return in（用于查询）的字符串
	 */
	private String array2inStr(String[] array) {
		if (array == null || !(array.length > 0)) {
			return "";
		}
		String instr = "";
		for (int i = 0; i < array.length; i++) {
			instr += "'" + array[i] + "',";
		}
		instr = StaticMethod.removeLastStr(instr, ",");
		return instr;

	}

	public List getUserBydeptidAndSubs(String _deptId) {
		String hql = " from TawSystemUser systemuser"
				+ " where systemuser.deptid like '"
				+ _deptId
				+ "%' and systemuser.userid !='admin' and systemuser.isPartners='0' order by username";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;
	}

	public List getUserLike(String deptid) {
		String hql = " from TawSystemUser systemuser where systemuser.deptid like '"
				+ deptid
				+ "%' and systemuser.userid !='admin' and systemuser.deleted='0' order by username ";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;
	}

	public List getUserBydeptidAndSubsPartner(String _deptId) {
		String hql = " from TawSystemUser systemuser"
				+ " where systemuser.deptid like '"
				+ _deptId
				+ "%' and systemuser.userid !='admin' and systemuser.isPartners='1' order by username";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);

		return list;
	}
	/**
	 * 根据手机号和userid获取user对象
	 * @param mobile
	 * @param userid
	 * @return
	 */
	public List getUserByIdMobile (String name){
		String hql = " from TawSystemUser systemuser"
			+ " where systemuser.mobile ='"+name+"'";
	List list = new ArrayList();
	list = (ArrayList) getHibernateTemplate().find(hql);
	
	return list;
	}
	
	public List listByNameQuery(String q)
	{
		String hql = "select u.username, u.userid from TawSystemUser u where u.username like '%" + q + "%' and deleted='0'  order by username";
		List list = new ArrayList();
		list = (ArrayList)getHibernateTemplate().find(hql);
		return list;
	}
	

}

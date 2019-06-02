package com.boco.eoms.commons.system.role.dao.hibernate;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.role.dao.TawSystemSubRoleDao;
import com.boco.eoms.commons.system.role.model.TawSystemRole;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.util.RoleXmlSchema;

public class TawSystemSubRoleDaoHibernate extends BaseDaoHibernate implements
		TawSystemSubRoleDao, ID2NameDAO {

	/**
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemSubRoleDao#getTawSystemSubRoles(com.boco.eoms.commons.system.role.model.TawSystemSubRole)
	 */
	public List getTawSystemSubRoles(final long roleId) {
		return getHibernateTemplate().find(
				"from TawSystemSubRole as t where t.roleId=" + roleId
						+ " and t.deleted='0'");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawSystemSubRole == null) {
		 * return getHibernateTemplate().find("from TawSystemSubRole"); } else { //
		 * filter on properties set in the tawSystemSubRole HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex =
		 * Example.create(tawSystemSubRole).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return session.createCriteria(TawSystemSubRole.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemSubRoleDao#getTawSystemSubRoles(String
	 *      whereStr)
	 */
	public List getTawSystemSubRoles(final String whereStr) {
		String queryStr = "from TawSystemSubRole";
		if (whereStr != null && whereStr.length() > 0)
			queryStr += " where " + whereStr + " and deleted='0'";
		else
			queryStr += " where deleted='0'";
		return getHibernateTemplate().find(queryStr);
	}

	/**
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemSubRoleDao#getTawSystemSubRole(long
	 *      id)
	 */
	public TawSystemSubRole getTawSystemSubRole(final String id) {
		TawSystemSubRole tawSystemSubRole = (TawSystemSubRole) getHibernateTemplate()
				.get(TawSystemSubRole.class, id);
		return tawSystemSubRole;
	}

	/**
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemSubRoleDao#saveTawSystemSubRole(TawSystemSubRole
	 *      tawSystemSubRole)
	 */
	public void saveTawSystemSubRole(final TawSystemSubRole tawSystemSubRole) {
		// String logo =
		// ""+tawSystemSubRole.getDeptId()+StaticMethod.null2String(tawSystemSubRole.getType1())+StaticMethod.null2String(tawSystemSubRole.getType2())+StaticMethod.null2String(tawSystemSubRole.getType3())+
		// StaticMethod.null2String(tawSystemSubRole.getType4())+tawSystemSubRole.getRoleId();
		// tawSystemSubRole.setLogo(logo);

		if ((tawSystemSubRole.getId() == null)) {
			// TawSystemSubRole subRole = this.getTawSystemSubRoleByLogo(logo);
			// if(subRole==null){
			getHibernateTemplate().save(tawSystemSubRole);
			getHibernateTemplate().flush();
			getHibernateTemplate().clear();
			// }
		} else
			getHibernateTemplate().saveOrUpdate(tawSystemSubRole);
	}

	/**
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemSubRoleDao#removeTawSystemSubRole(long
	 *      id)
	 */
	public void removeTawSystemSubRole(final String id) {
		// getHibernateTemplate().delete(getTawSystemSubRole(id));
		TawSystemSubRole tawSystemSubRole = this.getTawSystemSubRole(id);
		tawSystemSubRole.setDeleted("1");
		getHibernateTemplate().saveOrUpdate(tawSystemSubRole);
	}

	public void removeTawSystemSubRole(final long roleId) {
		List list = this.getSubRolesByRoleId(roleId);
		for (int i = 0; i < list.size(); i++) {
			TawSystemSubRole tawSystemSubRole = (TawSystemSubRole) list.get(i);
			tawSystemSubRole.setDeleted("1");
			getHibernateTemplate().saveOrUpdate(tawSystemSubRole);
		}
	}

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ�� whereStr
	 * where�������䣬������"where"��ͷ,����Ϊ��
	 */
	public Map getTawSystemSubRoles(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the tawSystemSubRole
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawSystemSubRole";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += " where " + whereStr + " and deleted='0'";
				else
					queryStr += " where deleted='0'";
				String queryCountStr = "select count(*) " + queryStr;

				Integer total = (Integer) session.createQuery(queryCountStr)
						.iterate().next();
				Query query = session.createQuery(queryStr+" order by subRoleName");
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

	public Map getTawSystemSubRoles(final Integer curPage,
			final Integer pageSize) {
		return this.getTawSystemSubRoles(curPage, pageSize, null);
	}
	
    /**
     * 根据开始查询的记录序数start、每页最多记录数limit和查询条件whereStr查询子角色（分页）。
     * 返回Map对象：
     *   {List} result 查询子角色结果集的当前分页的list
     *   {Integer} total 子角色结果集的总数
     * @param start 开始查询序数
     * @param limit 每页记录数
     * @param whereStr 条件sql字符串
     * @return Map
     */
	public Map mapTawSystemSubRoles(final Integer start,
			final Integer limit, final String whereStr) {
		// filter on properties set in the tawSystemSubRole
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawSystemSubRole";
				if (whereStr != null && whereStr.length() > 0)
					queryStr += " where " + whereStr + " and deleted='0'";
				else
					queryStr += " where deleted='0'";
				String queryCountStr = "select count(*) " + queryStr;

				Integer total = (Integer) session.createQuery(queryCountStr)
						.iterate().next();
				Query query = session.createQuery(queryStr+" order by subRoleName");
				query.setFirstResult(start.intValue());
				query.setMaxResults(limit.intValue());
				List result = query.list();
				HashMap map = new HashMap();
				map.put("total", total);
				map.put("result", result);
				return map;
			}
		};
		return (Map) getHibernateTemplate().execute(callback);
	}
	/**
	 * 获取某角色下所有子角色
	 * 
	 * @param roleId
	 * @return
	 */
	public List getSubRolesByRoleId(final long roleId) {
		String queryStr = "from TawSystemSubRole where roleId=" + roleId
				+ " and deleted='0'";
		return getHibernateTemplate().find(queryStr);
	}

	public void createSubRolesByDept(final TawSystemRole role,
			final TawSystemDept tawSystemDept) {
		TawSystemSubRole tawSystemSubRole = new TawSystemSubRole();
		tawSystemSubRole.setRoleId(role.getRoleId());
		tawSystemSubRole.setDeptId(tawSystemDept.getDeptId());
		tawSystemSubRole.setSubRoleName(tawSystemDept.getDeptName()
				+ role.getRoleName());
		tawSystemSubRole.setLeaf(new Integer(1));
		getHibernateTemplate().save(tawSystemSubRole);
		getHibernateTemplate().flush();
		getHibernateTemplate().clear();
	}

	public List getSubRolesByDeptId(final String deptId) {
		return getHibernateTemplate().find(
				"from TawSystemSubRole as t where t.deptId='" + deptId
						+ "' and deleted='0'");
	}

	public TawSystemSubRole getTawSystemSubRoleByLogo(final String logo) {
		List list = getHibernateTemplate().find(
				"from TawSystemSubRole as t where t.logo='" + logo
						+ "' and deleted='0'");
		if (list != null && list.size() > 0)
			return (TawSystemSubRole) list.get(0);
		else
			return null;
	}

	/**
	 * 获取子角色，ID用逗号分隔
	 */
	public List getSubRolesByIds(String subRoleIds) {
		String hql = "from TawSystemSubRole as t where t.id in(" + subRoleIds
				+ ")";
		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * 
	 * @param roleId
	 *            角色类别ID
	 * @param filterHash
	 *            key为TawSystemSubRole表中区分度对应的业务名称
	 * @return List<TawSystemSubRole>
	 * @throws Exception
	 */
	public List getSubRoles(String roleId, Hashtable filterHash)
			throws Exception {
		String hql = "from TawSystemSubRole as t where t.roleId=" + roleId
				+ " and deleted='0'";

		String whereString = "";

		if (filterHash != null) {

			Enumeration eunm = filterHash.keys();

			while (eunm.hasMoreElements()) {
				String key = (String) eunm.nextElement();
				System.out.println("----err-----key-"+key);
				String value = (String) filterHash.get(key);
				whereString += " and (t."
						+ key+ "='"
						+ value + "' or t."
						+ key
						+ " is null or t."
						+ key
						+ "='')";
			}
		}

		hql += whereString;
		System.out.print("--------"+hql);

		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * 根据角色ID和USERID获取子角色列表
	 * 
	 * @param userId
	 * @param roleId
	 * @return <TawSystemSubRole>
	 */
	public List getSubRoles(String userId, String roleId) {
		String hql = "from TawSystemSubRole r,TawSystemUserRefRole t where r.id=t.subRoleid and r.roleId='"
				+ roleId + "' and t.userid='" + userId + "' and r.deleted='0'";
		List list = getHibernateTemplate().find(hql);
		List subRoleList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			subRoleList.add(((Object[]) list.get(i))[0]);
		}
		return subRoleList;

	}

	/**
	 * 部门树，根据部门ID和流程ID获取子角色
	 * 
	 * @param deptId
	 * @param systemId
	 * @return
	 */
	public List getSubRolesByDeptId(final String deptId, final String systemId) {
		String hql = "from TawSystemSubRole as t,TawSystemRole r where t.roleId=r.roleId and t.deptId='"
				+ deptId
				+ "' and r.workflowFlag='"
				+ systemId
				+ "' and t.deleted='0'";
		List list = getHibernateTemplate().find(hql);
		List subRoleList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			subRoleList.add(((Object[]) list.get(i))[0]);
		}
		return subRoleList;
	}

	/**
	 * 部门树，根据部门ID和流程ID获取子角色
	 * 
	 * @param deptId
	 * @param systemId
	 * @return
	 */
	public List getSubRolesByDeptId(final String deptId, final String systemId,
			final String roleId) {
		if (roleId == null || roleId.length() < 1)
			return this.getSubRolesByDeptId(deptId, systemId);
		else {
			String hql = "from TawSystemSubRole as t,TawSystemRole r where t.roleId=r.roleId and t.roleId="
					+ roleId
					+ " and t.deptId='"
					+ deptId
					+ "' and r.workflowFlag='"
					+ systemId
					+ "' and t.deleted='0'";
			List list = getHibernateTemplate().find(hql);
			List subRoleList = new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				subRoleList.add(((Object[]) list.get(i))[0]);
			}
			return subRoleList;
		}
	}

	/*
	 * id2name，即子角色id转为子角色名称 added by qinmin
	 * 
	 * @see com.boco.eoms.base.dao.ID2NameDAO#id2Name(java.lang.String)
	 */
	public String id2Name(final String id) throws DictDAOException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// hql，以子角色ID为条件查询
				String queryStr = " from TawSystemSubRole subRole where subRole.id=:id";

				Query query = session.createQuery(queryStr);
				// 子角色ID号
				query.setString("id", id);
				// 仅查一条
				query.setFirstResult(0);
				query.setMaxResults(1);
				List list = query.list();
				TawSystemSubRole subRole = null;

				if (list != null && !list.isEmpty()) {
					// 不为空则取dept
					subRole = (TawSystemSubRole) list.iterator().next();
				} else {
					// 为空，写入将部门名称设为未知联系人
					subRole = new TawSystemSubRole();
					subRole.setSubRoleName(Util.idNoName());
				}
				return subRole;
			}
		};

		TawSystemSubRole subRole = null;
		try {
			subRole = (TawSystemSubRole) getHibernateTemplate().execute(
					callback);
		} catch (Exception e) {
			// 若有异常则抛出daoexception,加入DAoException是为了解藕，若抛出hibernateException，这样在换orm时，如ibatis，service就要换异常捕捉
			throw new DictDAOException(e);
		}
		return subRole.getSubRoleName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.role.dao.ITawSystemRoleImportDao#removeTawSystemRoleImportByVersion(java.lang.String)
	 */
	public void removeTawSystemSubRoleByVersion(String version) {
		List list = this.getHibernateTemplate().find(
				"from TawSystemSubRole t where t.version='" + version + "'");
		if (list != null) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				TawSystemSubRole tawSystemSubRole = (TawSystemSubRole) it
						.next();
				this.removeTawSystemSubRole(tawSystemSubRole);
			}
		}

	}

	/**
	 * 数据库中真实删除
	 * 
	 * @param tawSystemSubRole
	 *            子角色对象
	 */
	public void removeTawSystemSubRole(TawSystemSubRole tawSystemSubRole) {
		getHibernateTemplate().delete(tawSystemSubRole);
	}

	/**
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemSubRoleDao#getOtherSubroles(long,
	 *      String)
	 */
	public List getOtherSubroles(long roleId, String subroleId) {
		return getHibernateTemplate().find(
				"from TawSystemSubRole subrole where subrole.roleId=" + roleId
						+ " and id<>'" + subroleId + "'");
	}

	public List getSubrolesByCon(String roleId, String condition) {
		String hql = "from TawSystemSubRole subrole where (subrole.roleId="
				+ roleId + ") and " + StaticMethod.noDeletedCon("subrole");
		if (condition != null && !"".equals(condition)) {
			condition += " and " + condition;
		}

		return getHibernateTemplate().find(hql);
	}

	public List getSubRolesByAreaIdAndRoleId(String areaId, String roleId) {
		return getHibernateTemplate().find(
				"from TawSystemSubRole subrole where subrole.roleId=" + roleId
						+ " and area='" + areaId + "' and "
						+ StaticMethod.noDeletedCon("subrole"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemSubRoleDao#listSubRole(java.lang.String,
	 *      java.lang.String)
	 */
	public List listSubRole(String areaId, int roleId) {
		return getHibernateTemplate().find(
				"from TawSystemSubRole subrole where subrole.roleId=" + roleId
						+ " and "+getDeptWhere(areaId)+" and "
						+ StaticMethod.noDeletedCon("subrole"));
						//+ " and type1 is null");//modify by wangbeiying ,cause by this method should return value inclue all subrole.
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemSubRoleDao#listSubRoleWithType1Null(java.lang.String,
	 *      java.lang.String)
	 */
	public List listSubRoleWithType1Null(String areaId, String roleId) {
		return getHibernateTemplate().find(
				"from TawSystemSubRole subrole where subrole.roleId=" + roleId
						+ " and "+getDeptWhere(areaId)+" and "
						+ StaticMethod.noDeletedCon("subrole")
						+ " and (type1 is null or type1 = '')");
	}
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.role.dao.TawSystemSubRoleDao#listSubRole(java.lang.String,
	 *      java.lang.String)
	 */
	public List listSubRoleWithType1NotNull(String areaId, String roleId) {
		
		
		
		return getHibernateTemplate().find(
				"from TawSystemSubRole subrole where subrole.roleId=" + roleId
						+ " and "+getDeptWhere(areaId)+" and "
						+ StaticMethod.noDeletedCon("subrole")
						+ " and type1 is not null");

	}
	
	private String getDeptWhere(String areaId){
		String deptwhere=null;
		if(areaId==null){
			deptwhere="(deptId='' or deptId is null or deptId='null')";
		}
		else{
			deptwhere="(deptId='" + areaId + "')";
		}
		return deptwhere;
	}
	
	public boolean getSubRoleByRole(String roleId, String subRoleId) {
		boolean flag = false;
		String hql = "from TawSystemSubRole subrole where subrole.roleId = '"+roleId+"' and subrole.id = '" +
				subRoleId + "' and subrole.deleted='0'";
		List list = getHibernateTemplate().find(hql);
		if(list.size()>0){
			TawSystemSubRole r = (TawSystemSubRole)list.get(0);
			flag = true;
		}else{
			flag = false;
		}
		return flag;
	}
	
	public List getCountyByRoleId(String areaId, int roleId) {
		String hql = "select distinct d from TawSystemArea d,TawSystemSubRole r where r.roleId="
			+ roleId + " and r.deptId='" + areaId
			+ "' and r.county=d.areaid and r.deleted='0' order by d.areaid";
	List list = getHibernateTemplate().find(hql);
	return list;
	}
	
	public List listCountySubRole(String areaId, int roleId) {
		return getHibernateTemplate().find(
				"from TawSystemSubRole subrole where subrole.roleId=" + roleId
						+ " and "+getCountyWhere(areaId)+" and "
						+ StaticMethod.noDeletedCon("subrole"));
	}
	
	private String getCountyWhere(String areaId){
		String countywhere=null;
		if(areaId==null){
			countywhere="(county='' or county is null or county='null')";
		}
		else{
			countywhere="(county='" + areaId + "')";
		}
		return countywhere;
	}
}

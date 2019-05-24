package com.boco.eoms.commons.system.dept.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.loging.BocoLog;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dept.model.TawPartnersDept;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.util.DeptConstants;
import com.boco.eoms.commons.system.dept.dao.TawSystemDeptDao;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.util.Util;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSystemDeptDaoHibernate extends BaseDaoHibernate implements
		TawSystemDeptDao, ID2NameDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.dept.dao.TawSystemDeptDao#getDepts(java.lang.Integer,
	 *      java.lang.Integer, java.lang.String)
	 */
	public Map getDepts(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		// filter on properties set in the TawSystemDept
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawSystemDept where "
						+ StaticMethod.noDeletedCon(null);
				if (whereStr != null && whereStr.length() > 0)
					queryStr += " and (" + whereStr + ")";
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
	 * @see com.boco.eoms.commons.system.dept.dao.TawSystemDeptDao#listDeptOfArea(java.lang.String)
	 */
	public List listDeptOfArea(String areaId) {

		return getHibernateTemplate().find(
				"from TawSystemDept where areaid='" + areaId + "' and "
						+ StaticMethod.noDeletedCon(null));
	}

	/**
	 * @see com.boco.eoms.commons.system.dept.dao.TawSystemDeptDao#getTawSystemDepts(com.boco.eoms.commons.system.dept.model.TawSystemDept)
	 */
	public List getTawSystemDepts(final TawSystemDept tawSystemDept) {
		return getHibernateTemplate().find("from TawSystemDept");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawSystemDept == null) {
		 * return getHibernateTemplate().find("from TawSystemDept"); } else { //
		 * filter on properties set in the tawSystemDept HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex =
		 * Example.create(tawSystemDept).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return session.createCriteria(TawSystemDept.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.system.dept.dao.TawSystemDeptDao#getTawSystemDept(String
	 *      id)
	 */
	public TawSystemDept getTawSystemDept(final Integer id) {

		TawSystemDept tawSystemDept = (TawSystemDept) getHibernateTemplate()
				.get(TawSystemDept.class, id);
		if (tawSystemDept == null) {
			BocoLog.warn(this, "uh oh, tawSystemDept with id '" + id
					+ "' not found...");
			throw new ObjectRetrievalFailureException(TawSystemDept.class, id);
		}

		return tawSystemDept;
	}

	/**
	 * @see com.boco.eoms.commons.system.dept.dao.TawSystemDeptDao#saveTawSystemDept(TawSystemDept
	 *      tawSystemDept)
	 */
	public void saveTawSystemDept(final TawSystemDept tawSystemDept) {
		if ((tawSystemDept.getId() == null)
				|| (tawSystemDept.getId().equals("")))
			getHibernateTemplate().save(tawSystemDept);
		else
			getHibernateTemplate().merge(tawSystemDept);
	}

	/**
	 * @see com.boco.eoms.commons.system.dept.dao.TawSystemDeptDao#removeTawSystemDept(String
	 *      id)
	 */
	public void removeTawSystemDept(final Integer id) {
		getHibernateTemplate().delete(getTawSystemDept(id));
	}

	/**
	 * 根据部门ID和删除标志 查询某部门信息
	 * 
	 * @param deptid
	 * @param delid
	 *            0表示全部查询 1表示查询停职或被删除的
	 * @return
	 */
	public TawSystemDept getDeptinfobydeptid(String deptid, String delid) {
		TawSystemDept sysdept = null;
		String hql = " from TawSystemDept sysdept where sysdept.deptId='"
				+ deptid + "'" + " and sysdept.deleted='" + delid + "'";
		List list = getHibernateTemplate().find(hql);
		if (list != null) {
			if (!list.isEmpty()) {
				sysdept = (TawSystemDept) list.iterator().next();
			}
		}
		return sysdept;
	}

	/**
	 * 根据部门名称和删除标志查询部门信息
	 * 
	 * @param deptname
	 * @param delid
	 *            0表示全部查询 1表示查询停职或被删除的
	 * @return
	 */
	public TawSystemDept getDeptinfoBydeptname(String deptname, String delid) {

		TawSystemDept sysdept = null;
		String hql = " from TawSystemDept sysdept where sysdept.deptName='"
				+ deptname + "'" + " and sysdept.deleted='" + delid + "'";
		List list = new ArrayList();
		list = getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			sysdept = new TawSystemDept();
			sysdept = (TawSystemDept) list.get(0);
		}
		return sysdept;

	}

	/**
	 * 根据部门ID 和删除标志 查询同级别的部门信息
	 * 
	 * @param deptid
	 * @param delid
	 *            0表示全部查询 1表示查询停职或被删除的
	 * @return
	 */
	public List getSamelevelDeptbyDeptids(String deptid, String ordercode,
			String delid) {

		String hql = " from TawSystemDept sysdept where sysdept.parentDeptid ='"
				+ deptid
				+ "'"
				+ " and sysdept.deleted='"
				+ delid
				+ "'"
				+ " and sysdept.ordercode='" + ordercode + "'";
		List list = new ArrayList();
		list = getHibernateTemplate().find(hql);

		return list;
	}

	/**
	 * 根据删除标志 查询部门的增删情况
	 * 
	 * @param delid
	 *            0表示全部查询 1表示查询停职或被删除的
	 * @return
	 */
	public List geDeptdelInfos(String delid) {

		String hql = " from TawSystemDept sysdept where sysdept.deleted='"
				+ delid + "'";
		List list = new ArrayList();
		list = getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 查询某用户创建的部门情况
	 * 
	 * @param operuserid
	 * @param delid
	 *            0表示全部查询 1表示查询停职或被删除的
	 * @return
	 */
	public List getDeptdelInfosByoperuserid(String operuserid, String delid) {

		String hql = " from TawSystemDept sysdept where sysdept.operuserid='"
				+ operuserid + "'" + " and sysdept.deleted='" + delid + "'";
		List list = new ArrayList();
		list = getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 根据deptids 和 delid 进行IN查询部门信息
	 * 
	 * @param deptids
	 * @param delid
	 *            delid 0表示全部查询 1表示查询停职或被删除的
	 * @return
	 */
	public List getDeptByinDeptidsandDelids(String deptids, String delid) {

		String hql = " from TawSystemDept sysdept where sysdept.deptId in("
				+ deptids + ")" + " and sysdept.deleted='" + delid + "'";
		List list = new ArrayList();
		list = getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 判断部门名称是否存在
	 * 
	 * @param deptname
	 * @return
	 */
	public boolean getDeptnameIsExist(String deptname, String delid) {
		boolean flag = false;
		TawSystemDept sysdept = new TawSystemDept();
		sysdept = getDeptinfoBydeptname(deptname, delid);
		if (sysdept != null) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 查询所有的部门名称
	 * 
	 * @return
	 */
	public List getDeptnames() {

		String hql = " select sysdept.deptName from TawSystemDept sysdept ";
		List list = new ArrayList();
		list = getHibernateTemplate().find(hql);
		return list;

	}
	public List getworkplanDeptnames() {

		String hql = "from TawSystemDept  where deleted= '0' ";
		List list = new ArrayList();
		list = getHibernateTemplate().find(hql);
		return list;

	}
	/**
	 * 得到下一级子部门的部门信息
	 * 
	 * @param pardeptid
	 * @param delid
	 * @return
	 */
	public List getNextLevecDepts(String pardeptid, String delid) {

		String hql = " from TawSystemDept sysdept where sysdept.parentDeptid='"
				+ pardeptid + "'" + " and sysdept.deleted='" + delid
				// + "' order by substr(sysdept.deptName,'0','1')";
				+ "' order by linkid";
		List list = getHibernateTemplate().find(hql);
		return list;

	}

	/**
	 * 得到下一级非代维部门信息 2008-12-2 wangsixuan
	 * 
	 * @param pardeptid
	 * @param delid
	 * @return
	 */
	public List getNextLevecDeptsNoPartner(String pardeptid, String delid) {

		String hql = " from TawSystemDept sysdept where sysdept.isPartners!='1' and sysdept.parentDeptid='"
				+ pardeptid + "'" + " and sysdept.deleted='" + delid
				// + "' order by substr(sysdept.deptName,'0','1')";
				+ "' order by linkid";
		List list = getHibernateTemplate().find(hql);
		return list;

	}

	/**
	 * 得到是否是代维部门信息 2008-12-2 wangsixuan
	 * 
	 * @param pardeptid
	 * @param delid
	 * @return
	 */
	public boolean isPartner(String delid) {
		String hqlDept = " from TawSystemDept sysdept where sysdept.isPartners='1' and sysdept.deleted='0' and sysdept.deptId ='"
				+ delid + "'order by linkid";
		List listDept = new ArrayList();
		listDept = (ArrayList) getHibernateTemplate().find(hqlDept);
		if (!listDept.isEmpty()) {
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 得到下一级代维子部门的部门信息 2008-11-17 liujinlong
	 * 
	 * @param pardeptid
	 * @param delid
	 * @return
	 */
	public List getNextLevecDaiweiDepts(String pardeptid, String delid) {
		String hql = " from TawSystemDept sysdept where sysdept.isPartners='1' and sysdept.parentDeptid='"
				+ pardeptid
				+ "'"
				+ " and sysdept.deleted='"
				+ delid
				+ "' order by linkid";
		if (pardeptid.equals("-1")) {// 查询代维部门根节点
			hql = " from TawSystemDept sysdept where sysdept.isPartners='1' and sysdept.isDaiweiRoot='1'"
					+ " and sysdept.deleted='" + delid + "' order by linkid";
		}

		List list = getHibernateTemplate().find(hql);
		return list;

	}

	/**
	 * 查询某个地区的部门信息
	 * 
	 * @param regionid
	 * @return
	 */
	public List getDeptRegion(Integer regionid, String delid) {

		String hql = " from TawSystemDept sysdept where sysdept.regionflag="
				+ regionid + " and sysdept.deleted='" + delid + "'";
		List list = new ArrayList();
		list = getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 查询所有的地市
	 * 
	 * @param delid
	 * @return
	 */
	public List getAllRegion(String delid) {
		List list = new ArrayList();
		String hql = " select distinct sysdept.regionflag from TawSystemDept sysdept where sysdept.deleted='"
				+ delid + "'";

		list = getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 查询所有的子部门 LINK查询
	 * 
	 * @param deptid
	 * @param delid
	 * @return
	 */
	public List getALLSondept(String deptid, String delid) {
		String hql = "";
		List list = new ArrayList();
		if (deptid.equals("-1")) {
			hql = " from TawSystemDept sysdept where sysdept.deleted='" + delid
					+ "'";
		} else {
			hql = " from TawSystemDept sysdept where sysdept.deleted='" + delid
					+ "'" + " and sysdept.deptId like '" + deptid + "%'"
					+ " and sysdept.deptId !='" + deptid
					+ "' ";
		}
		list = (ArrayList) getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 查询所有的子部门 值班用
	 * 
	 * @param deptid
	 * @param delid
	 * @return
	 */
	public List getALLdept(String deptid, String delid) {
		String hql = "";
		List list = new ArrayList();
		if (deptid.equals("-1")) {
			hql = " from TawSystemDept sysdept where sysdept.deleted='" + delid
					+ "'";
		} else {
			hql = " from TawSystemDept sysdept where sysdept.deleted='" + delid
					+ "'" + " and sysdept.deptId in ( 1," + deptid + ")";
		}
		list = (ArrayList) getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 得到所有得父部门除了根
	 * 
	 * @param deptid
	 * @param len
	 * @param deleted
	 * @return
	 */
	public List getFdeptInfo(String deptid, int len, String deleted) {

		List list = new ArrayList();
		String sql = "";
		if (deleted.equals("0") || deleted.equals("1")) {
			sql = " from TawSystemDept sysdept where sysdept.deptId like '"
					+ deptid + "%' and length(sysdept.deptId)<'" + len
					+ "' and sysdept.deleted='" + deleted + "'";
		} else {
			sql = " from TawSystemDept sysdept where sysdept.deptId like '"
					+ deptid + "%' and length(sysdept.deptId)<'" + len + "'";
		}
		list = (ArrayList) getHibernateTemplate().find(sql);

		return list;
	}

	/**
	 * 得到所有得父部门除了根 值班
	 * 
	 * @param deptid
	 * @param len
	 * @param deleted
	 * @return
	 */
	public List getFdeptInfoduty(String deptid, int len, String deleted) {

		List list = new ArrayList();
		String sql = "";
		if (deleted.equals("0") || deleted.equals("1")) {
			sql = " from TawSystemDept sysdept where sysdept.deptId like '"
					+ deptid + "%' and length(sysdept.deptId)<'" + len
					+ "' and sysdept.deleted='" + deleted + "'";
		} else {
			sql = " from TawSystemDept sysdept where sysdept.deptId like '"
					+ deptid + "%' and length(sysdept.deptId)<'" + len + "'";
		}
		list = (ArrayList) getHibernateTemplate().find(sql);

		return list;
	}

	/*
	 * id2name，即部门id转为部门名称 added by qinmin
	 * 
	 * @see com.boco.eoms.base.dao.ID2NameDAO#id2Name(java.lang.String)
	 */
	public String id2Name(final String id) throws DictDAOException {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// hql，以部门ID为条件查询
				String queryStr = " from TawSystemDept dept where dept.deptId=:deptId and deleted=0";

				Query query = session.createQuery(queryStr);
				// 部门ID号
				query.setString("deptId", id);
				// 仅查一条
				query.setFirstResult(0);
				query.setMaxResults(1);
				List list = query.list();
				TawSystemDept dept = null;

				if (list != null && !list.isEmpty()) {
					// 不为空则取dept
					dept = (TawSystemDept) list.iterator().next();
				} else {
					// 为空，写入将部门名称设为未知联系人
					dept = new TawSystemDept();
					dept.setDeptName(Util.idNoName());
				}
				return dept;
			}
		};

		TawSystemDept dept = null;
		try {
			dept = (TawSystemDept) getHibernateTemplate().execute(callback);
		} catch (Exception e) {
			// 若有异常则抛出daoexception,加入DAoException是为了解藕，若抛出hibernateException，这样在换orm时，如ibatis，service就要换异常捕捉
			throw new DictDAOException(e);
		}
		return dept.getDeptName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.dept.dao.TawSystemDeptDao#getRootDept()
	 */
	public List getRootDept() {
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				// hql，以部门ID为条件查询
				String queryStr = " from TawSystemDept dept where dept.parentDeptid=:parentDeptid and "
						+ StaticMethod.noDeletedCon("dept");

				Query query = session.createQuery(queryStr);
				// 部门ID号
				query.setString("parentDeptid",
						DeptConstants.ROOT_PARENTDEPT_ID);

				return query.list();

			}
		};

		return (List) getHibernateTemplate().execute(callback);

	}

	/**
	 * 根据关键字查询部门名称，得到部门列表
	 * 
	 * @param word
	 *            部门名称关键字
	 * @return List
	 */
	public List searchByName(String word) {
		String hql = "from TawSystemDept d where d.deptName like '%" + word
				+ "%' order by deptName";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		return list;
	}
	
	/**
	 * 根据关键字查询部门名称，得到部门列表，只是非代维部门
	 * 2008-12-3 wangsixuan
	 * @param word
	 *            部门名称关键字
	 * @return List
	 */
	public List searchByNameNoPartner(String word) {
		String hql = "from TawSystemDept d where d.isPartners!='1' and d.deptName like '%" + word
				+ "%' order by deptName";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		return list;
	}
	
	/**
	 * 根据关键字查询部门名称，得到部门列表，只是代维部门
	 * 2008-12-3 wangsixuan
	 * @param word
	 *            部门名称关键字
	 * @return List
	 */
	public List searchByNamePartner(String word,String contacter,String deptphone) {
		String hql = "from TawSystemDept d where 1=1 and d.isPartners = '1'";
		if(word!=null&&!"".equals(word))
			hql += " and d.deptName like '%" + word + "%'";
		if(contacter!=null&&!"".equals(contacter))
			hql += " and d.deptmanager like '%" + contacter + "%'";
		if(deptphone!=null&&!"".equals(deptphone))
			hql += " and d.deptphone like '%" + deptphone + "%'";
		hql += " order by deptName";
		List list = new ArrayList();
		list = (ArrayList) getHibernateTemplate().find(hql);
		return list;
	}

	public List getNextLikeDepts(String parentLinkId, String deleted) {
		String hql = " from TawSystemDept sysdept where sysdept.parentLinkid like '"
				+ parentLinkId + "%'" + " and sysdept.deleted='" + deleted
				// + "' order by substr(sysdept.deptName,'0','1')";
				+ "' order by linkid";
		List list = getHibernateTemplate().find(hql);
		return list;
	}
	public TawSystemDept getTawSystemDeptById(Integer id) {
		TawSystemDept tawSystemDept = (TawSystemDept) getHibernateTemplate().get(TawSystemDept.class, id);
		return tawSystemDept;
	}

	/**
	 * 根据linkID和删除标志 查询某部门信息
	 * 
	 * @param linkid
	 * @param delid
	 *            0表示全部查询 1表示查询停职或被删除的
	 * @return
	 */
	public TawSystemDept getDeptinfobylinkid(String linkid, String delid) {
		TawSystemDept sysdept = null;
		String hql = " from TawSystemDept where linkId='"
				+ linkid + "'" + " and deleted='" + delid + "'";
		List list = getHibernateTemplate().find(hql);
		if (list != null) {
			if (!list.isEmpty()) {
				sysdept = (TawSystemDept) list.iterator().next();
			}
		}
		return sysdept;
	}

}

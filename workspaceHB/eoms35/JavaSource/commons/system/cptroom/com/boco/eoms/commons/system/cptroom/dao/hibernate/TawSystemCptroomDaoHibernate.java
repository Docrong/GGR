package com.boco.eoms.commons.system.cptroom.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom;
import com.boco.eoms.commons.system.cptroom.dao.TawSystemCptroomDao;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSystemCptroomDaoHibernate extends BaseDaoHibernate implements
		TawSystemCptroomDao, ID2NameDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 */
	public String id2Name(String id) throws DictDAOException {
		TawSystemCptroom room = this.getTawSystemCptroom(new Integer(id));
		if (room != null) {
			return room.getRoomname();
		}
		return "";
	}

	/**
	 * @see com.boco.eoms.commons.system.cptroom.dao.TawSystemCptroomDao#getTawSystemCptrooms(com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom)
	 */
	public List getTawSystemCptrooms(final TawSystemCptroom tawSystemCptroom) {
		return getHibernateTemplate().find("from TawSystemCptroom");

		/*
		 * Remove the line above and uncomment this code block if you want to
		 * use Hibernate's Query by Example API. if (tawSystemCptroom == null) {
		 * return getHibernateTemplate().find("from TawSystemCptroom"); } else { //
		 * filter on properties set in the tawSystemCptroom HibernateCallback
		 * callback = new HibernateCallback() { public Object
		 * doInHibernate(Session session) throws HibernateException { Example ex =
		 * Example.create(tawSystemCptroom).ignoreCase().enableLike(MatchMode.ANYWHERE);
		 * return session.createCriteria(TawSystemCptroom.class).add(ex).list(); } };
		 * return (List) getHibernateTemplate().execute(callback); }
		 */
	}

	/**
	 * @see com.boco.eoms.commons.system.cptroom.dao.TawSystemCptroomDao#getTawSystemCptroom(Integer
	 *      id)
	 */
	public TawSystemCptroom getTawSystemCptroom(final Integer id) {
		TawSystemCptroom tawSystemCptroom = (TawSystemCptroom) getHibernateTemplate()
				.get(TawSystemCptroom.class, id);
		if (tawSystemCptroom == null) {
			throw new ObjectRetrievalFailureException(TawSystemCptroom.class,
					id);
		}

		return tawSystemCptroom;
	}

	/**
	 * @see com.boco.eoms.commons.system.cptroom.dao.TawSystemCptroomDao#saveTawSystemCptroom(TawSystemCptroom
	 *      tawSystemCptroom)
	 */
	public void saveTawSystemCptroom(final TawSystemCptroom tawSystemCptroom) {
		if ((tawSystemCptroom.getId() == null)
				|| (tawSystemCptroom.getId().equals("")))
			getHibernateTemplate().save(tawSystemCptroom);
		else
			getHibernateTemplate().merge(tawSystemCptroom);
	}

	/**
	 * Deletes a tawSystemCptroom's information
	 * 
	 * @param tawSystemCptroom
	 */
	public void removeTawSystemCptroom(TawSystemCptroom tawSystemCptroom) {
		getHibernateTemplate().delete(tawSystemCptroom);
	}

	/**
	 * @see com.boco.eoms.commons.system.cptroom.dao.TawSystemCptroomDao#removeTawSystemCptroom(Integer
	 *      id)
	 */
	public void removeTawSystemCptroom(final Integer id) {
		getHibernateTemplate().delete(getTawSystemCptroom(id));
	}

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ�� whereStr
	 * where�������䣬������"where"��ͷ,����Ϊ��
	 */
	public Map getTawSystemCptrooms(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the tawSystemCptroom
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from TawSystemCptroom";
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

	public Map getTawSystemCptrooms(final Integer curPage,
			final Integer pageSize) {
		return this.getTawSystemCptrooms(curPage, pageSize, null);
	}

	/**
	 * @author 孙圣泰
	 * @see 通过机房ID、删除标记取机房model
	 * @param Integer
	 *            id 机房ID
	 * @param int
	 *            deleted 删除标记
	 * @return TawSystemCptroom 机房model
	 */
	public TawSystemCptroom getTawSystemCptroomById(Integer id, int deleted) {
		String hql = "  from TawSystemCptroom operation where operation.id='"
				+ id + "' and operation.deleted='" + deleted + "'";
		// System.out.println("hql====="+hql);
		TawSystemCptroom operation = null;
		List list = getHibernateTemplate().find(hql);// modify by guanming
		// 2008/6/4
		if (list != null && !list.isEmpty()) {
			operation = (TawSystemCptroom) list.get(0);
		}
		return operation;
	}

	/**
	 * @author 孙圣泰
	 * @see 通过机房名称、删除标记取机房model
	 * @param String
	 *            String 机房名称
	 * @param int
	 *            deleted 删除标记
	 * @return TawSystemCptroom 机房model
	 */
	public TawSystemCptroom getTawSystemCptroomByRoomname(String roomname,
			int deleted) {
		String hql = "  from TawSystemCptroom operation where operation.roomname='"
				+ roomname + "' and operation.deleted='" + deleted + "'";
		TawSystemCptroom operation = new TawSystemCptroom();
		operation = (TawSystemCptroom) getHibernateTemplate().find(hql).get(0);
		return operation;
	}

	/**
	 * @author 孙圣泰
	 * @see 通过机房ID、机房名称和删除标记取机房model
	 * @param Integer
	 *            id 机房ID
	 * @param String
	 *            roomname 机房名称
	 * @param int
	 *            deleted 删除标记
	 * @return TawSystemCptroom 机房model
	 */
	public TawSystemCptroom getTawSystemCptroom(Integer id, String roomname,
			int deleted) {
		String hql = "  from TawSystemCptroom operation where operation.id='"
				+ id + "' and operation.roomname='" + roomname
				+ "' and operation.deleted='" + deleted + "'";
		TawSystemCptroom operation = new TawSystemCptroom();
		operation = (TawSystemCptroom) getHibernateTemplate().find(hql).get(0);
		return operation;
	}

	/**
	 * @see 根据机房ID取机房名称
	 * @param Integer
	 *            id 机房ID
	 * @author 孙圣泰
	 */
	public String getTawSystemCptroomName(Integer id) {
		String cptroomname = "";
		String hql = "  from TawSystemCptroom operation where operation.id='"
				+ id + "' and operation.deleted='" + StaticVariable.UNDELETED
				+ "'";
		TawSystemCptroom operation = new TawSystemCptroom();
		List list = getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			operation = (TawSystemCptroom) getHibernateTemplate().find(hql)
					.get(0);
			cptroomname = operation.getRoomname();
		}

		return cptroomname;
	}

	/**
	 * @see 通过用户的部门编号、删除标志得到该部门的机房列表
	 * @author 孙圣泰
	 * @param deptId
	 *            String 查询条件
	 * @return List集合，子元素是TawSystemCptroom机房名称和机房ID。
	 */
	public List getTawSystemCptroomList(String deptid, int deleted) {
		ArrayList list = new ArrayList();
		ArrayList returnList = new ArrayList();
		String hql = "  from TawSystemCptroom operation where operation.deptid='"
				+ deptid + "' and operation.deleted='" + deleted + "'";
		list = (ArrayList) getHibernateTemplate().find(hql);
		int listSize = list.size();
		for (int i = 0; i < listSize; i++) {
			TawSystemCptroom tawSystemCptroom = new TawSystemCptroom();
			tawSystemCptroom = (TawSystemCptroom) list.get(i);
			returnList.add(new org.apache.struts.util.LabelValueBean(
					StaticMethod.dbNull2String(tawSystemCptroom.getRoomname()),
					StaticMethod.dbNull2String(String.valueOf(tawSystemCptroom
							.getId()))));
		}
		return returnList;
	}

	/**
	 * @author 孙圣泰
	 * @see 根据机房ID取机房地址
	 * @param Integer
	 *            id 机房ID
	 * @return String 机房地址
	 */
	public String getTawSystemCptroomAddress(Integer id) {
		String cptroomaddress = "";
		String hql = "  from TawSystemCptroom operation where operation.id='"
				+ id + "' and operation.deleted='" + StaticVariable.UNDELETED
				+ "'";
		TawSystemCptroom operation = new TawSystemCptroom();
		operation = (TawSystemCptroom) getHibernateTemplate().find(hql).get(0);
		cptroomaddress = operation.getDeptid();
		return cptroomaddress;
	}

	/**
	 * @author 孙圣泰
	 * @see 得到全部的机房列表
	 * @return List集合，子元素是TawSystemCptroom机房名称和机房ID。
	 */
	public List getTawSystemCptroomList() {
		ArrayList list = new ArrayList();
		ArrayList returnList = new ArrayList();
		String hql = "  from TawSystemCptroom operation where operation.deleted='"
				+ StaticVariable.UNDELETED + "'";
		list = (ArrayList) getHibernateTemplate().find(hql);
		int listSize = list.size();
		for (int i = 0; i < listSize; i++) {
			TawSystemCptroom tawSystemCptroom = new TawSystemCptroom();
			tawSystemCptroom = (TawSystemCptroom) list.get(i);
			returnList.add(new org.apache.struts.util.LabelValueBean(
					StaticMethod.null2String(tawSystemCptroom.getRoomname()),
					StaticMethod.null2String(String.valueOf(tawSystemCptroom
							.getId()))));
		}
		return returnList;
	}

	/**
	 * @author 孙圣泰
	 * @see 通过用户的部门编号得到该部门的机房列表
	 * @param deptid
	 *            部门ID
	 * @return List 机房model的list
	 */
	public List getTawSystemCptroomListByDeptid(String deptid) {
		ArrayList list = new ArrayList();
		ArrayList returnList = new ArrayList();
		String hql = "  from TawSystemCptroom operation where operation.deptid='"
				+ deptid
				+ "' and operation.deleted='"
				+ StaticVariable.UNDELETED + "'";
		list = (ArrayList) getHibernateTemplate().find(hql);
		int listSize = list.size();
		for (int i = 0; i < listSize; i++) {
			TawSystemCptroom tawSystemCptroom = new TawSystemCptroom();
			tawSystemCptroom = (TawSystemCptroom) list.get(i);
			returnList.add(new org.apache.struts.util.LabelValueBean(
					StaticMethod.dbNull2String(tawSystemCptroom.getRoomname()),
					StaticMethod.dbNull2String(String.valueOf(tawSystemCptroom
							.getId()))));
		}
		return returnList;
	}

	/**
	 * @author 孙圣泰
	 * @see 根据父节点和删除标志得到下一级子机房的部门信息
	 * @param parentid
	 * @param delid
	 * @return List
	 */
	public List getNextLevelCptrooms(String parentid, String delid) {
		String hql = " from TawSystemCptroom syscptroom where syscptroom.parentid='"
				+ parentid
				+ "'"
				+ " and syscptroom.deleted='"
				+ delid
				+ "' order by substr(syscptroom.roomname,'0','1')";
		List list = new ArrayList();
		list = getHibernateTemplate().find(hql);
		return list;
	}

}

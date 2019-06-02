package com.boco.eoms.km.cptroom.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.common.util.StaticVariable;
import com.boco.eoms.km.cptroom.model.KmsystemCptroom;
import com.boco.eoms.km.cptroom.dao.KmsystemCptroomDao;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class KmsystemCptroomDaoHibernate extends BaseDaoHibernate implements
        KmsystemCptroomDao, ID2NameDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.commons.system.dict.dao.ID2NameDAO#id2Name(java.lang.String)
	 */
	public String id2Name(String id) throws DictDAOException {
		KmsystemCptroom room = this.getKmsystemCptroom(new Integer(id));
		if (room != null) {
			return room.getRoomname();
		}
		return "";
	}

	/**
	 * @see com.boco.eoms.commons.system.cptroom.dao.KmsystemCptroomDao#getKmsystemCptrooms(com.boco.eoms.commons.system.cptroom.model.KmsystemCptroom)
	 */
	public List getKmsystemCptrooms(final KmsystemCptroom kmsystemCptroom) {
		return getHibernateTemplate().find("from KmsystemCptroom");

	}

	/**
	 * @see com.boco.eoms.commons.system.cptroom.dao.KmsSystemCptroomDao#getKmsystemCptroom(Integer
	 *      id)
	 */
	public KmsystemCptroom getKmsystemCptroom(final Integer id) {
		KmsystemCptroom kmsystemCptroom = (KmsystemCptroom) getHibernateTemplate()
				.get(KmsystemCptroom.class, id);
		if (kmsystemCptroom == null) {
			throw new ObjectRetrievalFailureException(KmsystemCptroom.class,
					id);
		}

		return kmsystemCptroom;
	}

	/**
	 * @see com.boco.eoms.commons.system.cptroom.dao.KmsystemCptroomDao#saveKmsystemCptroom(KmsystemCptroom
	 *      KmsystemCptroom)
	 */
	public void saveKmsystemCptroom(final KmsystemCptroom kmsystemCptroom) {
		getHibernateTemplate().clear();
		if ((kmsystemCptroom.getId() == null)
				|| (kmsystemCptroom.getId().equals("")))
			getHibernateTemplate().save(kmsystemCptroom);
		else
			getHibernateTemplate().saveOrUpdate(kmsystemCptroom);
	}

	/**
	 * Deletes a KmsystemCptroom's information
	 * 
	 * @param KmsystemCptroom
	 */
	public void removeKmsystemCptroom(KmsystemCptroom kmsystemCptroom) {
		getHibernateTemplate().delete(kmsystemCptroom);
	}

	/**
	 * @see com.boco.eoms.commons.system.cptroom.dao.KmsystemCptroomDao#removeKmsystemCptroom(Integer
	 *      id)
	 */
	public void removeKmsystemCptroom(final Integer id) {
		getHibernateTemplate().delete(getKmsystemCptroom(id));
	}

	public Map getKmsystemCptrooms(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		// filter on properties set in the KmsystemCptroom
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				String queryStr = "from KmsystemCptroom";
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

	public Map getKmsystemCptrooms(final Integer curPage,
			final Integer pageSize) {
		return this.getKmsystemCptrooms(curPage, pageSize, null);
	}

	/**
	 * @author 孙圣泰
	 * @see 通过机房ID、删除标记取机房model
	 * @param Integer
	 *            id 机房ID
	 * @param int
	 *            deleted 删除标记
	 * @return KmsystemCptroom 机房model
	 */
	public KmsystemCptroom getKmsystemCptroomById(Integer id, int deleted) {
		String hql = "  from KmsystemCptroom operation where operation.id='"
				+ id + "' and operation.deleted='" + deleted + "'";
		// System.out.println("hql====="+hql);
		KmsystemCptroom operation = null;
		List list = getHibernateTemplate().find(hql);// modify by guanming
		// 2008/6/4
		if (list != null && !list.isEmpty()) {
			operation = (KmsystemCptroom) list.get(0);
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
	 * @return KmsystemCptroom 机房model
	 */
	public KmsystemCptroom getKmsystemCptroomByRoomname(String roomname,
			int deleted) {
		String hql = "  from KmsystemCptroom operation where operation.roomname='"
				+ roomname + "' and operation.deleted='" + deleted + "'";
		KmsystemCptroom operation = new KmsystemCptroom();
		operation = (KmsystemCptroom) getHibernateTemplate().find(hql).get(0);
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
	 * @return KmsystemCptroom 机房model
	 */
	public KmsystemCptroom getKmsystemCptroom(Integer id, String roomname,
			int deleted) {
		String hql = "  from KmsystemCptroom operation where operation.id='"
				+ id + "' and operation.roomname='" + roomname
				+ "' and operation.deleted='" + deleted + "'";
		KmsystemCptroom operation = new KmsystemCptroom();
		operation = (KmsystemCptroom) getHibernateTemplate().find(hql).get(0);
		return operation;
	}

	/**
	 * @see 根据机房ID取机房名称
	 * @param Integer
	 *            id 机房ID
	 * @author 孙圣泰
	 */
	public String getKmsystemCptroomName(Integer id) {
		String cptroomname = "";
		String hql = "  from KmsystemCptroom operation where operation.id='"
				+ id + "' and operation.deleted='" + StaticVariable.UNDELETED
				+ "'";
		KmsystemCptroom operation = new KmsystemCptroom();
		List list = getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			operation = (KmsystemCptroom) getHibernateTemplate().find(hql)
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
	 * @return List集合，子元素是KmsystemCptroom机房名称和机房ID。
	 */
	public List getKmsystemCptroomList(String deptid, int deleted) {
		ArrayList list = new ArrayList();
		ArrayList returnList = new ArrayList();
		String hql = "  from KmsystemCptroom operation where operation.deptid='"
				+ deptid + "' and operation.deleted='" + deleted + "'";
		list = (ArrayList) getHibernateTemplate().find(hql);
		int listSize = list.size();
		for (int i = 0; i < listSize; i++) {
			KmsystemCptroom kmsystemCptroom = new KmsystemCptroom();
			kmsystemCptroom = (KmsystemCptroom) list.get(i);
			returnList.add(new org.apache.struts.util.LabelValueBean(
					StaticMethod.dbNull2String(kmsystemCptroom.getRoomname()),
					StaticMethod.dbNull2String(String.valueOf(kmsystemCptroom
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
	public String getKmsystemCptroomAddress(Integer id) {
		String cptroomaddress = "";
		String hql = "  from KmsystemCptroom operation where operation.id='"
				+ id + "' and operation.deleted='" + StaticVariable.UNDELETED
				+ "'";
		KmsystemCptroom operation = new KmsystemCptroom();
		operation = (KmsystemCptroom) getHibernateTemplate().find(hql).get(0);
		cptroomaddress = operation.getDeptid();
		return cptroomaddress;
	}

	/**
	 * @author 孙圣泰
	 * @see 得到全部的机房列表
	 * @return List集合，子元素是KmsystemCptroom机房名称和机房ID。
	 */
	public List getKmsystemCptroomList() {
		ArrayList list = new ArrayList();
		ArrayList returnList = new ArrayList();
		String hql = "  from KmsystemCptroom operation where operation.deleted='"
				+ StaticVariable.UNDELETED + "'";
		list = (ArrayList) getHibernateTemplate().find(hql);
		int listSize = list.size();
		for (int i = 0; i < listSize; i++) {
			KmsystemCptroom kmsystemCptroom = new KmsystemCptroom();
			kmsystemCptroom = (KmsystemCptroom) list.get(i);
			returnList.add(new org.apache.struts.util.LabelValueBean(
					StaticMethod.null2String(kmsystemCptroom.getRoomname()),
					StaticMethod.null2String(String.valueOf(kmsystemCptroom
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
	public List getKmsystemCptroomListByDeptid(String deptid) {
		ArrayList list = new ArrayList();
		ArrayList returnList = new ArrayList();
		String hql = "  from KmsystemCptroom operation where operation.deptid='"
				+ deptid
				+ "' and operation.deleted='"
				+ StaticVariable.UNDELETED + "'";
		list = (ArrayList) getHibernateTemplate().find(hql);
		int listSize = list.size();
		for (int i = 0; i < listSize; i++) {
			KmsystemCptroom KmsystemCptroom = new KmsystemCptroom();
			KmsystemCptroom = (KmsystemCptroom) list.get(i);
			returnList.add(new org.apache.struts.util.LabelValueBean(
					StaticMethod.dbNull2String(KmsystemCptroom.getRoomname()),
					StaticMethod.dbNull2String(String.valueOf(KmsystemCptroom
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
		String hql = " from KmsystemCptroom syscptroom where syscptroom.parentid='"
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

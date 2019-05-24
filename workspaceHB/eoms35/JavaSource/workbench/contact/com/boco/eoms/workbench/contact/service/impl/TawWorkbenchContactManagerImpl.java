package com.boco.eoms.workbench.contact.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.workbench.contact.dao.TawWorkbenchContactDao;
import com.boco.eoms.workbench.contact.dao.TawWorkbenchDeptContactDao;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContact;
import com.boco.eoms.workbench.contact.model.TawWorkbenchDeptContact;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchContactManager;

/**
 * <p>
 * Title:个人通讯录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 22, 2008 15:59:30 AM
 * </p>
 * 
 * @author 龚玉峰
 * @version 3.5.1
 * 
 */
public class TawWorkbenchContactManagerImpl extends BaseManager implements
		ITawWorkbenchContactManager {
	private TawWorkbenchContactDao dao;

	/**
	 * 部门通迅录
	 */
	private TawWorkbenchDeptContactDao tawWorkbenchDeptContactDao;

	/**
	 * @param tawWorkbenchDeptContactDao
	 *            the tawWorkbenchDeptContactDao to set
	 */
	public void setTawWorkbenchDeptContactDao(
			TawWorkbenchDeptContactDao tawWorkbenchDeptContactDao) {
		this.tawWorkbenchDeptContactDao = tawWorkbenchDeptContactDao;
	}

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawWorkbenchContactDao(TawWorkbenchContactDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.workbench.contact.service.ITawWorkbenchContactManager#getTawWorkbenchContacts(com.boco.eoms.workbench.contact.model.TawWorkbenchContact)
	 */
	public List getTawWorkbenchContacts(
			final TawWorkbenchContact tawWorkbenchContact) {
		return dao.getTawWorkbenchContacts(tawWorkbenchContact);
	}

	/**
	 * @see com.boco.eoms.workbench.contact.service.ITawWorkbenchContactManager#getTawWorkbenchContact(String
	 *      id)
	 */
	public TawWorkbenchContact getTawWorkbenchContact(final String id) {
		return dao.getTawWorkbenchContact(new String(id));
	}

	/**
	 * @see com.boco.eoms.workbench.contact.service.ITawWorkbenchContactManager#saveTawWorkbenchContact(TawWorkbenchContact
	 *      tawWorkbenchContact)
	 */
	public void saveTawWorkbenchContact(TawWorkbenchContact tawWorkbenchContact) {
		dao.saveTawWorkbenchContact(tawWorkbenchContact);
	}

	/**
	 * @see com.boco.eoms.workbench.contact.service.ITawWorkbenchContactManager#removeTawWorkbenchContact(String
	 *      id)
	 */
	public void removeTawWorkbenchContact(final String id) {
		dao.removeTawWorkbenchContact(new String(id));
	}

	/**
	 * 
	 */
	public Map getTawWorkbenchContacts(final Integer curPage,
			final Integer pageSize) {
		return dao.getTawWorkbenchContacts(curPage, pageSize, null);
	}

	public Map getTawWorkbenchContacts(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		return dao.getTawWorkbenchContacts(curPage, pageSize, whereStr);
	}

	public List getSonsById(String parentid) {
		return dao.getSonsById(parentid);
	}

	// 根据分组id得到分组下的人员列表
	public List getNextLevecContacts(final String userid, final String id,
			final String deleted) {
		return dao.getNextLevecContacts(userid, id, deleted);
	}

	public String getUserStrById(final String id) {
		return dao.getUserStrById(id);
	}

	public String getTeleByUserId(final String userId) {
		return dao.getTeleByUserId(userId);
	}

	public String getEmailByUserId(final String userId) {
		return dao.getEmailByUserId(userId);
	}

	public boolean getContactName(String contactName, String userid) {
		boolean bool = false;
		List list = new ArrayList();
		list = dao.getContactName(contactName, userid);
		if (list.size() == 0) {
			bool = true;
		}
		return bool;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.contact.service.ITawWorkbenchContactManager#getContacts(java.lang.String,
	 *      java.lang.String)
	 */
	public List getContacts(String name, String userId, String deptId) {
		List deptContacts = tawWorkbenchDeptContactDao
				.getContacts(name, deptId);
		List contacts = dao.getContactsByName(name, userId);

		if (deptContacts != null) {
			for (Iterator it = deptContacts.iterator(); it.hasNext();) {
				TawWorkbenchDeptContact tawWorkbenchDeptContact = (TawWorkbenchDeptContact) it
						.next();
				// 将部门通迅录合并为个人通迅录
				TawWorkbenchContact tawWorkbenchContact = new TawWorkbenchContact();
				// 地址
				tawWorkbenchContact.setAddress(tawWorkbenchDeptContact
						.getAddress());
				// 联系人名称
				tawWorkbenchContact.setContactName(tawWorkbenchDeptContact
						.getContactName());
				// 部门id
				tawWorkbenchContact.setDeptId(tawWorkbenchDeptContact
						.getDeptId());
				// 部门名称
				tawWorkbenchContact.setDeptName(tawWorkbenchDeptContact
						.getDeptName());
				// email
				tawWorkbenchContact
						.setEmail(tawWorkbenchDeptContact.getEmail());
				// 电话
				tawWorkbenchContact.setTele(tawWorkbenchDeptContact.getTele());
				// 位置
				tawWorkbenchContact.setPosition(tawWorkbenchDeptContact
						.getPosition());
				// 用户id
				tawWorkbenchContact.setUserId(tawWorkbenchDeptContact
						.getUserId());
				// 部门通迅录合并为个人通迅录
				contacts.add(tawWorkbenchContact);

			}
		}
		return contacts;
	}
}

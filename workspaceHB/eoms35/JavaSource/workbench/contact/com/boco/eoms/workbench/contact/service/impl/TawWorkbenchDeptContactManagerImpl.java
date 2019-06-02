package com.boco.eoms.workbench.contact.service.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.workbench.contact.dao.TawWorkbenchContactDao;
import com.boco.eoms.workbench.contact.dao.TawWorkbenchDeptContactDao;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContact;
import com.boco.eoms.workbench.contact.model.TawWorkbenchDeptContact;
import com.boco.eoms.workbench.contact.service.ITawWorkbenchDeptContactManager;

/**
 * <p>
 * Title:部门通讯录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 30, 2008 20:30:30 PM
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 * 
 */
public class TawWorkbenchDeptContactManagerImpl extends BaseManager implements
		ITawWorkbenchDeptContactManager {
	private TawWorkbenchDeptContactDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setTawWorkbenchDeptContactDao(TawWorkbenchDeptContactDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.workbench.contact.service.ITawWorkbenchContactManager#getTawWorkbenchContacts(com.boco.eoms.workbench.contact.model.TawWorkbenchContact)
	 */
	public List getTawWorkbenchDeptContacts(
			final TawWorkbenchDeptContact tawWorkbenchDeptContact) {
		return dao.getTawWorkbenchDeptContacts(tawWorkbenchDeptContact);
	}

	/**
	 * @see com.boco.eoms.workbench.contact.service.ITawWorkbenchContactManager#getTawWorkbenchContact(String
	 *      id)
	 */
	public TawWorkbenchDeptContact getTawWorkbenchDeptContact(final String id) {
		return dao.getTawWorkbenchDeptContact(new String(id));
	}

	/**
	 * @see com.boco.eoms.workbench.contact.service.ITawWorkbenchContactManager#saveTawWorkbenchContact(TawWorkbenchContact
	 *      tawWorkbenchContact)
	 */
	public void saveTawWorkbenchDeptContact(TawWorkbenchDeptContact tawWorkbenchDeptContact) {
		dao.saveTawWorkbenchDeptContact(tawWorkbenchDeptContact);
	}

	/**
	 * @see com.boco.eoms.workbench.contact.service.ITawWorkbenchContactManager#removeTawWorkbenchContact(String
	 *      id)
	 */
	public void removeTawWorkbenchDeptContact(final String id) {
		dao.removeTawWorkbenchDeptContact(new String(id));
	}

	/**
	 * 
	 */
	public Map getTawWorkbenchDeptContacts(final Integer curPage,
			final Integer pageSize) {
		return dao.getTawWorkbenchDeptContacts(curPage, pageSize, null);
	}

	public Map getTawWorkbenchDeptContacts(final Integer curPage,
			final Integer pageSize, final String whereStr) {
		return dao.getTawWorkbenchDeptContacts(curPage, pageSize, whereStr);
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
	public String  getEmailByUserId(final String userId){
		return dao.getEmailByUserId(userId);
	}
}

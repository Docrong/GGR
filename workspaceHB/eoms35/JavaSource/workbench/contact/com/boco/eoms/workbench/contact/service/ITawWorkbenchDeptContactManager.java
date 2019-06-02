package com.boco.eoms.workbench.contact.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.workbench.contact.model.TawWorkbenchDeptContact;
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
public interface ITawWorkbenchDeptContactManager extends Manager {
	/**
	 * Retrieves all of the tawWorkbenchContacts
	 */
	public List getTawWorkbenchDeptContacts(TawWorkbenchDeptContact tawWorkbenchDeptContact);

	/**
	 * Gets tawWorkbenchDeptContact's information based on id.
	 * 
	 * @param id
	 *            the tawWorkbenchDeptContact's id
	 * @return tawWorkbenchDeptContact populated tawWorkbenchDeptContact object
	 */
	public TawWorkbenchDeptContact getTawWorkbenchDeptContact(final String id);

	/**
	 * Saves a tawWorkbenchDeptContact's information
	 * 
	 * @param tawWorkbenchDeptContact
	 *            the object to be saved
	 */
	public void saveTawWorkbenchDeptContact(TawWorkbenchDeptContact tawWorkbenchDeptContact);

	/**
	 * Removes a tawWorkbenchDeptContact from the database by id
	 * 
	 * @param id
	 *            the tawWorkbenchDeptContact's id
	 */
	public void removeTawWorkbenchDeptContact(final String id);

	public Map getTawWorkbenchDeptContacts(final Integer curPage,
			final Integer pageSize);

	public Map getTawWorkbenchDeptContacts(final Integer curPage,
			final Integer pageSize, final String whereStr);

	public List getSonsById(String parentid);

	// 根据分组id得到分组下的人员列表
	public List getNextLevecContacts(final String userid, final String id,
			final String deleted);

	public String getUserStrById(final String id);

	// 根据用户名得到用户的手机号码
	public String getTeleByUserId(final String userId);

	public String getEmailByUserId(final String userId);
}

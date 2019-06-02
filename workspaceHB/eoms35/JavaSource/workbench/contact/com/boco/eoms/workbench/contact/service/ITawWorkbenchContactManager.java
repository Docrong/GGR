package com.boco.eoms.workbench.contact.service;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.workbench.contact.model.TawWorkbenchContact;
import com.boco.eoms.workbench.contact.dao.TawWorkbenchContactDao;

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
public interface ITawWorkbenchContactManager extends Manager {
	/**
	 * Retrieves all of the tawWorkbenchContacts
	 */
	public List getTawWorkbenchContacts(TawWorkbenchContact tawWorkbenchContact);

	/**
	 * Gets tawWorkbenchContact's information based on id.
	 * 
	 * @param id
	 *            the tawWorkbenchContact's id
	 * @return tawWorkbenchContact populated tawWorkbenchContact object
	 */
	public TawWorkbenchContact getTawWorkbenchContact(final String id);

	/**
	 * Saves a tawWorkbenchContact's information
	 * 
	 * @param tawWorkbenchContact
	 *            the object to be saved
	 */
	public void saveTawWorkbenchContact(TawWorkbenchContact tawWorkbenchContact);

	/**
	 * Removes a tawWorkbenchContact from the database by id
	 * 
	 * @param id
	 *            the tawWorkbenchContact's id
	 */
	public void removeTawWorkbenchContact(final String id);

	public Map getTawWorkbenchContacts(final Integer curPage,
			final Integer pageSize);

	public Map getTawWorkbenchContacts(final Integer curPage,
			final Integer pageSize, final String whereStr);

	public List getSonsById(String parentid);

	// 根据分组id得到分组下的人员列表
	public List getNextLevecContacts(final String userid, final String id,
			final String deleted);

	public String getUserStrById(final String id);

	// 根据用户名得到用户的手机号码
	public String getTeleByUserId(final String userId);

	public String getEmailByUserId(final String userId);

	public boolean getContactName(final String contactName, final String userid);

	/**
	 * 取某部门下的某联系人(name)的列表
	 * 
	 * @param name
	 *            联系人
	 * @param deptId
	 *            部门id
	 * @param userId
	 *            个人通迅录中的用户id
	 * @return 某部门下的某些联系人列表
	 */
	public List getContacts(final String name, final String userId,
			final String deptId);

}

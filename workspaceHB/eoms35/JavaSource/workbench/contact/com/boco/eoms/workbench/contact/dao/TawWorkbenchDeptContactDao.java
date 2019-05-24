package com.boco.eoms.workbench.contact.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
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
public interface TawWorkbenchDeptContactDao extends Dao {

	/**
	 * Retrieves all of the tawWorkbenchContacts
	 */
	public List getTawWorkbenchDeptContacts(
			TawWorkbenchDeptContact tawWorkbenchDeptContact);

	/**
	 * Gets tawWorkbenchContact's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the tawWorkbenchContact's id
	 * @return tawWorkbenchContact populated tawWorkbenchContact object
	 */
	public TawWorkbenchDeptContact getTawWorkbenchDeptContact(final String id);

	/**
	 * Saves a tawWorkbenchContact's information
	 * 
	 * @param tawWorkbenchContact
	 *            the object to be saved
	 */
	public void saveTawWorkbenchDeptContact(
			TawWorkbenchDeptContact tawWorkbenchDeptContact);

	/**
	 * Removes a tawWorkbenchContact from the database by id
	 * 
	 * @param id
	 *            the tawWorkbenchContact's id
	 */
	public void removeTawWorkbenchDeptContact(final String id);

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ��
	 */
	public Map getTawWorkbenchDeptContacts(final Integer curPage,
			final Integer pageSize);

	public Map getTawWorkbenchDeptContacts(final Integer curPage,
			final Integer pageSize, final String whereStr);

	public List getSonsById(String parentid);

	public List getNextLevecContacts(final String userid, final String id,
			final String deleted);

	public String getUserStrById(final String id);

	public String getTeleByUserId(final String userId);

	public String getEmailByUserId(final String userId);

	/**
	 * 模糊查询某部门某联系人(name)的列表
	 * 
	 * @param name
	 *            联系人
	 * @param deptId
	 *            部门
	 * @return
	 */
	public List getContacts(String name, String deptId);
}

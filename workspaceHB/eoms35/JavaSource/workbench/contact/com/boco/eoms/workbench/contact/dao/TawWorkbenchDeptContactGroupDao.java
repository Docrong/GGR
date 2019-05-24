package com.boco.eoms.workbench.contact.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.workbench.contact.model.TawWorkbenchDeptContactGroup;

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
public interface TawWorkbenchDeptContactGroupDao extends Dao {

	/**
	 * Retrieves all of the tawWorkbenchContactGroups
	 */
	public List getTawWorkbenchDeptContactGroups(
			TawWorkbenchDeptContactGroup tawWorkbenchDeptContactGroup);

	/**
	 * Gets tawWorkbenchContactGroup's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the tawWorkbenchContactGroup's id
	 * @return tawWorkbenchContactGroup populated tawWorkbenchContactGroup
	 *         object
	 */
	public TawWorkbenchDeptContactGroup getTawWorkbenchDeptContactGroup(
			final String id);

	/**
	 * Saves a tawWorkbenchContactGroup's information
	 * 
	 * @param tawWorkbenchContactGroup
	 *            the object to be saved
	 */
	public void saveTawWorkbenchDeptContactGroup(
			TawWorkbenchDeptContactGroup tawWorkbenchDeptContactGroup);

	/**
	 * Removes a tawWorkbenchContactGroup from the database by id
	 * 
	 * @param id
	 *            the tawWorkbenchContactGroup's id
	 */
	public void removeTawWorkbenchDeptContactGroup(final String id);

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ��
	 */
	public Map getTawWorkbenchDeptContactGroups(final Integer curPage,
			final Integer pageSize);

	public Map getTawWorkbenchDeptContactGroups(final Integer curPage,
			final Integer pageSize, final String whereStr);

	public List getSonsById(String parentid);

	public List getNextLevecGroups(String nodis, String userId, String deleted);

	public int getMaxGroupId();

	public TawWorkbenchDeptContactGroup getTawWorkbenchDeptContactGroupById(
			final String id);

	public List getOwnerGroup(String nodid, String userId, String deleted);

	

}

package com.boco.eoms.workbench.infopub.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.workbench.infopub.model.ThreadPermimissionOrg;

/**
 * 
 * <p>
 * Title:记录信息可否操作，组织结构（谁）权限dao接口
 * </p>
 * <p>
 * Description:谁可看贴，谁可发贴
 * </p>
 * <p>
 * Date:May 24, 2008 6:02:03 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface ThreadPermimissionOrgDao extends Dao {

	/**
	 * Retrieves all of the threadPermimissionOrgs
	 */
	public List getThreadPermimissionOrgs(
			ThreadPermimissionOrg threadPermimissionOrg);

	/**
	 * Gets threadPermimissionOrg's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the threadPermimissionOrg's id
	 * @return threadPermimissionOrg populated threadPermimissionOrg object
	 */
	public ThreadPermimissionOrg getThreadPermimissionOrg(final String id);

	/**
	 * Saves a threadPermimissionOrg's information
	 * 
	 * @param threadPermimissionOrg
	 *            the object to be saved
	 */
	public void saveThreadPermimissionOrg(
			ThreadPermimissionOrg threadPermimissionOrg);

	/**
	 * Removes a threadPermimissionOrg from the database by id
	 * 
	 * @param id
	 *            the threadPermimissionOrg's id
	 */
	public void removeThreadPermimissionOrg(final String id);

	/**
	 * 删除permimissionOrg对象
	 * 
	 * @param permimissionOrg
	 *            权限组织结构
	 */
	public void removeThreadPermimissionOrg(
			final ThreadPermimissionOrg permimissionOrg);

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ��
	 */
	public Map getThreadPermimissionOrgs(final Integer curPage,
			final Integer pageSize);

	public Map getThreadPermimissionOrgs(final Integer curPage,
			final Integer pageSize, final String whereStr);

	/**
	 * 通过信息id获取相关权限
	 * 
	 * @param threadId
	 *            权限相关id
	 * @return 权限列表
	 */
	public List getThreadPermissionOrgsByThreadId(String threadId);
}

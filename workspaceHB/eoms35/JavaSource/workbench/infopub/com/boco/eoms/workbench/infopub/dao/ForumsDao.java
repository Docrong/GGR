package com.boco.eoms.workbench.infopub.dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.workbench.infopub.model.Forums;

/**
 * 
 * <p>
 * Title:版块操作dao的接口
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:01:07 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface ForumsDao extends Dao {

	/**
	 * Retrieves all of the forumss
	 */
	public List getForumss(Forums forums);

	/**
	 * Gets forums's information based on primary key. An
	 * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
	 * found.
	 * 
	 * @param id
	 *            the forums's id
	 * @return forums populated forums object
	 */
	public Forums getForums(final String id);

	/**
	 * Saves a forums's information
	 * 
	 * @param forums
	 *            the object to be saved
	 */
	public void saveForums(Forums forums);

	/**
	 * Removes a forums from the database by id
	 * 
	 * @param id
	 *            the forums's id
	 */
	public void removeForums(final String id);

	/**
	 * ���ڷ�ҳ��ʾ curPage ��ǰҳ�� pageSize ÿҳ��ʾ��
	 */
	public Map getForumss(final Integer curPage, final Integer pageSize);

	public Map getForumss(final Integer curPage, final Integer pageSize,
			final String whereStr);

	/**
	 * 根据父结点取子节点
	 * 
	 * @param parentId
	 *            父结点id
	 * @return 取出子节点
	 */
	public List getForumsByParentId(String parentId);

	/**
	 * 根据父结点取子节点
	 * 
	 * @param parentId
	 *            父结点id
	 * @param 取前num条子节点
	 * @return 取出子节点
	 */
	public List getForumsByParentId(String parentId, Integer num);
	//2009-04-10 根据专题名得到专题对象
	public Forums getForumsByTitle(final String title);
}

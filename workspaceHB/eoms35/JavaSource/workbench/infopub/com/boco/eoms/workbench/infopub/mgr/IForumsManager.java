package com.boco.eoms.workbench.infopub.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.workbench.infopub.model.Forums;

/**
 * 
 * <p>
 * Title:版块mgr
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:06:20 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public interface IForumsManager extends Manager {
	/**
	 * Retrieves all of the forumss
	 */
	public List getForumss(Forums forums);

	/**
	 * Gets forums's information based on id.
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
	 * 删除当前版块及子版块信息（标记）
	 * 
	 * @param id
	 *            版块id
	 */
	public void removeForums(final String id);

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
	 * 移动主题到另一主题
	 * 
	 * @param forumId
	 *            被移动主题id
	 * @param toForumId
	 *            要移动到另一主题的id
	 */
	public void forum2forum(String forumId, String toForumId);
	
	//2009-04-10 根据专题名得到专题对象
	public Forums getForumsByTitle(final String title);
	
}

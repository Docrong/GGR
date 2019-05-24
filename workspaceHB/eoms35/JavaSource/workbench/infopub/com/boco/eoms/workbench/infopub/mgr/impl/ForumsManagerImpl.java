package com.boco.eoms.workbench.infopub.mgr.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.Constants;
import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.cache.application.ApplicationCacheMgr;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.workbench.infopub.dao.ForumsDao;
import com.boco.eoms.workbench.infopub.mgr.IForumsManager;
import com.boco.eoms.workbench.infopub.model.Forums;
import com.boco.eoms.workbench.infopub.util.InfopubConstants;
import com.boco.eoms.workbench.infopub.util.InfopubUtil;

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
public class ForumsManagerImpl extends BaseManager implements IForumsManager {
	private ForumsDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setForumsDao(ForumsDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IForumsManager#getForumss(com.boco.eoms.workbench.infopub.model.Forums)
	 */
	public List getForumss(final Forums forums) {
		return dao.getForumss(forums);
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IForumsManager#getForums(String
	 *      id)
	 */
	public Forums getForums(final String id) {
		return dao.getForums(new String(id));
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IForumsManager#saveForums(Forums
	 *      forums)
	 */
	public void saveForums(Forums forums) {
		// 若为新增版块则设为叶子结点
		if (forums.getId() == null || "".equals(forums.getId())) {
			forums.setIsLeaf(InfopubConstants.TRUE_STR);
			// 设置未删除标记
			forums.setIsDel(Constants.NOT_DELETED_FLAG);
			dao.saveForums(forums);

			// 若不为根则取父节点
			if (!Constants.TREE_ROOT_FLAG.equals(forums.getParentId())) {
				// 取父版块
				Forums pForums = dao.getForums(forums.getParentId());
				// 修改为非叶子节点
				pForums.setIsLeaf(InfopubConstants.FALSE_STR);

				dao.saveForums(pForums);
			}
		} else {
			dao.saveForums(forums);
		}

		try {
			ApplicationCacheMgr cacheMgr = (ApplicationCacheMgr) ApplicationContextHolder.getInstance().getBean("ApplicationCacheMgr");
			// 刷新所有组件缓存
			cacheMgr.flush("com.boco.eoms.commons.system.dict.DICT_CATCH");
		} catch (Exception e) {
			System.out.println("Error:ForumsManagerImpl.saveForums -- cachMgr.flush error!");
		}
	}

	/**
	 * @see com.boco.eoms.workbench.infopub.mgr.IForumsManager#removeForums(String
	 *      id)
	 */
	public void removeForums(final String id) {
		// dao.removeForums(new String(id));

		Forums forums = dao.getForums(id);
		forums.setIsDel(Constants.DELETED_FLAG);
		dao.saveForums(forums);
		
		// 若父版块不为根，则将父版块的isLeaf（叶子节点）设为true
		if (!UIConstants.ROOT_NODE.equals(forums.getParentId())) {
			Forums parentForums = dao.getForums(forums.getParentId());
			List parent = dao.getForumsByParentId(parentForums.getId());
			if(parent==null||parent.isEmpty()){//2009-5-14 解决子专题删除后其他子专题不显示的问题
				// 设为子节点
				parentForums.setIsLeaf(InfopubConstants.TRUE_STR);
			}
			dao.saveForums(parentForums);
		}
		
		// 取该版块下的子版块
		List list = dao.getForumsByParentId(id);
		// 递归
		if (list != null && !list.isEmpty()) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				Forums f = (Forums) it.next();
				this.removeForums(f.getId());
			}
		}
	}

	/**
	 * 
	 */
	public Map getForumss(final Integer curPage, final Integer pageSize) {
		return dao.getForumss(curPage, pageSize, null);
	}

	public Map getForumss(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return dao.getForumss(curPage, pageSize, whereStr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IForumsManager#getForumsByParentId(java.lang.String)
	 */
	public List getForumsByParentId(String parentId) {
		return dao.getForumsByParentId(parentId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.workbench.infopub.mgr.IForumsManager#forum2forum(java.lang.String,
	 *      java.lang.String)
	 */
	public void forum2forum(String forumId, String toForumId) {
		if (forumId.equals(toForumId)) {
			return;
		}
		// 被移动的专题
		Forums forum = dao.getForums(forumId);
		// 父专题移动到子专题
		if (this.isExistSubForum(forumId, toForumId)) {
			List subForums = this.getForumsByParentId(forumId);
			if (subForums != null) {
				// 将父专题的所有子专题移动到父专题的父专题下
				for (Iterator it = subForums.iterator(); it.hasNext();) {
					Forums item = (Forums) it.next();
					item.setParentId(forum.getParentId());
					dao.saveForums(item);
				}
			}
			// 移动到另一专题
			forum.setParentId(toForumId);
			dao.saveForums(forum);
		} else {
			// 移动到另一专题
			forum.setParentId(toForumId);
			dao.saveForums(forum);
		}

		// 修改是否为叶子节点标识
		updateForumsLeaf();

	}

	/**
	 * 判断子专题（subForumId）是否在专题（forumId)下
	 * 
	 * @param forumId
	 *            专题
	 * @param subForumId
	 *            子专题
	 * @return 子专题是否在专题下
	 */
	private boolean isExistSubForum(String forumId, String subForumId) {

		Forums forums = this.getForums(forumId);
		List result = new ArrayList();
		listSubForums(forums, result);
		// 若无子专题则返回false
		if (result == null || result.isEmpty()) {
			return false;
		}
		// 判断子专题中是否含有subForumId（要判断的子专题）
		for (Iterator it = result.iterator(); it.hasNext();) {
			Forums item = (Forums) it.next();
			if (subForumId.equals(item.getId())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 取子专题列表
	 * 
	 * @param forums
	 *            父专题
	 * @param result
	 *            存储子专题的列表
	 * 
	 */
	private void listSubForums(Forums forums, List result) {
		List list = getForumsByParentId(forums.getId());
		// 逐级取子专题
		if (list != null && !list.isEmpty()) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				Forums item = (Forums) it.next();
				listSubForums(item, result);
			}
			result.addAll(list);
		}
	}

	

	/**
	 * 判断专题(forums）是否存在子节点
	 * 
	 * @param forums
	 *            专题
	 * @return 专题（forums）下是否有子专题
	 */
	private boolean isExistSubForums(Forums forums) {
		List list = dao.getForumsByParentId(forums.getId(),
				new Integer(1));
		if (list != null && !list.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 修改叶子节点标识
	 *
	 */
	private void updateForumsLeaf() {
		List forums = dao.getForumss(null);
		if (forums != null) {
			for (Iterator it = forums.iterator(); it.hasNext();) {
				Forums item = (Forums) it.next();
				System.out.println(item.getTitle());
				// 存在子节点，则叶子(isLeaf)节点标识为false
				if (isExistSubForums(item)) {
					// 减少修改（操作数据库）次数
					if (InfopubUtil.isTrue(item.getIsLeaf())) {
						item.setIsLeaf(InfopubConstants.FALSE_STR);
						saveForums(item);
					}

				}
				// 不存在子节点，则叶子(isLeaf)节点标识为true
				else {
					if (!InfopubUtil.isTrue(item.getIsLeaf())) {
						item.setIsLeaf(InfopubConstants.TRUE_STR);
						saveForums(item);
					}
				}
			}
		}

	}
	//2009-04-10 根据专题名得到专题对象
	public Forums getForumsByTitle(final String title){
		return dao.getForumsByTitle(title);
	}
}

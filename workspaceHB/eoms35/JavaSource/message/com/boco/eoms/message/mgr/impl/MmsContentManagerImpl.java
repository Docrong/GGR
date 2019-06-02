package com.boco.eoms.message.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.message.dao.IMmsContentDao;
import com.boco.eoms.message.mgr.IMmsContentManager;
import com.boco.eoms.message.model.MmsContent;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:2009-3-11 下午02:55:02
 * </p>
 * 
 * @author 孙圣泰
 * @version 3.5.1
 * 
 */
public class MmsContentManagerImpl extends BaseManager implements
		IMmsContentManager {
	private IMmsContentDao dao;

	/**
	 * Set the Dao for communication with the data layer.
	 * 
	 * @param dao
	 */
	public void setMmsContentDao(IMmsContentDao dao) {
		this.dao = dao;
	}

	/**
	 * @see com.boco.eoms.message.mgr.IMmsContentManager#getMmsContents(com.boco.eoms.message.model.MmsContent)
	 */
	public List getMmsContents(final MmsContent mmsContent) {
		return dao.getMmsContents(mmsContent);
	}

	/**
	 * @see com.boco.eoms.message.mgr.IMmsContentManager#getMmsContent(String
	 *      id)
	 */
	public MmsContent getMmsContent(final String id) {
		return dao.getMmsContent(new String(id));
	}

	/**
	 * @see com.boco.eoms.message.mgr.IMmsContentManager#saveMmsContent(MmsContent
	 *      mmsContent)
	 */
	public void saveMmsContent(MmsContent mmsContent) {
		dao.saveMmsContent(mmsContent);
	}

	/**
	 * @see com.boco.eoms.message.mgr.IMmsContentManager#removeMmsContent(String
	 *      id)
	 */
	public void removeMmsContent(final String id) {
		dao.removeMmsContent(new String(id));
	}

	/**
	 * 
	 */
	public Map getMmsContents(final Integer curPage, final Integer pageSize) {
		return dao.getMmsContents(curPage, pageSize, null);
	}

	public Map getMmsContents(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return dao.getMmsContents(curPage, pageSize, whereStr);
	}


	public List retriveMmsContents(String monitorId) {
		return dao.retriveMmsContents(monitorId);
	}

	
}

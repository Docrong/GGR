/*
 * Created on 2008-3-13
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.commons.system.role.service.impl;

import java.util.List;

import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.role.dao.TawSystemSubRoleRefPostDao;
import com.boco.eoms.commons.system.role.model.TawSystemSubRoleRefPost;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleRefPostManager;

/**
 * @author IBM
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TawSystemSubRoleRefPostManagerImpl extends BaseManager implements ITawSystemSubRoleRefPostManager{
	private TawSystemSubRoleRefPostDao dao;
	/**
	 * @param dao The dao to set.
	 */
	public void setTawSystemSubRoleRefPostDao(TawSystemSubRoleRefPostDao dao) {
		this.dao = dao;
	}
	/* (non-Javadoc)
	 * @see com.boco.eoms.commons.system.role.service.ITawSystemSubRoleRefPostManager#saveTawSystemSubRoleRefPost(com.boco.eoms.commons.system.role.model.TawSystemSubRoleRefPost)
	 */
	public void saveTawSystemSubRoleRefPost(TawSystemSubRoleRefPost tawSystemSubRoleRefPost) {
		dao.saveTawSystemSubRoleRefPost(tawSystemSubRoleRefPost);
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.commons.system.role.service.ITawSystemSubRoleRefPostManager#getPostBySubRoleId(java.lang.String)
	 */
	public List getPostBySubRoleId(String subRoleid) {
		return dao.getPostBySubRoleId(subRoleid);
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.commons.system.role.service.ITawSystemSubRoleRefPostManager#getSubRoleByPostId(long)
	 */
	public List getSubRoleByPostId(long postId) {
		return dao.getSubRoleByPostId(postId);
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.commons.system.role.service.ITawSystemSubRoleRefPostManager#removeSubRoleRefPostByPostId(long)
	 */
	public void removeSubRoleRefPostByPostId(long postId) {
		dao.removeSubRoleRefPostByPostId(postId);		
	}

	/* (non-Javadoc)
	 * @see com.boco.eoms.commons.system.role.service.ITawSystemSubRoleRefPostManager#removeSubSoleRefPostBySubRoleId(java.lang.String)
	 */
	public void removeSubSoleRefPostBySubRoleId(String subRoleId) {
		dao.removeSubSoleRefPostBySubRoleId(subRoleId);
	}

}

package com.boco.eoms.commons.system.user.service.impl;


import com.boco.eoms.base.service.impl.BaseManager;
import com.boco.eoms.commons.system.user.dao.TawPartnersUserDao;
import com.boco.eoms.commons.system.user.model.TawPartnersUser;
import com.boco.eoms.commons.system.user.service.ITawPartnersUserManager;

public class TawPartnersUserManagerImpl extends BaseManager implements ITawPartnersUserManager{
	private TawPartnersUserDao dao;
	
	public void setTawPartnersUserDao(TawPartnersUserDao dao) {
		this.dao = dao;
	}
	/*
	 * (non-Javadoc)
	 * 2008-11-11 liujinlong
	 * @see com.boco.eoms.commons.system.user.service.ITawSystemUserManager#saveTawPartnersUser(com.boco.eoms.commons.system.user.model.TawPartnersUser)
	 */
	public void saveTawPartnersUser(TawPartnersUser tawPartnersUser){
		dao.saveTawPartnersUser(tawPartnersUser);
	}
	/**
	 * @see com.boco.eoms.commons.system.user.service.ITawSystemUserManager#getPartnersUserByUserId(String
	 *      userid)
	 *      2008-11-12 liujinlong
	 */
	public TawPartnersUser getPartnersUserByUserId(String userid){
		return dao.getPartnersUserByUserId(userid);
	}
	/**
	 * 删除tawPartnersUser
	 * 
	 * @param tawPartnersUser
	 * 2008-11-12 liujinlong
	 */
	public void removeTawPartnersUser(TawPartnersUser tawPartnersUser) {
		dao.removeTawPartnersUser(tawPartnersUser);
	}

}

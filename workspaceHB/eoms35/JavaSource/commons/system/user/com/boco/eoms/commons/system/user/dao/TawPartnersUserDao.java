package com.boco.eoms.commons.system.user.dao;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.commons.system.user.model.TawPartnersUser;

public interface TawPartnersUserDao extends Dao{
	/**
	 * 得到TawPartnersUser根据参数userid
	 * @param userid
	 * @return
	 * 2008-11-12 liujinlong
	 */
	public TawPartnersUser getPartnersUserByUserId(String userid);
	/**
	 * Saves a tawPartnersUser's information
	 * 
	 * @param tawPartnersUser
	 *            the object to be saved
	 *            2008-11-11 liujinlong
	 */
	public void saveTawPartnersUser(TawPartnersUser tawPartnersUser);
	/**
	 * 删除tawPartnersUser
	 * 
	 * @param tawPartnersUser
	 * 2008-11-12 liujinlong
	 */
	public void removeTawPartnersUser(TawPartnersUser tawPartnersUser);

}

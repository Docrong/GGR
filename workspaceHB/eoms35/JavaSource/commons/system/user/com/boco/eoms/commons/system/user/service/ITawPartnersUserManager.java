package com.boco.eoms.commons.system.user.service;

import com.boco.eoms.base.service.Manager;
import com.boco.eoms.commons.system.user.model.TawPartnersUser;

public interface ITawPartnersUserManager extends Manager{
	/**
	 * Gets tawPartnersUser's information based on id.
	 * 
	 * @param id
	 *            the tawPartnersUser's id
	 * @return tawPartnersUser populated tawPartnersUser object
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

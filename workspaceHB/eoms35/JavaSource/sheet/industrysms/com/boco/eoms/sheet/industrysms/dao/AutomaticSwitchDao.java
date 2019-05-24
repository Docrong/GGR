package com.boco.eoms.sheet.industrysms.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.sheet.industrysms.model.AutomaticSwitch;

/**
 * AutomaticSwitch Data Access Object (DAO) interface.
 * 
 * <p>
 * <a href="AutomaticSwitchDao.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public interface AutomaticSwitchDao extends Dao {
	/**
	 * Gets automaticswitch information based on automaticswitchname
	 * 
	 * @param automaticswitchname
	 *            the automaticswitchname
	 * @return automaticswitch populated automaticswitch object
	 */
	public AutomaticSwitch getAutomaticSwitchByName(String automaticswitchname);

	/**
	 * Gets a list of automaticswitchs based on parameters passed in.
	 * 
	 * @return List populated list of automaticswitchs
	 */
	public List getAutomaticSwitchs(AutomaticSwitch automaticswitch);

	/**
	 * Saves a automaticswitch's information
	 * 
	 * @param automaticswitch
	 *            the object to be saved
	 */
	public void saveAutomaticSwitch(AutomaticSwitch automaticswitch);

	/**
	 * Removes a automaticswitch from the database by name
	 * 
	 * @param automaticswitchname
	 *            the automaticswitch's automaticswitchname
	 */
	public void removeAutomaticSwitch(String automaticswitchname);
}

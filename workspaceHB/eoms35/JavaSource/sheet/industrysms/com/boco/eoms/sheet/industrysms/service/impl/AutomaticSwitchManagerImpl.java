package com.boco.eoms.sheet.industrysms.service.impl;

import java.util.List;

import com.boco.eoms.sheet.industrysms.dao.AutomaticSwitchDao;
import com.boco.eoms.sheet.industrysms.model.AutomaticSwitch;
import com.boco.eoms.sheet.industrysms.service.AutomaticSwitchManager;

/**
 * Implementation of AutomaticSwitchManager interface.
 * </p>
 * 
 * <p>
 * <a href="AutomaticSwitchManagerImpl.java.html"><i>View Source</i></a>
 * </p>
 * 
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 */
public class AutomaticSwitchManagerImpl implements AutomaticSwitchManager {
	private AutomaticSwitchDao automaticswitchDao;

	public List getAutomaticSwitchs(AutomaticSwitch automaticswitch) {
		return automaticswitchDao.getAutomaticSwitchs(automaticswitch);
	}

	public AutomaticSwitch getAutomaticSwitch(String automaticswitchname) {
		return automaticswitchDao.getAutomaticSwitchByName(automaticswitchname);
	}

	public void saveAutomaticSwitch(AutomaticSwitch automaticswitch) {
		automaticswitchDao.saveAutomaticSwitch(automaticswitch);
	}

	public void removeAutomaticSwitch(String automaticswitchname) {
		automaticswitchDao.removeAutomaticSwitch(automaticswitchname);
	}

	public AutomaticSwitchDao getAutomaticswitchDao() {
		return automaticswitchDao;
	}

	public void setAutomaticswitchDao(AutomaticSwitchDao automaticswitchDao) {
		this.automaticswitchDao = automaticswitchDao;
	}

}

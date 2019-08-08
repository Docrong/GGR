/**
 * Business Service Interface to handle communication between web and
 * persistence layer.
 *
 * <p><a href="AutomaticSwitchManager.java.html"><i>View Source</i></a></p>
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler </a>
 */
package com.boco.eoms.sheet.industrysms.service;

import java.util.List;

import com.boco.eoms.sheet.industrysms.model.AutomaticSwitch;

public interface AutomaticSwitchManager {

    public List getAutomaticSwitchs(AutomaticSwitch automaticswitch);

    public AutomaticSwitch getAutomaticSwitch(String automaticswitchname);

    public void saveAutomaticSwitch(AutomaticSwitch automaticswitch);

    public void removeAutomaticSwitch(String automaticswitchname);
}

package com.boco.eoms.sheet.industrysms.dao.hibernate;

import java.util.List;

import com.boco.eoms.sheet.industrysms.dao.AutomaticSwitchDao;
import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.sheet.industrysms.model.AutomaticSwitch;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve AutomaticSwitch objects.
 *
 * <p>
 * <a href="AutomaticSwitchDaoHibernate.java.html"><i>View Source</i></a>
 * </p>
 *
 * @author <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 */
public class AutomaticSwitchDaoHibernate extends BaseDaoHibernate implements AutomaticSwitchDao {

    public List getAutomaticSwitchs(AutomaticSwitch automaticswitch) {
        return getHibernateTemplate().find("from AutomaticSwitch");
    }

    public AutomaticSwitch getAutomaticSwitch(Long automaticswitchId) {
        return (AutomaticSwitch) getHibernateTemplate().get(AutomaticSwitch.class, automaticswitchId);
    }

    public AutomaticSwitch getAutomaticSwitchByName(String automaticswitchname) {
        List automaticswitchs = getHibernateTemplate().find("from AutomaticSwitch where name=?",
                automaticswitchname);
        if (automaticswitchs.isEmpty()) {
            return null;
        } else {
            return (AutomaticSwitch) automaticswitchs.get(0);
        }
    }

    public void saveAutomaticSwitch(AutomaticSwitch automaticswitch) {
        getHibernateTemplate().saveOrUpdate(automaticswitch);
    }

    public void removeAutomaticSwitch(String automaticswitchname) {
        Object automaticswitch = getAutomaticSwitchByName(automaticswitchname);
        getHibernateTemplate().delete(automaticswitch);
    }

}

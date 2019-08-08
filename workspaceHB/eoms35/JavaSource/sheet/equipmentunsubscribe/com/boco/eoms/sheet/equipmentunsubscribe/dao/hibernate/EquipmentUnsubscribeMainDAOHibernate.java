package com.boco.eoms.sheet.equipmentunsubscribe.dao.hibernate;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.equipmentunsubscribe.dao.IEquipmentUnsubscribeMainDAO;

/**
 * <p>
 * Title:设备退订流程
 * </p>
 * <p>
 * Description:设备退订流程
 * </p>
 * <p>
 * Tue Oct 09 14:24:25 GMT+08:00 2018
 * </p>
 *
 * @author lyg
 * @version 3.6
 */

public class EquipmentUnsubscribeMainDAOHibernate extends MainDAO implements IEquipmentUnsubscribeMainDAO {


    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
     */
    public String getMainName() {
        return "EquipmentUnsubscribeMain";
    }

}
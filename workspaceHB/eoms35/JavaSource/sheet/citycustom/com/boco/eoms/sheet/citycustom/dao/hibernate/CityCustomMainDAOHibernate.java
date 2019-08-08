package com.boco.eoms.sheet.citycustom.dao.hibernate;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.citycustom.dao.ICityCustomMainDAO;

/**
 * <p>
 * Title:地市集客业务工单
 * </p>
 * <p>
 * Description:地市集客业务工单
 * </p>
 * <p>
 * Fri Sep 28 14:06:48 CST 2012
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class CityCustomMainDAOHibernate extends MainDAO implements ICityCustomMainDAO {


    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
     */
    public String getMainName() {
        return "CityCustomMain";
    }

}
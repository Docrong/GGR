package com.boco.eoms.sheet.circuitcontrol.dao.hibernate;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.circuitcontrol.dao.ICircuitControlMainDAO;

/**
 * <p>
 * Title:电路调度申请工单
 * </p>
 * <p>
 * Description:电路调度申请工单
 * </p>
 * <p>
 * Sun Sep 29 16:51:15 CST 2013
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class CircuitControlMainDAOHibernate extends MainDAO implements ICircuitControlMainDAO {


    /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
     */
    public String getMainName() {
        return "CircuitControlMain";
    }

}
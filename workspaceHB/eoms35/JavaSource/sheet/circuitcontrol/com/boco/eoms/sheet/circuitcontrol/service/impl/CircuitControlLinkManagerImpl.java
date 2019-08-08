package com.boco.eoms.sheet.circuitcontrol.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.service.impl.LinkServiceImpl;
import com.boco.eoms.sheet.circuitcontrol.dao.ICircuitControlLinkDAO;
import com.boco.eoms.sheet.circuitcontrol.service.ICircuitControlLinkManager;

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

public class CircuitControlLinkManagerImpl extends LinkServiceImpl implements ICircuitControlLinkManager {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition) throws Exception {
        ICircuitControlLinkDAO dao = (ICircuitControlLinkDAO) this.getLinkDAO();
        List list = dao.getLinksBycondition(condition, this.getLinkObject().getClass().getName());
        return list;
    }

}
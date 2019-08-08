package com.boco.eoms.sheet.plannconfirm.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.service.impl.LinkServiceImpl;
import com.boco.eoms.sheet.plannconfirm.dao.IPlannConfirmLinkDAO;
import com.boco.eoms.sheet.plannconfirm.service.IPlannConfirmLinkManager;

/**
 * <p>
 * Title:规划站址确认申请流程
 * </p>
 * <p>
 * Description:规划站址确认申请流程
 * </p>
 * <p>
 * Thu Jun 06 17:13:18 CST 2013
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class PlannConfirmLinkManagerImpl extends LinkServiceImpl implements IPlannConfirmLinkManager {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition) throws Exception {
        IPlannConfirmLinkDAO dao = (IPlannConfirmLinkDAO) this.getLinkDAO();
        List list = dao.getLinksBycondition(condition, this.getLinkObject().getClass().getName());
        return list;
    }

}
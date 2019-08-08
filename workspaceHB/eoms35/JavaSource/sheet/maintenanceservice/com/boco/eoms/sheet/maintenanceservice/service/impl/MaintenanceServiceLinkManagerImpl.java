package com.boco.eoms.sheet.maintenanceservice.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.service.impl.LinkServiceImpl;
import com.boco.eoms.sheet.maintenanceservice.dao.IMaintenanceServiceLinkDAO;
import com.boco.eoms.sheet.maintenanceservice.service.IMaintenanceServiceLinkManager;

/**
 * <p>
 * Title:维保服务审批
 * </p>
 * <p>
 * Description:维保服务审批
 * </p>
 * <p>
 * Thu Mar 16 15:48:02 CST 2017
 * </p>
 *
 * @author lizhi
 * @version 3.5
 */

public class MaintenanceServiceLinkManagerImpl extends LinkServiceImpl implements IMaintenanceServiceLinkManager {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition) throws Exception {
        IMaintenanceServiceLinkDAO dao = (IMaintenanceServiceLinkDAO) this.getLinkDAO();
        List list = dao.getLinksBycondition(condition, this.getLinkObject().getClass().getName());
        return list;
    }

}
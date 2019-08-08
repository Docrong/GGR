package com.boco.eoms.sheet.equipmentinstallation.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.service.impl.LinkServiceImpl;
import com.boco.eoms.sheet.equipmentinstallation.dao.IEquipmentInstallationLinkDAO;
import com.boco.eoms.sheet.equipmentinstallation.service.IEquipmentInstallationLinkManager;

/**
 * <p>
 * Title:设备安装流程
 * </p>
 * <p>
 * Description:设备安装流程
 * </p>
 * <p>
 * Tue Oct 09 14:09:25 GMT+08:00 2018
 * </p>
 *
 * @author lyg
 * @version 3.6
 */

public class EquipmentInstallationLinkManagerImpl extends LinkServiceImpl implements IEquipmentInstallationLinkManager {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition) throws Exception {
        IEquipmentInstallationLinkDAO dao = (IEquipmentInstallationLinkDAO) this.getLinkDAO();
        List list = dao.getLinksBycondition(condition, this.getLinkObject().getClass().getName());
        return list;
    }

}
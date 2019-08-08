package com.boco.eoms.sheet.equipmentunsubscribe.service.impl;

import java.util.List;

import com.boco.eoms.sheet.base.service.impl.LinkServiceImpl;
import com.boco.eoms.sheet.equipmentunsubscribe.dao.IEquipmentUnsubscribeLinkDAO;
import com.boco.eoms.sheet.equipmentunsubscribe.service.IEquipmentUnsubscribeLinkManager;

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

public class EquipmentUnsubscribeLinkManagerImpl extends LinkServiceImpl implements IEquipmentUnsubscribeLinkManager {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition) throws Exception {
        IEquipmentUnsubscribeLinkDAO dao = (IEquipmentUnsubscribeLinkDAO) this.getLinkDAO();
        List list = dao.getLinksBycondition(condition, this.getLinkObject().getClass().getName());
        return list;
    }

}
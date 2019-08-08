package com.boco.eoms.sheet.equipmentunsubscribe.service;

import java.util.List;

import com.boco.eoms.sheet.base.service.ILinkService;

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

public interface IEquipmentUnsubscribeLinkManager extends ILinkService {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition) throws Exception;

}
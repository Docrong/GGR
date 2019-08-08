package com.boco.eoms.sheet.equipmentunsubscribe.dao;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.ILinkDAO;

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

public interface IEquipmentUnsubscribeLinkDAO extends ILinkDAO {
    public List getLinksBycondition(String condition, String linkName) throws HibernateException;
}
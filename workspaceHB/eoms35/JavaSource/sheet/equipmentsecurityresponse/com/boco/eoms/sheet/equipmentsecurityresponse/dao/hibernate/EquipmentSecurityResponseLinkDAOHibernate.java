package com.boco.eoms.sheet.equipmentsecurityresponse.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.base.dao.hibernate.LinkDAO;
import com.boco.eoms.sheet.equipmentsecurityresponse.dao.IEquipmentSecurityResponseLinkDAO;

/**
 * <p>
 * Title:设备互联核查安全告警响应工单
 * </p>
 * <p>
 * Description:设备互联核查安全告警响应工单
 * </p>
 * <p>
 * Tue Apr 25 11:45:21 CST 2017
 * </p>
 *
 * @author liuyonggnag
 * @version 3.6
 */

public class EquipmentSecurityResponseLinkDAOHibernate extends LinkDAO implements IEquipmentSecurityResponseLinkDAO {
    /**
     * 根据条件查出所有的link对象
     */
    public List getLinksBycondition(String condition, String linkName) throws HibernateException {
        String hql = "from " + linkName + " where " + condition;
        return getHibernateTemplate().find(hql);
    }
}
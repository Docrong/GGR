package com.boco.eoms.crm.service.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.crm.bo.CrmWaitInfoBo;
import com.boco.eoms.crm.dao.ICrmWaitInfoDAO;
import com.boco.eoms.crm.model.CrmWaitInfo;
import com.boco.eoms.crm.service.ICrmWaitInfoManager;

/**
 * @author
 */
public class CrmWaitInfoManagerImpl implements ICrmWaitInfoManager {

    private ICrmWaitInfoDAO infoDao;

    public void saveOrUpdateCrmWaitInfo(CrmWaitInfo info) throws HibernateException {
        String sendType = XmlManage.getFile("/com/boco/eoms/crm/config/crm-config.xml").getProperty("SendImmediately");
        System.out.println("sendType=" + sendType);
        if (sendType != null && sendType.toLowerCase().equals("true")) {
            CrmWaitInfoBo.getInstance().sendInfo(info);
        } else {
            info.setCreateTime(new Date());
            infoDao.saveOrUpdateCrmWaitInfo(info);
        }
    }

    public CrmWaitInfo loadCrmWaitInfo(String id) throws Exception {
        return infoDao.loadCrmWaitInfo(id);
    }

    /**
     * 信息发送完后更改表中发送状态
     *
     * @param id
     * @param isSended
     * @throws HibernateException
     */
    public void updateSendStatusCrmWaitInfo(String id, Integer isSended)
            throws HibernateException {
        infoDao.updateSendStatusCrmWaitInfo(id, isSended);
    }

    /**
     * 获取所有未成功发送的信息
     *
     * @return
     * @throws HibernateException
     */
    public List getAllNotSendInfo() throws HibernateException {
        return infoDao.getAllNotSendInfo();
    }

    public ICrmWaitInfoDAO getInfoDao() {
        return infoDao;
    }

    public void setInfoDao(ICrmWaitInfoDAO infoDao) {
        this.infoDao = infoDao;
    }
}

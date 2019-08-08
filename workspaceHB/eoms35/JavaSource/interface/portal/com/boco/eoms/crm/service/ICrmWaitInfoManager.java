package com.boco.eoms.crm.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.crm.model.CrmWaitInfo;

/**
 * @author
 */
public interface ICrmWaitInfoManager {

    public void saveOrUpdateCrmWaitInfo(CrmWaitInfo info) throws HibernateException;

    public CrmWaitInfo loadCrmWaitInfo(String id) throws Exception;

    /**
     * 信息发送完后更改表中发送状态
     *
     * @param id
     * @param isSended
     * @throws HibernateException
     */
    public void updateSendStatusCrmWaitInfo(String id, Integer isSended)
            throws HibernateException;

    /**
     * 获取所有未成功发送的信息
     *
     * @return
     * @throws HibernateException
     */
    public List getAllNotSendInfo() throws HibernateException;

}

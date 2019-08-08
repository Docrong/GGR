package com.boco.eoms.sheet.sheetdelete.service;

import java.util.List;

import org.hibernate.HibernateException;

import com.boco.eoms.sheet.sheetdelete.model.WfSheetDeleteInfo;


public interface IWfSheetDeleteInfoManager {

    public void saveOrUpdateWfSheetDeleteInfo(WfSheetDeleteInfo info)
            throws HibernateException;

    public WfSheetDeleteInfo loadCrmWaitInfo(String id) throws Exception;

    /**
     * 信息删除完后更改表中删除状态
     *
     * @param id
     * @param isSended
     * @throws HibernateException
     */
    public void updateSendStatusWfSheetDeleteInfo(String id, String isSended)
            throws HibernateException;

    /**
     * 获取所有未成功删除的信息
     *
     * @return
     * @throws HibernateException
     */
    public List getAllNotSendInfo() throws HibernateException;

}

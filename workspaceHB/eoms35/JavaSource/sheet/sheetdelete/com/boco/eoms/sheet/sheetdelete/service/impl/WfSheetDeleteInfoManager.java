package com.boco.eoms.sheet.sheetdelete.service.impl;

import java.util.*;

import org.hibernate.HibernateException;
import com.boco.eoms.sheet.sheetdelete.dao.IWfSheetDeleteInfoDAO;
import com.boco.eoms.sheet.sheetdelete.model.WfSheetDeleteInfo;
import com.boco.eoms.sheet.sheetdelete.service.IWfSheetDeleteInfoManager;

public class WfSheetDeleteInfoManager implements IWfSheetDeleteInfoManager {
    private IWfSheetDeleteInfoDAO infoDao;

    public List getAllNotSendInfo() throws HibernateException {
        List list = infoDao.getAllNotSendInfo();
        return list;
    }

    public WfSheetDeleteInfo loadCrmWaitInfo(String id) throws Exception {
        return infoDao.loadWfSheetDeleteInfo(id);
    }

    public void saveOrUpdateWfSheetDeleteInfo(WfSheetDeleteInfo info)
            throws HibernateException {
        infoDao.saveOrUpdateWfInterfaceInfo(info);

    }

    public void saveWfInterfaceInto(String mainBeanId, String linkBeanId,
                                    String sheetKey, String linkId, String interfaceType,
                                    String methodType, String sendType) {

    }

    public void updateSendStatusWfSheetDeleteInfo(String id, String isSended)
            throws HibernateException {
        infoDao.updateSendStatusWfSheetDeleteInfo(id, isSended);

    }

    public IWfSheetDeleteInfoDAO getInfoDao() {
        return infoDao;
    }

    public void setInfoDao(IWfSheetDeleteInfoDAO infoDao) {
        this.infoDao = infoDao;
    }

}

package com.boco.eoms.sheet.offlineData.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.offlineData.dao.IOfflineDataInfoListDAO;
import com.boco.eoms.sheet.offlineData.model.OfflineDataInfoList;
import com.boco.eoms.sheet.offlineData.service.IOfflineDataInfoListManager;

/**
 * <p>
 * Title:码号资源申请工单
 * </p>
 * <p>
 * Description:码号资源申请工单
 * </p>
 * <p>
 * Mon Sep 27 18:07:01 CST 2010
 * </p>
 *
 * @author liuyang
 * @version 3.5
 */

public class OfflineDataInfoListManagerImpl implements IOfflineDataInfoListManager {
    private IOfflineDataInfoListDAO infoListDao;

    public HashMap getListByHqlBy(final Integer curPage, final Integer pageSize, final String hql, final String condictions, final String modelname) throws Exception {
        return infoListDao.getListByHqlBy(curPage, pageSize, hql, condictions, modelname);
    }

    /**
     * 根据拼写的hql去更新
     */
    public void updateModelByHql(String hql) throws Exception {
        infoListDao.updateModelByHql(hql);
    }

    public IOfflineDataInfoListDAO getInfoListDao() {
        return infoListDao;
    }

    public void setInfoListDao(IOfflineDataInfoListDAO infoListDao) {
        this.infoListDao = infoListDao;
    }


    public void saveORupdate(Object obj) throws Exception {
        infoListDao.saveOrupdate(obj);
    }

    public void delete(String id) throws Exception {
        infoListDao.delete(id);
    }

    public OfflineDataInfoList getBusinessupport(String id) throws Exception {
        return infoListDao.getBusinessupport(id);
    }

    public void saveOrupdate(Object obj) throws Exception {
        infoListDao.saveOrupdate(obj);
    }

    public HashMap getAllNumberApplyInfoidByMainid(String mainid, Integer pageSize, Integer curPage) throws Exception {
        return infoListDao.getAllNumberApplyInfoidByMainid(mainid, pageSize, curPage);
    }

}
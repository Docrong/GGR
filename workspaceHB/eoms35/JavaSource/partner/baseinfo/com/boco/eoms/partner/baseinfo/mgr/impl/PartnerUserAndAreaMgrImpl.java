package com.boco.eoms.partner.baseinfo.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.partner.baseinfo.model.PartnerUserAndArea;
import com.boco.eoms.partner.baseinfo.mgr.PartnerUserAndAreaMgr;
import com.boco.eoms.partner.baseinfo.dao.PartnerUserAndAreaDao;

/**
 * <p>
 * Title:��Ա�������
 * </p>
 * <p>
 * Description:��Ա�������
 * </p>
 * <p>
 * Tue Mar 10 16:24:32 CST 2009
 * </p>
 *
 * @author liujinlong
 * @version 3.5
 */
public class PartnerUserAndAreaMgrImpl implements PartnerUserAndAreaMgr {

    private PartnerUserAndAreaDao partnerUserAndAreaDao;

    public PartnerUserAndAreaDao getPartnerUserAndAreaDao() {
        return this.partnerUserAndAreaDao;
    }

    public void setPartnerUserAndAreaDao(PartnerUserAndAreaDao partnerUserAndAreaDao) {
        this.partnerUserAndAreaDao = partnerUserAndAreaDao;
    }

    public List getPartnerUserAndAreas() {
        return partnerUserAndAreaDao.getPartnerUserAndAreas();
    }

    public PartnerUserAndArea getPartnerUserAndArea(final String id) {
        return partnerUserAndAreaDao.getPartnerUserAndArea(id);
    }

    public void savePartnerUserAndArea(PartnerUserAndArea partnerUserAndArea) {
        partnerUserAndAreaDao.savePartnerUserAndArea(partnerUserAndArea);
    }

    public void removePartnerUserAndArea(final String id) {
        partnerUserAndAreaDao.removePartnerUserAndArea(id);
    }

    public Map getPartnerUserAndAreas(final Integer curPage, final Integer pageSize,
                                      final String whereStr) {
        return partnerUserAndAreaDao.getPartnerUserAndAreas(curPage, pageSize, whereStr);
    }

    //由userId得到人员地域信息
    public PartnerUserAndArea getObjectByUserId(String userId) {
        return partnerUserAndAreaDao.getObjectByUserId(userId);
    }

    /**
     * 判断人力地域表userId是否唯一
     */
    public Boolean isunique(final String userId) {
        return partnerUserAndAreaDao.isunique(userId);
    }
}
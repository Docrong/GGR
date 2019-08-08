package com.boco.eoms.partner.baseinfo.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.partner.baseinfo.model.TawPartnerOil;
import com.boco.eoms.partner.baseinfo.mgr.TawPartnerOilMgr;
import com.boco.eoms.partner.baseinfo.dao.TawPartnerOilDao;

/**
 * <p>
 * Title:油机信息
 * </p>
 * <p>
 * Description:油机信息
 * </p>
 * <p>
 * Thu Feb 05 13:56:15 CST 2009
 * </p>
 *
 * @author fengshaohong
 * @version 3.5
 */
public class TawPartnerOilMgrImpl implements TawPartnerOilMgr {

    private TawPartnerOilDao tawPartnerOilDao;

    public TawPartnerOilDao getTawPartnerOilDao() {
        return this.tawPartnerOilDao;
    }

    public void setTawPartnerOilDao(TawPartnerOilDao tawPartnerOilDao) {
        this.tawPartnerOilDao = tawPartnerOilDao;
    }

    public List getTawPartnerOils() {
        return tawPartnerOilDao.getTawPartnerOils();
    }

    public TawPartnerOil getTawPartnerOil(final String id) {
        return tawPartnerOilDao.getTawPartnerOil(id);
    }

    public void saveTawPartnerOil(TawPartnerOil tawPartnerOil) {
        tawPartnerOilDao.saveTawPartnerOil(tawPartnerOil);
    }

    public void removeTawPartnerOil(final String id) {
        tawPartnerOilDao.removeTawPartnerOil(id);
    }

    public Map getTawPartnerOils(final Integer curPage, final Integer pageSize,
                                 final String whereStr) {
        return tawPartnerOilDao.getTawPartnerOils(curPage, pageSize, whereStr);
    }

    public Boolean isunique(final String oil_number) {
        // TODO 自动生成方法存根
        return tawPartnerOilDao.isunique(oil_number);
    }

    public String name2Id(final String dictName, final String parentDictId) {
        // TODO 自动生成方法存根
        return tawPartnerOilDao.name2Id(dictName, parentDictId);
    }

}
package com.boco.eoms.commons.mms.statreport.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.commons.mms.statreport.model.Statreport;
import com.boco.eoms.commons.mms.statreport.mgr.StatreportMgr;
import com.boco.eoms.commons.mms.statreport.dao.StatreportDao;

/**
 * <p>
 * Title:报表实例
 * </p>
 * <p>
 * Description:彩信报系统
 * </p>
 * <p>
 * Wed Feb 18 11:35:28 CST 2009
 * </p>
 *
 * @author 李振友
 * @version 3.5
 */
public class StatreportMgrImpl implements StatreportMgr {

    private StatreportDao statreportDao;

    public StatreportDao getStatreportDao() {
        return this.statreportDao;
    }

    public void setStatreportDao(StatreportDao statreportDao) {
        this.statreportDao = statreportDao;
    }

    public List getStatreports() {
        return statreportDao.getStatreports();
    }

    public Statreport getStatreport(final String id) {
        return statreportDao.getStatreport(id);
    }

    public List getStatreportForMmsreportId(final String mmsreportId) {
        return statreportDao.getStatreportForMmsreportId(mmsreportId);
    }

    public void saveStatreport(Statreport statreport) {
        statreportDao.saveStatreport(statreport);
    }

    public void removeStatreport(final String id) {
        statreportDao.removeStatreport(id);
    }

    public Map getStatreports(final Integer curPage, final Integer pageSize,
                              final String whereStr) {
        return statreportDao.getStatreports(curPage, pageSize, whereStr);
    }

}
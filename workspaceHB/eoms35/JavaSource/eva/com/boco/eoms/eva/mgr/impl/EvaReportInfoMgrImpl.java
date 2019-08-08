package com.boco.eoms.eva.mgr.impl;

import java.util.List;

import com.boco.eoms.base.api.EOMSMgr;
import com.boco.eoms.base.util.StaticVariable;
import com.boco.eoms.commons.system.dept.dao.TawSystemDeptDao;
import com.boco.eoms.commons.system.role.util.RoleConstants;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.model.TawSystemUserRefRole;
import com.boco.eoms.eva.dao.IEvaReportInfoDao;
import com.boco.eoms.eva.mgr.IEvaReportInfoMgr;
import com.boco.eoms.eva.model.EvaReportInfo;

public class EvaReportInfoMgrImpl implements IEvaReportInfoMgr {

    private IEvaReportInfoDao ReportInfoDao;

    public IEvaReportInfoDao getEvaReportInfoDao() {
        return ReportInfoDao;
    }

    public void setEvaReportInfoDao(IEvaReportInfoDao ReportInfoDao) {
        this.ReportInfoDao = ReportInfoDao;
    }

    public void saveEvaReportInfo(EvaReportInfo evaReportInfo) {
        ReportInfoDao.saveEvaReportInfo(evaReportInfo);
    }

    public EvaReportInfo getEvaReportInfo(String id) {
        return ReportInfoDao.getEvaReportInfo(id);
    }

    public void removeEvaReportInfo(EvaReportInfo evaReportInfo) {
        ReportInfoDao.removeEvaReportInfo(evaReportInfo);
    }

    public List getReportInfoByCondition(String conditions) {
        return ReportInfoDao.getReportInfoByCondition(conditions);
    }
}

package com.boco.eoms.eva.dao;

import java.util.List;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.eva.model.EvaReportInfo;

public interface IEvaReportInfoDao extends Dao {

    public EvaReportInfo getEvaReportInfo(String id);

    public void saveEvaReportInfo(EvaReportInfo evaReportInfo);

    public void removeEvaReportInfo(EvaReportInfo evaReportInfo);

    // 根据条件获取模板列表
    public List getReportInfoByCondition(String where);
}

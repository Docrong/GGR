package com.boco.eoms.eva.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.eva.dao.IEvaReportInfoDao;
import com.boco.eoms.eva.model.EvaReportInfo;

public class EvaReportInfoDaoHibernate extends BaseDaoHibernate implements IEvaReportInfoDao {

    public EvaReportInfo getEvaReportInfo(String id) {
        EvaReportInfo ReportInfo = (EvaReportInfo) getHibernateTemplate().get(EvaReportInfo.class, id);
        if (null == ReportInfo) {
            throw new ObjectRetrievalFailureException(EvaReportInfo.class, id);
        }
        return ReportInfo;
    }

    public void saveEvaReportInfo(EvaReportInfo evaReportInfo) {
        if (null == evaReportInfo.getId() || "".equals(evaReportInfo.getId())) {
            getHibernateTemplate().save(evaReportInfo);
        } else {
            getHibernateTemplate().saveOrUpdate(evaReportInfo);
        }
    }

    public void removeEvaReportInfo(EvaReportInfo evaReportInfo) {
        getHibernateTemplate().delete(evaReportInfo);
    }

    // 根据条件获取模板列表
    public List getReportInfoByCondition(String where) {
        return getHibernateTemplate().find("from EvaReportInfo eri where 1=1 " + where);
    }
}

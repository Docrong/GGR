package com.boco.eoms.eva.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dict.dao.ID2NameDAO;
import com.boco.eoms.eva.dao.IEvaKpiInstanceDao;
import com.boco.eoms.eva.mgr.IEvaTaskDetailMgr;
import com.boco.eoms.eva.mgr.IEvaTaskMgr;
import com.boco.eoms.eva.model.EvaKpi;
import com.boco.eoms.eva.model.EvaKpiInstance;
import com.boco.eoms.eva.model.EvaTask;
import com.boco.eoms.eva.util.EvaConstants;
import com.boco.eoms.filemanager.model.TawFileMgrScheme;

public class EvaKpiInstanceDaoHibernate extends BaseDaoHibernate implements
        IEvaKpiInstanceDao, ID2NameDAO {

    public EvaKpiInstance getKpiInstance(String id) {
        EvaKpiInstance instance = (EvaKpiInstance) getHibernateTemplate().get(
                EvaKpiInstance.class, id);
        if (null == instance) {
            throw new ObjectRetrievalFailureException(EvaKpiInstance.class, id);
        }
        return instance;
    }

    public void removeKpiInstance(EvaKpiInstance kpiInstance) {
        getHibernateTemplate().delete(kpiInstance);
    }

    public void saveKpiInstance(EvaKpiInstance kpiInstance) {
        if (null == kpiInstance.getId() || "".equals(kpiInstance.getId())) {
            getHibernateTemplate().save(kpiInstance);
        } else {
            getHibernateTemplate().saveOrUpdate(kpiInstance);
        }
    }

    public EvaKpiInstance getKpiInstanceByTimeAndPartner(
            final String taskDetailId, final String timeType,
            final String time, final String partnerId) {
        EvaKpiInstance eki = new EvaKpiInstance();
        String hql = "from EvaKpiInstance i where 1=1 ";
        if (taskDetailId != null && !"".equals(taskDetailId))
            hql = hql + " and i.taskDetailId='" + taskDetailId + "'";
        if (timeType != null && !"".equals(timeType))
            hql = hql + " and i.timeType='" + timeType + "'";
        if (time != null && !"".equals(time))
            hql = hql + " and i.time='" + time + "'";
        if (partnerId != null && !"".equals(partnerId))
            hql = hql + " and i.partnerId='" + partnerId + "'";
        List list = getHibernateTemplate().find(hql);
        if (list != null) {
            if (!list.isEmpty()) {
                eki = (EvaKpiInstance) list.iterator().next();
            }
        }
        return eki;
    }

    public List getKpiInstanceListByTimeAndPartner(
            final String taskDetailId, final String partnerId, final String timeType,
            final String startTime, final String endTime, final String isPublish) {
        String hql = "from EvaKpiInstance i where 1=1 ";
        if (taskDetailId != null && !"".equals(taskDetailId))
            hql = hql + " and i.taskDetailId='" + taskDetailId + "'";
        if (partnerId != null && !"".equals(partnerId))
            hql = hql + " and i.partnerId='" + partnerId + "'";
        if (timeType != null && !"".equals(timeType))
            hql = hql + " and i.timeType='" + timeType + "'";
        if (startTime != null && !"".equals(startTime))
            hql = hql + " and i.time >='" + startTime + "'";
        if (endTime != null && !"".equals(endTime))
            hql = hql + " and i.time <='" + endTime + "'";
        if (isPublish != null && !"".equals(isPublish))
            hql = hql + " and i.isPublish ='" + isPublish + "'";
        hql = hql + " order by i.partnerId,i.time desc";
        return getHibernateTemplate().find(hql);
    }

    public List listKpiInstanceOfTemplate(final String orgId,
                                          final String startDate, final String endDate) {
        final String hql = "from EvaKpiInstance instance where instance.orgId='"
                + orgId
                + "' and instance.genTime>=:startDate and instance.genTime<=:endDate";
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                Query query = session.createQuery(hql);
                query.setString("startDate", startDate);
                query.setString("endDate", endDate);
                List list = query.list();
                return list;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public EvaKpiInstance getKpiInstanceOfTemplate(final String orgId,
                                                   final String kpiId, final String startDate, final String endDate) {
        final String hql = "from EvaKpiInstance instance where instance.orgId='"
                + orgId
                + "' and instance.kpiId='"
                + kpiId
                + "' and instance.genTime>=:startDate and instance.genTime<=:endDate";
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                EvaKpiInstance instance = new EvaKpiInstance();
                Query query = session.createQuery(hql);
                query.setString("startDate", startDate);
                query.setString("endDate", endDate);
                List list = query.list();
                Iterator it = list.iterator();
                if (it.hasNext()) {
                    instance = (EvaKpiInstance) it.next();
                }
                return instance;
            }
        };
        return (EvaKpiInstance) getHibernateTemplate().execute(callback);
    }

    // 详细任务ID转换为模板名称
    // 王思轩 09-1-21
    public String id2Name(String id) {
        String kpiName = "";
        EvaTaskDaoHibernate etd = new EvaTaskDaoHibernate();
        if (id != null && !"".equals(id)) {
            EvaTask et = etd.getTaskById(id);
            //EvaTask et = etd.getTaskById(id);
            EvaTemplateDaoHibernate eth = new EvaTemplateDaoHibernate();
            if (et.getTemplateId() != null && !"".equals(et.getTemplateId())) {
                if (kpiName != null && !"".equals(kpiName)) {
                    kpiName = eth.id2Name(et.getTemplateId());
                } else {
                    kpiName = EvaConstants.NODE_NONAME;
                }
            } else {
                kpiName = EvaConstants.NODE_NONAME;
            }
        }
        kpiName = EvaConstants.NODE_NONAME;
        return kpiName;
    }
}

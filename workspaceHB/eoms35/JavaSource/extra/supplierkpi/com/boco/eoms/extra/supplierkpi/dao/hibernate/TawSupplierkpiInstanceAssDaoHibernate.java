
package com.boco.eoms.extra.supplierkpi.dao.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstanceAss;
import com.boco.eoms.extra.supplierkpi.report.statistic.entity.StatisticEntity;
import com.boco.eoms.extra.supplierkpi.util.SuppStaticVariable;
import com.boco.eoms.extra.supplierkpi.dao.TawSupplierkpiInstanceAssDao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TawSupplierkpiInstanceAssDaoHibernate extends BaseDaoHibernate implements TawSupplierkpiInstanceAssDao {

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiInstanceAssDao#getTawSupplierkpiInstanceAsss(com.boco.eoms.commons.sample.model.TawSupplierkpiInstanceAss)
     */
    public List getTawSupplierkpiInstanceAsss(final TawSupplierkpiInstanceAss tawSupplierkpiInstanceAss) {
        return getHibernateTemplate().find("from TawSupplierkpiInstanceAss");

        /* Remove the line above and uncomment this code block if you want 
           to use Hibernate's Query by Example API.
        if (tawSupplierkpiInstanceAss == null) {
            return getHibernateTemplate().find("from TawSupplierkpiInstanceAss");
        } else {
            // filter on properties set in the tawSupplierkpiInstanceAss
            HibernateCallback callback = new HibernateCallback() {
                public Object doInHibernate(Session session) throws HibernateException {
                    Example ex = Example.create(tawSupplierkpiInstanceAss).ignoreCase().enableLike(MatchMode.ANYWHERE);
                    return session.createCriteria(TawSupplierkpiInstanceAss.class).add(ex).list();
                }
            };
            return (List) getHibernateTemplate().execute(callback);
        }*/
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiInstanceAssDao#getTawSupplierkpiInstanceAss(String id)
     */
    public TawSupplierkpiInstanceAss getTawSupplierkpiInstanceAss(final String id) {
        TawSupplierkpiInstanceAss tawSupplierkpiInstanceAss = (TawSupplierkpiInstanceAss) getHibernateTemplate().get(TawSupplierkpiInstanceAss.class, id);
        if (tawSupplierkpiInstanceAss == null) {
            throw new ObjectRetrievalFailureException(TawSupplierkpiInstanceAss.class, id);
        }

        return tawSupplierkpiInstanceAss;
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiInstanceAssDao#saveTawSupplierkpiInstanceAss(TawSupplierkpiInstanceAss tawSupplierkpiInstanceAss)
     */
    public void saveTawSupplierkpiInstanceAss(final TawSupplierkpiInstanceAss tawSupplierkpiInstanceAss) {
        if ((tawSupplierkpiInstanceAss.getId() == null) || (tawSupplierkpiInstanceAss.getId().equals("")))
            getHibernateTemplate().save(tawSupplierkpiInstanceAss);
        else
            getHibernateTemplate().saveOrUpdate(tawSupplierkpiInstanceAss);
    }

    /**
     * @see com.boco.eoms.commons.sample.dao.TawSupplierkpiInstanceAssDao#removeTawSupplierkpiInstanceAss(String id)
     */
    public void removeTawSupplierkpiInstanceAss(final String id) {
        getHibernateTemplate().delete(getTawSupplierkpiInstanceAss(id));
    }

    /**
     * curPage
     * pageSize
     * whereStr   sql filter
     */
    public Map getTawSupplierkpiInstanceAsss(final int curPage, final int pageSize, final String whereStr) {
        // filter on properties set in the tawSupplierkpiInstanceAss
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String queryStr = "from TawSupplierkpiInstanceAss";
                if (whereStr != null && whereStr.length() > 0)
                    queryStr += whereStr;
                String queryCountStr = "select count(*) " + queryStr;

                Integer total = (Integer) session.createQuery(queryCountStr).iterate()
                        .next();
                Query query = session.createQuery(queryStr);
                query.setFirstResult(pageSize
                        * curPage);
                query.setMaxResults(pageSize);
                List result = query.list();
                HashMap map = new HashMap();
                map.put("total", total);
                map.put("result", result);
                return map;
            }
        };
        return (Map) getHibernateTemplate().execute(callback);
    }

    public Map getTawSupplierkpiInstanceAsss(final int curPage, final int pageSize) {
        return this.getTawSupplierkpiInstanceAsss(curPage, pageSize, null);
    }

    public TawSupplierkpiInstanceAss getTawSupplierkpiInstanceAssBySpecialType(final String specialType) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String currentTime = StaticMethod.getCurrentDateTime();
                int date = Integer.parseInt(currentTime.substring(8, 10));
                String py = SuppStaticVariable.getLocalString(date, 0);
                String year = py.substring(0, 4);
                String month = py.substring(5, 7);
                String queryStr = "from TawSupplierkpiInstanceAss where year='" + year + "' and timeLatitude='" + month + "' and specialType='" + specialType + "'";
                Query query = session.createQuery(queryStr);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List list = query.list();
                TawSupplierkpiInstanceAss tawSupplierkpiInstanceAss = new TawSupplierkpiInstanceAss();

                if (list != null && !list.isEmpty()) {
                    tawSupplierkpiInstanceAss = (TawSupplierkpiInstanceAss) list.iterator().next();
                }
                return tawSupplierkpiInstanceAss;
            }
        };
        return (TawSupplierkpiInstanceAss) getHibernateTemplate().execute(callback);
    }

    public List getNodesFromInstanceAss(final String whereStr) {
        return getHibernateTemplate().find(whereStr);
    }

    public List getStaticEntitis(final String modelId, final String reportTime, final String specialType, final String kpiId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String reportcol = getReportCol(modelId, kpiId);
                String queryStr = "";
                //如果是代维
                if (specialType.indexOf("10402") != -1) {
                    queryStr = "select t3.deptName,t1." + reportcol + " from TawSuppkpiReportStorage as t1,TawSystemSubRole as t2,TawSystemDept as t3 where t1.fillRole=t2.id and t2.deptId=t3.deptId and t1.specialType='" + specialType + "' and t1.reportTime='" + reportTime + "' and t1.modelId='" + modelId + "'";
                } else {
                    queryStr = "select t2.supplierName,t1." + reportcol + " from TawSuppkpiReportStorage as t1,TawSupplierkpiInfo as t2 where t1.manufacturerId=t2.id and t1.specialType='" + specialType + "' and t1.reportTime='" + reportTime + "' and t1.modelId='" + modelId + "'";
                }
                Query query = session.createQuery(queryStr);
                List list = query.list();
                List entityList = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    StatisticEntity entity = new StatisticEntity();
                    entity.setName(StaticMethod.nullObject2String(obj[0]));
                    entity.setValue(StaticMethod.getFloatValue(obj[1].toString()));
                    entityList.add(entity);
                }
                return entityList;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    public String getReportCol(final String modelId, final String kpiId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String queryStr = "select distinct(reportcol) from TawSuppkpiReportmodelMatching where modelId='" + modelId + "' and kpiitemid='" + kpiId + "'";
                Query query = session.createQuery(queryStr);
                query.setFirstResult(0);
                query.setMaxResults(1);
                List list = query.list();
                String reportcol = "";

                if (list != null && !list.isEmpty()) {
                    reportcol = list.iterator().next().toString();
                }
                return reportcol;
            }
        };
        return getHibernateTemplate().execute(callback).toString();
    }

    public List getVerticalStaticEntitis(final String modelId, final String reportTime, final String specialType, final String kpiId, final String manufacturerId) {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException {
                String reportcol = getReportCol(modelId, kpiId);
                String queryStr = "";
                //如果是代维
                if (specialType.indexOf("10402") != -1) {
                    queryStr = "select t3.deptName,t1." + reportcol + " from TawSuppkpiReportStorage as t1,TawSystemSubRole as t2,TawSystemDept as t3 where t1.fillRole=t2.id and t2.deptId=t3.deptId and t1.specialType='" + specialType + "' and t1.reportTime='" + reportTime + "' and t1.modelId='" + modelId + "'";
                } else {
                    queryStr = "select t1.reportTime,t1." + reportcol + " from TawSuppkpiReportStorage as t1,TawSupplierkpiInfo as t2 where t1.manufacturerId=t2.id and t2.id='" + manufacturerId + "' and t1.specialType='" + specialType + "' and t1.reportTime='" + reportTime + "' and t1.modelId='" + modelId + "'";
                }
                Query query = session.createQuery(queryStr);
                List list = query.list();
                List entityList = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    String reportTime = StaticMethod.nullObject2String(obj[0]);
                    String reportTimeName = "";
                    if (null != reportTime && !"".equals(reportTime)) {
                        if ("one".equals(reportTime)) {
                            reportTimeName = "第一季度";
                        } else if ("two".equals(reportTime)) {
                            reportTimeName = "第二季度";
                        } else if ("three".equals(reportTime)) {
                            reportTimeName = "第三季度";
                        } else if ("four".equals(reportTime)) {
                            reportTimeName = "第四季度";
                        } else {
                            reportTimeName = reportTime;
                        }
                    }
                    StatisticEntity entity = new StatisticEntity();
                    entity.setName(reportTimeName);
                    entity.setValue(StaticMethod.getFloatValue(obj[1].toString()));
                    entityList.add(entity);
                }
                return entityList;
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }
}

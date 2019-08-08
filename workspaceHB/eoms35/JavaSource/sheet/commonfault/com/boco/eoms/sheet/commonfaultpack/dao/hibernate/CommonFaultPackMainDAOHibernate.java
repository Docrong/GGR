package com.boco.eoms.sheet.commonfaultpack.dao.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.boco.eoms.sheet.base.dao.hibernate.MainDAO;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.util.Constants;

import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfaultpack.dao.ICommonFaultPackMainDAO;
import com.boco.eoms.sheet.commonfaultpack.model.CommonFaultPackMain;

public class CommonFaultPackMainDAOHibernate extends MainDAO implements
        ICommonFaultPackMainDAO {

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePO(java.lang.String)
     */
    public CommonFaultPackMain loadSinglePO(String id)
            throws HibernateException {
        CommonFaultPackMain main = (CommonFaultPackMain) getHibernateTemplate()
                .get(CommonFaultPackMain.class, id);
        return main;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#loadSinglePOByProcessId(java.lang.String)
     */
    public CommonFaultPackMain loadSinglePOByProcessId(String processId)
            throws HibernateException {
        CommonFaultPackMain main = new CommonFaultPackMain();
        String sql = " from CommonFaultPackMain as main where main.piid ='"
                + processId + "'";
        List list = getHibernateTemplate().find(sql);
        if (list.size() == 0) {
            main = null;
        } else {
            main = (CommonFaultPackMain) list.get(0);
        }
        return main;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.IMainDAO#deleteMain(java.lang.String)
     */
    public void deleteMain(String id) throws HibernateException {
        CommonFaultPackMain main = (CommonFaultPackMain) getHibernateTemplate()
                .get(CommonFaultPackMain.class, id);
        getHibernateTemplate().delete(main);
    }

    /**
     * @see com.boco.eoms.base.dao.Dao#saveObject(java.lang.Object)
     */
    public void saveObject(Object o) {
        getHibernateTemplate().saveOrUpdate(o);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.sheet.base.dao.hibernate.MainDAO#getMainName()
     */
    public String getMainName() {
        // TODO Auto-generated method stub
        return "CommonFaultPackMain";
    }

    /**
     * 打包告警工单列表
     *
     * @return
     * @throws HibernateException
     */
    public List getListByMainId(final Integer curPage, final Integer pageSize,
                                final String mainId) throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                // hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
                String queryStr = "from CommonFaultPackMain alias where "
                        + "alias.mainId=:mainId  order by alias.mainFaultGenerantTime ,alias.id desc";

                Query query = session.createQuery(queryStr);
                // 设置归档标记
                query.setString("mainId", mainId);

                // 分页查询条
                query
                        .setFirstResult(pageSize.intValue()
                                * (curPage.intValue()));

                query.setMaxResults(pageSize.intValue());
                System.out.println("packSQl===" + queryStr);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 打包告警工单列表
     *
     * @return
     * @throws HibernateException
     */
    public List getListByMainId(final String mainId) throws HibernateException {
        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                // hql，以备资源名称＋系统角色＋资源类别＋本机管理IP地址为条件查询
                String queryStr = "from CommonFaultPackMain alias where "
                        + "alias.mainId=:mainId  order by alias.mainFaultGenerantTime,alias.id desc";

                Query query = session.createQuery(queryStr);
                // 设置归档标记
                query.setString("mainId", mainId);
                return query.list();
            }
        };
        return (List) getHibernateTemplate().execute(callback);
    }

    /**
     * 打包告警数
     *
     * @return
     * @throws HibernateException
     */
    public Integer getCountByMainId(final String mainId)
            throws HibernateException {

        HibernateCallback callback = new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {

                // 取列表数量
                String queryCountStr = "select count(alias.id) from CommonFaultPackMain"
                        + " alias where alias.mainId=:mainId";
                Query query = session.createQuery(queryCountStr);
                // TODO 归档标记，需确认
                query.setString("mainId", mainId);
                Integer total = (Integer) query.iterate().next();
                return total;
            }
        };
        return (Integer) getHibernateTemplate().execute(callback);
    }

    /**
     * 通过告警号获取工单
     *
     * @param alarmId 告警号
     * @return
     * @throws HibernateException
     */
    public CommonFaultPackMain getMainByAlarmId(String alarmId) throws HibernateException {
        CommonFaultPackMain main = new CommonFaultPackMain();
        String sql = "from CommonFaultPackMain as main where main.mainAlarmNum ='"
                + alarmId + "'";
        List list = getHibernateTemplate().find(sql);
        if (list.size() == 0) {
            main = null;
        } else {
            main = (CommonFaultPackMain) list.get(0);
        }
        return main;
    }


    public String getIfAlarmSolve(String alarmId) throws HibernateException {
        String ifAlarmSolve = "";
        String sql = "from CommonFaultPackMain as main where main.mainId ='"
                + alarmId + "' and main.mainAlarmSolveDate is null";
        List list = getHibernateTemplate().find(sql);
        if (list.size() == 0) {
            ifAlarmSolve = "true";
        } else {
            ifAlarmSolve = "部分追单告警没有告警恢复时间";
        }
        return ifAlarmSolve;
    }


    public List getMainsByMainId(String mainId) {
        String sql = "from CommonFaultPackMain as main where main.mainId ='" + mainId + "' and main.mainAlarmSolveDate is null";
        return getHibernateTemplate().find(sql);
    }

}

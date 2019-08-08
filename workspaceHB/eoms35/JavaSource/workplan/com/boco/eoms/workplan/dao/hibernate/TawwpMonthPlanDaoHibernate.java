package com.boco.eoms.workplan.dao.hibernate;

import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import com.boco.eoms.base.dao.hibernate.BaseDaoHibernate;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.db.hibernate.HibernateUtil;
import com.boco.eoms.workplan.dao.ITawwpMonthPlanDao;
import com.boco.eoms.workplan.model.TawwpExecuteReport;
import com.boco.eoms.workplan.model.TawwpMonthPlan;
import com.boco.eoms.workplan.model.TawwpNet;
import com.boco.eoms.workplan.model.TawwpYearPlan;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Jun 26, 2008 12:51:19 AM
 * </p>
 *
 * @author 曲静�?
 * @version 3.5.1
 */
public class TawwpMonthPlanDaoHibernate extends BaseDaoHibernate implements
        ITawwpMonthPlanDao {
    /**
     * 保存月度作业计划
     *
     * @param _tawwpMonthPlan TawwpMonthPlan 月度作业计划�? @ 操作异常
     */
    public void saveMonthPlan(TawwpMonthPlan _tawwpMonthPlan) {
        // this.save(_tawwpMonthPlan);
        this.getHibernateTemplate().save(_tawwpMonthPlan);
    }

    /**
     * 删除月度作业计划
     *
     * @param _tawwpMonthPlan TawwpMonthPlan 月度作业计划�? @ 操作异常
     */
    public void deleteMonthPlan(TawwpMonthPlan _tawwpMonthPlan) {
        // this.delete(_tawwpMonthPlan);
        this.getHibernateTemplate().delete(_tawwpMonthPlan);
    }

    /**
     * 根据Id 批量查找作业计划
     *
     * @param _userId String 负责人用�? @ 操作异常
     * @return List 月度作业计划类列�?
     */
    public List listMonthPlanByIds(String ids) {
        // Session s = HibernateUtil.currentSession();
        // String hSql = "";
        String hSql = "from TawwpMonthPlan as tawwpmonthplan where tawwpmonthplan.deleted = '0' "
                + "  and tawwpmonthplan.id  in ("
                + ids
                + ") order by tawwpmonthplan.crtime";

        // Query query = s.createQuery(hSql);
        // return query.list();
        return this.getHibernateTemplate().find(hSql);
    }

    /**
     * 修改月度作业计划
     *
     * @param _tawwpMonthPlan TawwpMonthPlan 月度作业计划�? @ 操作异常
     */
    public void updateMonthPlan(TawwpMonthPlan _tawwpMonthPlan) {
        // this.update(_tawwpMonthPlan);
        this.getHibernateTemplate().update(_tawwpMonthPlan);
    }

    /**
     * 查询月度作业计划信息
     *
     * @param id String 月度作业计划标识 @ 操作异常
     * @return TawwpMonthPlan 月度作业计划�?
     */
    public TawwpMonthPlan loadMonthPlan(String id) {
        // return (TawwpMonthPlan) this.load(id, TawwpMonthPlan.class);
        return (TawwpMonthPlan) this.getHibernateTemplate().get(
                TawwpMonthPlan.class, id);
    }

    /**
     * 加载月度作业计划信息 先清理缓�?
     *
     * @param id String 月度作业计划标识 @ 操作异常
     * @return TawwpMonthPlan 月度作业计划�?
     */
    public TawwpMonthPlan getdMonthPlan(String id) {
        // return (TawwpMonthPlan) this.get(id, TawwpMonthPlan.class);
        return loadMonthPlan(id);
    }

    /**
     * 查询�?有月度作业计划信�? @ 操作异常
     *
     * @return List 月度作业计划类列�?
     */
    public List listMonthPlan() {
        // Session s = HibernateUtil.currentSession();
        // String hSql = "";
        // hSql = "from TawwpMonthPlan as tawwpmonthplan where
        // tawwpmonthplan.deleted = '0' ";
        //
        // Query query = s.createQuery(hSql);
        // return query.list();

        return this
                .getHibernateTemplate()
                .find(
                        "from TawwpMonthPlan as tawwpmonthplan where tawwpmonthplan.deleted = '0' ");
    }

    public List monthListCode() {//改成从年计划表里查询
        // Session s = HibernateUtil.currentSession();
        // String hSql = "";
        // hSql = "from TawwpMonthPlan as tawwpmonthplan where
        // tawwpmonthplan.deleted = '0' ";
        //
        // Query query = s.createQuery(hSql);
        // return query.list();

        return this
                .getHibernateTemplate()
                .find(
                        "from TawwpYearPlan as tawwpmonthplan where tawwpmonthplan.deleted = '0' ");
    }

    /**
     * 查询1.指定部门�?2.指定月度�?,全部月度作业计划信息
     *
     * @param _deptId    String 部门编号
     * @param _yearFlag  String 执行年度
     * @param _monthFlag String 执行月度 @ 异常
     * @return List 月度作业计划类列�?
     */
    public List listMonthPlan(String _deptId, String _yearFlag,
                              String _monthFlag) {
        // Session s = HibernateUtil.currentSession();
        StringBuffer hSql = new StringBuffer();
        hSql
                .append("from TawwpMonthPlan as tawwpmonthplan where tawwpmonthplan.deleted = '0' and tawwpmonthplan.tawwpYearPlan.deleted = '0'");
        if (_deptId != null && !_deptId.equals("")) {
            hSql.append(" and tawwpmonthplan.deptId = '" + _deptId + "' ");
        }
        if (_yearFlag != null && !_yearFlag.equals("")) {
            hSql.append("  and tawwpmonthplan.yearFlag = '" + _yearFlag + "' ");
        }
        if (_monthFlag != null && !_monthFlag.equals("")) {
            hSql.append("  and tawwpmonthplan.monthFlag = '" + _monthFlag + "' ");
        }
        // + "and tawwpmonthplan.deptId = '"
        // + _deptId
        // + "' and tawwpmonthplan.monthFlag = '"
        // + _monthFlag
        // + "' and tawwpmonthplan.yearFlag = '" + _yearFlag + "'";
        //
        // Query query = s.createQuery(hSql);
        // return query.list();

        return this.getHibernateTemplate()
                .find(hSql.toString());
    }

    /**
     * 查询指定月度�?,全部月度作业计划信息
     *
     * @param _yearFlag   String 执行年度
     * @param _monthFlag  String 执行月度
     * @param _yearPlanId String 年度作业计划标识
     * @param _deptId     String 部门 用�?�，”分�? @ 异常
     * @return List 月度作业计划类列�?
     */
    public List listMonthPlan(String _yearFlag, String _monthFlag,
                              String _yearPlanId, String _deptId) {
        // Session s = HibernateUtil.currentSession();
        String hSql = "";

        if (_deptId.equals("-1")) {
            hSql = "from TawwpMonthPlan as tawwpmonthplan where tawwpmonthplan.deleted = '0' "
                    + "and tawwpmonthplan.monthFlag = '"
                    + _monthFlag
                    + "' and tawwpmonthplan.yearFlag = '"
                    + _yearFlag
                    + "' and tawwpmonthplan.yearPlanId = '"
                    + _yearPlanId
                    + "' order by deptId";
        } else {
            hSql = "from TawwpMonthPlan as tawwpmonthplan where tawwpmonthplan.deleted = '0' "
                    + "and tawwpmonthplan.monthFlag = '"
                    + _monthFlag
                    + "' and tawwpmonthplan.yearFlag = '"
                    + _yearFlag
                    + "' and tawwpmonthplan.yearPlanId = '"
                    + _yearPlanId
                    + "' and tawwpmonthplan.deptId = ("
                    + _deptId
                    + ") order by deptId";
        }

        // Query query = s.createQuery(hSql);
        return this.getHibernateTemplate().find(hSql);

        // return query.list();
    }

    /**
     * 获取月度作业计划的月报或周报信息
     *
     * @param _tawwpMonthPlan TawwpMonthPlan 月度作业计划对象
     * @param _state          String 状�?? 0：周�? 1：月�? @ 异常
     * @return List 月报或周报信息集�?
     */
    public List filterTawwpExecuteReprot(TawwpMonthPlan _tawwpMonthPlan,
                                         String _state) {
        // Session s = HibernateUtil.currentSession();
        // List result =
        // s.createFilter(_tawwpMonthPlan.getTawwpExecuteReports(),
        // "where this.reportType='" + _state + "'").list();
        // return result;

        // Session session = this.getHibernateTemplate().getSessionFactory()
        // .openSession();
        // List result = session.createFilter(
        // _tawwpMonthPlan.getTawwpExecuteReports(),
        // "where this.reportType='" + _state + "'").list();
        //
        // session.close();

        List result = new ArrayList();
        if (_tawwpMonthPlan != null
                && _tawwpMonthPlan.getTawwpExecuteReports() != null) {
            for (Iterator it = _tawwpMonthPlan.getTawwpExecuteReports()
                    .iterator(); it.hasNext(); ) {
                TawwpExecuteReport report = (TawwpExecuteReport) it.next();
                if (_state.equals(report.getReportType())) {
                    result.add(report);
                }
            }
        }
        return result;

    }

    /**
     * 查询1.指定月度�?2.与指定年度作业计划关联�??3.指定状�?�的,全部月度作业计划信息
     *
     * @param _tawwpYearPlan TawwpYearPlan 年度作业计划对象
     * @param _monthFlag     String 执行月度
     * @param _state         String 状�?? @ 异常
     * @return List 月度作业计划类列�?
     */
    public List listByYearPlan(String _monthFlag, TawwpYearPlan _tawwpYearPlan,
                               String _state) {

        // Session s = HibernateUtil.currentSession();
        // Criteria criteria = s.createCriteria(TawwpMonthPlan.class);
        //
        // criteria.add(Expression.eq("tawwpYearPlan", _tawwpYearPlan));
        // criteria.add(Expression.eq("monthFlag", _monthFlag));
        // if (!"-1".equals(_state)) {
        // criteria.add(Expression.eq("constituteState", _state));
        // }
        // criteria.add(Expression.eq("deleted", "0"));
        // criteria.toString();
        // return criteria.list();

        DetachedCriteria criteria = DetachedCriteria
                .forClass(TawwpMonthPlan.class);
        criteria.add(Expression.eq("tawwpYearPlan", _tawwpYearPlan));
        criteria.add(Expression.eq("monthFlag", _monthFlag));
        if (!"-1".equals(_state)) {
            criteria.add(Expression.eq("constituteState", _state));
        }
        criteria.add(Expression.eq("deleted", "0"));
        criteria.toString();
        return getHibernateTemplate().findByCriteria(criteria);

    }

    /**
     * 判断是否具有该网元的月度作业计划
     *
     * @param _monthFlag     String 月度
     * @param _tawwpYearPlan TawwpYearPlan 年度作业计划
     * @param _tawwpNet      TawwpNet 网元 @ 异常
     * @return boolean 是否有该网元的月度作业计�?
     */
    public boolean exitMonthPlanOfNet(String _monthFlag,
                                      TawwpYearPlan _tawwpYearPlan, TawwpNet _tawwpNet) {

        // Session s = HibernateUtil.currentSession();
        // Criteria criteria = s.createCriteria(TawwpMonthPlan.class);
        //
        // criteria.add(Expression.eq("tawwpYearPlan", _tawwpYearPlan));
        // if (_tawwpNet != null) {
        // criteria.add(Expression.eq("tawwpNet", _tawwpNet));
        // } else {
        // criteria.add(Expression.isNull("tawwpNet"));
        // }
        // criteria.add(Expression.eq("monthFlag", _monthFlag));
        // criteria.add(Expression.eq("deleted", "0"));
        // criteria.toString();
        // if (criteria.list().size() > 0) {
        // return true;
        // } else {
        // return false;
        // }

        DetachedCriteria criteria = DetachedCriteria
                .forClass(TawwpMonthPlan.class);

        criteria.add(Expression.eq("tawwpYearPlan", _tawwpYearPlan));
        if (_tawwpNet != null) {
            criteria.add(Expression.eq("tawwpNet", _tawwpNet));
        } else {
            criteria.add(Expression.isNull("tawwpNet"));
        }
        criteria.add(Expression.eq("monthFlag", _monthFlag));
        criteria.add(Expression.eq("deleted", "0"));
        criteria.toString();
        List list = getHibernateTemplate().findByCriteria(criteria, 0, 1);
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 查询与指定年度作业计划关联和网元信息相同的作业计划信�?
     *
     * @param _tawwpYearPlan TawwpYearPlan
     * @param _tawwpNet      TawwpNet @
     * @return List
     */
    public List listByYearPlanNet(TawwpYearPlan _tawwpYearPlan,
                                  TawwpNet _tawwpNet) {

        // Session s = HibernateUtil.currentSession();
        // Criteria criteria = s.createCriteria(TawwpMonthPlan.class);
        //
        // criteria.add(Expression.eq("tawwpYearPlan", _tawwpYearPlan));
        // criteria.add(Expression.eq("tawwpNet", _tawwpNet));
        // criteria.add(Expression.eq("constituteState", "1"));
        // criteria.add(Expression.eq("deleted", "0"));
        // criteria.toString();
        // return criteria.list();
        //
        DetachedCriteria criteria = DetachedCriteria
                .forClass(TawwpMonthPlan.class);
        criteria.add(Expression.eq("tawwpYearPlan", _tawwpYearPlan));
        criteria.add(Expression.eq("tawwpNet", _tawwpNet));
        criteria.add(Expression.eq("constituteState", "1"));
        criteria.add(Expression.eq("deleted", "0"));
        // criteria.toString();

        return getHibernateTemplate().findByCriteria(criteria);

    }

    /**
     * 查询1.指定月度�?2.与指定年度作业计划关联�??3.指定状�?�的,全部月度作业计划信息
     *
     * @param _monthFlag     String 指定月度
     * @param _tawwpYearPlan TawwpYearPlan 年度作业计划对象
     * @param _state         String 制定状�?? @ 异常
     * @return List 月度作业计划对象集合
     */
    public List filterTawwpMonth(String _monthFlag,
                                 TawwpYearPlan _tawwpYearPlan, String _state) {
        // Session s = HibernateUtil.currentSession();
        // Session s = this.getHibernateTemplate().getSessionFactory()
        // .openSession();
        List result = new ArrayList();
        if (!"-1".equals(_state)) {
            // result = s.createFilter(
            // _tawwpYearPlan.getTawwpMonthPlans(),
            // "where this.monthFlag = '" + _monthFlag
            // + "' and this.constituteState = '" + _state + "'")
            // .list();
            if (_tawwpYearPlan != null
                    && _tawwpYearPlan.getTawwpMonthPlans() != null) {
                for (Iterator it = _tawwpYearPlan.getTawwpMonthPlans()
                        .iterator(); it.hasNext(); ) {
                    TawwpMonthPlan plan = (TawwpMonthPlan) it.next();
                    if (_monthFlag.equals(plan.getMonthFlag())
                            && _state.equals(plan.getConstituteState())) {
                        result.add(plan);
                    }
                }
            }

        } else {
            // result = s.createFilter(_tawwpYearPlan.getTawwpMonthPlans(),
            // "where this.monthFlag = '" + _monthFlag + "'").list();

            if (_tawwpYearPlan != null
                    && _tawwpYearPlan.getTawwpMonthPlans() != null) {
                for (Iterator it = _tawwpYearPlan.getTawwpMonthPlans()
                        .iterator(); it.hasNext(); ) {
                    TawwpMonthPlan plan = (TawwpMonthPlan) it.next();
                    if (_monthFlag.equals(plan.getMonthFlag())) {
                        result.add(plan);
                    }
                }
            }
        }
        // s.close();
        return result;
    }

    /**
     * 根据查询信息map，查询符合条件的月度作业计划 条件包括 name 月度作业计划名称 deptId 部门 sysTypeId 系统类别
     * netTypeId 网元类型 yearFlag 年度 monthFlag 月度 constituteState 制定状�?? executeState
     * 执行状�?? tawwpNet 网元
     *
     * @param _map Map 查询条件map @ 操作异常
     * @return List 月度作业计划列表
     */
    public List searchMonthPlan(Map _map) {
        // Session s = HibernateUtil.currentSession();
        // Criteria criteria = s.createCriteria(TawwpMonthPlan.class);
        DetachedCriteria criteria = DetachedCriteria
                .forClass(TawwpMonthPlan.class);
        String condition = null;
        List netList = null;

        // 获取条件信息列表
        Set set = _map.keySet();
        Iterator iterator = set.iterator();

        // 循环增加查询条件
        while (iterator.hasNext()) {
            condition = (String) iterator.next(); // 获取�?个查询提�?

            if (condition != null) {
                if (condition.equals("name")) {
                    criteria.add(Expression
                            .like(condition, _map.get(condition)));
                } else if (condition.equals("netId")) {
                    netList = (List) _map.get(condition);
                    criteria.add(Expression.in("tawwpNet", netList));
                } else {
                    criteria.add(Expression.eq(condition, _map.get(condition)));
                }
            }
        }
        // 获取查询结果
        return getHibernateTemplate().findByCriteria(criteria);

    }


    /* (non-Javadoc)
     * @see com.boco.eoms.workplan.dao.ITawwpMonthPlanDao#searchMonthPlanByNext(java.util.Map)
     */
    public List searchMonthPlanByNext(Map _map) {
        // Session s = HibernateUtil.currentSession();
        // Criteria criteria = s.createCriteria(TawwpMonthPlan.class);
        DetachedCriteria criteria = DetachedCriteria
                .forClass(TawwpMonthPlan.class);
        String condition = null;
        List netList = null;
        String[] deptList = null;

        // 获取条件信息列表
        Set set = _map.keySet();
        Iterator iterator = set.iterator();

        // 循环增加查询条件
        while (iterator.hasNext()) {
            condition = (String) iterator.next(); // 获取�?个查询提�?

            if (condition != null) {
                if (condition.equals("name")) {
                    criteria.add(Expression
                            .like(condition, _map.get(condition)));
                } else if (condition.equals("netId")) {
                    netList = (List) _map.get(condition);
                    criteria.add(Expression.in("tawwpNet", netList));
                } else if (condition.equals("deptId")) {
                    deptList = (String[]) _map.get(condition);
                    criteria.add(Expression.in("deptId", deptList));
                } else if (condition.equals("isNext")) {
                } else {
                    criteria.add(Expression.eq(condition, _map.get(condition)));
                }
            }
        }
        // 获取查询结果
        return getHibernateTemplate().findByCriteria(criteria);

    }

    /**
     * 查询异常�?有月度作业计划信�?
     *
     * @param _userId String 负责人用�? @ 操作异常
     * @return List 月度作业计划类列�?
     */
    public List listAllMonthPlan() {
        // Session s = HibernateUtil.currentSession();
        // String hSql = "";
        String hSql = "from TawwpMonthPlan as tawwpmonthplan where tawwpmonthplan.deleted = '0' "
                + " and tawwpmonthplan.executeState <> '1' and tawwpmonthplan.executeState <> '0'";

        // Query query = s.createQuery(hSql);
        // return query.list();
        return this.getHibernateTemplate().find(hSql);
    }

    /**
     * 查询�?有月度作业计划信�?
     *
     * @param _userId String 负责人用�? @ 操作异常
     * @return List 月度作业计划类列�?
     */
    public List listMonthPlan(String _userId) {
        // Session s = HibernateUtil.currentSession();
        // String hSql = "";
        String hSql = "from TawwpMonthPlan as tawwpmonthplan where tawwpmonthplan.deleted = '0' "
                + "and tawwpmonthplan.principal = '"
                + _userId
                + "' and tawwpmonthplan.executeState <> '1' and tawwpmonthplan.executeState <> '0' order by tawwpmonthplan.crtime desc";

        // Query query = s.createQuery(hSql);
        // return query.list();
        return this.getHibernateTemplate().find(hSql);
    }

    /**
     * added by lijia 2005-11-28 查询1.指定月度�?2.与指定年度作业计划关联�??3.属于指定部门�?,全部月度作业计划信息
     *
     * @param _monthFlag     String 指定月度
     * @param _tawwpYearPlan TawwpYearPlan 年度作业计划对象
     * @param _deptId        String 制定部门 @ 异常
     * @return List 月度作业计划对象集合
     */
    public List filterTawwpMonthByDept(String _monthFlag,
                                       TawwpYearPlan _tawwpYearPlan, String _deptId) {
        // Session session = this.getHibernateTemplate().getSessionFactory()
        // .openSession();

        List result = new ArrayList();

        // result = session.createFilter(
        // _tawwpYearPlan.getTawwpMonthPlans(),
        // "where this.monthFlag = '" + _monthFlag
        // + "' and this.deptId = '" + _deptId + "'").list();
        // session.close();

        if (_tawwpYearPlan != null
                && _tawwpYearPlan.getTawwpMonthPlans() != null) {
            for (Iterator it = _tawwpYearPlan.getTawwpMonthPlans().iterator(); it
                    .hasNext(); ) {
                TawwpMonthPlan plan = (TawwpMonthPlan) it.next();
                if (_monthFlag.equals(plan.getMonthFlag())
                        && _deptId.equals(plan.getDeptId())) {
                    result.add(plan);
                }
            }
        }
        return result;
    }

    public List listMonthPlanOfChildDeptSpec(String _deptId, String _yearFlag,
                                             String _monthFlag, String _advice) {
        String hSql = "";
        if (!"".equals(_advice)) {
            hSql = "select distinct tawwpmonthplan from TawwpMonthPlan as tawwpmonthplan,TawwpMonthExecute as tawwpmonthexecute  where tawwpmonthplan.deleted = '0' "
                    + "and tawwpmonthplan.deptId in ("
                    + _deptId
                    + ") and tawwpmonthplan.monthFlag = '"
                    + _monthFlag
                    + "' and tawwpmonthplan.yearFlag = '"
                    + _yearFlag
                    + "' and tawwpmonthplan.id = tawwpmonthexecute.tawwpMonthPlan.id and tawwpmonthexecute.advice = '"
                    + _advice + "'";
        } else {
            hSql = "select distinct tawwpmonthplan from TawwpMonthPlan as tawwpmonthplan,TawwpMonthExecute as tawwpmonthexecute  where tawwpmonthplan.deleted = '0' "
                    + "and tawwpmonthplan.deptId in ("
                    + _deptId
                    + ") and tawwpmonthplan.monthFlag = '"
                    + _monthFlag
                    + "' and tawwpmonthplan.yearFlag = '" + _yearFlag + "'";
        }
        return this.getHibernateTemplate().find(hSql);
    }

    public List listNetMonthPlanOfChildDept(String _deptId, String _yearFlag,
                                            String _monthFlag) {
        String hSql = "";
        hSql = "from TawwpMonthPlan as tawwpmonthplan where tawwpmonthplan.deleted = '0' "
                + "and tawwpmonthplan.deptId in ("
                + _deptId
                + ") and tawwpmonthplan.monthFlag = '"
                + _monthFlag
                + "' and tawwpmonthplan.tawwpNet is not null and tawwpmonthplan.yearFlag = '"
                + _yearFlag + "' order by tawwpmonthplan.name";

        return this.getHibernateTemplate().find(hSql);
    }

    public List listNetNum(String _deptId, String _yearFlag, String _monthFlag) {
        String hSql = "";
        hSql = " select count(tawwpmonthplan.tawwpNet)"
                + " from TawwpMonthPlan as tawwpmonthplan where tawwpmonthplan.deleted = '0'"
                + "and tawwpmonthplan.deptId in ("
                + _deptId
                + ") and tawwpmonthplan.monthFlag = '"
                + _monthFlag
                + "' and tawwpmonthplan.tawwpNet is not null and tawwpmonthplan.yearFlag = '"
                + _yearFlag + "' group by tawwpmonthplan.tawwpNet";

        return this.getHibernateTemplate().find(hSql);
    }

    /* (non-Javadoc)
     * @see com.boco.eoms.workplan.dao.ITawwpMonthPlanDao#ListYearOrderByMonth(java.lang.String)
     * add by gongyufeng
     */

    /**
     * add by gongyufeng  按月份将某年度作业计划中一年的作业计划导出到一个EXCEL文件中
     *
     * @param year
     * @return
     */
    public List ListYearOrderByMonth(String year) {
        String hSql = "";
        hSql = " from TawwpMonthPlan as tawwpmonthplan where tawwpmonthplan.deleted = '0' and tawwpmonthplan.yearFlag='"
                + year + "' order by tawwpmonthplan.monthFlag";
        return this.getHibernateTemplate().find(hSql);
    }

    /**
     * update by zhaodongliang 根据年计划ID和月度标示获取月度作业计划的信息
     *
     * @param yearPlanId 年计划ID
     * @param months     月度信息，以“'05','06','07'”这种方式传入
     * @return
     */
    public List getMonthPlan(String yearPlanId, String months) {
        // TODO query.setCacheable(true);
        String hSql = "";
        hSql = "from TawwpMonthPlan as tawwpMonthPlan where tawwpMonthPlan.deleted = '0' and tawwpMonthPlan.yearPlanId = '"
                + yearPlanId
                + "' and tawwpMonthPlan.monthFlag in ("
                + months
                + ")";
        return getHibernateTemplate().find(hSql);
    }


    /**
     * 取到当前网元，当前日期的作业计划
     *
     * @param netid
     * @param year
     * @param months
     * @return
     */
    public List getMonthPlanByNet(String netid, String year, String months) {
        // TODO query.setCacheable(true);
        String hSql = "";
        hSql = "from TawwpMonthPlan as tawwpMonthPlan where tawwpMonthPlan.deleted = '0' and tawwpMonthPlan.yearFlag = '"
                + year
                + "' and tawwpMonthPlan.monthFlag ='"
                + months
                + "' and tawwpMonthPlan.netTypeId = '" + netid + "'";
        return getHibernateTemplate().find(hSql);
    }

    /**
     * 查询1.指定部门包括子部门、2.指定月度的,全部月度作业计划信息
     *
     * @param _deptId    String 部门编号
     * @param _yearFlag  String 执行年度
     * @param _monthFlag String 执行月度
     * @return List 月度作业计划类列表
     * @throws Exception 异常
     */
    public List listMonthPlanOfChildDept(String _deptId, String _yearFlag,
                                         String _monthFlag) throws Exception {

        String hSql = "";
        hSql = "from TawwpMonthPlan as tawwpmonthplan where tawwpmonthplan.deleted = '0' "
                + "and tawwpmonthplan.deptId in ("
                + _deptId
                + ") and tawwpmonthplan.monthFlag = '"
                + _monthFlag
                + "' and tawwpmonthplan.yearFlag = '" + _yearFlag + "'";

        return getHibernateTemplate().find(hSql);
    }

    /**
     * 查询所有月度作业计划执行内容信息
     *
     * @return List 月度作业计划执行内容类列表
     * @throws Exception 操作异常
     */
    public List listMonthExecute(String _id) throws Exception {
        String hSql = "";
        hSql = "from TawwpMonthExecute as tawwpmonthexecute where tawwpmonthexecute.deleted = '0' and tawwpmonthexecute.tawwpMonthPlan='" + _id + "'";
        return getHibernateTemplate().find(hSql);
    }


    /* (non-Javadoc)
     * 月度查詢級聯下級部門
     */
    public List searchMonthPlanLink(Map _mapQuery) {
        String deptId, netId, constituteState, yearFlag, sysTypeId, netTypeId, executeState, monthFlag = "";

        yearFlag = (String) _mapQuery.get("yearFlag");
        sysTypeId = (String) _mapQuery.get("sysTypeId");
        netTypeId = (String) _mapQuery.get("netTypeId");
        deptId = (String) _mapQuery.get("deptId");
        executeState = (String) _mapQuery.get("executeState");
        monthFlag = (String) _mapQuery.get("monthFlag");
        netId = (String) _mapQuery.get("netId");
        constituteState = (String) _mapQuery.get("constituteState");
        List deptIdlist = (List) _mapQuery.get("deptIdlist");
        TawSystemDept tawSystemDept = new TawSystemDept();
        String deptIds = "";

        if (deptIdlist != null) {
            for (int i = 0; i < deptIdlist.size(); i++) {
                tawSystemDept = (TawSystemDept) deptIdlist.get(i);
                deptIds += "'" + tawSystemDept.getDeptId() + "',";

            }
            if (!"".equals(deptIds)) {
                deptIds = deptIds.substring(0, deptIds.length() - 1);
                //+ ",'"+deptId+"'"
            }
        }
        String hSql = "";

        hSql = "from TawwpMonthPlan as tawwpMonthPlan where tawwpMonthPlan.deleted = '0' ";

        if (monthFlag != null && !monthFlag.equals("")) {
            hSql += " and monthFlag = '" + monthFlag + "'";
        }
        if (netId != null && !netId.equals("")) {
            hSql += " and netId = '" + netId + "'";
        }
        if (yearFlag != null && !yearFlag.equals("")) {
            hSql += " and yearFlag = '" + yearFlag + "'";
        }
        if (sysTypeId != null && !sysTypeId.equals("")) {
            hSql += " and sysTypeId = '" + sysTypeId + "'";
        }
        if (netTypeId != null && !netTypeId.equals("")) {
            hSql += " and netTypeId = '" + netTypeId + "'";
        }
        if (constituteState != null && !constituteState.equals("")) {
            hSql += " and constituteState = '" + constituteState + "'";
        }
        if (executeState != null && !executeState.equals("")) {
            hSql += " and executeState = '" + executeState + "'";
        }
        if (deptId != null && !deptId.equals("")) {
            hSql += " and deptId in (" + deptIds + ")";
        }
        System.out.println("@@@@@" + hSql);
        return getHibernateTemplate().find(hSql);

    }

    /**
     * 取得指定的年度作业计划中，制定月份小于当前月份，并且已经通过审批的作业计划集合
     *
     * @param netid
     * @param year
     * @param months
     * @return
     */
    public List getEarlyCrrMonthPlan(TawwpYearPlan _tawwpYearPlan, String _monthFlag) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TawwpMonthPlan.class);
        criteria.add(Expression.eq("tawwpYearPlan", _tawwpYearPlan));
        criteria.add(Expression.lt("monthFlag", _monthFlag));
        criteria.add(Expression.eq("constituteState", "1"));
        criteria.addOrder(Order.asc("tawwpNet")).addOrder(Order.asc("monthFlag"));
        criteria.toString();
        return getHibernateTemplate().findByCriteria(criteria);
    }

    /**
     * 取得不可以删除（已执行）的月度作业计划
     *
     * @param netid
     * @param year
     * @param months
     * @return
     */
    public List listNoDeleteMonthPlans(String _yearFlag,
                                       String _monthFlag) {
        String hSql = "select count(tawwpMonthExecuteUser.tawwpMonthPlan.id),tawwpMonthExecuteUser.tawwpMonthPlan.id from TawwpMonthExecuteUser as tawwpMonthExecuteUser " +
                "where tawwpMonthExecuteUser.yearFlag='"
                + _yearFlag + "' and tawwpMonthExecuteUser.monthFlag='"
                + _monthFlag + "' and tawwpMonthExecuteUser.state='4' group by tawwpMonthExecuteUser.tawwpMonthPlan.id";
        return getHibernateTemplate().find(hSql);
    }
}

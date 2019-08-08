package com.boco.eoms.km.statics.mgr.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.km.statics.dao.DeptContributeStatisticDao;
import com.boco.eoms.km.statics.mgr.DeptContributeStatisticMgr;
import com.boco.eoms.km.statics.model.DeptContributeStatistic;
import com.boco.eoms.km.statics.util.StatisticMethod;

/**
 * <p>
 * Title:部门知识贡献情况
 * </p>
 * <p>
 * Description:部门知识贡献情况
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 *
 * @author ljt
 * @version 0.1
 */
public class DeptContributeStatisticMgrImpl implements DeptContributeStatisticMgr {

    //调用知识操作日统计表接口,读取所需数据
    private DeptContributeStatisticDao deptContributeStatisticDao;

    public DeptContributeStatisticDao getDeptContributeStatisticDao() {
        return deptContributeStatisticDao;
    }

    public void setDeptContributeStatisticDao(
            DeptContributeStatisticDao deptContributeStatisticDao) {
        this.deptContributeStatisticDao = deptContributeStatisticDao;
    }

    /**
     * 根据条件分页查询部门知识贡献情况
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @return 返回部门知识贡献情况的分页列表
     */
    public Map getDeptContributeStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate) {

        Map map = null;

        if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            map = this.deptContributeStatisticDao.getDeptContributeStatistics(curPage, pageSize, startDate, endDate);
        } else if ("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            map = this.deptContributeStatisticDao.getDeptContributeStatistics(curPage, pageSize, StaticMethod.date2String(startDate), StaticMethod.date2String(endDate));
        }

        //遍历取回数据集合,封装以List<PersonalUseStatistic>中
        List list = (List) map.get("result");
        List list_result = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Object[] objs = (Object[]) list.get(i);
            DeptContributeStatistic deptContributeStatistic = new DeptContributeStatistic();
            deptContributeStatistic.setDeptName(StatisticMethod.objectToString(objs[0]));
            deptContributeStatistic.setAddCount(StatisticMethod.objectToInteger(objs[1]));

            Integer auditOkCount = StatisticMethod.objectToInteger(objs[2]);
            Integer auditBackCount = StatisticMethod.objectToInteger(objs[3]);
            Integer auditAll = new Integer(auditOkCount.intValue() + auditBackCount.intValue());
            deptContributeStatistic.setAuditOkPercentage((auditAll == null || auditAll.intValue() == 0) ? "0%" : auditOkCount.intValue() / auditAll.intValue() * 100 + "%");
            deptContributeStatistic.setUseCount(StatisticMethod.objectToInteger(objs[4]));
            deptContributeStatistic.setUtilizationRate((auditOkCount == null || auditOkCount.intValue() == 0) ? "0%" : deptContributeStatistic.getUseCount().intValue() / (auditOkCount.intValue() * 100) + "%");
            deptContributeStatistic.setUpCount(StatisticMethod.objectToInteger(objs[5]));
            list_result.add(deptContributeStatistic);

        }
        map.put("result", list_result);

        //返回结果
        return map;
    }
}
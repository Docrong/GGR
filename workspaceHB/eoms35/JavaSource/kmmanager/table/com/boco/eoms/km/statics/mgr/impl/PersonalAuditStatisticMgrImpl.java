package com.boco.eoms.km.statics.mgr.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.km.statics.dao.PersonalAuditStatisticDao;
import com.boco.eoms.km.statics.mgr.PersonalAuditStatisticMgr;
import com.boco.eoms.km.statics.model.PersonalAuditStatistic;
import com.boco.eoms.km.statics.util.StatisticMethod;

/**
 * <p> Title:知识审核情况统计 </p>
 * <p> Description:知识审核情况统计 </p>
 * <p> Mon Mar 30 14:39:15 CST 2009 </p>
 *
 * @author zhangxiaobo
 * @version 0.1
 */

public class PersonalAuditStatisticMgrImpl implements PersonalAuditStatisticMgr {

    //调用知识操作日统计表接口,读取所需数据
    private PersonalAuditStatisticDao personalAuditStatisticDao;

    public PersonalAuditStatisticDao getPersonalAuditStatisticDao() {
        return personalAuditStatisticDao;
    }

    public void setPersonalAuditStatisticDao(
            PersonalAuditStatisticDao personalAuditStatisticDao) {
        this.personalAuditStatisticDao = personalAuditStatisticDao;
    }

    /**
     * 知识审核情况统计（人员）
     *
     * @param curPage   当前页
     * @param pageSize  每页显示条数
     * @param startDate 统计开始时间
     * @param endDate   统计结束时间
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getPersonalAuditStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate) {

        Map map = null;

        if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            Date newStartDate = StatisticMethod.stringToDate(startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            Date newEndDate = StatisticMethod.stringToDate(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
            map = this.personalAuditStatisticDao.getPersonalAuditStatistics(curPage, pageSize, newStartDate, newEndDate);
        } else if ("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            String newStartDate = startDate + " 00:00:00";
            String newEndDate = endDate + " 23:59:59";
            map = this.personalAuditStatisticDao.getPersonalAuditStatistics(curPage, pageSize, newStartDate, newEndDate);
        }

        //遍历取回数据集合,封装以List<PersonalAuditStatistic>中
        List list = (List) map.get("result");
        List list_result = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Object[] objs = (Object[]) list.get(i);
            PersonalAuditStatistic auditStatistic = new PersonalAuditStatistic();
            auditStatistic.setUserName(objs[0] != null ? objs[0] + "" : "");
            auditStatistic.setUserDept(objs[1] != null ? objs[1] + "" : "");
            auditStatistic.setAuditOkCount(objs[2] != null ? (Integer) objs[2] : new Integer(0));
            auditStatistic.setAuditBackCount(objs[3] != null ? (Integer) objs[3] : new Integer(0));

            list_result.add(auditStatistic);
        }
        map.put("result", list_result);

        //返回结果
        return map;
    }

    /**
     * 知识审核情况统计（部门）
     *
     * @param curPage   当前页
     * @param pageSize  每页显示条数
     * @param startDate 统计开始时间
     * @param endDate   统计结束时间
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getDeptAuditStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate) {

        Map map = null;

        if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            Date newStartDate = StatisticMethod.stringToDate(startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            Date newEndDate = StatisticMethod.stringToDate(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
            map = this.personalAuditStatisticDao.getDeptAuditStatistics(curPage, pageSize, newStartDate, newEndDate);
        } else if ("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            String newStartDate = startDate + " 00:00:00";
            String newEndDate = endDate + " 23:59:59";
            map = this.personalAuditStatisticDao.getDeptAuditStatistics(curPage, pageSize, newStartDate, newEndDate);
        }

        //遍历取回数据集合,封装以List<PersonalAuditStatistic>中
        List list = (List) map.get("result");
        List list_result = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Object[] objs = (Object[]) list.get(i);
            PersonalAuditStatistic auditStatistic = new PersonalAuditStatistic();
            auditStatistic.setUserDept(objs[0] != null ? objs[0] + "" : "");
            auditStatistic.setAuditOkCount(objs[1] != null ? (Integer) objs[1] : new Integer(0));
            auditStatistic.setAuditBackCount(objs[2] != null ? (Integer) objs[2] : new Integer(0));

            list_result.add(auditStatistic);
        }
        map.put("result", list_result);

        //返回结果
        return map;
    }
}
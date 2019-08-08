package com.boco.eoms.km.statics.mgr.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.km.statics.dao.PersonalStatisticDao;
import com.boco.eoms.km.statics.mgr.PersonalStatisticMgr;
import com.boco.eoms.km.statics.model.PersonalStatistic;
import com.boco.eoms.km.statics.util.StatisticMethod;

/**
 * <p>
 * Title:人员知识贡献统计
 * </p>
 * <p>
 * Description:人员知识贡献统计
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 *
 * @author ljt
 * @version 0.1
 */
public class PersonalStatisticMgrImpl implements PersonalStatisticMgr {

    private PersonalStatisticDao personalStatisticDao;

    public PersonalStatisticDao getPersonalStatisticDao() {
        return personalStatisticDao;
    }

    public void setPersonalStatisticDao(PersonalStatisticDao personalStatisticDao) {
        this.personalStatisticDao = personalStatisticDao;
    }

    /**
     * 根据条件分页查询人员知识贡献统计
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @return 返回人员知识贡献统计
     */
    public Map getPersonalStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate) {

        Map map = null;

        if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            Date newStartDate = StatisticMethod.stringToDate(startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            Date newEndDate = StatisticMethod.stringToDate(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
            map = this.personalStatisticDao.getPersonalStatistics(curPage, pageSize, newStartDate, newEndDate);
        } else if ("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            String newStartDate = startDate + " 00:00:00";
            String newEndDate = endDate + " 23:59:59";
            map = this.personalStatisticDao.getPersonalStatistics(curPage, pageSize, newStartDate, newEndDate);
        }

        List list = (List) map.get("result");
        List list_result = new ArrayList();
        int length = list.size();
        for (int i = 0; i < length; i++) {
            Object[] objs = (Object[]) list.get(i);
            PersonalStatistic personalStatistic = new PersonalStatistic();
            personalStatistic.setUserName(StatisticMethod.objectToString(objs[0])); //用户姓名
            personalStatistic.setUserDept(StatisticMethod.objectToString(objs[1])); //用户部门
            personalStatistic.setAddCount(StatisticMethod.objectToInteger(objs[2])); //编写知识数
            personalStatistic.setModifyCount(StatisticMethod.objectToInteger(objs[3])); //修改知识数
            personalStatistic.setOverCount(StatisticMethod.objectToInteger(objs[4])); //失效知识数
            personalStatistic.setDeleteCount(StatisticMethod.objectToInteger(objs[5])); //删除知识数
            personalStatistic.setUsedCount(StatisticMethod.objectToInteger(objs[6])); //使用知识数
            personalStatistic.setUpCount(StatisticMethod.objectToInteger(objs[7])); //上传文件数
            //personalStatistic.setDownCount(StatisticMethod.objectToInteger(objs[8])); //下载文件数
            list_result.add(personalStatistic);
        }
        map.put("result", list_result);
        return map;
    }

    /**
     * 根据条件分页查询部门知识贡献统计
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @return 返回人员知识贡献统计
     */
    public Map getDeptStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate) {

        Map map = null;
        if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            Date newStartDate = StatisticMethod.stringToDate(startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            Date newEndDate = StatisticMethod.stringToDate(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
            map = this.personalStatisticDao.getDeptStatistics(curPage, pageSize, newStartDate, newEndDate);
        } else if ("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            String newStartDate = startDate + " 00:00:00";
            String newEndDate = endDate + " 23:59:59";
            map = this.personalStatisticDao.getDeptStatistics(curPage, pageSize, newStartDate, newEndDate);
        }

        List list = (List) map.get("result");
        List list_result = new ArrayList();
        int length = list.size();
        for (int i = 0; i < length; i++) {
            Object[] objs = (Object[]) list.get(i);
            PersonalStatistic personalStatistic = new PersonalStatistic();
            personalStatistic.setUserDept(StatisticMethod.objectToString(objs[0])); //用户部门
            personalStatistic.setAddCount(StatisticMethod.objectToInteger(objs[1])); //编写知识数
            personalStatistic.setModifyCount(StatisticMethod.objectToInteger(objs[2])); //修改知识数
            personalStatistic.setOverCount(StatisticMethod.objectToInteger(objs[3])); //失效知识数
            personalStatistic.setDeleteCount(StatisticMethod.objectToInteger(objs[4])); //删除知识数
            personalStatistic.setUsedCount(StatisticMethod.objectToInteger(objs[5])); //使用知识数
            personalStatistic.setUpCount(StatisticMethod.objectToInteger(objs[6])); //上传文件数
            list_result.add(personalStatistic);
        }
        map.put("result", list_result);
        return map;
    }
}
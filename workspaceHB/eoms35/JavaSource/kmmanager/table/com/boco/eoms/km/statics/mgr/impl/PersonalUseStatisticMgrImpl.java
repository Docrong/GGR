package com.boco.eoms.km.statics.mgr.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.km.statics.dao.PersonalUseStatisticDao;
import com.boco.eoms.km.statics.mgr.PersonalUseStatisticMgr;
import com.boco.eoms.km.statics.model.PersonalUseStatistic;
import com.boco.eoms.km.statics.util.StatisticMethod;

/**
 * <p>
 * Title:知识使用人本期（周、月、季、年）使用知识情况统计表
 * </p>
 * <p>
 * Description:知识使用人本期（周、月、季、年）使用知识情况统计表
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 *
 * @author ljt
 * @version 0.1
 */
public class PersonalUseStatisticMgrImpl implements PersonalUseStatisticMgr {

    //调用知识操作日统计表接口,读取所需数据
    private PersonalUseStatisticDao personalUseStatisticDao;

    public PersonalUseStatisticDao getPersonalUseStatisticDao() {
        return personalUseStatisticDao;
    }

    public void setPersonalUseStatisticDao(
            PersonalUseStatisticDao personalUseStatisticDao) {
        this.personalUseStatisticDao = personalUseStatisticDao;
    }


    /**
     * 根据条件分页查询知识使用人本期（周、月、季、年）使用知识情况统计表
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param period   查询范围(year,month,season,week)
     * @return 返回知识使用人本期（周、月、季、年）使用知识情况统计表的分页列表
     */
    public Map getPersonalUseStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate) {

        Map map = null;

        if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            Date newStartDate = StatisticMethod.stringToDate(startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            Date newEndDate = StatisticMethod.stringToDate(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
            map = this.personalUseStatisticDao.getPersonalUseStatistics(curPage, pageSize, newStartDate, newEndDate);
        } else if ("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            String newStartDate = startDate + " 00:00:00";
            String newEndDate = endDate + " 23:59:59";
            map = this.personalUseStatisticDao.getPersonalUseStatistics(curPage, pageSize, newStartDate, newEndDate);
        }

        //遍历取回数据集合,封装以List<PersonalUseStatistic>中
        List list = (List) map.get("result");
        List list_result = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Object[] objs = (Object[]) list.get(i);
            PersonalUseStatistic useStatistic = new PersonalUseStatistic();
            useStatistic.setUserName(StatisticMethod.objectToString(objs[0]));
            useStatistic.setUserDept(StatisticMethod.objectToString(objs[1]));
            useStatistic.setReadCount(StatisticMethod.objectToInteger(objs[2])); //阅读次数
            useStatistic.setOpinionCount(StatisticMethod.objectToInteger(objs[3])); //评论次数
            useStatistic.setAdviceCount(StatisticMethod.objectToInteger(objs[4])); //建议次数
            useStatistic.setUseCount(StatisticMethod.objectToInteger(objs[5])); //使用知识数
            useStatistic.setDownCount(StatisticMethod.objectToInteger(objs[6]));  //下载文件数
            list_result.add(useStatistic);

        }
        map.put("result", list_result);

        //返回结果
        return map;
    }

    /**
     * 根据条件分页查询部门知识贡献统计
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @return 返回人员知识贡献统计
     */
    public Map getDeptUseStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate) {

        Map map = null;
        if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            Date newStartDate = StatisticMethod.stringToDate(startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            Date newEndDate = StatisticMethod.stringToDate(endDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
            map = this.personalUseStatisticDao.getDeptUseStatistics(curPage, pageSize, newStartDate, newEndDate);
        } else if ("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            String newStartDate = startDate + " 00:00:00";
            String newEndDate = endDate + " 23:59:59";
            map = this.personalUseStatisticDao.getDeptUseStatistics(curPage, pageSize, newStartDate, newEndDate);
        }

        //遍历取回数据集合,封装以List<PersonalUseStatistic>中
        List list = (List) map.get("result");
        List list_result = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Object[] objs = (Object[]) list.get(i);
            PersonalUseStatistic useStatistic = new PersonalUseStatistic();
            useStatistic.setUserDept(StatisticMethod.objectToString(objs[0])); //用户部门
            useStatistic.setReadCount(StatisticMethod.objectToInteger(objs[1])); //阅读次数
            useStatistic.setOpinionCount(StatisticMethod.objectToInteger(objs[2])); //评论次数
            useStatistic.setAdviceCount(StatisticMethod.objectToInteger(objs[3])); //建议次数
            useStatistic.setUseCount(StatisticMethod.objectToInteger(objs[4])); //使用知识数
            useStatistic.setDownCount(StatisticMethod.objectToInteger(objs[5]));  //下载文件数
            list_result.add(useStatistic);

        }
        map.put("result", list_result);

        //返回结果
        return map;
    }
}
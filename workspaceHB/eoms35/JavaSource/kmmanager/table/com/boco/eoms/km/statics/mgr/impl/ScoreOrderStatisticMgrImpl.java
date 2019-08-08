package com.boco.eoms.km.statics.mgr.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.km.statics.dao.ScoreOrderStatisticDao;
import com.boco.eoms.km.statics.mgr.ScoreOrderStatisticMgr;
import com.boco.eoms.km.statics.model.PersonalStatistic;
import com.boco.eoms.km.statics.util.StatisticMethod;


/**
 * <p>
 * Title:知识积分排行
 * </p>
 * <p>
 * Description:知识积分排行
 * </p>
 *
 * @author daizhigang
 * @version 0.1
 */
public class ScoreOrderStatisticMgrImpl implements ScoreOrderStatisticMgr {

    private ScoreOrderStatisticDao scoreOrderStatisticDao;

    public ScoreOrderStatisticDao getScoreOrderStatisticDao() {
        return scoreOrderStatisticDao;
    }

    public void setScoreOrderStatisticDao(ScoreOrderStatisticDao scoreOrderStatisticDao) {
        this.scoreOrderStatisticDao = scoreOrderStatisticDao;
    }

    /**
     * 知识排名（使用数排行）
     *
     * @param num 排名显示数量
     * @return 知识排名（使用数排行
     */
    public List getKnowledgeUsedOrder(final int number) {

        List list_result = new ArrayList();
        List list = new ArrayList();
        list = this.scoreOrderStatisticDao.getKnowledgeUsedOrder(number);
        int length = list.size();
        for (int i = 0; i < length & i < number; i++) {
            Object[] objs = (Object[]) list.get(i);
            String[] orderStr = new String[2];
            orderStr[0] = StatisticMethod.objectToString(objs[0]);
            orderStr[1] = StatisticMethod.objectToString(objs[1]);
            list_result.add(orderStr);
        }
        return list_result;
    }

    /**
     * 知识排名（使用数排行）
     *
     * @param num       排名显示数量
     * @param startDate 统计开始时间
     * @param endDate   统计结束时间
     * @return 知识排名（使用数排行
     */
    public List getKnowledgeUsedOrder(final int number, String startDate, String endDate) {

        List list_result = new ArrayList();
        List list = new ArrayList();
//		list = this.scoreOrderStatisticDao.getKnowledgeUsedOrder(number);
        if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            Date newStartDate = StatisticMethod.stringToDate(startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            Date newEndDate = StatisticMethod.stringToDate(endDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            list = this.scoreOrderStatisticDao.getKnowledgeUsedOrder(number, newStartDate, newEndDate);
        } else if ("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            String newStartDate = startDate + " 00:00:00";
            String newEndDate = endDate + " 00:00:00";
            list = this.scoreOrderStatisticDao.getKnowledgeUsedOrder(number, newStartDate, newEndDate);
        }
        int length = list.size();
        for (int i = 0; i < length & i < number; i++) {
            Object[] objs = (Object[]) list.get(i);
            String[] orderStr = new String[2];
            orderStr[0] = StatisticMethod.objectToString(objs[0]);
            orderStr[1] = StatisticMethod.objectToString(objs[1]);
            list_result.add(orderStr);
        }
        return list_result;
    }

    /**
     * 本月员工贡献排名（ok）（新建数排行）
     *
     * @param num       排名显示数量
     * @param startDate 统计开始时间
     * @param endDate   统计结束时间
     * @return 知识排名（使用数排行
     */
    public List getMonthUserScoreOrder(final int number, String startDate, String endDate) {

        List list_result = new ArrayList();
        List list = new ArrayList();
//		list = this.scoreOrderStatisticDao.getKnowledgeUsedOrder(number);
        if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            Date newStartDate = StatisticMethod.stringToDate(startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            Date newEndDate = StatisticMethod.stringToDate(endDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            list = this.scoreOrderStatisticDao.getMonthUserScoreOrder(number, newStartDate, newEndDate);
        } else if ("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            String newStartDate = startDate + " 00:00:00";
            String newEndDate = endDate + " 00:00:00";
            list = this.scoreOrderStatisticDao.getMonthUserScoreOrder(number, newStartDate, newEndDate);
        }
        int length = list.size();
        for (int i = 0; i < length & i < number; i++) {
            Object[] objs = (Object[]) list.get(i);
            String[] orderStr = new String[2];
            orderStr[0] = StatisticMethod.objectToString(objs[0]);
            orderStr[1] = StatisticMethod.objectToString(objs[1]);
            list_result.add(orderStr);
        }
        return list_result;
    }

    public List getUserScoreOrder(final int number) {
        List list_result = new ArrayList();
        List list = this.scoreOrderStatisticDao.getUserScoreOrder(number);
        int length = list.size();
        for (int i = 0; i < length & i < number; i++) {
            Object[] objs = (Object[]) list.get(i);
            String[] orderStr = new String[2];
            orderStr[0] = StatisticMethod.objectToString(objs[0]);
            orderStr[1] = StatisticMethod.objectToString(objs[1]);
            list_result.add(orderStr);
        }
        return list_result;
    }

    /**
     * 本月知识增长排名（ok）
     *
     * @param num       排名显示数量
     * @param startDate 统计开始时间
     * @param endDate   统计结束时间
     * @return 当月知识库知识总量排名
     */
    public List getMonthKnowledgeCountOrder(final int number, String startDate, String endDate) {

        List list_result = new ArrayList();
        List list = new ArrayList();
//		list = this.scoreOrderStatisticDao.getKnowledgeUsedOrder(number);
        if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            Date newStartDate = StatisticMethod.stringToDate(startDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            Date newEndDate = StatisticMethod.stringToDate(endDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
            list = this.scoreOrderStatisticDao.getMonthKnowledgeCountOrder(number, newStartDate, newEndDate);
        } else if ("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            String newStartDate = startDate + " 00:00:00";
            String newEndDate = endDate + " 00:00:00";
            list = this.scoreOrderStatisticDao.getMonthKnowledgeCountOrder(number, newStartDate, newEndDate);
        }
        int length = list.size();
        for (int i = 0; i < length & i < number; i++) {
            Object[] objs = (Object[]) list.get(i);
            String[] orderStr = new String[2];
            orderStr[0] = StatisticMethod.objectToString(objs[0]);
            orderStr[1] = StatisticMethod.objectToString(objs[1]);
            list_result.add(orderStr);
        }
        return list_result;
    }


    public List getKnowledgeCountOrder(final int number) {
        List list_result = new ArrayList();
        List list = this.scoreOrderStatisticDao.getKnowledgeCountOrder(number);
        int length = list.size();
        for (int i = 0; i < length & i < number; i++) {
            Object[] objs = (Object[]) list.get(i);
            String[] orderStr = new String[2];
            orderStr[0] = StatisticMethod.objectToString(objs[0]);
            orderStr[1] = StatisticMethod.objectToString(objs[1]);
            list_result.add(orderStr);
        }
        return list_result;
    }

    public List getKnowledgeReadOrder(final int number) {
        List list_result = new ArrayList();
        List list = this.scoreOrderStatisticDao.getKnowledgeReadOrder(number);
        int length = list.size();
        for (int i = 0; i < length & i < number; i++) {
            Object[] objs = (Object[]) list.get(i);
            String[] orderStr = new String[2];
            orderStr[0] = StatisticMethod.objectToString(objs[0]);
            orderStr[1] = StatisticMethod.objectToString(objs[1]);
            list_result.add(orderStr);
        }
        return list_result;
    }

    /**
     * 积分排名（新建数排行）
     *
     * @param num       排名显示数量
     * @param startDate 统计开始时间
     * @param endDate   统计结束时间
     * @return 知识排名（使用数排行
     */
    public List getUserKnowledScoreOrder(final int number) {
        List list_result = new ArrayList();
        List list = this.scoreOrderStatisticDao.getUserKnowledScoreOrder(number);
        int length = list.size();
        for (int i = 0; i < length && i < number; i++) {
            Object[] objs = (Object[]) list.get(i);
            String[] orderStr = new String[2];
            orderStr[0] = StatisticMethod.objectToString(objs[0]);
            orderStr[1] = StatisticMethod.objectToString(objs[1]);
            list_result.add(orderStr);
        }
        return list_result;
    }

    public List getUserKnowledScoreOByUserId(String userId) {
        List list_result = new ArrayList();
        Integer sumUser = new Integer(0);
        List list = this.scoreOrderStatisticDao.getUserKnowledScoreOByUserId(userId);
        if (list != null && list.size() > 0) {
            Object[] objects = (Object[]) list.get(0);
            sumUser = StatisticMethod.objectToInteger(objects[1]);
        }
        list_result.add(sumUser);
        return list_result;
    }
}

package com.boco.eoms.km.statics.mgr.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.km.log.mgr.KmOperateDateLogMgr;
import com.boco.eoms.km.statics.dao.PersonalContributeStatisticDao;
import com.boco.eoms.km.statics.mgr.PersonalContributeStatisticMgr;
import com.boco.eoms.km.statics.model.PersonalAuditStatistic;
import com.boco.eoms.km.statics.model.PersonalContributeStatistic;
import com.boco.eoms.km.statics.util.KmOperateScoreConstants;
import com.boco.eoms.km.statics.util.StatisticMethod;

/**
 * <p>
 * Title:知识编写人本期（周、月、季、年）知识贡献情况统计表
 * </p>
 * <p>
 * Description:知识编写人本期（周、月、季、年）知识贡献情况统计表
 * </p>
 * <p>
 * Mon Mar 30 14:39:14 CST 2009
 * </p>
 *
 * @author ljt
 * @version 0.1
 */
public class PersonalContributeStatisticMgrImpl implements PersonalContributeStatisticMgr {

    //调用知识操作日统计表接口,读取所需数据
    private PersonalContributeStatisticDao personalContributeStatisticDao;

    public PersonalContributeStatisticDao getPersonalContributeStatisticDao() {
        return personalContributeStatisticDao;
    }

    public void setPersonalContributeStatisticDao(
            PersonalContributeStatisticDao personalContributeStatisticDao) {
        this.personalContributeStatisticDao = personalContributeStatisticDao;
    }

    /**
     * 根据条件分页查询知识编写人本期（周、月、季、年）知识贡献情况统计表
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param period   查询范围(year,month,season,week)
     * @return 返回知识编写人本期（周、月、季、年）知识贡献情况统计表
     */
    public Map getPersonalContributeStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate) {

        Map map = null;

        if ("org.hibernate.dialect.InformixDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            map = this.personalContributeStatisticDao.getPersonalContributeStatistics(curPage, pageSize, startDate, endDate);
        } else if ("org.hibernate.dialect.OracleDialect".equals(ApplicationContextHolder.getInstance().getHQLDialect())) {
            map = this.personalContributeStatisticDao.getPersonalContributeStatistics(curPage, pageSize, StaticMethod.date2String(startDate), StaticMethod.date2String(endDate));
        }

        List list = (List) map.get("result");
        List list_result = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Object[] objs = (Object[]) list.get(i);
            PersonalContributeStatistic personalAuditStatistic = new PersonalContributeStatistic();
            personalAuditStatistic.setUserName(StatisticMethod.objectToString(objs[0])); //用户姓名
            personalAuditStatistic.setUserDept(StatisticMethod.objectToString(objs[1])); //用户部门
            personalAuditStatistic.setAddCount(StatisticMethod.objectToInteger(objs[2])); //编写知识数
            personalAuditStatistic.setModifyCount(StatisticMethod.objectToInteger(objs[3])); //修改知识数
            personalAuditStatistic.setOverCount(StatisticMethod.objectToInteger(objs[4])); //失效知识数
            personalAuditStatistic.setDeleteCount(StatisticMethod.objectToInteger(objs[5])); //删除知识数
            personalAuditStatistic.setUpCount(StatisticMethod.objectToInteger(objs[6])); //上传文件数

            list_result.add(personalAuditStatistic);

        }

        map.put("result", list_result);
        return map;
    }

}
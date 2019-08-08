package com.boco.eoms.km.statics.dao;

import java.util.Date;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;

/**
 * <p> Title:知识申请情况统计 </p>
 * <p> Description:知识申请情况统计 </p>
 * <p> Mon Mar 30 14:39:15 CST 2009 </p>
 *
 * @author zhangxiaobo
 * @version 0.1
 */

public interface PersonalApplyStatisticDao extends Dao {

    /**
     * 知识申请统计（人员）- Informix 数据库
     *
     * @param curPage   当前页
     * @param pageSize  每页显示条数
     * @param startDate 统计开始时间
     * @param endDate   统计结束时间
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getPersonalApplyStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate);

    /**
     * 知识申请统计（人员）- Oracle 数据库
     *
     * @param curPage   当前页
     * @param pageSize  每页显示条数
     * @param startDate 统计开始时间
     * @param endDate   统计结束时间
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getPersonalApplyStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate);

    /**
     * 知识申请统计（部门）- Informix 数据库
     *
     * @param curPage   当前页
     * @param pageSize  每页显示条数
     * @param startDate 统计开始时间
     * @param endDate   统计结束时间
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getDeptApplyStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate);

    /**
     * 知识申请统计（部门）- Oracle 数据库
     *
     * @param curPage   当前页
     * @param pageSize  每页显示条数
     * @param startDate 统计开始时间
     * @param endDate   统计结束时间
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getDeptApplyStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate);
}
package com.boco.eoms.km.statics.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.dao.Dao;
import com.boco.eoms.km.statics.model.PersonalContributeStatistic;

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
public interface PersonalContributeStatisticDao extends Dao {

    /**
     * 取知识编写人本期（周、月、季、年）知识贡献情况统计表列表
     *
     * @return 返回知识编写人本期（周、月、季、年）知识贡献情况统计表列表
     */
    public List getPersonalContributeStatistics();

    /**
     * 根据主键查询知识编写人本期（周、月、季、年）知识贡献情况统计表
     *
     * @param id 主键
     * @return 返回某id的知识编写人本期（周、月、季、年）知识贡献情况统计表
     */
    public PersonalContributeStatistic getPersonalContributeStatistic(final String id);

    /**
     * 保存知识编写人本期（周、月、季、年）知识贡献情况统计表
     *
     * @param personalContributeStatistic 知识编写人本期（周、月、季、年）知识贡献情况统计表
     */
    public void savePersonalContributeStatistic(PersonalContributeStatistic personalContributeStatistic);

    /**
     * 根据id删除知识编写人本期（周、月、季、年）知识贡献情况统计表
     *
     * @param id 主键
     */
    public void removePersonalContributeStatistic(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getPersonalContributeStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate);

    public Map getPersonalContributeStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate);

}
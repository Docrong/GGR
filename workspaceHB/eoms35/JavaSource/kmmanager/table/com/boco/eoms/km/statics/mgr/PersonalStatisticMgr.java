package com.boco.eoms.km.statics.mgr;

import java.util.Map;

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
public interface PersonalStatisticMgr {

    /**
     * 根据条件分页查询人员知识贡献统计
     *
     * @param curPage   当前页
     * @param pageSize  每页包含记录条数
     * @param startDate 统计开始时间
     * @param endDate   统计结束时间
     * @return 返回人员知识贡献统计的分页列表
     */
    public Map getPersonalStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate);

    public Map getDeptStatistics(final Integer curPage, final Integer pageSize, final String startDate, final String endDate);

}
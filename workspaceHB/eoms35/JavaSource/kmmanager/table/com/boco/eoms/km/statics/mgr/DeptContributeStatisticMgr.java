package com.boco.eoms.km.statics.mgr;

import java.util.Date;
import java.util.Map;

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
public interface DeptContributeStatisticMgr {

    /**
     * 根据条件分页查询部门知识贡献情况
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @return 返回部门知识贡献情况的分页列表
     */
    public Map getDeptContributeStatistics(final Integer curPage, final Integer pageSize, final Date startDate, final Date endDate);

}
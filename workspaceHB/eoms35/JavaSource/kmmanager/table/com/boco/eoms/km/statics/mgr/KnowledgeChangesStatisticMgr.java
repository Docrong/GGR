package com.boco.eoms.km.statics.mgr;

import java.util.Map;

/**
 * <p>
 * Title:知识库知识变更情况统计表
 * </p>
 * <p>
 * Description:知识库知识变更情况统计表
 * </p>
 * <p>
 * Mon Mar 30 14:39:15 CST 2009
 * </p>
 *
 * @author ljt
 * @version 0.1
 */
public interface KnowledgeChangesStatisticMgr {

    /**
     * 根据条件分页查询知识库知识变更情况统计表
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @return 返回知识库知识变更情况统计表的分页列表
     */
    public Map getKnowledgeChangesStatistics(final Integer curPage, final Integer pageSize);

}
package com.boco.eoms.km.knowledge.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.knowledge.model.KmContentsHistory;

/**
 * <p>
 * Title:知识管理
 * </p>
 * <p>
 * Description:知识管理
 * </p>
 * <p>
 * Tue Mar 24 10:32:33 CST 2009
 * </p>
 *
 * @author eoms
 * @version 1.0
 */
public interface KmContentsHistoryMgr {

    /**
     * 取知识管理 列表
     *
     * @return 返回知识管理列表
     */
    public List getKmContentsHistorys();

    /**
     * 根据主键查询知识管理
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public KmContentsHistory getKmContentsHistory(final String id);

    /**
     * 保存知识管理
     *
     * @param kmContentsHistory 知识管理
     */
    public void saveKmContentsHistory(KmContentsHistory kmContentsHistory);

    /**
     * 根据主键删除知识管理
     *
     * @param id 主键
     */
    public void removeKmContentsHistory(final String id);

    /**
     * 根据条件分页查询知识管理
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回知识管理的分页列表
     */
    public Map getKmContentsHistorys(final Integer curPage, final Integer pageSize,
                                     final String whereStr);

}
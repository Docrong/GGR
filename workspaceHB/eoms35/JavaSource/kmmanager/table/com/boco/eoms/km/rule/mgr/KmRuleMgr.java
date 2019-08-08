package com.boco.eoms.km.rule.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.knowledge.model.KmContents;
import com.boco.eoms.km.rule.model.KmRule;

/**
 * <p>
 * Title:规则库
 * </p>
 * <p>
 * Description:规则库
 * </p>
 * <p>
 * Fri Apr 17 16:06:45 CST 2009
 * </p>
 *
 * @author hsl
 * @version 1.0
 */
public interface KmRuleMgr {

    /**
     * 取规则库 列表
     *
     * @return 返回规则库列表
     */
    public List getKmRules();

    /**
     * 根据主键查询规则库
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public KmRule getKmRule(final String id);

    /**
     * 保存规则库
     *
     * @param kmRule 规则库
     */
    public void saveKmRule(KmRule kmRule);

    /**
     * 根据主键删除规则库
     *
     * @param id 主键
     */
    public void removeKmRule(final String id);

    /**
     * 根据条件分页查询规则库
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回规则库的分页列表
     */
    public Map getKmRules(final Integer curPage, final Integer pageSize,
                          final String whereStr);

    /**
     * 查询下一级节点
     *
     * @param parentNodeId 父节点id
     * @return 下级节点列表
     */
    public List getNextLevelKmRules(String parentNodeId);

    public String checkSql(String sql);

    /**
     * 自动推送接口(根据规则及输入条件自动查询)
     *
     * @param sql
     * @param valueList
     * @param typeList
     * @param curPage
     * @param pageSize
     * @return
     */
    public List getQueryList(String sql, List valueList, List typeList, final Integer curPage, final Integer pageSize);
}
package com.boco.eoms.sheet.acceptsheetrule.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.sheet.acceptsheetrule.model.AcceptSheetRule;

/**
 * <p>
 * Title:自动接单规则配置
 * </p>
 * <p>
 * Description:自动接单规则配置
 * </p>
 * <p>
 * Wed Apr 22 09:19:35 CST 2009
 * </p>
 *
 * @author 史闯科
 * @version 3.5
 */
public interface AcceptSheetRuleDao extends Dao {

    /**
     * 取自动接单规则配置列表
     *
     * @return 返回自动接单规则配置列表
     */
    public List getAcceptSheetRules();

    /**
     * 根据主键查询自动接单规则配置
     *
     * @param id 主键
     * @return 返回某id的自动接单规则配置
     */
    public AcceptSheetRule getAcceptSheetRule(final String id);

    /**
     * 保存自动接单规则配置
     *
     * @param acceptSheetRule 自动接单规则配置
     */
    public void saveAcceptSheetRule(AcceptSheetRule acceptSheetRule);

    /**
     * 根据id删除自动接单规则配置
     *
     * @param id 主键
     */
    public void removeAcceptSheetRule(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getAcceptSheetRules(final Integer curPage, final Integer pageSize,
                                   final String whereStr);

}
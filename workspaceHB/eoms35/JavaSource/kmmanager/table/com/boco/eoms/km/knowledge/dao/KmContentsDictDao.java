package com.boco.eoms.km.knowledge.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.knowledge.model.KmContentsDict;

/**
 * <p>
 * Title:知识管理
 * </p>
 * <p>
 * Description:知识管理
 * </p>
 * <p>
 * Tue Mar 24 10:33:19 CST 2009
 * </p>
 *
 * @author eoms
 * @version 1.0
 */
public interface KmContentsDictDao extends Dao {

    /**
     * 取知识管理列表
     *
     * @return 返回知识管理列表
     */
    public List getKmContentsDicts();

    /**
     * 根据主键查询知识管理
     *
     * @param id 主键
     * @return 返回某id的知识管理
     */
    public KmContentsDict getKmContentsDict(final String id);

    /**
     * 保存知识管理
     *
     * @param kmContentsDict 知识管理
     */
    public void saveKmContentsDict(KmContentsDict kmContentsDict);

    /**
     * 根据id删除知识管理
     *
     * @param id 主键
     */
    public void removeKmContentsDict(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getKmContentsDicts(final Integer curPage, final Integer pageSize,
                                  final String whereStr);

}
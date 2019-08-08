package com.boco.eoms.km.ask.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.ask.model.KmAskCountScore;

/**
 * <p>
 * Title:问答总积分
 * </p>
 * <p>
 * Description:问答总积分
 * </p>
 * <p>
 * Wed Aug 19 10:09:58 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public interface KmAskCountScoreDao extends Dao {

    /**
     * 取问答总积分列表
     *
     * @return 返回问答总积分列表
     */
    public List getKmAskCountScores();

    /**
     * 查询某人的积分情况
     *
     * @param id
     * @return
     */
    public KmAskCountScore getKmAskCountScoreByUser(final String userName);

    /**
     * 根据主键查询问答总积分
     *
     * @param id 主键
     * @return 返回某id的问答总积分
     */
    public KmAskCountScore getKmAskCountScore(final String id);

    /**
     * 保存问答总积分
     *
     * @param kmAskCountScore 问答总积分
     */
    public void saveKmAskCountScore(KmAskCountScore kmAskCountScore);

    /**
     * 根据id删除问答总积分
     *
     * @param id 主键
     */
    public void removeKmAskCountScore(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getKmAskCountScores(final Integer curPage, final Integer pageSize,
                                   final String whereStr);

}
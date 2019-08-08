package com.boco.eoms.km.ask.mgr;

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
public interface KmAskCountScoreMgr {

    /**
     * 取问答总积分 列表
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
     * @return 返回某id的对象
     */
    public KmAskCountScore getKmAskCountScore(final String id);

    /**
     * 根据用户的操作更新总积分
     *
     * @param user
     * @param userDept
     * @param operateId
     */
    public void saveKmAskCountScore(final String user, final String userDept, final Integer operateId);

    /**
     * 保存问答总积分
     *
     * @param kmAskCountScore 问答总积分
     */
    public void saveKmAskCountScore(KmAskCountScore kmAskCountScore);

    /**
     * 根据主键删除问答总积分
     *
     * @param id 主键
     */
    public void removeKmAskCountScore(final String id);

    /**
     * 根据条件分页查询问答总积分
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回问答总积分的分页列表
     */
    public Map getKmAskCountScores(final Integer curPage, final Integer pageSize,
                                   final String whereStr);

}
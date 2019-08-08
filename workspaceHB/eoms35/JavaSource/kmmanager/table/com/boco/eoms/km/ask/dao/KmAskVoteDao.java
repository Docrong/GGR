package com.boco.eoms.km.ask.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.ask.model.KmAskVote;

/**
 * <p>
 * Title:投票
 * </p>
 * <p>
 * Description:投票
 * </p>
 * <p>
 * Fri Aug 14 15:39:20 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public interface KmAskVoteDao extends Dao {

    /**
     * 取投票列表
     *
     * @return 返回投票列表
     */
    public List getKmAskVotes();

    /**
     * 根据questionId查询所有投票信息
     *
     * @param questionId
     * @return
     */
    public List getKmAskVoteByQuestionId(final String questionId);

    /**
     * 根据问题id和投票人id查询投票信息
     *
     * @param questionId
     * @param userId
     * @return
     */
    public KmAskVote getKmAskVote(final String questionId, final String userId);

    /**
     * 根据主键查询投票
     *
     * @param id 主键
     * @return 返回某id的投票
     */
    public KmAskVote getKmAskVote(final String id);

    /**
     * 保存投票
     *
     * @param kmAskVote 投票
     */
    public void saveKmAskVote(KmAskVote kmAskVote);

    /**
     * 根据id删除投票
     *
     * @param id 主键
     */
    public void removeKmAskVote(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getKmAskVotes(final Integer curPage, final Integer pageSize,
                             final String whereStr);

}
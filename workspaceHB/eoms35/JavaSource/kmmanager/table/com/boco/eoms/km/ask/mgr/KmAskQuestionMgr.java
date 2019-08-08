package com.boco.eoms.km.ask.mgr;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.ask.model.KmAskQuestion;

/**
 * <p>
 * Title:问题
 * </p>
 * <p>
 * Description:问题
 * </p>
 * <p>
 * Tue Aug 04 15:17:03 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public interface KmAskQuestionMgr {

    /**
     * 取问题列表
     *
     * @return 返回问题列表
     */
    public List getKmAskQuestions(final String nodeId, final String questionStatus);

    /**
     * 查询待解决的所用问题
     *
     * @return
     */
    public List getKmAskQuestions0();

    /**
     * 查询已解决问题
     *
     * @return
     */
    public List getKmAskQuestions1();

    /**
     * 查询投票中的问题
     *
     * @return
     */
    public List getKmAskQuestions2();

    /**
     * 根据主键查询问题
     *
     * @param id 主键
     * @return 返回某id的对象
     */
    public KmAskQuestion getKmAskQuestion(final String id);

    /**
     * 保存问题
     *
     * @param kmAskQuestion 问题
     */
    public void saveKmAskQuestion(KmAskQuestion kmAskQuestion);

    /**
     * 根据主键删除问题
     *
     * @param id 主键
     */
    public void removeKmAskQuestion(final String id);

    /**
     * 查询默认回答的问题
     *
     * @param userId 回答问题的人
     * @return
     */
    public List getKmAskQuestionByMyReply(final String userId);

    /**
     * 根据条件分页查询问题
     *
     * @param curPage  当前页
     * @param pageSize 每页包含记录条数
     * @param whereStr 查询条件
     * @return 返回问题的分页列表
     */
    public Map getKmAskQuestions(final Integer curPage, final Integer pageSize,
                                 final String whereStr);

}
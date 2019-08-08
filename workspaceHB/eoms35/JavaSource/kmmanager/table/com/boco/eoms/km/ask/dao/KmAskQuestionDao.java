package com.boco.eoms.km.ask.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

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
public interface KmAskQuestionDao extends Dao {

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
     * @return 返回某id的问题
     */
    public KmAskQuestion getKmAskQuestion(final String id);

    /**
     * 保存问题
     *
     * @param kmAskQuestion 问题
     */
    public void saveKmAskQuestion(KmAskQuestion kmAskQuestion);

    /**
     * 根据id删除问题
     *
     * @param id 主键
     */
    public void removeKmAskQuestion(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getKmAskQuestions(final Integer curPage, final Integer pageSize,
                                 final String whereStr);

}
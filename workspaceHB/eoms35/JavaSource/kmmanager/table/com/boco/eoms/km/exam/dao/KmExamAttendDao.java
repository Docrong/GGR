package com.boco.eoms.km.exam.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.exam.model.KmExamAttend;

/**
 * <p>
 * Title:考试信息
 * </p>
 * <p>
 * Description:考试信息
 * </p>
 * <p>
 * Mon May 11 10:55:38 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public interface KmExamAttendDao extends Dao {

    /**
     * 根据试卷id和参加考试人信息得到唯一考试信息
     *
     * @param testID
     * @param attendUser
     * @return
     */
    public KmExamAttend getKmExamAttend(final String testID, final String attendUser);

    /**
     * 查询当前人参加考试的发布结果信息
     *
     * @param attendUser
     * @return
     */
    public List getKmExamAttends(final String attendUser, final String isPublic);

    /**
     * 取考试信息列表
     *
     * @return 返回考试信息列表
     */
    public List getKmExamAttends();

    /**
     * 根据主键查询考试信息
     *
     * @param id 主键
     * @return 返回某id的考试信息
     */
    public KmExamAttend getKmExamAttend(final String id);

    /**
     * 保存考试信息
     *
     * @param kmExamAttend 考试信息
     */
    public void saveKmExamAttend(KmExamAttend kmExamAttend);

    /**
     * 根据id删除考试信息
     *
     * @param id 主键
     */
    public void removeKmExamAttend(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getKmExamAttends(final Integer curPage, final Integer pageSize,
                                final String whereStr);

}
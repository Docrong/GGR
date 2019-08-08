package com.boco.eoms.km.exam.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.exam.model.KmExamChoice;

/**
 * <p>
 * Title:选项表
 * </p>
 * <p>
 * Description:选项表
 * </p>
 * <p>
 * Fri May 08 16:40:12 CST 2009
 * </p>
 *
 * @author hsl
 * @version 1.0
 */
public interface KmExamChoiceDao extends Dao {

    /**
     * 查询某试题下的所用选项
     *
     * @param questionID
     * @return
     */
    public List getKmExamChoices(final String questionID);

    /**
     * 取选项表列表
     *
     * @return 返回选项表列表
     */
    public List getKmExamChoices();

    /**
     * 根据主键查询选项表
     *
     * @param id 主键
     * @return 返回某id的选项表
     */
    public KmExamChoice getKmExamChoice(final String id);

    /**
     * 保存选项表
     *
     * @param kmExamChoice 选项表
     */
    public void saveKmExamChoice(KmExamChoice kmExamChoice);

    /**
     * 根据id删除选项表
     *
     * @param id 主键
     */
    public void removeKmExamChoice(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getKmExamChoices(final Integer curPage, final Integer pageSize,
                                final String whereStr);

    public List getKmExamChoicesByQuestionID(final String questionID);
}
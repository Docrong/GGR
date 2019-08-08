package com.boco.eoms.km.lesson.dao;

import com.boco.eoms.base.dao.Dao;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.lesson.model.KmLesson;

/**
 * <p>
 * Title:课程创建
 * </p>
 * <p>
 * Description:课程创建
 * </p>
 * <p>
 * Wed Jul 01 15:12:51 CST 2009
 * </p>
 *
 * @author mosquito
 * @version 1.0
 */
public interface KmLessonDao extends Dao {

    /**
     * 取课程创建列表
     *
     * @return 返回课程创建列表
     */
    public List getKmLessons();

    /**
     * 根据主键查询课程创建
     *
     * @param id 主键
     * @return 返回某id的课程创建
     */
    public KmLesson getKmLesson(final String id);

    /**
     * 保存课程创建
     *
     * @param KmLesson 课程创建
     */
    public void saveKmLesson(KmLesson creatlesson);

    /**
     * 更新
     *
     * @param id
     */
    public void updateKmLesson(final String id, final String subjectName,
                               final String businessType, final String theme,
                               final String subjectContents, final String attachment, final String totalTime);

    /**
     * 根据id删除课程创建
     *
     * @param id 主键
     */
    public void removeKmLesson(final String id);

    /**
     * 分页取列表
     *
     * @param curPage  当前页
     * @param pageSize 每页显示条数
     * @param whereStr where条件
     * @return map中total为条数, result(list) curPage页的记录
     */
    public Map getKmLessons(final Integer curPage, final Integer pageSize, final String whereStr);

}
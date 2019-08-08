package com.boco.eoms.km.lesson.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.lesson.model.LessonAnaysis;
import com.boco.eoms.km.lesson.mgr.LessonAnaysisMgr;
import com.boco.eoms.km.lesson.dao.LessonAnaysisDao;

/**
 * <p>
 * Title:考试课程分析
 * </p>
 * <p>
 * Description:考试课程分析
 * </p>
 * <p>
 * Tue Jul 07 09:44:42 CST 2009
 * </p>
 *
 * @author mosquito
 * @version 1.0
 */
public class LessonAnaysisMgrImpl implements LessonAnaysisMgr {

    private LessonAnaysisDao lessonAnaysisDao;

    public LessonAnaysisDao getLessonAnaysisDao() {
        return this.lessonAnaysisDao;
    }

    public void setLessonAnaysisDao(LessonAnaysisDao lessonAnaysisDao) {
        this.lessonAnaysisDao = lessonAnaysisDao;
    }

    public List getLessonAnaysiss() {
        return lessonAnaysisDao.getLessonAnaysiss();
    }

    public LessonAnaysis getLessonAnaysis(final String id) {
        return lessonAnaysisDao.getLessonAnaysis(id);
    }

    public void saveLessonAnaysis(LessonAnaysis lessonAnaysis) {
        lessonAnaysisDao.saveLessonAnaysis(lessonAnaysis);
    }

    public void removeLessonAnaysis(final String id) {
        lessonAnaysisDao.removeLessonAnaysis(id);
    }

    public Map getLessonAnaysiss(final Integer curPage, final Integer pageSize,
                                 final String whereStr) {
        return lessonAnaysisDao.getLessonAnaysiss(curPage, pageSize, whereStr);
    }

}
package com.boco.eoms.km.lesson.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.lesson.model.KmLesson;
import com.boco.eoms.km.lesson.mgr.KmLessonMgr;
import com.boco.eoms.km.lesson.dao.KmLessonDao;

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
public class KmLessonMgrImpl implements KmLessonMgr {

    private KmLessonDao kmLessonDao;

    public KmLessonDao getKmLessonDao() {
        return this.kmLessonDao;
    }

    public void setKmLessonDao(KmLessonDao kmLessonDao) {
        this.kmLessonDao = kmLessonDao;
    }

    public List getKmLessons() {
        return kmLessonDao.getKmLessons();
    }

    public KmLesson getKmLesson(final String id) {
        return kmLessonDao.getKmLesson(id);
    }

    public void saveKmLesson(KmLesson creatlesson) {
        kmLessonDao.saveKmLesson(creatlesson);
    }

    public void removeKmLesson(final String id) {
        kmLessonDao.removeKmLesson(id);
    }

    public Map getKmLessons(final Integer curPage, final Integer pageSize,
                            final String whereStr) {
        return kmLessonDao.getKmLessons(curPage, pageSize, whereStr);
    }

    public void updateKmLesson(final String id, final String subjectName,
                               final String businessType, final String theme,
                               final String subjectContents, final String attachment,
                               final String totalTime) {
        kmLessonDao.updateKmLesson(id, subjectName, businessType, theme,
                subjectContents, attachment, totalTime);
    }

}
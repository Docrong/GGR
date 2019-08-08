package com.boco.eoms.km.exam.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.exam.model.KmExamChoice;
import com.boco.eoms.km.exam.mgr.KmExamChoiceMgr;
import com.boco.eoms.km.exam.dao.KmExamChoiceDao;

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
public class KmExamChoiceMgrImpl implements KmExamChoiceMgr {

    private KmExamChoiceDao kmExamChoiceDao;

    public KmExamChoiceDao getKmExamChoiceDao() {
        return this.kmExamChoiceDao;
    }

    public void setKmExamChoiceDao(KmExamChoiceDao kmExamChoiceDao) {
        this.kmExamChoiceDao = kmExamChoiceDao;
    }

    /**
     * 查询某试题下的所用选项
     *
     * @param questionID
     * @return
     */
    public List getKmExamChoices(final String questionID) {
        return kmExamChoiceDao.getKmExamChoices(questionID);
    }

    public List getKmExamChoices() {
        return kmExamChoiceDao.getKmExamChoices();
    }

    public KmExamChoice getKmExamChoice(final String id) {
        return kmExamChoiceDao.getKmExamChoice(id);
    }

    public void saveKmExamChoice(KmExamChoice kmExamChoice) {
        kmExamChoiceDao.saveKmExamChoice(kmExamChoice);
    }

    public void removeKmExamChoice(final String id) {
        kmExamChoiceDao.removeKmExamChoice(id);
    }

    public Map getKmExamChoices(final Integer curPage, final Integer pageSize,
                                final String whereStr) {
        return kmExamChoiceDao.getKmExamChoices(curPage, pageSize, whereStr);
    }

    public List getKmExamChoicesByQuestionID(final String questionID) {
        return kmExamChoiceDao.getKmExamChoicesByQuestionID(questionID);
    }

}
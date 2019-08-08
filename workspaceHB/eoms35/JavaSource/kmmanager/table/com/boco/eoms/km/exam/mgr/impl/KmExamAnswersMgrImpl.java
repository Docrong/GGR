package com.boco.eoms.km.exam.mgr.impl;

import java.util.List;
import java.util.Map;

import com.boco.eoms.km.exam.model.KmExamAnswers;
import com.boco.eoms.km.exam.mgr.KmExamAnswersMgr;
import com.boco.eoms.km.exam.dao.KmExamAnswersDao;

/**
 * <p>
 * Title:答题信息表
 * </p>
 * <p>
 * Description:答题信息表
 * </p>
 * <p>
 * Mon May 11 11:00:31 CST 2009
 * </p>
 *
 * @author lvweihua
 * @version 1.0
 */
public class KmExamAnswersMgrImpl implements KmExamAnswersMgr {

    private KmExamAnswersDao kmExamAnswersDao;

    public KmExamAnswersDao getKmExamAnswersDao() {
        return this.kmExamAnswersDao;
    }

    public void setKmExamAnswersDao(KmExamAnswersDao kmExamAnswersDao) {
        this.kmExamAnswersDao = kmExamAnswersDao;
    }

    /**
     * 根据试卷id 题目id 参加考试人 查询答题信息
     *
     * @param testID
     * @param questionID
     * @param attendUser
     * @return
     */
    public KmExamAnswers getKmExamAnswers(final String testID, final String questionID, final String attendUser) {
        return kmExamAnswersDao.getKmExamAnswers(testID, questionID, attendUser);
    }

    /**
     * 对某个人的某套试卷进行求和
     *
     * @param testID
     * @param attendUser
     * @return该人该套试卷的总成绩
     */
    public String getSumScore(String testID, String attendUser) {
        return kmExamAnswersDao.getSumScore(testID, attendUser);
    }

    public List getKmExamAnswerss() {
        return kmExamAnswersDao.getKmExamAnswerss();
    }

    public KmExamAnswers getKmExamAnswers(final String id) {
        return kmExamAnswersDao.getKmExamAnswers(id);
    }

    public void saveKmExamAnswers(KmExamAnswers kmExamAnswers) {
        kmExamAnswersDao.saveKmExamAnswers(kmExamAnswers);
    }

    public void removeKmExamAnswers(final String id) {
        kmExamAnswersDao.removeKmExamAnswers(id);
    }

    public Map getKmExamAnswerss(final Integer curPage, final Integer pageSize,
                                 final String whereStr) {
        return kmExamAnswersDao.getKmExamAnswerss(curPage, pageSize, whereStr);
    }

}
package com.boco.eoms.km.ask.mgr.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.boco.eoms.km.ask.model.KmAskQuestion;
import com.boco.eoms.km.ask.model.KmAskReply;
import com.boco.eoms.km.ask.mgr.KmAskQuestionMgr;
import com.boco.eoms.km.ask.util.Common;
import com.boco.eoms.km.ask.dao.KmAskQuestionDao;
import com.boco.eoms.km.ask.dao.KmAskReplyDao;

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
public class KmAskQuestionMgrImpl implements KmAskQuestionMgr {

    private KmAskQuestionDao kmAskQuestionDao;

    private KmAskReplyDao kmAskReplyDao;

    public KmAskQuestionDao getKmAskQuestionDao() {
        return this.kmAskQuestionDao;
    }

    public void setKmAskQuestionDao(KmAskQuestionDao kmAskQuestionDao) {
        this.kmAskQuestionDao = kmAskQuestionDao;
    }

    /**
     * 取问题列表
     *
     * @return 返回问题列表
     */
    public List getKmAskQuestions(final String nodeId, final String questionStatus) {
        List kaqList = new ArrayList();
        KmAskQuestion kaq = new KmAskQuestion();
        List list = kmAskQuestionDao.getKmAskQuestions(nodeId, questionStatus);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                kaq = (KmAskQuestion) list.get(i);
                kaq.setIsAddScore(Common.getPastTime(kaq.getAskTime()));
                kaqList.add(kaq);
            }
        }
        return kaqList;
    }


    /**
     * 查询待解决的所用问题
     *
     * @return
     */
    public List getKmAskQuestions0() {
        return kmAskQuestionDao.getKmAskQuestions0();
    }

    /**
     * 查询已解决问题
     *
     * @return
     */
    public List getKmAskQuestions1() {
        return kmAskQuestionDao.getKmAskQuestions1();
    }

    /**
     * 查询投票中的问题
     *
     * @return
     */
    public List getKmAskQuestions2() {
        return kmAskQuestionDao.getKmAskQuestions2();
    }

    public KmAskQuestion getKmAskQuestion(final String id) {
        return kmAskQuestionDao.getKmAskQuestion(id);
    }

    public void saveKmAskQuestion(KmAskQuestion kmAskQuestion) {
        kmAskQuestionDao.saveKmAskQuestion(kmAskQuestion);
    }

    public void removeKmAskQuestion(final String id) {
        kmAskQuestionDao.removeKmAskQuestion(id);
    }

    /**
     * 查询默认回答的问题
     *
     * @param userId 回答问题的人
     * @return
     */
    public List getKmAskQuestionByMyReply(final String userId) {
        //该人的所有回答
        List replyList = kmAskReplyDao.getKmAskReplysByAnswerUser(userId);
        //所用问题id
        List questionIdList = new ArrayList();
        //所有问题
        List questionList = new ArrayList();
        KmAskReply kmAskReply = new KmAskReply();
        KmAskQuestion kmAskQuestion = new KmAskQuestion();
        //等到问题id并且消除问题id中的重复
        HashSet h = new HashSet();
        if (replyList != null) {
            for (int i = 0; i < replyList.size(); i++) {
                kmAskReply = (KmAskReply) replyList.get(i);
                String questionId = kmAskReply.getQuestionId();
                h.add(questionId);
            }
            questionIdList.addAll(h);
            for (int j = 0; j < questionIdList.size(); j++) {
                String questionId = questionIdList.get(j).toString();
                kmAskQuestion = kmAskQuestionDao.getKmAskQuestion(questionId);
                //时间转化
                kmAskQuestion.setIsAddScore(Common.getPastTime(kmAskQuestion.getAskTime()));
                questionList.add(kmAskQuestion);
            }
        }
        return questionList;
    }

    public Map getKmAskQuestions(final Integer curPage, final Integer pageSize,
                                 final String whereStr) {
        List kaqList = new ArrayList();
        KmAskQuestion kaq = new KmAskQuestion();
        Map map = kmAskQuestionDao.getKmAskQuestions(curPage, pageSize, whereStr);
        List list = (List) map.get("result");
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                kaq = (KmAskQuestion) list.get(i);
                kaq.setIsAddScore(Common.getPastTime(kaq.getAskTime()));
                kaqList.add(kaq);
            }
        }
        map.put("result", kaqList);
        return map;
    }

    public KmAskReplyDao getKmAskReplyDao() {
        return kmAskReplyDao;
    }

    public void setKmAskReplyDao(KmAskReplyDao kmAskReplyDao) {
        this.kmAskReplyDao = kmAskReplyDao;
    }

}
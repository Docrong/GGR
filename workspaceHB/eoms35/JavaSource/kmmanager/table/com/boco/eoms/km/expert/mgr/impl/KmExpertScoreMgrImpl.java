package com.boco.eoms.km.expert.mgr.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.km.expert.dao.KmExpertScoreDao;
import com.boco.eoms.km.expert.mgr.KmExpertScoreMgr;
import com.boco.eoms.km.expert.model.KmExpertScore;


/**
 * <p>
 * Title:知识库专家积分
 * </p>
 * <p>
 * Description:知识库专家积分
 * </p>
 * <p>
 * 2009-07-27
 * </p>
 *
 * @author daizhigang
 * @version 1.0
 */

public class KmExpertScoreMgrImpl implements KmExpertScoreMgr {

    private KmExpertScoreDao kmExpertScoreDao;

    public KmExpertScoreDao getKmExpertScoreDao() {
        return this.kmExpertScoreDao;
    }

    public void setKmExpertScoreDao(KmExpertScoreDao kmExpertScoreDao) {
        this.kmExpertScoreDao = kmExpertScoreDao;
    }


    /**
     * 保存当天专家积分
     *
     * @param scoreTime 积分时间（原则每天操作汇总到一个记录）
     */
    public void upOrSaveDayScore(KmExpertScore kmExpertScore) {
        KmExpertScore kmExpertScoreHis = kmExpertScoreDao.getKmExpertScoreByUserIdAndTime(kmExpertScore.getCreateTime(), kmExpertScore.getExpertUserId());
        if (kmExpertScoreHis == null) {
            kmExpertScoreDao.saveKmExpertScore(kmExpertScore);
        } else {
            String problemNum = String.valueOf(StaticMethod.nullObject2int(kmExpertScoreHis.getProblemNum()) + StaticMethod.nullObject2int(kmExpertScore.getProblemNum()));
            String answerNum = String.valueOf(StaticMethod.nullObject2int(kmExpertScoreHis.getAnswerNum()) + StaticMethod.nullObject2int(kmExpertScore.getAnswerNum()));
            String answerSati = String.valueOf(StaticMethod.nullObject2int(kmExpertScoreHis.getAnswerSati()) + StaticMethod.nullObject2int(kmExpertScore.getAnswerSati()));
            String caseNum = String.valueOf(StaticMethod.nullObject2int(kmExpertScoreHis.getCaseNum()) + StaticMethod.nullObject2int(kmExpertScore.getCaseNum()));
            String baseScore = String.valueOf(StaticMethod.nullObject2int(kmExpertScoreHis.getBaseScore()) + StaticMethod.nullObject2int(kmExpertScore.getBaseScore()));
            String knowledgeScore = String.valueOf(StaticMethod.nullObject2int(kmExpertScoreHis.getKnowledgeScore()) + StaticMethod.nullObject2int(kmExpertScore.getKnowledgeScore()));
            String answerScore = String.valueOf(StaticMethod.nullObject2int(kmExpertScoreHis.getAnswerScore()) + StaticMethod.nullObject2int(kmExpertScore.getAnswerScore()));
            String inputScore = String.valueOf(StaticMethod.nullObject2int(kmExpertScoreHis.getInputScore()) + StaticMethod.nullObject2int(kmExpertScore.getInputScore()));
            kmExpertScoreHis.setProblemNum(problemNum);
            kmExpertScoreHis.setAnswerNum(answerNum);
            kmExpertScoreHis.setAnswerSati(answerSati);
            kmExpertScoreHis.setCaseNum(caseNum);
            kmExpertScoreHis.setBaseScore(baseScore);
            kmExpertScoreHis.setKnowledgeScore(knowledgeScore);
            kmExpertScoreHis.setAnswerScore(answerScore);
            kmExpertScoreHis.setInputScore(inputScore);
            kmExpertScoreDao.saveKmExpertScore(kmExpertScoreHis);
        }

    }

    /**
     * 保存主动输入积分
     *
     * @param kmExpertScoreSum 专家Score对象
     */
    public void saveExpertScore(KmExpertScore kmExpertScoreSum) {
        kmExpertScoreDao.saveKmExpertScore(kmExpertScoreSum);
    }

    /**
     * 保存主动输入积分
     *
     * @param expertUserId[] 专家id集合
     * @param inputScore[]   主动输入积分集合
     */
    public void saveInputScore(String expertUserId[], String baseScore[], String inputScore[]) {
        int strLength = expertUserId.length;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newTime = dateFormat.format(new java.util.Date());
        for (int i = 0; i < strLength; i++) {
            KmExpertScore kmExpertScoreSum = new KmExpertScore();
            kmExpertScoreSum.setCreateTime(newTime);
            kmExpertScoreSum.setExpertUserId(expertUserId[i]);
            kmExpertScoreSum.setBaseScore(baseScore[i]);
            kmExpertScoreSum.setTypeFlag("sum");
            kmExpertScoreSum.setInputScore(inputScore[i]);
            kmExpertScoreDao.saveKmExpertScore(kmExpertScoreSum);
        }
    }


    /**
     * 取所有专家积分
     *
     * @return 返回所有专家积分
     */
    public List getKmExpertScores() {
        return kmExpertScoreDao.getKmExpertScores();
    }

    /**
     * 按专业和打分状态取所有专家积分
     *
     * @return 按专业和打分状态返回所有专家积分
     */
    public List getKmExpertScores(String specialty, String treeType) {
        List list = new ArrayList();
        if ("uninputed".equals(treeType)) {
            list = kmExpertScoreDao.getKmExpertScoresUninputed(specialty);
        } else if ("inputed".equals(treeType)) {
            list = kmExpertScoreDao.getKmExpertScoresInputed(specialty);
        }
        return list;
    }

    /**
     * 根据主键查询专家积分记录
     *
     * @param id 主键
     * @return 返回某id的专家积分记录
     */
    public KmExpertScore getKmExpertScore(final String id) {
        return kmExpertScoreDao.getKmExpertScore(id);
    }

    /**
     * 保存专家积分记录
     *
     * @param kmExpertScore 专家积分记录
     */
    public void saveKmExpertScore(KmExpertScore kmExpertScore) {
        kmExpertScoreDao.saveKmExpertScore(kmExpertScore);
    }

    /**
     * 根据专家积分，对专家进行排行
     *
     * @param
     */
    public List getExpertScoresOrder(String user_id, int num) {
        return kmExpertScoreDao.getExpertScoresOrder(user_id, num);
    }

    /**
     * 根据主键删除专家积分记录
     *
     * @param id 主键
     */
    public void removeKmExpertScore(final String id) {
        kmExpertScoreDao.removeKmExpertScore(id);
    }

    /**
     * 根据专家id查询记录类型是sum的总积分
     *
     * @param expertUserId 专家ID
     */
    public int getSumByexpertUserId(final String expertUserId) {
        return kmExpertScoreDao.getSumByexpertUserId(expertUserId);
    }
}
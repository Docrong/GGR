package com.boco.eoms.km.exam.mgr.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts.upload.FormFile;

import com.boco.eoms.km.exam.model.KmExamChoice;
import com.boco.eoms.km.exam.model.KmExamQuestions;
import com.boco.eoms.km.exam.mgr.KmExamQuestionsMgr;
import com.boco.eoms.km.exam.dao.KmExamChoiceDao;
import com.boco.eoms.km.exam.dao.KmExamQuestionsDao;

/**
 * <p>
 * Title:题库管理
 * </p>
 * <p>
 * Description:题库管理
 * </p>
 * <p>
 * Fri May 08 16:40:11 CST 2009
 * </p>
 *
 * @author hsl
 * @version 1.0
 */
public class KmExamQuestionsMgrImpl implements KmExamQuestionsMgr {

    private KmExamQuestionsDao kmExamQuestionsDao;

    private KmExamChoiceDao kmExamChoiceDao;

    public KmExamQuestionsDao getKmExamQuestionsDao() {
        return this.kmExamQuestionsDao;
    }

    public void setKmExamQuestionsDao(KmExamQuestionsDao kmExamQuestionsDao) {
        this.kmExamQuestionsDao = kmExamQuestionsDao;
    }

    public List getKmExamQuestionss() {
        return kmExamQuestionsDao.getKmExamQuestionss();
    }

    public KmExamQuestions getKmExamQuestions(final String id) {
        return kmExamQuestionsDao.getKmExamQuestions(id);
    }

    public void saveKmExamQuestions(KmExamQuestions kmExamQuestions) {
        kmExamQuestionsDao.saveKmExamQuestions(kmExamQuestions);
    }

    public void removeKmExamQuestions(final String id) {
        kmExamQuestionsDao.removeKmExamQuestions(id);
    }

    public void saveKmExamQuestions(final Map map) {
        try {
            //得到试题的各个属性
            KmExamQuestions kmExamQuestions = new KmExamQuestions();
            kmExamQuestions.setAnswer(map.get("answer").toString());
            kmExamQuestions.setCreateDept(map.get("createDept").toString());
            //kmExamQuestions.setCreateTime(new Date());
            kmExamQuestions.setCreateUser(map.get("createUser").toString());
            kmExamQuestions.setDeptId(map.get("deptId").toString());
            kmExamQuestions.setDifficulty(map.get("difficulty").toString());
            kmExamQuestions.setIsDeleted("0");
            kmExamQuestions.setQuestion(map.get("question").toString());
            kmExamQuestions.setQuestionType(map.get("questionType").toString());
            kmExamQuestionsDao.saveKmExamQuestions(kmExamQuestions);
            String questionsID = kmExamQuestions.getQuestionID();
            //判断题 A B 两项为必选的  CDEFG为可选
            if (map.get("questionType").equals("1") || map.get("questionType").equals("2") || map.get("questionType").equals("3")) {
                //A选项
                KmExamChoice kmExamChoicea = new KmExamChoice();
                kmExamChoicea.setOrderBy("A");
                kmExamChoicea.setContent(map.get("A").toString());
                kmExamChoicea.setQuestionsID(questionsID);
                kmExamChoiceDao.saveKmExamChoice(kmExamChoicea);
                //B选项
                KmExamChoice kmExamChoiceb = new KmExamChoice();
                kmExamChoiceb.setOrderBy("B");
                kmExamChoiceb.setContent(map.get("B").toString());
                kmExamChoiceb.setQuestionsID(questionsID);
                kmExamChoiceDao.saveKmExamChoice(kmExamChoiceb);
                //C选项
                if (map.get("C") != null) {
                    KmExamChoice kmExamChoicec = new KmExamChoice();
                    kmExamChoicec.setOrderBy("C");
                    kmExamChoicec.setContent(map.get("C").toString());
                    kmExamChoicec.setQuestionsID(questionsID);
                    kmExamChoiceDao.saveKmExamChoice(kmExamChoicec);
                }
                //D选项
                if (map.get("D") != null) {
                    KmExamChoice kmExamChoiced = new KmExamChoice();
                    kmExamChoiced.setOrderBy("D");
                    kmExamChoiced.setContent(map.get("D").toString());
                    kmExamChoiced.setQuestionsID(questionsID);
                    kmExamChoiceDao.saveKmExamChoice(kmExamChoiced);
                }
                //E选项
                if (map.get("E") != null) {
                    KmExamChoice kmExamChoicee = new KmExamChoice();
                    kmExamChoicee.setOrderBy("E");
                    kmExamChoicee.setContent(map.get("E").toString());
                    kmExamChoicee.setQuestionsID(questionsID);
                    kmExamChoiceDao.saveKmExamChoice(kmExamChoicee);
                }
                //F选项
                if (map.get("F") != null) {
                    KmExamChoice kmExamChoicef = new KmExamChoice();
                    kmExamChoicef.setOrderBy("F");
                    kmExamChoicef.setContent(map.get("F").toString());
                    kmExamChoicef.setQuestionsID(questionsID);
                    kmExamChoiceDao.saveKmExamChoice(kmExamChoicef);
                }
                //G选项
                if (map.get("G") != null) {
                    KmExamChoice kmExamChoiceg = new KmExamChoice();
                    kmExamChoiceg.setOrderBy("G");
                    kmExamChoiceg.setContent(map.get("G").toString());
                    kmExamChoiceg.setQuestionsID(questionsID);
                    kmExamChoiceDao.saveKmExamChoice(kmExamChoiceg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map getKmExamQuestionss(final Integer curPage, final Integer pageSize,
                                   final String whereStr) {
        return kmExamQuestionsDao.getKmExamQuestionss(curPage, pageSize, whereStr);
    }

    public KmExamChoiceDao getKmExamChoiceDao() {
        return kmExamChoiceDao;
    }

    public void setKmExamChoiceDao(KmExamChoiceDao kmExamChoiceDao) {
        this.kmExamChoiceDao = kmExamChoiceDao;
    }

}
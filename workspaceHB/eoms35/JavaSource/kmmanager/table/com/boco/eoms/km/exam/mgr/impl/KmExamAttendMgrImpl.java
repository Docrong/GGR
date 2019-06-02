package com.boco.eoms.km.exam.mgr.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.km.exam.model.KmExamAnswers;
import com.boco.eoms.km.exam.model.KmExamAttend;
import com.boco.eoms.km.exam.model.KmExamQuestions;
import com.boco.eoms.km.exam.model.KmExamTestType;
import com.boco.eoms.km.exam.model.KmExamTestTypeContent;
import com.boco.eoms.km.exam.mgr.KmExamAttendMgr;
import com.boco.eoms.km.exam.dao.KmExamAnswersDao;
import com.boco.eoms.km.exam.dao.KmExamAttendDao;
import com.boco.eoms.km.exam.dao.KmExamQuestionsDao;
import com.boco.eoms.km.exam.dao.KmExamTestTypeContentDao;
import com.boco.eoms.km.exam.dao.KmExamTestTypeDao;

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
 * 
 */
public class KmExamAttendMgrImpl implements KmExamAttendMgr {
	
	private KmExamAttendDao  kmExamAttendDao;
 	
	private KmExamTestTypeDao  kmExamTestTypeDao;
	
	private KmExamTestTypeContentDao  kmExamTestTypeContentDao;
	
	private KmExamQuestionsDao  kmExamQuestionsDao;
	
	private KmExamAnswersDao  kmExamAnswersDao;
	
	public KmExamAttendDao getKmExamAttendDao() {
		return this.kmExamAttendDao;
	}
 	
	public void setKmExamAttendDao(KmExamAttendDao kmExamAttendDao) {
		this.kmExamAttendDao = kmExamAttendDao;
	}
 	
	/**
	 * 根据试卷id和参加考试人信息得到唯一考试信息
	 * @param testID
	 * @param attendUser
	 * @return
	 */
	public KmExamAttend getKmExamAttend(final String testID,final String attendUser){
		return kmExamAttendDao.getKmExamAttend(testID, attendUser);
	}
	
	/**
     * 保存答题信息
     * @param
     * @return
     */
    public void saveKmExamAttend(final Map map) {
		//当前所答试卷id
    	String testID = getValue(map,"testID");
    	String userId = StaticMethod.nullObject2String(map.get("userId"));
    	String deptId = StaticMethod.nullObject2String(map.get("deptId"));

		//查询该试卷下所有的类型
		List kmExamTestTypeList = kmExamTestTypeDao.getKmExamTestTypesByTestID(testID);
		//答题用户信息
		for(int i=0;i<kmExamTestTypeList.size();i++){
			//得到试题类型信息
			KmExamTestType kmExamTestType = (KmExamTestType)kmExamTestTypeList.get(i);
			String testTypeID = kmExamTestType.getTestTypeId(); 
			//查询类型下所有的试题
			//保存不同分类下的答题信息，并计算客观题分数 4填空题 5简答题
			List kmExamTestTypeContentList = kmExamTestTypeContentDao.getKmExamTestTypeContentByTestTypeID(testTypeID);
			int list = kmExamTestTypeContentList.size();
			for(int j=0;j<list;j++){
				KmExamTestTypeContent kmExamTestTypeContent = (KmExamTestTypeContent)kmExamTestTypeContentList.get(j);
				String questionID = kmExamTestTypeContent.getQuestionID();
				//得到用户所答试卷 答案数组
				String answer = getValue(map,questionID);
				
				//根据问题questionID查询问题标准答案
				KmExamQuestions kmExamQuestions = kmExamQuestionsDao.getKmExamQuestions(questionID);
				String referenceAnswer = kmExamQuestions.getAnswer();
				
				KmExamAnswers kmExamAnswers = new KmExamAnswers();
				kmExamAnswers.setQuestionId(questionID);
				kmExamAnswers.setTestID(testID);
				kmExamAnswers.setAnswer(answer);
				kmExamAnswers.setReferenceAnswer(referenceAnswer);
				kmExamAnswers.setAttendUser(userId);
				kmExamAnswers.setReferenceScore(kmExamTestTypeContent.getScore());
				//对客观题进行自动判分  1单选题 2多选题 3判断题 
				if(kmExamTestType.getType().equals("1")||kmExamTestType.getType().equals("2")||kmExamTestType.getType().equals("3")){
					String answerScore = "0";
					if(referenceAnswer.equals(answer)){
						answerScore = kmExamTestTypeContent.getScore();
					}
					kmExamAnswers.setScore(answerScore);
				}
				//保存用户所写的答案
				kmExamAnswersDao.saveKmExamAnswers(kmExamAnswers);
			}
		}
		
		//保存参加考试人的相关信息
		KmExamAttend kmExamAttend = new KmExamAttend();
		//用户开始考试答题的时间
		String dateString = getValue(map,"dateString");
		Date attendTime = Timestamp.valueOf(dateString);
		//用户结束考试的时间
		Date overTime = StaticMethod.getLocalTime();
		kmExamAttend.setAttendTime(attendTime);
		kmExamAttend.setAttendOverTime(overTime);
		kmExamAttend.setAttendUser(userId);
		kmExamAttend.setAttendDept(deptId);
		kmExamAttend.setTestId(testID);
		kmExamAttend.setIsRead("0");
		kmExamAttend.setIsPublic("0");
		kmExamAttendDao.saveKmExamAttend(kmExamAttend);
    }
	
    public String getValue(Map map,String o){
		Object [] values = (Object [])map.get(o);
		String value = "";
		if(values!=null){
			for(int k=0;k<values.length;k++){
				value = value + values[k];
			}
		}
		return value;
    }
    
	/**
	 * 查询当前人参加考试的发布结果信息
	 * @param attendUser
	 * @return
	 */
	public List getKmExamAttends(final String attendUser,final String isPublic){
		return kmExamAttendDao.getKmExamAttends(attendUser, isPublic);
	}
	
    public List getKmExamAttends() {
    	return kmExamAttendDao.getKmExamAttends();
    }
    
    public KmExamAttend getKmExamAttend(final String id) {
    	return kmExamAttendDao.getKmExamAttend(id);
    }
    
    public void saveKmExamAttend(KmExamAttend kmExamAttend) {
    	kmExamAttendDao.saveKmExamAttend(kmExamAttend);
    }
    
    public void removeKmExamAttend(final String id) {
    	kmExamAttendDao.removeKmExamAttend(id);
    }
    
    public Map getKmExamAttends(final Integer curPage, final Integer pageSize,
			final String whereStr) {
		return kmExamAttendDao.getKmExamAttends(curPage, pageSize, whereStr);
	}

	public KmExamTestTypeDao getKmExamTestTypeDao() {
		return kmExamTestTypeDao;
	}

	public void setKmExamTestTypeDao(KmExamTestTypeDao kmExamTestTypeDao) {
		this.kmExamTestTypeDao = kmExamTestTypeDao;
	}

	public KmExamTestTypeContentDao getKmExamTestTypeContentDao() {
		return kmExamTestTypeContentDao;
	}

	public void setKmExamTestTypeContentDao(
			KmExamTestTypeContentDao kmExamTestTypeContentDao) {
		this.kmExamTestTypeContentDao = kmExamTestTypeContentDao;
	}

	public KmExamQuestionsDao getKmExamQuestionsDao() {
		return kmExamQuestionsDao;
	}

	public void setKmExamQuestionsDao(KmExamQuestionsDao kmExamQuestionsDao) {
		this.kmExamQuestionsDao = kmExamQuestionsDao;
	}

	public KmExamAnswersDao getKmExamAnswersDao() {
		return kmExamAnswersDao;
	}

	public void setKmExamAnswersDao(KmExamAnswersDao kmExamAnswersDao) {
		this.kmExamAnswersDao = kmExamAnswersDao;
	}
	
}
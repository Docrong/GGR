package com.boco.eoms.km.exam.webapp.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.EOMSAttributes;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dept.dao.hibernate.TawSystemDeptDaoHibernate;
import com.boco.eoms.commons.system.user.dao.hibernate.TawSystemUserDaoHibernate;
import com.boco.eoms.km.exam.dao.hibernate.KmExamTestDaoHibernate;
import com.boco.eoms.km.exam.mgr.KmExamAnswersMgr;
import com.boco.eoms.km.exam.mgr.KmExamAttendMgr;
import com.boco.eoms.km.exam.mgr.KmExamAttendRecordMgr;
import com.boco.eoms.km.exam.mgr.KmExamChoiceMgr;
import com.boco.eoms.km.exam.mgr.KmExamQuestionsMgr;
import com.boco.eoms.km.exam.mgr.KmExamTestMgr;
import com.boco.eoms.km.exam.mgr.KmExamTestTypeContentMgr;
import com.boco.eoms.km.exam.mgr.KmExamTestTypeMgr;
import com.boco.eoms.km.exam.model.KmExamAnswers;
import com.boco.eoms.km.exam.model.KmExamAttend;
import com.boco.eoms.km.exam.model.KmExamAttendRecord;
import com.boco.eoms.km.exam.model.KmExamQuestions;
import com.boco.eoms.km.exam.model.KmExamTest;
import com.boco.eoms.km.exam.model.KmExamTestType;
import com.boco.eoms.km.exam.model.KmExamTestTypeContent;
import com.boco.eoms.km.exam.observer.ExamControllor;
import com.boco.eoms.km.exam.observer.KmExamAttender;
import com.boco.eoms.km.exam.util.KmExamAttendConstants;
import com.boco.eoms.km.exam.util.KmExamTestConstants;
import com.boco.eoms.km.exam.webapp.form.KmExamAttendForm;
import com.boco.eoms.km.exam.webapp.form.KmExamQuestionsForm;
import com.boco.eoms.km.exam.webapp.form.KmExamTestForm;
import com.boco.eoms.km.exam.webapp.form.KmExamTestTypeForm;
import com.boco.eoms.km.statics.webapp.action.PersonalApplyStatisticAction;
import com.boco.eoms.sequence.ISequenceFacade;
import com.boco.eoms.sequence.Sequence;
import com.boco.eoms.sequence.exception.SequenceNotFoundException;
import com.boco.eoms.sequence.util.SequenceLocator;

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
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 */
public final class KmExamAttendAction extends BaseAction {

    /**
     * 未指定方法时默认调用的方法
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward unspecified(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return search(mapping, form, request, response);
    }

    /**
     * 进入考试
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward attend(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //所要参加考试的试卷的id信息
        String testID = StaticMethod.null2String(request.getParameter("testID"));
        KmExamTestMgr kmExamTestMgr = (KmExamTestMgr) getBean("kmExamTestMgr");
        //当前试卷信息
        KmExamTest kmExamTest = (KmExamTest) kmExamTestMgr.getKmExamTest(testID);
        request.setAttribute("kmExamTest", kmExamTest);

        //当前用户进入时间
        Date currentDate = StaticMethod.getLocalTime();

        //*********记录参加考试状态*******
        KmExamAttendRecordMgr kmExamAttendRecordMgr = (KmExamAttendRecordMgr) getBean("kmExamAttendRecordMgr");
        String userId = this.getUser(request).getUserid();
        String ipAddress = request.getRemoteAddr();
        KmExamAttendRecord kmExamAttendRecord = kmExamAttendRecordMgr.getKmExamAttendRecordByUser(userId, ipAddress);
        if (kmExamAttendRecord != null) {
            return mapping.findForward("fail3");
        }
        kmExamAttendRecord = (KmExamAttendRecord) kmExamAttendRecordMgr.getKmExamAttendNoOtherRecord(userId, ipAddress, testID);
        if (kmExamAttendRecord == null) {
            kmExamAttendRecord = (KmExamAttendRecord) kmExamAttendRecordMgr.getKmExamAttendRecord(userId, ipAddress, testID);
            if (kmExamAttendRecord == null) {
                ;
                Date overTime = new Date();
                int testTime = Integer.parseInt(kmExamTest.getTestDuration());
                overTime.setTime(currentDate.getTime() + testTime * 60 * 1000);
                kmExamAttendRecord = new KmExamAttendRecord();
                kmExamAttendRecord.setInTime(currentDate);
                kmExamAttendRecord.setIsOut("0");
                kmExamAttendRecord.setMacAddress(ipAddress);
                kmExamAttendRecord.setOverTime(overTime);
                kmExamAttendRecord.setUserId(userId);
                kmExamAttendRecord.setTestId(testID);
                kmExamAttendRecordMgr.saveKmExamAttendRecord(kmExamAttendRecord);
                //加入到考试监控
                KmExamAttender kmExamAttender = new KmExamAttender();
                //BeanUtils.copyProperties(kmExamAttender, kmExamAttendRecord);
                kmExamAttender.setId(kmExamAttendRecord.getId());
                kmExamAttender.setOverTime(kmExamAttendRecord.getOverTime());
                ExamControllor examControlloer = ExamControllor.getInstance();
                examControlloer.addObserver(kmExamAttender);
                kmExamAttender.setKmExamAttendRecordMgr(kmExamAttendRecordMgr);

            } else if ("1".equals(kmExamAttendRecord.getIsOut())) {
                //System.out.println("你的本次考试已结束");
                response.getWriter().write("1");
                return null;
            }
        } else {
            //System.out.println("你的本次考试已结束");
            response.getWriter().write("2");
            return null;
        }
        request.setAttribute("currentTime", String.valueOf(currentDate.getTime()));
        request.setAttribute("overTime", String.valueOf(kmExamAttendRecord.getOverTime().getTime()));
        //*************end*************


        //判断该考生是否已经做过该套试卷了  查询该用户所有的考试信息
        KmExamAttendMgr kmExamAttendMgr = (KmExamAttendMgr) getBean("kmExamAttendMgr");
        //当前登陆人信息
        String attendUser = this.getUser(request).getUserid();
        String id = kmExamAttendMgr.getKmExamAttend(testID, attendUser).getId();
        if (id != null && id.length() != 0) {
            return mapping.findForward("fail");//您已经参加过该考试了！
        }

        //得到该试卷的有效时间
        Date testBeginDate = kmExamTest.getTestBeginTime();
        Date testEndTime = kmExamTest.getTestEndTime();

        String dateString = StaticMethod.date2String(StaticMethod.getLocalTime());
        request.setAttribute("dateString", dateString);

        if (currentDate.before(testBeginDate)) {
            return mapping.findForward("fail1");//时间不到
        }
        if (currentDate.after(testEndTime)) {
            return mapping.findForward("fail2");//时间已经过了
        }
        KmExamTestTypeMgr kmExamTestTypeMgr = (KmExamTestTypeMgr) getBean("kmExamTestTypeMgr");
        KmExamTestTypeContentMgr kmExamTestTypeContentMgr = (KmExamTestTypeContentMgr) getBean("kmExamTestTypeContentMgr");
        KmExamQuestionsMgr kmExamQuestionsMgr = (KmExamQuestionsMgr) getBean("kmExamQuestionsMgr");
        KmExamChoiceMgr kmExamChoiceMgr = (KmExamChoiceMgr) getBean("kmExamChoiceMgr");
        KmExamTestForm kmExamTestForm = (KmExamTestForm) convert(kmExamTest);
        updateFormBean(mapping, request, kmExamTestForm);

        List testTypeList = kmExamTestTypeMgr.getKmExamTestTypesByTestID(testID);
        request.setAttribute("testTypeList", testTypeList);
        int totalQuestions = 0;
        for (Iterator iter = testTypeList.iterator(); iter.hasNext(); ) {
            KmExamTestType kmExamTestType = (KmExamTestType) iter.next();
            String testTypeID = kmExamTestType.getTestTypeId();
            String type = kmExamTestType.getType();
            List contentList = kmExamTestTypeContentMgr.getKmExamTestTypeContentByTestTypeID(testTypeID);
            KmExamTestTypeForm kmExamTestTypeForm = (KmExamTestTypeForm) convert(kmExamTestType);
            request.setAttribute("kmExamTestTypeForm" + type, kmExamTestTypeForm);
            request.setAttribute("contentList" + type, contentList);
            int count = 1;
            List questionlist = new ArrayList();
            List choiceList = new ArrayList();
            for (Iterator iter1 = contentList.iterator(); iter1.hasNext(); ) {
                KmExamTestTypeContent kmExamTestTypeContent = (KmExamTestTypeContent) iter1.next();
                String questionID = kmExamTestTypeContent.getQuestionID();
                KmExamQuestions kmExamQuestions = kmExamQuestionsMgr.getKmExamQuestions(questionID);
                KmExamQuestionsForm kmExamQuestionsForm = (KmExamQuestionsForm) convert(kmExamQuestions);
                questionlist.add(kmExamQuestionsForm);
                request.setAttribute("kmExamQuestionsForm" + type + "" + count, kmExamQuestionsForm);
                List choiceList1 = kmExamChoiceMgr.getKmExamChoicesByQuestionID(questionID);
                choiceList.addAll(choiceList1);
                count++;
            }
            request.setAttribute("choiceList" + type, choiceList);
            request.setAttribute("questionlist" + type, questionlist);
            totalQuestions += questionlist.size();
        }

        request.setAttribute("total", new Integer(totalQuestions));
        return mapping.findForward("attend");
    }

    /**
     * 进入随机试卷的考试
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward attendRandomTest(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmExamTestMgr kmExamTestMgr = (KmExamTestMgr) getBean("kmExamTestMgr");
        List list = kmExamTestMgr.getKmExamRandomTests();
        int testsList = list.size();
        //如果没有随机题目。。
//		if(testsList ==0){
//			return mapping.findForward("error");
//		}
        int i = (int) (Math.random() * testsList);
        KmExamTest kmExamTest = (KmExamTest) list.get(i);
        request.setAttribute("kmExamTest", kmExamTest);
        KmExamTestTypeMgr kmExamTestTypeMgr = (KmExamTestTypeMgr) getBean("kmExamTestTypeMgr");
        KmExamTestTypeContentMgr kmExamTestTypeContentMgr = (KmExamTestTypeContentMgr) getBean("kmExamTestTypeContentMgr");
        KmExamQuestionsMgr kmExamQuestionsMgr = (KmExamQuestionsMgr) getBean("kmExamQuestionsMgr");
        KmExamChoiceMgr kmExamChoiceMgr = (KmExamChoiceMgr) getBean("kmExamChoiceMgr");
        String testID = kmExamTest.getTestID();
        KmExamTestForm kmExamTestForm = (KmExamTestForm) convert(kmExamTest);
        updateFormBean(mapping, request, kmExamTestForm);

        List testTypeList = kmExamTestTypeMgr.getKmExamTestTypesByTestID(testID);
        request.setAttribute("testTypeList", testTypeList);

        for (Iterator iter = testTypeList.iterator(); iter.hasNext(); ) {
            KmExamTestType kmExamTestType = (KmExamTestType) iter.next();
            String testTypeID = kmExamTestType.getTestTypeId();
            String type = kmExamTestType.getType();
            List contentList = kmExamTestTypeContentMgr.getKmExamTestTypeContentByTestTypeID(testTypeID);
            KmExamTestTypeForm kmExamTestTypeForm = (KmExamTestTypeForm) convert(kmExamTestType);
            request.setAttribute("kmExamTestTypeForm" + type, kmExamTestTypeForm);
            request.setAttribute("contentList" + type, contentList);
            int count = 1;
            List questionlist = new ArrayList();
            List choiceList = new ArrayList();
            for (Iterator iter1 = contentList.iterator(); iter1.hasNext(); ) {
                KmExamTestTypeContent kmExamTestTypeContent = (KmExamTestTypeContent) iter1.next();
                String questionID = kmExamTestTypeContent.getQuestionID();
                KmExamQuestions kmExamQuestions = kmExamQuestionsMgr.getKmExamQuestions(questionID);
                KmExamQuestionsForm kmExamQuestionsForm = (KmExamQuestionsForm) convert(kmExamQuestions);
                questionlist.add(kmExamQuestionsForm);
                request.setAttribute("kmExamQuestionsForm" + type + "" + count, kmExamQuestionsForm);
                List choiceList1 = kmExamChoiceMgr.getKmExamChoicesByQuestionID(questionID);
                choiceList.addAll(choiceList1);
                count++;
            }
            request.setAttribute("choiceList" + type, choiceList);
            request.setAttribute("questionlist" + type, questionlist);
        }
        return mapping.findForward("attendRandom");
    }

    /**
     * 提交答题信息并保存 保存时对客观题进行阅卷
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmExamAttendMgr kmExamAttendMgr = (KmExamAttendMgr) getBean("kmExamAttendMgr");
        //答题用户信息
        String userId = this.getUser(request).getUserid();
        String deptId = this.getUser(request).getDeptid();
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("deptId", deptId);
        // 处理：页面数据格式转换
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = (String) parameterNames.nextElement();
            String values[] = request.getParameterValues(name);
            map.put(name, values);
        }
        String sequenceOpen = StaticMethod
                .null2String(((EOMSAttributes) ApplicationContextHolder
                        .getInstance().getBean("eomsAttributes"))
                        .getSequenceOpen());
        if ("true".equals(sequenceOpen)) {
            ISequenceFacade sequenceFacade = SequenceLocator
                    .getSequenceFacade();
            Sequence saveexamSequence = null;
            try {
                saveexamSequence = sequenceFacade.getSequence("saveexam");
            } catch (SequenceNotFoundException e) {
                e.printStackTrace();
            }
            sequenceFacade.put(kmExamAttendMgr, "saveKmExamAttend",
                    new Class[]{java.util.Map.class},
                    new Object[]{map}, null,
                    saveexamSequence);
            saveexamSequence.setChanged();
            sequenceFacade.doJob(saveexamSequence);

        } else {
            kmExamAttendMgr.saveKmExamAttend(map);
        }
        //*********记录参加考试结束状态*******
        KmExamAttendRecordMgr kmExamAttendRecordMgr = (KmExamAttendRecordMgr) getBean("kmExamAttendRecordMgr");
        String ipAddress = request.getRemoteAddr();
        String testID = StaticMethod.null2String(request.getParameter("testID"));
        KmExamAttendRecord kmExamAttendRecord = (KmExamAttendRecord) kmExamAttendRecordMgr.getKmExamAttendRecord(userId, ipAddress, testID);
        if (kmExamAttendRecord != null) {
            kmExamAttendRecord.setIsOut("1");
            kmExamAttendRecord.setOutTime(new Date());
            kmExamAttendRecordMgr.saveKmExamAttendRecord(kmExamAttendRecord);
        }
        //*************end*************

        return mapping.findForward("success");
    }


    /**
     * 提交答题信息并保存 保存时对客观题进行阅卷
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
//	public ActionForward save(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		//答题用户信息
//		String userId  = this.getUser(request).getUserid();
//		String deptId = this.getUser(request).getDeptid();
//		KmExamAttendMgr kmExamAttendMgr = (KmExamAttendMgr) getBean("kmExamAttendMgr");
//		KmExamTestTypeMgr kmExamTestTypeMgr = (KmExamTestTypeMgr) getBean("kmExamTestTypeMgr");
//		KmExamTestTypeContentMgr kmExamTestTypeContentMgr = (KmExamTestTypeContentMgr) getBean("kmExamTestTypeContentMgr");
//		KmExamQuestionsMgr kmExamQuestionsMgr = (KmExamQuestionsMgr)getBean("kmExamQuestionsMgr");
//		KmExamAnswersMgr kmExamAnswersMgr = (KmExamAnswersMgr) getBean("kmExamAnswersMgr");
//		//当前所答试卷id
//		String testID = StaticMethod.null2String(request.getParameter("testID"));
//		//查询该试卷下所有的类型
//		List kmExamTestTypeList = kmExamTestTypeMgr.getKmExamTestTypesByTestID(testID);
//		
//		for(int i=0;i<kmExamTestTypeList.size();i++){
//			//得到试题类型信息
//			KmExamTestType kmExamTestType = (KmExamTestType)kmExamTestTypeList.get(i);
//			String testTypeID = kmExamTestType.getTestTypeId();
//			//查询类型下所有的试题
//			//保存不同分类下的答题信息，并计算客观题分数 4填空题 5简答题
//			List kmExamTestTypeContentList = kmExamTestTypeContentMgr.getKmExamTestTypeContentByTestTypeID(testTypeID);
//			int list = kmExamTestTypeContentList.size();
//			for(int j=0;j<list;j++){
//				KmExamTestTypeContent kmExamTestTypeContent = (KmExamTestTypeContent)kmExamTestTypeContentList.get(j);
//				String questionID = kmExamTestTypeContent.getQuestionID();
//				//得到用户所答试卷 答案数组
//				String [] answers = request.getParameterValues(questionID);
//				String answer = "";
//				if(answers!=null){
//					for(int k=0;k<answers.length;k++){
//						answer = answer + answers[k];
//					}
//				}
//				//根据问题questionID查询问题标准答案
//				KmExamQuestions kmExamQuestions = kmExamQuestionsMgr.getKmExamQuestions(questionID);
//				String referenceAnswer = kmExamQuestions.getAnswer();
//				
//				KmExamAnswers kmExamAnswers = new KmExamAnswers();
//				kmExamAnswers.setQuestionId(questionID);
//				kmExamAnswers.setTestID(testID);
//				kmExamAnswers.setAnswer(answer);
//				kmExamAnswers.setReferenceAnswer(referenceAnswer);
//				kmExamAnswers.setAttendUser(userId);
//				kmExamAnswers.setReferenceScore(kmExamTestTypeContent.getScore());
//				//对客观题进行自动判分  1单选题 2多选题 3判断题 
//				if(kmExamTestType.getType().equals("1")||kmExamTestType.getType().equals("2")||kmExamTestType.getType().equals("3")){
//					String answerScore = "0";
//					if(referenceAnswer.equals(answer)){
//						answerScore = kmExamTestTypeContent.getScore();
//					}
//					kmExamAnswers.setScore(answerScore);
//				}
//				
//				//保存用户所写的答案
//				String sequenceOpen = StaticMethod
//				.null2String(((EOMSAttributes) ApplicationContextHolder
//						.getInstance().getBean("eomsAttributes"))
//						.getSequenceOpen());
//				if ("true".equals(sequenceOpen)) {
//					ISequenceFacade sequenceFacade = SequenceLocator
//							.getSequenceFacade();
//					Sequence saveexamSequence = null;
//					try {
//						saveexamSequence = sequenceFacade.getSequence("saveexam");
//					} catch (SequenceNotFoundException e) {
//						e.printStackTrace();
//					}			
//					sequenceFacade.put(kmExamAnswersMgr, "saveKmExamAnswers", 
//							new Class[] {com.boco.eoms.km.exam.model.KmExamAnswers.class},
//							new Object[] {kmExamAnswers}, null,
//							saveexamSequence);
//					saveexamSequence.setChanged();
//					sequenceFacade.doJob(saveexamSequence);
//					 
//				} else {
//					kmExamAnswersMgr.saveKmExamAnswers(kmExamAnswers);
//				}
//				
//			}
//		}
//		
//		//保存参加考试人的相关信息
//		KmExamAttend kmExamAttend = new KmExamAttend();
//		//用户开始考试答题的时间
//		String dateString = StaticMethod.null2String(request.getParameter("dateString"));
//		Date attendTime = Timestamp.valueOf(dateString);
//		//用户结束考试的时间
//		Date overTime = StaticMethod.getLocalTime();
//		kmExamAttend.setAttendTime(attendTime);
//		kmExamAttend.setAttendOverTime(overTime);
//		kmExamAttend.setAttendUser(userId);
//		kmExamAttend.setAttendDept(deptId);
//		kmExamAttend.setTestId(testID);
//		kmExamAttend.setIsRead("0");
//		kmExamAttend.setIsPublic("0");
//		
//		String sequenceOpen = StaticMethod
//		.null2String(((EOMSAttributes) ApplicationContextHolder
//				.getInstance().getBean("eomsAttributes"))
//				.getSequenceOpen());
//		if ("true".equals(sequenceOpen)) {
//			ISequenceFacade sequenceFacade = SequenceLocator
//					.getSequenceFacade();
//			Sequence saveexamSequence1 = null;
//			try {
//				saveexamSequence1 = sequenceFacade.getSequence("saveexam1");
//			} catch (SequenceNotFoundException e) {
//				e.printStackTrace();
//			}			
//			sequenceFacade.put(kmExamAttendMgr, "saveKmExamAttend", 
//					new Class[] {com.boco.eoms.km.exam.model.KmExamAttend.class},
//					new Object[] {kmExamAttend}, null,
//					saveexamSequence1);
//			saveexamSequence1.setChanged();
//			sequenceFacade.doJob(saveexamSequence1);
//			 
//		} else {
//			kmExamAttendMgr.saveKmExamAttend(kmExamAttend);
//		}
//		return mapping.findForward("success");
//	}


    /**
     * 查看已阅卷信息列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 已阅试卷列表
     * @throws Exception
     */
    public ActionForward searchRead(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmExamAttendConstants.KMEXAMATTEND_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmExamAttendMgr kmExamAttendMgr = (KmExamAttendMgr) getBean("kmExamAttendMgr");
        String whereStr = " where kmExamAttend.isRead = '1'";
        Map map = (Map) kmExamAttendMgr.getKmExamAttends(pageIndex, pageSize, whereStr);
        List list = (List) map.get("result");
        request.setAttribute(KmExamAttendConstants.KMEXAMATTEND_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("read");
    }

    /**
     * 查看已阅卷详情
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 已阅试卷详情
     * @throws Exception
     */
    public ActionForward attendRead(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //试卷的现实查询
        String testID = StaticMethod.null2String(request.getParameter("testID"));
        String attendUser = StaticMethod.null2String(request.getParameter("attendUser"));
        request.setAttribute("attendUser", attendUser);
        KmExamAttendMgr kmExamAttendMgr = (KmExamAttendMgr) getBean("kmExamAttendMgr");
        KmExamAttend kmExamAttend = kmExamAttendMgr.getKmExamAttend(testID, attendUser);
        request.setAttribute("kmExamAttend", kmExamAttend);

        KmExamTestMgr kmExamTestMgr = (KmExamTestMgr) getBean("kmExamTestMgr");
        //当前试卷信息
        KmExamTest kmExamTest = (KmExamTest) kmExamTestMgr.getKmExamTest(testID);
        request.setAttribute("kmExamTest", kmExamTest);

        KmExamTestTypeMgr kmExamTestTypeMgr = (KmExamTestTypeMgr) getBean("kmExamTestTypeMgr");
        KmExamTestTypeContentMgr kmExamTestTypeContentMgr = (KmExamTestTypeContentMgr) getBean("kmExamTestTypeContentMgr");
        KmExamQuestionsMgr kmExamQuestionsMgr = (KmExamQuestionsMgr) getBean("kmExamQuestionsMgr");
        KmExamChoiceMgr kmExamChoiceMgr = (KmExamChoiceMgr) getBean("kmExamChoiceMgr");
        KmExamTestForm kmExamTestForm = (KmExamTestForm) convert(kmExamTest);
        updateFormBean(mapping, request, kmExamTestForm);

        //得到所有的试卷类型
        List testTypeList = kmExamTestTypeMgr.getKmExamTestTypesByTestID(testID);
        request.setAttribute("testTypeList", testTypeList);

        for (Iterator iter = testTypeList.iterator(); iter.hasNext(); ) {
            KmExamTestType kmExamTestType = (KmExamTestType) iter.next();
            String testTypeID = kmExamTestType.getTestTypeId();
            String type = kmExamTestType.getType();
            List contentList = kmExamTestTypeContentMgr.getKmExamTestTypeContentByTestTypeID(testTypeID);
            KmExamTestTypeForm kmExamTestTypeForm = (KmExamTestTypeForm) convert(kmExamTestType);
            request.setAttribute("kmExamTestTypeForm" + type, kmExamTestTypeForm);
            request.setAttribute("contentList" + type, contentList);
            int count = 1;
            List questionlist = new ArrayList();
            List choiceList = new ArrayList();
            List answerList = new ArrayList();
            for (Iterator iter1 = contentList.iterator(); iter1.hasNext(); ) {
                KmExamTestTypeContent kmExamTestTypeContent = (KmExamTestTypeContent) iter1.next();
                String questionID = kmExamTestTypeContent.getQuestionID();
                KmExamQuestions kmExamQuestions = kmExamQuestionsMgr.getKmExamQuestions(questionID);
                KmExamQuestionsForm kmExamQuestionsForm = (KmExamQuestionsForm) convert(kmExamQuestions);
                questionlist.add(kmExamQuestionsForm);
                request.setAttribute("kmExamQuestionsForm" + type + "" + count, kmExamQuestionsForm);
                //用户答题信息 每个问题对应一个答题信息
                KmExamAnswersMgr kmExamAnswersMgr = (KmExamAnswersMgr) getBean("kmExamAnswersMgr");
                KmExamAnswers kmExamAnswers = kmExamAnswersMgr.getKmExamAnswers(testID, questionID, attendUser);
                answerList.add(kmExamAnswers);
                //选项信息  对于选择题之类对应选项信息
                List choiceList1 = kmExamChoiceMgr.getKmExamChoicesByQuestionID(questionID);
                choiceList.addAll(choiceList1);
                count++;
            }
            request.setAttribute("answerList" + type, answerList);
            request.setAttribute("choiceList" + type, choiceList);
            request.setAttribute("questionlist" + type, questionlist);
        }

        return mapping.findForward("attendRead");
    }

    /**
     * 查看待阅卷信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 待阅试卷列表
     * @throws Exception
     */
    public ActionForward searchNoRead(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmExamAttendConstants.KMEXAMATTEND_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmExamAttendMgr kmExamAttendMgr = (KmExamAttendMgr) getBean("kmExamAttendMgr");

        String whereStr = " where kmExamAttend.isRead = '0'";
        Map map = (Map) kmExamAttendMgr.getKmExamAttends(pageIndex, pageSize, whereStr);
        List list = (List) map.get("result");
        request.setAttribute(KmExamAttendConstants.KMEXAMATTEND_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("noRead");
    }


    /**
     * 查看待阅试卷详情
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward attendNoRead(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //试卷的现实查询
        String testID = StaticMethod.null2String(request.getParameter("testID"));
        String attendId = StaticMethod.null2String(request.getParameter("id"));
        String attendUser = StaticMethod.null2String(request.getParameter("attendUser"));
        request.setAttribute("attendUser", attendUser);
        KmExamTestMgr kmExamTestMgr = (KmExamTestMgr) getBean("kmExamTestMgr");
        //当前试卷信息
        KmExamTest kmExamTest = (KmExamTest) kmExamTestMgr.getKmExamTest(testID);
        request.setAttribute("kmExamTest", kmExamTest);

        KmExamTestTypeMgr kmExamTestTypeMgr = (KmExamTestTypeMgr) getBean("kmExamTestTypeMgr");
        KmExamTestTypeContentMgr kmExamTestTypeContentMgr = (KmExamTestTypeContentMgr) getBean("kmExamTestTypeContentMgr");
        KmExamQuestionsMgr kmExamQuestionsMgr = (KmExamQuestionsMgr) getBean("kmExamQuestionsMgr");
        KmExamChoiceMgr kmExamChoiceMgr = (KmExamChoiceMgr) getBean("kmExamChoiceMgr");
        KmExamTestForm kmExamTestForm = (KmExamTestForm) convert(kmExamTest);
        updateFormBean(mapping, request, kmExamTestForm);

        //得到所有的试卷类型
        List testTypeList = kmExamTestTypeMgr.getKmExamTestTypesByTestID(testID);
        request.setAttribute("testTypeList", testTypeList);

        for (Iterator iter = testTypeList.iterator(); iter.hasNext(); ) {
            KmExamTestType kmExamTestType = (KmExamTestType) iter.next();
            String testTypeID = kmExamTestType.getTestTypeId();
            String type = kmExamTestType.getType();
            List contentList = kmExamTestTypeContentMgr.getKmExamTestTypeContentByTestTypeID(testTypeID);
            KmExamTestTypeForm kmExamTestTypeForm = (KmExamTestTypeForm) convert(kmExamTestType);
            request.setAttribute("kmExamTestTypeForm" + type, kmExamTestTypeForm);
            request.setAttribute("contentList" + type, contentList);
            int count = 1;
            List questionlist = new ArrayList();
            List choiceList = new ArrayList();
            List answerList = new ArrayList();
            for (Iterator iter1 = contentList.iterator(); iter1.hasNext(); ) {
                KmExamTestTypeContent kmExamTestTypeContent = (KmExamTestTypeContent) iter1.next();
                String questionID = kmExamTestTypeContent.getQuestionID();
                KmExamQuestions kmExamQuestions = kmExamQuestionsMgr.getKmExamQuestions(questionID);
                KmExamQuestionsForm kmExamQuestionsForm = (KmExamQuestionsForm) convert(kmExamQuestions);
                questionlist.add(kmExamQuestionsForm);
                request.setAttribute("kmExamQuestionsForm" + type + "" + count, kmExamQuestionsForm);
                //用户答题信息 每个问题对应一个答题信息
                KmExamAnswersMgr kmExamAnswersMgr = (KmExamAnswersMgr) getBean("kmExamAnswersMgr");
                KmExamAnswers kmExamAnswers = kmExamAnswersMgr.getKmExamAnswers(testID, questionID, attendUser);
                answerList.add(kmExamAnswers);
                //选项信息  对于选择题之类对应选项信息
                List choiceList1 = kmExamChoiceMgr.getKmExamChoicesByQuestionID(questionID);
                choiceList.addAll(choiceList1);
                count++;
            }
            request.setAttribute("answerList" + type, answerList);
            request.setAttribute("choiceList" + type, choiceList);
            request.setAttribute("questionlist" + type, questionlist);
        }

        return mapping.findForward("attendNoRead");
    }

    /**
     * 保存阅卷成绩（主观题手动阅卷的成绩） 并计算总分
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveRead(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmExamAttendMgr kmExamAttendMgr = (KmExamAttendMgr) getBean("kmExamAttendMgr");
        KmExamTestTypeMgr kmExamTestTypeMgr = (KmExamTestTypeMgr) getBean("kmExamTestTypeMgr");
        KmExamTestTypeContentMgr kmExamTestTypeContentMgr = (KmExamTestTypeContentMgr) getBean("kmExamTestTypeContentMgr");
        KmExamAnswersMgr kmExamAnswersMgr = (KmExamAnswersMgr) getBean("kmExamAnswersMgr");
        //参加考试人
        String attendUser = StaticMethod.null2String(request.getParameter("attendUser"));
        //得到试卷id
        String testID = StaticMethod.null2String(request.getParameter("testID"));
        //查询该试卷下所有的类型
        List kmExamTestTypeList = kmExamTestTypeMgr.getKmExamTestTypesByTestID(testID);
        for (int i = 0; i < kmExamTestTypeList.size(); i++) {
            //得到试题类型信息
            KmExamTestType kmExamTestType = (KmExamTestType) kmExamTestTypeList.get(i);
            String testTypeID = kmExamTestType.getTestTypeId();
            String type = kmExamTestType.getType();
            if (type.equals("4") || type.equals("5")) {
                List kmExamTestTypeContentList = kmExamTestTypeContentMgr.getKmExamTestTypeContentByTestTypeID(testTypeID);
                for (int j = 0; j < kmExamTestTypeContentList.size(); j++) {
                    KmExamTestTypeContent kmExamTestTypeContent = (KmExamTestTypeContent) kmExamTestTypeContentList.get(j);
                    String questionID = kmExamTestTypeContent.getQuestionID();
                    //得到所用主观题判分的数组
                    String score = StaticMethod.null2String(request.getParameter(questionID));
                    KmExamAnswers kmExamAnswers = kmExamAnswersMgr.getKmExamAnswers(testID, questionID, attendUser);
                    kmExamAnswers.setScore(score);
                    //保存用户所写的答案
                    kmExamAnswersMgr.saveKmExamAnswers(kmExamAnswers);
                }
            }
        }

        //总分计算
        //totalScore该人该套试卷的总成绩
        String totalScore = kmExamAnswersMgr.getSumScore(testID, attendUser);
        //阅卷人
        String readUser = this.getUser(request).getUserid();
        String readDept = this.getUser(request).getDeptid();
        //保存总成绩
        KmExamAttend kmExamAttend = kmExamAttendMgr.getKmExamAttend(testID, attendUser);
        kmExamAttend.setReadUser(readUser);
        kmExamAttend.setReadDept(readDept);
        kmExamAttend.setScore(totalScore);
        kmExamAttend.setIsRead("1");
        kmExamAttendMgr.saveKmExamAttend(kmExamAttend);
        return mapping.findForward("success");
    }

    /**
     * 发布阅卷完的试卷
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward isPublic(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmExamAttendMgr kmExamAttendMgr = (KmExamAttendMgr) getBean("kmExamAttendMgr");
        String testID = StaticMethod.null2String(request.getParameter("testID"));
        String attendUser = StaticMethod.null2String(request.getParameter("attendUser"));
        ;

        KmExamAttend kmExamAttend = kmExamAttendMgr.getKmExamAttend(testID, attendUser);
        kmExamAttend.setIsPublic("1");
        kmExamAttendMgr.saveKmExamAttend(kmExamAttend);
        return mapping.findForward("success");
    }

    /**
     * 查询考试结果
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchIsPublic(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmExamAttendConstants.KMEXAMATTEND_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        //当前查询人信息
        String userId = this.getUser(request).getUserid();
        String attendUser = userId;
        String isPublic = "1";
        KmExamAttendMgr kmExamAttendMgr = (KmExamAttendMgr) getBean("kmExamAttendMgr");
        //查询当前登陆人 已经发布的考试结果
        List list = kmExamAttendMgr.getKmExamAttends(attendUser, isPublic);
        request.setAttribute(KmExamAttendConstants.KMEXAMATTEND_LIST, list);
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("listIsPublic");
    }

    /**
     * 删除考试信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward remove(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmExamAttendMgr kmExamAttendMgr = (KmExamAttendMgr) getBean("kmExamAttendMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        kmExamAttendMgr.removeKmExamAttend(id);
        return search(mapping, form, request, response);
    }

    /**
     * 分页显示试卷列表 考试信息列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmExamAttendRecordMgr kmExamAttendRecordMgr = (KmExamAttendRecordMgr) getBean("kmExamAttendRecordMgr");
        String userId = this.getUser(request).getUserid();
        String ipAddress = request.getRemoteAddr();
        KmExamAttendRecord kmExamAttendRecord = kmExamAttendRecordMgr.getKmExamAttendRecordByUser(userId, ipAddress);
        if (kmExamAttendRecord != null) {
            return mapping.findForward("fail3");
        }

        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmExamTestConstants.KMEXAMTEST_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmExamTestMgr kmExamTestMgr = (KmExamTestMgr) getBean("kmExamTestMgr");
        KmExamAttendMgr kmExamAttendMgr = (KmExamAttendMgr) getBean("kmExamAttendMgr");
        List list = kmExamTestMgr.getKmExamPublicTests();
        //list1用于存放有效考试信息
        List list1 = new ArrayList();
        //取出已经参加过的考试和时间超时，时间还没有到的考试信息
        for (int i = 0; i < list.size(); i++) {
            KmExamTest kmExamTest = (KmExamTest) list.get(i);
            String testID = kmExamTest.getTestID();
            String attendUser = this.getUser(request).getUserid();
            //用来判断是否已经参加过考试了
            String id = kmExamAttendMgr.getKmExamAttend(testID, attendUser).getId();
            //得到该试卷的有效时间
            Date testBeginDate = kmExamTest.getTestBeginTime();
            Date testEndTime = kmExamTest.getTestEndTime();
            //当前用户进入时间
            Date date = StaticMethod.getLocalTime();
            if (id == null && date.after(testBeginDate) && date.before(testEndTime)) {
                list1.add(kmExamTest);
            }
        }

        request.setAttribute(KmExamTestConstants.KMEXAMTEST_LIST, list1);
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    /**
     * 查询已参加过的考试列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchForAttend(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmExamTestConstants.KMEXAMTEST_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmExamTestMgr kmExamTestMgr = (KmExamTestMgr) getBean("kmExamTestMgr");
        KmExamAttendMgr kmExamAttendMgr = (KmExamAttendMgr) getBean("kmExamAttendMgr");
        List list = kmExamTestMgr.getKmExamPublicTests();
        //list1用于存放有效考试信息
        List list1 = new ArrayList();
        //取出已经参加过的考试和时间超时，时间还没有到的考试信息
        for (int i = 0; i < list.size(); i++) {
            KmExamTest kmExamTest = (KmExamTest) list.get(i);
            String testID = kmExamTest.getTestID();
            String attendUser = this.getUser(request).getUserid();
            //用来判断是否已经参加过考试了
            String id = kmExamAttendMgr.getKmExamAttend(testID, attendUser).getId();
            if (id != null) {
                list1.add(kmExamTest);
            }
        }

        request.setAttribute(KmExamTestConstants.KMEXAMTEST_LIST, list1);
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    /**
     * 统计考试成绩
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward report(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmExamAttendConstants.KMEXAMATTEND_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmExamAttendMgr kmExamAttendMgr = (KmExamAttendMgr) getBean("kmExamAttendMgr");
        String whereStr = " where kmExamAttend.isRead = '1'";
        Map map = (Map) kmExamAttendMgr.getKmExamAttends(pageIndex, pageSize, whereStr);
        List list = (List) map.get("result");
        request.setAttribute(KmExamAttendConstants.KMEXAMATTEND_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);

        //导出excel
        String flagexcel = StaticMethod.nullObject2String(request.getParameter("flagexcel"));
        String url = request.getContextPath() + "/kmmanager/kmExamAttends.do?method=report" + "&flagexcel=true";
        request.setAttribute("excelUrl", url);
        if (flagexcel.equals("true")) {
            List contentList = (List) map.get("result");
            int listSize = contentList.size();
            TawSystemDeptDaoHibernate tawSystemDeptDao = (TawSystemDeptDaoHibernate) getBean("tawSystemDeptDao");
            KmExamTestDaoHibernate kmExamTestDao = (KmExamTestDaoHibernate) getBean("kmExamTestDao");
            TawSystemUserDaoHibernate tawSystemUserDao = (TawSystemUserDaoHibernate) getBean("tawSystemUserDao");
            for (int i = 0; i < listSize; i++) {
                KmExamAttend kmExamAttend = (KmExamAttend) contentList.get(i);
                kmExamAttend.setDeptName(tawSystemDeptDao.id2Name(kmExamAttend.getAttendDept()));
                kmExamAttend.setTestName(kmExamTestDao.id2Name(kmExamAttend.getTestId()));
                kmExamAttend.setUserName(tawSystemUserDao.id2Name(kmExamAttend.getAttendUser()));
            }
            String configPath = "";
            try {
                configPath = PersonalApplyStatisticAction.class.getResource("/").toString();
                configPath = configPath.substring(5) + "com/boco/eoms/km/config/";
            } catch (Exception e) {
                e.printStackTrace();
            }
            com.boco.eoms.km.excelmanage.PoiExcel poiExcel = new com.boco.eoms.km.excelmanage.PoiExcel(configPath);
            String path = poiExcel.getPoiExcel("kmExamAttendsReport", contentList);
            request.setAttribute("excelfile", path);
            request.setAttribute("excelfilename", path.substring(path
                    .lastIndexOf(File.separator) + 1, path.length()));
            return mapping.findForward("excelJsp");
        }

        return mapping.findForward("report");
    }

    /**
     * 根据查询条件分页显示模块信息表列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchX(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmExamAttendConstants.KMEXAMATTEND_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmExamAttendForm kmExamAttendForm = (KmExamAttendForm) form;

        String whereStr = " where kmExamAttend.isRead = '1'";
        if (kmExamAttendForm.getAttendUser() != null && !("").equals(kmExamAttendForm.getAttendUser())) {
            whereStr += " and kmExamAttend.attendUser='" + kmExamAttendForm.getAttendUser() + "'";
        }
        if (kmExamAttendForm.getTestId() != null && !("").equals(kmExamAttendForm.getTestId())) {
            whereStr += " and kmExamAttend.testId= '" + kmExamAttendForm.getTestId() + "'";
        }

        // whereStr+=" and kmExamAttend.isDeleted='0'";
        KmExamAttendMgr kmExamAttendMgr = (KmExamAttendMgr) getBean("kmExamAttendMgr");
        Map map = (Map) kmExamAttendMgr.getKmExamAttends(pageIndex, pageSize, whereStr);
        List list = (List) map.get("result");
        request.setAttribute(KmExamAttendConstants.KMEXAMATTEND_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("kmExamAttendForm", kmExamAttendForm);
        return mapping.findForward("report");
    }
}
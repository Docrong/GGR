package com.boco.eoms.km.ask.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.abdera.Abdera;
import org.apache.abdera.factory.Factory;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.model.Person;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.ask.mgr.KmAskCountScoreMgr;
import com.boco.eoms.km.ask.mgr.KmAskQuestionMgr;
import com.boco.eoms.km.ask.mgr.KmAskReplyMgr;
import com.boco.eoms.km.ask.mgr.KmAskVoteMgr;
import com.boco.eoms.km.ask.model.KmAskCountScore;
import com.boco.eoms.km.ask.model.KmAskQuestion;
import com.boco.eoms.km.ask.model.KmAskReply;
import com.boco.eoms.km.ask.model.KmAskVote;
import com.boco.eoms.km.ask.util.KmAskOperate;
import com.boco.eoms.km.ask.util.KmAskQuestionConstants;
import com.boco.eoms.km.ask.webapp.form.KmAskQuestionForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;

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
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 */
public final class KmAskQuestionAction extends BaseAction {

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
     * 问答类型树页面
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward tree(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("tree");
    }

    /**
     * 新增问题
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmAskQuestionConstants.KMASKQUESTION_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) getBean("kmAskQuestionMgr");
        KmAskCountScoreMgr kmAskCountScoreMgr = (KmAskCountScoreMgr) getBean("kmAskCountScoreMgr");
        String name = StaticMethod.null2String(request.getParameter("name"));

        String questionStatus = StaticMethod.null2String(request.getParameter("questionStatus"));
        String whereStr = " where kmAskQuestion.questionStatus = '" + questionStatus + "' and kmAskQuestion.name like '%" + name + "%'";
        Map map = (Map) kmAskQuestionMgr.getKmAskQuestions(pageIndex, pageSize, whereStr);
        List list = (List) map.get("result");
        request.setAttribute(KmAskQuestionConstants.KMASKQUESTION_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);

        //查询当前用户积分情况
        String userName = this.getUser(request).getUserid();
        KmAskCountScore kmAskCountScore = kmAskCountScoreMgr.getKmAskCountScoreByUser(userName);
        Integer countScore = kmAskCountScore.getCountScore();
        if (countScore == null) {
            countScore = new Integer(0);
        }
        request.setAttribute("countScore", countScore);
        return mapping.findForward("add");
    }

    /**
     * 修改问题
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) getBean("kmAskQuestionMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        KmAskQuestion kmAskQuestion = kmAskQuestionMgr.getKmAskQuestion(id);
        KmAskQuestionForm kmAskQuestionForm = (KmAskQuestionForm) convert(kmAskQuestion);
        updateFormBean(mapping, request, kmAskQuestionForm);
        return mapping.findForward("edit");
    }

    /**
     * 保存问题
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
        KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) getBean("kmAskQuestionMgr");
        KmAskCountScoreMgr kmAskCountScoreMgr = (KmAskCountScoreMgr) getBean("kmAskCountScoreMgr");
        KmAskQuestionForm kmAskQuestionForm = (KmAskQuestionForm) form;
        String userid = this.getUser(request).getUserid();
        String deptid = this.getUser(request).getDeptid();
        boolean isNew = (null == kmAskQuestionForm.getId() || "".equals(kmAskQuestionForm.getId()));
        KmAskQuestion kmAskQuestion = (KmAskQuestion) convert(kmAskQuestionForm);
        if (kmAskQuestion.getScore() == null) {
            kmAskQuestion.setScore(new Integer(0));
        }
        kmAskQuestion.setAskUser(userid);
        kmAskQuestion.setAskDept(deptid);
        kmAskQuestion.setAskTime(StaticMethod.getLocalTime());
        kmAskQuestion.setAnswerCount(new Integer(0));
        kmAskQuestion.setQuestionStatus("0");
        kmAskQuestion.setIsClosed("0");

        //悬赏分
        Integer score = kmAskQuestion.getScore();
        Integer countScore = null;
        KmAskCountScore kmAskCountScore = kmAskCountScoreMgr.getKmAskCountScoreByUser(userid);
        if (kmAskCountScore.getCountScore() != null) {
            countScore = new Integer(kmAskCountScore.getCountScore().intValue() - score.intValue());
            kmAskCountScore.setCountScore(countScore);
            kmAskCountScoreMgr.saveKmAskCountScore(kmAskCountScore);
        }

        //操作用户总积分
        Integer opereteId = KmAskOperate.KM_OPERATE_NAME_ASK_ADD;
        kmAskCountScoreMgr.saveKmAskCountScore(userid, deptid, opereteId);

        if (isNew) {
            kmAskQuestionMgr.saveKmAskQuestion(kmAskQuestion);
        } else {
            kmAskQuestionMgr.saveKmAskQuestion(kmAskQuestion);
        }
        return search(mapping, kmAskQuestionForm, request, response);
    }

    /**
     * 提问者对最佳答案的评论
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward comment(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) getBean("kmAskQuestionMgr");
        KmAskQuestionForm kmAskQuestionForm = (KmAskQuestionForm) form;
        boolean isNew = (null == kmAskQuestionForm.getId() || "".equals(kmAskQuestionForm.getId()));
        KmAskQuestion kmAskQuestion = (KmAskQuestion) convert(kmAskQuestionForm);
        if ("".equals(kmAskQuestion.getScore())) {
            kmAskQuestion.setScore(new Integer(0));
        }
        kmAskQuestion.setAskUser(this.getUser(request).getUserid());
        kmAskQuestion.setAskDept(this.getUser(request).getDeptid());
        kmAskQuestion.setAskTime(StaticMethod.getLocalTime());
        kmAskQuestion.setAnswerCount(new Integer(0));
        kmAskQuestion.setQuestionStatus("0");
        kmAskQuestion.setIsClosed("0");
        if (isNew) {
            kmAskQuestionMgr.saveKmAskQuestion(kmAskQuestion);
        } else {
            kmAskQuestionMgr.saveKmAskQuestion(kmAskQuestion);
        }
        return search(mapping, kmAskQuestionForm, request, response);
    }


    /**
     * 进入回答问题,投票,己解决等的页面
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
        KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) getBean("kmAskQuestionMgr");
        KmAskReplyMgr kmAskReplyMgr = (KmAskReplyMgr) getBean("kmAskReplyMgr");
        KmAskVoteMgr kmAskVoteMgr = (KmAskVoteMgr) getBean("kmAskVoteMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        request.setAttribute("questionId", id);
        List kmAskReplyList = kmAskReplyMgr.getKmAskReplysByQuestionId(id);
        request.setAttribute("replaySize", new Integer(kmAskReplyList.size()));
        request.setAttribute("kmAskReplyList", kmAskReplyList);

        KmAskQuestion kmAskQuestion = kmAskQuestionMgr.getKmAskQuestion(id);
        KmAskQuestionForm kmAskQuestionForm = (KmAskQuestionForm) convert(kmAskQuestion);
        updateFormBean(mapping, request, kmAskQuestionForm);
        if ("0".equals(kmAskQuestion.getQuestionStatus())) {
            return mapping.findForward("attendReply");
        } else if ("1".equals(kmAskQuestion.getQuestionStatus())) {
            return mapping.findForward("attendSolve");
        }
        return mapping.findForward("attendVote");
    }

    /**
     * 将问题设为投票状态
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward setVote(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) getBean("kmAskQuestionMgr");
        String questionId = StaticMethod.null2String(request.getParameter("questionId"));
        String questionStatus = StaticMethod.null2String(request.getParameter("questionStatus"));
        KmAskQuestion kmAskQuestion = kmAskQuestionMgr.getKmAskQuestion(questionId);
        kmAskQuestion.setQuestionStatus(questionStatus);
        kmAskQuestionMgr.saveKmAskQuestion(kmAskQuestion);
        return mapping.findForward("success");
    }

    /**
     * 进行投票
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward vote(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) getBean("kmAskQuestionMgr");
        KmAskReplyMgr kmAskReplyMgr = (KmAskReplyMgr) getBean("kmAskReplyMgr");
        KmAskVoteMgr kmAskVoteMgr = (KmAskVoteMgr) getBean("kmAskVoteMgr");
        KmAskCountScoreMgr kmAskCountScoreMgr = (KmAskCountScoreMgr) getBean("kmAskCountScoreMgr");
        //当前登陆人
        String userId = this.getUser(request).getUserid();
        String deptId = this.getUser(request).getDeptid();
        //答案id
        String replyId = StaticMethod.null2String(request.getParameter("replyId"));
        KmAskReply kmAskReply = kmAskReplyMgr.getKmAskReply(replyId);
        //问题id
        String questionId = StaticMethod.null2String(request.getParameter("questionId"));
        //该用户是否已经投票了
        KmAskVote kmAskVote = kmAskVoteMgr.getKmAskVote(questionId, userId);
        //flag为null的话 说明该用户没有透过票  还可以投票
        String flag = kmAskVote.getId();
        request.setAttribute("flag", flag);
        //如果该用户对这个问题还没有投票  就可以进行投票
        if (flag == null) {
            //最新的总投票数
            Integer countVote = new Integer(kmAskReply.getCountVote().intValue() + 1);
            kmAskReply.setCountVote(countVote);
            kmAskReplyMgr.saveKmAskReply(kmAskReply);
            //记录投票信息
            kmAskVote.setQuestionId(questionId);
            kmAskVote.setVoteDate(StaticMethod.getLocalTime());
            kmAskVote.setVoteUser(userId);
            kmAskVote.setVoteDept(deptId);
            kmAskVote.setReplyId(replyId);
            kmAskVoteMgr.saveKmAskVote(kmAskVote);
            request.setAttribute("countVote", countVote);

            //操作用户总积分
            Integer opereteId = KmAskOperate.KM_OPERATE_NAME_ASK_VOTE;
            kmAskCountScoreMgr.saveKmAskCountScore(userId, deptId, opereteId);
        } else {
            Integer countVote = kmAskReply.getCountVote();
            request.setAttribute("countVote", countVote);
        }
        return mapping.findForward("voteDetail");
    }

//	 /**
//	  * 查看已解决的问题的详情
//	  * @param mapping
//	  * @param form
//	  * @param request
//	  * @param response
//	  * @return
//	  * @throws Exception
//	  */
//	 public ActionForward attendSolve(ActionMapping mapping, ActionForm form,
//		HttpServletRequest request, HttpServletResponse response)
//					throws Exception {
//		KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) getBean("kmAskQuestionMgr");
//		KmAskReplyMgr kmAskReplyMgr = (KmAskReplyMgr) getBean("kmAskReplyMgr");
//		String id = StaticMethod.null2String(request.getParameter("id"));
//		request.setAttribute("questionId", id);
//		List kmAskReplyList = kmAskReplyMgr.getKmAskReplysByQuestionId(id);
//		request.setAttribute("replaySize", new Integer(kmAskReplyList.size()));
//		request.setAttribute("kmAskReplyList", kmAskReplyList);
//		
//		KmAskQuestion kmAskQuestion = kmAskQuestionMgr.getKmAskQuestion(id);
//		KmAskQuestionForm kmAskQuestionForm = (KmAskQuestionForm) convert(kmAskQuestion);
//		updateFormBean(mapping, request, kmAskQuestionForm);
//		return mapping.findForward("attendSolve");
//	}	 
//	 
//	 /**
//	  * 进入投票的问题界面
//	  * @param mapping
//	  * @param form
//	  * @param request
//	  * @param response
//	  * @return
//	  * @throws Exception
//	  */
//	 public ActionForward attendVote(ActionMapping mapping, ActionForm form,
//		HttpServletRequest request, HttpServletResponse response)
//					throws Exception {
//		KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) getBean("kmAskQuestionMgr");
//		KmAskReplyMgr kmAskReplyMgr = (KmAskReplyMgr) getBean("kmAskReplyMgr");
//		String id = StaticMethod.null2String(request.getParameter("id"));
//		request.setAttribute("questionId", id);
//		List kmAskReplyList = kmAskReplyMgr.getKmAskReplysByQuestionId(id);
//		request.setAttribute("replaySize", new Integer(kmAskReplyList.size()));
//		request.setAttribute("kmAskReplyList", kmAskReplyList);
//				
//		KmAskQuestion kmAskQuestion = kmAskQuestionMgr.getKmAskQuestion(id);
//		KmAskQuestionForm kmAskQuestionForm = (KmAskQuestionForm) convert(kmAskQuestion);
//		updateFormBean(mapping, request, kmAskQuestionForm);
//		return mapping.findForward("attendVote");
//	}	 

    /**
     * 删除问题
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
        KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) getBean("kmAskQuestionMgr");
        String id = StaticMethod.null2String(request.getParameter("id"));
        kmAskQuestionMgr.removeKmAskQuestion(id);
        return search(mapping, form, request, response);
    }

    /**
     * 分页显示问题列表
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
//		String pageIndexName = new org.displaytag.util.ParamEncoder(
//				KmAskQuestionConstants.KMASKQUESTION_LIST)
//				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
////		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
//				.getPageSize();
//		final Integer pageIndex = new Integer(GenericValidator
//				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
//				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        String nodeId = StaticMethod.null2String(request.getParameter("node"));
        KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) getBean("kmAskQuestionMgr");

        //根据节点(分类)查询知识问题列表  0:待解决 1：己解决 2：投票中
        String questionStatus0 = "0";
        String questionStatus1 = "1";
        String questionStatus2 = "2";
        List kmAskQuestionList0 = kmAskQuestionMgr.getKmAskQuestions(nodeId, questionStatus0);
        List kmAskQuestionList1 = kmAskQuestionMgr.getKmAskQuestions(nodeId, questionStatus1);
        List kmAskQuestionList2 = kmAskQuestionMgr.getKmAskQuestions(nodeId, questionStatus2);

        request.setAttribute("kmAskQuestionList0", kmAskQuestionList0);
        request.setAttribute("kmAskQuestionList1", kmAskQuestionList1);
        request.setAttribute("kmAskQuestionList2", kmAskQuestionList2);

        request.setAttribute("size0", new Integer(kmAskQuestionList0.size()));
        request.setAttribute("size1", new Integer(kmAskQuestionList1.size()));
        request.setAttribute("size2", new Integer(kmAskQuestionList2.size()));
        return mapping.findForward("listAll");
    }

    /**
     * 根据问题状态查询
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchByQuestionStatus(ActionMapping mapping, ActionForm form,
                                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmAskQuestionConstants.KMASKQUESTION_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) getBean("kmAskQuestionMgr");
        String questionStatus = StaticMethod.null2String(request.getParameter("questionStatus"));
        String whereStr = " where kmAskQuestion.questionStatus = '" + questionStatus + "'";
        Map map = (Map) kmAskQuestionMgr.getKmAskQuestions(pageIndex, pageSize, whereStr);
        List list = (List) map.get("result");
        request.setAttribute(KmAskQuestionConstants.KMASKQUESTION_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    /**
     * 查询与问题标题想干的问题(模糊查询)
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchByName(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmAskQuestionConstants.KMASKQUESTION_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) getBean("kmAskQuestionMgr");
        String name = StaticMethod.null2String(request.getParameter("name"));
        String questionStatus = StaticMethod.null2String(request.getParameter("questionStatus"));
        String whereStr = " where kmAskQuestion.questionStatus = '" + questionStatus + "' and kmAskQuestion.name like '%" + name + "%'";
        Map map = (Map) kmAskQuestionMgr.getKmAskQuestions(pageIndex, pageSize, whereStr);
        List list = (List) map.get("result");
        request.setAttribute(KmAskQuestionConstants.KMASKQUESTION_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    /**
     * 查询自己的所提的问题
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchMyQuestion(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmAskQuestionConstants.KMASKQUESTION_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) getBean("kmAskQuestionMgr");
        String askUser = this.getUser(request).getUserid();
        String whereStr = " where kmAskQuestion.askUser = '" + askUser + "'";
        Map map = (Map) kmAskQuestionMgr.getKmAskQuestions(pageIndex, pageSize, whereStr);
        List list = (List) map.get("result");
        request.setAttribute(KmAskQuestionConstants.KMASKQUESTION_LIST, list);
        request.setAttribute("resultSize", map.get("total"));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    /**
     * 查询自己回答的问题
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward searchMyReply(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String pageIndexName = new org.displaytag.util.ParamEncoder(
                KmAskQuestionConstants.KMASKQUESTION_LIST)
                .encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
        final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
                .getPageSize();
        final Integer pageIndex = new Integer(GenericValidator
                .isBlankOrNull(request.getParameter(pageIndexName)) ? 0
                : (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
        KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) getBean("kmAskQuestionMgr");
        String userId = this.getUser(request).getUserid();
        List list = kmAskQuestionMgr.getKmAskQuestionByMyReply(userId);
        request.setAttribute(KmAskQuestionConstants.KMASKQUESTION_LIST, list);
        request.setAttribute("resultSize", new Integer(list.size()));
        request.setAttribute("pageSize", pageSize);
        return mapping.findForward("list");
    }

    /**
     * 分页显示问题列表，支持Atom方式接入Portal
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
//	public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//		try {
//			// --------------用于分页，得到当前页号-------------
//			final Integer pageIndex = new Integer(request
//					.getParameter("pageIndex"));
//			final Integer pageSize = new Integer(request
//					.getParameter("pageSize"));
//			KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) getBean("kmAskQuestionMgr");
//			Map map = (Map) kmAskQuestionMgr.getKmAskQuestions(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			KmAskQuestion kmAskQuestion = new KmAskQuestion();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				kmAskQuestion = (KmAskQuestion) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/kmAskQuestion/kmAskQuestions.do?method=edit&id="
//						+ kmAskQuestion.getId() + "' target='_blank'>"
//						+ display name for list + "</a>");
//				entry.setSummary(summary);
//				entry.setContent(content);
//				entry.setLanguage(language);
//				entry.setText(text);
//				entry.setRights(tights);
//				
//				// 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数
//				entry.setUpdated(new java.util.Date());
//				entry.setPublished(new java.util.Date());
//				entry.setEdited(new java.util.Date());
//				
//				// 为person的name属性赋值，entry.addAuthor可以随意赋值
//				Person person = entry.addAuthor(userId);
//				person.setName(userName);
//			}
//			
//			// 每页显示条数
//			feed.setText(map.get("total").toString());
//		    OutputStream os = response.getOutputStream();
//		    PrintStream ps = new PrintStream(os);
//		    feed.getDocument().writeTo(ps);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}
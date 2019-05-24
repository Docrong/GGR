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
import com.boco.eoms.km.ask.model.KmAskCountScore;
import com.boco.eoms.km.ask.model.KmAskQuestion;
import com.boco.eoms.km.ask.model.KmAskReply;
import com.boco.eoms.km.ask.util.KmAskOperate;
import com.boco.eoms.km.ask.util.KmAskReplyConstants;
import com.boco.eoms.km.ask.webapp.form.KmAskReplyForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:答题
 * </p>
 * <p>
 * Description:回答答案
 * </p>
 * <p>
 * Tue Aug 04 15:52:08 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 * 
 */
public final class KmAskReplyAction extends BaseAction {
 
	/**
	 * 未指定方法时默认调用的方法
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
	 * 新增答题
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
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改答题
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
		KmAskReplyMgr kmAskReplyMgr = (KmAskReplyMgr) getBean("kmAskReplyMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		KmAskReply kmAskReply = kmAskReplyMgr.getKmAskReply(id);
		KmAskReplyForm kmAskReplyForm = (KmAskReplyForm) convert(kmAskReply);
		updateFormBean(mapping, request, kmAskReplyForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存答题
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
		KmAskReplyMgr kmAskReplyMgr = (KmAskReplyMgr) getBean("kmAskReplyMgr");
		KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) getBean("kmAskQuestionMgr");
		KmAskCountScoreMgr kmAskCountScoreMgr = (KmAskCountScoreMgr) getBean("kmAskCountScoreMgr");
		KmAskReplyForm kmAskReplyForm = (KmAskReplyForm) form;
		boolean isNew = (null == kmAskReplyForm.getId() || "".equals(kmAskReplyForm.getId()));
		KmAskReply kmAskReply = (KmAskReply) convert(kmAskReplyForm); 
		String userId = this.getUser(request).getUserid();
		String deptId= this.getUser(request).getDeptid();
		kmAskReply.setAnswerUser(userId);
		kmAskReply.setAnswerDept(deptId);
		kmAskReply.setAnswerTime(StaticMethod.getLocalTime());
		kmAskReply.setIsBest("0"); 
		//增加答案的时候默认将 所得的投票总数即为0
		kmAskReply.setCountVote(new Integer(0));
		if (isNew) {
			kmAskReplyMgr.saveKmAskReply(kmAskReply);
			
			//将回答总数+1
			KmAskQuestion kmAskQuestion = kmAskQuestionMgr.getKmAskQuestion(kmAskReply.getQuestionId());
			kmAskQuestion.setAnswerCount(new Integer(kmAskQuestion.getAnswerCount().intValue()+1));
			kmAskQuestionMgr.saveKmAskQuestion(kmAskQuestion);
		
			//操作用户总积分
			Integer opereteId = KmAskOperate.KM_OPERATE_NAME_ASK_COMMENT;
			kmAskCountScoreMgr.saveKmAskCountScore(userId, deptId, opereteId);
		} else {
			kmAskReplyMgr.saveKmAskReply(kmAskReply);
		}
		return mapping.findForward("success");
	}
	
	/**
	 * 设为最佳答案
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setBest(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KmAskReplyMgr kmAskReplyMgr = (KmAskReplyMgr) getBean("kmAskReplyMgr");
		KmAskQuestionMgr kmAskQuestionMgr = (KmAskQuestionMgr) getBean("kmAskQuestionMgr");
		KmAskCountScoreMgr kmAskCountScoreMgr =(KmAskCountScoreMgr) getBean("kmAskCountScoreMgr");
		KmAskReplyForm kmAskReplyForm = (KmAskReplyForm) form;
		boolean isNew = (null == kmAskReplyForm.getId() || "".equals(kmAskReplyForm.getId()));
		KmAskReply kmAskReply = kmAskReplyMgr.getKmAskReply(kmAskReplyForm.getId());
		
		//将问题状态改为已解决
		KmAskQuestion kmAskQuestion = kmAskQuestionMgr.getKmAskQuestion(kmAskReply.getQuestionId());
		kmAskQuestion.setQuestionStatus("1");
		kmAskQuestionMgr.saveKmAskQuestion(kmAskQuestion);
		//该题目的悬赏分
		Integer score = kmAskQuestion.getScore();
		//回答者
		String replyUser = kmAskReply.getAnswerUser();
		//最佳答案者 当前的积分详情
		KmAskCountScore kmAskCountScore = kmAskCountScoreMgr.getKmAskCountScoreByUser(replyUser);
		//增加积分
		Integer countScore = new Integer(kmAskCountScore.getCountScore().intValue()+score.intValue());
		kmAskCountScore.setCountScore(countScore);
		kmAskCountScoreMgr.saveKmAskCountScore(kmAskCountScore);
		kmAskReply.setIsBest("1"); 
		if (isNew) {
			kmAskReplyMgr.saveKmAskReply(kmAskReply);
		} else {
			kmAskReplyMgr.saveKmAskReply(kmAskReply);
		}
		return mapping.findForward("success");
	}
	
	/**
	 * 删除答题
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
		KmAskReplyMgr kmAskReplyMgr = (KmAskReplyMgr) getBean("kmAskReplyMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		kmAskReplyMgr.removeKmAskReply(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示答题列表
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
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				KmAskReplyConstants.KMASKREPLY_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		KmAskReplyMgr kmAskReplyMgr = (KmAskReplyMgr) getBean("kmAskReplyMgr");
		Map map = (Map) kmAskReplyMgr.getKmAskReplys(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(KmAskReplyConstants.KMASKREPLY_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示答题列表，支持Atom方式接入Portal
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
//			KmAskReplyMgr kmAskReplyMgr = (KmAskReplyMgr) getBean("kmAskReplyMgr");
//			Map map = (Map) kmAskReplyMgr.getKmAskReplys(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			KmAskReply kmAskReply = new KmAskReply();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				kmAskReply = (KmAskReply) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/kmAskReply/kmAskReplys.do?method=edit&id="
//						+ kmAskReply.getId() + "' target='_blank'>"
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
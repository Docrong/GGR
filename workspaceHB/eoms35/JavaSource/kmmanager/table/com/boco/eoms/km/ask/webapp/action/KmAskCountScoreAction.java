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
import com.boco.eoms.km.ask.model.KmAskCountScore;
import com.boco.eoms.km.ask.util.KmAskCountScoreConstants;
import com.boco.eoms.km.ask.webapp.form.KmAskCountScoreForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:问答总积分
 * </p>
 * <p>
 * Description:问答总积分
 * </p>
 * <p>
 * Wed Aug 19 10:09:58 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 * 
 */
public final class KmAskCountScoreAction extends BaseAction {
 
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
	 * 新增问答总积分
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
	 * 修改问答总积分
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
		KmAskCountScoreMgr kmAskCountScoreMgr = (KmAskCountScoreMgr) getBean("kmAskCountScoreMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		KmAskCountScore kmAskCountScore = kmAskCountScoreMgr.getKmAskCountScore(id);
		KmAskCountScoreForm kmAskCountScoreForm = (KmAskCountScoreForm) convert(kmAskCountScore);
		updateFormBean(mapping, request, kmAskCountScoreForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存问答总积分
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
		KmAskCountScoreMgr kmAskCountScoreMgr = (KmAskCountScoreMgr) getBean("kmAskCountScoreMgr");
		KmAskCountScoreForm kmAskCountScoreForm = (KmAskCountScoreForm) form;
		boolean isNew = (null == kmAskCountScoreForm.getId() || "".equals(kmAskCountScoreForm.getId()));
		KmAskCountScore kmAskCountScore = (KmAskCountScore) convert(kmAskCountScoreForm);
		if (isNew) {
			kmAskCountScoreMgr.saveKmAskCountScore(kmAskCountScore);
		} else {
			kmAskCountScoreMgr.saveKmAskCountScore(kmAskCountScore);
		}
		return search(mapping, kmAskCountScoreForm, request, response);
	}
	
	/**
	 * 删除问答总积分
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
		KmAskCountScoreMgr kmAskCountScoreMgr = (KmAskCountScoreMgr) getBean("kmAskCountScoreMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		kmAskCountScoreMgr.removeKmAskCountScore(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示问答总积分列表
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
				KmAskCountScoreConstants.KMASKCOUNTSCORE_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		KmAskCountScoreMgr kmAskCountScoreMgr = (KmAskCountScoreMgr) getBean("kmAskCountScoreMgr");
		List list = kmAskCountScoreMgr.getKmAskCountScores();
		request.setAttribute(KmAskCountScoreConstants.KMASKCOUNTSCORE_LIST, list);
		request.setAttribute("resultSize", new Integer(list.size()));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示问答总积分列表，支持Atom方式接入Portal
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
//			KmAskCountScoreMgr kmAskCountScoreMgr = (KmAskCountScoreMgr) getBean("kmAskCountScoreMgr");
//			Map map = (Map) kmAskCountScoreMgr.getKmAskCountScores(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			KmAskCountScore kmAskCountScore = new KmAskCountScore();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				kmAskCountScore = (KmAskCountScore) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/kmAskCountScore/kmAskCountScores.do?method=edit&id="
//						+ kmAskCountScore.getId() + "' target='_blank'>"
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
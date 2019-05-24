package com.boco.eoms.km.statics.webapp.action;

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
import com.boco.eoms.km.statics.mgr.KmOperateScoreMgr;
import com.boco.eoms.km.statics.model.KmOperateScore;
import com.boco.eoms.km.statics.util.KmOperateScoreConstants;
import com.boco.eoms.km.statics.webapp.form.KmOperateScoreForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:操作积分定义表
 * </p>
 * <p>
 * Description:操作积分定义表
 * </p>
 * <p>
 * Tue Mar 24 16:04:02 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() ljt
 * @moudle.getVersion() 0.1
 * 
 */
public final class KmOperateScoreAction extends BaseAction {
 
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
	 * 新增操作积分定义表
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
	 * 修改操作积分定义表
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
		KmOperateScoreMgr kmOperateScoreMgr = (KmOperateScoreMgr) getBean("kmOperateScoreMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		KmOperateScore kmOperateScore = kmOperateScoreMgr.getKmOperateScore(id);
		KmOperateScoreForm kmOperateScoreForm = (KmOperateScoreForm) convert(kmOperateScore);
		updateFormBean(mapping, request, kmOperateScoreForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存操作积分定义表
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
		KmOperateScoreMgr kmOperateScoreMgr = (KmOperateScoreMgr) getBean("kmOperateScoreMgr");
		KmOperateScoreForm kmOperateScoreForm = (KmOperateScoreForm) form;
		boolean isNew = (null == kmOperateScoreForm.getId() || "".equals(kmOperateScoreForm.getId()));
		KmOperateScore kmOperateScore = (KmOperateScore) convert(kmOperateScoreForm);
		if (isNew) {
			kmOperateScoreMgr.saveKmOperateScore(kmOperateScore);
		} else {
			kmOperateScoreMgr.saveKmOperateScore(kmOperateScore);
		}
		return search(mapping, kmOperateScoreForm, request, response);
	}
	
	/**
	 * 删除操作积分定义表
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
		KmOperateScoreMgr kmOperateScoreMgr = (KmOperateScoreMgr) getBean("kmOperateScoreMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		kmOperateScoreMgr.removeKmOperateScore(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示操作积分定义表列表
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
				KmOperateScoreConstants.KMOPERATESCORE_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		KmOperateScoreMgr kmOperateScoreMgr = (KmOperateScoreMgr) getBean("kmOperateScoreMgr");
		Map map = (Map) kmOperateScoreMgr.getKmOperateScores(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(KmOperateScoreConstants.KMOPERATESCORE_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示操作积分定义表列表，支持Atom方式接入Portal
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward search4Atom(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			// --------------用于分页，得到当前页号-------------
			final Integer pageIndex = new Integer(request
					.getParameter("pageIndex"));
			final Integer pageSize = new Integer(request
					.getParameter("pageSize"));
			KmOperateScoreMgr kmOperateScoreMgr = (KmOperateScoreMgr) getBean("kmOperateScoreMgr");
			Map map = (Map) kmOperateScoreMgr.getKmOperateScores(pageIndex, pageSize, "");
			List list = (List) map.get("result");
			KmOperateScore kmOperateScore = new KmOperateScore();
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				kmOperateScore = (KmOperateScore) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/kmOperateScore/kmOperateScores.do?method=edit&id="
//						+ kmOperateScore.getId() + "' target='_blank'>"
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
			
			// 每页显示条数
			feed.setText(map.get("total").toString());
		    OutputStream os = response.getOutputStream();
		    PrintStream ps = new PrintStream(os);
		    feed.getDocument().writeTo(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
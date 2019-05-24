package com.boco.eoms.km.expert.webapp.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.km.expert.mgr.KmExpertCetMgr;
import com.boco.eoms.km.expert.model.KmExpertCet;
import com.boco.eoms.km.expert.util.KmExpertCetConstants;
import com.boco.eoms.km.expert.webapp.form.KmExpertCetForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:证书管理
 * </p>
 * <p>
 * Description:证书管理
 * </p>
 * <p>
 * Mon Jun 15 18:07:23 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() zhangxb
 * @moudle.getVersion() 1.0
 * 
 */
public final class KmExpertCetAction extends BaseAction {
 
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
	 * 新增证书管理
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
	 * 修改证书管理
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
		KmExpertCetMgr kmExpertCetMgr = (KmExpertCetMgr) getBean("kmExpertCetMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		if(!id.equals("")){
			KmExpertCet kmExpertCet = kmExpertCetMgr.getKmExpertCet(id);
			KmExpertCetForm kmExpertCetForm = (KmExpertCetForm) convert(kmExpertCet);
			updateFormBean(mapping, request, kmExpertCetForm);
		}
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存证书管理
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
		KmExpertCetMgr kmExpertCetMgr = (KmExpertCetMgr) getBean("kmExpertCetMgr");
		KmExpertCetForm kmExpertCetForm = (KmExpertCetForm) form;
		boolean isNew = (null == kmExpertCetForm.getId() || "".equals(kmExpertCetForm.getId()));
		KmExpertCet kmExpertCet = (KmExpertCet) convert(kmExpertCetForm);
		if (isNew) {
			kmExpertCetMgr.saveKmExpertCet(kmExpertCet);
		} else {
			kmExpertCetMgr.saveKmExpertCet(kmExpertCet);
		}
		
		request.setAttribute("operType", "save");
		
		return mapping.findForward("edit");
	}
	
	/**
	 * 删除证书管理
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
		KmExpertCetMgr kmExpertCetMgr = (KmExpertCetMgr) getBean("kmExpertCetMgr");
//		String id = StaticMethod.null2String(request.getParameter("id"));
//		kmExpertCetMgr.removeKmExpertCet(id);
		String[] ids = request.getParameterValues("ids");
		kmExpertCetMgr.removeKmExpertCets(ids);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示证书管理列表
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
		
		String userId = StaticMethod.null2String(request.getParameter("userId"));
		
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				KmExpertCetConstants.KMEXPERTCET_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));

		KmExpertCetMgr kmExpertCetMgr = (KmExpertCetMgr) getBean("kmExpertCetMgr");
		Map map = (Map) kmExpertCetMgr.getKmExpertCetsByUserId(pageIndex, pageSize, userId);
		List list = (List) map.get("result");
		
		request.setAttribute(KmExpertCetConstants.KMEXPERTCET_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		
		request.setAttribute("userId", userId);
		
		return mapping.findForward("list");
	}

	public ActionForward listDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		search(mapping, form, request, response);
		return mapping.findForward("listDetail");
	}

	/**
	 * 分页显示证书管理列表，支持Atom方式接入Portal
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
//			KmExpertCetMgr kmExpertCetMgr = (KmExpertCetMgr) getBean("kmExpertCetMgr");
//			Map map = (Map) kmExpertCetMgr.getKmExpertCets(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			KmExpertCet kmExpertCet = new KmExpertCet();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				kmExpertCet = (KmExpertCet) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/kmExpertCet/kmExpertCets.do?method=edit&id="
//						+ kmExpertCet.getId() + "' target='_blank'>"
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
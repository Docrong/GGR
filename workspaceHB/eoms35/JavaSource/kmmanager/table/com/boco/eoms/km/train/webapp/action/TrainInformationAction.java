package com.boco.eoms.km.train.webapp.action;

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
import com.boco.eoms.km.train.mgr.TrainInformationMgr;
import com.boco.eoms.km.train.model.TrainInformation;
import com.boco.eoms.km.train.util.TrainInformationConstants;
import com.boco.eoms.km.train.webapp.form.TrainInformationForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:培训需求
 * </p>
 * <p>
 * Description:培训需求信息
 * </p>
 * <p>
 * Wed Jul 01 16:10:34 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 * 
 */
public final class TrainInformationAction extends BaseAction {
 
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
	 * 新增培训需求
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
	 * 修改培训需求
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
		TrainInformationMgr trainInformationMgr = (TrainInformationMgr) getBean("trainInformationMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		TrainInformation trainInformation = trainInformationMgr.getTrainInformation(id);
		TrainInformationForm trainInformationForm = (TrainInformationForm) convert(trainInformation);
		updateFormBean(mapping, request, trainInformationForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存培训需求
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
		TrainInformationMgr trainInformationMgr = (TrainInformationMgr) getBean("trainInformationMgr");
		TrainInformationForm trainInformationForm = (TrainInformationForm) form;
		boolean isNew = (null == trainInformationForm.getId() || "".equals(trainInformationForm.getId()));
		TrainInformation trainInformation = (TrainInformation) convert(trainInformationForm);
		if (isNew) {
			trainInformationMgr.saveTrainInformation(trainInformation);
		} else {
			trainInformationMgr.saveTrainInformation(trainInformation);
		}
		return search(mapping, trainInformationForm, request, response);
	}
	
	/**
	 * 删除培训需求
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
		TrainInformationMgr trainInformationMgr = (TrainInformationMgr) getBean("trainInformationMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		trainInformationMgr.removeTrainInformation(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示培训需求列表
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
				TrainInformationConstants.TRAININFORMATION_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		TrainInformationMgr trainInformationMgr = (TrainInformationMgr) getBean("trainInformationMgr");
		Map map = (Map) trainInformationMgr.getTrainInformations(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(TrainInformationConstants.TRAININFORMATION_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示培训需求列表，支持Atom方式接入Portal
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
//			TrainInformationMgr trainInformationMgr = (TrainInformationMgr) getBean("trainInformationMgr");
//			Map map = (Map) trainInformationMgr.getTrainInformations(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			TrainInformation trainInformation = new TrainInformation();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				trainInformation = (TrainInformation) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/trainInformation/trainInformations.do?method=edit&id="
//						+ trainInformation.getId() + "' target='_blank'>"
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
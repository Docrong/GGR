package com.boco.eoms.km.train.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
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
import com.boco.eoms.km.train.mgr.TrainEnterMgr;
import com.boco.eoms.km.train.mgr.TrainFeedbackMgr;
import com.boco.eoms.km.train.mgr.TrainPlanMgr;
import com.boco.eoms.km.train.model.TrainEnter;
import com.boco.eoms.km.train.model.TrainFeedback;
import com.boco.eoms.km.train.model.TrainPlan;
import com.boco.eoms.km.train.util.TrainEnterConstants;
import com.boco.eoms.km.train.webapp.form.TrainEnterForm;
import com.boco.eoms.km.train.webapp.form.TrainFeedbackForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;

/**
 * <p>
 * Title:报名信息
 * </p>
 * <p>
 * Description:报名信息
 * </p>
 * <p>
 * Fri Jul 10 10:50:46 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() lvweihua
 * @moudle.getVersion() 1.0
 * 
 */
public final class TrainEnterAction extends BaseAction {
 
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
	 * 新增报名信息
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
    	TrainPlanMgr trainPlanMgr = (TrainPlanMgr) getBean("trainPlanMgr");
    	TrainEnterForm trainEnterForm = (TrainEnterForm)form;
    	trainEnterForm.setEnterName(this.getUserId(request));
    	trainEnterForm.setTrainEnterDept(this.getUser(request).getDeptid());
    	String trainPlanId = StaticMethod.null2String(request.getParameter("trainPlanId"));
    	TrainPlan trainPlan = trainPlanMgr.getTrainPlan(trainPlanId);
    	Date trainEndTime = trainPlan.getTrainEndTime();
    	Date date = StaticMethod.getLocalTime();
    	if(date.after(trainEndTime)){
			return mapping.findForward("enterFalse");//时间已经过了 不能报名
		}
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改报名信息
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
		TrainEnterMgr trainEnterMgr = (TrainEnterMgr) getBean("trainEnterMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		TrainEnter trainEnter = trainEnterMgr.getTrainEnter(id);
		TrainEnterForm trainEnterForm = (TrainEnterForm) convert(trainEnter);
		updateFormBean(mapping, request, trainEnterForm);
		return mapping.findForward("edit");
	}
	
	/**
	 * 保存报名信息
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
		TrainEnterMgr trainEnterMgr = (TrainEnterMgr) getBean("trainEnterMgr");
		TrainEnterForm trainEnterForm = (TrainEnterForm) form;
		boolean isNew = (null == trainEnterForm.getId() || "".equals(trainEnterForm.getId()));
		TrainEnter trainEnter = (TrainEnter) convert(trainEnterForm);
		trainEnter.setTrainEnterTime(StaticMethod.getLocalTime());
		if (isNew) {
			trainEnterMgr.saveTrainEnter(trainEnter);
		} else {
			trainEnterMgr.saveTrainEnter(trainEnter);
		}
		return mapping.findForward("success");
	}
	
	/**
	 * 删除报名信息
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
		TrainEnterMgr trainEnterMgr = (TrainEnterMgr) getBean("trainEnterMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		trainEnterMgr.removeTrainEnter(id);
		return mapping.findForward("success");
	}
	
	/**
	 * 分页显示报名信息列表
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
				TrainEnterConstants.TRAINENTER_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		TrainEnterMgr trainEnterMgr = (TrainEnterMgr) getBean("trainEnterMgr");
		Map map = (Map) trainEnterMgr.getTrainEnters(pageIndex, pageSize, "");
		List list = (List) map.get("result");
		request.setAttribute(TrainEnterConstants.TRAINENTER_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示报名信息列表，支持Atom方式接入Portal
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
//			TrainEnterMgr trainEnterMgr = (TrainEnterMgr) getBean("trainEnterMgr");
//			Map map = (Map) trainEnterMgr.getTrainEnters(pageIndex, pageSize, "");
//			List list = (List) map.get("result");
//			TrainEnter trainEnter = new TrainEnter();
//			
//			//创建ATOM源
//			Factory factory = Abdera.getNewFactory();
//			Feed feed = factory.newFeed();
//			
//			// 分页
//			for (int i = 0; i < list.size(); i++) {
//				trainEnter = (TrainEnter) list.get(i);
//				
//				// TODO 请按照下面的实例给entry赋值
//				Entry entry = feed.insertEntry();
//				entry.setTitle("<a href='" + request.getScheme() + "://"
//						+ request.getServerName() + ":"
//						+ request.getServerPort()
//						+ request.getContextPath()
//						+ "/trainEnter/trainEnters.do?method=edit&id="
//						+ trainEnter.getId() + "' target='_blank'>"
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
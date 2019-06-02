package com.boco.eoms.duty.webapp.action;

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

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.duty.service.ITawRmDutyEventManager;
import com.boco.eoms.duty.util.CommonTools;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.duty.mgr.FaultCommontMgr;
import com.boco.eoms.duty.mgr.FaultEquipmentMgr;
import com.boco.eoms.duty.model.FaultCommont;
import com.boco.eoms.duty.model.FaultEquipment;
import com.boco.eoms.duty.model.TawRmDutyEvent;
import com.boco.eoms.duty.util.FaultEquipmentConstants;
import com.boco.eoms.duty.webapp.form.FaultEquipmentForm;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * <p>
 * Title:设备故障记录
 * </p>
 * <p>
 * Description:设备故障记录
 * </p>
 * <p>
 * Sun Mar 29 09:02:44 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() 李江红
 * @moudle.getVersion() 3.5
 * 
 */
public final class FaultEquipmentAction extends BaseAction {
 
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
	 * 新增设备故障记录
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
    	FaultEquipmentForm faultEquipmentForm = (FaultEquipmentForm)form;
    	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
    	if (saveSessionBeanForm == null) {
    		return mapping.findForward("timeout");
    	} 
    	
    	// 从通用故障记录传入数据
    	FaultCommont faultCommont = null;
    	if(faultEquipmentForm.getFaultCommontId()!=null) {
    		FaultCommontMgr mgr = (FaultCommontMgr) getBean("faultCommontMgr");
    		faultCommont = mgr.getFaultCommont(faultEquipmentForm.getFaultCommontId());
    		faultEquipmentForm.setTitle(faultCommont.getTitle());
    		faultEquipmentForm.setRecordPerId(faultCommont.getRecordPerId());
    		faultEquipmentForm.setGreffier(faultCommont.getGreffier());
    		faultEquipmentForm.setSerialNos(faultCommont.getSerialno());
    		faultEquipmentForm.setBeginTime(faultCommont.getBeginTime());
    		faultEquipmentForm.setEndTime(faultCommont.getEndTime());
    		faultEquipmentForm.setIsAppEffect(faultCommont.getIsAppEffect());
    		faultEquipmentForm.setAppEffect(faultCommont.getAppEffect());
    	}  
    	
    	TawRmDutyEvent tawRmDutyEvent = null;
    	if(faultEquipmentForm.getRecordPerId()!=null) {
    		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
    		tawRmDutyEvent = mgr.getTawRmDutyEvent(faultEquipmentForm.getRecordPerId());
    		faultEquipmentForm.setBeginTime(tawRmDutyEvent.getBeginTime());
    		faultEquipmentForm.setEndTime(tawRmDutyEvent.getEndtime());
    		faultEquipmentForm.setTitle(tawRmDutyEvent.getEventtitle());
    		faultEquipmentForm.setSerialNos(tawRmDutyEvent.getWorksheetid());
    	}  
    	
    	// 初始化故障记录人数据
    	FaultCommontMgr faultCommontMgr = (FaultCommontMgr) getBean("faultCommontMgr");
		List userList = faultCommontMgr.getRoomUsers(StaticMethod.nullObject2int(saveSessionBeanForm.getRoomId()));
		
		// 初始化故障发生时间值
		faultEquipmentForm.setBeginTime(StaticMethod.getCurrentDateTime());

    	request.setAttribute("USERLIST", userList);
    	
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改设备故障记录
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
    	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
    	if (saveSessionBeanForm == null) {
    		return mapping.findForward("timeout");
    	} 
		FaultEquipmentMgr faultEquipmentMgr = (FaultEquipmentMgr) getBean("faultEquipmentMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		FaultEquipment faultEquipment = faultEquipmentMgr.getFaultEquipment(id);
		FaultEquipmentForm faultEquipmentForm = (FaultEquipmentForm) convert(faultEquipment);
		updateFormBean(mapping, request, faultEquipmentForm);
		
		FaultCommontMgr faultCommontMgr = (FaultCommontMgr) getBean("faultCommontMgr");		
		// 初始化故障记录人数据
		List userList = faultCommontMgr.getRoomUsers(StaticMethod.nullObject2int(saveSessionBeanForm.getRoomId()));
		request.setAttribute("USERLIST", userList);

		return mapping.findForward("edit");
	}
    
    /**
	 * 查看设备故障记录
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	FaultEquipmentMgr faultEquipmentMgr = (FaultEquipmentMgr) getBean("faultEquipmentMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		FaultEquipment faultEquipment = faultEquipmentMgr.getFaultEquipment(id);
		
		// 计算时间差值
		faultEquipment.setTimeSlot(CommonTools.getResumeTimeSlot(faultEquipment.getBeginTime(), faultEquipment.getEndTime()));
		faultEquipment.setResumeTimeSlot(CommonTools.getResumeTimeSlot(faultEquipment.getBeginTime(), faultEquipment.getResumeTime()));
		
		FaultEquipmentForm faultEquipmentForm = (FaultEquipmentForm) convert(faultEquipment);
		updateFormBean(mapping, request, faultEquipmentForm);
		return mapping.findForward("view");
	}
    
	
	/**
	 * 保存设备故障记录
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
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
    	if (saveSessionBeanForm == null) {
    		return mapping.findForward("timeout");
    	} 
    	
		FaultEquipmentMgr faultEquipmentMgr = (FaultEquipmentMgr) getBean("faultEquipmentMgr");
		FaultEquipmentForm faultEquipmentForm = (FaultEquipmentForm) form;
		boolean isNew = (null == faultEquipmentForm.getId() || "".equals(faultEquipmentForm.getId()));
		FaultEquipment faultEquipment = (FaultEquipment) convert(faultEquipmentForm);
		if(!faultEquipment.getIsAppEffect().equals(FaultEquipmentForm.appEffectYes)) { // 不影响业务
			faultEquipment.setResumeTime("");
		}
		
		if (isNew) {
			faultEquipment.setInputUserId(saveSessionBeanForm.getUserid()); // 录入人
			faultEquipment.setInputTime(StaticMethod.getCurrentDateTime()); // 录入时间
			faultEquipment.setRoomId(saveSessionBeanForm.getRoomId()); //  机房编号
			faultEquipment.setSequenceNo(faultEquipmentMgr.getFaultSequenceNo()); // 编号
			faultEquipmentMgr.saveFaultEquipment(faultEquipment);
			
			// 把故障编号加到事件表中
			TawRmDutyEvent tawRmDutyEvent = null;
	    	if(faultEquipment.getRecordPerId()!=null) {
	    		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
	    		tawRmDutyEvent = mgr.getTawRmDutyEvent(faultEquipment.getRecordPerId());
	    		tawRmDutyEvent.setFaultEquipmentId(faultEquipment.getId());
	    		mgr.saveTawRmDutyEvent(tawRmDutyEvent);
	    	}  
		} else {
			faultEquipmentMgr.saveFaultEquipment(faultEquipment);
		}
		faultEquipmentForm = new FaultEquipmentForm();
		return list(mapping, faultEquipmentForm, request, response);
	}
	
	/**
	 * 删除设备故障记录
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
		FaultEquipmentMgr faultEquipmentMgr = (FaultEquipmentMgr) getBean("faultEquipmentMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		
		// 把故障编号加到事件表中
		TawRmDutyEvent tawRmDutyEvent = null;
		FaultEquipment faultEquipment = faultEquipmentMgr.getFaultEquipment(id);
    	if(faultEquipment.getRecordPerId()!=null) {
    		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
    		tawRmDutyEvent = mgr.getTawRmDutyEvent(faultEquipment.getRecordPerId());
    		tawRmDutyEvent.setFaultEquipmentId("");
    		mgr.saveTawRmDutyEvent(tawRmDutyEvent);
    	}  
    	
		faultEquipmentMgr.removeFaultEquipment(id);
		
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示设备故障记录列表
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
		TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
    	if (saveSessionBeanForm == null) {
    		return mapping.findForward("timeout");
    	}
		FaultCommontMgr faultCommontMgr = (FaultCommontMgr) getBean("faultCommontMgr");		
		// 初始化故障记录人数据
		List userList = faultCommontMgr.getRoomUsers(StaticMethod.nullObject2int(saveSessionBeanForm.getRoomId()));
		
		request.setAttribute("USERLIST", userList);
		return mapping.findForward("search");
	}
	
	/**
	 * 分页显示设备故障记录列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageIndexName = new org.displaytag.util.ParamEncoder(
				FaultEquipmentConstants.FAULTEQUIPMENT_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		FaultEquipmentMgr faultEquipmentMgr = (FaultEquipmentMgr) getBean("faultEquipmentMgr");
		String condition = faultEquipmentMgr.getSearchCondition((FaultEquipmentForm)form);
		Map map = (Map) faultEquipmentMgr.getFaultEquipments(pageIndex, pageSize, condition);
		List list = (List) map.get("result");
		request.setAttribute(FaultEquipmentConstants.FAULTEQUIPMENT_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 统计功能
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示设备故障记录列表，支持Atom方式接入Portal
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
			FaultEquipmentMgr faultEquipmentMgr = (FaultEquipmentMgr) getBean("faultEquipmentMgr");
			Map map = (Map) faultEquipmentMgr.getFaultEquipments(pageIndex, pageSize, "");
			List list = (List) map.get("result");
			FaultEquipment faultEquipment = new FaultEquipment();
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
			// 分页
			for (int i = 0; i < list.size(); i++) {
				faultEquipment = (FaultEquipment) list.get(i);
				// TODO 请按照下面的实例给entry赋值
				Entry entry = feed.insertEntry();
				/*---- 云南 先注释------------------------------------------
				entry.setTitle("<a href='" + request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort()
						+ request.getContextPath()
						+ "/faultEquipment/faultEquipments.do?method=edit&id="
						+ faultEquipment.getId() + "' target='_blank'>"
						+ display name for list + "</a>");
				entry.setSummary(summary);
				entry.setContent(content);
				entry.setLanguage(language);
				entry.setText(text);
				entry.setRights(tights);
				
				// 以下三项需要传入Date类型或String类型（yyyy-MM-dd）的参数
				entry.setUpdated(new java.util.Date());
				entry.setPublished(new java.util.Date());
				entry.setEdited(new java.util.Date());
				
				// 为person的name属性赋值，entry.addAuthor可以随意赋值
				Person person = entry.addAuthor(userId);
				person.setName(userName);
				-----------------------------------------------------------------*/
			}
			
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
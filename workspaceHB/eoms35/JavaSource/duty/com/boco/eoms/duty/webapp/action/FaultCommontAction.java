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

import com.boco.eoms.base.api.EOMSMgr;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.dict.util.Constants;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.duty.mgr.FaultCommontMgr;
import com.boco.eoms.duty.model.FaultCommont;
import com.boco.eoms.duty.model.TawRmDutyEvent;
import com.boco.eoms.duty.model.TawRmDutyEventSub;
import com.boco.eoms.duty.service.ITawRmDutyEventManager;
import com.boco.eoms.duty.util.CommonTools;
import com.boco.eoms.duty.util.FaultCommontConstants;
import com.boco.eoms.duty.webapp.form.FaultCommontForm;
import com.boco.eoms.duty.webapp.form.TawRmDutyEventForm;

/**
 * <p>
 * Title:通用故障记录
 * </p>
 * <p>
 * Description:通用故障记录功能
 * </p>
 * <p>
 * Mon Mar 23 15:39:20 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() 李江红
 * @moudle.getVersion() 3.5
 * 
 */
public final class FaultCommontAction extends BaseAction {
 
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
	 * 新增通用故障记录
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
    	FaultCommontForm faultCommontForm = (FaultCommontForm)form;
    	
    	TawRmDutyEvent tawRmDutyEvent = null;
    	if(faultCommontForm.getRecordPerId()!=null) {
    		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
    		tawRmDutyEvent = mgr.getTawRmDutyEvent(faultCommontForm.getRecordPerId());
    		faultCommontForm.setBeginTime(tawRmDutyEvent.getBeginTime());
    		faultCommontForm.setEndTime(tawRmDutyEvent.getEndtime());
    		faultCommontForm.setTitle(tawRmDutyEvent.getEventtitle());
    		
    		List list = mgr.getTawRmDutyEventSubByEventid(faultCommontForm.getRecordPerId());
    		if(list!=null&&list.get(0)!=null) {
    			TawRmDutyEventSub tawRmDutyEventSub = (TawRmDutyEventSub)list.get(0);
    			faultCommontForm.setFaultPhenomenon(tawRmDutyEventSub.getContent());
    			faultCommontForm.setSerialno(tawRmDutyEventSub.getWorksheetid());
    		}
    	}    	
    	
    	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
    	if (saveSessionBeanForm == null) {
    		return mapping.findForward("timeout");
    	} 
    	// 初始化故障记录人数据
    	FaultCommontMgr faultCommontMgr = (FaultCommontMgr) getBean("faultCommontMgr");
		List userList = faultCommontMgr.getRoomUsers(StaticMethod.nullObject2int(saveSessionBeanForm.getRoomId()));
		
		// 初始化故障发生时间值
		faultCommontForm.setBeginTime(StaticMethod.getCurrentDateTime());

    	request.setAttribute("USERLIST", userList);
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改通用故障记录
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
		FaultCommontMgr faultCommontMgr = (FaultCommontMgr) getBean("faultCommontMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		FaultCommont faultCommont = faultCommontMgr.getFaultCommont(id);
		FaultCommontForm faultCommontForm = (FaultCommontForm) convert(faultCommont);
		faultCommont.setTimeSlot(CommonTools.getResumeTimeSlot(faultCommont.getBeginTime(), faultCommont.getEndTime()));
		faultCommont.setResumeTimeSlot(CommonTools.getResumeTimeSlot(faultCommont.getBeginTime(), faultCommont.getResumeTime()));
		
		// 初始化故障记录人数据
		List userList = faultCommontMgr.getRoomUsers(StaticMethod.nullObject2int(saveSessionBeanForm.getRoomId()));

		updateFormBean(mapping, request, faultCommontForm);
		request.setAttribute("USERLIST", userList);
		return mapping.findForward("edit");
	}
    
    /**
	 * 查看通用故障记录
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
		FaultCommontMgr faultCommontMgr = (FaultCommontMgr) getBean("faultCommontMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		FaultCommont faultCommont = faultCommontMgr.getFaultCommont(id);
		faultCommont.setTimeSlot(CommonTools.getResumeTimeSlot(faultCommont.getBeginTime(), faultCommont.getEndTime()));
		faultCommont.setResumeTimeSlot(CommonTools.getResumeTimeSlot(faultCommont.getBeginTime(), faultCommont.getResumeTime()));
		FaultCommontForm faultCommontForm = (FaultCommontForm) convert(faultCommont);
		updateFormBean(mapping, request, faultCommontForm);
		return mapping.findForward("view");
	}
	
	/**
	 * 保存通用故障记录
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
    	
		FaultCommontMgr faultCommontMgr = (FaultCommontMgr) getBean("faultCommontMgr");
		FaultCommontForm faultCommontForm = (FaultCommontForm) form;
		boolean isNew = (null == faultCommontForm.getId() || "".equals(faultCommontForm.getId()));
		FaultCommont faultCommont = (FaultCommont) convert(faultCommontForm);
		
		if(faultCommont.getEstate().equals(FaultCommontForm.estateYes)) { // 为归档
			faultCommont.setIssueTime(StaticMethod.getCurrentDateTime());
			faultCommont.setIssueUser(saveSessionBeanForm.getUserid());
		}
		if(!faultCommont.getIsAppEffect().equals(FaultCommontForm.appEffectYes)) { // 不影响业务
			faultCommont.setResumeTime("");
			faultCommont.setAppEffect("");
		}

		String beanId = "dict-duty" + Constants.DICT_ID_SPLIT_CHAR + "faultfilialeId";
		faultCommont.setFaultAddress(StaticMethod.nullObject2String(EOMSMgr.getSysMgrs().getDictMgrs().getXMLDictMgr().itemId2name(beanId,faultCommont.getFaultAddressId())));
		if (isNew) {
			faultCommont.setInputUser(saveSessionBeanForm.getUserid()); // 录入人
			faultCommont.setInputTime(StaticMethod.getCurrentDateTime()); // 录入时间
			faultCommont.setRoomId(saveSessionBeanForm.getRoomId()); //  机房编号
			faultCommont.setSequenceNo(faultCommontMgr.getFaultSequenceNo()); // 编号
			
			faultCommontMgr.saveFaultCommont(faultCommont);
			
			// 把故障编号加到事件表中
			TawRmDutyEvent tawRmDutyEvent = null;
	    	if(faultCommontForm.getRecordPerId()!=null) {
	    		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
	    		tawRmDutyEvent = mgr.getTawRmDutyEvent(faultCommontForm.getRecordPerId());
	    		tawRmDutyEvent.setFaultCommontId(faultCommont.getId());
	    		mgr.saveTawRmDutyEvent(tawRmDutyEvent);
	    	}  
		} else {
			faultCommontMgr.saveFaultCommont(faultCommont);
		}
		faultCommontForm = new FaultCommontForm();
		faultCommontForm.setEstate(FaultCommontForm.estateNo);
		return list(mapping, faultCommontForm, request, response);
	}
	
	/**
	 * 删除通用故障记录
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
		FaultCommontMgr faultCommontMgr = (FaultCommontMgr) getBean("faultCommontMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		
		// 把故障编号加到事件表中
		TawRmDutyEvent tawRmDutyEvent = null;
		FaultCommont faultCommont = faultCommontMgr.getFaultCommont(id);
    	if(faultCommont.getRecordPerId()!=null) {
    		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
    		tawRmDutyEvent = mgr.getTawRmDutyEvent(faultCommont.getRecordPerId());
    		tawRmDutyEvent.setFaultCommontId("");
    		mgr.saveTawRmDutyEvent(tawRmDutyEvent);
    	}  
    	
		faultCommontMgr.removeFaultCommont(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 分页显示通用故障记录列表
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
		FaultCommontForm faultCommontForm =(FaultCommontForm)form;
		faultCommontForm.setStrutsAction(FaultCommontForm.ActionSearch);
		
		request.setAttribute("USERLIST", userList);
		return mapping.findForward("search");
	}
	
	/**
	 * 统计通用故障记录
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
		FaultCommontMgr faultCommontMgr = (FaultCommontMgr) getBean("faultCommontMgr");
		FaultCommontForm faultCommontForm = (FaultCommontForm)form;
		if(faultCommontForm.getFromBeginTime() == null ||faultCommontForm.getFromBeginTime().equals("")) {
			faultCommontForm.setFromBeginTime(StaticMethod.getCurrentDateTime().substring(0,11) + "00:00:00");
		}
		if(faultCommontForm.getToBeginTime() == null ||faultCommontForm.getToBeginTime().equals("")) {
			faultCommontForm.setToBeginTime(StaticMethod.getCurrentDateTime().substring(0,11) + "23:59:59");
		}
          
		String condition = faultCommontMgr.getSearchCondition(faultCommontForm);
		List list = faultCommontMgr.getStatList(condition);

        request.setAttribute("StatList",list);
		
		return mapping.findForward("stat");
	}
	
	/**
	 * 分页显示通用故障记录列表
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
				FaultCommontConstants.FAULTCOMMONT_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		FaultCommontMgr faultCommontMgr = (FaultCommontMgr) getBean("faultCommontMgr");
		String condition = faultCommontMgr.getSearchCondition((FaultCommontForm)form);
		Map map = (Map) faultCommontMgr.getFaultCommonts(pageIndex, pageSize, condition);
		List list = (List) map.get("result");
		request.setAttribute(FaultCommontConstants.FAULTCOMMONT_LIST, list);
		request.setAttribute("resultSize", map.get("total"));
		request.setAttribute("pageSize", pageSize);
		return mapping.findForward("list");
	}
	
	/**
	 * 分页显示通用故障记录列表，支持Atom方式接入Portal
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
			FaultCommontMgr faultCommontMgr = (FaultCommontMgr) getBean("faultCommontMgr");
			Map map = (Map) faultCommontMgr.getFaultCommonts(pageIndex, pageSize, "");
			List list = (List) map.get("result");
			FaultCommont faultCommont = new FaultCommont();
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
			// 分页
			for (int i = 0; i < list.size(); i++) {
				faultCommont = (FaultCommont) list.get(i);
				
				// TODO 请按照下面的实例给entry赋值
				Entry entry = feed.insertEntry();
				/*---- 云南 先注释------------------------------------------
				entry.setTitle("<a href='" + request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort()
						+ request.getContextPath()
						+ "/faultCommont/faultCommonts.do?method=edit&id="
						+ faultCommont.getId() + "' target='_blank'>"
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
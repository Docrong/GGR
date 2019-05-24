package com.boco.eoms.duty.webapp.action;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
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
import com.boco.eoms.duty.mgr.FaultCircuitMgr;
import com.boco.eoms.duty.mgr.FaultCommontMgr;
import com.boco.eoms.duty.model.FaultCircuit;
import com.boco.eoms.duty.model.FaultCommont;
import com.boco.eoms.duty.model.TawRmDutyEvent;
import com.boco.eoms.duty.service.ITawRmDutyEventManager;
import com.boco.eoms.duty.util.FaultCircuitConstants;
import com.boco.eoms.duty.webapp.form.FaultCircuitForm;
import com.boco.eoms.duty.util.CommonTools;
import com.boco.eoms.base.util.UtilMgrLocator;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;

/**
 * <p>
 * Title:线路故障记录
 * </p>
 * <p>
 * Description:线路故障记录功能
 * </p>
 * <p>
 * Sun Mar 29 12:55:57 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() 李江红
 * @moudle.getVersion() 3.5
 * 
 */
public final class FaultCircuitAction extends BaseAction {
 
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
	 * 新增线路故障记录
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
    	TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
    	if (saveSessionBeanForm == null) {
    		return mapping.findForward("timeout");
    	} 
    	FaultCircuitForm faultCircuitForm = (FaultCircuitForm)form;
    	faultCircuitForm.setBeginTime(StaticMethod.getCurrentDateTime());
    	
    	// 从通用故障记录传入数据
    	FaultCommont faultCommont = null;
    	if(faultCircuitForm.getFaultCommontId()!=null) {
    		FaultCommontMgr mgr = (FaultCommontMgr) getBean("faultCommontMgr");
    		faultCommont = mgr.getFaultCommont(faultCircuitForm.getFaultCommontId());
    		faultCircuitForm.setTitle(faultCommont.getTitle());
    		faultCircuitForm.setRecordPerId(faultCommont.getRecordPerId());
    		faultCircuitForm.setGreffier(faultCommont.getGreffier());
    		faultCircuitForm.setSerialNos(faultCommont.getSerialno());
    		faultCircuitForm.setBeginTime(faultCommont.getBeginTime());
    		faultCircuitForm.setEndTime(faultCommont.getEndTime());
    		faultCircuitForm.setIsAppEffect(faultCommont.getIsAppEffect());
    		faultCircuitForm.setAppEffect(faultCommont.getAppEffect());
    	}  
    	
    	// 从故障事件传入数据
    	TawRmDutyEvent tawRmDutyEvent = null;
    	if(faultCircuitForm.getRecordPerId()!=null) {
    		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
    		tawRmDutyEvent = mgr.getTawRmDutyEvent(faultCircuitForm.getRecordPerId());
    		faultCircuitForm.setBeginTime(tawRmDutyEvent.getBeginTime());
    		faultCircuitForm.setEndTime(tawRmDutyEvent.getEndtime());
    		faultCircuitForm.setTitle(tawRmDutyEvent.getEventtitle());
    		faultCircuitForm.setSerialNos(tawRmDutyEvent.getWorksheetid());
    	}   
    	
    	FaultCommontMgr faultCommontMgr = (FaultCommontMgr) getBean("faultCommontMgr");		
		// 初始化故障记录人数据
		List userList = faultCommontMgr.getRoomUsers(StaticMethod.nullObject2int(saveSessionBeanForm.getRoomId()));
		request.setAttribute("USERLIST", userList);
		
		// 初始化故障类别数据
		ITawSystemDictTypeManager tawSystemDictTypeMgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
		List faultTypeList = tawSystemDictTypeMgr.getDictSonsByDictid("1040403");
		request.setAttribute("FAULTTYPELIST", faultTypeList);
		return mapping.findForward("edit");
	}
	
	/**
	 * 修改线路故障记录
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
    	
		FaultCircuitMgr faultCircuitMgr = (FaultCircuitMgr) getBean("faultCircuitMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		FaultCircuit faultCircuit = faultCircuitMgr.getFaultCircuit(id);
		faultCircuit.setFaultTypeName(CommonTools.getTypeName(CommonTools.parseTypeList(faultCircuit.getTypeIdList())));
		FaultCircuitForm faultCircuitForm = (FaultCircuitForm) convert(faultCircuit);
		updateFormBean(mapping, request, faultCircuitForm);
		
		FaultCommontMgr faultCommontMgr = (FaultCommontMgr) getBean("faultCommontMgr");		
		// 初始化故障记录人数据
		List userList = faultCommontMgr.getRoomUsers(StaticMethod.nullObject2int(saveSessionBeanForm.getRoomId()));
		request.setAttribute("USERLIST", userList);
		
		// 初始化故障类别数据
		ITawSystemDictTypeManager tawSystemDictTypeMgr = (ITawSystemDictTypeManager) getBean("ItawSystemDictTypeManager");
		List faultTypeList = tawSystemDictTypeMgr.getDictSonsByDictid("1040403");
		request.setAttribute("FAULTTYPELIST", faultTypeList);
		return mapping.findForward("edit");
	}
    
    /**
	 * 查看线路故障记录
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
		FaultCircuitMgr faultCircuitMgr = (FaultCircuitMgr) getBean("faultCircuitMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		FaultCircuit faultCircuit = faultCircuitMgr.getFaultCircuit(id);

		// 计算时间差值
		faultCircuit.setTimeSlot(CommonTools.getResumeTimeSlot(faultCircuit.getBeginTime(),faultCircuit.getEndTime()));

		faultCircuit.setResumeTimeSlot(CommonTools.getResumeTimeSlot(faultCircuit.getBeginTime(),faultCircuit.getResumeTime()));
		faultCircuit.setFaultTypeName(CommonTools.getTypeName(CommonTools.parseTypeList(faultCircuit.getTypeIdList())));
		FaultCircuitForm faultCircuitForm = (FaultCircuitForm) convert(faultCircuit);
		updateFormBean(mapping, request, faultCircuitForm);
		return mapping.findForward("view");
	}
	
	/**
	 * 保存线路故障记录
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
    	
		FaultCircuitMgr faultCircuitMgr = (FaultCircuitMgr) getBean("faultCircuitMgr");
		FaultCircuitForm faultCircuitForm = (FaultCircuitForm) form;
		boolean isNew = (null == faultCircuitForm.getId() || "".equals(faultCircuitForm.getId()));
		FaultCircuit faultCircuit = (FaultCircuit) convert(faultCircuitForm);
		if (isNew) {
			faultCircuit.setInputUserId(saveSessionBeanForm.getUserid()); // 录入人
			faultCircuit.setInputTime(StaticMethod.getCurrentDateTime()); // 录入时间
			faultCircuit.setRoomId(saveSessionBeanForm.getRoomId()); //  机房编号
			faultCircuit.setSequenceNo(faultCircuitMgr.getFaultSequenceNo()); // 编号
			
			faultCircuitMgr.saveFaultCircuit(faultCircuit);
			
			// 把故障编号加到事件表中
			TawRmDutyEvent tawRmDutyEvent = null;
	    	if(faultCircuit.getRecordPerId()!=null) {
	    		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
	    		tawRmDutyEvent = mgr.getTawRmDutyEvent(faultCircuit.getRecordPerId());
	    		tawRmDutyEvent.setFaultCircuitId(faultCircuit.getId());
	    		mgr.saveTawRmDutyEvent(tawRmDutyEvent);
	    	}  
		} else {
			faultCircuitMgr.saveFaultCircuit(faultCircuit);
		}
		
		faultCircuitForm = new FaultCircuitForm();
		return list(mapping, faultCircuitForm, request, response);
	}
	
	/**
	 * 删除线路故障记录
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
		FaultCircuitMgr faultCircuitMgr = (FaultCircuitMgr) getBean("faultCircuitMgr");
		String id = StaticMethod.null2String(request.getParameter("id"));
		
		// 把故障编号加到事件表中
		TawRmDutyEvent tawRmDutyEvent = null;
		FaultCircuit faultCircuit = faultCircuitMgr.getFaultCircuit(id);
    	if(faultCircuit.getRecordPerId()!=null) {
    		ITawRmDutyEventManager mgr = (ITawRmDutyEventManager) getBean("ITawRmDutyEnventManager");
    		tawRmDutyEvent = mgr.getTawRmDutyEvent(faultCircuit.getRecordPerId());
    		tawRmDutyEvent.setFaultCircuitId("");
    		mgr.saveTawRmDutyEvent(tawRmDutyEvent);
    	}  
    	
		faultCircuitMgr.removeFaultCircuit(id);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 查询线路故障记录列表
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
	 * 分页显示线路故障记录列表
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
				FaultCircuitConstants.FAULTCIRCUIT_LIST)
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageSize = UtilMgrLocator.getEOMSAttributes()
				.getPageSize();
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		FaultCircuitMgr faultCircuitMgr = (FaultCircuitMgr) getBean("faultCircuitMgr");
		String condition = faultCircuitMgr.getSearchCondition((FaultCircuitForm)form);
		Map map = (Map) faultCircuitMgr.getFaultCircuits(pageIndex, pageSize, condition);
		List list = (List) map.get("result");
		List faultCircuitList = new ArrayList();
		
		for(int i=0;i<list.size();i++) {
			FaultCircuit faultCircuit = (FaultCircuit)list.get(i);
			faultCircuit.setFaultTypeName(CommonTools.getTypeName(CommonTools.parseTypeList(faultCircuit.getTypeIdList())));
			faultCircuitList.add(faultCircuit);
		}
		request.setAttribute(FaultCircuitConstants.FAULTCIRCUIT_LIST, faultCircuitList);
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
	 * 分页显示线路故障记录列表，支持Atom方式接入Portal
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
			FaultCircuitMgr faultCircuitMgr = (FaultCircuitMgr) getBean("faultCircuitMgr");
			Map map = (Map) faultCircuitMgr.getFaultCircuits(pageIndex, pageSize, "");
			List list = (List) map.get("result");
			FaultCircuit faultCircuit = new FaultCircuit();
			
			//创建ATOM源
			Factory factory = Abdera.getNewFactory();
			Feed feed = factory.newFeed();
			
			// 分页
			for (int i = 0; i < list.size(); i++) {
				faultCircuit = (FaultCircuit) list.get(i);
				
				// TODO 请按照下面的实例给entry赋值
				Entry entry = feed.insertEntry();
				/*---- 云南 先注释------------------------------------------
				entry.setTitle("<a href='" + request.getScheme() + "://"
						+ request.getServerName() + ":"
						+ request.getServerPort()
						+ request.getContextPath()
						+ "/faultCircuit/faultCircuits.do?method=edit&id="
						+ faultCircuit.getId() + "' target='_blank'>"
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
				----------------------------------------------*/
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
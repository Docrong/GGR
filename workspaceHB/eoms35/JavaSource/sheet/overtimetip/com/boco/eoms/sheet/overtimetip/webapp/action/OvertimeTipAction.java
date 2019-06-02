package com.boco.eoms.sheet.overtimetip.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.webapp.action.BaseAction;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.overtimetip.model.OvertimeTip;
import com.boco.eoms.sheet.overtimetip.service.IOvertimeTipManager;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;
import com.boco.eoms.sheet.overtimetip.webapp.form.OvertimeTipForm;

public class OvertimeTipAction extends BaseAction{
	
	/**
	 * 撤销
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * @author jialei
	 */
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String flowName = StaticMethod.nullObject2String(request.getParameter("flowName"));
		request.setAttribute("flowName", flowName);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 删除超时提醒
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * @author jialei
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'delete' method");
		}

		ActionMessages messages = new ActionMessages();
		OvertimeTipForm overtimeTipForm = (OvertimeTipForm) form;

		IOvertimeTipManager mgr = (IOvertimeTipManager) getBean("iOvertimeTipManager");
		
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		
		mgr.removeOvertimeTip(overtimeTipForm.getId());

		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("IproxyManager.deleted"));

		saveMessages(request.getSession(), messages);
		
		String flowName = StaticMethod.nullObject2String(request.getParameter("flowName"));
		request.setAttribute("flowName", flowName);
		return search(mapping, form, request, response);
	}
	
	/**
	 * 保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * @author jialei
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		OvertimeTipForm overtimeTipForm = (OvertimeTipForm) form;
		OvertimeTip overtimeTip = (OvertimeTip) convert(overtimeTipForm);
		IOvertimeTipManager mgr = (IOvertimeTipManager) getBean("iOvertimeTipManager");
		//查询该用户是否设置过该类工单
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		if(userId.equals("admin")){
			if(!overtimeTip.getUserId().equals(userId)){
				overtimeTip.setUserId("system");
			}else{
				overtimeTip.setUserId(userId);
			}
		}else{
			overtimeTip.setUserId(userId);
		}
		
		Integer.parseInt(overtimeTip.getOvertimeLimit());
		
		OvertimeTip tmpOvertimeTip = null;
		
		HashMap condition = new HashMap();
		if(overtimeTip.getUserId().equals("system")){
			condition.put("userId", "='system'");
		}else{
			condition.put("userId", "='"+userId+"'");
		}
		if(overtimeTip.getSpecialty1()!=null){
			condition.put("specialty1", "='"+overtimeTip.getSpecialty1()+"'");
		}
		if(overtimeTip.getSpecialty2()!=null){
			condition.put("specialty2", "='"+overtimeTip.getSpecialty2()+"'");
		}
		if(overtimeTip.getSpecialty3()!=null){
			condition.put("specialty3", "='"+overtimeTip.getSpecialty3()+"'");
		}
		if(overtimeTip.getSpecialty4()!=null){
			condition.put("specialty4", "='"+overtimeTip.getSpecialty4()+"'");
		}
		if(overtimeTip.getSpecialty5()!=null){
			condition.put("specialty5", "='"+overtimeTip.getSpecialty5()+"'");
		}
		if(overtimeTip.getSpecialty6()!=null){
			condition.put("specialty6", "='"+overtimeTip.getSpecialty6()+"'");
		}
		if(overtimeTip.getSpecialty7()!=null){
			condition.put("specialty7", "='"+overtimeTip.getSpecialty7()+"'");
		}
		if(overtimeTip.getSpecialty8()!=null){
			condition.put("specialty8", "='"+overtimeTip.getSpecialty8()+"'");
		}
		if(overtimeTip.getSpecialty9()!=null){
			condition.put("specialty9", "='"+overtimeTip.getSpecialty9()+"'");
		}
		if(overtimeTip.getSpecialty10()!=null){
			condition.put("specialty10", "='"+overtimeTip.getSpecialty10()+"'");
		}
		condition.put("flowName", "='"+overtimeTip.getFlowName()+"'");
		
		List timeList = mgr.getOvertimeTipByCondition(condition);
		if(timeList.size()>0){
			tmpOvertimeTip = (OvertimeTip)timeList.get(0);
			tmpOvertimeTip.setSetTime(new Date());
			tmpOvertimeTip.setOvertimeLimit(overtimeTip.getOvertimeLimit());
			overtimeTip = tmpOvertimeTip;
		}else{
			//生成id
			overtimeTip.setId(UUIDHexGenerator.getInstance().getID());
			//设置更新时间
			overtimeTip.setSetTime(new Date());
			overtimeTip.setOvertimeLimit(overtimeTip.getOvertimeLimit());
		}
		mgr.saveOvertimeTip(overtimeTip);
		String flowName = StaticMethod.nullObject2String(request.getParameter("flowName"));
		request.setAttribute("flowName", flowName);
		return search(mapping, form, request, response);
	}
	
	 /**
     * 查询超时提醒列表
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     * @author jialei
     */
	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();
		// 当前页数
		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		//得到用户名
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		
		String flowName = StaticMethod.nullObject2String(request.getParameter("flowName"));
		if(flowName.equals("")){
			flowName = StaticMethod.nullObject2String(request.getAttribute("flowName"));
		}
		IOvertimeTipManager mgr = (IOvertimeTipManager) getBean("iOvertimeTipManager");

		HashMap timeMap = null;
		List timeList = null;
		Integer timeTotal = new Integer(0);
		boolean hasCondition=false;
		if(userId.equals("admin")){
			//admin查询超时提醒
			timeMap = mgr.getOvertimeTipByAdmin(flowName, pageIndex, pageSize);
			timeList = (List)timeMap.get("timeList");
			timeTotal = (Integer)timeMap.get("timeTotal");
		}else{
			//普通用户查询超时提醒
			timeMap = mgr.getOvertimeTipByUserId(flowName,userId, pageIndex, pageSize);
			timeList = (List)timeMap.get("timeList");
			timeTotal = (Integer)timeMap.get("timeTotal");
			HashMap overtimeMap = new HashMap();
			if(timeTotal.intValue()>0){
				for(int i=0;i<timeList.size();i++){
					OvertimeTip ot = (OvertimeTip)timeList.get(i);
					String tmpKey = ot.getFlowName()+ot.getSpecialty1()+ot.getSpecialty2()+ot.getSpecialty3()+ot.getSpecialty4();
					if(overtimeMap.containsKey(tmpKey)){
						//如果admin和用户都设置过该类工单的超时提醒，则取最新设置的
						OvertimeTip tmpot = (OvertimeTip)overtimeMap.get(tmpKey);
						if(tmpot.getSetTime().before(ot.getSetTime())){
							overtimeMap.put(tmpKey, ot);
						}
					}else{
						overtimeMap.put(tmpKey, ot);
					}
				}
				timeList = new ArrayList();
				Iterator it = overtimeMap.keySet().iterator();
				while(it.hasNext()){
					timeList.add(overtimeMap.get(it.next()));
					OvertimeTip tmpot = (OvertimeTip)timeList.get(0);
					if(hasCondition==false&&tmpot.getSpecialty1()!=null&&!tmpot.getSpecialty1().equals("")){
						hasCondition = true;
					}
				}
				timeTotal = new Integer(timeList.size());
			}
		}
		if(timeTotal.intValue()==0||userId.equals("admin")||(timeTotal.intValue()>0&&hasCondition==true)){
			//如果没有查询到记录则使用系统默认值
			HashMap condition = new HashMap();
			condition.put("userId", "='system'");
			condition.put("flowName", "='"+flowName+"'");
			List tmptimeList = mgr.getOvertimeTipByCondition(condition);
			if(tmptimeList==null||tmptimeList.size()==0){
				condition.put("flowName", " is null");
				tmptimeList = mgr.getOvertimeTipByCondition(condition);
			}
			OvertimeTip tmpot = (OvertimeTip)tmptimeList.get(0);
			OvertimeTip ot = new OvertimeTip();
			ot.setId(tmpot.getId());
			ot.setOvertimeLimit(tmpot.getOvertimeLimit());
			ot.setUserId(tmpot.getUserId());
			ot.setFlowName(flowName);
			timeList.add(ot);
			timeTotal = new Integer(timeTotal.intValue()+1);
		}

		request.setAttribute("timeList", timeList);
		request.setAttribute("timeTotal", timeTotal);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("flowName", flowName);
		
		//根据配置文件显示页面
		HashMap map = OvertimeTipUtil.getColumnByMapping(flowName);
		if(map.size()>0)
			request.setAttribute("columnMap", map);
		
		return mapping.findForward("list");
	}
	/**
     * 默认执行方法
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     * @author jialei
     */
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String flowName = StaticMethod.nullObject2String(request.getParameter("flowName"));
		request.setAttribute("flowName", flowName);
		return search(mapping, form, request, response);
	}

	/**
	 * 显示保存和修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * @author jialei
	 */
	public ActionForward showInputPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'showInputPage' method");
		}
		String id = StaticMethod.nullObject2String(request.getParameter("id"));
		String flowName = StaticMethod.nullObject2String(request.getParameter("flowName"));
		IOvertimeTipManager mgr = (IOvertimeTipManager) getBean("iOvertimeTipManager");
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
		String userId = sessionform.getUserid();
		OvertimeTip overtimeTip = null;
		if(id.equals("")){
			HashMap condition = new HashMap();
			condition.put("userId", "='system'");
			condition.put("flowName", "='"+flowName+"'");
			List timeList = mgr.getOvertimeTipByCondition(condition);
			if(timeList==null||timeList.size()==0){
				condition.put("flowName", " is null");
				timeList = mgr.getOvertimeTipByCondition(condition);
			}
			overtimeTip = (OvertimeTip)timeList.get(0);
			overtimeTip.setUserId(userId);
		}else{
			overtimeTip = mgr.getOvertimeTip(id);
		}

		//判断用户是否可以保存和删除
		if(id.equals("")){
			request.setAttribute("isCanSave", "1");
			request.setAttribute("isCanDelete", "0");
		}else{
			if(overtimeTip.getUserId().equals("system")&&userId.equals("admin")){
				request.setAttribute("isCanSave", "1");
				request.setAttribute("isCanDelete", "0");
			}else if(overtimeTip.getUserId().equals(userId)){
				request.setAttribute("isCanSave", "1");
				request.setAttribute("isCanDelete", "1");
			}else if(overtimeTip.getUserId().equals("admin")){
				request.setAttribute("isCanSave", "1");
				request.setAttribute("isCanDelete", "0");
			}else{
				request.setAttribute("isCanSave", "0");
				request.setAttribute("isCanDelete", "0");
			}
		}
		overtimeTip.setFlowName(flowName);
		request.setAttribute("overtimetip", overtimeTip);
		request.setAttribute("userId", userId);
		
		if(!overtimeTip.getUserId().equals("system")){
			//配置默认值
			HashMap defaultValueMap = new HashMap();
			defaultValueMap.put("specialty1", overtimeTip.getSpecialty1());
			defaultValueMap.put("specialty2", overtimeTip.getSpecialty2());
			defaultValueMap.put("specialty3", overtimeTip.getSpecialty3());
			defaultValueMap.put("specialty4", overtimeTip.getSpecialty4());
			defaultValueMap.put("specialty5", overtimeTip.getSpecialty5());
			defaultValueMap.put("specialty6", overtimeTip.getSpecialty6());
			defaultValueMap.put("specialty7", overtimeTip.getSpecialty7());
			defaultValueMap.put("specialty8", overtimeTip.getSpecialty8());
			defaultValueMap.put("specialty9", overtimeTip.getSpecialty9());
			defaultValueMap.put("specialty10", overtimeTip.getSpecialty10());
			//根据配置文件显示页面
			HashMap map = OvertimeTipUtil.getHtmlByMapping(flowName,overtimeTip);
			if(map.size()>0){
				List list = (List)map.get("columnList");
				HashMap columnMap = (HashMap)map.get("columnMap");
				HashMap htmlMap = (HashMap)map.get("htmlMap");
				request.setAttribute("columnList", list);
				request.setAttribute("columnMap", columnMap);
				request.setAttribute("htmlMap", htmlMap);
				request.setAttribute("defaultValue", defaultValueMap);
			}
		}
		
		return mapping.findForward("input");
	}
	
}

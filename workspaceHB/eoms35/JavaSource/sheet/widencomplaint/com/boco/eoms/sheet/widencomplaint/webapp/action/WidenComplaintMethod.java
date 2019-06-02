package com.boco.eoms.sheet.widencomplaint.webapp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.bind.RequestUtils;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.commons.system.user.service.ITawSystemUserRefRoleManager;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefine;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefineExplain;
import com.boco.eoms.sheet.base.util.flowdefine.xml.PhaseId;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.interfaceBase.model.WfInterfaceInfo;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceInfoManager;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceOperateManager;
import com.boco.eoms.sheet.interfaceBase.util.XmlUtil;
import com.boco.eoms.sheet.overtimetip.service.IOvertimeTipManager;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;
import com.boco.eoms.sheet.widencomplaint.model.WidenComplaintLink;
import com.boco.eoms.sheet.widencomplaint.model.WidenComplaintMain;
import com.boco.eoms.sheet.widencomplaint.model.WidenComplaintTask;
import com.boco.eoms.sheet.widencomplaint.service.IWidenComplaintLinkManager;
import com.boco.eoms.sheet.widencomplaint.service.IWidenComplaintMainManager;
import com.boco.eoms.sheet.widencomplaint.service.IWidenComplaintTaskManager;

/**
 * <p>
 * Title:家宽投诉处理工单
 * </p>
 * <p>
 * Description:家宽投诉处理工单
 * </p>
 * <p>
 * Mon Feb 01 17:09:53 CST 2016
 * </p>
 * 
 * @author lizhi
 * @version 3.5
 * 
 */
 
 public class WidenComplaintMethod extends BaseSheet  {
     
     public String getPageColumnName() {
		
		return super.getPageColumnName()+"gatherPerformer@java.lang.String,gatherPerformerLeader@java.lang.String,"
		+ "gatherPerformerType@java.lang.String,gatherPhaseId@java.lang.String,";
		
	}
     
    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception   {
        
        HashMap hashMap = new HashMap();

    	HashMap sheetMap = new HashMap();
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
		BaseMain main = (BaseMain) this.getMainService().getMainObject().getClass().newInstance();
		if(!sheetKey.equals("")){
			sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			
		} 
		if(!sheetKey.equals("")){
			main = this.getMainService().getSingleMainPO(sheetKey);
		} 
		sheetMap.put("main", main);	
		sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
		sheetMap.put("operate", getPageColumnName());
		hashMap.put("selfSheet",sheetMap);
    	
    	return hashMap;
    }
    /**
     * 提交流程引擎前作最后一次参数处理
     */
     public void dealFlowEngineMap(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	super.dealFlowEngineMap(mapping, form, request, response);
    	
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
		HashMap sheetMap = this.getFlowEngineMap();
		
		Map main = (HashMap) sheetMap.get("main");
		Map operate = (HashMap)sheetMap.get("operate");
		Map link = (HashMap) sheetMap.get("link");
		
		String[] dealperformers = (StaticMethod.nullObject2String(operate.get("dealPerformer"))).split(",");

		if(taskName.equals("reply") || taskName.equals("advice"))
		{   			
			link.put("id", "");			
		}
		
		if(dealperformers.length>=1){
			
			String corrKey = "";
			String tmp = "";
			for(int i=0;i<dealperformers.length;i++){
				tmp =  UUIDHexGenerator.getInstance().getID();
				if(dealperformers.length == 1){
					corrKey = tmp;
				}else{
					if(corrKey.equals("")){
						corrKey = tmp;
					}else{
						corrKey = corrKey + "," + tmp;	
					}
					
				}
			}
			System.out.println("corrKey"+corrKey);
			operate.put("extendKey1", corrKey);
		}
		
		//将  现场测速照片 与 现场服务回执单 附件放到 nodeAccessories 中，以便在附件汇总中显示
		if("ExcuteHumTask".equals(taskName) && operateType.equals("102")){
			String linkSpeed = StaticMethod.nullObject2String(link.get("linkSpeed"));
			//'2017092601432341111.txt','2017092601432341122.txt'
			String linkReceipt = StaticMethod.nullObject2String(link.get("linkReceipt"));
			String nodeAccessories = StaticMethod.nullObject2String(link.get("nodeAccessories"));
			
			String temAccess = "";
			if(!"".equals(linkSpeed)){
				temAccess = linkSpeed;
			}
			if(!"".equals(linkSpeed) && !"".equals(linkReceipt)){
				temAccess = temAccess +","+linkReceipt;
			}else if("".equals(linkSpeed) && !"".equals(linkReceipt)){
				temAccess = linkReceipt;
			}
			String linkSpeedName = "";  
			if(!"".equals(linkSpeed)){
				linkSpeedName = photoStr(linkSpeed);
			}
			
			String linkReceiptName = "";  
			if(!"".equals(linkReceipt)){
				linkReceiptName = photoStr(linkReceipt);
			}
			link.put("linkSpeedName", linkSpeedName);
			link.put("linkReceiptName", linkReceiptName);
			
			
			if(!"".equals(temAccess)&&!"".equals(nodeAccessories)){
				nodeAccessories = nodeAccessories +","+temAccess;
			}else if(!"".equals(temAccess)&&"".equals(nodeAccessories)){
				nodeAccessories = temAccess;
			}
			link.put("nodeAccessories", nodeAccessories);
			
		}
		
		
		
		String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-crm.xml").getProperty("base.SendImmediately"));
		if (StaticMethod.nullObject2String(main.get("mainInterfaceSheetType")).equalsIgnoreCase("crm"))
		{
			if (!sendImmediately.equalsIgnoreCase("true"))
				if (taskName.equals("ExcuteHumTask") && operateType.equals("4"))
				{
					operate.put("interfaceType", "withdrawWorkSheet");
					operate.put("methodType", "withdrawWorkSheet");
				}else if (operateType.equals("9")){
					operate.put("interfaceType", "notifyWorkSheet");
					operate.put("methodType", "notifyWorkSheet");
				} else
				if (taskName.equals("FirstExcuteHumTask") && operateType.equals("61"))
				{
					String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
					ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean("iWidenComplaintTaskManager");
					ITask task = iTaskService.getSinglePO(taskId);
					if (task != null && !StaticMethod.nullObject2String(task.getSubTaskFlag()).equalsIgnoreCase("true"))
					{
						operate.put("interfaceType", "confirmWorkSheet");
						operate.put("methodType", "confirmWorkSheet");
					}
				} else
				if (taskName.equalsIgnoreCase("DeferExamineHumTask") && operateType.equalsIgnoreCase("66") && StaticMethod.nullObject2String(request.getParameter("mainDelayFlag")).equals("1"))
				{
					operate.put("interfaceType", "notifyWorkSheet");
					operate.put("methodType", "notifyWorkSheet");
				}
			if (operateType.equals("102"))
			{
				operate.put("interfaceType", "replyWorkSheet");
				operate.put("methodType", "replyWorkSheet");
				operate.put("sendType", new Integer("2"));
			}
			if (operateType.equals("103") && taskName.equals("CheckingHumTask"))
			{
				String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
				System.out.println("------质检环节------sheetkey-" + sheetKey);
				IWfInterfaceInfoManager iwfinterfaceinfoManager = (IWfInterfaceInfoManager)ApplicationContextHolder.getInstance().getBean("iWfInterfaceInfoManager");
				iwfinterfaceinfoManager.updateInfoForSend(sheetKey, "replyWorkSheet", "replyWorkSheet");
			}
		}
		
		if (taskName.equals("ExcuteHumTask") && operateType.equals("102"))
		{
//			String customAttribution = StaticMethod.nullObject2String(main.get("customAttribution"));
//			if (!"".equals(customAttribution)) {
//				customAttribution = customAttribution.substring(3);
//				String customAttributionValue = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty(customAttribution));
//				operate.put("copyPerformer", customAttributionValue);
//				operate.put("copyPerformerLeader", customAttributionValue);
//				operate.put("copyPerformerType", "subrole");
//			}
			
			String sheetId = StaticMethod.nullObject2String(main.get("sheetId"));
			String issueReasonTypeThree = StaticMethod.nullObject2String(link.get("issueReasonTypeThree"));
			String sheetIdMantissa = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("sheetIdMantissa"));
			String issueReasonTypeThreeIndex = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("issueReasonTypeThreeIndex"));
			// 判断是否自动转单
			if (sheetIdMantissa.indexOf(sheetId.substring(sheetId.length()-1)) == -1 || issueReasonTypeThreeIndex.indexOf(issueReasonTypeThree) == -1) {
				Date opeTime = new Date();
				String prelinkId = StaticMethod.nullObject2String(link.get("id"));
				String subroleid = StaticMethod.nullObject2String(main.get("sendRoleId"));
				System.out.println("lizhi:opeTime=" + opeTime + "prelinkId=" + prelinkId + "subroleid=" + subroleid);
				operate.put("sendType", new Integer("0"));
				operate.put("phaseId", "HoldTask");
				operate.put("dealPerformer", subroleid);
				operate.put("dealPerformerLeader", subroleid);
				operate.put("dealPerformerType", "subrole");
				createCheckingLink(main, opeTime, prelinkId, subroleid);
				createCheckingTask(main, prelinkId);
			}
		}
		
		if (operateType.equals("104") && taskName.equals("CheckingHumTask"))
		{
			String preLinkId = StaticMethod.nullObject2String(link.get("preLinkId"));
			if (!"".equals(preLinkId)) {
				ILinkService linkService = (ILinkService)ApplicationContextHolder.getInstance().getBean("iWidenComplaintLinkManager");
				BaseLink prelink = linkService.getSingleLinkPO(preLinkId);
				String operateRoleId = prelink.getOperateRoleId();
				if (!"".equals(operateRoleId)) {
					operate.put("dealPerformer", operateRoleId);
					operate.put("dealPerformerLeader", operateRoleId);
					operate.put("dealPerformerType", "subrole");
				}
			}
		}
		sheetMap.put("link", link);
		sheetMap.put("operate", operate);

		this.setFlowEngineMap(sheetMap);
    }
    
    
    /**
     * 设置需要从流程引擎中取的派往对象，包括派发，抄送，送审
     */
    public Map getProcessOjbectAttribute() {
     	Map  attributeMap = new HashMap();
     	attributeMap.put("dealPerformer","dealPerformer");
        attributeMap.put("copyPerformer","copyPerformer");
     	attributeMap.put("auditPerformer","auditPerformer");
     	attributeMap.put("subAuditPerformer","subAuditPerformer");
     	attributeMap.put("objectName", "operate");
  		return attributeMap;	
 	}
 	
	public Map getParameterMap() {
		Map  attributeMap = new HashMap();
		return attributeMap;
	} 
    
    /**
     * 设置main和link保存附件字段属性
     */
	 public Map getAttachmentAttributeOfOjbect() {
		Map objectMap = new HashMap();
		
		List mainAttachmentAttributes = new ArrayList();
		mainAttachmentAttributes.add("sheetAccessories");

		List linkAttachmentAttributes = new ArrayList();
		linkAttachmentAttributes.add("nodeAccessories");
			
		objectMap.put("mainObject", mainAttachmentAttributes);
		objectMap.put("linkObject", linkAttachmentAttributes);
		return objectMap;
	}
	 /**
     * 进入处理环节
     */
	public void showInputDealPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        super.showInputDealPage(mapping, form, request, response);
   		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
        String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"));
        //驳回上一级（不是移交的任务），需要取出上一级的角色和phaseId
        if (operateType.equals("4")) {
			BaseLink  prelink = this.getLinkService().getSingleLinkPO(preLinkId);
			if (prelink != null) {
				request.setAttribute("fOperateroleid", prelink.getOperateRoleId());
				request.setAttribute("fPreTaskName", prelink.getActiveTemplateId());
			}
		}
		//如果是移交后的任务被驳回 
		if (operateType.equals("4")) {
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			IWidenComplaintLinkManager service = (IWidenComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintLinkManager");
			String taskId = StaticMethod.nullObject2String(request.getParameter("taskId"));
			String condition = " mainId='" + sheetKey + "' and operateType=8 and aiid='" + taskId + "' order by operateTime desc";
			List linkList = service.getLinksBycondition(condition);
			if (linkList != null && linkList.size() > 0) {
				BaseLink prelink = (BaseLink)linkList.get(0);
				request.setAttribute("fOperateroleid", prelink.getOperateRoleId());
				request.setAttribute("fPreTaskName", prelink.getActiveTemplateId());
			}
		}
		
    }
 
	/**
	 * 显示未处理列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showListUndo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap filterMap = new HashMap();
		filterMap.put("TEMPLATE_NAME", getMainService().getFlowTemplateName());

		/** 获取登陆信息，add by qinmin* */
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		/** add by qinmin* */

		// 获取每页显示条数
		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
				.getInstance().getBean("SheetAttributes")).getPageSize();
		// 当前页数
		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		final Integer pageIndex = new Integer(GenericValidator
				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
		String order = StaticMethod
				.null2String(request
						.getParameter(new org.displaytag.util.ParamEncoder(
								"taskList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_ORDER)));
		String sort = StaticMethod
				.null2String(request
						.getParameter(new org.displaytag.util.ParamEncoder(
								"taskList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_SORT)));
		String orderCondition = "";
		if (!order.equals("")) {
			if (order.equals("1")) {
				order = " asc";
			} else {
				order = " desc";
			}
		}
		if (!sort.equals("")) {
			orderCondition = " " + sort + order;
		}
		String exportType = StaticMethod
				.null2String(request
						.getParameter(new org.displaytag.util.ParamEncoder(
								"taskList")
								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
		if (!exportType.equals("")) {
			pageSize = new Integer(-1);
		}
		String userId = sessionform.getUserid();
		String deptId = sessionform.getDeptid();
		// 增加map对象，存放main，传入taskService.getUndoTask
		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject()
				.getClass().newInstance();
		ITask taskObject = (ITask) this.getTaskService().getTaskModelObject()
				.getClass().newInstance();
		Map condition = new HashMap();
		condition.put("mainObject", mainObject);
		condition.put("taskObject", taskObject);
		condition.put("orderCondition", orderCondition);
		// HashMap taskListMap = taskService.getUndoTask(condition, userId,
		// deptId, this.getMainService().getFlowTemplateName(), pageIndex,
		// pageSize);
		// int total = ((Integer) taskListMap.get("taskTotal")).intValue();
		// List taskList = (List) taskListMap.get("taskList");

		// 得到待处理列表
		String flowName = this.getMainService().getFlowTemplateName();
		HashMap taskListOvertimeMap = this.getTaskService()
				.getUndoTaskByOverTime(condition, userId, deptId, flowName,
						pageIndex, pageSize);
		int total = ((Integer) taskListOvertimeMap.get("taskTotal")).intValue();
		List taskOvertimeList = (List) taskListOvertimeMap.get("taskList");
		List taskMapList = new ArrayList();
		List taskList = new ArrayList();
		// 设置每条task超时标识
		if (taskOvertimeList != null && taskOvertimeList.size() > 0) {
			// 查询超时配置表
			IOvertimeTipManager service = (IOvertimeTipManager) ApplicationContextHolder
					.getInstance().getBean("iOvertimeTipManager");
			List timeList = service.getEffectOvertimeTip(this.getMainService()
					.getFlowTemplateName(), userId);
			// 得到角色细分字段
			HashMap columnMap = OvertimeTipUtil
					.getAllMainColumnByMapping(flowName);
			System.out.println("wanghao1====cloumnMap:"+columnMap.size());
			HashMap columnMapOverTip = OvertimeTipUtil
					.getNotOverTimeColumnByMapping(flowName);
			
			IWidenComplaintMainManager widenComplaintMainManager = (IWidenComplaintMainManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintMainManager");
			int time = StaticMethod.nullObject2int(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("RepeatComplaintTime"));
			Calendar cal=Calendar.getInstance();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			// 循环为task超时标识赋值
			for (int i = 0; i < taskOvertimeList.size(); i++) {
				ITask tmptask = null;
				Map taskMap = new HashMap();
				Map tmptaskMap = new HashMap();
				HashMap conditionMap = new HashMap();
				if (columnMap.size() > 0) { 
					System.out.println("=========wanghao1============"); 
					Object[] tmpObjArr = (Object[]) taskOvertimeList.get(i);
					//tmptask = (ITask) tmpObjArr[columnMap.size()];
					tmptask = (ITask) tmpObjArr[0];
					// 根据角色细分得到需要匹配的字段
					Iterator it = columnMap.keySet().iterator();
					int j = 0;
					while (it.hasNext()) {
						j++;
						String elementKey = (String) it.next();
						Object tempcolumn = tmpObjArr[j];
						conditionMap.put(elementKey, tempcolumn);
						tmptaskMap.put(columnMap.get(elementKey), tempcolumn);
					}
				} else {
					tmptask = (ITask) taskOvertimeList.get(i);
				}
				// 得到超时类型
				if (exportType.equals("")) {
					String overtimeFlag = OvertimeTipUtil.setOvertimeTipFlag(
							columnMapOverTip, tmptask.getCompleteTimeLimit(),
							conditionMap, timeList, flowName);
					taskMap.put("overtimeType", overtimeFlag);
					long overtime = OvertimeTipUtil.getOvertime(
							columnMapOverTip, tmptask.getCompleteTimeLimit(),
							conditionMap, timeList, flowName);
					taskMap.put("overtime", new Long(overtime));
				}
				
				String customPhone=StaticMethod.nullObject2String(tmptaskMap.get("customPhone"));
				Date complaintTime=StaticMethod.nullObject2Timestamp(tmptaskMap.get("complaintTime"));
				cal.setTime(complaintTime);
				cal.add(Calendar.DATE, time);
				String beforedate = sdf.format(cal.getTime());
				String afterdate = sdf.format(complaintTime );
				System.out.println("lizhi:beforedate="+beforedate+"afterdate="+afterdate+"customPhone="+customPhone);
				int repeatNum = widenComplaintMainManager.getCustomPhoneBySendTime(beforedate,afterdate,customPhone);
				if(repeatNum==0){
					 taskMap.put("customPhoneRepeatCount", "1(无历史投诉)");
				 }else if(repeatNum==1){
					 taskMap.put("customPhoneRepeatCount", "1(无历史投诉)");
				 }else{
					 taskMap.put("customPhoneRepeatCount", repeatNum+"(重复投诉)" );
				 }
				taskMap.putAll(SheetBeanUtils.bean2Map(tmptask));
				taskMap.putAll(tmptaskMap);
				taskList.add(tmptask);
				taskMapList.add(taskMap);
			}
		}

		// 将分页后的列表写入页面供使用
		Integer overTimeTaskCount = this.getTaskService().getOverTimeTaskCount(condition, userId, deptId);
		request.setAttribute("taskList", taskMapList);
		request.setAttribute("total", new Integer(total));
		request.setAttribute("overTimeTaskCount", overTimeTaskCount);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("findForward", "list");
		request.setAttribute("module", mapping.getPath().substring(1));

		// 找出该流程中的节点
		String workflowName = this.getMainService().getFlowTemplateName();
		ArrayList phaseIdList = new ArrayList();
		Map phaseIdMap = new HashMap();
		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(
				workflowName, this.getRoleConfigPath());
		FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
		if (flowDefine != null) {
			PhaseId phaseIds[] = flowDefine.getPhaseId();
			for (int i = 0; i < phaseIds.length; i++) {
				PhaseId phaseId = phaseIds[i];
				if (!phaseId.getId().equals("receive")) {
					phaseIdMap.put(phaseId.getId(), phaseId.getName());
					phaseIdList.add(phaseId.getId());
				}
			}
		}
		request.setAttribute("phaseIdMap", phaseIdMap);
		request.setAttribute("stepIdList", phaseIdList);

		// 需要进行批量回复和批量归档的节点
		String batch = StaticMethod.null2String(request.getParameter("batch"));
		if (!batch.equals("") && batch.equals("true")) {
			// 需要进行批量回复和批量归档的步骤放入到tempMap中
			Map tempMap = new HashMap();
			String dictName = "dict-sheet-" + mapping.getPath().substring(1);
			List dictItems = DictMgrLocator.getDictService().getDictItems(
					Util.constituteDictId(dictName, "activeTemplateId"));
			for (Iterator it = dictItems.iterator(); it.hasNext();) {
				DictItemXML dictItemXml = (DictItemXML) it.next();
				String description = dictItemXml.getDescription();
				if (description.equals("batch:true")) {
					tempMap.put(dictItemXml.getItemId(), dictItemXml
							.getItemName());
				}
			}

			// 和所查找的任务列表进行对比，如果该列表中有批量回复和批量归档的步骤就放入到batchTaskMap中
			Map batchTaskMap = new HashMap();
			if (tempMap.size() > 0) {
				for (Iterator it = tempMap.keySet().iterator(); it.hasNext();) {
					String taskName = (String) it.next();
					for (Iterator tasks = taskList.iterator(); tasks.hasNext();) {
						ITask task = (ITask) tasks.next();
						if (taskName.equals(task.getTaskName())
								&& (task.getSubTaskFlag() == null
										|| task.getSubTaskFlag()
												.equals("false") || task
										.getSubTaskFlag().equals(""))) {
							batchTaskMap.put(task.getTaskName(), task
									.getTaskDisplayName());
							break;
						} else {
							continue;
						}
					}
				}
			}

			request.setAttribute("batchTaskMapKey", batchTaskMap.keySet());
			request.setAttribute("batchTaskMap", batchTaskMap);
		}
	}
	
	public void showDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{	
		
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
		if (sheetKey.equals("")) {
			sheetKey = StaticMethod.nullObject2String(request.getAttribute("sheetKey"), "");
		}
		WidenComplaintMain main =(WidenComplaintMain) this.getMainService().getSingleMainPO(sheetKey);
		String mainJkaccount = StaticMethod.null2String(main.getMainJkaccount());
		String customPhone = StaticMethod.null2String(main.getCustomPhone());
		String mainId = StaticMethod.null2String(main.getId());
		IDownLoadSheetAccessoriesService mgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder
        .getInstance().getBean("IDownLoadSheetAccessoriesService");
//		String sql = "SELECT M.ID, M.MAINJKACCOUNT, M.SHEETID, M.COMPLAINTTIME, M.CUSTOMPHONE, M.COMPLAINTDESC, M.SENDTIME, L.DEALDESC  FROM WIDENCOMPLAINT_MAIN M, WIDENCOMPLAINT_LINK L ,(SELECT l.mainid,MAX(l.operatetime) operatetime  FROM WIDENCOMPLAINT_MAIN M, WIDENCOMPLAINT_LINK L WHERE M.DELETED = '0' AND M.SENDTIME >= TRUNC(SYSDATE - 25)  AND m.ID=l.mainid AND l.activetemplateid ='102'  GROUP BY l.mainid ) t WHERE M.DELETED = '0' AND m.MAINJKACCOUNT IS NOT NULL AND m.MAINJKACCOUNT NOT LIKE '无宽带账号' AND M.SENDTIME >= TRUNC(SYSDATE - 25) AND (M.MAINJKACCOUNT = '"+mainJkaccount+"' OR  M.CUSTOMPHONE = '"+customPhone+"') AND M.ID = L.MAINID(+) AND l.activetemplateid (+)='102' AND l.mainid=t.mainid(+) AND l.operatetime=t.operatetime(+)  AND M.ID <> '"+mainId+"' ORDER BY M.SENDTIME";
		String sql = "select distinct m.id,m.mainJkaccount,m.sheetid,m.complainttime,m.customPhone,m.complaintDesc,m.sendtime,l.dealDesc  from widencomplaint_main m, widencomplaint_link l where m.deleted = '0'  and m.sendtime >= trunc(sysdate - 5)  and (m.mainJkaccount = '" + mainJkaccount + "' or m.customPhone = '" + customPhone + "') and m.id = l.mainid  and m.id <> '" + mainId + "' order by m.sendtime";
		List totalResults = mgr.getSheetAccessoriesList(sql);
		String mainRepeatNum1 = "";
		if(totalResults != null && totalResults.size()>0){
			mainRepeatNum1 = ((int) totalResults.size())+"";
		}
		request.setAttribute("mainRepeatNum1", mainRepeatNum1);
		
		
		super.showDetailPage(mapping, form, request, response);
		
		String cusPhone = StaticMethod.nullObject2String(request.getParameter("cusPhone"), "");
		String cusPhones[] = cusPhone.split("\\(");
		String phoneNum = cusPhones[0];
		String ifrepeat = "";
		String repeatNum = "";
		if ("1".equals(phoneNum))
		{
			ifrepeat = "否";
			repeatNum = "1";
		} else
		{
			ifrepeat = "是";
			repeatNum = phoneNum;
		}
		request.setAttribute("ifrepeat", ifrepeat);
		request.setAttribute("repeatNum", repeatNum);
		
		String taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
		if (!"".equals(taskStatus) && "4".equals(taskStatus))
		{
			String t1dealer = StaticMethod.nullObject2String(request.getParameter("u"));
			System.out.println("t1dealer's id is===" + t1dealer);
			TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
			ITawSystemUserRefRoleManager manager = (ITawSystemUserRefRoleManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserRefRoleManager");
			List userids = manager.getUserIdBySubRoleids(t1dealer);
			String ift1dealer = "false";
			if (userids != null && userids.size() > 0)
			{
				System.out.println("check it ok!!");
				ift1dealer = userids.contains(sessionform.getUserid()) ? "true" : ift1dealer;
			}
			request.setAttribute("ift1dealer", ift1dealer);
		}
		
		String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"), "");
		if ("ExcuteHumTask".equals(taskName)) {
			sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
			IWidenComplaintLinkManager linkservice = (IWidenComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintLinkManager");
			String condition = "operateType = '8' and activeTemplateId = 'ExcuteHumTask' and mainId = '" + sheetKey + "'";
			List widenComplaintLinkList = linkservice.getLinksBycondition(condition);
			if (widenComplaintLinkList.size()>0) {
				request.setAttribute("transferflag", "false");
			} else {
				request.setAttribute("transferflag", "true");
			}
		}
	}
	
	public void newPerformNonFlow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
	{
		BocoLog.info(this, "===优化======非流程动作===");
		HashMap columnMap = getInterfaceObjMap(mapping, form, request, response);
		Map map = newSetDealRequestMap(mapping, form, request, response);
		Object taskIdObject = map.get("TKID");
		if (taskIdObject != null && taskIdObject.getClass().isArray())
			taskIdObject = ((Object[])taskIdObject)[0];
		String taskId = StaticMethod.nullObject2String(taskIdObject);
		Map serializableMap = SheetUtils.serializableParemeterMap(map);
		Iterator it = serializableMap.keySet().iterator();
		HashMap WpsMap = new HashMap();
		HashMap tempWpsMap;
		for (; it.hasNext(); WpsMap.putAll(tempWpsMap))
		{
			String mapKey = (String)it.next();
			Map tempMap = (Map)serializableMap.get(mapKey);
			if (taskId.equals(""))
			{
				Object obj = tempMap.get("aiid");
				if (obj.getClass().isArray())
				{
					Object obja[] = (Object[])obj;
					obj = obja[0];
				}
				taskId = StaticMethod.nullObject2String(obj);
			}
			HashMap tempColumnMap = (HashMap)columnMap.get(mapKey);
			tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap, tempColumnMap);
		}
	
		setFlowEngineMap(WpsMap);
		dealFlowEngineMap(mapping, form, request, response);
		try
		{
			HashMap tmpMap = getFlowEngineMap();
			newSaveNonFlowData(taskId, tmpMap);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	// 保存数据到link表
	public static WidenComplaintLink createCheckingLink(Map mainrule, Date operateTime, String prelinkId, String subroleid)
	throws Exception
	{
		String autoUser = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("AutoUser"));
		String autoSubRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("AutoSubRole"));
		System.out.println("lizhi:autoUser=" + autoUser + "autoSubRole=" + autoSubRole);
		ITawSystemUserManager tawSystemUserManager = (ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		TawSystemUser tawSystemUser = tawSystemUserManager.getUserByuserid(autoUser);
		String autoDeptId = StaticMethod.nullObject2String(tawSystemUser.getDeptid());
		IWidenComplaintLinkManager linkservice = (IWidenComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintLinkManager");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(operateTime);
		calendar.add(13, 30);
		WidenComplaintLink T1link61 = (WidenComplaintLink)linkservice.getLinkObject().getClass().newInstance();
		T1link61.setId(UUIDHexGenerator.getInstance().getID());
		T1link61.setOperateType(new Integer("61"));
		T1link61.setActiveTemplateId("CheckingHumTask");
		T1link61.setOperateTime(calendar.getTime());
		T1link61.setOperateDay(calendar.get(5));
		T1link61.setOperateMonth(calendar.get(2) + 1);
		T1link61.setOperateYear(calendar.get(1));
		T1link61.setMainId(StaticMethod.nullObject2String(mainrule.get("id")));
		T1link61.setToOrgRoleId(subroleid);
		T1link61.setPreLinkId(prelinkId);
		T1link61.setNodeAccessories("");
		T1link61.setToOrgType(new Integer(0));
		T1link61.setCompleteFlag(new Integer(0));
		T1link61.setOperateUserId(StaticMethod.nullObject2String(autoUser));
		T1link61.setOperateRoleId(StaticMethod.nullObject2String(autoSubRole));
		T1link61.setOperateDeptId(autoDeptId);
		T1link61.setTemplateFlag(0);
		linkservice.addLink(T1link61);
		calendar.add(13, 30);
		WidenComplaintLink T1link = (WidenComplaintLink)linkservice.getLinkObject().getClass().newInstance();
		T1link.setId(UUIDHexGenerator.getInstance().getID());
		T1link.setMainId(StaticMethod.nullObject2String(mainrule.get("id")));
		T1link.setOperateType(new Integer(103));
		T1link.setOperateTime(calendar.getTime());
		T1link.setOperateDay(calendar.get(5));
		T1link.setOperateMonth(calendar.get(2) + 1);
		T1link.setOperateYear(calendar.get(1));
		T1link.setAcceptFlag(new Integer(0));
		T1link.setPreLinkId(prelinkId);
		T1link.setActiveTemplateId("CheckingHumTask");
		T1link.setToOrgType(new Integer(0));
		T1link.setCompleteFlag(new Integer(0));
		T1link.setOperateUserId(StaticMethod.nullObject2String(autoUser));
		T1link.setOperateRoleId(StaticMethod.nullObject2String(autoSubRole));
		T1link.setOperateDeptId(autoDeptId);
		String correlationKey = UUIDHexGenerator.getInstance().getID();
		T1link.setCorrelationKey(correlationKey);
		T1link.setTemplateFlag(0);
		T1link.setPiid(StaticMethod.nullObject2String(mainrule.get("piid")));
		T1link.setToOrgRoleId(subroleid);
		T1link.setNodeAcceptLimit(StaticMethod.nullObject2Timestamp(mainrule.get("sheetAcceptLimit")));
		T1link.setNodeCompleteLimit(StaticMethod.nullObject2Timestamp(mainrule.get("sheetCompleteLimit")));
		T1link.setLinkCheckResult("1030101");
		T1link.setLinkCheckIdea("ok");
		linkservice.addLink(T1link);
		return T1link;
	}
//保存数据到task表
	public static void createCheckingTask(Map mainrule, String preLinkId)
		throws Exception
	{
		String autoUser = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("AutoUser"));
		String autoSubRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("AutoSubRole"));
		IWidenComplaintTaskManager taskservice = (IWidenComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintTaskManager");
		WidenComplaintTask T1Task = (WidenComplaintTask)taskservice.getTaskModelObject().getClass().newInstance();
		try
		{
			T1Task.setId("_TKI:" + UUIDHexGenerator.getInstance().getID());
		}
		catch (Exception e3)
		{
			e3.printStackTrace();
		}
		T1Task.setTaskName("CheckingHumTask");
		T1Task.setTaskDisplayName("质检");
		T1Task.setFlowName("WidenComplaint");
		T1Task.setSendTime(StaticMethod.nullObject2Timestamp(mainrule.get("sendTime")));
		T1Task.setSheetKey(StaticMethod.nullObject2String(mainrule.get("id")));
		T1Task.setTaskStatus("5");
		T1Task.setSheetId(StaticMethod.nullObject2String(mainrule.get("sheetId")));
		T1Task.setTitle(StaticMethod.nullObject2String(mainrule.get("title")));
		T1Task.setOperateType("subrole");
		T1Task.setCreateTime(new Date(System.currentTimeMillis() - 500L));
		T1Task.setCreateYear(StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")));
		T1Task.setCreateMonth(StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")));
		T1Task.setCreateDay(StaticMethod.null2int(StaticMethod.getCurrentDateTime("dd")));
		T1Task.setOperateRoleId(StaticMethod.nullObject2String(autoSubRole));
		T1Task.setTaskOwner(StaticMethod.nullObject2String(autoUser));
		T1Task.setIfWaitForSubTask("false");
		T1Task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
		T1Task.setPreLinkId(StaticMethod.nullObject2String(preLinkId));
		T1Task.setAcceptTimeLimit(StaticMethod.nullObject2Timestamp(mainrule.get("sheetAcceptLimit")));
		T1Task.setCompleteTimeLimit(StaticMethod.nullObject2Timestamp(mainrule.get("sheetCompleteLimit")));
		taskservice.addTask(T1Task);
	}
	
	public void showInputSplitPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.showInputSplitPage(mapping, form, request, response);
		String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
		if ("ExcuteHumTask".equals(taskName)) {
			String splitSubRoleIds = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-util.xml").getProperty("splitSubRoleIds"));
			request.setAttribute("splitSubRoleIds", splitSubRoleIds);
		}
		
	}
	
	//showMainDetailPage
	public void showMainDetailPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String sheetKey = RequestUtils.getStringParameter(request, "sheetKey");
		WidenComplaintMain main =(WidenComplaintMain) this.getMainService().getSingleMainPO(sheetKey);
		String mainJkaccount = StaticMethod.null2String(main.getMainJkaccount());
		String customPhone = StaticMethod.null2String(main.getCustomPhone());
		String mainId = StaticMethod.null2String(main.getId());
		IDownLoadSheetAccessoriesService mgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder
        .getInstance().getBean("IDownLoadSheetAccessoriesService");
//		String sql = "SELECT M.ID, M.MAINJKACCOUNT, M.SHEETID, M.COMPLAINTTIME, M.CUSTOMPHONE, M.COMPLAINTDESC, M.SENDTIME, L.DEALDESC  FROM WIDENCOMPLAINT_MAIN M, WIDENCOMPLAINT_LINK L ,(SELECT l.mainid,MAX(l.operatetime) operatetime  FROM WIDENCOMPLAINT_MAIN M, WIDENCOMPLAINT_LINK L WHERE M.DELETED = '0' AND M.SENDTIME >= TRUNC(SYSDATE - 25)  AND m.ID=l.mainid AND l.activetemplateid ='102'  GROUP BY l.mainid ) t WHERE M.DELETED = '0' AND m.MAINJKACCOUNT IS NOT NULL AND m.MAINJKACCOUNT NOT LIKE '无宽带账号' AND M.SENDTIME >= TRUNC(SYSDATE - 25) AND (M.MAINJKACCOUNT = '"+mainJkaccount+"' OR  M.CUSTOMPHONE = '"+customPhone+"') AND M.ID = L.MAINID(+) AND l.activetemplateid (+)='102' AND l.mainid=t.mainid(+) AND l.operatetime=t.operatetime(+)  AND M.ID <> '"+mainId+"' ORDER BY M.SENDTIME";
		String sql = "select distinct m.id,m.mainJkaccount,m.sheetid,m.complainttime,m.customPhone,m.complaintDesc,m.sendtime,l.dealDesc  from widencomplaint_main m, widencomplaint_link l where m.deleted = '0'  and m.sendtime >= trunc(sysdate - 5)  and (m.mainJkaccount = '" + mainJkaccount + "' or m.customPhone = '" + customPhone + "') and m.id = l.mainid  and m.id <> '" + mainId + "' order by m.sendtime";
		List totalResults = mgr.getSheetAccessoriesList(sql);
		String mainRepeatNum1 = "";
		if(totalResults != null && totalResults.size()>0){
			mainRepeatNum1 = ((int) totalResults.size())+"";
		}
		request.setAttribute("mainRepeatNum1", mainRepeatNum1);
		super.showMainDetailPage(mapping, form, request, response);
	}
	
	
	
	 /**
	   * 
	   * @param photo '20160520140918.gif','20160520140939.gif'
	   * @return
	   */
	  public String photoStr (String photo){
		  IDownLoadSheetAccessoriesService services = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
		  String accessoriesSql = "select * from taw_commons_accessories where accessoriesname in ("+photo+")";
		  String accessoresRet = "";
		  try {
			  if(photo != null && !"".equals(photo)){
				  List accessorList = services.getSheetAccessoriesList(accessoriesSql);
				  for(int i=0;accessorList != null&&i<accessorList.size();i++){
					  String accessoriescnname = StaticMethod.nullObject2String(((Map) accessorList.get(i)).get("accessoriescnname"));
					  String temAccessores = accessoriescnname+",";
					  accessoresRet = accessoresRet +temAccessores;
				  }
			  }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("查询附件报错");
			e.printStackTrace();
		}
		if(!"".equals(accessoresRet)){
			accessoresRet = accessoresRet.substring(0, accessoresRet.lastIndexOf(","));
		}
		  return accessoresRet;
	  }
	  
	  
	  
		public void performPreCommit(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			
			IWidenComplaintTaskManager taskservice = (IWidenComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iWidenComplaintTaskManager");
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
			
			WidenComplaintMain main = (WidenComplaintMain)getMainService().getSingleMainPO(sheetKey);
			String condition = " sheetKey = '" + sheetKey + "' and taskstatus in ('8','2') and taskName ='ExcuteHumTask'  ORDER BY createtime DESC ";
	        List taskList = taskservice.getTasksByCondition(condition);
			
	        WidenComplaintLink linkbean = new WidenComplaintLink();
			String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
			String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
			
			String remark = StaticMethod.nullObject2String(request.getParameter("remark"));
			String ndeptContact = StaticMethod.nullObject2String(request.getParameter("ndeptContact"));
			String ndeptContactPhone = StaticMethod.nullObject2String(request.getParameter("ndeptContactPhone"));
			String compProp = StaticMethod.nullObject2String(request.getParameter("compProp"));
			String isReplied = StaticMethod.nullObject2String(request.getParameter("isReplied"));
			String issueEliminatTimeStr = StaticMethod.nullObject2String(request.getParameter("issueEliminatTime"));
			String issueEliminatReason = StaticMethod.nullObject2String(request.getParameter("issueEliminatReason"));
			String linkDealDesc = StaticMethod.nullObject2String(request.getParameter("linkDealDesc"));
			String dealResult = StaticMethod.nullObject2String(request.getParameter("dealResult"));
			
			linkbean.setActiveTemplateId(taskName);
			linkbean.setOperateType(new Integer(operateType));
			linkbean.setOperateUserId(sessionform.getUserid());
			
			Date issus = SheetUtils.stringToDate(issueEliminatTimeStr);
			if(!"".equals(issueEliminatTimeStr)&& issus != null){
				linkbean.setIssueEliminatTime(issus);
			}
			linkbean.setRemark(remark);
			linkbean.setNdeptContact(ndeptContact);
			linkbean.setNdeptContactPhone(ndeptContactPhone);
			linkbean.setCompProp(compProp);
			linkbean.setIsReplied(isReplied);
			linkbean.setIssueEliminatReason(issueEliminatReason);
			linkbean.setLinkDealDesc(linkDealDesc);
			linkbean.setDealResult(dealResult);
			
			
			WidenComplaintTask task = new WidenComplaintTask();
			if ((taskList != null) && (taskList.size() > 0)) {
				task = (WidenComplaintTask)taskList.get(0);
			}
			String interfaceBeanId = XmlUtil.getInterfaceBeanIdByMainBeanId("iWidenComplaintMainManager");
			IWfInterfaceOperateManager operateMgr = (IWfInterfaceOperateManager)ApplicationContextHolder.getInstance().getBean(interfaceBeanId);
			
			String status = "0";
			String text = "";
			
			System.out.println("widencomplaintsheet==="+main.getSheetId());
			
			boolean returnType = true;
			WfInterfaceInfo info = new WfInterfaceInfo();
			//T1驳回给建单人 调用接口，返回成功就流转、不成功不流转
			String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/widencomplaint-crm.xml").getProperty("base.SendImmediately"));
			String linkId = StaticMethod.nullObject2String(task.getId());
			String isSended = "0";
			String mainBeanId = "iWidenComplaintMainManager";
			String linkBeanId = "iWidenComplaintTaskManager";
			String interfaceType = "";
			String methodType = "";
			if (StaticMethod.nullObject2String(main.getMainInterfaceSheetType()).equalsIgnoreCase("crm"))
			{
				if (!sendImmediately.equalsIgnoreCase("true")){
					if (operateType.equals("4") && taskName.equals("ExcuteHumTask")){ //驳回
//						String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.InterfaceUser"));
//						String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.SendRoleId"));
//						String dealPerformer = StaticMethod.nullObject2String(request.getParameter("dealPerformer"));
//						System.out.println("lyg:dealPerformer=" + dealPerformer);
//						if (dealPerformer.equals(userId) || dealPerformer.equals(sendRoleId))
//						{	
							interfaceType = "withdrawWorkSheet";
							methodType = "withdrawWorkSheet";
							
							info.setInterfaceType(interfaceType);
							info.setSheetKey(sheetKey);
							info.setLinkId(linkId);
							info.setIsSended(isSended);
							info.setMainBeanId(mainBeanId);
							info.setLinkBeanId(linkBeanId);
							info.setMethodType(methodType);
							
							System.out.println("widencomplaintsheet==="+main.getSheetId()+"====lyg:returnType=4=befoer=="+returnType);
							returnType = operateMgr.sendData(info,linkbean);
							System.out.println("widencomplaintsheet==="+main.getSheetId()+"====lyg:returnType=4=after=="+returnType);
							
							if(!returnType){//返回的结果是false
								text = "调用客服接口错误！";
								status = "2";
							}
//						}
					} else if (taskName.equals("ExcuteHumTask") && operateType.equals("61")){//T1确认受理
							interfaceType = "confirmWorkSheet";
							methodType = "confirmWorkSheet";
							
							info.setInterfaceType(interfaceType);
							info.setSheetKey(sheetKey);
							info.setLinkId(linkId);
							info.setIsSended(isSended);
							info.setMainBeanId(mainBeanId);
							info.setLinkBeanId(linkBeanId);
							info.setMethodType(methodType);
							
							System.out.println("widencomplaintsheet==="+main.getSheetId()+"====lyg:returnType=61=befoer=="+returnType);
							returnType = operateMgr.sendData(info,linkbean);
							System.out.println("widencomplaintsheet==="+main.getSheetId()+"====lyg:returnType=61=after=="+returnType);
					}else if(taskName.equals("ExcuteHumTask") && operateType.equals("102")){
						interfaceType = "replyWorkSheet";
						methodType = "replyWorkSheet";
						
						info.setInterfaceType(interfaceType);
						info.setSheetKey(sheetKey);
						info.setLinkId(linkId);
						info.setIsSended(isSended);
						info.setMainBeanId(mainBeanId);
						info.setLinkBeanId(linkBeanId);
						info.setMethodType(methodType);
						
						System.out.println("widencomplaintsheet==="+main.getSheetId()+"====lyg:returnType=102=befoer=="+returnType);
						returnType = operateMgr.sendData(info,linkbean);
						System.out.println("widencomplaintsheet==="+main.getSheetId()+"====lyg:returnType=102=after=="+returnType);
						if(!returnType){//返回的结果是false
							text = "调用客服接口错误！";
							status = "2";
						}
					}
				}
				
			}
			
			JSONArray data = new JSONArray();
			JSONObject o = new JSONObject();
			o.put("text", text);
			data.put(o);
			JSONObject jsonRoot = new JSONObject();
			jsonRoot.put("data",data);
			jsonRoot.put("status", status);
			JSONUtil.print(response, jsonRoot.toString());
			
		}
	  
	  
	  
 }
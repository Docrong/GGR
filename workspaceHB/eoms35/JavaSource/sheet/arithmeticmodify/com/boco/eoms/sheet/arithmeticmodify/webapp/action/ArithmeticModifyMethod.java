
package com.boco.eoms.sheet.arithmeticmodify.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.arithmeticmodify.model.ArithmeticModifyMain;

public  class ArithmeticModifyMethod extends BaseSheet {
	public String getPageColumnName() {
		
		return super.getPageColumnName()+"gatherPerformer@java.lang.String,gatherPerformerLeader@java.lang.String,"
		+ "gatherPerformerType@java.lang.String,gatherPhaseId@java.lang.String,";
		
	}
   /* (non-Javadoc)
     * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#getInterfaceObjMap(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        HashMap hashMap = new HashMap();
//    	String taskName = StaticMethod.nullObject2String(request
//    			.getParameter("activeTemplateId"));
//    	String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));
    	HashMap sheetMap = new HashMap();
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("mainId"));
		BaseMain main = (BaseMain) this.getMainService().getMainObject().getClass().newInstance();
		BaseMain tmpMain = (BaseMain) this.getMainService().getMainObject().getClass().newInstance();
		if(!sheetKey.equals("")){
			sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));			
		} 
		if(!sheetKey.equals("")){
			tmpMain = this.getMainService().getSingleMainPO(sheetKey);
			if(tmpMain != null && tmpMain.getId().equals(sheetKey)){
				main = tmpMain;
			}else{
				main.setId(sheetKey);
			}
		} 
		sheetMap.put("main", main);	
		sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
		sheetMap.put("operate", getPageColumnName());
		hashMap.put("selfSheet",sheetMap);
    	
    	return hashMap;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#dealFlowEngineMap(org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public void dealFlowEngineMap(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	super.dealFlowEngineMap(mapping, form, request, response);
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		String gatherPhaseId = StaticMethod.nullObject2String(request
				.getParameter("gatherPhaseId"));
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("activeTemplateId"));
		if(taskName.equals("")){
			taskName = StaticMethod.nullObject2String(request
					.getParameter("taskName"));			
		}
		HashMap sheetMap = this.getFlowEngineMap();
		Map operate = (HashMap)sheetMap.get("operate");
		Map mainMap = (HashMap)sheetMap.get("main");
		Map linkMap = (HashMap)sheetMap.get("link");
		linkMap.put("serviceId", mainMap.get("serviceId"));
 		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		String[] dealperformers = (StaticMethod.nullObject2String(operate.get("dealPerformer"))).split(",");
//	    if(taskName.equals("DraftTask")){
//	    	operate.put("phaseId","PermitTask");
//			operate.put("operateType","3");
//		}
		if(taskName.equals("reply") || taskName.equals("advice"))
		{   
			Map link = (HashMap) sheetMap.get("link");
			link.put("id", "");
			sheetMap.put("link", link);
		}
		if(dealperformers.length>1){			
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
			operate.put("extendKey2",gatherPhaseId);
			operate.put("extendKey1", corrKey);
			sheetMap.put("operate", operate);

		}
		this.setFlowEngineMap(sheetMap);  
    }
    
  
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
		return this.getParameterMap();
	}    
    
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

	 public void showInputDealPage(ActionMapping mapping, ActionForm form,
	 			HttpServletRequest request, HttpServletResponse response)
	 			throws Exception {
	 		super.showInputDealPage(mapping, form, request, response);
	 		// 取上条TASK
	 		String taskName = StaticMethod.nullObject2String(request
	 				.getParameter("activeTemplateId"));
	 		if(taskName.equals("")){
	 			taskName = StaticMethod.nullObject2String(request
	 					.getParameter("taskName"));
	 		}

	 		//碰到需要驳回操作的节点时，去取上一个Task记录
	 		if (taskName.equals("PermitTask") || taskName.equals("RequireConfirmTask")|| taskName.equals("DeployImplementTask")||
	 				taskName.equals("ResultConfirmTask")|| taskName.equals("CheckDataSameTask") || taskName.equals("ResultCheckTask") || 
	 				taskName.equals("TargetCheckTask") || taskName.equals("TargetConfirmTask") || taskName.equals("FormalDeployTask") || 
	 				 taskName.equals("PublishNoticeTask") || taskName.equals("HoldTask")) {
	 			//下面这个方法写在base类中，会取出4个值，传递到inputdealpage页面中
	 			super.setParentTaskOperateWhenRejct(request);
	 		}
	 	}

	public String getProcessTemplateName() {
		// TODO Auto-generated method stub
		return "ArithmeticModifyProcess";
	}

	public String getSheetAttachCode() {
		// TODO Auto-generated method stub
		return "arithmeticmodify";
	}  

	public Map initMap(Map map, List attach, String type) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public void showDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		super.showDetailPage(mapping, form, request, response);
		String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"), "");
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		ArithmeticModifyMain main = (ArithmeticModifyMain)this.getMainService().getSingleMainPO(sheetKey);
		if(!preLinkId.equals("")){
			request.setAttribute("preLink", this.getLinkService().getSingleLinkPO(preLinkId));
		}	
	}	
	
//	/**
//	 * 同组模式待处理列表（本角色已接单未处理工单） 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @throws Exception
//	 */
//	public void showUndoListForSameTeam(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//
//		// 获取每页显示条数
//		Integer pageSize = ((SheetAttributes) ApplicationContextHolder
//				.getInstance().getBean("SheetAttributes")).getPageSize();
//
//		String pageIndexName = new org.displaytag.util.ParamEncoder("taskList")
//				.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
//
//		// 当前页数
//		final Integer pageIndex = new Integer(GenericValidator
//				.isBlankOrNull(request.getParameter(pageIndexName)) ? 0
//				: (Integer.parseInt(request.getParameter(pageIndexName)) - 1));
//		String order = StaticMethod
//				.null2String(request
//						.getParameter(new org.displaytag.util.ParamEncoder(
//								"taskList")
//								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_ORDER)));
//		String sort = StaticMethod
//				.null2String(request
//						.getParameter(new org.displaytag.util.ParamEncoder(
//								"taskList")
//								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_SORT)));
//		String orderCondition = "";
//		if (!order.equals("")) {
//			if (order.equals("1")) {
//				order = " asc";
//			} else {
//				order = " desc";
//			}
//		}
//		if (!sort.equals("")) {
//			orderCondition = " " + sort + order;
//		}
//
//		String exportType = StaticMethod
//				.null2String(request
//						.getParameter(new org.displaytag.util.ParamEncoder(
//								"taskList")
//								.encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_EXPORTTYPE)));
//		if (!exportType.equals("")) {
//			pageSize = new Integer(-1);
//		}
//
//		// 获取当前用户的角色列表
//		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
//				.getSession().getAttribute("sessionform");
//		String userId = sessionform.getUserid();
//        String deptId=sessionform.getDeptid();
//		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject()
//				.getClass().newInstance();
//		ITask taskObject = (ITask) this.getTaskService().getTaskModelObject()
//				.getClass().newInstance();
//		Map condition = new HashMap();
//		condition.put("mainObject", mainObject);
//		condition.put("taskObject", taskObject);
//		/**将部门ID放入查询条件中 add by 秦敏 20090909**/
//		condition.put("deptId", deptId);
//		condition.put("orderCondition", orderCondition);
//		String flowName = this.getMainService().getFlowTemplateName();
//		condition.put("flowName", flowName);
//		
//		HashMap taskListMap = null;
//		String type = StaticMethod.nullObject2String(request.getParameter("type"));
//		IArithmeticModifyTaskManager iArithmeticModifyTaskManager = (IArithmeticModifyTaskManager)ApplicationContextHolder.getInstance().getBean("iArithmeticModifyTaskManager");
//		System.out.println("===type===" + type);
//		if(!"".equals(type) && type.equals("nocheck")){
//			taskListMap = iArithmeticModifyTaskManager.getAcceptTaskByRoleNoCheck(condition, userId, deptId, flowName,
//					pageIndex, pageSize);
//		}else if(!"".equals(type) && type.equals("check")){
//			taskListMap = iArithmeticModifyTaskManager.getAcceptTaskByRoleCheck(condition, userId, deptId, flowName,
//					pageIndex, pageSize);
//		}else{
//			taskListMap = iArithmeticModifyTaskManager.getAcceptTaskByRole(condition, userId, deptId, flowName,
//					pageIndex, pageSize);
//		}
//		int total = ((Integer) taskListMap.get("taskTotal")).intValue();
//		List taskOvertimeList = (List) taskListMap.get("taskList");
//		List taskMapList = new ArrayList();
//		List taskList = new ArrayList();
//		// 设置每条task超时标识
//		if (taskOvertimeList != null && taskOvertimeList.size() > 0) {
//			// 查询超时配置表
//			IOvertimeTipManager service = (IOvertimeTipManager) ApplicationContextHolder
//					.getInstance().getBean("iOvertimeTipManager");
//			List timeList = service.getEffectOvertimeTip(this.getMainService()
//					.getFlowTemplateName(), userId);
//			// 得到角色细分字段
//			HashMap columnMap = OvertimeTipUtil
//					.getAllMainColumnByMapping(flowName);
//			// 循环为task超时标识赋值
//			HashMap columnMapOverTip = OvertimeTipUtil
//					.getNotOverTimeColumnByMapping(flowName);
//			for (int i = 0; i < taskOvertimeList.size(); i++) {
//				ITask tmptask = null;
//				Map taskMap = new HashMap();
//				HashMap conditionMap = new HashMap();
//				if (columnMap.size() > 0) {
//					Object[] tmpObjArr = (Object[]) taskOvertimeList.get(i);
//					tmptask = (ITask) tmpObjArr[0];
//					// 根据角色细分得到需要匹配的字段
//					Iterator it = columnMap.keySet().iterator();
//					int j = 0;
//					while (it.hasNext()) {
//						j++;
//						String elementKey = (String) it.next();
//						String tempcolumn = (String) tmpObjArr[j];
//						conditionMap.put(elementKey, tempcolumn);
//						taskMap.put(columnMap.get(elementKey), tempcolumn);
//					}
//				} else {
//					tmptask = (ITask) taskOvertimeList.get(i);
//				}
//				// 得到超时类型
//				if (exportType.equals("")) {
//					String overtimeFlag = OvertimeTipUtil.setOvertimeTipFlag(
//							columnMapOverTip, tmptask.getCompleteTimeLimit(),
//							conditionMap, timeList, flowName);
//					taskMap.put("overtimeType", overtimeFlag);
//				}
//				taskMap.putAll(SheetBeanUtils.bean2Map(tmptask));
//				taskList.add(tmptask);
//				taskMapList.add(taskMap);
//			}
//		}
//
//		// 将分页后的列表写入页面供使用
//		request.setAttribute("taskList", taskMapList);
//		request.setAttribute("total", new Integer(total));
//		request.setAttribute("pageSize", pageSize);
//		request.setAttribute("findForward", "list");
//		request.setAttribute("module", mapping.getPath().substring(1));
//
//		// 找出该流程中的节点
//		String workflowName = this.getMainService().getFlowTemplateName();
//		ArrayList phaseIdList = new ArrayList();
//		Map phaseIdMap = new HashMap();
//		FlowDefineExplain flowDefineExplain = new FlowDefineExplain(
//				workflowName, this.getRoleConfigPath());
//		FlowDefine flowDefine = flowDefineExplain.getFlowDefine();
//		if (flowDefine != null) {
//			PhaseId phaseIds[] = flowDefine.getPhaseId();
//			for (int i = 0; i < phaseIds.length; i++) {
//				PhaseId phaseId = phaseIds[i];
//				if (!phaseId.getId().equals("receive")) {
//					phaseIdMap.put(phaseId.getId(), phaseId.getName());
//					phaseIdList.add(phaseId.getId());
//				}
//			}
//		}
//		request.setAttribute("phaseIdMap", phaseIdMap);
//		request.setAttribute("stepIdList", phaseIdList);
//
//		// 需要进行批量回复和批量归档的节点
//		String batch = StaticMethod.null2String(request.getParameter("batch"));
//		if (!batch.equals("") && batch.equals("true")) {
//			// 需要进行批量回复和批量归档的步骤放入到tempMap中
//			Map tempMap = new HashMap();
//			String dictName = "dict-sheet-" + mapping.getPath().substring(1);
//			List dictItems = DictMgrLocator.getDictService().getDictItems(
//					Util.constituteDictId(dictName, "activeTemplateId"));
//			for (Iterator it = dictItems.iterator(); it.hasNext();) {
//				DictItemXML dictItemXml = (DictItemXML) it.next();
//				String description = dictItemXml.getDescription();
//				if (description.equals("batch:true")) {
//					tempMap.put(dictItemXml.getItemId(), dictItemXml
//							.getItemName());
//				}
//			}
//
//			// 和所查找的任务列表进行对比，如果该列表中有批量回复和批量归档的步骤就放入到batchTaskMap中
//			Map batchTaskMap = new HashMap();
//			if (tempMap.size() > 0) {
//				for (Iterator it = tempMap.keySet().iterator(); it.hasNext();) {
//					String taskName = (String) it.next();
//					for (Iterator tasks = taskList.iterator(); tasks.hasNext();) {
//						ITask task = (ITask) tasks.next();
//						if (taskName.equals(task.getTaskName())
//								&& (task.getSubTaskFlag() == null
//										|| task.getSubTaskFlag()
//												.equals("false") || task
//										.getSubTaskFlag().equals(""))) {
//							batchTaskMap.put(task.getTaskName(), task
//									.getTaskDisplayName());
//							break;
//						} else {
//							continue;
//						}
//					}
//				}
//			}
//
//			request.setAttribute("batchTaskMapKey", batchTaskMap.keySet());
//			request.setAttribute("batchTaskMap", batchTaskMap);
//		}
//	}
}

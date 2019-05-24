package com.boco.eoms.sheet.localCommonTask.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetAttributes;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefine;
import com.boco.eoms.sheet.base.util.flowdefine.xml.FlowDefineExplain;
import com.boco.eoms.sheet.base.util.flowdefine.xml.PhaseId;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.overtimetip.service.IOvertimeTipManager;
import com.boco.eoms.sheet.overtimetip.util.OvertimeTipUtil;

/**
 * <p>
 * Title:申请工单
 * </p>
 * <p>
 * Description:申请工单
 * </p>
 * <p>
 * Mon Sep 27 18:07:01 CST 2010
 * </p>
 * 
 * @author liuyang
 * @version 3.6
 * 
 */
 
 public class LocalCommonTaskMethod extends BaseSheet  {
     
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
			if (main == null) {
				main = (BaseMain) this.getMainService().getMainObject().getClass().newInstance();
			}
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
		//String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		//String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
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
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("activeTemplateId"));
		if (taskName.equals("")) {
			taskName = StaticMethod.nullObject2String(request
					.getParameter("taskName"));
		}
    }
	/**
	 * 工单初始化时即初始化mainid
	 * 
	 */
	public void showInputNewSheetPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.showInputNewSheetPage(mapping, form, request, response);
		try {
			// 新增一条记录，该记录中只有id和sendtime字段的数据
			BaseMain main = (BaseMain) request.getAttribute("sheetMain");
			main.setId(UUIDHexGenerator.getInstance().getID());
			
			request.setAttribute("sheetMain", main);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 引用模板时初始化mainid
	 */
	public void showInputTemplateSheetPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.showInputTemplateSheetPage(mapping, form, request, response);
		try {
			
			// 新增一条记录，该记录中只有id和sendtime字段的数据
			BaseMain main = (BaseMain) request.getAttribute("sheetMain");
			main.setId(UUIDHexGenerator.getInstance().getID());
			request.setAttribute("sheetMain", main);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getProcessTemplateName() {
		// TODO Auto-generated method stub
		return "LocalCommonTaskProcesses";
	}

	public String getSheetAttachCode() {
		// TODO Auto-generated method stub
		return "localCommonTask";
	}	
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
		condition.put("type", request.getParameter("type1"));
		condition.put("sheettype", request.getParameter("sheettype"));
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
			// 循环为task超时标识赋值
			for (int i = 0; i < taskOvertimeList.size(); i++) {
				ITask tmptask = null;
				Map taskMap = new HashMap();
				Map tmptaskMap = new HashMap();
				HashMap conditionMap = new HashMap();
				if (columnMap.size() > 0) { 
					
						if(request.getParameter("type")!=null&&request.getParameter("type").equals("1")){
							System.out.println("=========wanghao1============"); 
							Object[] tmpObjArr = (Object[]) taskOvertimeList.get(i);
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
						}else{
							System.out.println("=========wanghao1============"); 
							Object[] tmpObjArr = (Object[]) taskOvertimeList.get(i);
							tmptask = (ITask) tmpObjArr[1];
							// 根据角色细分得到需要匹配的字段
							Iterator it = columnMap.keySet().iterator();
							int j = 0;
//							while (it.hasNext()) {
//								j++;
								String elementKey = (String) it.next();
								Object tempcolumn = tmpObjArr[j];
								conditionMap.put(elementKey, tempcolumn);
								tmptaskMap.put(columnMap.get(elementKey), tempcolumn);
//							}
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
 }
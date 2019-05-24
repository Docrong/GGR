package com.boco.eoms.sheet.numberapply.webapp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.ui.util.UIConstants;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.numberapply.service.INumberApplyBatchHlrManager;
import com.boco.eoms.sheet.numberapply.service.INumberApplyBatchMscManager;
import com.boco.eoms.sheet.numberapply.service.INumberApplyMainManager;
import com.boco.eoms.sheet.numberapply.service.impl.RelationObjectManager;

/**
 * <p>
 * Title:码号资源申请工单
 * </p>
 * <p>
 * Description:码号资源申请工单
 * </p>
 * <p>
 * Mon Sep 27 18:07:01 CST 2010
 * </p>
 * 
 * @author liuyang
 * @version 3.6
 * 
 */
 
 public class NumberApplyMethod extends BaseSheet  {
     
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
		//add by zhouzhe 提交的时候将动态表中的数据设置为有效“0”表示无效，“1”表示有效
		String actionForword =StaticMethod.nullObject2String(request.getParameter("actionForword"));
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		if(!"".equals(actionForword)){
			if(actionForword.equals("hlrnew")){
				INumberApplyBatchHlrManager numberApplyBatchHlrManager = (INumberApplyBatchHlrManager)ApplicationContextHolder.getInstance().getBean("iNumberApplyBatchHlrManager");
				numberApplyBatchHlrManager.batchPreUpdate(sheetKey);
			}
			if (actionForword.equals("mscnew")){
				INumberApplyBatchMscManager numberApplyBatchMscManager = (INumberApplyBatchMscManager)ApplicationContextHolder.getInstance().getBean("iNumberApplyBatchMscManager");
				numberApplyBatchMscManager.batchPreUpdate(sheetKey);
			}
		}
		if(taskName.equals("HoldTask")){
			//工单的主键id		
			String sheetKey1 = (String)main.get("id");
			
			String mainResourceType = (String)main.get("mainResourceType");
			String mainHLRResource = (String)main.get("mainHLRResource");
			String mainMSCResource = (String)main.get("mainMSCResource");
			
			//主要是获得需要查询的Module对象（MSC或HLR）start
			INumberApplyMainManager numberApplyMainManager = (INumberApplyMainManager)ApplicationContextHolder.getInstance().getBean("iNumberApplyMainManager");
			//获得配置文件的bean
			RelationObjectManager objectManager = (RelationObjectManager)ApplicationContextHolder.getInstance().getBean("RelationObjectManager");
			Map needQueryObjectMap = new HashMap();
			//从Spring配置件中取出要查询的对象表
			Map queryObjectMap = objectManager.getFromQueryDataMoudle();
			List list = new ArrayList();
			list = numberApplyMainManager.analyzeDictionary(mainResourceType, mainHLRResource, mainMSCResource);
			for(int i =0;i<list.size();i++){
//				从queryObjectMap得到要查询的对象
				 String queryObjectName = StaticMethod.nullObject2String(queryObjectMap.get(list.get(i)));
				 needQueryObjectMap.put(queryObjectName, list.get(i));
			}
			
			//主要是获得需要更新的Module对象（MSC或HLR） end
				
			//得到更新的对象后拼接hql字符串进行更新
			if (needQueryObjectMap.size() > 0) {
				Set queryObj = needQueryObjectMap.keySet();
				for (Iterator it = queryObj.iterator(); it.hasNext();) {
					String objName = (String)it.next();
					String hql = " update " + objName + " set  isOccupation = 1 where sheetKey = '" + sheetKey1 + "'";
					numberApplyMainManager.updateModelByHql(hql);
				}
			}
			
		}
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
        //驳回上一级，需要取出上一级的角色和phaseId
        if (operateType.equals("4")) {
			BaseLink  prelink = this.getLinkService().getSingleLinkPO(preLinkId);
			if (prelink != null) {
				request.setAttribute("fOperateroleid", prelink.getOperateRoleId());
				request.setAttribute("fPreTaskName", prelink.getActiveTemplateId());
			}
		}
        if (taskName.equals("PermitTask")) {
			setParentTaskOperateWhenRejct("", request);
		}
    }
	/**
	 * ADD jialei 当驳回的时候查询之前特定的任务的执行者对象
	 * 从request中通过request.getAttribute(fOperateroleid/ftaskOwner/fOperateroleidType)
	 * 取得上一条任务的Operateroleid taskOwner OperateroleidType
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void setParentTaskOperateWhenRejct(String taskName,
			HttpServletRequest request) throws Exception {
		String prelinkid = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"));
		BaseLink preLink = (BaseLink) this.getLinkService().getSingleLinkPO(
				prelinkid);
		String sheetKey = preLink.getMainId();
		String fOperateroleid = "";
		String ftaskOwner = "";
		String fOperateroleidType = "";
		String fPreTaskName = "";
		if (preLink != null) {
			// 不是流程第一个操作步骤
			String parentTaskId = StaticMethod.nullObject2String(preLink
					.getAiid());
			if (!parentTaskId.equals("")) {
				ITask task = this.getTaskService().getSinglePO(parentTaskId);
				fOperateroleid = task.getOperateRoleId();
				ftaskOwner = task.getTaskOwner();
				fOperateroleidType = task.getOperateType();
				fPreTaskName = task.getTaskName();
			} else {
				BaseMain main = this.getMainService().getSingleMainPO(sheetKey);
				fOperateroleid = main.getSendRoleId();
				ftaskOwner = main.getSendUserId();
				fOperateroleidType = UIConstants.NODETYPE_SUBROLE;
			}
		}
		request.setAttribute("fOperateroleid", fOperateroleid);
		request.setAttribute("ftaskOwner", ftaskOwner);
		request.setAttribute("fOperateroleidType", fOperateroleidType);
		request.setAttribute("fPreTaskName", fPreTaskName);

		// 得到特定的任务的执行者对象
		if (taskName != null) {
			while (!taskName.equals(StaticMethod
					.nullObject2String(fPreTaskName))) {
				prelinkid = StaticMethod.nullObject2String(preLink
						.getPreLinkId());
				if (!prelinkid.equals("")) {
					preLink = (BaseLink) this.getLinkService().getSingleLinkPO(
							prelinkid);
				} else {
					preLink = null;
				}
				fOperateroleid = "";
				ftaskOwner = "";
				fOperateroleidType = "";
				fPreTaskName = "";
				if (preLink == null) {
					BaseMain main = this.getMainService().getSingleMainPO(
							sheetKey);
					fOperateroleid = main.getSendRoleId();
					ftaskOwner = main.getSendUserId();
					fOperateroleidType = UIConstants.NODETYPE_SUBROLE;
					fPreTaskName = "";

				} else {
					// 不是流程第一个操作步骤
					String parentTaskId = StaticMethod
							.nullObject2String(preLink.getAiid());
					if (!parentTaskId.equals("")) {
						ITask task = this.getTaskService().getSinglePO(
								parentTaskId);
						fOperateroleid = task.getOperateRoleId();
						ftaskOwner = task.getTaskOwner();
						fOperateroleidType = task.getOperateType();
						fPreTaskName = task.getTaskName();
					} else {
						BaseMain main = this.getMainService().getSingleMainPO(
								sheetKey);
						fOperateroleid = main.getSendRoleId();
						ftaskOwner = main.getSendUserId();
						fOperateroleidType = UIConstants.NODETYPE_SUBROLE;
						fPreTaskName = "";
					}
				}
				request.setAttribute(fPreTaskName + "Operateroleid",
						fOperateroleid);
				request.setAttribute(fPreTaskName + "TaskOwner", ftaskOwner);
				request.setAttribute(fPreTaskName + "OperateroleidType",
						fOperateroleidType);
				if (fPreTaskName.equals("")) {
					break;
				}
			}
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
		
	
 }
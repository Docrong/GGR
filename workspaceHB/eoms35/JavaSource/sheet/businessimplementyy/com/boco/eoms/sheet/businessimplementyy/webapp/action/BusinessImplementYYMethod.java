
package com.boco.eoms.sheet.businessimplementyy.webapp.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.interfaces.Irms.bo.IrmsResLoader;
import com.boco.eoms.businessupport.interfaces.service.bo.IrmsResourceBo;
import com.boco.eoms.businessupport.interfaces.transfer.client.TransferLoader;
import com.boco.eoms.businessupport.order.model.OrderSheet;
import com.boco.eoms.businessupport.order.service.IOrderSheetManager;
import com.boco.eoms.businessupport.order.webapp.action.OrderSheetMethod;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;

import com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYMain;
import com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYTask;
import com.boco.eoms.sheet.businessimplementyy.service.IBusinessImplementYYFlowManager;
import com.boco.eoms.sheet.businessimplementyy.service.IBusinessImplementYYTaskManager;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.tool.relation.model.TawSheetRelation;
import com.boco.eoms.sheet.tool.relation.service.ITawSheetRelationManager;
import com.boco.eoms.util.XmlDom4j;

public  class BusinessImplementYYMethod extends BaseSheet {
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
    	String taskName = StaticMethod.nullObject2String(request
    			.getParameter("activeTemplateId"));
    	String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));
    	
    	
    	HashMap sheetMap = new HashMap();
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("mainId"));
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
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("activeTemplateId"));
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		String phaseId = StaticMethod.nullObject2String(request
				.getParameter("phaseId"));
		String gatherPhaseId = StaticMethod.nullObject2String(request
				.getParameter("gatherPhaseId"));
		HashMap sheetMap = this.getFlowEngineMap();
		
		
		
		Map operate = (HashMap)sheetMap.get("operate");
 		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword()); 

 		
		
//		boolean ifexit = false;
//		String[] tempAllPhaseIds = {"DraftTask","RejectTask","AcceptTask","ExecuteTask","AcceptReply", "HoldTask","OverTask"};
//		for(int i=0;i<tempAllPhaseIds.length;i++){
//			
//			if(phaseId.equals(tempAllPhaseIds[i])){
//				ifexit = true;
//				break;
//			}			
//		}
//		if(ifexit == false){
//			throw new Exception("工单即将流转的步骤不存在，步骤Id为："+phaseId+"请联系管理员!");
//		}
		
		String[] dealperformers = (StaticMethod.nullObject2String(operate.get("dealPerformer"))).split(",");

		
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
			System.out.println("gatherPhaseId -== gatherPhaseIdmethod" + gatherPhaseId);
			//extendKey2 此为流程中的聚合点 值
			operate.put("extendKey2",gatherPhaseId);
			operate.put("extendKey1", corrKey);
			sheetMap.put("operate", operate);

		}
		//added by liufuyuan
		Map mainMap = (HashMap) sheetMap.get("main");
		if (operateType.equals("0")) {
			String orderId = StaticMethod.nullObject2String(request
					.getParameter("orderId"));
			if (orderId.length() > 0) {

				OrderSheet order = new OrderSheet();
				SheetBeanUtils.populateMap2Bean(order, request
						.getParameterMap());
				order.setId(orderId);
				order.setOrderBuisnessType(StaticMethod
						.nullObject2String(mainMap.get("mainSpecifyType")));
				order.setUrgentDegree(StaticMethod.nullObject2String(mainMap
						.get("mainArgument")));
				order.setCreatTime(new Date());
				order.setOrderType("yuyin");

				IOrderSheetManager mgr = (IOrderSheetManager) ApplicationContextHolder
						.getInstance().getBean("IOrderSheetManager");
				mgr.saveOrUpdate(order);

				mainMap.put("orderSheetId", orderId);
			}
		}
		
		System.out.println("main="+mainMap);
		if(mainMap!=null && StaticMethod.nullObject2String(mainMap.get("mainSendSheetModule")).equalsIgnoreCase("1")){
			String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessimplementyy-crm.xml").getProperty("base.SendImmediately"));
			if(!sendImmediately.equalsIgnoreCase("true")){
				if (taskName.equals("AuditTask") && operateType.equals("4")) {//驳回
					operate.put("interfaceType", "withdrawWorkSheet");
					operate.put("methodType", "withdrawWorkSheet");
				} else if (operateType.equals("9")) {//阶段回复
					operate.put("interfaceType", "notifyWorkSheet");
					operate.put("methodType", "notifyWorkSheet");
				} else if (taskName.equals("AuditTask") && operateType.equals("61")) {// 受理
					operate.put("interfaceType", "confirmWorkSheet");
					operate.put("methodType", "confirmWorkSheet");
				} else if (operateType.equals("77")) {// 回复
					operate.put("interfaceType", "replyWorkSheet");
					operate.put("methodType", "replyWorkSheet");
				}
			}
		}
		
		if(taskName.equals("HoldTask")&&operateType.equals("18")){
	    	   
	           String orderSheetId = StaticMethod.nullObject2String(mainMap.get("orderSheetId"));	   
	           List list = OrderSheetMethod.getSpecialLineList(orderSheetId, com.boco.eoms.businessupport.util.Constants._LANGUAGE_LINE);
			   IrmsResourceBo.occupyServiceRes(mainMap, list);	//èµæºå®å 	

			   String enable = XmlManage.getFile("/com/boco/eoms/businessupport/interfaces/Irms/config/irmsres-config.xml").getProperty("enable");
				if(enable.equalsIgnoreCase("true")){
					IOrderSheetManager mgr = (IOrderSheetManager) ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");
					OrderSheet ordersheet = (OrderSheet) mgr.getOrderSheet(orderSheetId);
		   		   //å°çµè·¯åç§°ä¼ ç»IRMS
					Map map = SheetBeanUtils.bean2Map(ordersheet);
					map.putAll(mainMap);
		   		   IrmsResLoader.addEomsResByProdTypeBO(map,list);
				}
	       }       

		
		sheetMap.put("main", mainMap);
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
		// TODO Auto-generated method stub
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
 		
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), ""); 		
 		Object obj = request.getAttribute("task");
 		
 		if(obj != null){
 			ITask taskModel = (ITask)obj;
 			Map objectMap = new HashMap();
 			objectMap = SheetBeanUtils.bean2Map(taskModel);
 			IBusinessImplementYYTaskManager ibusinessimplementyyTaskManager = (IBusinessImplementYYTaskManager) this.getTaskService();
 	 		Integer tempCount = ibusinessimplementyyTaskManager.getCountOfBrother(sheetKey,StaticMethod.nullObject2String(objectMap.get("parentLevelId")));
 	 		if(tempCount.intValue()>1){
 	 			request.setAttribute("hasbrother", "hasbrother");
 	 			System.out.println("=========hasbrother========");
 	 		}
 		}
		
 		//工单互调接口 added by liufuyuan
 		String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
 		if(taskName.equals("OpenTask")){
 			String taskKey = StaticMethod.nullObject2String(request.getParameter("taskKey"));
 			  sheetKey  =  StaticMethod.nullObject2String(request.getParameter("sheetKey"));
	 		ITawSheetRelationManager  rmgr  = 
	 		(ITawSheetRelationManager)  ApplicationContextHolder.getInstance().getBean("ITawSheetRelationManager");
	
	 		List relationAllList=rmgr.getAllSheetByParentIdAndPhaseId(sheetKey, taskName);
	    	if(relationAllList != null){
				for(int i=0;i<relationAllList.size();i++){
					TawSheetRelation relation=(TawSheetRelation)relationAllList.get(i);
					int state=relation.getInvokeState();
					if(state==Constants.INVOKE_STATE_RUNNING){
						request.setAttribute("ifInvoke", "no");
						System.out.println("@@ifInvoke=no==1=");
						break;
					}
					request.setAttribute("ifInvoke", "yes");  
					System.out.println("@@ifInvoke===yes===");
				}
	    	}else{
				request.setAttribute("ifInvoke", "no");
				System.out.println("@@ifInvoke==out=no===");
			}
 		}
 		

    }

    /* 提交前验证放法 使该步骤（Taskname和operate）必须调用其他流程
     * 验证该步骤必须要调用其他流程，否则不允许提交:
     * (non-Javadoc)
     * @see com.boco.eoms.sheet.base.webapp.action.BaseSheet#performPreCommit(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
 
	public void performPreCommit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("activeTemplateId"));//activeTemplate == taskname
		if(taskName.equals("")){
			taskName = StaticMethod.nullObject2String(request
					.getParameter("taskName"));
		}
		
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		if(taskName.equals("OpenTask")&&operateType.equals("111")){
			//查询工单互调表
		    	ITawSheetRelationManager rmgr = (ITawSheetRelationManager) ApplicationContextHolder
					.getInstance().getBean("ITawSheetRelationManager");
		    	List relationAllList=rmgr.getRunningSheetByParentIdAndPhaseId(sheetKey, taskName);
		    	//如果已经调用了其他工单则继续执行父类的performPreCommit方法,否则返回status为-1
		    	if(relationAllList != null&&relationAllList.size()>0){
		    		super.performPreCommit(mapping, form, request, response);
		    	}else{
		    		JSONArray data = new JSONArray();
		    		JSONObject o = new JSONObject();
		    		o.put("text", "请调用其他流程！");
		    		data.put(o);
		    		JSONObject jsonRoot = new JSONObject();
		    		jsonRoot.put("data",data);
		    		jsonRoot.put("status", "2");
					JSONUtil.print(response, jsonRoot.toString());
		    	}
		}else{
			super.performPreCommit(mapping, form, request, response);
		}
		
	}
 
//	
	/**
	 * 申明一个任务
	 */
	public void performClaim(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.performClaim(mapping, form, request, response);
		String activeTemplateId = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		if(activeTemplateId.equals("ExcuteHumTask")){
			System.out.println("确认受理");
			HashMap sessionMap = new HashMap();
			
			TawSystemSessionForm sessionform = (TawSystemSessionForm) request
			.getSession().getAttribute("sessionform");
			sessionMap.put("userId", sessionform.getUserid());
			sessionMap.put("password", sessionform.getPassword());
			
			String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
			ITask task = this.getTaskService().getSinglePO(taskId);
			IBusinessImplementYYFlowManager service = (IBusinessImplementYYFlowManager)this.getBusinessFlowService();
			service.setProcessInstanceCustomProperty(task.getProcessId(), "ifAccepted", "true", sessionMap);
		}
	}
	
	
    
	/**
	 * 显示工单详细页面(Atom系统)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void showAtomDetailPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
        this.showDetailPageAtom(mapping, form, request, response);
        BusinessImplementYYMain mainObject = (BusinessImplementYYMain)request.getAttribute("sheetMain");
        BusinessImplementYYTask  task = (BusinessImplementYYTask)request.getAttribute("task");
		String isAccept = null;
		if(task.getTaskStatus().equals(Constants.TASK_STATUS_READY)){
			isAccept = "0" ;
		}
		if(task.getTaskStatus().equals(Constants.TASK_STATUS_CLAIMED)){
			isAccept = "1" ;	
		}
		String  asXML = BusinessImplementYYMethod.showAtomDetail(mainObject, task, isAccept, request);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(asXML);
	
	}	
	
	/**
	 * 将detail所需的信息以及我们处理所需参数写入XML
	 * @param mainObject
	 * @param task
	 * @param isAccept
	 * @param request
	 * @return
	 * @throws DictServiceException
	 */
	public static String showAtomDetail(BusinessImplementYYMain mainObject,ITask task,String isAccept,HttpServletRequest request) throws DictServiceException{

//		Document document = DocumentFactory.getInstance().createDocument();
//
//		// 添加根元素
//		Element root = document.addElement("process");
//	    //工单详细信息部分      如有本地化的字段才在此处添加,注意格式     
//		// 元素信息		
//		Element attributes = root.addElement("attributes");
//		Element attribute1 = attributes.addElement("attribute");		
//		// 添加第一个人的信息<用的一种方法>
//		Element title1 = attribute1.addElement("title");
//		Element name1 = attribute1.addElement("name");		
//		title1.setText("工单流水号");
//		name1.setText(StaticMethod.null2String(mainObject.getSheetId()));
//		
//		Element attribute2 = attributes.addElement("attribute");		
//		Element title2 = attribute2.addElement("title");
//		Element name2 = attribute2.addElement("name");		
//		title2.setText("工单状态");
//		IDictService serviceOne = (IDictService)ApplicationContextHolder.getInstance().getBean("DictService");
//		String sheetStatus = (String) serviceOne.itemId2name("dict-sheet-common#sheetStatus", mainObject.getStatus());
//        //String statusName = (String) EOMSMgr.getSysMgrs().getDictMgrs().getXMLDictMgr().itemId2name("dict-sheet-common#sheetStatus", mainObject.getStatus());
//		name2.setText(sheetStatus);	
//		
//		Element attribute3 = attributes.addElement("attribute");		
//		Element title3 = attribute3.addElement("title");
//		Element name3 = attribute3.addElement("name");		
//		title3.setText("工单主题");
//		name3.setText(StaticMethod.null2String(mainObject.getTitle()));		
//		
//		Element attribute4 = attributes.addElement("attribute");		
//		Element title4 = attribute4.addElement("title");
//		Element name4 = attribute4.addElement("name");		
//		title4.setText("操作人");
//		ID2NameService service = (ID2NameService) ApplicationContextHolder
//		.getInstance().getBean("ID2NameGetServiceCatch");		
//		String operateName = service.id2Name(mainObject.getSendUserId(),"tawSystemUserDao");
//		name4.setText(operateName);	
//		
//		Element attribute5 = attributes.addElement("attribute");		
//		Element title5 = attribute5.addElement("title");
//		Element name5 = attribute5.addElement("name");		
//		title5.setText("操作部门");		
//		String operateDept = service.id2Name(mainObject.getSendDeptId(),"tawSystemDeptDao");
//		name5.setText(operateDept);	
//		
//		Element attribute6 = attributes.addElement("attribute");		
//		Element title6 = attribute6.addElement("title");
//		Element name6 = attribute6.addElement("name");		
//		title6.setText("操作人当前角色");			
//		String operateRoleID = service.id2Name(mainObject.getSendRoleId(),"tawSystemSubRoleDao");
//		name6.setText(operateRoleID);			
//		
//		Element attribute7 = attributes.addElement("attribute");		
//		Element title7 = attribute7.addElement("title");
//		Element name7 = attribute7.addElement("name");		
//		title7.setText("操作人联系方式");			
//		name7.setText(StaticMethod.null2String(mainObject.getSendContact()));	
//		
//		Element attribute8 = attributes.addElement("attribute");		
//		Element title8 = attribute8.addElement("title");
//		Element name8 = attribute8.addElement("name");		
//		title8.setText("操作时间");
//	//	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	//	String operatetime=df.format(mainObject.getSendTime());
//		String operatetime=StaticMethod.date2String(mainObject.getSendTime());		
//		name8.setText(operatetime);			
//		
//		Element attribute9 = attributes.addElement("attribute");		
//		Element title9 = attribute9.addElement("title");
//		Element name9 = attribute9.addElement("name");		
//		title9.setText("受理时限");			
//		name9.setText(StaticMethod.date2String(mainObject.getSheetAcceptLimit()));				
//
//		Element attribute10 = attributes.addElement("attribute");		
//		Element title10 = attribute10.addElement("title");
//		Element name10 = attribute10.addElement("name");		
//		title10.setText("处理时限");	
//		name10.setText(StaticMethod.date2String(mainObject.getSheetCompleteLimit()));		
//		
//		Element attribute11 = attributes.addElement("attribute");		
//		Element title11 = attribute11.addElement("title");
//		Element name11 = attribute11.addElement("name");		
//		title11.setText("网络分类(一级)");	
//		String netSortOne = service.id2Name(mainObject.getMainNetSort1(),"ItawSystemDictTypeDao");
//		name11.setText(netSortOne);				
//				
//		Element attribute12 = attributes.addElement("attribute");		
//		Element title12 = attribute12.addElement("title");
//		Element name12 = attribute12.addElement("name");		
//		title12.setText("网络分类(二级)");	
//		String netSortTwo = service.id2Name(mainObject.getMainNetSort2(),"ItawSystemDictTypeDao");
//		name12.setText(netSortTwo);				
//		
//		Element attribute13 = attributes.addElement("attribute");		
//		Element title13 = attribute13.addElement("title");
//		Element name13 = attribute13.addElement("name");		
//		title13.setText("网络分类(三级)");	
//		String netSortThree = service.id2Name(mainObject.getMainNetSort3(),"ItawSystemDictTypeDao");
//		name13.setText(netSortThree);	
//		
//		Element attribute14 = attributes.addElement("attribute");		
//		Element title14 = attribute14.addElement("title");
//		Element name14 = attribute14.addElement("name");		
//		title14.setText("网络分类(三级)");	
//		String mainTaskType = service.id2Name(mainObject.getMainTaskType(),"ItawSystemDictTypeDao");
//		name14.setText(mainTaskType);			
//
//		Element attribute15 = attributes.addElement("attribute");		
//		Element title15 = attribute15.addElement("title");
//		Element name15 = attribute15.addElement("name");		
//		title15.setText("任务描述");			
//		name15.setText(StaticMethod.null2String(mainObject.getMainTaskDescription()));	
//		
//		Element attribute16 = attributes.addElement("attribute");		
//		Element title16 = attribute16.addElement("title");
//		Element name16 = attribute16.addElement("name");		
//		title16.setText("备注");			
//		name16.setText(StaticMethod.null2String(mainObject.getMainRemark()));			
//		
//		Element attribute17 = attributes.addElement("attribute");		
//		Element title17 = attribute17.addElement("title");
//		Element name17 = attribute17.addElement("name");		
//		title17.setText("附件");	
//		ITawCommonsAccessoriesManager mgrr = 
//			   (ITawCommonsAccessoriesManager) ApplicationContextHolder.getInstance().getBean("ItawCommonsAccessoriesManager");
//		try {
//			String str = mainObject.getSheetAccessories();
//			List list = mgrr.getAllFileById(mainObject.getSheetAccessories());
//			String url ="";
//			for(int i = 0; i<list.size();i++){
//				TawCommonsAccessories  tawCommonsAccessories = (TawCommonsAccessories)list.get(i);
//				// TODO by yangliangliang 如果与wap平台与WPS不同context，则session中没有用户信息，url需要加type=interface&userName=" + request.getParameter("userName"),如果在同一个context,请去掉type=interface&userName=" + request.getParameter("userName");
//				url = url+"<a href='http://"+request.getLocalAddr()+":"+request.getLocalPort()+"/eoms35/accessories/tawCommonsAccessoriesConfigs.do?method=download&type=interface&userName=" + request.getParameter("userName") + "&id="+tawCommonsAccessories.getId()+"'>"
//	         +tawCommonsAccessories.getAccessoriesCnName()+"</a><br>";
//				}
//			name17.setText(url);
//			} catch (AccessoriesException e) {
//				e.printStackTrace();
//			}	
//	
//	
//	
//	//	<!-- 为流程隐藏的往回传的参数 -->
//		Element parameters = root.addElement("parameters");
//		
//		Element parameter1 = parameters.addElement("hidden");
//		Element id1 = parameter1.addElement("id");
//		Element value1 = parameter1.addElement("value");
//		id1.setText("beanName");
//		value1.setText(request.getParameter("beanName"));
//		
//		Element parameter2 = parameters.addElement("hidden");
//		Element id2 = parameter2.addElement("id");
//		Element value2 = parameter2.addElement("value");
//		id2.setText("sheetKey");
//		value2.setText(task.getSheetKey());		  
//		
//		Element parameter3 = parameters.addElement("hidden");
//		Element id3 = parameter3.addElement("id");
//		Element value3 = parameter3.addElement("value");
//		id3.setText("taskId");
//		value3.setText(task.getId());			
//		
//		Element parameter4 = parameters.addElement("hidden");
//		Element id4 = parameter4.addElement("id");
//		Element value4 = parameter4.addElement("value");
//		id4.setText("taskName");
//		value4.setText(task.getTaskName());		
//	
//		Element parameter5 = parameters.addElement("hidden");
//		Element id5 = parameter5.addElement("id");
//		Element value5 = parameter5.addElement("value");
//		id5.setText("preLinkId");
//		value5.setText(task.getPreLinkId());				
//		
//		Element parameter6 = parameters.addElement("hidden");
//		Element id6 = parameter6.addElement("id");
//		Element value6 = parameter6.addElement("value");
//		id6.setText("isAccept");
//		value6.setText(isAccept);
//		
//		Element parameter7 = parameters.addElement("hidden");
//		Element id7 = parameter7.addElement("id");
//		Element value7 = parameter7.addElement("value");
//		id7.setText("activeTemplateId");
//		value7.setText(task.getTaskName());			
//		
//		Element parameter8 = parameters.addElement("hidden");
//		Element id8 = parameter8.addElement("id");
//		Element value8 = parameter8.addElement("value");
//		id8.setText("beanName");
//		value8.setText(request.getParameter("beanName"));
//		
//		Element parameter9 = parameters.addElement("hidden");
//		Element id9 = parameter9.addElement("id");
//		Element value9 = parameter9.addElement("value");
//		id9.setText("beanId");
//		value9.setText("iCommonFaultMainManager");
//		
//		Element parameter10 = parameters.addElement("hidden");
//		Element id10 = parameter10.addElement("id");
//		Element value10 = parameter10.addElement("value");
//		id10.setText("mainClassName");
//		value10.setText("com.boco.eoms.sheet.businessimplementyy.model.businessimplementyyMain");
//		
//		Element parameter11 = parameters.addElement("hidden");
//		Element id11 = parameter11.addElement("id");
//		Element value11 = parameter11.addElement("value");
//		id11.setText("linkClassName");
//		value11.setText("com.boco.eoms.sheet.businessimplementyy.model.businessimplementyyLink");
//		
//		Element parameter12 = parameters.addElement("hidden");
//		Element id12 = parameter12.addElement("id");
//		Element value12 = parameter12.addElement("value");
//		id12.setText("aiid");
//		value12.setText(task.getId());	
//		
//		Element parameter13 = parameters.addElement("hidden");
//		Element id13 = parameter13.addElement("id");
//		Element value13 = parameter13.addElement("value");
//		id13.setText("piid");
//		value13.setText(StaticMethod.null2String(task.getProcessId()));			
//	
//		Element parameter14 = parameters.addElement("hidden");
//		Element id14 = parameter14.addElement("id");
//		Element value14 = parameter14.addElement("value");
//		id14.setText("mainId");
//		value14.setText(task.getSheetKey());		
//	
//		Element parameter15 = parameters.addElement("hidden");
//		Element id15 = parameter15.addElement("id");
//		Element value15 = parameter15.addElement("value");
//		id15.setText("TKID");
//		value15.setText(task.getId());			
//	
//	//<!-- wap上用来作判断的参数 -->
//		Element parameter01 = parameters.addElement("parameter");
//		Element id01 = parameter01.addElement("id");
//		Element value01 = parameter01.addElement("value");
//		id01.setText("taskStatus");
//		value01.setText(task.getTaskStatus());					
//		
//		System.out.println("------------------------------------"+document.asXML());
//	   return document.asXML();	
		return null;	
			
		}

	public String getProcessTemplateName() {
		// TODO Auto-generated method stub
		return "businessimplementyyMainFlowProcess";
	}

	public String getSheetAttachCode() {
		// TODO Auto-generated method stub
		return "businessimplementyy";
	}

	public Map initMap(Map map, List attach, String type) throws Exception {
		try{
			String configPath = "config/businessimplementyy-interface-config.xml";
			map = this.loadDefaultMap(map, configPath, type);
			if(type.equals(InterfaceConstants.WORKSHEET_NEW)){
				
//				网络分类
				String NeTypeCode = StaticMethod.nullObject2String(map.get("netType"));
				String mainNetSortThree = "";
				if(NeTypeCode.length()>0){		
					String rootId = XmlManage.getFile("/config/businessimplementyy-interface-config.xml").getProperty("base.rootNeTypeId");
					ITawSystemDictTypeManager dictMgr=
						 (ITawSystemDictTypeManager)ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
					mainNetSortThree = dictMgr.getDictIdByDictCode(rootId,NeTypeCode);
					if(mainNetSortThree==null||mainNetSortThree.length()==0)
						System.out.println("没有找到映射的网络分类");
					else{		
						try{
							System.out.println("mainNetSortThree="+mainNetSortThree);
							TawSystemDictType dict3 = dictMgr.getDictByDictId(mainNetSortThree);
							if(dict3!=null){
								//mainNetSortThree = dict3.getDictId();
								map.put("mainNetSort3", mainNetSortThree);
							
								String pId = dict3.getParentDictId();
								
									TawSystemDictType dict2 = dictMgr.getDictByDictId(pId);
									if(dict2!=null){
										map.put("mainNetSort2", dict2.getDictId());
									
										pId = dict2.getParentDictId();
										if(!pId.equals("-1")){
											TawSystemDictType dict1 = dictMgr.getDictByDictId(pId);
											if(dict1!=null)
												map.put("mainNetSort1", dict1.getDictCode());
										}
									}
							}else{
								System.out.println("dict3 is null");
							}
						}catch(Exception err){
							System.out.println("没有找到映射的网络分类");
						}		
						
					}
				}
			}
			return map;
		}catch(Exception err){
			err.printStackTrace();
			throw err;
		}
	}
	
	/**
	 * 同组模式待处理列表（本角色已接单未处理工单)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author wangjianhua
	 * @date 2009-08-24
	 * @province 甘肃
	 * 
	 */
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
//
//		BaseMain mainObject = (BaseMain) this.getMainService().getMainObject()
//				.getClass().newInstance();
//		ITask taskObject = (ITask) this.getTaskService().getTaskModelObject()
//				.getClass().newInstance();
//		Map condition = new HashMap();
//		condition.put("operateType", "dept");
//		condition.put("mainObject", mainObject);
//		condition.put("taskObject", taskObject);
//		condition.put("orderCondition", orderCondition);
//		String flowName = this.getMainService().getFlowTemplateName();
//		condition.put("flowName", flowName);
//		
//		HashMap taskListMap = this.getTaskService().getAcceptTaskByRole(
//				condition, userId, pageIndex, pageSize);
//		
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

	public void showDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
		       HttpServletResponse response) throws Exception {
	// TODO Auto-generated method stub
	super.showDetailPage(mapping, form, request, response);
	String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"), "");
	if(!preLinkId.equals("")){
		request.setAttribute("preLink", this.getLinkService().getSingleLinkPO(preLinkId));
	}
	String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
	if (!sheetKey.equals("")) {
		//根据sheetKey得到工单main对象，取出ordersheetid，查找定单信息
	    BusinessImplementYYMain businessimplementMain = (BusinessImplementYYMain)this.getMainService().getSingleMainPO(sheetKey);
	    String orderSheetId =  businessimplementMain.getOrderSheetId();
		if(orderSheetId != null && !orderSheetId.equals("")){
     IOrderSheetManager iOrderSheetManager = (IOrderSheetManager)ApplicationContextHolder
           .getInstance().getBean("IOrderSheetManager");
     OrderSheet orderSheet = iOrderSheetManager.getOrderSheet(orderSheetId);
     System.out.println("@@@@orderSheetId"+orderSheetId);
     request.setAttribute("orderSheet", orderSheet);
     request.setAttribute("orderSheetId", orderSheetId);
		}
	}			
}

	//获取与工单相关的定单信息，呈现在工单的detail页面上 modify by shichuangke
	public void showMainDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
		       HttpServletResponse response) throws Exception {
	// TODO Auto-generated method stub
	super.showMainDetailPage(mapping, form, request, response);
	String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"), "");
	if(!preLinkId.equals("")){
		request.setAttribute("preLink", this.getLinkService().getSingleLinkPO(preLinkId));
	}
	String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
	if (!sheetKey.equals("")) {
		//根据sheetKey得到工单main对象，取出ordersheetid，查找定单信息
	 BusinessImplementYYMain businessimplementMain = (BusinessImplementYYMain)this.getMainService().getSingleMainPO(sheetKey);
	 String orderSheetId =  businessimplementMain.getOrderSheetId();
		if(orderSheetId != null && !orderSheetId.equals("")){
	IOrderSheetManager iOrderSheetManager = (IOrderSheetManager)ApplicationContextHolder
	    .getInstance().getBean("IOrderSheetManager");
	OrderSheet orderSheet = iOrderSheetManager.getOrderSheet(orderSheetId);
	request.setAttribute("orderSheet", orderSheet);
	request.setAttribute("orderSheetId", orderSheetId);
		}
	}			
}
	
	
}

package com.boco.eoms.sheet.mofficedata.webapp.action;

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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.accessories.model.TawCommonsAccessories;
import com.boco.eoms.commons.accessories.webapp.form.TawCommonsAccessoriesForm;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.ui.util.JSONUtil;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataLink;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataMain;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataProMatch;
import com.boco.eoms.sheet.mofficedata.model.MofficeDataTask;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataBuisTypeManager;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataLinkManager;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataMainManager;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataProMatchManager;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataSubLinkManager;
import com.boco.eoms.sheet.mofficedata.service.IMofficeDataTaskManager;
import com.boco.eoms.sheet.mofficedata.util.MofficeDataInService;


/**
 * <p>
 * Title:局数据工单流程
 * </p>
 * <p>
 * Description:局数据工单流程
 * </p>
 * <p>
 * Tue Mar 22 09:31:29 CST 2016
 * </p>
 * 
 * @author weichao
 * @version 3.5
 * 
 */

public class MofficeDataMethod extends BaseSheet {

	public String getPageColumnName() {

		return super.getPageColumnName() + "gatherPerformer@java.lang.String,gatherPerformerLeader@java.lang.String,"
				+ "gatherPerformerType@java.lang.String,gatherPhaseId@java.lang.String,";

	}

	public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HashMap hashMap = new HashMap();

		HashMap sheetMap = new HashMap();
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
		BaseMain main = (BaseMain) this.getMainService().getMainObject().getClass().newInstance();
		if (!sheetKey.equals("")) {
			sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));

		}
		if (!sheetKey.equals("")) {
			main = this.getMainService().getSingleMainPO(sheetKey);
		}
		sheetMap.put("main", main);
		sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
		sheetMap.put("operate", getPageColumnName());
		hashMap.put("selfSheet", sheetMap);

		return hashMap;
	}

	/**
	 * 提交流程引擎前作最后一次参数处理
	 */
	public void dealFlowEngineMap(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.dealFlowEngineMap(mapping, form, request, response);

		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		HashMap sheetMap = this.getFlowEngineMap();

		Map main = (HashMap) sheetMap.get("main");
		Map operate = (HashMap) sheetMap.get("operate");
		Map link = (HashMap) sheetMap.get("link");

		String[] dealperformers = (StaticMethod.nullObject2String(operate.get("dealPerformer"))).split(",");

		if (taskName.equals("reply") || taskName.equals("advice")) {
			link.put("id", "");
		}
		
		/*新建派发、重新派发、草稿派发的时候需要将所选的制作人信息存入到单表中 add by weichao 20161206*/
		if("0".equals(operateType)||"54".equals(operateType)||"3".equals(operateType)||"22".equals(operateType)){
			IDownLoadSheetAccessoriesService downMgr = (IDownLoadSheetAccessoriesService) ApplicationContextHolder
							.getInstance().getBean("IDownLoadSheetAccessoriesService");
			IMofficeDataProMatchManager proMgr = (IMofficeDataProMatchManager)ApplicationContextHolder.getInstance()
						.getBean("iMofficeDataProMatchManager");
			
			int mainSendModeType = StaticMethod.nullObject2int(main.get("mainSendModeType"));
			if (mainSendModeType == 0)
			{
				String id = StaticMethod.nullObject2String(main.get("id"));
				String[] proids = request.getParameterValues("proid");
				String[] buissTypes = request.getParameterValues("buissType");
				String[] majorTypes = request.getParameterValues("majorType");
				String[] producerIds = request.getParameterValues("producerId");
				String[] producerNames = request.getParameterValues("producerName");
				String producerType = request.getParameter("producerType");
				String[] netIds = request.getParameterValues("netId");
				String[] netNames = request.getParameterValues("netName");
				String[] deviceNames = request.getParameterValues("deviceName");
				String[] deviceIds = request.getParameterValues("deviceId");
				String[] accessoriess = request.getParameterValues("accessories");
				
				String[] mainStyle9 = request.getParameterValues("mainStyle9");
				String[] mainStyle10 = request.getParameterValues("mainStyle10");
				
				String now = StaticMethod.getCurrentDateTime();
				if(null!=buissTypes&&buissTypes.length>0){
					List typeList = new ArrayList();
					for(int i=0;i<buissTypes.length;i++){
						String proid = StaticMethod.nullObject2String(proids[i]);
						if(!"".equals(proid)){
							MofficeDataProMatch mat = proMgr.getProMatchObjectById(proid);
							mat.setBuissType(buissTypes[i]);
							mat.setMajorType(majorTypes[i]);
							mat.setProducerId(producerIds[i]);
							mat.setDeviceId(deviceIds[i]);
							mat.setProducerName(producerNames[i]);
							mat.setDeviceName(deviceNames[i]);
							mat.setAccessories(accessoriess[i]);
							mat.setCreateTime(now);
							mat.setNetId(netIds[i]);
							mat.setNetName(netNames[i]);
							
							mat.setMainStyle9(mainStyle9[i]);
							mat.setMainStyle10(mainStyle10[9]);
							
							proMgr.saveOrUpdate(mat);
						}else{
							Map type = new HashMap();
							type.put("id", UUIDHexGenerator.getInstance().getID());
							type.put("buissType", buissTypes[i]);
							type.put("majorType", majorTypes[i]);
							type.put("mainId", id);
							type.put("producerId", producerIds[i]);
							type.put("producerName", producerNames[i]);
							type.put("producerType", producerType);
							type.put("netId", netIds[i]);
							type.put("netName", netNames[i]);
							type.put("deviceName", deviceNames[i]);
							type.put("deviceId", deviceIds[i]);
							type.put("accessories", accessoriess[i]);
							type.put("createTime", now);
						
							type.put("correKey", "");
							type.put("phaseName", "");
							type.put("rejectPhaseName", "");
							type.put("linkId", "");
							
							type.put("mainStyle9", mainStyle9[i]);
							type.put("mainStyle10", mainStyle10[i]);
							
							typeList.add(type);
						}
					}
					if (null != typeList && !typeList.isEmpty()) {
						downMgr.batchExcuteSql(typeList, new MofficeDataProMatch(), "insert");			
					}
				}
			}else if (1 == mainSendModeType){
				String id = StaticMethod.nullObject2String(main.get("id"));
				String proids[] = request.getParameterValues("proid");
				String producerIds[] = request.getParameterValues("producerId1");
				String producerNames[] = request.getParameterValues("producerName1");
				String netIds[] = request.getParameterValues("netId1");
				String netNames[] = request.getParameterValues("netName1");
				String deviceNames[] = request.getParameterValues("deviceName1");
				String deviceIds[] = request.getParameterValues("deviceId1");
				String now = StaticMethod.getCurrentDateTime();
				if (producerNames != null && producerNames.length > 0)
				{
					List typeList = new ArrayList();
					for (int i = 0; i < producerNames.length; i++)
					{
						String proid = StaticMethod.nullObject2String(proids[i]);
						if (!"".equals(proid))
						{
							MofficeDataProMatch mat = proMgr.getProMatchObjectById(proid);
							mat.setProducerId(producerIds[i]);
							mat.setProducerName(producerNames[i]);
							mat.setCreateTime(now);
							mat.setNetId(netIds[i]);
							mat.setNetName(netNames[i]);
							mat.setDeviceName(deviceNames[i]);
							proMgr.saveOrUpdate(mat);
						} else
						{
							Map type = new HashMap();
							type.put("id", UUIDHexGenerator.getInstance().getID());
							type.put("buissType", "");
							type.put("majorType", "");
							type.put("mainId", id);
							type.put("producerId", producerIds[i]);
							type.put("producerName", producerNames[i]);
							type.put("producerType", "");
							type.put("netId", netIds[i]);
							type.put("netName", netNames[i]);
							type.put("deviceName", deviceNames[i]);
							type.put("deviceId", deviceIds[i]);
							type.put("accessories", "");
							type.put("createTime", now);
							type.put("correKey", "");
							type.put("phaseName", "");
							type.put("rejectPhaseName", "");
							type.put("linkId", "");
							type.put("mainStyle6", "");
							type.put("mainStyle7", "");
							type.put("mainStyle8", "");
							type.put("mainStyle9", "");
							type.put("mainStyle10", "");
							typeList.add(type);
						}
					}
					if (typeList != null && !typeList.isEmpty())
						downMgr.batchExcuteSql(typeList, new MofficeDataProMatch(), "insert");
				}
			}
		}
			
		// 当从归档环节退回到地市拨测环节时候 将已经驳回过的link操作做标记
		if ("108".equals(operateType) || "117".equals(operateType)) {
			String ids = StaticMethod.nullObject2String(request.getParameter("linkRejectIds"));
			ids = this.c2ids(ids);
			List baseLinks = this.getLinkService().getLinksBySql("from MofficeDataLink where id in " + ids);
			for (int i = 0; null != baseLinks && i < baseLinks.size(); i++) {
				MofficeDataLink tmpLink = (MofficeDataLink) baseLinks.get(i);
				tmpLink.setLinkStyle1("mark");
				this.getLinkService().addLink(tmpLink);
			}
		}

		// 当审核通过的时候，需要将选择的局数据制作人保存起来 然后在激活成功后自动流转到这个人的待办下面
		int mainSendModeType = StaticMethod.nullObject2int(main.get("mainSendModeType"));
		if ("101".equals(operateType) && mainSendModeType == 0){
			String corrKey = StaticMethod.nullObject2String(request.getParameter("corrKey"));
			int proMatchSize = StaticMethod.nullObject2int(request.getParameter("proMatchSize"));
			String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
			if("AutoCheckTask".equals(phaseId)){
				if(proMatchSize==1){
					String userId = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("autoCheck.userId");
					operate.put("dealPerformer", userId);
					operate.put("dealPerformerLeader", userId);
					operate.put("dealPerformerType", "user");
					BocoLog.info(this, "userId==" + userId);
				}else if(proMatchSize>1){
					String userId = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("autoCheck.userId");
					String des = "";
					String det = "";
					for(int i=0;i<proMatchSize;i++){
						if(i==0){
							des = userId;
							det = "user";
						}else{
							des+=","+userId;
							det+=",user";
						}
					}
					BocoLog.info(this, "corrKey" + corrKey);
					operate.put("extendKey1", corrKey);
					operate.put("dealPerformer", des);
					operate.put("dealPerformerLeader", des);
					operate.put("dealPerformerType", det);
					operate.put("hasNextTaskFlag", "true");
					operate.put("extendKey2", "DataCheckTask");
					operate.put("gatherPhaseId", "DataCheckTask");
					BocoLog.info(this, "des==" + des);
					BocoLog.info(this, "det==" + det);
				}
			}else if("OfficeMadeTask".equals(phaseId)){
				operate.put("extendKey1", corrKey);
				operate.put("extendKey2", "DataCheckTask");
				operate.put("gatherPhaseId", "DataCheckTask");
			}	
			
		}
		if ("101".equals(operateType) && 1 == mainSendModeType) {
			String corrKey = StaticMethod.nullObject2String(request.getParameter("corrKey"));
			String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
			
			
			if ("OfficeMadeTask".equals(phaseId))
			{
				operate.put("extendKey1", corrKey);
				operate.put("extendKey2", "DataCheckTask");
				operate.put("gatherPhaseId", "DataCheckTask");
			}
		}
		if ("101".equals(operateType) && 1 == mainSendModeType)
		{
			operate.put("phaseId", "OfficeMadeTask");
			link.put("operateType", "120");
		}
		if ("111".equals(operateType))
		{
			String corrKey = StaticMethod.nullObject2String(request.getParameter("corrKey"));
			int proMatchSize = StaticMethod.nullObject2int(request.getParameter("proMatchSize"));
			String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
			
			
			if("AutoACheckTask".equals(phaseId)){
				if(proMatchSize==1){
					String userId = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("autoCheck.userId");
					operate.put("dealPerformer", userId);
					operate.put("dealPerformerLeader", userId);
					operate.put("dealPerformerType", "user");
					BocoLog.info(this, "userId==" + userId);
				}else if(proMatchSize>1){
					String userId = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("autoCheck.userId");
					String des = "";
					String det = "";
					for(int i=0;i<proMatchSize;i++){
						if(i==0){
							des = userId;
							det = "user";
						}else{
							des+=","+userId;
							det+=",user";
						}
					}
					BocoLog.info(this, "corrKey" + corrKey);
					operate.put("extendKey1", corrKey);
					operate.put("dealPerformer", des);
					operate.put("dealPerformerLeader", des);
					operate.put("dealPerformerType", det);
					operate.put("hasNextTaskFlag", "true");
					operate.put("extendKey2", "DataCheckTask");
					operate.put("gatherPhaseId", "DataCheckTask");
					BocoLog.info(this, "des==" + des);
					BocoLog.info(this, "det==" + det);
				}
			}	
		}
		
		if ("104".equals(operateType))
			sheetMap = autoHold(sheetMap);
		String mainStyle2 = StaticMethod.nullObject2String(main.get("mainStyle2"));
		System.out.println("接口调用成功的标志位" + mainStyle2);
		if ("61".equals(operateType) && "OfficeMadeTask".equals(taskName) && "false".equals(mainStyle2))
		{
			operate.put("phaseId", "RejectTask");
			link.put("operateType", "119");
		}
		
		
		if (dealperformers.length >= 1 && !"101".equals(operateType)&& !"111".equals(operateType)) {
			String corrKey = "";
			String tmp = "";
			for (int i = 0; i < dealperformers.length; i++) {
				tmp = UUIDHexGenerator.getInstance().getID();
				if (dealperformers.length == 1) {
					corrKey = tmp;
				} else {
					if (corrKey.equals("")) {
						corrKey = tmp;
					} else {
						corrKey = corrKey + "," + tmp;
					}

				}
			}
			BocoLog.info(this, "corrKey" + corrKey);
			operate.put("extendKey1", corrKey);

			// 设置归档为一派多操作的聚合点
			if (mainSendModeType == 0 && ("0".equals(operateType) || "53".equals(operateType) || "109".equals(operateType) || "108".equals(operateType))) {
				operate.put("extendKey2", "HoldTask");
				operate.put("gatherPhaseId", "HoldTask");
			}
			if (mainSendModeType == 1 && "117".equals(operateType))
			{
				operate.put("extendKey2", "DataCheckTask");
				operate.put("gatherPhaseId", "DataCheckTask");
			}
		}
		

		sheetMap.put("link", link);
		sheetMap.put("operate", operate);

		this.setFlowEngineMap(sheetMap);
	}
	
	private static HashMap autoHold(HashMap sheetMap)
	{
		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		try
		{
			IMofficeDataLinkManager linkManager = (IMofficeDataLinkManager)ApplicationContextHolder.getInstance().getBean("iMofficeDataLinkManager");
			IMofficeDataTaskManager taskManager = (IMofficeDataTaskManager)ApplicationContextHolder.getInstance().getBean("iMofficeDataTaskManager");
			Map main = (HashMap)sheetMap.get("main");
			Map link = (HashMap)sheetMap.get("link");
			Map operate = (HashMap)sheetMap.get("operate");
			main.put("status", "1");
			operate.put("phaseId", "Over");
			operate.put("hasNextTaskFlag", "true");
			String tkid = "_AI:" + UUIDHexGenerator.getInstance().getID();
			MofficeDataTask holdTask = (MofficeDataTask)taskManager.getTaskModelObject();
			calendar.setTime(new Date());
			HashMap taskObject = new HashMap();
			taskObject.put("id", tkid);
			taskObject.put("createTime", new Date());
			taskObject.put("taskStatus", "5");
			taskObject.put("processId", StaticMethod.nullObject2String(main.get("piid")));
			taskObject.put("sheetKey", StaticMethod.nullObject2String(main.get("id")));
			taskObject.put("sheetId", StaticMethod.nullObject2String(main.get("sheetId")));
			taskObject.put("title", StaticMethod.nullObject2String(main.get("title")));
			taskObject.put("acceptTimeLimit", (Date)link.get("nodeAcceptLimit"));
			taskObject.put("completeTimeLimit", (Date)link.get("nodeCompleteLimit"));
			taskObject.put("preLinkId", StaticMethod.nullObject2String(link.get("id")));
			taskObject.put("IfWaitForSubTask", "false");
			taskObject.put("taskDisplayName", "待归档");
			taskObject.put("taskName", "HoldTask");
			taskObject.put("operateRoleId", StaticMethod.nullObject2String(main.get("sendRoleId")));
			taskObject.put("taskOwner", StaticMethod.nullObject2String(main.get("sendUserId")));
			taskObject.put("operateType", "subrole");
			taskObject.put("flowName", "MofficeData");
			taskObject.put("sendTime", (Date)main.get("sendTime"));
			SheetBeanUtils.populate(holdTask, taskObject);
			holdTask.setCreateDay(calendar.get(5));
			holdTask.setCreateMonth(calendar.get(2) + 1);
			holdTask.setCreateYear(calendar.get(1));
			taskManager.addTask(holdTask);
			System.out.println("task表入库成功===");
			MofficeDataLink holdLink = (MofficeDataLink)linkManager.getLinkObject();
			HashMap linkMap = new HashMap();
			linkMap.put("id", UUIDHexGenerator.getInstance().getID());
			linkMap.put("mainId", StaticMethod.nullObject2String(main.get("id")));
			Date currentTime = new Date();
			Calendar date_one = Calendar.getInstance();
			date_one.setTime(currentTime);
			date_one.set(13, date_one.get(13) + 20);
			linkMap.put("operateTime", date_one.getTime());
			linkMap.put("operateType", Integer.valueOf(18));
			linkMap.put("operateUserId", StaticMethod.nullObject2String(main.get("sendUserId")));
			linkMap.put("operateDeptId", StaticMethod.nullObject2String(main.get("sendDeptId")));
			linkMap.put("operateRoleId", StaticMethod.nullObject2String(main.get("sendRoleId")));
			linkMap.put("activeTemplateId", "HoldTask");
			linkMap.put("aiid", tkid);
			linkMap.put("piid", StaticMethod.nullObject2String(main.get("piid")));
			linkMap.put("correlationKey", UUIDHexGenerator.getInstance().getID());
			linkMap.put("preLinkId", StaticMethod.nullObject2String(link.get("preLinkId")));
			SheetBeanUtils.populate(holdLink, linkMap);
			linkManager.addLink(holdLink);
			sheetMap.put("operate", operate);
			System.out.println("link表入库成功===");
			System.out.println("自动归档成功");
		}
		catch (Exception e)
		{
			System.out.println("The reply recode" + e.getMessage());
			e.printStackTrace();
		}
		return sheetMap;
	}

	/**
	 * 设置需要从流程引擎中取的派往对象，包括派发，抄送，送审
	 */
	public Map getProcessOjbectAttribute() {
		Map attributeMap = new HashMap();
		attributeMap.put("dealPerformer", "dealPerformer");
		attributeMap.put("copyPerformer", "copyPerformer");
		attributeMap.put("auditPerformer", "auditPerformer");
		attributeMap.put("subAuditPerformer", "subAuditPerformer");
		attributeMap.put("objectName", "operate");
		return attributeMap;
	}

	public Map getParameterMap() {
		Map attributeMap = new HashMap();
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
	public void showInputDealPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.showInputDealPage(mapping, form, request, response);
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"));
		// 驳回上一级，需要取出上一级的角色和phaseId
		if (operateType.equals("4") || operateType.equals("105") || operateType.equals("107")) {
			BaseLink prelink = this.getLinkService().getSingleLinkPO(preLinkId);
			if (prelink != null) {
				request.setAttribute("fOperateroleid", prelink.getOperateRoleId());
				request.setAttribute("fPreTaskName", prelink.getActiveTemplateId());
			}
		}

		if (operateType.equals("105") || operateType.equals("107") || operateType.equals("118")){
			BaseLink prelink = this.getLinkService().getSingleLinkPO(preLinkId);
			if (prelink != null) {
				request.setAttribute("fOperateuserid", prelink.getOperateUserId());
				request.setAttribute("fOperateroleid", prelink.getOperateRoleId());
				request.setAttribute("fPreTaskName", prelink.getActiveTemplateId());
			}
		}

		// 当选择从归档环节退回的时候，只能退回到地市拨测环节的
		if (operateType.equals("108")) {
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			String condition = " mainId='" + sheetKey + "' and operateType='106' and linkStyle1 is null ";
			List baseLinks = this.getLinkService().getLinksBycondition(condition, "MofficeDataLink");
			request.setAttribute("baseLinks", baseLinks);
		}

//		if ("103".equals(operateType)) {// 将token串传给对端，用于登陆对端系统
//			IWorkApplyManageTokenManager tokenMgr = (IWorkApplyManageTokenManager) ApplicationContextHolder
//					.getInstance().getBean("iWorkApplyManageTokenManager");
//			TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
//			String md5Id = tokenMgr.getMd5Id(sessionform.getUserid(), sessionform.getUsername());
//			BocoLog.info(this, "===md5Id===" + md5Id);
//			request.setAttribute("md5String", md5Id);
//
//		}
		
		if (operateType.equals("117"))
		{
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			String condition = " mainId='" + sheetKey + "' and operateType='116' and linkStyle1 is null ";
			List baseLinks = getLinkService().getLinksBycondition(condition, "MofficeDataLink");
			request.setAttribute("baseLinks", baseLinks);
		}
		MofficeDataMain main = (MofficeDataMain)request.getAttribute("sheetMain");
		if (main == null)
		{
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			main = (MofficeDataMain)getMainService().getSingleMainPO(sheetKey);
		}
		int mainSendModeType = StaticMethod.nullObject2int(main.getMainSendModeType());
		/*审批通过的时候，将预先选好的执行人呈现到前台 add by weichao 20161206*/
		if (operateType.equals("101") && mainSendModeType == 0){
			String tkid = StaticMethod.nullObject2String(request.getParameter("TKID"));
			IMofficeDataProMatchManager proMgr = (IMofficeDataProMatchManager)ApplicationContextHolder.getInstance()
						.getBean("iMofficeDataProMatchManager");
			if(!"".equals(tkid)){
				MofficeDataTask task = (MofficeDataTask)this.getTaskService().getSinglePO(tkid);
				String correKey = task.getCorrelationKey();
				MofficeDataProMatch matt = proMgr.getProMatchObjectByCorreKey(correKey);
				if(null!=matt && !"".equals(matt.getId())){/*有过驳回记录的单条proMatch对象*/
					matt.setPhaseName("AutoCheckTask");
					matt.setRejectPhaseName("RejectTask");
					if("user".equals(matt.getProducerType())){
						request.setAttribute("nextPhasesId4s", "AutoCheckTask");
					}else if("subrole".equals(matt.getProducerType())){
						request.setAttribute("nextPhasesId4s", "OfficeMadeTask");
					}
					request.setAttribute("dealPerformer", matt.getProducerId());
					request.setAttribute("dealPerformerName", matt.getProducerName());
					request.setAttribute("dealPerformerType", matt.getProducerType());
					request.setAttribute("proMatchSize", "1");
					proMgr.saveOrUpdate(matt);
				}else{/*首次审批通过*/
					String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
					List list = proMgr.getProMatchObjects(sheetKey);	
					if(null!=list&&!list.isEmpty()){
						String corrKey = "";
						String dealPerformer = "";
						String dealPerformerName = "";
						String dealPerformerType = "";
						if(1==list.size()){
							MofficeDataProMatch mat = (MofficeDataProMatch)list.get(0);
							dealPerformer = mat.getProducerId();
							dealPerformerName = mat.getProducerName();
							dealPerformerType = mat.getProducerType();
							mat.setCorreKey(correKey);
							mat.setPhaseName("AutoCheckTask");
							mat.setRejectPhaseName("RejectTask");
							proMgr.saveOrUpdate(mat);
							
							if("user".equals(mat.getProducerType())){
								request.setAttribute("nextPhasesId4s", "AutoCheckTask");
							}else if("subrole".equals(mat.getProducerType())){
								request.setAttribute("nextPhasesId4s", "OfficeMadeTask");
							}
						}else{
							for(int i=0;i<list.size();i++){
								MofficeDataProMatch mat = (MofficeDataProMatch)list.get(i);
								if(i==0){
									dealPerformer = mat.getProducerId();
									dealPerformerName = mat.getProducerName();
									dealPerformerType = mat.getProducerType();
								}else{
									dealPerformer +="," + mat.getProducerId();
									dealPerformerName +="，" + mat.getProducerName();
									dealPerformerType +="," +  mat.getProducerType();
								}			
								
								/*将审批环节一派多的corrKey在这里初始化*/
								if("".equals(StaticMethod.nullObject2String(mat.getCorreKey()))){
									mat.setCorreKey(UUIDHexGenerator.getInstance().getID());
									mat.setPhaseName("AutoCheckTask");
									mat.setRejectPhaseName("RejectTask");
									proMgr.saveOrUpdate(mat);
								}
								
								if(i==0){
									corrKey = mat.getCorreKey();
									if("user".equals(mat.getProducerType())){
										request.setAttribute("nextPhasesId4s", "AutoCheckTask");
									}else if("subrole".equals(mat.getProducerType())){
										request.setAttribute("nextPhasesId4s", "OfficeMadeTask");
									}
								}else{
									corrKey = corrKey + "," + mat.getCorreKey();
								}			
							}
						}						
						
						request.setAttribute("dealPerformer", dealPerformer);
						request.setAttribute("dealPerformerName", dealPerformerName);
						request.setAttribute("dealPerformerType", dealPerformerType);
						request.setAttribute("corrKey", corrKey);
						request.setAttribute("proMatchSize", Integer.valueOf(list.size()));
						
					}
				}
			}
			
			
		}
		
		if (operateType.equals("101") && 1 == mainSendModeType)
		{
			request.setAttribute("nextPhasesId4s", "OfficeMadeTask");
			String tkid = StaticMethod.nullObject2String(request.getParameter("TKID"));
			IMofficeDataProMatchManager proMgr = (IMofficeDataProMatchManager)ApplicationContextHolder.getInstance().getBean("iMofficeDataProMatchManager");

			if (!"".equals(tkid))
			{
				MofficeDataTask task = (MofficeDataTask)getTaskService().getSinglePO(tkid);
				String correKey = task.getCorrelationKey();
				MofficeDataProMatch matt = proMgr.getProMatchObjectByCorreKey(correKey);
				if (matt != null && !"".equals(matt.getId()))
				{
					request.setAttribute("dealPerformer", matt.getProducerId());
					request.setAttribute("dealPerformerName", matt.getProducerName());
					request.setAttribute("dealPerformerType", matt.getProducerType());
					request.setAttribute("proMatchSize", "1");
				} else
				{
					String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
					List list = proMgr.getProMatchObjects(sheetKey);
					if (list != null && !list.isEmpty())
					{
						String corrKey = "";
						String dealPerformer = "";
						String dealPerformerName = "";
						String dealPerformerType = "";
						if (1 == list.size())
						{
							MofficeDataProMatch mat = (MofficeDataProMatch)list.get(0);
							dealPerformer = mat.getProducerId();
							dealPerformerName = mat.getProducerName();
							dealPerformerType = mat.getProducerType();
							mat.setCorreKey(correKey);


							proMgr.saveOrUpdate(mat);
						} else
						{
							for (int i = 0; i < list.size(); i++)
							{
								MofficeDataProMatch mat = (MofficeDataProMatch)list.get(i);
								if (i == 0)
								{
									dealPerformer = mat.getProducerId();
									dealPerformerName = mat.getProducerName();
									dealPerformerType = mat.getProducerType();
								} else
								{
									dealPerformer = dealPerformer + "," + mat.getProducerId();
									dealPerformerName = dealPerformerName + "，" + mat.getProducerName();
									dealPerformerType = dealPerformerType + "," + mat.getProducerType();
								}
								if ("".equals(StaticMethod.nullObject2String(mat.getCorreKey())))
								{
									mat.setCorreKey(UUIDHexGenerator.getInstance().getID());
									proMgr.saveOrUpdate(mat);
								}

								if (i == 0)
									corrKey = mat.getCorreKey();
								else
									corrKey = corrKey + "," + mat.getCorreKey();
							}

						}
						request.setAttribute("dealPerformer", dealPerformer);
						request.setAttribute("dealPerformerName", dealPerformerName);
						request.setAttribute("dealPerformerType", dealPerformerType);
						request.setAttribute("corrKey", corrKey);
						request.setAttribute("proMatchSize", Integer.valueOf(list.size()));
					}
				}
			}
		}
		
		/*质检不通过的时候，需要找出那条对应的proMatch记录 add by weichao 20161207*/
		if (operateType.equals("111") && mainSendModeType == 0){
			IMofficeDataProMatchManager proMgr = (IMofficeDataProMatchManager)ApplicationContextHolder.getInstance()
						.getBean("iMofficeDataProMatchManager");
			String tkid = StaticMethod.nullObject2String(request.getParameter("TKID"));
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			List list = proMgr.getProMatchObjects(sheetKey);	
			if(!"".equals(tkid)){
				MofficeDataTask task = (MofficeDataTask)this.getTaskService().getSinglePO(tkid);
				String correKey = task.getCorrelationKey();				
				if(null!=list&&!list.isEmpty()){
					String corrKey = "";
					String dealPerformer = "";
					String dealPerformerName = "";
					String dealPerformerType = "";
					if(1==list.size()){
						MofficeDataProMatch mat = (MofficeDataProMatch)list.get(0);
						dealPerformer = mat.getProducerId();
						dealPerformerName = mat.getProducerName();
						dealPerformerType = mat.getProducerType();
						mat.setCorreKey(correKey);
						mat.setPhaseName("AutoACheckTask");
						mat.setRejectPhaseName("DataCheckTask");
						proMgr.saveOrUpdate(mat);
					}else{
						for(int i=0;i<list.size();i++){
							MofficeDataProMatch mat = (MofficeDataProMatch)list.get(i);
							if(i==0){
								dealPerformer = mat.getProducerId();
								dealPerformerName = mat.getProducerName();
								dealPerformerType = mat.getProducerType();
							}else{
								dealPerformer +="," + mat.getProducerId();
								dealPerformerName +="，" + mat.getProducerName();
								dealPerformerType +="," +  mat.getProducerType();
							}	
							
							/*将审批环节一派多的corrKey在这里初始化*/			
							mat.setCorreKey(UUIDHexGenerator.getInstance().getID());
							mat.setPhaseName("AutoACheckTask");
							mat.setRejectPhaseName("DataCheckTask");
							proMgr.saveOrUpdate(mat);
							
							
							if(i==0){
								corrKey = mat.getCorreKey();	
							}else{
								corrKey = corrKey + "," + mat.getCorreKey();
							}
							
						}
					}
					request.setAttribute("dealPerformer", dealPerformer);
					request.setAttribute("dealPerformerName", dealPerformerName);
					request.setAttribute("dealPerformerType", dealPerformerType);
					request.setAttribute("corrKey", corrKey);
					request.setAttribute("proMatchSize", Integer.valueOf(list.size()));	
				}
			}		
		}
		
		if (operateType.equals("104") && mainSendModeType == 0)
		{
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			IMofficeDataProMatchManager proMgr = (IMofficeDataProMatchManager)ApplicationContextHolder.getInstance().getBean("iMofficeDataProMatchManager");
			List list = proMgr.getProMatchObjects(sheetKey);
			if (list != null && !list.isEmpty())
				request.setAttribute("prolist", list);
		}

	}

	public void performPreCommit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
	String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
	BocoLog.info(this, "operateType from preCommit===" + operateType);
	if ("101".equals(operateType) || "111".equals(operateType))
	{



		String id = StaticMethod.nullObject2String(request.getParameter("sheetKey"));

		MofficeDataMain main = (MofficeDataMain)getMainService().getSingleMainPO(id);
		int mainSendModeType = StaticMethod.nullObject2int(main.getMainSendModeType());
		if (mainSendModeType == 0)
		{
			String linkIsNeedTest = StaticMethod.nullObject2String(request.getParameter("linkIsNeedTest"));
			String tkid = StaticMethod.nullObject2String(request.getParameter("TKID")).trim();
			MofficeDataTask task = (MofficeDataTask)getTaskService().getSinglePO(tkid);
			String dealperformer = StaticMethod.nullObject2String(request.getParameter("dealPerformer"));
			String returnvalue = MofficeDataInService.neWorkSheet4More(main, task, dealperformer, linkIsNeedTest);
			System.out.println("操作类型为101和111时 The returnvalue is " + returnvalue);
			if (!"000".equals(returnvalue))
			{
				JSONArray data = new JSONArray();
				JSONObject o = new JSONObject();
				o.put("text", "调局数据系统接口失败，请联系管理员！");
				data.put(o);
				JSONObject jsonRoot = new JSONObject();
				jsonRoot.put("data", data);
				jsonRoot.put("status", "2");
				JSONUtil.print(response, jsonRoot.toString());
			} else
			{
				super.performPreCommit(mapping, form, request, response);
			}
		} else
		{
			super.performPreCommit(mapping, form, request, response);
		}
	} else
	if ("61".equals(operateType) && "OfficeMadeTask".equals(taskName))
	{
		IMofficeDataMainManager mainMgr = (IMofficeDataMainManager)ApplicationContextHolder.getInstance().getBean("iMofficeDataMainManager");
		String id = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		MofficeDataMain main = (MofficeDataMain)getMainService().getSingleMainPO(id);
		int mainSendModeType = StaticMethod.nullObject2int(main.getMainSendModeType());
		if (mainSendModeType == 0)
		{
			String tkid = StaticMethod.nullObject2String(request.getParameter("TKID")).trim();
			MofficeDataTask task = (MofficeDataTask)getTaskService().getSinglePO(tkid);
			IMofficeDataProMatchManager proMgr = (IMofficeDataProMatchManager)ApplicationContextHolder.getInstance().getBean("iMofficeDataProMatchManager");
			MofficeDataProMatch mat = proMgr.getProMatchObjectByCorreKey(task.getCorrelationKey());
			String sendObject = StaticMethod.nullObject2String(mat.getProducerType());
			if ("subrole".equals(sendObject))
			{
				TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");

				String dealperformer = sessionform.getUserid();
				String returnvalue = MofficeDataInService.neWorkSheet2T(main, task.getCorrelationKey(), dealperformer);
				System.out.println("操作类型为61当前任务为OfficeMadeTask The returnvalue is " + returnvalue);
				if (!"0".equals(returnvalue))
				{
					main.setMainStyle2("false");
					mainMgr.saveOrUpdateMain(main);
					JSONArray data = new JSONArray();
					JSONObject o = new JSONObject();
					o.put("text", "调局数据系统接口失败，请联系管理员！");
					data.put(o);
					JSONObject jsonRoot = new JSONObject();
					jsonRoot.put("data", data);
					jsonRoot.put("status", "1");
					JSONUtil.print(response, jsonRoot.toString());
				} else
				{
					mat.setProducerId(dealperformer);
					mat.setProducerName(sessionform.getUsername());
					mat.setProducerType("user");
					proMgr.saveOrUpdate(mat);
					super.performPreCommit(mapping, form, request, response);
				}
			} else
			{
				super.performPreCommit(mapping, form, request, response);
			}
		} else
		{
			super.performPreCommit(mapping, form, request, response);
		}
	} else
	{
		super.performPreCommit(mapping, form, request, response);
	}
}

public void showDealReplyAcceptPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
	if ("DataCheckTask".equals(taskName))
	{
		String TKID = StaticMethod.nullObject2String(request.getParameter("TKID"));
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		List list = getAllReplayObject(TKID, sheetKey);
		if (list != null && list.size() > 0)
			request.setAttribute("allList", list);
	}
	super.showDealReplyAcceptPage(mapping, form, request, response);
}

public void showDealReplyRejectPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	String taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
	if ("DataCheckTask".equals(taskName))
	{
		String TKID = StaticMethod.nullObject2String(request.getParameter("TKID"));
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		List list = getAllReplayObject(TKID, sheetKey);
		if (list != null && list.size() > 0)
			request.setAttribute("allList", list);
	}
	super.showDealReplyRejectPage(mapping, form, request, response);
}

private static List getAllReplayObject(String TKID, String sheetKey)
{
	IDownLoadSheetAccessoriesService downMgr;
	String getAllReplayObject;
	downMgr = (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
	getAllReplayObject = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("getAllReplayObject");
	getAllReplayObject = getAllReplayObject.replace("@parentTaskId@", TKID);
	getAllReplayObject = getAllReplayObject.replace("@mainId@", sheetKey);
	List list = new ArrayList();
	try {
		list = downMgr.getSheetAccessoriesList(getAllReplayObject);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	List newList = new ArrayList();
	if (list != null && list.size() > 0)
	{
		for (int i = 0; i < list.size(); i++)
		{
			Map temp = (Map)list.get(i);
			MofficeDataTask task = new MofficeDataTask();
			String status = StaticMethod.nullObject2String(temp.get("taskStatus"));
			String subTaskDealFalg = StaticMethod.nullObject2String(temp.get("subTaskDealFalg"));
			System.out.println("The getAllReplayObject subTaskDealFalg is " + subTaskDealFalg);
			String id = StaticMethod.nullObject2String(temp.get("id"));
			if ("2".equals(status) || "8".equals(status))
				task.setTaskDisplayName("未回复");
			else
			if ("5".equals(status) && "true".equals(subTaskDealFalg))
				task.setTaskDisplayName("已回复通过");
			else
				task.setTaskDisplayName("待处理回复");
			task.setOperateRoleId(StaticMethod.nullObject2String(temp.get("operateRoleId")));
			task.setId(id);
			newList.add(task);
		}

	}
	return newList;
}

	/**
	 * 
	 */
	public void showDraftPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.showDraftPage(mapping, form, request, response);
		IMofficeDataBuisTypeManager buisTypeMgr = (IMofficeDataBuisTypeManager) ApplicationContextHolder.getInstance()
				.getBean("iMofficeDataBuisTypeManager");
		List list = buisTypeMgr.getBuisTypeObjects();
		request.setAttribute("buiss", list);
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		BocoLog.info(this, "operateType from showDraftPage===" + operateType);

		/*增加字典值的解析 add by weichao 20161205*/
		ITawSystemDictTypeManager dictMgr = (ITawSystemDictTypeManager) ApplicationContextHolder.getInstance()
				.getBean("ItawSystemDictTypeManager");
		String parentdictid = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("majorDictId");
		BocoLog.info(this, "userOrsubrole====" + parentdictid);
		List majors = dictMgr.getDictSonsByDictid(parentdictid);
		request.setAttribute("majors", majors);
		BocoLog.info(this, "userOrsubrole====" + majors.size());
		
		String userOrsubrole = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("userOrsubrole");
		BocoLog.info(this, "userOrsubrole====" + userOrsubrole);
		if("0".equals(userOrsubrole)){
			request.setAttribute("chooseType", "user");
		}else if("1".equals(userOrsubrole)){
			request.setAttribute("chooseType", "subrole");
		}
		
		
		
		
		/*重新派发的时候不允许删除或者新增proMatch信息，只允许修改proMatch信息*/
		IMofficeDataProMatchManager proMgr = (IMofficeDataProMatchManager)ApplicationContextHolder.getInstance()
				.getBean("iMofficeDataProMatchManager");
		if("54".equals(operateType)){
			String tkid = StaticMethod.nullObject2String(request.getParameter("TKID")).trim();
			MofficeDataTask task = (MofficeDataTask)this.getTaskService().getSinglePO(tkid);
			String correKey = task.getCorrelationKey();
			MofficeDataProMatch matt = proMgr.getProMatchObjectByCorreKey(correKey);
			List prolist = new ArrayList();
			prolist.add(matt);
			request.setAttribute("prolist", prolist);
			request.setAttribute("prolistnum", "1");
			request.setAttribute("delFlag", "false");
		}else{
			String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
			if(!"".equals(sheetKey)){	
				List prolist = proMgr.getProMatchObjects(sheetKey);	
				if(null!=prolist && !prolist.isEmpty()){
					request.setAttribute("prolist", prolist);
					request.setAttribute("prolistnum", Integer.valueOf(prolist.size()));
				}
			}
			request.setAttribute("delFlag", "true");
		}
		
//		bylyg start
		IDownLoadSheetAccessoriesService sqlMgr=(IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
		String sql="select areaid keyId,areaname keyName from taw_system_area where parentareaid='18' AND areaid NOT IN ('1817','1818') order by areaid";
		List cityList = sqlMgr.getSheetAccessoriesList(sql);
		request.setAttribute("cityList", cityList);
		
		//bylyg start
		
	}

	public void showInputNewSheetPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.showInputNewSheetPage(mapping, form, request, response);
		IMofficeDataBuisTypeManager buisTypeMgr = (IMofficeDataBuisTypeManager) ApplicationContextHolder.getInstance()
				.getBean("iMofficeDataBuisTypeManager");
		List list = buisTypeMgr.getBuisTypeObjects();
		request.setAttribute("buiss", list);
		
		/*增加字典值的解析 add by weichao 20161205*/
		ITawSystemDictTypeManager dictMgr = (ITawSystemDictTypeManager) ApplicationContextHolder.getInstance()
				.getBean("ItawSystemDictTypeManager");
		String parentdictid = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("majorDictId");
		BocoLog.info(this, "userOrsubrole====" + parentdictid);
		List majors = dictMgr.getDictSonsByDictid(parentdictid);
		request.setAttribute("majors", majors);
		BocoLog.info(this, "userOrsubrole====" + majors.size());

		String userOrsubrole = XmlManage.getFile("/config/mofficedata-util.xml").getProperty("userOrsubrole");
		BocoLog.info(this, "userOrsubrole====" + userOrsubrole);
		if("0".equals(userOrsubrole)){
			request.setAttribute("chooseType", "user");
		}else if("1".equals(userOrsubrole)){
			request.setAttribute("chooseType", "subrole");
		}
		
//		bylyg start
		IDownLoadSheetAccessoriesService sqlMgr=(IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
		String sql="select areaid keyId,areaname keyName from taw_system_area where parentareaid='18' AND areaid NOT IN ('1817','1818') order by areaid";
		List cityList = sqlMgr.getSheetAccessoriesList(sql);
		request.setAttribute("cityList", cityList);
		
		//bylyg start
		
	}

	public void showDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.showDetailPage(mapping, form, request, response);

		MofficeDataMain mainObject = (MofficeDataMain) request.getAttribute("sheetMain");
		String nets = StaticMethod.nullObject2String(mainObject.getMainNetType());
		String netNames = this.getDulDictName(nets);
		String devices = StaticMethod.nullObject2String(mainObject.getMainDeviceFa());
		String deviceNames = this.getDulDictName(devices);
		request.setAttribute("mainNetType", netNames);
		request.setAttribute("mainDeviceFa", deviceNames);

	}

	public void showMainDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.showMainDetailPage(mapping, form, request, response);

		MofficeDataMain mainObject = (MofficeDataMain) request.getAttribute("sheetMain");
		String nets = StaticMethod.nullObject2String(mainObject.getMainNetType());
		String netNames = this.getDulDictName(nets);
		String devices = StaticMethod.nullObject2String(mainObject.getMainDeviceFa());
		String deviceNames = this.getDulDictName(devices);
		request.setAttribute("mainNetType", netNames);
		request.setAttribute("mainDeviceFa", deviceNames);
	}

	/**
	 * 显示处理过程
	 */
	public void showSheetDealList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		super.showSheetDealList(mapping, form, request, response);

		IMofficeDataSubLinkManager sublinkMgr = (IMofficeDataSubLinkManager) ApplicationContextHolder.getInstance()
				.getBean("iMofficeDataSubLinkManager");
		List list = (List) request.getAttribute("HISTORY");
		for (int i = 0; i < list.size(); i++) {
			BaseLink link = (BaseLink) list.get(i);
			if ("103".equals(link.getOperateType().toString()) && "OfficeMadeTask".equals(link.getActiveTemplateId())) {
				List sublinks = sublinkMgr.getSubLinks(link.getId());
				request.setAttribute(link.getId(), sublinks);
			}
		}

	}

	/**
	 * 显示处理过程
	 */
	public void showSheetAccessoriesList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	throws Exception
{
	super.showSheetAccessoriesList(mapping, form, request, response);
	List list = (List)request.getAttribute("sheetAccessories");
	if (list == null)
		list = new ArrayList();
	String mainId = StaticMethod.null2String(request.getParameter("id"));
	IMofficeDataMainManager mainMgr = (IMofficeDataMainManager)ApplicationContextHolder.getInstance().getBean("iMofficeDataMainManager");
	IMofficeDataProMatchManager proMgr = (IMofficeDataProMatchManager)ApplicationContextHolder.getInstance().getBean("iMofficeDataProMatchManager");
	MofficeDataMain baseMain = (MofficeDataMain)mainMgr.getSingleMainPO(mainId);
	List prolist = proMgr.getProMatchObjects(baseMain.getId());
	StringBuffer where = new StringBuffer();
	if (prolist != null && !prolist.isEmpty())
	{
		for (int i = 0; i < prolist.size(); i++)
		{
			MofficeDataProMatch mat = (MofficeDataProMatch)prolist.get(i);
			if (mat.getMainStyle6() != null)
			{
				if (where.length() > 0)
					where.append(",");
				where.append(mat.getMainStyle6());
			}
			if (mat.getMainStyle7() != null)
			{
				if (where.length() > 0)
					where.append(",");
				where.append(mat.getMainStyle7());
			}
			if (mat.getMainStyle8() != null)
			{
				if (where.length() > 0)
					where.append(",");
				where.append(mat.getMainStyle8());
			}
			if (mat.getMainStyle9() != null)
			{
				if (where.length() > 0)
					where.append(",");
				where.append(mat.getMainStyle9());
			}
			if (mat.getMainStyle10() != null)
			{
				if (where.length() > 0)
					where.append(",");
				where.append(mat.getMainStyle10());
			}
		}

	}
	BocoLog.info(this, "==" + where.toString());
	if (!where.toString().equals(""))
	{
		List attachments = getMainService().getAllAttachmentsBySheet(where.toString());
		if (attachments.size() > 0)
		{
			TawCommonsAccessoriesForm tawCommonsAccessoriesForm;
			for (Iterator it = attachments.iterator(); it.hasNext(); list.add(tawCommonsAccessoriesForm))
			{
				TawCommonsAccessories attachment = (TawCommonsAccessories)it.next();
				tawCommonsAccessoriesForm = new TawCommonsAccessoriesForm();
				Map attachmentMap = SheetBeanUtils.describe(attachment);
				SheetBeanUtils.populateMap2Bean(tawCommonsAccessoriesForm, attachmentMap);
				tawCommonsAccessoriesForm.setAccessoriesUploadDate(attachment.getAccessoriesUploadTime());
				tawCommonsAccessoriesForm.setActiveTemplateId("OfficeMadeTask");
			}

		}
		request.removeAttribute("sheetAccessories");
		request.setAttribute("sheetAccessories", list);
	}
}
	
	/**
	 * 根据字典值返回字典名称
	 * 
	 * @param vaiues
	 * @return
	 * @date 2016-3-24下午04:50:01
	 * @author weichao
	 */
	private String getDulDictName(String values) throws Exception {
		StringBuffer names = new StringBuffer();
		if (null != values && !"".equals(values)) {
			ITawSystemDictTypeManager dictMgr = (ITawSystemDictTypeManager) ApplicationContextHolder.getInstance()
					.getBean("ItawSystemDictTypeManager");
			if (values.contains(",")) {
				String[] valuesl = values.split(",");
				for (int i = 0; i < valuesl.length; i++) {
					String tmpId = valuesl[i];
					names.append(dictMgr.id2Name(tmpId)).append(",");
				}
			} else {
				names.append(dictMgr.id2Name(values));
			}
		}
		return names.toString();
	}

	/**
	 * 转换字符串操作
	 * 
	 * @param ids
	 * @return
	 * @date 2016-3-29上午10:53:30
	 * @author weichao
	 */
	private String c2ids(String ids) {
		if (ids.contains(",")) {
			ids = " ('" + ids.replace(",", "','") + "')";
		} else {
			ids = " ('" + ids + "')";
		}
		return ids;
	}
}
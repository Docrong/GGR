package com.boco.eoms.sheet.resourceconfirm.webapp.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.interfaces.service.bo.IrmsResourceBo;
import com.boco.eoms.businessupport.order.model.OrderSheet;
import com.boco.eoms.businessupport.order.service.IOrderSheetManager;
import com.boco.eoms.businessupport.order.webapp.action.OrderSheetMethod;
import com.boco.eoms.businessupport.product.model.GprsSpecialLine;
import com.boco.eoms.businessupport.product.model.IPSpecialLine;
import com.boco.eoms.businessupport.product.model.LanguageSpecialLine;
import com.boco.eoms.businessupport.product.model.Smsspecialline;
import com.boco.eoms.businessupport.product.model.TrancePoint;
import com.boco.eoms.businessupport.product.model.TransferSpecialLine;
import com.boco.eoms.businessupport.product.service.TrancePointMgr;
import com.boco.eoms.businessupport.serviceprepare.mgr.ServicePrepareMgr;
import com.boco.eoms.businessupport.serviceprepare.model.ProcessTasks;
import com.boco.eoms.businessupport.serviceprepare.model.ProfessionalServiceDirectory;
import com.boco.eoms.businessupport.serviceprepare.model.ServiceConfiguration;
import com.boco.eoms.businessupport.util.Constants;
//import com.boco.eoms.businessupport.util.Constants;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dict.model.DictItemXML;
import com.boco.eoms.commons.system.dict.service.IDictService;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.sheet.resourceconfirm.model.ResourceConfirmLink;
import com.boco.eoms.sheet.resourceconfirm.model.ResourceConfirmMain;
import com.boco.eoms.sheet.resourceconfirm.model.ResourceConfirmTask;
import com.boco.eoms.sheet.resourceconfirm.service.IResourceConfirmLinkManager;
import com.boco.eoms.sheet.resourceconfirm.service.IResourceConfirmTaskManager;
import com.ibm.bpe.api.LocalBusinessFlowManager;
import com.ibm.bpe.api.LocalBusinessFlowManagerHome;

public class ResourceConfirmMethod extends BaseSheet {
	public String getPageColumnName() {

		return super.getPageColumnName()
				+ ",toMorePhaseId@java.lang.String,supplier1Performer@java.lang.String,supplier1PerformerLeader@java.lang.String,"
				+"supplier10Performer@java.lang.String,supplier10PerformerLeader@java.lang.String," 
				+"supplier10PerformerType@java.lang.String,supplier10CorrKey@java.lang.String,supplier11Performer@java.lang.String,supplier11PerformerLeader@java.lang.String," 
				+"supplier11PerformerType@java.lang.String,supplier11CorrKey@java.lang.String,supplier12Performer@java.lang.String,supplier12PerformerLeader@java.lang.String," 
				+"supplier12PerformerType@java.lang.String,supplier12CorrKey@java.lang.String,"
				
				+ "supplier1PerformerType@java.lang.String,supplier2Performer@java.lang.String,supplier2PerformerLeader@java.lang.String,supplier2PerformerType@java.lang.String,"
				+ "supplier1CorrKey@java.lang.String,supplier2CorrKey@java.lang.String,supplier3Performer@java.lang.String,supplier3PerformerLeader@java.lang.String,supplier3PerformerType@java.lang.String,"
				+ "supplier3CorrKey@java.lang.String";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.sheet.base.webapp.action.IBaseSheet#getInterfaceObjMap(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		HashMap hashMap = new HashMap();
		HashMap sheetMap = new HashMap();
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("mainId"));
		BaseMain main = (BaseMain) this.getMainService().getMainObject()
				.getClass().newInstance();
		if (!sheetKey.equals("")) {
			sheetKey = StaticMethod.nullObject2String(request
					.getParameter("sheetKey"));

		}
		if (!sheetKey.equals("")) {
			main = this.getMainService().getSingleMainPO(sheetKey);
		}
		sheetMap.put("main", main);
		sheetMap.put("link", getLinkService().getLinkObject().getClass()
				.newInstance());
		sheetMap.put("operate", getPageColumnName());
		hashMap.put("selfSheet", sheetMap);

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
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.dealFlowEngineMap(mapping, form, request, response);
		String cityA = "";
		String cityZ = "";
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("activeTemplateId"));
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"));
		String phaseId = StaticMethod.nullObject2String(request
				.getParameter("phaseId"));
		HashMap sheetMap = this.getFlowEngineMap();

		Map mainMap = (HashMap) sheetMap.get("main");
		Map linkMap = (HashMap) sheetMap.get("link");
		Map operate = (HashMap) sheetMap.get("operate");

		TrancePointMgr businessupportMgr = (TrancePointMgr) ApplicationContextHolder
				.getInstance().getBean("businessupportMgr");

		String id = StaticMethod.nullObject2String(request
				.getParameter("businesSupportId"));
		String specialtyType = StaticMethod.nullObject2String(mainMap
				.get("mainSpecifyType"));
		IOrderSheetManager iOrderSheetManager = (IOrderSheetManager) ApplicationContextHolder
				.getInstance().getBean("IOrderSheetManager");
		String orderid = StaticMethod.nullObject2String(request
				.getParameter("orderSheetId"));
		
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
		.getSession().getAttribute("sessionform");
		if(taskName.equals("AcceptTask") && operateType.equals("200")){
			String userName= sessionform.getUsername();
			String ontactMobile=sessionform.getContactMobile();
			mainMap.put("mainGroupContact", userName);
			mainMap.put("mainGroupTelephone", ontactMobile);
		}

		if (taskName.equals("UserTask") && operateType.equals("202")) {
			// 更新接入点表

			// String orderSheetId =
			// StaticMethod.nullObject2String(request.getParameter("orderSheetId"));
			String sevPointContact = StaticMethod.nullObject2String(request
					.getParameter("sevPointContact"));
			String sevPointContactPhone = StaticMethod
					.nullObject2String(request
							.getParameter("sevPointContactPhone"));
			String sevPointContactEmail = StaticMethod
					.nullObject2String(request
							.getParameter("sevPointContactEmail"));
			String sevPointContactAdd = StaticMethod.nullObject2String(request
					.getParameter("sevPointContactAdd"));
			String fiberEquipName = StaticMethod.nullObject2String(request
					.getParameter("fiberEquipName"));
			String fiberEquipCode = StaticMethod.nullObject2String(request
					.getParameter("fiberEquipCode"));
			String siteEquipCode = StaticMethod.nullObject2String(request
					.getParameter("siteEquipCode"));
			String siteName = StaticMethod.nullObject2String(request
					.getParameter("siteName"));
			String accessSiteIden = StaticMethod.nullObject2String(request
					.getParameter("accessSiteIden"));
			String remark = StaticMethod.nullObject2String(request
					.getParameter("remark"));

			TrancePoint s = businessupportMgr.getBusinessupport(id);

			s.setSevPointContact(sevPointContact);
			s.setSevPointContactAdd(sevPointContactAdd);
			s.setSevPointContactEmail(sevPointContactEmail);
			s.setSevPointContactPhone(sevPointContactPhone);
			s.setFiberEquipCode(fiberEquipCode);
			s.setFiberEquipName(fiberEquipName);
			s.setSiteEquipCode(siteEquipCode);
			s.setSiteName(siteName);
			s.setAccessSiteIden(accessSiteIden);
			s.setRemark(remark);

			businessupportMgr.saveBusinessupport(s);
		}
		/**
		if (taskName.equals("AccessTask") && operateType.equals("203")) {
			// 更新接入点表
			// String portEngineRoomName =
			// StaticMethod.nullObject2String(request.getParameter("portEngineRoomName"));
			// String portInterfaceType =
			// StaticMethod.nullObject2String(request.getParameter("portInterfaceType"));
			// String portDeviceName =
			// StaticMethod.nullObject2String(request.getParameter("portDeviceName"));
			// String portCustAidPerson =
			// StaticMethod.nullObject2String(request.getParameter("portCustAidPerson"));
			// String portACustAidPhone =
			// StaticMethod.nullObject2String(request.getParameter("portACustAidPhone"));
			// String portDetailAdd =
			// StaticMethod.nullObject2String(request.getParameter("portDetailAdd"));
			//			
			// TrancePoint s = businessupportMgr.getBusinessupport(id);
			//			
			// s.setPortACustAidPhone(id);
			// s.setPortEngineRoomName(portEngineRoomName);
			// s.setPortInterfaceType(portInterfaceType);
			// s.setPortDeviceName(portDeviceName);
			// s.setPortCustAidPerson(portCustAidPerson);
			// s.setPortACustAidPhone(portACustAidPhone);
			// s.setPortDetailAdd(portDetailAdd);
			//			
			// businessupportMgr.saveBusinessupport(s);
			//			
			// //更新 ordersheet表
			//			
			// if(specialtyType != null && !specialtyType.equals("") &&
			// (specialtyType.equals("101230101") ||
			// specialtyType.equals("101100101")||
			// specialtyType.equals("101220102"))){//GPRS专线
			// IGprsSpecialLineManager gslm =
			// (IGprsSpecialLineManager)ApplicationContextHolder.getInstance().getBean("IGprsSpecialLineManager");
			//				  
			// GprsSpecialLine objectName = new GprsSpecialLine();
			// List list =
			// iOrderSheetManager.getSpecialLinesByType(orderid,objectName);
			//	        	   
			// //add by lizhigang shanxi
			// for(int i = 0;list!=null&& i<list.size();i++){
			// GprsSpecialLine gprsSpecialLine = (GprsSpecialLine)list.get(i);
			// String portADetailAdd = gprsSpecialLine.getPortADetailAdd();
			// String portZDetailAdd = gprsSpecialLine.getPortZDetailAdd();
			// if(portADetailAdd.equals(portDetailAdd)){
			// gprsSpecialLine.setApointComputHouseName(portEngineRoomName);
			// gprsSpecialLine.setPortAInterfaceType(portInterfaceType);
			// gprsSpecialLine.setPortABDeviceName(portDeviceName);
			// gprsSpecialLine.setApointLocalPerson(portCustAidPerson);
			// gprsSpecialLine.setPortAContactPhone(portACustAidPhone);
			// gprsSpecialLine.setPortADetailAdd(portDetailAdd);
			//	        			   
			// gslm.saveObject(gprsSpecialLine);
			// }
			//	        		   
			// if(portZDetailAdd.equals(portDetailAdd)){
			// gprsSpecialLine.setZpointComputerHorseName(portEngineRoomName);
			// gprsSpecialLine.setPortZInterfaceType(portInterfaceType);
			// gprsSpecialLine.setPortZBDeviceName(portDeviceName);
			// gprsSpecialLine.setZpointLocalPerson(portCustAidPerson);
			// gprsSpecialLine.setPortZContactPhone(portACustAidPhone);
			// gprsSpecialLine.setPortZDetailAdd(portDetailAdd);
			// gslm.saveObject(gprsSpecialLine);
			// }
			//	        		   
			// }
			// }else if(specialtyType != null && !specialtyType.equals("") &&
			// (specialtyType.equals("101230102") ||
			// specialtyType.equals("101100102")||
			// specialtyType.equals("101220101"))){//IP专线
			// IIPSpecialLineManager ipm =
			// (IIPSpecialLineManager)ApplicationContextHolder.getInstance().getBean("IIPSpecialLineManager");
			// IPSpecialLine objectName = new IPSpecialLine();
			// List list =
			// iOrderSheetManager.getSpecialLinesByType(orderid,objectName);
			//	        	   
			// //add by lizhigang shanxi
			// for(int i = 0;list!=null&& i<list.size();i++){
			// IPSpecialLine pPSpecialLine = (IPSpecialLine)list.get(i);
			// String portADetailAdd = pPSpecialLine.getPortADetailAdd();
			// String portZDetailAdd = pPSpecialLine.getPortZDetailAdd();
			// if(portADetailAdd.equals(portDetailAdd) ||
			// portZDetailAdd.equals(portDetailAdd)){
			// pPSpecialLine.setApointComputHouseName(portEngineRoomName);
			// pPSpecialLine.setPortAInterfaceType(portInterfaceType);
			// pPSpecialLine.setPortABDeviceName(portDeviceName);
			// pPSpecialLine.setApointLocalPerson(portCustAidPerson);
			// pPSpecialLine.setPortAContactPhone(portACustAidPhone);
			// pPSpecialLine.setPortADetailAdd(portDetailAdd);
			// ipm.saveObject(pPSpecialLine);
			// }
			// if(portZDetailAdd.equals(portDetailAdd)){
			// pPSpecialLine.setZpointComputerHorseName(portEngineRoomName);
			// pPSpecialLine.setPortZInterfaceType(portInterfaceType);
			// pPSpecialLine.setPortZBDeviceName(portDeviceName);
			// pPSpecialLine.setZpointLocalPerson(portCustAidPerson);
			// pPSpecialLine.setPortZContactPhone(portACustAidPhone);
			// pPSpecialLine.setPortZDetailAdd(portDetailAdd);
			// ipm.saveObject(pPSpecialLine);
			// }
			// }
			// }else if(specialtyType != null && !specialtyType.equals("") &&
			// (specialtyType.equals("101230103") ||
			// specialtyType.equals("101100103")||
			// specialtyType.equals("101220103"))){//传输专线
			// ITransferSpecialLineManager tsm =
			// (ITransferSpecialLineManager)ApplicationContextHolder.getInstance().getBean("ITransferSpecialLineManager");
			// TransferSpecialLine objectName = new TransferSpecialLine();
			// List list =
			// iOrderSheetManager.getSpecialLinesByType(orderid,objectName);
			//	        	   
			// for(int i = 0;list!=null&& i<list.size();i++){
			// TransferSpecialLine transferSpecialLine =
			// (TransferSpecialLine)list.get(i);
			// String portADetailAdd = transferSpecialLine.getPortADetailAdd();
			// String portZDetailAdd = transferSpecialLine.getPortZDetailAdd();
			// if(portADetailAdd.equals(portDetailAdd)){
			// transferSpecialLine.setApointComputHouseName(portEngineRoomName);
			// transferSpecialLine.setPortAInterfaceType(portInterfaceType);
			// transferSpecialLine.setPortABDeviceName(portDeviceName);
			// transferSpecialLine.setApointLocalPerson(portCustAidPerson);
			// transferSpecialLine.setPortAContactPhone(portACustAidPhone);
			// transferSpecialLine.setPortADetailAdd(portDetailAdd);
			// tsm.saveObject(transferSpecialLine);
			//	        			  
			// }
			// if(portZDetailAdd.equals(portDetailAdd)){
			// transferSpecialLine.setZpointComputerHorseName(portEngineRoomName);
			// transferSpecialLine.setPortZInterfaceType(portInterfaceType);
			// transferSpecialLine.setPortZBDeviceName(portDeviceName);
			// transferSpecialLine.setZpointLocalPerson(portCustAidPerson);
			// transferSpecialLine.setPortZContactPhone(portACustAidPhone);
			// transferSpecialLine.setPortZDetailAdd(portDetailAdd);
			// tsm.saveObject(transferSpecialLine);
			//	        			  
			// }
			// }
			// }
		}
		*/
		if ((phaseId.equals("UserTask") && operateType.equals("2011"))
				|| (phaseId.equals("AccessTask") && operateType.equals("2012"))
				|| (phaseId.equals("CityTask") && operateType.equals("2013"))||operateType.equals("2016")||operateType.equals("2017")) {
			String sheetKey = StaticMethod.null2String(request
					.getParameter("sheetKey"));
			String parentTaskId = StaticMethod.null2String(request
					.getParameter("parentTaskId"));
			String parentLinkId = StaticMethod.null2String(request
					.getParameter("parentLinkId"));
			BaseMain sheetMain = this.getMainService()
					.getSingleMainPO(sheetKey);
			Map sheetMainMap = SheetBeanUtils.bean2Map(sheetMain);
			linkMap.put("aiid", parentTaskId);
			linkMap.put("preLinkId", parentLinkId);
			sheetMap.put("main", sheetMainMap);
			sheetMap.put("link", linkMap);
		}

		String[] dealperformers = (StaticMethod.nullObject2String(operate
				.get("dealPerformer"))).split(",");
		String[] toMorePhaseIds = (StaticMethod.nullObject2String(operate
				.get("toMorePhaseId"))).split(",");

		if (taskName.equals("reply") || taskName.equals("advice")) {
			Map link = (HashMap) sheetMap.get("link");
			link.put("id", "");
			sheetMap.put("link", link);
		}

		if (dealperformers.length > 1) {

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
			System.out.println("corrKey" + corrKey);
			operate.put("extendKey1", corrKey);

		}

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
				order.setOrderType("1040104");

				IOrderSheetManager mgr = (IOrderSheetManager) ApplicationContextHolder
						.getInstance().getBean("IOrderSheetManager");
				mgr.saveOrUpdate(order);

				mainMap.put("orderSheetId", orderId);

				// add by lizhigang 插入电路
				InterfaceUtilProperties properties = new InterfaceUtilProperties();
				Object objectName = "";
				Map tranceMap = new HashMap();
				if (specialtyType != null
						&& !specialtyType.equals("")
						&& (specialtyType.equals("101230101")
								|| specialtyType.equals("101100101") || specialtyType
								.equals("101220101"))) {// GPRS专线
					objectName = new GprsSpecialLine();
				} else if (specialtyType != null
						&& !specialtyType.equals("")
						&& (specialtyType.equals("101230102")
								|| specialtyType.equals("101100102") || specialtyType
								.equals("101220102"))) {// IP专线
					objectName = new IPSpecialLine();
				} else if (specialtyType != null
						&& !specialtyType.equals("")
						&& (specialtyType.equals("101230103")
								|| specialtyType.equals("101100103") || specialtyType
								.equals("101220103"))) {// 传输专线
					objectName = new TransferSpecialLine();
				}
				else if (specialtyType != null
						&& !specialtyType.equals("")
						&& (specialtyType.equals("101230104")
								|| specialtyType.equals("101100104") || specialtyType
								.equals("101220104"))) {// 
					objectName = new LanguageSpecialLine();
				}
				else if (specialtyType != null
						&& !specialtyType.equals("")
						&& (specialtyType.equals("101230105")
								|| specialtyType.equals("101230105") || specialtyType
								.equals("101230106"))) {// 
					objectName = new Smsspecialline();
				}
				String filePath = StaticMethod
						.getFilePathForUrl("classpath:config/resourceConfirm-crm.xml");
				List list = mgr.getSpecialLinesByType(orderId, objectName);
				for (int i = 0; list != null && i < list.size(); i++) {
					Map dataMap = SheetBeanUtils.bean2Map(list.get(i));
					cityA = StaticMethod.nullObject2String(dataMap.get("cityA"));
					cityZ = StaticMethod.nullObject2String(dataMap.get("cityZ"));
					String portADetailAdd = StaticMethod
							.nullObject2String(dataMap.get("portADetailAdd"));
					String portZDetailAdd = StaticMethod
							.nullObject2String(dataMap.get("portZDetailAdd"));

					if (!tranceMap.containsKey(portADetailAdd)) {
						Map tempMap = new HashMap();
						tempMap.putAll(dataMap);
						tempMap = properties.getMapFromXml(tempMap, filePath,
								"portA");
						TrancePoint p = new TrancePoint();
						SheetBeanUtils.populate(p, tempMap);
						p.setOrderSheetId(orderId);
						tranceMap.put(portADetailAdd, p);
					}
					if (specialtyType != null
							&& !specialtyType.equals("")
							&& (specialtyType.equals("101230103")
									|| specialtyType.equals("101100103") || specialtyType 
									.equals("101220103"))) {// 传输专线
						if (!tranceMap.containsKey(portZDetailAdd)) {
							Map tempMap = new HashMap();
							tempMap.putAll(dataMap);
							tempMap = properties.getMapFromXml(tempMap,filePath, "portZ");
							TrancePoint p = new TrancePoint();
							SheetBeanUtils.populate(p, tempMap);
							p.setOrderSheetId(orderId);
							tranceMap.put(portZDetailAdd, p);
						}
					}

				}
				Set set = tranceMap.keySet();
				Iterator it = set.iterator();
				while (it.hasNext()) {
					String key = StaticMethod.nullObject2String(it.next());
					TrancePoint p = (TrancePoint) tranceMap.get(key);
					p.setId(null);
					businessupportMgr.saveBusinessupport(p);
				}
			}
		}
		if (operateType.equals("205")) {
			String accessories = StaticMethod.nullObject2String(request.getParameter("accessories"));
			String orderSheetId = StaticMethod.nullObject2String(mainMap.get("orderSheetId"));
			if (orderSheetId != null && !orderSheetId.equals("")) {

				OrderSheet orderSheet = iOrderSheetManager.getOrderSheet(orderSheetId);
				orderSheet.setAccessories(accessories);
				iOrderSheetManager.saveOrderSheet(orderSheet);

			}
		}

		if (toMorePhaseIds.length > 0) {

			String tmp = "";
			for (int i = 0; i < toMorePhaseIds.length; i++) {
				tmp = UUIDHexGenerator.getInstance().getID();
				if (toMorePhaseIds[i].equals("CddSupplyTask")) {
					operate.put("supplier1CorrKey", tmp);
				} else if (toMorePhaseIds[i].equals("AllotPortTask")) {
					operate.put("supplier2CorrKey", tmp);
				}
				BocoLog.info(this, "=======tmp=CorrKey==" + tmp);
			}
		}
		if (mainMap != null
				&& StaticMethod.nullObject2String(
						mainMap.get("mainSendSheetModel"))
						.equalsIgnoreCase("1")) {
			String sendImmediately = StaticMethod.nullObject2String(XmlManage
					.getFile("/config/resourceConfirm-crm.xml").getProperty(
							"base.SendImmediately"));
			if (!sendImmediately.equalsIgnoreCase("true")) {

				if (taskName.equals("AcceptTask") && operateType.equals("4")) {// 驳回
					operate.put("interfaceType", "withdrawWorkSheet");
					operate.put("methodType", "withdrawWorkSheet");
				} else if (operateType.equals("9")) {// 阶段回复
					operate.put("interfaceType", "notifyWorkSheet");
					operate.put("methodType", "notifyWorkSheet");
				} else if (taskName.equals("AcceptTask")&& operateType.equals("61")) {// 受理
					operate.put("interfaceType", "confirmWorkSheet");
					operate.put("methodType", "confirmWorkSheet");
				} else if (operateType.equals("2091")|| operateType.equals("2015")) {
					operate.put("interfaceType", "replyWorkSheet");
					operate.put("methodType", "replyWorkSheet");
				}
			}
		}

		if (operateType.equals("200") && !specialtyType.equals(Constants._TRANSFER_LINE)) { // 审核通过，调用资管的初始化接口
			String enable = StaticMethod
					.nullObject2String(XmlManage
							.getFile(
									"/com/boco/eoms/businessupport/config/resourceInterface_util.xml")
							.getProperty("base.resourceConfirmEnable"));
			if (enable.equalsIgnoreCase("true")) {

				Map orderMap = new HashMap();
				String mainSpecifyType = StaticMethod.nullObject2String(mainMap
						.get("mainSpecifyType"));
				String orderSheetId = StaticMethod.nullObject2String(mainMap
						.get("orderSheetId"));
				if (orderSheetId != null && !orderSheetId.equals("")) {

					OrderSheet orderSheet = iOrderSheetManager
							.getOrderSheet(orderSheetId);
					orderMap.putAll(SheetBeanUtils.bean2Map(orderSheet));
				}

				List list = OrderSheetMethod.getSpecialLineList(orderSheetId,
						mainSpecifyType);

				orderMap.putAll(mainMap);


				try {
					IrmsResourceBo.createProService(orderMap, list); // 资源初始化
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (specialtyType != null
				&& !specialtyType.equals("")
				&& (specialtyType.equals("101230103")
						|| specialtyType.equals("101100103") || specialtyType
						.equals("101220103")) && !"".equals(cityA) && cityA.equals(cityZ)) {
			String subroleid = "";
			String subrolename = "";
			ITawSystemSubRoleManager itawsystemsubrolemanager = (ITawSystemSubRoleManager)ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
			List list = itawsystemsubrolemanager.getTawSystemSubRoles(928);
			for (int i=0;i<list.size();i++) {
				TawSystemSubRole tawsystemsubrole = (TawSystemSubRole)list.get(i);
				subrolename = tawsystemsubrole.getSubRoleName();
				if (subrolename.indexOf(cityA)!=-1) {
					subroleid = tawsystemsubrole.getId();
				}
			}
			operate.put("phaseId", "ExplorateTask");
			operate.put("dealPerformer", StaticMethod.nullObject2String(subroleid));
			operate.put("dealPerformerLeader", StaticMethod.nullObject2String(subroleid));
			operate.put("dealPerformerType", "subrole");
			this.createTaskAndLink(mainMap);
		}
		sheetMap.put("link", linkMap);
		sheetMap.put("main", mainMap);
		sheetMap.put("operate", operate);
		this.setFlowEngineMap(sheetMap);

	}
	
	public void createTaskAndLink(Map main) {
		IResourceConfirmLinkManager linkservice = (IResourceConfirmLinkManager)ApplicationContextHolder.getInstance().getBean("iResourceConfirmLinkManager");
		IResourceConfirmTaskManager taskservice = (IResourceConfirmTaskManager)ApplicationContextHolder.getInstance().getBean("iResourceConfirmTaskManager");
		Calendar calendar = Calendar.getInstance();
		//质检确认受理的link记录
		ResourceConfirmLink linkbean = new ResourceConfirmLink();
		try {
			linkbean.setId(UUIDHexGenerator.getInstance().getID());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		linkbean.setMainId(StaticMethod.nullObject2String(main.get("id")));
		calendar.add(Calendar.SECOND, -5);
		linkbean.setOperateTime(calendar.getTime());
		linkbean.setOperateUserId(StaticMethod.nullObject2String(main.get("sendUserId")));
		linkbean.setToOrgType(new Integer(0));
		linkbean.setAcceptFlag(new Integer(1));
		linkbean.setCompleteFlag(new Integer(1));
		linkbean.setOperateType(new Integer(61));
		linkbean.setOperateDay(calendar.get(5));
		linkbean.setOperateMonth(calendar.get(2) + 1);
		linkbean.setOperateYear(calendar.get(1));
		linkbean.setOperateDeptId(StaticMethod.nullObject2String(main.get("sendDeptId")));
		linkbean.setOperateRoleId(StaticMethod.nullObject2String(main.get("sendRoleId")));
		linkbean.setOperaterContact(StaticMethod.nullObject2String(main.get("sendContact")));
		linkbean.setToOrgRoleId(StaticMethod.nullObject2String(main.get("sendUserId")));
		linkbean.setActiveTemplateId("AcceptTask");
		try {
			linkservice.addLink(linkbean);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
//		质检确认受理的link记录
		ResourceConfirmLink commitbean = new ResourceConfirmLink();
		try {
			commitbean.setId(UUIDHexGenerator.getInstance().getID());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		commitbean.setMainId(StaticMethod.nullObject2String(main.get("id")));
		calendar.add(Calendar.SECOND, 2);
		commitbean.setOperateTime(calendar.getTime());
		commitbean.setOperateType(new Integer(200));
		commitbean.setOperateDay(calendar.get(5));
		commitbean.setOperateMonth(calendar.get(2) + 1);
		commitbean.setOperateYear(calendar.get(1));
		commitbean.setOperateUserId(StaticMethod.nullObject2String(main.get("sendUserId")));
		commitbean.setOperateDeptId(StaticMethod.nullObject2String(main.get("sendDeptId")));
		commitbean.setOperateRoleId(StaticMethod.nullObject2String(main.get("sendRoleId")));
		commitbean.setOperaterContact(StaticMethod.nullObject2String(main.get("sendContact")));
		commitbean.setToOrgRoleId(StaticMethod.nullObject2String(main.get("sendUserId")));
		commitbean.setToOrgType(new Integer(0));
		commitbean.setAcceptFlag(new Integer(1));
		commitbean.setCompleteFlag(new Integer(1));
		commitbean.setActiveTemplateId("AcceptTask");
		commitbean.setLinkUrgency("101010202");
		commitbean.setLinkReqCompleteTime(calendar.getTime());
		commitbean.setLinkSendSheetDesc("请处理");
		try {
			linkservice.addLink(commitbean);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//产生质检的task记录
		ResourceConfirmTask task = new ResourceConfirmTask();
		try {
			task.setId(UUIDHexGenerator.getInstance().getID());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		task.setTaskName("AcceptTask");
		task.setTaskDisplayName("资源确认受理");
		task.setFlowName("ResourceConfirmProcess");
		task.setSendTime(new Date());
		task.setSheetKey(StaticMethod.nullObject2String(main.get("id")));
		task.setTaskStatus("5");
		task.setSheetId(StaticMethod.nullObject2String(main.get("sheetId")));
		task.setTitle(StaticMethod.nullObject2String(main.get("title")));
		task.setOperateType("subrole");
		task.setCreateTime(new Date());
		task.setCreateYear(calendar.get(1));
		task.setCreateMonth(calendar.get(2) + 1);
		task.setCreateDay(calendar.get(5));
		task.setOperateRoleId(StaticMethod.nullObject2String(main.get("sendRoleId")));
		task.setTaskOwner(StaticMethod.nullObject2String(main.get("sendUserId")));
		task.setIfWaitForSubTask("false");
		task.setParentTaskId("_AI:" + (new Date()).getTime());
		task.setPreLinkId(linkbean.getId());
		try
		{
			taskservice.addTask(task);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
	}

	public Map getProcessOjbectAttribute() {
		Map attributeMap = new HashMap();
		attributeMap.put("dealPerformer", "dealPerformer");
		attributeMap.put("copyPerformer", "copyPerformer");
		attributeMap.put("auditPerformer", "auditPerformer");
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
		linkAttachmentAttributes.add("linkProgramTopology");
		objectMap.put("mainObject", mainAttachmentAttributes);
		objectMap.put("linkObject", linkAttachmentAttributes);
		return objectMap;
	}

	public void showInputDealPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.showInputDealPage(mapping, form, request, response);
		TawSystemSessionForm sessionform = (TawSystemSessionForm) request
				.getSession().getAttribute("sessionform");
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");
		String operateType = StaticMethod.nullObject2String(request
				.getParameter("operateType"), "");
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("activeTemplateId"));
		if (taskName.equals("")) {
			taskName = StaticMethod.nullObject2String(request
					.getParameter("taskName"));
		}
		if (taskName.equals("RejectTask") || taskName.equals("AcceptTask")
				|| taskName.equals("ExplorateTask")
				|| taskName.equals("MakeTask") || taskName.equals("HandleTask")
				|| taskName.equals("HoldTask")) {
			super.setParentTaskOperateWhenRejct(request);
		}
		
		ResourceConfirmMain resourceconfirmMain = (ResourceConfirmMain) this
		.getMainService().getSingleMainPO(sheetKey);
		
		// add by lizhigang
		TrancePointMgr businessupportMgr = (TrancePointMgr) ApplicationContextHolder
				.getInstance().getBean("businessupportMgr");

		if (taskName.equals("ExplorateTask") && operateType.equals("201")) {
			ServicePrepareMgr servicePrepareMgr = (ServicePrepareMgr) ApplicationContextHolder
					.getInstance().getBean("IServicePrepareMgr");
			IDictService dictService = (IDictService) ApplicationContextHolder
					.getInstance().getBean("DictService");
//			List commonList = dictService.getDictItems("dict-businesssupport-professionalservicedirectory#common");
			String flowId = StaticMethod.null2String(request
					.getParameter("flowId"));
			List serviceConfigurationList = servicePrepareMgr
					.getAllListByCondition(flowId, taskName,resourceconfirmMain.getMainSpecifyType());
			List serviceList = new ArrayList();
			if (serviceConfigurationList != null) {
				for (int i = 0; i < serviceConfigurationList.size(); i++) {
					Map serviceConfigurationMap = new HashMap();
					Object[] tmpObjArr = (Object[]) serviceConfigurationList.get(i);
					ServiceConfiguration serviceConfiguration = (ServiceConfiguration) tmpObjArr[0];
					ProfessionalServiceDirectory professionalServiceDirectory = (ProfessionalServiceDirectory) tmpObjArr[1];
					serviceConfigurationMap.put("id", serviceConfiguration.getId());
					serviceConfigurationMap.put("specialtyId",serviceConfiguration.getSpecialtyId());
					if (professionalServiceDirectory != null && professionalServiceDirectory.getRemark()!=null) {
						String subTaskName = professionalServiceDirectory.getRemark();
							serviceConfigurationMap.put("taskName", subTaskName);
							serviceConfigurationMap.put("serviceCnName",professionalServiceDirectory.getName());
							serviceList.add(serviceConfigurationMap);

					}
					
				}
			}

			request.setAttribute("serviceList", serviceList);	//服务列表
			// add by lizhigang start

			

			String orderSheetId = resourceconfirmMain.getOrderSheetId();
			List BusinesSupportList = businessupportMgr
					.getBusinessupportBySheetId(orderSheetId);

			request.setAttribute("BusinesSupportList", BusinesSupportList);

			// add by lizhigang end
			ITask task = (ITask) request.getAttribute("task");
			if (task != null) {
				String taskId = task.getId();
				List processTasksList = servicePrepareMgr
						.getProcessTasksByParentTaskId(taskId);
				request.setAttribute("processTasksList", processTasksList);	//任务列表
			}
		}
	}

	public String getProcessTemplateName() {
		// TODO Auto-generated method stub
		return "ResourceConfirmProcess";
	}

	public String getSheetAttachCode() {
		// TODO Auto-generated method stub
		return "resourceconfirm";
	}

	public Map initMap(Map map, List attach, String type) throws Exception {
		return null;
	}

	public void newPerformDeal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		super.newPerformDeal(mapping, form, request, response);
		/**
		 * 在任务环节结束的时候更新流程任务表，将里面的任务记录状态更新到8状态
		 */
		String taskName = StaticMethod.null2String(request
				.getParameter("taskName"));
		String operateType = StaticMethod.null2String(request
				.getParameter("operateType"));
		if ((taskName.equals("UserTask") && operateType.equals("202")) // 此处耦合性太高，需要重构
				|| (taskName.equals("AccessTask") && operateType.equals("203"))
				|| (taskName.equals("CityTask") && operateType.equals("204"))
				|| operateType.equals("207") || operateType.equals("208")
				|| (taskName.equals("LauguageTask") && operateType.equals("210"))
				|| (taskName.equals("CaiXinTask") && operateType.equals("220"))) {
			Map sheetMap = getFlowEngineMap();
			Map linkMap = (HashMap) sheetMap.get("link");
			String processTasks_ParentLinkId = StaticMethod.null2String(request
					.getParameter("processTasks_ParentLinkId"));
			ServicePrepareMgr servicePrepareMgr = (ServicePrepareMgr) ApplicationContextHolder
					.getInstance().getBean("IServicePrepareMgr");
			ProcessTasks processTasks = (ProcessTasks) servicePrepareMgr
					.getProcessTasksByParentLinkId(processTasks_ParentLinkId);
			if (processTasks != null) {
				processTasks.setStatus("8");
				processTasks.setEndtime((Date) linkMap.get("operateTime"));
				servicePrepareMgr.addObject(processTasks);
			}
		}

	}

	/*
	 * 获取与工单相关的定单信息，呈现在工单的detail页面上 modify by shichuangke
	 */
	public void showDetailPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		super.showDetailPage(mapping, form, request, response);
		String preLinkId = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"), "");
		if (!preLinkId.equals("")) {
			request.setAttribute("preLink", this.getLinkService()
					.getSingleLinkPO(preLinkId));
		}
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");
		if (!sheetKey.equals("")) {
			// 根据sheetKey得到工单main对象，取出ordersheetid，查找定单信息
			ResourceConfirmMain resourceconfirmMain = (ResourceConfirmMain) this
					.getMainService().getSingleMainPO(sheetKey);
			String orderSheetId = resourceconfirmMain.getOrderSheetId();
			if (orderSheetId != null && !orderSheetId.equals("")) {
				IOrderSheetManager iOrderSheetManager = (IOrderSheetManager) ApplicationContextHolder
						.getInstance().getBean("IOrderSheetManager");
				OrderSheet orderSheet = iOrderSheetManager
						.getOrderSheet(orderSheetId);
				request.setAttribute("orderSheet", orderSheet);
			}
		}
		// add by lizhigang
		TrancePointMgr businessupportMgr = (TrancePointMgr) ApplicationContextHolder
				.getInstance().getBean("businessupportMgr");
		String taskName = StaticMethod.nullObject2String(request
				.getParameter("taskName"));
		TrancePoint support = null;

		request.getSession().removeAttribute("support");
		if (taskName.equals("UserTask") || taskName.equals("AccessTask")) {
			support = businessupportMgr
					.getBusinessupport(((ResourceConfirmLink) (this
							.getLinkService().getSingleLinkPO(preLinkId)))
							.getLinkDevicePort());

			request.getSession().setAttribute("support", support);
		}
	}

	/*
	 * 获取与工单相关的定单信息，呈现在工单的detail页面上 modify by shichuangke
	 */
	public void showMainDetailPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		super.showMainDetailPage(mapping, form, request, response);
		String preLinkId = StaticMethod.nullObject2String(request
				.getParameter("preLinkId"), "");
		if (!preLinkId.equals("")) {
			request.setAttribute("preLink", this.getLinkService()
					.getSingleLinkPO(preLinkId));
		}
		String sheetKey = StaticMethod.nullObject2String(request
				.getParameter("sheetKey"), "");
		if (!sheetKey.equals("")) {
			// 根据sheetKey得到工单main对象，取出ordersheetid，查找定单信息
			ResourceConfirmMain resourceconfirmMain = (ResourceConfirmMain) this
					.getMainService().getSingleMainPO(sheetKey);
			String orderSheetId = resourceconfirmMain.getOrderSheetId();
			if (orderSheetId != null && !orderSheetId.equals("")) {
				IOrderSheetManager iOrderSheetManager = (IOrderSheetManager) ApplicationContextHolder
						.getInstance().getBean("IOrderSheetManager");
				OrderSheet orderSheet = iOrderSheetManager
						.getOrderSheet(orderSheetId);
				request.setAttribute("orderSheet", orderSheet);
				request.setAttribute("orderSheetId", orderSheetId);
			}
		}
	}

}

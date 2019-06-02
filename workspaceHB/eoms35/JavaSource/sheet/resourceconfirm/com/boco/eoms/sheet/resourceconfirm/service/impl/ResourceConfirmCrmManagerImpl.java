package com.boco.eoms.sheet.resourceconfirm.service.impl;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.order.model.OrderSheet;
import com.boco.eoms.businessupport.order.service.IOrderSheetManager;
import com.boco.eoms.businessupport.product.model.GprsSpecialLine;
import com.boco.eoms.businessupport.product.model.IPSpecialLine;
import com.boco.eoms.businessupport.product.model.LanguageSpecialLine;
import com.boco.eoms.businessupport.product.model.Smsspecialline;
import com.boco.eoms.businessupport.product.model.TransferSpecialLine;
import com.boco.eoms.businessupport.product.service.IGprsSpecialLineManager;
import com.boco.eoms.businessupport.product.service.IIPSpecialLineManager;
import com.boco.eoms.businessupport.product.service.ITransferSpecialLineManager;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.interfaceBase.service.IInterfaceServiceManage;
import com.boco.eoms.sheet.interfaceBase.service.impl.InterfaceServiceManageAbstract;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.resourceconfirm.model.ResourceConfirmLink;
import com.boco.eoms.sheet.resourceconfirm.model.ResourceConfirmTask;
import com.boco.eoms.sheet.resourceconfirm.service.IResourceConfirmLinkManager;
import com.boco.eoms.sheet.resourceconfirm.service.IResourceConfirmTaskManager;

public class ResourceConfirmCrmManagerImpl extends InterfaceServiceManageAbstract implements IInterfaceServiceManage {

	public String getLinkBeanId() {
		return "iResourceConfirmLinkManager";
	}

	public String getMainBeanId() {
		return "iResourceConfirmMainManager";
	}

	public String getOperateName() {
		return "newWorksheet";
	}

	public String getProcessTemplateName() {
		// TODO Auto-generated method stub
		return "ResourceConfirmProcess";
	}

	public String getSendUser(Map map) {
		String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/resourceConfirm-crm.xml").getProperty("base.InterfaceUser"));
		return userId;
	}

	public String getTaskBeanId() {
		// TODO Auto-generated method stub
		return "iResourceConfirmTaskManager";
	}
	public HashMap addPara(HashMap hashMap){
		try{
			System.out.println("star corrKey...");
			hashMap.put("corrKey",UUIDHexGenerator.getInstance().getID());
			System.out.println("corrKey="+hashMap.get("corrKey").toString());
			HashMap main = (HashMap)hashMap.get("main");
			HashMap operate = (HashMap)hashMap.get("operate");
			String mainSpecifyType = (String)main.get("mainSpecifyType");
			String orderSheetId = (String)main.get("orderSheetId");
			Object objectName = "";
			String cityA = "";
			String cityZ = "";
			IOrderSheetManager mgr = (IOrderSheetManager) ApplicationContextHolder
			.getInstance().getBean("IOrderSheetManager");
			if (mainSpecifyType != null
					&& !mainSpecifyType.equals("")
					&& (mainSpecifyType.equals("101230101")
							|| mainSpecifyType.equals("101100101") || mainSpecifyType
							.equals("101220101"))) {// GPRS专线
				objectName = new GprsSpecialLine();
			} else if (mainSpecifyType != null
					&& !mainSpecifyType.equals("")
					&& (mainSpecifyType.equals("101230102")
							|| mainSpecifyType.equals("101100102") || mainSpecifyType
							.equals("101220102"))) {// IP专线
				objectName = new IPSpecialLine();
			} else if (mainSpecifyType != null
					&& !mainSpecifyType.equals("")
					&& (mainSpecifyType.equals("101230103")
							|| mainSpecifyType.equals("101100103") || mainSpecifyType
							.equals("101220103"))) {// 传输专线
				objectName = new TransferSpecialLine();
			}
			else if (mainSpecifyType != null
					&& !mainSpecifyType.equals("")
					&& (mainSpecifyType.equals("101230104")
							|| mainSpecifyType.equals("101100104") || mainSpecifyType
							.equals("101220104"))) {// 
				objectName = new LanguageSpecialLine();
			}
			else if (mainSpecifyType != null
					&& !mainSpecifyType.equals("")
					&& (mainSpecifyType.equals("101230105")
							|| mainSpecifyType.equals("101230105") || mainSpecifyType
							.equals("101230106"))) {// 
				objectName = new Smsspecialline();
			}
			List list = mgr.getSpecialLinesByType(orderSheetId, objectName);
			for (int i = 0; list != null && i < list.size(); i++) {
				Map dataMap = SheetBeanUtils.bean2Map(list.get(i));
				cityA = StaticMethod.nullObject2String(dataMap.get("cityA"));
				cityZ = StaticMethod.nullObject2String(dataMap.get("cityZ"));
			}
			if (mainSpecifyType != null
					&& !mainSpecifyType.equals("")
					&& (mainSpecifyType.equals("101230103")
							|| mainSpecifyType.equals("101100103") || mainSpecifyType
							.equals("101220103")) && !"".equals(cityA) && cityA.equals(cityZ)) {
				String subroleid = "";
				String subrolename = "";
				ITawSystemSubRoleManager itawsystemsubrolemanager = (ITawSystemSubRoleManager)ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
				List listrole = itawsystemsubrolemanager.getTawSystemSubRoles(928);
				for (int i=0;i<listrole.size();i++) {
					TawSystemSubRole tawsystemsubrole = (TawSystemSubRole)listrole.get(i);
					subrolename = tawsystemsubrole.getSubRoleName();
					if (subrolename.indexOf(cityA)!=-1) {
						subroleid = tawsystemsubrole.getId();
					}
				}
				if (null !=subroleid&&!"".equals(subroleid)) {
					operate.put("phaseId", "ExplorateTask");
					operate.put("dealPerformer", StaticMethod.nullObject2String(subroleid));
					operate.put("dealPerformerLeader", StaticMethod.nullObject2String(subroleid));
					operate.put("dealPerformerType", "subrole");
					this.createTaskAndLink(main);
				}
			}
			hashMap.put("main", main);
			hashMap.put("operate", operate);
		}catch(Exception err){
			err.printStackTrace();
		}
		return hashMap;
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
		calendar.add(Calendar.SECOND, 5);
		linkbean.setOperateTime(calendar.getTime());
		linkbean.setOperateUserId("fangmin");
		linkbean.setToOrgType(new Integer(0));
		linkbean.setAcceptFlag(new Integer(1));
		linkbean.setCompleteFlag(new Integer(1));
		linkbean.setOperateType(new Integer(61));
		linkbean.setOperateDay(calendar.get(5));
		linkbean.setOperateMonth(calendar.get(2) + 1);
		linkbean.setOperateYear(calendar.get(1));
		linkbean.setOperateDeptId("12201");
		linkbean.setOperateRoleId("8a9982f22ccfa1c9012cf20e39b837c3");
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
		calendar.add(Calendar.SECOND, 10);
		commitbean.setOperateTime(calendar.getTime());
		commitbean.setOperateType(new Integer(200));
		commitbean.setOperateDay(calendar.get(5));
		commitbean.setOperateMonth(calendar.get(2) + 1);
		commitbean.setOperateYear(calendar.get(1));
		commitbean.setOperateUserId("fangmin");
		commitbean.setOperateDeptId("12201");
		commitbean.setOperateRoleId("8a9982f22ccfa1c9012cf20e39b837c3");
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
		task.setOperateRoleId("8a9982f22ccfa1c9012cf20e39b837c3");
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
	
	public Map initMap(Map map, List attach, String type) throws Exception {
		try{
			System.out.println("map.serviceType:"+map.get("serviceType")+"----"+map.get("zcostomContact")+map.get("acostomContact")+map.get("isApplyPre"));
			map = this.loadDefaultMap(map, "config/resourceConfirm-crm.xml", type);
			map.put("corrKey", UUIDHexGenerator.getInstance().getID());
			
			if(type.equals(InterfaceConstants.WORKSHEET_NEW)||type.equals(InterfaceConstants.WORKSHEET_RENEW)){
//				String serviceType = InterfaceUtilProperties.getInstance().getDictIdByInterfaceCode("serviceType",StaticMethod.nullObject2String(map.get("serviceType")).toString());
//				System.out.println("业务类型:"+serviceType);
//				map.put("btype1", serviceType);//业务类型
				
				//map.put("mainProductInstanceCode",StaticMethod.nullObject2String(map.get("title")).split("-")[2]);
				
				String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/resourceConfirm-crm.xml").getProperty("base.SendRoleId"));
				map.put("sendRoleId", sendRoleId);
				
				String bigRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/resourceConfirm-crm.xml").getProperty("base.AcceptGroupId"));
				String toDeptId = StaticMethod.nullObject2String(map.get("toDeptId"));
			    
				WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
				String subRoleId = "";
				TawSystemSubRole subRole = sm.getMatchRoles("ResourceConfirmProcess", toDeptId, bigRole, map);
				if(subRole==null||subRole.getId()==null||subRole.getId().length()==0){
					System.out.println("未找到匹配角色，使用默认角色");
					subRoleId = XmlManage.getFile("/config/resourceConfirm-crm.xml").getProperty("base.AcceptRoleId");
				}else{
					subRoleId = subRole.getId();
				}
				map = sm.setAcceptRole(subRoleId, map);			
				
				
				OrderSheet order = new OrderSheet();	
				SheetBeanUtils.populateMap2Bean(order, map);
				String orderSheetId = StaticMethod.nullObject2String(map.get("orderSheetId")); 
				if(orderSheetId.equals(""))
					orderSheetId = UUIDHexGenerator.getInstance().getID();
				order.setId(orderSheetId);
				IOrderSheetManager mgr = (IOrderSheetManager)ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");		
				mgr.saveOrUpdate(order);
//				map.put("orderSheetId", orderSheetId);
				
				String attachId = this.getAttach(attach);
				System.out.println("#####################resourceconfirm#######attachId="+attachId);
				if(attachId!=null&&attachId.length()>0){
						map.put("resourceconfirm", attachId);
						map.put("sheetAccessories", attachId);
				}
				
//	           String specialType = order.getOrderBuisnessType();
//	            //当专业类型为GPRS专线时保存到GPRS存量表中,当专业类型为IP专线时保存到IP存量表中,同时将定单id保存到存量表中
	           /*if(specialType.equals("101230101")){//GPRS专线
	            	GprsSpecialLine gprs = new GprsSpecialLine();
	            	SheetBeanUtils.populateMap2Bean(gprs, map);
	            	gprs.setOrderSheet_Id(orderSheetId);
	            	IGprsSpecialLineManager gprsmgr = (IGprsSpecialLineManager)ApplicationContextHolder.getInstance().getBean("IGprsSpecialLineManager");
	            	gprsmgr.saveOrUpdate(gprs);
	            }else if(specialType.equals("101230102")){//IP专线
	            	IPSpecialLine ip = new IPSpecialLine();
	            	SheetBeanUtils.populateMap2Bean(ip, map);
	            	ip.setOrderSheet_Id(orderSheetId);
	            	IIPSpecialLineManager ipmgr = (IIPSpecialLineManager)ApplicationContextHolder.getInstance().getBean("IIPSpecialLineManager");
	            	ipmgr.saveOrUpdate(ip);
	            }else if(specialType.equals("101230103")){//传输专线
	            	TransferSpecialLine transfer = new TransferSpecialLine();
	            	SheetBeanUtils.populateMap2Bean(transfer, map);
	            	transfer.setOrderSheet_Id(orderSheetId);
	            	ITransferSpecialLineManager transfermgr = (ITransferSpecialLineManager)ApplicationContextHolder.getInstance().getBean("ITransferSpecialLineManager");
	            	transfermgr.saveOrUpdate(transfer);
	            }*/

				String sheetKey = UUIDHexGenerator.getInstance().getID();
				map.put("id", sheetKey);
				//将定单的id保存到工单main表中
				// map.put("orderSheetId", order.getId());
				
			}
			if(type.equals(InterfaceConstants.WORKSHEET_RENEW)||type.equals(InterfaceConstants.WORKSHEET_UNTREAD)){
				String sheetId = StaticMethod.nullObject2String(map.get("serialNo"));
				if(sheetId==null || sheetId.equals(""))
					throw new Exception("sheetId为空");
				
				BaseMain main = null;
				IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(this.getMainBeanId());
				ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean(this.getTaskBeanId());
				
				List list = iMainService.getMainListByParentSheetId(sheetId);
				if(list!=null&&list.size()>0){
					boolean b = false;
					main = (BaseMain)list.get(0);
					List taskList = iTaskService.getCurrentUndoTask(main.getId());
					if(taskList!=null){
						for(int i=0;i<taskList.size();i++){
							ITask task = (ITask) taskList.get(i);
							if(task.getTaskOwner().equals(main.getSendRoleId())||task.getTaskOwner().equals(main.getSendUserId())||task.getTaskOwner().equals(main.getSendDeptId())){
									b = true;
									break;
							}
						}
					}
					if(!b){
						if(type.equals(InterfaceConstants.WORKSHEET_RENEW))
							throw new Exception("工单未被驳回，不能重派");
						else if(type.equals(InterfaceConstants.WORKSHEET_UNTREAD))
							throw new Exception("工单未回复，不能退回");
					}
						
				}else
					throw new Exception("没找到sheetNo＝"+sheetId+"对应的工单");
				
				System.out.println("attach size=="+attach.size());
				String attachId = this.getAttach(attach);
				System.out.println("#####################resourceconfirm#######attachId="+attachId);
				if(attachId!=null&&attachId.length()>0){
						//map.put("resourceconfirm", attachId);
						map.put("sheetAccessories", attachId);
				}
			}
//			if(type.equals(InterfaceConstants.WORKSHEET_HOLD)){	//归档
//				String endResult = StaticMethod.nullObject2String(map.get("endResult"));
//				
//					BaseMain main = null;
//					IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(this.getMainBeanId());
//					
//					String sheetId = StaticMethod.nullObject2String(map.get("serialNo"));
//					List list = iMainService.getMainListByParentSheetId(sheetId);
//					if(list.size()>0){
//						main = (BaseMain)list.get(0);
//						
//						IResourceAffirmSpecialInfoManager mgr = (IResourceAffirmSpecialInfoManager)ApplicationContextHolder.getInstance().getBean("iResourceAffirmSpecialInfoManager");
//						List productList = mgr.getBDSInfoList(main.getId());
//					
//						HashMap orderMap = SheetBeanUtils.bean2Map(main);
//						if(!endResult.equals("否")){	//释放资源
//							IrmsResourceBo.undoTaskRes(orderMap, productList);
//						}else{//实占资源
//							IrmsResourceBo.occupyServiceRes(orderMap, productList);
//						}
//							
//					}else{
//						throw new Exception("没找到sheetNo＝"+sheetId+"对应的工单");
//					}
//				
//			}
			return map;
		}catch(Exception err){
			err.printStackTrace();
			throw err;
		}
	}
	public String getSheetAttachCode() {
		return "resourceconfirm";
	}

}

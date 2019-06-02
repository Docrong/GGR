package com.boco.eoms.sheet.businessimplementyy.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.interfaces.service.bo.IrmsResourceBo;
import com.boco.eoms.businessupport.order.model.OrderSheet;
import com.boco.eoms.businessupport.order.service.IOrderSheetManager;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
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
import com.boco.eoms.sheet.businessimplementyy.model.BusinessImplementYYMain;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;


public class BusinessImplementYYCrmManagerImpl extends InterfaceServiceManageAbstract implements IInterfaceServiceManage{

	public String getLinkBeanId() {
		// TODO Auto-generated method stub
		return "iBusinessImplementYYLinkManager";
	}

	public String getMainBeanId() {
		// TODO Auto-generated method stub
		return "iBusinessImplementYYMainManager";
	}

	public String getOperateName() {
		// TODO Auto-generated method stub
		return "newWorksheet";
	}

	public String getProcessTemplateName() {
		// TODO Auto-generated method stub
		return "BusinessImplementYYProcess";
	}

	public String getSendUser(Map map) {
		String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessimplementyy-crm.xml").getProperty("base.InterfaceUser"));
		return userId;
	}

	public String getTaskBeanId() {
		// TODO Auto-generated method stub
		return "iBusinessImplementYYTaskManager";
	}
	
	public HashMap addPara(HashMap hashMap){
		try{
			System.out.println("star corrKey...");
			hashMap.put("corrKey",UUIDHexGenerator.getInstance().getID());
			System.out.println("corrKey="+hashMap.get("corrKey").toString());
		}catch(Exception err){
			err.printStackTrace();
		}
		return hashMap;
	}

	public Map initMap(Map map, List attach, String type) throws Exception {
		try{
			map = this.loadDefaultMap(map, "config/businessimplementyy-crm.xml", type); 
			if(type.equals(InterfaceConstants.WORKSHEET_NEW)||type.equals(InterfaceConstants.WORKSHEET_RENEW)){
				
				String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessimplementyy-crm.xml").getProperty("base.SendRoleId"));
				map.put("sendRoleId", sendRoleId);
				
				String bigRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessimplementyy-crm.xml").getProperty("base.AcceptGroupId"));
				String toDeptId = StaticMethod.nullObject2String(map.get("toDeptId"));
				
				WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
				String subRoleId = "";
				TawSystemSubRole subRole = sm.getMatchRoles("BusinessImplementProcess", toDeptId, bigRole, map);
				if(subRole==null||subRole.getId()==null||subRole.getId().length()==0){
					System.out.println("未找到匹配角色，使用默认角色");
					subRoleId = XmlManage.getFile("/config/businessimplementyy-crm.xml").getProperty("base.AcceptRoleId");
				}else{
					subRoleId = subRole.getId();
				}
				map = sm.setAcceptRole(subRoleId, map);
				
				
				String orderSheetId = StaticMethod.nullObject2String(map.get("orderSheetId")); 
				if(orderSheetId.equals("")){
					OrderSheet order = new OrderSheet();	
					SheetBeanUtils.populateMap2Bean(order, map);

					orderSheetId = UUIDHexGenerator.getInstance().getID();
					order.setId(orderSheetId);
					IOrderSheetManager mgr = (IOrderSheetManager)ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");		
					mgr.saveOrUpdate(order);
				}
				
				System.out.println("attach size=="+attach.size());
				String attachId = this.getAttach(attach);
				System.out.println("#####################businessimplement#######attachId="+attachId);
				if(attachId!=null&&attachId.length()>0){
						map.put("businessimplement", attachId);
						map.put("sheetAccessories", attachId);
				}
			}
			if(type.equals(InterfaceConstants.WORKSHEET_RENEW)||type.equals(InterfaceConstants.WORKSHEET_UNTREAD)){
				String sheetId = StaticMethod.nullObject2String(map.get("serialNo"));
				if(sheetId==null || sheetId.equals(""))
					throw new Exception("sheetId为空");
				
				BaseMain main = null;
				IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(this.getMainBeanId());
				ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean(this.getTaskBeanId());
				
				List list = iMainService.getMainListByParentSheetId(sheetId);
				if(list.size()>0){
					boolean b = false;
					main = (BaseMain)list.get(0);
					System.out.println("SendRoleId="+main.getSendRoleId());
					List taskList = iTaskService.getCurrentUndoTask(main.getId());
					if(taskList!=null){
						for(int i=0;i<taskList.size();i++){
							ITask task = (ITask) taskList.get(i);
							System.out.println("TaskOwner="+task.getTaskOwner());
							if(task.getTaskOwner().equals(main.getSendRoleId())||task.getTaskOwner().equals(main.getSendUserId())||task.getTaskOwner().equals(main.getSendDeptId())){
									b = true;
									break;
							}
						}
					}
					System.out.println("allow renew:"+b);
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
				System.out.println("#####################businessimplement#######attachId="+attachId);
				if(attachId!=null&&attachId.length()>0){
						map.put("businessimplement", attachId);
						map.put("sheetAccessories", attachId);
				}
			}
			if(type.equals(InterfaceConstants.WORKSHEET_HOLD)){	//归档
				String endResult = StaticMethod.nullObject2String(map.get("endResult"));
				BocoLog.info(this, "endResult="+endResult);
				BusinessImplementYYMain main = null;
				IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(this.getMainBeanId());
				
				String sheetId = StaticMethod.nullObject2String(map.get("serialNo"));
				List list = iMainService.getMainListByParentSheetId(sheetId);
				if(list.size()>0){
					main = (BusinessImplementYYMain)list.get(0);		
					HashMap orderMap = SheetBeanUtils.bean2Map(main);
					
					String enable = XmlManage.getFile("/com/boco/eoms/businessupport/config/resourceInterface_util.xml").getProperty("base.enable");
					if(enable.equalsIgnoreCase("true")){
						IrmsResourceBo.occupyServiceRes(orderMap, null);	//资源实占	
					}
					
//					String orderSheetId = StaticMethod.nullObject2String(main.getOrderSheetId());	   
//		            List productList = OrderSheetMethod.getSpecialLineList(orderSheetId, Constants._LANGUAGE_LINE);
//					
//		            enable = XmlManage.getFile("/com/boco/eoms/businessupport/interfaces/transfer/config/transfer-config.xml")
//					.getProperty("enable");
//			 
//		    	   if(enable.equalsIgnoreCase("true")){
//					//传输归档
//						String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(orderMap,productList,StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/businessupport/interfaces/transfer/config/transfer-config.xml"), "irmsAttempArchive");
//						String result = TransferLoader.irmsAttempArchive(main.getSendUserId(), "", opDetail);
//						
//						InterfaceUtil interfaceUtil =new InterfaceUtil();
//						HashMap sheetMap = new HashMap();
//						sheetMap=interfaceUtil.xmlElements(result, sheetMap, "FieldContent");		
//						String isSuccess = StaticMethod.nullObject2String(sheetMap.get("isSuccess"));
//						if(isSuccess.equals("0"))
//							throw new Exception("传输归档失败：" + StaticMethod.nullObject2String(sheetMap.get("errorInfo")));
//		    	   }
//					enable = XmlManage.getFile("/com/boco/eoms/businessupport/interfaces/Irms/config/irmsres-config.xml").getProperty("enable");
//					if(enable.equalsIgnoreCase("true")){
//		    		   //将电路名称传给IRMS
//						IOrderSheetManager mgr = (IOrderSheetManager) ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");
//						OrderSheet ordersheet = (OrderSheet) mgr.getOrderSheet(orderSheetId);
//						orderMap.putAll(SheetBeanUtils.bean2Map(ordersheet));
//		    		   IrmsResLoader.addEomsResByProdTypeBO(orderMap,productList);
//					}
				}else{
					throw new Exception("没找到sheetNo＝"+sheetId+"对应的工单");
				}
			}
			return map;
		}catch(Exception err){
			err.printStackTrace();
			throw err;
		}
	}
	public String getSheetAttachCode() {
		return "businessimplementyy";
	}

}

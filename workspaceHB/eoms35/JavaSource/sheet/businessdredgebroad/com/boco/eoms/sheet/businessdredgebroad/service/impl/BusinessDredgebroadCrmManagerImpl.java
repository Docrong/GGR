package com.boco.eoms.sheet.businessdredgebroad.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.area.model.TawSystemArea;
import com.boco.eoms.commons.system.area.service.ITawSystemAreaManager;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.interfaceBase.service.IInterfaceServiceManage;
import com.boco.eoms.sheet.interfaceBase.service.impl.InterfaceServiceManageAbstract;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceConstants;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;


public class BusinessDredgebroadCrmManagerImpl extends InterfaceServiceManageAbstract implements IInterfaceServiceManage{

	public String getLinkBeanId() {
		// TODO Auto-generated method stub
		return "iBusinessDredgebroadLinkManager";
	}

	public String getMainBeanId() {
		// TODO Auto-generated method stub
		return "iBusinessDredgebroadMainManager";
	}

	public String getOperateName() {
		// TODO Auto-generated method stub
		return "newSheet";
	}

	public String getProcessTemplateName() {
		// TODO Auto-generated method stub
		return "BusinessDredgebroadMainFlowProcess";
	}

	public String getSendUser(Map map) {
		String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessDredgebroad-crm.xml").getProperty("base.InterfaceUser"));
		return userId;
	}

	public String getTaskBeanId() {
		// TODO Auto-generated method stub
		return "iBusinessDredgebroadTaskManager";
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
			map = this.loadDefaultMap(map, "config/businessDredgebroad-crm.xml", type); 
			if(type.equals(InterfaceConstants.WORKSHEET_NEW)||type.equals(InterfaceConstants.WORKSHEET_RENEW)){
				
				String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessDredgebroad-crm.xml").getProperty("base.SendRoleId"));
				map.put("sendRoleId", sendRoleId);
				
				String bigRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessDredgebroad-crm.xml").getProperty("base.AcceptGroupId"));
				//alter by yangna
				//String toDeptId = StaticMethod.nullObject2String(map.get("toDeptId"));
				
				String businesstype = InterfaceUtilProperties.getInstance().getDictIdByInterfaceCode("serviceType",StaticMethod.nullObject2String(map.get("serviceType")).toString());
				System.out.println("业务类型:"+businesstype);
				if("101100104".equals(businesstype)){
					String zxType = StaticMethod.nullObject2String(map.get("zxType"));
					System.out.println("专线类型:"+zxType);
					if("1".equals(zxType)){
						map.put("businesstype1", "101100108");
					}else if("2".equals(zxType)){
						map.put("businesstype1", "101100106");
					}else if("3".equals(zxType)){
						map.put("businesstype1", "101100107");
					}else if("4".equals(zxType)){
						map.put("businesstype1", "101100104");
					}else{
						map.put("businesstype1", "101100104");
					}
				}else{
				map.put("businesstype1", businesstype);//业务类型
				}
				 //add by yangna
				String toDeptId = StaticMethod.nullObject2String(map.get("cityName"));
				toDeptId = 	toDeptId+"市";
				System.out.println("toDeptId=="+toDeptId);
				ITawSystemAreaManager areaMgr = 
					(ITawSystemAreaManager)ApplicationContextHolder.getInstance().getBean("ItawSystemAreaManager");
				if(toDeptId.length()>0){
					try{
						TawSystemArea area = areaMgr.getAreaByCode(toDeptId);
						
						if(area!=null){
							System.out.println("Areaid=="+area.getAreaid());
							toDeptId = area.getAreaid();
						    map.put("toDeptId", area.getAreaid());
						}else{
							System.out.println("没有找到映射的用户归属地");
						}
					}catch(Exception err){
						System.out.println("没有找到映射的用户归属地");
					}
				}
				String title = StaticMethod.nullObject2String(map.get("title"));
				String city = StaticMethod.nullObject2String(map.get("cityName"));
				String customName = StaticMethod.nullObject2String(map.get("customName"));
				String btype1 = StaticMethod.nullObject2String(map.get("businesstype1"));
				ID2NameService service = (ID2NameService) ApplicationContextHolder
					.getInstance().getBean("ID2NameGetServiceCatch");			
                btype1 = service.id2Name(btype1,"ItawSystemDictTypeDao");
				title= title +"_"+city+customName+btype1;
				map.put("title", title);
				//end by yangna
				WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
				String subRoleId = "";
				//added by zhoupeng 20100819 个人宽带除西安以外的其他地市按县区匹配角色
				if(!"2801".equals(toDeptId)&&"101100109".equals(btype1)){
					String country = StaticMethod.nullObject2String(map.get("isRadiusValidate"));
					if(country.length()>0){
						try{
							TawSystemArea area = areaMgr.getAreaByCode(country);
							
							if(area!=null){
								System.out.println("country=="+area.getAreaid());
								country = area.getAreaid();
//							    map.put("toDeptId", area.getAreaid());
								TawSystemSubRole tempRole  = sm.getMatchRoles("BusinessDredgebroadProcess", country, bigRole, map);
								if(tempRole!=null&&tempRole.getId()!=null&&tempRole.getId().length()!=0){
									subRoleId = tempRole.getId();
								}
							}else{
								System.out.println("没有找到映射的所属县区："+country);
							}
						}catch(Exception err){
							System.out.println("没有找到映射的所属县区："+country);
						}
					}
				}
				if("".equals(subRoleId)){
				//added by zhoupeng 20100819
					TawSystemSubRole subRole = sm.getMatchRoles("BusinessDredgebroadProcess", toDeptId, bigRole, map);
					if(subRole==null||subRole.getId()==null||subRole.getId().length()==0){
						System.out.println("未找到匹配角色，使用默认角色");
						subRoleId = XmlManage.getFile("/config/businessDredgebroad-crm.xml").getProperty("base.AcceptRoleId");
					}else{
						subRoleId = subRole.getId();
					}
				}
				map = sm.setAcceptRole(subRoleId, map);
				
//				String needNetLevel = StaticMethod.nullObject2String(map.get("needNetLevel"));	//需求网络等级
//				String nowNetLevel = StaticMethod.nullObject2String(map.get("nowNetLevel"));	//实际网络等级
//				System.out.println("needNetLevel="+needNetLevel);
//				System.out.println("nowNetLevel="+nowNetLevel);
				
//				if(StaticMethod.getIntValue(needNetLevel)<StaticMethod.getIntValue(nowNetLevel)){	//送审,上海本地化
//					String auditPerformer = XmlManage.getFile("/config/businessDredgebroad-crm.xml").getProperty("base.AuditRoleId");
//					map = sm.setAuditRole(auditPerformer, map);
//
//					System.out.println("AuditRoleId="+auditPerformer);
//				}
				//附件下载重复，将下面代码整合到这里 alter by yangna20100331
				System.out.println("attach size=="+attach.size());
				String attachId = this.getAttach(attach);
				System.out.println("#####################BusinessDredgebroad#######attachId="+attachId);
				if(attachId!=null&&attachId.length()>0){
					map.put("businessdredgebroad", attachId);
					map.put("sheetAccessories", attachId);
					String serviceType = StaticMethod.nullObject2String(map.get("serviceType"));
					if(serviceType.equals("1"))
						map.put("numDetail", attachId);
					else if(serviceType.equals("2"))
						map.put("appProgramme", attachId);
					else if(serviceType.equals("3"))
						map.put("appProgramme", attachId);
					else if (serviceType.equals("4"))
						map.put("netTop", attachId);
				}
			}
			map.put("acceptTimeLimit", map.get("sheetAcceptLimit"));
			map.put("completeTimeLimit", map.get("sheetCompleteLimit"));
			map.put("nodeAcceptLimit", map.get("sheetAcceptLimit"));
			map.put("nodeCompleteLimit", map.get("sheetCompleteLimit"));	
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
			}
			
			 //add by yangna 下载重复，这段代码整合到上面162-177行
			/** 附件，暂时屏蔽掉*/
			//System.out.println("attach size=="+attach.size());
			//String attachId = this.getAttach(attach);
			//System.out.println("#####################BusinessBackout#######attachId="+attachId);
			//if(attachId!=null&&attachId.length()>0){
			//		map.put("businessbackout", attachId);
			//		map.put("sheetAccessories", attachId);
			//}
			//end by yangna
			return map;
		}catch(Exception err){
			err.printStackTrace();
			throw err;
		}
	}
	public String getSheetAttachCode() {
		return "businessdredgebroad";
	}

}

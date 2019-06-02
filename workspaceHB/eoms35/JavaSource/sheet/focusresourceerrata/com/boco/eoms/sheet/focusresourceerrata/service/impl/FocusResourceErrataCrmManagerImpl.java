package com.boco.eoms.sheet.focusresourceerrata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.focusresourceerrata.service.IFocusResourceErrataMainManager;
import com.boco.eoms.sheet.focusresourceerrata.webapp.action.FocusResourceErrataAction;
import com.boco.eoms.sheet.interfaceBase.service.IInterfaceServiceManage;
import com.boco.eoms.sheet.interfaceBase.service.impl.InterfaceServiceManageAbstract;

public class FocusResourceErrataCrmManagerImpl extends InterfaceServiceManageAbstract implements IInterfaceServiceManage{

	public String getLinkBeanId() {
		// TODO Auto-generated method stub
		return "iFocusResourceErrataLinkManager";
	}

	public String getMainBeanId() {
		// TODO Auto-generated method stub
		return "iFocusResourceErrataMainManager";
	}

	public String getOperateName() {
		// TODO Auto-generated method stub
		return "newWorkSheet";
	}

	public String getProcessTemplateName() {
		// TODO Auto-generated method stub
		return "FocusResourceErrata";
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
	public String getSendUser(Map map) {
		String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("base.InterfaceUser"));
		return userId;
	}

	public String getTaskBeanId() {
		// TODO Auto-generated method stub
		return "iFocusResourceErrataTaskManager";
	}
	
	public Map initMap(Map map, List attach, String type) throws Exception {
		try{
			IFocusResourceErrataMainManager mainservice = (IFocusResourceErrataMainManager)ApplicationContextHolder.getInstance().getBean("iFocusResourceErrataMainManager");
			map = this.loadDefaultMap(map, "config/focusresourceerrata-crm.xml", type);
			String mainSheetNun = StaticMethod.nullObject2String(map.get("mainSheetNun"));
			if(!"".equals(mainSheetNun)){
				List mainsList = mainservice.getMainsByCondition(" mainSheetNun ="+mainSheetNun+" and deleted = '0'");
				if(mainsList != null && mainsList.size()>0){
					throw new Exception("已存在网元对应的勘误工单");
				}
			}else{
				throw new Exception("工单编号（开通工单）为空");
			}
			//设置时间
			String acceptLimit = StaticMethod.getLocalString(6);
			String completeLimit = StaticMethod.getLocalString(7);
			map.put("sheetAcceptLimit", acceptLimit);
			map.put("sheetCompleteLimit", completeLimit);
			map.remove("id");
			if(type.equals("newWorkSheet")){
				WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
				
				/**
				 * 根据字段 电路核查状态 判定流程走向
  					电路核查状态 0 时：新建派发----监控部自动流转----归档
  
  					电路核查状态 1 时，
  					业务类型  跨省、跨市专线， 新建派发---监控部自动流转----传输网部审核  在此环节的人可以手动 提交 监控部自动流转和地市勘误
  					业务类型  其他专线类型，根据  电路核查失败所属地市 字段判定流程走向，新建派发---监控部自动流转----传输网部审核--地市勘误
  
  					流程到了  地市勘误 后，
  					提交  监控部自动流转（并且调用综资接口核查电路状态），状态为 0 自动归档，状态为1 进入循环
				 */
				//电路核查状态
				String mainCheckState = StaticMethod.nullObject2String(map.get("mainCheckState"));
				//业务类型
				String mainBusinessType = StaticMethod.nullObject2String(map.get("mainBusinessType"));
				//电路核查失败所属地市
				String mainFailCity = StaticMethod.nullObject2String(map.get("mainFailCity"));
				
				String subRoleId = "";
				FocusResourceErrataAction action = new FocusResourceErrataAction();
				IMainService iMainService = (IMainService) ApplicationContextHolder.getInstance().getBean(getMainBeanId());
				String sheetId = StaticMethod.nullObject2String(iMainService.getSheetId());
				String sheetKey = UUIDHexGenerator.getInstance().getID();
				map.put("sheetId", sheetId);
				map.put("sheetKey", sheetKey);
				map.put("id", sheetKey);
				System.out.println("id==lyg=="+sheetKey+"====sheetId="+sheetId);
				String title = StaticMethod.nullObject2String(map.get("title"));
				Map sheetMap = new HashMap();
				sheetMap.put("sheetId", sheetId);
				sheetMap.put("sheetKey", sheetKey);
				sheetMap.put("title", title);
				sheetMap.put("mainCheckState", mainCheckState);
				sheetMap.put("timeLine", "crm");
				
				if("0".equals(mainCheckState)){
					//自动归档
					subRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("base.InterfaceUser"));
					action.createMainData(map);
					sheetMap.put("dealPerformer", subRoleId);
					sheetMap.put("operateType", "102");
					action.createT1Data(sheetMap);
					action.createHoldTaskData(sheetMap);
					map.put("phaseId", "Over");
				}else{
					if("4".equals(mainBusinessType) || "5".equals(mainBusinessType) || "2004".equals(mainBusinessType)){//业务类型  跨省、跨市专线
						//此时工单流转到 传输网部审核(T2)
						subRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/focusresourceerrata-crm.xml").getProperty("T2.subRoleId"));
						//T1自动生成
						sheetMap.put("dealPerformer", subRoleId);
						sheetMap.put("operateType", "101");
						action.createT1Data(sheetMap);
						map.put("phaseId", "TransmissionNet");
					}else{
						//工单流转到 地市勘误(T3)
						ITawSystemSubRoleManager mgr = (ITawSystemSubRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
						if("".equals(mainFailCity)){
							throw new Exception("电路核查失败所属地市为空导致没有找到匹配角色");
						}
						List subRoleList = mgr.getTawSystemSubRoles(" roleid= '1004' and deptid='"+mainFailCity+"'");
						if(subRoleList != null && subRoleList.size()>0){
							TawSystemSubRole subrole =(TawSystemSubRole) subRoleList.get(0);
							subRoleId = subrole.getId();
							sheetMap.put("dealPerformer", subRoleId);
							sheetMap.put("operateType", "101");
							action.createT1Data(sheetMap);
							sheetMap.put("operateType", "104");
							action.createT2Data(sheetMap);
						}else{
							throw new Exception("根据 电路核查失败所属地市 没有找到匹配角色");
						}
						map.put("phaseId", "CitieErrata");
					}
				}
				
				
				if(!"".equals(subRoleId))
				{
					map = sm.setAcceptRole(subRoleId, map);
				}else{
					System.out.println("newWorkSheet未找到匹配角色,工单生成失败");
					throw new Exception("根据规则没有找到匹配角色");
				}
				
			}
			return map;
		}catch(Exception err){
			err.printStackTrace();
			throw err;
		}		
	}

	public String getSheetAttachCode()
	{
		return "focusresourceerrata";
	}
	
	
}

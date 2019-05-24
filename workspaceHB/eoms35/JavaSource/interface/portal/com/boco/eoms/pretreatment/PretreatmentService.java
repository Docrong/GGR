package com.boco.eoms.pretreatment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.service.IBusinessFlowService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.SheetUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.commonfault.model.CommonFaultLink;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultLinkManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultTaskManager;
import com.boco.eoms.sheet.commonfault.service.bo.CommonFaultBO;
import com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask;
import com.boco.eoms.sheet.netownership.model.NetOwnership;
import com.boco.eoms.sheet.netownership.service.INetOwnershipManager;
import com.boco.eoms.sheet.netownershipwireless.model.NetOwnershipwireless;
import com.boco.eoms.sheet.netownershipwireless.service.INetOwnershipwirelessManager;
import com.boco.eoms.util.InterfaceUtil;


public class PretreatmentService {
	
	
	
	/**
	 * 传递预处理结果服务接口
	 * @param opDetail 为封装好的xml格式字符串
	 * @exception Exception
	 * */
	public String transmitPretreatmentResult(String opDetail) {
		
		String autoUser = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("AutoUser"));
  		String autoSubRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("AutoSubRole"));
		String result ="";
		try{
		InterfaceUtil interfaceUtil =new InterfaceUtil();
		ICommonFaultMainManager mainservice = (ICommonFaultMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
	    ICommonFaultTaskManager taskservice = (ICommonFaultTaskManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultTaskManager");
		ICommonFaultLinkManager iCommonFaultLinkManager = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
		  WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
		CommonFaultMain main = new CommonFaultMain();
		HashMap sheetMap = new HashMap();
		
		System.out.println("transmitPretreatmentResult:opDetail=" + opDetail);
		if(opDetail != null && !"".equals(opDetail)){
			//把输入进来的xml形式的opDetail转化为Map类型
			sheetMap=interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
			String sheetId = StaticMethod.nullObject2String(sheetMap.get("sheetId"));
			String pretResult = StaticMethod.nullObject2String(sheetMap.get("pretResult"));
			String preMark = StaticMethod.nullObject2String(sheetMap.get("preMark"));
			if (pretResult != null && !"".equals(pretResult)) {
				if(sheetId != null && !"".equals(sheetId)){
					main = (CommonFaultMain)mainservice.getMainBySheetId(sheetId);//获取CommonFaultMain对象
					if(null != main){
						String mainPretResult = StaticMethod.nullObject2String(main.getMainPretResult());
						String mainPretResultOne = StaticMethod.nullObject2String(main.getMainPretResultOne());
						String mainPretResultTwo= StaticMethod.nullObject2String(main.getMainPretResultTwo());
						String mainPretResultThree = StaticMethod.nullObject2String(main.getMainPretResultThree());
						String mainPretResultFour = StaticMethod.nullObject2String(main.getMainPretResultFour());
						if ("".equals(mainPretResult)) {
							main.setMainPretResult(pretResult);
							mainservice.addMain(main);
						} else if ("".equals(mainPretResultOne)) {
							main.setMainPretResultOne(pretResult);
							mainservice.addMain(main);
						} else if ("".equals(mainPretResultTwo)) {
							main.setMainPretResultTwo(pretResult);
							mainservice.addMain(main);
						} else if ("".equals(mainPretResultThree)) {
							main.setMainPretResultThree(pretResult);
							mainservice.addMain(main);
						} else if ("".equals(mainPretResultFour)) {
							main.setMainPretResultFour(pretResult);
							mainservice.addMain(main);
						}
						result = "Status=0;sheetDetail=;Errlist=";
						
		      String sheetKey = StaticMethod.nullObject2String(main.getId());
		      String mainStatus = StaticMethod.nullObject2String(main.getStatus());
			  String condition = " sheetKey = '" + sheetKey + "' and  taskName ='FirstExcuteHumTask' and taskstatus ='2' and  sendRejectFlag ='0'";
		        List taskList = taskservice.getTasksByCondition(condition);
		          if ((taskList != null) && (taskList.size() > 0) && !"-14".equals(mainStatus)) {	
		           	   ITask task = (ITask)taskList.get(0);
						String mainNetName = StaticMethod.nullObject2String(main.getMainNetName());
						String mainFaultResponseLevel = StaticMethod.nullObject2String(main.getMainFaultResponseLevel());
						String mainNetSortOne =StaticMethod.nullObject2String(main.getMainNetSortOne());
						String mainNetSortThree =StaticMethod.nullObject2String(main.getMainNetSortThree());
						String subroleid = "";
						String ifAutotran = "";
				      	String ccObject = "";
				    	String ccObject1 = "";
					//	String eomsCityId = "";
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
						String mainAlarmId = StaticMethod.nullObject2String(main.getMainAlarmId());
						INetOwnershipwirelessManager netOwnershipwirelessManager = (INetOwnershipwirelessManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipwirelessManager");
						String relationDH = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("relationDH"));//关联动环
						String relationCS = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("relationCS"));//关联传输
				//		String yijiaoType = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("ttTypeyj"));//铁塔移交站					
						System.out.println("lizhi:mainAlarmId="+mainAlarmId + "mainFaultResponseLevel=" + mainFaultResponseLevel);						 
				//		if("1".equals(preMark) || "2".equals(preMark)){
						if (mainNetSortOne.equals("101010401")) {//针对于无线专业的
							if (!"".equals(mainNetName)) {
										NetOwnershipwireless netownershipwireless = (NetOwnershipwireless) netOwnershipwirelessManager.getNetOwnershipByNetName(mainNetName);
										List tuifualarm = netOwnershipwirelessManager.getTuifuAlarmBynetId(" and netid='"+mainAlarmId+"'");//根据名字去表里查询对应的信息
										NetOwnershipwireless tttype = netOwnershipwirelessManager.getNetOwnershipByNetName(mainNetName);//铁塔类型
										if (null != netownershipwireless) {
											System.out.println("The information is tuifualarm.size():"+tuifualarm.size()+"; tttype.getTttype(): "+tttype.getTttype()+"; relationDH:"+relationDH+"  ;relationCS:"+relationCS);
											//判断为退服告警且标识为铁塔移交站，无动环关联标识的工单，不自动转单
											if(tuifualarm.size()>0  && relationDH.indexOf(mainNetSortThree)==-1 && relationCS.indexOf(mainNetSortThree)==-1)
											{
												if ("1".equals(preMark)){
												ifAutotran = "1";
												subroleid = netownershipwireless.getTtRoleId();
												ccObject1 = StaticMethod.nullObject2String(netownershipwireless.getTeamRoleId());
												}else if ("2".equals(preMark)){
												ifAutotran = "1";
												subroleid = netownershipwireless.getTeamRoleId();	
												}	else {
												ifAutotran = "1";	
												subroleid = netownershipwireless.getTtRoleId();	
												ccObject1 = StaticMethod.nullObject2String(netownershipwireless.getTeamRoleId());
												}
												ccObject = StaticMethod.nullObject2String(netownershipwireless.getCcObject());
													
											}									
										}
									} 
									
								System.out.println("fengmin:subroleid="+subroleid+"ifAutotran="+ifAutotran+"ccObject="+ccObject);
							
						}
					    Map mainrule = SheetBeanUtils.bean2Map(main);
				        HashMap columnMap = new HashMap();
				        HashMap sheetMap1 = new HashMap();	       
				        Map valueMap =  new  HashMap();
				        valueMap.putAll(mainrule);
				        sheetMap1.put("main", mainservice.getMainObject().getClass().newInstance());
						sheetMap1.put("link", iCommonFaultLinkManager.getLinkObject().getClass().newInstance());;
						sheetMap1.put("operate", "dealPerformer@java.lang.String,dealPerformerLeader@java.lang.String,dealPerformerType@java.lang.String,copyPerformer@java.lang.String,copyPerformerLeader@java.lang.String,copyPerformerType@java.lang.String,auditPerformer@java.lang.String,auditPerformerLeader@java.lang.String,auditPerformerType@java.lang.String,phaseId@java.lang.String,beanId@java.lang.String,mainClassName@java.lang.String,linkClassName@java.lang.String,hasNextTaskFlag@java.lang.String,reInvokeCount@java.lang.Integer,subAuditPerformer@java.lang.String,subAuditPerformerLeader@java.lang.String,subAuditPerformerType@java.lang.String,linkBeanId@java.lang.String,interfaceType@java.lang.String,methodType@java.lang.String,sendType@java.lang.String,extendPerformer@java.lang.String,extendPerformerLeader@java.lang.String,extendPerformerType@java.lang.String,extendKey1@java.lang.String,extendKey2@java.lang.String");
						columnMap.put("selfSheet", sheetMap1);
		
		    	if (!"".equals(subroleid) && subroleid !=null&& "1".equals(ifAutotran)) {
			           valueMap.put("phaseId", "SecondExcuteTask");			         
			           valueMap.put("dealPerformer", subroleid);
			           valueMap.put("dealPerformerLeader", subroleid);
			           valueMap.put("dealPerformerType", "subrole");
			           valueMap.put("operateType", "1");
			           valueMap.put("operateUserId",autoUser);
			           valueMap.put("operateRoleId",autoSubRole);
			           valueMap.put("operateDeptId","12201");
					//	Date opeTime = (Date)linkrule.get("operateTime");
						String prelinkId = StaticMethod.nullObject2String(task.getPreLinkId());
						String mainCompleteLimitT1 = StaticMethod.nullObject2String(mainrule.get("mainCompleteLimitT1"));
						Date mainCompleteLimitT1Date = new Date();
						if (!"".equals(mainCompleteLimitT1)) {
							mainCompleteLimitT1Date = sdf.parse(mainCompleteLimitT1);
						}
						String mainCompleteLimitT2 = StaticMethod.nullObject2String(mainrule.get("mainCompleteLimitT2"));
						Date mainCompleteLimitT2Date = new Date();
						if (!"".equals(mainCompleteLimitT2)) {
							mainCompleteLimitT2Date = sdf.parse(mainCompleteLimitT2);
						}
					    valueMap.put("mainId", main.getId());
						valueMap.put("mainManualPreSolve", "1030102");
						valueMap.put("linkIfMobile", "preTreatment");
					    createT1Link(mainrule,new Date(),prelinkId,subroleid,mainCompleteLimitT2Date);
					//	CommonFaultBO.createT1Task(mainrule,prelinkId,mainCompleteLimitT1Date);
						valueMap.put("nodeAcceptLimit", mainrule.get("mainCompleteLimitT2"));
						valueMap.put("nodeCompleteLimit", mainrule.get("mainCompleteLimitT2"));
						valueMap.put("manualPreSolve", "1030102");
						
						//判断是否铁塔班组start
						Map operateMap = new HashMap();
						operateMap.put("phaseId", "SecondExcuteTask");
						operateMap.put("dealPerformer", subroleid);
						CommonFaultBO bo = new CommonFaultBO();
						try {
							Map mainMap = (Map)bo.townerSend(mainrule, operateMap);
							if(mainMap != null){
								String mainIFTowner = StaticMethod.nullObject2String(mainMap.get("mainIFTowner"));
								valueMap.put("mainIFTowner", mainIFTowner);
							}
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
							System.out.println("PretreatmentService is erre");
						}
						//判断铁塔班组end
						
						String copyPerformer = "";
				        String copyPerformerLeader = "";
				        String copyPerformerType = "";
						if (!"".equals(ccObject)||!"".equals(ccObject1)) {
							
					        if(!"".equals(ccObject)&&"".equals(ccObject1)){
					        	copyPerformer =ccObject;
					        	copyPerformerLeader = ccObject;
					        	copyPerformerType ="subrole";
					        }else if(!"".equals(ccObject1)&&!"".equals(ccObject)){
					        	copyPerformer = ccObject +","+ccObject1;
					        	copyPerformerLeader = ccObject +","+ccObject1;
					        	copyPerformerType = "subrole,subrole";
					        }else if (!"".equals(ccObject1)&&"".equals(ccObject)){
					        	copyPerformer =ccObject1;
					        	copyPerformerLeader = ccObject1;
					        	copyPerformerType ="subrole";
					        }
						}
						valueMap.put("beanId", "iCommonFaultMainManager");
						valueMap.put("mainClassName", "com.boco.eoms.sheet.commonfault.model.CommonFaultMain");
						valueMap.put("linkClassName", "com.boco.eoms.sheet.commonfault.model.CommonFaultLink");
						
					     sm.dealSheet(sheetKey, valueMap, columnMap, autoUser, taskservice);  
					     
					     System.out.println("===lyg=copyPerformer=" + copyPerformer);
							if (!"".equals(copyPerformer))
							{
								System.out.println("===开始非流程抄送lyg===");
								valueMap.put("copyPerformer", copyPerformer);
								valueMap.put("copyPerformerLeader", copyPerformerLeader);
								valueMap.put("copyPerformerType", copyPerformerType);
								Map serializableMap = SheetUtils.serializableParemeterMap(valueMap);
								Iterator it = serializableMap.keySet().iterator();
								HashMap WpsMap = new HashMap();
								HashMap tempWpsMap;
								for (; it.hasNext(); WpsMap.putAll(tempWpsMap))
								{
									String mapKey = (String)it.next();
									Map tempMap = (Map)serializableMap.get(mapKey);
									HashMap tempColumnMap = (HashMap)columnMap.get(mapKey);
									tempWpsMap = SheetUtils.actionMapToEngineMap(tempMap, tempColumnMap);
								}

								IBaseSheet baseSheet = (IBaseSheet)ApplicationContextHolder.getInstance().getBean("CommonFault");
								baseSheet.newSaveNonFlowData("", WpsMap);
								System.out.println("===非流程抄送结束lyg===");
							}
					     
					     
				         
			    	}
			  //  	}     
		          
		          }
						
						return result;
						
					}else{
						result =  "Status=-1;sheetDetail=;Errlist=工单数据服务接口没有找到工单流水号为 "+sheetId+" 的工单未找到或不存在,请查证！";
						return result;
					}
				}else{
					result =  "Status=-1;sheetDetail=;Errlist=工单数据服务接口没有找到对应工单流水号Sheet_id的参数,请查证！";
					return result;
				}
			}else {
				result =  "Status=-1;sheetDetail=;Errlist=工单数据服务接口预处理结果为空,请查证！";
				return result;
			}
		}else{
			result ="Status=-1;sheetDetail=;Errlist=工单数据服务接口没有传入参数,请查证！";
			return result;
		}
	
		} catch (Exception e) {
			e.printStackTrace();
			result="Status=-1;sheetDetail=;Errlist=工单数据服务接口出错！详细信息为"+e.getMessage();
			return result;
		} finally {
			System.out.println("transmitPretreatmentResult:result=" + result);
		}
	}
	
	
	public static CommonFaultLink createT1Link(Map mainrule, Date operateTime, String prelinkId, String subroleid, Date mainCompleteLimitT2Date)
	throws Exception
	{
  		String autoUser = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("AutoUser"));
  		String autoSubRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/commonfault-util.xml").getProperty("AutoSubRole"));
  		ICommonFaultLinkManager linkservice = (ICommonFaultLinkManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultLinkManager");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(operateTime);
		calendar.add(13, -1);
		CommonFaultLink T1link61 = (CommonFaultLink)linkservice.getLinkObject().getClass().newInstance();
		T1link61.setId(UUIDHexGenerator.getInstance().getID());
		T1link61.setOperateType(new Integer("61"));
		T1link61.setActiveTemplateId("FirstExcuteHumTask");
		T1link61.setOperateTime(calendar.getTime());
		T1link61.setOperateDay(calendar.get(5));
		T1link61.setOperateMonth(calendar.get(2) + 1);
		T1link61.setOperateYear(calendar.get(1));
		T1link61.setMainId(StaticMethod.nullObject2String(mainrule.get("id")));
		T1link61.setToOrgRoleId(subroleid);
		T1link61.setPreLinkId(prelinkId);
		T1link61.setNodeAccessories("");
		T1link61.setToOrgType(new Integer(0));
		T1link61.setCompleteFlag(new Integer(0));
		T1link61.setOperateUserId(StaticMethod.nullObject2String(autoUser));
		T1link61.setOperateRoleId(StaticMethod.nullObject2String(autoSubRole));
		T1link61.setOperateDeptId("12201");
		T1link61.setTemplateFlag(0);
		linkservice.addLink(T1link61);
		return T1link61;
	}
}

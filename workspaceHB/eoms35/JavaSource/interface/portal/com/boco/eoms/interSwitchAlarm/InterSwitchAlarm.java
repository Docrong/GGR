package com.boco.eoms.interSwitchAlarm;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.EOMSAttributes;
import com.boco.eoms.common.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.user.util.UserMgrLocator;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sequence.ISequenceFacade;
import com.boco.eoms.sequence.Sequence;
import com.boco.eoms.sequence.exception.SequenceNotFoundException;
import com.boco.eoms.sequence.util.SequenceLocator;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.commonfault.model.CommonFaultMain;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultMainManager;
import com.boco.eoms.sheet.commonfault.service.bo.CommonFaultBO;
import com.boco.eoms.sheet.commonfaultpack.dao.ICommonFaultPackMainDAO;
import com.boco.eoms.sheet.commonfaultpack.model.CommonFaultPackMain;
import com.boco.eoms.sheet.commonfaultpack.service.ICommonFaultPackMainManager;
import com.boco.eoms.util.InterfaceUtil;
import com.boco.eoms.util.StaxParser;

public class InterSwitchAlarm {
	ICommonFaultMainManager iCommonFaultMainMgr = 
		 (ICommonFaultMainManager)ApplicationContextHolder.getInstance().getBean("iCommonFaultMainManager");
	/**
	 * 
	 * 连通测试
	 * 
	 * @param isAlive
	 * @param
	 * @return isAliveResult(string类型。为空表示相应服务可用，非空表示该服务当前出现问题，错误返回信息用相应明文进行说明)
	 * @throws 执行报错
	 *             RuleToolXMLException
	 */
	public String isAlive() {
		// BocoLog.trace(this, 35, "收到握手信号");
		System.out.println("收到握手信号");
		String isAliveResult = "";

		return isAliveResult;
	}

	// serSupplier string(16) 服务提供方 M 参见附录A.3
	// serCaller string(16) 服务调用方 M 参见附录A.3
	// callerPwd string(32) 口令 M,N /
	// callTime string(20) 服务调用时间 M /
	// alarmId string(80) 网管告警流水号 M 网管系统简称_告警ID
	// alarmStaId string(80) 网管告警ID M /
	// oriAlarmId string(40) 原始告警号 M,N /
	// alarmTitle string(400) 告警标题 M /
	// alarmCreateTime string(20) 告警产生时间 M /
	// neType string(20) 网元类型 M /
	// 网络层次
	// neName string(32) 网元名称 M /
	// alarmLevel string(20) 告警级别 M 梳理后网管定义的告警级别（四级）
	/**
	 * 
	 * 告警接收 E-OMS系统提供该接口服务用于接收需要自动派发故障工单的网管告警信息
	 * 
	 * @paramserSupplier string(16) 服务提供方 M 参见附录A.3
	 * @paramserCaller string(16) 服务调用方 M 参见附录A.3
	 * @paramcallerPwd string(32) 口令 M,N /
	 * @paramcallTime string(20) 服务调用时间 M /
	 * @param alarmId
	 *            string(80) 网管告警流水号 M 网管系统简称_告警ID
	 * @param alarmStaId
	 *            string(80) 网管告警ID M /
	 * @param oriAlarmId
	 *            string(40) 原始告警号 M,N /
	 * @param alarmTitle
	 *            string(400) 告警标题 M /
	 * @param alarmCreateTime
	 *            string(20) 告警产生时间 M /
	 * @param neType
	 *            string(20) 网元类型 M /
	 * @param neName
	 *            string(32) 网元名称 M /
	 * @param alarmLevel
	 *            string(20) 告警级别 M 梳理后网管定义的告警级别（四级）
	 * @return result
	 *         输出为一个字符串，格式为“sheetNo=工单号;errList=错误描述”。如果派单成功：“工单号”不为空串，“错误描述”为空串；如果派单失败，“工单号”为空串，“错误列表”为错误描述。约定“sheetNo=”和“errList=”不管派单成功与否都必须有，只是值可以为空串。
	 * @throws 执行报错RuleToolXMLException
	 *             public String newAlarm(String serSupplier, String serCaller,
	 *             String callerPwd, String callTime, String opDetail){ try{
	 *             System.out.println("告警派单");
	 *             System.out.println("opDetail＝"+opDetail);
	 * 
	 * HashMap sheetMap = new HashMap(); sheetMap.put("serSupplier",
	 * serSupplier); sheetMap.put("serCaller", serCaller);
	 * sheetMap.put("callerPwd", callerPwd); sheetMap.put("callTime", callTime);
	 * InterfaceUtil interfaceUtil =new InterfaceUtil();
	 * sheetMap=interfaceUtil.xmlElements(opDetail, sheetMap, "FieldContent");
	 * 
	 * String sheetKey = CommonFaultBO.performAdd(sheetMap);
	 * System.out.println("接口派单返回ID:"+sheetKey); String result =
	 * "sheetNo="+sheetKey + ";errList="; return result; }catch(Exception err){
	 * err.printStackTrace(); return "sheetNo=;errList="+err.getMessage(); } }
	 */

	/**
	 * 
	 * 告警接收 E-OMS系统提供该接口服务用于接收需要自动派发故障工单的网管告警信息
	 * 
	 * @paramserSupplier string(16) 服务提供方 M 参见附录A.3
	 * @paramserCaller string(16) 服务调用方 M 参见附录A.3
	 * @paramcallerPwd string(32) 口令 M,N /
	 * @paramcallTime string(20) 服务调用时间 M /
	 * @param alarmId
	 *            string(80) 网管告警流水号 M 网管系统简称_告警ID
	 * @param alarmStaId
	 *            string(80) 网管告警ID M /
	 * @param oriAlarmId
	 *            string(40) 原始告警号 M,N /
	 * @param alarmTitle
	 *            string(400) 告警标题 M /
	 * @param alarmCreateTime
	 *            string(20) 告警产生时间 M /
	 * @param neType
	 *            string(20) 网元类型 M /
	 * @param neName
	 *            string(32) 网元名称 M /
	 * @param alarmLevel
	 *            string(20) 告警级别 M 梳理后网管定义的告警级别（四级）
	 * @return result
	 *         输出为一个字符串，格式为“sheetNo=工单号;errList=错误描述”。如果派单成功：“工单号”不为空串，“错误描述”为空串；如果派单失败，“工单号”为空串，“错误列表”为错误描述。约定“sheetNo=”和“errList=”不管派单成功与否都必须有，只是值可以为空串。
	 * @throws 执行报错RuleToolXMLException
	 */
	public String newAlarm(String serSupplier, String serCaller,
			String callerPwd, String callTime, String opDetail) {
		try {
			ICommonFaultPackMainManager iCommonFaultMainManager = (ICommonFaultPackMainManager) ApplicationContextHolder
					.getInstance().getBean("iCommonFaultPackMainManager");
			ICommonFaultMainManager iCommonFaultMainMgr = (ICommonFaultMainManager) ApplicationContextHolder
		            .getInstance().getBean("iCommonFaultMainManager");
			String result = "";
			StringBuffer sheetKey = new StringBuffer();
			String mainAlarmNumStr = "";
			HashMap sheetMap = new HashMap();
			System.out.println("告警派单");
			System.out.println("opDetail＝" + opDetail);
			sheetMap.put("serSupplier", serSupplier);
			sheetMap.put("serCaller", serCaller);
			sheetMap.put("callerPwd", callerPwd);
			sheetMap.put("callTime", callTime);
			sheetMap.put("createType", "0");
			//sheetMap.put("operateRoleId", XmlManage.getFile("/config/commonfault-util.xml").getProperty("InterfaceSender"));//20110728
			//modify by xqz 2009-9-16
//			InterfaceUtil interfaceUtil = new InterfaceUtil();
//			List alarmList = interfaceUtil.xmlElementsList(opDetail, sheetMap,
//					"FieldContent");
			List alarmList = StaxParser.getInstance().getOpdetailList(opDetail,sheetMap);
			//modify by xqz 2009-9-16 end
			String[] sheetKeys = null;
			List compareList = new ArrayList();
			StringBuffer compareStr = new StringBuffer();
			if (alarmList.size() == 1) {
            // 非打包工单，单一告警
			    String packFlag=analyseAlarm(alarmList, sheetKey, result);
			    if(packFlag!=null&&"zhuipai".equals(packFlag)){
			    	try {
			    		HashMap alarmFisrtMap = (HashMap) alarmList.get(0);
			    		String sheetId = StaticMethod.nullObject2String(alarmFisrtMap.get("sheetNo"));
						String sequenceOpen = StaticMethod
								.null2String(((EOMSAttributes) ApplicationContextHolder
										.getInstance().getBean("eomsAttributes"))
										.getSequenceOpen());
						if ("true".equals(sequenceOpen)) {
							// 初始化队列
							ISequenceFacade sequenceFacade = SequenceLocator
									.getSequenceFacade();
							Sequence sendmsgSequence = null;
							try {
								sendmsgSequence = sequenceFacade.getSequence("sendmsg");
							} catch (SequenceNotFoundException e) {
								e.printStackTrace();
							}
							// 入队列
							CommonFaultBO bo = new CommonFaultBO();
							sequenceFacade.put(bo, "sendAlarmInfo", new Class[] {
									java.util.Map.class, java.lang.String.class,
									java.lang.String.class, java.lang.String.class,
									java.lang.String.class, java.lang.String.class },
									new Object[] {
									        alarmFisrtMap,
									        alarmFisrtMap.get("mainAlarmNum").toString(),
											sheetId,
											"待受理",
											StaticMethod.nullObject2String(alarmFisrtMap
													.get("serCaller")), null },
									null, sendmsgSequence);
							sendmsgSequence.setChanged();
							sequenceFacade.doJob(sendmsgSequence);
						} else {
							CommonFaultBO.sendAlarmInfo(alarmFisrtMap.get("mainAlarmNum")
									.toString(), sheetId, "待受理", StaticMethod
									.nullObject2String(alarmFisrtMap.get("serCaller")),
									null);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}	
			    }
			    sheetKeys = sheetKey.toString().split(";");   			    
			} else if (alarmList.size() > 1) {				
				ArrayList mapList = new ArrayList();
				String flag = null;
				for (int j = 0; j < alarmList.size(); j++) {
				     HashMap alarmMap = (HashMap) alarmList.get(j);
		             if (j == 0) {
		               //判断是新增告警，还是追加告警
					   flag = analyseAlarm(alarmList, sheetKey, result);
				    }
				    alarmMap = CommonFaultBO.parseAlarmInfo(alarmMap);
				    if (j == 0 && flag.equals("new")) {
				       sheetMap.put("sender", alarmMap.get("sender"));
					   sheetMap.put("alarmTitle", alarmMap.get("alarmTitle"));
					   continue;
				    } else {
					   mapList.add(alarmMap);
				    }	    
				    mainAlarmNumStr += StaticMethod.nullObject2String(alarmMap.get("mainAlarmNum")).trim()+ ";";
				    compareStr = (StringBuffer) alarmMap.get("compareStr");
				    compareList.add(compareStr);
				}
				
				String[] alarmInfo = compareStr.toString().split(";");
				System.out.println("alarmInfo length" + alarmInfo.length);
				if (alarmInfo.length != 5)
					return "sheetNo=;errList=告警信息不全，请补充后再派发！";	    
				HashMap alarmFisrtMap = (HashMap) alarmList.get(0);
				alarmFisrtMap.put("mainAlarmNum", mainAlarmNumStr);
				alarmFisrtMap.put("serSupplier", serSupplier);
				alarmFisrtMap.put("createType", "0");
				System.out.println("packfault flag is:" + flag);
				if(flag!=null&&"new".equals(flag)){
					sheetKey.append(CommonFaultBO.performAdd(alarmFisrtMap));			
				}else if(flag!=null&&"zhuipai".equals(flag)){
					try {
						String sheetId = StaticMethod.nullObject2String(alarmFisrtMap.get("sheetNo"));
						String sequenceOpen = StaticMethod
								.null2String(((EOMSAttributes) ApplicationContextHolder
										.getInstance().getBean("eomsAttributes"))
										.getSequenceOpen());
						if ("true".equals(sequenceOpen)) {
							// 初始化队列
							ISequenceFacade sequenceFacade = SequenceLocator
									.getSequenceFacade();
							Sequence sendmsgSequence = null;
							try {
								sendmsgSequence = sequenceFacade.getSequence("sendmsg");
							} catch (SequenceNotFoundException e) {
								e.printStackTrace();
							}
							// 入队列
							CommonFaultBO bo = new CommonFaultBO();
							sequenceFacade.put(bo, "sendAlarmInfo", new Class[] {
									java.util.Map.class, java.lang.String.class,
									java.lang.String.class, java.lang.String.class,
									java.lang.String.class, java.lang.String.class },
									new Object[] {
									        alarmFisrtMap,
									        alarmFisrtMap.get("mainAlarmNum").toString(),
											sheetId,
											"待受理",
											StaticMethod.nullObject2String(alarmFisrtMap
													.get("serCaller")), null },
									null, sendmsgSequence);
							sendmsgSequence.setChanged();
							sequenceFacade.doJob(sendmsgSequence);
						} else {
							CommonFaultBO.sendAlarmInfo(alarmFisrtMap.get("mainAlarmNum")
									.toString(), sheetId, "待受理", StaticMethod
									.nullObject2String(alarmFisrtMap.get("serCaller")),
									null);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				sheetKeys = sheetKey.toString().split(";");
				String mainAlarmNum[]=mainAlarmNumStr.split(";"); 
				//if(mainAlarmNum!=null&&mainAlarmNum.length>=1) alarmFisrtMap.put("", mainAlarmNum[0]);
				if(mainAlarmNum!=null&&mainAlarmNum.length>=1) alarmFisrtMap.put("mainAlarmNum", mainAlarmNum[0]);
				for (int k = 0; k < mapList.size(); k++) {
				    HashMap alarmMap = (HashMap) mapList.get(k);
				    CommonFaultPackMain mainObject = (CommonFaultPackMain) iCommonFaultMainManager
					    .getMainObject().getClass().newInstance();
				    SheetBeanUtils.populate(mainObject, alarmMap);
				    System.out.println("faultpacksinglemain:"
					    + mainObject.getMainAlarmNum());
				    mainObject.setMainId(sheetKeys[1]);
				    mainObject.setMainAlarmState("0");
				    if(flag!=null&&"zhuipai".equals(flag))
				    	mainObject.setTitle("【追】"+mainObject.getTitle());
				    iCommonFaultMainManager.saveOrUpdateMain(mainObject);
				  }
			    }
			    System.out.println("commonfault sheet add end ,sheetKey:" + sheetKey);
			    result = "sheetNo=" + sheetKeys[0] + ";errList=";
			    System.out.println("return result is:" + result);
			    return result;
			} catch (Exception err) {
			    err.printStackTrace();
			    return "sheetNo=;errList=" + err.getMessage();
			 }
	}

	private boolean compareListStr(List compareList) {
		boolean compareResult = false;
		String initStr = "";
		for (int i = 0; i < compareList.size(); i++) {
			StringBuffer compareStr = (StringBuffer) compareList.get(i);
			if (compareStr == null)
				return false;
			if ("".equals(initStr))
				initStr = compareStr.toString();
			else {
				if (initStr.equals(compareStr.toString()))
					compareResult = true;
				else
					compareResult = false;
			}
		}
		return compareResult;
	}

	// 参数名称 参数类型 中文名称 限定 说明
	// serSupplier string(16) 服务提供方 M 参见附录A.3
	// serCaller string(16) 服务调用方 M 参见附录A.3
	// callerPwd string(32) 口令 M,N /
	// callTime string(20) 服务调用时间 M /
	// alarmId
	// string(80) 网管告警ID M 网管系统简称_告警ID
	// alarmStatus Integer(2) 告警状态 M 1：自动清除；2：手工清除；
	// clearTime string(20) 告警清除时间 M /
	// staffNo string(32) 手工清除告警人员的工号 M,N /

	/**
	 * 
	 * 告警同步 (syncAlarm)
	 * 当已派发了故障工单的网管告警的状态发生变化时，网管系统调用E-OMS系统提供的接口服务，同步两个系统中的告警状态，并给出告警清除时间。
	 * 
	 * @param serSupplier
	 *            string(16) 服务提供方 M 参见附录A.3
	 * @param serCaller
	 *            string(16) 服务调用方 M 参见附录A.3
	 * @param callerPwd
	 *            string(32) 口令 M,N /
	 * @param callTime
	 *            string(20) 服务调用时间 M /
	 * @param alarmId
	 *            string(80) 网管告警ID M 网管系统简称_告警ID
	 * @param alarmStatus
	 *            Integer(2) 告警状态 M 1：自动清除；2：手工清除；
	 * @paramclearTime string(20) 告警清除时间 M /
	 * @param staffNo
	 *            string(32) 手工清除告警人员的工号 M,N /
	 * @return 输出为字符串。格式为“sheetNo=工单号;如果调用失败，则如果该告警在电子运维系统中不存在对应的工单则sheetNo为空串，，只是值可以为空串。
	 * @throws 执行报错
	 *             RuleToolXMLException
	 */
	public String syncAlarm(String serSupplier, String serCaller,
			String callerPwd, String callTime, String opDetail) {
		try {
			System.out.println("opDetail＝" + opDetail);

			HashMap sheetMap = new HashMap();
			sheetMap.put("serSupplier", serSupplier);
			sheetMap.put("serCaller", serCaller);
			sheetMap.put("callerPwd", callerPwd);
			sheetMap.put("callTime", callTime);
			InterfaceUtil interfaceUtil = new InterfaceUtil();
			sheetMap = interfaceUtil.xmlElements(opDetail, sheetMap,
					"FieldContent");
			String id = CommonFaultBO.updateMain(sheetMap);
			String result = "sheetNo=" + id + ";errList=";
			return result;
		} catch (Exception err) {
			err.printStackTrace();
			return "sheetNo=;errList=" + err.getMessage();
		}
	}

	public String eomsAuthentication(String serSupplier, String serCaller,
			String callerPwd, String callTime, String opDetail) {
		try {
			System.out.println("告警eoms鉴权");
			System.out.println("serSupplier＝" + serSupplier);
			System.out.println("serCaller＝" + serCaller);
			System.out.println("callerPwd＝" + callerPwd);
			System.out.println("callTime＝" + callTime);
			System.out.println("opDetail＝" + opDetail);

			String eomsAuthenticationresult = "";
			boolean result = false;
			InterfaceUtil interfaceUtil = new InterfaceUtil();
			HashMap map = new HashMap();
			map = interfaceUtil.xmlElements(opDetail, map, "FieldContent");

			System.out.println("userName=" + map.get("userName"));
			System.out.println("userPassword=" + map.get("userPassword"));

			String userName = (String) map.get("userName");
			String userPassword = (String) map.get("userPassword");
			result = UserMgrLocator.getTawSystemUserMgr().getUser(userName,
					userPassword);
			if (result == false) {
				eomsAuthenticationresult = "验证失败";
			}
			System.out.println("用户鉴权结果：" + eomsAuthenticationresult);
			return eomsAuthenticationresult;
		} catch (Exception err) {
			return err.getMessage();
		}
	}

	/**
	 * 解析主告警信息
	 * 
	 * @param alarmList
	 * @param sheetKey
	 * @param result
	 * @return
	 */
	private String analyseAlarm(List alarmList, StringBuffer sheetKey,
			String result) {
		try {

			// sheetKey = new StringBuffer();
			ICommonFaultPackMainManager iCommonFaultPackMainManager = (ICommonFaultPackMainManager) ApplicationContextHolder
					.getInstance().getBean("iCommonFaultPackMainManager");
			ICommonFaultMainManager iCommonFaultMainMgr = (ICommonFaultMainManager) ApplicationContextHolder
					.getInstance().getBean("iCommonFaultMainManager");
			String flag = null;
			result = "";
			HashMap alarmMap = (HashMap) alarmList.get(0);
			String sheetNo = StaticMethod.nullObject2String(alarmMap
					.get("sheetNo"));
			// 张晓杰修改功能：判断接口是否传过来sheetNo值，若没有则新增工单；若有，再判断此工单是否处于新增待受理状态，若是则追加；否则是新增
			if (StaticMethod.nullObject2String(alarmMap.get("sheetNo")).equals(
					"")
					|| iCommonFaultMainMgr.isHoldedBySheetId(null, sheetNo)) {// 新增工单

				if (alarmList != null && alarmList.size() > 1) {
					alarmMap.put("mainIsPack", "1030101");
                    //penghuan			
		
			        String  newTitle = "【追】"+StaticMethod.nullObject2String(alarmMap.get("alarmTitle"));
			        System.out.println("----追加告警信息1----"+ newTitle);
			        alarmMap.put("alarmTitle", newTitle);
					
				} else {
					alarmMap.put("mainIsPack", "1030102");
					sheetKey.append(CommonFaultBO.performAdd(alarmMap));
				}
				flag = "new";
			} else {// 追加告警信息
				String mainId = null;
				CommonFaultMain baseMain = iCommonFaultMainMgr
						.getCommonFaultMainBySheetId(sheetNo);
				if (baseMain != null) {
					if (alarmList != null && alarmList.size() > 0) {
                       
					if (!baseMain.getMainIsPack().equals("1030101")){
                        baseMain.setTitle("【追】" +baseMain.getTitle());  //penghuan
                        System.out.println("----追加告警信息-2---");
						baseMain.setMainIsPack("1030101");
					}
						mainId = baseMain.getId();
					}
					iCommonFaultMainMgr.addMain(baseMain);
				}
				sheetKey.append(StaticMethod.nullObject2String(alarmMap
						.get("sheetNo"))
						+ ";" + mainId);
				// 当只有1个告警信息则SAVE至CommonFaultPack表
				if (alarmList.size() == 1) {
					alarmMap = CommonFaultBO.parseAlarmInfo(alarmMap);
					CommonFaultPackMain mainObject = (CommonFaultPackMain) iCommonFaultPackMainManager
							.getMainObject().getClass().newInstance();
					SheetBeanUtils.populate(mainObject, alarmMap);
					System.out.println("faultpacksinglemain:"
							+ mainObject.getMainAlarmNum());
					mainObject.setMainId(mainId);
					mainObject.setTitle("【追】" + mainObject.getTitle());
					mainObject.setMainAlarmState("0");
					iCommonFaultPackMainManager.saveOrUpdateMain(mainObject);
				}
				flag = "zhuipai";
			}
			// 张晓杰修改结束()
			return flag;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * 	告警预处理信息追加 (preDealAlarm) EOMS系统提供该接口服务，用于接收需要追加的故障管理系统预处理告警信息，服务调用成功后，在EOMS系统中完成对告警的预处理告警信息记录
	 * @param serSupplier 服务提供方
	 * @param serCaller 服务调用方
	 * @param callerPwd 口令
	 * @param callTime  服务调用时间
	 * @param opDetail 详细信息
	 * @return 输出为字符串。格式为“sheetNo=工单号;errList=错误描述”。如果调用成功，则sheetNo不为空串为该告警对应的工单号、errList为空串；如果调用失败，则如果该告警在电子运维系统中不存在对应的工单则sheetNo为空串，errList为错误描述。约定“sheetNo=”和“errList=”不管调用成功与否都必须有，只是值可以为空串。
	 */
	public String preDealAlarm(String serSupplier, String serCaller, String callerPwd, String callTime, String opDetail) {
		try {
			BocoLog.info(this, "preDealAlarm start");
			BocoLog.info(this, "opDetail＝" + opDetail);
			String result = "";
			Map sheetMap = StaxParser.getInstance().getOpdetail(opDetail);
			//String syncResult = InterSwitchAlarmUtil.preDealAlarmByAlarmId(sheetMap);

			String alarmId = StaticMethod.nullObject2String(sheetMap.get("alarmId"));
			String preDealInfo = StaticMethod.nullObject2String(sheetMap.get("preDealInfo"));
			if ("".equals(alarmId)) {
				result = "告警信息中的接口字段alarmId为空，请核对！";
			} else if ("".equals(preDealInfo)) {
				result = "告警信息中的接口字段preDealInfo为空，请核对！";
			} else if (preDealInfo.length() > 4000) {
				result = "告警信息中的接口字段preDealInfo长度超过了4000字符，请核对！";
			} else {
				String sheetId = "";
				CommonFaultMain main = (CommonFaultMain) iCommonFaultMainMgr.getMainByAlarmId(alarmId);
				if (main == null) {
					result = "没找到alarmId='" + alarmId + "'对应的工单,not found this sheet";
				} else {
					String firstDealDesc=StaticMethod.nullObject2String(main.getMainFaultFirstDealDesc());
                    String autoHandle=firstDealDesc+"【"+StaticMethod.getLocalString()+" 追加告警预处理信息为：】"+preDealInfo;
                    if(autoHandle.length()>4000){
						 autoHandle=autoHandle.substring(0,4000);
					 }
					 main.setMainFaultFirstDealDesc(autoHandle);
					 iCommonFaultMainMgr.saveOrUpdateMain(main);
					 sheetId=main.getSheetId();
					 result="sheetNo="+sheetId;
				}
			}

			if (result.indexOf("sheetNo") > 0) {
				result = result + ";errList=";
			} else {
				return "sheetNo=;errList=" + result;
			}
			return result;
		} catch (Exception err) {
			err.printStackTrace();
			return "sheetNo=;errList=" + err.getMessage();
		}

	}
}

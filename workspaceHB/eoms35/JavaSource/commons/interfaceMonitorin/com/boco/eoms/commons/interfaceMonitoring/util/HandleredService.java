package com.boco.eoms.commons.interfaceMonitoring.util;

import java.util.Map;

import org.apache.axis.MessageContext;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.interfaceMonitoring.dao.hibernate.InterfaceMonitoringDaoHibernate;
import com.boco.eoms.commons.interfaceMonitoring.mgr.InterfaceMonitoringMgr;
import com.boco.eoms.commons.interfaceMonitoring.model.InterfaceMonitoring;
import com.boco.eoms.workbench.contact.util.ContactAttributes;




public class HandleredService  {
	public String publicMethod(String callingTime, String callingSide,
			String provider, String interFaceType, String interFaceMethod) {
		System.out.println("调用时间" + callingTime + "调用人" + callingSide + "接口"
				+ provider + "" + interFaceType + "" + interFaceMethod);
		String text = "调用时间" + callingTime + "调用人" + callingSide + "接口"
				+ provider + "" + interFaceType + "" + interFaceMethod;
		String result = "Success";
		InterfaceMonitoring interfaceMonitoring = new InterfaceMonitoring();
		interfaceMonitoring.setCallingSide(callingSide);
		interfaceMonitoring.setCallingTime(callingTime);
		interfaceMonitoring.setProvider(provider);
		interfaceMonitoring.setInterFaceType(interFaceType);
		interfaceMonitoring.setInterFaceMethod("Web Service");
		
		interfaceMonitoring.setText(text);
		interfaceMonitoring.setCallDirection("Longitudinal");
		if (result.equals("Success")) {
			interfaceMonitoring.setSuccess("Success");
		} else {
			interfaceMonitoring.setSuccess("Failure");
			interfaceMonitoring.setExceptionLog(result);
		}
		InterfaceMonitoringMgr attributes = (InterfaceMonitoringMgr) ApplicationContextHolder
				.getInstance().getBean("InterfaceMonitoringMgr");
		InterfaceMonitoringDaoHibernate InterfaceMonitoringDaoHibernate = new InterfaceMonitoringDaoHibernate();
		attributes.saveInterfaceMonitoring(interfaceMonitoring);
		return result;
	}

	/**
	 * @see 告警接收服务接口
	 * @return errList 假如传递参数有错，返回错误代码，errList为空表示调用成功
	 * @param 参数名称
	 *            类型(长度) 中文名称 限定 错误码 说明 serSupplier String(16) 服务提供方 M 001 参见附录A
	 *            serCaller String(16) 服务调用方 M 002 参见附录A callTime String(20)
	 *            服务调用时间 M 003 / callerPwd String(32) O alarmId String(80)
	 *            网管告警ID M 101 网管系统简称_告警ID oriAlarmId String(40) 原始告警号 O 102
	 *            alarmTitle String(40) 告警标题 M 103 / alarmCreateTime String(20)
	 *            告警产生时间 M 104 / neType String(20) 网元类型 M 105 / neName
	 *            String(31) 网元名称 M 106 / alarmLevel String(20) 告警级别 M 107 /
	 *            alarmType String(20) 告警类型 M 108 / alarmRedefLevel String(20)
	 *            重定义告警级别 O 109 / + String(20) 重定义告警类型 O 110 / alarmLocation
	 *            String(100) 告警定位 O 111 / alarmDetail String(300) 告警描述 M 112 /
	 *            IP网管没有* alarmPropose String(300) 告警建议 O / /告警建议是和网管协商增加的参数
	 *            alarmRegion String(50) 告警发生地区 M /
	 *            /告警发生区域是和网管协商增加的参数形如“沈阳市”、“大连市” dispatchType String(20) IP派单类型
	 *            M /0,手工;1自动 IP网管方面提出de方案
	 */
	public String newAlarm(String serSupplier, String serCaller,
			String callTime, String callerPwd, String alarmId,
			String oriAlarmId, String alarmTitle, String alarmCreateTime,
			String neType, String neName, String alarmLevel, String alarmType,
			String alarmRedefLevel, String alarmRedefType,
			String alarmLocation, String alarmDetail, String alarmRegion,
			String dispatchType) {
		String result = "Success";
		java.util.Map map = new java.util.HashMap();
		try{
		
		InterfaceMonitoring interfaceMonitoring = new InterfaceMonitoring();
		map.put("serSupplier", serSupplier);
		map.put("serCaller", serCaller);
		map.put("callTime", callTime);
		map.put("callTime", callTime);
		map.put("keyword", alarmId);
//		interfaceMonitoring.setInterFaceMethod("Web Service");
		interfaceMonitoring.setKeyword(alarmId);
		interfaceMonitoring.setInterFaceType("告警接收服务");
		if(serSupplier.equals("EOMS")){
			interfaceMonitoring.setCallDirection("Horizontal");	
		}
		else{
		interfaceMonitoring.setCallDirection("Longitudinal");
		}
		if (result.equals("Success")) {
			interfaceMonitoring.setSuccess("Success");
		} else {
			interfaceMonitoring.setSuccess("失败");
			interfaceMonitoring.setExceptionLog(result);
		}
		String text = "服务提供方:" + serSupplier+"&#13" + "服务调用方:" + serCaller+"&#13"+ "服务调用时间:"
				+ callTime +"&#13"+ "网管告警ID:" + alarmId+"&#13"+ "原始告警号:" + oriAlarmId+"&#13"
				+ "告警标题:" + alarmTitle+"&#13"+ "告警产生时间:" + alarmCreateTime+"&#13"+ "网元类型:"
				+ neName +"&#13"+ "告警级别:" + alarmLevel+"&#13" + "告警类型:" + alarmType+"&#13"
				+ "重定义告警级别:" + alarmRedefLevel+"&#13" + "重定义告警类型:" + alarmRedefType+"&#13"
				+ "告警定位:" + alarmLocation+"&#13";
////		map.put(arg0, arg1)
//		interfaceMonitoring.setText(text);
//		InterfaceMonitoringMgr attributes = (InterfaceMonitoringMgr) ApplicationContextHolder
//				.getInstance().getBean("InterfaceMonitoringMgr");
//		InterfaceMonitoringDaoHibernate InterfaceMonitoringDaoHibernate = new InterfaceMonitoringDaoHibernate();
//		attributes.saveInterfaceMonitoring(interfaceMonitoring);
//		this.interfaceMonitoringLlog(map)
	} catch (Exception ex) {
		result=ex.toString();
	}
//		InterfaceMonitoringTools interfaceMonitoringTools =new InterfaceMonitoringTools();
//		interfaceMonitoringTools.interfaceMonitoringLlog(map, result,"告警接收服务","newAlarm");
		
		return result;
	}

//	public void interfaceMonitoringLlog(Map map) {
//		InterfaceMonitoringTools interfaceMonitoringTools =new InterfaceMonitoringTools();
//		interfaceMonitoringTools.e
//		
//	}

}

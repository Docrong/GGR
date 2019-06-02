package com.boco.eoms.sheet.industrysms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.crm.bo.CrmLoader;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.industrysms.interfaces.DNMSProcessTransitLocator;
import com.boco.eoms.sheet.industrysms.interfaces.DNMSProcessTransitPortType;
import com.boco.eoms.sheet.industrysms.util.DesHexEncrypt;
import com.boco.eoms.sheet.interfaceBase.service.IWfInterfaceOperateManager;
import com.boco.eoms.sheet.interfaceBase.service.impl.WfInterfaceOperateManagerAbstract;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceDataMonitor;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;

public class IndustrySmsInterfaceManagerImpl extends WfInterfaceOperateManagerAbstract implements IWfInterfaceOperateManager{

	public boolean sendFlowInterfaceData(BaseMain main, BaseLink link, String interfaceType, String methodType, String serviceType) {
		Map monitorMap = new HashMap();
		String result = "";
		try{
			String nodeName = "";
			String opDetail = "";

			String attachRef = CrmLoader.createAttachRefXml(link.getNodeAccessories());
			
			String taskName = link.getActiveTemplateId();
			String operateType = link.getOperateType().toString();
			
			HashMap map = SheetBeanUtils.bean2Map(link);
			map.putAll(SheetBeanUtils.bean2Map(main));
			
			System.out.println("taskName="+taskName);
			System.out.println("operateType="+operateType);
			if(serviceType == null || serviceType.length() == 0)
	            serviceType = "0";
			if (taskName.equalsIgnoreCase("AuditHumTask") && operateType.equals("102")) {
				String serialNo = StaticMethod.nullObject2String(map.get("sheetId"));
				String jobNature = "";
				String serCaller = StaticMethod.nullObject2String(XmlManage.getFile("/config/industrysms-crm.xml").getProperty("base.serCaller"));
				String callerPwd = StaticMethod.nullObject2String(XmlManage.getFile("/config/industrysms-crm.xml").getProperty("base.callerPwd"));
				//String userValue = StaticMethod.nullObject2String(map.get("userValue"));
				String passwordValue = StaticMethod.nullObject2String(map.get("passwordValue"));
				String passwordNew = StaticMethod.nullObject2String(map.get("passwordNew"));
				DesHexEncrypt dhe = new DesHexEncrypt();
				dhe.getKey("DNMS_EMOS_ITMS");
				if (!"".equals(passwordValue)) {
					passwordValue = StaticMethod.nullObject2String(dhe.getEncString(passwordValue));
					map.put("passwordValue", passwordValue);
				}
				if (!"".equals(passwordNew)) {
					passwordNew = StaticMethod.nullObject2String(dhe.getEncString(passwordNew));
					map.put("passwordNew", passwordNew);
				}
				
				String regional = StaticMethod.nullObject2String(map.get("regional"));
				String ecsiType = StaticMethod.nullObject2String(map.get("ecsiType"));
				String abbreviation = StaticMethod.nullObject2String(map.get("abbreviation"));
				if ("101250301".equals(ecsiType)) {
					if ("101250201".equals(regional)) {
						abbreviation = abbreviation + "(MAS)";
					} else if ("101250202".equals(regional)){
						abbreviation = abbreviation + "(全网MAS)";
					}
					map.put("abbreviation", abbreviation);
				}
				
				String callTime = StaticMethod.date2String(new Date());
				String spareOne = StaticMethod.nullObject2String(map.get("spareOne"));
				if (null !=spareOne && "101250101".equals(spareOne)) {
					nodeName = "auditWorkSheetNew";
					jobNature = "new";
				} else if (null !=spareOne && "101250102".equals(spareOne)) {
					nodeName = "auditWorkSheetEdit";
					jobNature = "edit";
				} else if (null !=spareOne && "101250103".equals(spareOne)) {
					nodeName = "auditWorkSheetDel";
					jobNature = "del";
				}
				opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, StaticMethod.getFilePathForUrl("classpath:config/industrysms-crm.xml"), nodeName);

				if ("101250102".equals(spareOne)) {
					if (!"".equals(StaticMethod.nullObject2String(map.get("abbreviationNew")))) {
						String abbreviationNewop = "<fieldInfo><fieldChName>EC/SI简称(新)</fieldChName><fieldEnName>AbbreviationNew</fieldEnName><fieldContent>" + StaticMethod.nullObject2String(map.get("abbreviationNew")) + "</fieldContent></fieldInfo></recordInfo></opDetail>";
						opDetail = opDetail.replace("</recordInfo></opDetail>",abbreviationNewop);
					}
					if ("".equals(StaticMethod.nullObject2String(map.get("protocolNew")))) {
						String protocolNewop = "<fieldInfo><fieldChName>EC/SI使用协议(新)</fieldChName><fieldEnName>ProtocolNew</fieldEnName><fieldContent></fieldContent></fieldInfo>";
						opDetail = opDetail.replace(protocolNewop,"");
					}
					if (!"".equals(StaticMethod.nullObject2String(map.get("passwordNew")))) {
						String passwordNewop = "<fieldInfo><fieldChName>登录网关密码(新)</fieldChName><fieldEnName>PasswordNew</fieldEnName><fieldContent>" + StaticMethod.nullObject2String(map.get("passwordNew")) + "</fieldContent></fieldInfo></recordInfo></opDetail>";
						opDetail = opDetail.replace("</recordInfo></opDetail>",passwordNewop);
					}
					if (!"".equals(StaticMethod.nullObject2String(map.get("cilentServerIpAddrNew")))) {
						String cilentServerIpAddrNewop = "<fieldInfo><fieldChName>客户服务器IP(新)</fieldChName><fieldEnName>CilentServerIpAddrNew</fieldEnName><fieldContent>" + StaticMethod.nullObject2String(map.get("cilentServerIpAddrNew")) + "</fieldContent></fieldInfo></recordInfo></opDetail>";
						opDetail = opDetail.replace("</recordInfo></opDetail>",cilentServerIpAddrNewop);
					}
					if (!"".equals(StaticMethod.nullObject2String(map.get("cilentServerIpAddrsNew")))) {
						String cilentServerIpAddrsNewsop = "<fieldInfo><fieldChName>是否为多IP(新)</fieldChName><fieldEnName>CilentServerIpAddrsNew</fieldEnName><fieldContent>" + StaticMethod.nullObject2String(map.get("cilentServerIpAddrsNew")) + "</fieldContent></fieldInfo></recordInfo></opDetail>";
						opDetail = opDetail.replace("</recordInfo></opDetail>",cilentServerIpAddrsNewsop);
					}
					if (!"".equals(StaticMethod.nullObject2String(map.get("maxConnections"))) && !"".equals(StaticMethod.nullObject2String(map.get("maxConnectionsNew")))) {
						String maxConnectionsop = "<fieldInfo><fieldChName>最大连接数</fieldChName><fieldEnName>MaxConnections</fieldEnName><fieldContent>" + StaticMethod.nullObject2String(map.get("maxConnections")) + "</fieldContent></fieldInfo></recordInfo></opDetail>";
						opDetail = opDetail.replace("</recordInfo></opDetail>",maxConnectionsop);
						String maxConnectionsNewop = "<fieldInfo><fieldChName>最大连接数(新)</fieldChName><fieldEnName>MaxConnectionsNew</fieldEnName><fieldContent>" + StaticMethod.nullObject2String(map.get("maxConnectionsNew")) + "</fieldContent></fieldInfo></recordInfo></opDetail>";
						opDetail = opDetail.replace("</recordInfo></opDetail>",maxConnectionsNewop);
					}
					if (!"".equals(StaticMethod.nullObject2String(map.get("maxInBytes"))) && !"".equals(StaticMethod.nullObject2String(map.get("maxInBytesNew")))) {
						String maxInBytesop = "<fieldInfo><fieldChName>最大下发流量</fieldChName><fieldEnName>MaxInBytes</fieldEnName><fieldContent>" + StaticMethod.nullObject2String(map.get("maxInBytes")) + "</fieldContent></fieldInfo></recordInfo></opDetail>";
						opDetail = opDetail.replace("</recordInfo></opDetail>",maxInBytesop);
						String maxInBytesNewop = "<fieldInfo><fieldChName>最大下发流量(新)</fieldChName><fieldEnName>MaxInBytesNew</fieldEnName><fieldContent>" + StaticMethod.nullObject2String(map.get("maxInBytesNew")) + "</fieldContent></fieldInfo></recordInfo></opDetail>";
						opDetail = opDetail.replace("</recordInfo></opDetail>",maxInBytesNewop);
					}
					if (!"".equals(StaticMethod.nullObject2String(map.get("maxOutBytes"))) && !"".equals(StaticMethod.nullObject2String(map.get("maxOutBytesNew")))) {
						String maxOutBytesop = "<fieldInfo><fieldChName>最大上行流量</fieldChName><fieldEnName>MaxOutBytes</fieldEnName><fieldContent>" + StaticMethod.nullObject2String(map.get("maxOutBytes")) + "</fieldContent></fieldInfo></recordInfo></opDetail>";
						opDetail = opDetail.replace("</recordInfo></opDetail>",maxOutBytesop);
						String maxOutBytesNewop = "<fieldInfo><fieldChName>最大上行流量(新)</fieldChName><fieldEnName>MaxOutBytesNew</fieldEnName><fieldContent>" + StaticMethod.nullObject2String(map.get("maxOutBytesNew")) + "</fieldContent></fieldInfo></recordInfo></opDetail>";
						opDetail = opDetail.replace("</recordInfo></opDetail>",maxOutBytesNewop);
					}
				}
				
				HashMap sheetMap = new HashMap();
				sheetMap.put("sheetType", "");
				sheetMap.put("serviceType", serviceType+"");
				sheetMap.put("serialNo", serialNo);
				sheetMap.put("serSupplier", "CRM");
				sheetMap.put("serCaller", serCaller);
				sheetMap.put("callerPwd", callerPwd);
				sheetMap.put("callTime", callTime);
				sheetMap.put("attachRef", attachRef);
				sheetMap.put("opPerson", "");
				sheetMap.put("opCorp", "");
				sheetMap.put("opDepart", "");
				sheetMap.put("opContact", "");
				sheetMap.put("opDepart", "");
				monitorMap.putAll(sheetMap);
				monitorMap.put("opDetail", opDetail);
				monitorMap.put("sheetKey", serialNo);
				
				DNMSProcessTransitLocator service = new DNMSProcessTransitLocator();
				DNMSProcessTransitPortType binding = (DNMSProcessTransitPortType)service.getDNMSProcessTransitHttpPort();
				System.out.println("lizhi:serialNo=" + serialNo + "jobNature=" + jobNature + "serCaller=" + serCaller + "callerPwd=" + callerPwd + "callTime=" + callTime + "opDetail=" + opDetail);
				result = binding.workSheetOpen(serialNo, jobNature, serCaller, callerPwd, callTime, "", "", "", "", "", opDetail);
				InterfaceDataMonitor monitor = new InterfaceDataMonitor();
				monitor.saveMonitor(monitorMap, result,  "crm", "auditHumTask");
			}
			
			if(result.endsWith("0"))
				return true;
			else
				return false;
		}catch(Exception err){
			err.printStackTrace();
			BocoLog.error(this, "调用接口失败：" + err.toString());
			InterfaceDataMonitor monitor = new InterfaceDataMonitor();
			monitor.saveMonitor(monitorMap, result,  "crm", "auditHumTask");
			return false;
		}
	}

}

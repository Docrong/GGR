// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BusinessImplementMethod.java

package com.boco.eoms.sheet.businessimplement.webapp.action;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.businessupport.interfaces.Irms.bo.IrmsResLoader;
import com.boco.eoms.businessupport.interfaces.transfer.client.TransferLoader;
import com.boco.eoms.businessupport.order.model.OrderSheet;
import com.boco.eoms.businessupport.order.service.IOrderSheetManager;
import com.boco.eoms.businessupport.order.webapp.action.OrderSheetMethod;
import com.boco.eoms.businessupport.product.model.*;
import com.boco.eoms.businessupport.product.service.*;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.commons.system.session.form.TawSystemSessionForm;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.*;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;
import com.boco.eoms.sheet.base.webapp.action.BaseSheet;
import com.boco.eoms.sheet.businessimplement.model.BusinessImplementLink;
import com.boco.eoms.sheet.businessimplement.model.BusinessImplementMain;
import com.boco.eoms.sheet.businessimplement.model.BusinessImplementTask;
import com.boco.eoms.sheet.businessimplement.service.IBusinessImplementFlowManager;
import com.boco.eoms.sheet.businessimplement.service.IBusinessImplementLinkManager;
import com.boco.eoms.sheet.businessimplement.service.IBusinessImplementTaskManager;
import com.boco.eoms.sheet.interfaceBase.util.InterfaceUtilProperties;
import com.boco.eoms.util.InterfaceUtil;
import com.boco.eoms.util.XmlDom4j;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.http.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class BusinessImplementMethod extends BaseSheet
{

	public BusinessImplementMethod()
	{
	}

	public String getPageColumnName()
	{
		return super.getPageColumnName() + ",toMorePhaseId@java.lang.String,supplier1Performer@java.lang.String,supplier1PerformerLeader@java.lang.String," + "supplier1PerformerType@java.lang.String,supplier2Performer@java.lang.String,supplier2PerformerLeader@java.lang.String,supplier2PerformerType@java.lang.String," + "supplier1CorrKey@java.lang.String,supplier2CorrKey@java.lang.String,supplier3Performer@java.lang.String,supplier3PerformerLeader@java.lang.String," + "supplier3PerformerType@java.lang.String,supplier3CorrKey@java.lang.String,supplier4Performer@java.lang.String,supplier4PerformerLeader@java.lang.String," + "supplier4PerformerType@java.lang.String,supplier4CorrKey@java.lang.String,supplier5Performer@java.lang.String,supplier5PerformerLeader@java.lang.String," + "supplier5PerformerType@java.lang.String,supplier5CorrKey@java.lang.String,supplier6Performer@java.lang.String,supplier6PerformerLeader@java.lang.String," + "supplier6PerformerType@java.lang.String,supplier6CorrKey@java.lang.String,supplier7Performer@java.lang.String,supplier7PerformerLeader@java.lang.String," + "supplier7PerformerType@java.lang.String,supplier7CorrKey@java.lang.String,gatherPhaseId@java.lang.String,gatherDealPerformer@java.lang.String," + "gatherDealPerformerLeader@java.lang.String,gatherDealPerformerType@java.lang.String,";
	}

	public HashMap getInterfaceObjMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		HashMap hashMap = new HashMap();
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		String operatName = StaticMethod.nullObject2String(request.getParameter("operateName"));
		HashMap sheetMap = new HashMap();
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("mainId"));
		BaseMain main = (BaseMain)getMainService().getMainObject().getClass().newInstance();
		if (!sheetKey.equals(""))
			sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"));
		if (!sheetKey.equals(""))
			main = getMainService().getSingleMainPO(sheetKey);
		sheetMap.put("main", main);
		sheetMap.put("link", getLinkService().getLinkObject().getClass().newInstance());
		sheetMap.put("operate", getPageColumnName());
		hashMap.put("selfSheet", sheetMap);
		return hashMap;
	}

	public void dealFlowEngineMap(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		super.dealFlowEngineMap(mapping, form, request, response);
		String cityA = "";
		String cityZ = "";
		String operateType = StaticMethod.nullObject2String(request.getParameter("operateType"));
		String phaseId = StaticMethod.nullObject2String(request.getParameter("phaseId"));
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		HashMap sheetMap = getFlowEngineMap();
		Map operate = (HashMap)sheetMap.get("operate");
		HashMap sessionMap = new HashMap();
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		sessionMap.put("userId", sessionform.getUserid());
		sessionMap.put("password", sessionform.getPassword());
		Map mainMap = (HashMap)sheetMap.get("main");
		Map linkMap = (HashMap)sheetMap.get("link");
		String specialtyType = StaticMethod.nullObject2String(mainMap
				.get("mainSpecifyType"));
		String dealperformers[] = StaticMethod.nullObject2String(operate.get("dealPerformer")).split(",");
		String toMorePhaseIds[] = StaticMethod.nullObject2String(operate.get("toMorePhaseId")).split(",");
		if (taskName.equals("reply") || taskName.equals("advice"))
		{
			Map link = (HashMap)sheetMap.get("link");
			link.put("id", "");
			sheetMap.put("link", link);
		}
		if (dealperformers.length > 1)
		{
			String corrKey = "";
			String tmp = "";
			for (int i = 0; i < dealperformers.length; i++)
			{
				tmp = UUIDHexGenerator.getInstance().getID();
				if (dealperformers.length == 1)
					corrKey = tmp;
				else
				if (corrKey.equals(""))
					corrKey = tmp;
				else
					corrKey = corrKey + "," + tmp;
			}

			System.out.println("corrKey" + corrKey);
			operate.put("extendKey1", corrKey);
			sheetMap.put("operate", operate);
		}
		if (toMorePhaseIds.length > 0)
		{
			String tmp = "";
			for (int i = 0; i < toMorePhaseIds.length; i++)
			{
				tmp = UUIDHexGenerator.getInstance().getID();
				if (toMorePhaseIds[i].equals("CityNetTask"))
					operate.put("supplier1CorrKey", tmp);
				else
				if (toMorePhaseIds[i].equals("ApnTask"))
					operate.put("supplier2CorrKey", tmp);
				else
				if (toMorePhaseIds[i].equals("GGSNTask"))
					operate.put("supplier5CorrKey", tmp);
				else
				if (toMorePhaseIds[i].equals("HLRTask"))
					operate.put("supplier6CorrKey", tmp);
				System.out.println("=======tmp=CorrKey==" + tmp);
			}

		}
		String isShowLanguage = StaticMethod.nullObject2String(request.getParameter("isShowLanguage"));
		if (operateType.equals("0") && !isShowLanguage.equals("yes"))
		{
			String orderId = StaticMethod.nullObject2String(request.getParameter("orderId"));
			if (orderId.length() > 0)
			{
				OrderSheet order = new OrderSheet();
				SheetBeanUtils.populateMap2Bean(order, request.getParameterMap());
				order.setId(orderId);
				order.setOrderBuisnessType(StaticMethod.nullObject2String(mainMap.get("mainSpecifyType")));
				order.setUrgentDegree(StaticMethod.nullObject2String(mainMap.get("mainArgument")));
				order.setCreatTime(new Date());
				order.setOrderType("1040104");
				IOrderSheetManager mgr = (IOrderSheetManager)ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");
				mgr.saveOrUpdate(order);
				mainMap.put("orderSheetId", orderId);
				InterfaceUtilProperties properties = new InterfaceUtilProperties();
				TrancePointMgr businessupportMgr = (TrancePointMgr) ApplicationContextHolder
				.getInstance().getBean("businessupportMgr");

				Object objectName = "";
				Map tranceMap = new HashMap();
				if (specialtyType != null
						&& !specialtyType.equals("")
						&& (specialtyType.equals("101230101")
								|| specialtyType.equals("101100101") || specialtyType
								.equals("101220101"))) {// GPRS????o?
					objectName = new GprsSpecialLine();
				} else if (specialtyType != null
						&& !specialtyType.equals("")
						&& (specialtyType.equals("101230102")
								|| specialtyType.equals("101100102") || specialtyType
								.equals("101220102"))) {// IP????o?
					objectName = new IPSpecialLine();
				} else if (specialtyType != null
						&& !specialtyType.equals("")
						&& (specialtyType.equals("101230103")
								|| specialtyType.equals("101100103") || specialtyType
								.equals("101220103"))) {// ???è??????o?
					objectName = new TransferSpecialLine();
				}
				String filePath = StaticMethod
						.getFilePathForUrl("classpath:config/resourceConfirm-crm.xml");
				List list = mgr.getSpecialLinesByType(orderId, objectName);
				for (int i = 0; list != null && i < list.size(); i++) {
					Map dataMap = SheetBeanUtils.bean2Map(list.get(i));
					cityA = StaticMethod.nullObject2String(dataMap.get("cityA"));
					cityZ = StaticMethod.nullObject2String(dataMap.get("cityZ"));
				}
			}
		}
		System.out.println("main=" + mainMap);
		if (mainMap != null && StaticMethod.nullObject2String(mainMap.get("mainSendSheetModule")).equalsIgnoreCase("1"))
		{
			String sendImmediately = StaticMethod.nullObject2String(XmlManage.getFile("/config/businessimplement-crm.xml").getProperty("base.SendImmediately"));
			if (!sendImmediately.equalsIgnoreCase("true"))
				if (taskName.equals("ImplementDealTask") && operateType.equals("4"))
				{
					operate.put("interfaceType", "withdrawWorkSheet");
					operate.put("methodType", "withdrawWorkSheet");
				} else
				if (operateType.equals("9"))
				{
					operate.put("interfaceType", "notifyWorkSheet");
					operate.put("methodType", "notifyWorkSheet");
				} else
				if (taskName.equals("ImplementDealTask") && operateType.equals("61"))
				{
					operate.put("interfaceType", "confirmWorkSheet");
					operate.put("methodType", "confirmWorkSheet");
				} else
				if (operateType.equals("76"))
				{
					operate.put("interfaceType", "replyWorkSheet");
					operate.put("methodType", "replyWorkSheet");
				}
		}
		String orderSheetId = StaticMethod.nullObject2String(mainMap.get("orderSheetId"));
		if (taskName.equals("TrasitionTask") && operateType.equals("61"))
		{
			String enable = XmlManage.getFile("/com/boco/eoms/businessupport/interfaces/transfer/config/transfer-config.xml").getProperty("enable");
			if (enable.equalsIgnoreCase("true"))
			{
				String result = "";
				Map map = new HashMap();
				map.putAll(mainMap);
				String mainSpecifyType = StaticMethod.nullObject2String(mainMap.get("mainSpecifyType"));
				if (orderSheetId != null && !orderSheetId.equals(""))
				{
					IOrderSheetManager iOrderSheetManager = (IOrderSheetManager)ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");
					OrderSheet orderSheet = iOrderSheetManager.getOrderSheet(orderSheetId);
					map.putAll(SheetBeanUtils.bean2Map(orderSheet));
				}
				List list = OrderSheetMethod.getSpecialLineList(orderSheetId, mainSpecifyType);
				String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, list, StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/businessupport/interfaces/transfer/config/transfer-config.xml"), "backupSheet");
				result = TransferLoader.irmsInitTraphInfos(sessionform.getUserid(), sessionform.getPassword(), opDetail);
				HashMap resultMap = new HashMap();
				InterfaceUtil interfaceUtil = new InterfaceUtil();
				resultMap = interfaceUtil.xmlElements(result, resultMap, "FieldContent");
				String isSuccess = StaticMethod.nullObject2String(resultMap.get("isSuccess"));
				if (!isSuccess.equals("0"))
				{
					String sheetId = StaticMethod.nullObject2String(resultMap.get("sheetId"));
					mainMap.put("mainCircuitSheetId", sheetId);
					sheetMap.put("main", mainMap);
				} else
				{
					String errorInfo = StaticMethod.nullObject2String(resultMap.get("errorInfo"));
					throw new Exception("\350\260\203\347\224\250\344\274\240\350\276\223\345\210\235\345\247\213\345\214\226\346\216\245\345\217\243\345\244\261\350\264\245\357\274\232" + errorInfo);
				}
			}
		}
		if (operateType.equals("97"))
		{
			String enable = XmlManage.getFile("/com/boco/eoms/businessupport/interfaces/transfer/config/transfer-config.xml").getProperty("enable");
			if (enable.equalsIgnoreCase("true"))
			{
				Map cirMap = new HashMap();
				cirMap.putAll(mainMap);
				String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(cirMap, StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/businessupport/interfaces/transfer/config/transfer-config.xml"), "irmsAttempArchive");
				String result = TransferLoader.irmsGetTraphInfos(sessionform.getUserid(), sessionform.getPassword(), opDetail);
				IGprsSpecialLineManager gprsMgr = (IGprsSpecialLineManager)ApplicationContextHolder.getInstance().getBean("IGprsSpecialLineManager");
				ITransferSpecialLineManager transMgr = (ITransferSpecialLineManager)ApplicationContextHolder.getInstance().getBean("ITransferSpecialLineManager");
				IIPSpecialLineManager ipMgr = (IIPSpecialLineManager)ApplicationContextHolder.getInstance().getBean("IIPSpecialLineManager");
				XmlDom4j dom = new XmlDom4j();
				List resultList = dom.getList(result);
				if (resultList != null)
				{
					String mainSpecifyType = StaticMethod.nullObject2String(mainMap.get("mainSpecifyType"));
					for (int i = 0; i < resultList.size(); i++)
					{
						Map tempResultMap = (Map)resultList.get(i);
						String isSuccess = StaticMethod.nullObject2String(tempResultMap.get("isSuccess"));
						if (!isSuccess.equals("0"))
						{
							String traphName = StaticMethod.nullObject2String(tempResultMap.get("traphName"));
							String traphCuid = StaticMethod.nullObject2String(tempResultMap.get("traphCuid"));
							if (traphCuid.equals(""))
								throw new Exception("\344\274\240\350\276\223\346\216\245\345\217\243\350\277\224\345\233\236\347\224\265\350\267\257\347\274\226\345\217\267\344\270\272\347\251\272\357\274\214\350\257\267\350\201\224\347\263\273\347\256\241\347\220\206\345\221\230");
							String zSwitchDevPort = StaticMethod.nullObject2String(tempResultMap.get("zSwitchDevPort"));
							String zSwitchDev = StaticMethod.nullObject2String(tempResultMap.get("zSwitchDev"));
							if (mainSpecifyType.equals("101230101"))
							{
								GprsSpecialLine line = gprsMgr.getSpecialLineByZPort(zSwitchDev, zSwitchDevPort);
								if (line == null)
									throw new Exception("\345\257\271\344\272\216Z\347\253\257\344\270\232\345\212\241\350\256\276\345\244\207\347\253\257\345\217\243'" + zSwitchDevPort + "',\346\234\252\346\211\276\345\210\260\345\257\271\345\272\224\347\232\204\344\270\223\347\272\277\357\274\214\346\227\240\346\263\225\344\277\235\345\255\230\347\224\265\350\267\257\345\220\215\347\247\260\343\200\202\350\257\267\347\241\256\350\256\244\344\274\240\350\276\223\350\277\224\345\233\236\347\232\204\347\253\231\347\202\271\345\220\215\347\247\260\346\230\257\345\220\246\346\255\243\347\241\256\357\274\201");
								line.setCircuitName(traphName);
								line.setCircuitSheetId(traphCuid);
								gprsMgr.saveOrUpdate(line);
							} else
							if (mainSpecifyType.equals("101230102"))
							{
								IPSpecialLine line = ipMgr.getSpecialLineByZPort(zSwitchDev, zSwitchDevPort);
								if (line == null)
									throw new Exception("\345\257\271\344\272\216Z\347\253\257\344\270\232\345\212\241\350\256\276\345\244\207\347\253\257\345\217\243'" + zSwitchDevPort + "',\346\234\252\346\211\276\345\210\260\345\257\271\345\272\224\347\232\204\344\270\223\347\272\277\357\274\214\346\227\240\346\263\225\344\277\235\345\255\230\347\224\265\350\267\257\345\220\215\347\247\260\343\200\202\350\257\267\347\241\256\350\256\244\344\274\240\350\276\223\350\277\224\345\233\236\347\232\204\347\253\231\347\202\271\345\220\215\347\247\260\346\230\257\345\220\246\346\255\243\347\241\256\357\274\201");
								line.setCircuitName(traphName);
								line.setCircuitSheetId(traphCuid);
								ipMgr.saveOrUpdate(line);
							} else
							if (mainSpecifyType.equals("101230103"))
							{
								TransferSpecialLine line = transMgr.getSpecialLineByZPort(zSwitchDev, zSwitchDevPort);
								if (line == null)
									throw new Exception("\345\257\271\344\272\216Z\347\253\257\344\270\232\345\212\241\350\256\276\345\244\207\347\253\257\345\217\243'" + zSwitchDevPort + "',\346\234\252\346\211\276\345\210\260\345\257\271\345\272\224\347\232\204\344\270\223\347\272\277\357\274\214\346\227\240\346\263\225\344\277\235\345\255\230\347\224\265\350\267\257\345\220\215\347\247\260\343\200\202\350\257\267\347\241\256\350\256\244\344\274\240\350\276\223\350\277\224\345\233\236\347\232\204\347\253\231\347\202\271\345\220\215\347\247\260\346\230\257\345\220\246\346\255\243\347\241\256\357\274\201");
								line.setCircuitName(traphName);
								line.setCircuitSheetId(traphCuid);
								transMgr.saveOrUpdate(line);
							}
						} else
						{
							String errorInfo = StaticMethod.nullObject2String(tempResultMap.get("errorInfo"));
							throw new Exception("\350\216\267\345\217\226\344\274\240\350\276\223\347\224\265\350\267\257\347\274\226\345\217\267\345\244\261\350\264\245\357\274\232" + errorInfo);
						}
					}

				}
			}
		}
		if (taskName.equals("CityNetTask") && operateType.equals("92"))
		{
			String enable = XmlManage.getFile("/com/boco/eoms/businessupport/interfaces/transfer/config/transfer-config.xml").getProperty("enable");
			String mainSpecifyType = StaticMethod.nullObject2String(mainMap.get("mainSpecifyType"));
			if (enable.equalsIgnoreCase("true") && mainSpecifyType.equals("101230103"))
			{
				String result = "";
				Map map = new HashMap();
				map.putAll(mainMap);
				map.putAll(linkMap);
				List childList = null;
				List list = OrderSheetMethod.getSpecialLineList(orderSheetId, mainSpecifyType);
				if (list != null && list.size() > 0)
					childList = list;
				String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, childList, StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/businessupport/interfaces/transfer/config/transfer-config.xml"), "backupSheet");
				result = TransferLoader.irmsInitTraphInfos(sessionform.getUserid(), sessionform.getPassword(), opDetail);
				HashMap resultMap = new HashMap();
				InterfaceUtil interfaceUtil = new InterfaceUtil();
				resultMap = interfaceUtil.xmlElements(result, resultMap, "FieldContent");
				String isSuccess = StaticMethod.nullObject2String(resultMap.get("isSuccess"));
				if (!isSuccess.equals("0"))
				{
					String sheetId = StaticMethod.nullObject2String(resultMap.get("sheetId"));
					mainMap.put("circuitDispatchNumber", sheetId);
					sheetMap.put("main", mainMap);
				} else
				{
					String errorInfo = StaticMethod.nullObject2String(resultMap.get("errorInfo"));
					throw new Exception("\350\260\203\347\224\250\344\274\240\350\276\223\346\216\245\345\217\243\345\244\261\350\264\245\357\274\232" + errorInfo);
				}
			}
		}
		if (taskName.equals("HoldTask") && operateType.equals("18"))
		{
			String enable = XmlManage.getFile("/com/boco/eoms/businessupport/interfaces/transfer/config/transfer-config.xml").getProperty("enable");
			String mainCircuitSheetId = StaticMethod.nullObject2String(mainMap.get("mainCircuitSheetId"));
			if (enable.equalsIgnoreCase("true") && !mainCircuitSheetId.equals(""))
			{
				String result = "";
				Map map = new HashMap();
				map.putAll(mainMap);
				map.putAll(linkMap);
				String opDetail = InterfaceUtilProperties.getInstance().getXmlFromMap(map, null, StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/businessupport/interfaces/transfer/config/transfer-config.xml"), "irmsAttempArchive");
				result = TransferLoader.irmsAttempArchive(sessionform.getUserid(), sessionform.getPassword(), opDetail);
				System.out.println("irmsAttempArchive result:" + result);
				XmlDom4j dom = new XmlDom4j();
				List resultList = dom.getList(result);
				if (resultList != null)
				{
					for (int i = 0; i < resultList.size(); i++)
					{
						Map tempResultMap = (Map)resultList.get(i);
						String isSuccess = StaticMethod.nullObject2String(tempResultMap.get("isSuccess"));
						if (isSuccess.equals("0"))
						{
							String errorInfo = StaticMethod.nullObject2String(tempResultMap.get("errorInfo"));
							throw new Exception("\344\274\240\350\276\223\345\275\222\346\241\243\345\244\261\350\264\245\357\274\232" + errorInfo);
						}
					}

				}
			}
			String mainSpecifyType = StaticMethod.nullObject2String(mainMap.get("mainSpecifyType"));
			orderSheetId = StaticMethod.nullObject2String(mainMap.get("orderSheetId"));
			List list = OrderSheetMethod.getSpecialLineList(orderSheetId, mainSpecifyType);
			enable = XmlManage.getFile("/com/boco/eoms/businessupport/interfaces/Irms/config/irmsres-config.xml").getProperty("enable");
			if (enable.equalsIgnoreCase("true"))
			{
				IOrderSheetManager mgr = (IOrderSheetManager)ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");
				OrderSheet ordersheet = mgr.getOrderSheet(orderSheetId);
				Map map = SheetBeanUtils.bean2Map(ordersheet);
				map.putAll(mainMap);
				IrmsResLoader.addEomsResByProdTypeBO(map, list);
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
			List list = itawsystemsubrolemanager.getTawSystemSubRoles(917);
			for (int i=0;i<list.size();i++) {
				TawSystemSubRole tawsystemsubrole = (TawSystemSubRole)list.get(i);
				subrolename = tawsystemsubrole.getSubRoleName();
				if (subrolename.indexOf(cityA)!=-1) {
					subroleid = tawsystemsubrole.getId();
				}
			}
			operate.put("phaseId", "ProjectDealTask");
			operate.put("dealPerformer", StaticMethod.nullObject2String(subroleid));
			operate.put("dealPerformerLeader", StaticMethod.nullObject2String(subroleid));
			operate.put("dealPerformerType", "subrole");
			this.createTaskAndLink(mainMap);
		}
		sheetMap.put("link", linkMap);
		sheetMap.put("main", mainMap);
		sheetMap.put("operate", operate);  
		
		setFlowEngineMap(sheetMap);
	}

	public void createTaskAndLink(Map main) {
		IBusinessImplementLinkManager linkservice = (IBusinessImplementLinkManager)ApplicationContextHolder.getInstance().getBean("iBusinessImplementLinkManager");
		IBusinessImplementTaskManager taskservice = (IBusinessImplementTaskManager)ApplicationContextHolder.getInstance().getBean("iBusinessImplementTaskManager");
		Calendar calendar = Calendar.getInstance();

		BusinessImplementLink linkbean = new BusinessImplementLink();
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
		linkbean.setActiveTemplateId("ImplementDealTask");
		try {
			linkservice.addLink(linkbean);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		

		BusinessImplementLink commitbean = new BusinessImplementLink();
		try {
			commitbean.setId(UUIDHexGenerator.getInstance().getID());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		commitbean.setMainId(StaticMethod.nullObject2String(main.get("id")));
		calendar.add(Calendar.SECOND, 2);
		commitbean.setOperateTime(calendar.getTime());
		commitbean.setOperateType(new Integer(91));
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
		commitbean.setActiveTemplateId("ImplementDealTask");
		commitbean.setLinkArugmentlevel("101010202");
		commitbean.setLinkNeedFinishTime(calendar.getTime());
		commitbean.setLinkSenderOpinition("请处理");
		try {
			linkservice.addLink(commitbean);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		BusinessImplementTask task = new BusinessImplementTask();
		try {
			task.setId(UUIDHexGenerator.getInstance().getID());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		task.setTaskName("ImplementDealTask");
		task.setTaskDisplayName("开通工单受理");
		task.setFlowName("BusinessImplementProcess");
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
	
	public Map getProcessOjbectAttribute()
	{
		Map attributeMap = new HashMap();
		attributeMap.put("dealPerformer", "dealPerformer");
		attributeMap.put("copyPerformer", "copyPerformer");
		attributeMap.put("auditPerformer", "auditPerformer");
		attributeMap.put("subAuditPerformer", "subAuditPerformer");
		attributeMap.put("objectName", "operate");
		return attributeMap;
	}

	public Map getParameterMap()
	{
		return getParameterMap();
	}

	public Map getAttachmentAttributeOfOjbect()
	{
		Map objectMap = new HashMap();
		List mainAttachmentAttributes = new ArrayList();
		mainAttachmentAttributes.add("sheetAccessories");
		List linkAttachmentAttributes = new ArrayList();
		linkAttachmentAttributes.add("nodeAccessories");
		objectMap.put("mainObject", mainAttachmentAttributes);
		objectMap.put("linkObject", linkAttachmentAttributes);
		return objectMap;
	}

	public void showInputDealPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		super.showInputDealPage(mapping, form, request, response);
		TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
		Object obj = request.getAttribute("task");
		if (obj != null)
		{
			ITask taskModel = (ITask)obj;
			Map objectMap = new HashMap();
			objectMap = SheetBeanUtils.bean2Map(taskModel);
			IBusinessImplementTaskManager ibusinessimplementTaskManager = (IBusinessImplementTaskManager)getTaskService();
			Integer tempCount = ibusinessimplementTaskManager.getCountOfBrother(sheetKey, StaticMethod.nullObject2String(objectMap.get("parentLevelId")));
			if (tempCount.intValue() > 1)
			{
				request.setAttribute("hasbrother", "hasbrother");
				System.out.println("=========hasbrother========");
			}
		}
		String taskName = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		if (taskName.equals("ProjectDealTask"))
		{
			HashMap sessionMap = new HashMap();
			sessionMap.put("userId", sessionform.getUserid());
			sessionMap.put("password", sessionform.getPassword());
			Map parameterValuemap = businessFlowService.getVariable(StaticMethod.null2String(request.getParameter("piid")), "control", sessionMap);
			String supplier1Performer = (String)parameterValuemap.get("supplier1Performer");
			String supplier2Performer = (String)parameterValuemap.get("supplier2Performer");
			request.setAttribute("supplier1Performer", supplier1Performer);
			request.setAttribute("supplier2Performer", supplier2Performer);
		}
		taskName = StaticMethod.nullObject2String(request.getParameter("taskName"));
		if (taskName.equals("HoldTask"))
		{
			BaseMain main = getMainService().getSingleMainPO(sheetKey);
			String parentSheetName = main.getParentSheetName();
			String parentSheetKey = main.getParentSheetId();
			if (parentSheetName != null && !parentSheetName.equals(""))
			{
				IMainService parentMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(parentSheetName);
				BaseMain parentMain = parentMainService.getSingleMainPO(parentSheetKey);
				String parentPhaseName = main.getParentPhaseName();
				System.out.println("@@parentPhaseName" + parentPhaseName);
				if (parentPhaseName.indexOf("@") != -1)
				{
					request.setAttribute("parentPiid", parentPhaseName.substring(parentPhaseName.indexOf("@") + 1));
					System.out.println("\345\233\236\350\260\203\357\274\232parentProcessId\357\274?" + parentPhaseName.substring(parentPhaseName.indexOf("@") + 1));
				} else
				{
					request.setAttribute("parentPiid", parentMain.getPiid());
				}
				request.setAttribute("parentMain", parentMain);
				request.setAttribute("parentProcessName", parentSheetName);
				System.out.println("circuitdiaptch \346\211\247\350\241\214\344\272\206\345\233\236\350\260?===========");
			}
			if (!sheetKey.equals(""))
			{
				BusinessImplementMain businessimplementMain = (BusinessImplementMain)getMainService().getSingleMainPO(sheetKey);
				String orderSheetId = businessimplementMain.getOrderSheetId();
				if (orderSheetId != null && !orderSheetId.equals(""))
				{
					IOrderSheetManager iOrderSheetManager = (IOrderSheetManager)ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");
					OrderSheet orderSheet = iOrderSheetManager.getOrderSheet(orderSheetId);
					request.setAttribute("orderSheet", orderSheet);
					request.setAttribute("orderSheetId", orderSheetId);
				}
			}
		}
	}

	public void performClaim(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		super.performClaim(mapping, form, request, response);
		String activeTemplateId = StaticMethod.nullObject2String(request.getParameter("activeTemplateId"));
		if (activeTemplateId.equals("ExcuteHumTask"))
		{
			System.out.println("\347\241\256\350\256\244\345\217\227\347\220\206");
			HashMap sessionMap = new HashMap();
			TawSystemSessionForm sessionform = (TawSystemSessionForm)request.getSession().getAttribute("sessionform");
			sessionMap.put("userId", sessionform.getUserid());
			sessionMap.put("password", sessionform.getPassword());
			String taskId = StaticMethod.nullObject2String(request.getParameter("TKID"));
			ITask task = getTaskService().getSinglePO(taskId);
			IBusinessImplementFlowManager service = (IBusinessImplementFlowManager)getBusinessFlowService();
			service.setProcessInstanceCustomProperty(task.getProcessId(), "ifAccepted", "true", sessionMap);
		}
	}

	public void showAtomDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		showDetailPageAtom(mapping, form, request, response);
		BusinessImplementMain mainObject = (BusinessImplementMain)request.getAttribute("sheetMain");
		BusinessImplementTask task = (BusinessImplementTask)request.getAttribute("task");
		String isAccept = null;
		if (task.getTaskStatus().equals("2"))
			isAccept = "0";
		if (task.getTaskStatus().equals("8"))
			isAccept = "1";
		String asXML = showAtomDetail(mainObject, task, isAccept, request);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(asXML);
	}

	public static String showAtomDetail(BusinessImplementMain mainObject, ITask task, String isAccept, HttpServletRequest request)
		throws DictServiceException
	{
		return null;
	}

	public String getProcessTemplateName()
	{
		return "businessimplementMainFlowProcess";
	}

	public String getSheetAttachCode()
	{
		return "businessimplement";
	}

	public Map initMap(Map map, List attach, String type)
		throws Exception
	{
		try{
		String configPath = "config/businessimplement-interface-config.xml";
		map = loadDefaultMap(map, configPath, type);
		if (type.equals("newWorkSheet"))
		{
			String NeTypeCode = StaticMethod.nullObject2String(map.get("netType"));
			String mainNetSortThree = "";
			if (NeTypeCode.length() > 0)
			{
				String rootId = XmlManage.getFile("/config/businessimplement-interface-config.xml").getProperty("base.rootNeTypeId");
				ITawSystemDictTypeManager dictMgr = (ITawSystemDictTypeManager)ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
				mainNetSortThree = dictMgr.getDictIdByDictCode(rootId, NeTypeCode);
				if (mainNetSortThree == null || mainNetSortThree.length() == 0)
					System.out.println("\346\262\241\346\234\211\346\211\276\345\210\260\346\230\240\345\260\204\347\232\204\347\275\221\347\273\234\345\210\206\347\261\273");
				else
					try
					{
						System.out.println("mainNetSortThree=" + mainNetSortThree);
						TawSystemDictType dict3 = dictMgr.getDictByDictId(mainNetSortThree);
						if (dict3 != null)
						{
							map.put("mainNetSort3", mainNetSortThree);
							String pId = dict3.getParentDictId();
							TawSystemDictType dict2 = dictMgr.getDictByDictId(pId);
							if (dict2 != null)
							{
								map.put("mainNetSort2", dict2.getDictId());
								pId = dict2.getParentDictId();
								if (!pId.equals("-1"))
								{
									TawSystemDictType dict1 = dictMgr.getDictByDictId(pId);
									if (dict1 != null)
										map.put("mainNetSort1", dict1.getDictCode());
								}
							}
						} else
						{
							System.out.println("dict3 is null");
						}
					}
					catch (Exception err)
					{
						System.out.println("\346\262\241\346\234\211\346\211\276\345\210\260\346\230\240\345\260\204\347\232\204\347\275\221\347\273\234\345\210\206\347\261\273");
					}
			}
		}
		return map;
	}catch(Exception err){
		err.printStackTrace();
		throw err;
	}
	}

	public void showDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		super.showDetailPage(mapping, form, request, response);
		String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"), "");
		if (!preLinkId.equals(""))
			request.setAttribute("preLink", getLinkService().getSingleLinkPO(preLinkId));
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
		if (!sheetKey.equals(""))
		{
			BusinessImplementMain businessimplementMain = (BusinessImplementMain)getMainService().getSingleMainPO(sheetKey);
			String orderSheetId = businessimplementMain.getOrderSheetId();
			if (orderSheetId != null && !orderSheetId.equals(""))
			{
				IOrderSheetManager iOrderSheetManager = (IOrderSheetManager)ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");
				OrderSheet orderSheet = iOrderSheetManager.getOrderSheet(orderSheetId);
				System.out.println("@@@@orderSheetId" + orderSheetId);
				request.setAttribute("orderSheet", orderSheet);
				request.setAttribute("orderSheetId", orderSheetId);
			}
		}
	}

	public void showMainDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		throws Exception
	{
		super.showMainDetailPage(mapping, form, request, response);
		String preLinkId = StaticMethod.nullObject2String(request.getParameter("preLinkId"), "");
		if (!preLinkId.equals(""))
			request.setAttribute("preLink", getLinkService().getSingleLinkPO(preLinkId));
		String sheetKey = StaticMethod.nullObject2String(request.getParameter("sheetKey"), "");
		if (!sheetKey.equals(""))
		{
			BusinessImplementMain businessimplementMain = (BusinessImplementMain)getMainService().getSingleMainPO(sheetKey);
			String orderSheetId = businessimplementMain.getOrderSheetId();
			if (orderSheetId != null && !orderSheetId.equals(""))
			{
				IOrderSheetManager iOrderSheetManager = (IOrderSheetManager)ApplicationContextHolder.getInstance().getBean("IOrderSheetManager");
				OrderSheet orderSheet = iOrderSheetManager.getOrderSheet(orderSheetId);
				request.setAttribute("orderSheet", orderSheet);
				request.setAttribute("orderSheetId", orderSheetId);
			}
		}
	}
}

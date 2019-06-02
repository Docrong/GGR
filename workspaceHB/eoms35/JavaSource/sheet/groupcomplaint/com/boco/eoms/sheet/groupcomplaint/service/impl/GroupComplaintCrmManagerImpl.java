// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GroupComplaintCrmManagerImpl.java

package com.boco.eoms.sheet.groupcomplaint.service.impl;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.base.util.UUIDHexGenerator;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.util.xml.XMLProperties;
import com.boco.eoms.commons.util.xml.XmlManage;
import com.boco.eoms.groupcomplantts.TSfaultInfo_yiyangLocator;
import com.boco.eoms.groupcomplantts.TSfaultInfo_yiyangSoap_PortType;
import com.boco.eoms.sheet.base.adapter.service.wps.WPSEngineServiceMethod;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.IDownLoadSheetAccessoriesService;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.webapp.action.IBaseSheet;
import com.boco.eoms.sheet.commonfault.model.CommonFaultLink;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultLinkManager;
import com.boco.eoms.sheet.commonfault.service.ICommonFaultTaskManager;
import com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask;
import com.boco.eoms.sheet.groupcheck.webapp.action.GroupCheckMethod;
import com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintLink;
import com.boco.eoms.sheet.groupcomplaint.model.GroupComplaintMain;
import com.boco.eoms.sheet.groupcomplaint.qo.EMOSAutomSearchServiceImpl;
import com.boco.eoms.sheet.groupcomplaint.qo.EMOSAutomSearchServiceImplServiceLocator;
import com.boco.eoms.sheet.groupcomplaint.service.IGroupComplaintLinkManager;
import com.boco.eoms.sheet.groupcomplaint.service.IGroupComplaintTaskManager;
import com.boco.eoms.sheet.groupcomplaint.task.impl.GroupComplaintTask;
import com.boco.eoms.sheet.groupcomplaint.zhzw.GroupComplaintZHZWService;
import com.boco.eoms.sheet.interfaceBase.service.IInterfaceServiceManage;
import com.boco.eoms.sheet.interfaceBase.service.impl.InterfaceServiceManageAbstract;
import com.boco.eoms.sheet.limit.model.LevelLimit;
import com.boco.eoms.sheet.limit.model.StepLimit;
import com.boco.eoms.sheet.limit.service.ISheetDealLimitManager;
import java.io.PrintStream;
import java.util.*;

import org.apache.axis.client.Call;  
import org.apache.axis.client.Service; 
import javax.xml.namespace.QName; 

public class GroupComplaintCrmManagerImpl extends InterfaceServiceManageAbstract
	implements IInterfaceServiceManage
{

	public GroupComplaintCrmManagerImpl()
	{
	}

	public String getLinkBeanId()
	{
		return "iGroupComplaintLinkManager";
	}

	public String getMainBeanId()
	{
		return "iGroupComplaintMainManager";
	}

	public String getOperateName()
	{
		return "newWorksheet";
	}

	public String getProcessTemplateName()
	{
		return "GroupComplaintProcess";
	}

	public String getSendUser(Map map)
	{
		String userId = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.InterfaceUser"));
		return userId;
	}

	public String getTaskBeanId()
	{
		return "iGroupComplaintTaskManager";
	}

	private Map getlimit(HashMap specialsMap, Map map)
		throws Exception
	{
		ISheetDealLimitManager mgr = (ISheetDealLimitManager)ApplicationContextHolder.getInstance().getBean("iSheetDealLimitManager");
		List sheetLimitList = mgr.getlevelLimitBySpecials(specialsMap);
		System.out.println("----groupcomplaint---size -" + sheetLimitList.size());
		Date sheetCompleteLimit = StaticMethod.nullObject2Timestamp(map.get("sheetCompleteLimit"));
		if (sheetLimitList != null && sheetLimitList.size() > 0)
		{
			LevelLimit levelLimit = (LevelLimit)sheetLimitList.get(0);
			String leveid = levelLimit.getId();
			System.out.println("--groupcomplaint---completeLimit-" + sheetCompleteLimit.toString());
			ISheetDealLimitManager mgrsheet = (ISheetDealLimitManager)ApplicationContextHolder.getInstance().getBean("iSheetDealLimitManager");
			List listsheet = mgrsheet.getStepLimitByLevel(leveid);
			System.out.println("--groupcomplaint--leveid-" + leveid + "-listsize-" + listsheet.size());
			Calendar calendarsheet = Calendar.getInstance();
			for (int i = 0; i < listsheet.size(); i++)
			{
				StepLimit stepLimit = (StepLimit)listsheet.get(i);
				if (stepLimit.getTaskName() != null && stepLimit.getTaskName().equals("FirstExcuteHumTask"))
				{
					calendarsheet.add(12, StaticMethod.null2int(stepLimit.getCompleteLimit()));
					map.put("mainCompleteLimitT1", calendarsheet.getTime());
				}
				if (stepLimit.getTaskName() != null && stepLimit.getTaskName().equals("SecondExcuteHumTask"))
				{
					calendarsheet.add(12, StaticMethod.null2int(stepLimit.getCompleteLimit()));
					map.put("mainCompleteLimitT2", calendarsheet.getTime());
				}
			}

		} else
		{
			Calendar calendarlimit = Calendar.getInstance();
			calendarlimit.setTime(sheetCompleteLimit);
			calendarlimit.add(12, -120);
			map.put("mainCompleteLimitT1", calendarlimit.getTime());
			map.put("mainCompleteLimitT2", calendarlimit.getTime());
		}
		String endtime = StaticMethod.nullObject2String((String)map.get("dealTime2"));
		map.put("mainCompleteLimitT2", endtime);
		return map;
	}

	public Map initMap(Map map, List attach, String type)
		throws Exception
	{
		map = loadDefaultMap(map, "config/groupComplaint-crm.xml", type);
		if (type.equals("newWorkSheet") || type.equals("renewWorkSheet"))
		{
			String sendRoleId = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.SendRoleId"));
			map.put("sendRoleId", sendRoleId);
			WPSEngineServiceMethod sm = new WPSEngineServiceMethod();
			if (type.equals("newWorkSheet"))
			{	
				//生成工单流水号
				IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(getMainBeanId());
				String sheetId = StaticMethod.nullObject2String(map.get("sheetId"));
				if("".equals(sheetId)){
					sheetId = StaticMethod.nullObject2String(iMainService.getSheetId());
					System.out.println("获取工单流水号sheetId="+sheetId);
					map.put("sheetId", sheetId);
				}
				String bigRole = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.AcceptGroupId"));
				String toDeptId = StaticMethod.nullObject2String(map.get("toDeptId"));
				String subRoleId = "";
				TawSystemSubRole subRole = sm.getMatchRoles("GroupComplaintProcess", toDeptId, bigRole, map);
				if (subRole == null || subRole.getId() == null || subRole.getId().length() == 0)
				{
					System.out.println("未找到匹配角色，使用默认角色");
					subRoleId = XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.AcceptRoleId");
				} else
				{
					subRoleId = subRole.getId();
				}
				map = sm.setAcceptRole(subRoleId, map);
				
//				判断是否为省直管集团客户
				String customNo = StaticMethod.nullObject2String(map.get("customNo"));
				String customNoSql = "SELECT * FROM groupcomplaint_pipe WHERE customNo = '"+customNo+"'";
				IDownLoadSheetAccessoriesService sqlMgr= (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
				List customNoList = (List) sqlMgr.getSheetAccessoriesList(customNoSql);
				if(customNoList != null && customNoList.size()>0){
					String titletem = StaticMethod.nullObject2String(map.get("title"));
					titletem = "（省直管集团客户）"+titletem;
					map.put("title", titletem);
				}
				
			} else
			{
				map = sm.setAcceptRole("", map);
				String sheetId = StaticMethod.nullObject2String(map.get("serialNo"));
				if (sheetId == null || sheetId.equals(""))
					throw new Exception("sheetId为空");
				BaseMain main = null;
				IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(getMainBeanId());
				ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean(getTaskBeanId());
				List list = iMainService.getMainListByParentSheetId(sheetId);
				if (list.size() > 0)
				{
					boolean b = false;
					main = (BaseMain)list.get(0);
					List taskList = iTaskService.getCurrentUndoTask(main.getId());
					if (taskList != null)
					{
						for (int i = 0; i < taskList.size(); i++)
						{
							ITask task = (ITask)taskList.get(i);
							if (!task.getTaskOwner().equals(main.getSendRoleId()) && !task.getTaskOwner().equals(main.getSendUserId()) && !task.getTaskOwner().equals(main.getSendDeptId()))
								continue;
							b = true;
							break;
						}

					}
					if (!b)
						throw new Exception("工单未被驳回，不能重派");
				} else
				{
					throw new Exception("没找到sheetNo＝" + sheetId + "对应的工单");
				}
			}
			String serviceType = StaticMethod.nullObject2String(map.get("bservType"));
			if (serviceType.equals("2") || serviceType.equals("3"))
			{
				if (map.get("enterpriseCode") == null || map.get("enterpriseCode").equals(""))
					throw new Exception("当业务类型为" + serviceType + "时，企业代码（enterpriseCode）不能为空");
				if (map.get("serverCode") == null || map.get("serverCode").equals(""))
					throw new Exception("当业务类型为" + serviceType + "时，服务代码（serverCode）不能为空");
			}
			if (serviceType.equals("1") && (map.get("APNName") == null || map.get("APNName").equals("")))
				throw new Exception("当业务类型为" + serviceType + "时，APN名称（APNName）不能为空");
			if (serviceType.equals("4") && (map.get("circuitCode") == null || map.get("circuitCode").equals("")))
				throw new Exception("当业务类型为" + serviceType + "时，传输专线电路代号（circuitCode）不能为空");
			String complaintType = StaticMethod.nullObject2String(map.get("complaintType"));
			System.out.println("complaintType=" + complaintType);
			map = setComplaintType(complaintType, map);
			String type1 = StaticMethod.nullObject2String(map.get("bservType"));
			System.out.println("groupComplaint------bservType:" + type1);
			HashMap specialsMap = new HashMap();
			specialsMap.put("flowName", "GroupComplaintProcess");
			specialsMap.put("specialty1", type1);
			map = getlimit(specialsMap, map);
			
			//调用集中故障接口
			map = setMainIfType2(map);
			//t1自动派单t2
			map = t1tot2(map);
			
		} else
		if (type.equals("untreadWorkSheet"))
		{
			String sheetId = StaticMethod.nullObject2String(map.get("serialNo"));
			if (sheetId == null || sheetId.equals(""))
				throw new Exception("sheetId为空");
			BaseMain main = null;
			IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(getMainBeanId());
			ITaskService iTaskService = (ITaskService)ApplicationContextHolder.getInstance().getBean(getTaskBeanId());
			List list = iMainService.getMainListByParentSheetId(sheetId);
			if (list.size() > 0)
			{
				boolean b = false;
				main = (BaseMain)list.get(0);
				List taskList = iTaskService.getCurrentUndoTask(main.getId());
				if (taskList != null)
				{
					for (int i = 0; i < taskList.size(); i++)
					{
						ITask task = (ITask)taskList.get(i);
						if (!task.getTaskOwner().equals(main.getSendRoleId()) && !task.getTaskOwner().equals(main.getSendUserId()) && !task.getTaskOwner().equals(main.getSendDeptId()))
							continue;
						b = true;
						break;
					}

				}
				if (!b)
					throw new Exception("工单未回复，不能退回");
			} else
			{
				throw new Exception("没找到sheetNo＝" + sheetId + "对应的工单");
			}
		}else if (type.equals("checkinWorkSheet"))//归档
			{
			System.out.println("进入归档！！！！");
			map = setMainIfType("1",map);
			System.out.println("归档！！！！");
		     
			}
		return map;
	}
	
	
	
	private Map setMainIfType2(Map map) throws Exception{
		String title = StaticMethod.nullObject2String(map.get("title"));
		String complaintTime = StaticMethod.nullObject2String(map.get("complaintTime"));
		String indexFlag = "";
		String sheetId = StaticMethod.nullObject2String(map.get("sheetId"));
		String complaintDesc = StaticMethod.nullObject2String(map.get("complaintDesc"));
		System.out.println("title22=="+title+"====sheetId22="+sheetId);
		System.out.println("complaintDesc22=="+complaintDesc);
		//得到 产品实例标识（13位数字）
		if(complaintDesc.indexOf("产品实例标识")>=0 && complaintDesc.indexOf("产品实例标识")+20 <= complaintDesc.length()){
			indexFlag = complaintDesc.substring(complaintDesc.indexOf("产品实例标识")+7, complaintDesc.indexOf("产品实例标识")+20);
			System.out.println("===input1====indexFlag22="+indexFlag);
			if(!indexFlag.matches("[0-9]+")){
				indexFlag = "";
			}
			System.out.println("===input2====indexFlag22="+indexFlag);
		}
		if(!"".equals(indexFlag)){
//			报文
			String opt =
				"<opDetail>"+								
			       "<recordInfo>"+							
		              "<fieldInfo>"+	
		              
		              		"<fieldChName>工单流水号</fieldChName>"+	
	              			"<fieldEnName>complaint_sheetId</fieldEnName>"+	
	              			"<fieldContent><![CDATA["+sheetId+"]]></fieldContent>"+
	              		"</fieldInfo>"+
		                
	              		"<fieldInfo>"+
		              		"<fieldChName>电路编号</fieldChName>"+	
		              		"<fieldEnName>elect_Code</fieldEnName>"+	
		              		"<fieldContent></fieldContent>"+
		              		"</fieldInfo>"+
		              		
		              		"<fieldInfo>"+	
		              		"<fieldChName>产品实例标识</fieldChName>"+	
		              		"<fieldEnName>product_identification</fieldEnName>"+	
		              		"<fieldContent><![CDATA["+indexFlag+"]]></fieldContent>"+
		              		"</fieldInfo>"+
		              		
		              		"<fieldInfo>"+
		              		"<fieldChName>投诉时间</fieldChName>"+	
		              		"<fieldEnName>Complaint_Time</fieldEnName>"+	
		              		"<fieldContent><![CDATA["+complaintTime+"]]></fieldContent>"+
		              		"</fieldInfo>"+
		              		
		              		"<fieldInfo>"+
		              		"<fieldChName>查询类型</fieldChName>"+	
		              		"<fieldEnName>group_Type</fieldEnName>"+	
		              		"<fieldContent>2</fieldContent>"+
		              		"</fieldInfo>"+
		              		
		              		"<fieldInfo>"+
		              		"<fieldChName>工单标题</fieldChName>"+	
		              		"<fieldEnName>complaint_sheetTitle</fieldEnName>"+	
		              		"<fieldContent>"+title+"</fieldContent>"+
		              		"</fieldInfo>"+
		              		
		              		"<fieldInfo>"+
		              		"<fieldChName>工单详情</fieldChName>"+	
		              		"<fieldEnName>complaint_sheetDetail</fieldEnName>"+	
		              		"<fieldContent><![CDATA["+complaintDesc+"]]></fieldContent>"+
		              		"</fieldInfo>"+
		            "</recordInfo>"+
		        "</opDetail>";
			String returnOpe = "";
			System.out.println("opt==="+opt);
			try {
				EMOSAutomSearchServiceImplServiceLocator service = new EMOSAutomSearchServiceImplServiceLocator();
				EMOSAutomSearchServiceImpl binding = service.getEMOSAutomSearchServiceImplPort();
				System.out.println("开始调用接口22222");
				returnOpe = binding.isConfirmSheet(opt);
				System.out.println("调用接口结束22222");
				System.out.println("returnOpe==lyg2=="+returnOpe);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("调用接口错误2222");
			}
			
			if(returnOpe != null && !"".equals(returnOpe)){
				if(returnOpe.indexOf("Status=")>=0 && returnOpe.indexOf("Status=")+8 <= returnOpe.length()){
					String inti = returnOpe.substring(returnOpe.indexOf("Status=")+7, returnOpe.indexOf("Status=")+8);
					if("0".equals(inti)){
						map.put("mainIfMatch", "是");
					}else{
						map.put("mainIfMatch", "否");
					}
				}else{
					System.out.println("返回状态异常2");
				}
			}
		}else{
			System.out.println("条件为空");
//			不需要调用接口，“是否管理告警”自动填入： 否
			map.put("mainIfMatch", "否");
		}
		return map;
	}
	
	
	private Map setMainIfType(String groupType,Map map) throws Exception{
		String serialNo = StaticMethod.nullObject2String(map.get("serialNo"));
		System.out.println("serialNo==="+serialNo);
		IMainService iMainService = (IMainService)ApplicationContextHolder.getInstance().getBean(getMainBeanId());
		List list = iMainService.getMainListByParentSheetId(serialNo);
		if (list.size() > 0)
		{
			GroupComplaintMain main = (GroupComplaintMain)list.get(0);
			String mainId = main.getId();
			String sheetId = main.getSheetId();
			Map returnMap = (Map)getComplaintById(mainId);
			//String flag = "0";//没有得到值
			String flag  = StaticMethod.nullObject2String(returnMap.get("flag"));
			if("1".equals(flag)){
				//调用接口
				//电路代号
				String electricCode  = StaticMethod.nullObject2String(returnMap.get("electricCode"));
				//产品实例标识
				String indexFlag  = StaticMethod.nullObject2String(returnMap.get("indexFlag"));
				//投诉时间
				String complaintTime  = StaticMethod.nullObject2String(returnMap.get("complaintTime"));
				
				String title  = StaticMethod.nullObject2String(returnMap.get("title"));
				String complaintDesc  = StaticMethod.nullObject2String(returnMap.get("complaintDesc"));
				
				//报文
				String opt =
					"<opDetail>"+								
				       "<recordInfo>"+							
			              "<fieldInfo>"+	
			              
			              		"<fieldChName>工单流水号</fieldChName>"+	
		              			"<fieldEnName>complaint_sheetId</fieldEnName>"+	
		              			"<fieldContent><![CDATA["+sheetId+"]]></fieldContent>"+
		              		"</fieldInfo>"+
			                
		              		"<fieldInfo>"+
			              		"<fieldChName>电路编号</fieldChName>"+	
			              		"<fieldEnName>elect_Code</fieldEnName>"+	
			              		"<fieldContent><![CDATA["+electricCode+"]]></fieldContent>"+
			              		"</fieldInfo>"+
			              		
			              		"<fieldInfo>"+	
			              		"<fieldChName>产品实例标识</fieldChName>"+	
			              		"<fieldEnName>product_identification</fieldEnName>"+	
			              		"<fieldContent><![CDATA["+indexFlag+"]]></fieldContent>"+
			              		"</fieldInfo>"+
			              		
			              		"<fieldInfo>"+
			              		"<fieldChName>投诉时间</fieldChName>"+	
			              		"<fieldEnName>Complaint_Time</fieldEnName>"+	
			              		"<fieldContent><![CDATA["+complaintTime+"]]></fieldContent>"+
			              		"</fieldInfo>"+
			              		
			              		"<fieldInfo>"+
			              		"<fieldChName>查询类型</fieldChName>"+	
			              		"<fieldEnName>group_Type</fieldEnName>"+	
			              		"<fieldContent>"+groupType+"</fieldContent>"+
			              		"</fieldInfo>"+
			              		
			              		"<fieldInfo>"+
			              		"<fieldChName>工单标题</fieldChName>"+	
			              		"<fieldEnName>complaint_sheetTitle</fieldEnName>"+	
			              		"<fieldContent>"+title+"</fieldContent>"+
			              		"</fieldInfo>"+
			              		
			              		"<fieldInfo>"+
			              		"<fieldChName>工单详情</fieldChName>"+	
			              		"<fieldEnName>complaint_sheetDetail</fieldEnName>"+	
			              		"<fieldContent>"+complaintDesc+"</fieldContent>"+
			              		"</fieldInfo>"+
			            "</recordInfo>"+
			        "</opDetail>";

//				String returnOpe = getInterfaceDate(opt);
				
				EMOSAutomSearchServiceImplServiceLocator service = new EMOSAutomSearchServiceImplServiceLocator();
				EMOSAutomSearchServiceImpl binding = service.getEMOSAutomSearchServiceImplPort();
				String returnOpe = "";
				try {
					System.out.println("开始调用接口1111");
					returnOpe = binding.isConfirmSheet(opt);
					System.out.println("调用接口结束1111");
				} catch (Exception e) {
					// TODO: handle exceptiono
					e.printStackTrace();
					System.out.println("1111lyg");
				}
				System.out.println("returnOpe==lyg2=="+returnOpe);
				
				//得到返回状态
				if("1".equals(groupType)){
					if(returnOpe.indexOf("Status=")>=0 && returnOpe.indexOf("Status=")+8 <= returnOpe.length()){
						String inti = returnOpe.substring(returnOpe.indexOf("Status=")+7, returnOpe.indexOf("Status=")+8);
						if("0".equals(inti)){
							map.put("mainIfSelf", "是");
						}else{
							map.put("mainIfSelf", "否");
						}
					}else{
						System.out.println("返回状态异常1");
					}
				}else if("2".equals(groupType)){
					if(returnOpe.indexOf("Status=")>=0 && returnOpe.indexOf("Status=")+8 <= returnOpe.length()){
						String inti = returnOpe.substring(returnOpe.indexOf("Status=")+7, returnOpe.indexOf("Status=")+8);
						if("0".equals(inti)){
							map.put("mainIfMatch", "是");
						}else{
							map.put("mainIfMatch", "否");
						}
					}else{
						System.out.println("返回状态异常22");
					}
				}
				
			}else if("2".equals(flag)) {//产品实例和电路单号都为空
				//不需要调用接口，“是否自动发现”自动填入： 否
				map.put("mainIfSelf", "否");
				//触发集团核查派单
				GroupCheckMethod method = new GroupCheckMethod();
				sheetId = main.getSheetId();
				System.out.println("sheetId=lyg="+sheetId);
				String title = StaticMethod.nullObject2String(main.getTitle());
				System.out.println("title=lyg="+title);
//				电路代号
				String electricCode  = StaticMethod.nullObject2String(returnMap.get("electricCode"));
				System.out.println("electricCode=lyg="+electricCode);
				//产品实例标识
				String indexFlag  = StaticMethod.nullObject2String(returnMap.get("indexFlag"));
				System.out.println("indexFlag=lyg="+indexFlag);
				//投诉时间
				String complaintTime  = StaticMethod.nullObject2String(returnMap.get("complaintTime"));
				System.out.println("complaintTime=lyg="+complaintTime);
				String operateroleid  = StaticMethod.nullObject2String(returnMap.get("operateroleid"));
				System.out.println("operateroleid=lyg="+operateroleid);
				if(complaintTime.indexOf(".")>0){
					complaintTime = complaintTime.substring(0, complaintTime.indexOf("."));
				}
				//所属地市
			    String cityName = StaticMethod.nullObject2String(main.getCityName());
			    System.out.println("cityName=lyg="+cityName);
				HashMap flowMap = new HashMap();
				
				flowMap.put("title", title);
				flowMap.put("mainGroupSheetId", sheetId);//集团投诉工单
				flowMap.put("mainProductIns", indexFlag);//产品实例标识
				flowMap.put("mainCircuitCode", electricCode);//电路代号
				flowMap.put("mainUserAffilia", cityName);//用户归属地
				flowMap.put("mainComplaintTime", complaintTime);//用户归属地
				flowMap.put("operateroleid", operateroleid);
				
				
				
				try {
					method.getNewFlow(flowMap);
				} catch (Exception e) {
					System.out.println("产品实例标识和电路代号为空 调用集团核查工单 报错！！");
					e.printStackTrace();
				}
			}
			
		} else
		{
			throw new Exception("没找到serialNo＝" + serialNo + "对应的工单");
		}
	
		
		return map;
	}

	private Map setComplaintType(String complaintType, Map map)
	{
		try
		{
			ITawSystemDictTypeManager dictMgr = (ITawSystemDictTypeManager)ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
			if (complaintType.length() > 0)
			{
				int len = 2;
				String type = "";
				String parentDictId = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.ComplaintTypeDictId"));
				if("1001".equals(complaintType.substring(0,4))){
					for (int i = 1; type.length() < complaintType.length(); i++)
					{
						if (complaintType.length() < len)
							break;
						type = complaintType.substring(0, len);
						TawSystemDictType dict = dictMgr.getDictByDictType(type, parentDictId);
						if (dict != null && dict.getDictId() != null)
						{
							String dictId = dict.getDictId();
							System.out.println("complaintType" + i + "=" + dictId);
							if (i == 3)
								map.put("complaintType", dictId);
							else
								map.put("complaintType" + i, dictId);
							parentDictId = dictId;
						} else
						if (i == 1)
						{
							String complaintType1 = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.ComplaintType1"));
							String complaintType2 = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.ComplaintType2"));
							map.put("complaintType1", complaintType1);
							map.put("complaintType2", complaintType2);
							break;
						}
						len += 2;
					}
				}else{
					for (int i = 1; i * 2 <= complaintType.length(); i++)
					{
						System.out.println("parentDictId" + i + "=" + parentDictId);
						String subcode = complaintType.substring(0, i * 2);
						System.out.println("subcode" + i + "=" + subcode);
						TawSystemDictType dict = dictMgr.getDictByDictType(subcode, parentDictId);
						if (dict != null && dict.getDictId() != null)
						{
							String dictId = dict.getDictId();
							System.out.println("complaintType" + i + "=" + dictId);
							if (i == 3)
								map.put("complaintType", dictId);
							else
								map.put("complaintType" + i, dictId);
							parentDictId = dictId;
							continue;
						}
						if (i != 1)
							continue;
						String complaintType1 = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.ComplaintType1"));
						String complaintType2 = StaticMethod.nullObject2String(XmlManage.getFile("/config/complaint-crm.xml").getProperty("base.ComplaintType2"));
						map.put("complaintType1", complaintType1);
						map.put("complaintType2", complaintType2);
						break;
					}
				}
				

			}
		}
		catch (Exception err)
		{
			System.out.println("没有找到映射的投诉分类");
			err.printStackTrace();
		}
		return map;
	}

	public String getSheetAttachCode()
	{
		return "groupcomplaint";
	}
	
	public Map getComplaintById(String mainId){
		//electricCode 电路代号link表   complaintDesc 内容信息 complaintTime 投诉时间
		//判断该工单是否  回单是T1班组和T2回单故障原因中“客户”原因  faultReason  故障原因  101031801 客户
		String complaintSql = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.ComplaintSql"));
		System.out.println("complaintSql="+complaintSql);
		complaintSql = complaintSql.replace("#mainid#", mainId);
		System.out.println("complaintSql===1="+complaintSql);
		IDownLoadSheetAccessoriesService sqlMgr= (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
		Map complaintMap = new HashMap();
		String flag = "0";//没有得到值
		try {
			List linkList =(List) sqlMgr.getSheetAccessoriesList(complaintSql);
			if(linkList != null && linkList.size()>0){
				String linkId = StaticMethod.nullObject2String(((Map) linkList.get(0)).get("id"));//linkId
				String faultreason = StaticMethod.nullObject2String(((Map) linkList.get(0)).get("faultreason"));//故障原因
				String operateroleid = StaticMethod.nullObject2String(((Map) linkList.get(0)).get("operateroleid"));//处理角色
				System.out.println("linkId==="+linkId+"======faultreason="+faultreason);
				System.out.println("operateroleid==="+operateroleid);
				complaintMap.put("operateroleid", operateroleid);
//				String indexFlag = "";
//				String electricCode ="";
//				
//				String desSql = "SELECT MAIN.title,main.complaintDesc, MAIN.COMPLAINTTIME, LINK.ELECTRICCODE FROM GROUPCOMPLAINT_MAIN MAIN, GROUPCOMPLAINT_LINK LINK WHERE MAIN.ID = LINK.MAINID AND LINK.ID = '"+linkId+"'";
//				List desList =(List) sqlMgr.getSheetAccessoriesList(desSql);
//				if(desList != null && desList.size()>0){
//					electricCode = StaticMethod.nullObject2String(((Map) desList.get(0)).get("electricCode"));
//					String title = StaticMethod.nullObject2String(((Map) desList.get(0)).get("title"));
//					String complaintTime = StaticMethod.nullObject2String(((Map) desList.get(0)).get("complaintTime"));
//					String complaintDesc = StaticMethod.nullObject2String(((Map) desList.get(0)).get("complaintDesc"));
//					complaintMap.put("electricCode", electricCode);
//					complaintMap.put("complaintTime", complaintTime);
//					complaintMap.put("title", title);
//					complaintMap.put("complaintDesc", complaintDesc);
//					System.out.println("title=="+title);
//					System.out.println("complaintDesc=="+complaintDesc);
//					//得到 产品实例标识（13位数字）
//					if(complaintDesc.indexOf("产品实例标识")>=0 && complaintDesc.indexOf("产品实例标识")+20 <= complaintDesc.length()){
//						indexFlag = complaintDesc.substring(complaintDesc.indexOf("产品实例标识")+7, complaintDesc.indexOf("产品实例标识")+20);
//						System.out.println("===input1===111111="+indexFlag);
//						if(!indexFlag.matches("[0-9]+")){
//							indexFlag = "";
//						}
//						System.out.println("===input2===111111="+indexFlag);
//						
//						complaintMap.put("indexFlag", indexFlag);
//						
//						System.out.println("====indexFlag==="+indexFlag);
//						System.out.println("electricCode="+electricCode+"====title="+title+"====complaintTime="+complaintTime);
//					}
//				}
				
				
				if(!"101031801".equals(faultreason)){//非 客户
					//查询 内容信息中“产品实例标识（13位数字）”和工单回单信息中“电路编号”，投诉时间
					String desSql = "SELECT MAIN.title,main.complaintDesc, MAIN.COMPLAINTTIME, LINK.ELECTRICCODE FROM GROUPCOMPLAINT_MAIN MAIN, GROUPCOMPLAINT_LINK LINK WHERE MAIN.ID = LINK.MAINID AND LINK.ID = '"+linkId+"'";
					List desList =(List) sqlMgr.getSheetAccessoriesList(desSql);
					if(desList != null && desList.size()>0){
					flag = "1";
						String electricCode = StaticMethod.nullObject2String(((Map) desList.get(0)).get("electricCode"));
						String title = StaticMethod.nullObject2String(((Map) desList.get(0)).get("title"));
						String complaintTime = StaticMethod.nullObject2String(((Map) desList.get(0)).get("complaintTime"));
						String complaintDesc = StaticMethod.nullObject2String(((Map) desList.get(0)).get("complaintDesc"));
						String indexFlag = "";
						//得到 产品实例标识（13位数字）
						if(complaintDesc.indexOf("产品实例标识")>=0 && complaintDesc.indexOf("产品实例标识")+20 <= complaintDesc.length()){
							indexFlag = complaintDesc.substring(complaintDesc.indexOf("产品实例标识")+7, complaintDesc.indexOf("产品实例标识")+20);
							System.out.println("===input1===111111="+indexFlag);
							if(!indexFlag.matches("[0-9]+")){
								indexFlag = "";
							}
							System.out.println("===input2===111111="+indexFlag);
						}
						if("".equals(indexFlag) && "".equals(electricCode) ){
							flag = "2";
						}
						complaintMap.put("electricCode", electricCode);
						complaintMap.put("indexFlag", indexFlag);
						complaintMap.put("complaintTime", complaintTime);
						
						complaintMap.put("title", title);
						complaintMap.put("complaintDesc", complaintDesc);
						
						complaintMap.put("operateroleid", operateroleid);
						
						System.out.println("title=="+title);
						System.out.println("complaintDesc=="+complaintDesc);
						System.out.println("====indexFlag==="+indexFlag);
						System.out.println("electricCode="+electricCode+"====title="+title+"====complaintTime="+complaintTime);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("根据mainId查询link报错！");
			e.printStackTrace();
		}
		complaintMap.put("flag", flag);
		return complaintMap;
	}
	
	public Map t1tot2(Map map){
		System.out.println("customAttribution="+map.get("customAttribution"));
		System.out.println("mainid="+map.get("id"));
		IDownLoadSheetAccessoriesService sqlMgr= (IDownLoadSheetAccessoriesService)ApplicationContextHolder.getInstance().getBean("IDownLoadSheetAccessoriesService");
		String customAttribution = StaticMethod.nullObject2String(map.get("customAttribution"));//用户归属地
		//判断归属地的格式  HB.WH
		if(customAttribution.indexOf(".")>0 && customAttribution.indexOf(".")+1 <= customAttribution.length()){
			String city = customAttribution.substring(customAttribution.indexOf(".")+1, customAttribution.length());
			System.out.println("city="+city);
			city = city.toUpperCase();
			System.out.println("city==dx="+city);
			//更具所得的city，查询派往t2的班组
			String subroleSql = "SELECT * FROM subrole_groupcomplaint WHERE area = '"+city+"'";
			try {
				List subroleList = (List)sqlMgr.getSheetAccessoriesList(subroleSql);
				if(subroleList != null && subroleList.size()>0){
					String subroleid = StaticMethod.nullObject2String(((Map) subroleList.get(0)).get("subroleid"));
					if(!"".equals(subroleid)){
						String mainid = UUIDHexGenerator.getInstance().getID();
						System.out.println("mainid=liy="+mainid);
						map.put("id", mainid);
//						1.T1确认受理
						GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
						calendar.add(13, 30);
						IGroupComplaintLinkManager linkservice = (IGroupComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintLinkManager");
						GroupComplaintLink T1link61 = (GroupComplaintLink)linkservice.getLinkObject().getClass().newInstance();
						String linkId = UUIDHexGenerator.getInstance().getID();
						T1link61.setId(UUIDHexGenerator.getInstance().getID());
						T1link61.setOperateType(new Integer("61"));
						T1link61.setActiveTemplateId("FirstExcuteHumTask");
						T1link61.setOperateTime(calendar.getTime());
						T1link61.setOperateDay(calendar.get(5));
						T1link61.setOperateMonth(calendar.get(2) + 1);
						T1link61.setOperateYear(calendar.get(1));
						T1link61.setMainId(StaticMethod.nullObject2String(map.get("id")));
//						T1link61.setToOrgRoleId(subroleid);//综合班组
						T1link61.setPreLinkId(linkId);
						T1link61.setNodeAccessories("");
						T1link61.setToOrgType(new Integer(0));
						T1link61.setCompleteFlag(new Integer(0));
						T1link61.setOperateUserId("fangmin");
						T1link61.setOperateRoleId("8aa0813b1c6f2386011c6f39c8350027");
						T1link61.setOperateDeptId("12201");
						T1link61.setTemplateFlag(0);
						linkservice.addLink(T1link61);
						
						//t1派单
						calendar.add(13, 10);
						GroupComplaintLink T1link1 = (GroupComplaintLink)linkservice.getLinkObject().getClass().newInstance();
						T1link1.setId(UUIDHexGenerator.getInstance().getID());
						T1link1.setOperateType(new Integer("1"));
						T1link1.setActiveTemplateId("FirstExcuteHumTask");
						T1link1.setOperateTime(calendar.getTime());
						T1link1.setOperateDay(calendar.get(5));
						T1link1.setOperateMonth(calendar.get(2) + 1);
						T1link1.setOperateYear(calendar.get(1));
						T1link1.setMainId(StaticMethod.nullObject2String(map.get("id")));
						T1link1.setToOrgRoleId(subroleid);//综合班组
						T1link1.setPreLinkId(linkId);
						T1link1.setNodeAccessories("");
						T1link1.setToOrgType(new Integer(0));
						T1link1.setCompleteFlag(new Integer(0));
						T1link1.setOperateUserId("fangmin");
						T1link1.setOperateRoleId("8aa0813b1c6f2386011c6f39c8350027");
						T1link1.setOperateDeptId("12201");
						T1link1.setTemplateFlag(0);
						linkservice.addLink(T1link1);
						
						IGroupComplaintTaskManager taskservice = (IGroupComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintTaskManager");
						GroupComplaintTask T1Task = (GroupComplaintTask)taskservice.getTaskModelObject().getClass().newInstance();
						
						try
						{
							T1Task.setId("_TKI:" + UUIDHexGenerator.getInstance().getID());
						}
						catch (Exception e3)
						{
							e3.printStackTrace();
						}
						T1Task.setTaskName("FirstExcuteHumTask");
						T1Task.setTaskDisplayName("一级处理");
						T1Task.setFlowName("GroupComplaintMainFlowProcess");
						T1Task.setSendTime(new Date());
						T1Task.setSheetKey(StaticMethod.nullObject2String(map.get("id")));
						T1Task.setTaskStatus("5");
						T1Task.setSheetId(StaticMethod.nullObject2String(map.get("sheetId")));
						T1Task.setTitle(StaticMethod.nullObject2String(map.get("title")));
						T1Task.setOperateType("subrole");
						T1Task.setCreateTime(new Date(System.currentTimeMillis() - 500L));
						T1Task.setCreateYear(StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")));
						T1Task.setCreateMonth(StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")));
						T1Task.setCreateDay(StaticMethod.null2int(StaticMethod.getCurrentDateTime("dd")));
						T1Task.setOperateRoleId(StaticMethod.nullObject2String("8aa0813b1c6f2386011c6f39c8350027"));
						T1Task.setTaskOwner(StaticMethod.nullObject2String("fangmin"));
						T1Task.setIfWaitForSubTask("false");
						T1Task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
						T1Task.setPreLinkId(StaticMethod.nullObject2String(linkId));
						taskservice.addTask(T1Task);
						
						//派单t2 设置派单对象，下个环节
						map.put("dealPerformer",subroleid);
						map.put("dealPerformerLeader",subroleid);
						map.put("dealPerformerType","subrole");
						map.put("toOrgRoleId",subroleid);
						map.put("phaseId","SecondExcuteHumTask");
					}else{
						System.out.println("根据city查询到subroleid为空");
					}
					
				}else{
					System.out.println("根据city没有查询到subroleid");
				}
			} catch (Exception e) {
				System.out.println("根据city查询subroleid报错");
				e.printStackTrace();
			}
			
		}
		return map;
	}
	
	
	
	public void finishNew(BaseMain main, Map WpsMap, Map map)
	{	
		//新建工单T1自动处理时抄送
		String phaseId = StaticMethod.nullObject2String(map.get("phaseId"));
		String title = StaticMethod.nullObject2String(map.get("title"));
		if("SecondExcuteHumTask".equals(phaseId)){
			if(title.length() > 9 && "（省直管集团客户）".equals(title.substring(0, 9))){
				try {
//					String users = "zhangjunjie,jkts";
					String users = StaticMethod.nullObject2String(XmlManage.getFile("/config/groupComplaint-crm.xml").getProperty("base.copyUserId"));
					String userarr[] = users.split(",");
					for(int i= 0;i<userarr.length;i++){
						GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
						calendar.add(13, 40);
						IGroupComplaintLinkManager linkservice = (IGroupComplaintLinkManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintLinkManager");
						GroupComplaintLink link10 = (GroupComplaintLink)linkservice.getLinkObject().getClass().newInstance();
						String linkId = UUIDHexGenerator.getInstance().getID();
						link10.setId(UUIDHexGenerator.getInstance().getID());
						link10.setOperateType(new Integer(Constants.ACTION_MAKECOPYFOR));
						link10.setActiveTemplateId("cc");
						link10.setOperateTime(calendar.getTime());
						link10.setOperateDay(calendar.get(5));
						link10.setOperateMonth(calendar.get(2) + 1);
						link10.setOperateYear(calendar.get(1));
						link10.setMainId(StaticMethod.nullObject2String(map.get("id")));
						link10.setPreLinkId(linkId);
						link10.setNodeAccessories("");
						link10.setToOrgType(new Integer(0));
						link10.setCompleteFlag(new Integer(0));
						link10.setOperateUserId(userarr[i]);
						link10.setOperateRoleId("");
						link10.setOperateDeptId("");
						link10.setTemplateFlag(0);
						try {
							linkservice.addLink(link10);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						// 保存task数据
						IGroupComplaintTaskManager taskservice = (IGroupComplaintTaskManager)ApplicationContextHolder.getInstance().getBean("iGroupComplaintTaskManager");
						GroupComplaintTask T1Task = (GroupComplaintTask)taskservice.getTaskModelObject().getClass().newInstance();
						
						try
						{
							T1Task.setId("_TKI:" + UUIDHexGenerator.getInstance().getID());
						}
						catch (Exception e3)
						{
							e3.printStackTrace();
						}
						T1Task.setTaskName("cc");
						T1Task.setTaskDisplayName("抄送");
						T1Task.setFlowName("GroupComplaintMainFlowProcess");
						T1Task.setSendTime(new Date());
						T1Task.setSheetKey(StaticMethod.nullObject2String(map.get("id")));
						T1Task.setTaskStatus("2");
						T1Task.setSheetId(StaticMethod.nullObject2String(map.get("sheetId")));
						T1Task.setTitle(StaticMethod.nullObject2String(map.get("title")));
						T1Task.setOperateType("subrole");
						T1Task.setCreateTime(new Date(System.currentTimeMillis() - 500L));
						T1Task.setCreateYear(StaticMethod.null2int(StaticMethod.getCurrentDateTime("yyyy")));
						T1Task.setCreateMonth(StaticMethod.null2int(StaticMethod.getCurrentDateTime("MM")));
						T1Task.setCreateDay(StaticMethod.null2int(StaticMethod.getCurrentDateTime("dd")));
						T1Task.setOperateRoleId("");
						T1Task.setTaskOwner(userarr[i]);
						T1Task.setIfWaitForSubTask("false");
						T1Task.setParentTaskId("_AI:" + UUIDHexGenerator.getInstance().getID());
						T1Task.setPreLinkId(StaticMethod.nullObject2String(linkId));
						taskservice.addTask(T1Task);
					}
					
					/**
					 * 当带有“（省直管集团客户）”标识的工单由T1环节派发到T2环节的时候，eoms需要将投诉工单对应的工单流水号、集团名称、工单派单时间、T2处理时限、所属地市等字段通过webservice传给智远的自动传报平台
					 */
					GroupComplaintMain groupMain = (GroupComplaintMain) main;
					String sheetId = groupMain.getSheetId();//工单流水号
					Date sendTime = groupMain.getSendTime();
					Date dealTime2 = groupMain.getDealTime2();
					String sendTimeStr = "";//工单派单时间
					String dealTime2Str = "";//T2处理时限
					if(sendTime != null){
						sendTimeStr = StaticMethod.date2String(sendTime);
					}
					if(dealTime2 != null){
						dealTime2Str = StaticMethod.date2String(dealTime2);
					}
					String customName = groupMain.getCustomName();//集团名称
					String cityName = groupMain.getCityName();//所属地市
					TSfaultInfo_yiyangLocator service = new TSfaultInfo_yiyangLocator();
					TSfaultInfo_yiyangSoap_PortType bing = service.getTSfaultInfo_yiyangSoap();
					String ret = bing.TS_ConveyMessage(customName, sendTimeStr, dealTime2Str, cityName, sheetId, "webs_yiyang", "sBIQCUoBGyIlo6dAxcQdtQ==");
					System.out.println("TSfaultInfo sheetid="+sheetId+"===ret="+ret);
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					System.out.println("copy err");
				}
				
			}
			
			//T1自动处理，调用智慧专维重派接口
			String groupType = StaticMethod.nullObject2String(map.get("groupType"));
			GroupComplaintZHZWService groupComplaintZHZWService=new GroupComplaintZHZWService();
			if("renewWorkSheet".equals(groupType)){//重派
				System.out.println("T1toT2==repeatSendSheet=="+main.getSheetId());
				groupComplaintZHZWService.repeatSendSheet(map);
			}else if("newWorkSheet".equals(groupType)){
				System.out.println("T1toT2==sendSheet=="+main.getSheetId());
				groupComplaintZHZWService.sendSheet(map);
			}
			
			
			
			
			
		}
		
		
		
	}
	
	
}

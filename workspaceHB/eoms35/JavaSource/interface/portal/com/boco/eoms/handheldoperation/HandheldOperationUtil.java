package com.boco.eoms.handheldoperation;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.base.util.StaticMethod;
import com.boco.eoms.commons.loging.BocoLog;
import com.boco.eoms.commons.system.dept.service.bo.TawSystemDeptBo;
import com.boco.eoms.commons.system.dict.model.TawSystemDictType;
import com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.user.service.bo.ITawSystemUserBo;
import com.boco.eoms.commons.system.role.model.TawSystemSubRole;
import com.boco.eoms.commons.system.role.service.ITawSystemSubRoleManager;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.service.ILinkService;
import com.boco.eoms.sheet.base.service.IMainService;
import com.boco.eoms.sheet.base.service.ITaskService;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;
import com.boco.eoms.sheet.base.util.SheetBeanUtils;
import com.boco.eoms.sheet.base.util.UUIDHexGenerator;

public class HandheldOperationUtil {

	/**
	 * 生成接口的xml字符
	 * @param objectMap
	 * @param filePath
	 * @param nodePath
	 * @return
	 * @throws Exception
	 */
	public String getXmlFromMap(Map objectMap,String filePath,String nodePath) throws Exception{	
		List chNameList = new ArrayList();
		List enNameList = new ArrayList();
		List contentList = new ArrayList();
		
		return this.getXmlFromMap(objectMap, filePath, nodePath,chNameList, enNameList, contentList);
		
	}
	public String getXmlFromMap(Map objectMap,String filePath,String nodePath,List chNameList,List enNameList,List contentList) throws Exception{	
		try{
			ITawSystemUserBo userBO = (ITawSystemUserBo)ApplicationContextHolder.getInstance().getBean("iTawSystemUserBo");
			ITawSystemDictTypeManager dictMgr= (ITawSystemDictTypeManager)ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
	//		ITawSystemSubRoleManager subRoleMgr=(ITawSystemSubRoleManager) ApplicationContextHolder.getInstance().getBean("ItawSystemSubRoleManager");
	
			SAXBuilder dc=new SAXBuilder();
			Document doc = dc.build(new File(filePath));
			
			List analysisList = new ArrayList();
			
			Element element = doc.getRootElement();
			element = element.getChild(nodePath);
			
			List list = element.getChildren();
			for(int i=0;i<list.size();i++){
				Element node = (Element)list.get(i);
				String interfaceCnName = node.getAttribute("interfaceCnName").getValue();
				String interfaceEnName = node.getAttribute("interfaceEnName").getValue();
				String columnName = node.getAttribute("columnName").getValue();
				
				System.out.println("interfaceCnName="+interfaceCnName);
				System.out.println("interfaceEnName="+interfaceEnName);
				System.out.println("columnName="+columnName);
				
				String value = "";
				if (columnName.length() > 0)
				{
					Object obj = objectMap.get(columnName);
					if (obj != null)
						if (obj.getClass().isArray())
							obj = ((Object[])obj)[0];
						else
						if (obj instanceof Date)
						{
							String type = "yyyy-MM-dd HH:mm:ss";
							if (node.getAttribute("type") != null)
								type = node.getAttribute("type").getValue();
							SimpleDateFormat dateformat = new SimpleDateFormat(type);
							value = dateformat.format(obj);
						}
					if ("".equals(value))
						value = StaticMethod.nullObject2String(obj);
				}
				if(value.length()<=0)
					value = node.getAttribute("defauleValue").getValue();
	
				if(value.length()>0){	
					if(node.getAttribute("dictNodePath")!=null){//需要从xml文件转换数据字典
						String dictNodePath = node.getAttribute("dictNodePath").getValue();
						System.out.println("dictNodePath="+dictNodePath);
						value = this.getDictIdByInterfaceCode(dictNodePath, value);
					}
					if(node.getAttribute("type")!=null){
						String type = node.getAttribute("type").getValue();
						System.out.println("type="+type);
						if("dept".equalsIgnoreCase(type)){	//获取部门名称
							value = TawSystemDeptBo.getInstance().getDeptnameBydeptid(value);
						}else if("user".equalsIgnoreCase(type)){	//获取人员
							TawSystemUser user = userBO.getUserByuserid(value);
							value = user.getUsername();
						}else if("dict".equalsIgnoreCase(type)){	//获取字典
							TawSystemDictType dict = dictMgr.getDictByDictId(value);
							if(dict!=null)
								value = dict.getDictName();
					//	}else if("subrole".equalsIgnoreCase(type)){//瀛愯鑹?
					//		TawSystemSubRole subRole=subRoleMgr.getTawSystemSubRole(value);
					//		if(subRole!=null)
					//			value=subRole.getSubRoleName();
						}
					}
					if(node.getAttribute("analysis")!=null){//判断内容是否需要xml解析
						String analysis = node.getAttribute("analysis").getValue();
						System.out.println("analysis="+analysis);
						if("no".equals(analysis)){
							analysisList.add(interfaceEnName);
						}
						
					}
					if(node.getAttribute("dictPath")!=null){
						String dictPath = node.getAttribute("dictPath").getValue();
				//		String cvalue =node.getAttribute(columnName).getValue();
						System.out.println("----cvalue---"+columnName);
						System.out.println("dictPath="+dictPath);
						value = this.getDictNameByDictId(dictPath, value);
					}

				}
				chNameList.add(interfaceCnName);
				enNameList.add(interfaceEnName);
				contentList.add(value);
			}
			
			String opDetail = HandheldOperationLoader.createOpDetailXml(chNameList, enNameList,
					contentList,analysisList);
			BocoLog.info(this, nodePath+" opDetail="+opDetail);
			return opDetail;
		}catch(Exception err){
			err.printStackTrace();
			throw new Exception("生成xml出错："+err.getMessage());
		}
	}
	
	public String getDictIdByInterfaceCode(String nodePath,String code) throws Exception{
		String filePath = StaticMethod.getFilePathForUrl("classpath:com/boco/eoms/crm/config/crm-config.xml");
		return this.getDictIdByInterfaceCode(filePath, nodePath, code);
	}
	
	/**
	 * 通过业务类型查找对应的字典
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public String getDictIdByInterfaceCode(String filePath,String nodePath,String code) throws Exception{
		SAXBuilder dc=new SAXBuilder();
		Document doc = dc.build(new File(filePath));
		
		Element element = doc.getRootElement();
		element = element.getChild("dict");
		element = element.getChild(nodePath);
		if(element==null){
			System.out.println("dict."+nodePath+" not find");
			return "";
		}
		
		List list = element.getChildren();
		for(int i=0;i<list.size();i++){
			Element node = (Element)list.get(i);
			String dict = node.getAttribute("dictid").getValue();
			String interfacecode = node.getAttribute("interfacecode").getValue();
			if(interfacecode.equals(code))
				return dict;
		}
		return "";
	}
	
	public String getDictNameByDictId(String dictPath, String code)
	throws Exception
{
	String codeName = "";
	System.out.println("----dictNameBydictid--"+code);
	String filePath = StaticMethod.getFilePathForUrl("classpath:config/" + dictPath);
	SAXBuilder dc = new SAXBuilder();
	Document doc = dc.build(new File(filePath));
	Element element = doc.getRootElement();
	if (element == null)
	{
		System.out.println(dictPath + " not find");
		return "";
	}
	List list = element.getChildren();
	for (int i = 0; i < list.size(); i++)
	{
		Element node = (Element)list.get(i);
		List itList = node.getChildren();
		for (int j = 0; j < itList.size(); j++)
		{
			Element childNode = (Element)itList.get(j);
			String childId = StaticMethod.null2String(childNode.getAttribute("id").getValue());
			System.out.println("-------------------childId为" + childId + "-------------");
			System.out.println("---code--+"+code+"---childId-+"+childId+"-");
			if (childId.equals(code)){
			
				codeName = StaticMethod.null2String(childNode.getAttribute("name").getValue());
				return codeName;
			}
			System.out.println("-------------------codeName为" + codeName + "-------------");
		}

	}

	return codeName;
}

	/**
	 * 多个Map转换为xml
	 * @author sizhongyuan
	 * @param objecList List对象 存放多个Map
	 * @param filePath 本地字段与接口字段对应的xml配置文件路径
	 * @param nodePath 配置文件中所需对应的标签
	 * */
	public String getXmlFromMap(List objecList,String filePath,String nodePath,String totals) throws Exception{
		String opDetail ="<opDetail>";
		 try{
		for(int i =0;i<objecList.size();i++){
			List chNameList = new ArrayList();
			List enNameList = new ArrayList();
			List contentList = new ArrayList();
			Map map =(Map)objecList.get(i);
			if("2".equals(map.get("taskStatus")) ){
				map.put("taskStatus", "未接单");
			}else if("8".equals(map.get("taskStatus"))){
				map.put("taskStatus", "已接单未处理");
			}
			map.put("sheet_totals", totals); 
			String op= this.getXmlFromMap(map, filePath, nodePath,chNameList, enNameList, contentList);
			System.out.println("--op--"+op);
			op=op.trim();
			op = op.substring(10, op.length());
			op = op.substring(0, op.length()-11);
			opDetail=opDetail+op;
			System.out.println("--opDetail--"+opDetail);
			
		}
		} catch (Exception e) {
		      e.printStackTrace();
	    }
		opDetail = opDetail+"</opDetail>";
		System.out.println("-111-opDetail--"+opDetail);
		return opDetail;
	}
	
	/**
	 * 抄送
	 * */
	public void newSaveNonFlowData(String mainId,Map map,Map columnMap,IMainService mainservice,ITaskService taskService,ILinkService linkService) throws Exception{
		
		HashMap linkMap = (HashMap)map;
		HashMap operateMap = (HashMap)map;
		ITask task = null;
		String operateType = StaticMethod.nullObject2String(map.get("operateType"));
		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);
		BaseMain main = mainservice.getSingleMainPO(mainId);
		BocoLog.info(this, "===优化======抄送===");
		// 抄送
		String copyPerformer = StaticMethod.nullObject2String(map.get("copyPerformer"));
		String[] copyPerformers = copyPerformer.split(",");
		// 抄送多人
		String copyPerformerLeader = StaticMethod.nullObject2String(operateMap.get("copyPerformerLeader"));
		String copyPerformerType = StaticMethod.nullObject2String(operateMap.get("copyPerformerType"));
		String[] copyPerformerLeaders = copyPerformerLeader.split(",");
		String[] copyPerformerTypes = copyPerformerType.split(",");

		for (int i = 0; i < copyPerformers.length; i++) {
			BaseLink linkbean = (BaseLink)linkService.getLinkObject().getClass().newInstance();
			SheetBeanUtils.populateEngineMap2Bean(linkbean, linkMap);
			linkbean.setId(UUIDHexGenerator.getInstance().getID());
			linkbean.setOperateType(new Integer(Constants.ACTION_MAKECOPYFOR));
			linkbean.setOperateTime(nowDate);
			linkbean.setMainId(mainId);
			linkbean.setActiveTemplateId(StaticMethod.nullObject2String(map.get("activetemplateid")));
			linkbean.setToOrgRoleId(copyPerformers[i]);
			linkbean.setOperateDay(calendar.get(Calendar.DATE));
			linkbean.setOperateMonth(calendar.get(Calendar.MONTH) + 1);
			linkbean.setOperateYear(calendar.get(Calendar.YEAR));
			linkbean.setOperateUserId(StaticMethod.nullObject2String(map.get("operateuserid")));
			linkbean.setOperateDeptId(StaticMethod.nullObject2String(map.get("operateDeptId")));
			linkbean.setOperaterContact(StaticMethod.nullObject2String(map.get("operaterContact")));
			linkbean.setOperateRoleId(StaticMethod.nullObject2String(map.get("operateroleid")));
			// 保存task数据
			task = (ITask) taskService.getTaskModelObject().getClass().newInstance();
			task.setTaskName("cc");
			task.setTaskDisplayName("抄送");
			task.setOperateRoleId(copyPerformers[i]);
			task.setTaskOwner(copyPerformerLeaders[i]);
			task.setFlowName(StaticMethod.nullObject2String(map.get("processTemplateName")));
			task.setOperateType(copyPerformerTypes[i]);
			newSaveTask(main,linkbean,task, taskService);
			linkbean.setAiid(task.getId());
			linkService.addLink(linkbean);
			// 如果为抄送再抄送，则需要结束上条task
			if (Integer.parseInt(operateType) == Constants.ACTION_READCOPY) {
				ITask perTask = taskService.getTaskByPreLinkid(linkbean.getPreLinkId());
				perTask.setTaskStatus(Constants.TASK_STATUS_FINISHED);
				perTask.setCurrentLinkId(linkbean.getId());
				taskService.addTask(perTask);
			}
		}
	}
	private void newSaveTask(BaseMain main,BaseLink link,ITask task,ITaskService iTaskService)throws Exception {
		Date nowDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowDate);

		if(task.getId() == null)
			task.setId(UUIDHexGenerator.getInstance().getID());
		if(task.getCreateTime() == null)
			task.setCreateTime(nowDate);
		if(task.getTaskStatus() == null)
			task.setTaskStatus(Constants.TASK_STATUS_READY);
		if(task.getProcessId() == null && main.getPiid() != null)
			task.setProcessId(main.getPiid());
		if(task.getSheetKey() == null)
			task.setSheetKey(main.getId());
		if(task.getSheetId() == null)
			task.setSheetId(main.getSheetId());
		if(task.getTitle() == null)
			task.setTitle(main.getTitle());
		if(task.getAcceptTimeLimit() == null)
			task.setAcceptTimeLimit(link.getNodeAcceptLimit());
		if(task.getCompleteTimeLimit() == null)
			task.setCompleteTimeLimit(link.getNodeCompleteLimit());
		if(task.getPreLinkId() == null)
			task.setPreLinkId(link.getId());
		if(task.getIfWaitForSubTask() == null)
			task.setIfWaitForSubTask("false");
		
		task.setCreateDay(calendar.get(Calendar.DATE));
		task.setCreateMonth(calendar.get(Calendar.MONTH) + 1);
		task.setCreateYear(calendar.get(Calendar.YEAR));
		
		iTaskService.addTask(task);
	}
//	public static void main(String[] args) {
//		String opDetail = "<opDetail><recordInfo><fieldInfo><fieldChName>当前待办人</fieldChName><fieldEnName>User_id</fieldEnName><fieldContent>shilin12</fieldContent></fieldInfo><fieldInfo><fieldChName>工单状态</fieldChName><fieldEnName>Sheet_status</fieldEnName><fieldContent>1</fieldContent></fieldInfo><fieldInfo><fieldChName>网络类型</fieldChName><fieldEnName>NETWORK_SORT</fieldEnName><fieldContent></fieldContent></fieldInfo><fieldInfo><fieldChName>开始记录数</fieldChName><fieldEnName>Start_records</fieldEnName><fieldContent>3</fieldContent></fieldInfo><fieldInfo><fieldChName>结束记录数</fieldChName><fieldEnName>End_records</fieldEnName><fieldContent>10</fieldContent></fieldInfo></recordInfo></opDetail>";
//		opDetail = opDetail.substring(10, opDetail.length());
//		System.out.println("截取前面 opDetail="+opDetail);
//		opDetail = opDetail.substring(0, opDetail.length()-11);
//		System.out.println("截取后面 opDetail="+opDetail);
//	}
	
}

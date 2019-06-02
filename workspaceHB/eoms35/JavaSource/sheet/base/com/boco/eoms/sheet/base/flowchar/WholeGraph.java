package com.boco.eoms.sheet.base.flowchar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.util.DateUtil;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.dept.model.TawSystemDept;
import com.boco.eoms.commons.system.dept.service.ITawSystemDeptManager;
import com.boco.eoms.commons.system.dict.service.ID2NameService;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.user.model.TawSystemUser;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.sheet.base.flowchar.xmltree.XMLModel;
import com.boco.eoms.sheet.base.model.BaseLink;
import com.boco.eoms.sheet.base.model.BaseMain;
import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.util.Constants;

public class WholeGraph {
	private String linkServiceName = "";

	private String description = "";

	private String dictSheet = "";
	
	private String sendUserId = "";
	
	private BaseMain baseMain ;
	
	private Map processIdMap = new HashMap();
	
	private Map parentModelMap = new HashMap();
	
	private ID2NameService service;
	
	private ITawSystemUserManager userManager;
	
	private ITawSystemDeptManager deptManager;
	
	
	

	public ID2NameService getService() {
		return service;
	}

	public void setService(ID2NameService service) {
		this.service = service;
	}

	public Map getParentModelMap() {
		return parentModelMap;
	}

	public void setParentModelMap(Map parentModelMap) {
		this.parentModelMap = parentModelMap;
	}

	public Map getProcessIdMap() {
		return processIdMap;
	}

	public void setProcessIdMap(Map processIdMap) {
		this.processIdMap = processIdMap;
	}

	public WholeGraph(String linkServiceName, String description, String dictSheet, BaseMain baseMain) {
		this.linkServiceName = linkServiceName;
		this.description = description;
		this.dictSheet = dictSheet;
		this.baseMain = baseMain;
		this.service = (ID2NameService) ApplicationContextHolder.getInstance().getBean("ID2NameGetServiceCatch");
		this.userManager = (ITawSystemUserManager)  ApplicationContextHolder.getInstance().getBean("ItawSystemUserSaveManagerFlush");
		this.deptManager = (ITawSystemDeptManager)  ApplicationContextHolder.getInstance().getBean("ItawSystemDeptSaveManagerFlush");
	}

	public String[] draw(List allTiTaskList, List preLinks, List allReplyTaskList, List confirmList)
			throws Exception {
		
		int taskNodeListSize = allTiTaskList.size();
		String[] result = new String[1];
		//新建节点
		XMLModel xModel = new XMLModel();
		xModel.setId("0");
		String detp = this.service.id2Name(baseMain.getSendDeptId(), "tawSystemDeptDao");
		String name = service.id2Name(baseMain.getSendUserId(),"tawSystemUserDao");
		xModel.setName( detp + " (" + name  + ")");
		xModel.setOperate("");
		xModel.setParentId("");
		xModel.setParentLink("");
		xModel.setMainId(baseMain.getId());
		xModel.setDispalyMsg("处理环节：新建工单 &lt;br&gt;  &lt;br&gt; 派发人：" + name + " &lt;br&gt;  &lt;br&gt;联系方式: " + baseMain.getSendContact() + " &lt;br&gt; &lt;br&gt; 派发时间：" + DateUtil.formatDate(baseMain.getSendTime(), "yyyy-MM-dd hh:mm:ss"));
		
		//判断新增的节点是否显亮，主要是判断下面的子节点中是否有回复中的任务，回复中的任务状态如果都完成了，则该节点显示已完成状态，否则显示为未完成状态
		int reply = 0; 
		if (allReplyTaskList.size() > 0) {
			String subStatusDes = "isNoSubTask";
			for (int i = 0; i < allReplyTaskList.size(); i++) {
				ITask replyTask = (ITask) allReplyTaskList.get(i);
				if (baseMain.getPiid().equals(replyTask.getParentProcessName())){
					if (Integer.parseInt(replyTask.getTaskStatus()) == Integer.parseInt(Constants.TASK_STATUS_FINISHED)) {
						subStatusDes = "finished";
					} else {
						subStatusDes = "running";
					}
					reply ++;
				} 
			}
			
			if (subStatusDes.equals("running")) {
				xModel.setStatus(Constants.TASK_STATUS_RUNNING);
			} else if (subStatusDes.equals("finished")) {
				xModel.setStatus(Constants.TASK_STATUS_FINISHED);
			} else {
				xModel.setStatus(Constants.TASK_STATUS_FINISHED);
			}
		} else {
			xModel.setStatus(Constants.TASK_STATUS_FINISHED);
		}
		xModel.setReplyNumber(String.valueOf(reply));
		
		
		//判断归档环节
		//归档时间
		String holdTime = "";
		for (int i =0; i < preLinks.size(); i++) {
			BaseLink preLink = (BaseLink)preLinks.get(i);
			if (preLink.getOperateType().intValue() == Constants.ACTION_HOLD) {
				holdTime =  DateUtil.formatDate(preLink.getOperateTime(), "yyyy-MM-dd hh:mm:ss");
			} else {
				holdTime = "未归档";
			}
		}
		
		for (int j = 0; j < taskNodeListSize; j++) {
			ITask iTask = (ITask) allTiTaskList.get(j);	
			if (iTask.getParentProcessName() == null && iTask.getOperateRoleId().equals(baseMain.getSendRoleId()) && !iTask.getTaskStatus().equals(Constants.TASK_STATUS_FINISHED)) {
				xModel.setStatus(Constants.TASK_STATUS_RUNNING);
				xModel.setOperate(iTask.getTaskName());
				xModel.setDispalyMsg("处理环节：待归档 &lt;br&gt;  &lt;br&gt; " 
									+ "操作人：" + name + " &lt;br&gt;  &lt;br&gt;"
									+ "联系方式: " + baseMain.getSendContact()  + "&lt;br&gt; &lt;br&gt;" 
									+ "操作时间：" + holdTime);
			}
		}
		


		//查找出新建到派发的第一层节点
		List firstNodeList = new ArrayList();
		Map firstMap = new HashMap();
		
		for (int index = 0; index < taskNodeListSize; index++) {
			ITask iTask = (ITask) allTiTaskList.get(index);	
			String preLinkId = iTask.getPreLinkId();
			for (Iterator iterator = preLinks.iterator(); iterator.hasNext(); ) {
				BaseLink baseLink = (BaseLink)iterator.next();
				//linkId
				String linkId = baseLink.getId();
				//操作类型
				Integer operateType = baseLink.getOperateType();
				if (preLinkId.equals(linkId) && (operateType.intValue() == Constants.ACTION_SEND || operateType.intValue() == Constants.ACTION_DRAFT_TO_SEND || operateType.intValue() == Constants.ACTION_AUDIT_PASS)) {
				
					XMLModel newXmlModel = new XMLModel();					
					newXmlModel.setId(iTask.getId());
					int replyNumber = 0;
					String isReply = "no"; 
					if (allReplyTaskList.size() > 0) {
						String subStatusDes = "isNoSubTask";
						for (int i = 0; i < allReplyTaskList.size(); i++) {
							ITask replyTask = (ITask) allReplyTaskList.get(i);
							if (iTask.getProcessId().equals(replyTask.getParentProcessName()) && !iTask.getParentProcessName().equals(replyTask.getParentProcessName())){
								if (replyTask.getTaskStatus().equals(Constants.TASK_STATUS_FINISHED)) {
									subStatusDes = "finished";
								} else {
									subStatusDes = "running";
									
								}
								replyNumber ++;
							}
							//是否回复
						  if (iTask.getProcessId().equals(replyTask.getProcessId())) {
							  isReply = "yes";
						  }
						}
						if (subStatusDes.equals("running")) {
							newXmlModel.setStatus(Constants.TASK_STATUS_RUNNING);
						} else if (subStatusDes.equals("finished")) {
							newXmlModel.setStatus(Constants.TASK_STATUS_FINISHED);
						} else {
							newXmlModel.setStatus(iTask.getTaskStatus());
						}
					} else {
						newXmlModel.setStatus(iTask.getTaskStatus());
					}
					String replyTask = "";
					//且不是回复不通过的任务
					if (isReply.equals("yes") && baseLink.getOperateType().intValue() != 212) {
						replyTask = "&lt;br&gt;&lt;br&gt; (已回复)";
					}
					//下面是弹出框显示的内容
					String detpName = "";
					TawSystemUser tawSystemUser =  (TawSystemUser)userManager.getUserByuserid(iTask.getTaskOwner());
					String userName = "";
					String operateContact = ""; 
					if (tawSystemUser != null) {
						detpName = tawSystemUser.getDeptname();
						userName = tawSystemUser.getUsername();
						operateContact = tawSystemUser.getMobile();
					} else {
						detpName = service.id2Name(iTask.getTaskOwner(), "tawSystemRoleDao");
						userName = detpName;
					}
					if (detpName == null || detpName.equals("")) {
						detpName = service.id2Name(iTask.getTaskOwner(), "tawSystemDeptDao");
						if (!detpName.equals("")) {
							TawSystemDept department = deptManager.getTawSystemDeptById(new Integer(iTask.getTaskOwner()));
							if (department != null)
								operateContact = department.getDeptmobile();
							else 
								operateContact = "";
						}
						userName = detpName;
					}
					//处理时间，也就是确认受理时间,只要该task任务记录和确认受理的prelinkid是相同
					String operateTime = "";
					if (iTask.getTaskStatus().equals(Constants.TASK_STATUS_READY)) {
						operateTime = "该任务还没有接单";
					} else {
						for (int i =0; i < confirmList.size(); i++) {
							BaseLink confrimLink = (BaseLink)confirmList.get(i);
							if (confrimLink.getPreLinkId().equals(iTask.getPreLinkId())) {
								operateTime = DateUtil.formatDate(confrimLink.getOperateTime(), "yyyy-MM-dd hh:mm:ss");
							}
						}
					}
					
					if (tawSystemUser == null || tawSystemUser.getUsername() == null) {
						newXmlModel.setName(detpName);
					} else {
						newXmlModel.setName(detpName + " (" + tawSystemUser.getUsername() + ")" + replyTask);
					}
					String operName = (String) DictMgrLocator.getDictService().itemId2name(Util.constituteDictId(dictSheet,description),String.valueOf(baseLink.getOperateType()));
					String taskName = (String) DictMgrLocator.getDictService().itemId2name(Util.constituteDictId(dictSheet,"activeTemplateId"),iTask.getTaskName());
					newXmlModel.setOperate(operName);
					newXmlModel.setCurrentLink(iTask.getCurrentLinkId());
					newXmlModel.setParentId("0");
					newXmlModel.setMainId(baseMain.getId());
					newXmlModel.setParentLink(iTask.getPreLinkId());
					newXmlModel.setReplyNumber(String.valueOf(replyNumber));
					String completeTime = "";
					if (iTask.getCompleteTimeLimit() == null) {
						completeTime = DateUtil.formatDate(baseMain.getSheetCompleteLimit(), "yyyy-MM-dd hh:mm:ss");
					} else {
						completeTime = DateUtil.formatDate(iTask.getCompleteTimeLimit(), "yyyy-MM-dd hh:mm:ss");
					}
					
					newXmlModel.setDispalyMsg("处理环节："   + taskName + " &lt;br&gt; &lt;br&gt; " +
											  "处理人："    + userName + " &lt;br&gt; &lt;br&gt; " +
											  "联系方式: "  + operateContact + " &lt;br&gt; &lt;br&gt; " +							
											  "处理时限："  + completeTime  + "&lt;br&gt; &lt;br&gt; " +
											  "接单时间："  + operateTime);
					
					for (int j = 0; j < allTiTaskList.size(); j++) {
						ITask ohterTask = (ITask) allTiTaskList.get(j);	
						if (ohterTask.getProcessId().equals(iTask.getProcessId()) && !ohterTask.getId().equals(iTask.getId())) {
							newXmlModel.setStatus(ohterTask.getTaskStatus());
						}	
					}
					
					xModel.addSub(newXmlModel);
					
					firstNodeList.add(iTask);
					firstMap.put(iTask.getId(), newXmlModel);
					this.processIdMap.put(iTask.getProcessId(), iTask);
				} 
			}

		} 
		
		//其它层次的节点
		List otherNodeList = new ArrayList();
		for (int index = 0; index < taskNodeListSize; index++) {
			ITask iTask = (ITask) allTiTaskList.get(index);	
			if (firstMap.get(iTask.getId()) == null) {
				otherNodeList.add(iTask);
			}
		}
		
		xModel.setSubSize(firstNodeList.size());
		
		//将linksList转成map
		Map linksMap = new HashMap();
		for (Iterator iterator = preLinks.iterator(); iterator.hasNext(); ) {
			BaseLink baseLink = (BaseLink)iterator.next();
			linksMap.put(baseLink.getId(), baseLink);
		}
		
		Map sameTaskMap = new HashMap();
		List newOtherNodeList = new ArrayList();
		
		for (int index = 0; index < otherNodeList.size(); index++) {
			ITask iTask = (ITask) otherNodeList.get(index);	
			if (sameTaskMap.get(iTask.getProcessId()) == null) {
				sameTaskMap.put(iTask.getProcessId(), iTask);
				newOtherNodeList.add(iTask);
			} 	
		}
		
		
		
		if (newOtherNodeList.size() > 0 && firstNodeList.size() > 0) {
			getAllNode(firstNodeList, firstMap, newOtherNodeList, linksMap, allReplyTaskList ,allTiTaskList, confirmList);
		}
				
		result[0] = xModel.getXML();
		
		return result;
	}
	
	public void setSubModel() {
		
	}

	public void getAllNode(List firstNodeList, Map firstMap, List otherNodeList,Map preLinks, List allReplyTaskList, List allTiTaskList, List confirmList)
			throws Exception {
		
		int otherNodeListSize = otherNodeList.size();
		Map fatherMap = new HashMap();
		List otherNodes = new ArrayList();
		List firstNodes = new ArrayList();
		//一个子节点对应一个父节点
		Map subTaskMap = new HashMap(); 
		
		for (int index = 0; index < otherNodeList.size(); index++) {
			
			ITask subTask = (ITask) otherNodeList.get(index);			
			
			for (Iterator it = firstNodeList.iterator(); it.hasNext();) {
				ITask parenetTask = (ITask)it.next();
				if (parenetTask.getProcessId().equals(subTask.getParentProcessName()) && subTaskMap.get(subTask.getId()) == null) {
					//放入子节点Map
					subTaskMap.put(subTask.getId(), subTask.getId());
					
					XMLModel parentModel = (XMLModel)firstMap.get(parenetTask.getId());
					XMLModel newXmlModel = new XMLModel();
					newXmlModel.setId(subTask.getId());
					
					String detpName = "";
					String userName = "";
					String operateContact = "";
					TawSystemUser  tawSystemUser = (TawSystemUser)userManager.getUserByuserid(subTask.getTaskOwner());
					if (tawSystemUser != null) {
						detpName = tawSystemUser.getDeptname();
						userName = tawSystemUser.getUsername();
						operateContact = tawSystemUser.getMobile();
					} else {
						detpName = service.id2Name(subTask.getTaskOwner(), "tawSystemRoleDao");
					}
					if (detpName == null || detpName.equals("")) {
						detpName = service.id2Name(subTask.getTaskOwner(), "tawSystemDeptDao");
						if (!detpName.equals("")) {
							TawSystemDept department = deptManager.getTawSystemDeptById(new Integer(subTask.getTaskOwner()));
							if (department != null)
								operateContact = department.getDeptmobile();
							else 
								operateContact = "";
						}
						userName = detpName;
					}
				

					String operateTime = "";
					if (subTask.getTaskStatus().equals(Constants.TASK_STATUS_READY)) { 
						operateTime = "该任务还没有接单";
					} else {
						for (int i =0; i < confirmList.size(); i++) {
							BaseLink confrimLink = (BaseLink)confirmList.get(i);
							if (confrimLink.getPreLinkId().equals(subTask.getPreLinkId())) {
								operateTime = DateUtil.formatDate(confrimLink.getOperateTime(), "yyyy-MM-dd hh:mm:ss");
							}
						}
					}
					String deallimitTime = "";
					if (subTask.getCompleteTimeLimit() == null) {
						deallimitTime = DateUtil.formatDate(baseMain.getSheetCompleteLimit(), "yyyy-MM-dd hh:mm:ss");
					} else {
						deallimitTime = DateUtil.formatDate(subTask.getCompleteTimeLimit(), "yyyy-MM-dd hh:mm:ss");
					}
					
					int replyNumber = 0;
					String isReply = "no";
					
				
					if (allReplyTaskList.size() > 0 && subTask.getTaskStatus().equals(Constants.TASK_STATUS_FINISHED)) {
						String subStatusDes = "isNoSubTask";
						for (int i = 0; i < allReplyTaskList.size(); i++) {
							ITask replyTask = (ITask) allReplyTaskList.get(i);
							if (subTask.getProcessId().equals(replyTask.getParentProcessName()) && !subTask.getParentProcessName().equals(replyTask.getParentProcessName())){
								if (replyTask.getTaskStatus().equals(Constants.TASK_STATUS_FINISHED)) {
									subStatusDes = "finished";	
								} else {
									subStatusDes = "running";
								}
								replyNumber ++;
							}
							//是否回复
							  if (subTask.getProcessId().equals(replyTask.getProcessId())) {
								  isReply = "yes";
							  }
						}
						if (subStatusDes.equals("running")) {
							newXmlModel.setStatus(Constants.TASK_STATUS_RUNNING);
						} else if (subStatusDes.equals("finished")) {
							newXmlModel.setStatus(Constants.TASK_STATUS_FINISHED);
						} else {
							newXmlModel.setStatus(subTask.getTaskStatus());
						}
					} else {
						newXmlModel.setStatus(subTask.getTaskStatus());
					}
					
					

					
					String replyTaskName = "";
					//如果该任务是回复不通过的任务，则不要显示已回复
					Object obj = (Object)preLinks.get(subTask.getPreLinkId());
					String display = "true";
					if (obj != null) {
						BaseLink preLink = (BaseLink)obj;
						int operateType = preLink.getOperateType().intValue();
						if (operateType == 212) {
							display = "false";
						}
					}
					if (isReply.equals("yes") && display.equals("true")) {
						replyTaskName = "&lt;br&gt;&lt;br&gt; (已回复)";
					}
					
					if (tawSystemUser== null || tawSystemUser.getUsername() == null) {
						newXmlModel.setName(detpName);
					} else {
						newXmlModel.setName(detpName + " (" + tawSystemUser.getUsername() + ")" + replyTaskName);
					}

					newXmlModel.setMainId(baseMain.getId());
					newXmlModel.setCurrentLink(subTask.getCurrentLinkId());
					newXmlModel.setParentId(parentModel.getId());
					newXmlModel.setParentLink(subTask.getPreLinkId());
					String taskName = (String) DictMgrLocator.getDictService().itemId2name(Util.constituteDictId(dictSheet,"activeTemplateId"),subTask.getTaskName());
					parentModel.setSubSize(parentModel.getSubSize() + 1);
					newXmlModel.setDispalyMsg("处理环节："   + taskName + " &lt;br&gt; &lt;br&gt; " +
							  "处理人："    + userName + " &lt;br&gt; &lt;br&gt; " +
							  "联系方式: "  + operateContact + " &lt;br&gt; &lt;br&gt; " +
							  "处理时限："  + deallimitTime + "&lt;br&gt; &lt;br&gt; " +
							  "接单时间："  + operateTime);
					newXmlModel.setReplyNumber(String.valueOf(replyNumber));
					parentModel.addSub(newXmlModel);
					
					for (int j = 0; j < allTiTaskList.size(); j++) {
						ITask iTask = (ITask) allTiTaskList.get(j);	
						if (iTask.getProcessId().equals(subTask.getProcessId()) && !iTask.getId().equals(subTask.getId())) {
							newXmlModel.setStatus(iTask.getTaskStatus());
						}	
					}
					
					firstNodes.add(subTask);
					fatherMap.put(subTask.getId(), newXmlModel);
					this.processIdMap.put(subTask.getProcessId(), subTask);
					
					

				}
			}
		}

		
		for (int index = 0; index < otherNodeListSize; index++) {
			ITask iTask = (ITask) otherNodeList.get(index);	
			if (fatherMap.get(iTask.getId()) == null && iTask.getParentProcessName() != null && this.processIdMap.get(iTask.getProcessId()) == null) {
				otherNodes.add(iTask);
			}	
		}
		
		Map sameTaskMap = new HashMap();
		List newOtherNodeList = new ArrayList();
		
		for (int index = 0; index < otherNodes.size(); index++) {
			ITask iTask = (ITask) otherNodes.get(index);	
			if (sameTaskMap.get(iTask.getProcessId()) == null) {
				sameTaskMap.put(iTask.getProcessId(), iTask);
				newOtherNodeList.add(iTask);
			} 	
		}
		
		if (newOtherNodeList.size() > 0 && firstNodes.size() > 0) {
			getAllNode(firstNodes, fatherMap, newOtherNodeList, preLinks, allReplyTaskList,allTiTaskList, confirmList);
		}
	}
	
	
}
package com.boco.eoms.duty.displaytag.support;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.displaytag.decorator.TableDecorator;

import com.boco.eoms.base.util.ApplicationContextHolder;
import com.boco.eoms.commons.system.cptroom.service.ITawSystemCptroomManager;
import com.boco.eoms.commons.system.dict.exceptions.DictDAOException;
import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;
import com.boco.eoms.commons.system.user.service.ITawSystemUserManager;
import com.boco.eoms.duty.model.TawRmDispatchRecord;
import com.boco.eoms.duty.model.TawRmPlanContent;
import com.boco.eoms.duty.model.TawRmReliefRecord;
import com.boco.eoms.duty.model.TawRmWorkorderRecord;
import com.boco.eoms.duty.util.DutyConstacts;

public class DutyListDisplaytagDecorator extends TableDecorator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public String getUserId(){
		ITawSystemUserManager userManager=(ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		TawRmPlanContent tawRmPlanContent=(TawRmPlanContent)getCurrentRowObject();
		String userName="";
		userName=userManager.getUserByuserid(tawRmPlanContent.getUserId()).getUsername();
		return userName;
	}

	public String getSender(){
		ITawSystemUserManager userManager=(ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		TawRmWorkorderRecord tawRmWorkorderRecord=(TawRmWorkorderRecord)getCurrentRowObject();
		String senderName="";
		try {
			if(!tawRmWorkorderRecord.getSender().equals("")){
				senderName=userManager.id2Name(tawRmWorkorderRecord.getSender());
			}
		} catch (DictDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return senderName;
	}
	/**
	 * 文件属性
	 * @return
	 */
	public String getFileProperty(){
		TawRmDispatchRecord tawRmDispatchRecord = (TawRmDispatchRecord) getCurrentRowObject();
		String filePropertyName = "";
		try {
			filePropertyName = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-plancontent",
									"fileProperty"), tawRmDispatchRecord.getFileProperty());
		} catch (DictServiceException e) {
			filePropertyName = Util.idNoName();
		}
		return filePropertyName;
	}
	
	/**
	 * 工单状态
	 * @return
	 */
	public String getOrderState(){
		TawRmWorkorderRecord tawRmWorkorderRecord=(TawRmWorkorderRecord)getCurrentRowObject();
		String orderState = "";
		try {
			orderState = (String) DictMgrLocator.getDictService()
					.itemId2name(
							Util.constituteDictId("dict-plancontent",
									"orderState"), tawRmWorkorderRecord.getOrderState());
		} catch (DictServiceException e) {
			orderState = Util.idNoName();
		}
		return orderState;
	}

	public String getReceiver(){
		ITawSystemUserManager userManager=(ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		TawRmDispatchRecord tawRmDispatchRecord = (TawRmDispatchRecord) getCurrentRowObject();
		String receiverName="";
		receiverName=userManager.getUserByuserid(tawRmDispatchRecord.getReceiver()).getUsername();
		return receiverName;
	}

	public String getHandoverId(){
		ITawSystemUserManager userManager=(ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		TawRmReliefRecord tawRmReliefRecord = (TawRmReliefRecord) getCurrentRowObject();
		String handoverName="";
		handoverName=userManager.getUserByuserid(tawRmReliefRecord.getHandoverId()).getUsername();
		return handoverName;
	}

	public String getSuccessorId(){
		ITawSystemUserManager userManager=(ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
		TawRmReliefRecord tawRmReliefRecord = (TawRmReliefRecord) getCurrentRowObject();
		String successorName="";
		successorName=userManager.getUserByuserid(tawRmReliefRecord.getSuccessorId()).getUsername();
		return successorName;
	}

	public String getRoomId(){
		ITawSystemCptroomManager roomManager=(ITawSystemCptroomManager)ApplicationContextHolder.getInstance().getBean("ItawSystemCptroomManager");
		TawRmReliefRecord tawRmReliefRecord = (TawRmReliefRecord) getCurrentRowObject();
		String roomName="";
		roomName=roomManager.getTawSystemCptroomName(new Integer(tawRmReliefRecord.getRoomId()));
		return roomName;
	}
	
	public String getLink()
	{
		String ip="";
		String port="";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			ip=addr.getHostAddress().toString();
			port=DutyConstacts.PORT;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TawRmWorkorderRecord tawRmWorkorderRecord=(TawRmWorkorderRecord)getCurrentRowObject();
		String orderState=tawRmWorkorderRecord.getOrderState();
		String subProcessInstId=tawRmWorkorderRecord.getSubProcessInstId();
		String processInstId=tawRmWorkorderRecord.getProcessInstId();
		String activityInstId=tawRmWorkorderRecord.getActivityInstId();
		String workItemId=tawRmWorkorderRecord.getWorkItemId();
		String activityDefId=tawRmWorkorderRecord.getActivityDefId();
		String activityInstName=tawRmWorkorderRecord.getActivityInstName();
		String sheetType=tawRmWorkorderRecord.getSheetType();
		String title=tawRmWorkorderRecord.getTitle();
		StringBuffer url=new StringBuffer();
		url.append("<a href=\"");
		url.append("http://");
		url.append(ip);
		url.append(":");
		url.append(port);
		url.append("/");
		if(orderState.equals("10")){
			if(sheetType.equals("0")){
				url.append("tasksheet.pr.prShowDetail.do");
				url.append("?processInstID=");
				url.append(processInstId);
				url.append("&activityInstID=");
				url.append(activityInstId);
				url.append("&workItemID=");
				url.append(workItemId);
				url.append("&activityDefID=");
				url.append(activityDefId);
				url.append("&activityInstName=");
				url.append(activityInstName);
				url.append("&detail/isDetail=0");
			}
			if(sheetType.equals("1")){
				url.append("tasksheet.pr.prShowDetail.do");
				url.append("?subProcessInstID=");
				url.append(processInstId);
				url.append("&processInstID=");
				url.append(subProcessInstId);
				url.append("&activityInstID=");
				url.append(activityInstId);
				url.append("&workItemID=");
				url.append(workItemId);
				url.append("&activityDefID=");
				url.append(activityDefId);
				url.append("&activityInstName=");
				url.append(activityInstName);
				url.append("&detail/isDetail=0&detail/isCs=1");
			}
			if(sheetType.equals("2")){
				url.append("tasksheet.pr.prShowDetail.do");
				url.append("?subProcessInstID=");
				url.append(processInstId);
				url.append("&processInstID=");
				url.append(subProcessInstId);
				url.append("&activityInstID=");
				url.append(activityInstId);
				url.append("&workItemID=");
				url.append(workItemId);
				url.append("&activityDefID=");
				url.append(activityDefId);
				url.append("&activityInstName=");
				url.append(activityInstName);
				url.append("&detail/isDetail=0&detail/isCs=2");
			}
		}
		if(orderState.equals("12")){
			if(sheetType.equals("0")){
				url.append("tasksheet.pr.prShowDetail.do");
				url.append("?processInstID=");
				url.append(processInstId);
				url.append("&activityInstID=");
				url.append(activityInstId);
				url.append("&workItemID=");
				url.append(workItemId);
				url.append("&activityDefID=");
				url.append(activityDefId);
				url.append("&activityInstName=");
				url.append(activityInstName);
				url.append("&detail/isDetail=1&detail/isFinsh=1");
			}
			if(sheetType.equals("1")){
				url.append("tasksheet.pr.prShowDetail.do");
				url.append("?subProcessInstID=");
				url.append(processInstId);
				url.append("&processInstID=");
				url.append(subProcessInstId);
				url.append("&activityInstID=");
				url.append(activityInstId);
				url.append("&workItemID=");
				url.append(workItemId);
				url.append("&activityDefID=");
				url.append(activityDefId);
				url.append("&activityInstName=");
				url.append(activityInstName);
				url.append("&detail/isDetail=1&detail/isCs=1");
			}
			if(sheetType.equals("2")){
				url.append("tasksheet.pr.prShowDetail.do");
				url.append("?subProcessInstID=");
				url.append(processInstId);
				url.append("&processInstID=");
				url.append(subProcessInstId);
				url.append("&activityInstID=");
				url.append(activityInstId);
				url.append("&workItemID=");
				url.append(workItemId);
				url.append("&activityDefID=");
				url.append(activityDefId);
				url.append("&activityInstName=");
				url.append(activityInstName);
				url.append("&detail/isDetail=1&detail/isCs=2");
			}
		}
		url.append("\">");
		url.append(title);
		url.append("</a>");
		return url.toString();
	}

}

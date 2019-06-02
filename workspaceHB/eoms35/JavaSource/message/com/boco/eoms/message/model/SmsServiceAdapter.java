package com.boco.eoms.message.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsServiceAdapter {
	private String id;
	private String name;
	private String parentId;
	private String msgType;
	private Integer count;
	private String interval;
	private String isSendImediat;
	private String isSendNight;
	private String startTime;
	private String endTime;
	private String sendDay;
	private String sendHour;
	private String sendMin;
	private String sendStatus;
	private String isCycleSend;
	private String cycleStatus;
	private String cycleMonth;
	private String cycleDay;
	private String cycleHour;
	private String regetData;
	private String regetAddr;
	private String regetProtocol;
	private String userId;
	private String status;
	private String selStatus;
	private String deleted;
	private String leaf;
	private String remark;
	private String applyId;
	private String buizid;
	private String serviceId;
	private String receiverId;
	private String addresser;
	private String addressee;
	private Date dispatchTime;
	private String subject;
	private String content;
	private String accessoriesUrl;
	
	
	/**
	 * @author xugengxian
	 *         当selStatus的value为false时userId里代表这些人不要这个服务；否则当selStatus的value为true时代表仅userId这些人拥有该服务
	 */
	private String usersId;

	public String getUsersId() {
		return usersId;
	}

	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}

	// 2009-03-31 add 非值班状态是否发送,1发送，非1不发送
	private String isSendUnDuty;

	public String getIsSendUnDuty() {
		return isSendUnDuty;
	}

	public void setIsSendUnDuty(String isSendUnDuty) {
		this.isSendUnDuty = isSendUnDuty;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * name="删除标志"
	 * 
	 */
	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * name="是否立即发送"
	 * 
	 */
	public String getIsSendImediat() {
		return isSendImediat;
	}

	public void setIsSendImediat(String isSendImediat) {
		this.isSendImediat = isSendImediat;
	}

	/**
	 * name="是否允许晚上发送"
	 * 
	 */
	public String getIsSendNight() {
		return isSendNight;
	}

	public void setIsSendNight(String isSendNight) {
		this.isSendNight = isSendNight;
	}

	/**
	 * name="消息类型"
	 * 
	 */
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	/**
	 * name="服务名称"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * name="备注" not-null="false" unique="true"
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * name="消息发送次数" not-null="false" unique="true"
	 */
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * name="循环天数" not-null="false" unique="true"
	 */
	public String getCycleDay() {
		return cycleDay;
	}

	public void setCycleDay(String cycleDay) {
		this.cycleDay = cycleDay;
	}

	/**
	 * "循环小时数" not-null="false" unique="true"
	 */
	public String getCycleHour() {
		return cycleHour;
	}

	public void setCycleHour(String cycleHour) {
		this.cycleHour = cycleHour;
	}

	/**
	 * "循环小时数" not-null="false" unique="true"
	 */
	public String getCycleMonth() {
		return cycleMonth;
	}

	public void setCycleMonth(String cycleMonth) {
		this.cycleMonth = cycleMonth;
	}

	/**
	 * "服务结束时间" not-null="false" unique="true"
	 * 
	 */
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * "消息发送间隔" not-null="false" unique="true"
	 * 
	 */
	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	/**
	 * "叶子节点" not-null="false" unique="true"
	 */
	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	/**
	 * "是否重新采集数据" not-null="false" unique="true"
	 */
	public String getRegetData() {
		return regetData;
	}

	public void setRegetData(String regetData) {
		this.regetData = regetData;
	}

	/**
	 * "催办天数" not-null="false" unique="true"
	 * 
	 */
	public String getSendDay() {
		return sendDay;
	}

	public void setSendDay(String sendDay) {
		this.sendDay = sendDay;
	}

	/**
	 * "催办小时数" not-null="false" unique="true"
	 * 
	 */
	public String getSendHour() {
		return sendHour;
	}

	public void setSendHour(String sendHour) {
		this.sendHour = sendHour;
	}

	/**
	 * "催办分钟数" not-null="false" unique="true"
	 * 
	 */
	public String getSendMin() {
		return sendMin;
	}

	public void setSendMin(String sendMin) {
		this.sendMin = sendMin;
	}

	/**
	 * 
	 * "消息发送时间标志" not-null="false" unique="true"
	 * 
	 */
	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	/**
	 * 
	 * "服务开始时间" not-null="false" unique="true"
	 * 
	 */
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * "当前用户名称"
	 */
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * "循环标志"
	 * 
	 */
	public String getCycleStatus() {
		return cycleStatus;
	}

	public void setCycleStatus(String cycleStatus) {
		this.cycleStatus = cycleStatus;
	}

	/**
	 * "是否循环发送"
	 * 
	 */
	public String getIsCycleSend() {
		return isCycleSend;
	}

	public void setIsCycleSend(String isCycleSend) {
		this.isCycleSend = isCycleSend;
	}

	/**
	 * "父节点ID"
	 * 
	 */
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 
	 * "模块或者业务"
	 * 
	 */
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * "重新采集地址"
	 * 
	 */
	public String getRegetAddr() {
		return regetAddr;
	}

	public void setRegetAddr(String regetAddr) {
		this.regetAddr = regetAddr;
	}

	/**
	 * "重新采集数据协议"
	 * 
	 */
	public String getRegetProtocol() {
		return regetProtocol;
	}

	public void setRegetProtocol(String regetProtocol) {
		this.regetProtocol = regetProtocol;
	}

	/**
	 * 
	 * "正反标志"
	 * 
	 */
	public String getSelStatus() {
		return selStatus;
	}

	public void setSelStatus(String selStatus) {
		this.selStatus = selStatus;
	}
	
	/**
	 * 字符串转为一定格式的时间
	 * @author mengxianyu
	 * @param str
	 * @return
	 */
	private Date str2Date(String str){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date timeTemp = null;
    	try {
    		timeTemp = formatter.parse(str);
		} catch (ParseException e) {
			System.out.println("字符串转时间出错： " + e.getMessage());
		}
		return timeTemp;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getBuizid() {
		return buizid;
	}

	public void setBuizid(String buizid) {
		this.buizid = buizid;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getAddresser() {
		return addresser;
	}

	public void setAddresser(String addresser) {
		this.addresser = addresser;
	}

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	public Date getDispatchTime() {
		return dispatchTime;
	}

	public void setDispatchTime(Date dispatchTime) {
		this.dispatchTime = dispatchTime;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAccessoriesUrl() {
		return accessoriesUrl;
	}

	public void setAccessoriesUrl(String accessoriesUrl) {
		this.accessoriesUrl = accessoriesUrl;
	}
	
//	/**
//	 * 日期转字符串
//	 * @author mengxianyu
//	 * @param str
//	 * @return
//	 */
//	private Date str2Date(String str){
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
//		Date timeTemp = null;
//		try {
//			timeTemp = formatter.parse(str);
//		} catch (ParseException e) {
//			System.out.println("字符串转时间出错： " + e.getMessage());
//		}
//		return timeTemp;
//	}
}

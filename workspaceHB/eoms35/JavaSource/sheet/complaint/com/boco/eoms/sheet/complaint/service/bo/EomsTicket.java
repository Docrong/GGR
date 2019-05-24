package com.boco.eoms.sheet.complaint.service.bo;

public class EomsTicket {
	private String eomsSeq;		//EOMS工单流水号
	private String ticketSeq;	//客服流水号
	private String serviceName;	//投诉分类（将7级分类合并成一个字段）
	private String userPhone;	//客户电话
	private String userProv;	//投诉受理省份
	private String userCity;	//用户归属地
	private String compTime;	//投诉时间
	private String compContent;	//投诉内容
	private String place;		//故障地点
	private String workerPhone;	//派发联系人电话
	public String getEomsSeq() {
		return eomsSeq;
	}
	public void setEomsSeq(String eomsSeq) {
		this.eomsSeq = eomsSeq;
	}
	public String getTicketSeq() {
		return ticketSeq;
	}
	public void setTicketSeq(String ticketSeq) {
		this.ticketSeq = ticketSeq;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserProv() {
		return userProv;
	}
	public void setUserProv(String userProv) {
		this.userProv = userProv;
	}
	public String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public String getCompTime() {
		return compTime;
	}
	public void setCompTime(String compTime) {
		this.compTime = compTime;
	}
	public String getCompContent() {
		return compContent;
	}
	public void setCompContent(String compContent) {
		this.compContent = compContent;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getWorkerPhone() {
		return workerPhone;
	}
	public void setWorkerPhone(String workerPhone) {
		this.workerPhone = workerPhone;
	}
	
}

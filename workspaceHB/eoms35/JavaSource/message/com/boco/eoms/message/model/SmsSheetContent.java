package com.boco.eoms.message.model;

public class SmsSheetContent {
	private String id;
	private String flowId;
	private String processCnName;
	private String workFlowName;
	private String smsServiceId ;
	private String owner;
	private String receiver;
	private String content;
	private String type;
	private String send_day;
	private String send_hour;
	private String send_min;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getProcessCnName() {
		return processCnName;
	}
	public void setProcessCnName(String processCnName) {
		this.processCnName = processCnName;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getSend_day() {
		return send_day;
	}
	public void setSend_day(String send_day) {
		this.send_day = send_day;
	}
	public String getSend_hour() {
		return send_hour;
	}
	public void setSend_hour(String send_hour) {
		this.send_hour = send_hour;
	}
	public String getSend_min() {
		return send_min;
	}
	public void setSend_min(String send_min) {
		this.send_min = send_min;
	}
	public String getSmsServiceId() {
		return smsServiceId;
	}
	public void setSmsServiceId(String smsServiceId) {
		this.smsServiceId = smsServiceId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWorkFlowName() {
		return workFlowName;
	}
	public void setWorkFlowName(String workFlowName) {
		this.workFlowName = workFlowName;
	}
	
}

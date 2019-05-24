package com.boco.eoms.testcard.util;

/**
 * <p>
 * Title:值班属性配置类
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:Apr 15, 2008 1:42:00 PM
 * </p>
 * 
 * @author 曲静波
 * @version 1.0
 * 
 */
public class TestCardAttributes {

	private String normalState;
	private String downtimeState;//CRM接口地址
	private String lostState;//服务提供方
	private String registrationState;//服务调用方
	private String lendingState;//服务调用方密码(可选)
	private String scrappedState;//信息专题id
	private String useState;//信息专题id
	private String questionState;//信息专题id
	private String auditedState;//信息专题id
	private String pendingState;
	private String serverId ;
	private String returnDay;
	public String getReturnDay() {
		return returnDay;
	}
	public void setReturnDay(String returnDay) {
		this.returnDay = returnDay;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	public String getNormalState() {
		return normalState;
	}
	public void setNormalState(String normalState) {
		this.normalState = normalState;
	}
	public String getDowntimeState() {
		return downtimeState;
	}
	public void setDowntimeState(String downtimeState) {
		this.downtimeState = downtimeState;
	}
	public String getLostState() {
		return lostState;
	}
	public void setLostState(String lostState) {
		this.lostState = lostState;
	}
	public String getRegistrationState() {
		return registrationState;
	}
	public void setRegistrationState(String registrationState) {
		this.registrationState = registrationState;
	}
	public String getLendingState() {
		return lendingState;
	}
	public void setLendingState(String lendingState) {
		this.lendingState = lendingState;
	}
	public String getScrappedState() {
		return scrappedState;
	}
	public void setScrappedState(String scrappedState) {
		this.scrappedState = scrappedState;
	}
	public String getUseState() {
		return useState;
	}
	public void setUseState(String useState) {
		this.useState = useState;
	}
	public String getQuestionState() {
		return questionState;
	}
	public void setQuestionState(String questionState) {
		this.questionState = questionState;
	}
	public String getAuditedState() {
		return auditedState;
	}
	public void setAuditedState(String auditedState) {
		this.auditedState = auditedState;
	}
	public String getPendingState() {
		return pendingState;
	}
	public void setPendingState(String pendingState) {
		this.pendingState = pendingState;
	}
	

	


}

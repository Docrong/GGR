package com.boco.eoms.sheet.complaint.service.bo;

public class EomsReply {
	private String contactor; // 联系人
	private String contactPhone; // 联系人电话
	private String complaintType; // 投诉性质
	private String isReply; // 是否答复客户（是、否）
	private String solvedTime; // 问题消除时间（yyyy-MM-dd HH:mm:ss）*
	private String result; // 处理结果（解决、未解决）
	private String dutyReason; // 问题原因分类（XXX|XXX|XXX|XXX）
	private String complaintCause; // 问题原因
	private String solution; // 解决措施
	private String replier; // 回复人
	private String replyTime; // 回复人联系电话
	private String replyContent; // 处理经过*
	private String isSolved; // 投诉是否解决
	private String isConfirmed; // 用户是否确认（是、否）
	private String isRepeat; // 是否重复投诉（是、否）
	private String userSatisfaction;// 用户满意情况
	private String isContacted; // 是否联系用户（是、否）
	private String major; // 投诉涉及专业
	private String areaType; // 投诉区域性质
	private String ci; // 主覆盖小区CI
	private String cellName; // 主覆盖小区名称
	private String deviceType; // 设备类型
	private String deviceFactory; // 设备厂家
	private String hasAlarm; // 是否有告警
	private String weakoverlay; // 弱覆盖三网测试情况*

	public String getContactor() {
		return contactor;
	}

	public void setContactor(String contactor) {
		this.contactor = contactor;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getComplaintType() {
		return complaintType;
	}

	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}

	public String getIsReply() {
		return isReply;
	}

	public void setIsReply(String isReply) {
		this.isReply = isReply;
	}

	public String getSolvedTime() {
		return solvedTime;
	}

	public void setSolvedTime(String solvedTime) {
		this.solvedTime = solvedTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDutyReason() {
		return dutyReason;
	}

	public void setDutyReason(String dutyReason) {
		this.dutyReason = dutyReason;
	}

	public String getComplaintCause() {
		return complaintCause;
	}

	public void setComplaintCause(String complaintCause) {
		this.complaintCause = complaintCause;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getReplier() {
		return replier;
	}

	public void setReplier(String replier) {
		this.replier = replier;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getIsSolved() {
		return isSolved;
	}

	public void setIsSolved(String isSolved) {
		this.isSolved = isSolved;
	}

	public String getIsConfirmed() {
		return isConfirmed;
	}

	public void setIsConfirmed(String isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public String getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(String isRepeat) {
		this.isRepeat = isRepeat;
	}

	public String getUserSatisfaction() {
		return userSatisfaction;
	}

	public void setUserSatisfaction(String userSatisfaction) {
		this.userSatisfaction = userSatisfaction;
	}

	public String getIsContacted() {
		return isContacted;
	}

	public void setIsContacted(String isContacted) {
		this.isContacted = isContacted;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getCi() {
		return ci;
	}

	public void setCi(String ci) {
		this.ci = ci;
	}

	public String getCellName() {
		return cellName;
	}

	public void setCellName(String cellName) {
		this.cellName = cellName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceFactory() {
		return deviceFactory;
	}

	public void setDeviceFactory(String deviceFactory) {
		this.deviceFactory = deviceFactory;
	}

	public String getHasAlarm() {
		return hasAlarm;
	}

	public void setHasAlarm(String hasAlarm) {
		this.hasAlarm = hasAlarm;
	}

	public String getWeakoverlay() {
		return weakoverlay;
	}

	public void setWeakoverlay(String weakoverlay) {
		this.weakoverlay = weakoverlay;
	}

}

package com.boco.eoms.workplan.util;

public class WorkplanAttributes {
	private String serverId;
	private String deptAudits;//审核人员过滤条件 0：显示全部审核人 1：显示上一级部门和本部门的审核人员 
	public String getDeptAudits() {
		return deptAudits;
	}

	public void setDeptAudits(String deptAudits) {
		this.deptAudits = deptAudits;
	}

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
	private String newstrregioncode;

	public String getNewstrregioncode() {
		return newstrregioncode;
	}

	public void setNewstrregioncode(String newstrregioncode) {
		this.newstrregioncode = newstrregioncode;
	}
	private String intfaceUrl;

	public String getIntfaceUrl() {
		return intfaceUrl;
	}

	public void setIntfaceUrl(String intfaceUrl) {
		this.intfaceUrl = intfaceUrl;
	}
	private String port;
	private String user;
	private String password;
	private String ip;
	private String timeout;//接口调用服务的超时设置
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getTimeout() {
		return timeout;
	}
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
	
	private String statday;//作业计划可以延迟执行的天数
	private String switchQualityCheck;//是否开放质检功能
	private String userServiceId;//内蒙轮巡发送短信给用户的服务
	private String deptServiceId;//内蒙轮巡发送短信给部门的服务
	private String roomServiceId;//内蒙轮巡发送短信给机房的服务

	public String getDeptServiceId() {
		return deptServiceId;
	}

	public void setDeptServiceId(String deptServiceId) {
		this.deptServiceId = deptServiceId;
	}

	public String getRoomServiceId() {
		return roomServiceId;
	}

	public void setRoomServiceId(String roomServiceId) {
		this.roomServiceId = roomServiceId;
	}

	public String getStatday() {
		return statday;
	}

	public void setStatday(String statday) {
		this.statday = statday;
	}

	public String getSwitchQualityCheck() {
		return switchQualityCheck;
	}

	public void setSwitchQualityCheck(String switchQualityCheck) {
		this.switchQualityCheck = switchQualityCheck;
	}

	public String getUserServiceId() {
		return userServiceId;
	}

	public void setUserServiceId(String userServiceId) {
		this.userServiceId = userServiceId;
	}
	
    String errorLogPath;
	private String ftpLogIP;
	private String ftpLogPort;
	private String ftpLogAcc;
	private String ftpLogPwd;
	private String intfaceDept;

	public String getErrorLogPath() {
		return errorLogPath;
	}

	public void setErrorLogPath(String errorLogPath) {
		this.errorLogPath = errorLogPath;
	}

	public String getFtpLogAcc() {
		return ftpLogAcc;
	}

	public void setFtpLogAcc(String ftpLogAcc) {
		this.ftpLogAcc = ftpLogAcc;
	}

	public String getFtpLogIP() {
		return ftpLogIP;
	}

	public void setFtpLogIP(String ftpLogIP) {
		this.ftpLogIP = ftpLogIP;
	}

	public String getFtpLogPort() {
		return ftpLogPort;
	}

	public void setFtpLogPort(String ftpLogPort) {
		this.ftpLogPort = ftpLogPort;
	}

	public String getFtpLogPwd() {
		return ftpLogPwd;
	}

	public void setFtpLogPwd(String ftpLogPwd) {
		this.ftpLogPwd = ftpLogPwd;
	}

	public String getIntfaceDept() {
		return intfaceDept;
	}

	public void setIntfaceDept(String intfaceDept) {
		this.intfaceDept = intfaceDept;
	}

	 
}

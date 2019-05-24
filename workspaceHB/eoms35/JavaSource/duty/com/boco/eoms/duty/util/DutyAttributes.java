package com.boco.eoms.duty.util;

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
public class DutyAttributes {

	/**
	 * 控制值班类型
	 */
	private String dutyOtherAssign;

	public String getDutyOtherAssign() {
		return dutyOtherAssign;
	}

	public void setDutyOtherAssign(String dutyOtherAssign) {
		this.dutyOtherAssign = dutyOtherAssign;
	}
	
	/**
	 * 上传路径设置
	 */
	private String dutyRootPath;

	public String getDutyRootPath() {
		return dutyRootPath;
	}

	public void setDutyRootPath(String dutyRootPath) {
		this.dutyRootPath = dutyRootPath;
	}
	
	/**
	 * 上传路径设置
	 */
	private String dutyOldPath;

	public String getDutyOldPath() {
		return dutyOldPath;
	}

	public void setDutyOldPath(String dutyOldPath) {
		this.dutyOldPath = dutyOldPath;
	}
	
	private String serverId;

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	
	private String sendSMsgAvailable;

	public String getSendSMsgAvailable() {
		return sendSMsgAvailable;
	}

	public void setSendSMsgAvailable(String sendSMsgAvailable) {
		this.sendSMsgAvailable = sendSMsgAvailable;
	}

}

package com.boco.eoms.sheet.numberapply.model;

public class NumberApplyHlrid {
	/**
	 * 
	 * 主键
	 */
	private String id;
	
	/**
	 * 关联main表的id
	 */
	private String mainid;
	
	/**
	 * 网元名称
	 */
	private String netName;
	
	/**
	 * 网元代号
	 */
	private String netId;
	
	/**
	 * 网元属性
	 */
	private String netProp;
	/**
	 * 
	 * 建设地点
	 */
	private String buildAddress;
	
	/**
	 * 供应商
	 */
	private String supplier;
	
	/**
	 * 信令点编码(24位)
	 */
	private String commandCode24;
	
	/**
	 * 
	 * HLR ID
	 */
	private String hlrId;
	
	
	/**
	 * 信令点编码(14位)
	 */
	private String commandCode14;
	
	/**
	 * 硬件平台
	 */
	private String hardwareFlatRoof;
	
	/**
	 * 软件版本
	 */
	private String softwareVersion;
	/**
	 * 
	 * 容量（万）
	 */
	private String capability;
	
	/**
	 * 信令链路数（2MB）
	 */
	private String commondLink;
	
	/**
	 * E1端口总数（承载窄带)
	 */
	private String portCount;
	
	/**
	 * 覆盖地区
	 */
	private String coverArea;
	
	/**
	 * 覆盖地区长途区号
	 */
	private String areaNumber;
	
	/**
	 * 城市
	 */
	private String city;
	
	/**
	 * 设备所在本地网名称
	 */
	private String deviceName;
	
	/**
	 * 归属区域
	 */
	private String attachArea;
	
	/**
	 * 批复文件号
	 */
	private String fileNumber;
	/**
	 * 是否生效
	 */
	private String isVaild ;

	public String getAreaNumber() {
		return areaNumber;
	}

	public void setAreaNumber(String areaNumber) {
		this.areaNumber = areaNumber;
	}

	public String getAttachArea() {
		return attachArea;
	}

	public void setAttachArea(String attachArea) {
		this.attachArea = attachArea;
	}

	public String getBuildAddress() {
		return buildAddress;
	}

	public void setBuildAddress(String buildAddress) {
		this.buildAddress = buildAddress;
	}

	public String getCapability() {
		return capability;
	}

	public void setCapability(String capability) {
		this.capability = capability;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCommandCode14() {
		return commandCode14;
	}

	public void setCommandCode14(String commandCode14) {
		this.commandCode14 = commandCode14;
	}

	public String getCommandCode24() {
		return commandCode24;
	}

	public void setCommandCode24(String commandCode24) {
		this.commandCode24 = commandCode24;
	}

	public String getCommondLink() {
		return commondLink;
	}

	public void setCommondLink(String commondLink) {
		this.commondLink = commondLink;
	}

	public String getCoverArea() {
		return coverArea;
	}

	public void setCoverArea(String coverArea) {
		this.coverArea = coverArea;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	public String getHardwareFlatRoof() {
		return hardwareFlatRoof;
	}

	public void setHardwareFlatRoof(String hardwareFlatRoof) {
		this.hardwareFlatRoof = hardwareFlatRoof;
	}

	public String getHlrId() {
		return hlrId;
	}

	public void setHlrId(String hlrId) {
		this.hlrId = hlrId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMainid() {
		return mainid;
	}

	public void setMainid(String mainid) {
		this.mainid = mainid;
	}

	public String getNetId() {
		return netId;
	}

	public void setNetId(String netId) {
		this.netId = netId;
	}

	public String getNetName() {
		return netName;
	}

	public void setNetName(String netName) {
		this.netName = netName;
	}

	public String getNetProp() {
		return netProp;
	}

	public void setNetProp(String netProp) {
		this.netProp = netProp;
	}

	public String getPortCount() {
		return portCount;
	}

	public void setPortCount(String portCount) {
		this.portCount = portCount;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getIsVaild() {
		return isVaild;
	}

	public void setIsVaild(String isVaild) {
		this.isVaild = isVaild;
	}
	
	
	
	
	
	
	
}

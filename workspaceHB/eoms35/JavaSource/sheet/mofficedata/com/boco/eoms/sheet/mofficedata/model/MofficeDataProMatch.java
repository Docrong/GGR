package com.boco.eoms.sheet.mofficedata.model;

import java.io.Serializable;

/**
 * <p>
 * Title:局数据工单流程
 * </p>
 * <p>
 * Description:局数据工单流程
 * </p>
 * <p>
 * Tue Mar 22 09:31:29 CST 2016
 * </p>
 * 
 * @author weichao
 * @version 3.5
 * 
 */

public class MofficeDataProMatch implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2780357284555751446L;
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 关联main表Id
	 */
	private String mainId;
	/**
	 * 业务类型
	 */
	private String buissType;
	/**
	 * 专业
	 */
	private String majorType;
	/**
	 * 制作人
	 */
	private String producerId;
	/**
	 * 制作人中文名
	 */
	private String producerName;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 制作人类型
	 */
	private String producerType;
	/**
	 * 网元类型id
	 */
	private String netId;
	/**
	 * 网元类型名称
	 */
	private String netName;
	/**
	 * 设备厂家名称
	 */
	private String deviceName;
	/**
	 * 设备厂家Id
	 */
	private String deviceId;
	/**
	 * 附件
	 */
	private String accessories;
	
	private String correKey;
	private String phaseName;
	private String rejectPhaseName;
	private String linkId;
	
	private String mainStyle6;
	private String mainStyle7;
	private String mainStyle8;
	private String mainStyle9;//地市
	private String mainStyle10;//网元名称
	
	public String getAccessories() {
		return accessories;
	}
	public void setAccessories(String accessories) {
		this.accessories = accessories;
	}
	public String getBuissType() {
		return buissType;
	}
	public void setBuissType(String buissType) {
		this.buissType = buissType;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMainId() {
		return mainId;
	}
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
	public String getMajorType() {
		return majorType;
	}
	public void setMajorType(String majorType) {
		this.majorType = majorType;
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
	public String getProducerId() {
		return producerId;
	}
	public void setProducerId(String producerId) {
		this.producerId = producerId;
	}
	public String getProducerName() {
		return producerName;
	}
	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}
	public String getProducerType() {
		return producerType;
	}
	public void setProducerType(String producerType) {
		this.producerType = producerType;
	}
	public String getCorreKey() {
		return correKey;
	}
	public void setCorreKey(String correKey) {
		this.correKey = correKey;
	}
	public String getPhaseName() {
		return phaseName;
	}
	public void setPhaseName(String phaseName) {
		this.phaseName = phaseName;
	}
	public String getRejectPhaseName() {
		return rejectPhaseName;
	}
	public void setRejectPhaseName(String rejectPhaseName) {
		this.rejectPhaseName = rejectPhaseName;
	}
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public String getMainStyle10() {
		return mainStyle10;
	}
	public void setMainStyle10(String mainStyle10) {
		this.mainStyle10 = mainStyle10;
	}
	public String getMainStyle6() {
		return mainStyle6;
	}
	public void setMainStyle6(String mainStyle6) {
		this.mainStyle6 = mainStyle6;
	}
	public String getMainStyle7() {
		return mainStyle7;
	}
	public void setMainStyle7(String mainStyle7) {
		this.mainStyle7 = mainStyle7;
	}
	public String getMainStyle8() {
		return mainStyle8;
	}
	public void setMainStyle8(String mainStyle8) {
		this.mainStyle8 = mainStyle8;
	}
	public String getMainStyle9() {
		return mainStyle9;
	}
	public void setMainStyle9(String mainStyle9) {
		this.mainStyle9 = mainStyle9;
	}
	
}
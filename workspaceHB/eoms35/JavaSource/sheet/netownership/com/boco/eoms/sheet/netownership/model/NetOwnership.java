package com.boco.eoms.sheet.netownership.model;

import java.io.Serializable;

/**
 * 
 * @author weichao
 *
 */
public class NetOwnership implements Serializable {

	/**
	 * 主键id
	 */
	private java.lang.String id;

	/**
	 * 网元id 
	 */
	private java.lang.String netId;

	/**
	 * 网元名称
	 */
	private java.lang.String netName;

	/**
	 * 网元类型
	 */
	private java.lang.String netType;

	/**
	 * 地市
	 */
	private java.lang.String city;
	
	private java.lang.String cityId;

	/**
	 * 区县
	 */
	private java.lang.String county;
	private java.lang.String countyId;
	/**
	 * 入库时间
	 */
	private java.util.Date saveTime;

	/**
	 * 创建人
	 */
	private java.lang.String createUserId;

	/**
	 * 创建人所属部门
	 */
	private java.lang.String createDeptId;

	/**
	 * 创建时间
	 */
	private java.util.Date createTime;

	/**
	 * 维护班组ID
	 */
	private java.lang.String teamRoleId;

	/**
	 * 运维中心Id
	 */
	private java.lang.String centerId;

	/**
	 * 删除标志位 ，0:有效,1:删除
	 */
	private java.lang.Integer deleted;

	/**
	 * 存储eoms的地市区县信息
	 */
	private java.lang.String eomsCounty;
	private java.lang.String eomsCountyId;
	private java.lang.String eomsCity;
	private java.lang.String eomsCityId;

	/**
	 * 是否自动移交，0：不自动移交  1：自动移交		 
	 */
	private java.lang.String ifAutotran;
	/**
	 * 抄送对象
	 */
	private java.lang.String ccObject;
	//综资网元名称
	private String netNameByEdis;
	
	private String zhuanye;
	
	public String getNetNameByEdis() {
		return netNameByEdis;
	}

	public void setNetNameByEdis(String netNameByEdis) {
		this.netNameByEdis = netNameByEdis;
	}

	public java.lang.String getCcObject() {
		return ccObject;
	}

	public void setCcObject(java.lang.String ccObject) {
		this.ccObject = ccObject;
	}

	public java.lang.String getIfAutotran() {
		return ifAutotran;
	}

	public void setIfAutotran(java.lang.String ifAutotran) {
		this.ifAutotran = ifAutotran;
	}

	public java.lang.String getEomsCity() {
		return eomsCity;
	}

	public void setEomsCity(java.lang.String eomsCity) {
		this.eomsCity = eomsCity;
	}

	public java.lang.String getEomsCityId() {
		return eomsCityId;
	}

	public void setEomsCityId(java.lang.String eomsCityId) {
		this.eomsCityId = eomsCityId;
	}

	public java.lang.String getEomsCounty() {
		return eomsCounty;
	}

	public void setEomsCounty(java.lang.String eomsCounty) {
		this.eomsCounty = eomsCounty;
	}

	public java.lang.String getEomsCountyId() {
		return eomsCountyId;
	}

	public void setEomsCountyId(java.lang.String eomsCountyId) {
		this.eomsCountyId = eomsCountyId;
	}

	public java.lang.String getCenterId() {
		return centerId;
	}

	public void setCenterId(java.lang.String centerId) {
		this.centerId = centerId;
	}

	public java.lang.String getCity() {
		return city;
	}

	public void setCity(java.lang.String city) {
		this.city = city;
	}

	public java.lang.String getCounty() {
		return county;
	}

	public void setCounty(java.lang.String county) {
		this.county = county;
	}

	public java.lang.String getCreateDeptId() {
		return createDeptId;
	}

	public void setCreateDeptId(java.lang.String createDeptId) {
		this.createDeptId = createDeptId;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(java.lang.String createUserId) {
		this.createUserId = createUserId;
	}

	public java.lang.Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(java.lang.Integer deleted) {
		this.deleted = deleted;
	}

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getNetId() {
		return netId;
	}

	public void setNetId(java.lang.String netId) {
		this.netId = netId;
	}

	public java.lang.String getNetName() {
		return netName;
	}

	public void setNetName(java.lang.String netName) {
		this.netName = netName;
	}

	public java.lang.String getNetType() {
		return netType;
	}

	public void setNetType(java.lang.String netType) {
		this.netType = netType;
	}

	public java.util.Date getSaveTime() {
		return saveTime;
	}

	public void setSaveTime(java.util.Date saveTime) {
		this.saveTime = saveTime;
	}

	public java.lang.String getTeamRoleId() {
		return teamRoleId;
	}

	public void setTeamRoleId(java.lang.String teamRoleId) {
		this.teamRoleId = teamRoleId;
	}

	public java.lang.String getCityId() {
		return cityId;
	}

	public void setCityId(java.lang.String cityId) {
		this.cityId = cityId;
	}

	public java.lang.String getCountyId() {
		return countyId;
	}

	public void setCountyId(java.lang.String countyId) {
		this.countyId = countyId;
	}

	public String getZhuanye() {
		return zhuanye;
	}

	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}

	
}
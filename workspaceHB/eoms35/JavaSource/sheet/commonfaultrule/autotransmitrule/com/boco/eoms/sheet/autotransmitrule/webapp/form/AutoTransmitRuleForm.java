package com.boco.eoms.sheet.autotransmitrule.webapp.form;

import java.io.Serializable;
import java.util.Date;

import com.boco.eoms.base.webapp.form.BaseForm;

public class AutoTransmitRuleForm extends BaseForm implements Serializable{

	protected String id;
	protected String netTypeOne;
	protected String netTypeTwo;
	protected String netTypeThree;
	protected String roleId;
	protected String domainId;
	protected String equipmentFactory;
	protected String faultResponseLevel;
	protected String stopTime;
	public String getDomainId() {
		return domainId;
	}
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	public String getEquipmentFactory() {
		return equipmentFactory;
	}
	public void setEquipmentFactory(String equipmentFactory) {
		this.equipmentFactory = equipmentFactory;
	}
	public String getFaultResponseLevel() {
		return faultResponseLevel;
	}
	public void setFaultResponseLevel(String faultResponseLevel) {
		this.faultResponseLevel = faultResponseLevel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNetTypeOne() {
		return netTypeOne;
	}
	public void setNetTypeOne(String netTypeOne) {
		this.netTypeOne = netTypeOne;
	}
	public String getNetTypeThree() {
		return netTypeThree;
	}
	public void setNetTypeThree(String netTypeThree) {
		this.netTypeThree = netTypeThree;
	}
	public String getNetTypeTwo() {
		return netTypeTwo;
	}
	public void setNetTypeTwo(String netTypeTwo) {
		this.netTypeTwo = netTypeTwo;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

}

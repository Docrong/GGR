package com.boco.eoms.commons.system.dept.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

public class TawPartnersDept extends BaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2429985109375780431L;
	
	private String id;
	private String qualification;//公司资质（字典）
	private String registerFund;//注册资金
	private String companyType;//公司类型（字典）
	private String setupTime;//成立时间
	private String fixAsset;//固定资产
	private String peopleNumber;//企业人数
	private String companyWeb;//公司网页
	private String contacter;//联系人
	private String businessLicense;//营业执照范围
	private String certificationDept;//认证机关
	private String professionalLevel;//专业资质/等级
	private String isUnicomAssociation;//联通关联（字典，是/否 ）
	private String managePeople;//管理人员
	private String seniorTitle;//高级职称
	private String intermediateTitle;//中级职称
	private String juniorTitle;//初级职称
	private String workers;//职工
	private String ownCars;//自有车辆(台)（主从表）
	private String ownInstrument;//自有仪表（主从表）
	private String lines;//线路(公里)（主从表，代维的维护内容）
	private String baseStations;//基站（个）（主从表）
	private String towers;//铁塔（座）（主从表）
	private String microwaves;//微波（跳）（主从表）
	private String powerAndSet;//电源及配套（套）（主从表）
	private String qualificationValidity;//资质有效期
	private String bankAccount;//银行帐号
	private String thirdServiceContract;//第三方服务合同信息
	private String attachName;//附件名
	private String deptId;//部门Id，与TawSystemDept关联字段
	

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getRegisterFund() {
		return registerFund;
	}

	public void setRegisterFund(String registerFund) {
		this.registerFund = registerFund;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getSetupTime() {
		return setupTime;
	}

	public void setSetupTime(String setupTime) {
		this.setupTime = setupTime;
	}

	public String getFixAsset() {
		return fixAsset;
	}

	public void setFixAsset(String fixAsset) {
		this.fixAsset = fixAsset;
	}

	public String getPeopleNumber() {
		return peopleNumber;
	}

	public void setPeopleNumber(String peopleNumber) {
		this.peopleNumber = peopleNumber;
	}

	public String getCompanyWeb() {
		return companyWeb;
	}

	public void setCompanyWeb(String companyWeb) {
		this.companyWeb = companyWeb;
	}

	public String getContacter() {
		return contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getCertificationDept() {
		return certificationDept;
	}

	public void setCertificationDept(String certificationDept) {
		this.certificationDept = certificationDept;
	}

	public String getProfessionalLevel() {
		return professionalLevel;
	}

	public void setProfessionalLevel(String professionalLevel) {
		this.professionalLevel = professionalLevel;
	}

	public String getIsUnicomAssociation() {
		return isUnicomAssociation;
	}

	public void setIsUnicomAssociation(String isUnicomAssociation) {
		this.isUnicomAssociation = isUnicomAssociation;
	}

	public String getManagePeople() {
		return managePeople;
	}

	public void setManagePeople(String managePeople) {
		this.managePeople = managePeople;
	}

	public String getSeniorTitle() {
		return seniorTitle;
	}

	public void setSeniorTitle(String seniorTitle) {
		this.seniorTitle = seniorTitle;
	}

	public String getIntermediateTitle() {
		return intermediateTitle;
	}

	public void setIntermediateTitle(String intermediateTitle) {
		this.intermediateTitle = intermediateTitle;
	}

	public String getJuniorTitle() {
		return juniorTitle;
	}

	public void setJuniorTitle(String juniorTitle) {
		this.juniorTitle = juniorTitle;
	}

	public String getWorkers() {
		return workers;
	}

	public void setWorkers(String workers) {
		this.workers = workers;
	}

	public String getOwnCars() {
		return ownCars;
	}

	public void setOwnCars(String ownCars) {
		this.ownCars = ownCars;
	}

	public String getOwnInstrument() {
		return ownInstrument;
	}

	public void setOwnInstrument(String ownInstrument) {
		this.ownInstrument = ownInstrument;
	}

	public String getLines() {
		return lines;
	}

	public void setLines(String lines) {
		this.lines = lines;
	}

	public String getBaseStations() {
		return baseStations;
	}

	public void setBaseStations(String baseStations) {
		this.baseStations = baseStations;
	}

	public String getTowers() {
		return towers;
	}

	public void setTowers(String towers) {
		this.towers = towers;
	}

	public String getMicrowaves() {
		return microwaves;
	}

	public void setMicrowaves(String microwaves) {
		this.microwaves = microwaves;
	}

	public String getPowerAndSet() {
		return powerAndSet;
	}

	public void setPowerAndSet(String powerAndSet) {
		this.powerAndSet = powerAndSet;
	}

	public String getQualificationValidity() {
		return qualificationValidity;
	}

	public void setQualificationValidity(String qualificationValidity) {
		this.qualificationValidity = qualificationValidity;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getThirdServiceContract() {
		return thirdServiceContract;
	}

	public void setThirdServiceContract(String thirdServiceContract) {
		this.thirdServiceContract = thirdServiceContract;
	}

	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}


	public boolean equals(Object o) {
		if (o instanceof TawPartnersDept) {
			TawPartnersDept tawPartnersDept = (TawPartnersDept) o;
			if (this.id != null || this.id.equals(tawPartnersDept.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

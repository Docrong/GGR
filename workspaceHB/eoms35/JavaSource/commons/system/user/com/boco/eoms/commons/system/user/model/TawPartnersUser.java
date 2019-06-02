package com.boco.eoms.commons.system.user.model;

import java.io.Serializable;
import java.util.Date;


import com.boco.eoms.base.model.BaseObject;

public class TawPartnersUser extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String inCompany;//所属代维公司（自动填写）
	private String inCity;//所在地市（字典）
	private String inDistrict;//所在区县（字典，理论上与所属地市联动）
	private String education;//学历（字典，初中，高中，大专，本科，硕士，博士）
	private String birthday;//出生年月
	private String techTitle;//技术职称
	private String IDNumber;//身份证号码
	private String postState;//岗位状态 职称（高、中、低。需要在后台修改关联代维部门的高中睇职称人数）？
	private String photoInfo;//照片信息 （公共）
	private String staffLabel;//员工标号
	private String isMarried;//婚否（字典，是否，该字典已经有）
	private String jobType;//工作类别（字典）
	private String post;//工作岗位（字典）
	private String graduateSchool;//毕业学校
	private String specialties;//所学专业
	private String politicalStatus;//政治面貌（字典，群众，团员，党员）
	private String workPeriod;//工作时间
	private String personDiscription;//个人说明
	private String startWorkTime;//参加工作时间
	private String toDeptTime;//参加本单位工作时间
	private String certificationInfo;//认证信息
	private String userid;//用户id，与TawSystemUser关联

	public boolean equals(Object o) {
		if (o instanceof TawPartnersUser) {
			TawPartnersUser tawPartnersUser = (TawPartnersUser) o;
			if (this.id != null || this.id.equals(tawPartnersUser.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}


	public String getInCompany() {
		return inCompany;
	}

	public void setInCompany(String inCompany) {
		this.inCompany = inCompany;
	}

	public String getInCity() {
		return inCity;
	}

	public void setInCity(String inCity) {
		this.inCity = inCity;
	}

	public String getInDistrict() {
		return inDistrict;
	}

	public void setInDistrict(String inDistrict) {
		this.inDistrict = inDistrict;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getTechTitle() {
		return techTitle;
	}

	public void setTechTitle(String techTitle) {
		this.techTitle = techTitle;
	}

	public String getIDNumber() {
		return IDNumber;
	}

	public void setIDNumber(String number) {
		IDNumber = number;
	}

	public String getPostState() {
		return postState;
	}

	public void setPostState(String postState) {
		this.postState = postState;
	}

	public String getPhotoInfo() {
		return photoInfo;
	}

	public void setPhotoInfo(String photoInfo) {
		this.photoInfo = photoInfo;
	}

	public String getStaffLabel() {
		return staffLabel;
	}

	public void setStaffLabel(String staffLabel) {
		this.staffLabel = staffLabel;
	}

	public String getIsMarried() {
		return isMarried;
	}

	public void setIsMarried(String isMarried) {
		this.isMarried = isMarried;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public String getSpecialties() {
		return specialties;
	}

	public void setSpecialties(String specialties) {
		this.specialties = specialties;
	}

	public String getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public String getWorkPeriod() {
		return workPeriod;
	}

	public void setWorkPeriod(String workPeriod) {
		this.workPeriod = workPeriod;
	}

	public String getPersonDiscription() {
		return personDiscription;
	}

	public void setPersonDiscription(String personDiscription) {
		this.personDiscription = personDiscription;
	}

	
	public String getStartWorkTime() {
		return startWorkTime;
	}


	public void setStartWorkTime(String startWorkTime) {
		this.startWorkTime = startWorkTime;
	}


	public String getToDeptTime() {
		return toDeptTime;
	}


	public void setToDeptTime(String toDeptTime) {
		this.toDeptTime = toDeptTime;
	}


	public String getCertificationInfo() {
		return certificationInfo;
	}

	public void setCertificationInfo(String certificationInfo) {
		this.certificationInfo = certificationInfo;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


}

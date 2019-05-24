package com.boco.eoms.commons.system.user.webapp.form;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * Generated by XDoclet/actionform. This class can be further processed with
 * XDoclet/webdoclet/strutsconfigxml and XDoclet/webdoclet/strutsvalidationxml.
 * 
 * @struts.form name="tawSystemUserForm"
 */
public class TawSystemUserForm extends BaseForm implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6264667550565571985L;

	/**
	 * 为修改个人信息时判定是否修改密码
	 */
	private String newPassword;

	protected String cptroomid;

	protected String cptroomname;

	protected String deptid;

	protected String deptname;

	protected String email;

	protected String familyaddress;

	protected String fax;

	protected String id;

	protected String mobile;

	protected String operuserid;

	protected String phone;

	protected String remark;

	protected String sex;

	protected String userdegree;

	protected String userid;//与TawSystemUser关联

	protected String username;

	protected String operremotip;

	protected String savetime;

	protected String updatetime;

	protected String password;

	protected String deleted;

	protected String isfullemploy;

	protected String isrest;

	protected String portalrolename;

	protected String olduserid;
	
    //判断是否是代维公司
    private String isPartners;
    
    //以下字段是代维公司人员特有的字段，TawPartnersUser
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
	
	private FormFile accessoryName; //批量录入文件

	/**
	 * 帐号锁定
	 */
	protected boolean accountLocked;

	/**
	 * 帐号过期
	 */
	protected boolean enabled;

	/**
	 * @return the accountLocked
	 */
	public boolean isAccountLocked() {
		return accountLocked;
	}

	/**
	 * @param accountLocked
	 *            the accountLocked to set
	 */
	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	/**
	 * @return Returns the enabled.
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            The enabled to set.
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * 
	 * @return
	 */
	public String getDeleted() {
		return deleted;
	}

	/**
	 * 
	 * @param deleted
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	/**
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** Default empty constructor. */
	public TawSystemUserForm() {
	}

	public String getCptroomid() {
		return this.cptroomid;
	}

	/**
	 */

	public void setCptroomid(String cptroomid) {
		this.cptroomid = cptroomid;
	}

	public String getCptroomname() {
		return this.cptroomname;
	}

	/**
	 */

	public void setCptroomname(String cptroomname) {
		this.cptroomname = cptroomname;
	}

	public String getDeptid() {
		return this.deptid;
	}

	/**
	 */

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return this.deptname;
	}

	/**
	 */

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 */

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFamilyaddress() {
		return this.familyaddress;
	}

	/**
	 */

	public void setFamilyaddress(String familyaddress) {
		this.familyaddress = familyaddress;
	}

	public String getFax() {
		return this.fax;
	}

	/**
	 */

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getId() {
		return this.id;
	}

	/**
	 */

	public void setId(String id) {
		this.id = id;
	}

	public String getMobile() {
		return this.mobile;
	}

	/**
	 */

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOperuserid() {
		return this.operuserid;
	}

	/**
	 */

	public void setOperuserid(String operuserid) {
		this.operuserid = operuserid;
	}

	public String getPhone() {
		return this.phone;
	}

	/**
	 */

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return this.remark;
	}

	/**
	 */

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSex() {
		return this.sex;
	}

	/**
	 */

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserdegree() {
		return this.userdegree;
	}

	/**
	 */

	public void setUserdegree(String userdegree) {
		this.userdegree = userdegree;
	}

	public String getUserid() {
		return this.userid;
	}

	/**
	 */

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	/**
	 */

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOperremotip() {
		return this.operremotip;
	}

	/**
	 */

	public void setOperremotip(String operremotip) {
		this.operremotip = operremotip;
	}

	public String getSavetime() {
		return this.savetime;
	}

	/**
	 */

	public void setSavetime(String savetime) {
		this.savetime = savetime;
	}

	public String getUpdatetime() {
		return this.updatetime;
	}

	/**
	 */

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	/*
	 * To add non XDoclet-generated methods, create a file named
	 * xdoclet-TawSystemUserForm.java containing the additional code and place
	 * it in your metadata/web directory.
	 */
	/**
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// reset any boolean data types to false

	}

	public String getIsfullemploy() {
		return isfullemploy;
	}

	public void setIsfullemploy(String isfullemploy) {
		this.isfullemploy = isfullemploy;
	}

	public String getIsrest() {
		return isrest;
	}

	public void setIsrest(String isrest) {
		this.isrest = isrest;
	}

	public String getPortalrolename() {
		return portalrolename;
	}

	public void setPortalrolename(String portalrolename) {
		this.portalrolename = portalrolename;
	}

	public String getOlduserid() {
		return olduserid;
	}

	public void setOlduserid(String olduserid) {
		this.olduserid = olduserid;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword
	 *            the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getIsPartners() {
		return isPartners;
	}

	public void setIsPartners(String isPartners) {
		this.isPartners = isPartners;
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


	public String getCertificationInfo() {
		return certificationInfo;
	}

	public void setCertificationInfo(String certificationInfo) {
		this.certificationInfo = certificationInfo;
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

	public FormFile getAccessoryName() {
		return accessoryName;
	}

	public void setAccessoryName(FormFile accessoryName) {
		this.accessoryName = accessoryName;
	}

	
	
}
package com.boco.eoms.commons.system.myinfo.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawSystemMyinfoForm extends BaseForm implements java.io.Serializable{
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

	protected String userid;

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
	
	protected String oldPassword;
	protected String subRoleid;
	protected String roleType;
	/**
	 * 帐号锁定
	 */
	protected boolean accountLocked;

	/**
	 * 帐号过期
	 */
	protected boolean enabled;

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public String getCptroomid() {
		return cptroomid;
	}

	public void setCptroomid(String cptroomid) {
		this.cptroomid = cptroomid;
	}

	public String getCptroomname() {
		return cptroomname;
	}

	public void setCptroomname(String cptroomname) {
		this.cptroomname = cptroomname;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFamilyaddress() {
		return familyaddress;
	}

	public void setFamilyaddress(String familyaddress) {
		this.familyaddress = familyaddress;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getOlduserid() {
		return olduserid;
	}

	public void setOlduserid(String olduserid) {
		this.olduserid = olduserid;
	}

	public String getOperremotip() {
		return operremotip;
	}

	public void setOperremotip(String operremotip) {
		this.operremotip = operremotip;
	}

	public String getOperuserid() {
		return operuserid;
	}

	public void setOperuserid(String operuserid) {
		this.operuserid = operuserid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPortalrolename() {
		return portalrolename;
	}

	public void setPortalrolename(String portalrolename) {
		this.portalrolename = portalrolename;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSavetime() {
		return savetime;
	}

	public void setSavetime(String savetime) {
		this.savetime = savetime;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getUserdegree() {
		return userdegree;
	}

	public void setUserdegree(String userdegree) {
		this.userdegree = userdegree;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSubRoleid() {
		return subRoleid;
	}

	public void setSubRoleid(String subRoleid) {
		this.subRoleid = subRoleid;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
}

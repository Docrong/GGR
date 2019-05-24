package com.boco.eoms.commons.system.session.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.boco.eoms.base.webapp.form.BaseForm;
import com.boco.eoms.common.util.StaticMethod;

public class TawSystemSessionForm extends BaseForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 741578523486383940L;

	/**
	 * 用户表id（主键），为portal登陆使用，由于portal使用主键与业务有关，故记入用户表id主键做为登陆条件
	 */
	private String id;

	private String userid;

	private String username;

	private String deptid;

	private Integer deptpriid;

	private String deptname;

	private String privid;

	private boolean isHavePriv;// 是否有权限

	private String privname;

	private String roleid;

	private String rolename;

	private boolean isadmin;

	private String romteaddr;

	private String password;

	private ArrayList rolelist;

	private String contactMobile;
	
	private String attribute1;
	private String attribute2;
	private String originPassword;

	/**
	 * 值班号
	 */
	private String workSerial; // add by wangheqi for duty dutyId

	/**
	 * 物理地址
	 */
	private String realPath; // add by wangheqi for duty

	/**
	 * 是否为值班长
	 */
	private boolean isDutyMaster = false; // add by wangheqi for duty

	/**
	 * 值班开始时间
	 */
	private String startDuty;

	/**
	 * 值班结束时间
	 */
	private String endDuty;

	/**
	 * 机房id
	 */
	private String roomId;

	/**
	 * 机房name
	 */
	private String roomname;

	/**
	 * 代理人集合
	 */
	private List stubUserList;

	/**
	 * 值班时间段
	 */
	private String workSerialTime;

	public String getWorkSerialTime() {
		return workSerialTime;
	}

	public void setWorkSerialTime(String workSerialTime) {
		this.workSerialTime = workSerialTime;
	}

	public List getStubUserList() {
		return stubUserList;
	}

	public void setStubUserList(List stubUserList) {
		this.stubUserList = stubUserList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRomteaddr() {
		return romteaddr;
	}

	public void setRomteaddr(String romteaddr) {
		this.romteaddr = romteaddr;
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

	public String getPrivid() {
		return privid;
	}

	public void setPrivid(String privid) {
		this.privid = privid;
	}

	public String getPrivname() {
		return privname;
	}

	public void setPrivname(String privname) {
		this.privname = privname;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
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

	/**
	 * 判断是否为管理员
	 * 
	 * @return 是否为管理员
	 */
	public boolean isAdmin() {
		return isadmin;
	}

	public void setIsadmin(boolean isadmin) {
		this.isadmin = isadmin;
	}

	/**
	 * @return Returns the deptpriid.
	 */
	public Integer getDeptpriid() {
		return deptpriid;
	}

	/**
	 * @param deptpriid
	 *            The deptpriid to set.
	 */
	public void setDeptpriid(Integer deptpriid) {
		this.deptpriid = deptpriid;
	}

	/**
	 * @return Returns the rolelist.
	 */
	public ArrayList getRolelist() {
		return rolelist;
	}

	/**
	 * @param rolelist
	 *            The rolelist to set.
	 */
	public void setRolelist(ArrayList rolelist) {
		this.rolelist = rolelist;
	}

	/**
	 * @return Returns the contactMobile.
	 */
	public String getContactMobile() {
		return contactMobile;
	}

	/**
	 * @param contactMobile
	 *            The contactMobile to set.
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getEndDuty() {
		return endDuty;
	}

	public void setEndDuty(String endDuty) {
		this.endDuty = endDuty;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getStartDuty() {
		return startDuty;
	}

	public void setStartDuty(String startDuty) {
		this.startDuty = startDuty;
	}

	public String getWorkSerial() {
		if (StaticMethod.dbNull2String(this.workSerial).equals("")) {
			this.setWorkSerial("0");
		}
		return workSerial;
	}

	public void setWorkSerial(String workSerial) {
		this.workSerial = workSerial;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public boolean getIsDutyMaster() {
		return isDutyMaster;
	}

	public void setIsDutyMaster(boolean isDutyMaster) {
		this.isDutyMaster = isDutyMaster;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public boolean isHavePriv() {
		return isHavePriv;
	}

	public void setHavePriv(boolean isHavePriv) {
		this.isHavePriv = isHavePriv;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getOriginPassword() {
		return originPassword;
	}

	public void setOriginPassword(String originPassword) {
		this.originPassword = originPassword;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

}

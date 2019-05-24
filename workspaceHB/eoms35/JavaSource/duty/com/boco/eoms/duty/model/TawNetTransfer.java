package com.boco.eoms.duty.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;

public class TawNetTransfer extends BaseObject{

  /**
	 * author 冯少宏
	 */
  private static final long serialVersionUID = 707639488872188702L;
  
  private String id;
  private String originateDept;//发起部门
  private String originater;//发起人
  private Date date;//当前时间
  private String dispatchNum;//发文编号
  private String dutyMan;//责任人
  private String state;//状态
  private String contact;//联系方式
  private String speciality;//专业名称
  private String equipmentDept;//设备所属部门
  private String referNet;//涉及网元
  private String title;//主题
  private String content;//网调内容
  private String remark;//备注
  private String accessory;//附件
  private String hasub;//是否有子处理 0 没有，1 有
public String getAccessory() {
	return accessory;
}
public void setAccessory(String accessory) {
	this.accessory = accessory;
}
public String getContact() {
	return contact;
}
public void setContact(String contact) {
	this.contact = contact;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getDispatchNum() {
	return dispatchNum;
}
public void setDispatchNum(String dispatchNum) {
	this.dispatchNum = dispatchNum;
}
public String getDutyMan() {
	return dutyMan;
}
public void setDutyMan(String dutyMan) {
	this.dutyMan = dutyMan;
}
public String getEquipmentDept() {
	return equipmentDept;
}
public void setEquipmentDept(String equipmentDept) {
	this.equipmentDept = equipmentDept;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getOriginateDept() {
	return originateDept;
}
public void setOriginateDept(String originateDept) {
	this.originateDept = originateDept;
}
public String getOriginater() {
	return originater;
}
public void setOriginater(String originater) {
	this.originater = originater;
}
public String getReferNet() {
	return referNet;
}
public void setReferNet(String referNet) {
	this.referNet = referNet;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public String getSpeciality() {
	return speciality;
}
public void setSpeciality(String speciality) {
	this.speciality = speciality;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public boolean equals(Object o) {
	// TODO 自动生成方法存根
	return false;
}
/**
 * @return hasub
 */
public String getHasub() {
	return hasub;
}
/**
 * @param hasub 要设置的 hasub
 */
public void setHasub(String hasub) {
	this.hasub = hasub;
}
  
}
package com.boco.eoms.duty.webapp.form;

import java.io.Serializable;
import java.util.Date;
import com.boco.eoms.base.webapp.form.BaseForm;

public class TawNetTransferForm extends BaseForm implements Serializable {

	  /**
	 * 
	 */
	private static final long serialVersionUID = -5477975540049331788L;
	private String id;
	  private String originateDept;//发起部门
	  private String originater;//发起人
	  private String date;//当前时间
	  private String dispatchNum;//发文编号
	  private String dutyMan;//责任人
	  private String state;//状态
	  private String contact;//责任人联系方式
	  private String speciality;//专业名称
	  private String equipmentDept;//设备所属部门
	  private String referNet;//涉及网元
	  private String title;//主题
	  private String content;//网调内容
	  private String remark;//备注
	  private String accessory;//附件
	  private String startTime;
	  private String endTime;
	  private String hasub;
	  
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
	/**
	 * @return endTime
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * @param endTime 要设置的 endTime
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * @return startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime 要设置的 startTime
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return accessory
	 */
	public String getAccessory() {
		return accessory;
	}
	/**
	 * @param accessory 要设置的 accessory
	 */
	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}
	/**
	 * @return contact
	 */
	public String getContact() {
		return contact;
	}
	/**
	 * @param contact 要设置的 contact
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	/**
	 * @return content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content 要设置的 content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date 要设置的 date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return dispatchNum
	 */
	public String getDispatchNum() {
		return dispatchNum;
	}
	/**
	 * @param dispatchNum 要设置的 dispatchNum
	 */
	public void setDispatchNum(String dispatchNum) {
		this.dispatchNum = dispatchNum;
	}
	/**
	 * @return dutyMan
	 */
	public String getDutyMan() {
		return dutyMan;
	}
	/**
	 * @param dutyMan 要设置的 dutyMan
	 */
	public void setDutyMan(String dutyMan) {
		this.dutyMan = dutyMan;
	}
	/**
	 * @return equipmentDept
	 */
	public String getEquipmentDept() {
		return equipmentDept;
	}
	/**
	 * @param equipmentDept 要设置的 equipmentDept
	 */
	public void setEquipmentDept(String equipmentDept) {
		this.equipmentDept = equipmentDept;
	}
	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id 要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return originateDept
	 */
	public String getOriginateDept() {
		return originateDept;
	}
	/**
	 * @param originateDept 要设置的 originateDept
	 */
	public void setOriginateDept(String originateDept) {
		this.originateDept = originateDept;
	}
	/**
	 * @return originater
	 */
	public String getOriginater() {
		return originater;
	}
	/**
	 * @param originater 要设置的 originater
	 */
	public void setOriginater(String originater) {
		this.originater = originater;
	}
	/**
	 * @return referNet
	 */
	public String getReferNet() {
		return referNet;
	}
	/**
	 * @param referNet 要设置的 referNet
	 */
	public void setReferNet(String referNet) {
		this.referNet = referNet;
	}
	/**
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark 要设置的 remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return speciality
	 */
	public String getSpeciality() {
		return speciality;
	}
	/**
	 * @param speciality 要设置的 speciality
	 */
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	/**
	 * @return state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state 要设置的 state
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title 要设置的 title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}

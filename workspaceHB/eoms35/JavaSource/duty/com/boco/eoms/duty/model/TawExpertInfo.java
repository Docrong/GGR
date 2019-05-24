package com.boco.eoms.duty.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;

public class TawExpertInfo extends BaseObject{

  /**
	 * author 冯少宏
	 */
  
  private String id;
  private String expertId;//专家id
  private String adress;//
  private Date addTime;//
  private String creater;//
  private String telephone;//
  private String email;//
  private String mobile;//联系方式
  private String post;//专家职位
  private String dept;//所属部门
  private String beGoodAt;//特长
  private String resume;//
  private String expertName;
public String getExpertName() {
	return expertName;
}
public void setExpertName(String expertName) {
	this.expertName = expertName;
}
public boolean equals(Object o) {
	// TODO 自动生成方法存根
	return false;
}
/**
 * @return addTime
 */
public Date getAddTime() {
	return addTime;
}
/**
 * @param addTime 要设置的 addTime
 */
public void setAddTime(Date addTime) {
	this.addTime = addTime;
}
/**
 * @return adress
 */
public String getAdress() {
	return adress;
}
/**
 * @param adress 要设置的 adress
 */
public void setAdress(String adress) {
	this.adress = adress;
}
/**
 * @return beGoodAt
 */
public String getBeGoodAt() {
	return beGoodAt;
}
/**
 * @param beGoodAt 要设置的 beGoodAt
 */
public void setBeGoodAt(String beGoodAt) {
	this.beGoodAt = beGoodAt;
}
/**
 * @return creater
 */
public String getCreater() {
	return creater;
}
/**
 * @param creater 要设置的 creater
 */
public void setCreater(String creater) {
	this.creater = creater;
}
/**
 * @return dept
 */
public String getDept() {
	return dept;
}
/**
 * @param dept 要设置的 dept
 */
public void setDept(String dept) {
	this.dept = dept;
}
/**
 * @return email
 */
public String getEmail() {
	return email;
}
/**
 * @param email 要设置的 email
 */
public void setEmail(String email) {
	this.email = email;
}
/**
 * @return expertId
 */
public String getExpertId() {
	return expertId;
}
/**
 * @param expertId 要设置的 expertId
 */
public void setExpertId(String expertId) {
	this.expertId = expertId;
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
 * @return mobile
 */
public String getMobile() {
	return mobile;
}
/**
 * @param mobile 要设置的 mobile
 */
public void setMobile(String mobile) {
	this.mobile = mobile;
}
/**
 * @return post
 */
public String getPost() {
	return post;
}
/**
 * @param post 要设置的 post
 */
public void setPost(String post) {
	this.post = post;
}
/**
 * @return resume
 */
public String getResume() {
	return resume;
}
/**
 * @param resume 要设置的 resume
 */
public void setResume(String resume) {
	this.resume = resume;
}
/**
 * @return telephone
 */
public String getTelephone() {
	return telephone;
}
/**
 * @param telephone 要设置的 telephone
 */
public void setTelephone(String telephone) {
	this.telephone = telephone;
}

  
}
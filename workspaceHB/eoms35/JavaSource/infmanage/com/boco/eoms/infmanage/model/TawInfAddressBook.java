package com.boco.eoms.infmanage.model;

import com.boco.eoms.common.util.StaticVariable;

import java.util.Collection;

public class TawInfAddressBook {
  private int id;

  private String deptName;

  private String company;

  private String specialty;

  private String duty;

  private String name;

  private int groupId;

  private String groupName;

  private String userId;

  private String mobile;

  private String officeTel;

  private String smart;

  private String email;

  private String remark;

  private String recType;

  private Collection groupTypeCollection;

  private Collection recTypeCollection;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCompany() {
    return company;
  }

  public String getDuty() {
    return duty;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public void setDuty(String duty) {
    this.duty = duty;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public String getGroupName() {
    return groupName;
  }

  public Collection getGroupTypeCollection() {
    return groupTypeCollection;
  }

  public void setGroupTypeCollection(Collection groupTypeCollection) {
    this.groupTypeCollection = groupTypeCollection;
  }

  public String getMobile() {
    return mobile;
  }

  public String getName() {
    return name;
  }

  public String getOfficeTel() {
    return officeTel;
  }

  public String getRecType() {
    return recType;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public void setRecType(String recType) {
    this.recType = recType;
  }

  public void setOfficeTel(String officeTel) {
    this.officeTel = officeTel;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public void setRecTypeCollection(Collection recTypeCollection) {
    this.recTypeCollection = recTypeCollection;
  }

  public void setSmart(String smart) {
    this.smart = smart;
  }

  public void setSpecialty(String specialty) {
    this.specialty = specialty;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Collection getRecTypeCollection() {
    return recTypeCollection;
  }

  public String getSmart() {
    return smart;
  }

  public String getSpecialty() {
    return specialty;
  }

  public String getUserId() {
    return userId;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }
}

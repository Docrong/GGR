//---------------------------------------------------------
// Application: E_OMS
// Author     : Jerry
// File       : TawApparatusroom.java
//
// Copyright 2003 boco
// Generated at Tue Mar 25 17:18:02 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.duty.model;
import com.boco.eoms.common.util.StaticVariable;

public class TawApparatusroom implements java.io.Serializable{
  private String roomName="";
  private String manager="";
  private String tempManager="";
  private int deptId=StaticVariable.defnull;
  private String phone="";
  private String mobile="";
  private String fax="";
  private String address="";
  private int deleted;
  private String notes="";
  private int id=StaticVariable.defnull;
  private String deptName="";
  private String endtime="";
  private String manName="";
  private String tempName="";

  public String getRoomName() {
    return roomName;
  }
  public String getManager() {
    return manager;
  }
  public String getTempManager() {
    return tempManager;
  }
  public int getDeptId() {
    return deptId;
  }
  public String getEndtime() {
    return endtime;
  }
  public String getPhone() {
    return phone;
  }
  public String getMobile() {
    return mobile;
  }
  public String getFax() {
    return fax;
  }
  public String getAddress() {
    return address;
  }
  public int getDeleted() {
    return deleted;
  }
  public String getNotes() {
    return notes;
  }
  public int getId() {
    return id;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }
  public void setManager(String manager) {
    this.manager = manager;
  }
  public void setTempManager(String tempManager) {
    this.tempManager = tempManager;
  }
  public void setDeptId(int deptId) {
    this.deptId = deptId;
  }
  public void setEndtime(String endtime) {
    this.endtime = endtime;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  public void setFax(String fax) {
    this.fax = fax;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public void setDeleted(int deleted) {
    this.deleted = deleted;
  }
  public void setNotes(String notes) {
    this.notes = notes;
  }
  public void setId(int id) {
    this.id = id;
  }
  public String getDeptName() {
    return deptName;
  }
  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }
  public String getManName() {
    return manName;
  }
  public void setManName(String manName) {
    this.manName = manName;
  }
  public String getTempName() {
    return tempName;
  }
  public void setTempName(String tempName) {
    this.tempName = tempName;
  }
}

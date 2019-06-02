//---------------------------------------------------------
// Application: E_OMS
// Author     : Jerry
// File       : SaveSessionBean.java
//
// Copyright 2003 boco
// Generated at Thu Mar 27 10:15:57 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.common.model;

public class SaveSessionBean {
  private int wrf_RoomID;
  private String wrf_UserName;
  private int wrf_DeptID;
  private String wrf_DeptName;
  private boolean isDutyMaster;
  private int workSerial;
  private int dbType;
  private String wrf_UserID;
  private String wrf_UserPwd;
  private String realPath = "";
  private String wrf_RoomName ;
  private String  workSerialTime ;

  public int getWrf_RoomID() {
    return wrf_RoomID;
  }
  public String getWrf_UserName() {
    return wrf_UserName;
  }
  public int getWrf_DeptID() {
    return wrf_DeptID;
  }
  public String getWrf_DeptName() {
    return wrf_DeptName;
  }
  public boolean getIsDutyMaster() {
    return isDutyMaster;
  }
  public int getWorkSerial() {
    return workSerial;
  }
  public int getDbType() {
    return dbType;
  }
  public String getWrf_UserID() {
    return wrf_UserID;
  }
  public String getRealPath(){
   return realPath;
 }
  public String getWrf_RoomName(){
    return wrf_RoomName;
  }
  public String getWorkSerialTime(){
     return workSerialTime;
  }


  public void setWrf_RoomID(int wrf_RoomID) {
    this.wrf_RoomID = wrf_RoomID;
  }
  public void setWrf_UserName(String wrf_UserName) {
    this.wrf_UserName = wrf_UserName;
  }
  public void setWrf_DeptID(int wrf_DeptID) {
    this.wrf_DeptID = wrf_DeptID;
  }
  public void setWrf_DeptName(String wrf_DeptName) {
    this.wrf_DeptName = wrf_DeptName;
  }
  public void setIsDutyMaster(boolean isDutyMaster) {
    this.isDutyMaster = isDutyMaster;
  }
  public void setWorkSerial(int workSerial) {
    this.workSerial = workSerial;
  }
  public void setDbType(int dbType) {
    this.dbType = dbType;
  }
  public void setWrf_UserID(String wrf_UserID) {
    this.wrf_UserID = wrf_UserID;
  }
  public String getWrf_UserPwd() {
    return wrf_UserPwd;
  }
  public void setWrf_UserPwd(String wrf_UserPwd) {
    this.wrf_UserPwd = wrf_UserPwd;
  }
  public void setRealPath(String realPath)
  {
    this.realPath=realPath;
  }
  public void  setWrf_RoomName(String wrf_RoomName){
    this.wrf_RoomName = wrf_RoomName;
  }
  public void  setWorkSerialTime(String workSerialTime){
    this.workSerialTime = workSerialTime;
  }

}

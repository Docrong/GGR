//---------------------------------------------------------
//Application: E_OMS
//Author     : Jerry
//File       : SaveSessionBeanForm.java
//
//Copyright 2003 boco
//
//Generated at Thu Mar 27 10:15:57 CST 2003
//using Karapan Sapi Struts Generator
//Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.base.webapp.form;

import java.util.List;

import org.apache.struts.validator.*;


public class SaveSessionBeanForm extends ValidatorForm {
private int wrf_RoomID = 0; //机房ID
private String wrf_UserName = ""; //用户名称
private int wrf_DeptID = 0; //部门ID
private String wrf_DeptName = ""; //部门名称
private boolean isDutyMaster = false; //是否是值班人员
private int workSerial = 0;
private int dbType = 0; //数据库类型
private String wrf_UserID = ""; //用户ID
private String wrf_UserPwd =""; //用户密码
private String realPath = ""; //物理地址
private String wrf_RoomName = "无"; //机房名称
private String  workSerialTime = "非值班";
private String accessIp; //IP地址
private List stubUserList; //代理人员
private String positionName; //角色名称。预留，目前不用
private int orgId = -1; //角色ID。预留，目前不用
private String userStatus; //用户状态
private List postList; //角色列表
private String flowSystemName; //系统类别名称

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
public String getAccessIp() {
 return accessIp;
}
public void setAccessIp(String accessIp) {
 this.accessIp = accessIp;
}
public List getStubUserList() {
 return stubUserList;
}
public void setStubUserList(List stubUserList) {
 this.stubUserList = stubUserList;
}
public String getPositionName() {
 return positionName;
}
public void setPositionName(String positionName) {
 this.positionName = positionName;
}
public int getOrgId() {
 return orgId;
}
public List getPostList() {
return postList;
}

public String getUserStatus() {
return userStatus;
}

public String getFlowSystemName() {
 return flowSystemName;
}

public void setOrgId(int orgId) {
 this.orgId = orgId;
}

public SaveSessionBeanForm() {
}
public void setPostList(List postList) {
this.postList = postList;
}
public void setUserStatus(String userStatus) {
 this.userStatus = userStatus;
}

public void setFlowSystemName(String flowSystemName) {
 this.flowSystemName = flowSystemName;
}

}

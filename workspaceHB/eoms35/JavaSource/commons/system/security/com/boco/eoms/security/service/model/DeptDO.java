/**
 * Copyright \u00AE 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-15         created
 */

package com.boco.eoms.security.service.model;

import java.io.Serializable;
import java.security.Principal;
import java.util.Vector;
//import com.boco.eoms.jbzl.model.*;
import com.boco.eoms.common.util.StaticVariable;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object </p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class DeptDO 
    implements Principal, Serializable, Comparable {

  private String deptName = "";
  private int parentDept = StaticVariable.defnull;
  private String manager = "";
  private String tempManager = "";
  private String deptType = "";
  private String phone = "";
  private String mobile = "";
  private String fax = "";
  private int deleted;
  private String notes = "";
  private int ordercode = StaticVariable.defnull;
  private int regionflag = StaticVariable.defnull;
  private int deptId = StaticVariable.defnull;
  private String stopTime = "";
  private String parentName = "";
  private String id = "";
  private String singleId = "";

  private String departmentID = "";
  private String comments = "";
  private String teleNumber = "";
  private String email = "";
  private String regionID = "";
  private String specialFlag = "";
  private String regionName = "";
  private String departmentParentID = "";
  private Vector roleList = new Vector();
  private Vector rangeList = new Vector();
  private Vector userList = new Vector();

  public String getDepartmentParentID() {
    return departmentParentID;
  }

  public void setDepartmentParentID(String departmentParentID) {
    this.departmentParentID = departmentParentID;
  }

  public String getDepartmentID() {
    return departmentID;
  }

  public void setDepartmentID(String departmentID) {
    this.departmentID = departmentID;
  }

  public String getName() {
    return deptName;
  }

  public void setName(String name) {
    this.deptName = name;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public String getTeleNumber() {
    return teleNumber;
  }

  public void setTeleNumber(String teleNumber) {
    this.teleNumber = teleNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRegionID() {
    return regionID;
  }

  public void setRegionID(String regionID) {
    this.regionID = regionID;
  }

  public String getSpecialFlag() {
    return specialFlag;
  }

  public void setSpecialFlag(String specialFlag) {
    this.specialFlag = specialFlag;
  }

  public String getComments() {
    return this.comments;
  }

  public void setComments(String newComments) {
    this.comments = newComments;
  }

  public Vector getRoleList() {
    return this.roleList;
  }

  public void setRoleList(Vector newRoleList) {
    this.roleList = newRoleList;
  }

  public Vector getUserList() {
    return this.userList;
  }

  public void setUserList(Vector newUserList) {
    this.userList = newUserList;
  }

  public Vector getRangeList() {
    return this.rangeList;
  }

  public void setRangeList(Vector newRangeList) {
    this.rangeList = newRangeList;
  }

  public String getRegionName() {
    return this.regionName;
  }

  public void setRegionName(String newRegionName) {
    this.regionName = newRegionName;
  }

  public int hashCode() {
    return this.departmentID.hashCode();
  }

  public boolean equals(Object obj) {
    DeptDO rdo = (DeptDO) obj;
    if (rdo.hashCode() == this.hashCode()) {
      return true;
    }
    else {
      return false;
    }
  }

  public String toString() {
    return  "Dept Name: " + getName() ;
  }

  public int compareTo(Object o) {
    DeptDO d = (DeptDO) o;

    return this.getName().compareTo(d.getName());
  }
  public int getDeleted() {
    return deleted;
  }
  public int getDeptId() {
    return deptId;
  }
  public String getDeptType() {
    return deptType;
  }
  public String getFax() {
    return fax;
  }
  public String getId() {
    return id;
  }
  public String getManager() {
    return manager;
  }
  public String getMobile() {
    return mobile;
  }
  public String getNotes() {
    return notes;
  }
  public int getOrdercode() {
    return ordercode;
  }
  public int getParentDept() {
    return parentDept;
  }
  public String getParentName() {
    return parentName;
  }
  public String getPhone() {
    return phone;
  }
  public void setDeleted(int deleted) {
    this.deleted = deleted;
  }
  public void setDeptId(int deptId) {
    this.deptId = deptId;
  }
  public void setDeptType(String deptType) {
    this.deptType = deptType;
  }
  public void setFax(String fax) {
    this.fax = fax;
  }
  public void setId(String id) {
    this.id = id;
  }
  public void setManager(String manager) {
    this.manager = manager;
  }
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  public void setNotes(String notes) {
    this.notes = notes;
  }
  public void setOrdercode(int ordercode) {
    this.ordercode = ordercode;
  }
  public void setParentDept(int parentDept) {
    this.parentDept = parentDept;
  }
  public void setParentName(String parentName) {
    this.parentName = parentName;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public void setRegionflag(int regionflag) {
    this.regionflag = regionflag;
  }
  public int getRegionflag() {
    return regionflag;
  }
  public String getSingleId() {
    return singleId;
  }
  public String getStopTime() {
    return stopTime;
  }
  public void setSingleId(String singleId) {
    this.singleId = singleId;
  }
  public void setStopTime(String stopTime) {
    this.stopTime = stopTime;
  }
  public String getTempManager() {
    return tempManager;
  }
  public void setTempManager(String tempManager) {
    this.tempManager = tempManager;
  }
}
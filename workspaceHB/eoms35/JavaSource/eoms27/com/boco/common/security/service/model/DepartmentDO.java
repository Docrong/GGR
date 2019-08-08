/**
 * Copyright \u00AE 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-15         created
 */

package com.boco.common.security.service.model;

import java.io.Serializable;
import java.security.Principal;
import java.util.Vector;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object </p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 *
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class DepartmentDO
        implements Principal, Serializable, Comparable {

    private String departmentID = "";
    private String name = "";
    private String comments = "";
    private String teleNumber = "";
    private String email = "";
    private String regionID = "";
    private String specialFlag = "";
    private String regionName = "";
    //    -------------------------------------------------------------
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        DepartmentDO rdo = (DepartmentDO) obj;
        if (rdo.hashCode() == this.hashCode()) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "Department ID: " + getDepartmentID() + "\n" +
                "Department Name: " + getName() + "\n" +
                "Description: " + getComments() + "\n" +
                "Tel Number: " + getTeleNumber() + "\n" +
                "E-Mail: " + getEmail() + "\n" +
                "Category: " + getSpecialFlag() + "\n" +
                "Region ID: " + getRegionID() + "\n" +
                "Region Name: " + getRegionName() + "\n" +
                "Users: " + getUserList() + "\n" +
                "Ragnes: " + getRangeList() + "\n" +
                "Roles: " + getRoleList();
    }

    public int compareTo(Object o) {
        DepartmentDO d = (DepartmentDO) o;

        return this.getName().compareTo(d.getName());
    }
}

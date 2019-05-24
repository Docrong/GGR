/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-15         created
 */

package com.boco.eoms.security.service.model;

import java.io.Serializable;
import java.security.Principal;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Role Data Object </p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class RoleDO
    implements Principal, Serializable, Comparable {

  private String roleID;
  private String name;
  private String comment;

  public RoleDO() {}

  /**constructor**/
  public RoleDO(String roleid, String name, String comment) {
    this.roleID = roleid;
    this.name = name;
    this.comment = comment;
  }

  public String getRoleID() {
    return roleID;
  }

  public void setRoleID(String roleID) {
    this.roleID = roleID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public int hashCode() {
    return this.roleID.hashCode();
  }

  public boolean equals(Object obj) {
    RoleDO rdo = (RoleDO) obj;
    if (rdo.hashCode() == this.hashCode()) {
      return true;
    }
    else {
      return false;
    }
  }

  public String toString() {
    return "Role ID: " + getRoleID() + "\n" +
        "Role Name: " + getName() + "\n" +
        "Description: " + getComment() + "\n";
  }
  public int compareTo(Object o) {
    RoleDO r = (RoleDO)o;

    return this.getName().compareTo(r.getName());
  }

}

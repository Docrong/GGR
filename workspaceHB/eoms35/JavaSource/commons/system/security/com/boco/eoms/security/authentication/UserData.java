/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-15         created
 */

package com.boco.eoms.security.authentication;

import java.util.Date;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Department Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class UserData
    extends SimplePrincipal {

  private String uid;
  private String pwd;
  private Date logonTime;
  private String legalHost;
  private SimpleGroup departments;
  private SimpleGroup regions;
  private SimpleGroup roles;
  private SimpleGroup permissions;
  private SimpleGroup ranges;

  public UserData(String s) {
    super(s);
    departments = new SimpleGroup("departments");
    roles = new SimpleGroup("roles");
    permissions = new SimpleGroup("permissions");
    ranges = new SimpleGroup("ranges");
    regions = new SimpleGroup("regions");
  }

  /**
   * getter and setter methods for uid attribute
   */
  public String getUid() {
    return uid;
  }

  public void setUid(String s) {
    this.uid = s;
  }

  /**
   * getter and setter methods for pwd attribute
   */
  public String getPwd() {
    return pwd;
  }

  public void setPwd(String s) {
    this.pwd = s;
  }

  /**
   * getter and setter methods for groups attribute
   */
  public SimpleGroup getdepartments() {
    return departments;
  }

  public void setdepartments(SimpleGroup simpledepartment) {
    this.departments = simpledepartment;
  }

  /**
   * getter and setter methods for roles attribute
   */
  public SimpleGroup getRoles() {
    return roles;
  }

  public void setRoles(SimpleGroup simplegroup) {
    this.roles = simplegroup;
  }

  /**
   * getter and setter methods for permissions attribute
   */
  public SimpleGroup getPermissions() {
    return permissions;
  }

  public void setPermissions(SimpleGroup simplegroup) {
    this.permissions = simplegroup;
  }

  /**
   * getter and setter methods for realms attribute
   */
  public SimpleGroup getRanges() {
    return ranges;
  }

  public void setRanges(SimpleGroup simplegroup) {
    this.ranges = simplegroup;
  }

  /**
   * getter and setter methods for regions attribute
   */
  public SimpleGroup getRegions() {
    return regions;
  }

  public void setRegions(SimpleGroup simplegroup) {
    this.regions = simplegroup;
  }

  /**
   * getter and setter methods for allowed register time
   */
  public Date getLogonTime(){
    return this.logonTime;
  }

  public void setLogonTime(Date newTime){
    this.logonTime = newTime;
  }

  /**
   * getter and setter methods for hosts which are allowed to logon
   */
  public String getLegalHost(){
    return this.legalHost;
  }

  public void setLegalHost(String newHostList){
    this.legalHost = newHostList;
  }

  /**
   * Description: judge if a user has the specified role
   * @param String - role name
   * @return boolean
   */
  public boolean isUserInRole(String role) {
    SimpleGroup simplegroup = getRoles();
    if (simplegroup == null) {
      return false;
    }
    else {
      return simplegroup.isMember(role);
    }
  }

  /**
   * Description: judge if a user has the specified permission
   * @param String - role name
   * @return boolean
   */
  public boolean hasPermission(String perm) {
    SimpleGroup simplegroup = getPermissions();
    if (simplegroup == null) {
      return false;
    }
    else {
      return simplegroup.isMember(perm);
    }
  }

  /**
   * Description: judge if a user has the specified data range
   * @param String - role name
   * @return boolean
   */
  public boolean hasRange(String range) {
    SimpleGroup simplegroup = getRanges();
    if (simplegroup == null) {
      return false;
    }
    else {
      return simplegroup.isMember(range);
    }
  }
}

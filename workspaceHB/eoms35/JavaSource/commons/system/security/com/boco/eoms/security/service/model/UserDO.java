/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-15         created
 * V1.0.0.0     weis            2003-08-20                  modify
 */

package com.boco.eoms.security.service.model;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.boco.eoms.common.util.StaticVariable;
//import com.boco.eoms.jbzl.model.*;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of User Data Object </p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class UserDO 
    implements Principal, Serializable, Comparable {

  private HashMap m_props;

  private int id=StaticVariable.defnull;
  private String userName="";
  private String password="";
  private String email="";
  private int deptId=StaticVariable.defnull;
  private int levelId=0;
  private String phone="";
  private String mobile="";
  private String fax="";
  private int deleted=0;
  private String notes="";
  private String userId="";
  private String deptName="";
  private String postName="";
  private String levelName="";
  private int userDegree=0;
  private String degreeName="";
  private String psconfig="";
  private int orderId=0;
  private String uidArray[] = new String[0];
  private String accessTimeBe="";
  private String accessTimeEn="";
  private int accessTimeFlag=0;
  private String accessIp="";
  private int accessIpFlag=0;
  private int activeFlag=0;
  private String fullName="";

  public UserDO() {
    m_props = new HashMap();
  }

  public UserDO(String userName, String fullName) {
    this.userName = userName;
    this.fullName = fullName;
    this.userId = userName;
    m_props = new HashMap();
  }

  public UserDO(String userName) {
    this.userName = userName;
    this.userId = userName;
  //  this.fullName = "";
    m_props = new HashMap();
  }



  /**
   * Set the user"s identifier.
   * @param userID
   */
  /*    public void setUserID(String userID) {
          this.userID = name;
      }*/

  /**
   * Return the full user username.
   *
   * @return
   *      Return the username.
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * Set the user's full name.
   * @param name
   */
  public void setName(String userName) {
    this.userName = userName;
  }

  public String getName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }


  /**
   * Set the user's full name.
   * @param fullName
   */
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  /**
   * Return the user's password.
   * @return
   *      Return the user's password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Set the user's password
   * @param password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  public int hashCode() {
    return this.userId.hashCode();
  }

  public boolean equals(Object obj) {
    UserDO rdo = (UserDO) obj;
    if (rdo.hashCode() == this.hashCode()) {
      return true;
    }
    else {
      return false;
    }
  }

  public Map getProperties() {
    return m_props;
  }

  /**
   * Retrieve the requested user property.
   *
   * @param  key      Property's name.
   *
   * @return  Return the property's value of the specified key, or null if the
   *          property does not exist.
   */
  public Object getProperty(String key) {
    return m_props.get(key);
  }

  /**
   * Remove the specified property from the properties list.
   *
   * @param   key     Property's name.
   */
  public void removeProperty(String key) {
    m_props.remove(key);
  }

  /**
   * Add (or update if not already in the property list) a property key-value
   * pair in the user's properties list.
   *
   * @param  key      Property's name.
   * @param  value    Property's value.
   */
  public void setProperty(String key, Object value) {
    m_props.put(key, value);
  }

  public String toString() {
    return "User Name: " + this.userName;

  }
  public int compareTo(Object o) {
    UserDO u = (UserDO)o;
    return this.getUserName().compareTo(u.getUserName());
  }
  public String getAccessIp() {
    return accessIp;
  }
  public int getAccessIpFlag() {
    return accessIpFlag;
  }
  public String getAccessTimeBe() {
    return accessTimeBe;
  }
  public String getAccessTimeEn() {
    return accessTimeEn;
  }
  public int getAccessTimeFlag() {
    return accessTimeFlag;
  }
  public int getActiveFlag() {
    return activeFlag;
  }
 /* public String getComment() {
    return comment;
  }*/
  public String getDegreeName() {
    return degreeName;
  }
  public int getDeleted() {
    return deleted;
  }
  public int getDeptId() {
    return deptId;
  }
  public String getDeptName() {
    return deptName;
  }
  public String getEmail() {
    return email;
  }
  public String getFax() {
    return fax;
  }
  public void setAccessIp(String accessIp) {
    this.accessIp = accessIp;
  }
  public void setAccessIpFlag(int accessIpFlag) {
    this.accessIpFlag = accessIpFlag;
  }
  public void setAccessTimeBe(String accessTimeBe) {
    this.accessTimeBe = accessTimeBe;
  }
  public void setAccessTimeEn(String accessTimeEn) {
    this.accessTimeEn = accessTimeEn;
  }
  public void setAccessTimeFlag(int accessTimeFlag) {
    this.accessTimeFlag = accessTimeFlag;
  }
  public void setActiveFlag(int activeFlag) {
    this.activeFlag = activeFlag;
  }
 /* public void setComment(String comment) {
    this.comment = comment;
  }*/
  public void setDegreeName(String degreeName) {
    this.degreeName = degreeName;
  }
  public void setDeleted(int deleted) {
    this.deleted = deleted;
  }
  public void setDeptId(int deptId) {
    this.deptId = deptId;
  }
  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public void setFax(String fax) {
    this.fax = fax;
  }
  public int getId() {
    return id;
  }
 /* public String getLegalHost() {
    return legalHost;
  }*/
  public int getLevelId() {
    return levelId;
  }
  public String getLevelName() {
    return levelName;
  }
 /* public String getLogonTime() {
    return logonTime;
  }*/
  public HashMap getM_props() {
    return m_props;
  }
  public String getMobile() {
    return mobile;
  }
  public String getNotes() {
    return notes;
  }
  public int getOrderId() {
    return orderId;
  }
  public String getPhone() {
    return phone;
  }
  public String getPostName() {
    return postName;
  }
  public String getPsconfig() {
    return psconfig;
  }
  public String[] getUidArray() {
    return uidArray;
  }
  public int getUserDegree() {
    return userDegree;
  }
  public String getUserId() {
    return userId;
  }
  public void setId(int id) {
    this.id = id;
  }
/*  public void setLegalHost(String legalHost) {
    this.legalHost = legalHost;
  }*/
  public void setLevelId(int levelId) {
    this.levelId = levelId;
  }
  public void setLevelName(String levelName) {
    this.levelName = levelName;
  }
  /*public void setLogonTime(String logonTime) {
    this.logonTime = logonTime;
  }*/
  public void setM_props(HashMap m_props) {
    this.m_props = m_props;
  }
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  public void setNotes(String notes) {
    this.notes = notes;
  }
  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public void setPostName(String postName) {
    this.postName = postName;
  }

  public void setPsconfig(String psconfig) {
    this.psconfig = psconfig;
  }
  public void setUidArray(String[] uidArray) {
    this.uidArray = uidArray;
  }
  public void setUserDegree(int userDegree) {
    this.userDegree = userDegree;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}

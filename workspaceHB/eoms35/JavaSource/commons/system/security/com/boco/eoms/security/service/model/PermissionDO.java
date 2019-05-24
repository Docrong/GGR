/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-15         created
 * V1.0.0.0   weis                 2003-08-20                modify
 */

package com.boco.eoms.security.service.model;

import java.io.Serializable;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Permission Data Object </p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class PermissionDO
    implements Principal, Serializable, Comparable {

  private String permissionID;
  private String name;
  private String category;
  private String comment;

  private HashMap m_props;

  /**
   * constructor
   */
  public PermissionDO() {
    m_props = new HashMap();
  }

  public PermissionDO(String permissionID) {
    this.permissionID = permissionID;
    m_props = new HashMap();
  }

  public PermissionDO(String id, String name, String category, String desc) {
    this.permissionID = id;
    this.name = name;
    this.category = category;
    this.comment = desc;
    m_props = new HashMap();
  }

  /**
   * get the permission identifier
   * @return  permissionID the permission identifier
   */
  public String getPermissionID() {
    return permissionID;
  }

  /**
   * set the permission identifier
   * @param permissionID permission identifier
   */
  public void setPermissionID(String permissionID) {
    this.permissionID = permissionID;
  }

  /**
   * get the permission's name
   * @return
   */
  public String getName() {
    return name;
  }

  /**
   * set the permission's name
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * get the permission's category
   * @return category the permission's category
   */
  public String getCategory() {
    return category;
  }

  /**
   * set the permission's category
   * @param category  the permission's category
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * get the permission's description
   * @return  comment the permission's description
   */
  public String getComment() {
    return comment;
  }

  /**
   * set the the permission's description
   * @param comment the permission's description
   */
  public void setComment(String comment) {
    this.comment = comment;
  }

  public int hashCode() {
    return this.permissionID.hashCode();
  }

  public boolean equals(Object obj) {
    PermissionDO rdo = (PermissionDO) obj;
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
   * Retrieve the requested permission property.
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
   * pair in the permission's properties list.
   *
   * @param  key      Property's name.
   * @param  value    Property's value.
   */
  public void setProperty(String key, Object value) {
    m_props.put(key, value);
  }

  public String toString() {
    return "Permission ID: " + getPermissionID() + "\n" +
        "Permission Name: " + getName() + "\n" +
        "Description: " + getComment() + "\n";
  }
  public int compareTo(Object o) {
    PermissionDO p = (PermissionDO)o;
    return this.getName().compareTo(p.getName());
  }
}

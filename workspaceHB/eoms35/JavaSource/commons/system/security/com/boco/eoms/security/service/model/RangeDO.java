/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-15         created
 */

package com.boco.eoms.security.service.model;

import java.io.Serializable;
import java.security.Principal;
import java.util.HashMap;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Range Data Object</p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class RangeDO
    implements Principal, Serializable, Comparable {

  private String rangeID;
  private String name;
  private String category;
  private String comment;
//  private Vector commonAttribute;
//  private Vector commonRegion;
//  private Vector commonFunction;
//  private Vector commonType;
//  private Vector commonOwner;
//  private Vector commonSystem;
  private String parentID;

  private HashMap m_props;

  public RangeDO() {
    m_props = new HashMap();
  }

  public RangeDO(String rangeID, String name, String category) {
    this.rangeID = rangeID;
    this.category = category;
    this.name = name;
    m_props = new HashMap();
  }

  public String getParentID() {
    return parentID;
  }

  public void setParentID(String parentID) {
    this.parentID = parentID;
  }

  public String getRangeID() {
    return rangeID;
  }

  public void setRangeID(String rangeID) {
    this.rangeID = rangeID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

//  public Vector getCommonAttribute() {
//    return commonAttribute;
//  }
//
//  public void setCommonAttribute(Vector commonAttribute) {
//    this.commonAttribute = commonAttribute;
//  }
//
//  public Vector getCommonRegion() {
//    return commonRegion;
//  }
//
//  public void setCommonRegion(Vector commonRegion) {
//    this.commonRegion = commonRegion;
//  }
//
//  public Vector getCommonFunction() {
//    return commonFunction;
//  }
//
//  public void setCommonFunction(Vector commonFunction) {
//    this.commonFunction = commonFunction;
//  }
//
//  public Vector getCommonType() {
//    return commonType;
//  }
//
//  public void setCommonType(Vector commonType) {
//    this.commonType = commonType;
//  }
//
//  public Vector getCommonOwner() {
//    return commonOwner;
//  }
//
//  public void setCommonOwner(Vector commonOwner) {
//    this.commonOwner = commonOwner;
//  }
//
//  public Vector getCommonSystem() {
//    return commonSystem;
//  }
//
//  public void setCommonSystem(Vector commonSystem) {
//    this.commonSystem = commonSystem;
//  }

  /**
   * Retrieve the requested Data Range property.
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
   * pair in the dataRanges properties list.
   *
   * @param  key      Property's name.
   * @param  value    Property's value.
   */
  public void setProperty(String key, Object value) {
    m_props.put(key, value);
  }

  public int hashCode() {
    return this.rangeID.hashCode();
  }

  public boolean equals(Object obj) {
    RangeDO rdo = (RangeDO) obj;
    if (rdo.hashCode() == this.hashCode()) {
      return true;
    }
    else {
      return false;
    }
  }

  public String toString() {
    return "Range ID: " + getRangeID() + "\n" +
        "Range Name: " + getName() + "\n" +
        "Description: " + getComment() + "\n";
  }
  public int compareTo(Object o) {
    RangeDO r = (RangeDO)o;
    return this.getName().compareTo(r.getName());
  }

}

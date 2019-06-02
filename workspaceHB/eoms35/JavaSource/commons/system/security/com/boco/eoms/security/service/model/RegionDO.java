/**
 * Copyright ? 2003  boco Co.,Ltd
 * All right reserved.
 * Version    Author          Date(YYYY-MM-DD)   Action
 * V1.0.0.0   Wang Zhuo Wei   2003-08-15         created
 */

package com.boco.eoms.security.service.model;

import java.io.Serializable;
import java.security.Principal;
import java.util.Vector;

/**
 * <p>Title: BOCO Authentication and Authorization System</p>
 * <p>Description: The object of Region Data Object </p>
 * <p>Copyright: Copyright (c) 2003 boco Co.,Ltd</p>
 * <p>Company: BOCO</p>
 * @author Wang Zhuo Wei
 * @version 1.0
 */

public class RegionDO
    implements Principal, Serializable, Comparable {

  private String regionID;
  private String name;
  private String comment;
  private Vector parentHierarchy = new Vector();
  private Vector rangeList = new Vector();
  private String notes;
  public String getRegionID() {
    return regionID;
  }

  public void setRegionID(String regionID) {
    this.regionID = regionID;
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

  public void addParentHierarchy(String name) {
    this.parentHierarchy.add(name);
  }

  public void removeParentHierarchy(String name) {
    this.parentHierarchy.remove(name);
  }

  public Vector getParentHierarchy() {
    return this.parentHierarchy;
  }

  public Vector getRangeList(){
    return this.rangeList;
  }
  public void setRangeList(Vector newVec){
    this.rangeList = newVec;
  }

  public int hashCode() {
    return this.regionID.hashCode();
  }

  public boolean equals(Object obj) {
    RegionDO rdo = (RegionDO) obj;
    if (rdo.hashCode() == this.hashCode()) {
      return true;
    }
    else {
      return false;
    }
  }

  public String toString() {
    return "Region ID: " + getRegionID() + "\n" +
        "Region Name: " + getName() + "\n" +
        "Description: " + getComment() + "\n" +
        "Parent Hierarchy: " + getParentHierarchy() + "\n";
  }
  public int compareTo(Object o) {
    RegionDO r = (RegionDO)o;
    return this.getName().compareTo(r.getName());
  }

public String getNotes() {
	return notes;
}

public void setNotes(String notes) {
	this.notes = notes;
}

}

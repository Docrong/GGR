package com.boco.eoms.infmanage.model;

import com.boco.eoms.common.util.StaticVariable;

import java.util.Collection;

public class TawInfGroup {
  private int groupId;

  private String groupName;

  private String userId;

  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserId() {
    return userId;
  }

}

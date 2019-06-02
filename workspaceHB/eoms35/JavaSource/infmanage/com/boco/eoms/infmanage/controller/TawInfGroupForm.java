package com.boco.eoms.infmanage.controller;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import org.apache.struts.validator.*;
import java.util.Collection;

public class TawInfGroupForm
    extends ValidatorForm {

  private int groupId;

  private String groupName;

  private String userId;

  public TawInfGroupForm() {
  }

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }

  public int getGroupId() {
    return groupId;
  }

  public String getGroupName() {
    return groupName;
  }

  public String getUserId() {
    return userId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}

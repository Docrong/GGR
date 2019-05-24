//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : TawUserRoomForm.java
//
// Copyright 2003 Company
//
// Generated at Thu Mar 27 10:17:32 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.duty.controller;

import org.apache.struts.action.*;
import org.apache.struts.validator.*;

import com.boco.eoms.duty.model.*;

public class TawUserRoomForm extends ValidatorForm {
  public final static int ADD = 1;
  public final static int EDIT = 2;
  private int strutsAction;
  private String strutsButton = "";
  private int ordercode = 0;
  private int flag = 0;
  private String userId = "";
  private int roomId = 0;

  public int getStrutsAction() {
    return strutsAction;
  }
  public String getStrutsButton() {
    return strutsButton;
  }
  public int getOrdercode() {
    return ordercode;
  }
  public int getFlag() {
    return flag;
  }
  public String getUserId() {
    return userId;
  }
  public int getRoomId() {
    return roomId;
  }

  public void setStrutsAction(int strutsAction) {
    this.strutsAction = strutsAction;
  }
  public void setStrutsButton(String strutsButton) {
    this.strutsButton = strutsButton;
  }
  public void setOrdercode(int ordercode) {
    this.ordercode = ordercode;
  }
  public void setFlag(int flag) {
    this.flag = flag;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }
  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }

}

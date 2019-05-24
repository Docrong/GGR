//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : TawRmExchangeForm.java
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

public class TawRmExchangeForm extends ValidatorForm {
  public final static int ADD = 1;
  public final static int EDIT = 2;
  private int strutsAction;
  private String strutsButton = "";
  private String exchangetime = "";
  private int roomId = 0;
  private short id = 0;

  public int getStrutsAction() {
    return strutsAction;
  }
  public String getStrutsButton() {
    return strutsButton;
  }
  public String getExchangetime() {
    return exchangetime;
  }
  public int getRoomId() {
    return roomId;
  }
  public short getId() {
    return id;
  }

  public void setStrutsAction(int strutsAction) {
    this.strutsAction = strutsAction;
  }
  public void setStrutsButton(String strutsButton) {
    this.strutsButton = strutsButton;
  }
  public void setExchangetime(String exchangetime) {
    this.exchangetime = exchangetime;
  }
  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }
  public void setId(short id) {
    this.id = id;
  }

}

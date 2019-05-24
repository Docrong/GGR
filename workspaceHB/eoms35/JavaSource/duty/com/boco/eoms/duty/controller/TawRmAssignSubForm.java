//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : TawRmAssignSubForm.java
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

public class TawRmAssignSubForm extends ValidatorForm {
  public final static int ADD = 1;
  public final static int EDIT = 2;
  private int strutsAction;
  private String strutsButton = "";
  private int workserial = 0;
  private String dutyman = "";
  private String notes = "";
  private int id = 0;

  public int getStrutsAction() {
    return strutsAction;
  }
  public String getStrutsButton() {
    return strutsButton;
  }
  public int getWorkserial() {
    return workserial;
  }
  public String getDutyman() {
    return dutyman;
  }
  public String getNotes() {
    return notes;
  }
  public int getId() {
    return id;
  }

  public void setStrutsAction(int strutsAction) {
    this.strutsAction = strutsAction;
  }
  public void setStrutsButton(String strutsButton) {
    this.strutsButton = strutsButton;
  }
  public void setWorkserial(int workserial) {
    this.workserial = workserial;
  }
  public void setDutyman(String dutyman) {
    this.dutyman = dutyman;
  }
  public void setNotes(String notes) {
    this.notes = notes;
  }
  public void setId(int id) {
    this.id = id;
  }

}

//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : TawRmDutyfileForm.java
//
// Copyright 2003 Company
//
// Generated at Thu Mar 27 10:17:32 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.km.duty.webapp.form;

import org.apache.struts.action.*;
import org.apache.struts.validator.*;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.duty.model.*;

public class KmhangoverForm extends ValidatorForm {

  public final static int ADD = 1;
  public final static int EDIT = 2;
  private int strutsAction;
  private String strutsButton = "";

  private int hangWorkserial = 0;
  private int receiveWorkserial = 0 ;
  private String hangQuestion = "";
  private int flag = 0;
  private String dealer = "";
  private String notes = "";
  private int id = 0;
  private String hangQuestion0 = "";

  public int getHangWorkserial() {
    return hangWorkserial;
  }
  public int getReceiveWorkserial() {
  return receiveWorkserial;
  }
  public String getHangQuestion() {
    return hangQuestion;
  }
  public String getDealer() {
    return dealer;
  }
  public int getFlag() {
    return flag;
  }
  public String getNotes() {
    return notes;
  }
  public int getId() {
    return id;
  }
  public String getHangQuestion0() {
    return hangQuestion0;
  }




  public void setHangWorkserial(int hangWorkserial) {
    this.hangWorkserial = hangWorkserial;
  }
  public void setReceiveWorkserial(int receiveWorkserial) {
    this.receiveWorkserial = receiveWorkserial;
  }
  public void setFlag(int flag) {
    this.flag = flag;
  }
  public void setHangQuestion(String hangQuestion) {
    this.hangQuestion = hangQuestion;
  }
  public void setDealer(String dealer) {
    this.dealer = dealer;
  }
  public void setNotes(String notes) {
    this.notes = notes;
  }
  public void setId(int id) {
    this.id = id;
  }
  public void setHangQuestion0(String hangQuestion0) {
    this.hangQuestion0 = hangQuestion0;
  }
}

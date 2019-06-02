//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : TawRmAssignworkForm.java
//
// Copyright 2003 Company
//
// Generated at Thu Mar 27 10:17:32 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.km.duty.webapp.form;

import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.*;

import com.boco.eoms.duty.model.*;

public class KmassignworkForm extends ValidatorForm {
  public final static int ADD = 1;
  public final static int EDIT = 2;
  private int strutsAction;
  private String strutsButton = "";
  private int regionId = 0;
  private int roomId = 0;
  private String dutydate = "";
  private int workid = 0;
  private int flag = 0;
  private String dutymaster = "";
  private String starttimeDefined = "";
  private String endtimeDefined = "";
  private int smsflag = 0;
  private String notes = "";
  private int id = 0;
  private FormFile thisFile;  //文件
  
  public FormFile getThisFile() { 
  return thisFile; 
  } 

  public void setThisFile(FormFile thisFile) { 
  this.thisFile = thisFile; 
  }
  public int getStrutsAction() {
    return strutsAction;
  }
  public String getStrutsButton() {
    return strutsButton;
  }
  public int getRegionId() {
    return regionId;
  }
  public int getRoomId() {
    return roomId;
  }
  public String getDutydate() {
    return dutydate;
  }
  public int getWorkid() {
    return workid;
  }
  public int getFlag() {
    return flag;
  }
  public String getDutymaster() {
    return dutymaster;
  }
  public String getStarttimeDefined() {
    return starttimeDefined;
  }
  public String getEndtimeDefined() {
    return endtimeDefined;
  }
  public int getSmsflag() {
    return smsflag;
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
  public void setRegionId(int regionId) {
    this.regionId = regionId;
  }
  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }
  public void setDutydate(String dutydate) {
    this.dutydate = dutydate;
  }
  public void setWorkid(int workid) {
    this.workid = workid;
  }
  public void setFlag(int flag) {
    this.flag = flag;
  }
  public void setDutymaster(String dutymaster) {
    this.dutymaster = dutymaster;
  }
  public void setStarttimeDefined(String starttimeDefined) {
    this.starttimeDefined = starttimeDefined;
  }
  public void setEndtimeDefined(String endtimeDefined) {
    this.endtimeDefined = endtimeDefined;
  }
  public void setSmsflag(int smsflag) {
    this.smsflag = smsflag;
  }
  public void setNotes(String notes) {
    this.notes = notes;
  }
  public void setId(int id) {
    this.id = id;
  }

}

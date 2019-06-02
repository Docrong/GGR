//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : TawRmRecordSubForm.java
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

public class TawRmRecordSubForm extends ValidatorForm {
  public final static int ADD = 1;
  public final static int EDIT = 2;
  private int strutsAction;
  private String strutsButton = "";
  private int roomId = 0;
  private int workserial = 0;
  private String dutyman = "";
  private String starttime = "";
  private String endtime = "";
  private String starttimeDefined = "";
  private String endtimeDefined = "";
  private String statement = "";
  private int workflag = 0;
  private int status = 0;
  private String notes = "";
  private int id = 0;
  private String dutycheck;
  
  public String importantnetfault;
  public String importantsocietydisaster;
  public String needcorrespond;
  public String handoverproceeding;

  public int getStrutsAction() {
    return strutsAction;
  }
  public String getStrutsButton() {
    return strutsButton;
  }
  public int getRoomId() {
    return roomId;
  }
  public int getWorkserial() {
    return workserial;
  }
  public String getDutyman() {
    return dutyman;
  }
  public String getStarttime() {
    return starttime;
  }
  public String getEndtime() {
    return endtime;
  }
  public String getStarttimeDefined() {
    return starttimeDefined;
  }
  public String getEndtimeDefined() {
    return endtimeDefined;
  }
  public String getStatement() {
    return statement;
  }
  public int getWorkflag() {
    return workflag;
  }
  public int getStatus() {
    return status;
  }
  public String getNotes() {
    return notes;
  }
  public int getId() {
    return id;
  }

  public String getDutycheck() {
    return dutycheck;
  }

  public void setStrutsAction(int strutsAction) {
    this.strutsAction = strutsAction;
  }
  public void setStrutsButton(String strutsButton) {
    this.strutsButton = strutsButton;
  }
  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }
  public void setWorkserial(int workserial) {
    this.workserial = workserial;
  }
  public void setDutyman(String dutyman) {
    this.dutyman = dutyman;
  }
  public void setStarttime(String starttime) {
    this.starttime = starttime;
  }
  public void setEndtime(String endtime) {
    this.endtime = endtime;
  }
  public void setStarttimeDefined(String starttimeDefined) {
    this.starttimeDefined = starttimeDefined;
  }
  public void setEndtimeDefined(String endtimeDefined) {
    this.endtimeDefined = endtimeDefined;
  }
  public void setStatement(String statement) {
    this.statement = statement;
  }
  public void setWorkflag(int workflag) {
    this.workflag = workflag;
  }
  public void setStatus(int status) {
    this.status = status;
  }
  public void setNotes(String notes) {
    this.notes = notes;
  }
  public void setId(int id) {
    this.id = id;
  }

  public void setDutycheck(String dutycheck) {
    this.dutycheck = dutycheck;
  }
	public String getHandoverproceeding() {
		return handoverproceeding;
	}
	public void setHandoverproceeding(String handoverproceeding) {
		this.handoverproceeding = handoverproceeding;
	}
	public String getImportantnetfault() {
		return importantnetfault;
	}
	public void setImportantnetfault(String importantnetfault) {
		this.importantnetfault = importantnetfault;
	}
	public String getImportantsocietydisaster() {
		return importantsocietydisaster;
	}
	public void setImportantsocietydisaster(String importantsocietydisaster) {
		this.importantsocietydisaster = importantsocietydisaster;
	}
	public String getNeedcorrespond() {
		return needcorrespond;
	}
	public void setNeedcorrespond(String needcorrespond) {
		this.needcorrespond = needcorrespond;
	}
  
  
  
}

//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : TawRmSysteminfoForm.java
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

import com.boco.eoms.duty.model.*;

public class KmsysteminfoForm extends ValidatorForm {
  public final static int ADD = 1;
  public final static int EDIT = 2;
  private int strutsAction;
  private String strutsButton = "";
  private int deptId = 0;
  private int maxerrortime = 30;
  private int maxdutynum = 4;
  private int exRequest = 0;
  private int exAnswer = 0;
  private int dutyInform = 0;
  private int exchangeFlag=0;
  private int roomId = 0;
  private int cycleTime =30;
  private int staggertime=0;

  public int getStaggertime() {
	return staggertime;
}
public void setStaggertime(int staggertime) {
	this.staggertime = staggertime;
}
public int getStrutsAction() {
    return strutsAction;
  }
  public String getStrutsButton() {
    return strutsButton;
  }
  public int getDeptId() {
    return deptId;
  }
  public int getMaxerrortime() {
    return maxerrortime;
  }
  public int getMaxdutynum() {
    return maxdutynum;
  }
  public int getExRequest() {
    return exRequest;
  }
  public int getExAnswer() {
    return exAnswer;
  }
  public int getDutyInform() {
    return dutyInform;
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
  public void setDeptId(int deptId) {
    this.deptId = deptId;
  }
  public void setMaxerrortime(int maxerrortime) {
    this.maxerrortime = maxerrortime;
  }
  public void setMaxdutynum(int maxdutynum) {
    this.maxdutynum = maxdutynum;
  }
  public void setExRequest(int exRequest) {
    this.exRequest = exRequest;
  }
  public void setExAnswer(int exAnswer) {
    this.exAnswer = exAnswer;
  }
  public void setDutyInform(int dutyInform) {
    this.dutyInform = dutyInform;
  }
  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }
  public int getExchangeFlag() {
    return exchangeFlag;
  }
  public void setExchangeFlag(int exchangeFlag) {
    this.exchangeFlag = exchangeFlag;
  }
public int getCycleTime() {
	return cycleTime;
}
public void setCycleTime(int cycleTime) {
	this.cycleTime = cycleTime;
}

}

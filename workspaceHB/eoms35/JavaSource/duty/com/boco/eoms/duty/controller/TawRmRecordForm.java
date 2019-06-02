//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : TawRmRecordForm.java
//
// Copyright 2003 Company
//
// Generated at Thu Mar 27 10:17:32 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.duty.controller;

import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.*;

import com.boco.eoms.duty.model.*;

public class TawRmRecordForm extends ValidatorForm {
  public final static int ADD = 1;
  public final static int EDIT = 2;
  private int strutsAction;
  private String strutsButton = "";
  private int regionId = 0;
  private int roomId = 0;
  private int flag = 0;
  private String starttime = "";
  private String endtime = "";
  private String weather = "";
  private int temperature = 0;
  private int wet = 0;
  private String dutydate = "";
  private String clean = "";
  private String clean1 = "";
  private String conditioner = "";
  private String hander = "";
  private String dutyman = "";
  private String receiver = "";
  private String dutyrecord = "";
  private String notes = "";
  private int id = 0;

//new
  private String starttimeDefined = "";
  private String endtimeDefined = "";
  private int roomIdsub = 0;
  private int workserial = 0;
  private String dutymansub = "";
  private String starttimesub = "";
  private String endtimesub = "";
  private String statement = "";
  private int workflag = 0;
  private int status = 0;
  private String notessub = "";
  private int idsub = 0;
  private String dutymaster;
  //本次值班要填写的附加表个数
  private int sheetCount=0;
  //本次值班已经填写的附加表个数
  private int realCount=0;  
  //本次需要填写的周期附加表个数
  private int cycleCount = 0;
  private String auContent;
  private String auditor;
  private String auTime;
  private String dutycheck;
  
  public String dutySummary;
  public String importRecord;
  public String importFault;
  public String netCut;
  public String netKPI;
  public String reportMonth; //月报统计月份
  public String reportYear; //月报统计年份

  public String importantnetfault;
  public String importantsocietydisaster;
  public String needcorrespond;
  public String handoverproceeding;
 
  
  private String netfault;
  private String importantaffair;
  private String harmony;
  private String otheraffair;
 
   

//
//交填写接班内容字段
  public String dutyMan;
  public String exchangeTime;
  public String exReceiver;
  public int workSerial;
  public int prAlarmNums;
  public int sendSheetNum;
  public int alAlarmNum;
  public int noAlarmNum;
  public String operationalState;
  public String yeLinetype;
  public String toLinetype;
  public String difLinetype;
  public int leaveSheetNum;
  public int leaveSheetNums;
  public int chiefSheetNum;
  public String purpose;
  public String completeFlag;
  public String balance;
  public String attentive;
  public String city;
  public String sheetId;
  public String reason;
  public String phone;
  public String person;
  public String rate;
  public String exFlag;
  public int getAlAlarmNum() {
	return alAlarmNum;
}
public void setAlAlarmNum(int alAlarmNum) {
	this.alAlarmNum = alAlarmNum;
}
public String getAttentive() {
	return attentive;
}
public void setAttentive(String attentive) {
	this.attentive = attentive;
}
public String getBalance() {
	return balance;
}
public void setBalance(String balance) {
	this.balance = balance;
}
public int getChiefSheetNum() {
	return chiefSheetNum;
}
public void setChiefSheetNum(int chiefSheetNum) {
	this.chiefSheetNum = chiefSheetNum;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getCompleteFlag() {
	return completeFlag;
}
public void setCompleteFlag(String completeFlag) {
	this.completeFlag = completeFlag;
}
public String getDifLinetype() {
	return difLinetype;
}
public void setDifLinetype(String difLinetype) {
	this.difLinetype = difLinetype;
}
public String getDutyMan() {
	return dutyMan;
}
public void setDutyMan(String dutyMan) {
	this.dutyMan = dutyMan;
}
public String getEndtimesub() {
	return endtimesub;
}
public void setEndtimesub(String endtimesub) {
	this.endtimesub = endtimesub;
}
public String getExchangeTime() {
	return exchangeTime;
}
public void setExchangeTime(String exchangeTime) {
	this.exchangeTime = exchangeTime;
}
public String getExFlag() {
	return exFlag;
}
public void setExFlag(String exFlag) {
	this.exFlag = exFlag;
}
public String getExReceiver() {
	return exReceiver;
}
public void setExReceiver(String exReceiver) {
	this.exReceiver = exReceiver;
}
public int getLeaveSheetNum() {
	return leaveSheetNum;
}
public void setLeaveSheetNum(int leaveSheetNum) {
	this.leaveSheetNum = leaveSheetNum;
}
public int getLeaveSheetNums() {
	return leaveSheetNums;
}
public void setLeaveSheetNums(int leaveSheetNums) {
	this.leaveSheetNums = leaveSheetNums;
}
public int getNoAlarmNum() {
	return noAlarmNum;
}
public void setNoAlarmNum(int noAlarmNum) {
	this.noAlarmNum = noAlarmNum;
}
public String getNotessub() {
	return notessub;
}
public void setNotessub(String notessub) {
	this.notessub = notessub;
}
public String getOperationalState() {
	return operationalState;
}
public void setOperationalState(String operationalState) {
	this.operationalState = operationalState;
}
public String getPerson() {
	return person;
}
public void setPerson(String person) {
	this.person = person;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public int getPrAlarmNums() {
	return prAlarmNums;
}
public void setPrAlarmNums(int prAlarmNums) {
	this.prAlarmNums = prAlarmNums;
}
public String getPurpose() {
	return purpose;
}
public void setPurpose(String purpose) {
	this.purpose = purpose;
}
public String getRate() {
	return rate;
}
public void setRate(String rate) {
	this.rate = rate;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
public int getRoomIdsub() {
	return roomIdsub;
}
public void setRoomIdsub(int roomIdsub) {
	this.roomIdsub = roomIdsub;
}
public int getSendSheetNum() {
	return sendSheetNum;
}
public void setSendSheetNum(int sendSheetNum) {
	this.sendSheetNum = sendSheetNum;
}
public String getSheetId() {
	return sheetId;
}
public void setSheetId(String sheetId) {
	this.sheetId = sheetId;
}
public String getToLinetype() {
	return toLinetype;
}
public void setToLinetype(String toLinetype) {
	this.toLinetype = toLinetype;
}
public int getWorkSerial() {
	return workSerial;
}
public void setWorkSerial(int workSerial) {
	this.workSerial = workSerial;
}
public String getYeLinetype() {
	return yeLinetype;
}
public void setYeLinetype(String yeLinetype) {
	this.yeLinetype = yeLinetype;
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
  public int getFlag() {
    return flag;
  }
  public String getStarttime() {
    return starttime;
  }
  public String getEndtime() {
    return endtime;
  }
  public String getWeather() {
    return weather;
  }
  public int getTemperature() {
    return temperature;
  }
  public int getWet() {
    return wet;
  }
  public String getDutydate() {
    return dutydate;
  }
  public String getClean() {
    return clean;
  }
  public String getClean1() {
    return clean1;
  }
  public String getConditioner() {
    return conditioner;
  }
  public String getHander() {
    return hander;
  }
  public String getDutyman() {
    return dutyman;
  }
  public String getReceiver() {
    return receiver;
  }
  public String getDutyrecord() {
    return dutyrecord;
  }
  public String getNotes() {
    return notes;
  }
  public int getId() {
    return id;
  }

  //new
  public String getStarttimeDefined() {
    return starttimeDefined;
  }
  public String getEndtimeDefined() {
    return endtimeDefined;
  }

  public String getDutymansub() {
    return dutymansub;
  }

  public int getIdsub() {
    return idsub;
  }

  public String getStarttimesub() {
    return starttimesub;
  }

  public int getWorkflag() {
    return workflag;
  }

  public int getStatus() {
    return status;
  }

  public String getStatement() {
    return statement;
  }

  public int getWorkserial() {
    return workserial;
  }

  public String getDutymaster() {
    return dutymaster;
  }

  public String getReportMonth() {
	    return reportMonth;
  }

  public String getReportYear() {
	    return reportYear;
  }
  
//

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
  public void setFlag(int flag) {
    this.flag = flag;
  }
  public void setStarttime(String starttime) {
    this.starttime = starttime;
  }
  public void setEndtime(String endtime) {
    this.endtime = endtime;
  }
  public void setWeather(String weather) {
    this.weather = weather;
  }
  public void setTemperature(int temperature) {
    this.temperature = temperature;
  }
  public void setWet(int wet) {
    this.wet = wet;
  }
  public void setDutydate(String dutydate) {
    this.dutydate = dutydate;
  }
  public void setClean(String clean) {
    this.clean = clean;
  }
  public void setClean1(String clean1) {
    this.clean1 = clean1;
  }
  public void setConditioner(String conditioner) {
    this.conditioner = conditioner;
  }
  public void setHander(String hander) {
    this.hander = hander;
  }
  public void setDutyman(String dutyman) {
    this.dutyman = dutyman;
  }
  public void setReceiver(String receiver) {
    this.receiver = receiver;
  }
  public void setDutyrecord(String dutyrecord) {
    this.dutyrecord = dutyrecord;
  }
  public void setNotes(String notes) {
    this.notes = notes;
  }
  public void setId(int id) {
    this.id = id;
  }

//new
  public void setStarttimeDefined(String starttimeDefined) {
    this.starttimeDefined = starttimeDefined;
  }
  public void setEndtimeDefined(String endtimeDefined) {
    this.endtimeDefined = endtimeDefined;
  }
  public void setDutymansub(String dutymansub) {
    this.dutymansub = dutymansub;
  }

  public void setStarttimesub(String starttimesub) {
    this.starttimesub = starttimesub;
  }

  public void setWorkflag(int workflag) {
    this.workflag = workflag;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public void setStatement(String statement) {
    this.statement = statement;
  }

  public void setWorkserial(int workserial) {
    this.workserial = workserial;
  }

  public void setIdsub(int idsub) {
    this.idsub = idsub;
  }

  public void setDutymaster(String dutymaster) {
    this.dutymaster = dutymaster;
  }

  public void setReportMonth(String reportMonth) {
	    this.reportMonth = reportMonth;
  }

  public void setReportYear(String reportYear) {
	    this.reportYear = reportYear;
  }

  public int getSheetCount() {
    return sheetCount;
  }
  public void setSheetCount(int sheetCount) {
    this.sheetCount = sheetCount;
  }
  public int getRealCount() {
    return realCount;
  }
  public void setRealCount(int realCount) {
    this.realCount = realCount;
  }
  public String getAuContent() {
    return auContent;
  }
  public void setAuContent(String auContent) {
    this.auContent = auContent;
  }
  public String getAuditor() {
    return auditor;
  }
  public void setAuditor(String auditor) {
    this.auditor = auditor;
  }
  public String getAuTime() {
    return auTime;
  }

  public String getDutycheck() {
    return dutycheck;
  }

  public void setAuTime(String auTime) {
    this.auTime = auTime;
  }

  public void setDutycheck(String dutycheck) {
    this.dutycheck = dutycheck;
  }
public int getCycleCount() {
	return cycleCount;
}
public void setCycleCount(int cycleCount) {
	this.cycleCount = cycleCount;
}
public String getDutySummary() {
	return dutySummary;
}
public void setDutySummary(String dutySummary) {
	this.dutySummary = dutySummary;
}
public String getImportFault() {
	return importFault;
}
public void setImportFault(String importFault) {
	this.importFault = importFault;
}
public String getImportRecord() {
	return importRecord;
}
public void setImportRecord(String importRecord) {
	this.importRecord = importRecord;
}
public String getNetCut() {
	return netCut;
}
public void setNetCut(String netCut) {
	this.netCut = netCut;
}
public String getNetKPI() {
	return netKPI;
}
public void setNetKPI(String netKPI) {
	this.netKPI = netKPI;
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
 
public String getNetfault() {
	return netfault;
}
public void setNetfault(String netfault) {
	this.netfault = netfault;
}
public String getImportantaffair() {
	return importantaffair;
}
public void setImportantaffair(String importantaffair) {
	this.importantaffair = importantaffair;
}
public String getHarmony() {
	return harmony;
}
public void setHarmony(String harmony) {
	this.harmony = harmony;
}
public String getOtheraffair() {
	return otheraffair;
}
public void setOtheraffair(String otheraffair) {
	this.otheraffair = otheraffair;
}

  
}

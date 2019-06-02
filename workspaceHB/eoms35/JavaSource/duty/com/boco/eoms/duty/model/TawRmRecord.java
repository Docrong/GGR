//---------------------------------------------------------
// Application: 值班记录
// Author     : cheng
// File       : TawRmRecord.java
//
// Copyright 2003 Company
// Generated at Thu Mar 27 10:17:32 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.duty.model;

import com.boco.eoms.common.util.StaticMethod;

public class TawRmRecord {
  private int regionId;
  private int roomId;
  private int flag;
  //private java.sql.Timestamp starttime;
  private String starttime;
  //private java.sql.Timestamp endtime;
  private String endtime;
  private String weather;
  private int temperature;
  private int wet;
  //private java.sql.Timestamp dutydate;

  private String dutydate;
  private String clean;
  private String clean1;
  private String conditioner;
  private String hander;
  private String dutyman;
  private String receiver;
  private String dutyrecord;
  private String notes;
  private int id;
//new
  private String starttimeDefined;
  private String endtimeDefined;
  private String dutymansub;
  private int workflag;
  private int status;
  private String statement;
  private String starttimesub;
  private int workserial;
  private int idsub;
  private String dutymaster;
  private String auContent;
  private String auditor;
  private String auTime;
  private String dutycheck;
  
  
  public String dutySummary;
  public String importRecord;
  public String importFault;
  public String netCut;
  public String netKPI;
  public String sheetUrl;
  private String roomname;
  
  public String importantnetfault;
  public String importantsocietydisaster;
  public String needcorrespond;
  public String handoverproceeding;
  
  private String netfault;
  private String importantaffair;
  private String harmony;
  private String otheraffair;

  private String regionname;

public String getRoomname() {
	return roomname;
}
public void setRoomname(String roomname) {
	this.roomname = roomname;
}
//
  public String getDutymaster() {
    return dutymaster;
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
  //public java.sql.Timestamp getStarttime() {
  public String getStarttime() {
    return starttime;
  }
  //public java.sql.Timestamp getEndtime() {
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
  //public java.sql.Timestamp getDutydate() {
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

  public int getIdsub() {
    return idsub;
  }

//

  public void setRegionId(int regionId) {
    this.regionId = regionId;
  }
  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }
  public void setFlag(int flag) {
    this.flag = flag;
  }
//  public void setStarttime(java.sql.Timestamp starttime) {
  public void setStarttime(String starttime) {
    this.starttime = starttime;
  }
//  public void setEndtime(java.sql.Timestamp endtime) {
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
//  public void setDutydate(java.sql.Timestamp dutydate) {
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
public String getSheetUrl() {
	return sheetUrl;
}
public void setSheetUrl(String sheetUrl) {
	this.sheetUrl = sheetUrl;
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
	return StaticMethod.nullObject2String(netfault).trim();
}
public void setNetfault(String netfault) {
	this.netfault = netfault;
}
public String getImportantaffair() {
	return StaticMethod.nullObject2String(importantaffair).trim();
}
public void setImportantaffair(String importantaffair) {
	this.importantaffair = importantaffair;
}
public String getHarmony() {
	return StaticMethod.nullObject2String(harmony).trim();
}
public void setHarmony(String harmony) {
	this.harmony = harmony;
}
public String getOtheraffair() {
	return StaticMethod.nullObject2String(otheraffair).trim();
}
public void setOtheraffair(String otheraffair) {
	this.otheraffair = otheraffair;
}
public String getRegionname() {
	return regionname;
}
public void setRegionname(String regionname) {
	this.regionname = regionname;
}


//
}

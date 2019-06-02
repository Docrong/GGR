//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : TawRmRecordSub.java
//
// Copyright 2003 Company
// Generated at Thu Mar 27 10:17:32 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.km.duty.model;

import com.boco.eoms.common.util.StaticMethod;

public class KmrecordSub {
  private int roomId;
  private int workserial;
  private String dutyman;
  /*
  private java.sql.Timestamp starttime;
  private java.sql.Timestamp endtime;
  private java.sql.Timestamp starttimeDefined;
  private java.sql.Timestamp endtimeDefined;
  */
  private String starttime;
  private String endtime;
  private String starttimeDefined;
  private String endtimeDefined;
  private String statement;
  private int workflag;
  private int status;
  private String notes;
  private int id;
  private int tableId;
  private String dutycheck;
  
  public String importantnetfault;
  public String importantsocietydisaster;
  public String needcorrespond;
  public String handoverproceeding;

  public String netfault;
  public String importantaffair;
  public String harmony;
  public String otheraffair;

  public int getRoomId() {
    return roomId;
  }
  public int getWorkserial() {
    return workserial;
  }
  public String getDutyman() {
    return dutyman;
  }
  /*
  public java.sql.Timestamp getStarttime() {
    return starttime;
  }
  public java.sql.Timestamp getEndtime() {
    return endtime;
  }
  public java.sql.Timestamp getStarttimeDefined() {
    return starttimeDefined;
  }
  public java.sql.Timestamp getEndtimeDefined() {
    return endtimeDefined;
  }
      */
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

  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }
  public void setWorkserial(int workserial) {
    this.workserial = workserial;
  }
  public void setDutyman(String dutyman) {
    this.dutyman = dutyman;
  }
  /*
  public void setStarttime(java.sql.Timestamp starttime) {
    this.starttime = starttime;
  }
  public void setEndtime(java.sql.Timestamp endtime) {
    this.endtime = endtime;
  }
  public void setStarttimeDefined(java.sql.Timestamp starttimeDefined) {
    this.starttimeDefined = starttimeDefined;
  }
  public void setEndtimeDefined(java.sql.Timestamp endtimeDefined) {
    this.endtimeDefined = endtimeDefined;
  }
  */
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
  public int getTableId() {
    return tableId;
  }

  public String getDutycheck() {
    return dutycheck;
  }

  public void setTableId(int tableId) {
    this.tableId = tableId;
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
  
  
}

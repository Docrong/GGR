package com.boco.eoms.duty.model;

public class TawRmRecordDef {
  private int id;
  private int roomId;
  private int workserial;
  private int tableId;
  private String talbeDesc;
  private int mainId;
  private String recordMain;
  private int subId;
  private String recordSub;
  private String dutyRecord;
  public String getDutyRecord() {
    return dutyRecord;
  }
  public int getId() {
    return id;
  }
  public int getMainId() {
    return mainId;
  }
  public String getRecordMain() {
    return recordMain;
  }
  public String getRecordSub() {
    return recordSub;
  }
  public int getSubId() {
    return subId;
  }
  public int getTableId() {
    return tableId;
  }
  public String getTalbeDesc() {
    return talbeDesc;
  }
  public int getWorkserial() {
    return workserial;
  }
  public void setWorkserial(int workserial) {
    this.workserial = workserial;
  }
  public void setTalbeDesc(String talbeDesc) {
    this.talbeDesc = talbeDesc;
  }
  public void setTableId(int tableId) {
    this.tableId = tableId;
  }
  public void setSubId(int subId) {
    this.subId = subId;
  }
  public void setRecordSub(String recordSub) {
    this.recordSub = recordSub;
  }
  public void setRecordMain(String recordMain) {
    this.recordMain = recordMain;
  }
  public void setMainId(int mainId) {
    this.mainId = mainId;
  }
  public void setId(int id) {
    this.id = id;
  }
  public void setDutyRecord(String dutyRecord) {
    this.dutyRecord = dutyRecord;
  }
    public int getRoomId()
    {
        return roomId;
    }
    public void setRoomId(int roomId)
    {
        this.roomId = roomId;
    }

}

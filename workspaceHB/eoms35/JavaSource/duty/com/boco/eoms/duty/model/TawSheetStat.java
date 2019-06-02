package com.boco.eoms.duty.model;

public class TawSheetStat {
  private String sheetId;
  private String worksheetId;
  private String title;
  private String sendTime;
  private int deptId;
  private int roomId;
  private int dutyId;
  private String dutyDate;
  private String dutyMaster;
  private String url;
  public TawSheetStat() {
  }
  public String getSheetId() {
    return sheetId;
  }
  public void setSheetId(String sheetId) {
    this.sheetId = sheetId;
  }
  public String getWorksheetId() {
    return worksheetId;
  }
  public void setWorksheetId(String worksheetId) {
    this.worksheetId = worksheetId;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getSendTime() {
    return sendTime;
  }
  public void setSendTime(String sendTime) {
    this.sendTime = sendTime;
  }
  public int getDeptId() {
    return deptId;
  }
  public void setDeptId(int deptId) {
    this.deptId = deptId;
  }
  public int getRoomId() {
    return roomId;
  }
  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }
  public int getDutyId() {
    return dutyId;
  }
  public void setDutyId(int dutyId) {
    this.dutyId = dutyId;
  }
  public void setDutyDate(String dutyDate) {
   this.dutyDate = dutyDate;
 }
 public String getDutyDate() {
  dutyDate=dutyDate.substring(0,10);
  return dutyDate;
}

public void setDutyMaster(String dutyMaster) {
  this.dutyMaster = dutyMaster;
}
public String getDutyMaster() {
 return dutyMaster;
}
  public void setUrl(String url) {
    this.url = url;
  }
  public String getUrl() {
   return url;
  }



}

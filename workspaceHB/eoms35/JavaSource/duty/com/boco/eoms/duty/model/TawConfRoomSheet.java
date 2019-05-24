package com.boco.eoms.duty.model;

public class TawConfRoomSheet {
  //记录Id
  private int id;
  //机房 Id
  private int roomId;
  //附加表的记录id
  private int sheetId;
  //附加表名称
  private String sheetName;
  //机房名称
  private String roomName;

  private String isNotFault;
  private String toAttrField;
  private String byAttrName;
  private String byAttrFieldName;
  private String toAttrName;
  private String toAttrFieldName;
  private int byAttr;
  private int byAttrField;
  private int toAttr;
  private int deptId;
  private String deptName;

  public TawConfRoomSheet() {
  }
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public int getRoomId() {
    return roomId;
  }
  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }
  public String getRoomName() {
    return roomName;
  }
  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }
  public int getSheetId() {
    return sheetId;
  }
  public void setSheetId(int sheetId) {
    this.sheetId = sheetId;
  }
  public String getSheetName() {
    return sheetName;
  }
  public void setSheetName(String sheetName) {
    this.sheetName = sheetName;
  }
  public String getIsNotFault() {
    return isNotFault;
  }
  public void setIsNotFault(String isNotFault) {
    this.isNotFault = isNotFault;
  }
  public int getByAttr() {
    return byAttr;
  }
  public void setByAttr(int byAttr) {
    this.byAttr = byAttr;
  }
  public int getByAttrField() {
    return byAttrField;
  }
  public void setByAttrField(int byAttrField) {
    this.byAttrField = byAttrField;
  }
  public int getToAttr() {
    return toAttr;
  }
  public void setToAttr(int toAttr) {
    this.toAttr = toAttr;
  }
  public String getToAttrField() {
    return toAttrField;
  }
  public void setToAttrField(String toAttrField) {
    this.toAttrField = toAttrField;
  }
  public String getByAttrName() {
    return byAttrName;
  }
  public void setByAttrName(String byAttrName) {
    this.byAttrName = byAttrName;
  }
  public String getByAttrFieldName() {
    return byAttrFieldName;
  }
  public void setByAttrFieldName(String byAttrFieldName) {
    this.byAttrFieldName = byAttrFieldName;
  }
  public String getToAttrName() {
    return toAttrName;
  }
  public void setToAttrName(String toAttrName) {
    this.toAttrName = toAttrName;
  }
  public String getToAttrFieldName() {
    return toAttrFieldName;
  }
  public void setToAttrFieldName(String toAttrFieldName) {
    this.toAttrFieldName = toAttrFieldName;
  }
  public int getDeptId() {
    return deptId;
  }
  public void setDeptId(int deptId) {
    this.deptId = deptId;
  }
  public String getDeptName() {
    return deptName;
  }
  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

}
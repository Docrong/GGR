package com.boco.eoms.duty.controller;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class TawConfRoomSheetForm extends ActionForm {
  //机房Id
  private int roomId;
  //机房名称
  private String roomName;
  //定制的附加表Id
  private int sheetId;
  //定制的附加表名称
  private String sheetName;
  //当前记录Id
  private int id;
  //机房列表
  private java.util.Collection collRoomSelect;
  //附加表列表
  private java.util.Collection collSheetSelect;
  //是否故障表列表
  private java.util.Collection collIsNotFault;
  //是否故障
  private String isNotFault;

  //按照哪个显示字段(Id)进入遗留问题列表
  //boco表中的哪个字段(Id)进入遗留问题列表

 //哪个字段(Id)进入遗留问题
  //boco中的哪个字段(Id)进入遗留问题
  private String toAttrField;

  //根据对应的Id得到对应的名称
  private String toAttrName;
  private String toAttrFieldName;
  private String byAttrName;
  private String byAttrFieldName;
  private int byAttr;
  private int byAttrField;
  private int toAttr;
  private int deptId;
  private String deptName;


  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }
  public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
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
  public java.util.Collection getCollRoomSelect() {
    return collRoomSelect;
  }
  public void setCollRoomSelect(java.util.Collection collRoomSelect) {
    this.collRoomSelect = collRoomSelect;
  }
  public java.util.Collection getCollSheetSelect() {
    return collSheetSelect;
  }
  public void setCollSheetSelect(java.util.Collection collSheetSelect) {
    this.collSheetSelect = collSheetSelect;
  }
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public java.util.Collection getCollIsNotFault() {
    return collIsNotFault;
  }
  public void setCollIsNotFault(java.util.Collection collIsNotFault) {
    this.collIsNotFault = collIsNotFault;
  }
  public String getIsNotFault() {
    return isNotFault;
  }
  public void setIsNotFault(String isNotFault) {
    this.isNotFault = isNotFault;
  }
  public int getByAttrField() {
    return byAttrField;
  }
  public void setByAttrField(int byAttrField) {
    this.byAttrField = byAttrField;
  }
  public String getToAttrField() {
    return toAttrField;
  }
  public void setToAttrField(String toAttrField) {
    this.toAttrField = toAttrField;
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
  public int getByAttr() {
    return byAttr;
  }
  public void setByAttr(int byAttr) {
    this.byAttr = byAttr;
  }
  public int getToAttr() {
    return toAttr;
  }
  public void setToAttr(int toAttr) {
    this.toAttr = toAttr;
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
package com.boco.eoms.duty.controller;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class TawDutySheetForm extends ActionForm {
  //附加表的记录Id
  private int sheet_id;
  //附加表的名称
  private String sheetName;
  //机房Id
  private int roomId;
  //机房名称
  private String roomName;
    //本机房已经配置的附加表有几张
  private int sheetCount;
    //本机房有数据的附加表有几张
  private int realCount;
  private int id;
  private int workserial;
  private int boco_Id;
  private String oper_Id;
  private String oper_Time;

  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }
  public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
  }
  public int getSheet_id() {
    return sheet_id;
  }
  public void setSheet_id(int sheet_id) {
    this.sheet_id = sheet_id;
  }
  public String getSheetName() {
    return sheetName;
  }
  public void setSheetName(String sheetName) {
    this.sheetName = sheetName;
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
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public int getWorkserial() {
    return workserial;
  }
  public void setWorkserial(int workserial) {
    this.workserial = workserial;
  }
  public int getBoco_Id() {
    return boco_Id;
  }
  public void setBoco_Id(int boco_Id) {
    this.boco_Id = boco_Id;
  }
  public String getOper_Id() {
    return oper_Id;
  }
  public void setOper_Id(String oper_Id) {
    this.oper_Id = oper_Id;
  }
  public String getOper_Time() {
    return oper_Time;
  }
  public void setOper_Time(String oper_Time) {
    this.oper_Time = oper_Time;
  }
}
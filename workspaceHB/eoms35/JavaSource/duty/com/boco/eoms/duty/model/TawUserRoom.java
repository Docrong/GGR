//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : TawUserRoom.java
//
// Copyright 2003 Company
// Generated at Thu Mar 27 10:17:32 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.duty.model;

public class TawUserRoom {
  private int ordercode;
  private int flag;
  private String userId;
  private int roomId;

  public int getOrdercode() {
    return ordercode;
  }
  public int getFlag() {
    return flag;
  }
  public String getUserId() {
    return userId;
  }
  public int getRoomId() {
    return roomId;
  }

  public void setOrdercode(int ordercode) {
    this.ordercode = ordercode;
  }
  public void setFlag(int flag) {
    this.flag = flag;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }
  public void setRoomId(int roomId) {
    this.roomId = roomId;
  }
}

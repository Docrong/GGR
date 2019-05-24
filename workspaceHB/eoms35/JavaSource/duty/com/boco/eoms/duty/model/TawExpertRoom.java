
package com.boco.eoms.duty.model;

public class TawExpertRoom {
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

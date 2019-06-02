
package com.boco.eoms.duty.model;

public class TawRmAssignExpert {
  private int workserial;
  private String expert;
  private String notes;
  private int id;

  public int getWorkserial() {
    return workserial;
  }
  public String getDutyman() {
    return expert;
  }
  public String getNotes() {
    return notes;
  }
  public int getId() {
    return id;
  }

  public void setWorkserial(int workserial) {
    this.workserial = workserial;
  }
  public void setDutyman(String expert) {
    this.expert = expert;
  }
  public void setNotes(String notes) {
    this.notes = notes;
  }
  public void setId(int id) {
    this.id = id;
  }
}

//---------------------------------------------------------
// Application: 出入机房登记
// Author     : ChengJiwu
// File       : TawRmGuestform.java
//
// Copyright 2003 Company
// Generated at Thu Apr 03 11:10:12 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.duty.model;

public class TawRmGuestform {
    private int roomId;
    //private java.sql.Timestamp inputdate;
    private String inputdate;
    private String guestname;
    private String company;
    private String sender;
    private String department;
    private String dutyman;
    //private java.sql.Timestamp starttime;
    private String starttime;
    //private java.sql.Timestamp endtime;
    private String endtime;
    private String purpose;
    private String concerned;
    private String affection;
    private int flag;
    private String notes;
    private int id;

    private String cruser;
    private String deptId;
    private String deptName;

    public int getRoomId() {
        return roomId;
    }

    //public java.sql.Timestamp getInputdate() {
    public String getInputdate() {
        return inputdate;
    }

    public String getGuestname() {
        return guestname;
    }

    public String getCompany() {
        return company;
    }

    public String getSender() {
        return sender;
    }

    public String getDepartment() {
        return department;
    }

    public String getDutyman() {
        return dutyman;
    }

    //public java.sql.Timestamp getStarttime() {
    public String getStarttime() {
        return starttime;
    }

    //public java.sql.Timestamp getEndtime() {
    public String getEndtime() {
        return endtime;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getConcerned() {
        return concerned;
    }

    public String getAffection() {
        return affection;
    }

    public int getFlag() {
        return flag;
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

    //public void setInputdate(java.sql.Timestamp inputdate) {
    public void setInputdate(String inputdate) {
        this.inputdate = inputdate;
    }

    public void setGuestname(String guestname) {
        this.guestname = guestname;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setDutyman(String dutyman) {
        this.dutyman = dutyman;
    }

    //public void setStarttime(java.sql.Timestamp starttime) {
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    //public void setEndtime(java.sql.Timestamp endtime) {
    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setConcerned(String concerned) {
        this.concerned = concerned;
    }

    public void setAffection(String affection) {
        this.affection = affection;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCruser() {
        return cruser;
    }

    public void setCruser(String cruser) {
        this.cruser = cruser;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}

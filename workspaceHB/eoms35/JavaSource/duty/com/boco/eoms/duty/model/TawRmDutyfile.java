//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : TawRmDutyfile.java
//
// Copyright 2003 Company
// Generated at Thu Mar 27 10:17:32 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.duty.model;

import org.apache.struts.upload.FormFile;

public class TawRmDutyfile {
    private int workserial;
    private String filename;
    private String encodename;
    private String dutyman;
    private String transtime;
    private int class_no;
    private String notes;
    private int id;
    private FormFile attachName;

    public int getWorkserial() {
        return workserial;
    }

    public String getFilename() {
        return filename;
    }

    public String getEncodename() {
        return encodename;
    }

    public String getDutyman() {
        return dutyman;
    }

    public String getTranstime() {
        return transtime;
    }

    public int getClass_no() {
        return class_no;
    }

    public String getNotes() {
        return notes;
    }

    public int getId() {
        return id;
    }

    public FormFile getAttachName() {
        return attachName;
    }

    public void setAttachName(FormFile attachName) {
        this.attachName = attachName;
    }

    public void setWorkserial(int workserial) {
        this.workserial = workserial;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setEncodename(String encodename) {
        this.encodename = encodename;
    }

    public void setDutyman(String dutyman) {
        this.dutyman = dutyman;
    }

    public void setTranstime(String transtime) {
        this.transtime = transtime;
    }

    public void setClass_no(int class_no) {
        this.class_no = class_no;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setId(int id) {
        this.id = id;
    }
}

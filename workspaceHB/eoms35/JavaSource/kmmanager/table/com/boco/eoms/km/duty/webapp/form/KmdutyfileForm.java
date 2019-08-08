//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : TawRmDutyfileForm.java
//
// Copyright 2003 Company
//
// Generated at Thu Mar 27 10:17:32 CST 2003
// using Karapan Sapi Struts Generator
// Visit http://www.javanovic.com
//---------------------------------------------------------

package com.boco.eoms.km.duty.webapp.form;

import org.apache.struts.action.*;
import org.apache.struts.validator.*;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.duty.model.*;

public class KmdutyfileForm extends ValidatorForm {
    public final static int ADD = 1;
    public final static int EDIT = 2;
    private int strutsAction;
    private String strutsButton = "";
    private int workserial = 0;
    private String filename = "";
    private String encodename = "";
    private String dutyman = "";
    private String transtime = "";
    private int class_no = 0;
    private String notes = "";
    private int id = 0;
    private FormFile attachName;

    public int getStrutsAction() {
        return strutsAction;
    }

    public String getStrutsButton() {
        return strutsButton;
    }

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

    public void setStrutsAction(int strutsAction) {
        this.strutsAction = strutsAction;
    }

    public void setStrutsButton(String strutsButton) {
        this.strutsButton = strutsButton;
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

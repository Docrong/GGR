//---------------------------------------------------------
// Application: Application Name
// Author     : Author
// File       : TawRmRecordSubForm.java
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

import com.boco.eoms.duty.model.*;

public class KmrecordPerForm extends ValidatorForm {
    public final static int ADD = 1;
    public final static int EDIT = 2;
    private int strutsAction;
    private String strutsButton = "";

    public int getStrutsAction() {
        return strutsAction;
    }

    public String getStrutsButton() {
        return strutsButton;
    }


    public void setStrutsAction(int strutsAction) {
        this.strutsAction = strutsAction;
    }

    public void setStrutsButton(String strutsButton) {
        this.strutsButton = strutsButton;
    }

    private int workserial = 0;
    private String recordtime = "";
    private String dutyman = "";
    private String dutymanname = "";
    private String dutyrecord = "";
    private int id = 0;


    public String getDutyman() {
        return dutyman;
    }

    public String getDutymanname() {
        return dutymanname;
    }

    public String getDutyrecord() {
        return dutyrecord;
    }

    public int getId() {
        return id;
    }

    public String getRecordtime() {
        return recordtime;
    }

    public int getWorkserial() {
        return workserial;
    }

    public void setDutyman(String dutyman) {
        this.dutyman = dutyman;
    }

    public void setDutymanname(String dutymanname) {
        this.dutymanname = dutymanname;
    }

    public void setDutyrecord(String dutyrecord) {
        this.dutyrecord = dutyrecord;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWorkserial(int workserial) {
        this.workserial = workserial;
    }

    public void setRecordtime(String recordtime) {
        this.recordtime = recordtime;
    }


}

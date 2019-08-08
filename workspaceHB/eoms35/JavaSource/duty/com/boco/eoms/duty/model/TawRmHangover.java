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

public class TawRmHangover {
    private int hangWorkserial;
    private int receiveWorkserial;
    private String hangQuestion;
    private int flag;
    private String dealer;
    private String notes;
    private int id;
    private String hangQuestion0;

    public int getHangWorkserial() {
        return hangWorkserial;
    }

    public int getReceiveWorkserial() {
        return receiveWorkserial;
    }

    public String getHangQuestion() {
        return hangQuestion;
    }

    public String getDealer() {
        return dealer;
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

    public String getHangQuestion0() {
        return hangQuestion0;
    }


    public void setHangWorkserial(int hangWorkserial) {
        this.hangWorkserial = hangWorkserial;
    }

    public void setReceiveWorkserial(int receiveWorkserial) {
        this.receiveWorkserial = receiveWorkserial;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setHangQuestion(String hangQuestion) {
        this.hangQuestion = hangQuestion;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHangQuestion0(String hangQuestion0) {
        this.hangQuestion0 = hangQuestion0;
    }

}

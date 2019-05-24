package com.boco.eoms.sparepart.model;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawRemind {
    private int id;
    private String storageid;
    private String object;
    private String amount;
    private String upperlimit;
    private String lowerlimit;
    private String limitdate;
    private String sendmsg;
    private String note;
    private int type;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getStorageid() {
        return storageid;
    }
    public void setStorageid(String storageid) {
        this.storageid = storageid;
    }
    public String getObject() {
        return object;
    }
    public void setObject(String object) {
        this.object = object;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getUpperlimit() {
        return upperlimit;
    }
    public void setUpperlimit(String upperlimit) {
        this.upperlimit = upperlimit;
    }
    public String getLowerlimit() {
        return lowerlimit;
    }
    public void setLowerlimit(String lowerlimit) {
        this.lowerlimit = lowerlimit;
    }
    public String getLimitdate() {
        return limitdate;
    }
    public void setLimitdate(String limitdate) {
        this.limitdate = limitdate;
    }
    public String getSendmsg() {
        return sendmsg;
    }
    public void setSendmsg(String sendmsg) {
        this.sendmsg = sendmsg;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
}
package com.boco.eoms.sparepart.model;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class PartRemind {
    private String storageid;
    private String object;
    private String upperlimit;
    private String lowerlimit;
    private String nowdata;
    private String sendMsg;
    public PartRemind() {
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
    public String getNowdata() {
        return nowdata;
    }
    public void setNowdata(String nowdata) {
        this.nowdata = nowdata;
    }
    public String getSendMsg() {
        return sendMsg;
    }
    public void setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg;
    }

}
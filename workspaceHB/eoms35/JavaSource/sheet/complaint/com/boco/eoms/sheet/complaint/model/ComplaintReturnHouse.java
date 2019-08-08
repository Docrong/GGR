package com.boco.eoms.sheet.complaint.model;

import java.util.Date;

public class ComplaintReturnHouse {
    private String id;
    private String sheetid;
    private String sheetkey;
    private String returnresultthree;
    private String userid;
    private String deptid;
    private String dealpartment;
    private Date returndealtime;
    private String returncontext;
    private String returnresult;
    private int count;
    private String returnresulttwo;
    private String preLinkId;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDealpartment() {
        return dealpartment;
    }

    public void setDealpartment(String dealpartment) {
        this.dealpartment = dealpartment;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReturncontext() {
        return returncontext;
    }

    public void setReturncontext(String returncontext) {
        this.returncontext = returncontext;
    }

    public Date getReturndealtime() {
        return returndealtime;
    }

    public void setReturndealtime(Date returndealtime) {
        this.returndealtime = returndealtime;
    }

    public String getSheetid() {
        return sheetid;
    }

    public void setSheetid(String sheetid) {
        this.sheetid = sheetid;
    }

    public String getSheetkey() {
        return sheetkey;
    }

    public void setSheetkey(String sheetkey) {
        this.sheetkey = sheetkey;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getReturnresult() {
        return returnresult;
    }

    public void setReturnresult(String returnresult) {
        this.returnresult = returnresult;
    }

    public String getReturnresultthree() {
        return returnresultthree;
    }

    public void setReturnresultthree(String returnresultthree) {
        this.returnresultthree = returnresultthree;
    }

    public String getReturnresulttwo() {
        return returnresulttwo;
    }

    public void setReturnresulttwo(String returnresulttwo) {
        this.returnresulttwo = returnresulttwo;
    }

    public String getPreLinkId() {
        return preLinkId;
    }

    public void setPreLinkId(String preLinkId) {
        this.preLinkId = preLinkId;
    }
}

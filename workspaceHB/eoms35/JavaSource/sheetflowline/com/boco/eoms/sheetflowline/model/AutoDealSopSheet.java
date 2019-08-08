// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   AutoDealSopSheet.java

package com.boco.eoms.sheetflowline.model;

import java.util.Date;

public class AutoDealSopSheet {

    private String id;
    private String ruleId;
    private String sheetId;
    private Date createTime;

    public AutoDealSopSheet(String ruleId, String sheetId, Date createTime) {
        this.ruleId = ruleId;
        this.sheetId = sheetId;
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }
}

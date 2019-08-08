// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonFaultTask.java

package com.boco.eoms.sheet.commonfault.task.impl;

import com.boco.eoms.sheet.base.task.ITask;
import com.boco.eoms.sheet.base.task.impl.TaskImpl;

public class CommonFaultTask extends TaskImpl
        implements ITask {

    private String preDealDept;
    private String preDealUserId;
    private String sendRejectFlag;

    public CommonFaultTask() {
    }

    public String getPreDealDept() {
        return preDealDept;
    }

    public void setPreDealDept(String preDealDept) {
        this.preDealDept = preDealDept;
    }

    public String getPreDealUserId() {
        return preDealUserId;
    }

    public void setPreDealUserId(String preDealUserId) {
        this.preDealUserId = preDealUserId;
    }

    public String getSendRejectFlag() {
        return sendRejectFlag;
    }

    public void setSendRejectFlag(String sendRejectFlag) {
        this.sendRejectFlag = sendRejectFlag;
    }
}

	


// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CommonTaskMain.java

package com.boco.eoms.sheet.commontask.model;

import com.boco.eoms.sheet.base.model.BaseMain;

public class CommonTaskMain extends BaseMain {

    private String mainNetSort1;
    private String mainNetSort2;
    private String mainNetSort3;
    private String mainTaskType;
    private String mainTaskDescription;
    private String mainRemark;
    private String ifAgent;
    private int mainAdviceNum;
    private String mainAdviceContent;
    private String revert;

    public CommonTaskMain() {
    }

    public String getIfAgent() {
        return ifAgent;
    }

    public void setIfAgent(String ifAgent) {
        this.ifAgent = ifAgent;
    }

    public String getMainNetSort1() {
        return mainNetSort1;
    }

    public void setMainNetSort1(String mainNetSort1) {
        this.mainNetSort1 = mainNetSort1;
    }

    public String getMainNetSort2() {
        return mainNetSort2;
    }

    public void setMainNetSort2(String mainNetSort2) {
        this.mainNetSort2 = mainNetSort2;
    }

    public String getMainNetSort3() {
        return mainNetSort3;
    }

    public void setMainNetSort3(String mainNetSort3) {
        this.mainNetSort3 = mainNetSort3;
    }

    public String getMainTaskType() {
        return mainTaskType;
    }

    public void setMainTaskType(String mainTaskType) {
        this.mainTaskType = mainTaskType;
    }

    public String getMainTaskDescription() {
        return mainTaskDescription;
    }

    public void setMainTaskDescription(String mainTaskDescription) {
        this.mainTaskDescription = mainTaskDescription;
    }

    public String getMainRemark() {
        return mainRemark;
    }

    public void setMainRemark(String mainRemark) {
        this.mainRemark = mainRemark;
    }

    public int getMainAdviceNum() {
        return mainAdviceNum;
    }

    public void setMainAdviceNum(int mainAdviceNum) {
        this.mainAdviceNum = mainAdviceNum;
    }

    public String getMainAdviceContent() {
        return mainAdviceContent;
    }

    public void setMainAdviceContent(String mainAdviceContent) {
        this.mainAdviceContent = mainAdviceContent;
    }

    public String getRevert() {
        return revert;
    }

    public void setRevert(String revert) {
        this.revert = revert;
    }
}

// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TechniqueSupportHbLink.java

package com.boco.eoms.sheet.techniquesupporthb.model;

import com.boco.eoms.sheet.base.model.BaseLink;

public class TechniqueSupportHbLink extends BaseLink {

    private String linkDealOpinion;

    public TechniqueSupportHbLink() {
    }

    public String getLinkDealOpinion() {
        return linkDealOpinion;
    }

    public void setLinkDealOpinion(String linkDealOpinion) {
        this.linkDealOpinion = linkDealOpinion;
    }

    private String workTime;

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }
}

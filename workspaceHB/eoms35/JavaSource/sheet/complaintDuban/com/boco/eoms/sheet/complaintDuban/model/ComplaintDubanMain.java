
package com.boco.eoms.sheet.complaintDuban.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseMain;

public class ComplaintDubanMain extends BaseMain {
    private String complaintText;
    private String complaintInfo;
    private String complaintType;
    private String trailId;
    private String eomsId;
    private String complaintSort;
    private String appearance;
    private String acceptNum;
    private String opinion;

    public String getComplaintInfo() {
        return complaintInfo;
    }

    public void setComplaintInfo(String complaintInfo) {
        this.complaintInfo = complaintInfo;
    }

    public String getComplaintText() {
        return complaintText;
    }

    public void setComplaintText(String complaintText) {
        this.complaintText = complaintText;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getAcceptNum() {
        return acceptNum;
    }

    public void setAcceptNum(String acceptNum) {
        this.acceptNum = acceptNum;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getComplaintSort() {
        return complaintSort;
    }

    public void setComplaintSort(String complaintSort) {
        this.complaintSort = complaintSort;
    }

    public String getEomsId() {
        return eomsId;
    }

    public void setEomsId(String eomsId) {
        this.eomsId = eomsId;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getTrailId() {
        return trailId;
    }

    public void setTrailId(String trailId) {
        this.trailId = trailId;
    }
}

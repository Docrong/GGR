
package com.boco.eoms.sheet.complaintDuban.model;

import java.util.Date;

import com.boco.eoms.sheet.base.model.BaseLink;

public class ComplaintDubanLink extends BaseLink {

    private String linkOperationDesc;

    private String delayCase;
    private Date delayTime;
    private Date applyTime;
    private Date approveTime;
    private String solveCond;
    private String solveTool;
    private String projectName;
    private String approveResult;
    private String approveOpinion;
    private String linkTdComplaint;

    public String getLinkOperationDesc() {
        return linkOperationDesc;
    }

    public void setLinkOperationDesc(String linkOperationDesc) {
        this.linkOperationDesc = linkOperationDesc;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApproveOpinion() {
        return approveOpinion;
    }

    public void setApproveOpinion(String approveOpinion) {
        this.approveOpinion = approveOpinion;
    }

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult;
    }

    public Date getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Date approveTime) {
        this.approveTime = approveTime;
    }

    public String getDelayCase() {
        return delayCase;
    }

    public void setDelayCase(String delayCase) {
        this.delayCase = delayCase;
    }

    public Date getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Date delayTime) {
        this.delayTime = delayTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSolveCond() {
        return solveCond;
    }

    public void setSolveCond(String solveCond) {
        this.solveCond = solveCond;
    }

    public String getSolveTool() {
        return solveTool;
    }

    public void setSolveTool(String solveTool) {
        this.solveTool = solveTool;
    }

    public String getLinkTdComplaint() {
        return linkTdComplaint;
    }

    public void setLinkTdComplaint(String linkTdComplaint) {
        this.linkTdComplaint = linkTdComplaint;
    }

}

package com.boco.eoms.filemanager.form;

/**
 * Created by IntelliJ IDEA.
 * User: liqifei
 * Date: 2005-9-26
 * Time: 10:25:57
 * Boco Corporation
 * 部门：亿阳信通软件研究院 EOMS
 * 地址：北京市海淀区亮甲店130号亿阳大厦 12层3室
 * To change this template use File | Settings | File Templates.
 */
public class StatResultBean {
    private String reportId; //按报表统计
    private String reportName;
    private String acceptDeptId;//按部门统计
    private String acceptDeptName;
    private int intimeCount;
    private int validCount;
    private int notAcceptCount;
    private int totalCount;
    private String intimeRate; //及时率
    private String validRate;//合格率

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getAcceptDeptId() {
        return acceptDeptId;
    }

    public void setAcceptDeptId(String acceptDeptId) {
        this.acceptDeptId = acceptDeptId;
    }

    public String getAcceptDeptName() {
        return acceptDeptName;
    }

    public void setAcceptDeptName(String acceptDeptName) {
        this.acceptDeptName = acceptDeptName;
    }

    public int getIntimeCount() {
        return intimeCount;
    }

    public void setIntimeCount(int intimeCount) {
        this.intimeCount = intimeCount;
    }

    public int getNotAcceptCount() {
        return notAcceptCount;
    }

    public void setNotAcceptCount(int notAcceptCount) {
        this.notAcceptCount = notAcceptCount;
    }

    public int getValidCount() {
        return validCount;
    }

    public void setValidCount(int validCount) {
        this.validCount = validCount;
    }

    public String getIntimeRate() {
        return intimeRate;
    }

    public void setIntimeRate(String intimeRate) {
        this.intimeRate = intimeRate;
    }

    public String getValidRate() {
        return validRate;
    }

    public void setValidRate(String validRate) {
        this.validRate = validRate;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}

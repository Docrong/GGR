package com.boco.eoms.extra.supplierkpi.report.common;

public class ReportInfo {
    private String reportPath = "";
    private String destPath = "";
    private String sql = "";
    private boolean realtime = false;
    private Parameter[] parameters = null;

    public String getDestPath() {
        return destPath;
    }

    public boolean isRealtime() {
        return realtime;
    }

    public void setRealtime(boolean realtime) {
        this.realtime = realtime;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    public Parameter[] getParameters() {
        return parameters;
    }

    public void setParameters(Parameter[] parameters) {
        this.parameters = parameters;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}

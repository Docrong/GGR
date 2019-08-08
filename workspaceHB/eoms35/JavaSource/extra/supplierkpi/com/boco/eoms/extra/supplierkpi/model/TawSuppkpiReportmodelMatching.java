package com.boco.eoms.extra.supplierkpi.model;

import java.sql.Timestamp;

import com.boco.eoms.base.model.BaseObject;

public class TawSuppkpiReportmodelMatching extends BaseObject {

    private String id;
    private String modelId;               //报表模型ID
    private String kpiItemId;             //kpi指标ID
    private String reportcol;             //报表列
    private String createMan;             //创建人
    private Timestamp createTime;         //创建时间
    private String state;                 //状态
    private Timestamp deleteTime;         //删除时间

    public String getCreateMan() {
        return createMan;
    }

    public void setCreateMan(String createMan) {
        this.createMan = createMan;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Timestamp deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKpiItemId() {
        return kpiItemId;
    }

    public void setKpiItemId(String kpiItemId) {
        this.kpiItemId = kpiItemId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getReportcol() {
        return reportcol;
    }

    public void setReportcol(String reportcol) {
        this.reportcol = reportcol;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

}

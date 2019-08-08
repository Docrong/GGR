package com.boco.eoms.sheet.supervisetask.model;

/**
 * 备份发送到主体责任单的数据
 *
 * @author gr
 */
public class SuperviseTaskMainDuty {


    private String id;
    private String deleted;//0未删除 |1已删除
    private String workflowType;//工单类型commonfault|listedregulation
    private String sheetid;//工单ID
    private String sendtime;//发送时间
    private String workflowStatus;//工单状态 0|1
    private String errorSource;//故障来源
    private String cityid;//地市
    private String userid;//问题派发人
    private String deptid;//派发人部门
    private String errorDesc;//故障描述
    private String expectGoal;//成效评估目标


    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public String getErrorSource() {
        return errorSource;
    }

    public void setErrorSource(String errorSource) {
        this.errorSource = errorSource;
    }

    public String getExpectGoal() {
        return expectGoal;
    }

    public void setExpectGoal(String expectGoal) {
        this.expectGoal = expectGoal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getSheetid() {
        return sheetid;
    }

    public void setSheetid(String sheetid) {
        this.sheetid = sheetid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    public String getWorkflowType() {
        return workflowType;
    }

    public void setWorkflowType(String workflowType) {
        this.workflowType = workflowType;
    }


}

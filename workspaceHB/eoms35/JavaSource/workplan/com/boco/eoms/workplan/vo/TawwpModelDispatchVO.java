package com.boco.eoms.workplan.vo;

/**
 * <p>Title: 作业计划模板派发信息数据显示类</p>
 * <p>Description: 提供页面的所需的数据封装</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

public class TawwpModelDispatchVO {
    private String id; //标识
    private String sendDeptId; //派发部门
    private String sendCrtime; //派发时间
    private String sendUser; //派发人
    private String inceptDeptId; //接收部门
    private String inceptCrtime; //接收时间
    private String inceptUser; //接收人
    private String state; //派发状态 0：待接收 1：接收
    private String content; //派发信息
    private String yearFlag; //年度标识

    public TawwpModelDispatchVO() {
    }

    public String getId() {
        if (id == null) {
            id = "";
        }

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        if (content == null) {
            content = "";
        }

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getInceptCrtime() {
        if (inceptCrtime == null) {
            inceptCrtime = "";
        }

        return inceptCrtime;
    }

    public void setInceptCrtime(String inceptCrtime) {
        this.inceptCrtime = inceptCrtime;
    }

    public String getInceptDeptId() {
        if (inceptDeptId == null) {
            inceptDeptId = "";
        }

        return inceptDeptId;
    }

    public void setInceptDeptId(String inceptDeptId) {
        this.inceptDeptId = inceptDeptId;
    }

    public String getInceptUser() {
        if (inceptUser == null) {
            inceptUser = "";
        }

        return inceptUser;
    }

    public void setInceptUser(String inceptUser) {
        this.inceptUser = inceptUser;
    }

    public String getSendCrtime() {
        if (sendCrtime == null) {
            sendCrtime = "";
        }

        return sendCrtime;
    }

    public void setSendCrtime(String sendCrtime) {
        this.sendCrtime = sendCrtime;
    }

    public String getSendDeptId() {
        if (sendDeptId == null) {
            sendDeptId = "";
        }

        return sendDeptId;
    }

    public void setSendDeptId(String sendDeptId) {
        this.sendDeptId = sendDeptId;
    }

    public String getSendUser() {
        if (sendUser == null) {
            sendUser = "";
        }

        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getState() {
        if (state == null) {
            state = "";
        }

        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getYearFlag() {
        if (yearFlag == null) {
            yearFlag = "";
        }

        return yearFlag;
    }

    public void setYearFlag(String yearFlag) {
        this.yearFlag = yearFlag;
    }

}

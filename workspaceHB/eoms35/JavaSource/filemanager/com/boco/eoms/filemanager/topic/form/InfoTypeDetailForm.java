package com.boco.eoms.filemanager.topic.form;

import org.apache.struts.action.ActionForm;

/**
 * Created by IntelliJ IDEA.
 * User: lxl
 * Date: 2003-8-30
 * Time: 9:40:13
 * To change this template use Options | File Templates.
 */
public class InfoTypeDetailForm extends ActionForm {
    private String actId = "";
    private String hdId = "";
    private String hdClassId = "";
    private String hdTopicPath = "";
    private String hdParentId = "";
    private String[] selectPrvlg = null;
    private String topicTypeId="1";
    private String topicTypeName="";

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getHdId() {
        return hdId;
    }

    public void setHdId(String hdId) {
        this.hdId = hdId;
    }

    public String getHdClassId() {
        return hdClassId;
    }

    public void setHdClassId(String hdClassId) {
        this.hdClassId = hdClassId;
    }

    public String getHdTopicPath() {
        return hdTopicPath;
    }

    public void setHdTopicPath(String hdTopicPath) {
        this.hdTopicPath = hdTopicPath;
    }

    public String getHdParentId() {
        return hdParentId;
    }

    public void setHdParentId(String hdParentId) {
        this.hdParentId = hdParentId;
    }

    public String[] getSelectPrvlg() {
        return selectPrvlg;
    }

    public void setSelectPrvlg(String[] selectPrvlg) {
        this.selectPrvlg = selectPrvlg;
    }

    private String topicName = "";
    private String topicPath = "";
    private String topicOrder = "";
    private String topicMemo = "";
    private String classId = "";

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicPath() {
        return topicPath;
    }

    public void setTopicPath(String topicPath) {
        this.topicPath = topicPath;
    }

    public String getTopicOrder() {
        return topicOrder;
    }

    public void setTopicOrder(String topicOrder) {
        this.topicOrder = topicOrder;
    }

    public String getTopicMemo() {
        return topicMemo;
    }

    public void setTopicMemo(String topicMemo) {
        this.topicMemo = topicMemo;
    }

    public String getTopicTypeId() {
        return topicTypeId;
    }

    public void setTopicTypeId(String topicTypeId) {
        this.topicTypeId = topicTypeId;
    }

    public String getTopicTypeName() {
        return topicTypeName;
    }

    public void setTopicTypeName(String topicTypeName) {
        this.topicTypeName = topicTypeName;
    }
}

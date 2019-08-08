package com.boco.eoms.infmanage.controller;

import org.apache.struts.action.ActionForm;

import java.util.Collection;

public class TawInfInfoForm
        extends ActionForm {
    // 记录ID
    private int id;

    // 资料编号
    private String infInfoId;

    // 资料名称
    private String infInfoName;

    // 资料类别编号
    private int infSortId;

    // 资料类别名称
    private String infSortName;

    // 资料所属部门编号
    private int deptId;

    // 资料所属部门名称
    private String deptName;

    // 上传人ID
    private String infUpId;

    // 上传人姓名
    private String infUpName;

    // 上传文件名称
    private String infUpfileName;

    // 上传时间
    private String infUpTime;

    private java.util.Collection collectionSort;

    public TawInfInfoForm() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfInfoId() {
        return infInfoId;
    }

    public void setInfInfoId(String infInfoId) {
        this.infInfoId = infInfoId;
    }

    public String getInfInfoName() {
        return infInfoName;
    }

    public void setInfInfoName(String infInfoName) {
        this.infInfoName = infInfoName;
    }

    public int getInfSortId() {
        return infSortId;
    }

    public void setInfSortId(int infSortId) {
        this.infSortId = infSortId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getInfUpId() {
        return infUpId;
    }

    public void setInfUpId(String infUpId) {
        this.infUpId = infUpId;
    }

    public String getInfUpTime() {
        return infUpTime;
    }

    public void setInfUpTime(String infUpTime) {
        this.infUpTime = infUpTime;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getInfSortName() {
        return infSortName;
    }

    public void setInfSortName(String infSortName) {
        this.infSortName = infSortName;
    }

    public String getInfUpName() {
        return infUpName;
    }

    public void setInfUpName(String infUpName) {
        this.infUpName = infUpName;
    }

    public java.util.Collection getCollectionSort() {
        return collectionSort;
    }

    public void setCollectionSort(java.util.Collection collectionSort) {
        this.collectionSort = collectionSort;
    }

    public String getInfUpfileName() {
        return infUpfileName;
    }

    public void setInfUpfileName(String infUpfileName) {
        this.infUpfileName = infUpfileName;
    }

}
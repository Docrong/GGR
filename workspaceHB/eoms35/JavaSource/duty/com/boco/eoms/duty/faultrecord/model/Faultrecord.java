package com.boco.eoms.duty.faultrecord.model;

import java.sql.Timestamp;

/**
 * <p>Title: ���ϼ�¼</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author zhangxiaobo
 * @version 1.0
 */

public class Faultrecord{

    public Faultrecord() {
    }
    
    /** ������� */
    private String id;
    
    /** ������ */
    private String userId;
    
    /** �������� */
    private String deptId;
    
    /** ����ʱ�� */
    private Timestamp insertTime;

    /** ɾ���� */
    private int delFlag;

    /** ���� */
    private String accessories;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public Timestamp getInsertTime() {
        return this.insertTime;
    }

    public void setInsertTime(Timestamp insertTime) {
        this.insertTime = insertTime;
    }

    public int getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public String getAccessories() {
        return this.accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }


  /*****************************************************************
   * �����Ǹ��?���е�����
   *****************************************************************/


    /** �澯ʱ�� */
    private Timestamp startTime;
    
    public Timestamp getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }


    /** ��Ԫ��� */
    private String networkName;
    
    public String getNetworkName() {
        return this.networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }


    /** �豸���� */
    private int devVendor;
    
    public int getDevVendor() {
        return this.devVendor;
    }

    public void setDevVendor(int devVendor) {
        this.devVendor = devVendor;
    }


    /** �豸���� */
    private int devicetype;
    
    public int getDevicetype() {
        return this.devicetype;
    }

    public void setDevicetype(int devicetype) {
        this.devicetype = devicetype;
    }


    /** ���ϵ�Ԫ���� */
    private int faultUnitLevel;
    
    public int getFaultUnitLevel() {
        return this.faultUnitLevel;
    }

    public void setFaultUnitLevel(int faultUnitLevel) {
        this.faultUnitLevel = faultUnitLevel;
    }


    /** ���ϼ��� */
    private int faultLevel;
    
    public int getFaultLevel() {
        return this.faultLevel;
    }

    public void setFaultLevel(int faultLevel) {
        this.faultLevel = faultLevel;
    }


    /** �������� */
    private String faultContent;
    
    public String getFaultContent() {
        return this.faultContent;
    }

    public void setFaultContent(String faultContent) {
        this.faultContent = faultContent;
    }


    /** ���ϴ������ */
    private String faultResult;
    
    public String getFaultResult() {
        return this.faultResult;
    }

    public void setFaultResult(String faultResult) {
        this.faultResult = faultResult;
    }


    /** �����걨�� */
    private String declareUser;
    
    public String getDeclareUser() {
        return this.declareUser;
    }

    public void setDeclareUser(String declareUser) {
        this.declareUser = declareUser;
    }


    /** �걨ʱ�� */
    private Timestamp declareTime;
    
    public Timestamp getDeclareTime() {
        return this.declareTime;
    }

    public void setDeclareTime(Timestamp declareTime) {
        this.declareTime = declareTime;
    }


    /** ���ϴ����� */
    private String dealUser;
    
    public String getDealUser() {
        return this.dealUser;
    }

    public void setDealUser(String dealUser) {
        this.dealUser = dealUser;
    }


    /** ����ʱ�� */
    private Timestamp dealTime;
    
    public Timestamp getDealTime() {
        return this.dealTime;
    }

    public void setDealTime(Timestamp dealTime) {
        this.dealTime = dealTime;
    }


    /** ���ϻָ� */
    private Timestamp endTime;
    
    public Timestamp getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }


    /** ������ */
    private int problemSolveInfo;
    
    public int getProblemSolveInfo() {
        return this.problemSolveInfo;
    }

    public void setProblemSolveInfo(int problemSolveInfo) {
        this.problemSolveInfo = problemSolveInfo;
    }


    /** ������ʱ */
    private String totalTime;
    
    public String getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }


    /** ҵ���ж� */
    private String operHaltTime;
    
    public String getOperHaltTime() {
        return this.operHaltTime;
    }

    public void setOperHaltTime(String operHaltTime) {
        this.operHaltTime = operHaltTime;
    }


    /** ��ע */
    private String remark;
    
    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /** ��ע */
    private String insertTimeFrom;
    
    public String getInsertTimeFrom() {
        return this.insertTimeFrom;
    }

    public void setInsertTimeFrom(String insertTimeFrom) {
        this.insertTimeFrom = insertTimeFrom;
    }

    /** ��ע */
    private String insertTimeTo;
    
    public String getInsertTimeTo() {
        return this.insertTimeTo;
    }

    public void setInsertTimeTo(String insertTimeTo) {
        this.insertTimeTo = insertTimeTo;
    }
    
}
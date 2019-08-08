package com.boco.eoms.sheet.ptnpretreatmentrule.model;


public class PtnPretreatmentRule {

    /**
     * 厂家
     */
    private String factory;

    /**
     * 设备类型
     */
    private String equipmentType;

    /**
     * 告警名称
     */
    private String alarmName;

    /**
     * 告警ID
     */
    private String alatmID;

    /**
     * 故障原因及处理措施
     */
    private String faultDealDesc;

    /**
     * 归因一级
     */
    private String faultReasonSort1;

    /**
     * 归因二级
     */
    private String faultReasonSort2;


    /**
     * 归因三级
     */
    private String faultReasonSort3;

    /**
     * 预处理对应关系
     */
    private String preDealRelation;

    /**
     * 主键
     */
    private String id;

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getAlatmID() {
        return alatmID;
    }

    public void setAlatmID(String alatmID) {
        this.alatmID = alatmID;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getFaultDealDesc() {
        return faultDealDesc;
    }

    public void setFaultDealDesc(String faultDealDesc) {
        this.faultDealDesc = faultDealDesc;
    }

    public String getFaultReasonSort1() {
        return faultReasonSort1;
    }

    public void setFaultReasonSort1(String faultReasonSort1) {
        this.faultReasonSort1 = faultReasonSort1;
    }

    public String getFaultReasonSort2() {
        return faultReasonSort2;
    }

    public void setFaultReasonSort2(String faultReasonSort2) {
        this.faultReasonSort2 = faultReasonSort2;
    }

    public String getFaultReasonSort3() {
        return faultReasonSort3;
    }

    public void setFaultReasonSort3(String faultReasonSort3) {
        this.faultReasonSort3 = faultReasonSort3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPreDealRelation() {
        return preDealRelation;
    }

    public void setPreDealRelation(String preDealRelation) {
        this.preDealRelation = preDealRelation;
    }

}

package com.boco.eoms.sheet.autotransmitrule.model;


import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;


public class AutoTransmitRule extends BaseObject implements Serializable {

    private String id;
    private String netTypeOne;
    private String netTypeTwo;
    private String netTypeThree;
    private String roleId;
    private String domainId;
    private String equipmentFactory;
    private String faultResponseLevel;
    private String stopTime;

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getEquipmentFactory() {
        return equipmentFactory;
    }

    public void setEquipmentFactory(String equipmentFactory) {
        this.equipmentFactory = equipmentFactory;
    }

    public String getFaultResponseLevel() {
        return faultResponseLevel;
    }

    public void setFaultResponseLevel(String faultResponseLevel) {
        this.faultResponseLevel = faultResponseLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNetTypeOne() {
        return netTypeOne;
    }

    public void setNetTypeOne(String netTypeOne) {
        this.netTypeOne = netTypeOne;
    }

    public String getNetTypeThree() {
        return netTypeThree;
    }

    public void setNetTypeThree(String netTypeThree) {
        this.netTypeThree = netTypeThree;
    }

    public String getNetTypeTwo() {
        return netTypeTwo;
    }

    public void setNetTypeTwo(String netTypeTwo) {
        this.netTypeTwo = netTypeTwo;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public boolean equals(Object arg0) {
        // TODO Auto-generated method stub
        return false;
    }


}

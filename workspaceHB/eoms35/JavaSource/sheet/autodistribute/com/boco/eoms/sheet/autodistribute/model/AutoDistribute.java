package com.boco.eoms.sheet.autodistribute.model;


import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;


public class AutoDistribute extends BaseObject implements Serializable {

    private String id;
    private String flowName;
    private String autoType;
    private String roleId;
    private String threshold;

    public String getAutoType() {
        return autoType;
    }

    public void setAutoType(String autoType) {
        this.autoType = autoType;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean equals(Object arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }


}

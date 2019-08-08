/*
 *
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.userdefinegroup.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wangjianhua@boco.com.cn
 * <p>
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class UserdefineGroup implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String dealRoleId;

    private String dealRoleName;

    private String dealUserId;

    private String dealUserName;

    private String dealDeptId;

    private String dealDeptName;

    private String flowId;

    private String flowName;

    private String userId;

    private String remark1;

    private String remark2;

    private Date createDate;


    public String getDealDeptName() {
        return dealDeptName;
    }

    public void setDealDeptName(String dealDeptName) {
        this.dealDeptName = dealDeptName;
    }

    public String getDealRoleName() {
        return dealRoleName;
    }

    public void setDealRoleName(String dealRoleName) {
        this.dealRoleName = dealRoleName;
    }

    public String getDealUserName() {
        return dealUserName;
    }

    public void setDealUserName(String dealUserName) {
        this.dealUserName = dealUserName;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDealDeptId() {
        return dealDeptId;
    }

    public void setDealDeptId(String dealDeptId) {
        this.dealDeptId = dealDeptId;
    }

    public String getDealRoleId() {
        return dealRoleId;
    }

    public void setDealRoleId(String dealRoleId) {
        this.dealRoleId = dealRoleId;
    }

    public String getDealUserId() {
        return dealUserId;
    }

    public void setDealUserId(String dealUserId) {
        this.dealUserId = dealUserId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFlowName() {
        return flowName;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }


}

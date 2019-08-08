/*
 * Created on 2007-8-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.boco.eoms.sheet.base.model;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Administrator
 * <p>
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BaseLink implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 关联main id
     */
    private String mainId;

    /**
     * 接收时限
     */
    private Date nodeAcceptLimit;

    /**
     * 完成时限
     */
    private Date nodeCompleteLimit;

    /**
     * 操作名称
     */
    private Integer operateType;

    /**
     * 操作时间
     */
    private Date operateTime;

    /**
     * 操作组织类型,用户、角色、部门
     */
    private String operateOrgType;

    /**
     * 操作者用户ID
     */
    private String operateUserId;

    /**
     * 操作者部门ID
     */
    private String operateDeptId;

    /**
     * 操作者角色id
     */
    private String operateRoleId;

    /**
     * 目的对象的类型 部门 角色 人
     */
    private Integer toOrgType;

    /**
     * 目的用户id
     */
    private String toOrgUserId;

    /**
     * 目的部门id
     */
    private String toOrgDeptId;

    /**
     * 目的角色id
     */
    private String toOrgRoleId;

    /**
     * 接单类型,不超时,超时
     */
    private Integer acceptFlag;

    /**
     * 接单时间
     */
    private Date acceptTime;

    /**
     * 完成超时类型 超时 正常
     */
    private Integer completeFlag;

    /**
     * 完成时间
     */
    private Date completeTime;

    /**
     * 上一条link id
     */
    private String preLinkId;

    /**
     * 父流程的linkid 即产生子流程的那条link的主键
     */
    private String parentLinkId;

    /**
     * 对应每个工单中的第一条link的主键
     */
    private String firstLinkId;

    /**
     * 流程实例号，根据所属的流程不同而不同（不同实例或者不同的父子流程中）
     */
    private String piid;

    /**
     * 操作所对应的active的实例id
     */
    private String aiid;

    /**
     * WPS的stepID，通常都是一个humantask的id，注意这个id不是humantask的实例id
     */
    private String activeTemplateId;

    /**
     * 模板名称 为将来可能出现的模板方式预留，在卓越流程中暂时还没有发现有用到
     */
    private String nodeTemplateName;

    /**
     * 附件
     */
    private String nodeAccessories;

    /**
     * 流程关联Key
     */
    private String correlationKey;

    /**
     * 操作所对应的taskid
     */
    private String tkid;

    /**
     * 移交说明
     * add by zhangying
     */

    private String transferReason;

    private int templateFlag;
    /**
     * 操作者联系方式
     */
    private String operaterContact;

    /**
     * 模板名称
     *
     * @author wangjianhua
     * @date 2008-7-22
     */
    private String templateName;

    /**
     * 创建模板的用户
     *
     * @author wangjianhua
     * @date 2008-7-22
     */
    private String templateCreateUserId;

    /**
     * 备注
     *
     * @author yangliangliang
     * @date 2008-8-6
     */
    private String remark;

    /**
     * 操作时间中的年份
     *
     * @author qinmin
     * @date 2008-10-07
     */
    private int operateYear;
    /**
     * 操作时间中的月份
     *
     * @author qinmin
     * @date 2008-10-07
     */
    private int operateMonth;
    /**
     * 操作时间中的天
     *
     * @author qinmin
     * @date 2008-10-07
     */
    private int operateDay;
    /**
     * 派往对象
     *
     * @author wangjianhua
     * @date 2009-2-12
     */
    private String sendObject;

    public String getSendObject() {
        return sendObject;
    }

    public void setSendObject(String sendObject) {
        this.sendObject = sendObject;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return templateName
     */
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * @return userId
     */
    public String getTemplateCreateUserId() {
        return templateCreateUserId;
    }

    public void setTemplateCreateUserId(String templateCreateUserId) {
        this.templateCreateUserId = templateCreateUserId;
    }

    /**
     * @return template
     */
    public int getTemplateFlag() {
        return templateFlag;
    }

    /**
     * @param template
     */
    public void setTemplateFlag(int templateFlag) {
        this.templateFlag = templateFlag;
    }

    /**
     * @return Returns the accessories.
     */
    public String getNodeAccessories() {
        return nodeAccessories;
    }

    /**
     * @param accessories The accessories to set.
     */
    public void setNodeAccessories(String accessories) {
        this.nodeAccessories = accessories;
    }

    /**
     * @return the acceptLimit
     */
    public Date getNodeAcceptLimit() {
        return nodeAcceptLimit;
    }

    /**
     * @param acceptLimit the acceptLimit to set
     */
    public void setNodeAcceptLimit(Date acceptLimit) {
        this.nodeAcceptLimit = acceptLimit;
    }

    /**
     * @return the acceptTime
     */
    public Date getAcceptTime() {
        return acceptTime;
    }

    /**
     * @param acceptTime the acceptTime to set
     */
    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    /**
     * @return the acceptFlag
     */
    public Integer getAcceptFlag() {
        return acceptFlag;
    }

    /**
     * @param acceptType the acceptType to set
     */
    public void setAcceptFlag(Integer acceptFlag) {
        this.acceptFlag = acceptFlag;
    }

    /**
     * @return the activeTemplateId
     */
    public String getActiveTemplateId() {
        return activeTemplateId;
    }

    /**
     * @param activeTemplateId the activeTemplateId to set
     */
    public void setActiveTemplateId(String activeTemplateId) {
        this.activeTemplateId = activeTemplateId;
    }

    /**
     * @return the aiid
     */
    public String getAiid() {
        return aiid;
    }

    /**
     * @param aiid the aiid to set
     */
    public void setAiid(String aiid) {
        this.aiid = aiid;
    }

    /**
     * @return the commpleteFlag
     */
    public Integer getCompleteFlag() {
        return completeFlag;
    }

    /**
     * @param commpleteFlag the commpleteFlag to set
     */
    public void setCompleteFlag(Integer completeFlag) {
        this.completeFlag = completeFlag;
    }

    /**
     * @return the completeLimit
     */
    public Date getNodeCompleteLimit() {
        return nodeCompleteLimit;
    }

    /**
     * @param completeLimit the completeLimit to set
     */
    public void setNodeCompleteLimit(Date completeLimit) {
        this.nodeCompleteLimit = completeLimit;
    }

    /**
     * @return the completeTime
     */
    public Date getCompleteTime() {
        return completeTime;
    }

    /**
     * @param completeTime the completeTime to set
     */
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    /**
     * @return the firstLinkId
     */
    public String getFirstLinkId() {
        return firstLinkId;
    }

    /**
     * @param firstLinkId the firstLinkId to set
     */
    public void setFirstLinkId(String firstLinkId) {
        this.firstLinkId = firstLinkId;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the mainId
     */
    public String getMainId() {
        return mainId;
    }

    /**
     * @param mainId the mainId to set
     */
    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    /**
     * @return the operateDeptId
     */
    public String getOperateDeptId() {
        return operateDeptId;
    }

    /**
     * @param operateDeptId the operateDeptId to set
     */
    public void setOperateDeptId(String operateDeptId) {
        this.operateDeptId = operateDeptId;
    }

    /**
     * @return the operateOrgType
     */
    public String getOperateOrgType() {
        return operateOrgType;
    }

    /**
     * @param operateOrgType the operateOrgType to set
     */
    public void setOperateOrgType(String operateOrgType) {
        this.operateOrgType = operateOrgType;
    }

    /**
     * @return the operateRoleId
     */
    public String getOperateRoleId() {
        return operateRoleId;
    }

    /**
     * @param operateRoleId the operateRoleId to set
     */
    public void setOperateRoleId(String operateRoleId) {
        this.operateRoleId = operateRoleId;
    }

    /**
     * @return the operateTime
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * @param operateTime the operateTime to set
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * @return the operateType
     */
    public Integer getOperateType() {
        return operateType;
    }

    /**
     * @param operateType the operateType to set
     */
    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    /**
     * @return the operateUserId
     */
    public String getOperateUserId() {
        return operateUserId;
    }

    /**
     * @param operateUserId the operateUserId to set
     */
    public void setOperateUserId(String operateUserId) {
        this.operateUserId = operateUserId;
    }

    /**
     * @return the parentLinkId
     */
    public String getParentLinkId() {
        return parentLinkId;
    }

    /**
     * @param parentLinkId the parentLinkId to set
     */
    public void setParentLinkId(String parentLinkId) {
        this.parentLinkId = parentLinkId;
    }

    /**
     * @return the piid
     */
    public String getPiid() {
        return piid;
    }

    /**
     * @param piid the piid to set
     */
    public void setPiid(String piid) {
        this.piid = piid;
    }

    /**
     * @return the preLinkId
     */
    public String getPreLinkId() {
        return preLinkId;
    }

    /**
     * @param preLinkId the preLinkId to set
     */
    public void setPreLinkId(String preLinkId) {
        this.preLinkId = preLinkId;
    }

    /**
     * @return the templateName
     */
    public String getNodeTemplateName() {
        return nodeTemplateName;
    }

    /**
     * @param templateName the templateName to set
     */
    public void setNodeTemplateName(String templateName) {
        this.nodeTemplateName = templateName;
    }

    /**
     * @return the toOrgDeptId
     */
    public String getToOrgDeptId() {
        return toOrgDeptId;
    }

    /**
     * @param toOrgDeptId the toOrgDeptId to set
     */
    public void setToOrgDeptId(String toOrgDeptId) {
        this.toOrgDeptId = toOrgDeptId;
    }

    /**
     * @return the toOrgRoleId
     */
    public String getToOrgRoleId() {
        return toOrgRoleId;
    }

    /**
     * @param toOrgRoleId the toOrgRoleId to set
     */
    public void setToOrgRoleId(String toOrgRoleId) {
        this.toOrgRoleId = toOrgRoleId;
    }

    /**
     * @return the toOrgType
     */
    public Integer getToOrgType() {
        return toOrgType;
    }

    /**
     * @param toOrgType the toOrgType to set
     */
    public void setToOrgType(Integer toOrgType) {
        this.toOrgType = toOrgType;
    }

    /**
     * @return the toOrgUserId
     */
    public String getToOrgUserId() {
        return toOrgUserId;
    }

    /**
     * @param toOrgUserId the toOrgUserId to set
     */
    public void setToOrgUserId(String toOrgUserId) {
        this.toOrgUserId = toOrgUserId;
    }

    /**
     * @return Returns the correlationKey.
     */
    public String getCorrelationKey() {
        return correlationKey;
    }

    /**
     * @param correlationKey The correlationKey to set.
     */
    public void setCorrelationKey(String correlationKey) {
        this.correlationKey = correlationKey;
    }


    /**
     * @return Returns the tkid.
     */
    public String getTkid() {
        return tkid;
    }

    /**
     * @param tkid The tkid to set.
     */
    public void setTkid(String tkid) {
        this.tkid = tkid;
    }

    public String getTransferReason() {
        return transferReason;
    }

    public void setTransferReason(String transferReason) {
        this.transferReason = transferReason;
    }

    public String getOperaterContact() {
        return operaterContact;
    }

    public void setOperaterContact(String operaterContact) {
        this.operaterContact = operaterContact;
    }

    public int getOperateDay() {
        return operateDay;
    }

    public void setOperateDay(int operateDay) {
        this.operateDay = operateDay;
    }

    public int getOperateMonth() {
        return operateMonth;
    }

    public void setOperateMonth(int operateMonth) {
        this.operateMonth = operateMonth;
    }

    public int getOperateYear() {
        return operateYear;
    }

    public void setOperateYear(int operateYear) {
        this.operateYear = operateYear;
    }


}

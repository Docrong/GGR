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
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BaseMain implements Serializable{
	 /**
     * 主键
     */
    private String id;
 
    /**
     * 工单id
     */
    private String sheetId;

    /**
     * 工单标题
     */
    private String title;

    /**
     * 接收时限
     */
    private Date sheetAcceptLimit;

    /**
     * 完成时限
     */
    private Date sheetCompleteLimit;

    /**
     * 附件地址
     */
    private String sheetAccessories;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 发送组织类型，用户，部门，角色
     */
    private String  sendOrgType;

    /**
     * 发送人id
     */
    private String sendUserId;

    /**
     * 发送部门id,注：由于dept的主键为int故这里使用Integer类型，建议以后改为统一string
     */
    private String sendDeptId;

    /**
     * 发送角色ID,注：由于role的主键为int故这里使用Integer类型，建议以后改为统一string
     */
    private String sendRoleId;
    
    /**
     * 发送人联系方式
     */
    private String sendContact;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 结束信息（归档内容）
     */
    private String endResult;

    /**
     * 工单状态，未结束,已归档,已结束未归档
     */
    private Integer status;

    /**
     * 归档满意度
     */
    private Integer holdStatisfied;

    /**
     * 结束工单用户id
     */
    private String endUserId;

    /**
     * 结束工单用户部门
     */
    private String endDeptId;

    /**
     * 结束工单用户角色
     */
    private String endRoleId;
    
    /**
     * 工单派往部门
     */
    private String toDeptId;

    /**
     * 工单有效否
     */
    private Integer deleted;

    /**
     * wps的流程实例号
     */
    private String piid;

    /**
     * 当A流程触发B流程时，A流程的工单名
     */
    private String parentSheetName;

    /**
     * 当A流程触发B流程时，A流程的工单流水号
     */
    private String parentSheetId;
    /**
     * 当A流程触发B流程时，A流程当前环节名称
     */
    private String parentPhaseName;

    /**
     * 模板名称（预留）
     */
    private String sheetTemplateName;
    
    /**
     * 流程关联Key
     */
    private String correlationKey;
    
    /**
     * 父流程关联Key
     */
    private String parentCorrelation;
    
    /**
     * 撤销原因
     */
    private String cancelReason;
    /**
     * 是否是模板
     * 0或者null表示不是，1:表示是模板
     * @author wangjianhua
     * @date 2008-7-22
     */
    private int templateFlag;
    /**
     * 派单时间中的年份
     * @author qinmin
     * @date 2008-10-07
     */
    private int sendYear;
    /**
     * 派单时间中的月份
     * @author qinmin
     * @date 2008-10-07
     */
    private int sendMonth;
    /**
     * 派单时间中的天
     * @author qinmin
     * @date 2008-10-07
     */
    private int sendDay;
    
    /**
     * @see 调用类型。
     * @see 异步调用:asynchronism,同步:synchronization
     * @author yyk
     * @date 2008-11-26
     */
    private String invokeMode;
    /**
     * 派往对象
     * @author wangjianhua
     * @date 2009-2-12
     * 
     */
    private String sendObject;
    
    public String getSendObject() {
		return sendObject;
	}

	public void setSendObject(String sendObject) {
		this.sendObject = sendObject;
	}

	public String getInvokeMode() {
		return invokeMode;
	}

	public void setInvokeMode(String invokeMode) {
		this.invokeMode = invokeMode;
	}

	/**
     * 
     * @return template
     */
	public int getTemplateFlag() {
		return templateFlag;
	}

	/**
	 * 
	 * @param template
	 */
	public void setTemplateFlag(int templateFlag) {
		this.templateFlag = templateFlag;
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
	 * @return Returns the parentCorrelation.
	 */
	public String getParentCorrelation() {
		return parentCorrelation;
	}
	/**
	 * @param parentCorrelation The parentCorrelation to set.
	 */
	public void setParentCorrelation(String parentCorrelation) {
		this.parentCorrelation = parentCorrelation;
	}
    /**
     * @return the acceptLimit
     */
    public Date getSheetAcceptLimit() {
        return sheetAcceptLimit;
    }

    /**
     * @param acceptLimit
     *            the acceptLimit to set
     */
    public void setSheetAcceptLimit(Date acceptLimit) {
        this.sheetAcceptLimit = acceptLimit;
    }

    /**
     * @return the accessories
     */
    public String getSheetAccessories() {
        return sheetAccessories;
    }

    /**
     * @param accessories
     *            the accessories to set
     */
    public void setSheetAccessories(String accessories) {
        this.sheetAccessories = accessories;
    }

    /**
     * @return the completeLimit
     */
    public Date getSheetCompleteLimit() {
        return sheetCompleteLimit;
    }

    /**
     * @param completeLimit
     *            the completeLimit to set
     */
    public void setSheetCompleteLimit(Date completeLimit) {
        this.sheetCompleteLimit = completeLimit;
    }

    /**
     * @return the endDeptId
     */
    public String getEndDeptId() {
        return endDeptId;
    }

    /**
     * @param endDeptId
     *            the endDeptId to set
     */
    public void setEndDeptId(String endDeptId) {
        this.endDeptId = endDeptId;
    }

    /**
     * @return the endResult
     */
    public String getEndResult() {
        return endResult;
    }

    /**
     * @param endResult
     *            the endResult to set
     */
    public void setEndResult(String endResult) {
        this.endResult = endResult;
    }

    /**
     * @return the endRoleId
     */
    public String getEndRoleId() {
        return endRoleId;
    }

    /**
     * @param endRoleId
     *            the endRoleId to set
     */
    public void setEndRoleId(String endRoleId) {
        this.endRoleId = endRoleId;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the endUserId
     */
    public String getEndUserId() {
        return endUserId;
    }

    /**
     * @param endUserId
     *            the endUserId to set
     */
    public void setEndUserId(String endUserId) {
        this.endUserId = endUserId;
    }

    /**
     * @return the holdStatisfied
     */
    public Integer getHoldStatisfied() {
        return holdStatisfied;
    }

    /**
     * @param holdStatisfied
     *            the holdStatisfied to set
     */
    public void setHoldStatisfied(Integer holdStatisfied) {
        this.holdStatisfied = holdStatisfied;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the parentSheetId
     */
    public String getParentSheetId() {
        return parentSheetId;
    }

    /**
     * @param parendSheetId
     *            the parentSheetId to set
     */
    public void setParentSheetId(String parendSheetId) {
        this.parentSheetId = parendSheetId;
    }

    /**
     * @return the parentSheetName
     */
    public String getParentSheetName() {
        return parentSheetName;
    }

    /**
     * @param parentSheetName
     *            the parentSheetName to set
     */
    public void setParentSheetName(String parentSheetName) {
        this.parentSheetName = parentSheetName;
    }

    /**
     * @return the piid
     */
    public String getPiid() {
        return piid;
    }

    /**
     * @param piid
     *            the piid to set
     */
    public void setPiid(String piid) {
        this.piid = piid;
    }

    /**
     * @return the sendContact
     */
    public String getSendContact() {
        return sendContact;
    }

    /**
     * @param sendContact
     *            the sendContact to set
     */
    public void setSendContact(String sendContact) {
        this.sendContact = sendContact;
    }

    /**
     * @return the sendDeptId
     */
    public String getSendDeptId() {
        return sendDeptId;
    }

    /**
     * @param sendDeptId
     *            the sendDeptId to set
     */
    public void setSendDeptId(String sendDeptId) {
        this.sendDeptId = sendDeptId;
    }

 

    /**
     * @return the sendRoleId
     */
    public String getSendRoleId() {
        return sendRoleId;
    }

    /**
     * @param sendRoleId
     *            the sendRoleId to set
     */
    public void setSendRoleId(String sendRoleId) {
        this.sendRoleId = sendRoleId;
    }

    /**
     * @return the sendTime
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * @param sendTime
     *            the sendTime to set
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * @return the sendUserId
     */
    public String getSendUserId() {
        return sendUserId;
    }

    /**
     * @param sendUserId
     *            the sendUserId to set
     */
    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    /**
     * @return the sheetId
     */
    public String getSheetId() {
        return sheetId;
    }

    /**
     * @return the deleted
     */
    public Integer getDeleted() {
        return deleted;
    }
    /**
     * @param deleted the deleted to set
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    /**
     * @return the sendOrgType
     */
    public String getSendOrgType() {
        return sendOrgType;
    }
    /**
     * @param sendOrgType the sendOrgType to set
     */
    public void setSendOrgType(String sendOrgType) {
        this.sendOrgType = sendOrgType;
    }
    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * @param sheetId
     *            the sheetId to set
     */
    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }


    /**
     * @return the templateName
     */
    public String getSheetTemplateName() {
        return sheetTemplateName;
    }

    /**
     * @param templateName
     *            the templateName to set
     */
    public void setSheetTemplateName(String templateName) {
        this.sheetTemplateName = templateName;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
	/**
	 * @return Returns the toDeptId.
	 */
	public String getToDeptId() {
		return toDeptId;
	}
	/**
	 * @param toDeptId The toDeptId to set.
	 */
	public void setToDeptId(String toDeptId) {
		this.toDeptId = toDeptId;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public int getSendDay() {
		return sendDay;
	}

	public void setSendDay(int sendDay) {
		this.sendDay = sendDay;
	}

	public int getSendMonth() {
		return sendMonth;
	}

	public void setSendMonth(int sendMonth) {
		this.sendMonth = sendMonth;
	}

	public int getSendYear() {
		return sendYear;
	}

	public void setSendYear(int sendYear) {
		this.sendYear = sendYear;
	}

	public String getParentPhaseName() {
		return parentPhaseName;
	}

	public void setParentPhaseName(String parentPhaseName) {
		this.parentPhaseName = parentPhaseName;
	}
	
	
}

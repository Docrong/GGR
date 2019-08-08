package com.boco.eoms.workbench.infopub.model;

import java.util.Date;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:信息发布公告
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 21, 2008 3:31:20 PM
 * </p>
 *
 * @author 曲静波
 * @version 3.5.1
 */

/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="myappfuse_test"
 */
public class Thread extends BaseObject {

    /**
     *
     */
    private static final long serialVersionUID = -621109313487845702L;

    /**
     * 主键
     */
    private String id;

    /**
     * 版块id
     */
    private String forumsId;

    /**
     * 创建人用户id
     */
    private String createrId;

    /**
     * 排序，置顶
     */
    private String sortNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后一贴编辑时间隔
     */
    private Date editTime;

    /**
     * 主题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 紧急程度
     */
    private String threadTypeId;
    private String threadTypeName;
    /**
     * 是否需要回复
     */
    private String reply;
    private String replyName;
    /**
     * 有效期
     */
    private String validity;
    /**
     * 被查看过多少次
     */
    private Integer threadCount;

    /**
     * 是否被删除
     */
    private String isDel;

    /**
     * 是否通过审核
     */
    private String status;

    /**
     * 创建者人名
     */
    private String createrName;

    private String accessories;
    /**
     * CRM工单编号
     */
    private String serialNo;

    //2009-04-07 是否包括子部门  1包括，0或null不包括
    private String isIncludeSubDept;

    //2009-05-26 是否给选择的范围发送信息 1发送，0或null不发送
    private String isSend;
    //2009-05-26 是否给审核人发送信息 1发送，0或null不发送
    private String isAuditSend;

    //2009-04-10 外系统类型
    private String otherSystemType;    //存字典值

    private String interFaceMethod;//接口方法 "Web Service"
    //----------------------

    public String getInterFaceMethod() {
        return interFaceMethod;
    }

    public void setInterFaceMethod(String interFaceMethod) {
        this.interFaceMethod = interFaceMethod;
    }

    public String getOtherSystemType() {
        return otherSystemType;
    }

    public void setOtherSystemType(String otherSystemType) {
        this.otherSystemType = otherSystemType;
    }

    public String getIsIncludeSubDept() {
        return isIncludeSubDept;
    }

    public void setIsIncludeSubDept(String isIncludeSubDept) {
        this.isIncludeSubDept = isIncludeSubDept;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    /**
     * @return the content *
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the threadCount
     */
    public Integer getThreadCount() {
        return threadCount;
    }

    /**
     * @param threadCount the threadCount to set
     */
    public void setThreadCount(Integer threadCount) {
        this.threadCount = threadCount;
    }

    /**
     * @return the createrId
     */
    public String getCreaterId() {
        return createrId;
    }

    /**
     * @param createrId the createrId to set
     */
    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    /**
     * @return the createTime *
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the editTime *
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * @param editTime the editTime to set
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    /**
     * @return the forumsId
     */
    public String getForumsId() {
        return forumsId;
    }

    /**
     * @param forumsId the forumsId to set
     */
    public void setForumsId(String forumsId) {
        this.forumsId = forumsId;
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
     * @return the isDel
     */
    public String getIsDel() {
        return isDel;
    }

    /**
     * @param isDel the isDel to set
     */
    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    /**
     * @return the sortNum
     */
    public String getSortNum() {
        return sortNum;
    }

    /**
     * @param sortNum the sortNum to set
     */
    public void setSortNum(String sortNum) {
        this.sortNum = sortNum;
    }

    /**
     * @return the threadTypeId
     */
    public String getThreadTypeId() {
        return threadTypeId;
    }

    /**
     * @param threadTypeId the threadTypeId to set
     */
    public void setThreadTypeId(String threadTypeId) {
        this.threadTypeId = threadTypeId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.base.model.BaseObject#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        if (o instanceof Forums) {
            Thread thread = (Thread) o;
            if (this.id != null || this.id.equals(thread.getId())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * @return the createrName
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * @param createrName the createrName to set
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getThreadTypeName() {
        if (this.threadTypeId.equals("1")) {
            threadTypeName = "紧急";
        } else {
            replyName = "一般";
        }
        return threadTypeName;
    }

    public void setThreadTypeName(String threadTypeName) {
        this.threadTypeName = threadTypeName;
    }

    public String getReplyName() {
        if (this.reply.equals("1")) {
            replyName = "是";
        } else {
            replyName = "否";
        }
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }

    public String getIsAuditSend() {
        return isAuditSend;
    }

    public void setIsAuditSend(String isAuditSend) {
        this.isAuditSend = isAuditSend;
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend;
    }


}

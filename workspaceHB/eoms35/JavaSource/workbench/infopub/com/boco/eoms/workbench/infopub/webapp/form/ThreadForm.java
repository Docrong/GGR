package com.boco.eoms.workbench.infopub.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * 
 * <p>
 * Title:信息（贴子）form
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Date:May 24, 2008 6:27:50 PM
 * </p>
 * 
 * @author 曲静波
 * @version 3.5.1
 * 
 */
public class ThreadForm extends BaseForm implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5774469843779671617L;

	/**
	 * 移动信息到版本时，要移动到的版本
	 */
	protected String toForumsId;
	/**
	 * 发布人姓名
	 */
	protected String createrName;

	protected String content;

	protected String threadCount;

	protected String createrId;

	protected String createTime;

	protected String editTime;

	protected String forumsId;

	protected String id;

	protected String isDel;

	protected String sortNum;

	protected String threadTypeId;
	
	protected String reply;
	
	protected String replyresult;
	
	protected String validity;

	protected String title;
    
	protected String accessories;
	
	protected String accessoriesName;
	/**
	 * 评阅信息
	 */
	protected String note;

	/**
	 * 审核状态
	 */
	protected String status;

	/**
	 * 开始时间
	 */
	protected String startDate;

	/**
	 * 结束时间
	 */
	protected String endDate;

	/**
	 * org相关内容，通过json传输
	 */
	protected String org;

	/**
	 * 审核人
	 */
	protected String auditUser;

	/**
	 * 传阅人
	 */
	protected String reader;

	/**
	 * 是否选择审核人
	 */
	protected String isAudit;
	
	/**
	 * 是否提交审核
	 */
	protected String isSubmitAudit;

	/**
	 * 是否机密
	 */
	protected String isSecret;
	
	protected String historyType;

	protected String comments;
	
	protected String isSend;
	
	protected String isAuditSend;
	
	//2009-04-07 是否包括子部门  1包括，0或null不包括
	private String isIncludeSubDept;
	
	//2009-04-10 外系统类型
	private String otherSystemType;	//存字典值
	
	private String interFaceMethod;//接口方法 "Web Service"
    //----------------------

	public String getIsIncludeSubDept() {
		return isIncludeSubDept;
	}

	public void setIsIncludeSubDept(String isIncludeSubDept) {
		this.isIncludeSubDept = isIncludeSubDept;
	}
	
	/** Default empty constructor. */
	public ThreadForm() {
	}

	public String getContent() {
		return this.content;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreaterId() {
		return this.createrId;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEditTime() {
		return this.editTime;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}

	public String getForumsId() {
		return this.forumsId;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setForumsId(String forumsId) {
		this.forumsId = forumsId;
	}

	public String getId() {
		return this.id;
	}

	/**
	 */

	public void setId(String id) {
		this.id = id;
	}

	public String getIsDel() {
		return this.isDel;
	}

	/**
	 * @struts.validator type="required"
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
	 * @param sortNum
	 *            the sortNum to set
	 */
	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}

	/**
	 * @return the threadCount
	 */
	public String getThreadCount() {
		return threadCount;
	}

	/**
	 * @param threadCount
	 *            the threadCount to set
	 */
	public void setThreadCount(String threadCount) {
		this.threadCount = threadCount;
	}

	public String getThreadTypeId() {
		return this.threadTypeId;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setThreadTypeId(String threadTypeId) {
		this.threadTypeId = threadTypeId;
	}

	public String getTitle() {
		return this.title;
	}

	/**
	 * @struts.validator type="required"
	 */

	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * To add non XDoclet-generated methods, create a file named
	 * xdoclet-ThreadForm.java containing the additional code and place it in
	 * your metadata/web directory.
	 */
	/**
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping,
	 *      javax.servlet.http.HttpServletRequest)
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// reset any boolean data types to false

	}

	/**
	 * @return the toForumsId
	 */
	public String getToForumsId() {
		return toForumsId;
	}

	/**
	 * @param toForumsId
	 *            the toForumsId to set
	 */
	public void setToForumsId(String toForumsId) {
		this.toForumsId = toForumsId;
	}

	/**
	 * @return the org
	 */
	public String getOrg() {
		return org;
	}

	/**
	 * @param org
	 *            the org to set
	 */
	public void setOrg(String org) {
		this.org = org;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	/**
	 * @return the createrName
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param createrName the createrName to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the auditUserName
	 */
	public String getAuditUser() {
		return auditUser;
	}

	/**
	 * @param auditUserName the auditUserName to set
	 */
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}

	/**
	 * @return the isAudit
	 */
	public String getIsAudit() {
		return isAudit;
	}

	/**
	 * @param isAudit the isAudit to set
	 */
	public void setIsAudit(String isAudit) {
		this.isAudit = isAudit;
	}

	/**
	 * @return the isSecret
	 */
	public String getIsSecret() {
		return isSecret;
	}

	/**
	 * @param isSecret the isSecret to set
	 */
	public void setIsSecret(String isSecret) {
		this.isSecret = isSecret;
	}

	/**
	 * @return the isSubmitAudit
	 */
	public String getIsSubmitAudit() {
		return isSubmitAudit;
	}

	/**
	 * @param isSubmitAudit the isSubmitAudit to set
	 */
	public void setIsSubmitAudit(String isSubmitAudit) {
		this.isSubmitAudit = isSubmitAudit;
	}

	/**
	 * @return the reader
	 */
	public String getReader() {
		return reader;
	}

	/**
	 * @param reader the reader to set
	 */
	public void setReader(String reader) {
		this.reader = reader;
	}

	/**
	 * @return the historyType
	 */
	public String getHistoryType() {
		return this.historyType;
	}

	/**
	 * @param historyType the historyType to set
	 */
	public void setHistoryType(String historyType) {
		this.historyType = historyType;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAccessories() {
		return accessories;
	}

	public void setAccessories(String accessories) {
		this.accessories = accessories;
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

	public String getReplyresult() {
		return replyresult;
	}

	public void setReplyresult(String replyresult) {
		this.replyresult = replyresult;
	}

	public String getOtherSystemType() {
		return otherSystemType;
	}

	public void setOtherSystemType(String otherSystemType) {
		this.otherSystemType = otherSystemType;
	}

	public String getInterFaceMethod() {
		return interFaceMethod;
	}

	public void setInterFaceMethod(String interFaceMethod) {
		this.interFaceMethod = interFaceMethod;
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

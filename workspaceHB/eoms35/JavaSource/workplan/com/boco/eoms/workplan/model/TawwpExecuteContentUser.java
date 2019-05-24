package com.boco.eoms.workplan.model;

/**
 * <p>Title: 执行作业计划执行内容（个人）�?/p>
 * <p>Description: 执行作业计划执行内容类信息，其中包括执行时间，执行内容，备注，附件，特殊表格�?/p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */
import java.io.*;
import java.util.*;
import com.boco.eoms.common.oo.DataObject;

/**
 * @hibernate.class table="TAW_WP_EXECUTECONTENTUSER"
 */

public class TawwpExecuteContentUser implements Serializable, DataObject {

	private String id; // 标识

	private String startDate; // 计划开始时�?

	private String endDate; // 计划结束时间

	private String crtime; // 创建时间 （按创建时间排序�?

	private String writeDate;

	private String cruser; // 执行�?

	private String stubUser; // 代理执行�?

	private String content; // 执行内容

	private String remark; // 备注

	private String eligibility; // 合格标志 0：合�?1：不合格

	private String executeFlag; // 填写标志 0：未填写 1：按时填�?2：超时填�?

	private String formId; // 附加表格格式信息

	private String formDataId; // 附加表格信息（附加表格字符串，用","分割�?

	private String normalFlag; // h合格标志
	
	private String reason;

	private TawwpExecuteContent tawwpExecuteContent; // 执行作业计划内容对象

	private Set tawwpExecuteFiles = new HashSet(); // 对应的执行内容附件

	public TawwpExecuteContentUser() {
	}

	public TawwpExecuteContentUser(String _startDate, String _endDate,
			String _crtime, String _writeDate, String _cruser,
			String _stubUser, String _content, String _remark,
			String _eligibility, String _executeFlag, String _formId,
			String _formDataId, TawwpExecuteContent _tawwpExecuteContent,
			String _normalFlag,String _reason) {
		this.startDate = _startDate;
		this.endDate = _endDate;
		this.crtime = _crtime;
		this.writeDate = _writeDate;
		this.cruser = _cruser;
		this.stubUser = _stubUser;
		this.content = _content;
		this.remark = _remark;
		this.eligibility = _eligibility;
		this.executeFlag = _executeFlag;
		this.formId = _formId;
		this.formDataId = _formDataId;
		this.tawwpExecuteContent = _tawwpExecuteContent;
		this.normalFlag = _normalFlag;
		this.reason=_reason;
	}

	/**
	 * @hibernate.id column="ID" length="32" unsaved-value="null"
	 *               generator-class="uuid.hex"
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @hibernate.property column="CONTENT" length="200" not-null="false"
	 *                     update="true"
	 */
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @hibernate.property column="REMARK" length="200" not-null="false"
	 *                     update="true"
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @hibernate.property column="STARTDATE" length="19" not-null="true"
	 *                     update="true"
	 */
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @hibernate.property column="ENDDATE" length="19" not-null="true"
	 *                     update="true"
	 */
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @hibernate.property column="CRTIME" length="19" not-null="false"
	 *                     update="true"
	 */
	public String getCrtime() {
		return crtime;
	}

	public void setCrtime(String crtime) {
		this.crtime = crtime;
	}

	/**
	 * @hibernate.property column="CRUSER" length="20" not-null="true"
	 *                     update="true"
	 */
	public String getCruser() {
		return cruser;
	}

	public void setCruser(String cruser) {
		this.cruser = cruser;
	}

	/**
	 * @hibernate.property column="STUBUSER" length="20" not-null="false"
	 *                     update="true"
	 */
	public String getStubUser() {
		return stubUser;
	}

	public void setStubUser(String stubUser) {
		this.stubUser = stubUser;
	}

	/**
	 * @hibernate.property column="FORMDATAID" length=200" not-null="false"
	 *                     update="true"
	 */
	public String getFormDataId() {
		return formDataId;
	}

	public void setFormDataId(String formDataId) {
		this.formDataId = formDataId;
	}

	/**
	 * @hibernate.property column="FORMID" length=20" not-null="false"
	 *                     update="true"
	 */
	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	/**
	 * @hibernate.property column="ELIGIBILITY" length=1" not-null="true"
	 *                     update="true"
	 */
	public String getEligibility() {
		return eligibility;
	}

	public void setEligibility(String eligibility) {
		this.eligibility = eligibility;
	}

	/**
	 * @hibernate.property column="EXECUTEFLAG" length=1" not-null="true"
	 *                     update="true"
	 */
	public String getExecuteFlag() {
		return executeFlag;
	}

	public void setExecuteFlag(String executeFlag) {
		this.executeFlag = executeFlag;
	}

	/**
	 * @hibernate.many-to-one column="EXECUTE_CONTENT_ID" cascade="none"
	 *                        not-null="true"
	 */
	public TawwpExecuteContent getTawwpExecuteContent() {
		return tawwpExecuteContent;
	}

	public void setTawwpExecuteContent(TawwpExecuteContent tawwpExecuteContent) {
		this.tawwpExecuteContent = tawwpExecuteContent;
	}

	/**
	 * @hibernate.set inverse="true" lazy="true" cascade="save-update"
	 * @hibernate.collection-key column="EXECUTE_CONTENT_USER_ID"
	 * @hibernate.collection-one-to-many class="com.boco.eoms.workplan.model.TawwpExecuteFile"
	 */
	public Set getTawwpExecuteFiles() {
		return tawwpExecuteFiles;
	}

	public void setTawwpExecuteFiles(Set tawwpExecuteFiles) {
		this.tawwpExecuteFiles = tawwpExecuteFiles;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getNormalFlag() {
		return normalFlag;
	}

	public void setNormalFlag(String normalFlag) {
		this.normalFlag = normalFlag;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}

package com.boco.eoms.km.file.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:文件管理
 * </p>
 * <p>
 * Description:文件管理
 * </p>
 * <p>
 * Wed Mar 25 11:32:18 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() eoms
 * @moudle.getVersion() 1.0
 * 
 */
public class KmFileForm extends BaseForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 * 文件名
	 *
	 */
	private java.lang.String fileName;

	public void setFileName(java.lang.String fileName) {
		this.fileName = fileName;
	}

	public java.lang.String getFileName() {
		return this.fileName;
	}

	/**
	 *
	 * 文件后缀名
	 *
	 */
	private java.lang.String expand;

	public void setExpand(java.lang.String expand) {
		this.expand = expand;
	}

	public java.lang.String getExpand() {
		return this.expand;
	}

	/**
	 *
	 * 用户ID
	 *
	 */
	private java.lang.String userId;

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.String getUserId() {
		return this.userId;
	}

	/**
	 *
	 * 联系电话
	 *
	 */
	private java.lang.String phone;

	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	public java.lang.String getPhone() {
		return this.phone;
	}

	/**
	 *
	 * 上传部门
	 *
	 */
	private java.lang.String deptId;

	public java.lang.String getDeptId() {
		return deptId;
	}

	public void setDeptId(java.lang.String deptId) {
		this.deptId = deptId;
	}

	/**
	 *
	 * 上传时间
	 *
	 */
	private java.lang.String uploadTime;

	public void setUploadTime(java.lang.String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public java.lang.String getUploadTime() {
		return this.uploadTime;
	}

	/**
	 *
	 * 文件大小
	 *
	 */
	private java.lang.String fileSize;

	public void setFileSize(java.lang.String fileSize) {
		this.fileSize = fileSize;
	}

	public java.lang.String getFileSize() {
		return this.fileSize;
	}

	/**
	 *
	 * 文件级别
	 *
	 */
	private java.lang.String fileGrade;

	public void setFileGrade(java.lang.String fileGrade) {
		this.fileGrade = fileGrade;
	}

	public java.lang.String getFileGrade() {
		return this.fileGrade;
	}

	/**
	 *
	 * 关键字
	 *
	 */
	private java.lang.String keywords;

	public void setKeywords(java.lang.String keywords) {
		this.keywords = keywords;
	}

	public java.lang.String getKeywords() {
		return this.keywords;
	}

	/**
	 *
	 * 摘要
	 *
	 */
	private java.lang.String fileAbstract;

	public void setFileAbstract(java.lang.String fileAbstract) {
		this.fileAbstract = fileAbstract;
	}

	public java.lang.String getFileAbstract() {
		return this.fileAbstract;
	}

	/**
	 *
	 * 文件状态
	 *
	 */
	private java.lang.String fileState;

	public void setFileState(java.lang.String fileState) {
		this.fileState = fileState;
	}

	public java.lang.String getFileState() {
		return this.fileState;
	}

	/**
	 *
	 * 点击数
	 *
	 */
	private java.lang.Long clickCount;

	public void setClickCount(java.lang.Long clickCount) {
		this.clickCount = clickCount;
	}

	public java.lang.Long getClickCount() {
		return this.clickCount;
	}

	private FormFile file;

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public String nodeId;

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 *
	 * 记录状态
	 *
	 */
	private java.lang.String state;

	public void setState(java.lang.String state) {
		this.state = state;
	}

	public java.lang.String getState() {
		return this.state;
	}

	/**
	 *
	 * 版本号
	 *
	 */
	private java.lang.String version;

	public void setVersion(java.lang.String version) {
		this.version = version;
	}

	public java.lang.String getVersion() {
		return this.version;
	}

	/**
	 *
	 * 是否删除
	 *
	 */
	private java.lang.String isDeleted;

	public java.lang.String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(java.lang.String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.expand = "";
		this.fileName = "";
		this.keywords = "";
		this.starttime = "";
		this.endtime = "";
		super.reset(mapping, request);
	}

	/**
	 * 开始时间
	 */
	private String starttime;

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	/**
	 * 结束时间
	 */
	private String endtime;

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	/**
	 * 时间排序
	 */
	private String timeSort;

	public String getTimeSort() {
		return timeSort;
	}

	public void setTimeSort(String timeSort) {
		this.timeSort = timeSort;
	}

}
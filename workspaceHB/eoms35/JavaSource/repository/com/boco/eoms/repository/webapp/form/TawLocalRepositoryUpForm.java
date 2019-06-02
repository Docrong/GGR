package com.boco.eoms.repository.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:tawLocalRepositoryUp
 * </p>
 * <p>
 * Description:tawLocalRepositoryUp
 * </p>
 * <p>
 * Fri Oct 30 16:52:13 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() 李锋
 * @moudle.getVersion() 1.0
 * 
 */
public class TawLocalRepositoryUpForm extends BaseForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private String id;
	
	private String sheetkey;
	
	public String getSheetkey() {
		return sheetkey;
	}

	public void setSheetkey(String sheetkey) {
		this.sheetkey = sheetkey;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 *
	 * repositoryId
	 *
	 */
	private java.lang.String repositoryId;
   
	public void setRepositoryId(java.lang.String repositoryId){
		this.repositoryId= repositoryId;       
	}
   
	public java.lang.String getRepositoryId(){
		return this.repositoryId;
	}

	/**
	 *
	 * 前硬件版本
	 *
	 */
	private java.lang.String beforeHardwareRepository;
   
	public void setBeforeHardwareRepository(java.lang.String beforeHardwareRepository){
		this.beforeHardwareRepository= beforeHardwareRepository;       
	}
   
	public java.lang.String getBeforeHardwareRepository(){
		return this.beforeHardwareRepository;
	}

	/**
	 *
	 * 前软件版本
	 *
	 */
	private java.lang.String beforeSoftwareRepository;
   
	public void setBeforeSoftwareRepository(java.lang.String beforeSoftwareRepository){
		this.beforeSoftwareRepository= beforeSoftwareRepository;       
	}
   
	public java.lang.String getBeforeSoftwareRepository(){
		return this.beforeSoftwareRepository;
	}

	/**
	 *
	 * 前补丁
	 *
	 */
	private java.lang.String beforePatch;
   
	public void setBeforePatch(java.lang.String beforePatch){
		this.beforePatch= beforePatch;       
	}
   
	public java.lang.String getBeforePatch(){
		return this.beforePatch;
	}

	/**
	 *
	 * 硬件版本
	 *
	 */
	private java.lang.String hardwareRepository;
   
	public void setHardwareRepository(java.lang.String hardwareRepository){
		this.hardwareRepository= hardwareRepository;       
	}
   
	public java.lang.String getHardwareRepository(){
		return this.hardwareRepository;
	}

	/**
	 *
	 * 软件版本
	 *
	 */
	private java.lang.String softwareRepository;
   
	public void setSoftwareRepository(java.lang.String softwareRepository){
		this.softwareRepository= softwareRepository;       
	}
   
	public java.lang.String getSoftwareRepository(){
		return this.softwareRepository;
	}

	/**
	 *
	 * 补丁
	 *
	 */
	private java.lang.String patch;
   
	public void setPatch(java.lang.String patch){
		this.patch= patch;       
	}
   
	public java.lang.String getPatch(){
		return this.patch;
	}

	/**
	 *
	 * 升级内容
	 *
	 */
	private java.lang.String content;
   
	public void setContent(java.lang.String content){
		this.content= content;       
	}
   
	public java.lang.String getContent(){
		return this.content;
	}

	/**
	 *
	 * 原因
	 *
	 */
	private java.lang.String reason;
   
	public void setReason(java.lang.String reason){
		this.reason= reason;       
	}
   
	public java.lang.String getReason(){
		return this.reason;
	}

	/**
	 *
	 * 批文
	 *
	 */
	private java.lang.String approval;
   
	public void setApproval(java.lang.String approval){
		this.approval= approval;       
	}
   
	public java.lang.String getApproval(){
		return this.approval;
	}

	/**
	 *
	 * 升级时间
	 *
	 */
	private java.lang.String uptime;
   
	public void setUptime(java.lang.String uptime){
		this.uptime= uptime;       
	}
   
	public java.lang.String getUptime(){
		return this.uptime;
	}

	private int upNum;

	public int getUpNum() {
		return upNum;
	}

	public void setUpNum(int upNum) {
		this.upNum = upNum;
	}
}
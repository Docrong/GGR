package com.boco.eoms.cutapply.webapp.form;

import org.apache.struts.upload.FormFile;

import com.boco.eoms.base.webapp.form.BaseForm;

/**
 * <p>
 * Title:干线割接管理
 * </p>
 * <p>
 * Description:干线割接管理
 * </p>
 * <p>
 * Thu Apr 02 16:59:37 CST 2009
 * </p>
 * 
 * @moudle.getAuthor() wangsixuan
 * @moudle.getVersion() 3.5
 * 
 */
public class CutApplyForm extends BaseForm implements java.io.Serializable {

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
	 * 割接申请人
	 *
	 */
	private java.lang.String userId;
   
	public void setUserId(java.lang.String userId){
		this.userId= userId;       
	}
   
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *
	 * 割接申请人部门
	 *
	 */
	private java.lang.String deptId;
   
	public void setDeptId(java.lang.String deptId){
		this.deptId= deptId;       
	}
   
	public java.lang.String getDeptId(){
		return this.deptId;
	}

	/**
	 *
	 * 割接内容
	 *
	 */
	private java.lang.String context;
   
	public void setContext(java.lang.String context){
		this.context= context;       
	}
   
	public java.lang.String getContext(){
		return this.context;
	}

	/**
	 *
	 * 割接所属地市
	 *
	 */
	private java.lang.String areaId;
   
	public void setAreaId(java.lang.String areaId){
		this.areaId= areaId;       
	}
   
	public java.lang.String getAreaId(){
		return this.areaId;
	}


	/**
	 *
	 * 割接开始时间
	 *
	 */
	private java.lang.String cutStartTime;
   
	public void setCutStartTime(java.lang.String cutStartTime){
		this.cutStartTime= cutStartTime;       
	}
   
	public java.lang.String getCutStartTime(){
		return this.cutStartTime;
	}

	/**
	 *
	 * 割接结束时间
	 *
	 */
	private java.lang.String cutEndTime;
   
	public void setCutEndTime(java.lang.String cutEndTime){
		this.cutEndTime= cutEndTime;       
	}
   
	public java.lang.String getCutEndTime(){
		return this.cutEndTime;
	}

	/**
	 *
	 * 割接现场负责人
	 *
	 */
	private java.lang.String manager;
   
	public void setManager(java.lang.String manager){
		this.manager= manager;       
	}
   
	public java.lang.String getManager(){
		return this.manager;
	}

	/**
	 *
	 * 联系电话
	 *
	 */
	private java.lang.String phone;
   
	public void setPhone(java.lang.String phone){
		this.phone= phone;       
	}
   
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *
	 * 申请割接工单号或公文号
	 *
	 */
	private java.lang.String fileId;
   
	public void setFileId(java.lang.String fileId){
		this.fileId= fileId;       
	}
   
	public java.lang.String getFileId(){
		return this.fileId;
	}

	/**
	 *
	 * 是否影响业务
	 *
	 */
	private java.lang.String isAffect;
   
	public void setIsAffect(java.lang.String isAffect){
		this.isAffect= isAffect;       
	}
   
	public java.lang.String getIsAffect(){
		return this.isAffect;
	}

	/**
	 *
	 * 影响时间开始
	 *
	 */
	private java.lang.String affectStartTime;
   
	public void setAffectStartTime(java.lang.String affectStartTime){
		this.affectStartTime= affectStartTime;       
	}
   
	public java.lang.String getAffectStartTime(){
		return this.affectStartTime;
	}

	/**
	 *
	 * 影响结束时间
	 *
	 */
	private java.lang.String affectEndTime;
   
	public void setAffectEndTime(java.lang.String affectEndTime){
		this.affectEndTime= affectEndTime;       
	}
   
	public java.lang.String getAffectEndTime(){
		return this.affectEndTime;
	}

	/**
	 *
	 * 影响业务描述
	 *
	 */
	private java.lang.String affectInfo;
   
	public void setAffectInfo(java.lang.String affectInfo){
		this.affectInfo= affectInfo;       
	}
   
	public java.lang.String getAffectInfo(){
		return this.affectInfo;
	}

	/**
	 *
	 * 备注
	 *
	 */
	private java.lang.String remark;
   
	public void setRemark(java.lang.String remark){
		this.remark= remark;       
	}
   
	public java.lang.String getRemark(){
		return this.remark;
	}
	
	/**
	 *
	 * 是否由修改权限
	 *
	 */
	private java.lang.String isEdit;
  
	public void setIsEdit(java.lang.String isEdit){
		this.isEdit= isEdit;       
	}
  
	public java.lang.String getIsEdit(){
		return this.isEdit;
	}

	/**
	 *
	 * 批量录入文件
	 *
	 */
	private FormFile accessoryName;

	public FormFile getAccessoryName() {
		return accessoryName;
	}

	public void setAccessoryName(FormFile accessoryName) {
		this.accessoryName = accessoryName;
	} 

}
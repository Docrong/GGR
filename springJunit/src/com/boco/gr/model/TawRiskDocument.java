package com.boco.gr.model;
/**
 * 资料文档
 * */
public class TawRiskDocument{
	private String id;//主键
	private String deleted;//删除标识，0-正常；1-已删除
	private String riskType;//环节initDicId="67801"
	private String isShow;//是否视图中呈现
	
	private String docTitle;//资料标题
	private String docDesc;//资料描述
	private String docLinkName;//关联 附件表taw_commons_accessories的 accessoriesname
	
	private String uploadTime;//上传时间
	private String uploadUserId;//上传人-ID
	private String uploadUserDeptid;//上传人部门-ID
	private String uploadUserPhone;//上传人联系方式
	
	private String updateTime;//最后一次修改时间
	private String updateUserId;//最后一次修改人-ID
	private String updateUserDeptid;//最后一次修改人部门-ID
	private String updateUserPhone;//最后一次修改人联系方式
	
	private String sysNo;//编号
	
	
	public TawRiskDocument() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getDocTitle() {
		return docTitle;
	}
	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}
	public String getDocDesc() {
		return docDesc;
	}
	public void setDocDesc(String docDesc) {
		this.docDesc = docDesc;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getUploadUserId() {
		return uploadUserId;
	}
	public void setUploadUserId(String uploadUserId) {
		this.uploadUserId = uploadUserId;
	}
	public String getUploadUserDeptid() {
		return uploadUserDeptid;
	}
	public void setUploadUserDeptid(String uploadUserDeptid) {
		this.uploadUserDeptid = uploadUserDeptid;
	}
	public String getDocLinkName() {
		return docLinkName;
	}
	public void setDocLinkName(String docLinkName) {
		this.docLinkName = docLinkName;
	}
	public String getRiskType() {
		if(riskType==null){
			riskType="";
		}
		return riskType;
	}
	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}
	public String getUploadUserPhone() {
		return uploadUserPhone;
	}
	public void setUploadUserPhone(String uploadUserPhone) {
		this.uploadUserPhone = uploadUserPhone;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getUpdateUserDeptid() {
		return updateUserDeptid;
	}
	public void setUpdateUserDeptid(String updateUserDeptid) {
		this.updateUserDeptid = updateUserDeptid;
	}
	public String getUpdateUserPhone() {
		return updateUserPhone;
	}
	public void setUpdateUserPhone(String updateUserPhone) {
		this.updateUserPhone = updateUserPhone;
	}
	public String getIsShow() {
		if(isShow==null){
			isShow="";
		}
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	public String getSysNo() {
		return sysNo;
	}
	public void setSysNo(String sysNo) {
		this.sysNo = sysNo;
	}

	
	
	
}

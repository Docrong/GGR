package com.boco.eoms.datum.webapp.form;

import com.boco.eoms.base.webapp.form.BaseForm;

public class TawImportExcelForm extends BaseForm implements
		java.io.Serializable {
	  private int deptId;
	  private String userId;
	  private int sheetId;
	  private int flag;
	  private org.apache.struts.upload.FormFile fileName;
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public org.apache.struts.upload.FormFile getFileName() {
		return fileName;
	}
	public void setFileName(org.apache.struts.upload.FormFile fileName) {
		this.fileName = fileName;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getSheetId() {
		return sheetId;
	}
	public void setSheetId(int sheetId) {
		this.sheetId = sheetId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}

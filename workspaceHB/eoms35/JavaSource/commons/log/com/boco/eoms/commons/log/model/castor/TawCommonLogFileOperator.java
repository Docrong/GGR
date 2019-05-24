package com.boco.eoms.commons.log.model.castor;

import java.io.Serializable;

/**
 * 
 * @author panlong
 * @Mar 23, 2007 3:35:31 AM
 */
public class TawCommonLogFileOperator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String message;

	private String remark;

	private String modeid;

	private String savetime;

	private String userid;

	private String operid;

	private String ramoteip;

	private String saveyear;

	private String savemonth;

	private String saveday;

	private String loglevel;

	private String siteName;

	private String modelname;

	private String opername;

	private String classname;

	private String filepathandname;

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getLoglevel() {
		return loglevel;
	}

	public void setLoglevel(String loglevel) {
		this.loglevel = loglevel;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSaveday() {
		return saveday;
	}

	public void setSaveday(String saveday) {
		this.saveday = saveday;
	}

	public String getModeid() {
		return modeid;
	}

	public void setModeid(String modeid) {
		this.modeid = modeid;
	}

	public String getOperid() {
		return operid;
	}

	public void setOperid(String operid) {
		this.operid = operid;
	}

	public String getRamoteip() {
		return ramoteip;
	}

	public void setRamoteip(String ramoteip) {
		this.ramoteip = ramoteip;
	}

	public String getSavemonth() {
		return savemonth;
	}

	public void setSavemonth(String savemonth) {
		this.savemonth = savemonth;
	}

	public String getSavetime() {
		return savetime;
	}

	public void setSavetime(String savetime) {
		this.savetime = savetime;
	}

	public String getSaveyear() {
		return saveyear;
	}

	public void setSaveyear(String saveyear) {
		this.saveyear = saveyear;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	public String getOpername() {
		return opername;
	}

	public void setOpername(String opername) {
		this.opername = opername;
	}

	public String getFilepathandname() {
		return filepathandname;
	}

	public void setFilepathandname(String filepathandname) {
		this.filepathandname = filepathandname;
	}
}

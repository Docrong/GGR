package com.boco.eoms.commons.log.model.castor;

import java.io.Serializable;

/**
 * 
 * @author panlong
 * @Mar 23, 2007 3:32:36 AM
 */
public class TawCommonLogFileDeploy implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String modeid;

	private String savetime;

	private String filepath;

	private String userid;

	private String operid;

	public String getOperid() {
		return operid;
	}

	public void setOperid(String operid) {
		this.operid = operid;
	}

	public String getModeid() {
		return modeid;
	}

	public void setModeid(String modeid) {
		this.modeid = modeid;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getSavetime() {
		return savetime;
	}

	public void setSavetime(String savetime) {
		this.savetime = savetime;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
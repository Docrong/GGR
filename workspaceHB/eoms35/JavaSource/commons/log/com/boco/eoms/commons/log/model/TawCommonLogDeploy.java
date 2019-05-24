package com.boco.eoms.commons.log.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="TawCommonLogDeploy.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_common_logdeploy"
 */
public class TawCommonLogDeploy extends BaseObject implements Serializable {

	
	private static final long serialVersionUID = 1L;

	 
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	 
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	 
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	private String id;

	private String userid;

	private String operid;

	private String opername;

	private String modelid;

	private String modelname;

	private String operdesc;

	private String isdebug;

	private String loglevel;

	private String filesavepath;

	private Integer filecutsize;

	private String noteremark;

	private String beginnotetime;

	private String savetype;

	/**
	 * @hibernate.property length="10"
	 * @eoms.show
	 * @eoms.cn name="记录的定制时间"
	 * @return
	 */
	public String getBeginnotetime() {
		return beginnotetime;
	}

	public void setBeginnotetime(String beginnotetime) {
		this.beginnotetime = beginnotetime;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="文件的保存路径"
	 * @return
	 */
	public String getFilesavepath() {
		return filesavepath;
	}

	public void setFilesavepath(String filesavepath) {
		this.filesavepath = filesavepath;
	}

	/**
	 * @hibernate.id column="id" generator-class="uuid.hex" unsaved-value="null"
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @hibernate.property length="2"
	 * @eoms.show
	 * @eoms.cn name="是否记录DEBUG"
	 * @return
	 */
	public String getIsdebug() {

		return isdebug;
	}

	public void setIsdebug(String isdebug) {
		this.isdebug = isdebug;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="日志级别"
	 * @return
	 */
	public String getLoglevel() {
		return loglevel;
	}

	public void setLoglevel(String loglevel) {
		this.loglevel = loglevel;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="模块ID"
	 * @return
	 */
	public String getModelid() {
		return modelid;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="模块NAME"
	 * @return
	 */
	public String getModelname() {
		return modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="记录备注"
	 * @return
	 */
	public String getNoteremark() {
		return noteremark;
	}

	public void setNoteremark(String noteremark) {
		this.noteremark = noteremark;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="某个操作的业务描述"
	 * @return
	 */
	public String getOperdesc() {
		return operdesc;
	}

	public void setOperdesc(String operdesc) {
		this.operdesc = operdesc;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="没个操作的ID"
	 * @return
	 */
	public String getOperid() {
		return operid;
	}

	public void setOperid(String operid) {
		this.operid = operid;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="ACTIONNAME"
	 * @return
	 */
	public String getOpername() {
		return opername;
	}

	public void setOpername(String opername) {
		this.opername = opername;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="所执行操作的USERID"
	 * @return
	 */
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @hibernate.property length="10"
	 * @eoms.show
	 * @eoms.cn name="保存类型"
	 * @return
	 */
	public String getSavetype() {
		return savetype;
	}

	public void setSavetype(String savetype) {
		this.savetype = savetype;
	}

	/**
	 * @hibernate.property length="200"
	 * @eoms.show
	 * @eoms.cn name="文件的保存路径"
	 * @return
	 */
	public Integer getFilecutsize() {
		return filecutsize;
	}

	public void setFilecutsize(Integer filecutsize) {
		this.filecutsize = filecutsize;
	}

}

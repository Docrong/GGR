package com.boco.eoms.sheet.tool.access.model;


import com.boco.eoms.base.model.BaseObject;
/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="TawSheetAccess.java.do"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="sheet_tool_access"
 */
public class TawSheetAccess extends BaseObject {

	private Integer id;
	
	private String processname;
	
	private String taskname;
	
	private String accesss;
	
	private String leaf;
	private String accessid;
	private String parAccessid;
	private Integer ordercode;
	private String remark;

	
	/**
	 * @hibernate.property length="254"
	 * @eoms.show
	 * @eoms.cn name="附件" 
	 * @return
	 */
	public String getAccesss() {
		return accesss;
	}

	public void setAccesss(String accesss) {
		this.accesss = accesss;
	}

	
	/**
	 * @hibernate.id column="id" generator-class="increment" unsaved-value="null"
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="地域ID" 
	 * @return
	 */
	public String getProcessname() {
		return processname;
	}

	public void setProcessname(String processname) {
		this.processname = processname;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="地域ID" 
	 * @return
	 */
	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * @hibernate.property length="254"
	 * @eoms.show
	 * @eoms.cn name="附件ID" 
	 * @return
	 */
	public String getAccessid() {
		return accessid;
	}

	public void setAccessid(String accessid) {
		this.accessid = accessid;
	}
	/**
	 * @hibernate.property length="5"
	 * @eoms.show
	 * @eoms.cn name="附件叶子标识" 
	 * @return
	 */
	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}
	/**
	 * @hibernate.property
	 * @eoms.show
	 * @eoms.cn name="排序标志" 
	 * @return
	 */
	public Integer getOrdercode() {
		return ordercode;
	}

	public void setOrdercode(Integer ordercode) {
		this.ordercode = ordercode;
	}
	/**
	 * @hibernate.property length="254"
	 * @eoms.show
	 * @eoms.cn name="父ID" 
	 * @return
	 */
	public String getParAccessid() {
		return parAccessid;
	}

	public void setParAccessid(String parAccessid) {
		this.parAccessid = parAccessid;
	}
	/**
	 * @hibernate.property length="254"
	 * @eoms.show
	 * @eoms.cn name="备注" 
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}

package com.boco.eoms.operuser.model;

import com.boco.eoms.base.model.BaseObject;

/**
 * <p>
 * Title:operuser
 * </p>
 * <p>
 * Description:operuser
 * </p>
 * <p>
 * Tue Mar 31 09:42:13 CST 2009
 * </p>
 * 
 * @author xiang
 * @version 35
 * 
 */
public class Operuser extends BaseObject {
	
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
	 * name
	 *
	 */
	private java.lang.String name;
   
	public void setName(java.lang.String name){
		this.name= name;       
	}
   
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *
	 * deptid
	 *
	 */
	private java.lang.String deptid;
   
	public void setDeptid(java.lang.String deptid){
		this.deptid= deptid;       
	}
   
	public java.lang.String getDeptid(){
		return this.deptid;
	}

	/**
	 *
	 * deptname
	 *
	 */
	private java.lang.String deptname;
   
	public void setDeptname(java.lang.String deptname){
		this.deptname= deptname;       
	}
   
	public java.lang.String getDeptname(){
		return this.deptname;
	}

	/**
	 *
	 * sex
	 *
	 */
	private java.lang.String sex;
   
	public void setSex(java.lang.String sex){
		this.sex= sex;       
	}
   
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *
	 * subarea
	 *
	 */
	private java.lang.String subarea;
   
	public void setSubarea(java.lang.String subarea){
		this.subarea= subarea;       
	}
   
	public java.lang.String getSubarea(){
		return this.subarea;
	}

	/**
	 *
	 * savetime
	 *
	 */
	private java.util.Date savetime;
   
	public void setSavetime(java.util.Date savetime){
		this.savetime= savetime;       
	}
   
	public java.util.Date getSavetime(){
		return this.savetime;
	}

	/**
	 *
	 * majortype
	 *
	 */
	private java.lang.String majortype;
   
	public void setMajortype(java.lang.String majortype){
		this.majortype= majortype;       
	}
   
	public java.lang.String getMajortype(){
		return this.majortype;
	}

	/**
	 *
	 * birthday
	 *
	 */
	private java.util.Date birthday;
	
	public java.util.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}
	
	
	/**
	 *
	 * jobtype
	 *
	 */
	private java.lang.String jobtype;
   
	public void setJobtype(java.lang.String jobtype){
		this.jobtype= jobtype;       
	}
   
	public java.lang.String getJobtype(){
		return this.jobtype;
	}

	/**
	 *
	 * joblevele
	 *
	 */
	private java.lang.String joblevele;
   
	public void setJoblevele(java.lang.String joblevele){
		this.joblevele= joblevele;       
	}
   
	public java.lang.String getJoblevele(){
		return this.joblevele;
	}

	/**
	 *
	 * schoollevel
	 *
	 */
	private java.lang.String schoollevel;
   
	public void setSchoollevel(java.lang.String schoollevel){
		this.schoollevel= schoollevel;       
	}
   
	public java.lang.String getSchoollevel(){
		return this.schoollevel;
	}

	/**
	 *
	 * powerlevel
	 *
	 */
	private java.lang.String powerlevel;
   
	public void setPowerlevel(java.lang.String powerlevel){
		this.powerlevel= powerlevel;       
	}
   
	public java.lang.String getPowerlevel(){
		return this.powerlevel;
	}

	/**
	 *
	 * prizelevel
	 *
	 */
	private java.lang.String prizelevel;
   
	public void setPrizelevel(java.lang.String prizelevel){
		this.prizelevel= prizelevel;       
	}
   
	public java.lang.String getPrizelevel(){
		return this.prizelevel;
	}

	/**
	 *
	 * worklevel
	 *
	 */
	private java.lang.String worklevel;
   
	public void setWorklevel(java.lang.String worklevel){
		this.worklevel= worklevel;       
	}
   
	public java.lang.String getWorklevel(){
		return this.worklevel;
	}

	/**
	 *
	 * remark
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
	 * 节点Id（按规则生成）
	 *//*
	private String nodeId;
	
	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
	*//**
	 * 父节点Id
	 *//*
	private String parentNodeId;
	
	public String getParentNodeId() {
		return parentNodeId;
	}

	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}
	
	*//**
	 * 是否叶节点
	 *//*
	private String leaf;
	
	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}*/

	public boolean equals(Object o) {
		if( o instanceof Operuser ) {
			Operuser operuser=(Operuser)o;
			if (this.id != null || this.id.equals(operuser.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	

}
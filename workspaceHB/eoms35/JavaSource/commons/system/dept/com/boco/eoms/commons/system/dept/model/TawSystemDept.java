package com.boco.eoms.commons.system.dept.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 * 
 * <p>
 * <a href="TawSystemDept.java.html"><i>View Source</i></a>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 *         Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 *         UserDetails interface by David Carter david@carter.net
 * 
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_system_dept"
 */
public class TawSystemDept extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2429985109375780431L;

	private Integer id;

	/**
	 * 为统计区分上下级部门及排序使用，现已改为用来维持部门树形结构的节点Id
	 */
	private String linkid;
	
	/**
	 * 父部门linkid
	 */
	private String parentLinkid;

	private String tmpdeptid;

	private String operuserid;

	private String deptId;

	private String olddeptId;

	private String deptName;

	private String deptmanager;

	private String parentDeptid;

	private String oldparentDeptid;

	private String tmporaryManager;

	private String deptType;

	private String deptphone;

	private String deptmobile;

	private String deptfax;

	private String deptemail;

	private String deleted;

	private Integer ordercode;

	private Integer regionflag;

	private String tmporarybegintime;

	private String tmporarystopTime;

	private String opertime;

	private String updatetime;

	private String leaf;

	private String operremoteip;

	private String remark;

	private String areaid;

	private String isPartners;//判断是否属于代维人员
	
	private String isDaiweiRoot;//是否代维根节点，用于选择代维人员的树型窗口，0表示非根，1表示根节点
	
	/**
	 * @hibernate.property length="10"
	 * @eoms.show
	 * @eoms.cn name="删除标志位"
	 * @return
	 */
	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="部门EMAIL"
	 * @return
	 */
	public String getDeptemail() {
		return deptemail;
	}

	public void setDeptemail(String deptemail) {
		this.deptemail = deptemail;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="部门传真"
	 * @return
	 */
	public String getDeptfax() {
		return deptfax;
	}

	public void setDeptfax(String deptfax) {
		this.deptfax = deptfax;
	}

	/**
	 * @hibernate.property length="1000"
	 * @eoms.show
	 * @eoms.cn name="部门ID"
	 * @return
	 */
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="部门负责人"
	 * @return
	 */
	public String getDeptmanager() {
		return deptmanager;
	}

	public void setDeptmanager(String deptmanager) {
		this.deptmanager = deptmanager;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="部门手机"
	 * @return
	 */
	public String getDeptmobile() {
		return deptmobile;
	}

	public void setDeptmobile(String deptmobile) {
		this.deptmobile = deptmobile;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="部门名称"
	 * @return
	 */
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="部门电话"
	 * @return
	 */
	public String getDeptphone() {
		return deptphone;
	}

	public void setDeptphone(String deptphone) {
		this.deptphone = deptphone;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="部门类型 标识"
	 * @return
	 */
	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="此次操作的IP地址"
	 * @return
	 */
	public String getOperremoteip() {
		return operremoteip;
	}

	public void setOperremoteip(String operremoteip) {
		this.operremoteip = operremoteip;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="操作时间"
	 * @return
	 */
	public String getOpertime() {
		return opertime;
	}

	public void setOpertime(String opertime) {
		this.opertime = opertime;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="操作者的USERID"
	 * @return
	 */
	public String getOperuserid() {
		return operuserid;
	}

	public void setOperuserid(String operuserid) {
		this.operuserid = operuserid;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="特殊排序标志位"
	 * @return
	 */
	public Integer getOrdercode() {
		return ordercode;
	}

	public void setOrdercode(Integer ordercode) {
		this.ordercode = ordercode;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="父部门ID"
	 * @return
	 */
	public String getParentDeptid() {
		return parentDeptid;
	}

	public void setParentDeptid(String parentDeptid) {
		this.parentDeptid = parentDeptid;
	}

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="地区部门标志位"
	 * @return
	 */
	public Integer getRegionflag() {
		return regionflag;
	}

	public void setRegionflag(Integer regionflag) {
		this.regionflag = regionflag;
	}

	/**
	 * @hibernate.property length="100"
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

	/**
	 * @hibernate.property length="50"
	 * @eoms.show
	 * @eoms.cn name="部门临时负责人开始负责时间"
	 * @return
	 */
	public String getTmporarybegintime() {
		return tmporarybegintime;
	}

	public void setTmporarybegintime(String tmporarybegintime) {
		this.tmporarybegintime = tmporarybegintime;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="部门临时负责人ID"
	 * @return
	 */
	public String getTmporaryManager() {
		return tmporaryManager;
	}

	public void setTmporaryManager(String tmporaryManager) {
		this.tmporaryManager = tmporaryManager;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="部门临时负责人结束负责时间"
	 * @return
	 */
	public String getTmporarystopTime() {
		return tmporarystopTime;
	}

	public void setTmporarystopTime(String tmporarystopTime) {
		this.tmporarystopTime = tmporarystopTime;
	}

	/**
	 * @hibernate.property length="100"
	 * @eoms.show
	 * @eoms.cn name="某记录更新时间"
	 * @return
	 */
	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	/**
	 * @hibernate.property length="10"
	 * @eoms.show
	 * @eoms.cn name="部门树的叶节点标志"
	 * @return
	 */
	public String getLeaf() {
		return leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	/**
	 * @hibernate.id column="id" generator-class="increment"
	 *               unsaved-value="null"
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
	 * @eoms.cn name="部门树的叶节点标志"
	 * @return
	 */
	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.boco.eoms.base.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		if (o instanceof TawSystemDept) {
			TawSystemDept tawSystemDept = (TawSystemDept) o;
			if (this.id != null || this.id.equals(tawSystemDept.getId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}


	/**
	 * @return the linkid
	 */
	public String getLinkid() {
		return linkid;
	}

	/**
	 * @param linkid
	 *            the linkid to set
	 */
	public void setLinkid(String linkid) {
		this.linkid = linkid;
	}

	public String getOlddeptId() {
		return olddeptId;
	}

	public void setOlddeptId(String olddeptId) {
		this.olddeptId = olddeptId;
	}

	public String getOldparentDeptid() {
		return oldparentDeptid;
	}

	public void setOldparentDeptid(String oldparentDeptid) {
		this.oldparentDeptid = oldparentDeptid;
	}

	public String getTmpdeptid() {
		// this.tmpdeptid=this.deptId;
		return tmpdeptid;
	}

	public void setTmpdeptid(String tmpdeptid) {
		this.tmpdeptid = tmpdeptid;
	}

	public String getIsPartners() {
		return isPartners;
	}

	public void setIsPartners(String isPartners) {
		this.isPartners = isPartners;
	}

	public String getIsDaiweiRoot() {
		return isDaiweiRoot;
	}

	public void setIsDaiweiRoot(String isDaiweiRoot) {
		this.isDaiweiRoot = isDaiweiRoot;
	}

	public String getParentLinkid() {
		return parentLinkid;
	}

	public void setParentLinkid(String parentLinkid) {
		this.parentLinkid = parentLinkid;
	}

}

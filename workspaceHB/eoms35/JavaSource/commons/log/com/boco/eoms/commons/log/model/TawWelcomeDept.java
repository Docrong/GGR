package com.boco.eoms.commons.log.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;


public class TawWelcomeDept extends BaseObject implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    /**
     * 为统计区分上下级部门及排序使用
     */
    private String linkid;

    private String operuserid;

    private String deptid;

    private String deptName;

    private String deptmanager;

    private String parentDeptid;

    private String tmporaryManager;

    private String depttype;

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

    /**
     * @return
     * @hibernate.property length="10"
     * @eoms.show
     * @eoms.cn name="删除标志位"
     */
    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="部门EMAIL"
     */
    public String getDeptemail() {
        return deptemail;
    }

    public void setDeptemail(String deptemail) {
        this.deptemail = deptemail;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="部门传真"
     */
    public String getDeptfax() {
        return deptfax;
    }

    public void setDeptfax(String deptfax) {
        this.deptfax = deptfax;
    }

    /**
     * @return
     * @hibernate.property length="1000"
     * @eoms.show
     * @eoms.cn name="部门ID"
     */
    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="部门负责人"
     */
    public String getDeptmanager() {
        return deptmanager;
    }

    public void setDeptmanager(String deptmanager) {
        this.deptmanager = deptmanager;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="部门手机"
     */
    public String getDeptmobile() {
        return deptmobile;
    }

    public void setDeptmobile(String deptmobile) {
        this.deptmobile = deptmobile;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="部门名称"
     */
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="部门电话"
     */
    public String getDeptphone() {
        return deptphone;
    }

    public void setDeptphone(String deptphone) {
        this.deptphone = deptphone;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="部门类型 标识"
     */
    public String getDepttype() {
        return depttype;
    }

    public void setDepttype(String depttype) {
        this.depttype = depttype;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="此次操作的IP地址"
     */
    public String getOperremoteip() {
        return operremoteip;
    }

    public void setOperremoteip(String operremoteip) {
        this.operremoteip = operremoteip;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="操作时间"
     */
    public String getOpertime() {
        return opertime;
    }

    public void setOpertime(String opertime) {
        this.opertime = opertime;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="操作者的USERID"
     */
    public String getOperuserid() {
        return operuserid;
    }

    public void setOperuserid(String operuserid) {
        this.operuserid = operuserid;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="特殊排序标志位"
     */
    public Integer getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(Integer ordercode) {
        this.ordercode = ordercode;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="父部门ID"
     */
    public String getParentDeptid() {
        return parentDeptid;
    }

    public void setParentDeptid(String parentDeptid) {
        this.parentDeptid = parentDeptid;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="地区部门标志位"
     */
    public Integer getRegionflag() {
        return regionflag;
    }

    public void setRegionflag(Integer regionflag) {
        this.regionflag = regionflag;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="备注"
     */
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="部门临时负责人开始负责时间"
     */
    public String getTmporarybegintime() {
        return tmporarybegintime;
    }

    public void setTmporarybegintime(String tmporarybegintime) {
        this.tmporarybegintime = tmporarybegintime;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="部门临时负责人ID"
     */
    public String getTmporaryManager() {
        return tmporaryManager;
    }

    public void setTmporaryManager(String tmporaryManager) {
        this.tmporaryManager = tmporaryManager;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="部门临时负责人结束负责时间"
     */
    public String getTmporarystopTime() {
        return tmporarystopTime;
    }

    public void setTmporarystopTime(String tmporarystopTime) {
        this.tmporarystopTime = tmporarystopTime;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="某记录更新时间"
     */
    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * @return
     * @hibernate.property length="10"
     * @eoms.show
     * @eoms.cn name="部门树的叶节点标志"
     */
    public String getLeaf() {
        return leaf;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    /**
     * @hibernate.id column="id" generator-class="increment"
     * unsaved-value="null"
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="部门树的叶节点标志"
     */
    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }


    public int hashCode() {
        return this.hashCode();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.boco.eoms.base.model.BaseObject#toString()
     */
    public String toString() {
        return this.toString();
    }

    /**
     * @return the linkid
     */
    public String getLinkid() {
        return linkid;
    }

    /**
     * @param linkid the linkid to set
     */
    public void setLinkid(String linkid) {
        this.linkid = linkid;
    }

    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return false;
    }
}

package com.boco.eoms.commons.system.priv.model;

import java.io.Serializable;

import com.boco.eoms.base.model.BaseObject;

/**
 * This class is used to generate the Struts Validator Form as well as the This
 * class is used to generate Spring Validation rules as well as the Hibernate
 * mapping file.
 *
 * <p>
 * <a href="TawSystemPrivUserAssign.java.html"><i>View Source</i></a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Updated by
 * Dan Kibler (dan@getrolling.com) Extended to implement Acegi
 * UserDetails interface by David Carter david@carter.net
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class table="taw_system_priv_userassign"
 */
public class TawSystemPrivUserAssign extends BaseObject implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1584700557326830843L;

    private String id;

    private String userid;

    private String parentprivid;

    private String currentprivid;

    private String parentprivname;

    private String currentprivname;

    private Integer hide;

    private Integer leaf;

    private Integer isonepriv;

    private Integer ordercode;

    private String url;

    private String remark;

    private String hrefTarget;

    private String menuid;

    private String isapp;

    private String orderby;

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }

    /**
     * @return
     * @hibernate.property length="500"
     * @eoms.show
     * @eoms.cn name="当前权限ID"
     */
    public String getCurrentprivid() {
        return currentprivid;
    }

    public void setCurrentprivid(String currentprivid) {
        this.currentprivid = currentprivid;
    }

    /**
     * @return
     * @hibernate.property length="200"
     * @eoms.show
     * @eoms.cn name="当前权限名称"
     */
    public String getCurrentprivname() {
        return currentprivname;
    }

    public void setCurrentprivname(String currentprivname) {
        this.currentprivname = currentprivname;
    }

    /**
     * @return
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="是否显示"
     */
    public Integer getHide() {
        return hide;
    }

    public void setHide(Integer hide) {
        this.hide = hide;
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
     * @return
     * @hibernate.property length="500"
     * @eoms.show
     * @eoms.cn name="是否属于一级目录"
     */
    public Integer getIsonepriv() {
        return isonepriv;
    }

    public void setIsonepriv(Integer isonepriv) {
        this.isonepriv = isonepriv;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="是否是叶子节点"
     */
    public Integer getLeaf() {
        return leaf;
    }

    public void setLeaf(Integer leaf) {
        this.leaf = leaf;
    }

    /**
     * @return
     * @hibernate.property length="50"
     * @eoms.show
     * @eoms.cn name="特殊排序标志"
     */
    public Integer getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(Integer ordercode) {
        this.ordercode = ordercode;
    }

    /**
     * @return
     * @hibernate.property length="500"
     * @eoms.show
     * @eoms.cn name="父权限ID"
     */
    public String getParentprivid() {
        return parentprivid;
    }

    public void setParentprivid(String parentprivid) {
        this.parentprivid = parentprivid;
    }

    /**
     * @return
     * @hibernate.property length="200"
     * @eoms.show
     * @eoms.cn name="父权限名称"
     */
    public String getParentprivname() {
        return parentprivname;
    }

    public void setParentprivname(String parentprivname) {
        this.parentprivname = parentprivname;
    }

    /**
     * @return
     * @hibernate.property length="200"
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
     * @hibernate.property length="100"
     * @eoms.show
     * @eoms.cn name="用户ID"
     */
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    public int hashCode() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return
     * @hibernate.property length="500"
     * @eoms.show
     * @eoms.cn name="用户ID"
     */
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return
     * @hibernate.property length="500"
     * @eoms.show
     * @eoms.cn name="链接的target"
     */
    public String getHrefTarget() {
        return hrefTarget;
    }

    public void setHrefTarget(String hrefTarget) {
        this.hrefTarget = hrefTarget;
    }

    public String getIsapp() {
        return isapp;
    }

    public void setIsapp(String isapp) {
        this.isapp = isapp;
    }

}

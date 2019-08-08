package com.boco.eoms.workplan.model;

/**
 * <p>Title: 作业计划模板类</p>
 * <p>Description: 作业计划模板信息，其中包括模板的名称，系统类型，网元类别等信息</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.boco.eoms.common.oo.DataObject;

/**
 * @hibernate.class table="TAW_WP_MODELPLAN"
 */

public class TawwpModelPlan implements Serializable, DataObject {

    /**
     * 序列
     */
    private static final long serialVersionUID = 7271932730344355136L;

    private String id; // 标识

    private String name; // 作业计划模板名称

    private String sysTypeId; // 系统类别

    private String netTypeId; // 网元类型

    private String typeIndex; // 网元大类型

    private String origin; // 来源 0:内部 1:接口

    private String deleted; // 删除标志

    private String cruser; // 创建人

    private String crtime; // 创建时间

    private String unicomType; // 联通分类 用于联通系统专用

    private String reportFlag;

    private Set tawwpModelExecutes = new HashSet(); // 对应的作业计划模板执行内容集合

    private Set tawwpModelGroup = new HashSet(); // 对应的作业计划模板组织信息集合

    private Set tawwpModelDispatch = new HashSet(); // 对应的作业计划派发信息

    private Set tawwpYearPlan = new HashSet(); // 对应的年度作业计划信息

    public TawwpModelPlan() {
    }

    public TawwpModelPlan(String _name,
                          String _sysTypeId,
                          String _netTypeId,// String _typeIndex,
                          String _origin, String _deleted, String _cruser, String _crtime,
                          Set _tawwpModelExecutes, Set _tawwpModelGroup,
                          Set _tawwpModelDispatch) {
        this.name = _name;
        this.sysTypeId = _sysTypeId;
        this.netTypeId = _netTypeId;
        // this.typeIndex=_typeIndex;
        this.origin = _origin;
        this.deleted = _deleted;
        this.cruser = _cruser;
        this.crtime = _crtime;
        this.tawwpModelExecutes = _tawwpModelExecutes;
        this.tawwpModelGroup = _tawwpModelGroup;
        this.tawwpModelDispatch = _tawwpModelDispatch;
    }

    public TawwpModelPlan(String _name, String _sysTypeId, String _netTypeId,
                          String _typeIndex, String _origin, String _deleted, String _cruser,
                          String _crtime) {
        this.name = _name;
        this.sysTypeId = _sysTypeId;
        this.netTypeId = _netTypeId;
        this.typeIndex = _typeIndex;
        this.origin = _origin;
        this.deleted = _deleted;
        this.cruser = _cruser;
        this.crtime = _crtime;
    }

    public TawwpModelPlan(String _name, String _sysTypeId, String _netTypeId,
                          String _origin, String _deleted, String _cruser, String _crtime) {
        this.name = _name;
        this.sysTypeId = _sysTypeId;
        this.netTypeId = _netTypeId;
        // this.typeIndex=_typeIndex;
        this.origin = _origin;
        this.deleted = _deleted;
        this.cruser = _cruser;
        this.crtime = _crtime;
    }

    /**
     * @hibernate.id column="ID" length="32" unsaved-value="null"
     * generator-class="uuid.hex"
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @hibernate.property column="NAMES" length="100" not-null="true"
     * update="true"
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @hibernate.property column="SYSTYPEID" length="10" not-null="false"
     * update="false"
     */

    public String getSysTypeId() {
        return sysTypeId;
    }

    public void setSysTypeId(String sysTypeId) {
        this.sysTypeId = sysTypeId;
    }

    /**
     * @hibernate.property column="NETTYPEID" length="10" not-null="false"
     * update="false"
     */

    public String getNetTypeId() {
        return netTypeId;
    }

    public void setNetTypeId(String netTypeId) {
        this.netTypeId = netTypeId;
    }

    public String getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(String typeIndex) {
        this.typeIndex = typeIndex;
    }

    /**
     * @hibernate.property column="ORIGIN" length="1" not-null="true"
     * update="false"
     */

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * @hibernate.property column="CRTIME" length="19" not-null="true"
     * update="false"
     */

    public String getCrtime() {
        return crtime;
    }

    public void setCrtime(String crtime) {
        this.crtime = crtime;
    }

    /**
     * @hibernate.property column="CRUSER" length="20" not-null="true"
     * update="false"
     */

    public String getCruser() {
        return cruser;
    }

    public void setCruser(String cruser) {
        this.cruser = cruser;
    }

    /**
     * @hibernate.property column="DELETED" length="1" not-null="true"
     * update="true"
     */

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    /**
     * @hibernate.property column="UNICOMTYPE" length="30" not-null="false"
     * update="true"
     */
    public String getUnicomType() {
        return unicomType;
    }

    public void setUnicomType(String unicomType) {
        this.unicomType = unicomType;
    }

    /**
     * @hibernate.set inverse="true" lazy="true" cascade="all"
     * @hibernate.collection-key column="MODEL_PLAN_ID"
     * @hibernate.collection-one-to-many class="com.boco.eoms.workplan.model.TawwpModelExecute"
     */

    public Set getTawwpModelExecutes() {
        return tawwpModelExecutes;
    }

    public void setTawwpModelExecutes(Set tawwpModelExecutes) {
        this.tawwpModelExecutes = tawwpModelExecutes;
    }

    /**
     * @hibernate.set inverse="true" lazy="true" cascade="save-update"
     * @hibernate.collection-key column="MODEL_PLAN_ID"
     * @hibernate.collection-one-to-many class="com.boco.eoms.workplan.model.TawwpModelGroup"
     */

    public Set getTawwpModelGroup() {
        return tawwpModelGroup;
    }

    public void setTawwpModelGroup(Set tawwpModelGroup) {
        this.tawwpModelGroup = tawwpModelGroup;
    }

    /**
     * @hibernate.set inverse="true" lazy="true" cascade="save-update"
     * @hibernate.collection-key column="MODEL_PLAN_ID"
     * @hibernate.collection-one-to-many class="com.boco.eoms.workplan.model.TawwpModelDispatch"
     */

    public Set getTawwpModelDispatch() {
        return tawwpModelDispatch;
    }

    public void setTawwpModelDispatch(Set tawwpModelDispatch) {
        this.tawwpModelDispatch = tawwpModelDispatch;
    }

    /**
     * @hibernate.set inverse="true" lazy="true" cascade="save-update"
     * @hibernate.collection-key column="MODEL_PLAN_ID"
     * @hibernate.collection-one-to-many class="com.boco.eoms.workplan.model.TawwpYearPlan"
     */
    public Set getTawwpYearPlan() {
        return tawwpYearPlan;
    }

    public void setTawwpYearPlan(Set tawwpYearPlan) {
        this.tawwpYearPlan = tawwpYearPlan;
    }

    public String getReportFlag() {
        return reportFlag;
    }

    public void setReportFlag(String reportFlag) {
        this.reportFlag = reportFlag;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if ((this.id == null) || ((TawwpModelPlan) obj).id == null)
            return false;
        return this.id.equals(((TawwpModelPlan) obj).id);
    }

}

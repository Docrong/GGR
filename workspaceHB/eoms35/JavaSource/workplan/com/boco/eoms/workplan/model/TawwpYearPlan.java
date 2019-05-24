package com.boco.eoms.workplan.model;

/**
 * <p>Title: 年度作业计划类</p>
 * <p>Description: 年度作业计划信息，其中包括名称，部门，系统类型，网元类别等信息</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */

import java.io.*;
import java.util.*;
import com.boco.eoms.common.oo.DataObject;
import com.boco.eoms.workplan.model.TawwpModelPlan;

/**
 * @hibernate.class
 * table="TAW_WP_YEARPLAN"
 */

public class TawwpYearPlan
    implements Serializable,DataObject,Comparable{

  private String id; //标识
  private String name; //年度作业计划名称
  private String deptId; //所属部门
  private String yearFlag; //年度标识
  private String sysTypeId; //系统类别
  private String netTypeId; //网元类型
  private String typeIndex;
  private String deleted; //删除标志
  private String cruser; //创建人
  private String crtime; //创建时间
  private String content; //描述内容
  private String remark;  //备注
  private String netList; //对应的网元信息
  private String state; //状态 0：待审批 1：通过 2：驳回
  private String unicomType; //联通分类 用于联通系统专用
  private String reportFlag; //联通上报标识 联通系统专用
  private String isApp;		//是否需要月度审核
  private String command;
  private String modelId;
  
  private TawwpModelPlan tawwpModelPlan; //作业计划模板对象
  private Set tawwpYearExecutes = new HashSet(); //对应的年度作业计划执行内容集合
  private Set tawwpYearChecks = new HashSet(); //年度作业计划的审批信息集合
  private Set tawwpMonthPlans = new HashSet(); // 对应的月度作业计划集合
  

  public TawwpYearPlan() {
  }

  public TawwpYearPlan(String _name, String _deptId, String _sysTypeId,
                       String _netTypeId, String _deleted, String _cruser,
                       String _crtime, String _state,String _yearFlag,
                       String _content,String _remark,String _netList,String _isApp,
                       TawwpModelPlan _tawwpModelPlan, Set _tawwpYearExecutes,
                       Set _tawwpYearChecks) {
    this.name = _name;
    this.deptId = _deptId;
    this.yearFlag = _yearFlag;
    this.sysTypeId = _sysTypeId;
    this.netTypeId = _netTypeId;
    this.content = _content;
    this.remark = _remark;
    this.netList = _netList;
    this.isApp=_isApp;
    this.deleted = _deleted;
    this.cruser = _cruser;
    this.crtime = _crtime;
    this.state = _state;
    this.tawwpModelPlan = _tawwpModelPlan;
    this.tawwpYearExecutes = _tawwpYearExecutes;
    this.tawwpYearChecks = _tawwpYearChecks;
    if(null!=_tawwpModelPlan.getUnicomType())
    this.unicomType=_tawwpModelPlan.getUnicomType();
  }

  public TawwpYearPlan(String _name, String _deptId, String _sysTypeId,
                       String _netTypeId, String _deleted, String _cruser,
                       String _content,String _remark,String _netList,String _isApp,
                       String _crtime, String _state,String _yearFlag,
                       TawwpModelPlan _tawwpModelPlan) {
    this.name = _name;
    this.deptId = _deptId;
    this.yearFlag = _yearFlag;
    this.sysTypeId = _sysTypeId;
    this.netTypeId = _netTypeId;
    this.content = _content;
    this.remark = _remark;
    this.netList = _netList;
    this.isApp=_isApp;
    this.deleted = _deleted;
    this.cruser = _cruser;
    this.crtime = _crtime;
    this.state = _state;
    this.tawwpModelPlan = _tawwpModelPlan;
    if(null!=_tawwpModelPlan&&null!=_tawwpModelPlan.getUnicomType())
    this.unicomType=_tawwpModelPlan.getUnicomType();
  }

  public TawwpYearPlan(String _name, String _deptId, String _sysTypeId,
                       String _netTypeId, String _deleted, String _cruser,
                       String _content,String _remark,String _netList,String _isApp,
                       String _crtime, String _state,String _yearFlag,
                       TawwpModelPlan _tawwpModelPlan,String _typeIndex) {
    this.name = _name;
    this.deptId = _deptId;
    this.yearFlag = _yearFlag;
    this.sysTypeId = _sysTypeId;
    this.netTypeId = _netTypeId;
    this.content = _content;
    this.remark = _remark;
    this.netList = _netList;
    this.isApp=_isApp;
    this.deleted = _deleted;
    this.cruser = _cruser;
    this.crtime = _crtime;
    this.state = _state;
    this.typeIndex=_typeIndex;
    this.tawwpModelPlan = _tawwpModelPlan;
    if(null!=_tawwpModelPlan&&null!=_tawwpModelPlan.getUnicomType())
    this.unicomType=_tawwpModelPlan.getUnicomType();
  }



  /**
   * @hibernate.id
   * column="ID"
   * length="32"
   * unsaved-value="null"
   * generator-class="uuid.hex"
   */
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  /**
   * @hibernate.property
   * column="DEPTID"
   * length="10"
   * not-null="true"
   * update="false"
   */
  public String getDeptId() {
    return deptId;
  }

  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }

  /**
   * @hibernate.property
   * column="NAMES"
   * length="100"
   * not-null="true"
   * update="true"
   */
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * @hibernate.property
   * column="YEARFLAG"
   * length="100"
   * not-null="true"
   * update="false"
   */
  public String getYearFlag() {
    return yearFlag;
  }
  public void setYearFlag(String yearFlag) {
    this.yearFlag = yearFlag;
  }

  /**
   * @hibernate.property
   * column="REMARK"
   * length="200"
   * not-null="false"
   * update="true"
   */
  public String getRemark() {
    return remark;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }

  /**
   * @hibernate.property
   * column="NETLIST"
   * length="200"
   * not-null="false"
   * update="true"
   */
  public String getNetList() {
    return netList;
  }
  public void setNetList(String netList) {
    this.netList = netList;
  }

  /**
   * @hibernate.property
   * column="CONTENT"
   * length="100"
   * not-null="false"
   * update="true"
   */
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }


  /**
   * @hibernate.property
   * column="SYSTYPEID"
   * length="10"
   * not-null="false"
   * update="false"
   */
  public String getSysTypeId() {
    return sysTypeId;
  }

  public void setSysTypeId(String sysTypeId) {
    this.sysTypeId = sysTypeId;
  }

  /**
   * @hibernate.property
   * column="NETTYPEID"
   * length="10"
   * not-null="false"
   * update="false"
   */
  public String getNetTypeId() {
    return netTypeId;
  }

  public void setTypeIndex(String typeIndex) {
    this.typeIndex = typeIndex;
  }
  public String getTypeIndex() {
      return typeIndex;
    }

    public void setNetTypeId(String netTypeId) {
      this.netTypeId = netTypeId;
    }

  /**
   * @hibernate.property
   * column="STATE"
   * length="2"
   * not-null="true"
   * update="true"
   */
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  /**
   * @hibernate.property
   * column="DELETED"
   * length="1"
   * not-null="true"
   * update="true"
   */
  public String getDeleted() {
    return deleted;
  }

  public void setDeleted(String deleted) {
    this.deleted = deleted;
  }

  /**
   * @hibernate.property
   * column="CRUSER"
   * length="20"
   * not-null="true"
   * update="false"
   */
  public String getCruser() {
    return cruser;
  }

  public void setCruser(String cruser) {
    this.cruser = cruser;
  }

  /**
   * @hibernate.property
   * column="CRTIME"
   * length="19"
   * not-null="true"
   * update="false"
   */
  public String getCrtime() {
    return crtime;
  }

  public void setCrtime(String crtime) {
    this.crtime = crtime;
  }

  /**
   * @hibernate.property
   * column="UNICOMTYPE"
   * length="30"
   * not-null="false"
   * update="true"
   */
  public String getUnicomType() {
    return unicomType;
  }

  public void setUnicomType(String unicomType) {
    this.unicomType = unicomType;
  }

  /**
   * @hibernate.property
   * column="REPORTFLAG"
   * length="2"
   * not-null="false"
   * update="true"
   */
  public String getReportFlag() {
    return reportFlag;
  }
  public void setReportFlag(String reportFlag) {
    this.reportFlag = reportFlag;
  }


  /**
   * @hibernate.many-to-one
   * column="MODEL_PLAN_ID"
   * cascade="none"
   * not-null="false"
   */
  public TawwpModelPlan getTawwpModelPlan() {
    return tawwpModelPlan;
  }

  public void setTawwpModelPlan(TawwpModelPlan tawwpModelPlan) {
    this.tawwpModelPlan = tawwpModelPlan;
  }

  /**
   * @hibernate.set
   * inverse="true"
   * lazy="true"
   * cascade="all"
   * @hibernate.collection-key
   * column="YEAR_PLAN_ID"
   * @hibernate.collection-one-to-many
   * class="com.boco.eoms.workplan.model.TawwpYearCheck"
   */

  public Set getTawwpYearChecks() {
    return tawwpYearChecks;
  }

  public void setTawwpYearChecks(Set tawwpYearChecks) {
    this.tawwpYearChecks = tawwpYearChecks;
  }

  /**
   * @hibernate.set
   * inverse="true"
   * lazy="true"
   * cascade="all"
   * @hibernate.collection-key
   * column="YEAR_PLAN_ID"
   * @hibernate.collection-one-to-many
   * class="com.boco.eoms.workplan.model.TawwpYearExecute"
   */
  public Set getTawwpYearExecutes() {
    return tawwpYearExecutes;
  }

  public void setTawwpYearExecutes(Set tawwpYearExecutes) {
    this.tawwpYearExecutes = tawwpYearExecutes;
  }

  /**
   * @hibernate.set
   * inverse="true"
   * lazy="true"
   * cascade="save-update"
   * @hibernate.collection-key
   * column="YEAR_PLAN_ID"
   * @hibernate.collection-one-to-many
   * class="com.boco.eoms.workplan.model.TawwpMonthPlan"
   */
  public Set getTawwpMonthPlans() {
    return tawwpMonthPlans;
  }
  public void setTawwpMonthPlans(Set tawwpMonthPlans) {
    this.tawwpMonthPlans = tawwpMonthPlans;
  }

  public int compareTo(Object otherObject){
                         TawwpYearPlan other = (TawwpYearPlan)otherObject;
                     if( crtime.compareTo(other.getCrtime()) < 0 ) return 1;     //比较以时间作为标准，返回1代表对象大
                     else if (crtime.compareTo(other.getCrtime()) > 0) return -1;    //返回－1代表对象小
                     else return 0;           //0表示相等
                 }
public String getCommand() {
	return command;
}

public void setCommand(String command) {
	this.command = command ;
}

public String getIsApp() {
	return isApp;
}

public void setIsApp(String isApp) {
	this.isApp = isApp;
}

public String getModelId() {
	return modelId;
}

public void setModelId(String modelId) {
	this.modelId = modelId;
}


}

package com.boco.eoms.workplan.model;

/**
 * <p>Title: 年度作业计划审批类</p>
 * <p>Description: 年度作业计划批类信息，其中包括审批人，审批部门，审批内容，状态，时间等</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */
import java.io.*;
import com.boco.eoms.common.oo.DataObject;

/**
 * @hibernate.class
 * table="TAW_WP_YEARCHECK"
 */

public class TawwpYearCheck
    implements Serializable, DataObject {

  private String id; //标识
  private String deptId; //部门
  private String checkUser; //审批人
  private String state; //审批状态  0：待审批 1：通过 2：驳回
  private String content; //审批信息
  private String crtime; //创建时间 （按创建时间排序）
  private String checktime; //审批时间
  private String flowSerial; //流程标识
  private String stepSerial; //步骤标识

  private TawwpYearPlan tawwpYearPlan; //年度作业计划对象
  private TawwpExecuteAssess tawwpExecuteAssess; //上步骤信息对象

  public TawwpYearCheck() {
  }

  public TawwpYearCheck(String _deptId, String _checkUser, String _content,
                        String _state, String _crtime, String _checktime,
                        String _flowSerial, String _stepSerial,
                        TawwpYearPlan _tawwpYearPlan,TawwpExecuteAssess _tawwpExecuteAssess) {
    this.deptId = _deptId;
    this.checkUser = _checkUser;
    this.content = _content;
    this.state = _state;
    this.crtime = _crtime;
    this.checktime = _checktime;
    this.flowSerial = _flowSerial;
    this.stepSerial = _stepSerial;
    this.tawwpYearPlan = _tawwpYearPlan;
    this.tawwpExecuteAssess = _tawwpExecuteAssess;
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
   * column="CONTENT"
   * length="200"
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
   * column="CHECKTIME"
   * length="19"
   * not-null="false"
   * update="true"
   */
  public String getChecktime() {
    return checktime;
  }

  public void setChecktime(String checktime) {
    this.checktime = checktime;
  }

  /**
   * @hibernate.property
   * column="CHECKUSER"
   * length="20"
   * not-null="true"
   * update="true"
   */
  public String getCheckUser() {
    return checkUser;
  }

  public void setCheckUser(String checkUser) {
    this.checkUser = checkUser;
  }

  /**
   * @hibernate.property
   * column="STATE"
   * length="2"
   * not-null="false"
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
   * column="FLOWSERIAL"
   * length="4"
   * not-null="false"
   * update="false"
   */
  public String getFlowSerial() {
    return flowSerial;
  }

  public void setFlowSerial(String flowSerial) {
    this.flowSerial = flowSerial;
  }

  /**
   * @hibernate.property
   * column="STEPSERIAL"
   * length="4"
   * not-null="false"
   * update="false"
   */
  public String getStepSerial() {
    return stepSerial;
  }

  public void setStepSerial(String stepSerial) {
    this.stepSerial = stepSerial;
  }

  /**
   * @hibernate.many-to-one
   * column="YEAR_PLAN_ID"
   * cascade="none"
   * not-null="true"
   */
  public TawwpYearPlan getTawwpYearPlan() {
    return tawwpYearPlan;
  }

  public void setTawwpYearPlan(TawwpYearPlan tawwpYearPlan) {
    this.tawwpYearPlan = tawwpYearPlan;
  }

  /**
   * @hibernate.many-to-one
   * column="EXECUTE_ASSESS_ID"
   * cascade="none"
   * not-null="false"
   */
  public TawwpExecuteAssess getTawwpExecuteAssess() {
    return tawwpExecuteAssess;
  }

  public void setTawwpExecuteAssess(TawwpExecuteAssess tawwpExecuteAssess) {
    this.tawwpExecuteAssess = tawwpExecuteAssess;
  }


}

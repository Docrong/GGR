package com.boco.eoms.workplan.vo;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class TawwpYearCheckVO
    implements Comparable {

  private String id; //标识
  private String deptId; //审批部门编号
  private String deptName; //审批部门名称
  private String checkUser; //审批人用户名
  private String checkUserName; //审批人姓名
  private String state; //审批状态编号  0：待审批 1：通过 2：驳回
  private String stateName; //审批状态名称
  private String content; //审批信息
  private String crtime; //创建时间 (按创建时间排序)
  private String checktime;
  private String flowSerial; //流程标识
  private String stepSerial; //步骤标识

  //制定状态名称数组
  static public String[] STATENAME = {
      "新建", "通过", "驳回"};

  public String getChecktime() {
    if (checktime == null) {
      checktime = "";
    }

    return checktime;
  }

  public void setChecktime(String checktime) {
    this.checktime = checktime;
  }

  public void setCheckUser(String checkUser) {
    this.checkUser = checkUser;
  }

  public String getContent() {
    if (content == null) {
      content = "";
    }

    return content;
  }

  public void setCheckUserName(String checkUserName) {
    this.checkUserName = checkUserName;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getCheckUserName() {
    if (checkUserName == null) {
      checkUserName = "";
    }

    return checkUserName;
  }

  public String getCrtime() {
    if (crtime == null) {
      crtime = "";
    }

    return crtime;
  }

  public String getDeptId() {
    if (deptId == null) {
      deptId = "";
    }

    return deptId;
  }

  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }

  public String getDeptName() {
    if (deptName == null) {
      deptName = "";
    }

    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public String getId() {
    if (id == null) {
      id = "";
    }

    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getState() {
    if (state == null) {
      state = "";
    }

    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public void setStateName(String stateName) {
    this.stateName = stateName;
  }

  public String getCheckUser() {
    if (checkUser == null) {
      checkUser = "";
    }

    return checkUser;
  }

  public void setCrtime(String crtime) {
    this.crtime = crtime;
  }

  public String getStateName() {
    if (stateName == null) {
      stateName = "";
    }

    return stateName;
  }

  public String getFlowSerial() {
    if (flowSerial == null) {
      flowSerial = "";
    }

    return flowSerial;
  }

  public void setFlowSerial(String flowSerial) {
    this.flowSerial = flowSerial;
  }

  public String getStepSerial() {
    if (stepSerial == null) {
      stepSerial = "";
    }

    return stepSerial;
  }

  public void setStepSerial(String stepSerial) {
    this.stepSerial = stepSerial;
  } //审批时间

  //进行排序处理，按创建时间进行排序
  public int compareTo(Object obj) {
    TawwpYearCheckVO tawwpYearCheckVO = (TawwpYearCheckVO) obj;
    String crtime1 = null;
    String crtime2 = null;

    int iRet = 0;

    try {
      crtime1 = this.getChecktime();
      crtime2 = tawwpYearCheckVO.getChecktime();

      iRet = crtime1.compareTo(crtime2);
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return iRet;
  }

}

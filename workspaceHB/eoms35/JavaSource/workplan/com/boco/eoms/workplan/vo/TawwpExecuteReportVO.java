package com.boco.eoms.workplan.vo;

/**
 * <p>Title: 执行作业计划周报、月报信息数据显示类</p>
 * <p>Description: 提供页面的所需的数据封装</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */

import java.util.List;

public class TawwpExecuteReportVO {

  private String id; //标识
  private String deptId; //部门编号
  private String deptName; //部门名称
  private String reportType; //汇报类型编号 0：周报 1：月报
  private String reportTypeName; //汇报类型名称
  private String startDate; //汇报的开始时间
  private String endDate; //汇报的结束时间
  private String content; //汇报信息
  private String reportUser; //汇报人登录名
  private String reportUserName; //汇报人姓名
  private String crtime; //创建时间 （按创建时间排序）
  private String reportFlag; //注意标志编号 0：正常 1：提醒
  private String reportFlagName; //注意标志名称
  private String remark; //备注
  private String advice; //建议

  //汇报类型名称数组
  static public String[] REPORTTYPE = {
      "周报", "月报"};

  //注意标志名称数组
  static public String[] REPORTFLAG = {
      "正常", "非正常"};

  /**
   * 获取汇报类型名称
   * @param _reportType 汇报类型编号
   * @return 汇报类型名称
   */
  public static String getReportTypeName(int _reportType) {
    //初始化数据
    String reportTypeName = "";
    //如果汇报类型编号在汇报类型范围以内
    if (_reportType < TawwpExecuteReportVO.REPORTTYPE.length &&
        _reportType >= 0) {
      //取出汇报类型显示名称
      reportTypeName = TawwpExecuteReportVO.REPORTTYPE[_reportType];
    }
    //返回汇报类型名称
    return reportTypeName;
  }

  /**
   * 获取注意标志名称
   * @param _reportFlag 注意标志编号
   * @return 注意标志名称
   */
  public static String getReportFlagName(int _reportFlag) {
    //初始化数据
    String reportFlagName = "";
    //如果注意标志编号在注意标志范围以内
    if (_reportFlag < TawwpExecuteReportVO.REPORTFLAG.length &&
        _reportFlag >= 0) {
      //取出注意标志显示名称
      reportFlagName = TawwpExecuteReportVO.REPORTFLAG[_reportFlag];
    }
    //返回注意标志名称
    return reportFlagName;
  }

  //各属性对应的set、get方法
  public String getAdvice() {
    if (advice == null) {
      advice = "";
    }

    return advice;
  }

  public String getContent() {
    if (content == null) {
      content = "";
    }

    return content;
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

  public String getDeptName() {
    if (deptName == null) {
      deptName = "";
    }

    return deptName;
  }

  public String getEndDate() {
    if (endDate == null) {
      endDate = "";
    }

    return endDate;
  }

  public String getId() {
    if (id == null) {
      id = "";
    }

    return id;
  }

  public String getRemark() {
    if (remark == null) {
      remark = "";
    }

    return remark;
  }

  public String getReportFlag() {
    if (reportFlag == null) {
      reportFlag = "";
    }

    return reportFlag;
  }

  public String getReportFlagName() {
    if (reportFlagName == null) {
      reportFlagName = "";
    }

    return reportFlagName;
  }

  public String getReportType() {
    if (reportType == null) {
      reportType = "";
    }

    return reportType;
  }

  public String getReportTypeName() {
    if (reportTypeName == null) {
      reportTypeName = "";
    }

    return reportTypeName;
  }

  public String getReportUser() {
    if (reportUser == null) {
      reportUser = "";
    }

    return reportUser;
  }

  public String getReportUserName() {
    if (reportUserName == null) {
      reportUserName = "";
    }

    return reportUserName;
  }

  public String getStartDate() {
    if (startDate == null) {
      startDate = "";
    }

    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public void setReportUserName(String reportUserName) {
    this.reportUserName = reportUserName;
  }

  public void setReportUser(String reportUser) {
    this.reportUser = reportUser;
  }

  public void setReportTypeName(String reportTypeName) {
    this.reportTypeName = reportTypeName;
  }

  public void setReportFlagName(String reportFlagName) {
    this.reportFlagName = reportFlagName;
  }

  public void setReportFlag(String reportFlag) {
    this.reportFlag = reportFlag;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }

  public void setCrtime(String crtime) {
    this.crtime = crtime;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setAdvice(String advice) {
    this.advice = advice;
  }

  public void setReportType(String reportType) {
    this.reportType = reportType;
  }

}

package com.boco.eoms.workplan.vo;

/**
 * <p>Title: 作业计划年度统计信息数据显示类</p>
 * <p>Description: 提供页面的所需的数据封装</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */

import java.util.Hashtable;

public class TawwpStatYearPlanVO {

  String yearPlanId;
  String yearPlanName;
  String yearFlag;

  Hashtable yearExecutehashtable;

  public TawwpStatYearPlanVO() {
    yearExecutehashtable = new Hashtable();
  }

  public String getYearPlanId() {
    if (yearPlanId == null) {
      yearPlanId = "";
    }

    return yearPlanId;
  }

  public void setYearPlanId(String yearPlanId) {
    this.yearPlanId = yearPlanId;
  }

  public String getYearPlanName() {
    if (yearPlanName == null) {
      yearPlanName = "";
    }

    return yearPlanName;
  }

  public void setYearPlanName(String yearPlanName) {
    this.yearPlanName = yearPlanName;
  }

  public Hashtable getYearExecutehashtable() {
    return yearExecutehashtable;
  }

  public void setYearExecutehashtable(Hashtable yearExecutehashtable) {
    this.yearExecutehashtable = yearExecutehashtable;
  }

  public String getYearFlag() {
    if (yearFlag == null) {
      yearFlag = "";
    }

    return yearFlag;
  }

  public void setYearFlag(String yearFlag) {
    this.yearFlag = yearFlag;
  }

}

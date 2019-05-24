package com.boco.eoms.workplan.vo;

public class TawwpExecuteCountVO {

  private String username; //用户
  private int dayExecute; //日常执行数量
  private int dutyExecute;//值班执行数量
  private String planName; //作业计划名称
  private String planId; //标识
  private TawwpMonthPlanVO tawwpMonthPlanVO;

  public int getDayExecute() {
    return dayExecute;
  }
  public void setDayExecute(int dayExecute) {
    this.dayExecute = dayExecute;
  }
  public String getUsername() {
    return username;
  }
  public void setDutyExecute(int dutyExecute) {
    this.dutyExecute = dutyExecute;
  }
  public int getDutyExecute() {
    return dutyExecute;
  }

  public String getPlanId() {
    return planId;
  }

  public String getPlanName() {
    return planName;
  }

  public TawwpMonthPlanVO getTawwpMonthPlanVO() {
    return tawwpMonthPlanVO;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPlanId(String planId) {
    this.planId = planId;
  }

  public void setPlanName(String planName) {
    this.planName = planName;
  }

  public void setTawwpMonthPlanVO(TawwpMonthPlanVO tawwpMonthPlanVO) {
    this.tawwpMonthPlanVO = tawwpMonthPlanVO;
  }

}

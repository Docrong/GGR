package com.boco.eoms.workplan.vo;

/**
 * <p>Title: 执行作业计划执行内容(单一用户)数据显示类</p>
 * <p>Description: 提供页面的所需的数据封装</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */
import java.util.List;

public class TawwpExecuteContentUserVO {

  private String id; //标识
  private String startDate; //计划开始时间
  private String name; //执行内容名称
  private String endDate; //计划结束时间
  private String crtime; //创建时间 （按创建时间排序）
  private String cruser; //执行人登录名
  private String userName; //执行人姓名
  private String stubUser; //代理执行人
  private String stubUserName; //代理执行人姓名
  private String content; //执行内容
  private String remark; //备注
  private String eligibility; //合格标志编号 0：合格 1：不合格
  private String eligibilityName; //合格标志名称
  private String executeFlag; //填写标志编号 0：未填写 1：按时填写 2：超时填写
  private String executeFlagName; //填写标志名称
  private String formId; //附加表格格式信息编号
  private String formName; //附加表格格式信息名称
  private String formDataId; //附加表格信息（附加表格字符串，用","分割）
  private int fileCount = 0; //附件数量
  private String fileName; //附件信息
  private String repReason;
  private String executeFlag2;
  private String executeFlag2Name;
  private String reason;
  private List executeFileListVO; //对应附件信息集合

  private String stubFlag; //代理标志

  //合格标志名称数组
  static public String[] ELIGIBILITY = {
      "合格", "不合格"};

  //填写标志名称数组
  static public String[] EXECUTEFLAG = {
      "未填写", "按时填写", "超时填写"};

  /**
   * 获取合格标志名称
   * @param _eligibility 合格标志编号
   * @return 合格标志名称
   */
  public static String getEligibilityName(int _eligibility) {
    //初始化数据
    String eligibilityName = "";
    //如果合格标志编号在合格标志范围以内
    if (_eligibility < TawwpExecuteContentUserVO.ELIGIBILITY.length &&
        _eligibility >= 0) {
      //取出合格标志显示名称
      eligibilityName = TawwpExecuteContentUserVO.ELIGIBILITY[_eligibility];
    }
    //返回合格标志名称
    return eligibilityName;
  }

  /**
   * 获取填写标志名称
   * @param _executeFlag 填写标志编号
   * @return 填写标志名称
   */
  public static String getExecuteFlagName(int _executeFlag) {
    //初始化数据
    String executeFlagName = "";
    //如果填写标志编号在填写标志范围以内
    if (_executeFlag < TawwpExecuteContentUserVO.EXECUTEFLAG.length &&
        _executeFlag >= 0) {
      //取出填写标志显示名称
      executeFlagName = TawwpExecuteContentUserVO.EXECUTEFLAG[_executeFlag];
    }
    //返回填写标志名称
    return executeFlagName;
  }

  //各属性对应的set、get方法
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

  public String getCruser() {
    if (cruser == null) {
      cruser = "";
    }

    return cruser;
  }

  public String getEligibility() {
    if (eligibility == null) {
      eligibility = "";
    }

    return eligibility;
  }

  public String getEligibilityName() {
    if (eligibilityName == null) {
      eligibilityName = "";
    }

    return eligibilityName;
  }

  public String getEndDate() {
    if (endDate == null) {
      endDate = "";
    }

    return endDate;
  }

  public String getExecuteFlag() {
    if (executeFlag == null) {
      executeFlag = "";
    }

    return executeFlag;
  }

  public String getExecuteFlagName() {
    if (executeFlagName == null) {
      executeFlagName = "";
    }

    return executeFlagName;
  }

  public String getFormDataId() {
    if (formDataId == null) {
      formDataId = "";
    }

    return formDataId;
  }

  public String getFormId() {
    if (formId == null) {
      formId = "";
    }

    return formId;
  }

  public String getFormName() {
    if (formName == null) {
      formName = "";
    }

    return formName;
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

  public String getStartDate() {
    if (startDate == null) {
      startDate = "";
    }

    return startDate;
  }

  public String getUserName() {
    if (userName == null) {
      userName = "";
    }

    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  public void setFormId(String formId) {
    this.formId = formId;
  }

  public void setFormDataId(String formDataId) {
    this.formDataId = formDataId;
  }

  public void setExecuteFlagName(String executeFlagName) {
    this.executeFlagName = executeFlagName;
  }

  public void setExecuteFlag(String executeFlag) {
    this.executeFlag = executeFlag;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public void setEligibilityName(String eligibilityName) {
    this.eligibilityName = eligibilityName;
  }

  public void setEligibility(String eligibility) {
    this.eligibility = eligibility;
  }

  public void setCruser(String cruser) {
    this.cruser = cruser;
  }

  public void setCrtime(String crtime) {
    this.crtime = crtime;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getStubUserName() {
    if (stubUserName == null) {
      stubUserName = "";
    }

    return stubUserName;
  }

  public String getStubUser() {
    if (stubUser == null) {
      stubUser = "";
    }

    return stubUser;
  }

  public void setStubUser(String stubUser) {
    this.stubUser = stubUser;
  }

  public void setStubUserName(String stubUserName) {
    this.stubUserName = stubUserName;
  }

  public String getName() {
    if (name == null) {
      name = "";
    }

    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List getExecuteFileListVO() {
    return executeFileListVO;
  }

  public void setExecuteFileListVO(List executeFileListVO) {
    this.executeFileListVO = executeFileListVO;
  }

  public int getFileCount() {
    return fileCount;
  }

  public void setFileCount(int fileCount) {
    this.fileCount = fileCount;
  }

  public String getStubFlag() {
    if (stubFlag == null) {
      stubFlag = "";
    }

    return stubFlag;
  }

  public void setStubFlag(String stubFlag) {
    this.stubFlag = stubFlag;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

public String getExecuteFlag2() {
	return executeFlag2;
}

public void setExecuteFlag2(String executeFlag2) {
	this.executeFlag2 = executeFlag2;
}

public String getExecuteFlag2Name() {
	return executeFlag2Name;
}

public void setExecuteFlag2Name(String executeFlag2Name) {
	this.executeFlag2Name = executeFlag2Name;
}

public String getRepReason() {
	return repReason;
}

public void setRepReason(String repReason) {
	this.repReason = repReason;
}

public String getReason() {
	return reason;
}

public void setReason(String reason) {
	this.reason = reason;
}

}

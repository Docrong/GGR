package com.boco.eoms.workplan.vo;

/**
 * <p>Title: 月度作业计划数据显示类</p>
 * <p>Description: 提供页面的所需的数据封装</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 * @author eoms
 * @version 1.0
 */
import java.util.List;
import java.util.Hashtable;
import com.boco.eoms.workplan.flow.model.Step;

public class TawwpMonthPlanVO {

  private String id; //标识
  private String name; //年度作业计划名称
  private String executeType; //填写类型编号(0：多人填写一份 1：多人填写多份 2：顺序填写)
  private String executeTypeName; //填写类型名称
  private String deptId; //所属部门编号
  private String deptName; //所属部门名称
  private String sysTypeId; //系统类型编号
  private String sysTypeName; //系统类型名称
  private String netTypeId; //网元类型编号
  private String netTypeName; //网元类型名称
  private String yearFlag; //年度标识
  private String monthFlag; //月度标识
  private String deleted; //删除标志
  private String cruser; //创建人用户名
  private String userName; //创建人姓名
  private String crtime; //创建时间
  private String constituteState; //制定状态编号 0：新建 1：通过 2：驳回 3：待审批
  private String constituteStateName; //制定状态名称
  private String monthCheckId; //作业计划审批编号

  //注意：当制定状态为1时,执行状态才能由0变成4

  private String executeState; //执行状态编号 0：未执行 1：归档 2：驳回 3：考核中 4：执行中
  private String executeStateName; //执行状态名称
  private String principal; //月度作业计划负责人用户名(负责填写周报月度和归档)
  private String principalName; //月度作业计划负责人姓名
  private String netId; //网元编号
  private String netName; //网元名称
  private String netSerialNo; //网元名称
  private String isApp; //是否进行月度送审 0: 不需要送审 1： 需要送审

  private Step step; //当前需要进行处理的审批流程

  //用于出完成率和及时率的3个属性
  private String totalCount; //需要执行的总数
  private String executedCount; //已经执行的总数
  private String inTimeCount; //及时执行的总数
  private String timeOutCount; //超时执行的总数

  private List monthExecuteVOList; //月度作业计划执行内容VO对象集合
  private List monthCheckVOList; //月度作业计划执行内容VO对象集合
  private Hashtable monthExecuteVOHash; //月度作业计划执行内容Hashtable对象集合
  private List executeReportVOList; //执行作业计划周报、月报VO对象集合
  private List executeAssessVOList; //执行作业计划考核VO对象集合

  private TawwpExecuteReportVO tawwpExecuteReportMonthVO; //月报信息

  private String stubFlag; //代理标志
  private String userByStub; //被当前用户代理用户的登录名
  private String monthExecuteUserId; //月度作业计划执行人信息编号
  private String monthExecuteUserState; //月度作业计划执行人信息状态

  private String unicomType; //联通分类 用于联通系统专用
  private String reportFlag;

  private String checkUsers; //审批人

  //制定状态名称数组
  static public String[] CONSTITUTESTATENAME = {
      "新建", "通过", "驳回", "审批中"};

  //执行状态名称数组
  static public String[] EXECUTESTATENAME = {
      "未执行", "归档", "驳回", "考核中", "执行中"};

  static public String[] MONTHREPORTSTATENAME = {
      "审批通过未提交", "提交总部成功", "提交总部失败"};

  //执行类型名称数组
  static public String[] EXECUTETYPENAME = {
      "多人填写一份", "多人填写多份", "顺序填写"};
  private String yearPlanId;//年度作业计划id
  /**
   * 获取制定状态名称
   * @param _constituteState 制定状态编号
   * @return 制定状态名称
   */
  public static String getConstituteStateName(int _constituteState) {
    //初始化数据
    String constituteStateName = "";

    //如果制定状态编号在制定状态范围以内
    if (_constituteState < TawwpMonthPlanVO.CONSTITUTESTATENAME.length &&
        _constituteState >= 0) {
      //取出制定状态显示名称
      constituteStateName = TawwpMonthPlanVO.CONSTITUTESTATENAME[
          _constituteState];
    }
    //返回制定状态名称
    return constituteStateName;
  }

  /**
   * 获取制定状态名称
   * @param _constituteState String 制定状态编号
   * @return String 制定状态名称
   */
  public static String getConstituteStateName(String _constituteState) {
    return getConstituteStateName(Integer.parseInt(_constituteState));
  }

  /**
   * 获取执行状态名称
   * @param _executeState 执行状态编号
   * @return 执行状态名称
   */
  public static String getExecuteStateName(int _executeState) {
    //初始化数据
    String executeStateName = "";

    //如果执行状态编号在执行状态范围以内
    if (_executeState < TawwpMonthPlanVO.EXECUTESTATENAME.length &&
        _executeState >= 0) {
      //取出执行状态显示名称
      executeStateName = TawwpMonthPlanVO.EXECUTESTATENAME[
          _executeState];
    }
    //返回执行状态名称
    return executeStateName;
  }

  /**
   * 获取执行状态名称
   * @param _executeState String 执行状态编号
   * @return String 执行状态名称
   */
  public static String getExecuteStateName(String _executeState) {
    return getExecuteStateName(Integer.parseInt(_executeState));
  }

  /**
   * 获取执行类型名称
   * @param _executeType 执行类型编号
   * @return 执行类型名称
   */
  public static String getExecuteTypeName(int _executeType) {
    //初始化数据
    String executeTypeName = "";

    //如果执行状态编号在执行状态范围以内
    if (_executeType < TawwpMonthPlanVO.EXECUTETYPENAME.length &&
        _executeType >= 0) {
      //取出执行状态显示名称
      executeTypeName = TawwpMonthPlanVO.EXECUTETYPENAME[
          _executeType];
    }
    //返回执行状态名称
    return executeTypeName;
  }

  
 
  /**
   * 获取执行类型名称
   * @param _executeType String 执行类型编号
   * @return String 执行类型名称
   */
  public static String getExecuteTypeName(String _executeType) {
    return getExecuteTypeName(Integer.parseInt(_executeType));
  }

  //以下为各属性set、get方法
  public String getConstituteState() {
    if (constituteState == null) {
      constituteState = "";
    }
    return constituteState;
  }

  public String getConstituteStateName() {
    if (constituteStateName == null) {
      constituteStateName = "";
    }

    return constituteStateName;
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

  public String getDeleted() {
    if (deleted == null) {
      deleted = "";
    }

    return deleted;
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

  public String getExecuteState() {
    if (executeState == null) {
      executeState = "";
    }

    return executeState;
  }

  public String getExecuteStateName() {
    if (executeStateName == null) {
      executeStateName = "";
    }

    return executeStateName;
  }

  public String getExecuteType() {
    if (executeType == null) {
      executeType = "";
    }

    return executeType;
  }

  public String getExecuteTypeName() {
    if (executeTypeName == null) {
      executeTypeName = "";
    }

    return executeTypeName;
  }

  public String getId() {
    if (id == null) {
      id = "";
    }

    return id;
  }

  public String getMonthFlag() {
    if (monthFlag == null) {
      monthFlag = "";
    }

    return monthFlag;
  }

  public String getName() {
    if (name == null) {
      name = "";
    }

    return name;
  }

  public String getNetId() {
    if (netId == null) {
      netId = "";
    }

    return netId;
  }

  public String getNetName() {
    if (netName == null) {
      netName = "";
    }

    return netName;
  }

  public String getNetTypeId() {
    if (netTypeId == null) {
      netTypeId = "";
    }

    return netTypeId;
  }

  public String getNetTypeName() {
    if (netTypeName == null) {
      netTypeName = "";
    }

    return netTypeName;
  }

  public String getPrincipal() {
    if (principal == null) {
      principal = "";
    }

    return principal;
  }

  public String getPrincipalName() {
    if (principalName == null) {
      principalName = "";
    }

    return principalName;
  }

  public String getSysTypeId() {
    if (sysTypeId == null) {
      sysTypeId = "";
    }

    return sysTypeId;
  }

  public String getSysTypeName() {
    if (sysTypeName == null) {
      sysTypeName = "";
    }

    return sysTypeName;
  }

  public String getUserName() {
    if (userName == null) {
      userName = "";
    }

    return userName;
  }

  public String getYearFlag() {
    if (yearFlag == null) {
      yearFlag = "";
    }

    return yearFlag;
  }

  public void setConstituteState(String constituteState) {
    this.constituteState = constituteState;
  }

  public void setConstituteStateName(String constituteStateName) {
    this.constituteStateName = constituteStateName;
  }

  public void setCrtime(String crtime) {
    this.crtime = crtime;
  }

  public void setCruser(String cruser) {
    this.cruser = cruser;
  }

  public void setDeleted(String deleted) {
    this.deleted = deleted;
  }

  public void setDeptId(String deptId) {
    this.deptId = deptId;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public void setExecuteState(String executeState) {
    this.executeState = executeState;
  }

  public void setExecuteStateName(String executeStateName) {
    this.executeStateName = executeStateName;
  }

  public void setExecuteType(String executeType) {
    this.executeType = executeType;
  }

  public void setExecuteTypeName(String executeTypeName) {
    this.executeTypeName = executeTypeName;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setMonthFlag(String monthFlag) {
    this.monthFlag = monthFlag;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setNetId(String netId) {
    this.netId = netId;
  }

  public void setNetName(String netName) {
    this.netName = netName;
  }

  public void setNetTypeId(String netTypeId) {
    this.netTypeId = netTypeId;
  }

  public void setNetTypeName(String netTypeName) {
    this.netTypeName = netTypeName;
  }

  public void setPrincipal(String principal) {
    this.principal = principal;
  }

  public void setPrincipalName(String principalName) {
    this.principalName = principalName;
  }

  public void setSysTypeId(String sysTypeId) {
    this.sysTypeId = sysTypeId;
  }

  public void setSysTypeName(String sysTypeName) {
    this.sysTypeName = sysTypeName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setYearFlag(String yearFlag) {
    this.yearFlag = yearFlag;
  }

  public List getMonthExecuteVOList() {
    return monthExecuteVOList;
  }

  public void setMonthCheckVOList(List monthCheckVOList) {
    this.monthCheckVOList = monthCheckVOList;
  }

  public void setMonthExecuteVOList(List monthExecuteVOList) {
    this.monthExecuteVOList = monthExecuteVOList;
  }

  public List getMonthCheckVOList() {
    return monthCheckVOList;
  }

  public List getExecuteReportVOList() {
    return executeReportVOList;
  }

  public List getExecuteAssessVOList() {
    return executeAssessVOList;
  }

  public void setExecuteAssessVOList(List executeAssessVOList) {
    this.executeAssessVOList = executeAssessVOList;
  }

  public void setExecuteReportVOList(List executeReportVOList) {
    this.executeReportVOList = executeReportVOList;
  }

  public Hashtable getMonthExecuteVOHash() {
    return monthExecuteVOHash;
  }

  public void setMonthExecuteVOHash(Hashtable monthExecuteVOHash) {
    this.monthExecuteVOHash = monthExecuteVOHash;
  }

  public String getInTimeCount() {
    if (inTimeCount == null) {
      inTimeCount = "";
    }

    return inTimeCount;
  }

  public void setInTimeCount(String inTimeCount) {
    this.inTimeCount = inTimeCount;
  }

  public String getExecutedCount() {
    if (executedCount == null) {
      executedCount = "";
    }

    return executedCount;
  }

  public void setExecutedCount(String executedCount) {
    this.executedCount = executedCount;
  }

  public String getTotalCount() {
    if (totalCount == null) {
      totalCount = "";
    }

    return totalCount;
  }

  public void setTotalCount(String totalCount) {
    this.totalCount = totalCount;
  }

  public String getTimeOutCount() {
    if (timeOutCount == null) {
      timeOutCount = "";
    }

    return timeOutCount;
  }

  public void setTimeOutCount(String timeOutCount) {
    this.timeOutCount = timeOutCount;
  }

  public TawwpExecuteReportVO getTawwpExecuteReportMonthVO() {
    return tawwpExecuteReportMonthVO;
  }

  public void setTawwpExecuteReportMonthVO(TawwpExecuteReportVO
                                           tawwpExecuteReportMonthVO) {
    this.tawwpExecuteReportMonthVO = tawwpExecuteReportMonthVO;
  }

  public Step getStep() {

    return step;
  }

  public void setStep(Step step) {
    this.step = step;
  }

  public String getMonthCheckId() {
    if (monthCheckId == null) {
      monthCheckId = "";
    }

    return monthCheckId;
  }

  public void setMonthCheckId(String monthCheckId) {
    this.monthCheckId = monthCheckId;
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

  public String getUserByStub() {
    if (userByStub == null) {
      userByStub = "";
    }

    return userByStub;
  }

  public void setUserByStub(String userByStub) {
    this.userByStub = userByStub;
  }

  public String getNetSerialNo() {
    if (netSerialNo == null) {
      netSerialNo = "";
    }

    return netSerialNo;
  }

  public void setNetSerialNo(String netSerialNo) {
    this.netSerialNo = netSerialNo;
  }

  public String getMonthExecuteUserId() {
    return monthExecuteUserId;
  }

  public String getMonthExecuteUserState() {
    return monthExecuteUserState;
  }

  public void setMonthExecuteUserId(String monthExecuteUserId) {
    this.monthExecuteUserId = monthExecuteUserId;
  }

  public void setMonthExecuteUserState(String monthExecuteUserState) {
    this.monthExecuteUserState = monthExecuteUserState;
  }

  public String[] getEXECUTESTATENAME() {
    return EXECUTESTATENAME;
  }

  public void setEXECUTESTATENAME(String[] EXECUTESTATENAME) {
    this.EXECUTESTATENAME = EXECUTESTATENAME;
  }

  public String getUnicomType() {
    return unicomType;
  }

  public void setUnicomType(String unicomType) {
    this.unicomType = unicomType;
  }

  public String getReportFlag() {
    return reportFlag;
  }

  public void setReportFlag(String reportFlag) {
    this.reportFlag = reportFlag;
  }

  public String getCheckUsers() {
    return checkUsers;
  }

  public void setCheckUsers(String checkUsers) {
    this.checkUsers = checkUsers;
  }

public String getIsApp() {
	return isApp;
}

public void setIsApp(String isApp) {
	this.isApp = isApp;
}

public String getYearPlanId() {
	return yearPlanId;
}

public void setYearPlanId(String yearPlanId) {
	this.yearPlanId = yearPlanId;
}

}

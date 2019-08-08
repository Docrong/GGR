package com.boco.eoms.workplan.vo;

/**
 * <p>Title: 执行作业计划执行内容(整体)数据显示类</p>
 * <p>Description: 提供页面的所需的数据封装</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

import java.util.List;

import com.boco.eoms.commons.system.dict.exceptions.DictServiceException;
import com.boco.eoms.commons.system.dict.util.DictMgrLocator;
import com.boco.eoms.commons.system.dict.util.Util;

public class TawwpExecuteContentVO {

    private String id; // 标识
    private String name; // 执行内容名称
    private String monthPlanNname; // 月计划名称
    private String netNname; // 网元名称
    private String botype; // 业务类型
    private String stubFlag; // 代理标志
    private String userByStub; // 被当前用户所代理的用户登录名

    private String writeDate; //填写时间
    private String fileFlag; // 是否上传附件
    private String fileFlagName;
    static public String[] ELIGIBILITY = {
            "合格", "不合格"};

    private String executedeptlevel; // 执行单位级别

    private String appdesc; // 适用说明

    private String startDate; // 计划开始时间

    private String endDate; // 计划结束时间

    private String crtime; // 创建时间 （按创建时间排序）

    private String cruser; // 执行人登录名

    private String userName; // 执行人姓名

    private String content; // 执行内容

    private String remark; // 备注

    private String extendremark;// 执行帮助

    private String cycle; // 周期

    private String deptId; // 部门

    private String cycleName; // 周期名称

    private String fileName; // 附件信息（附件名称字符串，用","分割）

    private String formId; // 附加表格格式信息

    private String formName; // 附加表格格式信息名称

    private String formDataId; // 附加表格信息（附加表格字符串，用","分割）

    private String workSheetId; // 工单编号（接口记录）

    private String eligibility; // 合格标志编号 0：合格 1：不合格

    private String eligibilityName; // 合格标志名称

    private String executeFlag; // 填写标志编号 0：未填写 1：按时填写 2：超时填写

    private String executeFlagName; // 填写标志编号名称

    private String checkContent; // 领导审批意见（备用属性）

    private String executeContentUserId; // 执行作业计划执行内容(单一用户)编号_用于批量填写页面显示

    private String executeType; // 月度作业计划的执行

    private String command; // 命令标识(智能巡检接口)

    private String commandName; // 命令名称(智能巡检接口)

    private int fileCount = 0; // 附件数量

    private List executeContentUserListVO; // 执行作业计划执行内容(单一用户)VO对象集合

    private List executeFileListVO; // 附件信息集合

    private String normalFlag; // 正常标志 0 不正常 ,1 正常

    private String normalFlagName; // 正常标志

    private String qualityCheckFlag; // 质检标志位 参数-""：不进行质检 "0"：未质检 "1"：质检通过 "2"：质检不通过

    private String qualityCheckFlagName; // 质检标志位

    private String qualityCheckDevice; // 质检意见

    private String qualityCheckUser; // 质检人

    private String qualityCheckUserName; // 质检人

    private String accessories; // 指导文件

    private String reason; //补填原因

    private String dailyExecuteFlagName;//日常执行当日状态名称

    // 填写标志名称数组
    static public String[] EXECUTEFLAG = {"未填写", "按时填写", "超时填写"};
    static public String[] DAILYEXECUTEFLAG = {"未填写", "已填写", "填写中"};

    /**
     * 获取合格标志名称
     *
     * @param _eligibility 合格标志编号
     * @return 合格标志名称
     */
    public static String getEligibilityName(String _eligibility) {
        // 初始化数据
        String eligibilityName = "";
        int eligibility = 0;
        try {
            eligibility = Integer.parseInt(_eligibility);
            // 如果合格标志编号在合格标志范围以内
            if (eligibility < TawwpExecuteContentVO.ELIGIBILITY.length
                    && eligibility >= 0) {
                // 取出合格标志显示名称
                eligibilityName = TawwpExecuteContentVO.ELIGIBILITY[eligibility];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回合格标志名称
        return eligibilityName;
    }

    /**
     * 获取填写标志名称
     *
     * @param _executeFlag 填写标志编号
     * @return 填写标志名称
     */
    public static String getExecuteFlagName(int _executeFlag) {
        // 初始化数据
        String executeFlagName = "";
        // 如果填写标志编号在填写标志范围以内
        if (_executeFlag < TawwpExecuteContentVO.EXECUTEFLAG.length
                && _executeFlag >= 0) {
            // 取出填写标志显示名称
            executeFlagName = TawwpExecuteContentVO.EXECUTEFLAG[_executeFlag];
        }
        // 返回填写标志名称
        return executeFlagName;
    }

    public static String getDailyExecuteFlagName(int _executeFlag) {
        // 初始化数据
        String dailyDxecuteFlagName = "";
        // 如果填写标志编号在填写标志范围以内
        if (_executeFlag < TawwpExecuteContentVO.DAILYEXECUTEFLAG.length
                && _executeFlag >= 0) {
            // 取出填写标志显示名称
            dailyDxecuteFlagName = TawwpExecuteContentVO.DAILYEXECUTEFLAG[_executeFlag];
        }
        // 返回填写标志名称
        return dailyDxecuteFlagName;
    }

    // 各属性对应的set、get方法
    public String getCheckContent() {
        if (checkContent == null) {
            checkContent = "";
        }

        return checkContent;
    }

    public String getContent() {
        if (content == null) {
            content = "";
        }

        return content;
    }

    public String getAccessories() {
        if (accessories == null) {
            accessories = "";
        }
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
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

    public List getExecuteContentUserListVO() {

        return executeContentUserListVO;
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

    public String getFileName() {
        if (fileName == null) {
            fileName = "";
        }

        return fileName;
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

    public String getWorkSheetId() {
        if (workSheetId == null) {
            workSheetId = "";
        }

        return workSheetId;
    }

    public void setWorkSheetId(String workSheetId) {
        this.workSheetId = workSheetId;
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

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public void setFormDataId(String formDataId) {
        this.formDataId = formDataId;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setExecuteFlagName(String executeFlagName) {
        this.executeFlagName = executeFlagName;
    }

    public void setExecuteFlag(String executeFlag) {
        this.executeFlag = executeFlag;
    }

    public void setExecuteContentUserListVO(List executeContentUserListVO) {
        this.executeContentUserListVO = executeContentUserListVO;
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

    public void setCheckContent(String checkContent) {
        this.checkContent = checkContent;
    }

    public String getCycle() {
        if (cycle == null) {
            cycle = "";
        }
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getCycleName() {
        if (cycleName == null) {
            cycleName = "";
        }

        return cycleName;
    }

    public void setCycleName(String cycleName) {
        this.cycleName = cycleName;
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

    public String getExecuteContentUserId() {
        if (executeContentUserId == null) {
            executeContentUserId = "";
        }

        return executeContentUserId;
    }

    public void setExecuteContentUserId(String executeContentUserId) {
        this.executeContentUserId = executeContentUserId;
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

    public String getExecuteType() {
        if (executeType == null) {
            executeType = "";
        }

        return executeType;
    }

    public void setExecuteType(String executeType) {
        this.executeType = executeType;
    }

    public List getExecuteFileListVO() {
        return executeFileListVO;
    }

    public void setExecuteFileListVO(List executeFileListVO) {
        this.executeFileListVO = executeFileListVO;
    }

    public String getFormName() {
        if (formName == null) {
            formName = "";
        }

        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
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

    public String getUserByStub() {
        if (userByStub == null) {
            userByStub = "";
        }

        return userByStub;
    }

    public void setUserByStub(String userByStub) {
        this.userByStub = userByStub;
    }

    public String getBotype() {
        return botype;
    }

    public void setBotype(String botype) {
        this.botype = botype;
    }

    public String getExecutedeptlevel() {
        return executedeptlevel;
    }

    public void setExecutedeptlevel(String executedeptlevel) {
        this.executedeptlevel = executedeptlevel;
    }

    public String getAppdesc() {
        return appdesc;
    }

    public void setAppdesc(String appdesc) {
        this.appdesc = appdesc;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public String getFileFlag() {
        return fileFlag;
    }

    public void setFileFlag(String fileFlag) {
        this.fileFlag = fileFlag;
    }

    public String getExtendremark() {
        return extendremark;
    }

    public void setExtendremark(String extendremark) {
        this.extendremark = extendremark;
    }

    public String getFileFlagName() {
        fileFlagName = "";
        if (fileFlag != null) {
            if (fileFlag.equals("1")) {
                fileFlagName = "是";
            } else if (fileFlag.equals("0")) {
                fileFlagName = "否";
            }
        }

        return fileFlagName;
    }

    public String getNormalFlag() {
        return normalFlag;
    }

    public void setNormalFlag(String normalFlag) {
        this.normalFlag = normalFlag;
    }

    public String getNormalFlagName() {
        String _normalFlagName = "";
        try {
            _normalFlagName = (String) DictMgrLocator.getDictService()
                    .itemId2name(
                            Util.constituteDictId("dict-workplan",
                                    "normalFlag"), normalFlag);
        } catch (DictServiceException e) {
            _normalFlagName = Util.idNoName();
        }
        return _normalFlagName;
    }

    public void setNormalFlagName(String normalFlag) {

        try {
            this.normalFlagName = (String) DictMgrLocator.getDictService()
                    .itemId2name(
                            Util.constituteDictId("dict-workplan",
                                    "normalFlag"), normalFlag);
        } catch (DictServiceException e) {
            this.normalFlagName = Util.idNoName();
        }

    }

    public String getMonthPlanNname() {
        return monthPlanNname;
    }

    public void setMonthPlanNname(String monthPlanNname) {
        this.monthPlanNname = monthPlanNname;
    }

    public void setFileFlagName(String fileFlagName) {
        this.fileFlagName = fileFlagName;
    }

    public String getNetNname() {
        return netNname;
    }

    public void setNetNname(String netNname) {
        this.netNname = netNname;
    }

    public String getQualityCheckFlag() {
        return qualityCheckFlag;
    }

    public void setQualityCheckFlag(String qualityCheckFlag) {
        this.qualityCheckFlag = qualityCheckFlag;
    }

    public String getQualityCheckFlagName() {
        if (qualityCheckFlag.equals("0")) {
            this.qualityCheckFlagName = "待质检";
        } else if (qualityCheckFlag.equals("1")) {
            this.qualityCheckFlagName = "质检通过";
        } else if (qualityCheckFlag.equals("2")) {
            this.qualityCheckFlagName = "质检不通过";
        }
        return qualityCheckFlagName;
    }

    public void setQualityCheckFlagName(String qualityCheckFlagName) {
        this.qualityCheckFlagName = qualityCheckFlagName;
    }

    public String getQualityCheckDevice() {
        return qualityCheckDevice;
    }

    public void setQualityCheckDevice(String qualityCheckDevice) {
        this.qualityCheckDevice = qualityCheckDevice;
    }

    public String getQualityCheckUser() {
        return qualityCheckUser;
    }

    public void setQualityCheckUser(String qualityCheckUser) {
        this.qualityCheckUser = qualityCheckUser;
    }

    public String getQualityCheckUserName() {
        return qualityCheckUserName;
    }

    public void setQualityCheckUserName(String qualityCheckUserName) {
        this.qualityCheckUserName = qualityCheckUserName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDailyExecuteFlagName() {
        return dailyExecuteFlagName;
    }

    public void setDailyExecuteFlagName(String dailyExecuteFlagName) {
        this.dailyExecuteFlagName = dailyExecuteFlagName;
    }
}

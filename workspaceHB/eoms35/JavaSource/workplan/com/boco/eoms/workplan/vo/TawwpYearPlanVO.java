package com.boco.eoms.workplan.vo;

/**
 * <p>Title: 年度作业计划数据显示类</p>
 * <p>Description: 提供页面的所需的数据封装</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

import com.boco.eoms.workplan.flow.model.Step;

public class TawwpYearPlanVO {

    private String id; //标识
    private String name; //年度作业计划名称
    private String deptId; //所属部门
    private String deptName; //所属部门名称
    private String yearFlag; //年度标识
    private String sysTypeId; //系统类别
    private String sysTypeName; //系统类别名称
    private String netTypeId; //网元类型
    private String netTypeName; //网元类型名称
    private String deleted; //删除标志
    private String cruser; //创建人
    private String cruserName; //创建人
    private String crtime; //创建时间
    private String content; //描述内容
    private String remark; //备注
    private String netList; //对应的网元信息
    private String netListName; //对应的网元信息
    private String state; //状态 0：新建 1：通过 2：驳回 3：待审批
    private String stateName; //状态 0：新建 1：通过 2：驳回 3：待审批
    private String yearCheckId; //作业计划审批编号
    private String modelPlanId; //模版名称
    private String isApp;        //是否审批
    private String userId;    //审核人

    private Step step; //当前需要进行处理的审批流程

    private List yearExecuteList; //作业计划模版执行内容集合
    private List yearCheckList; //作业计划模版审批集合
    private Hashtable monthPlanHash; //对应的月度作业计划集合（以部门当KEY值）
    private String unicomType; //联通分类 用于联通系统专用
    private String reportFlag; //联通上报标识 联通系统专用

    private String checkUsers; //审批人

    //制定状态名称数组
    static public String[] CONSTITUTESTATENAME = {
            "新建", "通过", "驳回", "审批中"};
    static public String[] REPORTSTATENAME = {
            "审批通过未提交", "提交总部成功", "提交总部失败"
    };

    public String getName() {
        if (name == null) {
            name = "";
        }
        return name;
    }

    public String getDeptId() {
        if (deptId == null) {
            deptId = "";
        }
        return deptId;
    }

    public String getState() {
        if (state == null) {
            state = "";
        }
        return state;
    }

    public String getNetTypeId() {
        if (netTypeId == null) {
            netTypeId = "";
        }

        return netTypeId;
    }

    public String getYearFlag() {
        if (yearFlag == null) {
            yearFlag = "";
        }

        return yearFlag;
    }

    public String getId() {
        if (id == null) {
            id = "";
        }

        return id;
    }

    public String getCrtime() {
        if (crtime == null) {
            crtime = "";
        }

        return crtime;
    }

    public String getRemark() {
        if (remark == null) {
            remark = "";
        }

        return remark;
    }

    public String getNetList() {
        if (netList == null) {
            netList = "";
        }

        return netList;
    }

    public String getContent() {
        if (content == null) {
            content = "";
        }

        return content;
    }

    public String getCruser() {
        if (cruser == null) {
            cruser = "";
        }

        return cruser;
    }

    public String getSysTypeId() {
        if (sysTypeId == null) {
            sysTypeId = "";
        }

        return sysTypeId;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setNetTypeId(String netTypeId) {
        this.netTypeId = netTypeId;
    }

    public void setYearFlag(String yearFlag) {
        this.yearFlag = yearFlag;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCrtime(String crtime) {
        this.crtime = crtime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setNetList(String netList) {
        this.netList = netList;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCruser(String cruser) {
        this.cruser = cruser;
    }

    public void setSysTypeId(String sysTypeId) {
        this.sysTypeId = sysTypeId;
    }

    public void setYearExecuteList(List yearExecuteList) {
        this.yearExecuteList = yearExecuteList;
    }

    public String getDeleted() {
        if (deleted == null) {
            deleted = "";
        }

        return deleted;
    }

    public List getYearExecuteList() {
        return yearExecuteList;
    }

    public Hashtable getMonthPlanHash() {
        return monthPlanHash;
    }

    public void setMonthPlanHash(Hashtable monthPlanHash) {
        this.monthPlanHash = monthPlanHash;
    }

    public List getYearCheckList() {
        return yearCheckList;
    }

    public void setYearCheckList(List yearCheckList) {
        this.yearCheckList = yearCheckList;
    }

    public Step getStep() {
        return step;
    }

    public String getNetListName() {
        if (netListName == null) {
            netListName = "";
        }

        return netListName;
    }

    public String getSysTypeName() {
        if (sysTypeName == null) {
            sysTypeName = "";
        }

        return sysTypeName;
    }

    public String getNetTypeName() {
        if (netTypeName == null) {
            netTypeName = "";
        }

        return netTypeName;
    }

    public String getDeptName() {
        if (deptName == null) {
            deptName = "";
        }

        return deptName;
    }

    public String getCruserName() {
        if (cruserName == null) {
            cruserName = "";
        }

        return cruserName;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public void setNetListName(String netListName) {
        this.netListName = netListName;
    }

    public void setSysTypeName(String sysTypeName) {
        this.sysTypeName = sysTypeName;
    }

    public void setNetTypeName(String netTypeName) {
        this.netTypeName = netTypeName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setCruserName(String cruserName) {
        this.cruserName = cruserName;
    }

    public String getStateName() {
        if (stateName == null) {
            stateName = "";
        }

        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getYearCheckId() {
        if (yearCheckId == null) {
            yearCheckId = "";
        }

        return yearCheckId;
    }

    public String getModelPlanId() {
        if (modelPlanId == null) {
            modelPlanId = "";
        }

        return modelPlanId;
    }

    public void setYearCheckId(String yearCheckId) {
        this.yearCheckId = yearCheckId;
    }

    public void setModelPlanId(String modelPlanId) {
        this.modelPlanId = modelPlanId;
    }

    public String getReportFlag() {
        return reportFlag;
    }

    public void setReportFlag(String reportFlag) {
        this.reportFlag = reportFlag;
    }

    public String getUnicomType() {
        return unicomType;
    }

    public void setUnicomType(String unicomType) {
        this.unicomType = unicomType;
    }

    public String getCheckUsers() {
        return checkUsers;
    }

    public void setCheckUsers(String checkUsers) {
        this.checkUsers = checkUsers;
    }

    public TawwpYearPlanVO() {
    }

    public TawwpYearPlanVO(boolean _flag) {
        if (_flag) {
            yearExecuteList = new ArrayList();
            yearCheckList = new ArrayList();
        }
    }

    public String getIsApp() {
        return isApp;
    }

    public void setIsApp(String isApp) {
        this.isApp = isApp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}

package com.boco.eoms.workplan.vo;

/**
 * <p>Title: 作业计划代理信息数据显示类</p>
 * <p>Description: 提供页面的所需的数据封装</p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: boco</p>
 *
 * @author eoms
 * @version 1.0
 */

public class TawwpStubUserVO {

    private String id; //标识
    private String cruser; //被代理人
    private String cruserName; //被代理人
    private String stubuser; //代理人
    private String stubuserName; //代理人
    private String startDate; //代理开始时间
    private String endDate; //代理结束时间
    private String content; //描述
    private String checkuser; //审批人
    private String checkuserName; //审批人
    private String state; //代理标志 0：新建 1：代理中 2：审批中 3：驳回 4：删除
    private String stateName; //代理标志 0：新建 1：代理中 2：审批中 3：驳回 4：删除

    //执行类型名称数组
    public static String[] STATETPYE = {
            "新建", "代理中", "审批中", "驳回", "删除"};

    public String getId() {
        if (id == null) {
            id = "";
        }

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCruser() {
        if (cruser == null) {
            cruser = "";
        }

        return cruser;
    }

    public void setCruser(String cruser) {
        this.cruser = cruser;
    }

    public String getCruserName() {
        if (cruserName == null) {
            cruserName = "";
        }

        return cruserName;
    }

    public void setCruserName(String cruserName) {
        this.cruserName = cruserName;
    }

    public void setStubuser(String stubuser) {
        this.stubuser = stubuser;
    }

    public String getStubuser() {
        if (stubuser == null) {
            stubuser = "";
        }

        return stubuser;
    }

    public String getStubuserName() {
        if (stubuserName == null) {
            stubuserName = "";
        }

        return stubuserName;
    }

    public void setStubuserName(String stubuserName) {
        this.stubuserName = stubuserName;
    }

    public String getCheckuser() {
        if (checkuser == null) {
            checkuser = "";
        }

        return checkuser;
    }

    public void setCheckuser(String checkuser) {
        this.checkuser = checkuser;
    }

    public String getCheckuserName() {
        if (checkuserName == null) {
            checkuserName = "";
        }

        return checkuserName;
    }

    public void setCheckuserName(String checkuserName) {
        this.checkuserName = checkuserName;
    }

    public String getContent() {
        if (content == null) {
            content = "";
        }

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEndDate() {
        if (endDate == null) {
            endDate = "";
        }

        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public String getState() {
        if (state == null) {
            state = "";
        }

        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    /**
     * 获取代理状态名称
     *
     * @param _stateType String 代理状态标识
     * @return String 代理状态名称
     */
    public static String getStateTypeName(String _stateType) {
        int stateType = Integer.parseInt(_stateType);
        //初始化数据
        String stateTypeName = "";
        //如果日志类型编号在执行人类型范围以内
        if (stateType < TawwpStubUserVO.STATETPYE.length && stateType >= 0) {
            //取出日志类型显示名称
            stateTypeName = TawwpStubUserVO.STATETPYE[stateType];
        }
        //返回日志类型名称
        return stateTypeName;
    }

}

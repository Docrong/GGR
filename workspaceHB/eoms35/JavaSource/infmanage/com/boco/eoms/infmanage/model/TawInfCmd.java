package com.boco.eoms.infmanage.model;

public class TawInfCmd
{
    // 命令所属交换机
    private String cmdSwich;

    // 命令名称
    private String cmdName;

    // 命令参数
    private String cmdParam;

    // 参数的取值范围
    private String paramScope;

    // 命令的基本描述
    private String cmdDes;

    // 记录ID
    private int id;

    // 命令编号
    private String cmdId;

    // 部门ID
    private int deptId;

    public TawInfCmd()
    {
    }

    public String getCmdSwich()
    {
        return cmdSwich;
    }

    public void setCmdSwich(String cmdSwich)
    {
        this.cmdSwich = cmdSwich;
    }

    public String getCmdId()
    {
        return cmdId;
    }

    public void setCmdId(String cmdId)
    {
        this.cmdId = cmdId;
    }

    public String getCmdName()
    {
        return cmdName;
    }

    public void setCmdName(String cmdName)
    {
        this.cmdName = cmdName;
    }

    public String getCmdParam()
    {
        return cmdParam;
    }

    public void setCmdParam(String cmdParam)
    {
        this.cmdParam = cmdParam;
    }

    public String getParamScope()
    {
        return paramScope;
    }

    public void setParamScope(String paramScope)
    {
        this.paramScope = paramScope;
    }

    public String getCmdDes()
    {
        return cmdDes;
    }

    public void setCmdDes(String cmdDes)
    {
        this.cmdDes = cmdDes;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
    public int getDeptId()
    {
        return deptId;
    }
    public void setDeptId(int deptId)
    {
        this.deptId = deptId;
    }

}
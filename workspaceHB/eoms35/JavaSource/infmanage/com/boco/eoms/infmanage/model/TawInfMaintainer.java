package com.boco.eoms.infmanage.model;

public class TawInfMaintainer
{
    // 维护人员编号
    private int maintainerId;

    // 维护人员名称
    private String maintainerName;

    // 维护人员性别
    private String maintainerSex;

    // 所属部门编号
    private int deptId;

    // 所属部门名称
    private String deptName;

    // 联系电话
    private String tele;

    // 移动电话
    private String teleMobile;

    // 家庭电话
    private String teleHome;

    // E-Mail
    private String email;

    // 个人专长
    private String special;

    public TawInfMaintainer()
    {
    }

    public int getMaintainerId()
    {
        return maintainerId;
    }

    public void setMaintainerId(int maintainerId)
    {
        this.maintainerId = maintainerId;
    }

    public String getMaintainerName()
    {
        return maintainerName;
    }

    public void setMaintainerName(String maintainerName)
    {
        this.maintainerName = maintainerName;
    }

    public String getMaintainerSex()
    {
        return maintainerSex;
    }

    public void setMaintainerSex(String maintainerSex)
    {
        this.maintainerSex = maintainerSex;
    }

    public int getDeptId()
    {
        return deptId;
    }

    public void setDeptId(int deptId)
    {
        this.deptId = deptId;
    }

    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public String getTele()
    {
        return tele;
    }

    public void setTele(String tele)
    {
        this.tele = tele;
    }

    public String getTeleMobile()
    {
        return teleMobile;
    }

    public void setTeleMobile(String teleMobile)
    {
        this.teleMobile = teleMobile;
    }

    public String getTeleHome()
    {
        return teleHome;
    }

    public void setTeleHome(String teleHome)
    {
        this.teleHome = teleHome;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSpecial()
    {
        return special;
    }

    public void setSpecial(String special)
    {
        this.special = special;
    }

}

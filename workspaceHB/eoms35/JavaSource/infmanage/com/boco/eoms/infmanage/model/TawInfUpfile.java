package com.boco.eoms.infmanage.model;

public class TawInfUpfile
{
    // 上传文件编号
    private int id;

    // 与资料信息表的记录ID关联
    private int infoId;

    // 上传文件名称
    private String infUpfileName;
    private String encodename;

    public TawInfUpfile()
    {
    }
    public int getInfoId()
    {
        return infoId;
    }
    public void setInfoId(int infoId)
    {
        this.infoId = infoId;
    }
    public String getInfUpfileName()
    {
        return infUpfileName;
    }
    public void setInfUpfileName(String infUpfileName)
    {
        this.infUpfileName = infUpfileName;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getEncodename()
    {
        return encodename;
    }
    public void setEncodename(String encodename)
    {
        this.encodename = encodename;
    }

}
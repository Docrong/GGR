package com.boco.eoms.sparepart.model;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */


public class TawSpDemo
{

    private int id;
    private int parentId;
    private String layer;
    private int radix;
    private String cname;
    private String ename;
    private String code;
    private String note;
    private int deleted;

    public TawSpDemo()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getParentId()
    {
        return parentId;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }

    public String getLayer()
    {
        return layer;
    }

    public void setLayer(String layer)
    {
        this.layer = layer;
    }

    public int getRadix()
    {
        return radix;
    }

    public void setRadix(int radix)
    {
        this.radix = radix;
    }

    public String getCname()
    {
        return cname;
    }

    public void setCname(String cname)
    {
        this.cname = cname;
    }

    public String getEname()
    {
        return ename;
    }

    public void setEname(String ename)
    {
        this.ename = ename;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public int getDeleted()
    {
        return deleted;
    }

    public void setDeleted(int deleted)
    {
        this.deleted = deleted;
    }
}

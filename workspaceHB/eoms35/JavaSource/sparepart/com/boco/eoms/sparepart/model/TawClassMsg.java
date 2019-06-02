package com.boco.eoms.sparepart.model;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawClassMsg{
    private String note;
    private String cname;
    private int deleted;
    private int parentId;
    private int id;
    private String ename;
    public TawClassMsg(){
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note=note;
    }

    public String getCname(){
        return cname;
    }

    public void setCname(String cname){
        this.cname=cname;
    }

    public int getDeleted(){
        return deleted;
    }

    public void setDeleted(int deleted){
        this.deleted=deleted;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getEname(){
        return ename;
    }

    public void setEname(String ename){
        this.ename=ename;
    }

    public int getParentId(){
        return parentId;
    }

    public void setParentId(int parentId){
        this.parentId=parentId;
    }

}

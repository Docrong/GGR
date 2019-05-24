package com.boco.eoms.sparepart.model;

/**
 * <p>Title: EOMS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: BOCO</p>
 * @author HAO
 * @version 2.0
 */

public class TawStorage{
    private String note;
    private String storagename;
    private int id;
    private String deptname;
    private String deptid;
    public TawStorage(){
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note=note;
    }

    public String getStoragename(){
        return storagename;
    }

  public String getDeptid() {
    return deptid;
  }

  public String getDeptname() {
    return deptname;
  }

  public void setStoragename(String storagename){
        this.storagename=storagename;
    }

  public void setDeptid(String deptid) {
    this.deptid = deptid;
  }

  public void setDeptname(String deptname) {
    this.deptname = deptname;
  }

}
